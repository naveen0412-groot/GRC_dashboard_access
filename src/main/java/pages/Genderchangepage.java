package pages;

import base.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import utils.SafeActionUtils;
import utils.WaitUtils;

import java.awt.*;

public class Genderchangepage extends BasePage {

    private final WaitUtils wait;
    SafeActionUtils safe;

    public Genderchangepage(WebDriver driver) throws AWTException {
        super(driver);
        this.driver = driver;
        this.wait = new WaitUtils(driver, 15);
        this.safe = new SafeActionUtils(driver);
    }

    @FindBy(xpath = "//input[@placeholder='Enter Name (with Surname)']")
    public WebElement name;

    @FindBy(xpath = "//input[@name='father_first_name']")
    public WebElement fatherName;

    @FindBy(xpath = "//input[@placeholder='Enter Permanent Address']")
    public WebElement PermanentAddress;

    @FindBy(xpath = "//input[@placeholder='Enter Present Address']")
    public WebElement PresentAddress;

    @FindBy(xpath = "//input[@placeholder='Enter Witness Name']")
    public WebElement witnessName1;

    @FindBy(xpath = "//input[@placeholder='Enter Witness Father Name']")
    public WebElement witnessfatherName;

    @FindBy(xpath = "//input[@placeholder='Enter Witness Contact Number']")
    public WebElement witnessContactNumber1;

    @FindBy(xpath = "//input[@placeholder='Enter Witness Address']")
    public WebElement witness1address;

    @FindBy(xpath = "//input[@name='address_line2']")
    public WebElement witnessAddress2;

    @FindBy(xpath = "//input[@name='mobile']")
    public WebElement witnessMobile;

    @FindBy(xpath = "//input[@name='company_name4']")
    public WebElement witnessname2;

    @FindBy(xpath = "//input[@name='company_name5']")
    public WebElement witnessname3;

    @FindBy(xpath = "//button[@class='font-medium text-[14px] rounded-[4px] flex items-center gap-[5px] p-[7px] px-[10px] relative overflow-hidden bg-[#022B50] border-[1px] border-[#022B50] text-white w-full justify-center event-track cursor-pointer hover:bg-opacity-90']")
    public WebElement personalbutton;

    @FindBy(xpath = "//button[@class='font-medium text-[14px] rounded-[4px] flex items-center gap-[5px] p-[7px] px-[10px] relative overflow-hidden bg-[#022B50] border-[1px] border-[#022B50] text-white w-full justify-center event-track cursor-pointer hover:bg-opacity-90']")
    public WebElement witness1button;

    @FindBy(xpath = "//button[@class='font-medium text-[14px] rounded-[4px] flex items-center gap-[5px] p-[7px] px-[10px] relative overflow-hidden bg-[#022B50] border-[1px] border-[#022B50] text-white w-full justify-center py-[1.2rem] event-track cursor-pointer hover:bg-opacity-90']")
    public WebElement witness2button;

    @FindBy(xpath = "//button[@class='font-medium text-[14px] rounded-[4px] flex items-center gap-[5px] p-[7px] px-[10px] relative overflow-hidden bg-[#022B50] border-[1px] border-[#022B50] text-white w-full justify-center py-[1.2rem] event-track cursor-pointer hover:bg-opacity-90']")
    public WebElement docbutton;

    @FindBy(xpath = "//a[@id='crossSaleNew']")
    public WebElement crossSaleNew;

    @FindBy(xpath = "//li[contains(@class, 'select2-results__option') and contains(text(), 'Gender Change')]")
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

    public void fatherName(String companynamemsme) {
        safe.safeType(fatherName, companynamemsme,"Father Name");
    }

    public void name(String companyvaluemsme) {
        safe.safeType(name, companyvaluemsme, "Chennai");
    }

    @FindBy(xpath = "//label[contains(text(), 'Current Gender')]/following::div[contains(@class, 'placeholder') or contains(@class, 'singleValue')]")
    private WebElement currentGenderDropdown;

    @FindBy(xpath = "//label[contains(text(), 'New Gender')]/following::div[contains(@class, 'placeholder') or contains(@class, 'singleValue')]")
    private WebElement newGenderDropdown;

    @FindBy(xpath = "//label[contains(text(), 'permanent address same')]/following::div[contains(@class, 'placeholder') or contains(@class, 'singleValue')]")
    private WebElement addressSameDropdown;

    private String genderOptionXpath = "//div[contains(@class, 'option') and text()='Male']";
    private String yesOptionXpath = "//div[contains(@class, 'option') and text()='Yes']";


    public void selectCurrentGenderMale() {
        safe.safeClick(currentGenderDropdown, "Opening Current Gender Dropdown");

        try { Thread.sleep(1000); } catch (InterruptedException e) {}

        WebElement maleOption = driver.findElement(By.xpath(genderOptionXpath));
        safe.safeClick(maleOption, "Selecting Male for Current Gender");
    }

    public void selectNewGenderMale() {
        safe.safeClick(newGenderDropdown, "Opening New Gender Dropdown");
        try { Thread.sleep(1000); } catch (InterruptedException e) {}
        WebElement maleOption = driver.findElement(By.xpath(genderOptionXpath));
        safe.safeClick(maleOption, "Selecting Male for New Gender");
    }


    public void selectPermanentAddressSameAsPresent() {
        safe.safeClick(addressSameDropdown, "Opening Address Toggle Dropdown");
        try { Thread.sleep(1000); } catch (InterruptedException e) {}
        WebElement yesOption = driver.findElement(By.xpath(yesOptionXpath));
        safe.safeClick(yesOption, "Selecting Yes for Address Same");
    }

    public void PermanentAddress(String Roadstreet) {

        safe.safeType(PermanentAddress, Roadstreet, "Permanant Address");
    }

    public void PresentAddress(String naturebusiness) {
        safe.safeType(PermanentAddress, naturebusiness, "Present address");
    }

    public void personalbutton() {
        safe.safeClick(personalbutton, "Personal detail page button");
    }

    public void witnessName1(String shareholderfathername) {
        safe.safeType(witnessName1, shareholderfathername, "Witness Name 1");
    }

    public void witnessfatherName(String shareholderfathername2) {
        safe.safeType(witnessfatherName, shareholderfathername2, "Witness Father Name");
    }

    public void witnessContactNumber1(String directorMobile1) {
        safe.safeType(witnessContactNumber1, directorMobile1, "Witness Contact Number 1");
    }

    public void witness1address(String address1) {
        safe.safeType(witness1address, address1, "Witness Address 1");
    }

    public void witness1button() {

        safe.safeClick(witness1button, "Witness 1 page button");
    }

    public void witnessAddress2(String directorName2) {
        safe.safeType(witnessAddress2, directorName2, "Witness Address 2");
    }

    public void witnessMobile(String directorMobile2) {
        safe.safeType(witnessMobile, directorMobile2, "Director mobile");

    }

    public void witnessname2(String address2) {
        safe.safeType(witnessname2, address2, "Witness name  2");

    }

    public void witnessname3(String fatherName2) {
        safe.safeType(witnessname3, fatherName2, "Witness Father name 2");
    }

    public void witness2button() {
        safe.safeClick(witness2button, "Witness 2 page button");
    }

    public void docbutton() {
        safe.safeClick(docbutton, "Upload document button");
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



