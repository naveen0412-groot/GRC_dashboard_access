package pages;

import base.BasePage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import utils.SafeActionUtils;
import utils.WaitUtils;

public class ChequeBounceNoticePage extends BasePage {

    private final WaitUtils wait;
    SafeActionUtils safe;

    public ChequeBounceNoticePage(WebDriver driver) {
        super(driver);
        this.wait = new WaitUtils(driver, 15);
        this.safe = new SafeActionUtils(driver);
    }

    @FindBy(xpath = "//input[@placeholder='Enter Name']")
    private WebElement name;

    @FindBy(xpath = "//input[@placeholder='Enter Address']")
    private WebElement address;

    @FindBy(xpath = "//input[@placeholder='Enter Name']")
    private WebElement oppositePartyName;

    @FindBy(xpath = "//input[@placeholder='Enter Address']")
    private WebElement oppositePartyAddress;

    @FindBy(xpath = "//input[@placeholder='Enter Description of Debt (Why they Owe you Money)']")
    private WebElement debtDescription;

    @FindBy(xpath = "//input[@placeholder='Enter What documents you have in Proof of Debt']")
    private WebElement proofOfDebtDescription;

    @FindBy(xpath = "//input[@placeholder='Enter Total Debt Amount']")
    private WebElement totalDebtAmount;

    @FindBy(xpath = "//input[@placeholder='Enter Cheque Number']")
    private WebElement chequeNumber;

    @FindBy(xpath = "//input[@placeholder='Date of Cheque']")
    private WebElement chequeDate;

    @FindBy(xpath = "//input[@placeholder='Enter Cheque Amount']")
    private WebElement chequeAmount;

    @FindBy(xpath = "//input[@placeholder='Enter Name of the Bank  (Branch) issued Cheque']")
    private WebElement branchName;

    @FindBy(xpath = "//input[@placeholder='Enter Bank Branch where Cheque is Deposited']")
    private WebElement depositedBranch;

    @FindBy(xpath = "//input[@placeholder='Date of Return Memo']")
    private WebElement memoReturnDate;

    @FindBy(xpath = "//input[@placeholder='Enter Reason For Return']")
    private WebElement reasonForReturn;

    @FindBy(xpath = "//input[@placeholder='Enter Other Details, If any']")
    private WebElement otherDetails;

    @FindBy(xpath = "(//p[text()='Next'])[2]")
    private WebElement nextButton;

    @FindBy(xpath = "(//div[@class='react-datepicker__week']/div[text()='1'])[1]")
    private WebElement selectDate;

    public void typeName(String yourName) {
        safe.safeType(name, yourName, "Enter Name");
    }

    public void typeAddress(String yourAddress) {
        safe.safeType(address, yourAddress, "Enter Address");
    }

    public void typeOppositePartyName(String oppName) {
        safe.safeType(oppositePartyName, oppName, "Enter Opposite Party Name");
    }

    public void typeOppositePartyAddress(String oppAddress) {
        safe.safeType(oppositePartyAddress, oppAddress, "Enter Opposite Party Address");
    }

    public void typeDebtDescription(String description) {
        safe.safeType(debtDescription, description, "Enter Description of Debt");
    }

    public void typeProofOfDebtDescription(String proof) {
        safe.safeType(proofOfDebtDescription, proof, "Enter Proof of Debt Description");
    }

    public void typeTotalDebtAmount(String amount) {
        safe.safeType(totalDebtAmount, amount, "Enter Total Debt Amount");
    }

    public void typeChequeNumber(String chequeNo) {
        safe.safeType(chequeNumber, chequeNo, "Enter Cheque Number");
    }

    public void typeChequeAmount(String chequeAmt) {
        safe.safeType(chequeAmount, chequeAmt, "Enter Cheque Amount");
    }

    public void typeBranchName(String bankBranch) {
        safe.safeType(branchName, bankBranch, "Enter Bank Branch Name (Issued Cheque)");
    }

    public void typeDepositedBranch(String depositBranch) {
        safe.safeType(depositedBranch, depositBranch, "Enter Deposited Branch");
    }

    public void typeReasonForReturn(String reason) {
        safe.safeType(reasonForReturn, reason, "Enter Reason for Return");
    }

    public void typeOtherDetails(String details) {
        safe.safeType(otherDetails, details, "Enter Other Details");
    }


    public void clickNextButton() {
        safe.safeClick(nextButton, "Next Button");
    }

    public void selectChequeDate() {
        logger.info("📅 Selecting Cheque Date");
        safe.safeClick(chequeDate, "Cheque calendar field");
        safe.safeClick(selectDate, "Cheque value");
    }

    public void selectMemoReturnDate() {
        logger.info("📅 Selecting Memo Return Date");
        safe.safeClick(memoReturnDate, "Memo Return calendar field");
        safe.safeClick(selectDate, "Memo Return value");
    }

    @FindBy(xpath = "//a[@id='crossSaleNew']")
    public WebElement crossSaleNew;

    @FindBy(xpath = "//li[contains(@class, 'select2-results__option') and contains(text(), 'Cheque Bounce Notice')]")
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
