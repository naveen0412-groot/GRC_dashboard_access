package pages;

import base.BasePage;
import org.openqa.selenium.By;
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

public class Propertyverifypage extends BasePage {

    private final WaitUtils wait;
    SafeActionUtils safe;

    public Propertyverifypage(WebDriver driver) throws AWTException {
        super(driver);
        this.driver = driver;
        this.wait = new WaitUtils(driver, 15);
        this.safe = new SafeActionUtils(driver);
    }

    @FindBy(xpath = "//input[@placeholder='Enter Seller/Donor/Transferor Details']")
    public WebElement Sellerdetails;

    @FindBy(xpath = "//input[@placeholder='Enter Buyer/Donee/Transferee Details (if multiple)']")
    public WebElement Buyerdetails;

    @FindBy(xpath = "//input[@id='option-Type of Property-Residential']")
    public WebElement typeofproperty;

    @FindBy(xpath = "//input[@id='option-Purpose of Registration:-Gift Deed']")
    public WebElement purposeofregistration;

    @FindBy(xpath = "//input[@placeholder='Enter Property Address (including survey number/plot number, landmark, pin code)']")
    public WebElement propertyaddress;

    @FindBy(xpath = "//input[@placeholder='Enter Property Location (District, Taluk, Village)']")
    public WebElement location;


    @FindBy(xpath = "//button[@class='font-medium text-[14px] rounded-[4px] flex items-center gap-[5px] p-[7px] px-[10px] relative overflow-hidden bg-[#022B50] border-[1px] border-[#022B50] text-white w-full justify-center event-track cursor-pointer hover:bg-opacity-90']")
    public WebElement basicbutton;




    public void Sellerdetails(String directorName1) {

        safe.safeType(Sellerdetails, directorName1, "Seller details");
    }

    public void Buyerdetails(String Address1) {

        safe.safeType(Buyerdetails, Address1, "Buyer details");
    }

    public void basicbutton() {

        safe.safeClick(basicbutton, "Next Button");
    }

    public void typeofproperty() {

        safe.safeClick(typeofproperty, "Type of Property");
    }

    public void purposeofregistration() {

        safe.safeClick(purposeofregistration, "Purpose of Property");
    }

    public void location(String OPCLocality) {

        safe.safeType(location, OPCLocality, "Location");
    }

    public void propertyaddress(String Address1) {

        safe.safeType(propertyaddress, Address1, "Property address");
    }
    @FindBy(xpath = "//a[@id='crossSaleNew']")
    public WebElement crossSaleNew;

    @FindBy(xpath = "//li[contains(@class, 'select2-results__option') and contains(text(), 'Property Verification (Due Diligence)')]")
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

//    public void selectingtheservice() {
//        safe.safeClick(selectingtheservice, "Selecting these services");
//
//    }

    public void selectingtheservice(String Propertyverification) {
        String xpath = String.format("//li[contains(@class, 'select2-results__option') and text()='%s']", Propertyverification);
        WebElement element = driver.findElement(By.xpath(xpath));
        safe.safeClick(element, "Selecting " + Propertyverification);
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








