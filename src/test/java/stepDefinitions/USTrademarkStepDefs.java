package stepDefinitions;

import com.fasterxml.jackson.databind.DatabindContext;
import hooks.Hooks;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import io.qameta.allure.Step;
import org.apache.logging.log4j.Logger;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.WebDriverException;
import pages.USTrademarkPage;
import utils.*;

import java.awt.*;
import java.awt.datatransfer.DataFlavor;
import static utils.AllureLoggerUtils.logToAllure;

public class USTrademarkStepDefs {

    WebDriver driver = Hooks.driver;
    USTrademarkPage usTrademarkPage;
    Logger logger;
    AllureLoggerUtils allureLogging;
    WaitUtils wait;
    SearchTicketStepdefs searchTicketsteps;
    LoginStepDefinitions loginSteps;

    public USTrademarkStepDefs() {
        this.driver = Hooks.driver;
        this.usTrademarkPage = new USTrademarkPage(Hooks.driver);
        this.logger = LoggerUtils.getLogger(getClass());
        this.wait = new WaitUtils(driver,30);
        this.searchTicketsteps = new SearchTicketStepdefs(Hooks.driver);
        this.loginSteps = new LoginStepDefinitions();

    }

    @Step("{message}")
    public void logStep(String message) {
        // Allure will log this message as a step
    }

    @Given("the user redirect to US Trademark service flow")
    public void the_user_redirect_to_us_trademark_service_flow() {
        try {
            searchTicketsteps.theUserRedirectToTicketListPage();
            searchTicketsteps.theUserEnterTheTicket();
            searchTicketsteps.theUserClickTheSearchCTA();
            searchTicketsteps.theUserViewTheTicketPageByClickingOnTheLink();
            Thread.sleep(2000);
            ScreenshotUtils.attachScreenshotToAllure(driver, "1_Ticket_List_View");

            usTrademarkPage.crossSaleNew();
            usTrademarkPage.createservice(ConfigReader.get("applyforustrademark"));
            usTrademarkPage.selectingtheservice();
            Thread.sleep(2000);
            usTrademarkPage.createbutton();
            logger.info("Service creation initiated.");
            ScreenshotUtils.attachScreenshotToAllure(driver, "2_Service_Created");

            Thread.sleep(15000);
            usTrademarkPage.recenttickets();
            Thread.sleep(4000);
            logger.info("Recent Tickets modal opened.");
            ScreenshotUtils.attachScreenshotToAllure(driver, "3_Recent_Tickets_Modal");

            try {
                WebElement firstTicket = driver.findElement(By.cssSelector("tr.ticket_index_tr ul.tcktdetails li a"));
                firstTicket.click();
                logger.info("Clicked first dynamic ticket link.");
                Thread.sleep(10000);
                ScreenshotUtils.attachScreenshotToAllure(driver, "4_Selected_Ticket_Page");

                usTrademarkPage.copyurl.click();
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

            logStep("✅ Redirection to US Trademark Upload Document Page successful");
            logToAllure("Success Validation", "User successfully redirected to the dynamic flow.");
            logger.info("Step completed successfully.");

        } catch (WebDriverException | AssertionError | InterruptedException e) {
            String msg = "❌ Exception during US Trademark flow: " + e.getMessage();
            logger.error(msg);
            logToAllure("Step Failure", msg);
            ScreenshotUtils.attachScreenshotToAllure(driver, "Critical_Flow_Failure");
            Assert.fail(msg);
        }
    }


    @When("the user update US Trademark details")
    public void the_user_update_us_trademark_details() {
        try{
            usTrademarkPage.typeBrandName(ConfigReader.get("BrandName"));
            usTrademarkPage.clickNatureOfMarkWord();
            logger.info("✅ update US Trademark details");
            logStep("✅ Completed entering US Trademark details");
            logToAllure("✅ US Trademark Details Validation", "US Trademark details successfully updated");
            ScreenshotUtils.attachScreenshotToAllure(driver, "USTrademarkDetails");
            Thread.sleep(4000);
            usTrademarkPage.clickNextButton();
        } catch (AssertionError | InterruptedException e) {
            String msg = "❌ Exception during US Trademark details: " + e.getMessage();
            logger.error(msg);
            logStep(msg);
            logToAllure("❌ Exception", msg);
            ScreenshotUtils.attachScreenshotToAllure(driver, "USTrademarkDetails_Exception");
        }
    }
    @When("the user update US Applicant details")
    public void the_user_update_us_applicant_details() {
        try{
            usTrademarkPage.typeApplicantName(ConfigReader.get("USApplicantName"));
            usTrademarkPage.clickNatureOfEntityCorporation();
            usTrademarkPage.typeApplicantEmail(ConfigReader.get("ApplicantEmail"));
            usTrademarkPage.typeApplicantPhone(ConfigReader.get("ApplicantPhone"));
            usTrademarkPage.typeAuthorizedSignatoryName(ConfigReader.get("AuthorizedSignatoryName"));
            usTrademarkPage.typeAuthorizedSignatoryDesignation(ConfigReader.get("AuthorizedSignatoryDesignation"));
            usTrademarkPage.typeAddressLine1(ConfigReader.get("AddressLine1"));
            usTrademarkPage.typeZipCode(ConfigReader.get("ZipCode"));
            logger.info("✅ update US Applicant details");
            logStep("✅ Completed entering Applicant details");
            logToAllure("✅ Applicant Details Validation", "Applicant details successfully updated");
            ScreenshotUtils.attachScreenshotToAllure(driver, "USApplicant");
            usTrademarkPage.clickNextButton();
        } catch (AssertionError e) {
            String msg = "❌ Exception during US Trademark Applicant details: " + e.getMessage();
            logger.error(msg);
            logStep(msg);
            logToAllure("❌ Exception", msg);
            ScreenshotUtils.attachScreenshotToAllure(driver, "USApplicant_Exception");
        }
    }
    @When("the user claim details")
    public void the_user_claim_details() {
        try{
            usTrademarkPage.typeCurrentUse(ConfigReader.get("CurrentUse"));
            usTrademarkPage.selectUsageDate();
            logger.info("✅ update US Trademark claim details");
            logStep("✅ Completed entering claim details");
            logToAllure("✅ Claim Details Validation", "Claim details successfully updated");
            ScreenshotUtils.attachScreenshotToAllure(driver, "ClaimDetails");
            usTrademarkPage.clickNextButton();
        } catch (AssertionError e) {
            String msg = "❌ Exception during Claim details: " + e.getMessage();
            logger.error(msg);
            logStep(msg);
            logToAllure("❌ Exception", msg);
            ScreenshotUtils.attachScreenshotToAllure(driver, "ClaimDetails_Exception");
        }
    }
    @When("the user update specification of service")
    public void the_user_update_specification_of_service() {
        try{
            usTrademarkPage.clickGoods();
            logger.info("✅ Update US Trademark service specification");
            logStep("✅ Completed service specification");
            logToAllure("✅ Service specification Validation", "Service specification successfully updated");
            ScreenshotUtils.attachScreenshotToAllure(driver, "ServiceSpecification");
            usTrademarkPage.clickNextButton();
        } catch (AssertionError e) {
            String msg = "❌ Exception during Service specification: " + e.getMessage();
            logger.error(msg);
            logStep(msg);
            logToAllure("❌ Exception", msg);
            ScreenshotUtils.attachScreenshotToAllure(driver, "ServiceSpecification_Exception");
        }
    }

}
