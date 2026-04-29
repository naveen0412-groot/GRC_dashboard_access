package pages;

import base.BasePage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import utils.SafeActionUtils;
import utils.WaitUtils;

public class CompanyAuditPage extends BasePage {

        private final WaitUtils wait;
        SafeActionUtils safe;

    public CompanyAuditPage(WebDriver driver) {
            super(driver);
            this.wait = new WaitUtils(driver, 15);
            this.safe = new SafeActionUtils(driver);
    }

    @FindBy(xpath = "//input[@placeholder='Enter Income Tax login details']")
    private WebElement inputCompanyName;

    @FindBy(xpath = "//input[@placeholder='Enter GST login details']")
    private WebElement inputGST;

    @FindBy(xpath = "//input[@placeholder='Enter Books of accounts-Zoho/Tally']")
    private WebElement inputBooks;

    @FindBy(xpath = "//input[@placeholder='Enter TDS Challans']")
    private WebElement inputTDS;

    @FindBy(xpath = "//input[@placeholder='Enter PF/ESI Challans']")
    private WebElement inputPF;

    @FindBy(xpath = "//input[@placeholder='Enter Professional Tax challans']")
    private WebElement inputProfTax;

    @FindBy(xpath = "//input[@placeholder='Enter Labour welfare fund payment challan']")
    private WebElement inputLabourFund;

    @FindBy(xpath = "//input[@placeholder='Enter Signed Financials & Audit report']")
    private WebElement inputSignedFinancials;

    @FindBy(xpath = "//input[@placeholder='Enter MSME registation details of the creditors/vendors']")
    private WebElement inputMSME;

    @FindBy(xpath = "//input[@placeholder='Enter PAN of Directors/Shareholders']")
    private WebElement inputPAN;

    @FindBy(xpath = "//input[@placeholder='Enter PAN of creditors']")
    private WebElement inputPANCreditor;

    @FindBy(xpath = "//input[@placeholder='Enter Gratuity fund/contribution details']")
    private WebElement inputGratuity;

    @FindBy(xpath = "//input[@placeholder='Enter TP Audit applicability']")
    private WebElement inputTPAudit;

    @FindBy(xpath = "//input[@placeholder='Enter Purchase of shares']")
    private WebElement inputPurchases;

    @FindBy(xpath = "//input[@placeholder='Enter Issue of shares by company']")
    private WebElement inputIssues;

    @FindBy(xpath = "//input[@placeholder='Enter Change in shareholding pattern']")
    private WebElement inputChangeInShareholding;

    @FindBy(xpath = "//input[@placeholder='Enter Other secretarial Documents']")
    private WebElement inputOther;

    @FindBy(xpath = "(//p[text()='Next'])[2]")
    private WebElement nextButton;

    public void typeCompanyName(String companyName) {
        safe.safeType(inputCompanyName, companyName, "Income Tax Login Details / Company Name");
    }

    public void typeGSTDetails(String gstDetails) {
        safe.safeType(inputGST, gstDetails, "GST Login Details");
    }

    public void typeBooksOfAccounts(String books) {
        safe.safeType(inputBooks, books, "Books of Accounts");
    }

    public void typeTDSChallans(String tds) {
        safe.safeType(inputTDS, tds, "TDS Challans");
    }

    public void typePFChallans(String pf) {
        safe.safeType(inputPF, pf, "PF/ESI Challans");
    }

    public void typeProfessionalTax(String professionalTax) {
        safe.safeType(inputProfTax, professionalTax, "Professional Tax Challans");
    }

    public void typeLabourWelfareFund(String labourFund) {
        safe.safeType(inputLabourFund, labourFund, "Labour Welfare Fund Payment Challan");
    }

    public void typeSignedFinancials(String financials) {
        safe.safeType(inputSignedFinancials, financials, "Signed Financials & Audit Report");
    }

    public void typeMSMEDetails(String msme) {
        safe.safeType(inputMSME, msme, "MSME Registration Details of Creditors/Vendors");
    }

    public void typePANOfDirectors(String pan) {
        safe.safeType(inputPAN, pan, "PAN of Directors/Shareholders");
    }

    public void typePANOfCreditors(String panCreditor) {
        safe.safeType(inputPANCreditor, panCreditor, "PAN of Creditors");
    }

    public void typeGratuityDetails(String gratuity) {
        safe.safeType(inputGratuity, gratuity, "Gratuity Fund/Contribution Details");
    }

    public void typeTPAuditApplicability(String tpAudit) {
        safe.safeType(inputTPAudit, tpAudit, "TP Audit Applicability");
    }

    public void typePurchaseOfShares(String purchase) {
        safe.safeType(inputPurchases, purchase, "Purchase of Shares");
    }

    public void typeIssueOfShares(String issue) {
        safe.safeType(inputIssues, issue, "Issue of Shares by Company");
    }

    public void typeChangeInShareholding(String change) {
        safe.safeType(inputChangeInShareholding, change, "Change in Shareholding Pattern");
    }

    public void typeOtherDocuments(String otherDocs) {
        safe.safeType(inputOther, otherDocs, "Other Secretarial Documents");
    }

    public void clickNextButton() {
        safe.safeClick(nextButton, "Next Button");
    }
    @FindBy(xpath = "//a[@id='crossSaleNew']")
    public WebElement crossSaleNew;

    @FindBy(xpath = "//li[contains(@class, 'select2-results__option') and contains(text(), 'Audit your Company')]")
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
