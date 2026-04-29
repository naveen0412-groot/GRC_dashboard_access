package pages;

import base.BasePage;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.interactions.MoveTargetOutOfBoundsException;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import utils.ScreenshotUtils;
import utils.WaitUtils;

import java.time.Duration;

public class UploadDocumentPage extends BasePage {

    private final WaitUtils wait;

    public UploadDocumentPage(WebDriver driver) {
        super(driver);
        this.wait = new WaitUtils(driver, 30);
    }

    //Document xpath
    @FindBy(xpath = "//p[text()='PAN card']/parent::div/parent::div/parent::div/div[2]/input")
    public WebElement PANUpload;

    @FindBy(xpath = "//p[text()='Passport size photo']/parent::div/parent::div/parent::div/div[2]/input")
    public WebElement PassportSize;

    @FindBy(xpath = "//p[text()='Certificate of incorporation']/parent::div/parent::div/parent::div/div[2]/input")
    public WebElement COIUpload;

    @FindBy(xpath = "//p[text()='MOA']/parent::div/parent::div/parent::div/div[2]/input")
    public WebElement MOAUpload;

    @FindBy(xpath = "//p[text()='Driving license']/parent::div/parent::div/parent::div/div[2]/input")
    public WebElement AddressProofUpload;

    @FindBy(xpath = "//p[text()='Certificate of incorporation (coi)']/parent::div/parent::div/parent::div/div[2]/input")
    public WebElement CERTIFICATE_OF_INCORPORATIONUpload;

    @FindBy(xpath = "//p[text()='Articles of association (aoa)']/parent::div/parent::div/parent::div/div[2]/input")
    public WebElement AOAUpload;

    @FindBy(xpath = "//p[text()='Memorandum of association (moa)']/parent::div/parent::div/parent::div/div[2]/input")
    public WebElement MOAUploadRemove;

    @FindBy(xpath = "//p[text()='Certificate of incorporation (coi)']/parent::div/parent::div/parent::div/div[2]/input")
    public WebElement COIUploadRemove;

    @FindBy(xpath = "//p[text()='Driving license']/preceding-sibling::img[1]")
    private WebElement selectDrivingLicenseOption;

    @FindBy(xpath = "//div[@class='flex flex-col gap-[0.8rem]']")
    private WebElement selectAddressProof;

    public void chooseAddressProof() {
        safeClick(selectAddressProof, "Delete Icon");
        safeClick(selectDrivingLicenseOption, "Delete Icon");
    }

    @FindBy(xpath = "(//img[@alt='down-arrow'])[1]")
    private WebElement downArrow;

    @FindBy(xpath = "(//img[@alt='down-arrow'])[2]")
    private WebElement downArrow2;

    @FindBy(xpath = "(//img[@alt='delete'])[1]")
    private WebElement deleteIcon;

    @FindBy(xpath = "//p[text()='Yes']")
    private WebElement yesButton;

    @FindBy(xpath = "//input[@placeholder='Enter Company Name']")
    private WebElement enterCompanyName;

    @FindBy(xpath = "//input[@name='final_company_name']")
    private WebElement enterCompanyNameChangeCmpyObj;

    @FindBy(xpath = "(//p[text()='Next'])[2]")
    private WebElement nextCTA;

    @FindBy(xpath = "//input[@placeholder='Enter Company CIN']")
    private WebElement enterCompanyCIN;

    @FindBy(xpath = "//p[text()='Add Director(s)']")
    private WebElement addDirectorCTA;

    @FindBy(xpath = "(//p[text()='Fill details'])[1]")
    private WebElement fillDetailsCTA;

    @FindBy(xpath = "//input[@placeholder='Enter Name of the Director']")
    private WebElement enterDirectorName;

    @FindBy(xpath = "//input[@name='din']")
    private WebElement enterDin;

    @FindBy(xpath = "//input[@name='father_last_name']")
    private WebElement enterFatherName;

    @FindBy(xpath = "//input[@name='director_addressLine1']")
    private WebElement enterAddressLine1;

    @FindBy(xpath = "//input[@name='email']")
    private WebElement enterDirectorEmail;

