package practicetng;

import static org.testng.Assert.assertEquals;

import org.jspecify.annotations.Nullable;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;


import io.github.bonigarcia.wdm.WebDriverManager;
import pagefactorymr.LoginMR_PF;
import pagefactorymr.MR_HomePage;

public class LogIn_main {

	WebDriver driver;
	LoginMR_PF logIn;
	MR_HomePage mrhome;

	@BeforeClass
	public void lauchBrowser() {
		
		WebDriverManager.chromedriver().setup();
		
		ChromeOptions chromeOptions = new ChromeOptions();
		chromeOptions.addArguments("--incognito");
		
		chromeOptions.addArguments("--disable-infobars");
		chromeOptions.setExperimentalOption("excludeSwitches", new String[] { "enable-automation" });

		driver = new ChromeDriver(chromeOptions);
		driver.manage().window().maximize();
		driver.get("https://manageright-preprod-4.telebright.com/manageright/AdminServlet");

	}

	@Parameters({"userID", "pass"})
	@Test (priority= 1)
	public void loginMR(String userID, String pass) {

//		driver.findElement(By.xpath("(//input[@name='username'])[2]")).sendKeys(userID);
//		driver.findElement(By.xpath("(//input[@name='password'])[2]")).sendKeys(pass);
//		driver.findElement(By.xpath("(//input[@name='signInSubmitButton'])[2]")).click();
		
		logIn = new LoginMR_PF(driver);
		logIn.enterUserName(userID);
		logIn.enterPassword(pass);
		logIn.clickSignIn();
	}
	
	@Parameters({"company"})
	@Test(priority= 2)
	
	public void selectCustomerAccount(String company) {
		
		mrhome = new MR_HomePage(driver);
		
		String exceptedTitle = "ManageRight";
		String actualTitle = driver.getTitle();
		
		assertEquals(exceptedTitle, actualTitle);
		
//		String Customer="Envision Healthcare Corporation";
		
		mrhome.enterCompanyName(company);
		mrhome.clickSearch();
		mrhome.clickEnvision();
	}

	@AfterClass
	public void MR_Login_Sucessfully() {
		@Nullable
		String pagetitle = driver.getTitle();
		System.out.println(pagetitle);
		
		System.out.println("MR_LoggedIn Successfuly");
	}

}
