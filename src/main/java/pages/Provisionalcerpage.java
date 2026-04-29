package pages;

import base.BasePage;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import utils.SafeActionUtils;
import utils.ScreenshotUtils;
import utils.WaitUtils;

import java.awt.*;


public class Provisionalcerpage extends BasePage {

    private final WaitUtils wait;
    SafeActionUtils safe;

    public Provisionalcerpage(WebDriver driver) throws AWTException {
        super(driver);
        this.driver = driver;
        this.wait = new WaitUtils(driver, 15);
        this.safe = new SafeActionUtils(driver);
    }

    @FindBy(xpath = "//input[@placeholder='Enter Foundation Name ']")
    public WebElement FoundationName;

    @FindBy(xpath = "//input[@placeholder='Enter Mobile Number ']")
    public WebElement mobileNumber;

    @FindBy(xpath = "//input[@placeholder='Enter Email ID ']")
    public WebElement emailID;

    @FindBy(xpath = "//input[@placeholder='Enter Address']")
    public WebElement address;

    @FindBy(xpath = "//input[@placeholder='Enter Income Tax Login ID ']")
    public WebElement incomeTaxLoginID;

    @FindBy(xpath = "//input[@placeholder='Enter Income Tax Login Password']")
    public WebElement incomeTaxLoginPassword;

    @FindBy(xpath = "(//input[@role='combobox'])[1]")
    public WebElement natureofactivity;

    @FindBy(xpath = "(//input[@role='combobox'])[2]")
    public WebElement typeofconstution;

    @FindBy(xpath = "(//input[@role='combobox'])[3]")
    public WebElement trustdeed;

    @FindBy(xpath = "//input[@placeholder='Enter Name']")
    public WebElement name;

    @FindBy(xpath = "//input[@placeholder='Enter Email']")
    public WebElement getEmailID;

    @FindBy(xpath = "//input[@placeholder='Enter Mobile number']")
    public WebElement getMobileNumber;

    @FindBy(xpath = "//input[@placeholder='Enter Income Tax Login']")
    public WebElement getIncomeTaxLoginID;

    @FindBy(xpath = "//input[@placeholder='Enter Income Tax Password']")
    public WebElement getIncomeTaxLoginPassword;
    


    @FindBy(xpath = "//p[text()='PAN card']/parent::div/parent::div/parent::div/div[2]/input")
    public WebElement Pancard;

    @FindBy(xpath = "//p[text()='Aadhar card front']/parent::div/parent::div/parent::div/div[2]/input")
    public WebElement Aadharcard;

    @FindBy(xpath = "//p[text()='Aadhar card back']/parent::div/parent::div/parent::div/div[2]/input")
    public WebElement aadharcardback;

    @FindBy(xpath = "//button[@class='font-medium text-[14px] rounded-[4px] flex items-center gap-[5px] p-[7px] px-[10px] relative overflow-hidden bg-[#022B50] border-[1px] border-[#022B50] text-white w-full justify-center event-track cursor-pointer hover:bg-opacity-90']")
    public WebElement DetailsofNgobutton;

    @FindBy(xpath = "//button[@class='font-medium text-[14px] rounded-[4px] flex items-center gap-[5px] p-[7px] px-[10px] relative overflow-hidden bg-[#022B50] border-[1px] border-[#022B50] text-white w-full justify-center event-track cursor-pointer hover:bg-opacity-90']")
    public WebElement detailsofauthbutton;

    @FindBy(xpath = "//button[@class='font-medium text-[14px] rounded-[4px] flex items-center gap-[5px] p-[7px] px-[10px] relative overflow-hidden bg-[#022B50] border-[1px] border-[#022B50] text-white w-full justify-center py-[1.2rem] event-track cursor-pointer hover:bg-opacity-90']")
    public WebElement uploadbbutton;

    public void selectFromReactDropdown(WebElement element, String value) throws InterruptedException {
        logger.info("Selecting [{}] from dropdown", value);

        element.click();
        Thread.sleep(1000);

        element.sendKeys(value);
        Thread.sleep(1500); // Wait for results to filter

        element.sendKeys(Keys.ENTER);
        logger.info("Successfully selected [{}]", value);
    }

    public void FoundationName(String CompanyName) {
        safe.safeType(FoundationName, CompanyName, "Company name");
    }

    public void DetailsofNgobutton() {
        safe.safeClick(DetailsofNgobutton, "Details of Ngo Button");
    }

