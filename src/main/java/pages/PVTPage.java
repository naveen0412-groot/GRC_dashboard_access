package pages;

import base.BasePage;
import org.openqa.selenium.*;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import utils.SafeActionUtils;
import utils.WaitUtils;

import java.time.Duration;

public class PVTPage extends BasePage {

    private final WaitUtils wait;
    SafeActionUtils safe;

    public PVTPage(WebDriver driver) {
        super(driver);
        this.wait = new WaitUtils(driver, 15);
        this.safe = new SafeActionUtils(driver);
    }

    @FindBy(xpath = "//input[@placeholder='eg. Software Development, Textile Manufacturing']")
    private WebElement searchEnterCompanyName;

    @FindBy(xpath = "//button[text()='Search']")
    private WebElement clickSearchCTA;

    @FindBy(xpath="//label[text()='NIC CODE: 47413']")
    private WebElement selectNICCODE;

    @FindBy(xpath = "(//p[text()='Next'])[2]")
    private WebElement nextButton;

    @FindBy(xpath = "//p[text()='Change']")
    private WebElement changeButton;

    @FindBy(xpath = "(//p[text()='Confirm'])[2]")
    public WebElement confirmButton;

    @FindBy(xpath = "//input[@placeholder='Enter Company name']")
    private WebElement enterCompanyName;

    @FindBy(xpath = "//img[@alt='star']")
    public WebElement editIcon;

    @FindBy(xpath = "//div[@class='border-[1px] border-[#231F20] rounded-[8px] overflow-hidden flex justify-between items-center bg-[#ffff]']/input")
    private WebElement editCompanyName;

    @FindBy(xpath = "//textarea[@name='significance_1']")
    private WebElement significance;

    @FindBy(xpath = "//p[text()='Check availability']")
    private WebElement checkAvailability;

    //Director Flow

    @FindBy(xpath = "//div//p[text()='2']/following::p[text()='Director details']")
    private WebElement directorDetailsTab;

    @FindBy(xpath = "(//img[@alt='delete'])[1]")
    private WebElement deleteButton;

    @FindBy(xpath = "//p[text()='Yes']")
    private WebElement yesButton;

    @FindBy(xpath = "//p[text()='Add 2 Directors']")
    private WebElement add2Directors;

    @FindBy(xpath = "(//p[text()='Fill Step 1'])[1]")
    private WebElement clickFillStep;

    @FindBy(xpath = "//input[@placeholder='Enter name']")
    private WebElement enterName;

//    @FindBy(xpath = "//div[@id='react-select-3-placeholder']")
//    private WebElement selectNationality;
//
//    @FindBy(xpath = "//div[@id='react-select-3-placeholder']/following-sibling::input[@id='react-select-3-input']")
//    public WebElement clickNationality;


    @FindBy(xpath = "//input[@name='email']")
    private WebElement enterEmail;

    @FindBy(xpath = "//input[@name='mobile']")
    private WebElement enterMobile;



//    @FindBy(xpath = "//div[contains(@class,'option') and text()='Director cum shareholder']")
//    private WebElement selectRoleDirector;

    @FindBy(xpath = "//input[@type='checkbox' and @class='accent-[#022B50] rounded-[0.4rem] cursor-pointer']")
    private WebElement enableCheckbox;

    @FindBy(xpath = "//p[text()='Upload by myself']")
    private WebElement clickUploadMyself;

    //Upload Flow
    @FindBy(xpath = "//p[text()='Aadhar card front']/parent::div/parent::div/parent::div/div[2]/input")
    public WebElement selectAadharFront;

    @FindBy(xpath = "//p[text()='Aadhar card back']/parent::div/parent::div/parent::div/div[2]/input")
    public WebElement selectAadharBack;

    @FindBy(xpath = "//p[text()='Passport size photograph']/parent::div/parent::div/parent::div/div[2]/input")
    public WebElement uploadPassportSize;

    @FindBy(xpath = "//p[text()='PAN card']/parent::div/parent::div/parent::div/div[2]/input")
    public WebElement uploadPAN;

