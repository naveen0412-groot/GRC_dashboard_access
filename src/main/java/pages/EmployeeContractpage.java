package pages;

import base.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import utils.SafeActionUtils;
import utils.WaitUtils;

import java.time.Duration;

import static utils.AllureLoggerUtils.logToAllure;

public class EmployeeContractpage extends BasePage {

    private final WaitUtils wait;
    SafeActionUtils safe;

    public EmployeeContractpage(WebDriver driver) {
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

    @FindBy(xpath = "//li[contains(@class, 'select2-results__option') and contains(text(), 'Employee Contract')]")
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

    @FindBy(xpath = "//input[@name='last_name']")
    public WebElement nameofbusiness;

    @FindBy(xpath = "//input[@name='director_addressLine1']")
    public WebElement address;

    @FindBy(xpath = "//input[@name='street_name']")
    public WebElement authsignatory;

    @FindBy(xpath = "//button[@class='font-medium text-[14px] rounded-[4px] flex items-center gap-[5px] p-[7px] px-[10px] relative overflow-hidden bg-[#022B50] border-[1px] border-[#022B50] text-white w-full justify-center event-track cursor-pointer hover:bg-opacity-90']")
    public WebElement detailsofempbutton;

    @FindBy(xpath = "//button[@class='font-medium text-[14px] rounded-[4px] flex items-center gap-[5px] p-[7px] px-[10px] relative overflow-hidden bg-[#022B50] border-[1px] border-[#022B50] text-white w-full justify-center py-[1.2rem] event-track cursor-pointer hover:bg-opacity-90']")
    public WebElement employeebutton;

    @FindBy(xpath = "//button[@class='font-medium text-[14px] rounded-[4px] flex items-center gap-[5px] p-[7px] px-[10px] relative overflow-hidden bg-[#022B50] border-[1px] border-[#022B50] text-white w-full justify-center py-[1.2rem] event-track cursor-pointer hover:bg-opacity-90']")
    public WebElement detailsempbutton;

    @FindBy(xpath = "//button[@class='font-medium text-[14px] rounded-[4px] flex items-center gap-[5px] p-[7px] px-[10px] relative overflow-hidden bg-[#022B50] border-[1px] border-[#022B50] text-white w-full justify-center py-[1.2rem] event-track cursor-pointer hover:bg-opacity-90']")
    public WebElement descriptionofbusiness;

    @FindBy(xpath = "//button[@class='']")
    public WebElement probationbutton;

//    @FindBy(xpath = "//button[@class='']")
//    public WebElement button;
//
//    @FindBy(xpath = "//button[@class='']")
//    public WebElement button;
//
//    @FindBy(xpath = "//button[@class='']")
//    public WebElement button;

    @FindBy(xpath = "//input[@name='mother_name']")
    public WebElement nameoftheemployee;

    @FindBy(xpath = "//input[@name='director_addressLine2']")
    public WebElement addressofemployee;

    @FindBy(xpath = "//input[@name='father_first_name']")
    public WebElement Fathername;

    @FindBy(xpath = "//input[@name='aadharno']")
    public WebElement aadharno;
    
    @FindBy(xpath = "//input[@name='pan']")
    public WebElement panno;

    @FindBy(xpath = "//input[@name='designation']")
    public WebElement designation;

    @FindBy(xpath = "//input[@name='flat_no']")
    public WebElement Jobdescription;

    @FindBy(xpath = "//input[@name='passport_no']")
    public WebElement annualctc;

    @FindBy(xpath = "//input[@name='floor_no']")
    public WebElement salarybreakup;

    @FindBy(xpath = "//input[@name='director_or_promotor']")
    public WebElement Employeewillreport;

    @FindBy(xpath = "//input[@name='no_of_shares']")
    public WebElement noofdays;

    @FindBy(xpath = "//input[@name='membership_no']")
    public WebElement workinghours;

    @FindBy(xpath = "//input[@id='date_of_acquisition']")
    private WebElement directordateField;

    @FindBy(xpath = "(//div[@class='react-datepicker__week']/div[text()='1'])[1]")
    private WebElement selectDate1;

    public void directordate() {
        safe.safeClick(directordateField, "DOB calendar field");
        safe.safeClick(selectDate1, "DOB value");
    }

    @FindBy(xpath = "//input[@name='alternative_email']")
    public WebElement DescriptionofyourBusiness;

    @FindBy(xpath = "//input[@name='address_line1']")
    public WebElement Tenure;

    @FindBy(xpath = "//input[@name='address_line2']")
    public WebElement noticeperiod;

    @FindBy(xpath = "//input[@name='company_name6']")
    public WebElement legalentitle;

//    @FindBy(xpath = "//input[@name='']")
//    public WebElement otherinformation;
//
//    @FindBy(xpath = "//input[@name='']")
//    public WebElement otherinformation;
//
//    @FindBy(xpath = "//input[@name='']")
//    public WebElement otherinformation;
//
//    @FindBy(xpath = "//input[@name='']")
//    public WebElement otherinformation;



    @FindBy(xpath = "//div[contains(@id, 'Nature of Entity')]//div[contains(@class, 'control')]")
    public WebElement entityDropdownContainer;

    @FindBy(xpath = "//div[contains(@id, 'Nature of Entity')]//input")
    public WebElement entityInput;

    private String optionXpath = "//div[contains(@class, 'menu')]//div[text()='%s']";

    public void selectEntityType(String entityType) {
        safe.safeClick(entityDropdownContainer, "Nature of Entity dropdown");
        entityInput.sendKeys(entityType);
        String dynamicXpath = String.format(optionXpath, entityType);
        WebElement optionToSelect = driver.findElement(By.xpath(dynamicXpath));
        safe.safeClick(optionToSelect, "Selecting: " + entityType);
    }

    public void nameofbusiness(String directorName1) {
        safe.safeType(nameofbusiness, directorName1, "Name of business");
    }

    public void address(String directorName1) {
        safe.safeType(address, directorName1, "Address of business");
    }

    public void authsignatory(String directorName1) {
        safe.safeType(authsignatory, directorName1, "Authorized of signatory");
    }

    public void detailsofempbutton() {
        safe.safeClick(detailsofempbutton, "Details of employee button");
    }

    public void nameoftheemployee(String directorName2) {
        safe.safeType(nameoftheemployee, directorName2, "Name of employee");
    }

    public void addressofemployee(String address2) {
        safe.safeType(addressofemployee, address2, "Address of employee");
    }

    public void Fathername(String fatherName2) {
        safe.safeType(Fathername, fatherName2, "Father of employee");
    }

    public void aadharno(String aadhar1) {
        safe.safeType(aadharno, aadhar1, "Aadhar of employee");
    }

    public void panno(String pan1) {
        safe.safeType(panno, pan1, "Pan number of employee");
    }

    public void employeebutton() {
        safe.safeClick(employeebutton, "Employee button");
    }

    public void detailsempbutton() {

        safe.safeClick(detailsempbutton, "Details of employee button");
    }

    public void designation(String directorName2) {

        safe.safeType(designation, directorName2, "Designation");
    }

    public void Jobdescription(String address2) {

        safe.safeType(Jobdescription, address2, "Job Description");
    }

    public void annualctc(String fatherName2) {

        safe.safeType(annualctc, fatherName2, "Annual ctc");
    }

    public void salarybreakup(String aadhar1) {
        safe.safeType(salarybreakup, aadhar1, "Salary breakup");
    }

    public void Employeewillreport(String aadhar1) {
        safe.safeType(Employeewillreport, aadhar1, "Employee Will Report");
    }

    public void noofdays(String aadhar1) {
        safe.safeType(noofdays, aadhar1, "Noof days");
    }

    public void workinghours(String aadhar1) {
        safe.safeType(workinghours, aadhar1, "Working hours");
    }

    public void DescriptionofyourBusiness(String aadhar1) {

        safe.safeType(DescriptionofyourBusiness, aadhar1, "Description of your business");
    }

    public void descriptionofbusiness() {

        safe.safeClick(descriptionofbusiness, "Description button");
    }

    public void Tenure(String aadhar1) {

        safe.safeType(Tenure, aadhar1, "Tenure");
    }

    public void noticeperiod(String aadhar1) {

        safe.safeType(noticeperiod, aadhar1, "Notice period");
    }

    public void legalentitle(String aadhar1) {
        safe.safeType(legalentitle, aadhar1, "Legal title");
    }

    public void probationbutton() {

        safe.safeClick(probationbutton, "Probation button");
    }
}
