package manageRight;

import static org.testng.Assert.assertEquals;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.LoadableComponent;

import notUsed.selectAccountPO;

public class MRHomePagePO extends LoadableComponent<MRHomePagePO> {

	@FindBy (xpath = "")
	@CacheLookup
	private WebElement selectAccount;
	
	@FindBy (xpath = "")
	@CacheLookup
	private WebElement signOut;
	
	private WebDriver driver;

	private String expectedTitle = "ManageRight";
	
	public MRHomePagePO(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	@Override
	protected void load() {
		// TODO Auto-generated method stub
		
	}

	@Override
	protected void isLoaded() throws Error {
		// TODO Auto-generated method stub
		assertEquals(driver.getTitle(),expectedTitle ,"My Account Page Load Failed.");
	}
	
	public selectAccountPO goToContactUs() {
		selectAccount.click();
		String parenetWindowHandle = driver.getWindowHandle();
		//driver.switchTo().window("Contact");
		selectAccountPO myselectAccount = new selectAccountPO(driver,parenetWindowHandle);
		return myselectAccount;
	}
	
	
	public LoginPO signOut() {
		signOut.click();
		return new LoginPO(driver);
	}

	public MRHomePagePO login(String string, String string2) {
		// TODO Auto-generated method stub
		return null;
	}
	

}
