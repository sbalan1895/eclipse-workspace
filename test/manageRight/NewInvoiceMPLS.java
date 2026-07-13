package manageRight;

import org.testng.annotations.Test;

import java.time.LocalDateTime;
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
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;

public class NewInvoiceMPLS {
	WebDriver driver;
	WebDriverWait wait;
	private String expectedTitle;
	private String actualTitle;
	private LocalDateTime start ;
	private String invoiceNumber;
	TakesScreenshot ts;
	CommonMethods commonMethodClass = new CommonMethods();
  

@Test ()
  	public void NewInvoiceMPLSCreate() throws InterruptedException, IOException {
	
	wait = new WebDriverWait(driver,30);
		
		
		WebElement InvoiceLNLink = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("menu-invoices")));
	  	InvoiceLNLink.click();
		
		
	  	WebElement NewInvoice= wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("menu-invoices-new")));
		NewInvoice.click(); 
		
		newInvoiceCreate(); 	
	}
	private void newInvoiceCreate() throws InterruptedException {
	// TODO Auto-generated method stub
		String parentWindowHandle = driver.getWindowHandle(); 
				
		WebElement output = wait.until(ExpectedConditions.visibilityOfElementLocated(By.name("cmbVendor")));
		Select vendorDropdown = new Select(driver. findElement(By.name("cmbVendor")));
		Select vendorAddressDropdown = new Select(driver. findElement(By.name("cmbVendorAddress")));
		WebElement invoiceNumberElement = driver.findElement(By.name("dfInvoiceNum"));
		WebElement totalAmtDueElement = driver.findElement(By.name("dfInvoiceAmt"));
		WebElement currentChargeElement = driver.findElement(By.name("dfProcessAmt"));
		WebElement invoiceSave = driver.findElement(By.xpath("//button[@type = \"submit\"]"));
		WebElement vendorAccountElement = driver.findElement(By.name("dfAccountCode"));
		WebElement vendorAccountSearchIcon = driver.findElement(By.xpath("//table/tbody/tr/td/button/i[@class='fa fa-search']"));
		WebElement invoiceDateElement = driver.findElement(By.id("dfStartDate"));
		WebElement invoiceDueDateElement = driver.findElement(By.id("dfEndDate"));
	
	
		vendorDropdown.selectByVisibleText("American Messaging");
	
		if (vendorAddressDropdown.getFirstSelectedOption().equals(null))
			{
				vendorAddressDropdown.selectByIndex(0);
			}
	
		invoiceNumberElement.sendKeys("AutoMPLS" + invoiceNumber.substring(2, 16));
		totalAmtDueElement.sendKeys("100");
		currentChargeElement.sendKeys("100");
	
		invoiceDateElement.click(); 
		invoiceDateElement.sendKeys("04/01/2021");
		
		invoiceDueDateElement.click(); 
		invoiceDueDateElement.sendKeys("10/31/2021");
		
		driver.switchTo().window(parentWindowHandle);
		
		Select invoiceServiceElement = new Select(driver.findElement(By.name("cmbCustInvoiceSetup16")));
		invoiceServiceElement.selectByIndex(1);; 	
	
		vendorAccountSearchIcon.click();
		commonMethodClass.accountSearchMethod(driver,1); //selecting acct by list index 1
		//commonMethodClass.accountSearchMethod(driver,accountNumber); //selecting acct by account number
		
		driver.switchTo().window(parentWindowHandle);
		
		Thread.sleep(3000);
		
		invoiceSave.click();
		try {
			Thread.sleep(3000);
			driver.switchTo().alert().accept();
			}catch(Exception e) {System.out.println("Exception Occurred");}
		
			
	}


@BeforeClass
  public void beforeClass() throws InterruptedException, IOException {
	System.setProperty("webdriver.chrome.driver", "test\\resources\\chromedriver.exe");
	driver = new ChromeDriver(); 
	driver.manage().window().maximize();
		  
	ManageRightLogin mrLogin = new ManageRightLogin (driver);		
	expectedTitle = "ManageRight";	
	actualTitle = driver.getTitle();
	assertEquals(actualTitle, expectedTitle, "Incorrect Title.");
	
	commonMethodClass.selectCustomerMethod(driver);
	this.invoiceNumber = this.start.now().toString();
  }

@AfterClass
public void afterClass() throws InterruptedException, IOException {
	
  }

 @AfterMethod
  public void afterMethod() {
	  //Logout from MR
	  //driver.quit();
  }
}
