package practicetng;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;

public class TestNGClass {
	
	WebDriver driver;
	
	@BeforeClass
	public void browserLaunch() {
				
		WebDriverManager.chromedriver().setup();
		driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.get("https://manageright-preprod-4.telebright.com/manageright/AdminServlet");
		
	}
	
	@Test(priority=1)
	public void loginCredentials() {
		driver.findElement(By.xpath("(//input[@name='username'])[2]")).sendKeys("preproduser");
		driver.findElement(By.xpath("(//input[@name='password'])[2]")).sendKeys("tele_123");
		driver.findElement(By.xpath("(//input[@name='signInSubmitButton'])[2]")).click();
		
	}
	
	@Test(priority=2)
	public void selectCustomer() {
		driver.findElement(By.xpath("//tr/*[contains(text(), 'Envision Healthcare Corporation')]")).click();
		String customerName = driver.findElement(By.xpath("//li[@class='customerName dropdown user user-menu']")).getText();
		System.out.println(customerName);
		
	}
	
	@AfterClass
	public void loginValidation() {
		String username = driver.findElement(By.xpath("//span[@class='user-name']")).getText();
		String pageTitle = driver.getTitle();
		Assert.assertEquals(pageTitle, "ManageRight");
		
		System.out.println("login user is "+ username);
	}
	
	
}
