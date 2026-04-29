package stepDefinitions;

import hooks.Hooks;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import io.qameta.allure.Step;
import org.apache.logging.log4j.Logger;
import org.junit.Assert;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import pages.PVTPage;
import utils.*;

import java.awt.*;
import java.awt.datatransfer.DataFlavor;
import java.io.File;
import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import static utils.AllureLoggerUtils.logToAllure;

public class PVTStepDefs {
    WebDriver driver = Hooks.driver;
    PVTPage pvtPage;
    Logger logger;
    AllureLoggerUtils allureLogging;
    WaitUtils wait;
    SeleniumHelperMethods seleniumHelperMethods;
    SearchTicketStepdefs searchTicketsteps;
    LoginStepDefinitions loginSteps;

    public PVTStepDefs() {
        this.driver = Hooks.driver;
        this.pvtPage = new PVTPage(Hooks.driver);
        this.logger = LoggerUtils.getLogger(getClass());
        this.wait = new WaitUtils(driver, 30);
        this.searchTicketsteps = new SearchTicketStepdefs(Hooks.driver);
        this.loginSteps = new LoginStepDefinitions();
    }

    @Step("{message}")
    public void logStep(String message) {
        // Allure will log this message as a step
    }

    @Given("the user redirect to Private Limited Company Registration Page")
    public void the_user_redirect_to_private_limited_company_registration_page() throws InterruptedException {
        try {

            searchTicketsteps.theUserRedirectToTicketListPage();
            searchTicketsteps.theUserEnterTheTicket();
            searchTicketsteps.theUserClickTheSearchCTA();
            searchTicketsteps.theUserViewTheTicketPageByClickingOnTheLink();

            pvtPage.crossSaleNew();
            pvtPage.createservice(ConfigReader.get("Pvtservice"));
            pvtPage.selectingtheservice();
            Thread.sleep(2000);
            pvtPage.createbutton();
            logger.info("Service creation initiated.");
            ScreenshotUtils.attachScreenshotToAllure(driver, "2_Service_Created");

            Thread.sleep(15000);
            pvtPage.recenttickets();
            Thread.sleep(4000);
            logger.info("Recent Tickets modal opened.");
            ScreenshotUtils.attachScreenshotToAllure(driver, "3_Recent_Tickets_Modal");
            try {
                WebElement firstTicket = driver.findElement(By.cssSelector("tr.ticket_index_tr ul.tcktdetails li a"));
                firstTicket.click();
                logger.info("Clicked first dynamic ticket link.");
                Thread.sleep(10000);
                ScreenshotUtils.attachScreenshotToAllure(driver, "4_Selected_Ticket_Page");

                pvtPage.copyurl.click();
                logger.info("Copy button clicked.");
                Thread.sleep(4000);
                ScreenshotUtils.attachScreenshotToAllure(driver, "5_After_Copy_Action");

                String dynamicUrl = (String) Toolkit.getDefaultToolkit().getSystemClipboard().getData(DataFlavor.stringFlavor);

                if (dynamicUrl != null && dynamicUrl.startsWith("http")) {
                    logger.info("Navigating to dynamic URL captured: {}", dynamicUrl);
                    logStep("🌐 Navigating to: " + dynamicUrl);
                    driver.get(dynamicUrl);

                    Thread.sleep(4000);
                    ScreenshotUtils.attachScreenshotToAllure(driver, "6_Final_Destination_Page");
                } else {
                    throw new Exception("Clipboard did not contain a valid URL.");
                }

            } catch (Exception e) {
                logger.error("❌ Error during ticket selection/URL capture: {}", e.getMessage());
                ScreenshotUtils.attachScreenshotToAllure(driver, "Error_Capture_Failure");
                Assert.fail("Failed during dynamic link capture: " + e.getMessage());
            }
            logStep("🌐 Navigating to the Private Limited Registration - Document page...");
            logStep("✅ Open Private Limited Registration Document Page");
            logToAllure("✅ Private Limited Registration - Redirection Validation", "Private Limited Registration Page redirected successfully");
            ScreenshotUtils.attachScreenshotToAllure(driver, "PVTPage");

        } catch (WebDriverException | AssertionError e) {
            String msg = "❌ Exception during Private Limited Registration page: " + e.getMessage();
            logger.error(msg);
            logStep(msg);
            logToAllure("❌ Exception", msg);
            ScreenshotUtils.attachScreenshotToAllure(driver, "PVTURL_Exception");
            throw e;
        }
    }

