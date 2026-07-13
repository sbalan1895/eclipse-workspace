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

public class MPLSAssetCreation {
	WebDriver driver;
	WebDriverWait wait;
	private String expectedTitle;
	private String actualTitle;
	private LocalDateTime start ;
	private String assetID;
	TakesScreenshot ts;
	CommonMethods commonMethodClass = new CommonMethods();
  
@Test
  	public void MPLSAssetCreate() throws InterruptedException, IOException {
	 	  
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
	private void assetsModuleMethod(WebElement assetsLNLink) throws InterruptedException {
				// TODO Auto-generated method stub
				assetsLNLink.click();
				
				WebElement output = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//a[contains(text(),'Data')]")));
				output = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//a[contains(text(),'Data')]")));
				WebElement dataAssetMenu = driver.findElement(By.xpath("//a[contains(text(),'Data')]"));
				dataAssetMenu.click();
				
				
				output = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//a[contains(text(),'MPLS')]")));
				WebElement MPLSAssetMenu = driver.findElement(By.xpath("//a[contains(text(),'MPLS')]"));
				MPLSAssetMenu.click();
				
				output = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//button[contains(text(), 'new')]")));
				WebElement newMPLSAsset = driver.findElement(By.xpath("//button[contains(text(), 'new')]"));
				newMPLSAsset.click();
				String newAssetWindowHandle = driver.getWindowHandle();
				driver.switchTo().window(newAssetWindowHandle);
				Thread.sleep(2000);
				
				newMPLSAssetDetailEntry();
				
	}
	private void newMPLSAssetDetailEntry() throws InterruptedException {
		// TODO Auto-generated method stub
		String parentWindowHandle = driver.getWindowHandle();
		
		//Mandatory -Port Speed, CoS/QoS, Circuit ID, IP Address, Service location, Vendor, account #, contract term, save and close
		Select portSpeed = new Select (driver.findElement(By.id("cmbPortSpeed")));
		Select CosQos = new Select (driver.findElement(By.id("cmbCOSQOS")));
		WebElement circuitId = driver.findElement(By.id("txtCircuitId"));
		WebElement IPAddress = driver.findElement(By.id("txtIPAddress"));
		Select serviceLocation = new Select (driver.findElement(By.id("cmbAddress")));
		Select vendorName = new Select (driver.findElement(By.id("cmbVendor")));
		WebElement accountSearchIcon = driver.findElement(By.xpath("//button[@id='btnAccount']/i[@class = 'fa fa-search']"));
		WebElement accountNumber = driver.findElement(By.id("dfAccountNum"));
		Select contractTerm = new Select (driver.findElement(By.id("cmbTerm")));
		
		String uniqueID = "AutoMPLS" + assetID.substring(0, 17);
		WebElement saveAsset = driver.findElement(By.xpath("//button[contains (text(), 'save')]"));
		WebElement closeWindow = driver.findElement(By.xpath("//button[contains (text(), 'close')]"));
		
		portSpeed.selectByIndex(1);
		CosQos.selectByIndex(1);
		circuitId.sendKeys(uniqueID);
		IPAddress.sendKeys("100");
		serviceLocation.selectByIndex(1);
		vendorName.selectByIndex(2);
		accountSearchIcon.click();
		commonMethodClass.accountSearchMethod(driver, 1);
		driver.switchTo().window(parentWindowHandle);
		
		contractTerm.selectByIndex(2);
		//explicit wait
		//WebElement output = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//button[contains (text(), 'save')]")));
		WebElement output = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[contains (text(), 'save')]")));
		saveAsset.click(); //throwing error?
		

		
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
	  public void afterMethod() {}
}