    public void detailsofauthbutton() {
        safe.safeClick(detailsofauthbutton, "Details of Auth person entered");
    }

    public void uploadbbutton() {
        safe.safeClick(uploadbbutton, "Process completed successfully");
    }

    public void mobileNumber(String directorMobile1) {
        safe.safeType(mobileNumber, directorMobile1, "Mobile number");
    }

    public void emailID(String directorEmail2) {
        safe.safeType(emailID, directorEmail2, "Email ID");
    }

    public void address(String applicantAddress) {
        safe.safeType(address, applicantAddress, "Address");
    }

    public void incomeTaxLoginID(String gstDetails) {
        safe.safeType(incomeTaxLoginID, gstDetails, "Income Tax Login ID");
    }

    public void incomeTaxLoginPassword(String lutpassword) {
        safe.safeType(incomeTaxLoginPassword, lutpassword, "Income Tax Login Password");
    }

    public void selectReactDropdown(WebElement element, String value, String logName) throws InterruptedException {
        logger.info("Starting selection for {}: [{}]", logName, value);

        try {
            ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView({block: 'center'});", element);
            Thread.sleep(500);

            ((JavascriptExecutor) driver).executeScript("arguments[0].click();", element);
            Thread.sleep(500);

            element.sendKeys(value);
            logger.info("Typed [{}] into {}", value, logName);
            Thread.sleep(1000);

            element.sendKeys(Keys.ENTER);
            logger.info("Successfully selected [{}]", value);

            ScreenshotUtils.attachScreenshotToAllure(driver, logName + "_Selected");
        } catch (Exception e) {
            logger.error("Failed to select {} due to: {}", logName, e.getMessage());
            ScreenshotUtils.attachScreenshotToAllure(driver, logName + "_Error");
            throw e;
        }
    }


    public void name(String directorName1) {
        safe.safeType(name, directorName1, "Trust deed");
    }

    public void getEmailID(String directorEmail2) {
        safe.safeType(getEmailID, directorEmail2, "Trust deed");
    }

    public void getMobileNumber(String directorMobile1) {
        safe.safeType(getMobileNumber, directorMobile1, "Trust deed");
    }

    public void getIncomeTaxLoginID(String gstDetails) {
        safe.safeType(getIncomeTaxLoginID, gstDetails, "Trust deed");
    }

    public void getIncomeTaxLoginPassword(String lutpassword) {
        safe.safeType(getIncomeTaxLoginPassword, lutpassword, "Trust deed");
    }
    @FindBy(xpath = "//a[@id='crossSaleNew']")
    public WebElement crossSaleNew;

    @FindBy(xpath = "//li[contains(@class, 'select2-results__option') and contains(text(), 'Provisional certificate of 12A and 80G')]")
    public WebElement selectingtheservice;

    @FindBy(xpath = "//button[@class='btn btn-default btn-u cross_sale_ticket_create']")
    public WebElement createbutton;

    @FindBy(xpath = "//button[@id='copy_upload_doc_link']")
    public WebElement copyurl;

    @FindBy(xpath = "//a[@class='btn recent_tickets']")
    public WebElement recenttickets;


    public void crossSaleNew() {

        safe.safeClick(crossSaleNew, "Cross Sale click action");
    }

    public void selectingtheservice() {
        safe.safeClick(selectingtheservice, "Selecting these services");

    }


    public void recenttickets() {
        safe.safeClick(recenttickets, "Recent tickets");
    }

    @FindBy(xpath = "//button[@id='paymentNoBtn']")
    public WebElement crossNobutton;

    @FindBy(xpath = "//span[@id='select2-cross_service_tag-container']")
    public WebElement createservice;

    @FindBy(xpath = "//span[contains(@class, 'select2-container--open')]//input[@class='select2-search__field']")
    public WebElement select2SearchField;


    public void createservice(String applyforustrademark) throws InterruptedException {
        safe.safeClick(createservice, "Opening Service Dropdown");

        Thread.sleep(1500);

        try {
            select2SearchField.sendKeys(applyforustrademark);
            logger.info("Typed service name successfully.");
        } catch (Exception e) {
            safe.safeType(select2SearchField, applyforustrademark, "Creating a service");
        }
    }



    public void createbutton() throws InterruptedException {
        safe.safeClick(createbutton, "Service create button");

        Thread.sleep(2000);
        safe.safeClick(crossNobutton, "Cross Sale No payment action");
        Thread.sleep(2000);
        driver.switchTo().alert().accept();
        logger.info("Browser alert accepted.");
    }
}