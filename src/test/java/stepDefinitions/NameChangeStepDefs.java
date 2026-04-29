package stepDefinitions;

import hooks.Hooks;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import io.qameta.allure.Step;
import org.apache.logging.log4j.Logger;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.WebElement;
import pages.AddingPartnerPage;
import pages.NameChangePage;
import utils.*;

import java.awt.*;
import java.awt.datatransfer.DataFlavor;

import static utils.AllureLoggerUtils.logToAllure;

public class NameChangeStepDefs {
    WebDriver driver = Hooks.driver;
    NameChangePage nameChangePage;
    Logger logger;
    AllureLoggerUtils allureLogging;
    WaitUtils wait;
    SearchTicketStepdefs searchTicketsteps;
    LoginStepDefinitions loginSteps;

    public NameChangeStepDefs() {
        this.driver = Hooks.driver;
        this.nameChangePage = new NameChangePage(Hooks.driver);
        this.logger = LoggerUtils.getLogger(getClass());
        this.wait = new WaitUtils(driver, 30);
        this.searchTicketsteps = new SearchTicketStepdefs(Hooks.driver);
        this.loginSteps = new LoginStepDefinitions();
    }

    @Step("{message}")
    public void logStep(String message) {
        // Allure will log this message as a step
    }

    @Given("the user redirect to Name Changes Service page")
    public void the_user_redirect_to_name_changes_service_page() throws InterruptedException {
        try {

            searchTicketsteps.theUserRedirectToTicketListPage();
            searchTicketsteps.theUserEnterTheTicket();
            searchTicketsteps.theUserClickTheSearchCTA();
            searchTicketsteps.theUserViewTheTicketPageByClickingOnTheLink();

            nameChangePage.crossSaleNew();
            nameChangePage.createservice(ConfigReader.get("Namechange"));
            nameChangePage.selectingtheservice(ConfigReader.get("Namechange"));
            Thread.sleep(2000);
            nameChangePage.createbutton();
            logger.info("Service creation initiated.");
            ScreenshotUtils.attachScreenshotToAllure(driver, "2_Service_Created");

            Thread.sleep(15000);
            nameChangePage.recenttickets();
            Thread.sleep(4000);
            logger.info("Recent Tickets modal opened.");
            ScreenshotUtils.attachScreenshotToAllure(driver, "3_Recent_Tickets_Modal");

            try {
                WebElement firstTicket = driver.findElement(By.cssSelector("tr.ticket_index_tr ul.tcktdetails li a"));
                firstTicket.click();
                logger.info("Clicked first dynamic ticket link.");
                Thread.sleep(10000);
                ScreenshotUtils.attachScreenshotToAllure(driver, "4_Selected_Ticket_Page");

                nameChangePage.copyurl.click();
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
            logStep("🌐 Navigating to the Name Changes Service page...");
            logStep("✅ Open Name Change Service Page");
            logToAllure("✅ Name Change Redirection Validation", "Name Change Page redirected successfully");
            ScreenshotUtils.attachScreenshotToAllure(driver, "NameChangePage");
        } catch (IllegalArgumentException | WebDriverException | AssertionError e) {
            String msg = "❌ Exception during Name Change page: " + e.getMessage();
            logger.error(msg);
            logStep(msg);
            logToAllure("❌ Exception", msg);
            ScreenshotUtils.attachScreenshotToAllure(driver, "NameChangeURL_Exception");
            throw e;
        }
    }

    @When("the user update Name Change details")
    public void the_user_update_name_change_details() {
        try {
            nameChangePage.typeCurrentName(ConfigReader.get("OldName"));
            nameChangePage.typeNewName(ConfigReader.get("NewName"));
            nameChangePage.typeGender(ConfigReader.get("Gender"));
            nameChangePage.typeReasonForNameChange(ConfigReader.get("Reason"));
            nameChangePage.typeDocumentName(ConfigReader.get("DocumentName"));
            nameChangePage.typePermanentAddress(ConfigReader.get("Address1"));
            nameChangePage.typePermanentPincode(ConfigReader.get("Pincode"));
            nameChangePage.typePresentAddress(ConfigReader.get("Address2"));
            nameChangePage.typePresentPincode(ConfigReader.get("Pincode"));
            logger.info("✅ Name Changes Details updated successfully");
            logStep("✅ Completed updating name change details");
            logToAllure("✅ Update Name Changes Details", "Name Changes details updated successfully");
            ScreenshotUtils.attachScreenshotToAllure(driver, "NameChangeDetails_Updated");
            nameChangePage.clickNextCTA();
        } catch (AssertionError e) {
            String message = "❌ Name Changes details entry: " + e.getMessage();
            logger.error(message);
            logStep(message);
            logToAllure("❌ Assertion Error", message);
            ScreenshotUtils.attachScreenshotToAllure(driver, "NameChangeDetails_Failed");

        }
    }

    @When("the user update the Witness details")
    public void the_user_update_the_witness_details() {
        //Witness 1
        try {
            nameChangePage.typeWitnessName(ConfigReader.get("directorName1"));
            nameChangePage.typeWitnessFatherName(ConfigReader.get("FatherName1"));
            nameChangePage.typeWitnessContactNumber(ConfigReader.get("DirectorMobile1"));
            nameChangePage.typeWitnessAddress(ConfigReader.get("Address1"));
            logger.info("✅ Witness 1 Details updated successfully");
            logStep("✅ Completed updating Witness 1 details");
            logToAllure("✅ Witness1 Changes Details", "Witness 1 details updated successfully");
            ScreenshotUtils.attachScreenshotToAllure(driver, "Witness1_Updated");
            nameChangePage.clickNextCTA();
        } catch (AssertionError e) {
            String message = "❌ Witness details (1) entry: " + e.getMessage();
            logger.error(message);
            logStep(message);
            logToAllure("❌ Assertion Error", message);
            ScreenshotUtils.attachScreenshotToAllure(driver, "WitnessDetails1_Failed");
        }
        //Witness 2
        try {
            nameChangePage.typeWitnessName(ConfigReader.get("directorName1"));
            nameChangePage.typeWitnessFatherName(ConfigReader.get("FatherName2"));
            nameChangePage.typeWitnessContactNumber(ConfigReader.get("DirectorMobile2"));
            nameChangePage.typeWitnessAddress(ConfigReader.get("Address2"));
            logger.info("✅ Witness 2 Details updated successfully");
            logStep("✅ Completed updating Witness 2 details");
            logToAllure("✅ Witness 2 Changes Details", "Witness 2 details updated successfully");
            ScreenshotUtils.attachScreenshotToAllure(driver, "Witness2_Updated");
            nameChangePage.clickNextCTA();
        } catch (AssertionError e) {
            String message = "❌ Witness details (2) entry: " + e.getMessage();
            logger.error(message);
            logStep(message);
            logToAllure("❌ Assertion Error", message);
            ScreenshotUtils.attachScreenshotToAllure(driver, "WitnessDetail2_Failed");
        }

    }

    @When("the user upload the documents for Name change service")
    public void the_user_upload_the_documents_for_name_change_service() {
        try {
//            nameChangePage.deleteDocumentSet1();
//            int count = 0;
//            do {
//                nameChangePage.deleteDocumentSet2();
//                count++;
//            } while (count < 2);
//            nameChangePage.deleteDocumentSet3();
            Thread.sleep(5000);
            nameChangePage.uploadPassportPhoto.sendKeys(ConfigReader.get("PAN1"));
            Thread.sleep(5000);
            nameChangePage.uploadPassport.sendKeys(ConfigReader.get("BankStatement"));
            Thread.sleep(5000);
            nameChangePage.uploadSignature.sendKeys(ConfigReader.get("PassportPhoto1"));
            Thread.sleep(5000);
            nameChangePage.uploadCertificate.sendKeys(ConfigReader.get("COI"));
            Thread.sleep(5000);
            logger.info("✅ Name Change documents uploaded successfully");
            logStep("✅ Uploaded the Name Change documents");
            logToAllure("✅ Name Change doc upload validation for RemoveDir", "Name Change document upload validated successfully");
            ScreenshotUtils.attachScreenshotToAllure(driver, "NameChangeDocuments_Uploaded");
            nameChangePage.clickNextCTA();

        } catch (Exception e) {
            String message = "❌ Name Change documents upload: " + e.getMessage();
            logger.error(message);
            logStep(message);
            logToAllure("❌ Assertion Error", message);
            ScreenshotUtils.attachScreenshotToAllure(driver, "NameChangeUpload_Failed");
        }
    }
}
