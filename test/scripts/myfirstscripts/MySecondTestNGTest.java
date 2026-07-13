package scripts.myfirstscripts;

import org.testng.annotations.Test;
import org.testng.annotations.BeforeMethod;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;

public class MySecondTestNGTest {
	WebDriver driver;
	@Test
	public void testGoogleSearch() {
		driver.get("https://www.google.com/"); 
		driver.findElement(By.name("q")).sendKeys("time"); 
				
		//driver.findElement(By.name("btnk")).click();
		
	}
	@Test
  public void nicheThyselfLogin() {
	  driver.get("http://selenium-examples.nichethyself.com/"); 
		driver.findElement(By.id("loginname")).sendKeys("stc123"); 
				
		driver.findElement(By.id("loginpassword")).sendKeys("12345");
		driver.findElement(By.id("loginbutton")).click();
  }
  @BeforeClass
  public void beforeClass() {
	   		
		System.setProperty("webdriver.chrome.driver", "test\\resources\\chromedriver.exe");
		
		driver = new ChromeDriver(); //opens chrome browser
		driver.manage().window().maximize();
  }

  @AfterClass
  public void afterClass() {
	  driver.quit();
  }

}
