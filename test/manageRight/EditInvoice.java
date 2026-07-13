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
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;

public class EditInvoice {
	WebDriver driver;
	WebDriverWait wait;
	private String expectedTitle;
	private String actualTitle;
	TakesScreenshot ts;
	CommonMethods commonMethodClass = new CommonMethods();

	@DataProvider (name="NewInvoiceData")
	public Object[][] invoiceDataProviderMethod() {
	    Object[][] retObjArr=getDataFromXLSUsingJXL("test\\resources\\data\\NewInvoicesInternet.xls",
	            "NewInvoice", "StartAndEnd");
	      return(retObjArr);	  
	}
	
@Test //(dataProvider = "NewInvoiceData")
  	public void editServiceDetails() throws InterruptedException, IOException {
	
	wait = new WebDriverWait(driver,30);
		
		
		WebElement output = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[contains(text(),'Invoices')]")));
	  	WebElement InvoiceLNLink= driver.findElement(By.xpath("//span[contains(text(),'Invoices')]"));
	  	  
		if (InvoiceLNLink.isDisplayed()) 
			{ 
				InvoiceLNLink.click();
			}
		
		String invoiceNum = "B5030179VH";
		getInvoiceList(invoiceNum);
		editInvoiceFilter();
		selectInvoice();
		editInternet();
	}
	

	
private void editInvoiceFilter() {
	By editFilter = By.xpath("//button[contains(text(),'edit filters')]");
	By service = By.xpath("//select[@name=\"cmbInvoiceServices\"]");
	By okButton = By.xpath("//button[contains(text(),'ok')]");

	String currentWindow = driver.getWindowHandle();
	driver.switchTo().window(currentWindow);

	driver.findElement(editFilter).click();


	Select serviceType = new Select(driver.findElement(service));
	serviceType.selectByValue("995");

	driver.findElement(okButton).click();
}

private void selectInvoice(){
		String currentWindow = driver.getWindowHandle();
		driver.switchTo().window(currentWindow);
		By invoice = By.xpath("//td[contains(text(),'302892')]");
		driver.findElement(invoice).click();
}
private void editInternet(){
	String currentWindow = driver.getWindowHandle();
	driver.switchTo().window(currentWindow);
	By editInternetTab = By.xpath("//a[@id='ui-id-7']");
	driver.findElement(editInternetTab).click();

}


private void getInvoiceList(String invoiceNum) {
	//Go to All Invoices Search Page
	// Select invoice as per invoice number parameter
	// return
	WebElement invoiceSearchLink = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//a[contains(text(),'Search By Status')]")));
	
	invoiceSearchLink.click();
	WebElement invoiceAllInvoicesSearchLink = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//a[contains(text(),'All Invoices')]")));
	JavascriptExecutor executor = (JavascriptExecutor)driver;
	executor.executeScript("arguments[0].scrollIntoView(true);", invoiceAllInvoicesSearchLink);	
	
	invoiceAllInvoicesSearchLink.click();
	List <WebElement> invoices = driver.findElements(By.xpath("//tr[contains(@onclick, 'gotoInvoice')]/td[1]"));
	
	int i= invoices.size();
	
	if (i != 0)
	{
		for(int n=0;n<i;n++)
		{
			System.out.println("Invoice number is " + invoices.get(n).getText());
			if(invoices.get(n).getText() == invoiceNum)
			{
				invoices.get(n).click();
				break;
			}
		}
	}
	else
		Assert.fail("No record found");
	
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

@AfterClass
public void afterClass() throws InterruptedException, IOException {
	//WebElement output = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("menu-invoices-search-pending")));
	//WebElement invoicePendingSearchLink= driver.findElement(By.id("menu-invoices-search-pending"));
	
	//invoicePendingSearchLink.click();
  }

 @AfterMethod
  public void afterMethod() {
	  //Logout from MR
	  //driver.quit();
  }
}
