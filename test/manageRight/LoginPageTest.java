package manageRight;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.Test;
import org.testng.annotations.BeforeMethod;

import static org.testng.Assert.assertEquals;

import org.testng.annotations.AfterMethod;

class LoginPageTest {
	LoginPO myLoginPage;
	MRHomePagePO myHomePage;
    WebDriver driver;
@Test
	public void MRLoginPage() {
	  myHomePage = myLoginPage.login("incubyte", "test123");
	  assertEquals(myLoginPage.getNextPageTitle(),"ManageRight");

  }
  
  
  
  @BeforeMethod
  public void beforeMethod() {
	  myLoginPage = new LoginPO(driver);
	  //myLoginPage.get();
  }

  @AfterMethod
  public void afterMethod() {
	  myLoginPage.closeBrowser();
  }

}

