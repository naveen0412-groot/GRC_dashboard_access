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

import static utils.AllureLoggerUtils.logToAllure;


public class Ngodisclosepage extends BasePage {

    private final WaitUtils wait;
    SafeActionUtils safe;

    public Ngodisclosepage(WebDriver driver) throws AWTException {
        super(driver);
        this.driver = driver;
        this.wait = new WaitUtils(driver, 15);
        this.safe = new SafeActionUtils(driver);
    }

    @FindBy(xpath = "//input[@placeholder='Enter Full Legal Name ']")
    public WebElement legalfullname;

    @FindBy(xpath = "//input[@placeholder='Enter Full Legal Address']")
    public WebElement legaladdress;

    @FindBy(xpath = "//input[@placeholder='Enter Primary Name']")
    public WebElement Primaryname;

    @FindBy(xpath = "//input[@placeholder='Enter Primary  Email']")
    public WebElement Primaryemail;

    @FindBy(xpath = "//input[@placeholder='Enter Primary Contact Number']")
    public WebElement primarycontact;

    @FindBy(xpath = "//input[@name='receiving_party_name']")
    public WebElement partyname;

    @FindBy(xpath = "//input[@name='receiving_party_address']")
    public WebElement partyaddress;

    @FindBy(xpath = "//input[@name='receiving_party_primary_name']")
    public WebElement partyprimaryname;

    @FindBy(xpath = "//input[@name='receiving_party_email']")
    public WebElement partyemail;

    @FindBy(xpath = "//input[@name='receiving_party_contact']")
    public WebElement partycontact;

    @FindBy(xpath = "//input[@id='react-select-2-input']")
    public WebElement nda;

    @FindBy(xpath = "//input[@placeholder='Enter General Definition of Confidential Information']")
    public WebElement confidentialinformation;

    @FindBy(xpath = "//input[@placeholder='Enter Specific Types of Information Included']")
    public WebElement typesofincluded;

    @FindBy(xpath = "//input[@placeholder='Enter Methods of Disclosure']")
    public WebElement methodsofdisclosure;

    @FindBy(xpath = "//input[@name='agreement_term']")
    public WebElement termofagreement;

    @FindBy(xpath = "//button[@class='font-medium text-[14px] rounded-[4px] flex items-center gap-[5px] p-[7px] px-[10px] relative overflow-hidden bg-[#022B50] border-[1px] border-[#022B50] text-white w-full justify-center event-track cursor-pointer hover:bg-opacity-90']")
    public WebElement partydetailsbutton;

    @FindBy(xpath = "//button[@class='font-medium text-[14px] rounded-[4px] flex items-center gap-[5px] p-[7px] px-[10px] relative overflow-hidden bg-[#022B50] border-[1px] border-[#022B50] text-white w-full justify-center py-[1.2rem] event-track cursor-pointer hover:bg-opacity-90']")
    public WebElement ndabutton;

    @FindBy(xpath = "//button[@class='font-medium text-[14px] rounded-[4px] flex items-center gap-[5px] p-[7px] px-[10px] relative overflow-hidden bg-[#022B50] border-[1px] border-[#022B50] text-white w-full justify-center py-[1.2rem] event-track cursor-pointer hover:bg-opacity-90']")
    public WebElement recieverbutton;

    @FindBy(xpath = "//button[@class='font-medium text-[14px] rounded-[4px] flex items-center gap-[5px] p-[7px] px-[10px] relative overflow-hidden bg-[#022B50] border-[1px] border-[#022B50] text-white w-full justify-center event-track cursor-pointer hover:bg-opacity-90']")
    public WebElement confidentialbutton;

    @FindBy(xpath = "//button[@class='font-medium text-[14px] rounded-[4px] flex items-center gap-[5px] p-[7px] px-[10px] relative overflow-hidden bg-[#022B50] border-[1px] border-[#022B50] text-white w-full justify-center event-track cursor-pointer hover:bg-opacity-90']")
    public WebElement termbutton;

    public void legalfullname(String ApplicantType) {
        safe.safeType(legalfullname, ApplicantType, "Legal full name");
    }

    public void partydetailsbutton() {
        safe.safeClick(partydetailsbutton, "Party details Button");
    }


    public void recieverbutton() {
        safe.safeClick(recieverbutton, "Receiver Button");
    }


