package pages;

import base.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import utils.SafeActionUtils;
import utils.WaitUtils;

import java.time.Duration;

public class USTrademarkPage extends BasePage {

    private final WaitUtils wait;
    SafeActionUtils safe;

    public USTrademarkPage(WebDriver driver) {
        super(driver);
        this.wait = new WaitUtils(driver, 30);
        this.safe = new SafeActionUtils(driver);
    }

    @FindBy(xpath = "//a[@id='crossSaleNew']")
    public WebElement crossSaleNew;

    @FindBy(xpath = "//button[@id='paymentNoBtn']")
    public WebElement crossNobutton;

    @FindBy(xpath = "//span[@id='select2-cross_service_tag-container']")
    public WebElement createservice;

    @FindBy(xpath = "//span[contains(@class, 'select2-container--open')]//input[@class='select2-search__field']")
    public WebElement select2SearchField;

    @FindBy(xpath = "//li[contains(@class, 'select2-results__option') and contains(text(), 'Apply for a Trademark in the U.S')]")
    public WebElement selectingtheservice;

    @FindBy(xpath = "//button[@class='btn btn-default btn-u cross_sale_ticket_create']")
    public WebElement createbutton;

    @FindBy(xpath = "//button[@id='copy_upload_doc_link']")
    public WebElement copyurl;

    @FindBy(xpath = "//a[@class='btn recent_tickets']")
    public WebElement recenttickets;

    @FindBy(xpath = "//input[@placeholder='Enter Brand name']")
    private WebElement brandName;

    @FindBy(xpath = "//input[@id='option-Nature of mark-word']")
    private WebElement natureOfMarkWord;

    @FindBy(xpath = "(//p[text()='Next'])[2]")
    private WebElement nextButton;

    @FindBy(xpath = "//input[@placeholder='Enter Applicant name']")
    private WebElement applicantName;

    @FindBy(xpath = "//input[@id='option-Nature of Entity-corporation']")
    private WebElement natureOfEntityCorporation;

    @FindBy(xpath = "//input[@placeholder='Enter Applicant Email']")
    private WebElement applicantEmail;

    @FindBy(xpath = "//input[@placeholder='Enter Applicant Phone']")
    private WebElement applicantPhone;

    @FindBy(xpath = "//input[@placeholder='Enter Name of authorized signatory ']")
    private WebElement nameOfAuthorizedSignatory;

    @FindBy(xpath = "//input[@placeholder='Enter Designation of the authorized signatory ']")
    private WebElement designationOfAuthorizedSignatory;

    @FindBy(xpath = "//input[@name='addressLine1']")
    private WebElement addressLine1;

    @FindBy(xpath = "//input[@placeholder='Enter zip/pin code']")
    private WebElement zipCode;

    @FindBy(xpath = "//input[@id='react-select-2-input']")
    private WebElement selectCurrentUse;

    @FindBy(xpath = "//input[@id='used_since']")
    private WebElement usedSince;

    @FindBy(xpath = "(//div[@class='react-datepicker__week']/div[text()='1'])[1]")
    private WebElement selectDate;

    public void selectUsageDate() {
        logger.info("📅 Selecting Usage Date");
        try {
            Thread.sleep(4000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        safe.safeClick(usedSince, "usedSince calendar field");
        safe.safeClick(selectDate, "usedSince value");
    }

    @FindBy(xpath = "//input[@id='option-Goods/Services-https://idm-tmng.uspto.gov/id-master-list-public.html']")
    private WebElement goods;

    public void typeBrandName(String brand) {
        safe.safeType(brandName, brand, "Brand Name");
    }

    public void clickNatureOfMarkWord() {
        safe.safeClick(natureOfMarkWord, "Nature of Mark - Word");
    }

    public void clickNextButton() {
        safe.safeClick(nextButton, "Next");
    }

    public void typeApplicantName(String name) {
        safe.safeType(applicantName, name, "Applicant Name");
    }

    public void clickNatureOfEntityCorporation() {
        safe.safeClick(natureOfEntityCorporation, "Nature of Entity - Corporation");
    }

    public void typeApplicantEmail(String email) {
        safe.safeType(applicantEmail, email, "Applicant Email");
    }

    public void typeApplicantPhone(String phone) {
        safe.safeType(applicantPhone, phone, "Applicant Phone");
    }

    public void typeAuthorizedSignatoryName(String signatoryName) {
        safe.safeType(nameOfAuthorizedSignatory, signatoryName, "Authorized Signatory Name");
    }

    public void typeAuthorizedSignatoryDesignation(String designation) {
        safe.safeType(designationOfAuthorizedSignatory, designation, "Authorized Signatory Designation");
    }

    public void typeAddressLine1(String address) {
        safe.safeType(addressLine1, address, "Address Line 1");
    }

    public void typeZipCode(String zip) {
        safe.safeType(zipCode, zip, "Zip Code");
    }

//    public void typeCurrentUse(String currentUse) {
//        safe.safeType(selectCurrentUse, currentUse, "Current Use");
//    }

    public void clickGoods() {
        safe.safeClick(goods, "Select Goods");
    }

    public void crossSaleNew() {

        safe.safeClick(crossSaleNew, "Cross Sale click action");
    }

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

    public void selectingtheservice() {
        safe.safeClick(selectingtheservice, "Selecting these services");
    }

    public void createbutton() throws InterruptedException {
        safe.safeClick(createbutton, "Service create button");

        Thread.sleep(2000);
        safe.safeClick(crossNobutton, "Cross Sale No payment action");
        Thread.sleep(2000);
        driver.switchTo().alert().accept();
        logger.info("Browser alert accepted.");
    }

    public void recenttickets() {
        safe.safeClick(recenttickets, "Recent tickets");
    }

    public void typeCurrentUse(String CurrentUse) {
        selectCurrentUse.sendKeys(CurrentUse);

        By optionLocator = By.xpath("//div[contains(@class, '-option') and text()='" + CurrentUse + "']");

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        wait.until(ExpectedConditions.elementToBeClickable(optionLocator)).click();
    }
}
