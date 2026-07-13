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

public class InvoiceSearchbyAllStatus {
	WebDriver driver;
	WebDriverWait wait;
	private String expectedTitle;
	private String actualTitle;
	TakesScreenshot ts;
	CommonMethods commonMethodClass = new CommonMethods();
	
	@DataProvider (name="SearchInvoiceData")
	public Object[][] invoiceDataProviderMethod() {
	    Object[][] retObjArr=getDataFromXLSUsingJXL("test\\resources\\data\\SearchInvoiceFilter.xls",
	            "filter", "StartAndEnd");
	      return(retObjArr);	  
	}
  
@Test
  	public void InvoiceSearchbyInvoiceNumber() throws InterruptedException, IOException {
	  	  
		wait = new WebDriverWait(driver,30);
				
		WebElement InvoiceLNLink = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[contains(text(),'Invoices')]")));
	  	  
		if (InvoiceLNLink.isDisplayed()) 
			{			
				InvoiceLNLink.click(); 
			}
		WebElement invoiceSearchLink = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//a[contains(text(),'Search By Status')]")));
	//	driver.findElement(By.xpath("//a[contains(text(),'All Invoices')]")).click();
		
		invoiceSearchLink.click();
		String parentWindowHandle = driver.getWindowHandle();
		editFilter();
		searchbyNumber("123658313");
		driver.switchTo().window(parentWindowHandle);
		//verify search results here
	  
	}
private void searchbyNumber(String InvoiceNum) throws InterruptedException {
	
		try {
					
			WebElement invoiceNumberEntry = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"filters\"]/form/div[1]/input"))); //using copied xpath in absence of unique identifier
									
			invoiceNumberEntry.clear();
			invoiceNumberEntry.sendKeys(InvoiceNum);
			
			WebElement filterSelectButton = driver.findElement(By.xpath("//button[contains(text(), 'ok')]"));
			
			Thread.sleep(2000);
			filterSelectButton.click();
			
		}catch(NoSuchWindowException e) {}
		
					
	}
@Test
  	public void InvoiceSearchbyService() throws InterruptedException, IOException {
	  	  
		wait = new WebDriverWait(driver,30);
		
		WebElement InvoiceLNLink = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[contains(text(),'Invoices')]")));
	  	    	  
		if (InvoiceLNLink.isDisplayed()) 
			{			
				InvoiceLNLink.click(); 
			}
		WebElement invoiceSearchLink = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//a[contains(text(),'Search By Status')]")));
		
		invoiceSearchLink.click();
		String parentWindowHandle = driver.getWindowHandle();
		
		editFilter();
		searchbyService();
		driver.switchTo().window(parentWindowHandle);
		//verify search results here
		//List <WebElement> serviceFilterResult = driver.findElements(By.xpath(""));
		
		//for 
		
	  
	}

private void searchbyService() throws InterruptedException {
		
	try {
		
		Select invoiceServiceEntry = new Select(wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//select[@name='cmbInvoiceServices']"))));
							
		invoiceServiceEntry.selectByValue("995");
				
		WebElement filterSelectButton = driver.findElement(By.xpath("//button[contains(text(), 'ok')]"));
		
		Thread.sleep(2000);
		filterSelectButton.click();
		
	}catch(NoSuchWindowException e) {System.out.println("there is an exception");}
	
}

@Test
	public void InvoiceSearchbyInvoiceStatus() throws InterruptedException, IOException {
    
	wait = new WebDriverWait(driver,30);
			
	WebElement InvoiceLNLink = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[contains(text(),'Invoices')]")));
  	    	  
	if (InvoiceLNLink.isDisplayed()) 
		{			
			InvoiceLNLink.click(); //Invoices from Left navigation
		}
	WebElement invoiceSearchLink = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//a[contains(text(),'Search By Status')]")));
	
	invoiceSearchLink.click();
	String parentWindowHandle = driver.getWindowHandle();
	editFilter();
	searchbyStatus();
	driver.switchTo().window(parentWindowHandle);
	//verify search results here
  
}

	private void searchbyStatus() throws InterruptedException {
	
		try {
			
			Select invoiceStatusEntry = new Select(wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//select[@name='cmbStatus']"))));
			
			invoiceStatusEntry.selectByValue("8");
			
			WebElement filterSelectButton = driver.findElement(By.xpath("//button[contains(text(), 'ok')]"));
			
			Thread.sleep(2000);
			filterSelectButton.click();
			
		}catch(NoSuchWindowException e) {}
		
}
@Test
  	public void InvoiceSearchbyVendor() throws InterruptedException, IOException {
	  	  
		wait = new WebDriverWait(driver,30);
				
		WebElement InvoiceLNLink = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[contains(text(),'Invoices')]")));
	  	  
		if (InvoiceLNLink.isDisplayed()) 
			{			
				InvoiceLNLink.click(); 
			}
		WebElement invoiceSearchLink = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//a[contains(text(),'Search By Status')]")));
		
		invoiceSearchLink.click();
		String parentWindowHandle = driver.getWindowHandle();
		editFilter();
		searchbyVendor();
		driver.switchTo().window(parentWindowHandle);
		//verify search results here
	  
	}
private void searchbyVendor() throws InterruptedException {
	
		try {
			
			Select invoiceVendorEntry = new Select(wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//select[@name='cmbVendor']"))));
			
			invoiceVendorEntry.selectByValue("697");
			
			WebElement filterSelectButton = driver.findElement(By.xpath("//button[contains(text(), 'ok')]"));
			
			Thread.sleep(2000);
			filterSelectButton.click();
			
		}catch(NoSuchWindowException e) {}

	}

	@Test
	public void InvoiceSearchbyTkt() throws InterruptedException, IOException {
  	  
	wait = new WebDriverWait(driver,30);
			
	WebElement InvoiceLNLink = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[contains(text(),'Invoices')]")));
  	  
	if (InvoiceLNLink.isDisplayed()) 
		{			
			InvoiceLNLink.click(); 
		}
	WebElement invoiceSearchLink = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//a[contains(text(),'Search By Status')]")));
	
	invoiceSearchLink.click();
	String parentWindowHandle = driver.getWindowHandle();
	editFilter();
	searchbyTicket();
	driver.switchTo().window(parentWindowHandle);
	//verify search results here
  
}
private void searchbyTicket() throws InterruptedException {

	try {
		
		Select invoiceTktStatusEntry = new Select(wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//select[@name='cmbTicket']"))));
		
		invoiceTktStatusEntry.selectByValue("3");
		
		WebElement filterSelectButton = driver.findElement(By.xpath("//button[contains(text(), 'ok')]"));
		
		Thread.sleep(2000);
		filterSelectButton.click();
		
	}catch(NoSuchWindowException e) {}
						
}
	private void editFilter() {
		WebElement allInvoicesSearchLink = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//a[contains(text(),'All Invoices')]")));
				
		allInvoicesSearchLink.click();		
		
		WebElement invoiceEditFilterButton = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//button[contains(text(), 'edit filters')]")));
		  		
		invoiceEditFilterButton.click();
		
		String filterWindowHandle = driver.getWindowHandle();
		driver.switchTo().window(filterWindowHandle);
				
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
  }

 @AfterMethod
  public void afterMethod() {
	  //Logout from MR
	  
	  //driver.quit();
  }


}
