package pages;

import base.BasePage;
import org.openqa.selenium.*;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import utils.SafeActionUtils;
import utils.WaitUtils;

import java.awt.*;
import java.time.Duration;


public class IncreaseAuthCap extends BasePage {

    private final WaitUtils wait;
    SafeActionUtils safe;

    public IncreaseAuthCap(WebDriver driver) throws AWTException {
        super(driver);
        this.driver = driver;
        this.wait = new WaitUtils(driver, 15);
        this.safe = new SafeActionUtils(driver);
    }

    @FindBy(xpath = "//input[@placeholder='Enter Company Name']")
    public WebElement companyName;

    @FindBy(xpath = "//input[@placeholder='Enter Company CIN']")
    public WebElement companyCIN;

    @FindBy(xpath = "//input[@placeholder='Enter Existing Authorised Share Capital']")
    public WebElement existingAuthorisedCapital;

    @FindBy(xpath = "//input[@placeholder='Enter Revised Authorised Share Capital']")
    public WebElement revisedAuthorisedCapital;

    @FindBy(xpath = "//input[@placeholder='Enter Reason for Increasing the Authorised capital of the company']")
    public WebElement increasedAuthorisedCapital;

    @FindBy(xpath = "//button[@class='font-medium text-[14px] rounded-[4px] flex items-center gap-[5px] p-[7px] px-[10px] relative overflow-hidden bg-[#022B50] border-[1px] border-[#022B50] text-white w-full justify-center event-track cursor-pointer hover:bg-opacity-90']")
    public WebElement companyinfobutton;

    @FindBy(xpath = "//input[@placeholder='Select Option']")
    public WebElement SelectOption;

    @FindBy(xpath = "//button[@class='font-medium text-[14px] rounded-[4px] flex items-center gap-[5px] p-[7px] px-[10px] relative overflow-hidden bg-[#022B50] border-[1px] border-[#022B50] text-white w-full justify-center py-[1.2rem] event-track cursor-pointer hover:bg-opacity-90']")
    public WebElement closebutton;




    public void companyName(String CompanyName) {
        safe.safeType(companyName, CompanyName, "Company Name");
    }

    public void companyCIN(String CompanyCIN) {
        safe.safeType(companyCIN, CompanyCIN, "Company CIN");
    }


    public void companyinfobutton() {
        safe.safeClick(companyinfobutton, "Company info button");
    }

    public void existingAuthorisedCapital(String CompanyCIN) {
        safe.safeType(existingAuthorisedCapital, CompanyCIN, "Existing auth capital");
    }

    public void revisedAuthorisedCapital(String CompanyCIN) {
        safe.safeType(revisedAuthorisedCapital, CompanyCIN, "Revised auth capital");
    }

    public void increasedAuthorisedCapital(String CompanyCIN) {
        safe.safeType(increasedAuthorisedCapital, CompanyCIN, "Increased auth capital");
    }

    public void SelectOption(String CompanyCIN) {
        safe.safeType(SelectOption, CompanyCIN, "Select Option");

    }

    public void closebutton() {
        safe.safeClick(closebutton, "Process completed");
    }
    @FindBy(xpath = "//a[@id='crossSaleNew']")
    public WebElement crossSaleNew;

    @FindBy(xpath = "//li[contains(@class, 'select2-results__option') and contains(text(), 'Increase in Authorized Capital of your Company')]")
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
