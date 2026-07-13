package manageRight;

import org.testng.annotations.Test;
import java.time.chrono.ChronoLocalDateTime;
import jxl.Cell;
import jxl.Sheet;
import jxl.Workbook;


import com.google.common.io.Files;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;

import static org.testng.Assert.assertEquals;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Set;
import java.io.IOException;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.NoSuchWindowException;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;

public class ReportInventoryMaster {
	WebDriver driver;
	WebDriverWait wait;
	private String expectedTitle;
	private String actualTitle;
	TakesScreenshot ts;
	CommonMethods commonMethodClass = new CommonMethods();
  

  	public void inventoryMasterLink() throws InterruptedException, IOException {
	  		
		WebElement ReportsLNLink = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("menu-reports")));
	  	 ReportsLNLink.click(); 
		
		WebElement allReportsLink = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("menu-reports-all")));
	  	 allReportsLink.click();
		
	  	//WebElement inventoryMasterReportLink = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//a[contains(@href, 'RepMasterInventory')]")));
	  	WebElement inventoryMasterReportLink = wait.until(ExpectedConditions.visibilityOfElementLocated(By.partialLinkText("Inventory Master")));
		 inventoryMasterReportLink.click();	  
		
	}

@Test
	public void reportCreateAllVendorWireless() throws InterruptedException, IOException {
			  
		inventoryMasterLink();
		String parentWindowHandle = driver.getWindowHandle();
		
		WebElement advancedFilterSelect = driver.findElement(By.xpath("//button[contains(@onclick, \"addAdvancedFilters\")]"));
		
		advancedFilterSelect.click();
		
		String filterWindowHandle = driver.getWindowHandle();
		
		driver.switchTo().window(filterWindowHandle);
		
		Select availableItemsList = new Select(wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("cmbAvailableItems"))));
		
		availableItemsList.selectByIndex(0);
		availableItemsList.selectByIndex(2);
		availableItemsList.selectByIndex(3);
		availableItemsList.selectByVisibleText("Employee Job Title");
		
		WebElement moveToSelectedButton = driver.findElement(By.xpath("//button[contains(text(), '>>')]"));
		
		moveToSelectedButton.click();
		
		availableItemsList.selectByIndex(0);
		availableItemsList.selectByIndex(1);
		
		moveToSelectedButton.click();
		
		WebElement selectFilter = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"formFilter\"]/table/tbody/tr[2]/td/button[1]"))); //using system generated xpath in absence of unique ID
				
		Thread.sleep(2000);
		selectFilter.click();
		try {
			Thread.sleep(3000);
			driver.switchTo().alert().accept();
			}catch(Exception e) {System.out.println("Exception Occurred");}
		
		driver.switchTo().window(parentWindowHandle);
		
		WebElement submitReport = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//button[contains (text(), 'submit')]")));
		submitReport.click();
			
	}