    @FindBy(xpath = "//input[@name='mobile']")
    private WebElement enterDirectorMobile;

    @FindBy(xpath = "//input[@name='current_occupation']")
    private WebElement enterCurrentOccupation;

    @FindBy(xpath = "//input[@placeholder='Enter Educational Qualification']")
    private WebElement enterEducationalQualification;

    @FindBy(xpath = "//input[@id='dob']")
    private WebElement clickDOB;

    @FindBy(xpath = "(//div[@class='react-datepicker__week']/div[text()='1'])[1]")
    private WebElement selectDate;

    @FindBy(xpath = "//input[@name='pan']")
    private WebElement enterPan;

    @FindBy(xpath = "//input[@name='aadharno']")
    private WebElement enterAadhaarNo;

    @FindBy(xpath = "//input[@name='other_occupation']")
    private WebElement enterOccupationType;

    @FindBy(xpath = "//input[@name='area_locality']")
    private WebElement enterAreaOfOccupation;

    @FindBy(xpath = "//input[@name='police_station']")
    private WebElement enterPoliceStation;

    @FindBy(xpath = "//input[@name='dsc_availability']")
    private WebElement enterDscAvailabilityAppDir;

    @FindBy(xpath = "//input[@name='director_addressLine1']")
    private WebElement enterDscAvailabilityRemDir;

    @FindBy(xpath = "(//button[text()='Upload'])[1]")
    public  WebElement clickUploadCTA;

    @FindBy(xpath = "//input[@placeholder='Enter Reason for resignation']")
    private WebElement enterResignationReason;

    @FindBy(xpath = "//input[@name='name']")
    private WebElement enterObjectiveName;

    @FindBy(xpath = "//input[@name='director_addressLine1']")
    private WebElement selectMainObjective;

    @FindBy(xpath = "//input[@name='company_name']")
    private WebElement selectOldObjects;

    @FindBy(xpath = "//input[@name='company_address']")
    private WebElement selectNewObjects;

    @FindBy(xpath = "//input[@id='option-Whether share transfer executed previously -yes']")
    private WebElement selectShareTransfer;

    @FindBy(xpath = "//input[@id='option-Whether upto date Annual Filings (RoC) filed -yes']")
    private WebElement selectROCFiled;

    @FindBy(xpath = "//input[@name='last_name']")
    private WebElement enterApplicantName;

    @FindBy(xpath = "//input[@placeholder='Enter Applicant name']")
    private WebElement enterApplicantNameForCopyRight;

    @FindBy(xpath = "//input[@id='react-select-7-input']")
    private WebElement enterApplicantGender;

    @FindBy(xpath = "//input[@id='react-select-2-input']")
    private WebElement enterApplyPatentApplicantGender;

    @FindBy(xpath = "//input[@name='director_addressLine1']")
    public WebElement enterApplicantAddress;

    @FindBy(xpath = "//input[@name='mobile']")
    private WebElement enterApplicantContactNumber;

    @FindBy(xpath = "//input[@name='email']")
    private WebElement enterApplicantEmailID;

    @FindBy(xpath = "//input[@id='option-Nature of Applicant-large entity']")
    private WebElement selectApplicantType;

    @FindBy(xpath = "//input[@placeholder='Enter Inventor Name']")
    private WebElement enterInventorName;

    @FindBy(xpath = "//input[@name='company_name5']")
    private WebElement enterInventorAddress;

    @FindBy(xpath = "//input[@placeholder='Enter Inventor Email Id']")
    private WebElement enterInventorEmailID;

    @FindBy(xpath = "//input[@placeholder='Enter Inventor Contact Number']")
    private WebElement enterInventorContactNumber;

    @FindBy(xpath = "//input[@placeholder='Enter Gender of the Inventor']")
    private WebElement enterInventorGender;

    @FindBy(xpath = "//textarea[@placeholder='Enter Signature of the inventors(s)']")
    private WebElement enterInventorSignature;

    @FindBy(xpath = "//input[@id='react-select-3-input']")
    private WebElement SelectPatentApplicant;

    @FindBy(xpath = "//input[@id='option-Normal publication (18 Months)-yes']")
    private WebElement selectNormPublication;

