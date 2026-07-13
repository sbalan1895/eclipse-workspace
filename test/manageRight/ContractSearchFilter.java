package manageRight;

import static org.testng.Assert.assertEquals;

import java.io.IOException;
import java.util.List;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.NoSuchWindowException;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class ContractSearchFilter {
	WebDriver driver;
	WebDriverWait wait;
	private String expectedTitle;
	private String actualTitle;
	TakesScreenshot ts;
	CommonMethods commonMethodClass = new CommonMethods();
  

@Test
	public void SearchByNumber() throws InterruptedException {
	// TODO Auto-generated method stub
		WebElement contractNumber = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("dfContractNumber")));
		contractNumber.sendKeys("MR3");
		WebElement search = driver.findElement(By.xpath("//button[@type ='submit']"));
		search.click();
		
		Thread.sleep(2000);
		List <WebElement> searchResult = driver.findElements(By.xpath("//tr[contains(@onclick, 'gotoContract')]/td[1]"));
				
		int n= searchResult.size();
		
		if (n == 0){ Assert.fail("No record found");}

		else {
			for (int i=0; i<searchResult.size(); i++)
			{
				if(searchResult.get(0).getText().contains("MR3")){Assert.assertTrue(true);}				
				else {Assert.fail("Record Mismatch");}
			}		
		}	
}
@Test
public void SearchByStatus() throws InterruptedException {
// TODO Auto-generated method stub
	
	Select contractStatus = new Select(wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("dfhStatus"))));
	contractStatus.selectByVisibleText("Active");
	WebElement search = driver.findElement(By.xpath("//button[@type ='submit']"));
	search.click();
	
	Thread.sleep(2000);
	List <WebElement> searchResult = driver.findElements(By.xpath("//tr[contains(@onclick, 'gotoContract')]/td[1]"));
			
	int n= searchResult.size();
	
	if (n == 0){ Assert.fail("No record found");}

	else {
		Assert.assertTrue(true);
	}	

	
}
@Test
public void SearchByVendor() throws InterruptedException {
// TODO Auto-generated method stub
	
	Select contractVendor = new Select(wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//Select[@name='cmbVendorFilter']"))));
	contractVendor.selectByIndex(1);
	String vendor = contractVendor.getFirstSelectedOption().getText();
	WebElement search = driver.findElement(By.xpath("//button[@type ='submit']"));
	search.click();
	
	Thread.sleep(2000);
	List <WebElement> searchResult = driver.findElements(By.xpath("//tr[contains(@onclick, 'gotoContract')]/td[3]"));
			
	int n= searchResult.size();
	
	if (n == 0){ Assert.fail("No record found");}

	else {
		for (int i=0; i<searchResult.size(); i++)
		{
			if(searchResult.get(0).getText().contains(vendor)){Assert.assertTrue(true);}				
			else {Assert.fail("Record Mismatch");}
		}		
	}	

}
@Test
public void SearchByService() throws InterruptedException {
// TODO Auto-generated method stub
	Select contractService = new Select(wait.until(ExpectedConditions.visibilityOfElementLocated(By.name("cmbService"))));
	contractService.selectByVisibleText("Access");
	String serviceType = contractService.getFirstSelectedOption().getText();	
	
	WebElement search = driver.findElement(By.xpath("//button[@type ='submit']"));
	search.click();
	
	Thread.sleep(2000);
	List <WebElement> searchResult = driver.findElements(By.xpath("//tr[contains(@onclick, 'gotoContract')]/td[4]"));
			
	int n= searchResult.size();
	
	if (n == 0){ Assert.fail("No record found");}

	else {
		for (int i=0; i<searchResult.size(); i++)
		{
			if(searchResult.get(0).getText().contains(serviceType)){Assert.assertTrue(true);}				
			else {Assert.fail("Record Mismatch");}
		}		
	}	

}
@Test
public void SearchByVehicle() throws InterruptedException {
// TODO Auto-generated method stub
	
	Select contractVehicle = new Select(wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("dfVehicle"))));
	contractVehicle.selectByIndex(1);
	String vehicleType = contractVehicle.getFirstSelectedOption().getText();
	WebElement search = driver.findElement(By.xpath("//button[@type ='submit']"));
	search.click();
	
	Thread.sleep(2000);
	List <WebElement> searchResult = driver.findElements(By.xpath("//tr[contains(@onclick, 'gotoContract')]/td[2]"));
			
	int n= searchResult.size();
	
	if (n == 0){ Assert.fail("No record found");}

	else {
		for (int i=0; i<searchResult.size(); i++)
		{
			if(searchResult.get(0).getText().contains(vehicleType)){Assert.assertTrue(true);}				
			else {Assert.fail("Record Mismatch");}
		}		
	}	

}

	
@BeforeClass
	  public void beforeClass() throws InterruptedException, IOException {
		
		WebDriverManager.chromedriver().setup();
		driver = new ChromeDriver(); 
		driver.manage().window().maximize();
				  
		ManageRightLogin mrLogin = new ManageRightLogin (driver);		
		wait = new WebDriverWait(driver,30);
		
		expectedTitle = "ManageRight";	
		actualTitle = driver.getTitle();
		assertEquals(actualTitle, expectedTitle, "Incorrect Title.");
				
		commonMethodClass.selectCustomerMethod(driver);
		
		WebElement contractLink = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("menu-contracts")));
		
		contractLink.click();
		
		WebElement searchContract = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("menu-contracts-search")));
		
		searchContract.click(); 
		
		
	}

@AfterMethod
public void afterMethod() throws InterruptedException {
	
	
	WebElement clearFilter = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//button[contains(text(), 'clear')]")));
	
	clearFilter.click(); 
	Thread.sleep(1000);
}

}
