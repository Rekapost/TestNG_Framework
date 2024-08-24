package testCases;
import java.io.IOException;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import pageObjects.LoginPage;
import utilities.ExcelReader;


public class TestCaseDDT extends BaseClass{
//	String[][] data=null;
//	WebDriver driver;

	@DataProvider(name="loginData")
	public String[][] getExcelData() throws IOException
	{
//		FileInputStream excel=new FileInputStream("C:\\Users\\Reka\\eclipse-workspace\\SeleniumTestNG_DDT\\src\\test\\resources\\testData\\LoginDDT.xlsx");
//		FileInputStream excel=new FileInputStream(path);
//		XSSFWorkbook workBook= new XSSFWorkbook(excel);
//		XSSFSheet sheet=workBook.getSheetAt(0);
		
		String path="src/test/resources/testData/loginDDTTestNg.xlsx";
		
		//String path="C:\\Users\\Reka\\Desktop\\New\\Eclipse\\loginDDTTestNg.xlsx";
		int noOfRows = ExcelReader.getRowCount(path, "Sheet1");
	    int noOfColumns = ExcelReader.getCellCount(path, "Sheet1", 1); //1 is rownumber
	    
	    String[][] dataTable = new String[noOfRows][noOfColumns];
	    for (int i = 1; i <=noOfRows; i++) {  
	    	// 0 row is header , so i=1
	        for (int j = 0; j < noOfColumns; j++) {   
	        	  //j=0 ,<  ; j=1 ,<=
	            dataTable[i-1][j] = ExcelReader.getCellData(path,"Sheet1",i,j);   //0,0= 1,0
	        }
	    }
	    return dataTable;
	}
		

	@Test (dataProvider = "loginData")
	public void loginWithCredential(String userName,String passWord)
	{
		LoginPage lp=new LoginPage(driver);
		lp.username(userName);
		lp.password(passWord);
		lp.login();
/*		if(isAlertPresent()==true) {
			driver.switchTo().alert().accept();
			driver.switchTo().defaultContent();
			Assert.assertTrue(false);
		}
		else {
			Assert.assertTrue(true);
			lp.clickLogOut();
			driver.switchTo().alert().accept();
			driver.switchTo().defaultContent();		}
*/		
	}

	
// login with invalid credentials , it pops up alert , so close that alert , and logout and again login 
	public boolean isAlertPresent()   // user difined to check if alert is present or not 
	{
		try 
		{
		driver.switchTo().alert();
		return true;
		}
			catch(Exception e) {
				return false;
				}	
		}
}
