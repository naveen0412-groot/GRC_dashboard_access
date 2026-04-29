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
import pages.OutsideAddressChangePage;
import utils.*;

import java.awt.*;
import java.awt.datatransfer.DataFlavor;

import static utils.AllureLoggerUtils.logToAllure;

public class OutsideAddressChangeStepDefs {

    WebDriver driver = Hooks.driver;
    OutsideAddressChangePage outsideAddressChangePage;
    Logger logger;
    AllureLoggerUtils allureLogging;
    WaitUtils wait;
    SearchTicketStepdefs searchTicketsteps;
    LoginStepDefinitions loginSteps;

    public OutsideAddressChangeStepDefs() {
        this.driver = Hooks.driver;
        this.outsideAddressChangePage = new OutsideAddressChangePage(Hooks.driver);
        this.logger = LoggerUtils.getLogger(getClass());
        this.wait = new WaitUtils(driver,30);
        this.searchTicketsteps = new SearchTicketStepdefs(Hooks.driver);
        this.loginSteps = new LoginStepDefinitions();
    }

    @Step("{message}")
    public void logStep(String message) {
        // Allure will log this message as a step
    }

    @Given("the user redirect to Outside Address Change service flow")
    public void the_user_redirect_to_outside_address_change_service_flow() throws InterruptedException {
        try {

            searchTicketsteps.theUserRedirectToTicketListPage();
            searchTicketsteps.theUserEnterTheTicket();
            searchTicketsteps.theUserClickTheSearchCTA();
            searchTicketsteps.theUserViewTheTicketPageByClickingOnTheLink();

            outsideAddressChangePage.crossSaleNew();
            outsideAddressChangePage.createservice(ConfigReader.get("Changecompanyoutsidecity"));
            outsideAddressChangePage.selectingtheservice();
            Thread.sleep(2000);
            outsideAddressChangePage.createbutton();
            logger.info("Service creation initiated.");
            ScreenshotUtils.attachScreenshotToAllure(driver, "2_Service_Created");

            Thread.sleep(15000);
            outsideAddressChangePage.recenttickets();
            Thread.sleep(4000);
            logger.info("Recent Tickets modal opened.");
            ScreenshotUtils.attachScreenshotToAllure(driver, "3_Recent_Tickets_Modal");

            try {
                WebElement firstTicket = driver.findElement(By.cssSelector("tr.ticket_index_tr ul.tcktdetails li a"));
                firstTicket.click();
                logger.info("Clicked first dynamic ticket link.");
                Thread.sleep(10000);
                ScreenshotUtils.attachScreenshotToAllure(driver, "4_Selected_Ticket_Page");

                outsideAddressChangePage.copyurl.click();
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


            logStep("🌐 Navigating to the Change the Official Address of Your Company (Outside the City) service page...");
            logStep("✅ Change Official Address of Your Company (Outside the City)");
            logToAllure("✅ Change the Official Address of Your Company (Outside the City) page redirection Validation", "Page redirected successfully");
            ScreenshotUtils.attachScreenshotToAllure(driver, "ChangeOutsideAddress");
        } catch (AssertionError | WebDriverException e) {
            String msg = "❌ Exception during Change the Official Address of Your Company (Outside the City) service page: " + e.getMessage();
            logger.error(msg);
            logStep(msg);
            logToAllure("❌ Exception", msg);
            ScreenshotUtils.attachScreenshotToAllure(driver, "ChangeOutsideAddress_Exception");
            throw e;
        }
    }
    @When("the user update the company info details")
    public void the_user_update_the_company_info_details() {
        try{
            outsideAddressChangePage.typeCompanyName(ConfigReader.get("CompanyName"));
            outsideAddressChangePage.typeCompanyCIN(ConfigReader.get("CompanyCIN"));
            logStep("✅ Update Company Info Details");
            logToAllure("✅ Company Info Details", "Update Company Info details successfully");
            ScreenshotUtils.attachScreenshotToAllure(driver, "CompanyInfoDetails");
            outsideAddressChangePage.clickNextButton();
        } catch (AssertionError e) {
            String msg = "❌ Exception during Change the Company Info Details: " + e.getMessage();
            logStep(msg);
            logToAllure("❌ Exception", msg);
            ScreenshotUtils.attachScreenshotToAllure(driver, "CompanyInfoDetails_Exception");
        }
    }
    @When("the user update the address details")
    public void the_user_update_the_address_details() {
        try{
            outsideAddressChangePage.typeAddressLine1(ConfigReader.get("Address1"));
            outsideAddressChangePage.typeAddressLine2(ConfigReader.get("Address2"));
            outsideAddressChangePage.typeCountry(ConfigReader.get("Country"));
            outsideAddressChangePage.typePinCode(ConfigReader.get("Pincode"));
            outsideAddressChangePage.typeLocality(ConfigReader.get("AreaLocality"));
            outsideAddressChangePage.typeLatitude(ConfigReader.get("Latitude"));
            outsideAddressChangePage.typeLongitude(ConfigReader.get("Longitude"));
            outsideAddressChangePage.typeJurisdiction(ConfigReader.get("Jurisdiction"));
            /*outsideAddressChangePage.clickNextButton();
            outsideAddressChangePage.typeRocUpToDate(ConfigReader.get("RocUpToDate"));*/
            logStep("✅ Update Company Address Details");
            logToAllure("✅ Company Address Details", "Update Company Address details successfully");
            ScreenshotUtils.attachScreenshotToAllure(driver, "CompanyAddressDetails");
            outsideAddressChangePage.clickNextButton();
        } catch (AssertionError e) {
            String msg = "❌ Exception during Change the Company Address Details: " + e.getMessage();
            logStep(msg);
            logToAllure("❌ Exception", msg);
            ScreenshotUtils.attachScreenshotToAllure(driver, "CompanyAddressDetails_Exception");
        }
    }

}
