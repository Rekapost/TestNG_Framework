
package testCases;
import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.util.concurrent.TimeUnit;

import org.apache.commons.lang3.RandomStringUtils;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.PageLoadStrategy;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.io.FileHandler;
import org.testng.ITestResult;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Listeners;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;

import com.aventstack.chaintest.service.ChainPluginService;

import utilities.ConfigReader;
import utilities.chainTestListener;

@Listeners(chainTestListener.class)
public class BaseClass {
    ConfigReader readConfig = new ConfigReader();
    public static WebDriver driver;
    public static Logger logger;
     
    // Configuration constants
    public String browserType;
    public boolean isLambdaTest;
    public String APP_URL = readConfig.getApplicationUrl();
    public String USERNAME = readConfig.getUsername();
    public String PASSWORD = readConfig.getPassword();
   
    @BeforeClass
    @Parameters({"browser", "isLambdaTest", "isHeadless"})
    public void setup(@Optional("chrome") String browser, 
                      @Optional("false") boolean isLambdaTest, 
                      @Optional("false") boolean isHeadless,
                      ITestResult result) throws MalformedURLException {
        this.browserType = browser;
        this.isLambdaTest = isLambdaTest;

        // Initialize logger
        logger = Logger.getLogger("nopCommerce");
        PropertyConfigurator.configure("src/test/resources/log4j.properties");
        // Initialize browser session
        if (isLambdaTest) {
            //initializeLambdaTestSession(browser, result.getMethod().getMethodName());
            //initializeLambdaTestSession(browser, "DefaultTestMethodName"); // Use default or a placeholder test name here
            // Dynamically use the class name or a default method name if required
            String testName = this.getClass().getSimpleName(); // Or any custom naming logic
            //String testName = this.getClass().getSimpleName() + "_" + result.getMethod().getMethodName();
            initializeLambdaTestSession(browser, testName);
        } else {
            initializeLocalDriver(browser, isHeadless);
        }
        // System info for reports
        ChainPluginService.getInstance().addSystemInfo("Build#", "1.0");
        ChainPluginService.getInstance().addSystemInfo("Owner Name#", "Reka");
        driver.get(APP_URL);
    }

    public void initializeLambdaTestSession(String browser, String testName) {
        
        driver = utilities.LambdaTestUtility.initializeLambdaTestSession(browser, testName);
        
    }

    public void initializeLocalDriver(String browser, boolean isHeadless) {
        //BrowserUtility browserUtility = new BrowserUtility(browser);
        //driver = browserUtility.getDriver();
        //setDriverOptions(browser, isHeadless);
        if (browser.equalsIgnoreCase("chrome")) {            
            ChromeOptions options = new ChromeOptions();
            if (isHeadless) {
                options.addArguments("--headless=new");  // Use the updated headless mode
            }
            options.addArguments("--no-sandbox");  // Disable the sandbox for root user
            options.addArguments("--disable-dev-shm-usage");  // Overcome limited resource problems
            options.addArguments("--disable-gpu");  // Disable GPU (recommended for headless)
            options.addArguments("--remote-allow-origins=*"); // Avoid cross-origin issues

            driver = new ChromeDriver(options);
            
        }

    /*    Map<String, Consumer<Boolean>> browserMap = Map.of(
        "chrome", this::initializeChromeDriver,
        "firefox", this::initializeFirefoxDriver,
        "edge", this::initializeEdgeDriver
        );
        browserMap.get(browser.toLowerCase()).accept(isHeadless);
    */
    }

    public void setDriverOptions(String browser, boolean isHeadless) {
        if (browser.equalsIgnoreCase("chrome")) {
            initializeChromeDriver(isHeadless);
        } else if (browser.equalsIgnoreCase("firefox")) {
            initializeFirefoxDriver();
        } else if (browser.equalsIgnoreCase("edge")) {
            initializeEdgeDriver();
        }
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
        driver.manage().timeouts().pageLoadTimeout(60, TimeUnit.SECONDS);
    }

    public void initializeChromeDriver(boolean isHeadless) {
        ChromeOptions options = new ChromeOptions();
        if (isHeadless) {
            options.addArguments("--headless", "--disable-gpu", "--no-sandbox", "--remote-allow-origins=*");
        }
        setDriverPath("chrome");
        options.setPageLoadStrategy(PageLoadStrategy.NORMAL);
        options.setAcceptInsecureCerts(true);
        driver = new ChromeDriver(options);
    }

    public void initializeFirefoxDriver() {
        FirefoxOptions options = new FirefoxOptions();
        options.setPageLoadStrategy(PageLoadStrategy.NORMAL);
        options.setAcceptInsecureCerts(true);
        driver = new FirefoxDriver(options);
    }

    public void initializeEdgeDriver() {
        EdgeOptions options = new EdgeOptions();
        options.setPageLoadStrategy(PageLoadStrategy.NORMAL);
        options.setAcceptInsecureCerts(true);
        driver = new EdgeDriver(options);
    }

    public void setDriverPath(String browser) {
    //    String driverPath = browser.equalsIgnoreCase("chrome") ? readConfig.getChromePath() :
    //                         browser.equalsIgnoreCase("firefox") ? readConfig.getGeckoPath() : readConfig.getEdgePath();
    //System.setProperty("webdriver." + browser.toLowerCase() + ".driver", driverPath);
    String driverPath = "C:\\Users\\nreka\\vscodedevops\\TestNG-Framework\\src\\test\\resources\\ChromeDriver\\chromedriver.exe"; // Update this path
    System.setProperty("webdriver.chrome.driver", driverPath);     
    }

    @AfterMethod
    public void attachScreenshot(ITestResult result) {
        if (!result.isSuccess()) {
            captureScreenshot(driver, result.getMethod().getMethodName());
            chainTestListener.log("Test failed: " + result.getMethod().getMethodName());
            chainTestListener.embed(getScreenshotBytes(), "image/png");
        }
    }

    @AfterClass
    public void tearDown() {
        if (isLambdaTest) {
            utilities.LambdaTestUtility.quitSession();
        } else {
            driver.quit();
        }
    }

    public byte[] getScreenshotBytes() {
        TakesScreenshot screenshot = (TakesScreenshot) driver;
        return screenshot.getScreenshotAs(OutputType.BYTES);
    }

    public void captureScreenshot(WebDriver driver, String testName) {
        try {
            TakesScreenshot screenshot = (TakesScreenshot) driver;
            File sourceFile = screenshot.getScreenshotAs(OutputType.FILE);
            //File destinationFile = new File("Screenshots/" + testName + ".png");
            File destinationFile = new File("Screenshots/" + testName + "_" + RandomStringUtils.randomAlphanumeric(5) + ".png");

            FileHandler.copy(sourceFile, destinationFile);
            logger.info("Screenshot captured for test: " + testName);
        } catch (IOException e) {
            logger.error("Error capturing screenshot: " + e.getMessage());
        }
    }

    public String randomString() {
        return RandomStringUtils.randomAlphabetic(5);
    }

    public static String randomNumber() {
        return RandomStringUtils.randomNumeric(4);
    }
}
