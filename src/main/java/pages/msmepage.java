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

import java.time.Duration;
import java.util.List;

public class msmepage extends BasePage {

    private final WaitUtils wait;
    SafeActionUtils safe;

    public msmepage(WebDriver driver) {
        super(driver);
        this.wait = new WaitUtils(driver, 15);
        this.safe = new SafeActionUtils(driver);
    }

    @FindBy(xpath = "//button[@class='font-medium text-[14px] rounded-[4px] flex items-center gap-[5px] bg-[none] p-[7px] px-[10px] relative overflow-hidden border-[#E6EAEE] border-[1px] w-full justify-center py-[1.2rem] border-none event-track cursor-pointer hover:bg-opacity-90']")
    public WebElement noitsanothercompany;

    @FindBy(xpath = "//div[@id='react-select-2-placeholder']")
    public WebElement companytype;


    @FindBy(xpath = "//input[@placeholder='Enter Company Name']")
    public WebElement company_name;

    @FindBy(xpath = "//input[@placeholder='Enter Mobile number']")
    public WebElement company_mobile;

    @FindBy(xpath = "//input[@placeholder='Enter Email ID']")
    public WebElement company_emailid;

    @FindBy(xpath = "//input[@placeholder='Pick Date of commencement']")
    public WebElement company_dob;

    @FindBy(xpath = "//label[contains(text(),'Nature of Business')]/following::div[contains(@class,'control')][1]")
    public WebElement natureofbusiness;

    @FindBy(xpath = "//input[@id='option-Do you have GSTIN ?-no']")
    public WebElement gstin;

    @FindBy(xpath = "//input[@id='option-Major Activity of Unit-Manufacturing']")
    public WebElement manufacturing;

    @FindBy(xpath = "//button[@class='font-medium text-[14px] rounded-[4px] flex items-center gap-[5px] p-[7px] px-[10px] relative overflow-hidden bg-[#022B50] border-[1px] border-[#022B50] text-white w-full justify-center py-[1.2rem] event-track cursor-pointer hover:bg-opacity-90']\n")
    public WebElement nextpage1;

    @FindBy(xpath = "//input[@placeholder='Enter Flat/Door number']")
    public WebElement address1;

    @FindBy(xpath = "//input[@placeholder='Enter Area/Locality name']")
    public WebElement arealocality;

    @FindBy(xpath = "//input[@name='nearest_police_station']")
    public WebElement Road_street;

    @FindBy(xpath = "//input[@placeholder='Enter Pin code']")
    public WebElement pincode;

    @FindBy(xpath = "//input[@placeholder='Select City']")
    public WebElement citycompany;

    @FindBy(xpath = "//div[@id='react-select-4-placeholder']")
    public WebElement statecompany;

    @FindBy(xpath = "//div[@id='react-select-5-placeholder']")
    public WebElement districtcompany;

    @FindBy(xpath = "//input[@placeholder='Enter Account number']")
    public WebElement BankAcc;

    @FindBy(xpath = "//input[@placeholder='Enter IFSC code']")
    public WebElement IFSCcode;

    @FindBy(xpath = "//button[@class='font-medium text-[14px] rounded-[4px] flex items-center gap-[5px] p-[7px] px-[10px] relative overflow-hidden bg-[#022B50] border-[1px] border-[#022B50] text-white w-full justify-center py-[1.2rem] event-track cursor-pointer hover:bg-opacity-90']")
    public WebElement addressnext;

    @FindBy(xpath = "//input[@placeholder='Enter name as per AADHAR']")
    public WebElement aadharno;

    @FindBy(xpath = "//input[@placeholder='Enter email']")
    public WebElement memberemail;

    @FindBy(xpath = "//input[@placeholder='Enter mobile number']")
    public WebElement membermobile;

    @FindBy(xpath = "//input[@placeholder='Enter Date of Birth']")
    public WebElement memberdob;

    @FindBy(xpath = "//button[@class='font-medium text-[14px] rounded-[4px] flex items-center gap-[5px] p-[7px] px-[10px] relative overflow-hidden bg-[#022B50] border-[1px] border-[#022B50] text-white w-full justify-center py-[1.2rem] event-track cursor-pointer hover:bg-opacity-90']")
    public WebElement directornext;

