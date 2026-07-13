package manageRight;

import org.testng.annotations.Test;

import jxl.Cell;
import jxl.Sheet;
import jxl.Workbook;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;

import java.io.File;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterClass;

public class MRAdminNewUserCreation {
	WebDriver driver;
	WebDriverWait wait;
	@DataProvider (name="UserSetupData")
	public Object[][] newUserDataProvider() {
	    Object[][] retObjArr=getDataFromXLSUsingJXL("test\\resources\\data\\MRNewUserSetupData.xls",
	            "userdata", "StartAndEnd");
	      return(retObjArr);	  
	}	
  @Test (dataProvider = "UserSetupData")
  public void NewUserCreate(String fname, String lname, String username, String pwd) throws InterruptedException {
	  
	  
		Thread.sleep(2000);
		driver.findElement(By.linkText("New")).click();
		
		Thread.sleep(1000);
		/*String firstName = "data";
		String lastName = "data";
		String userName = "testUser";
		String pwd = "test123";
		*/
		driver.findElement(By.name("dffirst_name")).sendKeys(fname);
		driver.findElement(By.name("dflast_name")).sendKeys(lname);
		driver.findElement(By.name("dfuser_alias")).sendKeys(username);
		driver.findElement(By.name("dfuser_pwd")).sendKeys(pwd);
		driver.findElement(By.name("dfconfirm_pwd")).sendKeys(pwd);
		driver.findElement(By.linkText("Save")).click();
		
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
  }

  @AfterMethod
  public void afterMethod() {
  }

  @BeforeClass
  public void beforeClass() throws InterruptedException {
	  System.setProperty("webdriver.chrome.driver", "test\\resources\\chromedriver.exe");
		driver = new ChromeDriver(); 
		driver.manage().window().maximize();
		driver.get("https://manageright-preprod-2.telebright.com/mrightadmin/BellAdminServlet");
		
		driver.findElement(By.name("INPUT_Login")).sendKeys("incubyteadmin"); //DDT?
		
		driver.findElement(By.name("INPUT_Password")).sendKeys("test123"); //DDT?
		driver.findElement(By.xpath("//input[@type='submit']")).click();
		
		Thread.sleep(2000);
		driver.findElement(By.linkText("Setup")).click();
		Thread.sleep(2000);
		driver.findElement(By.linkText("User Setup")).click();
  }

  @AfterClass
  public void afterClass() {
  }

}
