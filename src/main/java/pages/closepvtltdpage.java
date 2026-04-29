package pages;

import base.BasePage;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import utils.SafeActionUtils;
import utils.WaitUtils;

import java.awt.*;
import java.time.Duration;
import java.util.List;

public class closepvtltdpage extends BasePage {

    private final WaitUtils wait;
    SafeActionUtils safe;

    public closepvtltdpage(WebDriver driver) throws AWTException {
        super(driver);
        this.driver = driver;
        this.wait = new WaitUtils(driver, 15);
        this.safe = new SafeActionUtils(driver);
    }

    @FindBy(xpath = "//input[@placeholder='Enter Company has operations from the date of Incorporation or in last two years']")
    public WebElement company;

    @FindBy(xpath = "//input[@id='option-The assets and liabilities must be Nil-no']")
    public WebElement assets;

    @FindBy(xpath = "//input[@placeholder='Enter Bank Account, if any, must be closed and there should be no transaction for the last one year']")
    public WebElement bankAccount;

    @FindBy(xpath = "//input[@placeholder='Enter All the registrations obtained should be closed. Eg. Shops and Establishment, Professional Tax, GST']")
    public WebElement registrations;

    @FindBy(xpath = "//button[@class='font-medium text-[14px] rounded-[4px] flex items-center gap-[5px] p-[7px] px-[10px] relative overflow-hidden bg-[#022B50] border-[1px] border-[#022B50] text-white w-full justify-center event-track cursor-pointer hover:bg-opacity-90']")
    public WebElement closeButton;

    @FindBy(xpath = "//input[@id='option-Whether upto date Annual Filings (RoC) filed -no']")
    public WebElement Annual;

    @FindBy(xpath = "//input[@id='option-Whether upto date Income Tax Return (ITR) filed-yes']")
    public WebElement Income;

    @FindBy(xpath = "//input[@id='option-Whether Stamp Paper will be procured by you-yes']")
    public WebElement StampPaper;

    @FindBy(xpath = "//button[@class='font-medium text-[14px] rounded-[4px] flex items-center gap-[5px] p-[7px] px-[10px] relative overflow-hidden bg-[#022B50] border-[1px] border-[#022B50] text-white w-full justify-center py-[1.2rem] event-track cursor-pointer hover:bg-opacity-90']")
    public WebElement secondpagenext;

    @FindBy(xpath = "//input[@placeholder='Enter Company CIN']")
    public WebElement companyCin;

    @FindBy(xpath = "//input[@placeholder='Enter Company Name']")
    public WebElement companyName;

    @FindBy(xpath = "//input[@placeholder='Enter Company Email ID']")
    public WebElement companyEmail;

    @FindBy(xpath = "//input[@placeholder='Enter Reason for closure']")
    public WebElement reason;

    @FindBy(xpath = "//input[@placeholder='Enter ROC Office']")
    public WebElement ROCOffice;

    @FindBy(xpath = "//button[@class='font-medium text-[14px] rounded-[4px] flex items-center gap-[5px] p-[7px] px-[10px] relative overflow-hidden bg-[#022B50] border-[1px] border-[#022B50] text-white w-full justify-center event-track cursor-pointer hover:bg-opacity-90']")
    public WebElement companydetailsnext;

    @FindBy(xpath = "//input[@placeholder='Enter DIN (Director Identification No)']")
    public WebElement DIN;

    @FindBy(xpath = "//input[@placeholder='Enter Name of the Director']")
    public WebElement directorname;

    @FindBy(xpath = "//input[@placeholder='Enter Father Name']")
    public WebElement fathername;

    @FindBy(xpath = "//input[@placeholder='Enter DIN (Director Identification No)']")
    public WebElement direct_2DIN;

    @FindBy(xpath = "//input[@placeholder='Enter Name of the Director']")
    public WebElement director_2_name;

    @FindBy(xpath = "//input[@placeholder='Enter Father Name']")
    public WebElement Dir_2fathername;


