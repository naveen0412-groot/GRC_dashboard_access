package pages;

import base.BasePage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import utils.SafeActionUtils;
import utils.WaitUtils;

public class Patentsearchpage extends BasePage {

    private final WaitUtils wait;
    SafeActionUtils safe;

    public Patentsearchpage(WebDriver driver) {
        super(driver);
        this.wait = new WaitUtils(driver, 15);
        this.safe = new SafeActionUtils(driver);
    }

    @FindBy(xpath = "//input[@placeholder='Enter Brief Description of the Invention ']")
    public WebElement briefdescription;

    @FindBy(xpath = "//input[@placeholder='Enter Title of the invention']")
    public WebElement titleofinvention;

    @FindBy(xpath = "//input[@placeholder='Enter How did you come up with this invention']")
    public WebElement comeupwithininvention;

    @FindBy(xpath = "//input[@placeholder='Enter Novelty/newness (2-3 lines)']")
    public WebElement noveltynewness;

    @FindBy(xpath = "//input[@placeholder='Enter Detailed description of the invention (minimum 4 pages)']")
    public WebElement descriptionofinvention;



    @FindBy(xpath = "//input[@placeholder='Enter Advantages of your invention']")
    public WebElement advantagesofinvention;

    @FindBy(xpath = "//input[@placeholder='Enter How your invention is different from others?']")
    public WebElement differentinvention;

    @FindBy(xpath = "//input[@placeholder='Enter Essential feature or components']")
    public WebElement essentialfeature;

    @FindBy(xpath = "//input[@placeholder='Enter Prior art search']")
    public WebElement priorartsearch;

    @FindBy(xpath = "//input[@placeholder='Enter Status of your invention']")
    public WebElement statusofinvention;

    @FindBy(xpath = "//input[@placeholder='Enter Other details']")
    public WebElement otherdetails;

    @FindBy(id = "react-select-2-input")
    public WebElement dropdownInput;

    @FindBy(xpath = "//div[contains(@id, 'react-select-2-option') and text()='NO']")
    public WebElement optionNo;

    @FindBy(xpath = "//button[@class='font-medium text-[14px] rounded-[4px] flex items-center gap-[5px] p-[7px] px-[10px] relative overflow-hidden bg-[#022B50] border-[1px] border-[#022B50] text-white w-full justify-center event-track cursor-pointer hover:bg-opacity-90']")
    public WebElement page1button;

    @FindBy(xpath = "//button[@class='font-medium text-[14px] rounded-[4px] flex items-center gap-[5px] p-[7px] px-[10px] relative overflow-hidden bg-[#022B50] border-[1px] border-[#022B50] text-white w-full justify-center py-[1.2rem] event-track cursor-pointer hover:bg-opacity-90']")
    public WebElement page2button;

    @FindBy(xpath = "//button[@class='font-medium text-[14px] rounded-[4px] flex items-center gap-[5px] p-[7px] px-[10px] relative overflow-hidden bg-[#022B50] border-[1px] border-[#022B50] text-white w-full justify-center event-track cursor-pointer hover:bg-opacity-90']")
    public WebElement otherbutton;

    @FindBy(xpath = "//p[text()='Soft copy of the document to be copyright']/parent::div/parent::div/parent::div/div[2]/input")
    public WebElement softcopy;

    @FindBy(xpath = "//p[text()='Other Document(s)']/parent::div/parent::div/parent::div/div[2]/input")
    public WebElement otherdocument;

    @FindBy(xpath = "//button[@class='font-medium text-[14px] rounded-[4px] flex items-center gap-[5px] p-[7px] px-[10px] relative overflow-hidden bg-[#022B50] border-[1px] border-[#022B50] text-white w-full justify-center py-[1.2rem] event-track cursor-pointer hover:bg-opacity-90']")
    public WebElement finalbutton;



    public void briefdescription(String BriefDescription) {
        safe.safeType(briefdescription, BriefDescription, "Brief Description");
    }

    public void titleofinvention(String InventionTitle) {
        safe.safeType(titleofinvention, InventionTitle, "Invention Title");
    }

    public void comeupwithininvention(String InventionIdea) {
        safe.safeType(comeupwithininvention, InventionIdea, "Come up with invention");
    }

    public void noveltynewness(String NoveltyNewness) {
        safe.safeType(noveltynewness, NoveltyNewness, "Novel Newness");
    }

    public void descriptionofinvention(String DetailedDescription) {
        safe.safeType(descriptionofinvention, DetailedDescription, "Description");
    }

    public void page1button() {
        safe.safeClick(page1button, "Next");
    }

    public void advantagesofinvention(String InventionIdea) {
        safe.safeType(advantagesofinvention, InventionIdea, "Advantages of your invention");
    }

    public void differentinvention(String HowInventionDiffer) {
        safe.safeType(differentinvention, HowInventionDiffer, "different invention");
    }

    public void essentialfeature(String EssentialFeature) {
        safe.safeType(essentialfeature, EssentialFeature, "Essential Feature");
    }


    public void priorartsearch(String PriorArt) {
        safe.safeType(priorartsearch, PriorArt, "Prior art search");
    }

    public void statusofinvention(String InventionStatus) {
        safe.safeType(statusofinvention, InventionStatus, "Status of  your invention");
    }

    public void page2button() {
        safe.safeClick(page2button, "Next");
    }

    public void otherdetails(String NoOfInventor) {
        safe.safeType(otherdetails, NoOfInventor, "Other details");
    }

    public void selectNoAndProceed() {
        safe.safeClick(dropdownInput, "Dropdown menu");

        safe.safeClick(optionNo, "Option NO");
    }

    public void otherbutton() {
        safe.safeClick(otherbutton, "Next");
    }

    public void finalbutton() {
        safe.safeClick(finalbutton, "Process completed");
    }
    @FindBy(xpath = "//a[@id='crossSaleNew']")
    public WebElement crossSaleNew;

    @FindBy(xpath = "//li[contains(@class, 'select2-results__option') and contains(text(), 'Patent Search')]")
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
