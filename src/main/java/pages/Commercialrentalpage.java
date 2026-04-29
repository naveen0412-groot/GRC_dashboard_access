package pages;

import base.BasePage;
import org.openqa.selenium.*;
import org.openqa.selenium.support.FindBy;

import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import utils.SafeActionUtils;
import utils.WaitUtils;

import java.awt.*;
import java.time.Duration;


public class Commercialrentalpage extends BasePage {

    private final WaitUtils wait;
    SafeActionUtils safe;

    public Commercialrentalpage(WebDriver driver) throws AWTException {
        super(driver);
        this.driver = driver;
        this.wait = new WaitUtils(driver, 15);
        this.safe = new SafeActionUtils(driver);
    }

    @FindBy(xpath = "//input[@placeholder='Enter Commencement Date']")
    public WebElement commencementDate;

    @FindBy(xpath = "//input[@placeholder='Enter Lease Duration']")
    public WebElement leaseDuration;

    @FindBy(xpath = "//input[@placeholder='Enter Lock-in Period (if any)']")
    public WebElement lockInPeriod;

    @FindBy(xpath = "//input[@placeholder='Enter Effective Date for Rent Commencement (if different from possession date)']")
    public WebElement rentCommencementDate;

    @FindBy(xpath = "//input[@placeholder='Enter Option to Renew (Yes/No; specify renewal term and rent escalation)']")
    public WebElement optionToRenew;

    @FindBy(xpath = "//button[@class='font-medium text-[14px] rounded-[4px] flex items-center gap-[5px] p-[7px] px-[10px] relative overflow-hidden bg-[#022B50] border-[1px] border-[#022B50] text-white w-full justify-center event-track cursor-pointer hover:bg-opacity-90']")
    public WebElement closeButton;

    @FindBy(xpath = "//button[@class='font-medium text-[14px] rounded-[4px] flex items-center gap-[5px] p-[7px] px-[10px] relative overflow-hidden bg-[#022B50] border-[1px] border-[#022B50] text-white w-full justify-center event-track cursor-pointer hover:bg-opacity-90']")
    public WebElement basicpage1button;

    @FindBy(xpath = "//button[@class='font-medium text-[14px] rounded-[4px] flex items-center gap-[5px] p-[7px] px-[10px] relative overflow-hidden bg-[#022B50] border-[1px] border-[#022B50] text-white w-full justify-center py-[1.2rem] event-track cursor-pointer hover:bg-opacity-90']")
    public WebElement basicpage2button;

    @FindBy(xpath = "//button[@class='font-medium text-[14px] rounded-[4px] flex items-center gap-[5px] p-[7px] px-[10px] relative overflow-hidden bg-[#022B50] border-[1px] border-[#022B50] text-white w-full justify-center py-[1.2rem] event-track cursor-pointer hover:bg-opacity-90']")
    public WebElement rentandpaymentbutton;

    @FindBy(xpath = "//button[@class='font-medium text-[14px] rounded-[4px] flex items-center gap-[5px] p-[7px] px-[10px] relative overflow-hidden bg-[#022B50] border-[1px] border-[#022B50] text-white w-full justify-center py-[1.2rem] event-track cursor-pointer hover:bg-opacity-90']")
    public WebElement securitydepositbutton;
    @FindBy(xpath = "//button[@class='font-medium text-[14px] rounded-[4px] flex items-center gap-[5px] p-[7px] px-[10px] relative overflow-hidden bg-[#022B50] border-[1px] border-[#022B50] text-white w-full justify-center py-[1.2rem] event-track cursor-pointer hover:bg-opacity-90']")
    public WebElement Possessionbutton;