    @FindBy(xpath = "//input[@placeholder='Enter Name of the Shareholder']")
    public WebElement shareholder2name;


    @FindBy(xpath = "//input[@placeholder='Enter Address']")
    public WebElement director1_address;

    @FindBy(xpath = "//input[@placeholder='Enter Place of residence']")
    public WebElement Director1_residence;

    @FindBy(xpath = "//div[@id='react-select-2-placeholder']")
    public WebElement director1_gender;

    @FindBy(xpath = "//div[@id='react-select-3-placeholder']")
    public WebElement director2_gender;

    @FindBy(xpath = "//button[@class='font-medium text-[14px] rounded-[4px] flex items-center gap-[5px] p-[7px] px-[10px] relative overflow-hidden bg-[#022B50] border-[1px] border-[#022B50] text-white w-full justify-center py-[1.2rem] event-track cursor-pointer hover:bg-opacity-90']")
    public WebElement director2next;


    @FindBy(xpath = "//input[@placeholder='Enter Name of the Shareholder']")
    public WebElement shareholdername;

    @FindBy(xpath = "//input[@placeholder='Enter No of shares held']")
    public WebElement sharehold;

    @FindBy(xpath = "//input[@placeholder='Enter No of shares held']")
    public WebElement sharehold2;

    @FindBy(xpath = "//input[@placeholder='Enter Percentage of shares held']")
    public WebElement sharepercentage;

    @FindBy(xpath = "//input[@placeholder='Enter Fathers Name']")
    public WebElement sharefathername;

    @FindBy(xpath = "//input[@placeholder='Enter Address']")
    public WebElement shareaddress;

    @FindBy(xpath = "//input[@placeholder='Enter Place of residence']")
    public WebElement shareplaceofresidence;

    @FindBy(xpath = "//input[@placeholder='Board Meeting Date']")
    public WebElement Boardmeeting;

    @FindBy(xpath = "//input[@placeholder='SOA Date']")
    public WebElement soadate;


    @FindBy(xpath = "//div[@id='react-select-9-input']")
    public WebElement shareholder1gender;

    @FindBy(xpath = "//div[@id='react-select-10-input']")
    public WebElement shareholder2_gender;


    public void setCompany(String bankifsc) {
        safe.safeType(company, bankifsc, "Company name");
    }

    public void setregistration(String roadstreet) {
        safe.safeType(registrations, roadstreet, "registration number");
    }

    public void bank(String descrip) {
        safe.safeType(bankAccount, descrip, "Bank account");
    }

    public void nextbuttonnt() {
        safe.safeClick(closeButton, "Next Button");
    }

    public void noradio() {
        safe.safeClick(assets, "No Radio Button");
    }

    public void annualandroc() {
        safe.safeClick(Annual, "No Radio Button");
    }

    public void incometax() {
        safe.safeClick(Income, "Yes Radio Button");
    }

    public void setStampPaper() {
        safe.safeClick(StampPaper, "Yes Radio Button");
    }

    public void closurenext() {
        safe.safeClick(secondpagenext, "closure next Button");
    }

    public void companyCin(String CompanyCIN) {
        safe.safeType(companyCin, CompanyCIN, "CIN no");
    }

    public void companyName(String CompanyName) {
        safe.safeType(companyName, CompanyName, "Company name");
    }

    public void companyEmail(String companyemail) {
        safe.safeType(companyEmail, companyemail, "Company email");
    }

    public void reason(String companynamemsme) {
        safe.safeType(reason, companynamemsme, "Reason");
    }

    public void ROCOffice(String companyvaluemsme) {
        safe.safeType(ROCOffice, companyvaluemsme, "ROC office");
    }

    public void companydetailsnext() {
        safe.safeClick(companydetailsnext, "company details next Button");
    }

    public void director1_gender(String gender) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        ((JavascriptExecutor) driver)
                .executeScript("arguments[0].click();", director1_gender);

