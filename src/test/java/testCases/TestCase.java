package testCases;
import java.io.IOException;

import org.apache.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import io.qameta.allure.testng.AllureTestNg;
import pageObjects.LoginPage;
import utilities.Loggerload;

@Listeners(AllureTestNg.class)
public class TestCase extends BaseClass {
	private final String browser = "chrome"; // or any default browser //******
	//as u extended from parent base
	static Logger testCaseLogger = Logger.getLogger(TestCase.class);

//*************************************************************************************************
	
	@Test
	public void loginTestCase() throws IOException {

		System.out.println("Open nop commerce site: ");
		String testName = this.getClass().getSimpleName() + "_" + "loginTestCase";

		//driver.get(APP_URL);
		//getDriver().manage().window().maximize();
		driver.manage().window().maximize();
		System.out.println("Enter credentials:");
		//LoginPage lp = new LoginPage(getDriver());
		LoginPage lp = new LoginPage(driver);
		lp.username(USERNAME);
		testCaseLogger.info("entering username:");     //log4j  base class
		lp.password(PASSWORD);
		testCaseLogger.info("entering password:");     //log4j2   logeerload class
		lp.login();
        utilities.chainTestListener.log("opening dashboard page:");
		System.out.println("opening dashboard page:");
			
		// "Dashboard / nopCommerce administration"
		// PageFactory.initElements(driver, LoginPage.class);
		// driver.findElement(By.xpath("//button[normalize-space()='Log in']")).click();
		// LoginPage.loginButton.click();
		
		Loggerload.info("loging out of nop commerce ");  // log4j2   logeerload class
		testCaseLogger.info("loging out of nop commerce");     //log4j  base class
		System.out.println("loging out of nop commerce:");
		
		//if (driver.getTitle().equals("Dashboard / nopCommerce administration") ){
		//if (getDriver().getTitle().equals("My Account") ){
			if (driver.getTitle().equals("My Account") ){
			Assert.assertTrue(true);
			System.out.println(" successful login");
			//Assert.assertTrue(driver.getTitle().equals("My Account"), "Failed to login, page title does not match!");
			utilities.chainTestListener.log(" successful login");
			//ChainTestListener.embed("C:\\Users\\nreka\\Resume\\ghfgn.png", "image/png");
		}
		else 
		{
			//captureScreen(getDriver(),"loginTestCase");
			captureScreenshot(driver,"loginTestCase");
			Assert.assertTrue(false);
		}
		
		// Assert.assertEquals(expected: true, status); for boolean output boolean
		// status isDisplayed();
		// Assert.assertEquals("Dashboard / nopCommerce administration",
		// driver.getTitle());
		testCaseLogger.info("back to home page :");     //log4j  base class
		System.out.println("back to home page :");
		utilities.chainTestListener.log("back to home page :");
		//ChainTestListener.embed(new File("/Users/nreka/Resume/ghfgn.png"), "image/png");
		//getDriver().close();
		driver.close();
	}

	
/*	public void addingCustomer() {
		searchcust=new SearchCustomerPage(driver);
		searchcust.setEmail("reka@gmail.com");

		boolean status=searchCust.searchCustomerByEmail("reka@gmail.com");
        Assert.assertEquals(true,status);

	    searchCust=new searchCustomerPage(driver);
		searchCust.setFirstName("Reka")
	}
*/
}
