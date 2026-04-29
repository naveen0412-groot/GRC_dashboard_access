package pages;

import base.BasePage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import utils.SafeActionUtils;
import utils.WaitUtils;

public class OutsideAddressChangePage extends BasePage {

    private final WaitUtils wait;
    SafeActionUtils safe;

    public OutsideAddressChangePage(WebDriver driver) {
        super(driver);
        this.wait = new WaitUtils(driver, 30);
        this.safe = new SafeActionUtils(driver);
    }

    @FindBy(xpath = "//input[@placeholder='Enter Company Name']")
    private WebElement companyName;

    @FindBy(xpath = "//input[@placeholder='Enter Company CIN']")
    private WebElement companyCIN;

    @FindBy(xpath = "(//p[text()='Next'])[2]")
    private WebElement nextButton;

    @FindBy(xpath = "//input[@placeholder='Enter New Address Line 1']")
    private WebElement addressLine1;

    @FindBy(xpath = "//input[@placeholder='Enter New Address Line 2']")
    private WebElement addressLine2;

    @FindBy(xpath = "//input[@placeholder='Enter Country']")
    private WebElement country;

    @FindBy(xpath = "//input[@placeholder='Enter Pin Code/Zip code']")
    private WebElement pinCode;

    @FindBy(xpath = "//input[@placeholder='Enter Area/Locality']")
    private WebElement locality;

    @FindBy(xpath = "//input[@placeholder='Enter Latitude']")
    private WebElement latitude;

    @FindBy(xpath = "//input[@placeholder='Enter Longitude']")
    private WebElement longitude;

    @FindBy(xpath = "//input[@placeholder='Enter Jurisdiction of Police Station']")
    private WebElement jurisdiction;

    @FindBy(xpath = "//input[@placeholder='Select Option']")
    private WebElement rocUpToDate;

    public void typeRocUpToDate(String name) {
        safe.safeType(rocUpToDate, name, "Whether upto date Annual Filings (RoC) filed");
    }

    public void clickNextButton() {
        safe.safeClick(nextButton, "Next Button");
    }

    public void typeCompanyName(String name) {
        safe.safeType(companyName, name, "Enter Company Name");
    }

    public void typeCompanyCIN(String cinNumber) {
        safe.safeType(companyCIN, cinNumber, "Enter Company CIN");
    }

    public void typeAddressLine1(String line1) {
        safe.safeType(addressLine1, line1, "Enter New Address Line 1");
    }

    public void typeAddressLine2(String line2) {
        safe.safeType(addressLine2, line2, "Enter New Address Line 2");
    }

    public void typeCountry(String countryName) {
        safe.safeType(country, countryName, "Enter Country");
    }

    public void typePinCode(String pin) {
        safe.safeType(pinCode, pin, "Enter Pin Code/Zip code");
    }

    public void typeLocality(String area) {
        safe.safeType(locality, area, "Enter Area/Locality");
    }

    public void typeLatitude(String lat) {
        safe.safeType(latitude, lat, "Enter Latitude");
    }

    public void typeLongitude(String lng) {
        safe.safeType(longitude, lng, "Enter Longitude");
    }

    public void typeJurisdiction(String policeStation) {
        safe.safeType(jurisdiction, policeStation, "Enter Jurisdiction of Police Station");
    }
    @FindBy(xpath = "//a[@id='crossSaleNew']")
    public WebElement crossSaleNew;

    @FindBy(xpath = "//li[contains(@class, 'select2-results__option') and contains(text(), 'Change the Official Address of Your Company (Outside the City)')]")
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
