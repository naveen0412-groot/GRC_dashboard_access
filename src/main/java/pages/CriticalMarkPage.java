package pages;

import base.BasePage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import utils.SafeActionUtils;
import utils.WaitUtils;

public class CriticalMarkPage extends BasePage {

    private final WaitUtils wait;
    SafeActionUtils safe;

    public CriticalMarkPage(WebDriver driver) {
        super(driver);
        this.wait = new WaitUtils(driver, 30);
        this.safe = new SafeActionUtils(driver);
    }
    @FindBy(xpath = "//input[@placeholder='Enter Name']")
    private WebElement name;

    @FindBy(xpath = "//input[@placeholder='Enter Mobile Number']")
    private WebElement mobileNumber;

    @FindBy(xpath = "//input[@placeholder='Enter Email Id']")
    private WebElement email;

    @FindBy(xpath = "//input[@placeholder='Enter Aadhaar Number']")
    private WebElement adhaarNumber;

    @FindBy(xpath = "//input[@placeholder='Enter Building no / Flat number']")
    private WebElement buildingNo;

    @FindBy(xpath = "//input[@placeholder='Enter Road / Street']")
    private WebElement road;

    @FindBy(xpath = "//input[@placeholder='Enter Pincode']")
    private WebElement pincode;

    @FindBy(xpath = "(//p[text()='Next'])[2]")
    private WebElement nextButton;

    public void typeName(String fullName) {
        safe.safeType(name, fullName, "Name");
    }

    public void typeMobileNumber(String mobile) {
        safe.safeType(mobileNumber, mobile, "Mobile Number");
    }

    public void typeEmailId(String emailId) {
        safe.safeType(email, emailId, "Email ID");
    }

    public void typeAadhaarNumber(String aadhaar) {
        safe.safeType(adhaarNumber, aadhaar, "Aadhaar Number");
    }

    public void typeBuildingNo(String building) {
        safe.safeType(buildingNo, building, "Building/Flat Number");
    }

    public void typeRoad(String roadName) {
        safe.safeType(road, roadName, "Road/Street");
    }

    public void typePincode(String pin) {
        safe.safeType(pincode, pin, "Pincode");
    }

    public void clickNextButton() {
        safe.safeClick(nextButton, "Next Button");
    }

    @FindBy(xpath = "//a[@id='crossSaleNew']")
    public WebElement crossSaleNew;

    @FindBy(xpath = "//li[contains(@class, 'select2-results__option') and contains(text(), 'Apply for Critical Mark')]")
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
