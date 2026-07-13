package pagefactorymr;

import static org.testng.Assert.assertEquals;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.Test;

import practicetng.LogIn_main;

public class MR_HomePage {
	WebDriver driver;

	@FindBy(id = "dfCompanyName")
	WebElement companyName;

	@FindBy(id = "dfAgent")
	WebElement agentName;

	@FindBy(id = "dfFirstName")
	WebElement firstName;

	@FindBy(id = "dfLastName")
	WebElement lastName;

	@FindBy(xpath = "//button[contains(text(), 'Search')]")
	WebElement searchButton;

	@FindBy(xpath = "//button[contains(text(), 'Clear')]")
	WebElement clearButton;

	@FindBy(xpath = "//a[contains(text(), 'Next')]")
	WebElement nextPNButton;

	@FindBy(xpath = "//button[contains(text(), 'Last')]")
	WebElement lastPNButton;

	@FindBy(xpath = "//td[contains(text(),\"Envision\")]")
	WebElement customerEnvision;

	public MR_HomePage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	public void clickSearch() {
		searchButton.click();
	}

	public void clickClear() {
		clearButton.click();
	}

	public void enterCompanyName(String company) {
		companyName.sendKeys(company);
	}

	public void enterAgentName(String Agent) {
		companyName.sendKeys(Agent);
	}

	public void enterFName(String FN) {
		firstName.sendKeys(FN);
	}

	public void enterLastName(String LN) {
		lastName.sendKeys(LN);
	}

	public void clickNextPN() {
		nextPNButton.click();
	}

	public void clickLastPN() {
		lastPNButton.click();
	}

	public void clickEnvision() {
//		customerEnvision.sendKeys(envision);
		customerEnvision.click();
	}

	// a[contains(text(), 'Last')]

}
