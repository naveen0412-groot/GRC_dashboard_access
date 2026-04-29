package pages;

import base.BasePage;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import utils.SafeActionUtils;
import utils.WaitUtils;

import java.awt.*;

import static utils.AllureLoggerUtils.logToAllure;


public class Firearmpage extends BasePage {

    private final WaitUtils wait;
    SafeActionUtils safe;

    public Firearmpage(WebDriver driver) throws AWTException {
        super(driver);
        this.driver = driver;
        this.wait = new WaitUtils(driver, 15);
        this.safe = new SafeActionUtils(driver);
    }

    @FindBy(xpath = "//input[@placeholder='Enter Applicant Name']")
    public WebElement applicantName;

    @FindBy(xpath = "//input[@placeholder='Enter Father Name']")
    public WebElement fatherName;

    @FindBy(xpath = "//input[@placeholder='Enter Educational Qualification']")
    public WebElement educationalQual;

    @FindBy(xpath = "//input[@placeholder='Enter Address']")
    public WebElement address;

    @FindBy(xpath = "//input[@placeholder='Enter Mobile Number ']")
    public WebElement mobileNumber;

    @FindBy(xpath = "//input[@placeholder='Enter Email ID ']")
    public WebElement emailID1;

    @FindBy(xpath = "//input[@placeholder='Enter Purpose']")
    public WebElement purpose;


    @FindBy(xpath = "//button[@class='font-medium text-[14px] rounded-[4px] flex items-center gap-[5px] p-[7px] px-[10px] relative overflow-hidden bg-[#022B50] border-[1px] border-[#022B50] text-white w-full justify-center event-track cursor-pointer hover:bg-opacity-90']")
    public WebElement authButton;

    @FindBy(xpath = "//button[@class='font-medium text-[14px] rounded-[4px] flex items-center gap-[5px] p-[7px] px-[10px] relative overflow-hidden bg-[#022B50] border-[1px] border-[#022B50] text-white w-full justify-center py-[1.2rem] event-track cursor-pointer hover:bg-opacity-90']")
    public WebElement uploadbutton;

    @FindBy(xpath = "//p[text()=' applicant aadhar card']/parent::div/parent::div/parent::div/div[2]/input")
    public WebElement applicantaadharcard;

    @FindBy(xpath = "//p[text()='Mobile bill']/parent::div/parent::div/parent::div/div[2]/input")
    public WebElement mobilebill;

    @FindBy(xpath = "//input[@id='dob']")
    private WebElement clickDateField;

    @FindBy(xpath = "(//div[@class='react-datepicker__week']/div[text()='1'])[1]")
    private WebElement selectDate;

    public void selectDate() {
        safe.safeClick(clickDateField, "DOB calendar field");
        safe.safeClick(selectDate, "DOB value");
    }


    public void authButton() {

        safe.safeClick(authButton, "Authorised person next Button");
    }

    public void uploadbutton() {

    }



    public void emailID1(String DirectorEmail1) {

        safe.safeType(emailID1, DirectorEmail1, "Company email ID");
    }

    public void mobileNumber(String DirectorMobile1) {

        safe.safeType(mobileNumber, DirectorMobile1, "Mobile number");
    }

    public void address(String Address1) {
        safe.safeType(address, Address1, "address");
    }

    public void applicantName(String directorName1) {

        safe.safeType(applicantName, directorName1, "Authorized Name");
    }

    public void fatherName(String FatherName1) {
        safe.safeType(fatherName, FatherName1, "Father name");
    }

    public void educationalQual(String EducationalQualification1) {

        safe.safeType(educationalQual, EducationalQualification1, "Educational Qualification");
    }

    public void purpose(String Purposeofreg) {

        safe.safeType(purpose, Purposeofreg, "Purpose");
    }
    @FindBy(xpath = "//a[@id='crossSaleNew']")
    public WebElement crossSaleNew;

    @FindBy(xpath = "//li[contains(@class, 'select2-results__option') and contains(text(), 'Firearm/Gun license')]")
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