    @FindBy(xpath = "//p[text()='Aadhar card front']/parent::div/parent::div/parent::div/div[2]/input")
    public WebElement selectAadharFront;

    @FindBy(xpath = "//p[text()='Aadhar card back']/parent::div/parent::div/parent::div/div[2]/input")
    public WebElement selectAadharBack;


    @FindBy(xpath = "//p[text()='PAN card']/parent::div/parent::div/parent::div/div[2]/input")
    public WebElement uploadPAN;


    public void noitsanothercompany() {
        logger.info("Clicking → No it's another company");
        safe.safeClick(noitsanothercompany, "No it's for another company");
    }

    public void accountno(String account) {
        logger.info("Entering bank account number");
        safe.safeType(BankAcc, account, "bank account");
    }

    public void ifsc(String bankifsc) {
        logger.info("Entering IFSC code → {}", bankifsc);
        safe.safeType(IFSCcode, bankifsc, "ifsc code");
    }

    public void gstinvalue() {
        logger.info("Selecting GSTIN option → No");
        safe.safeClick(gstin, "No");
    }

    public void manufacture() {
        logger.info("Selecting Manufacture option");
        safe.safeClick(manufacturing, "manufacture");
    }

    public void nextcta() {
        logger.info("Clicking Next CTA");
        safe.safeClick(nextpage1, "next button");
    }


    public void directornext() {

        safe.safeClick(directornext, "next button");

    }

    public void companynext() {

        safe.safeClick(addressnext, "next button");

    }


    public void Comptype(String companyType) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        Actions actions = new Actions(driver);

