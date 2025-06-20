package practicetng;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;

public class PracticeTestNG {
	
	//div[@class="imageSliderModal modal displayBlock modalLogin dynHeight personal"]
	
	WebDriver driver;
	
	@BeforeClass
	public void lauchBrowser() {
		
		WebDriverManager.chromedriver().setup();
		
		ChromeOptions chromeOptions = new ChromeOptions();
		chromeOptions.addArguments("--incognito");
		
		chromeOptions.addArguments("--disable-infobars");
		chromeOptions.setExperimentalOption("excludeSwitches", new String[] { "enable-automation" });

		driver = new ChromeDriver(chromeOptions);
		driver.manage().window().maximize();
		driver.get("https://www.makemytrip.com/");

	}
	
	@Test
	public void alertHandles() {
		
		//span[@class='commonModal__close']
		driver.findElement(By.xpath("//span[@class='commonModal__close']")).click();
		
		System.out.println("ImageSlider Closed");
		
//		Alert alert = driver.switchTo().alert();
//		
//		WebElement imageSlider = driver.findElement(By.xpath("/div[@class=\"imageSliderModal modal displayBlock modalLogin dynHeight personal\"]"));
//		
//		String text = alert.getText();
//		System.out.println(text);
	}
	
	
	

}
