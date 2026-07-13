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
import org.openqa.selenium.JavascriptExecutor;
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
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;

public class InvoiceSearchFromLeftNav {
	WebDriver driver;
	WebDriverWait wait;
	private String expectedTitle;
	private String actualTitle;
	TakesScreenshot ts;
	CommonMethods commonMethodClass = new CommonMethods();
	
  
@Test
  	public void InvoiceSearchLNPending() throws InterruptedException, IOException {
	  	  
		/*WebElement output = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("menu-invoices-bystatus")));
		WebElement invoiceSearchLink= driver.findElement(By.id("menu-invoices-bystatus"));
		invoiceSearchLink.click();*/
		
		WebElement output = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("menu-invoices-search-pending")));
		WebElement invoicePendingSearchLink= driver.findElement(By.id("menu-invoices-search-pending"));
		
		invoicePendingSearchLink.click();
		
		//verifying in webtable all records are with status as Pending
		List <WebElement> statusColumnValues = driver.findElements(By.xpath("//tr[contains(@onclick, 'gotoInvoice')]/td[@class='left']"));
		
		int n= statusColumnValues.size();
		
		if (n != 0)
		{
			for (int i=0; i<statusColumnValues.size(); i++)
			{
				Assert.assertEquals(statusColumnValues.get(i).getText(), "Pending");
			}
		//statusColumnValues.get(0).click();
		}
		else
			Assert.fail("No record found");
		
		
		
		
	}
@Test
	public void InvoiceSearchLNReadyForApproval() throws InterruptedException, IOException {
  	  
	WebElement output = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("menu-invoices-search-approval1")));
	WebElement invoiceReadyForApprovalSearchLink= driver.findElement(By.id("menu-invoices-search-approval1"));
	
	invoiceReadyForApprovalSearchLink.click();
	
	//verifying in webtable all records are with status as Pending
	
	List <WebElement> statusColumnValues = driver.findElements(By.xpath("//tr[contains(@onclick, 'gotoInvoice')]/td[@class='left']"));
			
	int n= statusColumnValues.size();
	
	if (n != 0)
	{
		for (int i=0; i<statusColumnValues.size(); i++)
		{
			Assert.assertEquals(statusColumnValues.get(i).getText(), "Submitted For Approval");
		}
	//statusColumnValues.get(0).click();
	}
	else
		Assert.fail("No record found");
	
}

@Test
public void InvoiceSearchLNDisputed() throws InterruptedException, IOException {
	  
	WebElement invoiceDisputesSearchLink = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//li[@id='menu-invoices-search-disputes']")));
	//WebElement invoiceDisputesSearchLink= driver.findElement(By.xpath("//li[@id='menu-invoices-search-disputes']"));
	JavascriptExecutor executor = (JavascriptExecutor)driver;
	executor.executeScript("arguments[0].scrollIntoView(true);", invoiceDisputesSearchLink);	
	
	invoiceDisputesSearchLink.click();
	
	//verifying in webtable all records are with status as Pending
	
	List <WebElement> statusColumnValues = driver.findElements(By.xpath("//tr[contains(@onclick, 'gotoInvoice')]/td[@class='left']"));
			
	int n= statusColumnValues.size();
	
	if (n != 0)
	{
		for (int i=0; i<statusColumnValues.size(); i++)
		{
			Assert.assertEquals(statusColumnValues.get(i).getText(), "Disputed");
		}
	//statusColumnValues.get(0).click();
	}
	else
		Assert.fail("No record found");
	
	}
@Test
public void InvoiceSearchLNPaid() throws InterruptedException, IOException {
	  
	WebElement invoicePaidSearchLink = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//li[@id='menu-invoices-search-paid']")));
	JavascriptExecutor executor = (JavascriptExecutor)driver;
	executor.executeScript("arguments[0].scrollIntoView(true);", invoicePaidSearchLink);	
	
	invoicePaidSearchLink.click();
	
	//verifying in webtable all records are with status as paid
	
	List <WebElement> statusColumnValues = driver.findElements(By.xpath("//tr[contains(@onclick, 'gotoInvoice')]/td[@class='left']"));
			
	int n= statusColumnValues.size();
	
	if (n != 0)
	{
		for (int i=0; i<statusColumnValues.size(); i++)
		{
			Assert.assertEquals(statusColumnValues.get(i).getText(), "Paid");
		}
	//statusColumnValues.get(0).click();
	}
	else
		Assert.fail("No record found");
	
	}
@Test
public void InvoiceSearchLNSettled() throws InterruptedException, IOException {
	  
	WebElement invoiceSettledSearchLink = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//li[@id='menu-invoices-search-settled']")));
	JavascriptExecutor executor = (JavascriptExecutor)driver;
	executor.executeScript("arguments[0].scrollIntoView(true);", invoiceSettledSearchLink);	
	
	invoiceSettledSearchLink.click();
	
	//verifying in webtable all records are with status as Settled
	
	List <WebElement> statusColumnValues = driver.findElements(By.xpath("//tr[contains(@onclick, 'gotoInvoice')]/td[@class='left']"));
			
	int n= statusColumnValues.size();
	
	if (n != 0)
	{
		for (int i=0; i<statusColumnValues.size(); i++)
		{
			Assert.assertEquals(statusColumnValues.get(i).getText(), "Settled");
		}
	//statusColumnValues.get(0).click();
	}
	else
		Assert.fail("No record found");
	
	}
