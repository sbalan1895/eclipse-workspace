package scripts.myfirstscripts;

import org.testng.annotations.Test;
import org.testng.annotations.BeforeMethod;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterMethod;
//to print visible text of links and their URL from google.com page

public class GoogleLinksPrintText {
	WebDriver driver;
  @Test
  public void printGoogleLinkText() {
	  driver.get("http://www.google.com/");
	  System.out.println("Visible Text for all links are ");
	  List<WebElement> allGoogleLinks= driver.findElements(By.tagName("a"));
	  //driver.findElement(By.cssSelector("a"));
	  //driver.findElement(By.xpath("//a"));
	  /*for(int i=0; i<allGoogleLinks.size();i++) {
		  System.out.println(allGoogleLinks.get(i).getText() + "-" + allGoogleLinks.get(i).getAttribute("href"));
	  }*/
	  for (WebElement oneLink : allGoogleLinks) {
		  System.out.println(oneLink.getText() + "-" + oneLink.getAttribute("href"));
	  }
	  System.out.println("hello");
	  System.out.println(driver.findElement(By.tagName("body")).getText());
  }
  @BeforeMethod
  public void beforeMethod() {
	  System.setProperty("webdriver.chrome.driver", "test\\resources\\chromedriver.exe");
		
		driver = new ChromeDriver();
		driver.manage().window().maximize();
  }

  @AfterMethod
  public void afterMethod() {
	  driver.quit();
	  
  }

}
