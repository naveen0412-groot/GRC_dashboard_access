package pages;

import base.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import utils.SafeActionUtils;
import utils.WaitUtils;

import java.time.Duration;

public class OPCPage extends BasePage {
    private final WaitUtils wait;
    SafeActionUtils safe;

    public OPCPage(WebDriver driver) {
        super(driver);
        this.wait = new WaitUtils(driver, 30);
        this.safe = new SafeActionUtils(driver);
    }

    @FindBy(xpath = "//input[@placeholder='Enter Company name']")
    private WebElement companyName;

    @FindBy(xpath = "//textarea[@name='significance_1']")
    private WebElement significance;

    @FindBy(xpath = "//input[@placeholder='Select Industry']")
    private WebElement industry;

    @FindBy(xpath = "//p[text()='Software/ Technology']")
    private WebElement selectIndustryOption;

    @FindBy(xpath = "//p[text()='Add Director cum shareholder']")
    private WebElement addDirectorButton;

    @FindBy(xpath = "//input[@placeholder='Enter name']")
    private WebElement directorName;

    @FindBy(xpath = "//input[@placeholder='Enter email']")
    private WebElement directorEmailAddress;

    @FindBy(xpath = "//input[@placeholder='Enter mobile number']")
    private WebElement directorMobileNumber;

    @FindBy(xpath = "//button[@class='font-medium text-[14px] rounded-[4px] flex items-center gap-[5px] p-[7px] px-[10px] relative overflow-hidden bg-[#022B50] border-[1px] border-[#022B50] text-white m-[1.6rem] w-[calc(100%-3.2rem)] justify-center event-track cursor-pointer hover:bg-opacity-90']")
    private WebElement doneButton;

    @FindBy(xpath = "//button[@class='font-medium text-[14px] rounded-[4px] flex items-center gap-[5px] p-[7px] px-[10px] relative overflow-hidden bg-[#022B50] border-[1px] border-[#022B50] text-white w-full justify-center event-track cursor-pointer hover:bg-opacity-90')")
    private WebElement confirmButton;

    @FindBy(xpath = "//button[contains(., 'Upload by myself')]")
    private WebElement uploadMyselfButton;

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

    @FindBy(xpath = "(//input[contains(@id,'react-select') and @role='combobox'])[1]")
    private WebElement selectEducation;

    @FindBy(xpath = "(//input[contains(@id,'react-select') and @role='combobox'])[2]")
    private WebElement selectPlaceOFBirth;

    @FindBy(xpath = "(//input[contains(@id,'react-select') and @role='combobox'])[3]")
    private WebElement selectWorkStatus;

    @FindBy(xpath = "//input[@placeholder='Enter Address Line 1']")
    private WebElement enterAddressLine1;

    @FindBy(xpath = "//input[@placeholder='Enter Address Line 2']")
    private WebElement enterAddressLine2;

    @FindBy(xpath = "//input[@placeholder='Enter Pin Code']")
    private WebElement enterPinCode;

//    @FindBy(xpath = "(//input[contains(@id,'react-select') and @role='combobox'])[1]")
//    private WebElement selectAreaLocality;

    @FindBy(xpath = "//button[@class='font-medium text-[14px] rounded-[4px] flex items-center gap-[5px] p-[7px] px-[10px] relative overflow-hidden bg-[#022B50] border-[1px] border-[#022B50] text-white w-full justify-center py-[1.2rem] event-track cursor-pointer hover:bg-opacity-90']")
    public WebElement submitbutton;

    @FindBy(xpath = "(//input[contains(@id,'react-select') and @role='combobox'])[2]")
    private WebElement selectYear;

    @FindBy(xpath = "(//input[contains(@id,'react-select') and @role='combobox'])[3]")
    private WebElement selectMonth;

    @FindBy(xpath = "//button[@class='font-medium text-[14px] flex items-center gap-[5px] p-[7px] px-[10px] relative overflow-hidden w-full justify-center border-[0.1rem] border-dashed border-[#8095A7] bg-[#F2F4F6] rounded-md py-[1.2rem] event-track cursor-pointer hover:bg-opacity-90']")
    private WebElement clickAddNominee;

