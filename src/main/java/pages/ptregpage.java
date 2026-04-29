package pages;

import base.BasePage;
import org.openqa.selenium.*;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import utils.SafeActionUtils;
import utils.WaitUtils;

import java.time.Duration;
import java.util.List;

public class ptregpage extends BasePage {

    private final WaitUtils wait;
    SafeActionUtils safe;

    public ptregpage(WebDriver driver) {
        super(driver);
        this.wait = new WaitUtils(driver, 15);
        this.safe = new SafeActionUtils(driver);
    }

    @FindBy(xpath = "//button[@class='font-medium text-[14px] rounded-[4px] flex items-center gap-[5px] bg-[none] p-[7px] px-[10px] relative overflow-hidden border-[#E6EAEE] border-[1px] w-full justify-center py-[1.2rem] border-none event-track cursor-pointer hover:bg-opacity-90']")
    public WebElement noitsanothercompany;

    @FindBy(xpath = "//div[@id='react-select-2-placeholder']")
    public WebElement companytype;

    @FindBy(xpath = "//input[@placeholder='Enter Company Name']")
    public WebElement company_name;

    @FindBy(xpath = "//input[@placeholder='Enter Mobile number']")
    public WebElement company_mobile;

    @FindBy(xpath = "//input[@placeholder='Enter Email ID']")
    public WebElement company_emailid;

    @FindBy(xpath = "//input[@placeholder='Pick Commencement date (Company start date)']")
    public WebElement company_dob;

    @FindBy(xpath = "//input[@placeholder='Select Nature of Business']")
    public WebElement natureofbusiness;

    @FindBy(xpath = "//input[@placeholder='Search here']")
    public WebElement search;

    @FindBy(xpath = "//p[text()='Other Online Platform']")
    public WebElement searchclick;

    @FindBy(xpath = "//button[@class='font-medium text-[14px] rounded-[4px] flex items-center gap-[5px] p-[7px] px-[10px] relative overflow-hidden bg-[#022B50] border-[1px] border-[#022B50] text-white w-full justify-center py-[1.2rem] event-track cursor-pointer hover:bg-opacity-90']\n")
    public WebElement nextpage1;

    @FindBy(xpath = "//input[@placeholder='Enter Building / Flat number']")
    public WebElement address1;

    @FindBy(xpath = "//input[@placeholder='Enter Area/Locality name']")
    public WebElement arealocality;

    @FindBy(xpath = "//input[@placeholder='Enter Road/Street/Lane name']")
    public WebElement Road_street;

    @FindBy(xpath = "//input[@placeholder='Enter Pin code']")
    public WebElement pincode;

    @FindBy(xpath = "//input[@placeholder='Select District']")
    public WebElement citycompany;

    @FindBy(xpath = "//input[@placeholder='Select State']")
    public WebElement statecompany;

    @FindBy(xpath = "//input[@placeholder='Enter Nearest police station branch name']")
    public WebElement policestation;

    @FindBy(xpath = "//input[@placeholder='Enter Account number']")
    public WebElement BankAcc;

    @FindBy(xpath = "//input[@placeholder='Enter IFSC code']")
    public WebElement IFSCcode;

    @FindBy(xpath = "//button[@class='font-medium text-[14px] rounded-[4px] flex items-center gap-[5px] p-[7px] px-[10px] relative overflow-hidden bg-[#022B50] border-[1px] border-[#022B50] text-white w-full justify-center py-[1.2rem] event-track cursor-pointer hover:bg-opacity-90']")
    public WebElement addressnext;

    @FindBy(xpath = "//input[@placeholder='Enter first name']")
    public WebElement firstname;

    @FindBy(xpath = "//input[@placeholder='Enter email']")
    public WebElement memberemail;

    @FindBy(xpath = "//input[@placeholder='Enter mobile number']")
    public WebElement membermobile;

    @FindBy(xpath = "//input[@placeholder='Enter Date of Birth']")
    public WebElement memberdob;

    @FindBy(xpath = "//button[@class='font-medium text-[14px] rounded-[4px] flex items-center gap-[5px] p-[7px] px-[10px] relative overflow-hidden bg-[#022B50] border-[1px] border-[#022B50] text-white w-full justify-center py-[1.2rem] event-track cursor-pointer hover:bg-opacity-90']")
    public WebElement directornext;

    @FindBy(xpath = "//p[text()='Aadhar card front']/parent::div/parent::div/parent::div/div[2]/input")
    public WebElement selectAadharFront;

    @FindBy(xpath = "//p[text()='Aadhar card back']/parent::div/parent::div/parent::div/div[2]/input")
    public WebElement selectAadharBack;

    @FindBy(xpath = "//p[text()='Passport size photograph']/parent::div/parent::div/parent::div/div[2]/input")
    public WebElement uploadPassportSize;

    @FindBy(xpath = "//p[text()='PAN card']/parent::div/parent::div/parent::div/div[2]/input")
    public WebElement uploadPAN;

