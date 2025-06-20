package com.testbasic;

import java.time.Duration;
import java.util.concurrent.TimeoutException;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import io.github.bonigarcia.wdm.WebDriverManager;

public class ButtonSelect {
	public static void main(String[] args) {

		WebDriverManager.chromedriver().setup();
		WebDriver driver = new ChromeDriver();
		driver.get("https://demoqa.com/radio-button");
		driver.manage().window().maximize();

		WebDriverWait waits = new WebDriverWait(driver, Duration.ofSeconds(10));

//		try {

			WebElement yesClk = waits.until(ExpectedConditions
					.visibilityOfElementLocated(By.xpath("//input[@id='yesRadio']/following-sibling::label")));
			yesClk.click();
			
			System.out.println("Yes radio button is selected");
			
//		} catch (org.openqa.selenium.TimeoutException e) {
//			System.out.println("Timeout: Element not clickable in time");
//		} catch (org.openqa.selenium.ElementClickInterceptedException e) {
//			System.out.println("Intercepted: Element locator get intercepted");
//		} catch (org.openqa.selenium.NoSuchElementException e) {
//			System.out.println("NoSuchElement: Element not found");
//		} catch (Exception e) {
//			System.out.println(e.getMessage());
//		} finally {
			WebElement messagePresent = waits
					.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[text()='You have selected ']")));
			System.out.println(messagePresent.getText());

			driver.quit();
		}
	}
//}

//		

// *[text()='You have selected ']
//		yesRadio

//	