    @FindBy(xpath = "//input[@id='option-Early publication (1-2 months) (extra cost applicable)-yes']")
    private WebElement selectEarlyPublication;

    @FindBy(xpath = "//input[@id='option-Normal examination (extra cost applicable)-yes']")
    private WebElement selectNormExamination;

    @FindBy(xpath = "//input[@id='option-Expedited examination (extra cost applicable)-yes']")
    private WebElement selectExpeditedExamination;

    @FindBy(xpath = "//input[@name='director_addressLine1']")
    private WebElement enterCopyrightAddress;

    @FindBy(xpath = "//input[@name='alternative_email']")
    private WebElement enterCopyrightAuthorName;

    @FindBy(xpath = "//input[@name='premise_name']")
    private WebElement enterCopyrightAuthorAddress;

    @FindBy(xpath = "//input[@placeholder='Enter Nationality of the Applicant']")
    private WebElement enterCopyrightNationalityApplicant;

    @FindBy(xpath = "//input[@placeholder='Enter Nationality of the Author']")
    private WebElement  enterCopyrightNationalityAuditor;


    @FindBy(xpath = "//input[@placeholder='Enter Title of the work']")
    private WebElement enterWorkTitle;

    @FindBy(xpath = "//input[@placeholder='Enter Nature of the work']")
    private WebElement enterWorkNature;

    @FindBy(xpath = "//input[@placeholder='Enter Language of the work']")
    private WebElement enterWorkLanguage;

    @FindBy(xpath = "//input[@name='former_name']")
    private WebElement enterWorkOrigin;

    @FindBy(xpath = "//input[@placeholder='Enter Softcopies of work']")
    private WebElement enterWorkSoftcopies;

    @FindBy(xpath = "(//div/img[@class='false undefined'])[1]")
    private WebElement clickDateCalendarIcon;

    @FindBy(xpath = "(//div/img[@class='false undefined'])[2]")
    private WebElement clickDateCalendarIcon2;

    @FindBy(xpath = "//input[@name='flat_no']")
    private WebElement enterPublisherName;

    @FindBy(xpath = "//input[@placeholder='Enter Country of publication']")
    private WebElement enterPublisherCountry;

    @FindBy(xpath = "//p[text()='3']")
    private WebElement selectTab3;

    @FindBy(xpath = "//p[text()='2']")
    private WebElement selectTab2;

    public By getDownArrowLocator() {
        return By.xpath("(//img[@alt='down-arrow'])[1]");
    }

    public boolean exists(By locator, Duration timeout) {
        try {
            wait.waitForPresence(locator);
            return true;
        } catch (TimeoutException e) {
            return false;
        }
    }


    private void safeType(WebElement element, String value, String fieldName) {
        int attempts = 0;
        boolean typed = false;

        logger.info("⌨️ Attempting to type '{}' into {}", value, fieldName);

        while (attempts < 3 && !typed) {
            attempts++;
            try {
                // ✅ Ensure element is visible before interacting
                wait.waitForVisibility(element);

                if (element != null && element.isDisplayed() && element.isEnabled()) {
                    element.clear();
                    element.sendKeys(value);
                    logger.info("✅ Successfully typed '{}' into {} on attempt {}", value, fieldName, attempts);
                    typed = true;
                } else {
                    logger.warn("⚠️ {} not interactable (Displayed: {}, Enabled: {})",
                            fieldName, element.isDisplayed(), element.isEnabled());
                }

            } catch (StaleElementReferenceException e) {
                logger.warn("♻️ Stale element for {} on attempt {}. Retrying...", fieldName, attempts, e);
            } catch (Exception e) {
                logger.error("❌ Error typing into {} on attempt {}.", fieldName, attempts, e);
            }

            if (!typed) {
                try {
                    Thread.sleep(250);
                } catch (InterruptedException ignored) {
                }
            }
        }

        if (!typed) {
            throw new RuntimeException("Failed to type into " + fieldName + " after " + attempts + " attempts.");
        }
    }