    public void ndabutton() {
        safe.safeClick(ndabutton, "NDA button");
    }


    public void legaladdress(String panOfDirectors) {
        safe.safeType(legaladdress, panOfDirectors, "Legal address");
    }

    public void Primaryname(String directorName1) {
        safe.safeType(Primaryname, directorName1, "Primary name");
    }

    public void Primaryemail(String directorEmail1) {
        safe.safeType(Primaryemail, directorEmail1, "Primary email");
    }

    public void primarycontact(String directorMobile1) {
        safe.safeType(primarycontact, directorMobile1, "Primary contact");
    }

    public void partyname(String companyName) {
        safe.safeType(partyname, companyName, "Party name");
    }

    public void partyaddress(String address1) {
        safe.safeType(partyaddress, address1, "Party address");
    }

    public void partyprimaryname(String directorName1) {
        safe.safeType(partyprimaryname, directorName1, "Party primary name");
    }

    public void partyemail(String directorEmail1) {
        safe.safeType(partyemail, directorEmail1, "Party email");
    }

    public void partycontact(String directorMobile1) {
        safe.safeType(partycontact, directorMobile1, "Party contact");
    }

    public void nda(String typeofNDA) throws InterruptedException {
        logger.info("===== NDA Selection Process Started =====");

        try {

            safe.safeType(nda, typeofNDA, "NDA Input Field");
            logToAllure("Input", "Typed: " + typeofNDA);
            Thread.sleep(1000);
            String optionXpath = String.format("//div[contains(@class, '-option') and text()='%s']", typeofNDA);
            WebElement option = driver.findElement(By.xpath(optionXpath));
            option.click();
            logToAllure("Selection", "Successfully selected: " + typeofNDA);
            ScreenshotUtils.attachScreenshotToAllure(driver, "NDA_Selected_Value");
            logger.info("===== NDA Selection Completed =====");
        } catch (Exception e) {
            logger.error("❌ Failed to select NDA type: " + e.getMessage());
            ScreenshotUtils.attachScreenshotToAllure(driver, "Error_NDA_Selection");
            throw e;
        }
    }

    public void confidentialinformation(String state) {
        safe.safeType(confidentialinformation, state, "Confidential information");
    }

    public void typesofincluded(String naturebusiness) {
        safe.safeType(typesofincluded, naturebusiness, "Typesofincluded");
    }

    public void methodsofdisclosure(String subDivision) {
        safe.safeType(methodsofdisclosure, subDivision, "Method of closure");
    }

    public void confidentialbutton() {
        safe.safeClick(confidentialbutton, "Confidential button");
    }

    public void termofagreement(String State) {
        safe.safeType(termofagreement, State, "Term of membership");
    }

    public void termbutton() {
        safe.safeClick(termbutton, "Term of Agreement button");
    }

    @FindBy(xpath = "//input[@id='react-select-3-input']")
    public WebElement CertificationDestructionInput;

    public void CertificationDestruction(String certificationDestruct) throws InterruptedException {
        logger.info("===== Certification of Destruction Process Started =====");

        try {

            safe.safeType(CertificationDestructionInput, certificationDestruct.trim(), "Certification Input Field");
            logToAllure("Input", "Typed: " + certificationDestruct);

            Thread.sleep(1500);

            CertificationDestructionInput.sendKeys(Keys.ARROW_DOWN);
            Thread.sleep(500);
            CertificationDestructionInput.sendKeys(Keys.ENTER);

            logToAllure("Selection", "Successfully selected via keyboard: " + certificationDestruct);
            ScreenshotUtils.attachScreenshotToAllure(driver, "Certification_Selected_Value");
            logger.info("===== Certification Completed =====");

        } catch (Exception e) {
            logger.error("❌ Failed to select Certification: " + e.getMessage());
            ScreenshotUtils.attachScreenshotToAllure(driver, "Error_Certification_Selection");

            try {
                String optionXpath = String.format("//div[contains(@class, '-option') and normalize-space()='%s']", certificationDestruct.trim());
                driver.findElement(By.xpath(optionXpath)).click();
            } catch (Exception fallbackEx) {
                throw e;
            }
        }
    }
    @FindBy(xpath = "//a[@id='crossSaleNew']")
    public WebElement crossSaleNew;

    @FindBy(xpath = "//li[contains(@class, 'select2-results__option') and contains(text(), 'Non-Disclosure Agreement')]")
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