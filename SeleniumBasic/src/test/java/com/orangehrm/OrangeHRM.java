package com.orangehrm;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;

public class OrangeHRM {

WebDriver driver;
	
	@Test (priority=1)
	public void lauchBrowser() {
		WebDriverManager.chromedriver().setup();
		driver= new ChromeDriver();
		driver.get("https://opensource-demo.orangehrmlive.com/web/index.php/auth/login");
		driver.manage().window().maximize();
		
	}
	
	@Test
	public void logIn() {
		driver.findElement(By.xpath("//input[@name='username']")).sendKeys("Admin");
		driver.findElement(By.xpath("//input[@name='password']")).sendKeys("admin123");
		driver.findElement(By.xpath("//button[@type='submit']")).click();
	}
	
	@Test 
	public void validateINFO() {
		WebElement loginInfo = driver.findElement(By.xpath("//p[@class='oxd-userdropdown-name']"));
		System.out.println("Ensure the page title is displayed " +loginInfo.isDisplayed());
		System.out.println("LogID of OrangeHRM "+loginInfo);
	}
	
	

}