    private void safeClick(WebElement element, String fieldName) {
        int attempts = 0;
        boolean clicked = false;

        logger.info("🖱️ Attempting to click on {}", fieldName);

        while (attempts < 5 && !clicked) {
            attempts++;
            try {
                if (element == null) throw new RuntimeException("Null WebElement for: " + fieldName);

                // Bring into view (center) to avoid sticky headers/overlaps
                ((JavascriptExecutor) driver).executeScript(
                        "arguments[0].scrollIntoView({block:'center', inline:'center'});", element
                );

                // Wait until Selenium thinks it's clickable
                wait.waitForElementToBeClickable(element);

                // Small pause for CSS transitions
                Thread.sleep(120);

                // Try native click → Actions → JS
                try {
                    element.click();
                } catch (ElementClickInterceptedException | MoveTargetOutOfBoundsException e1) {
                    try {
                        new Actions(driver).moveToElement(element).pause(Duration.ofMillis(80)).click().perform();
                    } catch (Exception e2) {
                        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", element);
                    }
                }

                logger.info("✅ Clicked '{}' (attempt {})", fieldName, attempts);
                clicked = true;

            } catch (StaleElementReferenceException e) {
                logger.warn("♻️ Stale element for '{}' on attempt {}. Retrying...", fieldName, attempts);
                sleep(200);
            } catch (TimeoutException e) {
                logger.warn("⏳ Timeout waiting for '{}' on attempt {}. Retrying...", fieldName, attempts);
                sleep(200);
            } catch (Exception e) {
                logger.warn("🚧 Click issue on '{}' attempt {}: {}. Retrying...", fieldName, attempts, e.getClass().getSimpleName());
                sleep(250);
            }
        }

        if (!clicked) {
            try {
                ScreenshotUtils.attachScreenshotToAllure(driver, "ClickFail_" + fieldName);
            } catch (Exception ignored) {
            }
            throw new RuntimeException("Failed to click on " + fieldName + " after " + attempts + " attempts.");
        }
    }

    private void sleep(long ms) {
        try {
            Thread.sleep(ms);
        } catch (InterruptedException ignored) {
        }
    }


    public void deleteDirector() {
        safeClick(deleteIcon, "Delete Icon");
        safeClick(yesButton, "Yes Button");
    }

    public void deleteDocumentSet1() {
        safeClick(downArrow, "Down Arrow Icon");
        safeClick(deleteIcon, "Delete Icon");
        safeClick(yesButton, "Yes Button");
    }

    public void deleteDocumentSet2() {
        safeClick(downArrow2, "Down Arrow Icon");
        safeClick(deleteIcon, "Delete Icon");
        safeClick(yesButton, "Yes Button");
    }

    public void typeCompanyName(String companyName) {
        safeType(enterCompanyName, companyName, "Company Name");
    }

    public void typeCompanyNameForChangeCmpyObj(String companyName) {
        safeType(enterCompanyNameChangeCmpyObj, companyName, "Company Name");
    }

    public void clickNextCTA() {
        safeClick(nextCTA, "Next CTA");
    }


    public void typeCompanyCIN(String cin) {
        safeType(enterCompanyCIN, cin, "Company CIN");
    }

    public void clickAddDirectorCTA() {
        safeClick(addDirectorCTA, "Next CTA");
    }

    public void clickFillDetailsCTA() {
        safeClick(fillDetailsCTA, "Fill Details CTA");
    }

    public void typeDirectorName(String name) {
        safeType(enterDirectorName, name, "Director Name");
    }

    public void typeDin(String din) {
        safeType(enterDin, din, "DIN");
    }

    public void typeFatherName(String fatherName) {
        safeType(enterFatherName, fatherName, "Father Name");
    }

    public void typeAddressLine1(String address) {
        safeType(enterAddressLine1, address, "Address Line 1");
    }

    public void typeDirectorEmail(String email) {
        safeType(enterDirectorEmail, email, "Director Email");
    }

    public void typeDirectorMobile(String mobile) {
        safeType(enterDirectorMobile, mobile, "Director Mobile");
    }

    public void typeCurrentOccupation(String occupation) {
        safeType(enterCurrentOccupation, occupation, "Current Occupation");
    }

