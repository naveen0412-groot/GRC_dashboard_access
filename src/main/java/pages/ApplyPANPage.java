package pages;

import base.BasePage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import utils.SafeActionUtils;
import utils.WaitUtils;

public class ApplyPANPage extends BasePage {

    private final WaitUtils wait;
    SafeActionUtils safe;

    public ApplyPANPage(WebDriver driver) {
        super(driver);
        this.wait = new WaitUtils(driver, 15);
        this.safe = new SafeActionUtils(driver);
    }

    @FindBy(xpath = "//input[@placeholder='Enter Partnership Firm Name']")
    private WebElement enterPartnershipFirmName;

    @FindBy(xpath = "//input[@placeholder='Enter Full address with pincode of Partnership Firm']")
    private WebElement enterFullAddress;

    @FindBy(xpath = "//input[@placeholder='Enter Name of the Partner (Any one name)']")
    private WebElement enterPartnerName;

    @FindBy(xpath = "//input[@placeholder='Enter Phone Number']")
    private WebElement enterPhoneNumber;

    @FindBy(xpath = "//input[@placeholder='Enter Email ID']")
    private WebElement enterEmailID;

    @FindBy(xpath = "//input[@name='reason_for_name2']")
    private WebElement enterPANNumber;

    @FindBy(xpath = "//input[@id='date_of_inc']")
    private WebElement clickIncDate;

    @FindBy(xpath = "(//div[@class='react-datepicker__week']/div[text()='1'])[1]")
    private WebElement selectDate;

    @FindBy(xpath = "(//p[text()='Next'])[2]")
    private WebElement nextButton;

    public void selectIncDate() {
        logger.info("📅 Selecting Incorporation date");
        safe.safeClick(clickIncDate, "DOB calendar field");
        safe.safeClick(selectDate, "DOB value");
    }

    public void typePartnershipFirmName(String firmName) {
        safe.safeType(enterPartnershipFirmName, firmName, "Partnership Firm Name");
    }

    public void typeFullAddress(String address) {
        safe.safeType(enterFullAddress, address, "Full Address with Pincode of Partnership Firm");
    }

    public void typePartnerName(String partnerName) {
        safe.safeType(enterPartnerName, partnerName, "Name of the Partner");
    }

    public void typePhoneNumber(String phoneNumber) {
        safe.safeType(enterPhoneNumber, phoneNumber, "Phone Number");
    }

    public void typeEmailID(String email) {
        safe.safeType(enterEmailID, email, "Email ID");
    }

    public void typePANNumber(String panNumber) {
        safe.safeType(enterPANNumber, panNumber, "PAN Number");
    }

    public void clickNextButton() {
        safe.safeClick(nextButton,  "Next Button");
    }
    @FindBy(xpath = "//a[@id='crossSaleNew']")
    public WebElement crossSaleNew;

    @FindBy(xpath = "//li[contains(@class, 'select2-results__option') and contains(text(), 'Apply for a PAN Number')]")
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
