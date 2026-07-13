package manageRight;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class CalendarHandling {
	//div[@id="ui-datepicker-div"]

	
	// Thread.sleep(2000);

	Date d = new Date(1);
	SimpleDateFormat formatter = new SimpleDateFormat("dd-MMMM-yyyy");
	String date = formatter.format(d);
	String splitter[] = date.split("-");
	String month_year = splitter[1];
	String day = splitter[0]; 
	//System.out.println(month_year);
	//System.out.println(day);

/*
	selectDate(month_year,day); 
	Thread.sleep(3000);
*/

	public void selectDate(String month_year, String select_day) throws InterruptedException
	{
		WebDriver driver = null;
		List<WebElement> elements = driver.findElements(By.xpath("//div[@class='ui-datepicker-title']/span[1]"));

	for (int i=0; i<elements.size();i++)
	{
	System.out.println(elements.get(i).getText());

	//Selecting the month
	if(elements.get(i).getText().equals(month_year))
	{ 

	//Selecting the date 
	List<WebElement> days = driver.findElements(By.xpath("//div[@class='ui-datepicker-inline ui-datepicker ui-widget ui-widget-content ui-helper-clearfix ui-corner-all ui-datepicker-multi ui-datepicker-multi-2']/div[2]/table/tbody/tr/td/a"));

	for (WebElement d:days)
	{ 
	System.out.println(d.getText());
	if(d.getText().equals(select_day))
	{
	d.click();
	Thread.sleep(10000);
	return;
	}
	} 

	} 

	}
	driver.findElement(By.xpath("//div[@class='ui-datepicker-inline ui-datepicker ui-widget ui-widget-content ui-helper-clearfix ui-corner-all ui-datepicker-multi ui-datepicker-multi-2']/div[2]/div/a/span")).click();
	selectDate(month_year,select_day);

	}

}
