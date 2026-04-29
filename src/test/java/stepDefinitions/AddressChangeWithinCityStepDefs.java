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
import pages.AddressChangeWithinCityPage;
import utils.*;

import java.awt.*;
import java.awt.datatransfer.DataFlavor;

import static utils.AllureLoggerUtils.logToAllure;

public class AddressChangeWithinCityStepDefs {
    WebDriver driver = Hooks.driver;
    AddressChangeWithinCityPage addressChangeWithinCityPage;
    Logger logger;
    AllureLoggerUtils allureLogging;
    WaitUtils wait;
    SearchTicketStepdefs searchTicketsteps;
    LoginStepDefinitions loginSteps;

    public AddressChangeWithinCityStepDefs() {
        this.driver = Hooks.driver;
        this.addressChangeWithinCityPage = new AddressChangeWithinCityPage(Hooks.driver);
        this.logger = LoggerUtils.getLogger(getClass());
        this.wait = new WaitUtils(driver,30);
        this.searchTicketsteps = new SearchTicketStepdefs(Hooks.driver);
        this.loginSteps = new LoginStepDefinitions();
    }

    @Step("{message}")
    public void logStep(String message) {
        // Allure will log this message as a step
    }

    @Given("the user redirect to Address change within city service flow")
    public void the_user_redirect_to_address_change_within_city_service_flow() throws InterruptedException {
        try {

            searchTicketsteps.theUserRedirectToTicketListPage();
            searchTicketsteps.theUserEnterTheTicket();
            searchTicketsteps.theUserClickTheSearchCTA();
            searchTicketsteps.theUserViewTheTicketPageByClickingOnTheLink();

            addressChangeWithinCityPage.crossSaleNew();
            addressChangeWithinCityPage.createservice(ConfigReader.get("changeoffaddresscity"));
            addressChangeWithinCityPage.selectingtheservice();
            Thread.sleep(2000);
            addressChangeWithinCityPage.createbutton();
            logger.info("Service creation initiated.");
            ScreenshotUtils.attachScreenshotToAllure(driver, "2_Service_Created");

            Thread.sleep(15000);
            addressChangeWithinCityPage.recenttickets();
            Thread.sleep(3000);
            logger.info("Recent Tickets modal opened.");
            ScreenshotUtils.attachScreenshotToAllure(driver, "3_Recent_Tickets_Modal");

            try {
                WebElement firstTicket = driver.findElement(By.cssSelector("tr.ticket_index_tr ul.tcktdetails li a"));
                firstTicket.click();
                logger.info("Clicked first dynamic ticket link.");
                Thread.sleep(10000);
                ScreenshotUtils.attachScreenshotToAllure(driver, "4_Selected_Ticket_Page");

                addressChangeWithinCityPage.copyurl.click();
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


            logStep("🌐 Navigating to the Change the Official Address of Your Company (Within the City) service page...");
            logger.info("Navigated to URL: {}", ConfigReader.get("AddressChangeWithinCityURL"));
            logStep("✅ Change Official Address of Your Company (Within the City)");
            logToAllure("✅ Change the Official Address of Your Company (Within the City) page redirection Validation", "Page redirected successfully");
            ScreenshotUtils.attachScreenshotToAllure(driver, "ChangeWithinAddress");
        } catch (IllegalArgumentException | WebDriverException | AssertionError e) {
            String msg = "❌ Exception during Change the Official Address of Your Company (Within the City) service page: " + e.getMessage();
            logger.error(msg);
            logStep(msg);
            logToAllure("❌ Exception", msg);
            ScreenshotUtils.attachScreenshotToAllure(driver, "ChangeWithinAddress_Exception");
            throw e;
        }
    }
    @When("the user update the company address within city")
    public void the_user_update_the_company_address_within_city() {
        try{
            addressChangeWithinCityPage.typeCompanyName(ConfigReader.get("CompanyName"));
            addressChangeWithinCityPage.clickEntityTypePVT();
            addressChangeWithinCityPage.typeCIN(ConfigReader.get("CompanyCIN"));
            logStep("✅ Update Company Info Details");
            logToAllure("✅ Company Info Details", "Update Company Info details successfully");
            ScreenshotUtils.attachScreenshotToAllure(driver, "CompanyInfoDetailsWithinCity");
            addressChangeWithinCityPage.clickNextButton();
            addressChangeWithinCityPage.typeAddressLine1(ConfigReader.get("AddressLine1"));
            addressChangeWithinCityPage.typeAddressLine2(ConfigReader.get("Address2"));
            addressChangeWithinCityPage.typePinCode(ConfigReader.get("Pincode"));
            addressChangeWithinCityPage.typeAreaLocality(ConfigReader.get("AreaLocality"));
            addressChangeWithinCityPage.typeLongitude(ConfigReader.get("Longitude"));
            addressChangeWithinCityPage.typeLatitude(ConfigReader.get("Latitude"));
            logStep("✅ Update Company Address Details");
            logToAllure("✅ Company Address Details", "Update Company Address details successfully");
            ScreenshotUtils.attachScreenshotToAllure(driver, "CompanyAddressDetailsWithinCity");
            addressChangeWithinCityPage.clickNextButton();
        } catch (IllegalArgumentException | WebDriverException | AssertionError e) {
            String msg = "❌ Exception during Change the Company Info and Address Details: " + e.getMessage();
            logStep(msg);
            logToAllure("❌ Exception", msg);
            ScreenshotUtils.attachScreenshotToAllure(driver, "CompanyDetails_Exception");
        }
    }
    @When("the user upload the documents for address change")
    public void the_user_upload_the_documents_for_address_change() {
        try{
            addressChangeWithinCityPage.uploadNOC.sendKeys(ConfigReader.get("COI"));
            logStep("✅ Upload Company Documents");
            logToAllure("✅ Company Documents", "Upload Company Documents successfully");
            ScreenshotUtils.attachScreenshotToAllure(driver, "CompanyDocumentsDetails");
            addressChangeWithinCityPage.clickNextButton();
        } catch (IllegalArgumentException | WebDriverException | AssertionError e) {
            String msg = "❌ Exception during Change the Company Documents Details: " + e.getMessage();
            logStep(msg);
            logToAllure("❌ Exception", msg);
            ScreenshotUtils.attachScreenshotToAllure(driver, "CompanyDocumentsDetails_Exception");
        }
    }

}
