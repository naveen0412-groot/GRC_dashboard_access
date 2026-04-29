package pages;

import base.BasePage;
import org.openqa.selenium.*;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import utils.SafeActionUtils;
import utils.WaitUtils;

import java.awt.*;


public class Moupage extends BasePage {

    private final WaitUtils wait;
    SafeActionUtils safe;

    public Moupage(WebDriver driver) throws AWTException {
        super(driver);
        this.driver = driver;
        this.wait = new WaitUtils(driver, 15);
        this.safe = new SafeActionUtils(driver);
    }

    @FindBy(xpath = "//input[@placeholder='Enter Name of Party 1']")
    public WebElement nameofParty1;

    @FindBy(xpath = "//input[@placeholder='Enter Registered Address']")
    public WebElement registeredAddress;

    @FindBy(xpath = "//input[@placeholder='Enter Authorized Signatory (Name & Designation)']")
    public WebElement authorizedSignatory;

    @FindBy(xpath = "//input[@placeholder='Enter Nature of Entity (Company / LLP / NGO / Proprietorship / Individual)']")
    public WebElement natureOfEntity;

    @FindBy(xpath = "//input[@placeholder='Enter Brief Description of Business Activity']")
    public WebElement businessActivity;

    @FindBy(xpath = "//input[@placeholder='Enter Name of Party 2']")
    public WebElement nameofParty2;

    @FindBy(xpath = "//input[@name='company_address']")
    public WebElement registeredaddress2;

    @FindBy(xpath = "//input[@name='company_name']")
    public WebElement AuthorizedSignatory2;

    @FindBy(xpath = "//input[@name='director_addressLine2']")
    public WebElement natureofEntity2;

    @FindBy(xpath = "//input[@name='father_first_name']")
    public WebElement briefDescription2;

    @FindBy(xpath = "//button[@class='font-medium text-[14px] rounded-[4px] flex items-center gap-[5px] p-[7px] px-[10px] relative overflow-hidden bg-[#022B50] border-[1px] border-[#022B50] text-white w-full justify-center event-track cursor-pointer hover:bg-opacity-90']")
    public WebElement closeButton;

    @FindBy(xpath = "//input[@placeholder='Enter What is the main purpose or objective of this MoU?']")
    public WebElement objective;

    @FindBy(xpath = "//input[@placeholder='Enter Is this MoU for collaboration, partnership, pilot project, or joint venture?']")
    public WebElement mouforcollaboration;

    @FindBy(xpath = "//input[@placeholder='Enter Is the MoU binding or non-binding (except for specific clauses like confidentiality)?']")
    public WebElement mouforbinding;

    @FindBy(xpath = "//input[@placeholder='Enter Any prior relationship or engagement between the parties?']")
    public WebElement relationship;

    @FindBy(xpath = "//input[@placeholder='Enter What is the intended outcome or deliverable under this MoU?']")
    public WebElement intendedoutcome;

    @FindBy(xpath = "//input[@placeholder='Enter What activities will each party undertake under the MoU?']")
    public WebElement partyundertake;

    @FindBy(xpath = "//input[@placeholder='Enter What are the defined roles and responsibilities of each party?']")
    public WebElement rolesandresponsibility;

    @FindBy(xpath = "//input[@placeholder='Enter Are there any specific deliverables, milestones, or timelines?']")
    public WebElement timelines;

    @FindBy(xpath = "//input[@placeholder='Enter Are there any financial contributions from either party?']")
    public WebElement financialcontributions;

    @FindBy(xpath = "//input[@placeholder='Enter If yes, specify amount and purpose']")
    public WebElement specifyamountandpurpose;

    @FindBy(xpath = "//input[@placeholder='Enter Is there a revenue-sharing or cost-sharing arrangement?']")
    public WebElement revenuesharing;

    @FindBy(xpath = "//input[@placeholder='Enter Are there any reimbursements or expense-sharing terms?']")
    public WebElement reimbursements;

    @FindBy(xpath = "//input[@placeholder='Enter Will any invoices or formal billing be involved?']")
    public WebElement anyinvoices;

    @FindBy(xpath = "//input[@placeholder='Enter Effective Date of the MoU']")
    public WebElement effectivedate;

    @FindBy(xpath = "//input[@placeholder='Enter Duration / Validity Period']")
    public WebElement durationvalidity;

