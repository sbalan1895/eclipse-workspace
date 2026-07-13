package manageRight;

import com.google.common.collect.Table;
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
import org.testng.annotations.AfterMethod;
import org.testng.annotations.DataProvider;

public class NewInvoiceCreation {
	WebDriver driver;
	WebDriverWait wait;
	private String expectedTitle;
	private String actualTitle;
	private LocalDateTime start ;
	private String invoiceNumber;
	TakesScreenshot ts;
	CommonMethods commonMethodClass = new CommonMethods();
  
@Test
  	public void NewInvoiceCreate() throws InterruptedException, IOException {
	  //Select Account as AMR
	  
	wait = new WebDriverWait(driver,30);
		expectedTitle = "ManageRight";	
		actualTitle = driver.getTitle();
		assertEquals(actualTitle, expectedTitle, "Incorrect Title.");
		
		commonMethodClass.selectCustomerMethod(driver);
		
		WebElement output = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("menu-invoices")));
	  	  
		WebElement InvoiceLNLink= driver.findElement(By.id("menu-invoices"));
	  	  
		if (InvoiceLNLink.isDisplayed()) 
			{			
				invoiceModuleMethod(InvoiceLNLink);		//Invoices Module select
			}
		
		
		//next steps
		//go to invoice search page
		//Select search by status and All status LN link
		//search for newly created invoice at this page in pending status using assertequal. 
		// if found TC passes
	    	  
  	}
  
	private void invoiceModuleMethod(WebElement InvoiceLNLink) throws InterruptedException {
	// TODO Auto-generated method stub
	
		InvoiceLNLink.click(); //Invoices from Left navigation
		WebElement NewInvoice= driver.findElement(By.id("menu-invoices-new"));
		NewInvoice.click(); //New Invoice Entry from Left navigation
		
		newInvoiceCreate(); //create new invoice method
	  
	}

	private void newInvoiceCreate() throws InterruptedException {
	// TODO Auto-generated method stub
		  
		Select vendorDropdown = new Select(driver. findElement(By.name("cmbVendor")));
		Select vendorAddressDropdown = new Select(driver. findElement(By.name("cmbVendorAddress")));
		WebElement newInvoiceNumber = driver.findElement(By.name("dfInvoiceNum"));
		WebElement newTotalAmtDue = driver.findElement(By.name("dfInvoiceAmt"));
		WebElement newCurrentCharge = driver.findElement(By.name("dfProcessAmt"));
		WebElement invoiceSave = driver.findElement(By.xpath("//button[@type = \"submit\"]"));
		WebElement vendorAccount = driver.findElement(By.name("dfAccountCode"));
		WebElement vendorAccountSearchIcon = driver.findElement(By.xpath("//table/tbody/tr/td/button/i[@class='fa fa-search']"));
		WebElement invoiceDate = driver.findElement(By.id("dfStartDate"));
		WebElement invoiceDueDate = driver.findElement(By.id("dfEndDate"));
	
	
		vendorDropdown.selectByVisibleText("Adams");
	
		if (vendorAddressDropdown.getFirstSelectedOption().equals(null))
			{
				vendorAddressDropdown.selectByIndex(0);
			}
	
		String uniqueInvoiceNumber = "Auto" + invoiceNumber.substring(0, 17);
		
		
		newInvoiceNumber.sendKeys(uniqueInvoiceNumber);
		newTotalAmtDue.sendKeys("123");
		newCurrentCharge.sendKeys("120");
	
		//need to write function to pick date from calendar
		//selectDateFromCalendar();
		invoiceDate.click(); 
		invoiceDate.sendKeys("09/01/2020");
		
		invoiceDueDate.click(); 
		invoiceDueDate.sendKeys("09/30/2020");
		
		Select invoiceService = new Select(driver.findElement(By.name("cmbCustInvoiceSetup10")));
		invoiceService.selectByIndex(1); 	//.selectByValue("Internet RPI") OR selectByVisibleText("Internet RPI");
	
		String parentWindowHandle = driver.getWindowHandle();
				
		vendorAccountSearchIcon.click();
		commonMethodClass.accountSearchMethod(driver,1);
		
		driver.switchTo().window(parentWindowHandle);
		
		Thread.sleep(3000);
		
		invoiceSave.click();
			
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
        Table.Cell tableStart= (Table.Cell) sheet.findCell(tableName);
        
        int startRow,startCol, endRow, endCol,ci,cj;
        
        startRow= ((Cell) tableStart).getRow();//2
        startCol= ((Cell) tableStart).getColumn();//1

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

@BeforeMethod
  public void beforeMethod() {
	WebDriverManager.chromedriver().setup();
	driver = new ChromeDriver();
	driver.manage().window().maximize();

	ManageRightLogin mrLogin = new ManageRightLogin(driver);
	this.invoiceNumber = this.start.now().toString();
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
