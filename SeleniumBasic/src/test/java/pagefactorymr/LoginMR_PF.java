package pagefactorymr;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LoginMR_PF {
	
	WebDriver driver;
	
	@FindBy(xpath="(//input[@name='username'])[2]")
	WebElement username;
	
	@FindBy(xpath="(//input[@name='password'])[2]")
	WebElement password;
	
	@FindBy(xpath="(//input[@name='signInSubmitButton'])[2]")
	WebElement signInButton;
	
	public LoginMR_PF(WebDriver driver) {
		this.driver=driver;
		
		PageFactory.initElements(driver, this);
	}
	
	
	public void enterUserName(String userID) {
		username.sendKeys(userID);
	}
	
	public void enterPassword(String pass) {
		password.sendKeys(pass);
	}
	
	public void clickSignIn() {
		signInButton.click();
	}
		
}
