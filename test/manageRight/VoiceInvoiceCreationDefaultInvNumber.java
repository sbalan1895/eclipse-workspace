package manageRight;

import io.github.bonigarcia.wdm.WebDriverManager;
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

public class VoiceInvoiceCreationDefaultInvNumber {
	WebDriver driver;
	WebDriverWait wait;
	private String expectedTitle;
	private String actualTitle;
	TakesScreenshot ts;
	CommonMethods commonMethodClass = new CommonMethods();
  

@Test
	public void localInvoiceCreation() throws InterruptedException, IOException {
	  
	WebElement output = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[contains(text(),'Invoices')]")));
  	  
	WebElement InvoiceLNLink= driver.findElement(By.xpath("//span[contains(text(),'Invoices')]"));
  	
	InvoiceLNLink.click(); //Invoices from Left navigation
	
	output = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//a[@href=\"javascript:changeTab('InvoicesTabs')\"]")));
	WebElement NewInvoice= driver.findElement(By.xpath("//a[@href=\"javascript:changeTab('InvoicesTabs')\"]"));
	NewInvoice.click(); //New Invoice Entry from Left navigation
			
	localInvoiceCreate(); //create new invoice method
	
	try {
		Thread.sleep(10000);
		output = wait.until(ExpectedConditions.visibilityOfElementLocated(By.name("dfInvoiceNum")));
		WebElement newInvoiceNumber = driver.findElement(By.name("dfInvoiceNum"));
		
		String defaultInvoiceNumber = newInvoiceNumber.getText();
		System.out.println("Invoice num "+ defaultInvoiceNumber);
		}catch(Exception e) {System.out.println("Exception Occurred");}	
  
}

