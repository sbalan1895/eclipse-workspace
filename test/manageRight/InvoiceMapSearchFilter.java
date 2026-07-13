package manageRight;

import static org.testng.Assert.assertEquals;

import java.io.IOException;
import java.util.List;

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

public class InvoiceMapSearchFilter {
	WebDriver driver;
	WebDriverWait wait;
	private String expectedTitle;
	private String actualTitle;
	TakesScreenshot ts;
	CommonMethods commonMethodClass = new CommonMethods();
  

//@Test
public void SearchByStatus() throws InterruptedException {
	
	Select mapStatus = new Select(wait.until(ExpectedConditions.visibilityOfElementLocated(By.name("cmbStatus"))));
	mapStatus.selectByIndex(2);
	WebElement search = driver.findElement(By.xpath("//button[contains(@onclick, 'submit')]"));
	search.click();
	
	Thread.sleep(2000);
	List <WebElement> searchResult = driver.findElements(By.xpath("//tr[contains(@onclick, 'goToDetails')]/td[1]"));
			
	int n= searchResult.size();
	
	if (n == 0){ Assert.fail("No record found");}

	else {
		searchResult.get(0).click();
		Assert.assertTrue(true);
	}	

	
}
//@Test
public void SearchByVendor() throws InterruptedException {
	
	Select mapVendor = new Select(wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//Select[@name='cmbFilterVendor']"))));
	mapVendor.selectByIndex(1);
	String vendor = mapVendor.getFirstSelectedOption().getText();
	WebElement search = driver.findElement(By.xpath("//button[contains(@onclick, 'submit')]"));
	search.click();
	
	Thread.sleep(2000);
	List <WebElement> searchResult = driver.findElements(By.xpath("//tr[contains(@onclick, 'goToDetails')]/td[1]"));
			
	int n= searchResult.size();
	
	if (n == 0){ Assert.fail("No record found");}

	else {
		for (int i=0; i<searchResult.size(); i++)
		{
			if(searchResult.get(0).getText().contains(vendor))
			{
				searchResult.get(0).click();
				Assert.assertTrue(true);
			}				
			else {Assert.fail("Record Mismatch");}
		}		
	}	

}
@Test
public void SearchByService() throws InterruptedException {

	Select mapService = new Select(wait.until(ExpectedConditions.visibilityOfElementLocated(By.name("cmbFilterService"))));
	mapService.selectByVisibleText("Access");
	String serviceType = mapService.getFirstSelectedOption().getText();	
	
	WebElement search = driver.findElement(By.xpath("//button[@type ='submit']"));
	search.click();
	
	Thread.sleep(2000);
	List <WebElement> searchResult = driver.findElements(By.xpath("//tr[contains(@onclick, 'gotoContract')]/td[2]"));
			
	int n= searchResult.size();
	
	if (n == 0){ Assert.fail("No record found");}

	else {
		for (int i=0; i<searchResult.size(); i++)
		{
			if(searchResult.get(0).getText().contains(serviceType)){
				searchResult.get(0).click();
				Assert.assertTrue(true);
				}				
			else {Assert.fail("Record Mismatch");}
		}		
	}	

}
//@Test
public void SearchByMapType() throws InterruptedException {
	
	Select mapType = new Select(wait.until(ExpectedConditions.visibilityOfElementLocated(By.name("cmbType"))));
	mapType.selectByVisibleText("Generic");
	WebElement search = driver.findElement(By.xpath("//button[contains(@onclick, 'submit')]"));
	search.click();
	
	Thread.sleep(2000);
	List <WebElement> searchResult = driver.findElements(By.xpath("//tr[contains(@onclick, 'goToDetails')]/td[2]"));
			
	int n= searchResult.size();
	
	if (n == 0){ Assert.fail("No record found");}

	else {
		for (int i=0; i<searchResult.size(); i++)
		{
			if(!searchResult.get(0).getText().isEmpty())
			{
				searchResult.get(0).click();
				Assert.assertTrue(true);
			}				
			else {Assert.fail("Record Mismatch");}
		}		
	}	

}

	
@BeforeClass
	  public void beforeClass() throws InterruptedException, IOException {
		
		System.setProperty("webdriver.chrome.driver", "test\\resources\\chromedriver.exe");
		driver = new ChromeDriver(); 
		driver.manage().window().maximize();
				  
		ManageRightLogin mrLogin = new ManageRightLogin (driver);		
		wait = new WebDriverWait(driver,30);
		
		expectedTitle = "ManageRight";	
		actualTitle = driver.getTitle();
		assertEquals(actualTitle, expectedTitle, "Incorrect Title.");
				
		commonMethodClass.selectCustomerMethod(driver);
		
		WebElement setupLink = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("menu-setup")));
		
		setupLink.click();
		
		WebElement invoiceMapLink = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("menu-setup-invoicemaps")));
		
		invoiceMapLink.click(); 
		
		WebElement allInvoicesLink = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("menu-setup-invoicemaps-all")));
		
		allInvoicesLink.click(); 
		
		
	}

@AfterMethod
public void afterMethod() throws InterruptedException {
	
	WebElement allInvoicesLink = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("menu-setup-invoicemaps-all")));
	
	allInvoicesLink.click(); 
	WebElement clearFilter = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//button[contains(text(), 'clear')]")));
	
	clearFilter.click(); 
	Thread.sleep(1000);
}

}
