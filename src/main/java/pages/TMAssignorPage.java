package pages;

import base.BasePage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import utils.SafeActionUtils;
import utils.WaitUtils;

public class TMAssignorPage extends BasePage {

    private final WaitUtils wait;
    SafeActionUtils safe;

    public TMAssignorPage(WebDriver driver) {
        super(driver);
        this.wait = new WaitUtils(driver, 15);
        this.safe = new SafeActionUtils(driver);
    }

    @FindBy(xpath = "//input[@placeholder='Enter Aadhar Number']")
    private WebElement adharNumber;

    @FindBy(xpath = "//input[@placeholder='Enter Trademark Application number']")
    private WebElement trademarkApplicationNumber;

    @FindBy(xpath = "(//p[text()='Next'])[2]")
    private WebElement nextButton;

    @FindBy(xpath = "//p[text()='Add Director(s)']")
    private WebElement addDirectorsButton;

    @FindBy(xpath = "(//p[text()='Fill details'])[1]")
    private WebElement fillDetailsButton;

    @FindBy(xpath = "//input[@placeholder='Enter Applicant Name']")
    private WebElement applicantName;

    @FindBy(xpath = "//input[@placeholder='Enter Address']")
    private WebElement address;

    @FindBy(xpath = "//input[@placeholder='Enter Email ID']")
    private WebElement email;

    @FindBy(xpath = "//input[@placeholder='Enter Mobile Number']")
    private WebElement mobileNumber;

    @FindBy(xpath = "//input[contains(@id,'react-select') and @role='combobox']")
    public WebElement enterRole;

    @FindBy(xpath = "//p[text()='Partnership deed']/parent::div/parent::div/parent::div/div[2]/input")
    public WebElement uploadPartnershipDeed;

    @FindBy(xpath = "//p[text()='Tm - 48 document']/parent::div/parent::div/parent::div/div[2]/input")
    public WebElement uploadTmDocument;

    @FindBy(xpath = "(//img[@alt='delete'])[1]")
    private WebElement deleteButton;

    @FindBy(xpath = "//button/p[text()='Yes']")
    private WebElement yesButton;

    @FindBy(xpath = "//p[text()='3']")
    private WebElement DocumentsTab;

    @FindBy(xpath = "(//img[@alt='down-arrow'])[1]")
    private WebElement downArrow;

    @FindBy(xpath = "(//img[@alt='delete'])[1]")
    private WebElement deleteIcon;

    public void deleteDocument() {
        safe.safeClick(downArrow, "Down Arrow Icon");
        safe.safeClick(deleteIcon, "Delete Icon");
        safe.safeClick(yesButton, "Yes Button");
    }

    public void typeAadharNumber(String aadhar) {
        safe.safeType(adharNumber, aadhar, "Aadhar Number");
    }

    public void typeTrademarkApplicationNumber(String appNumber) {
        safe.safeType(trademarkApplicationNumber, appNumber, "Trademark Application Number");
    }

    public void clickDocumentsTab() {
        safe.safeClick(DocumentsTab, "Documents Tab");
    }

    public void clickNextButton() {
        safe.safeClick(nextButton, "Next Button");
    }

    public void clickDeleteButton() {
        if(deleteButton.isDisplayed()) {
            safe.safeClick(deleteButton, "Delete Button");
            safe.safeClick(yesButton, "Yes Button");
        }
    }

    public void clickAddDirectorsButton() {
        safe.safeClick(addDirectorsButton, "Add Directors Button");
    }

    public void clickFillDetailsButton() {
        safe.safeClick(fillDetailsButton, "Fill Details Button");
    }

    public void typeApplicantName(String name) {
        safe.safeType(applicantName, name, "Applicant Name");
    }

    public void typeAddress(String addr) {
        safe.safeType(address, addr, "Address");
    }

    public void typeEmail(String emailId) {
        safe.safeType(email, emailId, "Email ID");
    }

    public void typeMobileNumber(String mobile) {
        safe.safeType(mobileNumber, mobile, "Mobile Number");
    }

    @FindBy(xpath = "//a[@id='crossSaleNew']")
    public WebElement crossSaleNew;

    @FindBy(xpath = "//li[contains(@class, 'select2-results__option') and contains(text(), 'Assign Your Trademark')]")
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
