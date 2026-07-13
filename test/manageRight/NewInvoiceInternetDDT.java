package manageRight;

import io.github.bonigarcia.wdm.WebDriverManager;
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

public class NewInvoiceInternetDDT {
	WebDriver driver;
	WebDriverWait wait;
	private String expectedTitle;
	private String actualTitle;
	private LocalDateTime start ;
	private String invoiceNumber;
	TakesScreenshot ts;
	CommonMethods commonMethodClass = new CommonMethods();
  
@DataProvider (name="NewInvoiceData")
	public Object[][] invoiceDataProviderMethod() {
	    Object[][] retObjArr=getDataFromXLSUsingJXL("test\\resources\\data\\NewInvoicesInternet.xls",
	            "NewInvoice", "StartAndEnd");
	      return(retObjArr);	  
	}
	
@Test (dataProvider = "NewInvoiceData")
  	public void NewInvoiceInternetCreateDDT(String vendor, String	vendorAddress, String accountNumber, String	invoiceNumber, String invoiceDate, String dueDate, String totalAmountDue, String currentCharges, String serviceMap
) throws InterruptedException, IOException {
	
	wait = new WebDriverWait(driver,30);
		
		
		WebElement output = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[contains(text(),'Invoices')]")));
	  	WebElement InvoiceLNLink= driver.findElement(By.xpath("//span[contains(text(),'Invoices')]"));
	  	  
		if (InvoiceLNLink.isDisplayed()) 
			{ 
				InvoiceLNLink.click();
			}
		
		WebElement NewInvoice= driver.findElement(By.xpath("//a[@href=\"javascript:changeTab('InvoicesTabs')\"]"));
		NewInvoice.click(); 
		
		newInvoiceCreate(vendor, vendorAddress, accountNumber, invoiceNumber, invoiceDate, dueDate, totalAmountDue, currentCharges, serviceMap); 	
	}
	private void newInvoiceCreate(String vendor, String	vendorAddress, String accountNumber, String	invoiceNumber, 
								String invoiceDate, String dueDate, String totalAmountDue, String currentCharges, 
								String serviceMap) throws InterruptedException {
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
	
	
		vendorDropdown.selectByVisibleText(vendor);
	
		if (vendorAddressDropdown.getFirstSelectedOption().equals(null))
			{
				vendorAddressDropdown.selectByIndex(0);
			}
	
		invoiceNumberElement.sendKeys(invoiceNumber);
		totalAmtDueElement.sendKeys(totalAmountDue);
		currentChargeElement.sendKeys(currentCharges);
	
		invoiceDateElement.click(); 
		invoiceDateElement.sendKeys(invoiceDate);
		
		invoiceDueDateElement.click(); 
		invoiceDueDateElement.sendKeys(dueDate);
		
		driver.switchTo().window(parentWindowHandle);
		
		Select invoiceServiceElement = new Select(driver.findElement(By.name("cmbCustInvoiceSetup10")));
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
  public void beforeClass() throws InterruptedException, IOException {
    WebDriverManager.chromedriver().setup();
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
	WebElement output = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//a[contains(text(),'Pending')]")));
	WebElement invoicePendingSearchLink= driver.findElement(By.id("menu-invoices-search-pending"));
	
	invoicePendingSearchLink.click();
  }

 @AfterMethod
  public void afterMethod() {
	  //Logout from MR
	  //driver.quit();
  }
}