    //Company Details
    @FindBy(xpath = "//p[text()='Own Property']")
    private WebElement clickPropertyType;



    @FindBy(xpath = "//input[@id='Electricity Bill']")
    private WebElement enableUploadLaterCheckbox;

    @FindBy(xpath = "//input[@name='addressLine1']")
    private WebElement compAddressLine1;

    @FindBy(xpath = "//input[@name='addressLine2']")
    private WebElement compAddressLine2;

    @FindBy(xpath = "//input[@name='pincode']")
    private WebElement compPincode;

    @FindBy(xpath = "//div[contains(@id, 'Area locality')]//input")
    private WebElement compAreaLocality;

    @FindBy(xpath = "//button[@class='styles_outlined__S3VtZ undefined  ']")
    private WebElement compSubmitButton;

    @FindBy(xpath = "//button[@class='styles_outlined__S3VtZ undefined  ']")
    private WebElement compConfirmButton;

    @FindBy(xpath = "(//p[text()='Next'])[2]")
    private WebElement nextButton;

    @FindBy(xpath="//input[@placeholder='Enter Email ID']")
    private WebElement enterCompEmailID;

    @FindBy(xpath = "//input[@placeholder='Enter Mobile Number']")
    private WebElement enterCompMobileNumber;

    @FindBy(xpath = "//img[@alt='delete']")
    public WebElement deleteButton;

    @FindBy(xpath = "//p[text()='Yes']")
    private WebElement yesButton;

    public void clickYesButton(){
        safe.safeClick(yesButton, "Yes to delete");
    }
    public void clickDeleteButton() {
        safe.safeClick(deleteButton, "Delete the applicant");
    }

    public void typeCompanyName(String companyNameValue) {
        safe.safeType(companyName, companyNameValue, "Company Name");
    }

    public void typeSignificance(String significanceValue) {
        safe.safeType(significance, significanceValue, "Significance");
    }

    public void setClickIndustry() {
        safe.safeClick(industry, "Industry");
    }

    public void clickIndustryOption() {
        safe.safeClick(selectIndustryOption, "Industry Option - Software/ Technology");
    }

    public void clickAddDirectorCumShareholder() {
        safe.safeClick(addDirectorButton, "Add Director cum Shareholder Button");
    }

    public void typeDirectorName(String name) {
        safe.safeType(directorName, name, "Director Name");
    }

    public void typeDirectorEmailAddress(String email) {
        safe.safeType(directorEmailAddress, email, "Director Email Address");
    }

    public void typeDirectorMobileNumber(String mobileNumber) {
        safe.safeType(directorMobileNumber, mobileNumber, "Director Mobile Number");
    }

    public void clickDoneButton() {
        safe.safeClick(doneButton, "Done Button");
    }

    public void clickConfirmButton() {
        safe.safeClick(confirmButton, "Confirm Button");
    }

    public void clickUploadByMyselfButton() {
        safe.safeClick(uploadMyselfButton, "Upload by Myself Button");
    }

    public void clickUploadBankStatementCheckbox() {
        safe.safeClick(clickUploadBankStatementCheckbox, "Upload Bank Statement Checkbox");
    }

    public void clickSignButton() {
        safe.safeClick(clickSign, "Sign Button");
    }

    public void clickIAgreeSignNowButton() {
        safe.safeClick(clickIAgree, "I Agree, Sign Now Button");
    }

    public void clickGotItButton() {
        safe.safeClick(clickGotIt, "Got It Button");
    }

    public void clickOnSignCanvas() {
        safe.safeClick(clickSignCanvas, "Signature Canvas");
    }

    public void clickSaveAndNextButton() {
        safe.safeClick(clickSaveAndNext, "Save & Next Button");
    }

    public void clickSubmitButton() {
        safe.safeClick(clickSubmit, "Submit Button");
    }

    public void submitbutton() {
        safe.safeClick(submitbutton, "Submit Button");
    }

