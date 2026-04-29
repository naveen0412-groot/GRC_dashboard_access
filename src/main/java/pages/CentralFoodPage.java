package pages;

import base.BasePage;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import utils.SafeActionUtils;
import utils.SeleniumHelperMethods;
import utils.WaitUtils;

public class CentralFoodPage extends BasePage {
    private final WaitUtils wait;
    SafeActionUtils safe;
    SeleniumHelperMethods seleniumHelperMethods;
    public CentralFoodPage(WebDriver driver) {
        super(driver);
        this.wait = new WaitUtils(driver, 15);
        this.safe = new SafeActionUtils(driver);
        this.seleniumHelperMethods = new SeleniumHelperMethods();
    }

    @FindBy(xpath = "//p[text()='Private Limited Company']")
    private WebElement chooseCompanyType;

    @FindBy(xpath = "//input[@placeholder='Enter Company Name']")
    private WebElement enterCompanyName;

    @FindBy(xpath = "(//input[@placeholder='Enter address line 1'])[1]")
    private WebElement enterAddressLine1;

    @FindBy(xpath = "(//input[@placeholder='Enter address line 2'])[1]")
    private WebElement enterAddressLine2;

    @FindBy(xpath = "(//input[@placeholder='Enter Pin code'])[1]")
    private WebElement enterPinCode;

    @FindBy(xpath = "(//input[contains(@id,'react-select') and @role='combobox'])[2]")
    private WebElement selectState;

    @FindBy(xpath = "(//input[contains(@id,'react-select') and @role='combobox'])[3]")
    private WebElement selectDistrict;

    @FindBy(xpath = "(//input[contains(@id,'react-select') and @role='combobox'])[4]")
    private WebElement selectSubDivision;

    @FindBy(xpath = "(//input[contains(@id,'react-select') and @role='combobox'])[5]")
    private WebElement selectVillage;

    @FindBy(xpath = "//input[@id='Is your Correspondence Address same as Address of Premises?']")
    public WebElement clickSameAsAddress;

    @FindBy(xpath = "(//p[text()='Next'])[2]")
    private WebElement nextButton;

    @FindBy(xpath = "(//label/input[@type='checkbox'])[1]")
    public WebElement selectTypeOfFood;;

    @FindBy(xpath = "//p[text()='Submit']")
    private WebElement submitButton;

    @FindBy(xpath = "//p[text()='Select category']")
    public WebElement selectCategory;

    @FindBy(xpath = "//p[text()='Change category']")
    private WebElement changeCategory;

    @FindBy(xpath = "(//label/input[@type='checkbox'])[2]")
    public WebElement selectFoodCategory;

    @FindBy(xpath = "(//label/input[@type='checkbox'])[3]")
    public WebElement changeFoodCategory;

    @FindBy(xpath = "//p[text()='Save']")
    private WebElement saveFoodCategory;

    @FindBy(xpath = "//p[text()='Add applicant']")
    private WebElement addApplicant;

    @FindBy(xpath = "//input[@placeholder='Enter name']")
    private WebElement enterName;

    @FindBy(xpath = "//input[@placeholder='Enter mobile number']")
    private WebElement enterMobileNumber;

    @FindBy(xpath = "//input[@type='checkbox']/following-sibling::p[normalize-space()='Make this applicant as an technically qualified/ in charge of operations']")
    public WebElement enableMakeCharge;

    @FindBy(xpath = "//p[text()='Done']")
    private WebElement buttonDone;

    @FindBy(xpath = "//p[text()='Fill details by myself']")
    private WebElement clickFillDetails;

    //Director Details

    @FindBy(xpath = "//input[@placeholder='Enter email']")
    private WebElement enterEmail;

    @FindBy(xpath = "(//input[contains(@id,'react-select') and @role='combobox'])[1]")
    private WebElement selectEducationQualification;

    @FindBy(xpath = "//input[@placeholder='Enter father’s name*']")
    private WebElement enterFatherName;

    @FindBy(xpath = "//input[@placeholder='Enter building no / flat number']")
    private WebElement enterBuilding;

    @FindBy(xpath = "//input[@placeholder='Enter road / street']")
    private WebElement enterRoad;

    //Upload Flow

    @FindBy(xpath = "//p[text()='Aadhar card front']/parent::div/parent::div/parent::div/div[2]/input")
    public WebElement selectAadharFront;

    @FindBy(xpath = "//p[text()='Aadhar card back']/parent::div/parent::div/parent::div/div[2]/input")
    public WebElement selectAadharBack;

    @FindBy(xpath = "//p[text()='Passport size photo']/parent::div/parent::div/parent::div/div[2]/input")
    public WebElement uploadPassportSize;

    @FindBy(xpath = "//p[text()='PAN card']/parent::div/parent::div/parent::div/div[2]/input")
    public WebElement uploadPAN;

    //Office Documents

    @FindBy(xpath = "//p[text()='COI']/parent::div/parent::div/parent::div/div[2]/input")
    public WebElement uploadCOI;

    @FindBy(xpath = "//p[text()='MOA']/parent::div/parent::div/parent::div/div[2]/input")
    public WebElement uploadMOA;

    @FindBy(xpath = "//p[text()='Rental agreement']/parent::div/parent::div/parent::div/div[2]/input")
    public WebElement uploadRentalAgreement;

    @FindBy(xpath = "//p[text()='Electricity bill']/parent::div/parent::div/parent::div/div[2]/input")
    public WebElement uploadElectricityBill;

    @FindBy(xpath = "//p[text()='NOC from the Landlord']/parent::div/parent::div/parent::div/div[2]/input")
    public WebElement uploadNOCFromTheLandlord;

