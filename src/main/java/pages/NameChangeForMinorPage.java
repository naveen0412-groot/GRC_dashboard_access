package pages;

import base.BasePage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import utils.SafeActionUtils;
import utils.WaitUtils;

public class NameChangeForMinorPage extends BasePage {

    private final WaitUtils wait;
    SafeActionUtils safe;

    public NameChangeForMinorPage(WebDriver driver) {
        super(driver);
        this.wait = new WaitUtils(driver, 15);
        this.safe = new SafeActionUtils(driver);
    }
    //Tab 1
    @FindBy(xpath = "//input[@placeholder='Enter Current Name (With surname)']")
    private WebElement enterCurrentName;

    @FindBy(xpath = "//input[@placeholder='Enter New Name (With surname)']")
    private WebElement enterNewName;

    @FindBy(xpath = "//input[@placeholder='Enter Gender']")
    private WebElement enterGender;

    @FindBy(xpath = "//input[@placeholder='Enter Reason for Name Change']")
    private WebElement enterReason;

    @FindBy(xpath = "//input[@placeholder='Enter In which document do you have to change your name?']")
    private WebElement enterDocumentName;

    @FindBy(xpath = "//input[@placeholder='Enter Permanent Address']")
    private WebElement enterPermanentAddress;

    @FindBy(xpath = "//input[@name='director_pincode' and @placeholder='Enter Pincode']")
    private WebElement enterPermanentPincode;

    @FindBy(xpath = "//input[@placeholder='Enter Present Address']")
    private WebElement enterPresentAddress;

    @FindBy(xpath = "//input[@name='pan_name' and @placeholder='Enter Pincode']")
    private WebElement enterPresentPincode;

    @FindBy(xpath = "(//p[text()='Next'])[2]")
    private WebElement clickNextCTA;

    //Tab 2
    @FindBy(xpath = "//input[@placeholder='Enter Witness Name 1']")
    private WebElement enterWitnessName1;

    @FindBy(xpath = "//input[@placeholder='Enter Witness Name 2']")
    private WebElement enterWitnessName2;

    @FindBy(xpath = "//input[@placeholder='Enter Witness Father Name']")
    private WebElement enterWitnessFatherName;

    @FindBy(xpath = "//input[@placeholder='Enter Witness Contact Number']")
    private WebElement enterContactNumber;

    @FindBy(xpath = "//input[@placeholder='Enter Witness Address']")
    private WebElement enterWitnessAddress;

    //Tab 3

    @FindBy(xpath = "//p[text()='Passport size photograph']/parent::div/parent::div/parent::div/div[2]/input")
    public WebElement uploadPassportPhoto;

    @FindBy(xpath = "//p[text()='Proof of identity']/parent::div/parent::div/parent::div/div[2]/input")
    public WebElement uploadPassport;

    @FindBy(xpath = "//p[text()='Signature']/parent::div/parent::div/parent::div/div[2]/input")
    public WebElement uploadSignature;

    @FindBy(xpath = "(//img[@alt='down-arrow'])[1]")
    private WebElement downArrow;

    @FindBy(xpath = "(//img[@alt='down-arrow'])[2]")
    private WebElement downArrow2;

    @FindBy(xpath = "(//img[@alt='down-arrow'])[3]")
    private WebElement downArrow3;

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

    public void typeCurrentName(String currentName) {
        safe.safeType(enterCurrentName, currentName, "Current Name (With surname)");
    }

    public void typeNewName(String newName) {
        safe.safeType(enterNewName, newName, "New Name (With surname)");
    }

    public void typeGender(String gender) {
        safe.safeType(enterGender, gender, "Gender");
    }

    public void typeReasonForNameChange(String reason) {
        safe.safeType(enterReason, reason, "Reason for Name Change");
    }

    public void typeDocumentName(String documentName) {
        safe.safeType(enterDocumentName, documentName, "Document Name for Name Change");
    }

    public void typePermanentAddress(String address) {
        safe.safeType(enterPermanentAddress, address, "Permanent Address");
    }

    public void typePermanentPincode(String pincode) {
        safe.safeType(enterPermanentPincode, pincode, "Permanent Pincode");
    }

    public void typePresentAddress(String address) {
        safe.safeType(enterPresentAddress, address, "Present Address");
    }

    public void typePresentPincode(String pincode) {
        safe.safeType(enterPresentPincode, pincode, "Present Pincode");
    }
    //tab 2- Witness Details
    public void typeWitnessName1(String witnessName) {
        safe.safeType(enterWitnessName1, witnessName, "Witness Name");
    }

    public void typeWitnessName2(String witnessName) {
        safe.safeType(enterWitnessName2, witnessName, "Witness Name");
    }

    public void typeWitnessFatherName(String fatherName) {
        safe.safeType(enterWitnessFatherName, fatherName, "Witness Father Name");
    }

    public void typeWitnessContactNumber(String contactNumber) {
        safe.safeType(enterContactNumber, contactNumber, "Witness Contact Number");
    }

    public void typeWitnessAddress(String address) {
        safe.safeType(enterWitnessAddress, address, "Witness Address");
    }

    public void clickNextCTA(){
        safe.safeClick(clickNextCTA, "Next");
    }

    @FindBy(xpath = "//a[@id='crossSaleNew']")
    public WebElement crossSaleNew;

    @FindBy(xpath = "//li[contains(@class, 'select2-results__option') and contains(text(), 'Apply for Name Change - Minor')]")
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
