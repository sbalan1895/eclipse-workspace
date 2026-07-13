package manageRight;


import com.google.common.io.Files;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.io.File;
import java.io.IOException;

public class MRLoginPageFirstTest {
    WebDriver driver;
    WebDriverWait wait;
    private String expectedTitle;
    private String actualTitle;
    TakesScreenshot ts;


    @BeforeClass
    public void beforeClassd() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().window().maximize();

        ManageRightLogin mrLogin = new ManageRightLogin(driver);
    }

    @Test
    public void SelectCustomerAccount() throws InterruptedException, IOException {
        //Select Account as AMR
        //driver.findElement(By.xpath("//tr/*[contains(text(), 'American Medical Response')]")).click();
        expectedTitle = "ManageRight";
        actualTitle = driver.getTitle();
        String searchText = driver.findElement(By.id("dfCompanyName")).getText();
        //Wait <WebDriver> wait = new FluentWait<WebDriver>(driver);
        searchText = "American";
        //if (searchText.equals(null))
        //{
        driver.findElement(By.id("dfCompanyName")).sendKeys(searchText);
        driver.findElement(By.xpath("//button[contains(text(), 'search')]")).click();
        //}
        //else
        // {
        // driver.findElement(By.xpath("//button[contains(text(), 'clear')]")).click();
        //}


        driver.findElement(By.xpath("//*[@id=\"myTable01\"]/tbody/tr/td[contains(text(), 'American')]")).click();
        ;
		
		
	 /* WebElement InvoiceLNLink = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("menu-invoices")));
	  	  
	  WebElement OrganizationsLNLink= driver.findElement(By.id("menu-organization"));
	  WebElement ContractsLNLink= driver.findElement(By.id("menu-contracts"));
	  WebElement EmployeesLNLink= driver.findElement(By.id("menu-employees"));
	  WebElement VendorsLNLink= driver.findElement(By.id("menu-setup-vendors"));
	  WebElement AssetsLNLink= driver.findElement(By.id("menu-assets"));
	  WebElement OrdersLNLink= driver.findElement(By.id("menu-orders"));
	   WebElement ReportsLNLink= driver.findElement(By.id("menu-reports"));
	  WebElement SetupLNLink= driver.findElement(By.id("menu-setup"));
	  
	  
	  	  	  
	  	  
	  if (InvoiceLNLink.isDisplayed()) {			
		  invoiceModuleMethod(InvoiceLNLink);		//Invoices Module select
		 }
	  
	  WebElement LocationsLNLink = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("menu-location")));
	  
	  if (LocationsLNLink.isDisplayed()) {
		  locationModuleMethod(LocationsLNLink);
	  }
	  
	  WebElement OrdersLNLink = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("menu-orders")));
	  
	  if (OrdersLNLink.isDisplayed()) {
		  ordersModuleMethod(OrdersLNLink);
	  }
	  
	 
	  organizationModuleMethod(OrganizationsLNLink);
	  
	  
	  contractsModuleMethod(ContractsLNLink);
	  
	  
	  emplyoeesModuleMethod(EmployeesLNLink);
	  
	  
	  vendorsModuleMethod(VendorsLNLink);
	  
	  
	  assetsModuleMethod(AssetsLNLink);
	  
	  
	  
	  setupModuleMethod(SetupLNLink);
	  
	  	 
	  assert title of page*/

        ts = (TakesScreenshot) driver;
        File srcFile = ts.getScreenshotAs(OutputType.FILE);
        //FileUtils.copyFile(srcFile, new File ("c:\\tmp\\main_page_success.png"));
        Files.copy(srcFile, new File("c:\\Personal\\main_page_success.png"));


    }

    //@Test
    private void MRlogout() {
        //Logout from MR

        String parentWindowHandle = driver.getWindowHandle();
        //open top right menu window
        driver.findElement(By.xpath("//li/*[@class='dropdown-toggle']")).click();

        String signoutWindowHandle = driver.getWindowHandle();

        driver.switchTo().window(signoutWindowHandle);
        //select signout button

        driver.findElement(By.xpath("//a[contains(@href, 'LogOff')]")).click();
        /*
         * try { driver.findElement(By.xpath("//li/*[@class='dropdown-menu']")).click();
         * driver.findElement(By.xpath("//a[@href='AdminServlet?pageName=LogOff']")).
         * click(); }catch(NullPointerException e) {
         * System.out.println("It is a Null Pointer Exception");
         *
         * }
         */
    }

    private void setupModuleMethod(WebElement setupLNLink) {
        // TODO Auto-generated method stub
        setupLNLink.click();
        //Invoice map sub link
        //Import map sub link
    }

    private void ordersModuleMethod(WebElement ordersLNLink) throws InterruptedException {
        // TODO Auto-generated method stub
        ordersLNLink.click();
        WebElement newOrderWait = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("menu-orders-new")));

        WebElement NewOrder = driver.findElement(By.id("menu-orders-new"));
        NewOrder.click();
        newOrderCreate(); //to create method for new order creation
    }

    private void newOrderCreate() {
        // TODO Auto-generated method stub

    }

    private void assetsModuleMethod(WebElement assetsLNLink) {
        // TODO Auto-generated method stub
        assetsLNLink.click();
    }

    private void vendorsModuleMethod(WebElement vendorsLNLink) {
        // TODO Auto-generated method stub
        vendorsLNLink.click();
    }

    private void emplyoeesModuleMethod(WebElement employeesLNLink) {
        // TODO Auto-generated method stub
        employeesLNLink.click();
    }

    private void contractsModuleMethod(WebElement contractsLNLink) {
        // TODO Auto-generated method stub
        contractsLNLink.click();
    }

    private void organizationModuleMethod(WebElement organizationsLNLink) {
        // TODO Auto-generated method stub
        organizationsLNLink.click();
    }

    private void locationModuleMethod(WebElement locationsLNLink) {
        // TODO Auto-generated method stub
        locationsLNLink.click();
        WebElement locationSearch = driver.findElement(By.id("menu-location-search"));
        locationSearch.click();
    }

    private void invoiceModuleMethod(WebElement InvoiceLNLink) throws InterruptedException {
        // TODO Auto-generated method stub
        InvoiceLNLink.click();
        WebElement NewInvoice = driver.findElement(By.id("menu-invoices-new"));
        NewInvoice.click(); //New Invoice Entry from Left navigation
        newInvoiceCreate(); //create new invoice

    }

    private void newInvoiceCreate() throws InterruptedException {
        // TODO Auto-generated method stub

        Select vendorDropdown = new Select(driver.findElement(By.name("cmbVendor")));
        Select vendorAddressDropdown = new Select(driver.findElement(By.name("cmbVendorAddress")));
        WebElement newInvoiceNumber = driver.findElement(By.name("dfInvoiceNum"));
        WebElement newTotalAmtDue = driver.findElement(By.name("dfInvoiceAmt"));
        WebElement newCurrentCharge = driver.findElement(By.name("dfProcessAmt"));
        WebElement invoiceSave = driver.findElement(By.xpath("//button[@type = \"submit\"]"));
        WebElement vendorAccount = driver.findElement(By.name("dfAccountCode"));
        WebElement vendorAccountSearchIcon = driver.findElement(By.xpath("//table/tbody/tr/td/button/i[@class='fa fa-search']"));
        WebElement invoiceDate = driver.findElement(By.id("dfStartDate"));
        WebElement invoiceDueDate = driver.findElement(By.id("dfEndDate"));


        vendorDropdown.selectByVisibleText("Adams");

        if (vendorAddressDropdown.getFirstSelectedOption().equals(null)) {
            vendorAddressDropdown.selectByIndex(0);
        }

        newInvoiceNumber.sendKeys("TestInvoice");
        newTotalAmtDue.sendKeys("123");
        newCurrentCharge.sendKeys("120");

        Thread.sleep(3000); //due to dynamic population of service dropdown, explicit wait is not working

        //WebElement invoiceServiceWait = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("cmbCustInvoiceSetup10")));

        Select invoiceService = new Select(driver.findElement(By.name("cmbCustInvoiceSetup10")));

        invoiceService.selectByIndex(1);

        invoiceDate.click();
        //calendar handling?
        selectDateFromCalendar();
        invoiceDate.sendKeys("09/01/2020");

        invoiceDueDate.click();
        invoiceDueDate.sendKeys("09/30/2020");
        //need to write function to pick date from calendar
        //selectDateFromCalendar();

        vendorAccountSearchIcon.click();
        //switch to account search window handling?
        try {
            driver.switchTo().window("Select Account(s)");
            Thread.sleep(2000);
            WebElement accountSelectButton = driver.findElement(By.xpath("//button[contains(text(), 'select')]"));
            WebElement accountCloseWindowButton = driver.findElement(By.xpath("//button[contains(text(), 'close')]"));

            Thread.sleep(2000);
            Select accountNumberList = new Select(driver.findElement(By.xpath("//select[@id='cmbAccountNum']")));
            accountNumberList.selectByIndex(1);
            Thread.sleep(2000);
            accountSelectButton.click();
            //accountCloseWindowButton.click();
        } catch (NoSuchWindowException e) {
        }

        //Thread.sleep(2000);
        //invoiceSave.click();

    }

    private void selectDateFromCalendar() {
        // TODO Auto-generated method stub


    }
//for data driven testing and providing data to test ng like different combinations of login id and pwd


    //@AfterMethod
    public void afterMethod() {

        //driver.quit();
    }


}