@Test
public void reportCreateSingleVendorWireless() throws InterruptedException, IOException {
		  
	inventoryMasterLink();
	String parentWindowHandle = driver.getWindowHandle();
	
	Select vendorDropdown = new Select(wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//select[@name='dfFilter1']"))));
	vendorDropdown.selectByIndex(5);

	WebElement advancedFilterSelect = driver.findElement(By.xpath("//button[contains(@onclick, \"addAdvancedFilters\")]"));
	
	advancedFilterSelect.click();
	
	String filterWindowHandle = driver.getWindowHandle();
	
	driver.switchTo().window(filterWindowHandle);
	
	Select availableItemsList = new Select(wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("cmbAvailableItems"))));
	
	availableItemsList.selectByIndex(0);
	availableItemsList.selectByIndex(2);
	availableItemsList.selectByIndex(3);
	availableItemsList.selectByVisibleText("Employee Job Title");
	
	WebElement moveToSelectedButton = driver.findElement(By.xpath("//button[contains(text(), '>>')]"));
	
	moveToSelectedButton.click();
	
	availableItemsList.selectByIndex(0);
	availableItemsList.selectByIndex(1);
	
	moveToSelectedButton.click();
	
	WebElement selectFilter = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"formFilter\"]/table/tbody/tr[2]/td/button[1]"))); //using system generated xpath in absence of unique ID
			
	Thread.sleep(2000);
	selectFilter.click();
	try {
		Thread.sleep(3000);
		driver.switchTo().alert().accept();
		}catch(Exception e) {System.out.println("Exception Occurred");}
	
	driver.switchTo().window(parentWindowHandle);
	
	WebElement submitReport = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//button[contains (text(), 'submit')]")));
	submitReport.click();
		
}
@Test
public void reportCreateAllVendorInternet() throws InterruptedException, IOException {
		  
	inventoryMasterLink();
	String parentWindowHandle = driver.getWindowHandle();
	Select serviceDropdown = new Select(wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//select[@name='dfFilter10']"))));
	//serviceDropdown.selectByIndex(3);
	serviceDropdown.selectByVisibleText("Internet");
	
	WebElement advancedFilterSelect = driver.findElement(By.xpath("//button[contains(@onclick, \"addAdvancedFilters\")]"));
	
	advancedFilterSelect.click();
	
	String filterWindowHandle = driver.getWindowHandle();
	
	driver.switchTo().window(filterWindowHandle);
	
	Select availableItemsList = new Select(wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("cmbAvailableItems"))));
	
	availableItemsList.selectByIndex(0);
	availableItemsList.selectByIndex(2);
	availableItemsList.selectByIndex(3);
	
	WebElement moveToSelectedButton = driver.findElement(By.xpath("//button[contains(text(), '>>')]"));
	
	moveToSelectedButton.click();
	
	availableItemsList.selectByIndex(0);
	availableItemsList.selectByIndex(1);
	
	moveToSelectedButton.click();
	
	WebElement selectFilter = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"formFilter\"]/table/tbody/tr[2]/td/button[1]"))); //using system generated xpath in absence of unique ID
			
	Thread.sleep(2000);
	selectFilter.click();
	try {
		Thread.sleep(3000);
		driver.switchTo().alert().accept();
		}catch(Exception e) {System.out.println("Exception Occurred");}
	
	driver.switchTo().window(parentWindowHandle);
	
	WebElement submitReport = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//button[contains (text(), 'submit')]")));
	submitReport.click();
		
}
@Test
public void reportCreateSingleVendorInternet() throws InterruptedException, IOException {
		  
	inventoryMasterLink();
	String parentWindowHandle = driver.getWindowHandle();
	
	Select vendorDropdown = new Select(wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//select[@name='dfFilter1']"))));
	vendorDropdown.selectByIndex(5);
	
	Select serviceDropdown = new Select(wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//select[@name='dfFilter10']"))));
	//serviceDropdown.selectByIndex(3);
	serviceDropdown.selectByVisibleText("Internet");

	WebElement advancedFilterSelect = driver.findElement(By.xpath("//button[contains(@onclick, \"addAdvancedFilters\")]"));
	
	advancedFilterSelect.click();
	
	String filterWindowHandle = driver.getWindowHandle();
	
	driver.switchTo().window(filterWindowHandle);
	
	Select availableItemsList = new Select(wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("cmbAvailableItems"))));
	
	availableItemsList.selectByIndex(0);
	availableItemsList.selectByIndex(2);
	availableItemsList.selectByIndex(3);
	
	WebElement moveToSelectedButton = driver.findElement(By.xpath("//button[contains(text(), '>>')]"));
	
	moveToSelectedButton.click();
	
	availableItemsList.selectByIndex(0);
	availableItemsList.selectByIndex(1);
	
	moveToSelectedButton.click();
	
	WebElement selectFilter = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"formFilter\"]/table/tbody/tr[2]/td/button[1]"))); //using system generated xpath in absence of unique ID
			
	Thread.sleep(2000);
	selectFilter.click();
	try {
		Thread.sleep(3000);
		driver.switchTo().alert().accept();
		}catch(Exception e) {System.out.println("Exception Occurred");}
	
	driver.switchTo().window(parentWindowHandle);
	
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