    @FindBy(xpath = "//input[@placeholder='Enter Is renewal possible? (Automatic / By mutual consent)']")
    public WebElement renewalpossible;

    @FindBy(xpath = "//input[@placeholder='Enter Can either party terminate the MoU? If yes, specify notice period']")
    public WebElement partyterminate;


    public void nameofParty1(String directorName2) {
        safe.safeType(nameofParty1, directorName2, "Name of party 1");
    }

    public void registeredAddress(String Address1) {
        safe.safeType(registeredAddress, Address1, "Registered Address");
    }
    public void registeredaddress2(String Address2) {
        safe.safeType(registeredaddress2, Address2, "registered address 2");
    }
    public void authorizedSignatory(String AuthorizedSignatoryName) {
        safe.safeType(authorizedSignatory, AuthorizedSignatoryName, "authorized signatory");
    }
    public void natureOfEntity(String naturebusiness) {
        safe.safeType(natureOfEntity, naturebusiness, "nature of entity");
    }
    public void businessActivity(String DescribeBusiness) {
        safe.safeType(businessActivity, DescribeBusiness, "business activity");
    }
    public void nameofParty2(String directorName1) {
        safe.safeType(nameofParty2, directorName1, "name of party 2");
    }
    public void AuthorizedSignatory2(String AuthorizedSignatoryDesignation) {
        safe.safeType(AuthorizedSignatory2, AuthorizedSignatoryDesignation, "authorized signatory 2");
    }
    public void natureofEntity2(String WorkNature) {
        safe.safeType(natureofEntity2, WorkNature, "nature of entity");
    }

    public void briefDescription2(String WorkStatus) {
        safe.safeType(briefDescription2, WorkStatus, "brief description");
    }

    public void nextbuttonnt() {
        safe.safeClick(closeButton, "Next Button");
    }


    public void closeButton() {
        safe.safeClick(closeButton, "Next Button");
    }

    public void objective(String AddObjective) {
        safe.safeType(objective, AddObjective , "Objective");
    }

    public void mouforcollaboration(String WorkNature) {
        safe.safeType(mouforcollaboration, WorkNature , "Mou Collaboration");
    }

    public void mouforbinding(String OldName) {
        safe.safeType(mouforbinding, OldName , "Mou Binding");
    }

    public void relationship(String Reason) {
        safe.safeType(relationship, Reason , "Relationship");
    }

    public void intendedoutcome(String ApplicantType) {
        safe.safeType(intendedoutcome, ApplicantType , "individual outcome");
    }

    public void partyundertake(String currentOccupation1) {
        safe.safeType(partyundertake, currentOccupation1 , "party undertake");
    }

    public void rolesandresponsibility(String role) {
        safe.safeType(rolesandresponsibility, role , "roles and responsibility");
    }

    public void timelines(String pageLoadTimeout) {safe.safeType(timelines, pageLoadTimeout , "timelines");

    }

    public void financialcontributions(String currentOccupation1) {

        safe.safeType(financialcontributions, currentOccupation1 , "financial");
    }

    public void specifyamountandpurpose(String DebtAmount) {
        safe.safeType(specifyamountandpurpose, DebtAmount , "specify amount");
    }

    public void revenuesharing(String ChequeNumber) {
        safe.safeType(revenuesharing, ChequeNumber , "revenuesharing");
    }

    public void reimbursements(String BranchName) {
        safe.safeType(reimbursements, BranchName , "Reimbursements");
    }

    public void anyinvoices(String AnotherWork) {
        safe.safeType(anyinvoices, AnotherWork , "Any Invoices");
    }

    public void effectivedate(String rocUpToDate) {
        safe.safeType(effectivedate, rocUpToDate , "Effective date");
    }

    public void durationvalidity(String durationYears) {
        safe.safeType(durationvalidity, durationYears , "Duration validity");
    }

    public void renewalpossible(String currentUse) {
        safe.safeType(renewalpossible, currentUse , "Renewal possible");
    }

    public void partyterminate(String zipCode) {
        safe.safeType(partyterminate, zipCode , "Party Terminate");
    }
    @FindBy(xpath = "//a[@id='crossSaleNew']")
    public WebElement crossSaleNew;

    @FindBy(xpath = "//li[contains(@class, 'select2-results__option') and contains(text(), 'Memorandum of Understanding (MoU)')]")
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