    public void typeEducationalQualification(String qualification) {
        safeType(enterEducationalQualification, qualification, "Educational Qualification");
    }

    public void selectDOB() {
        logger.info("📅 Selecting Date of Birth");
        safeClick(clickDOB, "DOB calendar field");
        safeClick(selectDate, "DOB value");
    }

    public void typePan(String pan) {
        safeType(enterPan, pan, "PAN");
    }

    public void typeAadhaarNo(String aadhaar) {
        safeType(enterAadhaarNo, aadhaar, "Aadhaar Number");
    }

    public void typeOccupationType(String occupationType) {
        safeType(enterOccupationType, occupationType, "Occupation Type");
    }

    public void typeAreaOfOccupation(String area) {
        safeType(enterAreaOfOccupation, area, "Area of Occupation");
    }

    public void typePoliceStation(String policeStation) {
        safeType(enterPoliceStation, policeStation, "Police Station");
    }

    public void typeDscAvailabilityAppDir(String dsc) {
        safeType(enterDscAvailabilityAppDir, dsc, "DSC Availability");
    }

    public void typeDscAvailabilityRemDir(String dsc) {
        safeType(enterDscAvailabilityRemDir, dsc, "DSC Availability");
    }

    public void typeResignationReason(String resignationReason) {
        safeType(enterResignationReason, resignationReason, "Reason for Resignation");
    }

    public void typeObjectiveName(String objectiveName) {
        safeType(enterObjectiveName, objectiveName, "Objects to be added");
    }

    public void typeMainObjective(String objectiveName) {
        safeType(selectMainObjective, objectiveName,"New objects are to be added as Main Objects or as Ancillary Objects");
    }

    public void typeOldObjective(String objectiveName) {
        safeType(selectOldObjects, objectiveName, "Old objects are to be replaced with new objects");
    }

    public void typeNewObjective(String objectiveName) {
        safeType(selectNewObjects, objectiveName, "New objects are to be added along with existing objects");
    }

    public void chooseShareTransfer() {
        safeClick(selectShareTransfer, "Whether share transfer executed previously");
    }

    public void chooseROCFiled() {
        safeClick(selectROCFiled, "Whether upto date Annual Filings (RoC) filed");
    }

    //Apply for a patent
    public void typeApplicantName(String ApplicantName) {
        safeType(enterApplicantName, ApplicantName, "Applicant Name");
    }

    public void typeApplicantNameForCopyRight(String ApplicantName) {
        safeType(enterApplicantNameForCopyRight, ApplicantName, "Applicant Name");
    }


    public void typApplyPatentApplicantGender(String ApplicantGender) {
        safeType(enterApplyPatentApplicantGender, ApplicantGender, "Applicant Gender");
    }

    public void typeApplicantAddress(String ApplicantAddress) {
        safeType(enterApplicantAddress, ApplicantAddress, "Applicant Address");
    }

    public void typeApplicantContactNumber(String ApplicantContactNumber) {
        safeType(enterApplicantContactNumber, ApplicantContactNumber, "Applicant Contact Number");
    }

    public void typeApplicantEmailID(String ApplicantEmailID) {
        safeType(enterApplicantEmailID, ApplicantEmailID, "Applicant Email ID");
    }

    public void ChooseApplicantType() {
        safeClick(selectApplicantType, "Nature of Applicant");
    }

    public void typeInventorName(String InventorName) {
        safeType(enterInventorName, InventorName, "Inventor Name");
    }

    public void typeInventorAddress(String InventorAddress) {
        safeType(enterInventorAddress, InventorAddress, "Inventor Address");
    }

    public void typeInventorEmailID(String InventorEmailID) {
        safeType(enterInventorEmailID, InventorEmailID, "Inventor EmailID");
    }

    public void typeInventorContactNumber(String InventorContactNumber) {
        safeType(enterInventorContactNumber, InventorContactNumber, "Inventor Contact Number");
    }

    public void typeInventorGender(String InventorGender) {
        safeType(enterInventorGender, InventorGender, "Inventor Gender");
    }

    public void typeInventorSignature(String InventorSignature) {
        safeType(enterInventorSignature, InventorSignature, "Inventor Signature");
    }

