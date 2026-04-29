package pages;

import base.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import utils.SafeActionUtils;
import utils.WaitUtils;

import java.awt.*;
import java.time.Duration;

public class PowerofAttroneypage extends BasePage {

    private final WaitUtils wait;
    SafeActionUtils safe;

    public PowerofAttroneypage(WebDriver driver) throws AWTException {
        super(driver);
        this.driver = driver;
        this.wait = new WaitUtils(driver, 15);
        this.safe = new SafeActionUtils(driver);
    }

    @FindBy(xpath = "//input[@placeholder='Enter Full Name of Principal']")
    public WebElement fullnameofprincipal;

    @FindBy(xpath = "//input[@placeholder='Enter Address']")
    public WebElement address;

    @FindBy(xpath = "//input[@placeholder='Enter Contact Number']")
    public WebElement contactnumber;

    @FindBy(xpath = "//input[@placeholder='Enter Email ID']")
    public WebElement emailid;

    @FindBy(xpath = "//input[@placeholder='Enter PAN / Aadhaar / Identification Number']")
    public WebElement Panidentificationnumber;

    @FindBy(xpath = "//input[@placeholder='Enter Nature of Entity (Individual / Company / LLP / Trust / Other)']")
    public WebElement entity;

    @FindBy(xpath = "//input[@placeholder='Enter Authorized Signatory (if entity)']")
    public WebElement Authorizedsignatory;

    @FindBy(xpath = "//input[@placeholder='Enter Full Name of Attorney / Agent']")
    public WebElement attroneyname;

    @FindBy(xpath = "//input[@placeholder='Enter Address']")
    public WebElement attroneyaddress;

    @FindBy(xpath = "//input[@placeholder='Enter Contact Number']")
    public WebElement attroneycontactnumber;

    @FindBy(xpath = "//input[@placeholder='Enter Email ID']")
    public WebElement attroneyemailid;

    @FindBy(xpath = "//input[@placeholder='Enter Relationship with Principal']")
    public WebElement relationshipwithprincipal;

    @FindBy(xpath = "//input[@placeholder='Enter Nature of Entity (Individual / Company / LLP / Other)']")
    public WebElement natureofentity;

    @FindBy(xpath = "//input[@placeholder='Enter Is this a General PoA or Specific PoA?']")
    public WebElement Poa;

    @FindBy(xpath = "//input[@placeholder='Enter If Specific, specify the purpose or transaction for which PoA is granted']")
    public WebElement purposeoftransaction;

    @FindBy(xpath = "//input[@placeholder='Enter Effective Date of PoA']")
    public WebElement dateofpoa;

    @FindBy(xpath = "//input[@placeholder='Enter Expiry Date (if any)']")
    public WebElement expirydate;
    @FindBy(xpath = "//input[@placeholder='Enter Is the PoA revocable or irrevocable?']")
    public WebElement revocable;



    @FindBy(xpath = "//button[@class='font-medium text-[14px] rounded-[4px] flex items-center gap-[5px] p-[7px] px-[10px] relative overflow-hidden bg-[#022B50] border-[1px] border-[#022B50] text-white w-full justify-center event-track cursor-pointer hover:bg-opacity-90']")
    public WebElement principalbutton;

    @FindBy(xpath = "//button[@class='font-medium text-[14px] rounded-[4px] flex items-center gap-[5px] p-[7px] px-[10px] relative overflow-hidden bg-[#022B50] border-[1px] border-[#022B50] text-white w-full justify-center event-track cursor-pointer hover:bg-opacity-90']")
    public WebElement agentbutton;

    @FindBy(xpath = "//button[@class='font-medium text-[14px] rounded-[4px] flex items-center gap-[5px] p-[7px] px-[10px] relative overflow-hidden bg-[#022B50] border-[1px] border-[#022B50] text-white w-full justify-center event-track cursor-pointer hover:bg-opacity-90']")
    public WebElement purposebutton;

    @FindBy(xpath = "//button[@class='font-medium text-[14px] rounded-[4px] flex items-center gap-[5px] p-[7px] px-[10px] relative overflow-hidden bg-[#022B50] border-[1px] border-[#022B50] text-white w-full justify-center event-track cursor-pointer hover:bg-opacity-90']")
    public WebElement termbutton;


    public void fullnameofprincipal(String directorName1) {
        safe.safeType(fullnameofprincipal, directorName1, "Full name");
    }



    public void principalbutton() {
        safe.safeClick(principalbutton, "Next Button");
    }

    public void agentbutton() {
        safe.safeClick(agentbutton, "Next Button");
    }

    public void purposebutton() {
        safe.safeClick(purposebutton, "Next Button");
    }

    public void termbutton() {
        safe.safeClick(termbutton, "Power of Attroney Successfully completed");
    }

    public void address(String Address1) {
        safe.safeType(address, Address1, "Address");
    }

    public void contactnumber(String directorMobile1) {
        safe.safeType(contactnumber, directorMobile1, "Mobile Number");
    }

    public void emailid(String directorEmail1) {
        safe.safeType(emailid, directorEmail1, "Email ID");
    }

    public void Panidentificationnumber(String Pan1) {
        safe.safeType(Panidentificationnumber, Pan1, "Pam");
    }

    public void entity(String entityType) {
        safe.safeType(entity, entityType, "Entity");
    }

    public void Authorizedsignatory(String authorizedSignatoryName) {
        safe.safeType(Authorizedsignatory, authorizedSignatoryName, "Authorized Signatory");
    }

    public void attroneyname(String fatherName2) {
        safe.safeType(attroneyname, fatherName2, "Father Name");
    }

    public void attroneyaddress(String address2) {
        safe.safeType(attroneyaddress, address2, "Address");
    }

    public void attroneycontactnumber(String directorMobile2) {
        safe.safeType(attroneycontactnumber, directorMobile2, "Mobile Number");
    }

    public void attroneyemailid(String directorEmail2) {
        safe.safeType(attroneyemailid, directorEmail2, "Email ID");
    }

    public void relationshipwithprincipal(String natureofact) {
        safe.safeType(relationshipwithprincipal, natureofact, "Relationship With Principal");
    }

    public void natureofentity(String entityType) {
        safe.safeType(natureofentity, entityType, "Entity Type");
    }

    public void Poa(String city) {
        safe.safeType(Poa, city, "Poa");
    }

    public void purposeoftransaction(String descrip) {
        safe.safeType(purposeoftransaction, descrip, "Purpose of Transaction");
    }

    public void dateofpoa(String city) {
        safe.safeType(dateofpoa, city, "Date of Transaction");
    }

    public void expirydate(String descrip) {
        safe.safeType(expirydate, descrip, "Expiry Date");
    }

    public void revocable(String descrip) {
        safe.safeType(revocable, descrip, "Revocable");
    }
    @FindBy(xpath = "//a[@id='crossSaleNew']")
    public WebElement crossSaleNew;

    @FindBy(xpath = "//li[contains(@class, 'select2-results__option') and contains(text(), 'Prepare a Power of Attorney')]")
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








