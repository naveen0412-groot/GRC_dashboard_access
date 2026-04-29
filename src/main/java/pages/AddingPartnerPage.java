package pages;

import base.BasePage;
import org.openqa.selenium.*;
import org.openqa.selenium.support.FindBy;
import utils.*;

public class AddingPartnerPage extends BasePage {

    private final WaitUtils wait;
    SafeActionUtils safe;

    public AddingPartnerPage(WebDriver driver) {
        super(driver);
        this.wait = new WaitUtils(driver, 15);
        this.safe = new SafeActionUtils(driver);
    }

    @FindBy(xpath = "//input[@placeholder='Enter LLP Name']")
    private WebElement enterName;

    @FindBy(xpath = "//input[@name='email']")
    private WebElement enterEmail;

    @FindBy(xpath = "(//p[text()='Next'])[2]")
    private WebElement nextButton;

    @FindBy(xpath = "//input[@placeholder='Enter LLPIN']")
    private WebElement enterLLPIN;

    @FindBy(xpath = "(//p[text()='Next'])[2]")
    private WebElement nextButton2;

    @FindBy(xpath = "//p[text()='Add Partner(s)']")
    private WebElement addPartnerButton;

    @FindBy(xpath = "(//img[@alt='delete'])[1]")
    private WebElement deletePartnerButton;

    @FindBy(xpath = "(//p[text()='Fill details'])[1]")
    private WebElement fillPartnerButton;

    @FindBy(xpath = "//input[@id='option-DSC-No']")
    private WebElement noDscOption;

    @FindBy(xpath = "//input[@id='dob']")
    private WebElement clickDOB;

    @FindBy(xpath = "(//div[@class='react-datepicker__week']/div[text()='1'])[1]")
    private WebElement selectDate;

    @FindBy(xpath = "//input[@name='pan']")
    private WebElement enterPan;

    @FindBy(xpath = "//input[@name='aadharno']")
    private WebElement enterAadhaarNo;

    @FindBy(xpath = "//input[@name='mobile']")
    private WebElement enterApplicantContactNumber;

    @FindBy(xpath = "//input[@name='director_addressLine1']")
    private WebElement enterAddressLine1;

    @FindBy(xpath = "//input[@name='din']")
    private WebElement enterDIN;

    @FindBy(xpath = "//input[@name='father_last_name']")
    private WebElement enterFatherName;

    @FindBy(xpath = "//input[@placeholder='Enter Name of the Partner']")
    private WebElement enterPartnerName;

    @FindBy(xpath = "//p[text()='Yes']")
    private WebElement yesDeletePartnerButton;

    @FindBy(xpath = "//p[text()='PAN card']/parent::div/parent::div/parent::div/div[2]/input")
    public WebElement PANUpload;

    @FindBy(xpath = "//p[text()='Aadhaar card']/parent::div/parent::div/parent::div/div[2]/input")
    public WebElement AadhaarUpload;

    @FindBy(xpath = "//p[text()='Driving license']/preceding-sibling::img[1]")
    private WebElement selectDrivingLicenseOption;

    @FindBy(xpath = "//div[@class='flex flex-col gap-[0.8rem]']")
    private WebElement selectAddressProof;

    public void chooseAddressProof() {
        safe.safeClick(selectAddressProof, "Delete Icon");
        safe.safeClick(selectDrivingLicenseOption, "Delete Icon");
    }

    @FindBy(xpath = "//p[text()='Driving license']/parent::div/parent::div/parent::div/div[2]/input")
    public WebElement AddressProofUpload;

    @FindBy(xpath = "//p[text()='LLP Agreement']/parent::div/parent::div/parent::div/div[2]/input")
    public WebElement LLPAgreementUpload;

    @FindBy(xpath = "//p[text()='Certificate of incorporation (coi)']/parent::div/parent::div/parent::div/div[2]/input")
    public WebElement COIUpload;

    @FindBy(xpath = "//p[text()='Llp constitutional agreement']/parent::div/parent::div/parent::div/div[2]/input")
    public WebElement LlpConstitutionalAgreementUpload;

    public void selectDOB() {
        logger.info("📅 Selecting Date of Birth");
        safe.safeClick(clickDOB, "DOB calendar field");
        safe.safeClick(selectDate, "DOB value");
    }

    public void typeEmail(String email) {
        safe.safeType(enterEmail, email, "Email ID");
    }

    public void typePan(String pan) {
        safe.safeType(enterPan, pan, "PAN");
    }

    public void typeAadhaarNo(String aadhaar) {
        safe.safeType(enterAadhaarNo, aadhaar, "Aadhaar Number");
    }

    public void typeLLPName(String llpName) {
        safe.safeType(enterName, llpName, "LLP Name");
    }

    public void typeLLPIN(String llpin) {
        safe.safeType(enterLLPIN, llpin, "LLPIN Field");
    }

    public void typeApplicantContactNumber(String contactNumber) {
        safe.safeType(enterApplicantContactNumber, contactNumber, "Applicant Contact Number");
    }

    public void typeAddressLine1(String addressLine1) {
        safe.safeType(enterAddressLine1, addressLine1, "Address Line 1");
    }

    public void typeDIN(String din) {
        safe.safeType(enterDIN, din, "DIN");
    }

    public void typeFatherName(String fatherName) {
        safe.safeType(enterFatherName, fatherName, "Father Name");
    }

    public void typePartnerName(String partnerName) {
        safe.safeType(enterPartnerName, partnerName, "Partner Name");
    }

    public void clickAddPartnerButton() {
        safe.safeClick(addPartnerButton, "Add Partner Button");
    }

    public void clickDeletePartnerButton() {
        try {
            Thread.sleep(4000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        safe.safeClick(deletePartnerButton, "Delete Partner Button");
        safe.safeClick(yesDeletePartnerButton, "Yes to Delete Partner Button");
    }

    public void clickFillPartnerButton() {
        safe.safeClick(fillPartnerButton, "Fill Partner Button");
    }

    public void selectNoDscOption() {
        safe.safeClick(noDscOption, "No DSC Option");
    }

    public void clickNextButton() {
        safe.safeClick(nextButton, "Next Button");
    }

    public void clickNextButton2() {
        safe.safeClick(nextButton2, "Next Button 2");
    }
    @FindBy(xpath = "(//img[@alt='down-arrow'])[1]")
    private WebElement downArrow;

    @FindBy(xpath = "(//img[@alt='down-arrow'])[2]")
    private WebElement downArrow2;

    @FindBy(xpath = "(//img[@alt='delete'])[1]")
    private WebElement deleteIcon;

    @FindBy(xpath = "//p[text()='Yes']")
    private WebElement yesButton;
    public void deleteDocumentSet1() {
        safe.safeClick(downArrow, "Down Arrow Icon");
        safe.safeClick(deleteIcon, "Delete Icon");
        safe.safeClick(yesButton, "Yes Button");
    }

    public void deleteDocumentSet2() {
        safe.safeClick(downArrow2, "Down Arrow Icon");
        safe.safeClick(deleteIcon, "Delete Icon");
        safe.safeClick(yesButton, "Yes Button");
    }

    @FindBy(xpath = "//a[@id='crossSaleNew']")
    public WebElement crossSaleNew;

    @FindBy(xpath = "//li[contains(@class, 'select2-results__option') and contains(text(), 'Adding a Partner')]")
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
