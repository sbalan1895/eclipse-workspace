package manageRight;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.testng.annotations.Test;
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

public class invoiceddtest {
	WebDriver driver;
	WebDriverWait wait;
	private String expectedTitle;
	private String actualTitle;

  
@Test(dataProvider = "NewInvoiceData")
  	public void SelectCustomerAccount() throws InterruptedException, IOException {
	  //Select Account as AMR
	  
		expectedTitle = "ManageRight";	
		actualTitle = driver.getTitle();
		
		CommonMethods commonMethodClass = new CommonMethods();
		commonMethodClass.selectCustomerMethod(driver);
		
}
//for data driven testing and providing data to testng like different combinations of login id and pwd

@DataProvider (name="NewInvoiceData")
public Object[][] invoiceDataProviderMethod() {
  Object[][] retObjArr=getDataFromXLSUsingJXL("test\\resources\\data\\NewTestInvoices.xls",
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

@BeforeMethod
public void beforeMethod() {
	WebDriverManager.chromedriver().setup();
	driver = new ChromeDriver(); 
	driver.manage().window().maximize();
		  
	ManageRightLogin mrLogin = new ManageRightLogin (driver);
}

@AfterMethod
public void afterMethod() {}

}
