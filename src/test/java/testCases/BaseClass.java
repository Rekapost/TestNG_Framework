package testCases;
import java.io.File;
import java.io.IOException;
import java.time.Duration;
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
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import io.github.bonigarcia.wdm.WebDriverManager;
import utilities.ConfigReader;
import utilities.Loggerload;

public class BaseClass  {
	public static WebDriver driver;
	ConfigReader readConfig=new ConfigReader();
//	public  String BROWSER="chrome";
//	public  String CHROME_DRIVER="webdriver.chrome.driver";
//	public  String DRIVER_LOCATION="C:\\Users\\Reka\\eclipse-workspace\\CucumberBDD\\src\\test\\java\\drivers\\chromedriver.exe";
//	public  String APP_URL="https://admin-demo.nopcommerce.com/";
//	public  String USERNAME="admin@yourstore.com";
//	public  String PASSWORD="admin";
	
	//public  String BROWSER=readConfig.getBrowserType();
	public  String CHROME_DRIVER="webdriver.chrome.driver";
	public  String CHROME_DRIVER_LOCATION=readConfig.getChromePath();
	public  String FIREFOX_DRIVER_LOCATION=readConfig.getGeckoPath();
	public  String EDGE_DRIVER_LOCATION=readConfig.getEdgePath();
	public  String IE_DRIVER_LOCATION=readConfig.internetExplorerPath();
	public  String APP_URL=readConfig.getApplicationUrl();
	public  String USERNAME=readConfig.getUsername();
	public  String PASSWORD=readConfig.getPassword();
	public static Logger logger;
	
//	FIREFOX_DRIVER_LOCATION=./Drivers\\firefoxdriver.exe   ./ represents current project home directory
//	EDGE_DRIVER_LOCATION=./Drivers\\edgedriver.exe
//	System.getProperty("user.dir")= java class only   ==  ./
//	./   java class and properties file 
	
