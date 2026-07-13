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
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;


public class VoiceAssetCreation {
	WebDriver driver;
	WebDriverWait wait;
	private String expectedTitle;
	private String actualTitle;
	private LocalDateTime start ;
	private String assetID;
	TakesScreenshot ts;
	CommonMethods commonMethodClass = new CommonMethods();
  
@Test
  	public void internetAssetCreate() throws InterruptedException, IOException {
	 				
		WebElement output = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[contains(text(),'Assets')]")));
		output = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[contains(text(),'Assets')]")));
		WebElement DataAssetMenu = driver.findElement(By.xpath("//span[contains(text(),'Assets')]"));
		DataAssetMenu.click();	//Click on data sub menu
				
		output = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//a[contains(text(),'Voice')]")));
		WebElement InternetPortAssetMenu = driver.findElement(By.xpath("//a[contains(text(),'Voice')]"));
		InternetPortAssetMenu.click();	//click on internet port sub menu
			
		output = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//button[contains(text(), 'new')]")));
		WebElement NewInternetPortAsset = driver.findElement(By.xpath("//button[contains(text(), 'new')]"));
		NewInternetPortAsset.click();
		String newAssetWindowHandle = driver.getWindowHandle();
		driver.switchTo().window(newAssetWindowHandle);
		Thread.sleep(2000);
				
		String uniqueID = newInternetAssetDetailEntry();
			
		assetSearch(uniqueID);							
	}