    @FindBy(xpath = "(//input[@type='file'])[5]")
    public WebElement signature;

    @FindBy(xpath = "//p[text()='Rental agreement']/parent::div/parent::div/parent::div/div[2]/input")
    public WebElement rentalagreement;

    @FindBy(xpath = "//p[text()='Electricity bill']/parent::div/parent::div/parent::div/div[2]/input")
    public WebElement electricitybill;

    @FindBy(xpath = "//p[text()='Employee basic detail']/parent::div/parent::div/parent::div/div[2]/input")
    public WebElement employeebasicdetail;


    public void clickEmployeeCountDropdown() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        try {

            WebElement dropdownContainer = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[normalize-space()='Select Employee Count']")));
            dropdownContainer.click();

        } catch (Exception e) {
            throw new RuntimeException("❌ Unable to click Employee Count dropdown", e);
        }
    }

    public void enterEmployeeCount(String employeecount) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        try {

            WebElement input = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@type='text' and @role='combobox']")));
            input.clear();
            input.sendKeys(employeecount);

        } catch (Exception e) {
            throw new RuntimeException("❌ Unable to enter Employee Count: " + employeecount, e);
        }
    }


    public void selectEmployeeCountValue(String employeecount) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        try {

            WebElement option = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//div[normalize-space()='" + employeecount + "']")));
            option.click();
        } catch (Exception e) {
            throw new RuntimeException("❌ Unable to select Employee Count option: " + employeecount, e);
        }
    }

    public void noitsanothercompany() {

        safe.safeClick(noitsanothercompany, "No it's for another company");

    }

    public void accountno(String account) {
        safe.safeType(BankAcc, account, "bank account");

    }

    public void ifsc(String bankifsc) {
        safe.safeType(IFSCcode, bankifsc, "ifsc code");
    }

    public void nextcta() {
        safe.safeClick(nextpage1, "next button");
    }

    public void directornext() {
        safe.safeClick(directornext, "next button");
    }

    public void companynext() {
        safe.safeClick(addressnext, "next button");
    }


    public void Comptype(String companyValue) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        wait.until(ExpectedConditions.elementToBeClickable(companytype)).click();

        WebElement option = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//div[@role='option' and contains(text(),'" + companyValue + "')]")));
        option.click();
    }

    public void com_name(String companynamemsme) {
        safe.safeType(company_name, companynamemsme, "company name");
    }

    public void com_mobile(String companymobile) {
        safe.safeType(company_mobile, companymobile, "mobile");
    }

    public void com_email(String companyemail) {
        safe.safeType(company_emailid, companyemail, "email");
    }

    public void police(String PoliceStation) {
        safe.safeType(policestation, PoliceStation, "police");
    }


    @FindBy(xpath = "//input[@id='date_of_commencement']")
    private WebElement clickDateField;

    @FindBy(xpath = "(//div[@class='react-datepicker__week']/div[text()='1'])[1]")
    private WebElement selectDate;

    public void selectDate() {
        safe.safeClick(clickDateField, "DOB calendar field");
        safe.safeClick(selectDate, "DOB value");
    }

    @FindBy(xpath = "//input[@id='dob']")
    private WebElement directordateField;

    @FindBy(xpath = "(//div[@class='react-datepicker__week']/div[text()='1'])[1]")
    private WebElement selectDate1;

    public void directordate() {
        safe.safeClick(directordateField, "DOB calendar field");
        safe.safeClick(selectDate1, "DOB value");
    }


    public void nature() {
        safe.safeClick(natureofbusiness, "next button");
    }

    public void businessselect() {
        safe.safeClick(searchclick, "business select");
    }

    public void searchbusiness(String searchbus) {
        safe.safeType(search, searchbus, "search business");
    }

    public void flatno(String flatno) {
        safe.safeType(address1, flatno, "flat no");
    }

    public void area(String area) {
        safe.safeType(arealocality, area, "area locality");
    }

    public void road(String roadstreet) {
        safe.safeType(Road_street, roadstreet, "Road street");
    }

    public void pincodecomp(String Pincode) {
        safe.safeType(pincode, Pincode, "Enter pincode");
    }

    public void citycomp(String city) {
        safe.safeType(citycompany, city, " city ");
    }

    public void statecompany(String state) {
        safe.safeType(statecompany, state, " State ");
    }

    public void name(String Aadhar1) {
        safe.safeType(firstname, Aadhar1, " aadhar no ");
    }

    public void memberemail(String DirectorEmail2) {
        safe.safeType(memberemail, DirectorEmail2, " aadhar no ");
    }

    public void membermobile(String DirectorMobile2) {
        safe.safeType(membermobile, DirectorMobile2, " aadhar no ");
    }

    @FindBy(xpath = "//a[@id='crossSaleNew']")
    public WebElement crossSaleNew;

    @FindBy(xpath = "//li[contains(@class, 'select2-results__option') and contains(text(), 'Apply for Professional Tax Registration')]")
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
