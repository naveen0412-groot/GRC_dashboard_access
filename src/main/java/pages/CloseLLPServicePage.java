package pages;

import base.BasePage;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import utils.SafeActionUtils;
import utils.WaitUtils;

public class CloseLLPServicePage extends BasePage {

    private final WaitUtils wait;
    SafeActionUtils safe;

    public CloseLLPServicePage(WebDriver driver) {
        super(driver);
        this.wait = new WaitUtils(driver, 30);
        this.safe = new SafeActionUtils(driver);
    }

    @FindBy(xpath = "//input[@placeholder='Enter LLP Name']")
    private WebElement LLPName;

    @FindBy(xpath = "//input[@placeholder='Enter LLPIN']")
    private WebElement LLPIN;

    @FindBy(xpath = "//input[@placeholder='Enter LLP has operations from the date of Incorporation or in last one year']")
    private WebElement lastOneYear;

    @FindBy(xpath = "//input[contains(@id,'react-select') and @role='combobox']")
    private WebElement liabilityAsset;

    @FindBy(xpath = "//input[@placeholder='Enter Bank Account, if any, must be closed and there should be no transaction for the last one year']")
    private WebElement bankAccount;

    @FindBy(xpath = "//input[@placeholder='Enter All the registrations obtained should be closed. Eg. Shops and Establishment, Professional Tax, GST']")
    private WebElement allTheRegistrations;

    @FindBy(xpath = "(//input[contains(@id,'react-select') and @role='combobox'])[1]")
    private WebElement allAnnualFilingseRegistration;

    @FindBy(xpath = "(//input[contains(@id,'react-select') and @role='combobox'])[2]")
    private WebElement incomeTaxReturn;

    @FindBy(xpath = "(//input[contains(@id,'react-select') and @role='combobox'])[3]")
    private WebElement stampPaper;

    @FindBy(xpath = "(//p[text()='Next'])[2]")
    private WebElement nextButton;

    public void clickNextButton() {
        safe.safeClick(nextButton, "Next Button");
    }

    public void typeLLPName(String llpName) {
        safe.safeType(LLPName, llpName, "Enter LLP Name");
    }

    public void typeLLPIN(String llpin) {
        safe.safeType(LLPIN, llpin, "Enter LLPIN");
    }

    public void typeLastOneStatus(String status) {
        safe.safeType(lastOneYear, status, "LLP Operations Status (Last One Year)");
    }

    public void typeBankAccountStatus(String bankStatus) {
        safe.safeType(bankAccount, bankStatus, "Bank Account Status");
    }

    public void typeAllRegistrationsStatus(String registrationStatus) {
        safe.safeType(allTheRegistrations, registrationStatus, "All Registrations Closure Status");
    }

    public void selectLiabilityAsset(String value) {
        safe.safeType(liabilityAsset, value, "Liabilities & Assets");
    }

    public void selectAnnualFilingsStatus(String value) {
        safe.safeType(allAnnualFilingseRegistration, value, "Annual Filings Status");
        allAnnualFilingseRegistration.sendKeys(Keys.ENTER);
    }

    public void selectIncomeTaxReturnStatus(String value) {
        safe.safeType(incomeTaxReturn, value, "Income Tax Return Status");
        incomeTaxReturn.sendKeys(Keys.ENTER);
    }

    public void selectStampPaperStatus(String value) {
        safe.safeType(stampPaper, value, "Stamp Paper Status");
        stampPaper.sendKeys(Keys.ENTER);
    }
    @FindBy(xpath = "//a[@id='crossSaleNew']")
    public WebElement crossSaleNew;

    @FindBy(xpath = "//li[contains(@class, 'select2-results__option') and contains(text(), 'Close your Limited Liability Partnership')]")
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
