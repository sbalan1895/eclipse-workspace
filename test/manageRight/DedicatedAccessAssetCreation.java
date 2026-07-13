package manageRight;

import static org.testng.Assert.assertEquals;

import java.io.IOException;
import java.time.LocalDateTime;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class DedicatedAccessAssetCreation {
	WebDriver driver;
	WebDriverWait wait;
	private String expectedTitle;
	private String actualTitle;
	private LocalDateTime start ;
	private String assetID;
	TakesScreenshot ts;
	CommonMethods commonMethodClass = new CommonMethods();
  
@Test
  	public void DedicatedAccessAssetCreate() throws InterruptedException, IOException {
	 	  
		wait = new WebDriverWait(driver,30);
		
		WebElement output = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[contains(text(),'Assets')]")));
		output = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[contains(text(),'Assets')]")));
		  WebElement AssetsLNLink= driver.findElement(By.xpath("//span[contains(text(),'Assets')]"));
		  if (AssetsLNLink.isDisplayed()) 
		  {
			  assetsModuleMethod(AssetsLNLink);
		  }
	}
	private void assetsModuleMethod(WebElement assetsLNLink) throws InterruptedException {
				// TODO Auto-generated method stub
				assetsLNLink.click();

				WebElement output = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//a[contains(text(),'Data')]")));
				output = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//a[contains(text(),'Data')]")));
				WebElement dataAssetMenu = driver.findElement(By.xpath("//a[contains(text(),'Data')]"));
				dataAssetMenu.click();

				
				output = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//a[contains(text(),'Dedicated Access')]")));
				WebElement dedicatedAccessAssetMenu = driver.findElement(By.xpath("//a[contains(text(),'Dedicated Access')]"));
				dedicatedAccessAssetMenu.click();
				
				output = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//button[contains(text(), 'new')]")));
				WebElement newDedicatedAccessAsset = driver.findElement(By.xpath("//button[contains(text(), 'new')]"));
				newDedicatedAccessAsset.click();
				String newAssetWindowHandle = driver.getWindowHandle();
				driver.switchTo().window(newAssetWindowHandle);
				Thread.sleep(2000);
				
				newDedicatedAccessAssetDetailEntry();
				
	}
	
	private void newDedicatedAccessAssetDetailEntry() throws InterruptedException {
		// TODO Auto-generated method stub
		String parentWindowHandle = driver.getWindowHandle();
		
		//Mandatory -Access Type, Service Location, Circuit ID, Vendor, account #, contract term, save and close
		Select accessType = new Select (driver.findElement(By.id("cmbAccessSpeed")));
		Select serviceLocation = new Select (driver.findElement(By.id("cmbAddress")));
		WebElement circuitId = driver.findElement(By.id("txtCircuitId"));
		Select vendorName = new Select (driver.findElement(By.id("cmbVendor")));
		WebElement accountSearchIcon = driver.findElement(By.xpath("//button[@id='btnAccount']/i[@class = 'fa fa-search']"));
		WebElement accountNumber = driver.findElement(By.id("dfAccountNum"));
		Select contractTerm = new Select (driver.findElement(By.id("cmbTerm")));
		
		String uniqueID = "AutoDA" + assetID.substring(0, 17);
		
		
		WebElement saveAsset = driver.findElement(By.xpath("//button[contains (text(), 'save')]"));
		WebElement closeWindow = driver.findElement(By.xpath("//button[contains (text(), 'close')]"));
		
		accessType.selectByIndex(1);
		serviceLocation.selectByIndex(1);
		circuitId.sendKeys(uniqueID);
		vendorName.selectByIndex(2);
		accountSearchIcon.click();
		commonMethodClass.accountSearchMethod(driver,1);
		driver.switchTo().window(parentWindowHandle);
		contractTerm.selectByIndex(2);
		saveAsset.click();
		
		
	}
	@BeforeMethod
	  public void beforeMethod() throws InterruptedException, IOException {
		WebDriverManager.chromedriver().setup();
		driver = new ChromeDriver(); 
		driver.manage().window().maximize();
				  
		ManageRightLogin mrLogin = new ManageRightLogin (driver);		
		expectedTitle = "ManageRight";	
		actualTitle = driver.getTitle();
		assertEquals(actualTitle, expectedTitle, "Incorrect Title.");
				
		commonMethodClass.selectCustomerMethod(driver);
		this.assetID = this.start.now().toString();
	}

	 @AfterMethod
	  public void afterMethod() {}
}
