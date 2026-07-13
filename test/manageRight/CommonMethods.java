package manageRight;

import java.io.IOException;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchWindowException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

public class CommonMethods {
	WebElement windowLoadWait;
	WebDriverWait wait;
		
	public void selectCustomerMethod(WebDriver driver) throws InterruptedException, IOException {
		
		String searchText="American";
	 
		driver.findElement(By.id("dfCompanyName")).sendKeys(searchText);
		driver.findElement(By.xpath("//button[@type='submit']")).click();
		List <WebElement> customerList = driver.findElements(By.xpath("//tr[contains(@onclick, 'gotoInvoice')]"));
		
		//System.out.println("size is" + customerList.size());- why it is coming as zero
		//customerList.get(0).click();
		driver.findElement(By.xpath("//td[contains(text(),'American Medical Response')]")).click();

	}

	public void accountSearchMethod(WebDriver driver, int index) throws InterruptedException {
		// TODO Auto-generated method stub
		
		String accountWindowHandle = driver.getWindowHandle();
		driver.switchTo().window(accountWindowHandle);
					
			try {
					Thread.sleep(2000);
					WebElement accountCloseWindowButton = driver.findElement(By.xpath("//button[contains(text(), 'close')]"));		
										
					Select accountNumberList = new Select(driver.findElement(By.id("cmbAccountNum")));
					
					accountNumberList.selectByIndex(index);

					Thread.sleep(2000);
					//WebElement output = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//button[contains(text(), 'select')]")));
					WebElement accountSelectButton = driver.findElement(By.xpath("//*[@id=\"formSelectAccount\"]/table[2]/tbody/tr[2]/td/button[1]"));
					accountSelectButton.click();
					//accountCloseWindowButton.click();
				}catch(NoSuchWindowException e) {}
		
		//driver.switchTo().window(parentWindowHandle);
		
	}

	public void orgSearchMethod(WebDriver driver) throws InterruptedException {
		// TODO Auto-generated method stub
		String organizationSearchWindowHandle = driver.getWindowHandle();
		driver.switchTo().window(organizationSearchWindowHandle);
		
		try {
			Thread.sleep(2000);
			WebElement orgCloseWindowButton = driver.findElement(By.xpath("//button[contains(text(), 'close')]"));		
			WebElement orgSelectButton = driver.findElement(By.xpath("//button[contains(text(), 'select')]"));
			WebElement orgSearchButton = driver.findElement(By.xpath("//button[contains(text(), 'search')]"));
			WebElement orgClearButton = driver.findElement(By.xpath("//button[contains(text(), 'clear')]"));
			Select orgNumberList = new Select(driver.findElement(By.id("cmbOrgn")));
			
			orgNumberList.selectByIndex(1); //DDT?
			Thread.sleep(2000);
			orgSelectButton.click();
			//orgCloseWindowButton.click();
		}catch(NoSuchWindowException e) {}
		
	}

	public void employeeSearchMethod(WebDriver driver) throws InterruptedException {
		// TODO Auto-generated method stub
		String employeeSearchWindowHandle = driver.getWindowHandle();
		driver.switchTo().window(employeeSearchWindowHandle);
		
		try {
			Thread.sleep(4000);
			WebElement empCancelWindowButton = driver.findElement(By.xpath("//button[contains(text(), 'cancel')]"));		
			WebElement empSelectButton = driver.findElement(By.xpath("//button[contains(text(), 'select')]"));
			WebElement empSearchButton = driver.findElement(By.xpath("//button[contains(text(), 'search')]"));
			
			Select empNumberList = new Select(driver.findElement(By.name("cmbEmployee")));
			
			empNumberList.selectByIndex(1);
			Thread.sleep(4000);
			empCancelWindowButton.click();
			empSelectButton.click();
			//empCloseWindowButton.click();
		}catch(NoSuchWindowException e) {}
		
	}

	public void locationSearchMethod(WebDriver driver) throws InterruptedException {
		// TODO Auto-generated method stub
		
		String locationWindowHandle = driver.getWindowHandle();
		driver.switchTo().window(locationWindowHandle);
		try {
			
			wait = new WebDriverWait (driver, 30);
			windowLoadWait = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("cmbLocationName")));
			
			WebElement closeWindowButton = driver.findElement(By.xpath("//button[contains(text(), 'close')]"));		
			
			WebElement clearButton = driver.findElement(By.xpath("//form[@id='formSelectLocation']//button[contains(text(), 'clear')]"));	

			clearButton.click();
			Thread.sleep(2000);
			WebElement selectButton = driver.findElement(By.xpath("//button[contains(text(), 'select')]"));
			WebElement locationWait = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("cmbLocationName")));
			
			Select locationsList = new Select(driver.findElement(By.id("cmbLocationName")));
//DDT?		
			
			locationsList.selectByValue("21993");
			selectButton.click();
			//closeWindowButton.click();
		}catch(NoSuchWindowException e) {}
		
	}

	public void planSearchMethod(WebDriver driver) throws InterruptedException {
		// TODO Auto-generated method stub
		String planWindowHandle = driver.getWindowHandle();
		driver.switchTo().window(planWindowHandle);
					
			try {
					Thread.sleep(2000);
					WebElement planCloseWindowButton = driver.findElement(By.xpath("//button[contains(text(), 'close')]"));		
					WebElement planSelectButton = driver.findElement(By.xpath("//button[contains(text(), 'select')]"));
					Select plansList = new Select(driver.findElement(By.id("cmbPlans")));
					
					plansList.selectByValue("1");
					Thread.sleep(2000);
					planSelectButton.click();
					//accountCloseWindowButton.click();
				}catch(NoSuchWindowException e) {}
		
	}

	public void accountSearchMethod(WebDriver driver, String accountNumber) throws InterruptedException {
		// TODO Auto-generated method stub
		String accountWindowHandle = driver.getWindowHandle();
		driver.switchTo().window(accountWindowHandle);
					
			try {
					Thread.sleep(2000);
					WebElement accountCloseWindowButton = driver.findElement(By.xpath("//button[contains(text(), 'close')]"));		
					WebElement accountSelectButton = driver.findElement(By.xpath("//button[contains(text(), 'select')]"));					
					Select accountNumberList = new Select(driver.findElement(By.id("cmbAccountNum")));

					accountNumberList.selectByVisibleText(accountNumber);


				Thread.sleep(2000);
					accountSelectButton.click();
					//accountCloseWindowButton.click();
				}catch(NoSuchWindowException e) {}
		
		//driver.switchTo().window(parentWindowHandle);
		
		
	}
}