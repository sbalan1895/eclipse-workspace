package manageRight;

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


public class InternetPortAssetCreation {
	WebDriver driver;
	WebDriverWait wait;
	private String expectedTitle;
	private String actualTitle;
	private LocalDateTime start ;
	private String assetID;
	TakesScreenshot ts;
	CommonMethods commonMethodClass = new CommonMethods();
  
@Test
  	public void InternetPortAssetCreate() throws InterruptedException, IOException {
	  //Select Account as AMR
	  
		wait = new WebDriverWait(driver,30);
		
		expectedTitle = "ManageRight";	
		actualTitle = driver.getTitle();
		assertEquals(actualTitle, expectedTitle, "Incorrect Title.");
				
		commonMethodClass.selectCustomerMethod(driver);
		
		WebElement output = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("menu-assets")));
		output = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("menu-assets")));
		  WebElement AssetsLNLink= driver.findElement(By.id("menu-assets"));
		  if (AssetsLNLink.isDisplayed()) 
		  {
			  assetsModuleMethod(AssetsLNLink);
		  }
	}
	private void assetsModuleMethod(WebElement assetsLNLink) throws InterruptedException {
				// TODO Auto-generated method stub
				assetsLNLink.click();
				
				WebElement output = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("menu-assets-data")));
				output = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("menu-assets-data")));
				WebElement DataAssetMenu = driver.findElement(By.id("menu-assets-data"));
				DataAssetMenu.click();	//Click on data sub menu
				
				
				output = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("menu-assets-data-internetports")));
				WebElement InternetPortAssetMenu = driver.findElement(By.id("menu-assets-data-internetports"));
				InternetPortAssetMenu.click();	//click on internet port sub menu
				
				output = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//button[contains(text(), 'new')]")));
				WebElement NewInternetPortAsset = driver.findElement(By.xpath("//button[contains(text(), 'new')]"));
				NewInternetPortAsset.click();
				String newAssetWindowHandle = driver.getWindowHandle();
				driver.switchTo().window(newAssetWindowHandle);
				Thread.sleep(2000);
				
				String uniqueID = newInternetAssetDetailEntry();
				//assetSearch(uniqueID);
				output = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("dfFilterCircuitId")));
				WebElement circuitIDValue = driver.findElement(By.id("dfFilterCircuitId"));
				
				circuitIDValue.sendKeys(uniqueID);
				output = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@type='submit']")));
				WebElement circuitIDSearch = driver.findElement(By.xpath("//input[@type='submit']"));
				
				circuitIDSearch.click();
				
	}
	
	private String newInternetAssetDetailEntry() throws InterruptedException {
		// TODO Auto-generated method stub
		//Service Details Section
		String parentWindowHandle = driver.getWindowHandle();
		
		Select serviceType = new Select(driver.findElement(By.id("cmbServiceType")));
		
		Select downloadSpeed = new Select(driver.findElement(By.id("cmbDownloadSpeed")));
		Select uploadSpeed = new Select(driver.findElement(By.id("cmbUploadSpeed")));
		Select serviceLocation = new Select(driver.findElement(By.id("cmbAddress")));
		WebElement internetCircuitId = driver.findElement(By.id("txtCircuitId"));
		WebElement internetLecCircuitId = driver.findElement(By.id("txtLecCircuitId"));
		Select coS = new Select(driver.findElement(By.id("cmbCoS")));
		
		serviceType.selectByIndex(1); //DDT?
		downloadSpeed.selectByIndex(1); //DDT?
		serviceLocation.selectByIndex(1); //DDT?
		String uniqueID = "AutoIP" + assetID.substring(0, 17);
		internetCircuitId.sendKeys(uniqueID); //DDT?
		
		WebElement employeeSearchIcon = driver.findElement(By.xpath("//button[@onclick = 'javascript:changeEmployee()']/i[@class='fa fa-search']"));
		
		
		 /*employeeSearchIcon.click();
		 commonMethodClass.employeeSearchMethod(driver);*/
		 
		 driver.switchTo().window(parentWindowHandle);
		  
		//Contract section
		
		Select vendorDropdown = new Select(driver. findElement(By.name("cmbVendor")));
		vendorDropdown.selectByIndex(1); //DDT?
		WebElement vendorAccountSearchIcon = driver.findElement(By.xpath("//button[@id='btnAccount']/i[@class = 'fa fa-search']"));
		
		vendorAccountSearchIcon.click();		
		commonMethodClass.accountSearchMethod(driver,1);
		
		driver.switchTo().window(parentWindowHandle);
				
		WebElement vendorAccount = driver.findElement(By.id("dfAccountNum"));
		Select contractNumber = new Select(driver.findElement(By.id("cmbContractNumber")));
		WebElement MRC = driver.findElement(By.id("txtMRC"));
		MRC.sendKeys("100"); //DDT?
		WebElement NRC = driver.findElement(By.id("txtNRC"));
		NRC.sendKeys("30"); //DDT?
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
		comments.sendKeys("Automation asset Internet Port"); //DDT?
		
		WebElement saveAsset = driver.findElement(By.xpath("//button[contains (text(), 'save')]"));
		saveAsset.click();
		
		try {
			Thread.sleep(3000);
			driver.switchTo().alert().accept();
			}catch(Exception e) {System.out.println("Exception Occurred");}
		
		//WebElement closeWindow = driver.findElement(By.xpath("//button[contains (text(), 'close')]"));
		return uniqueID;
				
	}
@BeforeMethod
	  public void beforeMethod() {
		System.setProperty("webdriver.chrome.driver", "test\\resources\\chromedriver.exe");
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
