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
import pages.ChangeLLPObjPage;
import utils.*;

import java.awt.*;
import java.awt.datatransfer.DataFlavor;

import static utils.AllureLoggerUtils.logToAllure;

public class ChangeLLPObjStepDefs {

    WebDriver driver = Hooks.driver;
    ChangeLLPObjPage changeLLPObjPage;
    Logger logger;
    AllureLoggerUtils allureLogging;
    WaitUtils wait;
    SearchTicketStepdefs searchTicketsteps;
    LoginStepDefinitions loginSteps;



    public ChangeLLPObjStepDefs() {
        this.driver = Hooks.driver;
        this.changeLLPObjPage = new ChangeLLPObjPage(Hooks.driver);
        this.logger = LoggerUtils.getLogger(getClass());
        this.wait = new WaitUtils(driver,30);
        this.searchTicketsteps = new SearchTicketStepdefs(Hooks.driver);
        this.loginSteps = new LoginStepDefinitions();
    }

    @Step("{message}")
    public void logStep(String message) {
        // Allure will log this message as a step
    }
    @Given("the user redirect to LLP Obj service")
    public void the_user_redirect_to_LLP_Obj_service() throws InterruptedException {
        try {

            searchTicketsteps.theUserRedirectToTicketListPage();
            searchTicketsteps.theUserEnterTheTicket();
            searchTicketsteps.theUserClickTheSearchCTA();
            searchTicketsteps.theUserViewTheTicketPageByClickingOnTheLink();

            changeLLPObjPage.crossSaleNew();
            changeLLPObjPage.createservice(ConfigReader.get("changeobjofLLP"));
            changeLLPObjPage.selectingtheservice5();
            Thread.sleep(2000);
            changeLLPObjPage.createbutton();
            logger.info("Service creation initiated.");
            ScreenshotUtils.attachScreenshotToAllure(driver, "2_Service_Created");

            Thread.sleep(15000);
            changeLLPObjPage.recenttickets();
            Thread.sleep(4000);
            logger.info("Recent Tickets modal opened.");
            ScreenshotUtils.attachScreenshotToAllure(driver, "3_Recent_Tickets_Modal");

            try {
                WebElement firstTicket = driver.findElement(By.cssSelector("tr.ticket_index_tr ul.tcktdetails li a"));
                firstTicket.click();
                logger.info("Clicked first dynamic ticket link.");
                Thread.sleep(10000);
                ScreenshotUtils.attachScreenshotToAllure(driver, "4_Selected_Ticket_Page");

                changeLLPObjPage.copyurl.click();
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

            logStep("🌐 Navigating to the Change the LLP  of Your Company service page...");
            logStep("✅ Change LLP Objective Of Company Document Page");
            logToAllure("✅ Change the LLP Objectives Redirection Validation", "Page redirected successfully");
            ScreenshotUtils.attachScreenshotToAllure(driver, "ChangeLLPObj");
        } catch (IllegalArgumentException | AssertionError e) {
            String msg = "❌ Exception during Change the LLP Objectives of Your Company service page: " + e.getMessage();
            logger.error(msg);
            logStep(msg);
            logToAllure("❌ Exception", msg);
            ScreenshotUtils.attachScreenshotToAllure(driver, "ChangeLLPObj_Exception");
            throw e;
        }
        }

    public class LLPStepDefinitions {

        @When("the user update the LLP Objectives Details")
        public void the_user_update_the_llp_objectives_details() {
            try {
                logStep("🚀 Starting LLP Objectives Update Process");

                String name = ConfigReader.get("LLPName");
                changeLLPObjPage.typeLLPName(name);
                logStep("Entered LLP Name: " + name);
                ScreenshotUtils.attachScreenshotToAllure(driver, "LLP_Name_Entered");

                changeLLPObjPage.clickNextButton();
                Thread.sleep(4000);

                // Step 2: LLPIN Entry
                String llpin = ConfigReader.get("LLPIN");
                changeLLPObjPage.typeLLPIn(llpin);
                logStep("Entered LLPIN: " + llpin);

                logStep("✅ LLP Details Identification Completed");
                logToAllure("✅ LLP Details", "LLP Name and PIN updated successfully");
                ScreenshotUtils.attachScreenshotToAllure(driver, "LLPIN_Details_Submitted");

                changeLLPObjPage.LLPINbutton();
                Thread.sleep(4000);
            } catch (Exception e) {
                handleException("Updating LLP Basic Details", e, "LLPODetails_Error");
            }
        }

        @When("the user update the Object details")
        public void the_user_update_the_object_details() {
            try {
                logStep("🚀 Transitioning to Object Details Section");
                Thread.sleep(4000);

                // Step 3: Fill Objectives
                String addObjective = ConfigReader.get("AddObjective");
                changeLLPObjPage.typeObjectsToBeAdded(addObjective);
                logStep("Injected New Objective: " + addObjective);

                String confirmDetails = ConfigReader.get("ConfirmAccountDetails");
                changeLLPObjPage.typeReplaceObject(confirmDetails);
                changeLLPObjPage.typeAddedNewObject(confirmDetails);

                logStep("Submitting Objectives form");
                changeLLPObjPage.Objectivesbutton();
                ScreenshotUtils.attachScreenshotToAllure(driver, "Objectives_Form_Filled");
                Thread.sleep(4000);

                changeLLPObjPage.typeAnnualFilings(confirmDetails);
                changeLLPObjPage.typeStampPaper(confirmDetails);
                logStep("Annual Filings and Stamp Paper details populated");

                logStep("✅ Change LLP Objective Of Company Document Page Verified");
                logToAllure("✅ Change the LLP Objectives Redirection Validation", "Page redirected and data populated successfully");
                ScreenshotUtils.attachScreenshotToAllure(driver, "Final_Review_Page");

                Thread.sleep(2000);
                changeLLPObjPage.lastbutton();
                logStep("Final submission button clicked");
                Thread.sleep(4000);

            } catch (Exception e) {
                handleException("Updating Object Details", e, "ChangeLLPObjDetails_Error");
            }
        }

        private void handleException(String context, Exception e, String screenshotName) {
            String msg = "❌ Exception during " + context + ": " + e.getMessage();
            logStep(msg);
            logToAllure("❌ Failure", msg);
            ScreenshotUtils.attachScreenshotToAllure(driver, screenshotName);
            Assert.fail(msg);
        }
    }
}
