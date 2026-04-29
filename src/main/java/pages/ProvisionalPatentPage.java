package pages;

import base.BasePage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import utils.SafeActionUtils;
import utils.WaitUtils;

public class ProvisionalPatentPage extends BasePage {

    private final WaitUtils wait;
    SafeActionUtils safe;

    public ProvisionalPatentPage(WebDriver driver) {
        super(driver);
        this.wait = new WaitUtils(driver, 15);
        this.safe = new SafeActionUtils(driver);
    }
//Tab 1
    @FindBy(xpath = "//input[@id='react-select-3-input']")
    private WebElement enterApplicantType;

    @FindBy(xpath = "//input[@name='applicant_name']")
    private WebElement enterApplicantName;

    @FindBy(xpath = "//input[@name='slogan']")
    private WebElement enterApplicantGender;

    @FindBy(xpath = "//input[@placeholder='Enter Applicant Complete Address']")
    private WebElement enterApplicantAddress;

    @FindBy(xpath = "//input[@placeholder='Enter Applicant Contact Number']")
    private WebElement enterApplicantContactNumber;

    @FindBy(xpath = "//input[@placeholder='Enter Applicant Email Id']")
    private WebElement enterApplicantEmailId;

    @FindBy(xpath = "(//p[text()='Next'])[2]")
    private WebElement clickNextButton;
//Tab 2
    @FindBy(xpath = "//input[@placeholder='Enter No of Inventor']")
    private WebElement enterNoOfInventor;
//Tab 3
    @FindBy(xpath = "//input[@id='react-select-4-input']")
    private WebElement enterApplicantNature;

    @FindBy(xpath = "//input[@placeholder='Enter Inventor Name']")
    private WebElement enterInventorName;

    @FindBy(xpath = "//input[@placeholder='Enter Inventor Address']")
    private WebElement enterInventorAddress;

    @FindBy(xpath = "//input[@placeholder='Enter Inventor Email Id']")
    private WebElement enterInventorEmailId;

    @FindBy(xpath = "//input[@placeholder='Enter Inventor Contact number']")
    private WebElement enterInventorContactNumber;

    @FindBy(xpath = "//input[@placeholder='Enter Gender of the inventor']")
    private WebElement enterInventorGender;
//Tab 4
    @FindBy(xpath = "//input[@id='react-select-5-input']")
    private WebElement enterPatentApplicantType;
//Tab 5
    @FindBy(xpath = "//input[@placeholder='Enter Title of the invention']")
    private WebElement enterInventionTitle;

    @FindBy(xpath = "//input[@placeholder='Enter Brief Description of the Invention ']")
    private WebElement enterBriefDescription;

    @FindBy(xpath = "//input[@placeholder='Enter How did you come up with this invention']")
    private WebElement enterInventionIdea;

    @FindBy(xpath = "//input[@placeholder='Enter Novelty/Newness (2-3 lines)']")
    private WebElement enterNoveltyNewness;

    @FindBy(xpath = "//input[@placeholder='Enter Detailed description of the invention (minimum 4 pages)']")
    private WebElement enterInventionDescription;

    @FindBy(xpath = "//input[@id='react-select-6-input']")
    private WebElement enterFiguresDrawing;

    @FindBy(xpath = "//input[@placeholder='Enter Advantages of your invention']")
    private WebElement enterInventionAdvantage;

    @FindBy(xpath = "//input[@placeholder='Enter How your invention is different from others?']")
    private WebElement enterHowInventionDiffer;

    @FindBy(xpath = "//input[@placeholder='Enter Essential feature or components']")
    private WebElement enterEssentialFeature;

    @FindBy(xpath = "//input[@placeholder='Enter Prior art search']")
    private WebElement enterPriorArt;

    @FindBy(xpath = "//input[@placeholder='Enter Status of your invention']")
    private WebElement enterInventionStatus;
//Tab 6
    @FindBy(xpath = "//input[@id='react-select-7-input']")
    private WebElement enterSignatureAttached;

    public void typeApplicantType(String applicantType) {
        safe.safeType(enterApplicantType, applicantType, "Applicant Type");
    }

