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
import pages.CompanyAuditPage;
import pages.GSTCancellationPage;
import utils.*;

import java.awt.*;
import java.awt.datatransfer.DataFlavor;

import static utils.AllureLoggerUtils.logToAllure;

public class GSTCancellationStepDefs {


    WebDriver driver = Hooks.driver;
    GSTCancellationPage gstCancellationPage;
    Logger logger;
    SearchTicketStepdefs searchTicketsteps;
    LoginStepDefinitions loginSteps;

    public GSTCancellationStepDefs() {
        this.driver = Hooks.driver;
        this.gstCancellationPage = new GSTCancellationPage(Hooks.driver);
        this.logger = LoggerUtils.getLogger(getClass());
        this.searchTicketsteps = new SearchTicketStepdefs(Hooks.driver);
        this.loginSteps = new LoginStepDefinitions();
    }

    @Step("{message}")
    public void logStep(String message) {
        // Allure will log this message as a step
    }

    @Given("the user redirect to the GST Cancellation service")
    public void the_user_redirect_to_the_gst_cancellation_service() {
        try {

            searchTicketsteps.theUserRedirectToTicketListPage();
            searchTicketsteps.theUserEnterTheTicket();
            searchTicketsteps.theUserClickTheSearchCTA();
            searchTicketsteps.theUserViewTheTicketPageByClickingOnTheLink();

            gstCancellationPage.crossSaleNew();
            gstCancellationPage.createservice(ConfigReader.get("Cancelgst"));
            gstCancellationPage.selectingtheservice();
            Thread.sleep(2000);
            gstCancellationPage.createbutton();
            logger.info("Service creation initiated.");
            ScreenshotUtils.attachScreenshotToAllure(driver, "2_Service_Created");

            Thread.sleep(15000);
            gstCancellationPage.recenttickets();
            Thread.sleep(4000);
            logger.info("Recent Tickets modal opened.");
            ScreenshotUtils.attachScreenshotToAllure(driver, "3_Recent_Tickets_Modal");

            try {
                WebElement firstTicket = driver.findElement(By.cssSelector("tr.ticket_index_tr ul.tcktdetails li a"));
                firstTicket.click();
                logger.info("Clicked first dynamic ticket link.");
                Thread.sleep(10000);
                ScreenshotUtils.attachScreenshotToAllure(driver, "4_Selected_Ticket_Page");

                gstCancellationPage.copyurl.click();
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




            logStep("🌐 Navigating to the Cancellation of GST service flow...");
            Thread.sleep(1000);
            logStep("✅ Open Cancellation of GST service Page");
            logToAllure("✅ Cancellation of GST - Page Redirection Validation", "GST Cancellation Page redirected successfully");
            ScreenshotUtils.attachScreenshotToAllure(driver, "GSTCancellation");
        } catch (AssertionError | InterruptedException e) {
            String msg = "❌ Exception during Cancellation of GST service flow: " + e.getMessage();
            logger.error(msg);
            logStep(msg);
            logToAllure("❌ Exception", msg);
            ScreenshotUtils.attachScreenshotToAllure(driver, "GSTCancellation_Exception");
        }
    }
    @When("the user update the GST Basic Details")
    public void the_user_update_the_gst_basic_details() throws InterruptedException {
        try {
            gstCancellationPage.typeGSTCredentials(ConfigReader.get("GSTCredentials"));
            gstCancellationPage.typeEntityType(ConfigReader.get("EntityType"));
            logger.info("Updating the GST Basic Details Flow...");
            logStep("✅ Start updating the GST Basic Details Flow");
            logToAllure("✅ Validate the GST Basic Details", "GST Cancellation - Basic Details updated successfully");
            ScreenshotUtils.attachScreenshotToAllure(driver, "GSTCancellationBasicDetails");
            gstCancellationPage.clickNextButton();
            Thread.sleep(2000);
        } catch (AssertionError e) {
            String msg = "❌ Exception during Updating the GST Basic Details flow: " + e.getMessage();
            logger.error(msg);
            logStep(msg);
            logToAllure("❌ Exception", msg);
            ScreenshotUtils.attachScreenshotToAllure(driver, "GSTCancellationBasicDetails_Exception");
        }
    }
    @When("the user update the GST Other Details")
    public void the_user_update_the_gst_other_details() throws InterruptedException {
        try {
            gstCancellationPage.typeReturnsFiledUpto(ConfigReader.get("ReturnsFiledUpto"));
            logger.info("Updating the GST Other Details Flow...");
            logStep("✅ Start updating the GST Other Details Flow");
            logToAllure("✅ Validate the GST Other Details", "GST Cancellation - Other Details updated successfully");
            ScreenshotUtils.attachScreenshotToAllure(driver, "GSTCancellationOtherDetails");
            gstCancellationPage.clickNextButton();
            Thread.sleep(2000);
        } catch (AssertionError e) {
            String msg = "❌ Exception during Updating the GST Other Details flow: " + e.getMessage();
            logger.error(msg);
            logStep(msg);
            logToAllure("❌ Exception", msg);
            ScreenshotUtils.attachScreenshotToAllure(driver, "GSTCancellationOtherDetails_Exception");
        }
    }
}