    @FindBy(xpath = "//input[@placeholder='Enter Name of the Lessor / Landlord']")
    public WebElement lessorName;
    @FindBy(xpath = "//input[@placeholder='Enter Address of the Lessor']")
    public WebElement addressoflessor;
    @FindBy(xpath = "//input[@placeholder='Enter Name of the Lessee / Tenant']")
    public WebElement nameoflessee;
    @FindBy(xpath = "//input[@placeholder='Enter Address of the Lessee']")
    public WebElement addressoflessee;
    @FindBy(xpath = "//div[@id='react-select-3-placeholder']")
    public WebElement natureofentity;
    @FindBy(xpath = "//input[@placeholder='Enter Name of the Authorized Signatory']")
    public WebElement authorizedSignatory;
    @FindBy(xpath = "//input[@placeholder='Enter Contact Details - Email']")
    public WebElement contactDetailsEmail;
    @FindBy(xpath = "//input[@placeholder='Enter Contact Details - Phone']")
    public WebElement contactDetailsPhone;
    @FindBy(xpath = "//input[@placeholder='Enter Nature of Business of Lessee']")
    public WebElement natureOfBusinessOfLessee;

    @FindBy(xpath = "//input[@placeholder='Enter Purpose for which Premises will be used']")
    public WebElement purposeForWhichPremisesWillBeUsed;

    @FindBy(xpath = "//div[@id='react-select-4-placeholder']")
    public WebElement typeofproperty;


    @FindBy(xpath = "//input[@placeholder='Enter Monthly Rent (in INR)']")
    public WebElement monthlyRent;
    @FindBy(xpath = "//input[@placeholder='Enter Rent per sq. ft. (if applicable)']")
    public WebElement rentPerSq;
    @FindBy(xpath = "//input[@placeholder='Enter Mode of Payment (Bank Transfer / Cheque)']")
    public WebElement modeOfPayment;
    @FindBy(xpath = "//input[@placeholder='Enter Due Date for Payment (e.g. on or before 5th of each month)']")
    public WebElement dateofPayment;
    @FindBy(xpath = "//input[@placeholder='Enter Grace Period (if any)']")
    public WebElement gracePeriod;
    @FindBy(xpath = "//input[@placeholder='Enter Interest / Penalty for Delay (specify rate per month or per annum)']")
    public WebElement penaltyofInterest;
    @FindBy(xpath = "//input[@placeholder='Enter Rent Escalation (percentage and frequency, e.g. 5% every year)']")
    public WebElement rentEscalation;
    @FindBy(xpath = "//input[@placeholder='Enter Any Rent-Free or Fit-Out Period (Yes/No; specify duration)']")
    public WebElement rentFreeOrNo;


    @FindBy(xpath = "//input[@placeholder='Enter Complete Address of the Premises']")
    public WebElement addressofPremises;
    @FindBy(xpath = "//input[@placeholder='Enter Total Built-up Area']")
    public WebElement totalBuiltupArea;
    @FindBy(xpath = "//input[@placeholder='Enter Carpet Area (if applicable)']")
    public WebElement carpetArea;
    @FindBy(xpath = "//input[@placeholder='Enter Floor Number(s) / Unit Number(s)']")
    public WebElement floorNumber;
    @FindBy(xpath = "//input[@placeholder='Enter Is parking included? If yes, specify number and type (Car / Two-wheeler)']")
    public WebElement parkingincluded;
    @FindBy(xpath = "//input[@placeholder='Enter Is the Premise furnished / semi-furnished / bare shell?']")
    public WebElement premisefurnished;
    @FindBy(xpath = "//input[@placeholder='Enter If furnished, provide inventory details']")
    public WebElement inventorydetails;


    @FindBy(xpath = "//input[@placeholder='Enter Amount of Security Deposit']")
    public WebElement securityDeposit;
    @FindBy(xpath = "//input[@placeholder='Enter Is the deposit interest-free?']")
    public WebElement depositinterest;
    @FindBy(xpath = "//input[@placeholder='Enter Mode of Payment']")
    public WebElement modeofpayment;
    @FindBy(xpath = "//input[@placeholder='Enter Is the deposit refundable? (Yes/No)']")
    public WebElement depositrefundable;
    @FindBy(xpath = "//input[@placeholder='Enter Conditions for refund (on expiry, after deductions, etc.)']")
    public WebElement conditionsforrefund;
    @FindBy(xpath = "//input[@placeholder='Enter Can the deposit be adjusted against rent?']")
    public WebElement depositcanthead;

