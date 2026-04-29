package pages;

import base.BasePage;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import utils.SafeActionUtils;
import utils.ScreenshotUtils;
import utils.WaitUtils;
import java.awt.*;
import java.util.List;
import static utils.AllureLoggerUtils.logToAllure;

public class Giftdeedpage extends BasePage {

    private final WaitUtils wait;
    SafeActionUtils safe;

    public Giftdeedpage(WebDriver driver) throws AWTException {
        super(driver);
        this.driver = driver;
        this.wait = new WaitUtils(driver, 15);
        this.safe = new SafeActionUtils(driver);
    }

    @FindBy(xpath = "//input[@placeholder='Enter Seller/Donor/Transferor Details']")
    public WebElement Sellerdetails;

    @FindBy(xpath = "//input[@placeholder='Enter Buyer/Donee/Transferee Details (if multiple):']")
    public WebElement Buyerdetails;

    @FindBy(xpath = "//input[@placeholder='Enter Property Address (including survey number/plot number, landmark, pin code)']")
    public WebElement locationofproperty;

    @FindBy(xpath = "//input[@placeholder='Enter Property Location (District, Taluk, Village)']")
    public WebElement plotno;

    @FindBy(id = "react-select-4-input")
    public WebElement typeofpropertyInput;

    @FindBy(id = "react-select-5-input")
    public WebElement purposeInput;

    @FindBy(xpath = "//div[contains(@class, '-option')]")
    public List<WebElement> dropdownOptions;

    @FindBy(xpath = "//button[@class='font-medium text-[14px] rounded-[4px] flex items-center gap-[5px] p-[7px] px-[10px] relative overflow-hidden bg-[#022B50] border-[1px] border-[#022B50] text-white w-full justify-center event-track cursor-pointer hover:bg-opacity-90']")
    public WebElement propertybutton;



    public void Sellerdetails(String directorName1) {
        safe.safeType(Sellerdetails, directorName1, "Seller details");
    }

    public void Buyerdetails(String Address1) {
        safe.safeType(Buyerdetails, Address1, "Buyer details");
    }


    public void propertybutton() {
        safe.safeClick(propertybutton, "Process completed successfully");
    }

    public void typeofproperty(String Typeofgift) throws InterruptedException {
        try {
            safe.safeType(typeofpropertyInput, Typeofgift, "Type of Property");
            Thread.sleep(1000);
            typeofpropertyInput.sendKeys(Keys.ENTER);
            logToAllure("Selection", "Selected Property Type: " + Typeofgift);
        } catch (Exception e) {
            logger.error("❌ Failed to select Property Type: " + e.getMessage());
            ScreenshotUtils.attachScreenshotToAllure(driver, "Error_PropertyType_Dropdown");
            throw e;
        }
    }

    public void purpose(String purposeofreg) throws InterruptedException {
        try {
            safe.safeType(purposeInput, purposeofreg, "Purpose of registration");
            Thread.sleep(1000);
            purposeInput.sendKeys(Keys.ENTER);
            logToAllure("Selection", "Selected Purpose: " + purposeofreg);
            ScreenshotUtils.attachScreenshotToAllure(driver, "Dropdowns_Selected");
        } catch (Exception e) {
            logger.error("❌ Failed to select Purpose: " + e.getMessage());
            throw e;
        }
    }

    public void locationofproperty(String typeofproperty) {
        safe.safeType(locationofproperty, typeofproperty, "Location of Property");
    }
    public void plotno(String Address1) {
        safe.safeType(plotno, Address1, "Address");
    }

    @FindBy(xpath = "//a[@id='crossSaleNew']")
    public WebElement crossSaleNew;

    @FindBy(xpath = "//li[contains(@class, 'select2-results__option') and contains(text(), 'Gift Deed')]")
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








