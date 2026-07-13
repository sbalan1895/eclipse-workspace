package manageRight;

import io.github.bonigarcia.wdm.managers.ChromeDriverManager;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.Test;
import org.testng.annotations.BeforeMethod;
import org.openqa.selenium.By;


public class LoginPO {
    private WebDriver driver;

    public LoginPO(WebDriver driver2) {
        driver2 = this.driver;
        // TODO Auto-generated constructor stub
    }


    @BeforeMethod
    public void beforeMethod() {
        System.setProperty("webdriver.chrome.driver", "test\\resources\\chromedriver.exe");


        driver.get("https://manageright-pre-prod.telebright.com/manageright/AdminServlet");
        driver.findElement(By.id("INPUT_Login")).sendKeys("deepali1");

        driver.findElement(By.id("INPUT_Password")).sendKeys("Ab@12345");
        driver.findElement(By.xpath("//button[@type='submit']")).click();

        //verify page title is ManageRight
        //AssertEqual()
    }

    @Test
    public void SelectCustomerAccount() {
        //Select Account as AMR
        driver.findElement(By.xpath("//tr/*[contains(text(), 'American Medical Response')]")).click();

        //Select Invoices from Left Navigation
        driver.findElement(By.xpath("//span[contains(text(), 'Invoices')]")).click();


        //Select New Invoice Entry from Left navigation
        driver.findElement(By.id("menu-invoices-new")).click();
        //assert title of page
        String InvoiceEntryScreenTitle = driver.findElement(By.xpath("//h1[contains(text(), 'New Invoice')]")).getText();
        //Above line will fail if title changes. Needs to be located by some other ID


    }

    // @AfterMethod
    public void afterMethod() {
        //Logout from MR

        //throwing ElementNotInteractableException. May be due to no Wait. Need to debug

        try {
            driver.findElement(By.xpath("//li/*[@class='dropdown-menu']")).click();
            driver.findElement(By.xpath("//a[@href='AdminServlet?pageName=LogOff']")).click();
        } catch (NullPointerException e) {
            System.out.println("It is a Null Pointer Exception");

        }
        //driver.quit();
    }

    public MRHomePagePO login(String loginId, String passWord) {
        // TODO Auto-generated method stub
        driver.findElement(By.name("username")).sendKeys(loginId);
        driver.findElement(By.name("password")).sendKeys(passWord);
        driver.findElement(By.xpath("//button[@type='submit']")).click();
        return new MRHomePagePO(driver);
    }

    public String getNextPageTitle() {
        // TODO Auto-generated method stub
        String CurrentPageURL = driver.getTitle();

        return CurrentPageURL;
    }

    public void closeBrowser() {
        // TODO Auto-generated method stub

    }

}
