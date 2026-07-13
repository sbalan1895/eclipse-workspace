package manageRight;

import static org.testng.Assert.assertEquals;

import java.io.File;
import java.io.IOException;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchWindowException;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import jxl.Cell;
import jxl.Sheet;
import jxl.Workbook;

public class DeleteOrderWireless {
	WebDriver driver;
	WebDriverWait wait;
	private String expectedTitle;
	private String actualTitle;
	TakesScreenshot ts;
	CommonMethods commonMethodClass = new CommonMethods();
  
	//@DataProvider (name="SelectOrderToEdit")
	public Object[][] invoiceDataProviderMethod() {
	    Object[][] retObjArr=getDataFromXLSUsingJXL("test\\resources\\data\\SelectOrderToEdit.xls",
	            "OrderNum", "StartAndEnd");
	      return(retObjArr);	  
	}
	
	

@Test
public void removeOrderCancel() {
	
		
	selectOrder();
	
	WebElement deleteOrder = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//button[contains(text(), 'remove')]")));
	
	deleteOrder.click();
	
	try {
		Thread.sleep(3000);
		driver.switchTo().alert().dismiss();;
		}catch(Exception e) {System.out.println("Exception Occurred");}
	
	
}

@Test
public void removeOrder() {
	
		
	selectOrder();
	
	WebElement deleteOrder = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//button[contains(text(), 'remove')]")));
	
	deleteOrder.click();
	
	try {
		Thread.sleep(3000);
		driver.switchTo().alert().dismiss();;
		}catch(Exception e) {System.out.println("Exception Occurred");}
	
	
}

private void selectOrder() {
	WebElement searchOrderLink = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("menu-orders-search")));
	
	searchOrderLink.click(); 
	List <WebElement> orderNumbers = driver.findElements(By.xpath("//tr[contains(@onclick, 'gotoOrder')]"));
		
	int n= orderNumbers.size();
	if (n != 0)
	{
			orderNumbers.get(0).click();
				
	}
	else
		Assert.fail("No record found");
	
}
/*private void selectOrderOpenStatus(String orderNumber) {
	WebElement searchOrderLink = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("menu-orders-search")));
	String targetOrderNum = orderNumber;
	searchOrderLink.click(); 
	
	List <WebElement> orderNumberStatus = driver.findElements(By.xpath("//tr[contains(@onclick, 'gotoOrder')]/td[contains(text(), 'Open')]"));
	
	int n= orderNumberStatus.size();
	if (n != 0)
	{
		orderNumberStatus.get(0).click();
	}
	else
		Assert.fail("No record found in Open State");
	
}*/

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
		
		System.setProperty("webdriver.chrome.driver", "test\\resources\\chromedriver.exe");
		driver = new ChromeDriver(); 
		driver.manage().window().maximize();
				  
		ManageRightLogin mrLogin = new ManageRightLogin (driver);		
		wait = new WebDriverWait(driver,30);
		
		expectedTitle = "ManageRight";	
		actualTitle = driver.getTitle();
		assertEquals(actualTitle, expectedTitle, "Incorrect Title.");
				
		commonMethodClass.selectCustomerMethod(driver);
		
		WebElement output = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("menu-orders")));
		
		WebElement ordersLNLink= driver.findElement(By.id("menu-orders"));
		  if (ordersLNLink.isDisplayed()) 
		  {
			  ordersLNLink.click();
		  }
	}

}
