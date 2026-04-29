package pages;

import base.BasePage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import utils.SafeActionUtils;
import utils.WaitUtils;

public class ApplyTANPage extends BasePage {

    private final WaitUtils wait;
    SafeActionUtils safe;

    public ApplyTANPage(WebDriver driver) {
        super(driver);
        this.wait = new WaitUtils(driver, 15);
        this.safe = new SafeActionUtils(driver);
    }

    @FindBy(xpath = "//input[@placeholder='Enter Partnership Firm Name']")
    private WebElement partnershipFirmName;

    @FindBy(xpath = "//input[@placeholder='Enter Full address with pincode of Partnership Firm']")
    private WebElement fullAddress;

    @FindBy(xpath = "//input[@placeholder='Enter Name of the Partner (Any one name)']")
    private WebElement partnerName;

    @FindBy(xpath = "//input[@placeholder='Enter Phone Number']")
    private WebElement phoneNumber;

    @FindBy(xpath = "//input[@placeholder='Enter Email ID']")
    private WebElement emailID;

    @FindBy(xpath = "//input[@id='date_of_inc']")
    private WebElement clickIncDate;

    @FindBy(xpath = "(//div[@class='react-datepicker__week']/div[text()='1'])[1]")
    private WebElement selectDate;

    @FindBy(xpath = "//input[@placeholder='Enter TAN, if its correction']")
    private WebElement tan;

    @FindBy(xpath = "(//p[text()='Next'])[2]")
    private WebElement nextButton;

    public void selectIncDate() {
        logger.info("📅 Selecting Incorporation Date");
        safe.safeClick(clickIncDate, "Incorporation Date calendar field");
        safe.safeClick(selectDate, "Incorporation Date value");
    }

    public void typePartnershipFirmName(String value) {
        safe.safeType(partnershipFirmName, value, "Partnership Firm Name");
    }

    public void typeFullAddress(String value) {
        safe.safeType(fullAddress, value, "Full Address of Partnership Firm");
    }

    public void typePartnerName(String value) {
        safe.safeType(partnerName, value, "Partner Name");
    }

    public void typePhoneNumber(String value) {
        safe.safeType(phoneNumber, value, "Phone Number");
    }

    public void typeEmailID(String value) {
        safe.safeType(emailID, value, "Email ID");
    }

    public void typeTAN(String value) {
        safe.safeType(tan, value, "TAN");
    }

    public void clickNextButton() {
        safe.safeClick(nextButton, "Next Button");
    }
    @FindBy(xpath = "//a[@id='crossSaleNew']")
    public WebElement crossSaleNew;

    @FindBy(xpath = "//li[contains(@class, 'select2-results__option') and contains(text(), 'Apply for a TAN Number')]")
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
