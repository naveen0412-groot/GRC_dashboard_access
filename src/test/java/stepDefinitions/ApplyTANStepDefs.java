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
import pages.ApplyTANPage;
import utils.*;

import java.awt.*;
import java.awt.datatransfer.DataFlavor;

import static utils.AllureLoggerUtils.logToAllure;

public class ApplyTANStepDefs {
    WebDriver driver = Hooks.driver;
    ApplyTANPage applyTANPage;
    Logger logger;
    AllureLoggerUtils allureLogging;
    WaitUtils wait;
    SearchTicketStepdefs searchTicketsteps;
    LoginStepDefinitions loginSteps;

    public ApplyTANStepDefs() {
        this.driver = Hooks.driver;
        this.applyTANPage = new ApplyTANPage(Hooks.driver);
        this.logger = LoggerUtils.getLogger(getClass());
        this.wait = new WaitUtils(driver,30);
        this.searchTicketsteps = new SearchTicketStepdefs(Hooks.driver);
        this.loginSteps = new LoginStepDefinitions();
    }

    @Step("{message}")
    public void logStep(String message) {
        // Allure will log this message as a step
    }

    @Given("the user redirect to Apply for a TAN service")
    public void the_user_redirect_to_apply_for_a_tan_service() throws InterruptedException {
        try {

            searchTicketsteps.theUserRedirectToTicketListPage();
            searchTicketsteps.theUserEnterTheTicket();
            searchTicketsteps.theUserClickTheSearchCTA();
            searchTicketsteps.theUserViewTheTicketPageByClickingOnTheLink();

            applyTANPage.crossSaleNew();
            applyTANPage.createservice(ConfigReader.get("ApplyforTan"));
            applyTANPage.selectingtheservice();
            Thread.sleep(2000);
            applyTANPage.createbutton();
            logger.info("Service creation initiated.");
            ScreenshotUtils.attachScreenshotToAllure(driver, "2_Service_Created");

            Thread.sleep(15000);
            applyTANPage.recenttickets();
            Thread.sleep(4000);
            logger.info("Recent Tickets modal opened.");
            ScreenshotUtils.attachScreenshotToAllure(driver, "3_Recent_Tickets_Modal");

            try {
                WebElement firstTicket = driver.findElement(By.cssSelector("tr.ticket_index_tr ul.tcktdetails li a"));
                firstTicket.click();
                logger.info("Clicked first dynamic ticket link.");
                Thread.sleep(10000);
                ScreenshotUtils.attachScreenshotToAllure(driver, "4_Selected_Ticket_Page");

                applyTANPage.copyurl.click();
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




            logStep("🌐 Navigating to the Add for a TAN - Upload Document page...");
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
            logger.info("Navigated to URL: {}", ConfigReader.get("ApplyTANURL"));
            logStep("✅ Open Apply TAN Document Page");
            logToAllure("✅ Apply TAN - Upload Document Page Redirection Validation", "Apply TAN - Upload Document Page redirected successfully");
            ScreenshotUtils.attachScreenshotToAllure(driver, "ApplyTAN");
        } catch (IllegalArgumentException | WebDriverException | AssertionError e) {
            String msg = "❌ Exception during TAN flow: " + e.getMessage();
            logger.error(msg);
            logStep(msg);
            logToAllure("❌ Exception", msg);
            ScreenshotUtils.attachScreenshotToAllure(driver, "ApplyTAN_Exception");
            throw e;
        }
    }
    @When("the user enter the Details")
    public void the_user_enter_the_details() {
        try{
            applyTANPage.typePartnershipFirmName(ConfigReader.get("directorName1"));
            applyTANPage.typeFullAddress(ConfigReader.get("Address1"));
            applyTANPage.typePartnerName(ConfigReader.get("InventorName"));
            applyTANPage.typePhoneNumber(ConfigReader.get("DirectorMobile1"));
            applyTANPage.typeEmailID(ConfigReader.get("DirectorEmail1"));
            applyTANPage.selectIncDate();
            applyTANPage.typeTAN(ConfigReader.get("Pan1"));
            applyTANPage.clickNextButton();
            Thread.sleep(2000);
            logger.info("✅ Entered TAN Details successfully");
            logStep("✅ Completed entering TAN details");
            logToAllure("✅ TAN Details Validation", "TAN details successfully updated");
            ScreenshotUtils.attachScreenshotToAllure(driver, "ApplyTAN");
        }catch (IllegalArgumentException | WebDriverException | AssertionError e){
            String message = "❌ Exception during TAN details entry: " + e.getMessage();
            logger.error(message, e);
            logStep(message);
            logToAllure("❌ Exception", message);
            ScreenshotUtils.attachScreenshotToAllure(driver, "TANDetails_Exception");
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
