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
import pages.AddressChangeWithinStatePage;
import utils.*;

import java.awt.*;
import java.awt.datatransfer.DataFlavor;

import static utils.AllureLoggerUtils.logToAllure;

public class AddressChangeWithinStateStepDefs {
    WebDriver driver = Hooks.driver;
    AddressChangeWithinStatePage addressChangeWithinStatePage;
    Logger logger;
    AllureLoggerUtils allureLogging;
    WaitUtils wait;
    SearchTicketStepdefs searchTicketsteps;
    LoginStepDefinitions loginSteps;

    public AddressChangeWithinStateStepDefs() {
        this.driver = Hooks.driver;
        this.addressChangeWithinStatePage = new AddressChangeWithinStatePage(Hooks.driver);
        this.logger = LoggerUtils.getLogger(getClass());
        this.wait = new WaitUtils(driver,30);
        this.searchTicketsteps = new SearchTicketStepdefs(Hooks.driver);
        this.loginSteps = new LoginStepDefinitions();
    }

    @Step("{message}")
    public void logStep(String message) {
        // Allure will log this message as a step
    }
    @Given("the user redirect to LLP Address change within state service flow")
    public void the_user_redirect_to_llp_address_change_within_state_service_flow() throws InterruptedException {
        try {

            searchTicketsteps.theUserRedirectToTicketListPage();
            searchTicketsteps.theUserEnterTheTicket();
            searchTicketsteps.theUserClickTheSearchCTA();
            searchTicketsteps.theUserViewTheTicketPageByClickingOnTheLink();

            addressChangeWithinStatePage.crossSaleNew();
            addressChangeWithinStatePage.createservice(ConfigReader.get("changetheaddressstate"));
            addressChangeWithinStatePage.selectingtheservice();
            Thread.sleep(2000);
            addressChangeWithinStatePage.createbutton();
            logger.info("Service creation initiated.");
            ScreenshotUtils.attachScreenshotToAllure(driver, "2_Service_Created");

            Thread.sleep(15000);
            addressChangeWithinStatePage.recenttickets();
            Thread.sleep(4000);
            logger.info("Recent Tickets modal opened.");
            ScreenshotUtils.attachScreenshotToAllure(driver, "3_Recent_Tickets_Modal");

            try {
                WebElement firstTicket = driver.findElement(By.cssSelector("tr.ticket_index_tr ul.tcktdetails li a"));
                firstTicket.click();
                logger.info("Clicked first dynamic ticket link.");
                Thread.sleep(10000);
                ScreenshotUtils.attachScreenshotToAllure(driver, "4_Selected_Ticket_Page");

                addressChangeWithinStatePage.copyurl.click();
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
            logStep("🌐 Navigating to the Change the Official Address of Your Company (Within the State) service page...");
            logger.info("Navigated to URL: {}", ConfigReader.get("AddressChangeWithinStateURL"));
            logStep("✅ Change Official Address of Your Company (Within the State)");
            logToAllure("✅ Change the Official Address of Your Company (Within the State) page redirection Validation", "Page redirected successfully");
            ScreenshotUtils.attachScreenshotToAllure(driver, "ChangeWithinStateAddress");
        } catch (IllegalArgumentException | WebDriverException | AssertionError e) {
            String msg = "❌ Exception during Change the Official Address of Your Company (Within the State) service page: " + e.getMessage();
            logger.error(msg);
            logStep(msg);
            logToAllure("❌ Exception", msg);
            ScreenshotUtils.attachScreenshotToAllure(driver, "ChangeWithinStateAddress_Exception");
            throw e;
        }
    }
    @When("the user update the LLP details within state")
    public void the_user_update_the_llp_details_within_state() {
        try{
            addressChangeWithinStatePage.typeLLPName(ConfigReader.get("LLPName"));
            addressChangeWithinStatePage.clickNextButton();
            addressChangeWithinStatePage.typeLLPIN(ConfigReader.get("LLPIN"));
            logStep("✅ Update LLP Details");
            logToAllure("✅ LLP Details", "Update LLP details successfully");
            ScreenshotUtils.attachScreenshotToAllure(driver, "LLPDetailsWithinState");
            addressChangeWithinStatePage.clickNextButton();
        } catch (IllegalArgumentException | WebDriverException | AssertionError e) {
            String msg = "❌ Exception during Change the LLP Details within the state: " + e.getMessage();
            logStep(msg);
            logToAllure("❌ Exception", msg);
            ScreenshotUtils.attachScreenshotToAllure(driver, "LLPDetailsWithinState_Exception");
        }
    }
    @When("the user upload the office details change within state")
    public void the_user_upload_the_office_details_change_within_state() {
        try{
            addressChangeWithinStatePage.typeNewAddressLine1(ConfigReader.get("Address1"));
            addressChangeWithinStatePage.typeNewAddressLine2(ConfigReader.get("Address2"));
            addressChangeWithinStatePage.typePinCode(ConfigReader.get("Pincode"));
            addressChangeWithinStatePage.typeLocality(ConfigReader.get("AreaLocality"));
            addressChangeWithinStatePage.typeLatitude(ConfigReader.get("Latitude"));
            addressChangeWithinStatePage.typeLongitude(ConfigReader.get("Longitude"));
            addressChangeWithinStatePage.typeJurisdiction(ConfigReader.get("Jurisdiction"));
            addressChangeWithinStatePage.clickNextButton();
            addressChangeWithinStatePage.clickUptoDateAnnualFilings();
            addressChangeWithinStatePage.clickUptoStampPaper();
            logStep("✅ Update LLP Office Address Details");
            logToAllure("✅ LLP Office Address Details", "Update LLP Office Address details successfully");
            ScreenshotUtils.attachScreenshotToAllure(driver, "LLPOfficeAddressDetailsWithinCity");
            addressChangeWithinStatePage.clickNextButton();
        } catch (IllegalArgumentException | WebDriverException | AssertionError e) {
            String msg = "❌ Exception during Change the LLP Office Address Details: " + e.getMessage();
            logStep(msg);
            logToAllure("❌ Exception", msg);
            ScreenshotUtils.attachScreenshotToAllure(driver, "LLPOfficeAddressDetails_Exception");
        }
    }

}