    @FindBy(xpath = "//input[@id='Bank statement']")
    private WebElement clickUploadBankStatementCheckbox;

    //Signature Flow
    @FindBy(xpath = "//button[text()='Sign']")
    private WebElement clickSign;

    @FindBy(xpath = "//button[text()='I agree, Sign now']")
    private WebElement clickIAgree;

    @FindBy(xpath = "//button[text()='Got it']")
    private WebElement clickGotIt;

    @FindBy(xpath = "//canvas[@class='sigCanvas']")
    private WebElement clickSignCanvas;

    @FindBy(xpath = "//button[text()='Save & Next']")
    private WebElement clickSaveAndNext;

    @FindBy(xpath = "//button[text()='Submit']")
    private WebElement clickSubmit;



    //Personal Info
//    @FindBy(xpath = "//input[@id='react-select-2-input']")
//    private WebElement enterEducation; //Primary education
//
//    @FindBy(xpath = "//input[@id='react-select-5-input']")
//    private WebElement enterPlaceOfBirth; //Chennai
//
//    @FindBy(xpath = "//input[@id='react-select-4-input']")
//    private WebElement enterWorkStatus; //Business

    @FindBy(xpath = "//div[contains(text(), 'educational qualification')]/following-sibling::div//input")
    private WebElement enterEducation;

    @FindBy(xpath = "//div[contains(text(), 'place of birth')]/following-sibling::div//input")
    private WebElement enterPlaceOfBirth;

    @FindBy(xpath = "//div[contains(text(), 'work status')]/following-sibling::div//input")
    private WebElement enterWorkStatus;

    @FindBy(xpath = "//input[@placeholder='Enter Address Line 1']")
    private WebElement enterAddressLine1;

    @FindBy(xpath = "//input[@placeholder='Enter Address Line 2']")
    private WebElement enterAddressLine2;

    @FindBy(xpath = "//input[@placeholder='Enter pin code']")
    private WebElement enterPinCode;

//    @FindBy(xpath = "//input[@id='react-select-5-input']")
//    private WebElement enterYear;
//
//    @FindBy(xpath = "//input[@id='react-select-6-input']")
//    private WebElement enterMonth;

    @FindBy(xpath = "//div[contains(text(), 'Select Year')]/following-sibling::div//input")
    private WebElement enterYear;

    @FindBy(xpath = "//div[contains(text(), 'Select Month')]/following-sibling::div//input")
    private WebElement enterMonth;

    //Next Step - Confirm the “Authorised signatory”
    //Company Details

    @FindBy(xpath = "//input[@placeholder='eg. Software Development, Textile Manufacturing']")
    private WebElement enterBusiness; //Software

    @FindBy(xpath = "//label[text()='NIC CODE: 46512']")
    private WebElement enterNIC;

    @FindBy(xpath = "//textarea[@name='objective']")
    private WebElement enterBusinessObjective;

    @FindBy(xpath = "//span[text()='Edit']")
    public WebElement editButton;

    @FindBy(xpath = "//p[text()='Done']")
    private WebElement buttonDone;

    //Company Contact Details
    @FindBy(xpath = "//input[@placeholder='Enter Email ID']")
    private WebElement enterCompEmailID;

    @FindBy(xpath = "//input[@placeholder='Enter Mobile Number']")
    private WebElement enterCompMobileNumber;

    @FindBy(xpath="//div/p[text()='Own Property']")
    private WebElement clickBusinessPropertyType;

    @FindBy(xpath = "//button[text()='Confirm']")
    private WebElement buttonConfirm;

    @FindBy(xpath = "//input[@id='Electricity bill']")
    private WebElement enableUploadLaterCheckbox;

    @FindBy(xpath = "//input[@name='addressLine1']")
    private WebElement compAddressLine1;

    @FindBy(xpath = "//input[@name='addressLine2']")
    private WebElement compAddressLine2;

    @FindBy(xpath = "//input[@name='pincode']")
    private WebElement compPincode;

