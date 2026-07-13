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
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.DataProvider;


public class WirelessMiscAssetCreation {
	WebDriver driver;
	WebDriverWait wait;
	private String expectedTitle;
	private String actualTitle;
	TakesScreenshot ts;
	CommonMethods commonMethodClass = new CommonMethods();
  
@Test
  	public void WirelessMiscAssetCreate() throws InterruptedException, IOException {
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
				
				Thread.sleep(10000);
				Actions builder = new Actions(driver);
				
				builder.moveToElement(assetsLNLink).perform();
				JavascriptExecutor executor = (JavascriptExecutor)driver;
				executor.executeScript("window.scrollBy(0,300)", "");
				
				executor.executeScript("window.scrollBy(0,-300)", "");
				builder.moveToElement(assetsLNLink).perform();
				executor.executeScript("window.scrollBy(0,300)", "");
				assetsLNLink.click();
				
				WebElement output = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//a[@href='#'][contains(text(),'Wireless')]")));
				output = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//a[@href='#'][contains(text(),'Wireless')]")));
				WebElement WirelessAssetMenu = driver.findElement(By.xpath("//a[@href='#'][contains(text(),'Wireless')]"));
				WirelessAssetMenu.click();	//Click on data sub menu
				
				
				
				WebElement element = driver.findElement(By.xpath("//a[contains(text(),'Miscellaneous')]"));
				
				//executor.executeScript("arguments[0].click();", element);
				
				//executor.executeScript("window.scrollBy(0,300)", "");
				
				
				  output = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//a[contains(text(),'Miscellaneous')]")));
				  WebElement wirelessMiscAssetMenu = driver.findElement(By.xpath("//a[contains(text(),'Miscellaneous')]"));
				  wirelessMiscAssetMenu.click();
				 
				
				output = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//button[contains(text(), 'new')]")));
				WebElement NewWirelessMiscAsset = driver.findElement(By.xpath("//button[contains(text(), 'new')]"));
				NewWirelessMiscAsset.click();
				String newAssetWindowHandle = driver.getWindowHandle();
				driver.switchTo().window(newAssetWindowHandle);
				Thread.sleep(2000);
				
				newWirelessMiscAssetDetailEntry();
				
	}
	
	private void newWirelessMiscAssetDetailEntry() throws InterruptedException, IOException {
		// TODO Auto-generated method stub
		//Mandatory fields- phone number, organization, vendor, account number and contract term
		String parentWindowHandle = driver.getWindowHandle();
				
		Select organization = new Select(driver.findElement(By.id("cmbOrganization")));
		WebElement phoneNumber = driver.findElement(By.id("dfCardNum"));
		
		phoneNumber.sendKeys("2001234569");
		organization.selectByIndex(1);
		
		Select vendorDropdown = new Select(driver. findElement(By.name("cmbVendor")));
		
		//vendorDropdown.selectByValue("1"); //DDT?
		vendorDropdown.selectByVisibleText("AT&T");
		driver.findElement(By.xpath("//button[@id='btnAccount']")).click();
		commonMethodClass.accountSearchMethod(driver,1);
		driver.switchTo().window(parentWindowHandle);
		try {
			Thread.sleep(3000);
			driver.switchTo().alert().accept();;
	
			}catch(Exception e) {System.out.println("Exception Occurred");}		
		
		Select contractTerm = new Select(driver.findElement(By.id("cmbTerm")));
		contractTerm.selectByValue("1"); //DDT?
		
		WebElement comments = driver.findElement(By.name("comments"));
		comments.sendKeys("Automation Wireless asset Miscellaneous"); //DDT?
		
		WebElement saveAsset = driver.findElement(By.xpath("//button[contains (text(), 'save')]"));
		saveAsset.click();
		//WebElement closeWindow = driver.findElement(By.xpath("//button[contains (text(), 'close')]"));
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