	@Parameters("browser")    // passing browser type through testNg.xml file 
	@BeforeClass
	public void setup(String br)  // so passing that parameter browser as br 
	//public void setup()  
	{			
				// LOG4J LOGGER CONFIGURATION
				logger=Logger.getLogger("nopCommerce");  // create object for Logger class
				//PropertyConfigurator.configure("log4j.properties");
			PropertyConfigurator.configure("src/test/resources/log4j.properties");
			
			//else if(browser.equalsIgnoreCase("chrome")){
		if(br.equalsIgnoreCase("chrome"))
		{
		//	System.setProperty(CHROME_DRIVER,DRIVER_LOCATION);	
		//	System.setProperty(CHROME_DRIVER, System.getProperty("user.dir")+"//drivers//chromedriver.exe");		
		//	driver=new ChromeDriver();  // instantiate chromedriver
//		WebDriverManager.chromedriver().setup();
//		ChromeOptions chromeOptions = new ChromeOptions();
//		chromeOptions.setPageLoadStrategy(PageLoadStrategy.NORMAL);
//		chromeOptions.setAcceptInsecureCerts(true);
//		chromeOptions.setScriptTimeout(Duration.ofSeconds(30));
//		chromeOptions.setPageLoadTimeout(Duration.ofMillis(30000));
//		chromeOptions.setImplicitWaitTimeout(Duration.ofSeconds(20));
//		chromeOptions.addArguments("--remote-allow-origins=*");	  
//		driver =new ChromeDriver(chromeOptions);	
			
//		WebDriverManager.chromedriver().clearDriverCache().setup();	
//	    driver = WebDriverManager.chromedriver().create();
			
			System.setProperty("webdriver.chrome.driver", "Chrome_Driver_126/chromedriver.exe");
			ChromeOptions chromeOptions = new ChromeOptions();
			chromeOptions.setPageLoadStrategy(PageLoadStrategy.NORMAL);
			chromeOptions.setAcceptInsecureCerts(true);
			chromeOptions.setScriptTimeout(Duration.ofSeconds(30));
			chromeOptions.setPageLoadTimeout(Duration.ofMillis(30000));
			chromeOptions.setImplicitWaitTimeout(Duration.ofSeconds(30));
			chromeOptions.addArguments("--remote-allow-origins=*");
			driver =new ChromeDriver(chromeOptions);
			driver.manage().deleteAllCookies();
		}
		
		else if(br.equalsIgnoreCase("firefox")){
			Loggerload.info("Testing on firefox");
			//System.setProperty("webdriver.gecko.driver",FIREFOX_DRIVER_LOCATION );
			//System.setProperty(CHROME_DRIVER, System.getProperty("user.dir")+"//drivers//chromedriver.exe");	
			//driver =new FirefoxDriver();
			WebDriverManager.firefoxdriver().setup();
			FirefoxOptions firefoxOptions = new FirefoxOptions();
			firefoxOptions.setPageLoadStrategy(PageLoadStrategy.NORMAL);
			firefoxOptions.setAcceptInsecureCerts(true);
			firefoxOptions.setScriptTimeout(Duration.ofSeconds(30));
			firefoxOptions.setPageLoadTimeout(Duration.ofMillis(30000));
			firefoxOptions.setImplicitWaitTimeout(Duration.ofSeconds(20));			  
			driver =new FirefoxDriver(firefoxOptions);				
		}
		 
		 else if (br.equalsIgnoreCase("edge")) {
			Loggerload.info("Testing on Edge");
			 //System.setProperty("webdriver.edge.driver",EDGE_DRIVER_LOCATION );
			 //driver = new EdgeDriver();
			   
			 	WebDriverManager.edgedriver().setup();
				EdgeOptions edgeOptions = new EdgeOptions();
				edgeOptions.setPageLoadStrategy(PageLoadStrategy.NORMAL);
				edgeOptions.setAcceptInsecureCerts(true);
				edgeOptions.setScriptTimeout(Duration.ofSeconds(30));
				edgeOptions.setPageLoadTimeout(Duration.ofMillis(30000));
				edgeOptions.setImplicitWaitTimeout(Duration.ofSeconds(20));				  
				driver =new EdgeDriver(edgeOptions);		
		}
		driver.manage().timeouts().implicitlyWait(10,TimeUnit.SECONDS);
		driver.get(APP_URL);
		
	}	
	
	@AfterClass
	public void tearDown()
	{
		//driver.quit();	  ************************	
	}
	
	public void captureScreen(WebDriver driver, String tname) throws IOException
	{
	    TakesScreenshot screenshot=(TakesScreenshot) driver;
	    File sourceFile=screenshot.getScreenshotAs(OutputType.FILE);
		File destinationFile = new File("Screenshots/Screenshots"+tname+".png");  
		//FileUtils.copyFile(sourceFile3, destinationFile3); 
		FileHandler.copy(sourceFile, destinationFile);
		System.out.println("Screenshot Taken");
	}	
	
	public String random_String() {		
	String generatedString= RandomStringUtils.randomAlphabetic(5);  // 5 character string will be generated
	return generatedString;
// in test case 
// 	String email=random_String()+"@gmail.com";
//  addCust.custemailid(email);
//	}
	}
	
	public static String random_Number() {		
		String generatedString2= RandomStringUtils.randomNumeric(4);  // 4 digits will be generated
		return generatedString2;
	// in test case 
		}
	
/*	// to validate if particular page has any message like succcesfully registered 
	boolean res=driver.getPageSource().contains("Customer Registered Successfully!!!");
	if(res==true)
	{
		Assert.assertTrue(true);
	}
	else
	{ 
		captureScreen(driver, "addNewCustomer");  // testcasename
		Assert.assertTrue(false);	
	}
*/	

}
//run.bat
// bat file is batch file , executable file
// cd C:\Users\Reka\eclipse-workspace\TestNG_Framework\Reka.TestNG_Framework_DDT
//C:\Users\Reka\eclipse-workspace\TestNG_Framework\Reka.TestNG_Framework_DDT>mvn clean install