    @When("the user enter the Company Name details")
    public void the_user_enter_the_company_name_details() {
        try {
            logStep("🏢 Updating company name...");
            pvtPage.typeSearchEnterCompanyName(ConfigReader.get("DescribeBusiness"));
            pvtPage.clickSearchCTA();
            ScreenshotUtils.attachScreenshotToAllure(driver, "After_Search_CTA");

            pvtPage.clickSelectNICCODE();
            ScreenshotUtils.attachScreenshotToAllure(driver, "After_NIC_Code_Selection");

            pvtPage.clickNextButton();

            try {
                if (pvtPage.confirmButton.isDisplayed() && pvtPage.confirmButton.isEnabled()) {
                    ScreenshotUtils.attachScreenshotToAllure(driver, "Before_Confirm_Click");
                    pvtPage.clickConfirmButton();
                    Thread.sleep(2000);
                }
            } catch (Exception e) {
                logStep("ℹ️ Confirm Button not present, continuing flow");
            }

            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
            boolean isBusinessObjectivePage = driver.findElements(By.xpath("//p[text()='Business Objective']")).size() > 0;

            if (isBusinessObjectivePage) {
                logStep("➡️ Navigated to Business Objective page");
                pvtPage.objectivesedit();
                pvtPage.objectivesdetails(ConfigReader.get("DescribeBusiness"));
                ScreenshotUtils.attachScreenshotToAllure(driver, "Business_Objective_Entered");

                pvtPage.clickNextButton();
                logStep("✅ Business Objective entered");
            } else {
                logStep("➡️ Skipping Business Objective, Navigated to Company Details");
            }

            pvtPage.typeEnterCompanyName(ConfigReader.get("CompanyName"));
            ScreenshotUtils.attachScreenshotToAllure(driver, "Company_Name_Entered");

            try {
                if (pvtPage.editIcon.isDisplayed()) {
                    pvtPage.clickEditIcon();
                    pvtPage.typeByEditingCompanyName(ConfigReader.get("SearchCompanyName"));
                    pvtPage.clickCheckAvailability();
                    ScreenshotUtils.attachScreenshotToAllure(driver, "After_Check_Availability");

                    Thread.sleep(4000);
                    pvtPage.clickDoneButton();
                }
            } catch (Exception e) {
                logStep("ℹ️ Edit Icon not present, using default company name");
            }

            pvtPage.typeSignificance(ConfigReader.get("SignificanceDesc"));
            ScreenshotUtils.attachScreenshotToAllure(driver, "Significance_Entered");

            pvtPage.clickNextButton();
            ScreenshotUtils.attachScreenshotToAllure(driver, "Final_Company_Details_Step");

        } catch (Exception e) {
            String message = "❌ Exception during company name details flow: " + e.getMessage();
            logger.error(message, e);
            ScreenshotUtils.attachScreenshotToAllure(driver, "Flow_Exception");
        }
    }

