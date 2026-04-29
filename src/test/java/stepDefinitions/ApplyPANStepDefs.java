package stepDefinitions;

import hooks.Hooks;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import io.qameta.allure.Step;
import org.apache.logging.log4j.Logger;
import org.junit.Assert;
import org.openqa.selenium.*;
import pages.ApplyPANPage;
import utils.*;

import java.awt.*;
import java.awt.datatransfer.DataFlavor;

import static utils.AllureLoggerUtils.logToAllure;

public class ApplyPANStepDefs {
    WebDriver driver = Hooks.driver;
    ApplyPANPage applyPANPage;
    Logger logger;
    AllureLoggerUtils allureLogging;
    WaitUtils wait;
    SearchTicketStepdefs searchTicketsteps;
    LoginStepDefinitions loginSteps;

    public ApplyPANStepDefs() {
        this.driver = Hooks.driver;
        this.applyPANPage = new ApplyPANPage(Hooks.driver);
        this.logger = LoggerUtils.getLogger(getClass());
        this.wait = new WaitUtils(driver,30);
        this.searchTicketsteps = new SearchTicketStepdefs(Hooks.driver);
        this.loginSteps = new LoginStepDefinitions();
    }

    @Step("{message}")
    public void logStep(String message) {
        // Allure will log this message as a step
    }

    @Given("the user redirect to upload document page of Apply for a PAN")
    public void the_user_redirect_to_upload_document_page_of_apply_for_a_pan() throws InterruptedException {
        try {

            searchTicketsteps.theUserRedirectToTicketListPage();
            searchTicketsteps.theUserEnterTheTicket();
            searchTicketsteps.theUserClickTheSearchCTA();
            searchTicketsteps.theUserViewTheTicketPageByClickingOnTheLink();

            applyPANPage.crossSaleNew();
            applyPANPage.createservice(ConfigReader.get("applyforPannumber"));
            applyPANPage.selectingtheservice();
            Thread.sleep(2000);
            applyPANPage.createbutton();
            logger.info("Service creation initiated.");
            ScreenshotUtils.attachScreenshotToAllure(driver, "2_Service_Created");

            Thread.sleep(15000);
            applyPANPage.recenttickets();
            Thread.sleep(4000);
            logger.info("Recent Tickets modal opened.");
            ScreenshotUtils.attachScreenshotToAllure(driver, "3_Recent_Tickets_Modal");

            try {
                WebElement firstTicket = driver.findElement(By.cssSelector("tr.ticket_index_tr ul.tcktdetails li a"));
                firstTicket.click();
                logger.info("Clicked first dynamic ticket link.");
                Thread.sleep(10000);
                ScreenshotUtils.attachScreenshotToAllure(driver, "4_Selected_Ticket_Page");

                applyPANPage.copyurl.click();
                logger.info("Copy button clicked.");
                Thread.sleep(4000);
                ScreenshotUtils.attachScreenshotToAllure(driver, "5_After_Copy_Action");

                String dynamicUrl = (String) Toolkit.getDefaultToolkit().getSystemClipboard().getData(DataFlavor.stringFlavor);

                if (dynamicUrl != null && dynamicUrl.startsWith("http")) {
                    logger.info("Navigating to dynamic URL captured: {}", dynamicUrl);
                    logStep("🌐 Navigating to: " + dynamicUrl);
                    driver.get(dynamicUrl);

                    Thread.sleep(3000);
                    ScreenshotUtils.attachScreenshotToAllure(driver, "6_Final_Destination_Page");
                } else {
                    throw new Exception("Clipboard did not contain a valid URL.");
                }

            } catch (Exception e) {
                logger.error("❌ Error during ticket selection/URL capture: {}", e.getMessage());
                ScreenshotUtils.attachScreenshotToAllure(driver, "Error_Capture_Failure");
                Assert.fail("Failed during dynamic link capture: " + e.getMessage());
            }




            logStep("🌐 Navigating to the Add for a PAN - Upload Document page...");
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
            logger.info("Navigated to URL: {}", ConfigReader.get("ApplyPANURL"));
            logStep("✅ Open Apply PAN Document Page");
            logToAllure("✅ Apply PAN - Upload Document Page Redirection Validation", "Apply PAN - Upload Document Page redirected successfully");
            ScreenshotUtils.attachScreenshotToAllure(driver, "ApplyPAN");

        } catch (IllegalArgumentException | WebDriverException | AssertionError e) {
            String msg = "❌ Exception during PAN flow: " + e.getMessage();
            logger.error(msg);
            logStep(msg);
            logToAllure("❌ Exception", msg);
            ScreenshotUtils.attachScreenshotToAllure(driver, "ApplyPAN_Exception");
            throw e;
        }

    }

    @When("the user update the Details")
    public void the_user_update_the_details() {
        try{
            applyPANPage.typePartnershipFirmName(ConfigReader.get("PartnershipFirmName"));
            applyPANPage.typeFullAddress(ConfigReader.get("FullAddress"));
            applyPANPage.typePartnerName(ConfigReader.get("PartnerName"));
            applyPANPage.typePhoneNumber(ConfigReader.get("PhoneNumber"));
            applyPANPage.typeEmailID(ConfigReader.get("EmailID"));
            applyPANPage.selectIncDate();
            applyPANPage.typePANNumber(ConfigReader.get("TANValue"));
            applyPANPage.clickNextButton();
            Thread.sleep(2000);
            logger.info("✅ Entered PAN Details successfully");
            logStep("✅ Completed entering PAN details");
            logToAllure("✅ PAN Details Validation", "PAN details successfully updated");
            ScreenshotUtils.attachScreenshotToAllure(driver, "ApplyPAN");
        } catch (TimeoutException e) {
            String message = "❌ Exception during PAN details entry: " + e.getMessage();
            logger.error(message, e);
            logStep(message);
            logToAllure("❌ Exception", message);
            ScreenshotUtils.attachScreenshotToAllure(driver, "PANDetails_Exception");

        } catch (IllegalArgumentException | AssertionError e) {
            logToAllure("❌ Validation Failure", e.getMessage());
            ScreenshotUtils.attachScreenshotToAllure(driver, "PANDetails_Exception");
            throw e;

        } catch (WebDriverException e) {
            logToAllure("❌ WebDriver Failure", e.getMessage());
            ScreenshotUtils.attachScreenshotToAllure(driver, "PANDetails_Exception");
            throw e;
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