    @FindBy(xpath = "//input[@id='react-select-7-input']")
    private WebElement compAreaLocality;

    @FindBy(xpath = "//button/p[text()='Submit']")
    private WebElement compSubmitButton;

    @FindBy(xpath = "//button/p[text()='Confirm']")
    private WebElement compConfirmButton;

    public void clickBusinessPropertyType() {
        safe.safeClick(clickBusinessPropertyType, "Business Property Type");
    }

    public void clickConfirmPropertySelection() {
        safe.safeClick(buttonConfirm, "Confirm");
    }

    public void clickEnableUploadLaterCheckbox() {
        if (!enableUploadLaterCheckbox.isSelected()) {
            safe.safeClick(enableUploadLaterCheckbox, "Upload Later Checkbox");
            System.out.println("Checkbox was unchecked. Now enabled.");
        } else {
            System.out.println("Checkbox is already enabled.");
        }
    }


    public void typeCompAddressLine1(String address1) {
        safe.safeType(compAddressLine1, address1, "Company Address Line 1");
    }

    public void typeCompAddressLine2(String address2) {
        safe.safeType(compAddressLine2, address2, "Company Address Line 2");
    }

    public void typeCompPincode(String pincode) {
        safe.safeType(compPincode, pincode, "Company Pincode");
    }

    public void typeCompAreaLocality(String areaLocality) {
        safe.safeType(compAreaLocality, areaLocality, "Company Area Locality");
    }

    public void clickCompSubmitButton(){
        safe.safeClick(compSubmitButton, "Submit");
    }

    public void clickCompConfirmButton(){
        safe.safeClick(compConfirmButton, "Confirm");
    }

    public void typeEnterCompEmailID(String compEmailID) {
        safe.safeType(enterCompEmailID, compEmailID, "Company Email ID");
    }

    public void typeEnterCompMobileNumber(String compMobileNumber) {
        safe.safeType(enterCompMobileNumber, compMobileNumber, "Company Mobile Number");
    }

    public void typeSearchEnterCompanyName(String companyName) {
        safe.safeType(searchEnterCompanyName, companyName, "Search Company Name");
    }

    public void clickEditCTA() {
        safe.safeClick(editButton, "Edit CTA");
    }

    public void typeBusinessObjective(String businessObjective) {
        safe.safeType(enterBusinessObjective, businessObjective, "Business Objective");
    }

    public void typeEnterCompanyName(String companyName) {
        safe.safeType(enterCompanyName, companyName, "Company Name");
    }

    public void clickEditIcon(){
        safe.safeClick(editIcon, "Edit Icon");
    }

    public void typeByEditingCompanyName(String companyName) {
        safe.safeType(editCompanyName, companyName,"Edit Company Name");
    }

    public void typeSignificance(String value) {
        safe.safeType(significance, value, "Significance");
    }

    public void typeEnterName(String name) {
        safe.safeType(enterName, name, "Director Name");
    }

    public void typeEnterEmail(String email) {
        safe.safeType(enterEmail, email, "Director Email");
    }

    public void typeEnterMobile(String mobile) {
        safe.safeType(enterMobile, mobile, "Director Mobile Number");
    }
//
//    public void typeEnterEducation(String education) {
//        safe.safeType(enterEducation, education, "Education");
//    }
//
//    public void typeEnterPlaceOfBirth(String placeOfBirth) {
//        safe.safeType(enterPlaceOfBirth, placeOfBirth, "Place of Birth");
//    }
//
//    public void typeEnterWorkStatus(String workStatus) {
//        safe.safeType(enterWorkStatus, workStatus, "Work Status");
//    }

    public void selectFromReactDropdown(WebElement inputElement, String value, String fieldName) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        JavascriptExecutor js = (JavascriptExecutor) driver;