        try {
            WebElement dropdown = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//div[contains(@class, 'p72jfa-control')]")));

            dropdown.click();
            Thread.sleep(1000);

            actions.sendKeys(companyType).perform();
            logger.info("Typed company type: " + companyType);


            WebElement option = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//div[contains(@class, '-option') and (text()='" + companyType + "' or contains(., '" + companyType + "'))]")));

            option.click();
            logger.info("Successfully selected: " + companyType);

        } catch (Exception e) {
            logger.error("Failed to select company type: " + e.getMessage());
            actions.sendKeys(Keys.ENTER).perform();
        }
    }


    public void com_name(String companynamemsme) {
        safe.safeType(company_name, companynamemsme, "company name");
    }

    public void com_mobile(String companymobile) {
        safe.safeType(company_mobile, companymobile, "mobile");
    }

    public void com_email(String companyemail) {
        safe.safeType(company_emailid, companyemail, "emailid");

    }

    public void com_dob(String dob) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        try {

            ((JavascriptExecutor) driver).executeScript("arguments[0].click();", company_dob);

            wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[contains(@class,'calendar') or contains(@class,'react-datepicker')]")));

            String[] parts = dob.split("-");
            String day = parts[0];
            String month = parts[1];
            String year = parts[2];

            WebElement yearDropdown = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//select[contains(@class,'year-select')]")));
            yearDropdown.click();
            yearDropdown.findElement(By.xpath("//option[text()='" + year + "']")).click();

            WebElement monthDropdown = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//select[contains(@class,'month-select')]")));
            monthDropdown.click();
            monthDropdown.findElement(By.xpath("//option[@value='" + (Integer.parseInt(month) - 1) + "']")).click();

            WebElement dayElement = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//div[contains(@class,'day') and text()='" + Integer.parseInt(day) + "']")));
            dayElement.click();

        } catch (Exception e) {
            throw new RuntimeException("❌ DOB selection failed → " + e.getMessage());
        }
    }

    public void nature(String natureBusiness) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        Actions actions = new Actions(driver);

        try {

            WebElement dropdown = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//div[contains(@class, 'control')]//following::div[contains(text(), 'Nature')]")));

            actions.moveToElement(dropdown).click().perform();
            Thread.sleep(1000);

            actions.sendKeys(natureBusiness).perform();
            logger.info("Typed nature of business: " + natureBusiness);

            WebElement option = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//div[contains(@class, '-option') and contains(., '" + natureBusiness + "')]")));
            option.click();
            logger.info("Successfully selected nature: " + natureBusiness);

        } catch (Exception e) {
            logger.error("Nature of Business selection failed: " + e.getMessage());
            actions.sendKeys(Keys.ENTER).perform();
        }
    }

    public void flatno(String flatno) {
        safe.safeType(address1, flatno, "flat no");
    }

    public void area(String area) {
        safe.safeType(arealocality, area, "area locality");
    }

    public void road(String Roadstreet) {
        safe.safeType(Road_street, Roadstreet, "Road street");
    }


    public void pincodecomp(String Pincode) {
        safe.safeType(pincode, Pincode, "Enter pincode");
    }

    public void citycomp(String city) {
        safe.safeType(citycompany, city, " city ");
    }

    public void statecompany(String state) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", statecompany);

        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", statecompany);

        WebElement input = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//input[contains(@id,'react-select') and contains(@id,'input')]")));
        input.sendKeys(state);
        input.sendKeys(Keys.ENTER);
    }

    public void Districtcomp(String district) {

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", districtcompany);

        List<WebElement> inputs = driver.findElements(By.xpath("//input[contains(@id,'react-select') and contains(@id,'input')]"));

        WebElement districtInput = inputs.get(1);

        districtInput.sendKeys(district);
        districtInput.sendKeys(Keys.ENTER);
    }

    @FindBy(xpath = "//input[@id='dob']")
    private WebElement directordateField;

    @FindBy(xpath = "(//div[@class='react-datepicker__week']/div[text()='1'])[1]")
    private WebElement selectDate1;

    public void directordate() {
        safe.safeClick(directordateField, "DOB calendar field");
        safe.safeClick(selectDate1, "DOB value");
    }

    public void aadhar(String Aadhar1) {
        safe.safeType(aadharno, Aadhar1, " aadhar no ");
    }


    public void memberemail(String DirectorEmail2) {
        safe.safeType(memberemail, DirectorEmail2, " aadhar no ");
    }

    public void membermobile(String DirectorMobile2) {
        safe.safeType(membermobile, DirectorMobile2, " aadhar no ");
    }

    public void directorDob(String dob) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        try {

            wait.until(ExpectedConditions.elementToBeClickable(memberdob)).click();

            wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".react-datepicker")));

            String[] parts = dob.split("-");
            String day = parts[0];
            String month = parts[1]; // 01–12
            String year = parts[2];

            WebElement yearDropdown = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//select[contains(@class,'year-select')]")));
            new Select(yearDropdown).selectByVisibleText(year);

            WebElement monthDropdown = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//select[contains(@class,'month-select')]")));

            int monthIndex = Integer.parseInt(month) - 1;
            new Select(monthDropdown).selectByIndex(monthIndex);

            WebElement dayElement = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//div[contains(@class,'react-datepicker__day') and text()='" + Integer.parseInt(day) + "']")));
            dayElement.click();

        } catch (Exception e) {
            throw new RuntimeException("❌ Director DOB selection failed → " + e.getMessage());
        }
    }

    public void selectSocialCategory(String social) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        JavascriptExecutor js = (JavascriptExecutor) driver;

        try {
            WebElement inputField = wait.until(ExpectedConditions.presenceOfElementLocated(
                    By.cssSelector("input[id*='react-select-'][id$='-input']")
            ));
            js.executeScript("arguments[0].click();", inputField);
            inputField.sendKeys(social);
            logger.info("Typed '" + social + "' into the search field.");

            String optionXpath = String.format(
                    "//div[contains(@class, '-option') and contains(normalize-space(), '%s')]",
                    social
            );

            WebElement optionToSelect = wait.until(ExpectedConditions.elementToBeClickable(By.xpath(optionXpath)));

            js.executeScript("arguments[0].click();", optionToSelect);
            logger.info("✔ Successfully selected social category: " + social);

        } catch (Exception e) {
            List<WebElement> options = driver.findElements(By.cssSelector("div[class*='-option']"));
            if(options.isEmpty()){
                logger.error("❌ No options found in the dropdown DOM at all.");
            } else {
                for(WebElement opt : options) {
                    logger.info("Found available option: " + opt.getText());
                }
            }
            throw new RuntimeException("❌ Failed to select social category: " + social, e);
        }
    }

    @FindBy(xpath = "//a[@id='crossSaleNew']")
    public WebElement crossSaleNew;

    @FindBy(xpath = "//li[contains(@class, 'select2-results__option') and contains(text(), 'Apply for SSI / MSME Registration')]")
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