    @FindBy(xpath = "//input[@placeholder='Enter Date of Handing Over Possession']")
    public WebElement handingoverpossession;
    @FindBy(xpath = "//input[@placeholder='Enter Is the Lessor responsible for completing any works before handover?']")
    public WebElement lessorresponsible;
    @FindBy(xpath = "//input[@placeholder='Enter Fit-out Period allowed to Lessee (in days/weeks)']")
    public WebElement fitoutperiodallowed;
    @FindBy(xpath = "//input[@placeholder='Enter Who bears cost of fit-out or interior work?']")
    public WebElement bearscostoffitout;
    @FindBy(xpath = "//input[@placeholder='Enter Does Lessee require Lessor’s approval for interior modification?']")
    public WebElement lessorapproval;


    public void commencementDate(String companytype) {
        safe.safeType(commencementDate, companytype, "Commencement Date");
    }

    public void leaseDuration(String searchbus) {
        safe.safeType(leaseDuration, searchbus, "Lease Duration");
    }

    public void lockInPeriod(String Roadstreet) {
        safe.safeType(lockInPeriod, Roadstreet, "Lock In Period");
    }

    public void rentCommencementDate(String shareholderfathername2) {
        safe.safeType(rentCommencementDate, shareholderfathername2, "Rent Commence Date");
    }

    public void optionToRenew(String shareholderfathername) {
        safe.safeType(optionToRenew, shareholderfathername, "Option To Renew");
    }


    public void nextbuttonnt() {
        safe.safeClick(closeButton, "Next Button");
    }

    public void basicpage1button() {
        safe.safeClick(basicpage1button, "Basic page 1 Button");
    }

    public void rentandpaymentbutton() {
        safe.safeClick(rentandpaymentbutton, "Rent and pay Button");
    }

    public void Possessionbutton() {
        safe.safeClick(Possessionbutton, "Possession Button");
    }


    public void lessorName(String directorName1) {
        safe.safeType(lessorName, directorName1, "Name of lessor");
    }

    public void nameoflessee(String directorName2) {
        safe.safeType(nameoflessee, directorName2, "Name of Lessee");
    }

    public void addressoflessor(String Address1) {
        safe.safeType(addressoflessor, Address1, "Address of lessor");
    }

    public void addressoflessee(String Address2) {
        safe.safeType(addressoflessee, Address2, "Address of  Lessee");
    }

    public void natureofentity(String complaintvalue) {

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        try {
            JavascriptExecutor js = (JavascriptExecutor) driver;

            WebElement input = wait.until(ExpectedConditions.presenceOfElementLocated(By.id("react-select-3-input")));

            js.executeScript("arguments[0].scrollIntoView({block:'center'});", input);

            js.executeScript("arguments[0].focus();", input);

            input.sendKeys(complaintvalue);

            input.sendKeys(Keys.ARROW_DOWN);
            input.sendKeys(Keys.ENTER);

            logger.info("✔ Nature of entity selected → " + complaintvalue);

        } catch (Exception e) {
            throw new RuntimeException("❌ Unable to Nature of entity → " + complaintvalue, e);
        }
    }


    public void authorizedSignatory(String AuthorizedSignatoryName) {
        safe.safeType(authorizedSignatory, AuthorizedSignatoryName, "Option To Renew");
    }

    public void contactDetailsEmail(String companyemail) {
        safe.safeType(contactDetailsEmail, companyemail, "Email");
    }

    public void contactDetailsPhone(String companymobile) {
        safe.safeType(contactDetailsPhone, companymobile, "Mobile number");
    }

    public void natureOfBusinessOfLessee(String naturebusiness) {
        safe.safeType(natureOfBusinessOfLessee, naturebusiness, "Nature of business");
    }

    public void purposeForWhichPremisesWillBeUsed(String Roadstreet) {
        safe.safeType(purposeForWhichPremisesWillBeUsed, Roadstreet, "Option To Renew");
    }

    public void typeofproperty(String typeofproperty) {

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        try {
            JavascriptExecutor js = (JavascriptExecutor) driver;

            WebElement input = wait.until(ExpectedConditions.presenceOfElementLocated(By.id("react-select-4-input")));

            js.executeScript("arguments[0].scrollIntoView({block:'center'});", input);

            js.executeScript("arguments[0].focus();", input);

            input.sendKeys(typeofproperty);

            input.sendKeys(Keys.ARROW_DOWN);
            input.sendKeys(Keys.ENTER);

            logger.info("✔ Nature of entity selected → " + typeofproperty);

        } catch (Exception e) {
            throw new RuntimeException("❌ Unable to Nature of entity → " + typeofproperty, e);
        }
    }

