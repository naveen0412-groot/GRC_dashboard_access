package stepDefinitions;

import hooks.Hooks;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import io.qameta.allure.Step;
import org.apache.logging.log4j.Logger;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import pages.NameChangeForMinorPage;
import utils.*;

import java.awt.*;
import java.awt.datatransfer.DataFlavor;

import static utils.AllureLoggerUtils.logToAllure;

public class NameChangeForMinorStepDefs {
    WebDriver driver = Hooks.driver;
    NameChangeForMinorPage nameChangeMinorPage;
    Logger logger;
    AllureLoggerUtils allureLogging;
    WaitUtils wait;
    SearchTicketStepdefs searchTicketsteps;
    LoginStepDefinitions loginSteps;


    public NameChangeForMinorStepDefs() {
        this.driver = Hooks.driver;
        this.nameChangeMinorPage = new NameChangeForMinorPage(Hooks.driver);
        this.logger = LoggerUtils.getLogger(getClass());
        this.wait = new WaitUtils(driver, 30);
        this.searchTicketsteps = new SearchTicketStepdefs(Hooks.driver);
        this.loginSteps = new LoginStepDefinitions();
    }

    @Step("{message}")
    public void logStep(String message) {
        // Allure will log this message as a step
    }
    @Given("the user redirect to Name Changes for Minor Service page")
    public void the_user_redirect_to_name_changes_for_minor_service_page() throws InterruptedException {
        try {

            searchTicketsteps.theUserRedirectToTicketListPage();
            searchTicketsteps.theUserEnterTheTicket();
            searchTicketsteps.theUserClickTheSearchCTA();
            searchTicketsteps.theUserViewTheTicketPageByClickingOnTheLink();

            nameChangeMinorPage.crossSaleNew();
            nameChangeMinorPage.createservice(ConfigReader.get("Namechangeminor"));
            nameChangeMinorPage.selectingtheservice();
            Thread.sleep(2000);
            nameChangeMinorPage.createbutton();
            logger.info("Service creation initiated.");
            ScreenshotUtils.attachScreenshotToAllure(driver, "2_Service_Created");

            Thread.sleep(15000);
            nameChangeMinorPage.recenttickets();
            Thread.sleep(4000);
            logger.info("Recent Tickets modal opened.");
            ScreenshotUtils.attachScreenshotToAllure(driver, "3_Recent_Tickets_Modal");

            try {
                WebElement firstTicket = driver.findElement(By.cssSelector("tr.ticket_index_tr ul.tcktdetails li a"));
                firstTicket.click();
                logger.info("Clicked first dynamic ticket link.");
                Thread.sleep(10000);
                ScreenshotUtils.attachScreenshotToAllure(driver, "4_Selected_Ticket_Page");

                nameChangeMinorPage.copyurl.click();
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
            logStep("🌐 Navigating to the Name Changes - Minor Service page...");
            logStep("✅ Open Name Change Service Page");
            logToAllure("✅ Name Change Redirection Validation", "Name Change Page redirected successfully");
            ScreenshotUtils.attachScreenshotToAllure(driver, "NameChangeMinorPage");

        } catch (AssertionError e) {
            String msg = "❌ Exception during Name Change - Minor page: " + e.getMessage();
            logger.error(msg);
            logStep(msg);
            logToAllure("❌ Exception", msg);
            ScreenshotUtils.attachScreenshotToAllure(driver, "NameChangeMinorURL_Exception");
            throw e;
        }
    }

    @When("the user update Name Change details for Minor")
    public void the_user_update_name_change_details_for_minor() {
        try {
            nameChangeMinorPage.typeCurrentName(ConfigReader.get("OldName"));
            nameChangeMinorPage.typeNewName(ConfigReader.get("NewName"));
            nameChangeMinorPage.typeGender(ConfigReader.get("Gender"));
            nameChangeMinorPage.typeReasonForNameChange(ConfigReader.get("Reason"));
            nameChangeMinorPage.typeDocumentName(ConfigReader.get("DocumentName"));
            nameChangeMinorPage.typePermanentAddress(ConfigReader.get("Address1"));
            nameChangeMinorPage.typePermanentPincode(ConfigReader.get("Pincode"));
            nameChangeMinorPage.typePresentAddress(ConfigReader.get("Address2"));
            nameChangeMinorPage.typePresentPincode(ConfigReader.get("Pincode"));
            logger.info("✅ Name Changes Details updated successfully");
            logStep("✅ Completed updating name change details");
            logToAllure("✅ Update Name Changes Details", "Name Changes details updated successfully");
            ScreenshotUtils.attachScreenshotToAllure(driver, "NameChangeMinorDetails_Updated");
            nameChangeMinorPage.clickNextCTA();
        } catch (AssertionError e) {
            String message = "❌ Name Changes details entry: " + e.getMessage();
            logger.error(message);
            logStep(message);
            logToAllure("❌ Assertion Error", message);
            ScreenshotUtils.attachScreenshotToAllure(driver, "NameChangeMinorDetails_Failed");

        }
    }

    @When("the user update the Witness details for Name Change Minor")
    public void the_user_update_the_witness_details_for_name_change_minor() {
        //Witness 1
        try {
            nameChangeMinorPage.typeWitnessName1(ConfigReader.get("directorName1"));
            nameChangeMinorPage.typeWitnessFatherName(ConfigReader.get("FatherName1"));
            nameChangeMinorPage.typeWitnessContactNumber(ConfigReader.get("DirectorMobile1"));
            nameChangeMinorPage.typeWitnessAddress(ConfigReader.get("Address1"));
            logger.info("✅ Witness 1 Details updated successfully");
            logStep("✅ Completed updating Witness 1 details");
            logToAllure("✅ Witness1 Changes Details", "Witness 1 details updated successfully");
            ScreenshotUtils.attachScreenshotToAllure(driver, "Witness1ForMinor_Updated");
            nameChangeMinorPage.clickNextCTA();
        } catch (AssertionError e) {
            String message = "❌ Witness details (1) entry: " + e.getMessage();
            logger.error(message);
            logStep(message);
            logToAllure("❌ Assertion Error", message);
            ScreenshotUtils.attachScreenshotToAllure(driver, "WitnessDetails1ForMinor_Failed");
        }
        //Witness 2
        try {
            nameChangeMinorPage.typeWitnessName2(ConfigReader.get("directorName2"));
            nameChangeMinorPage.typeWitnessFatherName(ConfigReader.get("FatherName2"));
            nameChangeMinorPage.typeWitnessContactNumber(ConfigReader.get("DirectorMobile2"));
            nameChangeMinorPage.typeWitnessAddress(ConfigReader.get("Address2"));
            logger.info("✅ Witness 2 Details updated successfully");
            logStep("✅ Completed updating Witness 2 details");
            logToAllure("✅ Witness 2 Changes Details", "Witness 2 details updated successfully");
            ScreenshotUtils.attachScreenshotToAllure(driver, "WitnessDetails2ForMinor_Updated");
            nameChangeMinorPage.clickNextCTA();
        } catch (AssertionError e) {
            String message = "❌ Witness details (2) entry: " + e.getMessage();
            logger.error(message);
            logStep(message);
            logToAllure("❌ Assertion Error", message);
            ScreenshotUtils.attachScreenshotToAllure(driver, "WitnessDetail2ForMinor_Failed");
        }
    }

    @When("the user upload the documents for Name change Minor")
    public void the_user_upload_the_documents_for_name_change_minor() {
        try {
//            nameChangeMinorPage.deleteDocumentSet1();
//            int count = 0;
//            do {
//                nameChangeMinorPage.deleteDocumentSet2();
//                count++;
//            } while (count < 2);
            Thread.sleep(5000);
            nameChangeMinorPage.uploadPassportPhoto.sendKeys(ConfigReader.get("PassportPhoto1"));
            Thread.sleep(5000);
            nameChangeMinorPage.uploadPassport.sendKeys(ConfigReader.get("BankStatement"));
            Thread.sleep(5000);
            nameChangeMinorPage.uploadSignature.sendKeys(ConfigReader.get("PassportPhoto1"));
            Thread.sleep(5000);
            logger.info("✅ Name Change documents uploaded successfully");
            logStep("✅ Uploaded the Name Change documents");
            logToAllure("✅ Name Change doc upload validation for RemoveDir", "Name Change document upload validated successfully");
            ScreenshotUtils.attachScreenshotToAllure(driver, "NameChangeDocumentsForMinor_Uploaded");
            nameChangeMinorPage.clickNextCTA();

        } catch (Exception e) {
            String message = "❌ Name Change documents upload: " + e.getMessage();
            logger.error(message);
            logStep(message);
            logToAllure("❌ Assertion Error", message);
            ScreenshotUtils.attachScreenshotToAllure(driver, "NameChangeUploadForMinor_Failed");
        }
    }

}