    public void clickTab2() {
        safeClick(selectTab2, "Directors Details");
    }

    public void clickTab3() {
        safeClick(selectTab3, "Upload Documents");
    }

//    public void ChoosePatentApplicant(String PatentType) {
//        safeType(SelectPatentApplicant, PatentType, "Type of Patent Application");
//    }

    public void ChooseNormPublication() {
        safeClick(selectNormPublication, "Normal publication (18 Months)");
    }

    public void ChooseEarlyPublication() {
        safeClick(selectEarlyPublication, "Early publication (1-2 months) (extra cost applicable)");
    }

    public void ChooseNormExamination() {
        safeClick(selectNormExamination, "Normal examination (extra cost applicable)");
    }

    public void ChooseExpeditedExamination() {
        safeClick(selectExpeditedExamination, "Expedited examination (extra cost applicable)");
    }

    public void typeCopyrightAddress(String CopyrightAddress) {
        safeType(enterCopyrightAddress, CopyrightAddress, "Copyright Applicant's Address");
    }

    public void typeCopyrightNationalityApplicant(String CopyrightNationality) {
        safeType(enterCopyrightNationalityApplicant, CopyrightNationality, "Copyright Applicant's Nationality");
    }
    public void typeCopyrightNationalityAuditor(String CopyrightNationality) {
        safeType(enterCopyrightNationalityAuditor, CopyrightNationality, "Copyright Applicant's Nationality");
    }

    public void typeCopyrightAuthorName(String CopyrightAuthorName) {
        safeType(enterCopyrightAuthorName, CopyrightAuthorName, "Copyright Author Name");
    }

    public void typeCopyrightAuthorAddress(String CopyrightAuthorAddress) {
        safeType(enterCopyrightAuthorAddress, CopyrightAuthorAddress, "Copyright Author Address");
    }

    public void typeWorkTitle(String WorkTitle) {
        safeType(enterWorkTitle, WorkTitle, "Title of the work");
    }

    public void typeWorkNature(String WorkNature) {
        safeType(enterWorkNature, WorkNature, "Nature of the work");
    }

    public void typeWorkLanguage(String WorkLanguage) {
        safeType(enterWorkLanguage, WorkLanguage, "Language of the work");
    }

    public void typeWorkOrigin(String WorkOrigin) {
        safeType(enterWorkOrigin, WorkOrigin, "Originial work or translation of another work");
    }

    public void typeWorkSoftCopies(String WorkSoftCopies) {
        safeType(enterWorkSoftcopies, WorkSoftCopies, "SoftCopies of work");
    }

    public void typePublisherName(String WorkOrigin) {
        safeType(enterPublisherName, WorkOrigin, "Publisher name and address");
    }

    public void typePublisherCountry(String PublisherCountry) {
        safeType(enterPublisherCountry, PublisherCountry, "Publisher Country");
    }

    public void setPublishedDate() {
        logger.info("📅 Selecting Date of Birth");
        safeClick(clickDateCalendarIcon, "Click Date Calendar Icon");
        safeClick(selectDate, "Date Calendar value");
    }

    public void setCopyRightDate() {
        logger.info("📅 Selecting Date of Birth");
        safeClick(clickDateCalendarIcon2, "Click Date Calendar Icon");
        safeClick(selectDate, "Date Calendar value");
    }