        try {
            wait.until(ExpectedConditions.elementToBeClickable(inputElement));
            js.executeScript("arguments[0].click();", inputElement);

            inputElement.sendKeys(value);
            logger.info("Typed '" + value + "' into " + fieldName);

            String optionXpath = String.format("//div[contains(@class, '-option') and contains(normalize-space(), '%s')]", value);
            WebElement option = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(optionXpath)));

            js.executeScript("arguments[0].click();", option);
            logger.info("✔ Selected " + fieldName + " -> " + value);

        } catch (Exception e) {
            throw new RuntimeException("❌ Failed to select '" + value + "' for " + fieldName, e);
        }
    }

    // Updated wrapper methods
    public void typeEnterEducation(String education) {
        selectFromReactDropdown(enterEducation, education, "Education");
    }

    public void typeEnterPlaceOfBirth(String placeOfBirth) {
        selectFromReactDropdown(enterPlaceOfBirth, placeOfBirth, "Place of Birth");
    }

    public void typeEnterWorkStatus(String workStatus) {
        selectFromReactDropdown(enterWorkStatus, workStatus, "Work Status");
    }

    public void typeEnterAddressLine1(String address1) {
        safe.safeType(enterAddressLine1, address1, "Address Line 1");
    }

    public void typeEnterAddressLine2(String address2) {
        safe.safeType(enterAddressLine2, address2, "Address Line 2");
    }

    public void typeEnterPinCode(String pinCode) {
        safe.safeType(enterPinCode, pinCode, "Pin Code");
    }

//    public void typeEnterYear(String year) {
//        safe.safeType(enterYear, year, "Year");
//    }
//
//    public void typeEnterMonth(String month) {
//        safe.safeType(enterMonth, month, "Month");
//    }

    public void yearmonth(WebElement inputElement, String value, String fieldName) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        JavascriptExecutor js = (JavascriptExecutor) driver;

        try {
            wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//input"))); // General check
            js.executeScript("arguments[0].click();", inputElement);

            inputElement.sendKeys(value);
            logger.info("Typed '" + value + "' into " + fieldName);

            String optionXpath = String.format("//div[contains(@class, '-option') and normalize-space()='%s']", value);
            WebElement option = wait.until(ExpectedConditions.elementToBeClickable(By.xpath(optionXpath)));

            js.executeScript("arguments[0].click();", option);
            logger.info("✔ Selected " + fieldName + " -> " + value);

        } catch (Exception e) {
            throw new RuntimeException("❌ Failed to select '" + value + "' for " + fieldName, e);
        }
    }

    public void typeEnterYear(String year) {
        yearmonth(enterYear, year, "Year");
    }

    public void typeEnterMonth(String month) {
        yearmonth(enterMonth, month, "Month");
    }

    public void typeEnterBusiness(String business) {
        safe.safeType(enterBusiness, business, "Business Type");
    }

    public void clickSearchCTA() {
        safe.safeClick(clickSearchCTA, "Search");
    }

    public void clickSelectNICCODE() {
        safe.safeClick(selectNICCODE, "Select NIC Code");
    }

    public void clickNextButton() {
        safe.safeClick(nextButton, "Next Button");
    }

    public void clickChangeLink() {
        safe.safeClick(changeButton, "Change link");
    }

    public void clickConfirmButton() {
        if(confirmButton.isDisplayed()){
            safe.safeClick(confirmButton, "Confirm");
        }
        else if (nextButton.isDisplayed())
        {
            safe.safeClick(nextButton, "Next Button");
        }
    }

    public void clickCheckAvailability() {
        safe.safeClick(checkAvailability, "Check Availability");
    }

    public void clickDoneButton(){
        safe.safeClick(buttonDone, "Done Button");
    }
    public void clickTab2(){
        safe.safeClick(directorDetailsTab,"Tab 2");
    }

    public void clickDeleteButton(){
        safe.safeClick(deleteButton,"Delete Button");
    }

    public void clickYesButton(){
        safe.safeClick(yesButton,"Yes Button");
    }
    public void clickAdd2Directors() {
        safe.safeClick(add2Directors, "Add 2 Directors");
    }

    public void clickFillStep() {
        safe.safeClick(clickFillStep, "Fill Step 1");
    }

