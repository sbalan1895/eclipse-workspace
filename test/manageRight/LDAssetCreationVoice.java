package manageRight;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.testng.annotations.Test;
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
import java.io.IOException;
import java.time.LocalDateTime;

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


public class LDAssetCreationVoice {
	WebDriver driver;
	WebDriverWait wait;
	private String expectedTitle;
	private String actualTitle;
	private LocalDateTime start ;
	private String assetID;
	TakesScreenshot ts;
	CommonMethods commonMethodClass = new CommonMethods();
  
@Test
  	public void LDAssetCreate() throws InterruptedException, IOException {
	  //Select Account as AMR
	  
		wait = new WebDriverWait(driver,30);
		
		expectedTitle = "ManageRight";	
		actualTitle = driver.getTitle();
		assertEquals(actualTitle, expectedTitle, "Incorrect Title.");
				
		commonMethodClass.selectCustomerMethod(driver);
		
		WebElement output = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[contains(text(),'Assets')]")));
		output = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[contains(text(),'Assets')]")));
		  WebElement AssetsLNLink= driver.findElement(By.xpath("//span[contains(text(),'Assets')]"));
		  if (AssetsLNLink.isDisplayed()) 
		  {
			  assetsModuleMethod(AssetsLNLink);
		  }
	}
	private void assetsModuleMethod(WebElement assetsLNLink) throws InterruptedException, IOException {
				// TODO Auto-generated method stub
				assetsLNLink.click();
				
				WebElement output = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//a[contains(text(),'Voice')]")));
				output = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//a[contains(text(),'Voice')]")));
				WebElement VoiceAssetMenu = driver.findElement(By.xpath("//a[contains(text(),'Voice')]"));
				VoiceAssetMenu.click();	//Click on voice sub menu
				
				
				output = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//a[contains(text(),'Long Distance')]")));
				WebElement LDAssetMenu = driver.findElement(By.xpath("//a[contains(text(),'Long Distance')]"));
				LDAssetMenu.click();	//click on local sub menu
				
				output = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//button[contains(text(), 'new')]")));
				WebElement NewLDAsset = driver.findElement(By.xpath("//button[contains(text(), 'new')]"));
				NewLDAsset.click();
				String newAssetWindowHandle = driver.getWindowHandle();
				driver.switchTo().window(newAssetWindowHandle);
				Thread.sleep(2000);
				
				newLDAssetDetailEntry();
				
				//Click on new button at local search screen
				//Switch window to local edit screen
				//call new local asset creation method where all elements will be identified and details will be entered
				
	}
	
	private void newLDAssetDetailEntry() throws InterruptedException, IOException {
		// TODO Auto-generated method stub
//Mandatory fields- Circuit ID or ANI, Line type, Service location
		String parentWindowHandle = driver.getWindowHandle();
		
		Select lineType = new Select(driver.findElement(By.id("cmbLineType")));		
		Select serviceLocation = new Select(driver.findElement(By.id("cmbAddress")));
		WebElement longDistanceCircuitId = driver.findElement(By.id("dfCircuitId"));
		WebElement longDistanceANIFirst = driver.findElement(By.id("dfNpa"));
		WebElement longDistanceANIMid = driver.findElement(By.id("dfNxx"));
		WebElement longDistanceANILast = driver.findElement(By.id("dfStartOther"));
		
		String uniqueID = "AutoLD" + assetID.substring(0, 17);
		longDistanceCircuitId.sendKeys(uniqueID); //DDT?
		

		/* OR user can create asset with ANI using following code
		 longDistanceANIFirst.sendKeys("100");
		 longDistanceANIMid.sendKeys("200");
		 longDistanceANILast.sendKeys("3333");
		 */
		
		lineType.selectByIndex(1); //DDT?
		serviceLocation.selectByIndex(1); //DDT?
				   
		//Contract section
		
		Select vendorDropdown = new Select(driver. findElement(By.id("cmbVendor")));
		vendorDropdown.selectByIndex(1); //DDT?
		WebElement vendorAccountSearchIcon = driver.findElement(By.xpath("//button[@id='btnAccount']/i[@class = 'fa fa-search']"));
		
		vendorAccountSearchIcon.click();		
		commonMethodClass.accountSearchMethod(driver,1);
		
		driver.switchTo().window(parentWindowHandle);
				
		WebElement vendorAccount = driver.findElement(By.id("dfAccountNum"));
		Select contractNumber = new Select(driver.findElement(By.id("cmbContractNumber")));
		/*WebElement MRC = driver.findElement(By.id("txtMRC"));
		MRC.sendKeys("100"); //DDT?
		WebElement NRC = driver.findElement(By.id("txtNRC"));
		NRC.sendKeys("30"); //DDT?
		*/
		Select contractTerm = new Select(driver.findElement(By.id("cmbTerm")));
		contractTerm.selectByIndex(1); //DDT?
		//WebElement effectiveDate = driver.findElement(By.id("dfEffectiveDate"));
		//effectiveDate.sendKeys("11/11/2020"); //DDT?
		//select date from calendar needs to be handled
		
		
		WebElement organizationSearchIcon = driver.findElement(By.id("orgbutton"));
		organizationSearchIcon.click();
		commonMethodClass.orgSearchMethod(driver);
				
		driver.switchTo().window(parentWindowHandle);
		
		WebElement selectedOrganization = driver.findElement(By.name("dfOrgnName"));
				
		WebElement orgCostPercentage = driver.findElement(By.name("dfCostPer"));
		WebElement nextOrgCostPercentage = driver.findElement(By.id("nextButton"));
		//Cost% element and dynamic loading of table with click on next is to be handled
		
		WebElement comments = driver.findElement(By.name("comments"));
		comments.sendKeys("Automation Voice asset Local"); //DDT?
		
		//WebElement closeWindow = driver.findElement(By.xpath("//button[contains (text(), 'close')]"));
		WebElement saveAsset = driver.findElement(By.xpath("//button[contains (text(), 'save')]"));
		saveAsset.click();
		try
		{
			
			System.out.println("ALERTALERTALERT");
			
			
		}catch(Exception e) 
			{}
		ts = (TakesScreenshot)driver;
		File srcFile = ts.getScreenshotAs(OutputType.FILE);			
		//FileUtils.copyFile(srcFile, new File ("c:\\tmp\\main_page_success.png"));
		Files.copy(srcFile, new File ("c:\\Personal\\scriptsscreenshots\\LD_asset_alert.png"));
		
	
		
			
				
	}
	@BeforeMethod
	  public void beforeMethod() {
		WebDriverManager.chromedriver().setup();
		driver = new ChromeDriver(); 
		driver.manage().window().maximize();
				  
		ManageRightLogin mrLogin = new ManageRightLogin (driver);		
		this.assetID = this.start.now().toString();
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
