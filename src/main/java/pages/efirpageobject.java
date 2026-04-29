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

public class efirpageobject extends BasePage {

    private final WaitUtils wait;
    SafeActionUtils safe;

    public efirpageobject(WebDriver driver) throws AWTException {
        super(driver);
        this.driver = driver;
        this.wait = new WaitUtils(driver, 15);
        this.safe = new SafeActionUtils(driver);
    }

    @FindBy(xpath = "//input[@placeholder='Enter City']")
    public WebElement cityInput;

    @FindBy(xpath = "//div[@class='css-p72jfa-control'][1]")
    public WebElement complaintType;

    @FindBy(xpath = "//input[@id='react-select-3-input']")
    public WebElement complaintvalue;

    @FindBy(xpath = "//input[@placeholder='Enter Description']")
    public WebElement descriptionfir;

    // @FindBy(xpath = "//textarea[@name='objective_of_business']")
    //public WebElement descriptionfir;

    @FindBy(xpath = "//button[@class='font-medium text-[14px] rounded-[4px] flex items-center gap-[5px] p-[7px] px-[10px] relative overflow-hidden bg-[#022B50] border-[1px] border-[#022B50] text-white w-full justify-center event-track cursor-pointer hover:bg-opacity-90']")
    public WebElement button;


    public void city(String cityname) {
        safe.safeType(cityInput, cityname, "Chennai");
    }

    public void cType(String complainvalue) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        JavascriptExecutor js = (JavascriptExecutor) driver;

        // 1. Click the dropdown to open it
        WebElement dropdown = wait.until(ExpectedConditions.elementToBeClickable(complaintType));
        dropdown.click();

        // 2. Locate the option (Wait for presence, not just visibility)
        WebElement option = wait.until(ExpectedConditions.presenceOfElementLocated(
                By.xpath("//div[contains(@class,'-option') and text()='" + complainvalue + "']")));

        // 3. Scroll the option into the center of the screen to avoid footer/header overlays
        js.executeScript("arguments[0].scrollIntoView({block: 'center'});", option);

        // 4. Force the click via JavaScript
        js.executeScript("arguments[0].click();", option);
    }



    public void description(String desc) {
        safe.safeType(descriptionfir, desc, "Thank you for your response");

    }

    public void buttonnt() {
        safe.safeClick(button, "Next Button");

    }
    public void cvalue(String complainvalue) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        JavascriptExecutor js = (JavascriptExecutor) driver;

        // Step 1: Click and clear the input if necessary
        WebElement input = wait.until(ExpectedConditions.elementToBeClickable(By.id(String.valueOf(complaintvalue))));
        input.click();

        // Step 2: Send keys
        input.sendKeys(complainvalue);

        // Step 3: Wait for the dynamic result to appear and click via JS
        try {
            WebElement option = wait.until(ExpectedConditions.presenceOfElementLocated(
                    By.xpath("//div[contains(@class,'-option') and text()='" + complaintvalue + "']")));
            js.executeScript("arguments[0].click();", option);
        } catch (Exception e) {
            System.out.println("Dropdown option not found or blocked: " + e.getMessage());
        }
    }
    @FindBy(xpath = "//a[@id='crossSaleNew']")
    public WebElement crossSaleNew;

    @FindBy(xpath = "//li[contains(@class, 'select2-results__option') and contains(text(), 'eFIR')]")
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



