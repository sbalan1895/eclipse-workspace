package com.locator;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import io.github.bonigarcia.wdm.WebDriverManager;

public class LocatorElement {

	public static void main(String[] args) {
		
		WebDriver driver;

		WebDriverManager.chromedriver().setup();

//		ChromeOptions chromeOptions = new ChromeOptions();
//		chromeOptions.addArguments("--incognito");
//
//		chromeOptions.addArguments("--disable-infobars");
//		chromeOptions.setExperimentalOption("excludeSwitches", new String[] { "enable-automation" });
//
//		driver = new ChromeDriver(chromeOptions);
		
		driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.get("hthttps://www.gtpcm.ford.com/connectionSelection/Change");
		
		

	}

}
