package pages;

import base.BasePage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import utils.SafeActionUtils;
import utils.WaitUtils;

public class BusinessCommencementPage extends BasePage {

    private final WaitUtils wait;
    SafeActionUtils safe;

    public BusinessCommencementPage(WebDriver driver) {
        super(driver);
        this.wait = new WaitUtils(driver, 15);
        this.safe = new SafeActionUtils(driver);
    }
    @FindBy(xpath = "//input[@placeholder='Enter Company Name']")
    private WebElement companyName;

    @FindBy(xpath = "//input[@placeholder='Enter CIN']")
    private WebElement cin;

    @FindBy(xpath = "//input[@placeholder='Enter Confirm whether you have opened a current account in the name of your company']")
    private WebElement confirmAccountDetails;

    @FindBy(xpath = "(//p[text()='Next'])[2]")
    private WebElement nextButton;

    public void typeCompanyName(String name) {
        safe.safeType(companyName, name, "Company Name");
    }

    public void typeCIN(String cinNumber) {
        safe.safeType(cin, cinNumber, "CIN");
    }

    public void typeConfirmAccountDetails(String accountDetails) {
        safe.safeType(confirmAccountDetails, accountDetails, "Confirm Current Account Details");
    }

    public void clickNextButton() {
        safe.safeClick(nextButton, "Next Button");
    }

    @FindBy(xpath = "//a[@id='crossSaleNew']")
    public WebElement crossSaleNew;


    @FindBy(xpath = "//li[contains(@class, 'select2-results__option') and contains(text(), 'Certificate for Commencement of Business')]")
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
