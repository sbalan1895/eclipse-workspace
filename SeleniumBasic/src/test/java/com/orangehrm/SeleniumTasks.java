package com.orangehrm;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;
import practicetng.LogIn_main;

public class SeleniumTasks {

	public static void main(String[] args) {

		WebDriver driver;

//	@Test
//	public void logIn() {

		WebDriverManager.chromedriver().setup();
		driver = new ChromeDriver();
		driver.manage().window().maximize();

		try {
			driver.get("https://www.hyrtutorials.com/");
			WebElement tymore = driver.findElement(By.xpath("//a[@class='tymor']"));

//			try {
				if (tymore.isDisplayed()) {
					System.out.println("Button is enabled");
				} else {
					System.out.println("Button is disabled");
				}
			} catch (Exception e) {
				System.out.println("Error while checking button state: " + e.getMessage());
				System.out.println(e.getStackTrace());
				e.printStackTrace();
			}
//		} catch (Exception e) {
//			System.out.println("Error during test excution " + e.getMessage());
//			System.out.println(e.getStackTrace());
//			e.printStackTrace();
//		}

		finally {
			driver.quit();
			System.out.println("Testcase for button enabled/disabled excuted with Try/Catch/Finally blocks");
		}
	}
}