    @When("the user enter the Director Details")
    public void the_user_enter_the_director_details() {
        //Director 1
        try {
            pvtPage.clickTab2();
            for(int i=0;i<2;i++){
//                pvtPage.clickDeleteButton();
//                pvtPage.clickYesButton();
            }
            pvtPage.clickAdd2Directors();
            pvtPage.clickFillStep();
            //Basic Information
            pvtPage.typeEnterName(ConfigReader.get("directorName1"));
            pvtPage.selectNationality(ConfigReader.get("countryName")); // opens dropdown
//            pvtPage.clickNationality.sendKeys(Keys.ENTER); // selects first item
            pvtPage.typeEnterEmail(ConfigReader.get("DirectorEmail1"));
            pvtPage.typeEnterMobile(ConfigReader.get("DirectorMobile1"));
            pvtPage.selectDirectorRoleCommon();
            Thread.sleep(2000);
            pvtPage.clickEnableCheckbox();
            Thread.sleep(2000);
            pvtPage.clickDoneButton();
            pvtPage.afterdirectoraddconfirmbutton();
            Thread.sleep(2000);
            pvtPage.clickUploadMyself();
            //Document Upload flow
            Thread.sleep(2000);
            pvtPage.selectAadharFront.sendKeys(ConfigReader.get("AadharUploadPic1"));
            Thread.sleep(2000);
            pvtPage.selectAadharBack.sendKeys(ConfigReader.get("AadharUploadBack"));
            Thread.sleep(2000);
            pvtPage.uploadPassportSize.sendKeys(ConfigReader.get("PassportPhoto1"));
            Thread.sleep(2000);
            pvtPage.uploadPAN.sendKeys(ConfigReader.get("PAN1"));
            Thread.sleep(2000);
            pvtPage.clickSign();
            String parent = driver.getWindowHandle();
            String screenshotPath = ScreenshotUtils.takeScreenshot(driver, "CompanySign");
            System.out.println("DEBUG Screenshot path: " + screenshotPath);
            File file = new File(screenshotPath);
            if (!file.exists()) {
                System.err.println("FILE DOES NOT EXIST → " + screenshotPath);
            }
            String qrPath = QRReader.detectAndCrop(
                    screenshotPath,
                    System.getProperty("user.dir") + "/target/qr-crops"
            );
            JavascriptExecutor js = (JavascriptExecutor) driver;
            js.executeScript("window.open('" + qrPath + "', '_blank');");
            Thread.sleep(2000);
            // Switch to newest tab
            List<String> tabs = new ArrayList<>(driver.getWindowHandles());
            driver.switchTo().window(tabs.get(tabs.size() - 1));
            System.out.println("Cropped QR saved here: " + qrPath);
            pvtPage.clickIAgree();
            Thread.sleep(2000);
            pvtPage.clickGotIt();
            pvtPage.clickSignCanvas();
            for(int i=0; i<2; i++) {
                pvtPage.clickSaveAndNext();
                pvtPage.clickSignCanvas();
            }
            pvtPage.clickSubmit();
            Thread.sleep(2000);
            driver.close();
            driver.switchTo().window(parent);
            driver.navigate().refresh();
            Thread.sleep(2000);
            pvtPage.clickUploadBankStatementCheckbox();
            Thread.sleep(2000);
            logStep("✅ Director 1 documents uploaded successfully");
            logToAllure("Director 1 Documents", "Director 1 documents uploaded successfully");
            ScreenshotUtils.attachScreenshotToAllure(driver, "Director1_Documents");
            pvtPage.clickNextButton();
            Thread.sleep(4000);
            pvtPage.typeEnterEducation(ConfigReader.get("PVTEducation"));
            pvtPage.typeEnterPlaceOfBirth(ConfigReader.get("PlaceOfBirth"));
            pvtPage.typeEnterWorkStatus(ConfigReader.get("WorkStatus"));
            pvtPage.clickNextButton();
            Thread.sleep(2000);
            //Address Info
            pvtPage.typeEnterAddressLine1(ConfigReader.get("Address1"));
            pvtPage.typeEnterAddressLine2(ConfigReader.get("Address2"));
            pvtPage.typeEnterPinCode(ConfigReader.get("Pincode"));
            pvtPage.typeEnterYear(ConfigReader.get("DurationYears"));
            pvtPage.typeEnterMonth(ConfigReader.get("DurationMonths"));
            pvtPage.clickNextButton();
            Thread.sleep(2000);
            logStep("✅ Director 1 details successfully updated");
            logToAllure("Director 1 Details", "Director1 details successfully updated");
            ScreenshotUtils.attachScreenshotToAllure(driver, "Director1Details_Updated");
        } catch (AssertionError | InterruptedException e) {
            String message = "❌ Exception during Director 1 Details: " + e.getMessage();
            logger.error(message, e);
            logStep(message);
            logToAllure("❌ Exception", message);
            ScreenshotUtils.attachScreenshotToAllure(driver, "DirectorDetails_Exception");
        }

        //Director 2
        try {
            pvtPage.clickFillStep();
            //Basic Information
            pvtPage.typeEnterName(ConfigReader.get("directorName2"));
            pvtPage.selectNationality(ConfigReader.get("countryName")); // opens dropdown
//            pvtPage.clickNationality.sendKeys(Keys.ENTER); // selects first item
            pvtPage.typeEnterEmail(ConfigReader.get("DirectorEmail2"));
            pvtPage.typeEnterMobile(ConfigReader.get("DirectorMobile2"));
            pvtPage.selectDirectorRoleCommon();
//            pvtPage.clickEnableCheckbox();
            pvtPage.clickDoneButton();
            pvtPage.clickUploadMyself();
            //Document Upload flow
            Thread.sleep(2000);
            pvtPage.selectAadharFront.sendKeys(ConfigReader.get("AadharUploadPic1"));
            Thread.sleep(2000);
            pvtPage.selectAadharBack.sendKeys(ConfigReader.get("AadharUploadBack"));
            Thread.sleep(2000);
            pvtPage.uploadPassportSize.sendKeys(ConfigReader.get("PassportPhoto1"));
            Thread.sleep(2000);
            pvtPage.uploadPAN.sendKeys(ConfigReader.get("PAN2"));
            Thread.sleep(2000);
            pvtPage.clickSign();
            String parent = driver.getWindowHandle();
            String screenshotPath = ScreenshotUtils.takeScreenshot(driver, "CompanySign");
            System.out.println("DEBUG Screenshot path: " + screenshotPath);
            File file = new File(screenshotPath);
            if (!file.exists()) {
                System.err.println("FILE DOES NOT EXIST → " + screenshotPath);
            }
            String qrPath = QRReader.detectAndCrop(
                    screenshotPath,
                    System.getProperty("user.dir") + "/target/qr-crops"
            );
            JavascriptExecutor js = (JavascriptExecutor) driver;
            js.executeScript("window.open('" + qrPath + "', '_blank');");
            Thread.sleep(2000);
            // Switch to newest tab
            List<String> tabs = new ArrayList<>(driver.getWindowHandles());
            driver.switchTo().window(tabs.get(tabs.size() - 1));
            System.out.println("Cropped QR saved here: " + qrPath);
            pvtPage.clickIAgree();
            Thread.sleep(2000);
            pvtPage.clickGotIt();
            pvtPage.clickSignCanvas();
            for(int i=0; i<2; i++) {
                pvtPage.clickSaveAndNext();
                pvtPage.clickSignCanvas();
            }
            pvtPage.clickSubmit();
            Thread.sleep(2000);
            driver.close();
            driver.switchTo().window(parent);
            driver.navigate().refresh();
            Thread.sleep(2000);
            pvtPage.clickUploadBankStatementCheckbox();
            Thread.sleep(2000);
            logStep("✅ Director 2 documents uploaded successfully");
            logToAllure("Director 2 Documents", "Director 2 documents uploaded successfully");
            ScreenshotUtils.attachScreenshotToAllure(driver, "Director2_Documents");
            pvtPage.clickNextButton();
            Thread.sleep(2000);
            pvtPage.typeEnterEducation(ConfigReader.get("PVTEducation"));
            pvtPage.typeEnterPlaceOfBirth(ConfigReader.get("PlaceOfBirth"));
            pvtPage.typeEnterWorkStatus(ConfigReader.get("WorkStatus"));
            pvtPage.clickNextButton();
            Thread.sleep(2000);
            //Address Info
            pvtPage.typeEnterAddressLine1(ConfigReader.get("Address1"));
            pvtPage.typeEnterAddressLine2(ConfigReader.get("Address2"));
            pvtPage.typeEnterPinCode(ConfigReader.get("Pincode"));
            pvtPage.typeEnterYear(ConfigReader.get("DurationYears"));
            pvtPage.typeEnterMonth(ConfigReader.get("DurationMonths"));
            pvtPage.clickNextButton();
            Thread.sleep(2000);
            logStep("✅ Director 2 details successfully updated");
            logToAllure("Director 2 Details", "Director2 details successfully updated");
            ScreenshotUtils.attachScreenshotToAllure(driver, "Director2Details_Updated");
            pvtPage.clickNextButton();
            Thread.sleep(2000);
            pvtPage.afterdirectoraddconfirmbutton();
            Thread.sleep(2000);
        } catch (Exception e) {
            String message = "❌ Exception during Director 2 Details: " + e.getMessage();
            logger.error(message, e);
            logStep(message);
            logToAllure("❌ Exception", message);
            ScreenshotUtils.attachScreenshotToAllure(driver, "DirectorDetails2_Exception");
        }
    }

