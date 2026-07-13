package manageRight;

import static org.testng.Assert.assertEquals;

import java.io.File;
import java.io.IOException;
import java.util.List;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchWindowException;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import jxl.Cell;
import jxl.Sheet;
import jxl.Workbook;

public class EditOrderWireless {
    WebDriver driver;
    WebDriverWait wait;
    private String expectedTitle;
    private String actualTitle;
    TakesScreenshot ts;
    CommonMethods commonMethodClass = new CommonMethods();

    @DataProvider(name = "SelectOrderToEdit")
    public Object[][] invoiceDataProviderMethod() {
        Object[][] retObjArr = getDataFromXLSUsingJXL("test\\resources\\data\\SelectOrderToEdit.xls",
                "OrderNum", "StartAndEnd");
        return (retObjArr);
    }


    @Test
    public void editOrderSubmitForApprovalNoEmail(String orderNumber) {


        selectOrderOpenStatus(orderNumber);

        String parentWindowHandle = driver.getWindowHandle();

        Select vendor = new Select(wait.until(ExpectedConditions.visibilityOfElementLocated(By.name("cmbVendor"))));

        //vendor.selectByVisibleText("Adams");

        Select vendorAssessment = new Select(wait.until(ExpectedConditions.visibilityOfElementLocated(By.name("cmbVAImportance"))));

        vendorAssessment.selectByVisibleText("1");

        WebElement vendorOrderNumber = wait.until(ExpectedConditions.visibilityOfElementLocated(By.name("dfVendorOrderNumber")));
        vendorOrderNumber.sendKeys("testOrder");

        WebElement submitForApproval = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//button[contains(text(), 'submit for approval')]")));

        submitForApproval.click();
        String changeStatusWindowHandle = driver.getWindowHandle();
        driver.switchTo().window(changeStatusWindowHandle);

        WebElement approvalFromStatusWindow = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("btn1")));

        approvalFromStatusWindow.click();

        driver.switchTo().window(parentWindowHandle);

    }

    private void selectFilter() {
        WebElement editFilter = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//button[contains(text(), 'edit filters')]")));
        editFilter.click();

        String filterWindowHandle = driver.getWindowHandle();
        driver.switchTo().window(filterWindowHandle);

        Select orderStatus = new Select(driver.findElement(By.xpath("//select[@name='cmbOrderStatus']")));
        orderStatus.selectByVisibleText("Open");

        driver.findElement(By.xpath("//button[contains(text(),'ok')]")).click();

    }
    @Test
    public void editOrderSubmitForApprovalWithEmail() {

        selectOrderOpenStatus("181072");

        String parentWindowHandle = driver.getWindowHandle();
        String emailID = "ruchiagarwalnov@gmail.com";

        Select vendor = new Select(wait.until(ExpectedConditions.visibilityOfElementLocated(By.name("cmbVendor"))));

//vendor.selectByVisibleText("Adams");

        Select vendorAssessment = new Select(wait.until(ExpectedConditions.visibilityOfElementLocated(By.name("cmbVAImportance"))));

        vendorAssessment.selectByVisibleText("1");

        WebElement vendorOrderNumber = wait.until(ExpectedConditions.visibilityOfElementLocated(By.name("dfVendorOrderNumber")));
        vendorOrderNumber.sendKeys("testOrder");

        WebElement submitForApproval = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//button[contains(text(),'submit for approval')]")));

        submitForApproval.click();
        String changeStatusWindowHandle = driver.getWindowHandle();
        driver.switchTo().window(changeStatusWindowHandle);

        WebElement approvalFromStatusWindow = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("btn1")));

        WebElement emailRequiredChkBox = driver.findElement(By.name("cbEmailReqd"));
        emailRequiredChkBox.click(); //unchecking email required button

//enter email ID in to and cc fields
        WebElement emailTo = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"formApprovalPopup\"]/table/tbody/tr/td[3]/table[2]/tbody/tr[2]/td[1]/input"))); //need to add ID

        emailTo.sendKeys(emailID);


        approvalFromStatusWindow.click();

        driver.switchTo().window(parentWindowHandle);
