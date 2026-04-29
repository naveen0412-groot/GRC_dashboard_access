package pages;

import base.BasePage;
import org.openqa.selenium.*;
import org.openqa.selenium.support.FindBy;
import utils.SafeActionUtils;
import utils.WaitUtils;

import java.awt.*;
import java.time.Duration;

import static org.reflections8.Reflections.log;


public class Legalmeterpage extends BasePage {

    private final WaitUtils wait;
    SafeActionUtils safe;

    public Legalmeterpage(WebDriver driver) throws AWTException {
        super(driver);
        this.driver = driver;
        this.wait = new WaitUtils(driver, 15);
        this.safe = new SafeActionUtils(driver);
    }

    @FindBy(xpath = "//input[@placeholder='Enter Address Line 1']")
    public WebElement addressline1;

    @FindBy(xpath = "//input[@placeholder='Enter Address Line 2']")
    public WebElement addressline2;

    @FindBy(xpath = "//input[@placeholder='Enter Pincode']")
    public WebElement pincode;

    @FindBy(xpath = "//textarea[@name='purpose']")
    public WebElement listofproducts;

    @FindBy(xpath = "//input[@placeholder='Enter Authorized Person Name']")
    public WebElement authorizedpersonname;

    @FindBy(xpath = "//input[@placeholder='Enter Authorized Person Contact Number']")
    public WebElement authorizedpersoncontact;

    @FindBy(xpath = "//input[@placeholder='Enter Authorized Person Email ID ']")
    public WebElement authorizedpersonemail;

    @FindBy(xpath = "//input[@placeholder='Select Industry']")
    public WebElement industry;

    @FindBy(xpath = "//div[contains(@id, 'Entity type')]//input")
    public WebElement entitytype;

    @FindBy(xpath = "//p[text()='PAN card']/parent::div/parent::div/parent::div/div[2]/input")
    public WebElement pancard;

    @FindBy(xpath = "//p[text()='Certificate of incorporation']/parent::div/parent::div/parent::div/div[2]/input")
    public WebElement coi;

    @FindBy(xpath = "//p[text()='GST certificate']/parent::div/parent::div/parent::div/div[2]/input")
    public WebElement gstcertificate;

    @FindBy(xpath = "//p[text()='Aadhar card front']/parent::div/parent::div/parent::div/div[2]/input")
    public WebElement aadharcardfront;

    @FindBy(xpath = "//p[text()='Aadhar card back']/parent::div/parent::div/parent::div/div[2]/input")
    public WebElement aadharcardback;



    @FindBy(xpath = "//button[@class='font-medium text-[14px] rounded-[4px] flex items-center gap-[5px] p-[7px] px-[10px] relative overflow-hidden bg-[#022B50] border-[1px] border-[#022B50] text-white w-full justify-center event-track cursor-pointer hover:bg-opacity-90']")
    public WebElement addressnext;

    @FindBy(xpath = "//button[@class='font-medium text-[14px] rounded-[4px] flex items-center gap-[5px] p-[7px] px-[10px] relative overflow-hidden bg-[#022B50] border-[1px] border-[#022B50] text-white w-full justify-center py-[1.2rem] event-track cursor-pointer hover:bg-opacity-90']")
    public WebElement officebutton;

    @FindBy(xpath = "//button[@class='font-medium text-[14px] rounded-[4px] flex items-center gap-[5px] p-[7px] px-[10px] relative overflow-hidden bg-[#022B50] border-[1px] border-[#022B50] text-white w-full justify-center py-[1.2rem] event-track cursor-pointer hover:bg-opacity-90']")
    public WebElement uploadbutton;

    public void entitytype(String legalentity) {
        try {
            JavascriptExecutor js = (JavascriptExecutor) driver;

            WebElement input = driver.findElement(By.xpath("//div[contains(@id, 'Entity type')]//input"));

            js.executeScript("arguments[0].click();", input);

            input.sendKeys(legalentity);
            Thread.sleep(1000); 
            input.sendKeys(Keys.ENTER);

        } catch (Exception e) {
            JavascriptExecutor js = (JavascriptExecutor) driver;
            WebElement fallbackInput = driver.findElement(By.cssSelector("input[id^='react-select']"));
            fallbackInput.sendKeys(legalentity + Keys.ENTER);
        }
    }

    public void addressline1(String Address1) {safe.safeType(addressline1, Address1, "Address line 1");
    }

    public void addressline2(String address2) {
        safe.safeType(addressline2, address2, "Address line 2");
    }

    public void pincode(String FoodAddressPinCode) {
        safe.safeType(pincode, FoodAddressPinCode, "Pincode");
    }

    public void listofproducts(String howInventionDiffer) {
        safe.safeType(listofproducts, howInventionDiffer, "List of Products");
    }

    public void authorizedpersonname(String brandName) {
        safe.safeType(authorizedpersonname, brandName, "Authorized person name ");
    }

    public void authorizedpersoncontact(String applicantPhone) {
        safe.safeType(authorizedpersoncontact, applicantPhone, "Authorized person contact");
    }

    public void authorizedpersonemail(String applicantEmail) {
        safe.safeType(authorizedpersonemail, applicantEmail, "Authorized person email");
    }

    public void industry(String legalentity) {
        safe.safeType(industry, legalentity, "Industry");
    }

    public void addressnext() {
        safe.safeClick(addressnext, "Address Next Button");
    }

    public void officebutton() {
        safe.safeClick(officebutton, "Member Next Button");
    }

    public void uploadbutton() {safe.safeClick(uploadbutton, "Process completed successfully");
    }

    @FindBy(xpath = "//a[@id='crossSaleNew']")
    public WebElement crossSaleNew;

    @FindBy(xpath = "//li[contains(@class, 'select2-results__option') and contains(text(), 'Legal Metrology')]")
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




