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
import pages.BusinessCommencementPage;
import utils.*;

import java.awt.*;
import java.awt.datatransfer.DataFlavor;

import static utils.AllureLoggerUtils.logToAllure;

public class BusinessCommencementStepDefs {

    WebDriver driver = Hooks.driver;
    BusinessCommencementPage businessCommencementPage;
    Logger logger;
    AllureLoggerUtils allureLogging;
    WaitUtils wait;
    SearchTicketStepdefs searchTicketsteps;
    LoginStepDefinitions loginSteps;

    public BusinessCommencementStepDefs() {
        this.driver = Hooks.driver;
        this.businessCommencementPage = new BusinessCommencementPage(Hooks.driver);
        this.logger = LoggerUtils.getLogger(getClass());
        this.wait = new WaitUtils(driver, 30);
        this.searchTicketsteps = new SearchTicketStepdefs(Hooks.driver);
        this.loginSteps = new LoginStepDefinitions();
    }

    @Step("{message}")
    public void logStep(String message) {
        // Allure will log this message as a step
    }

    @Given("the user redirect to Business Commencement service flow")
    public void the_user_redirect_to_business_commencement_service_flow() {
        try {

            searchTicketsteps.theUserRedirectToTicketListPage();
            searchTicketsteps.theUserEnterTheTicket();
            searchTicketsteps.theUserClickTheSearchCTA();
            searchTicketsteps.theUserViewTheTicketPageByClickingOnTheLink();

            businessCommencementPage.crossSaleNew();
            businessCommencementPage.createservice(ConfigReader.get("BusinessCommencement"));
            businessCommencementPage.selectingtheservice();
            Thread.sleep(2000);
            businessCommencementPage.createbutton();
            logger.info("Service creation initiated.");
            ScreenshotUtils.attachScreenshotToAllure(driver, "2_Service_Created");

            Thread.sleep(15000);
            businessCommencementPage.recenttickets();
            Thread.sleep(4000);
            logger.info("Recent Tickets modal opened.");
            ScreenshotUtils.attachScreenshotToAllure(driver, "3_Recent_Tickets_Modal");

            try {
                WebElement firstTicket = driver.findElement(By.cssSelector("tr.ticket_index_tr ul.tcktdetails li a"));
                firstTicket.click();
                logger.info("Clicked first dynamic ticket link.");
                Thread.sleep(10000);
                ScreenshotUtils.attachScreenshotToAllure(driver, "4_Selected_Ticket_Page");

                businessCommencementPage.copyurl.click();
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




            logStep("🌐 Navigating to the Business Commencement service flow...");
            Thread.sleep(1000);
            logger.info("Navigated to URL: {}", ConfigReader.get("BusinessCommencementURL"));
            logStep("✅ Open Business Commencement Page");
            logToAllure("✅ Business Commencement Page Redirection Validation", "Business Commencement Page redirected successfully");
            ScreenshotUtils.attachScreenshotToAllure(driver, "BusinessCommencement");
        } catch (IllegalArgumentException | WebDriverException | AssertionError e) {
            String msg = "❌ Exception during Business Commencement flow: " + e.getMessage();
            logger.error(msg);
            logStep(msg);
            logToAllure("❌ Exception", msg);
            ScreenshotUtils.attachScreenshotToAllure(driver, "BusinessCommencement_Exception");
        }
        catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    @When("the user update the Business Details")
    public void the_user_update_the_business_details() {
        try {
            businessCommencementPage.typeCompanyName(ConfigReader.get("CompanyName"));
            businessCommencementPage.typeCIN(ConfigReader.get("CompanyCIN"));
            logger.info("Updated Business Details");
            logStep("✅ Open Business Details Page");
            logToAllure("✅ Business Details Update", "Business Details updated successfully");
            ScreenshotUtils.attachScreenshotToAllure(driver, "BusinessDetails");
            businessCommencementPage.clickNextButton();
        } catch (IllegalArgumentException | WebDriverException | AssertionError e) {
            String msg = "❌ Exception during Business Details flow: " + e.getMessage();
            logger.error(msg);
            logStep(msg);
            logToAllure("❌ Exception", msg);
            ScreenshotUtils.attachScreenshotToAllure(driver, "BusinessDetails_Exception");
        }
    }

    @When("the user update the Business Other Info")
    public void the_user_update_the_business_other_info() {
        try {
            businessCommencementPage.typeConfirmAccountDetails(ConfigReader.get("ConfirmAccountDetails"));
            logger.info("Updated Business Other Info");
            logStep("✅ Open Business Other Info Page");
            logToAllure("✅ Business Other Info Update", "Business Other Info updated successfully");
            ScreenshotUtils.attachScreenshotToAllure(driver, "BusinessOtherInfo");
            businessCommencementPage.clickNextButton();
        } catch (IllegalArgumentException | WebDriverException | AssertionError e) {
            String msg = "❌ Exception during Business Other Info flow: " + e.getMessage();
            logger.error(msg);
            logStep(msg);
            logToAllure("❌ Exception", msg);
            ScreenshotUtils.attachScreenshotToAllure(driver, "BusinessOtherInfo_Exception");
        }
    }
}
