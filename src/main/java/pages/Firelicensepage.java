package pages;

import base.BasePage;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import utils.SafeActionUtils;
import utils.ScreenshotUtils;
import utils.WaitUtils;

import java.awt.*;

import static utils.AllureLoggerUtils.logToAllure;


public class Firelicensepage extends BasePage {

    private final WaitUtils wait;
    SafeActionUtils safe;

    public Firelicensepage(WebDriver driver) throws AWTException {
        super(driver);
        this.driver = driver;
        this.wait = new WaitUtils(driver, 15);
        this.safe = new SafeActionUtils(driver);
    }

    @FindBy(xpath = "//input[@placeholder='Enter company Name']")
    public WebElement companyName;

    @FindBy(xpath = "//input[@placeholder='Enter Email ID ']")
    public WebElement emailID1;

    @FindBy(xpath = "//input[@placeholder='Enter Mobile Number']")
    public WebElement mobileNumber;

    @FindBy(xpath = "//input[@placeholder='Enter Address']")
    public WebElement address1;

    @FindBy(xpath = "//input[@placeholder='Enter CIN Number']")
    public WebElement cinNumber;

    @FindBy(xpath = "//input[@placeholder='Enter Name']")
    public WebElement authorizedname;

    @FindBy(xpath = "//input[@placeholder='Enter Email ID']")
    public WebElement emailID2;

    @FindBy(xpath = "//input[@placeholder='Enter Address']")
    public WebElement address2;

    @FindBy(css = "input[id^='react-select-'][id$='-input']")
    public WebElement companytype;

    @FindBy(xpath = "//button[@class='font-medium text-[14px] rounded-[4px] flex items-center gap-[5px] p-[7px] px-[10px] relative overflow-hidden bg-[#022B50] border-[1px] border-[#022B50] text-white w-full justify-center event-track cursor-pointer hover:bg-opacity-90']")
    public WebElement companybutton;

    @FindBy(xpath = "//button[@class='font-medium text-[14px] rounded-[4px] flex items-center gap-[5px] p-[7px] px-[10px] relative overflow-hidden bg-[#022B50] border-[1px] border-[#022B50] text-white w-full justify-center event-track cursor-pointer hover:bg-opacity-90']")
    public WebElement authButton;

    @FindBy(xpath = "//button[@class='font-medium text-[14px] rounded-[4px] flex items-center gap-[5px] p-[7px] px-[10px] relative overflow-hidden bg-[#022B50] border-[1px] border-[#022B50] text-white w-full justify-center py-[1.2rem] event-track cursor-pointer hover:bg-opacity-90']")
    public WebElement closeButton;

    @FindBy(xpath = "//p[text()='Aadhar card front']/parent::div/parent::div/parent::div/div[2]/input")
    public WebElement selectAadharFront;

    @FindBy(xpath = "//p[text()='Aadhar card back']/parent::div/parent::div/parent::div/div[2]/input")
    public WebElement selectAadharBack;

    @FindBy(xpath = "//p[text()='PAN card']/parent::div/parent::div/parent::div/div[2]/input")
    public WebElement uploadPAN;


    public void authButton() {

        safe.safeClick(authButton, "Authorised person next Button");
    }

    public void closeButton() {

        safe.safeClick(closeButton, "Process completed successfully");
    }

    public void companytype(String EntityType) throws InterruptedException {
        try {
            wait.waitForVisibility(companytype);
            companytype.click();

            companytype.sendKeys(EntityType);
            Thread.sleep(500);

            companytype.sendKeys(Keys.ENTER);

            logToAllure("Selection", "Selected Company type: " + EntityType);
        } catch (Exception e) {

        }
    }


    public void companyName(String CompanyName) {
        safe.safeType(companyName, CompanyName, "Company name");
    }

    public void emailID1(String DirectorEmail1) {

        safe.safeType(emailID1, DirectorEmail1, "Company email ID");
    }

    public void emailID2(String DirectorEmail2) {

        safe.safeType(emailID2, DirectorEmail2, "Authorised email ID");
    }

    public void mobileNumber(String DirectorMobile1) {

        safe.safeType(mobileNumber, DirectorMobile1, "Mobile number");
    }

    public void address1(String Address1) {
        safe.safeType(address1, Address1, "Company address");
    }

    public void address2(String Address1) {
        safe.safeType(address2, Address1, "Authorised address");
    }

    public void cinNumber(String CompanyCIN) {
        safe.safeType(cinNumber, CompanyCIN, "CIN number");
    }

    public void companybutton() {

        safe.safeClick(companybutton, "Next Button");
    }

    public void authorizedname(String directorName1) {

        safe.safeType(authorizedname, directorName1, "Authorized Name");
    }
    @FindBy(xpath = "//a[@id='crossSaleNew']")
    public WebElement crossSaleNew;

    @FindBy(xpath = "//li[contains(@class, 'select2-results__option') and contains(text(), 'Fire and Factory License')]")
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

