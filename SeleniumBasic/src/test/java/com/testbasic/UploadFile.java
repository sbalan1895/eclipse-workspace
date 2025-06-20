package com.testbasic;

import java.util.NoSuchElementException;
import org.openqa.selenium.TimeoutException;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import io.github.bonigarcia.wdm.WebDriverManager;

public class UploadFile {
	public static void main(String[] args) {

		WebDriverManager.chromedriver().setup();
		WebDriver driver = new ChromeDriver();
		driver.get("https://the-internet.herokuapp.com/upload");
		driver.manage().window().maximize();
		try {
			driver.findElement(By.id("file-upload"))
					.sendKeys("E:\\Sbalan018\\MY_STUDY\\TestStudy\\StudyJS\\First Day Program.docx");

			driver.findElement(By.id("file-submit")).click();

			WebElement element = driver.findElement(By.id("uploaded-files"));
			String fileName = element.getText();

			System.out.println("Uploaded file name: " + fileName);

			if (fileName.contains("First Day")) {
				System.out.println("File uploaded successfully");
			}

			else {
				System.out.println("File upload failed");
			}
		} catch (NoSuchElementException e) {
			System.out.println("Element not found: " + e.getMessage());
		} catch (TimeoutException e) {
			System.out.println("Operation time out: " + e.getMessage());
		} catch (Exception e) {
			System.out.println("Element not found: " + e.getMessage());
		}

		finally {
			if (driver != null) {
				driver.quit();
			}
		}
	}
}
