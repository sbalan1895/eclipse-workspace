package scripts.myfirstscripts;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class MyFirstWebDriverTest {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		//System.out.println("hi");
		WebDriver driver; //webdriver is an interface and no methods are implemented here, they are implemented in ChromeDriver
		
		System.setProperty("webdriver.chrome.driver", "test\\resources\\chromedriver.exe");
		//above can be done by adding new user variable webdriver.chrome.driver in Advanced system settings at your PC
		//but above method is better as it provides portability and can be used at any machine
		
		driver = new ChromeDriver(); //opens chrome browser
		driver.manage().window().maximize();
		
		driver.get("http://selenium-examples.nichethyself.com/"); //defined in webdriver and implemented in chromedriver
		driver.findElement(By.id("loginname")).sendKeys("stc123"); //method chaining
		//sendKeys method is defined in WebElement class as findElement returns WebElement object
		
		//WebElement user = driver.findElement(By.id("loginname"));
		//user.sendKeys("stc123");
		//line 25 and 26 combined are doing same as in line 22
		
		driver.findElement(By.id("loginpassword")).sendKeys("12345");
		driver.findElement(By.id("loginbutton")).click();
		
		
		driver.quit();
		

	}

}