@Test
public void InvoiceSearchLNPaymentSent() throws InterruptedException, IOException {
	  
	WebElement invoicePaymentSentSearchLink = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//li[@id='menu-invoices-search-paymentsent']")));
	JavascriptExecutor executor = (JavascriptExecutor)driver;
	executor.executeScript("arguments[0].scrollIntoView(true);", invoicePaymentSentSearchLink);	
	
	invoicePaymentSentSearchLink.click();
	
	//verifying in webtable all records are with status as PaymentSent
	
	List <WebElement> statusColumnValues = driver.findElements(By.xpath("//tr[contains(@onclick, 'gotoInvoice')]/td[@class='left']"));
			
	int n= statusColumnValues.size();
	
	if (n != 0)
	{
		for (int i=0; i<statusColumnValues.size(); i++)
		{
			Assert.assertEquals(statusColumnValues.get(i).getText(), "Payment Sent");
		}
	//statusColumnValues.get(0).click();
	}
	else
		Assert.fail("No record found");
	
	}
@Test
public void InvoiceSearchLNReadyForPayment() throws InterruptedException, IOException {
	  
	WebElement invoiceReadyForPaymentSearchLink = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//li[@id='menu-invoices-search-readyforap']")));
	JavascriptExecutor executor = (JavascriptExecutor)driver;
	executor.executeScript("arguments[0].scrollIntoView(true);", invoiceReadyForPaymentSearchLink);	
	
	invoiceReadyForPaymentSearchLink.click();
	
	//verifying in webtable all records are with status as ReadyForPayment
	
	List <WebElement> statusColumnValues = driver.findElements(By.xpath("//tr[contains(@onclick, 'gotoInvoice')]/td[@class='left']"));
			
	int n= statusColumnValues.size();
	
	if (n != 0)
	{
		for (int i=0; i<statusColumnValues.size(); i++)
		{
			Assert.assertEquals(statusColumnValues.get(i).getText(), "Approved"); //need to check how contains operator can be used here
		}
	//statusColumnValues.get(0).click();
	}
	else
		Assert.fail("No record found");
	
	}
@Test
public void InvoiceSearchLNSubmittedForPayment() throws InterruptedException, IOException {
	  
	WebElement invoiceSubmittedForPaymentSearchLink = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//li[@id='menu-invoices-search-sentforpayment']")));
	JavascriptExecutor executor = (JavascriptExecutor)driver;
	executor.executeScript("arguments[0].scrollIntoView(true);", invoiceSubmittedForPaymentSearchLink);	
	
	invoiceSubmittedForPaymentSearchLink.click();
	
	//verifying in webtable all records are with status as SubmittedForPayment
	
	List <WebElement> statusColumnValues = driver.findElements(By.xpath("//tr[contains(@onclick, 'gotoInvoice')]/td[@class='left']"));
			
	int n= statusColumnValues.size();
	
	if (n != 0)
	{
		for (int i=0; i<statusColumnValues.size(); i++)
		{
			Assert.assertEquals(statusColumnValues.get(i).getText(), "Submitted For Payment");
		}
	//statusColumnValues.get(0).click();
	}
	else
		Assert.fail("No record found");
	
	}
@Test
public void InvoiceSearchLNAllInvoices() throws InterruptedException, IOException {
	  
	WebElement invoiceAllInvoicesSearchLink = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//li[@id='menu-invoices-search-all']")));
	JavascriptExecutor executor = (JavascriptExecutor)driver;
	executor.executeScript("arguments[0].scrollIntoView(true);", invoiceAllInvoicesSearchLink);	
	
	invoiceAllInvoicesSearchLink.click();
		
	//verifying in webtable all records are with status as AllInvoices
	
	List <WebElement> statusColumnValues = driver.findElements(By.xpath("//tr[contains(@onclick, 'gotoInvoice')]/td[@class='left']"));
	
	int i= statusColumnValues.size();
	
	if (i != 0)
	{
	statusColumnValues.get(0).click();
	}
	else
		Assert.fail("No record found");
	
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
	wait = new WebDriverWait(driver,30);
	WebElement output = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("menu-invoices")));
	  
	WebElement InvoiceLNLink= driver.findElement(By.id("menu-invoices"));
  	  
	if (InvoiceLNLink.isDisplayed()) 
		{			
			InvoiceLNLink.click(); //Invoices from Left navigation
		}
	output = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("menu-invoices-bystatus")));
	WebElement invoiceSearchLink= driver.findElement(By.id("menu-invoices-bystatus"));
	invoiceSearchLink.click();
  }

 @AfterMethod
  public void afterMethod() {
	  //Logout from MR
	  
	  //driver.quit();
  }


}