	private void localInvoiceCreate() throws InterruptedException {
	// TODO Auto-generated method stub
		String parentWindowHandle = driver.getWindowHandle();
		
		WebElement output = wait.until(ExpectedConditions.visibilityOfElementLocated(By.name("cmbVendor")));
		Select vendorDropdown = new Select(driver. findElement(By.name("cmbVendor")));
		Select vendorAddressDropdown = new Select(driver. findElement(By.name("cmbVendorAddress")));
		//WebElement newInvoiceNumber = driver.findElement(By.name("dfInvoiceNum"));
		WebElement newTotalAmtDue = driver.findElement(By.name("dfInvoiceAmt"));
		WebElement newCurrentCharge = driver.findElement(By.name("dfProcessAmt"));
		WebElement invoiceSave = driver.findElement(By.xpath("//button[@type = \'submit\']"));
		WebElement vendorAccount = driver.findElement(By.name("dfAccountCode"));
		WebElement vendorAccountSearchIcon = driver.findElement(By.xpath("//table/tbody/tr/td/button/i[@class='fa fa-search']"));
		WebElement invoiceDate = driver.findElement(By.id("dfStartDate"));
		WebElement invoiceDueDate = driver.findElement(By.id("dfEndDate"));
	
	
		vendorDropdown.selectByVisibleText("1-VOIP Billing");
		Thread.sleep(1000);
		vendorAddressDropdown.selectByIndex(0);
		
		/*if (vendorAddressDropdown.getFirstSelectedOption().equals(null))
		{
			vendorAddressDropdown.selectByIndex(0);
		}
		*/
		newTotalAmtDue.sendKeys("123");
		newCurrentCharge.sendKeys("120");
	
		//need to write function to pick date from calendar
		//selectDateFromCalendar();
		invoiceDate.click(); 
		invoiceDate.sendKeys("09/01/2020");
		
		invoiceDueDate.click(); 
		invoiceDueDate.sendKeys("09/30/2020");
		
		driver.switchTo().window(parentWindowHandle);
		
		output = wait.until(ExpectedConditions.visibilityOfElementLocated(By.name("cmbCustInvoiceSetup0")));	
		Select invoiceService = new Select(driver.findElement(By.name("cmbCustInvoiceSetup0")));
		invoiceService.selectByIndex(1); 	
					
		vendorAccountSearchIcon.click();
		commonMethodClass.accountSearchMethod(driver,1);
		
		driver.switchTo().window(parentWindowHandle);
		
		Thread.sleep(3000);
		
		invoiceSave.click();
		try {
			Thread.sleep(3000);
			driver.switchTo().alert().accept();				
			}catch(Exception e) {System.out.println("Exception Occurred");}
		
}
	
@Test
	public void ldInvoiceCreation() throws InterruptedException, IOException {
  	  
	WebElement output = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("menu-invoices")));
  	  
	WebElement InvoiceLNLink= driver.findElement(By.id("menu-invoices"));
  	
	InvoiceLNLink.click(); //Invoices from Left navigation
	
	output = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("menu-invoices-new")));
	WebElement NewInvoice= driver.findElement(By.id("menu-invoices-new"));
	NewInvoice.click(); //New Invoice Entry from Left navigation
	Thread.sleep(1000);
	
	ldInvoiceCreate(); //create new invoice method
  
}

	private void ldInvoiceCreate() throws InterruptedException {
	// TODO Auto-generated method stub
		String parentWindowHandle = driver.getWindowHandle();
		
		WebElement output = wait.until(ExpectedConditions.visibilityOfElementLocated(By.name("cmbVendor")));
		Select vendorDropdown = new Select(driver. findElement(By.name("cmbVendor")));
		Select vendorAddressDropdown = new Select(driver. findElement(By.name("cmbVendorAddress")));
		WebElement newInvoiceNumber = driver.findElement(By.name("dfInvoiceNum"));
		WebElement newTotalAmtDue = driver.findElement(By.name("dfInvoiceAmt"));
		WebElement newCurrentCharge = driver.findElement(By.name("dfProcessAmt"));
		WebElement invoiceSave = driver.findElement(By.xpath("//button[@type = \'submit\']"));
		WebElement vendorAccount = driver.findElement(By.name("dfAccountCode"));
		WebElement vendorAccountSearchIcon = driver.findElement(By.xpath("//table/tbody/tr/td/button/i[@class='fa fa-search']"));
		WebElement invoiceDate = driver.findElement(By.id("dfStartDate"));
		WebElement invoiceDueDate = driver.findElement(By.id("dfEndDate"));
	
	
		vendorDropdown.selectByVisibleText("AT&T");
		Thread.sleep(1000);
		
		vendorAddressDropdown.selectByIndex(1);
				
		newTotalAmtDue.sendKeys("123");
		newCurrentCharge.sendKeys("120");
	
		//need to write function to pick date from calendar
		//selectDateFromCalendar();
		invoiceDate.click(); 
		invoiceDate.sendKeys("09/01/2020");
		
		invoiceDueDate.click(); 
		invoiceDueDate.sendKeys("09/30/2020");
		
		driver.switchTo().window(parentWindowHandle);
		
		output = wait.until(ExpectedConditions.visibilityOfElementLocated(By.name("cmbCustInvoiceSetup1")));
		Select invoiceService = new Select(driver.findElement(By.name("cmbCustInvoiceSetup1")));
		invoiceService.selectByIndex(1); 	
	
						
		vendorAccountSearchIcon.click();
		commonMethodClass.accountSearchMethod(driver,1);
		
		driver.switchTo().window(parentWindowHandle);
		
		Thread.sleep(3000);
		
		invoiceSave.click();
		try {
			Thread.sleep(3000);
			driver.switchTo().alert().accept();				
			}catch(Exception e) {System.out.println("Exception Occurred");}
	
}
@Test
	public void conferencingInvoiceCreation() throws InterruptedException, IOException {
  	  
	WebElement output = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("menu-invoices")));
  	  
	WebElement InvoiceLNLink= driver.findElement(By.id("menu-invoices"));
  	
	InvoiceLNLink.click(); //Invoices from Left navigation
	
	output = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("menu-invoices-new")));
	WebElement NewInvoice= driver.findElement(By.id("menu-invoices-new"));
	NewInvoice.click(); //New Invoice Entry from Left navigation
	Thread.sleep(1000);
	
	conferencingInvoiceCreate(); //create new invoice method
  
}

	private void conferencingInvoiceCreate() throws InterruptedException {
	// TODO Auto-generated method stub
		String parentWindowHandle = driver.getWindowHandle();
		
		WebElement output = wait.until(ExpectedConditions.visibilityOfElementLocated(By.name("cmbVendor")));
		Select vendorDropdown = new Select(driver. findElement(By.name("cmbVendor")));
		Select vendorAddressDropdown = new Select(driver. findElement(By.name("cmbVendorAddress")));
		WebElement newInvoiceNumber = driver.findElement(By.name("dfInvoiceNum"));
		WebElement newTotalAmtDue = driver.findElement(By.name("dfInvoiceAmt"));
		WebElement newCurrentCharge = driver.findElement(By.name("dfProcessAmt"));
		WebElement invoiceSave = driver.findElement(By.xpath("//button[@type = \'submit\']"));
		WebElement vendorAccount = driver.findElement(By.name("dfAccountCode"));
		WebElement vendorAccountSearchIcon = driver.findElement(By.xpath("//table/tbody/tr/td/button/i[@class='fa fa-search']"));
		WebElement invoiceDate = driver.findElement(By.id("dfStartDate"));
		WebElement invoiceDueDate = driver.findElement(By.id("dfEndDate"));
		
		vendorDropdown.selectByVisibleText("101Netlink");
	
		Thread.sleep(1000);
		if (vendorAddressDropdown.getFirstSelectedOption().equals(null))
		{
			vendorAddressDropdown.selectByIndex(0);
		}
		newTotalAmtDue.sendKeys("123");
		newCurrentCharge.sendKeys("120");
	
		//need to write function to pick date from calendar
		//selectDateFromCalendar();
		invoiceDate.click(); 
		invoiceDate.sendKeys("09/01/2020");
		
		invoiceDueDate.click(); 
		invoiceDueDate.sendKeys("09/30/2020");
		
		driver.switchTo().window(parentWindowHandle);
		
		output = wait.until(ExpectedConditions.visibilityOfElementLocated(By.name("cmbCustInvoiceSetup1")));
		Select invoiceService = new Select(driver.findElement(By.name("cmbCustInvoiceSetup11")));
		invoiceService.selectByIndex(1); 		
				
		vendorAccountSearchIcon.click();
		commonMethodClass.accountSearchMethod(driver,1);
		
		driver.switchTo().window(parentWindowHandle);
		
		Thread.sleep(3000);
		
		invoiceSave.click();
		try {
			Thread.sleep(3000);
			driver.switchTo().alert().accept();				
			}catch(Exception e) {System.out.println("Exception Occurred");}
	
}
@Test
	public void pagerInvoiceCreation() throws InterruptedException, IOException {
  	  
	WebElement output = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("menu-invoices")));
  	  
	WebElement InvoiceLNLink= driver.findElement(By.id("menu-invoices"));
  	
	InvoiceLNLink.click(); //Invoices from Left navigation
	
	output = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("menu-invoices-new")));
	WebElement NewInvoice= driver.findElement(By.id("menu-invoices-new"));
	NewInvoice.click(); //New Invoice Entry from Left navigation
	Thread.sleep(1000);
	
	pagerInvoiceCreate(); //create new invoice method
  
}

	private void pagerInvoiceCreate() throws InterruptedException {
	// TODO Auto-generated method stub
		String parentWindowHandle = driver.getWindowHandle();
		
		WebElement output = wait.until(ExpectedConditions.visibilityOfElementLocated(By.name("cmbVendor")));
		Select vendorDropdown = new Select(driver. findElement(By.name("cmbVendor")));
		Select vendorAddressDropdown = new Select(driver. findElement(By.name("cmbVendorAddress")));
		WebElement newInvoiceNumber = driver.findElement(By.name("dfInvoiceNum"));
		WebElement newTotalAmtDue = driver.findElement(By.name("dfInvoiceAmt"));
		WebElement newCurrentCharge = driver.findElement(By.name("dfProcessAmt"));
		WebElement invoiceSave = driver.findElement(By.xpath("//button[@type = \'submit\']"));
		WebElement vendorAccount = driver.findElement(By.name("dfAccountCode"));
		WebElement vendorAccountSearchIcon = driver.findElement(By.xpath("//table/tbody/tr/td/button/i[@class='fa fa-search']"));
		WebElement invoiceDate = driver.findElement(By.id("dfStartDate"));
		WebElement invoiceDueDate = driver.findElement(By.id("dfEndDate"));
		
		vendorDropdown.selectByVisibleText("American Messaging");
	
		Thread.sleep(1000);
		if (vendorAddressDropdown.getFirstSelectedOption().equals(null))
		{
			vendorAddressDropdown.selectByIndex(0);
		}	
		newTotalAmtDue.sendKeys("123");
		newCurrentCharge.sendKeys("120");
	
		//need to write function to pick date from calendar
		//selectDateFromCalendar();
		invoiceDate.click(); 
		invoiceDate.sendKeys("09/01/2020");
		
		invoiceDueDate.click(); 
		invoiceDueDate.sendKeys("09/30/2020");
		
		driver.switchTo().window(parentWindowHandle);
		
		output = wait.until(ExpectedConditions.visibilityOfElementLocated(By.name("cmbCustInvoiceSetup17")));
		Select invoiceService = new Select(driver.findElement(By.name("cmbCustInvoiceSetup17")));
		invoiceService.selectByIndex(1); 	
					
		vendorAccountSearchIcon.click();
		commonMethodClass.accountSearchMethod(driver,1);
		
		driver.switchTo().window(parentWindowHandle);
		
		Thread.sleep(3000);
		
		invoiceSave.click();
		try {
			Thread.sleep(3000);
			driver.switchTo().alert().accept();				
			}catch(Exception e) {System.out.println("Exception Occurred");}
	
}

	
	
