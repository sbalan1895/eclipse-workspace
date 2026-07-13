package manageRight;

import org.openqa.selenium.By;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.io.IOException;

import static org.testng.Assert.assertEquals;

public class ReportInvoiceMaster {
	WebDriver driver;
	WebDriverWait wait;
	private String expectedTitle;
	private String actualTitle;
	TakesScreenshot ts;
	CommonMethods commonMethodClass = new CommonMethods();
  

  	public void invoiceMasterLink() throws InterruptedException, IOException {
	  		
		WebElement ReportsLNLink = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("menu-reports")));
	  	 ReportsLNLink.click(); 
		
		WebElement allReportsLink = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("menu-reports-all")));
	  	 allReportsLink.click();
		
		WebElement invoiceMasterReportLink = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//a[contains (text(), 'Invoice Master')]")));
		 invoiceMasterReportLink.click();	  
	}

//@Test
	public void reportCreateDateRange() throws InterruptedException, IOException {
			  
		invoiceMasterLink();
		String parentWindowHandle = driver.getWindowHandle();
		
		WebElement fromDate = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("dfFilter1")));
		WebElement toDate = driver.findElement(By.id("dfFilter2"));
		
		fromDate.sendKeys("11/01/2020");
		toDate.sendKeys("11/15/2020");
		
		//driver.switchTo().window(parentWindowHandle);
		
		//	Select vendorDropdown = new Select(driver. findElement(By.name("cmbVendor")));
	
		WebElement advancedFilterSelect = driver.findElement(By.xpath("/html/body/div[5]/section/form[1]/div/div[2]/table[2]/tbody/tr/td[2]/table/tbody/tr/td/label/button"));
		
		advancedFilterSelect.click();
		
		String filterWindowHandle = driver.getWindowHandle();
		
		driver.switchTo().window(filterWindowHandle);
		
		Select itemType = new Select(wait.until(ExpectedConditions.visibilityOfElementLocated(By.name("dfSearchCriteriaFilterItems"))));
		itemType.selectByValue("1");
		
		Select availableItemsList = new Select(driver.findElement(By.id("cmbAvailableItems")));
		
		availableItemsList.selectByIndex(0);
		availableItemsList.selectByIndex(2);
		availableItemsList.selectByIndex(3);
		
		WebElement moveToSelectedButton = driver.findElement(By.xpath("//*[@id=\"lineitems\"]/div[2]/table/tbody/tr[1]/td[2]/table/tbody/tr[1]/td/button"));
		
		moveToSelectedButton.click();
		
		itemType.selectByValue("0");
		Thread.sleep(2000);
		
		availableItemsList.selectByIndex(0);
		availableItemsList.selectByIndex(1);
		
		moveToSelectedButton.click();
		
		WebElement saveFilter = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//button[contains (text(), 'ok')]")));
				
		saveFilter.click();
		try {
			Thread.sleep(3000);
			driver.switchTo().alert().accept();
			}catch(Exception e) {System.out.println("Exception Occurred");}
		
		driver.switchTo().window(parentWindowHandle);
		WebElement submitReport = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//button[contains (text(), 'submit')]")));
		submitReport.click();
			
	}
@Test
public void reportCreateInvoiceNum() throws InterruptedException, IOException {
	  
	invoiceMasterLink();
	String parentWindowHandle = driver.getWindowHandle();	
	
	WebElement advancedFilterSelect = driver.findElement(By.xpath("//button[contains(@onclick, \"addAdvancedFilters\")]"));
	
	advancedFilterSelect.click();
	
	String filterWindowHandle = driver.getWindowHandle();
	
	driver.switchTo().window(filterWindowHandle);
	
	Select itemType = new Select(wait.until(ExpectedConditions.visibilityOfElementLocated(By.name("dfSearchCriteriaFilterItems"))));
	 
	itemType.selectByValue("1");
	
	Select availableItemsList = new Select(driver.findElement(By.id("cmbAvailableItems")));
	
	availableItemsList.selectByIndex(0);
	availableItemsList.selectByIndex(2);
	availableItemsList.selectByIndex(3);
	
	WebElement moveToSelectedButton = driver.findElement(By.xpath("//*[@id=\"lineitems\"]/div[2]/table/tbody/tr[1]/td[2]/table/tbody/tr[1]/td/button"));
	
	moveToSelectedButton.click();
	
	itemType.selectByValue("0");
	Thread.sleep(2000);
	
	availableItemsList.selectByIndex(0);
	availableItemsList.selectByIndex(1);
	
	moveToSelectedButton.click();
	
	WebElement saveFilter = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//button[contains (text(), 'ok')]")));
	 	
	saveFilter.click();
	try {
		Thread.sleep(3000);
		driver.switchTo().alert().accept();
		}catch(Exception e) {System.out.println("Exception Occurred");}
	
	driver.switchTo().window(parentWindowHandle);
	WebElement invoiceNum = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("/html/body/div[5]/section/form[1]/div/div[2]/table[1]/tbody/tr[2]/td[2]/input")));
	invoiceNum.sendKeys("AutoInvoice19Jan1");
	WebElement submitReport = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//button[contains (text(), 'submit')]")));
	submitReport.click();
		
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
	
  }

 @AfterMethod
  public void afterMethod() {
	  //Logout from MR
	  
	  //throwing ElementNotInteractableException. May be due to no Wait. Need to debug
	  
	 /* try {
	  
	  driver.findElement(By.xpath("//li/*[@class='dropdown-menu']")).click();
	  driver.findElement(By.xpath("//a[@href='AdminServlet?pageName=LogOff']")).click();
	  }catch(NullPointerException e) {
		  System.out.println("It is a Null Pointer Exception");
		  
	  }
	  */
	  //driver.quit();
  }


}