    public void clickUploadCTA() {
        int attempts = 0;
        boolean clicked = false;

        logger.info("🔎 Attempting to click 'Upload' CTA using locator: {}", clickUploadCTA);

        while (attempts < 3 && !clicked) {
            attempts++;
            logger.info("🖱️ Attempt {} of 3 to click 'Upload' CTA", attempts);

            try {
                WebElement button = wait.waitForElementToBeClickable(clickUploadCTA);

                if (button != null && button.isDisplayed() && button.isEnabled()) {
                    logger.info("✅ 'Upload' CTA is visible and enabled. Trying safeClick...");
                    commonMethods.safeClick(driver, button, "Upload CTA", 10);
                    logger.info("🎯 Successfully clicked 'Upload' CTA on attempt {}", attempts);
                    clicked = true;
                }

            } catch (StaleElementReferenceException e) {
                logger.warn("♻️ StaleElementReferenceException on attempt {} for 'Upload' CTA. Retrying...", attempts, e);
            } catch (Exception e) {
                logger.error("❌ Unexpected exception on attempt {} for 'Upload' CTA.", attempts, e);
            }

            if (!clicked) {
                try {
                    Thread.sleep(250);
                } catch (InterruptedException ignored) {
                }
            }
        }

        if (!clicked) {
            logger.error("🚨 Failed to click 'Upload' CTA after {} attempts.", attempts);
            throw new RuntimeException("Failed to click 'Upload' CTA after " + attempts + " attempts.");
        }
    }
    @FindBy(xpath = "//a[@id='crossSaleNew']")
    public WebElement crossSaleNew;

    @FindBy(xpath = "//li[contains(@class, 'select2-results__option') and contains(text(), 'Appointment of a Director')]")
    public WebElement selectingtheservice;

    @FindBy(xpath = "//li[contains(@class, 'select2-results__option') and contains(text(), 'Apply for a Patent')]")
    public WebElement selectingtheservice2;

    @FindBy(xpath = "//li[contains(@class, 'select2-results__option') and contains(text(), 'Removal of a Director')]")
    public WebElement selectingtheservice3;

    @FindBy(xpath = "//li[contains(@class, 'select2-results__option') and contains(text(), 'Change the Objectives of Your Company')]")
    public WebElement selectingtheservice4;

    @FindBy(xpath = "//li[contains(@class, 'select2-results__option') and contains(text(), 'Copyright Registration')]")
    public WebElement selectingtheservice5;


    @FindBy(xpath = "//button[@class='btn btn-default btn-u cross_sale_ticket_create']")
    public WebElement createbutton;

    @FindBy(xpath = "//button[@id='copy_upload_doc_link']")
    public WebElement copyurl;

    @FindBy(xpath = "//a[@class='btn recent_tickets']")
    public WebElement recenttickets;


    public void crossSaleNew() {

        safeClick(crossSaleNew, "Cross Sale click action");
    }


    public void selectingtheservice2() {
        safeClick(selectingtheservice2, "Selecting these services");

    }

    public void selectingtheservice() {
        safeClick(selectingtheservice, "Selecting these services");

    }

    public void selectingtheservice3() {
        safeClick(selectingtheservice3, "Selecting these services");
    }

    public void selectingtheservice4() {
        safeClick(selectingtheservice4, "Selecting these services");
    }

    public void selectingtheservice5() {
        safeClick(selectingtheservice5, "Selecting these services");
    }

    public void recenttickets() {
        safeClick(recenttickets, "Recent tickets");
    }

    public void ChoosePatentApplicant(String PatentType) {
        SelectPatentApplicant.sendKeys(PatentType);

        By optionLocator = By.xpath("//div[contains(@class, '-option') and text()='" + PatentType + "']");

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        wait.until(ExpectedConditions.elementToBeClickable(optionLocator)).click();
    }

    @FindBy(xpath = "//button[@id='paymentNoBtn']")
    public WebElement crossNobutton;

    @FindBy(xpath = "//span[@id='select2-cross_service_tag-container']")
    public WebElement createservice;

    @FindBy(xpath = "//span[contains(@class, 'select2-container--open')]//input[@class='select2-search__field']")
    public WebElement select2SearchField;


    public void createservice(String applyforustrademark) throws InterruptedException {
        safeClick(createservice, "Opening Service Dropdown");

        Thread.sleep(1500);

        try {
            select2SearchField.sendKeys(applyforustrademark);
            logger.info("Typed service name successfully.");
        } catch (Exception e) {
            safeType(select2SearchField, applyforustrademark, "Creating a service");
        }
    }



    public void createbutton() throws InterruptedException {
        safeClick(createbutton, "Service create button");

        Thread.sleep(2000);
        safeClick(crossNobutton, "Cross Sale No payment action");
        Thread.sleep(2000);
        driver.switchTo().alert().accept();
        logger.info("Browser alert accepted.");
    }

}