//go to search order page

    }

    private void selectOrder(String orderNumber) {
        WebElement searchOrderLink = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//a[contains(text(),'Search Orders')]")));
        String targetOrderNum = orderNumber;
        searchOrderLink.click();
        List<WebElement> orderNumbers = driver.findElements(By.xpath("//tr[contains(@onclick, 'gotoOrder')]"));

        int n = orderNumbers.size();
        if (n != 0) {
            for (int i = 0; i < orderNumbers.size(); i++) {
                if (orderNumbers.get(i).getText().contains(targetOrderNum)) {
                    orderNumbers.get(i).click();
                    return;
                }
            }
        } else
            Assert.fail("No record found");

    }

    private void selectOrderOpenStatus(String orderNumber) {
        WebElement searchOrderLink = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//a[contains(text(),'Search Orders')]")));
        String targetOrderNum = orderNumber;
        searchOrderLink.click();
        //driver.findElement(By.xpath("//td[contains(text(),'181072')]")).click();
        selectFilter();
        List<WebElement> orderNumberStatus = driver.findElements(By.xpath("//tr[contains(@onclick, 'gotoOrder')]/td[contains(text(), 'Open')]"));

        int n = orderNumberStatus.size();
        if (n != 0) {
            orderNumberStatus.get(0).click();
        } else
            Assert.fail("No record found in Open State");

    }

    //@Test (dataProvider = "SelectOrderToEdit")
    public void editOrderWireless(String orderNumber) throws InterruptedException {
        // TODO Auto-generated method stub

        selectOrder(orderNumber);
        editOrder(); //create new Order method

    }

    private void editOrder() throws InterruptedException {

        // TODO Auto-generated method stub
        String parentWindowHandle = driver.getWindowHandle();

        Select vendor = new Select(wait.until(ExpectedConditions.visibilityOfElementLocated(By.name("cmbVendor"))));

        vendor.selectByValue("1");  //AT&T vendor

        Select vendorAssessment = new Select(wait.until(ExpectedConditions.visibilityOfElementLocated(By.name("cmbVAImportance"))));

        vendorAssessment.selectByVisibleText("1");

        WebElement vendorOrderNumber = wait.until(ExpectedConditions.visibilityOfElementLocated(By.name("dfVendorOrderNumber")));
        vendorOrderNumber.sendKeys("testOrder");

        WebElement save = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//button[contains(text(), 'save')]")));
        save.click();
    }

    public String[][] getDataFromXLSUsingJXL(String xlFilePath, String sheetName, String tableName) {
        String[][] tabArray = null;
        try {
            Workbook workbook = Workbook.getWorkbook(new File(xlFilePath));
            //Workbook class is provied by jxl.jar
            //WebDriver provided by Selenium
            //File is class provided by Java to read a physical file
            Sheet sheet = workbook.getSheet(sheetName);
            Cell tableStart = sheet.findCell(tableName);

            int startRow, startCol, endRow, endCol, ci, cj;

            startRow = tableStart.getRow();//2
            startCol = tableStart.getColumn();//1

            Cell tableEnd = sheet.findCell(tableName, startCol + 1, startRow + 1, 100, 64000, false);

            endRow = tableEnd.getRow();//6
            endCol = tableEnd.getColumn();//4
            System.out.println("startRow=" + startRow + ", endRow=" + endRow + ", " +
                    "startCol=" + startCol + ", endCol=" + endCol);
            tabArray = new String[endRow - startRow - 1][endCol - startCol - 1];//5,4
            ci = 0; //array row
            //ci=0,i=3, j=3,cj=1
            for (int i = startRow + 1; i < endRow; i++, ci++) {//i represents xls row
                cj = 0;//array column
                for (int j = startCol + 1; j < endCol; j++, cj++) {//j represents xls column
                    tabArray[ci][cj] = sheet.getCell(j, i).getContents();
                }
            }
        } catch (Exception e) {
            System.out.println("Please check if file path, sheet name and tag name are correct");

        }

        return (tabArray);
    }

    @BeforeClass
    public void beforeClass() throws InterruptedException, IOException {

        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().window().maximize();

        ManageRightLogin mrLogin = new ManageRightLogin(driver);
        wait = new WebDriverWait(driver, 30);

        expectedTitle = "ManageRight";
        actualTitle = driver.getTitle();
        assertEquals(actualTitle, expectedTitle, "Incorrect Title.");

        commonMethodClass.selectCustomerMethod(driver);

        WebElement output = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[contains(text(),'Orders')]")));

        WebElement ordersLNLink = driver.findElement(By.xpath("//span[contains(text(),'Orders')]"));
        if (ordersLNLink.isDisplayed()) {
            ordersLNLink.click();
        }
    }

}
