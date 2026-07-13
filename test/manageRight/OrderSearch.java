package manageRight;

import static org.testng.Assert.assertEquals;

import java.io.File;
import java.io.IOException;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchWindowException;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import jxl.Cell;
import jxl.Sheet;
import jxl.Workbook;

public class OrderSearch {
	WebDriver driver;
	WebDriverWait wait;
	private String expectedTitle;
	private String actualTitle;
	TakesScreenshot ts;
	CommonMethods commonMethodClass = new CommonMethods();
  
@DataProvider (name="SearchOrderTypeData")
	public Object[][] orderDataProviderMethod() {
	    Object[][] retObjArr=getDataFromXLSUsingJXL("test\\resources\\data\\SearchOrderType.xls",
	            "OrderNum", "StartAndEnd");
	      return(retObjArr);	  
	}
@Test
  	public void SelectCustomerAccount() throws InterruptedException, IOException {
	 	  
		wait = new WebDriverWait(driver,30);
		
		WebElement output = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[contains(text(),'Orders')]")));
		output = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[contains(text(),'Orders')]")));
		  WebElement ordersLNLink= driver.findElement(By.xpath("//span[contains(text(),'Orders')]"));
		  if (ordersLNLink.isDisplayed()) 
		  {
			  ordersLNLink.click(); //Orders from Left navigation
		  }
		  output = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//a[contains(text(),'Search Orders')]")));
		  WebElement searchOrderLink= driver.findElement(By.xpath("//a[contains(text(),'Search Orders')]"));
		  searchOrderLink.click(); //Search order from Left navigation
	      String OrderType ="1";
		  searchOrder(OrderType); //Search Order method
		  //WebElement filterText = driver.findElement(By.xpath("//div[@id= 'divFilters']"));
		  //System.out.println("text is " + filterText.getText());
		  WebElement clearFilter = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//button[contains(text(), 'clear')]")));
		  clearFilter.click();
		  //output = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//button[contains(text(), 'clear')]")));
		  Thread.sleep(2000);
		  //System.out.println("after clear text is " + filterText.getText());
}

	private void searchOrder(String OrderType) throws InterruptedException {
		
	// TODO Auto-generated method stub
		String parentWindowHandle = driver.getWindowHandle();
		
		WebElement output = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//button[contains(text(), 'edit filters')]")));
		WebElement orderEditFilterButton = driver.findElement(By.xpath("//button[contains(text(), 'edit filters')]"));
		
		orderEditFilterButton.click();
		
		String filterWindowHandle = driver.getWindowHandle();
		driver.switchTo().window(filterWindowHandle);
		
		try {
					
			output = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//select[@name='cmbOrderType']")));
			Select orderType = new Select(driver.findElement(By.xpath("//select[@name='cmbOrderType']")));
			
			orderType.selectByValue(OrderType); //as per data excel
			
			WebElement filterCancelButton = driver.findElement(By.xpath("//button[contains(text(), 'cancel')]"));		
			WebElement filterSelectButton = driver.findElement(By.xpath("//button[contains(text(), 'ok')]"));
			
			Thread.sleep(2000);
			filterSelectButton.click();
			
		}catch(NoSuchWindowException e) {}
		
		driver.switchTo().window(parentWindowHandle);	
		
		//if no record found, need to fail that TC using assert
		
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

}
