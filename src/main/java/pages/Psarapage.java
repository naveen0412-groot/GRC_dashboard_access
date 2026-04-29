package pages;

import base.BasePage;
import org.openqa.selenium.*;
import org.openqa.selenium.support.FindBy;
import utils.SafeActionUtils;
import utils.WaitUtils;

import java.awt.*;


public class Psarapage extends BasePage {

    private final WaitUtils wait;
    SafeActionUtils safe;

    public Psarapage(WebDriver driver) throws AWTException {
        super(driver);
        this.driver = driver;
        this.wait = new WaitUtils(driver, 15);
        this.safe = new SafeActionUtils(driver);
    }

    @FindBy(xpath = "//input[@placeholder='Enter Company Name ']")
    public WebElement companyName;

    @FindBy(xpath = "//input[@placeholder='Enter Email ID ']")
    public WebElement emailID;

    @FindBy(xpath = "//input[@placeholder='Enter Mobile Number ']")
    public WebElement mobileNumber;

    @FindBy(xpath = "//input[@placeholder='Enter Address']")
    public WebElement address;

    @FindBy(xpath = "//input[@placeholder='Enter No of Employees']")
    public WebElement noofEmployees;


    @FindBy(xpath = "//input[@placeholder='Enter Name']")
    public WebElement name;

    @FindBy(xpath = "//input[@placeholder='Enter Mobile Number ']")
    public WebElement authmobileno;

    @FindBy(xpath = "//input[@placeholder='Date of Birth ']")
    public WebElement Dob;

    @FindBy(xpath = "//input[@placeholder='Enter Email ID ']")
    public WebElement authemail;

    @FindBy(xpath = "//input[@placeholder='Enter Address']")
    public WebElement authAddress;

    @FindBy(xpath = "//p[text()='Rental agreement']/parent::div/parent::div/parent::div/div[2]/input")
    public WebElement rentalagreement;

    @FindBy(xpath = "//p[text()='Electricity bill']/parent::div/parent::div/parent::div/div[2]/input")
    public WebElement Electricitybill;

    @FindBy(xpath = "//p[text()='Incorporation certificate']/parent::div/parent::div/parent::div/div[2]/input")
    public WebElement incorporationcertificate;

    @FindBy(xpath = "//p[text()='GST certificate']/parent::div/parent::div/parent::div/div[2]/input")
    public WebElement Gstcertificate;

    @FindBy(xpath = "//p[text()='Company employee details']/parent::div/parent::div/parent::div/div[2]/input")
    public WebElement employeecertificate;



    @FindBy(xpath = "//button[@class='font-medium text-[14px] rounded-[4px] flex items-center gap-[5px] p-[7px] px-[10px] relative overflow-hidden bg-[#022B50] border-[1px] border-[#022B50] text-white w-full justify-center event-track cursor-pointer hover:bg-opacity-90']")
    public WebElement companybutton;

    @FindBy(xpath = "//button[@class='font-medium text-[14px] rounded-[4px] flex items-center gap-[5px] p-[7px] px-[10px] relative overflow-hidden bg-[#022B50] border-[1px] border-[#022B50] text-white w-full justify-center event-track cursor-pointer hover:bg-opacity-90']")
    public WebElement authbutton;

    @FindBy(xpath = "//button[@class='font-medium text-[14px] rounded-[4px] flex items-center gap-[5px] p-[7px] px-[10px] relative overflow-hidden bg-[#022B50] border-[1px] border-[#022B50] text-white w-full justify-center py-[1.2rem] event-track cursor-pointer hover:bg-opacity-90']")
    public WebElement uploadbbutton;

    @FindBy(xpath = "//input[@id='date_of_appointment']")
    private WebElement clickDateField;

    @FindBy(xpath = "(//div[@class='react-datepicker__week']/div[text()='1'])[1]")
    private WebElement selectDate;

    public void selectDate() {
        safe.safeClick(clickDateField, "DOB calendar field");
        safe.safeClick(selectDate, "DOB value");
    }

        public void companyName(String CompanyName) {safe.safeType(companyName, CompanyName, "Company name");
    }
    public void emailID(String ApplicantEmailId) {
        safe.safeType(emailID, ApplicantEmailId, "Email id");
    }

    public void address(String Address1) {
        safe.safeType(address, Address1, "Address");
    }

    public void companybutton() {
        safe.safeClick(companybutton, "Company Button");
    }

    public void authbutton() {
        safe.safeClick(authbutton, "Auth person entered");
    }

    public void uploadbbutton() {safe.safeClick(uploadbbutton, "Process completed successfully");
    }

    public void mobileNumber(String companymobile) {
        safe.safeType(mobileNumber, companymobile, "mobile no");
    }

    public void noofEmployees(String employeecount) {
        safe.safeType(noofEmployees, employeecount, "No of Employees");
    }

    public void name(String shareholderfathername2) {
        safe.safeType(name, shareholderfathername2, "Auth name");
    }

     public void authmobileno(String companymobile) {
        safe.safeType(authmobileno, companymobile, "Auth mobileno");
    }

    public void authemail(String applicantEmailId) {
        safe.safeType(authemail, applicantEmailId, "Auth email");
    }

    public void authAddress(String Address1) {
        safe.safeType(authAddress, Address1, "Auth Address");
    }
    @FindBy(xpath = "//a[@id='crossSaleNew']")
    public WebElement crossSaleNew;

    @FindBy(xpath = "//li[contains(@class, 'select2-results__option') and contains(text(), 'Apply for Psara License')]")
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




