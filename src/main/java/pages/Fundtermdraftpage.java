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

public class Fundtermdraftpage extends BasePage {

    private final WaitUtils wait;
    SafeActionUtils safe;

    public Fundtermdraftpage(WebDriver driver) throws AWTException {
        super(driver);
        this.driver = driver;
        this.wait = new WaitUtils(driver, 15);
        this.safe = new SafeActionUtils(driver);
    }

    @FindBy(xpath = "//input[@placeholder='Enter Investor Name']")
    public WebElement investorName;

    @FindBy(xpath = "//input[@placeholder='Enter Investor Age']")
    public WebElement investorAge;

    @FindBy(xpath = "//input[@placeholder='Enter Mailing Address of the Investor']")
    public WebElement addressofinvestor;

    @FindBy(xpath = "//input[@placeholder='Enter Mailing Address of the Company']")
    public WebElement mailingaddress;

    @FindBy(xpath = "//input[@placeholder='Enter The Exact Scope and Ambit of the Business carried out by your Company']")
    public WebElement scopeandambit;

    @FindBy(xpath = "//input[@placeholder='Enter The Investment amount']")
    public WebElement investmentamount;

    @FindBy(xpath = "//input[@placeholder='Enter Period of Investment, if any']")
    public WebElement periodofinvestment;

    @FindBy(xpath = "//input[@placeholder='Enter Terms of Investment']")
    public WebElement termofinvestment;

    @FindBy(xpath = "//input[@placeholder='Enter Specific rights / obligations applicable to the Investor']")
    public WebElement specificinvestor;

    @FindBy(xpath = "//input[@placeholder='Enter The Number and Nature of Shares being Issued']")
    public WebElement numberandnatureofshares;
    @FindBy(xpath = "//input[@placeholder='Enter Vesting Schedule Applicable, If any']")
    public WebElement visitingscheduleapplicable;
    @FindBy(xpath = "//input[@placeholder='Enter The Rights of the Promoters / Founders']")
    public WebElement rightsofthepromoters;
    @FindBy(xpath = "//input[@placeholder='Enter Time Frame for the Definitive Agreements (Shareholders Agreements)']")
    public WebElement agreements;
    @FindBy(xpath = "//input[@placeholder='Enter Any Conditions Contingent for the Investment to Happen']")
    public WebElement Contingent;


    @FindBy(xpath = "//button[@class='font-medium text-[14px] rounded-[4px] flex items-center gap-[5px] p-[7px] px-[10px] relative overflow-hidden bg-[#022B50] border-[1px] border-[#022B50] text-white w-full justify-center event-track cursor-pointer hover:bg-opacity-90']")
    public WebElement investorbutton;

    @FindBy(xpath = "//button[@class='font-medium text-[14px] rounded-[4px] flex items-center gap-[5px] p-[7px] px-[10px] relative overflow-hidden bg-[#022B50] border-[1px] border-[#022B50] text-white w-full justify-center py-[1.2rem] event-track cursor-pointer hover:bg-opacity-90']")
    public WebElement sharebutton;




    public void sharebutton() {
        safe.safeClick(sharebutton, "Process completed successfully");

    }

    public void investorbutton() {
        safe.safeClick(investorbutton, "Next Button");

    }


    public void investorName(String directorName2) {
        safe.safeType(investorName, directorName2, "Investor name");
    }

    public void investorAge(String complainvalue) {
        safe.safeType(investorAge, complainvalue, "Investor Age");
    }

    public void addressofinvestor(String directorEmail2) {

        safe.safeType(addressofinvestor, directorEmail2, "Investor Address");
    }

    public void mailingaddress(String directorEmail1) {
        safe.safeType(mailingaddress, directorEmail1, "Company name");
    }

    public void scopeandambit(String descrip) {
        safe.safeType(scopeandambit, descrip, "Scope and ambit");
    }

    public void investmentamount(String debtAmount) {
        safe.safeType(investmentamount, debtAmount, "Investor amount");
    }

    public void periodofinvestment(String opcCompanyName1) {
        safe.safeType(periodofinvestment, opcCompanyName1, "Period of investment");
    }

    public void termofinvestment(String descrip) {
        safe.safeType(termofinvestment, descrip, "Terms of Investment");
    }

    public void specificinvestor(String descrip) {
        safe.safeType(specificinvestor, descrip, "Specific Investor");
    }

    public void numberandnatureofshares(String directorName2) {

        safe.safeType(numberandnatureofshares, directorName2, "nature of shares");
    }

    public void visitingscheduleapplicable(String complainvalue) {
        safe.safeType(visitingscheduleapplicable, complainvalue, "Schedule applicable");
    }

    public void rightsofthepromoters(String descrip) {
        safe.safeType(rightsofthepromoters, descrip, "Right of Promoters");

    }

    public void agreements(String debtAmount) {
        safe.safeType(agreements, debtAmount, "Agreements");
    }

    public void Contingent(String opcCompanyName1) {
        safe.safeType(Contingent, opcCompanyName1, "Contingent");
    }
    @FindBy(xpath = "//a[@id='crossSaleNew']")
    public WebElement crossSaleNew;

    @FindBy(xpath = "//li[contains(@class, 'select2-results__option') and contains(text(), 'Fundraise-Term Sheet drafting')]")
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