    public void selectEducation(String education) {
        safe.safeType(selectEducation, education, "Education");
        selectEducation.sendKeys(Keys.ENTER);
    }

//    public void selectPlaceOfBirth(String placeOfBirth) {
//        safe.safeType(selectPlaceOFBirth, placeOfBirth, "Place of Birth");
//        selectPlaceOFBirth.sendKeys(Keys.ENTER);
//    }

    public void selectPlaceOfBirth(String placeOfBirth) {
        safe.safeClick(selectPlaceOFBirth, "Place of Birth Field");
        selectPlaceOFBirth.sendKeys(placeOfBirth);
        By resultLocator = By.xpath("//div[contains(@id, 'react-select') and contains(text(), '" + placeOfBirth + "')]");
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        try {
            WebElement result = wait.until(ExpectedConditions.elementToBeClickable(resultLocator));
            result.click();
        } catch (Exception e) {
            selectPlaceOFBirth.sendKeys(Keys.ENTER);
        }
    }

    public void selectWorkStatus(String workStatus) {
        safe.safeType(selectWorkStatus, workStatus, "Work Status");
        selectWorkStatus.sendKeys(Keys.ENTER);
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

    public void selectYear(String year) {
        safe.safeType(selectYear, year, "Year");
        selectYear.sendKeys(Keys.ENTER);
    }

    public void selectMonth(String month) {
        safe.safeType(selectMonth, month, "Month");
        selectMonth.sendKeys(Keys.ENTER);
    }

    public void clickAddNomineeButton() {
        safe.safeClick(clickAddNominee, "Add Nominee Button");
    }

    public void typeCompEmailID(String Email) {
        safe.safeType(enterCompEmailID, Email, "Company Email Id");
    }

    public void typeCompMobileNumber(String MobileNumber) {
        safe.safeType(enterCompMobileNumber, MobileNumber, "Company Mobile Number");
    }

    public void clickPropertyType() {
        safe.safeClick(clickPropertyType, "Family Property Type");
    }

    public void clickEnableUploadLaterCheckbox() {
        safe.safeClick(enableUploadLaterCheckbox, "Enable Upload Later Checkbox");
    }

    public void typeCompAddressLine1(String addressLine1) {
        safe.safeType(compAddressLine1, addressLine1, "Company Address Line 1");
    }

    public void typeCompAddressLine2(String addressLine2) {
        safe.safeType(compAddressLine2, addressLine2, "Company Address Line 2");
    }

    public void typeCompPincode(String pincode) {
        safe.safeType(compPincode, pincode, "Company Pincode");
    }

    public void selectCompAreaLocality(String locality) {
        safe.safeClick(compAreaLocality, "Company Area Dropdown");

        safe.safeType(compAreaLocality, locality, "Company Area / Locality");

        String optionXpath = String.format("//div[contains(@id, 'react-select') and text()='%s']", locality);

        try {
            WebElement option = driver.findElement(By.xpath(optionXpath));
            option.click();
        } catch (Exception e) {
            compAreaLocality.sendKeys(Keys.ARROW_DOWN);
            compAreaLocality.sendKeys(Keys.ENTER);
        }
    }

    public void clickCompSubmitButton() {
        safe.safeClick(compSubmitButton, "Company Submit Button");
    }

    public void clickCompConfirmButton() {
        safe.safeClick(compConfirmButton, "Company Confirm Button");
    }

    public void clickNextButton() {
        safe.safeClick(nextButton, "Next Button");
    }

    @FindBy(xpath = "//a[@id='crossSaleNew']")
    public WebElement crossSaleNew;



    @FindBy(xpath = "//li[contains(@class, 'select2-results__option') and contains(text(), 'Register a One Person Company')]")
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

    @FindBy(xpath = "//button[normalize-space()='Confirm']")
    public WebElement afterdirectoraddconfirmbutton;

    public void afterdirectoraddconfirmbutton() {
       WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.elementToBeClickable(afterdirectoraddconfirmbutton));

        safe.safeClick(afterdirectoraddconfirmbutton, "Authorised director add confirmbutton");

        wait.until(ExpectedConditions.invisibilityOf(afterdirectoraddconfirmbutton));
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

