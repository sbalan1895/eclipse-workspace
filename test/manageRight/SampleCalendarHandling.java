package manageRight;//Opening Chrome Browser
//package browser;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.awt.*;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.TimeUnit;

class BrowserSelection
{
static WebDriver driver;

public static WebDriver usingChrome()
{
System.setProperty("webdriver.chrome.driver", "E:\\SeleniumLibs\\\\chromedriver_win32\\chromedriver.exe"); 
driver = new ChromeDriver(); 
driver.manage().window().maximize();
return driver;
} 
}

//Test to select a desired date in the datepicker for departure
/*
package manageRight;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.event.KeyEvent;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.WebElement;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import browser.BrowserSelection;*/

class MakeMyTripDateTest {
    WebDriver driver;
    private String select_day;

    @BeforeMethod
    public void openBrowser() {

        driver = BrowserSelection.usingChrome();
    }

    @Test
    public void tripDetails() throws InterruptedException, AWTException {

//Modify Wait time as per the Network Ability in the Thread Sleep method

        driver.get("https://www.makemytrip.com/");
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        Thread.sleep(5000);

        try {

            driver.findElement(By.xpath("//input[@id='hp-widget__depart']")).click();
            Thread.sleep(2000);

            Date d = new Date(1);
            SimpleDateFormat formatter = new SimpleDateFormat("dd-MMMM-yyyy");
            String date = formatter.format(d);
            String splitter[] = date.split("-");
            String month_year = splitter[1];
            String day = splitter[0];
            System.out.println(month_year);
            System.out.println(day);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }


/*
selectDate(month_year,day);
Thread.sleep(3000);

    public void selectDate(String month_Year, String select_day)
    {
        List<WebElement> elements = driver.findElements(By.xpath("//div[@class='ui-datepicker-title']/span[1]"));

        for (int i = 0; i < elements.size(); i++) {
            System.out.println(elements.get(i).getText());

//Selecting the month
            if (elements.get(i).getText().equals(month_year)) {

//Selecting the date 
                List<WebElement> days = driver.findElements(By.xpath("//div[@class='ui-datepicker-inline ui-datepicker ui-widget ui-widget-content ui-helper-clearfix ui-corner-all ui-datepicker-multi ui-datepicker-multi-2']/div[2]/table/tbody/tr/td/a"));

                for (WebElement d : days) {
                    System.out.println(d.getText());
                    if (d.getText().equals(select_day)) {
                        d.click();
                        Thread.sleep(10000);
                        return;
                    }
                }

            }

        }
        driver.findElement(By.xpath("//div[@class='ui-datepicker-inline ui-datepicker ui-widget ui-widget-content ui-helper-clearfix ui-corner-all ui-datepicker-multi ui-datepicker-multi-2']/div[2]/div/a/span")).click();
        selectDate(month_year, select_day);

    }
}
*/
@AfterMethod
public void closeBrowser()
{
driver.quit();
}
}