    public void addressofPremises(String InventorAddress) {
        safe.safeType(addressofPremises, InventorAddress, "Address of Premises");
    }

    public void totalBuiltupArea(String WorkTitle) {
        safe.safeType(totalBuiltupArea, WorkTitle, "Built up area");
    }

    public void carpetArea(String WorkNature) {
        safe.safeType(carpetArea, WorkNature, "Carpet area");
    }

    public void floorNumber(String ApplicantAddress) {
        safe.safeType(floorNumber, ApplicantAddress, "Floor Number");
    }

    public void parkingincluded(String LLPName) {
        safe.safeType(parkingincluded, LLPName, "Parking Included");
    }

    public void premisefurnished(String ApplicantType) {
        safe.safeType(premisefurnished, ApplicantType, "Premise furnished");
    }

    public void inventorydetails(String InventionTitle) {
        safe.safeType(inventorydetails, InventionTitle, "Inventory details");
    }

    public void basicpage2button() {
        safe.safeClick(basicpage2button, "Basic page 2 Button");
    }

    public void monthlyRent(String FatherName1) {
        safe.safeType(monthlyRent, FatherName1, "Monthly Rent");

    }

    public void rentPerSq(String EducationalQualification1) {
        safe.safeType(rentPerSq, EducationalQualification1, "Rent Per Sq");
    }

    public void modeOfPayment(String Pan1) {
        safe.safeType(modeOfPayment, Pan1, "Mode of Payment");
    }

    public void dateofPayment(String Aadhar1) {
        safe.safeType(dateofPayment, Aadhar1, "Date of Payment");
    }

    public void gracePeriod(String EducationalQualification2) {
        safe.safeType(gracePeriod, EducationalQualification2, "Grace Period");
    }

    public void penaltyofInterest(String AreaOfOccupation) {
        safe.safeType(penaltyofInterest, AreaOfOccupation, "Penalty of Interest");
    }

    public void rentEscalation(String PoliceStation) {
        safe.safeType(rentEscalation, PoliceStation, "Rent Escalation");
    }

    public void rentFreeOrNo(String ReasonForResignation) {
        safe.safeType(rentFreeOrNo, ReasonForResignation, "Rent Free or No");
    }

    public void securitydepositbutton() {
        safe.safeClick(securitydepositbutton, "Security Button");
    }

    public void securityDeposit(String ApplicantName) {
        safe.safeType(securityDeposit, ApplicantName, "Security deposit");
    }

    public void depositinterest(String InventorAddress) {
        safe.safeType(depositinterest, InventorAddress, "Deposit interest");
    }

    public void modeofpayment(String PatentType) {
        safe.safeType(modeofpayment, PatentType, "Patent Type");
    }

    public void depositrefundable(String WorkTitle) {
        safe.safeType(depositrefundable, WorkTitle, "Work title");
    }

    public void conditionsforrefund(String WorkNature) {
        safe.safeType(conditionsforrefund, WorkNature, "Work Nature");
    }

    public void depositcanthead(String LLPName) {
        safe.safeType(depositcanthead, LLPName, "Deposit  can head");
    }


    public void handingoverpossession(String OldName) {

        safe.safeType(handingoverpossession, OldName, "handover possession");
    }

    public void lessorresponsible(String NewName) {
        safe.safeType(lessorresponsible, NewName, "lessor responsible");
    }

    public void fitoutperiodallowed(String Reason) {
        safe.safeType(fitoutperiodallowed, Reason, "Fitout period allowed");
    }

    public void bearscostoffitout(String DocumentName) {
        safe.safeType(bearscostoffitout, DocumentName, "bears cost offitout");
    }

    public void lessorapproval(String InventionIdea) {
        safe.safeType(lessorapproval, InventionIdea, "lessor approval");
    }

    @FindBy(xpath = "//a[@id='crossSaleNew']")
    public WebElement crossSaleNew;

    @FindBy(xpath = "//li[contains(@class, 'select2-results__option') and contains(text(), 'Commercial Rental / Lease Agreement')]")
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
