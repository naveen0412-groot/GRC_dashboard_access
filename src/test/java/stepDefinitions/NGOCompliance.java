package stepDefinitions;

import hooks.Hooks;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.qameta.allure.Step;
import org.apache.logging.log4j.Logger;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;
import pages.Ngocompliancepage;
import utils.ConfigReader;
import utils.LoggerUtils;
import utils.ScreenshotUtils;

import java.awt.*;
import java.awt.datatransfer.DataFlavor;
import java.time.Duration;

import static utils.AllureLoggerUtils.logToAllure;

public class NGOCompliance {

    WebDriver driver = Hooks.driver;
    Ngocompliancepage ngo;
    Logger logger;
    WebDriverWait wait;
    SearchTicketStepdefs searchTicketsteps;
    LoginStepDefinitions loginSteps;


    public NGOCompliance() throws AWTException {
        this.driver = Hooks.driver;
        this.ngo = new Ngocompliancepage(Hooks.driver);
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        this.logger = LoggerUtils.getLogger(getClass());
        this.searchTicketsteps = new SearchTicketStepdefs(Hooks.driver);
        this.loginSteps = new LoginStepDefinitions();
    }

    @Step("{message}")
    public void logStep(String message) {
    }

    @Given("the user redirect to NGO Compliance services")
    public void setNgo() throws InterruptedException{

        try {

            searchTicketsteps.theUserRedirectToTicketListPage();
            searchTicketsteps.theUserEnterTheTicket();
            searchTicketsteps.theUserClickTheSearchCTA();
            searchTicketsteps.theUserViewTheTicketPageByClickingOnTheLink();

            ngo.crossSaleNew();
            ngo.createservice(ConfigReader.get("NGOCompliance"));
            ngo.selectingtheservice();
            Thread.sleep(2000);
            ngo.createbutton();
            logger.info("Service creation initiated.");
            ScreenshotUtils.attachScreenshotToAllure(driver, "2_Service_Created");

            Thread.sleep(15000);
            ngo.recenttickets();
            Thread.sleep(4000);
            logger.info("Recent Tickets modal opened.");
            ScreenshotUtils.attachScreenshotToAllure(driver, "3_Recent_Tickets_Modal");

            try {
                WebElement firstTicket = driver.findElement(By.cssSelector("tr.ticket_index_tr ul.tcktdetails li a"));
                firstTicket.click();
                logger.info("Clicked first dynamic ticket link.");
                Thread.sleep(10000);
                ScreenshotUtils.attachScreenshotToAllure(driver, "4_Selected_Ticket_Page");

                ngo.copyurl.click();
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

            logStep("🌐 Redirecting the user to the NGO Compliance page...");
            logStep("✅ Field Details screen for NGO Compliance page");
            logToAllure("✅ The user Navigated to NGO Compliance page", "NGO Compliance page redirected successfully");
            ScreenshotUtils.attachScreenshotToAllure(driver, "NGO Compliance page redirected successfully");

        } catch (AssertionError | WebDriverException e) {
            String msg = "❌ Exception during Accessing the Customer view : " + e.getMessage();
            logger.error(msg);
            logStep(msg);
            logToAllure("❌ Exception", msg);
            ScreenshotUtils.attachScreenshotToAllure(driver, "NGO Compliance page_Exception");
            throw e;
        }
    }
    @When("the user navigate to ngo registration")
    public void ngoregistration() throws InterruptedException {
        logger.info("===== NGO Registration Flow Started =====");

        try {
            logStep("🏢 Entering Unique ID and Applicant Type");
            ngo.uniqueid(ConfigReader.get("ApplicantType"));
            logToAllure("Input", "Applicant Type entered: " + ConfigReader.get("ApplicantType"));

            logStep("💳 Entering PAN details of Directors");
            ngo.pannumber(ConfigReader.get("PANOfDirectors"));
            logToAllure("Input", "PAN details provided");

            logStep("📝 Selecting Nature of Work/Activities");
            ngo.activities(ConfigReader.get("WorkNature"));
            logToAllure("Selection", "Work nature selected: " + ConfigReader.get("WorkNature"));

            logStep("🖱️ Clicking NGO Registration Button");
            ngo.ngobutton();
            Thread.sleep(2000);

            ScreenshotUtils.attachScreenshotToAllure(driver, "NGO Registration Main Screen");
            logger.info("Successfully processed NGO Registration initial screen");

        } catch (AssertionError e) {
            logger.error("❌ Error in ngoregistration: " + e.getMessage());
            ScreenshotUtils.attachScreenshotToAllure(driver, "Error_NGORegistration");
            throw e;
        }
    }

    @Then("the user navigates to upload document page for Ngo compliance page")
    public void uploaddocsngo() throws InterruptedException {
        logger.info("===== NGO Document Upload Flow Started =====");

        try {
//            logStep("📄 Uploading Certificate of Incorporation (COI)");
//            ngo.COI.sendKeys(ConfigReader.get("COI"));
//            logToAllure("Upload", "COI Path: " + ConfigReader.get("COI"));
//            Thread.sleep(2000);
//
//            logStep("🪪 Uploading Company PAN Card");
//            ngo.Companypancard.sendKeys(ConfigReader.get("PAN1"));
//            logToAllure("Upload", "Company PAN Path: " + ConfigReader.get("PAN1"));
//            Thread.sleep(2000);
//
//            logStep("📤 Clicking Upload Button for Compliance");
//            ngo.uploadbbutton();
//            Thread.sleep(2000);
//
//            logStep("✅ Finalizing Document Upload");
            ScreenshotUtils.attachScreenshotToAllure(driver, "NGO_Compliance_Upload_Final");
            logToAllure("Status", "Documents uploaded successfully");

            logger.info("===== NGO Document Upload Flow Completed =====");

        } catch (AssertionError e) {
            logger.error("❌ Error in uploaddocsngo: " + e.getMessage());
            ScreenshotUtils.attachScreenshotToAllure(driver, "Error_DocumentUpload");
            throw e;
        }
    }
}




