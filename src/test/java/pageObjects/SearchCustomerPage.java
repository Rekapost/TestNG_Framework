package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.Test;

public class SearchCustomerPage {
	
	 WebDriver ldriver;	
	 //WaitHelper waithelper;
	 
	 //using constructor to initialze webelements in pagefactory	
		public SearchCustomerPage(WebDriver rdriver)
		{   ldriver=rdriver;
			PageFactory.initElements(rdriver,this);
			//waithelper=new WaitHelper(rdriver);
		}

		



}
