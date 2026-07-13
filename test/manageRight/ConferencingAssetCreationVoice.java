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
import org.openqa.selenium.Alert;
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


public class ConferencingAssetCreationVoice {
	WebDriver driver;
	WebDriverWait wait;
	private String expectedTitle;
	private String actualTitle;
	private LocalDateTime start ;
	private String assetID;
	TakesScreenshot ts;
	CommonMethods commonMethodClass = new CommonMethods();
  
@Test
  	public void ConferencingAssetCreate() throws InterruptedException, IOException {
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
	private void assetsModuleMethod(WebElement assetsLNLink) throws InterruptedException {
				// TODO Auto-generated method stub
				assetsLNLink.click();
				
				WebElement output = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//a[contains(text(),'Voice')]")));
				output = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//a[contains(text(),'Voice')]")));
				WebElement VoiceAssetMenu = driver.findElement(By.xpath("//a[contains(text(),'Voice')]"));
				VoiceAssetMenu.click();	//Click on voice sub menu
				
				
				output = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//a[contains(text(),'Conferencing')]")));
				WebElement ConferencingAssetMenu = driver.findElement(By.xpath("//a[contains(text(),'Conferencing')]"));
				ConferencingAssetMenu.click();	//click on local sub menu
				
				output = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//button[contains(text(), 'new')]")));
				WebElement NewConferencingAsset = driver.findElement(By.xpath("//button[contains(text(), 'new')]"));
				NewConferencingAsset.click();
				String newAssetWindowHandle = driver.getWindowHandle();
				driver.switchTo().window(newAssetWindowHandle);
				Thread.sleep(2000);
				
				newConferencingAssetDetailEntry();
				
				//Click on new button at local search screen
				//Switch window to local edit screen
				//call new local asset creation method where all elements will be identified and details will be entered
				
	}
	
	private void newConferencingAssetDetailEntry() throws InterruptedException {
		// TODO Auto-generated method stub
//Mandatory fields- chairperson entry code, organization, Category, vendor, account and contract term
		String parentWindowHandle = driver.getWindowHandle();
		
		Select category = new Select(driver.findElement(By.name("cmbCategory")));
		WebElement chairpersonEntryCode = driver.findElement(By.name("dfChairEntryCode"));
		WebElement orgSearchIcon = driver.findElement(By.id("orgbutton"));
		
		category.selectByIndex(1); //DDT?
		String uniqueID = "AutoConf" + assetID.substring(0, 17);
		chairpersonEntryCode.sendKeys(uniqueID); //DDT?
		orgSearchIcon.click();
		commonMethodClass.orgSearchMethod(driver);
		driver.switchTo().window(parentWindowHandle);
		   
		//Contract section
		
		Select vendorDropdown = new Select(driver. findElement(By.name("cmbVendor")));
		vendorDropdown.selectByIndex(1); //DDT?
		WebElement vendorAccountSearchIcon = driver.findElement(By.xpath("//button[@id='btnAccount']/i[@class = 'fa fa-search']"));
		
		vendorAccountSearchIcon.click();		
		commonMethodClass.accountSearchMethod(driver,1);		
		driver.switchTo().window(parentWindowHandle);
				
		try {
			Thread.sleep(3000);
			driver.switchTo().alert().accept();;
	
			}catch(Exception e) {System.out.println("Exception Occurred");}
		
		Select contractTerm = new Select(driver.findElement(By.name("cmbTerm")));
		contractTerm.selectByIndex(1); //DDT?
		//WebElement effectiveDate = driver.findElement(By.id("dfEffectiveDate"));
		//effectiveDate.sendKeys("11/11/2020"); //DDT?
		
		//WebElement selectedOrganization = driver.findElement(By.name("dfOrgnName"));
				
		WebElement comments = driver.findElement(By.name("comments"));
		comments.sendKeys("Automation Voice asset conferencing"); //DDT?
		
		//WebElement closeWindow = driver.findElement(By.xpath("//button[contains (text(), 'close')]"));
		WebElement saveAsset = driver.findElement(By.xpath("//button[contains (text(), 'save')]"));
		saveAsset.click();
		
				
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
		  
		  //driver.quit();
	}

}