//    public void clickSelectNationality() {
//        safe.safeClick(selectNationality, "Select Nationality");
//    }

//    public void clickSelectRoleDirector() {
//        safe.safeClick(selectRoleDirector, "Role - Director cum shareholder");
//    }

    public void clickEnableCheckbox() {
        safe.safeClick(enableCheckbox, "Enable Checkbox");
    }

    public void clickUploadMyself() {
        safe.safeClick(clickUploadMyself, "Upload by Myself");
    }

    public void clickUploadBankStatementCheckbox() {
        safe.safeClick(clickUploadBankStatementCheckbox, "Bank Statement Checkbox");
    }

    public void clickSign() {
        safe.safeClick(clickSign, "Sign");
    }

    public void clickIAgree() {
        safe.safeClick(clickIAgree, "I Agree Sign Now");
    }

    public void clickGotIt() {
        safe.safeClick(clickGotIt, "Got It");
    }

    public void clickSignCanvas() {
        safe.safeClick(clickSignCanvas, "Signature Canvas");
    }

    public void clickSaveAndNext() {
        safe.safeClick(clickSaveAndNext, "Save & Next");
    }

    public void clickSubmit() {
        safe.safeClick(clickSubmit, "Submit");
    }

    public void clickEnterNIC() {
        safe.safeClick(enterNIC, "NIC Code");
    }

    @FindBy(xpath = "//a[@id='crossSaleNew']")
    public WebElement crossSaleNew;

    @FindBy(xpath = "//li[contains(@class, 'select2-results__option') and contains(text(), 'Private Limited Company Registration')]")
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

    @FindBy(xpath = "//span[@class='text-[14px] mt-1']")
    public WebElement objectivesedit;


    public void objectivesedit() {

        safe.safeClick(objectivesedit, "Edit objective click");
    }
    @FindBy(xpath = "//textarea[@placeholder='eg. Software Development, Textile Manufacturing']")
    public WebElement objectivesdetails;


    public void objectivesdetails(String describeBusiness) {

        safe.safeType(objectivesdetails, describeBusiness, "Entering the objective details");
    }

    @FindBy(xpath = "//button[@class='font-medium text-[14px] rounded-[4px] flex items-center gap-[5px] p-[7px] px-[10px] relative overflow-hidden bg-[#022B50] border-[1px] border-[#022B50] text-white w-full justify-center event-track cursor-pointer hover:bg-opacity-90']")
    public WebElement afterdirectoraddconfirmbutton;


    public void afterdirectoraddconfirmbutton() {
        safe.safeClick(afterdirectoraddconfirmbutton, "Authorised director add confirmbutton");
    }

    @FindBy(xpath = "//label[text()='Nationality']/following::input[contains(@id, 'react-select')]")
    private WebElement nationalityInput;

    @FindBy(xpath = "//label[text()='Nationality']/following::div[contains(@class, 'placeholder')]")
    private WebElement nationalityClickable;

    public void selectNationality(String country) {
        safe.safeClick(nationalityClickable, "Nationality Dropdown");

        nationalityInput.sendKeys(country);

        try { Thread.sleep(500); } catch (InterruptedException e) {}
        nationalityInput.sendKeys(Keys.ENTER);
    }
//
//    @FindBy(xpath = "//div[@id='react-select-4-placeholder']")
//    private WebElement selectRole;
//
//    public void clickSelectRole() {
//        safe.safeClick(selectRole, "Select Role");
//    }
@FindBy(xpath = "//label[contains(text(), 'Role')]/following::div[contains(@class, 'placeholder')]")
private WebElement selectRoleContainer;

    @FindBy(xpath = "//div[contains(@class, 'option') and text()='Director cum shareholder']")
    private WebElement directorOption;

    public void selectDirectorRoleCommon() {
        safe.safeClick(selectRoleContainer, "Opening Role Dropdown");

        try { Thread.sleep(1000); } catch (InterruptedException e) { e.printStackTrace(); }

        safe.safeClick(directorOption, "Clicking Director cum shareholder Option");
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