//for data driven testing and providing data to testng like different combinations of login id and pwd

@DataProvider (name="NewInvoiceData")
public Object[][] invoiceDataProviderMethod() {
    Object[][] retObjArr=getDataFromXLSUsingJXL("test\\resources\\data\\NewInvoices.xls",
            "NewInvoice", "StartAndEnd");
      return(retObjArr);	  
}

public String[][] getDataFromXLSUsingJXL(String xlFilePath, String sheetName, String tableName){
    String[][] tabArray=null;
    try{
        Workbook workbook = Workbook.getWorkbook(new File(xlFilePath));
        //Workbook class is provied by jxl.jar
        //WebDriver provided by Selenium 
        //File is class provided by Java to read a physical file
        Sheet sheet = workbook.getSheet(sheetName);
        Cell tableStart=sheet.findCell(tableName);
        
        int startRow,startCol, endRow, endCol,ci,cj;
        
        startRow=tableStart.getRow();//2
        startCol=tableStart.getColumn();//1

        Cell tableEnd= sheet.findCell(tableName, startCol+1,startRow+1, 100, 64000,  false);                

        endRow=tableEnd.getRow();//6
        endCol=tableEnd.getColumn();//4
        System.out.println("startRow="+startRow+", endRow="+endRow+", " +
                "startCol="+startCol+", endCol="+endCol);
        tabArray=new String[endRow-startRow-1][endCol-startCol-1];//5,4
        ci=0; //array row
        //ci=0,i=3, j=3,cj=1
        for (int i=startRow+1;i<endRow;i++,ci++){//i represents xls row
            cj=0;//array column
            for (int j=startCol+1;j<endCol;j++,cj++){//j represents xls column
                tabArray[ci][cj]=sheet.getCell(j,i).getContents();
            }
        }
    }
    catch (Exception e)    {
        System.out.println("Please check if file path, sheet name and tag name are correct");
        
    }

    return(tabArray);
}

@BeforeClass
  public void beforeMethod() throws InterruptedException, IOException {
	WebDriverManager.chromedriver().setup();
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