	private String newInternetAssetDetailEntry() throws InterruptedException {
		// TODO Auto-generated method stub
		//Service Details Section
		String parentWindowHandle = driver.getWindowHandle();
		
		WebElement output = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("cmbServiceType")));
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
		
		/*WebElement employeeSearchIcon = driver.findElement(By.xpath("//button[@onclick = 'javascript:changeEmployee()']/i[@class='fa fa-search']"));		
		 employeeSearchIcon.click();
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
@Test
  	public void privateLineAssetCreate() throws InterruptedException, IOException {
	WebElement output = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("menu-assets-data")));
	output = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("menu-assets-data")));
	WebElement DataAssetMenu = driver.findElement(By.id("menu-assets-data"));
	DataAssetMenu.click();	//Click on data sub menu	
	
	output = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("menu-assets-data-privateline")));
	WebElement PrivateLineAssetMenu = driver.findElement(By.id("menu-assets-data-privateline"));
	PrivateLineAssetMenu.click();	//click on Private line sub menu
	
	output = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//button[contains(text(), 'new')]")));
	WebElement NewPrivateLineAsset = driver.findElement(By.xpath("//button[contains(text(), 'new')]"));
	NewPrivateLineAsset.click();
	String newAssetWindowHandle = driver.getWindowHandle();
	driver.switchTo().window(newAssetWindowHandle);
	Thread.sleep(2000);
	
	String uniqueID = newPrivateLineAssetDetailEntry();
	assetSearch(uniqueID);
	
	}
	private String newPrivateLineAssetDetailEntry() throws InterruptedException {
	// TODO Auto-generated method stub
		//WebElement windowLoadWait;
		String parentWindowHandle = driver.getWindowHandle();
		//Mandatory Service details fields
		
		WebElement output = wait.until(ExpectedConditions.visibilityOfElementLocated(By.name("cmbPrivateLineSpeed")));
		Select circuitSpeed = new Select(driver.findElement(By.name("cmbPrivateLineSpeed")));
		WebElement circuitId = driver.findElement(By.name("txtCircuitId"));
		WebElement originatingLocationSearchIcon = driver.findElement(By.xpath("//button[@id='btn-search-orig-address']"));
		WebElement terminatingLocationSearchIcon = driver.findElement(By.xpath("//button[@id='btn-search-term-address']"));
		WebElement originatingLocation = driver.findElement(By.name("dfOrigAddress"));
		WebElement terminatingLocation = driver.findElement(By.name("dfTermAddress"));
		
		circuitSpeed.selectByIndex(1);
		String uniqueID = "AutoPL" + assetID.substring(0, 17);
		circuitId.sendKeys(uniqueID);
		
		originatingLocationSearchIcon.click();
		commonMethodClass.locationSearchMethod(driver);
	
		driver.switchTo().window(parentWindowHandle);
	
		terminatingLocationSearchIcon.click();
		commonMethodClass.locationSearchMethod(driver);
		
		driver.switchTo().window(parentWindowHandle);
		
		Select vendorDropdown = new Select(driver. findElement(By.name("cmbVendor")));
		//DDT?
		vendorDropdown.selectByIndex(1);
		WebElement vendorAccountSearchIcon = driver.findElement(By.xpath("//button[@id='btnAccount']/i[@class = 'fa fa-search']"));
	
		vendorAccountSearchIcon.click();
		commonMethodClass.accountSearchMethod(driver,1);
					
		driver.switchTo().window(parentWindowHandle);
				
		//WebElement vendorAccount = driver.findElement(By.id("dfAccountNum"));
		Select contractTerm = new Select(driver.findElement(By.name("cmbTerm")));
		//DDT?
		contractTerm.selectByIndex(1);
		
		WebElement comments = driver.findElement(By.name("comments"));
		//DDT?
		comments.sendKeys("Automation asset private");
		
		WebElement saveAsset = driver.findElement(By.xpath("//button[contains (text(), 'save')]"));
		saveAsset.click();
		try {
			Thread.sleep(3000);
			driver.switchTo().alert().accept();
			}catch(Exception e) {System.out.println("Exception Occurred");}
		
		//WebElement closeWindow = driver.findElement(By.xpath("//button[contains (text(), 'close')]"));
		return uniqueID;
}

@Test
	public void mplsAssetCreate() throws InterruptedException, IOException {
		
		WebElement output = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("menu-assets-data")));
		output = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("menu-assets-data")));
		WebElement dataAssetMenu = driver.findElement(By.id("menu-assets-data"));
		dataAssetMenu.click();		
		
		output = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("menu-assets-data-mpls")));
		WebElement MPLSAssetMenu = driver.findElement(By.id("menu-assets-data-mpls"));
		MPLSAssetMenu.click();
		
		output = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//button[contains(text(), 'new')]")));
		WebElement newMPLSAsset = driver.findElement(By.xpath("//button[contains(text(), 'new')]"));
		newMPLSAsset.click();
		String newAssetWindowHandle = driver.getWindowHandle();
		driver.switchTo().window(newAssetWindowHandle);
		Thread.sleep(2000);
		
		String uniqueID = newMPLSAssetDetailEntry();
		
		assetSearch(uniqueID);
	
	}
	private String newMPLSAssetDetailEntry() throws InterruptedException {
	// TODO Auto-generated method stub
		String parentWindowHandle = driver.getWindowHandle();
		
		//Mandatory -Port Speed, CoS/QoS, Circuit ID, IP Address, Service location, Vendor, account #, contract term, save and close
		WebElement output = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("cmbPortSpeed")));
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
		output = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[contains (text(), 'save')]")));
		saveAsset.click(); //throwing error?
		try {
			Thread.sleep(3000);
			driver.switchTo().alert().accept();
			}catch(Exception e) {System.out.println("Exception Occurred");}
		
		return uniqueID;	
}
	
@Test
	public void dedicatedAccessAssetCreate() throws InterruptedException, IOException {
	
		WebElement output = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("menu-assets-data")));
		output = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("menu-assets-data")));
		WebElement dataAssetMenu = driver.findElement(By.id("menu-assets-data"));
		dataAssetMenu.click();
		
		
		output = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("menu-assets-data-dedicatedaccess")));
		WebElement dedicatedAccessAssetMenu = driver.findElement(By.id("menu-assets-data-dedicatedaccess"));
		dedicatedAccessAssetMenu.click();
		
		output = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//button[contains(text(), 'new')]")));
		WebElement newDedicatedAccessAsset = driver.findElement(By.xpath("//button[contains(text(), 'new')]"));
		newDedicatedAccessAsset.click();
		String newAssetWindowHandle = driver.getWindowHandle();
		driver.switchTo().window(newAssetWindowHandle);
		Thread.sleep(2000);
		
		String uniqueID = newDedicatedAccessAssetDetailEntry();
		
		assetSearch(uniqueID);
	}

	private String newDedicatedAccessAssetDetailEntry() throws InterruptedException {
	// TODO Auto-generated method stub
		String parentWindowHandle = driver.getWindowHandle();
		
		//Mandatory -Access Type, Service Location, Circuit ID, Vendor, account #, contract term, save and close
		WebElement output = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("cmbAccessSpeed")));
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
		try {
			Thread.sleep(3000);
			driver.switchTo().alert().accept();
			}catch(Exception e) {System.out.println("Exception Occurred");}
		
		return uniqueID;	
	}

	private void assetSearch(String uniqueID) {
		// TODO Auto-generated method stub
			WebElement output = wait.until(ExpectedConditions.visibilityOfElementLocated(By.name("dfFilterCircuitId")));
			WebElement circuitIDValue = driver.findElement(By.name("dfFilterCircuitId"));
			
			circuitIDValue.sendKeys(uniqueID);
			output = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@type='submit']")));
			WebElement circuitIDSearch = driver.findElement(By.xpath("//input[@type='submit']"));
			
			circuitIDSearch.click();		
	}
@BeforeClass
	public void beforeClass() throws InterruptedException, IOException {
		WebDriverManager.chromedriver().setup();
		driver = new ChromeDriver(); 
		driver.manage().window().maximize();
				  
		ManageRightLogin mrLogin = new ManageRightLogin (driver);	
		
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
			  AssetsLNLink.click();
		  }
		  
		  this.assetID = this.start.now().toString();
	}

@AfterMethod
	 public void afterMethod() throws InterruptedException {
		Thread.sleep(2000);
		  //driver.quit();
	}

}
