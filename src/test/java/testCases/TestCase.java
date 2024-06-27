package testCases;
import java.io.IOException;
import org.testng.Assert;
import org.testng.annotations.Test;
import pageObjects.LoginPage;
import utilities.Loggerload;

public class TestCase extends BaseClass {
	//as u extended from parent base
	//static Logger logger=Logger.getLogger(TestCase.class);

//*************************************************************************************************
	
	@Test
	public void loginTestCase() throws IOException {

		System.out.println("Open nop commerce site: ");
		//driver.get(APP_URL);
		driver.manage().window().maximize();
		LoginPage lp = new LoginPage(driver);
		System.out.println("Enter credentials:");
		lp = new LoginPage(driver);
		lp.username(USERNAME);
		lp.password(PASSWORD);
		lp.login();

		System.out.println("opening dashboard page:");
		// "Dashboard / nopCommerce administration"
		// PageFactory.initElements(driver, LoginPage.class);
		// driver.findElement(By.xpath("//button[normalize-space()='Log in']")).click();
		// LoginPage.loginButton.click();
		
		Loggerload.info("loging out of nop commerce ");  // log4j2   logeerload class
		logger.info("loging out of nop commerce");     //log4j  base class
		System.out.println("loging out of nop commerce:");
		
		if (driver.getTitle().equals("Dashboard / nopCommerce administration") ){
			Assert.assertTrue(true);
			System.out.println(" successful login");
		}
		else 
		{
			captureScreen(driver,"loginTestCase");
			Assert.assertTrue(false);
		}
		
		// Assert.assertEquals(expected: true, status); for boolean output boolean
		// status isDisplayed();
		// Assert.assertEquals("Dashboard / nopCommerce administration",
		// driver.getTitle());
		logger.info("back to home page :");     //log4j  base class
		System.out.println("back to home page :");
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
