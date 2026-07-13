package manageRight;

import static org.testng.Assert.assertEquals;

import java.io.IOException;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchWindowException;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class NewOrderCreationAllServices {
	WebDriver driver;
	WebDriverWait wait;
	private String expectedTitle;
	private String actualTitle;
	TakesScreenshot ts;
	CommonMethods commonMethodClass = new CommonMethods();
  
@Test
  	public void NewOrderCreateAllServices() throws InterruptedException, IOException {
	 	  
		wait = new WebDriverWait(driver,30);
		
		expectedTitle = "ManageRight";	
		actualTitle = driver.getTitle();
		assertEquals(actualTitle, expectedTitle, "Incorrect Title.");
				
		commonMethodClass.selectCustomerMethod(driver);
		
		WebElement output = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("menu-orders")));
		output = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("menu-orders")));
		  WebElement OrdersLNLink= driver.findElement(By.id("menu-orders"));
		  if (OrdersLNLink.isDisplayed()) 
		  {
			  ordersModuleMethod(OrdersLNLink);
		  }
	}

	private void ordersModuleMethod(WebElement ordersLNLink) throws InterruptedException {
	// TODO Auto-generated method stub
		ordersLNLink.click(); //Orders from Left navigation
		WebElement output = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("menu-orders-new")));
		WebElement NewOrder= driver.findElement(By.id("menu-orders-new"));
		NewOrder.click(); //New order creation from Left navigation
	
		newOrderCreate(); //create new Order method
	
}

	private void newOrderCreate() throws InterruptedException {
		
	// TODO Auto-generated method stub
		String parentWindowHandle = driver.getWindowHandle();
		
		WebElement output = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"ui-id-2\"]/form/table/tbody/tr/td[2]/table[1]/tbody/tr[1]/td/table/tbody/tr/td[2]/button/i")));
		WebElement orderServiceSearchIcon = driver.findElement(By.xpath("//*[@id=\"ui-id-2\"]/form/table/tbody/tr/td[2]/table[1]/tbody/tr[1]/td/table/tbody/tr/td[2]/button/i"));
		orderServiceSearchIcon.click();
		String serviceWindowHandle = driver.getWindowHandle();
		driver.switchTo().window(serviceWindowHandle);
		try {
			Thread.sleep(2000);
			WebElement serviceCancelButton = driver.findElement(By.xpath("//button[contains(text(), 'cancel')]"));		
			WebElement serviceSelectButton = driver.findElement(By.xpath("//button[contains(text(), 'select')]"));					
			Select availableServicesList = new Select(driver.findElement(By.name("cmbAllServices")));
			WebElement selectServiceArrow = driver.findElement(By.xpath("//button[contains (text(), '>')]"));
			WebElement deselectServiceArrow = driver.findElement(By.xpath("//button[contains (text(), '<')]"));
			WebElement selectAllServicesArrow = driver.findElement(By.xpath("//button[contains (text(), '>>')]"));
			WebElement deselectAllServicesArrow = driver.findElement(By.xpath("//button[contains (text(), '<<')]"));
			//Select selectedServicesList = new Select(driver.findElement(By.name("cmbASelectedServices")));
			
			//availableServicesList.selectByIndex(1);
			
			selectAllServicesArrow.click(); //All services are moved to selected services listbox
			Thread.sleep(2000);
			serviceSelectButton.click();
			//accountCloseWindowButton.click();
		}catch(NoSuchWindowException e) {}
		
		driver.switchTo().window(parentWindowHandle);
		output = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//button[contains (text(), 'save')]")));
		WebElement saveOrder = driver.findElement(By.xpath("//button[contains (text(), 'save')]"));
		saveOrder.click();
		
	
}
	@BeforeMethod
	  public void beforeMethod() {
		
		System.setProperty("webdriver.chrome.driver", "test\\resources\\chromedriver.exe");
		driver = new ChromeDriver(); 
		driver.manage().window().maximize();
				  
		ManageRightLogin mrLogin = new ManageRightLogin (driver);			

	  }	

}