        List<WebElement> inputs = driver.findElements(
                By.xpath("//input[contains(@id,'react-select') and contains(@id,'input')]")
        );

        WebElement activeInput = inputs.stream()
                .filter(WebElement::isDisplayed)
                .findFirst()
                .orElseThrow(() -> new RuntimeException("❌ Gender input not visible"));

        activeInput.sendKeys(gender);
        activeInput.sendKeys(Keys.ENTER);
    }


    public void director2_gender(String Director2Gender) {

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        ((JavascriptExecutor) driver)
                .executeScript("arguments[0].click();", director2_gender);

        List<WebElement> inputs = driver.findElements(
                By.xpath("//input[contains(@id,'react-select') and contains(@id,'input')]")
        );

        WebElement activeInput = inputs.stream()
                .filter(WebElement::isDisplayed)
                .findFirst()
                .orElseThrow(() -> new RuntimeException("❌ Gender input not visible"));

        activeInput.sendKeys(Director2Gender);
        activeInput.sendKeys(Keys.ENTER);
    }


    public void direct_2DIN(String din2) {
        safe.safeType(direct_2DIN, din2, "ROC office");
    }

    public void director_2_name(String directorName2) {
        safe.safeType(director_2_name, directorName2, "ROC office");
    }

    public void Dir_2fathername(String FatherName2) {
        safe.safeType(Dir_2fathername, FatherName2, "ROC office");
    }

    public void director1_address(String Address1) {
        safe.safeType(director1_address, Address1, "ROC office");
    }

    public void Director1_residence(String CurrentOccupation2) {
        safe.safeType(Director1_residence, CurrentOccupation2, "ROC office");
    }

    public void direct_1DIN(String din1) {
        safe.safeType(DIN, din1, "DIN");
    }

    public void directorname(String directorName1) {
        safe.safeType(directorname, directorName1, "Director name");
    }

    public void fathername(String FatherName1) {
        safe.safeType(fathername, FatherName1, "Father name");
    }

    public void director2next() {
        safe.safeClick(director2next, "Director 2 next Button");
    }

    public void shareholdername(String OldName) {

        safe.safeType(shareholdername, OldName, "Share holder 1 name");
    }

    public void shareholder2name(String NewName) {

        safe.safeType(shareholder2name, NewName, "Share holder 2 name");
    }


    public void sharehold(String Sharehold1) {
        safe.safeType(sharehold, Sharehold1, "Shareholder 1 holdings");
    }

    public void sharehold2(String Sharehold2) {
        safe.safeType(sharehold2, Sharehold2, "Shareholder 2 holdings");
    }


    public void sharepercentage(String Sharepercentage1) {
        safe.safeType(sharepercentage, Sharepercentage1, "Shareholder 1 percentage");
    }

    public void sharefathername(String shareholderfathername) {
        safe.safeType(sharefathername, shareholderfathername, "shareholder 1 Father name");
    }

    public void shareaddress(String FullAddress) {
        safe.safeType(shareaddress, FullAddress, "Address");
    }

    public void shareplaceofresidence(String AddressLine1) {
        safe.safeType(shareplaceofresidence, AddressLine1, "Shareholder 1 residence");
    }

    public void Boardmeeting(String Boarddate) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        try {
            wait.until(ExpectedConditions.elementToBeClickable(Boardmeeting)).click();

            wait.until(ExpectedConditions.visibilityOfElementLocated(
                    By.cssSelector(".react-datepicker")
            ));

            String[] parts = Boarddate.split("-");
            String day = parts[0];
            String month = parts[1]; // 01–12
            String year = parts[2];

            WebElement yearDropdown = wait.until(ExpectedConditions.elementToBeClickable(
                    By.xpath("//select[contains(@class,'year-select')]")
            ));
            new Select(yearDropdown).selectByVisibleText(year);

            WebElement monthDropdown = wait.until(ExpectedConditions.elementToBeClickable(
                    By.xpath("//select[contains(@class,'month-select')]")
            ));
            int monthIndex = Integer.parseInt(month) - 1;
            new Select(monthDropdown).selectByIndex(monthIndex);

            WebElement dayElement = wait.until(ExpectedConditions.elementToBeClickable(
                    By.xpath("//div[contains(@class,'react-datepicker__day') and text()='" + Integer.parseInt(day) + "']")
            ));
            dayElement.click();

        } catch (Exception e) {
            throw new RuntimeException("❌ Director DOB selection failed → " + e.getMessage());
        }
    }

    public void soadate(String SOAdate) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        try {
            wait.until(ExpectedConditions.elementToBeClickable(soadate)).click();

            wait.until(ExpectedConditions.visibilityOfElementLocated(
                    By.cssSelector(".react-datepicker")
            ));

            String[] parts = SOAdate.split("-");
            String day = parts[0];
            String month = parts[1]; // 01–12
            String year = parts[2];

            WebElement yearDropdown = wait.until(ExpectedConditions.elementToBeClickable(
                    By.xpath("//select[contains(@class,'year-select')]")
            ));
            new Select(yearDropdown).selectByVisibleText(year);

            WebElement monthDropdown = wait.until(ExpectedConditions.elementToBeClickable(
                    By.xpath("//select[contains(@class,'month-select')]")
            ));
            int monthIndex = Integer.parseInt(month) - 1;
            new Select(monthDropdown).selectByIndex(monthIndex);

            WebElement dayElement = wait.until(ExpectedConditions.elementToBeClickable(
                    By.xpath("//div[contains(@class,'react-datepicker__day') and text()='" + Integer.parseInt(day) + "']")
            ));
            dayElement.click();

        } catch (Exception e) {
            throw new RuntimeException("❌ Director DOB selection failed → " + e.getMessage());
        }
    }

    public void shareholder1gender(String gender) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));
        Actions actions = new Actions(driver);

        try {

            WebElement dropdown = wait.until(ExpectedConditions.elementToBeClickable(
                    By.xpath("//div[contains(@class, 'placeholder') and contains(text(), 'Gender')]/ancestor::div[contains(@class, 'control')]")
            ));

            actions.moveToElement(dropdown).click().perform();
            logger.info("Clicked Gender dropdown container");


            actions.sendKeys(gender).perform();
            logger.info("Typed gender value: " + gender);

            WebElement option = wait.until(ExpectedConditions.elementToBeClickable(
                    By.xpath("//div[contains(@class, '-option') and (text()='" + gender + "' or contains(., '" + gender + "'))]")
            ));
            option.click();
            logger.info("Successfully selected gender: " + gender);

        } catch (Exception e) {
            logger.error("❌ Failed to select gender: " + e.getMessage());
            actions.sendKeys(Keys.ENTER).perform();
        }
    }

    public void shareholder2_gender(String gender) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));
        Actions actions = new Actions(driver);

        try {

            WebElement dropdown = wait.until(ExpectedConditions.elementToBeClickable(
                    By.xpath("//div[contains(@class, 'placeholder') and contains(text(), 'Gender')]/ancestor::div[contains(@class, 'control')]")
            ));

            actions.moveToElement(dropdown).click().perform();
            logger.info("Clicked Gender dropdown container");

            actions.sendKeys(gender).perform();
            logger.info("Typed gender value: " + gender);

            WebElement option = wait.until(ExpectedConditions.elementToBeClickable(
                    By.xpath("//div[contains(@class, '-option') and (text()='" + gender + "' or contains(., '" + gender + "'))]")
            ));
            option.click();
            logger.info("Successfully selected gender: " + gender);

        } catch (Exception e) {
            logger.error("❌ Failed to select gender: " + e.getMessage());
            actions.sendKeys(Keys.ENTER).perform();
        }
    }

    @FindBy(xpath = "//a[@id='crossSaleNew']")
    public WebElement crossSaleNew;

    @FindBy(xpath = "//li[contains(@class, 'select2-results__option') and contains(text(), 'Close your Private Limited Company')]")
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

