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

public class ATMPortsAssetCreationData {
	WebDriver driver;
	WebDriverWait wait;
	private String expectedTitle;
	private String actualTitle;
	private LocalDateTime start ;
	private String assetID;
	TakesScreenshot ts;
	CommonMethods commonMethodClass = new CommonMethods();
  
@Test
  	public void ATMPortsAssetCreate() throws InterruptedException, IOException {
	 	  
		wait = new WebDriverWait(driver,30);
		
		expectedTitle = "ManageRight";	
		actualTitle = driver.getTitle();
		assertEquals(actualTitle, expectedTitle, "Incorrect Title.");
				
		commonMethodClass.selectCustomerMethod(driver);
		
		WebElement output = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[contains(text(),'Assets')]")));
		output = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("menu-assets")));
		  WebElement AssetsLNLink= driver.findElement(By.xpath("//span[contains(text(),'Assets')]"));
		  if (AssetsLNLink.isDisplayed()) 
		  {
			  assetsModuleMethod(AssetsLNLink);
		  }
	}
	private void assetsModuleMethod(WebElement assetsLNLink) throws InterruptedException {
				// TODO Auto-generated method stub
				assetsLNLink.click();
				System.out.println("clicked on asset");
				WebElement output = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//a[contains(text(),'Data')]")));
				output = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//a[contains(text(),'Data')]")));
				WebElement dataAssetMenu = driver.findElement(By.xpath("//a[contains(text(),'Data')]"));
				dataAssetMenu.click();
				System.out.println("clicked on data");
				
				output = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//a[contains(text(),'Internet Ports')]")));
				Thread.sleep(2000);
				WebElement atmPortsAssetMenu = driver.findElement(By.xpath("//a[contains(text(),'Internet Ports')]"));
				atmPortsAssetMenu.click();
				System.out.println("Trial with internet ports instead of atm ports");
				
				output = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//button[contains(text(), 'new')]")));
				WebElement newATMPortsAsset = driver.findElement(By.xpath("//button[contains(text(), 'new')]"));
				newATMPortsAsset.click();
				String newAssetWindowHandle = driver.getWindowHandle();
				driver.switchTo().window(newAssetWindowHandle);
				Thread.sleep(2000);
				
				newATMPortsAssetDetailEntry();
				
	}
	
	private void newATMPortsAssetDetailEntry() throws InterruptedException {
		// TODO Auto-generated method stub
		//New Internet Port creation

		String parentWindowHandle = driver.getWindowHandle();

		Select ServiceType = new Select(driver.findElement(By.xpath("//select[@id='cmbServiceType']")));
	    ServiceType.selectByValue("15");

		Select downloadSpeed = new Select(driver.findElement(By.xpath("//select[@id='cmbDownloadSpeed']")));
		downloadSpeed.selectByValue("1");

		Select location = new Select(driver.findElement(By.xpath("//select[@id='cmbAddress']")));
		location.selectByValue("21993");

		driver.findElement(By.xpath("//input[@id='txtCircuitId']")).sendKeys("T1E2S3T4567");

		Select vendor = new Select(driver.findElement(By.xpath("//select[@id='cmbVendor']")));
		vendor.selectByValue("1");

	    driver.findElement(By.xpath("//button[@id='btnAccount']")).click();
		commonMethodClass.accountSearchMethod(driver,1);

		Select serviceTerm = new Select(driver.findElement(By.xpath("//select[@id='cmbTerm']")));
		serviceTerm.selectByValue("1");

		driver.findElement(By.xpath("//button[contains(text(),'save')]")).click();


		//Mandatory -Port Speed, Service Location, Circuit ID, Vendor, account #, contract term, save and close
		//ATM PORT  Code

		/*Select portSpeed = new Select (driver.findElement(By.id("cmbPortBandwidth")));
		Select serviceLocation = new Select (driver.findElement(By.id("cmbAddress")));
		WebElement circuitId = driver.findElement(By.id("txtCircuitId"));
		Select vendorName = new Select (driver.findElement(By.id("cmbVendor")));
		WebElement accountSearchIcon = driver.findElement(By.xpath("//button[@id='btnAccount']/i[@class = 'fa fa-search']"));
		WebElement accountNumber = driver.findElement(By.id("dfAccountNum"));
		Select contractTerm = new Select (driver.findElement(By.id("cmbTerm")));
		
		String uniqueID = "AutoATMP" + assetID.substring(0, 17);
		
		
		WebElement saveAsset = driver.findElement(By.xpath("//button[contains (text(), 'save')]"));
		WebElement closeWindow = driver.findElement(By.xpath("//button[contains (text(), 'close')]"));
		
		portSpeed.selectByIndex(1);
		serviceLocation.selectByIndex(1);
		circuitId.sendKeys(uniqueID);
		vendorName.selectByIndex(2);
		accountSearchIcon.click();
		driver.switchTo().window(parentWindowHandle);

		contractTerm.selectByIndex(2);
		saveAsset.click();*/
		
		
	}
	@BeforeMethod
	  public void beforeMethod() {
		WebDriverManager.chromedriver().setup();
		driver = new ChromeDriver();
		driver.manage().window().maximize();

		ManageRightLogin mrLogin = new ManageRightLogin(driver);
		this.assetID = this.start.now().toString();
	}

	 @AfterMethod
	  public void afterMethod() {}
}
