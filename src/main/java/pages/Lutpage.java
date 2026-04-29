package pages;

import base.BasePage;
import org.openqa.selenium.*;
import org.openqa.selenium.support.FindBy;
import utils.SafeActionUtils;
import utils.WaitUtils;

import java.awt.*;


public class Lutpage extends BasePage {

    private final WaitUtils wait;
    SafeActionUtils safe;

    public Lutpage(WebDriver driver) throws AWTException {
        super(driver);
        this.driver = driver;
        this.wait = new WaitUtils(driver, 15);
        this.safe = new SafeActionUtils(driver);
    }

    @FindBy(xpath = "//input[@placeholder='Enter Username']")
    public WebElement username;

    @FindBy(xpath = "//input[@placeholder='Enter Password']")
    public WebElement password;

    @FindBy(xpath = "//input[@placeholder='Enter Pincode']")
    public WebElement pincode;

    @FindBy(xpath = "//input[@placeholder='Enter Address']")
    public WebElement address;

    @FindBy(xpath = "//input[@placeholder='Enter Occupation']")
    public WebElement occupation;

    @FindBy(xpath = "//input[@placeholder='Enter Name Of Witness']")
    public WebElement nameofwitness;

    @FindBy(xpath = "//button[@class='font-medium text-[14px] rounded-[4px] flex items-center gap-[5px] p-[7px] px-[10px] relative overflow-hidden bg-[#022B50] border-[1px] border-[#022B50] text-white w-full justify-center event-track cursor-pointer hover:bg-opacity-90']")
    public WebElement entitynext;

    @FindBy(xpath = "//button[@class='font-medium text-[14px] rounded-[4px] flex items-center gap-[5px] p-[7px] px-[10px] relative overflow-hidden bg-[#022B50] border-[1px] border-[#022B50] text-white w-full justify-center py-[1.2rem] event-track cursor-pointer hover:bg-opacity-90']")
    public WebElement gstloginbutton;

    @FindBy(xpath = "//button[@class='font-medium text-[14px] rounded-[4px] flex items-center gap-[5px] p-[7px] px-[10px] relative overflow-hidden bg-[#022B50] border-[1px] border-[#022B50] text-white w-full justify-center event-track cursor-pointer hover:bg-opacity-90']")
    public WebElement witnessbutton;

    public void entitytype(String Lutentity) {
        try {
            JavascriptExecutor js = (JavascriptExecutor) driver;

            WebElement input = driver.findElement(By.xpath("//div[contains(@id, 'Enter Entity Type')]//input"));

            js.executeScript("arguments[0].click();", input);

            input.sendKeys(Lutentity);
            Thread.sleep(1000); 
            input.sendKeys(Keys.ENTER);

        } catch (Exception e) {
            JavascriptExecutor js = (JavascriptExecutor) driver;
            WebElement fallbackInput = driver.findElement(By.cssSelector("input[id^='react-select']"));
            fallbackInput.sendKeys(Lutentity + Keys.ENTER);
        }
    }

    public void username(String FatherName1) {safe.safeType(username, FatherName1, "User name");
    }

    public void password(String lutpassword) {
        safe.safeType(password, lutpassword, "Password");
    }

    public void pincode(String FoodAddressPinCode) {
        safe.safeType(pincode, FoodAddressPinCode, "Pincode");
    }
    public void occupation(String AreaOfOccupation) {
        safe.safeType(occupation, AreaOfOccupation, "Occupation");
    }
    public void nameofwitness(String directorName2) {
        safe.safeType(nameofwitness, directorName2, "Name of witness");
    }

    public void address(String Address2) {
        safe.safeType(address, Address2, "Address");
    }

    public void entitynext() {
        safe.safeClick(entitynext, "Entity Next Button");
    }

    public void gstloginbutton() {
        safe.safeClick(gstloginbutton, "GST login details entered");
    }

    public void witnessbutton() {safe.safeClick(witnessbutton, "Process completed successfully");
    }
    @FindBy(xpath = "//a[@id='crossSaleNew']")
    public WebElement crossSaleNew;

    @FindBy(xpath = "//li[contains(@class, 'select2-results__option') and contains(text(), 'LUT Application')]")
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




