package manageRight;

import static org.testng.Assert.assertEquals;

import java.io.IOException;
import java.time.LocalDateTime;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchWindowException;
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

public class EquipmentAssetCreation {
	WebDriver driver;
	WebDriverWait wait;
	private String expectedTitle;
	private String actualTitle;
	private LocalDateTime start ;
	private String assetID;
	TakesScreenshot ts;
	CommonMethods commonMethodClass = new CommonMethods();
  
@Test
  	public void EquipmentAssetCreate() throws InterruptedException, IOException {
	 	  
		wait = new WebDriverWait(driver,30);
		
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
				
				WebElement output = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("menu-assets-equipment")));
				output = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("menu-assets-equipment")));
				WebElement equipmentAssetMenu = driver.findElement(By.id("menu-assets-equipment"));
				equipmentAssetMenu.click();
				
				output = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//button[contains(text(), 'new')]")));
				WebElement newEquipmentAsset = driver.findElement(By.xpath("//button[contains(text(), 'new')]"));
				newEquipmentAsset.click();
				String newAssetWindowHandle = driver.getWindowHandle();
				driver.switchTo().window(newAssetWindowHandle);
				Thread.sleep(2000);
				
				newEquipmentAssetDetailEntry();
				
	}
	
	private void newEquipmentAssetDetailEntry() throws InterruptedException {
		// TODO Auto-generated method stub
		String parentWindowHandle = driver.getWindowHandle();
		
		//Mandatory -Type, serial #, Service Status, Demand Response priority, Load(kW), Vendor, account #, Buy/lease, save and close
		Select equipmentType = new Select (driver.findElement(By.id("cmbEquipmentType")));
		Select serviceStatus = new Select (driver.findElement(By.id("cmbStatus")));
		WebElement serialNumber = driver.findElement(By.name("txtSrNum"));
		WebElement demandResponsePriority = driver.findElement(By.name("dfDemandResponsePriority"));
		WebElement equipmentLoad = driver.findElement(By.name("dfEquipLoad"));
		Select buyOrLease = new Select (driver.findElement(By.id("cmbBuyOrLease")));
		Select vendorName = new Select (driver.findElement(By.id("cmbVendor")));
		
		WebElement accountNumber = driver.findElement(By.id("dfAccountNum"));
		
		
		String uniqueID = "AutoEquipBuy" + assetID.substring(0, 17);
		
		
		WebElement saveAsset = driver.findElement(By.xpath("//button[contains (text(), 'save')]"));
		WebElement closeWindow = driver.findElement(By.xpath("//button[contains (text(), 'close')]"));
		
		equipmentType.selectByIndex(1);
		serviceStatus.selectByIndex(1);
		serialNumber.sendKeys(uniqueID);
		vendorName.selectByIndex(2);
		
		WebElement accountSearchIcon = driver.findElement(By.xpath("//button[@id='btnAccount']/i[@class = 'fa fa-search']"));
		accountSearchIcon.click();
		//commonMethodClass.accountSearchMethod(driver,1);- common method account search function cant be used as select button identifier is different for this asset.
		
		String accountWindowHandle = driver.getWindowHandle();
		driver.switchTo().window(accountWindowHandle);
					
			try {
					Thread.sleep(2000);
					WebElement accountCloseWindowButton = driver.findElement(By.xpath("//button[contains(text(), 'close')]"));		
					WebElement accountSelectButton = driver.findElement(By.xpath("//*[@id=\"formSelectAccount\"]//button[contains(text(), 'select')]"));					
					Select accountNumberList = new Select(driver.findElement(By.id("cmbAccountNum")));
					
					accountNumberList.selectByIndex(1);

					Thread.sleep(2000);
					accountSelectButton.click();
					//accountCloseWindowButton.click();
				}catch(NoSuchWindowException e) {}
		
		
		
		driver.switchTo().window(parentWindowHandle);
		try {
			Thread.sleep(3000);
			driver.switchTo().alert().accept();;
	
			}catch(Exception e) {System.out.println("Exception Occurred");}
		
		buyOrLease.selectByValue("1");
		//Select capitalExpenditure = new Select (driver.findElement(By.name("cbCapitalExpenditure")));
		//capitalExpenditure.se
		saveAsset.click();
		
		
	}
	@BeforeMethod
	  public void beforeMethod() throws InterruptedException, IOException {
		System.setProperty("webdriver.chrome.driver", "test\\resources\\chromedriver.exe");
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
