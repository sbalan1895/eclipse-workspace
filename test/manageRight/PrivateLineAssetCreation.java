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


public class PrivateLineAssetCreation {
	WebDriver driver;
	WebDriverWait wait;
	private String expectedTitle;
	private String actualTitle;
	private LocalDateTime start ;
	private String assetID;
	TakesScreenshot ts;
	CommonMethods commonMethodClass = new CommonMethods();
  
@Test
  	public void PrivateLineAssetCreate() throws InterruptedException, IOException {
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
				
				WebElement output = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//a[contains(text(),'Data')]")));
				output = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//a[contains(text(),'Data')]")));
				WebElement DataAssetMenu = driver.findElement(By.xpath("//a[contains(text(),'Data')]"));
				DataAssetMenu.click();	//Click on data sub menu
				
				
				output = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//a[contains(text(),'Private Line')]")));
				WebElement PrivateLineAssetMenu = driver.findElement(By.xpath("//a[contains(text(),'Private Line')]"));
				PrivateLineAssetMenu.click();	//click on Private line sub menu
				
				output = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//button[contains(text(), 'new')]")));
				WebElement NewPrivateLineAsset = driver.findElement(By.xpath("//button[contains(text(), 'new')]"));
				NewPrivateLineAsset.click();
				String newAssetWindowHandle = driver.getWindowHandle();
				driver.switchTo().window(newAssetWindowHandle);
				Thread.sleep(2000);
				
				newPrivateLineAssetDetailEntry();
				
								
				//Click on new button at Private line search screen
				//Switch window to private line edit screen
				//call new private line asset creation method where all elements will be identified and details will be entered
				
	}
	
	private void newPrivateLineAssetDetailEntry() throws InterruptedException {
		// TODO Auto-generated method stub
		WebElement windowLoadWait;
		String parentWindowHandle = driver.getWindowHandle();
//Mandatory Service details fields
		Select circuitSpeed = new Select(driver.findElement(By.name("cmbPrivateLineSpeed")));
		WebElement circuitId = driver.findElement(By.name("txtCircuitId"));
		WebElement originatingLocationSearchIcon = driver.findElement(By.xpath("//*[@id=\"dialog\"]/form/table/tbody/tr[8]/td/div/table[1]/tbody/tr[3]/td[2]/button/i"));
		WebElement terminatingLocationSearchIcon = driver.findElement(By.xpath("//*[@id=\"dialog\"]/form/table/tbody/tr[8]/td/div/table[1]/tbody/tr[3]/td[4]/button/i"));
		WebElement originatingLocation = driver.findElement(By.name("dfOrigAddress"));
		WebElement terminatingLocation = driver.findElement(By.name("dfTermAddress"));
		
		circuitSpeed.selectByIndex(1);
		circuitId.sendKeys("AutoPL" + assetID.substring(0, 17));
		
		originatingLocationSearchIcon.click();
		commonMethodClass.locationSearchMethod(driver);

		driver.switchTo().window(parentWindowHandle);

		terminatingLocationSearchIcon.click();
		commonMethodClass.locationSearchMethod(driver);
		
		driver.switchTo().window(parentWindowHandle);
		
		Select vendorDropdown = new Select(driver. findElement(By.name("cmbVendor")));
//DDT?
		vendorDropdown.selectByValue("1");
		WebElement vendorAccountSearchIcon = driver.findElement(By.xpath("//button[@id='btnAccount']/i[@class = 'fa fa-search']"));

		vendorAccountSearchIcon.click();
		commonMethodClass.accountSearchMethod(driver,1);
					
		driver.switchTo().window(parentWindowHandle);
				
		//WebElement vendorAccount = driver.findElement(By.id("dfAccountNum"));
		Select contractTerm = new Select(driver.findElement(By.name("cmbTerm")));
//DDT?
		contractTerm.selectByValue("1");
		
		WebElement comments = driver.findElement(By.name("comments"));
//DDT?
		comments.sendKeys("Automation asset private");
		
		WebElement saveAsset = driver.findElement(By.xpath("//button[contains (text(), 'save')]"));
		saveAsset.click();
		//WebElement closeWindow = driver.findElement(By.xpath("//button[contains (text(), 'close')]"));
		
		
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
		  
	}

}

