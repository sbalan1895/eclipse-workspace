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


public class WirelessILAssetCreation {
	WebDriver driver;
	WebDriverWait wait;
	private String expectedTitle;
	private String actualTitle;
	TakesScreenshot ts;
	CommonMethods commonMethodClass = new CommonMethods();
  
@Test
  	public void WirelessILAssetCreate() throws InterruptedException, IOException {
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
				
				WebElement output = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//a[@href='#'][contains(text(),'Wireless')]")));
				output = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//a[@href='#'][contains(text(),'Wireless')]")));
				WebElement WirelessAssetMenu = driver.findElement(By.xpath("//a[@href='#'][contains(text(),'Wireless')]"));
				WirelessAssetMenu.click();	//Click on data sub menu
				
				output = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//a[contains(text(),'Individual Lines')]")));
				WebElement wirelessILAssetMenu = driver.findElement(By.xpath("//a[contains(text(),'Individual Lines')]"));
				wirelessILAssetMenu.click();
				
				output = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//button[contains(text(), 'new')]")));
				WebElement NewWirelessILAsset = driver.findElement(By.xpath("//button[contains(text(), 'new')]"));
				NewWirelessILAsset.click();
				String newAssetWindowHandle = driver.getWindowHandle();
				driver.switchTo().window(newAssetWindowHandle);
				Thread.sleep(2000);
				
				newWirelessILAssetDetailEntry();
				
	}
	
	private void newWirelessILAssetDetailEntry() throws InterruptedException, IOException {
		// TODO Auto-generated method stub
		//Mandatory fields- Device type, mobile number, organization, vendor, voice plan and MRC, account number and contract term
		String parentWindowHandle = driver.getWindowHandle();
		//Device Section
		
		Select deviceType = new Select(driver.findElement(By.id("cmbDeviceType")));
		WebElement mobileNumberFirst = driver.findElement(By.id("dfNpa"));
		WebElement mobileNumberMiddle = driver.findElement(By.name("dfNxx"));
		WebElement mobileNumberEnd = driver.findElement(By.name("dfOther"));
		
		deviceType.selectByIndex(2);
		mobileNumberFirst.sendKeys("100");
		mobileNumberMiddle.sendKeys("200");
		mobileNumberEnd.sendKeys("3340");
		
		//User Section
		WebElement organizationSearchIcon = driver.findElement(By.id("orgbutton"));
		organizationSearchIcon.click();
		commonMethodClass.orgSearchMethod(driver);
		driver.switchTo().window(parentWindowHandle);
		  
		//Plan section
		
		Select vendorDropdown = new Select(driver. findElement(By.name("cmbVendor")));
		vendorDropdown.selectByValue("1"); //DDT?
		
		WebElement voicePlanNameSearchIcon = driver.findElement(By.xpath("//button[@name='btnPlanName']/i[@class = 'fa fa-search']"));
		voicePlanNameSearchIcon.click();
		commonMethodClass.planSearchMethod(driver);
		driver.switchTo().window(parentWindowHandle);
		
		WebElement dataPlanNameSearchIcon = driver.findElement(By.xpath("//button[@name='btnDataPlanName']/i[@class = 'fa fa-search']"));
		dataPlanNameSearchIcon.click();
		commonMethodClass.planSearchMethod(driver);
		driver.switchTo().window(parentWindowHandle);
		
		WebElement vendorAccountSearchIcon = driver.findElement(By.xpath("//button[@id='btnAccount']/i[@class = 'fa fa-search']"));		
		vendorAccountSearchIcon.click();		
		commonMethodClass.accountSearchMethod(driver,1);
		driver.switchTo().window(parentWindowHandle);
		
		try {
			Thread.sleep(3000);
			driver.switchTo().alert().accept();;
	
			}catch(Exception e) {System.out.println("Exception Occurred");}
				
		//WebElement vendorAccount = driver.findElement(By.id("dfAccountNum"));
		Select contractTerm = new Select(driver.findElement(By.id("cmbTerm")));
		contractTerm.selectByIndex(1); //DDT?
		
		WebElement comments = driver.findElement(By.name("comments"));
		comments.sendKeys("Automation Wireless asset Individual Line"); //DDT?
		
		WebElement saveInternetPortAsset = driver.findElement(By.xpath("//button[contains (text(), 'save')]"));
		saveInternetPortAsset.click();
		WebElement closeWindow = driver.findElement(By.xpath("//button[contains (text(), 'close')]"));
		
		/*try
		{
			
			System.out.println("ALERTALERTALERT");
			
			
		}catch(Exception e) 
		{
			ts = (TakesScreenshot)driver;
			File srcFile = ts.getScreenshotAs(OutputType.FILE);			
			//FileUtils.copyFile(srcFile, new File ("c:\\tmp\\main_page_success.png"));
			Files.copy(srcFile, new File ("c:\\Personal\\scriptsscreenshots\\wrls_asset_alert.png"));
		}*/
				
	}
	@BeforeMethod
	  public void beforeMethod() {
		WebDriverManager.chromedriver().setup();
		driver = new ChromeDriver(); 
		driver.manage().window().maximize();
				  
		ManageRightLogin mrLogin = new ManageRightLogin (driver);		
	}

	 @AfterMethod
	  public void afterMethod() {
		  //ManageRightLogout(driver);
		  //driver.quit();
	}

}
