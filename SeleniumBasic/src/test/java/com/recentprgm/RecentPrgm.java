package com.recentprgm;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

import org.apache.hc.core5.util.Asserts;
import org.jspecify.annotations.Nullable;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;

public class RecentPrgm {
	
	WebDriver driver;
	
	@BeforeClass
	public void launch() {
		WebDriverManager.chromedriver().setup();
//		driver= new ChromeDriver();
		driver= new ChromeDriver();
		
		driver.get("https://www.redbus.in/");
		driver.manage().window().maximize();
		
	}
	
	@Test(priority=1)
	public void validateLogin() {
		
		String actualTitle = driver.getTitle();
		System.out.println(actualTitle);
		
		WebElement redbusLogo = driver.findElement(By.xpath("//img[@alt='redBus logo']"));
//		redbusLogo.click();
			
		assertTrue(redbusLogo.isDisplayed());
		
		System.out.println("User successfully in RedBus HomePage");
		
	}
	
	@AfterClass
	public void browserQuit() {
		
//		driver.quit();
		System.out.println("Prgm excuted");
	}
	

}