    @FindBy(xpath="//input[@id='option-Are you involved in import/export business?-true']")
    private WebElement importExport;

    @FindBy(xpath="//input[@id='option-Are you involved in E-Commerce?-no']")
    private WebElement importECommerce;

    @FindBy(xpath = "//img[@alt='delete']")
    public WebElement deleteButton;

    @FindBy(xpath = "//p[text()='Yes']")
    private WebElement yesButton;

    @FindBy(xpath = "(//img[@alt='down-arrow'])[1]")
    public WebElement downArrow;

    @FindBy(xpath = "(//img[@alt='down-arrow'])[1]")
    public WebElement propertyDocDownArrow;

    public void clickPropertyDocDownArrow() {
        safe.safeClick(propertyDocDownArrow, "Press down arrow");
    }

    public void clickDownArrow() {
        safe.safeClick(downArrow, "Press down arrow");
    }

    public void clickYesButton(){
        safe.safeClick(yesButton, "Yes to delete");
    }
    public void clickDeleteButton() {
        safe.safeClick(deleteButton, "Delete the applicant");
    }

    public void clickImportECommerce() {
        safe.safeClick(importECommerce, "Are you involved in E-Commerce");
    }

    public void clickImportExport() {
        safe.safeClick(importExport, "Are you involved in import/export business");
    }

    public void clickChooseCompanyType() {
        if (seleniumHelperMethods.isElementDisplayed(chooseCompanyType)) {
            safe.safeClick(chooseCompanyType, "Choose Company Type - Private Limited Company");
        }
    }

    public void typeCompanyName(String companyName) {
        safe.safeType(enterCompanyName, companyName, "Company Name");
    }

    public void typeAddressLine1(String addressLine1) {
        safe.safeType(enterAddressLine1, addressLine1, "Address Line 1");
    }

    public void typeAddressLine2(String addressLine2) {
        safe.safeType(enterAddressLine2, addressLine2, "Address Line 2");
    }

    public void typePinCode(String pinCode) {
        safe.safeType(enterPinCode, pinCode, "Pin Code");
    }

    public void selectState(String state) {
        safe.safeType(selectState, state, "State");
        selectState.sendKeys(Keys.ARROW_DOWN);
        selectState.sendKeys(Keys.ENTER);
    }

    public void selectDistrict(String district) {
        safe.safeType(selectDistrict, district, "District");
        selectDistrict.sendKeys(Keys.ARROW_DOWN);
        selectDistrict.sendKeys(Keys.ENTER);
    }

    public void selectSubDivision(String subDivision) {
        safe.safeType(selectSubDivision, subDivision, "Sub Division");
        selectSubDivision.sendKeys(Keys.ENTER);
    }

    public void selectVillage(String village) {
        safe.safeType(selectVillage, village, "Village");
        selectVillage.sendKeys(Keys.ENTER);
    }

    public void clickSameAsAddressCheckbox() {
        if (!clickSameAsAddress.isSelected()) {
            safe.safeClick(clickSameAsAddress, "Same as Address Checkbox");
        } else {
            logger.info("ℹ️ 'Same as Address' checkbox is already selected. Skipping click.");
        }
    }

    public void clickNextButton() {
        safe.safeClick(nextButton, "Next Button");
    }

    public void selectTypeOfFood() {
        safe.safeClick(selectTypeOfFood, "Kind of Food");
    }

    public void clickSubmitButton() {
        safe.safeClick(submitButton, "Submit");
    }

    public void clickSelectCategory() {
        safe.safeClick(selectCategory, "Select Category");
    }

    public void clickChangeCategory() {
        safe.safeClick(changeCategory, "Change Category");
    }

    public void selectFoodCategoryOption() {
        safe.safeClick(selectFoodCategory, "Food Category");
    }

    public void changeFoodCategoryOption() {
        safe.safeClick(changeFoodCategory, "Food Category");
    }

    public void clickSaveFoodCategory() {
        safe.safeClick(saveFoodCategory, "Save Food Category");
    }

    public void clickAddApplicant() {
        safe.safeClick(addApplicant, "Add Applicant");
    }

    public void typeApplicantName(String name) {
        safe.safeType(enterName, name, "Applicant Name");
    }

    public void typeApplicantMobileNumber(String mobileNumber) {
        safe.safeType(enterMobileNumber, mobileNumber, "Applicant Mobile Number");
    }

    public void enableMakeChargeCheckbox() {
        safe.safeClick(enableMakeCharge, "Make Applicant In-Charge of Operations");
    }

    public void clickDoneButton() {
        safe.safeClick(buttonDone, "Done");
    }

    public void clickFillDetailsByMyself() {
        safe.safeClick(clickFillDetails, "Fill Details By Myself");
    }

    public void typeEmail(String email) {
        safe.safeType(enterEmail, email, "Email");
    }

    public void selectEducationQualification(String qualification) {
        safe.safeType(selectEducationQualification, qualification, "Education Qualification");
        selectEducationQualification.sendKeys(Keys.ENTER);
    }

    public void typeFatherName(String fatherName) {
        safe.safeType(enterFatherName, fatherName, "Father Name");
    }

    public void typeBuildingNumber(String building) {
        safe.safeType(enterBuilding, building, "Building / Flat Number");
    }

    public void typeRoadOrStreet(String road) {
        safe.safeType(enterRoad, road, "Road / Street");
    }

    @FindBy(xpath = "//a[@id='crossSaleNew']")
    public WebElement crossSaleNew;

    @FindBy(xpath = "//li[contains(@class, 'select2-results__option') and contains(text(), 'Central Food License')]")
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
