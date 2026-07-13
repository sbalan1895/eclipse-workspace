package manageRight;

import static org.testng.Assert.assertEquals;

import java.io.IOException;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.NoSuchWindowException;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class NewContract {
	WebDriver driver;
	WebDriverWait wait;
	private String expectedTitle;
	private String actualTitle;
	TakesScreenshot ts;
	CommonMethods commonMethodClass = new CommonMethods();
  

@Test
	public void NewContractCreation() throws InterruptedException {
	// TODO Auto-generated method stub
		
		WebElement contractLink = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("menu-contracts")));
		
		contractLink.click();
		
		WebElement NewContract = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("menu-contracts-new")));
		
		NewContract.click(); 
	
		newContractCreate(); 
	
}

	private void newContractCreate() throws InterruptedException {
		
	// TODO Auto-generated method stub
		String parentWindowHandle = driver.getWindowHandle();
		
		WebElement servicesSearch = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//button[contains(@onclick, 'selectServices')]")));
		servicesSearch.click();
		
		String serviceWindowHandle = driver.getWindowHandle();
		driver.switchTo().window(serviceWindowHandle);
		try {
			Thread.sleep(2000);
			WebElement serviceCloseButton = driver.findElement(By.xpath("//button[contains(text(), 'close')]"));		
			WebElement serviceSelectButton = driver.findElement(By.xpath("//button[contains(text(), 'select')]"));
			
			Select availableServicesList = new Select(wait.until(ExpectedConditions.visibilityOfElementLocated(By.name("cmbAvailableService"))));
						
			WebElement selectServiceArrow = driver.findElement(By.xpath("//*[@id=\"formPopup\"]/table[2]/tbody/tr[2]/td[2]/button[1]")); //no unique id with button[contains (text(), '>')]
			WebElement deselectServiceArrow = driver.findElement(By.xpath("//button[contains (text(), '<')]"));
			WebElement selectAllServicesArrow = driver.findElement(By.xpath("//button[contains (text(), '>>')]"));
			WebElement deselectAllServicesArrow = driver.findElement(By.xpath("//button[contains (text(), '<<')]"));
			//Select selectedServicesList = new Select(driver.findElement(By.name("cmbASelectedServices")));
			
			/*
			 * Actions action = new Actions(driver);
			 * availableServicesList.selectByVisibleText("Wireless");
			 * action.doubleClick().build().perform();
			 */
			
			  availableServicesList.selectByVisibleText("Wireless");
			  selectServiceArrow.click();
			 

			Thread.sleep(2000);
			serviceSelectButton.click();
			//accountCloseWindowButton.click();
		}catch(NoSuchWindowException e) {}
		
		driver.switchTo().window(parentWindowHandle);
		
		Select vendorDropdown = new Select(driver. findElement(By.name("cmbVendorFilter")));
		vendorDropdown.selectByVisibleText("101Netlink");
		
		WebElement vendorAccountSearchIcon = driver.findElement(By.xpath("//button[contains(@onclick, 'selectAccount')]"));
		vendorAccountSearchIcon.click();
		commonMethodClass.accountSearchMethod(driver,1);
		
		WebElement contractDescription = driver.findElement(By.name("dfContractDescription"));
		contractDescription.sendKeys("New Contract Automated");
		
		Select contractType = new Select(wait.until(ExpectedConditions.visibilityOfElementLocated(By.name("cmbContractTypeFilter")))); 
		contractType.selectByIndex(1);
		
		Select invoiceLocation = new Select(wait.until(ExpectedConditions.visibilityOfElementLocated(By.name("cmbInvoiceLocation")))); 
		invoiceLocation.selectByIndex(1);
		
		WebElement adminSearchIcon = driver.findElement(By.xpath("//button[contains(@onclick, 'changeManager')]"));
		adminSearchIcon.click();
		serviceWindowHandle = driver.getWindowHandle();
		driver.switchTo().window(serviceWindowHandle);
		try {
			Thread.sleep(2000);
			Select employee = new Select(wait.until(ExpectedConditions.visibilityOfElementLocated(By.name("cmbEmployee"))));
			employee.selectByIndex(1);
			
			Thread.sleep(2000);
			WebElement employeeSelect = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//button[contains(@onclick, 'returnCustomer')]")));
			
			JavascriptExecutor executor = (JavascriptExecutor)driver;
			//executor.executeScript("arguments[0].scrollIntoView(true);", employeeSelect);
			executor.executeScript("window.scrollBy(0,300)", "");
			
			employeeSelect.click();
			
			
			}catch(NoSuchWindowException e) {}
		
		driver.switchTo().window(parentWindowHandle);
			
		
		WebElement MARCAmount = driver.findElement(By.name("dfAnnualCommitment"));
		MARCAmount.sendKeys("10");
		
		WebElement effectiveDate = driver.findElement(By.id("dfEffectiveDate"));
		effectiveDate.sendKeys("04/01/2021");
		
		WebElement noticePeriodDays = driver.findElement(By.name("dfNoticePeriod"));
		noticePeriodDays.sendKeys("30");
		
		
		  WebElement saveContract = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//button[contains (text(), 'save')]")));
		  
		  saveContract.click();
		 
				
	
}
@BeforeClass
	  public void beforeClass() throws InterruptedException, IOException {
		
		System.setProperty("webdriver.chrome.driver", "test\\resources\\chromedriver.exe");
		driver = new ChromeDriver(); 
		driver.manage().window().maximize();
				  
		ManageRightLogin mrLogin = new ManageRightLogin (driver);		
		wait = new WebDriverWait(driver,30);
		
		expectedTitle = "ManageRight";	
		actualTitle = driver.getTitle();
		assertEquals(actualTitle, expectedTitle, "Incorrect Title.");
				
		commonMethodClass.selectCustomerMethod(driver);
		
		
	}

}
