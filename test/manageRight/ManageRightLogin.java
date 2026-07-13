package manageRight;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class ManageRightLogin {
	
	
	public ManageRightLogin(WebDriver driver) {
		
		//driver.get("https://manageright-pre-prod.telebright.com/manageright/AdminServlet");
		//driver.get("https://manageright-elb.telebright.com/manageright/AdminServlet");
		driver.get("https://manageright-demo.telebright.com/manageright/AdminServlet");
	//	WebDriverWait wait = new WebDriverWait(driver, 30);
	//	WebElement element = wait.until(
	//	ExpectedConditions.visibilityOfElementLocated(By.id("Passwd")));
		//driver.findElement(By.id("Passwd")).sendKeys("xyz");
		//river.findElement(By.xpath("//a[@class='btn btn-sm signinbutton signin-anchor']")).click();
		driver.findElement(By.xpath("//div[@class='modal-content background-customizable modal-content-mobile visible-md visible-lg']//div[@class='modal-body']//div//div//div//div//form[@class='cognito-asf']//div//input[@id='signInFormUsername']")).sendKeys("sri123");
		driver.findElement(By.xpath("//div[@class='modal-content background-customizable modal-content-mobile visible-md visible-lg']//div[@class='modal-body']//div//div//div//div//form[@class='cognito-asf']//div//input[@id='signInFormPassword']")).sendKeys("sri_2345");
		driver.findElement(By.xpath("//div[@class='modal-content background-customizable modal-content-mobile visible-md visible-lg']//div[@class='modal-body']//div//div//div//div//form[@class='cognito-asf']//input[@type='Submit']")).click();
	}
	}


