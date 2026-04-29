package pages;

import base.BasePage;
import org.openqa.selenium.*;
import org.openqa.selenium.support.FindBy;
import utils.SafeActionUtils;
import utils.WaitUtils;

import java.awt.*;


public class Ipinfringepage extends BasePage {

    private final WaitUtils wait;
    SafeActionUtils safe;

    public Ipinfringepage(WebDriver driver) throws AWTException {
        super(driver);
        this.driver = driver;
        this.wait = new WaitUtils(driver, 15);
        this.safe = new SafeActionUtils(driver);
    }

    @FindBy(xpath = "//input[@placeholder='Enter Opponent Applicant Number']")
    public WebElement applicantNumber;

    @FindBy(xpath = "//input[@placeholder='Enter Infringer Name']")
    public WebElement infringerName;

    @FindBy(xpath = "//input[@placeholder='Enter Infringer Address']")
    public WebElement infringerAddress;

    @FindBy(xpath = "//input[@placeholder='Enter Application Number']")
    public WebElement applicationNumber;

    @FindBy(xpath = "//input[@placeholder='Enter Applicant Name']")
    public WebElement applicantName;

    @FindBy(xpath = "//input[@placeholder='Enter Applicant Address']")
    public WebElement applicantAddress;

    @FindBy(xpath = "//input[@placeholder='Enter Client Name ']")
    public WebElement clientName;

    @FindBy(xpath = "//input[@placeholder='Enter Client Address']")
    public WebElement clientAddress;

    @FindBy(xpath = "//p[text()='Certificate of incorporation']/parent::div/parent::div/parent::div/div[2]/input")
    public WebElement coi;

    @FindBy(xpath = "//button[@class='font-medium text-[14px] rounded-[4px] flex items-center gap-[5px] p-[7px] px-[10px] relative overflow-hidden bg-[#022B50] border-[1px] border-[#022B50] text-white w-full justify-center event-track cursor-pointer hover:bg-opacity-90']")
    public WebElement infringebutton;

    @FindBy(xpath = "//button[@class='font-medium text-[14px] rounded-[4px] flex items-center gap-[5px] p-[7px] px-[10px] relative overflow-hidden bg-[#022B50] border-[1px] border-[#022B50] text-white w-full justify-center event-track cursor-pointer hover:bg-opacity-90']")
    public WebElement applicantbutton;

    @FindBy(xpath = "//button[@class='font-medium text-[14px] rounded-[4px] flex items-center gap-[5px] p-[7px] px-[10px] relative overflow-hidden bg-[#022B50] border-[1px] border-[#022B50] text-white w-full justify-center py-[1.2rem] event-track cursor-pointer hover:bg-opacity-90']")
    public WebElement clientbutton;

    @FindBy(xpath = "//button[@class='font-medium text-[14px] rounded-[4px] flex items-center gap-[5px] p-[7px] px-[10px] relative overflow-hidden bg-[#022B50] border-[1px] border-[#022B50] text-white w-full justify-center py-[1.2rem] event-track cursor-pointer hover:bg-opacity-90']")
    public WebElement uploadbutton;


    public void applicantNumber(String DirectorMobile2) {safe.safeType(applicantNumber, DirectorMobile2, "Applicant Number");
    }

    public void infringebutton() {
        safe.safeClick(infringebutton, "Infringe Button");
    }

    public void applicantbutton() {
        safe.safeClick(applicantbutton, "Member Next Button");
    }

    public void clientbutton() {
        safe.safeClick(clientbutton, "Client Next Button");
    }


    public void uploadbutton() {safe.safeClick(uploadbutton, "Process completed successfully");
    }

    public void infringerAddress(String ApplicantAddress) {
        safe.safeType(infringerAddress, ApplicantAddress, "Infringer Address");
    }

    public void infringerName(String ApplicantName) {
        safe.safeType(infringerName, ApplicantName, "Infringer Name");

    }

    public void applicationNumber(String tmApplicationNumber) {
        safe.safeType(applicationNumber, tmApplicationNumber, "Infringer Name");
    }

    public void applicantName(String inventorName) {
        safe.safeType(applicantName, inventorName, "Infringer Name");
    }

    public void applicantAddress(String addressLine1) {
        safe.safeType(applicantAddress, addressLine1, "Infringer Name");
    }

    public void clientName(String companyName1) {
        safe.safeType(clientName, companyName1, "Infringer Name");
    }

    public void clientAddress(String jurisdiction) {safe.safeType(clientAddress, jurisdiction, "Infringer Name");
    }
    @FindBy(xpath = "//a[@id='crossSaleNew']")
    public WebElement crossSaleNew;

    @FindBy(xpath = "//li[contains(@class, 'select2-results__option') and contains(text(), 'IP Infringement')]")
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