    @When("the user enter the Company Details")
    public void the_user_enter_the_company_details() {
        try{
            pvtPage.typeEnterCompEmailID(ConfigReader.get("ApplicantEmailId"));
            pvtPage.typeEnterCompMobileNumber(ConfigReader.get("ApplicantContactNumber"));
            pvtPage.clickNextButton();
            pvtPage.clickChangeLink();
            pvtPage.clickBusinessPropertyType();
            pvtPage.clickConfirmPropertySelection();
            pvtPage.clickEnableUploadLaterCheckbox();
            pvtPage.typeCompAddressLine1(ConfigReader.get("Address1"));
            pvtPage.typeCompAddressLine2(ConfigReader.get("Address2"));
            pvtPage.typeCompPincode(ConfigReader.get("Pincode"));
            pvtPage.typeCompAreaLocality(ConfigReader.get("AreaLocality"));
            pvtPage.clickCompSubmitButton();
            pvtPage.clickCompConfirmButton();
            logStep("✅ Company Contact details successfully updated");
            logToAllure("Company Contact Details", "Company Contact details successfully updated");
            ScreenshotUtils.attachScreenshotToAllure(driver, "CompanyContactDetails_Updated");
            pvtPage.clickNextButton();
        } catch (Exception e) {
            String message = "❌ Exception during Company Contact Details: " + e.getMessage();
            logger.error(message, e);
            logStep(message);
            logToAllure("❌ Exception", message);
            ScreenshotUtils.attachScreenshotToAllure(driver, "CompanyContactDetails_Exception");
        }

    }

}