    public void typeApplicantName(String applicantName) {
        safe.safeType(enterApplicantName, applicantName, "Applicant Name");
    }

    public void typeApplicantGender(String applicantGender) {
        safe.safeType(enterApplicantGender, applicantGender, "Applicant Gender");
    }

    public void     typeApplicantAddress(String applicantAddress) {
        safe.safeType(enterApplicantAddress, applicantAddress, "Applicant Complete Address");
    }

    public void typeApplicantContactNumber(String contactNumber) {
        safe.safeType(enterApplicantContactNumber, contactNumber, "Applicant Contact Number");
    }

    public void typeApplicantEmailId(String emailId) {
        safe.safeType(enterApplicantEmailId, emailId, "Applicant Email Id");
    }

    public void typeNoOfInventor(String noOfInventor) {
        safe.safeType(enterNoOfInventor, noOfInventor, "Number of Inventors");
    }

    public void typeApplicantNature(String applicantNature) {
        safe.safeType(enterApplicantNature, applicantNature, "Applicant Nature");
    }

    public void typeInventorName(String inventorName) {
        safe.safeType(enterInventorName, inventorName, "Inventor Name");
    }

    public void typeInventorAddress(String inventorAddress) {
        safe.safeType(enterInventorAddress, inventorAddress, "Inventor Address");
    }

    public void typeInventorEmailId(String inventorEmailId) {
        safe.safeType(enterInventorEmailId, inventorEmailId, "Inventor Email Id");
    }

    public void typeInventorContactNumber(String inventorContactNumber) {
        safe.safeType(enterInventorContactNumber, inventorContactNumber, "Inventor Contact Number");
    }

    public void typeInventorGender(String inventorGender) {
        safe.safeType(enterInventorGender, inventorGender, "Inventor Gender");
    }

    public void typePatentApplicantType(String patentApplicantType) {
        safe.safeType(enterPatentApplicantType, patentApplicantType, "Patent Applicant Type");
    }

    public void typeInventionTitle(String inventionTitle) {
        safe.safeType(enterInventionTitle, inventionTitle, "Title of the Invention");
    }

    public void typeBriefDescription(String briefDescription) {
        safe.safeType(enterBriefDescription, briefDescription, "Brief Description of the Invention");
    }

    public void typeInventionIdea(String inventionIdea) {
        safe.safeType(enterInventionIdea, inventionIdea, "Idea Behind the Invention");
    }

    public void typeNoveltyNewness(String noveltyNewness) {
        safe.safeType(enterNoveltyNewness, noveltyNewness, "Novelty/Newness");
    }

    public void typeInventionDescription(String inventionDescription) {
        safe.safeType(enterInventionDescription, inventionDescription, "Detailed Description of the Invention");
    }

    public void typeFiguresDrawing(String figuresDrawing) {
        safe.safeType(enterFiguresDrawing, figuresDrawing, "Figures or Drawings Attached");
    }

    public void typeInventionAdvantage(String inventionAdvantage) {
        safe.safeType(enterInventionAdvantage, inventionAdvantage, "Advantages of the Invention");
    }

    public void typeHowInventionDiffer(String howInventionDiffer) {
        safe.safeType(enterHowInventionDiffer, howInventionDiffer, "How the Invention Differs from Others");
    }

    public void typeEssentialFeature(String essentialFeature) {
        safe.safeType(enterEssentialFeature, essentialFeature, "Essential Features or Components");
    }

    public void typePriorArt(String priorArt) {
        safe.safeType(enterPriorArt, priorArt, "Prior Art Search");
    }

    public void typeInventionStatus(String inventionStatus) {
        safe.safeType(enterInventionStatus, inventionStatus, "Status of the Invention");
    }

    public void typeSignatureAttached(String signatureAttached) {
        safe.safeType(enterSignatureAttached, signatureAttached, "Signature Attached");
    }

    public void clickNextCTA(){
        safe.safeClick(clickNextButton, "Next");
    }

    @FindBy(xpath = "//a[@id='crossSaleNew']")
    public WebElement crossSaleNew;


    @FindBy(xpath = "//li[contains(@class, 'select2-results__option') and contains(text(), 'Apply for a Provisional Patent')]")
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
