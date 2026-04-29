package stepDefinitions;

import hooks.Hooks;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import io.qameta.allure.Step;
import org.apache.logging.log4j.Logger;
import org.junit.Assert;
import org.openqa.selenium.*;
import pages.OPCPage;
import utils.*;

import java.awt.*;
import java.awt.datatransfer.DataFlavor;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

import static utils.AllureLoggerUtils.logToAllure;

public class OPCStepDefs {

    WebDriver driver = Hooks.driver;
    OPCPage opcPage;
    Logger logger;
    WaitUtils wait;
    SearchTicketStepdefs searchTicketsteps;
    LoginStepDefinitions loginSteps;

    public OPCStepDefs() {
        this.driver = Hooks.driver;
        this.opcPage = new OPCPage(Hooks.driver);
        this.logger = LoggerUtils.getLogger(getClass());
        this.wait = new WaitUtils(driver,30);
        this.searchTicketsteps = new SearchTicketStepdefs(Hooks.driver);
        this.loginSteps = new LoginStepDefinitions();
    }

    @Step("{message}")
    public void logStep(String message) {
        // Allure will log this message as a step
    }
    @Given("the user redirect to OPC service")
    public void the_user_redirect_to_opc_service() throws InterruptedException {
        try {

            searchTicketsteps.theUserRedirectToTicketListPage();
            searchTicketsteps.theUserEnterTheTicket();
            searchTicketsteps.theUserClickTheSearchCTA();
            searchTicketsteps.theUserViewTheTicketPageByClickingOnTheLink();
            opcPage.crossSaleNew();
            opcPage.createservice(ConfigReader.get("OPCservice"));
            opcPage.selectingtheservice();
            Thread.sleep(2000);
            opcPage.createbutton();
            logger.info("Service creation initiated.");
            ScreenshotUtils.attachScreenshotToAllure(driver, "2_Service_Created");
            Thread.sleep(15000);
            opcPage.recenttickets();
            Thread.sleep(4000);
            logger.info("Recent Tickets modal opened.");
            ScreenshotUtils.attachScreenshotToAllure(driver, "3_Recent_Tickets_Modal");
            try {
                WebElement firstTicket = driver.findElement(By.cssSelector("tr.ticket_index_tr ul.tcktdetails li a"));
                firstTicket.click();
                logger.info("Clicked first dynamic ticket link.");
                Thread.sleep(10000);
                ScreenshotUtils.attachScreenshotToAllure(driver, "4_Selected_Ticket_Page");
                opcPage.copyurl.click();
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
            logStep("🌐 Navigating to the OPC service page...");
            logStep("✅ Register a One Person Company");
            logToAllure("✅ Register a One Person Company page redirection Validation", "Page redirected successfully");
            ScreenshotUtils.attachScreenshotToAllure(driver, "OPC");
        } catch (IllegalArgumentException | WebDriverException | AssertionError e) {
            String msg = "❌ Exception during Register a One Person Company service page: " + e.getMessage();
            logger.error(msg);
            logStep(msg);
            logToAllure("❌ Exception", msg);
            ScreenshotUtils.attachScreenshotToAllure(driver, "OPC_Exception");
            throw e;
        }
    }
    @When("the user update the OPC Company name")
    public void the_user_update_the_opc_company_name() {
        try{
            logStep("✅ OPC Company name");
            opcPage.typeCompanyName(ConfigReader.get("OPCCompanyName1"));
            opcPage.typeSignificance(ConfigReader.get("OPCSignificance"));
            opcPage.setClickIndustry();
            opcPage.clickIndustryOption();
            logToAllure("✅ OPC Company name Validation", "Page redirected successfully");
            ScreenshotUtils.attachScreenshotToAllure(driver, "OPCCompanyName");
            opcPage.clickNextButton();
        } catch (AssertionError e) {
            String msg = "❌ Exception during updating the OPC company name: " + e.getMessage();
            logger.error(msg);
            logStep(msg);
            logToAllure("❌ Exception", msg);
            ScreenshotUtils.attachScreenshotToAllure(driver, "OPCCompanyName_Exception");
        }
    }
    @When("the user update the OPC Member details")
    public void the_user_update_the_opc_member_details() {
        try{
            logStep("✅ OPC Director details");
//            try{
//                for(int i=0;i<2;i++) {
//                    if (opcPage.deleteButton.isDisplayed() && opcPage.deleteButton.isEnabled()) {
//                        opcPage.clickDeleteButton();
//                        opcPage.clickYesButton();
//                        Thread.sleep(2000);
//                    }
//                }
//            } catch (NoSuchElementException e) {
//                logStep("ℹ️ Delete icon is not present, skipping the action");
//            }
            opcPage.clickAddDirectorCumShareholder();
            opcPage.typeDirectorName(ConfigReader.get("directorName1"));
            opcPage.typeDirectorEmailAddress(ConfigReader.get("DirectorEmail1"));
            opcPage.typeDirectorMobileNumber(ConfigReader.get("DirectorMobile1"));
            opcPage.clickDoneButton();
            Thread.sleep(4000);
            opcPage.afterdirectoraddconfirmbutton();
            Thread.sleep(2000);
//          opcPage.clickConfirmButton();
            opcPage.clickUploadByMyselfButton();
            Thread.sleep(4000);
            opcPage.selectAadharFront.sendKeys(ConfigReader.get("AadharUploadPic1"));
            Thread.sleep(2000);
            opcPage.selectAadharBack.sendKeys(ConfigReader.get("AadharUploadBack"));
            Thread.sleep(2000);
            opcPage.uploadPassportSize.sendKeys(ConfigReader.get("PassportPhoto1"));
            Thread.sleep(2000);
            opcPage.uploadPAN.sendKeys(ConfigReader.get("PAN2"));
            Thread.sleep(2000);
            opcPage.clickSignButton();
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
            opcPage.clickIAgreeSignNowButton();
            Thread.sleep(2000);
            opcPage.clickGotItButton();
            opcPage.clickOnSignCanvas();
            for(int i=0; i<2; i++) {
                opcPage.clickSaveAndNextButton();
                opcPage.clickOnSignCanvas();
            }
            opcPage.clickSubmitButton();
            Thread.sleep(2000);
            driver.close();
            driver.switchTo().window(parent);
            driver.navigate().refresh();
            Thread.sleep(2000);
            opcPage.clickUploadBankStatementCheckbox();
            Thread.sleep(2000);
            logStep("✅ OPC Director documents uploaded successfully");
            logToAllure("OPC Director Documents", "Director documents uploaded successfully");
            ScreenshotUtils.attachScreenshotToAllure(driver, "Director_Documents");
            opcPage.clickNextButton();
            opcPage.selectEducation(ConfigReader.get("PVTEducation"));
            Thread.sleep(2000);
            opcPage.selectPlaceOfBirth(ConfigReader.get("PlaceOfBirth"));
            opcPage.selectWorkStatus(ConfigReader.get("WorkStatus"));
            opcPage.clickNextButton();
            //Address Info
            opcPage.typeAddressLine1(ConfigReader.get("Address1"));
            opcPage.typeAddressLine2(ConfigReader.get("Address2"));
            opcPage.typePinCode(ConfigReader.get("Pincode"));
            Thread.sleep(2000);
            opcPage.selectYear(ConfigReader.get("DurationYears"));
            opcPage.selectMonth(ConfigReader.get("DurationMonths"));
            opcPage.clickNextButton();
            logStep("✅ OPC Director details successfully updated");
            logToAllure("OPC Director Details", "Director details successfully updated");
            ScreenshotUtils.attachScreenshotToAllure(driver, "OPCDirectorDetails_Updated");
        } catch (AssertionError | InterruptedException e) {
            String message = "❌ Exception during OPC Director Details: " + e.getMessage();
            logger.error(message, e);
            logStep(message);
            logToAllure("❌ Exception", message);
            ScreenshotUtils.attachScreenshotToAllure(driver, "OPCDirectorDetails_Exception");
        }
        //Nominee Details
        try{
            logStep("✅ OPC Nominee details");
            opcPage.clickAddNomineeButton();
            opcPage.typeDirectorName(ConfigReader.get("directorName2"));
            opcPage.typeDirectorEmailAddress(ConfigReader.get("DirectorEmail2"));
            opcPage.typeDirectorMobileNumber(ConfigReader.get("DirectorMobile2"));
            opcPage.clickDoneButton();
            Thread.sleep(4000);
            opcPage.clickUploadByMyselfButton();
            Thread.sleep(4000);
            opcPage.selectAadharFront.sendKeys(ConfigReader.get("AadharUploadPic1"));
            Thread.sleep(2000);
            opcPage.selectAadharBack.sendKeys(ConfigReader.get("AadharUploadBack"));
            Thread.sleep(2000);
            opcPage.uploadPassportSize.sendKeys(ConfigReader.get("PassportPhoto1"));
            Thread.sleep(2000);
            opcPage.uploadPAN.sendKeys(ConfigReader.get("PAN1"));
            Thread.sleep(2000);
            opcPage.clickSignButton();
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
            opcPage.clickIAgreeSignNowButton();
            Thread.sleep(2000);
            opcPage.clickGotItButton();
            opcPage.clickOnSignCanvas();
            for(int i=0; i<2; i++) {
                opcPage.clickSaveAndNextButton();
                opcPage.clickOnSignCanvas();
            }
            opcPage.clickSubmitButton();
            Thread.sleep(2000);
            driver.close();
            driver.switchTo().window(parent);
            driver.navigate().refresh();
            Thread.sleep(2000);
            opcPage.clickUploadBankStatementCheckbox();
            Thread.sleep(2000);
            logStep("✅ OPC Nominee documents uploaded successfully");
            logToAllure("OPC Nominee Documents", "Nominee documents uploaded successfully");
            ScreenshotUtils.attachScreenshotToAllure(driver, "Nominee_Documents");
            opcPage.clickNextButton();
            opcPage.selectEducation(ConfigReader.get("PVTEducation"));
            opcPage.selectPlaceOfBirth(ConfigReader.get("PlaceOfBirth"));
            opcPage.selectWorkStatus(ConfigReader.get("WorkStatus"));
            opcPage.clickNextButton();
            //Address Info
            opcPage.typeAddressLine1(ConfigReader.get("Address1"));
            opcPage.typeAddressLine2(ConfigReader.get("Address2"));
            opcPage.typePinCode(ConfigReader.get("Pincode"));
            Thread.sleep(2000);
            opcPage.selectYear(ConfigReader.get("DurationYears"));
            opcPage.selectMonth(ConfigReader.get("DurationMonths"));
            opcPage.clickNextButton();
            Thread.sleep(2000);
            logStep("✅ OPC Nominee details successfully updated");
            logToAllure("OPC Nominee Details", "Nominee details successfully updated");
            ScreenshotUtils.attachScreenshotToAllure(driver, "OPCNomineeDetails_Updated");
            opcPage.clickNextButton();
        } catch (AssertionError | InterruptedException e) {
            String message = "❌ Exception during OPC Nominee Details: " + e.getMessage();
            logger.error(message, e);
            logStep(message);
            logToAllure("❌ Exception", message);
            ScreenshotUtils.attachScreenshotToAllure(driver, "OPCNomineeDetails_Exception");
        }
    }
    @When("the user update the OPC Company details")
    public void the_user_update_the_opc_company_details() throws InterruptedException {
        try{
            logStep("✅ OPC Company details");
            opcPage.typeCompEmailID(ConfigReader.get("EmailID"));
            opcPage.typeCompMobileNumber(ConfigReader.get("PhoneNumber"));
            opcPage.clickNextButton();
            Thread.sleep(2000);
            opcPage.clickPropertyType();
            opcPage.clickCompSubmitButton();
            Thread.sleep(2000);
            opcPage.clickCompConfirmButton();
            Thread.sleep(2000);
            opcPage.clickEnableUploadLaterCheckbox();
            opcPage.typeCompAddressLine1(ConfigReader.get("Address1"));
            opcPage.typeCompAddressLine2(ConfigReader.get("Address2"));
            opcPage.typeCompPincode(ConfigReader.get("Pincode"));
            Thread.sleep(2000);
            opcPage.selectCompAreaLocality(ConfigReader.get("OPCLocality"));
            logToAllure("✅ OPC Company details Validation", "Page redirected successfully");
            ScreenshotUtils.attachScreenshotToAllure(driver, "OPCCompanyDetails");
            opcPage.submitbutton();
            Thread.sleep(2000);
        } catch (AssertionError e) {
            String msg = "❌ Exception during updating the OPC company details: " + e.getMessage();
            logger.error(msg);
            logStep(msg);
            logToAllure("❌ Exception", msg);
            ScreenshotUtils.attachScreenshotToAllure(driver, "OPCCompanyDetails_Exception");
        }
    }
}
