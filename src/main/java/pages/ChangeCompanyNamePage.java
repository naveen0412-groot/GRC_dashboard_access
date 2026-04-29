package pages;

import base.BasePage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import utils.SafeActionUtils;
import utils.WaitUtils;

public class ChangeCompanyNamePage extends BasePage {
    private final WaitUtils wait;
    SafeActionUtils safe;

    public ChangeCompanyNamePage(WebDriver driver) {
        super(driver);
        this.wait = new WaitUtils(driver, 15);
        this.safe = new SafeActionUtils(driver);
    }
    @FindBy(xpath = "//input[@placeholder='Enter Company Name']")
    private WebElement companyName;

    @FindBy(xpath = "//input[@placeholder='Enter Company CIN']")
    private WebElement companyCIN;

    @FindBy(xpath = "//input[@placeholder='Enter Proposed Name 1']")
    private WebElement proposedName1;

    @FindBy(xpath = "//input[@placeholder='Enter Proposed Name 2']")
    private WebElement proposedName2;

    @FindBy(xpath = "//input[@placeholder='Enter Significance of the New Name: [Kindly enter the reason for choosing this new name]']")
    private WebElement significanceName;

    @FindBy(xpath = "//input[@placeholder='Enter Reason for change of Name: [Kindly enter why you have decided to change the name of the Company]']")
    private WebElement reason;

    @FindBy(xpath = "//input[(@placeholder='Select Option') and (@name='foreign_company_name')]")
    private WebElement tmAppliedForProposedName;

    @FindBy(xpath = "//input[@placeholder='Enter Enter the Tradename']")
    private WebElement tmName;

    @FindBy(xpath = "//input[(@placeholder='Select Option') and (@name='latitude')]")
    private WebElement changeObjective;

    @FindBy(xpath = "//input[@placeholder='Enter Enter the Proposed New Objects']")
    private WebElement newObjectProposed;

    @FindBy(xpath = "//input[@name='name']")
    private WebElement shareTransfer;

    @FindBy(xpath = "//input[@name='mother_name']")
    private WebElement rocAnnualFiling;

    @FindBy(xpath = "//input[@name='director_addressLine1']")
    private WebElement activeDIN;

    @FindBy(xpath = "//input[@name='director_addressLine2']")
    private WebElement activeDSC;

    @FindBy(xpath = "(//p[text()='Next'])[2]")
    private WebElement nextButton;

    public void typeCompanyName(String name) {
        safe.safeType(companyName, name, "Enter Company Name");
    }

    public void typeCompanyCIN(String cin) {
        safe.safeType(companyCIN, cin, "Enter Company Name");
    }


    public void typeProposedName1(String proposed1) {
        safe.safeType(proposedName1, proposed1, "Enter Proposed Name 1");
    }

    public void typeProposedName2(String proposed2) {
        safe.safeType(proposedName2, proposed2, "Enter Proposed Name 2");
    }

    public void typeSignificanceName(String significance) {
        safe.safeType(significanceName, significance, "Enter Significance of New Name");
    }

    public void typeReasonForChange(String reasonForChange) {
        safe.safeType(reason, reasonForChange, "Enter Reason for Change of Name");
    }

    public void typeTmAppliedForProposedName(String tmApplied) {
        safe.safeType(tmAppliedForProposedName, tmApplied, "Select TM Applied for Proposed Name");
    }

    public void typeTmName(String trademarkName) {
        safe.safeType(tmName, trademarkName, "Enter Tradename");
    }

    public void typeChangeObjective(String cinValue) {
        safe.safeType(changeObjective, cinValue, "Enter Company CIN");
    }

    public void typeNewObjectProposed(String proposedObject) {
        safe.safeType(newObjectProposed, proposedObject, "Enter Proposed New Objects");
    }

    public void typeShareTransfer(String shareTransferVal) {
        safe.safeType(shareTransfer, shareTransferVal, "Enter Share Transfer Value");
    }

    public void typeRocAnnualFiling(String rocFiling) {
        safe.safeType(rocAnnualFiling, rocFiling, "Enter ROC Annual Filing");
    }

    public void typeActiveDIN(String din) {
        safe.safeType(activeDIN, din, "Enter Active DIN");
    }

    public void typeActiveDSC(String dsc) {
        safe.safeType(activeDSC, dsc, "Enter Active DSC");
    }


    public void clickNextButton() {
        safe.safeClick(nextButton, "Next Button");
    }

    @FindBy(xpath = "//a[@id='crossSaleNew']")
    public WebElement crossSaleNew;


    @FindBy(xpath = "//li[contains(@class, 'select2-results__option') and contains(text(), 'Change your Company Name')]")
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
