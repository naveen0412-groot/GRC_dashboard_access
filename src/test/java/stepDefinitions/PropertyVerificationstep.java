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
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;
import pages.Propertyverifypage;
import utils.ConfigReader;
import utils.LoggerUtils;
import utils.ScreenshotUtils;

import java.awt.*;
import java.awt.datatransfer.DataFlavor;
import java.time.Duration;

import static utils.AllureLoggerUtils.logToAllure;

public class PropertyVerificationstep {

    WebDriver driver = Hooks.driver;
    Propertyverifypage verification;
    Logger logger;
    WebDriverWait wait;
    SearchTicketStepdefs searchTicketsteps;
    LoginStepDefinitions loginSteps;


    public PropertyVerificationstep()  {
        this.driver = Hooks.driver;
        try {
            this.verification = new Propertyverifypage(Hooks.driver);
        } catch (AWTException e) {
            throw new RuntimeException(e);
        }
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        this.logger = LoggerUtils.getLogger(getClass());
        this.searchTicketsteps = new SearchTicketStepdefs(Hooks.driver);
        this.loginSteps = new LoginStepDefinitions();
    }

    @Step("{message}")
    public void logStep(String message) {
    }

    @Given("the user redirect to Property Verification")
    public void redirectionofpropverification() throws InterruptedException {
        try {

            searchTicketsteps.theUserRedirectToTicketListPage();
            searchTicketsteps.theUserEnterTheTicket();
            searchTicketsteps.theUserClickTheSearchCTA();
            searchTicketsteps.theUserViewTheTicketPageByClickingOnTheLink();

            verification.crossSaleNew();
            verification.createservice(ConfigReader.get("Propertyverification"));
            verification.selectingtheservice(ConfigReader.get("Propertyverification"));
            Thread.sleep(2000);
            verification.createbutton();
            logger.info("Service creation initiated.");
            ScreenshotUtils.attachScreenshotToAllure(driver, "2_Service_Created");

            Thread.sleep(15000);
            verification.recenttickets();
            Thread.sleep(4000);
            logger.info("Recent Tickets modal opened.");
            ScreenshotUtils.attachScreenshotToAllure(driver, "3_Recent_Tickets_Modal");

            try {
                WebElement firstTicket = driver.findElement(By.cssSelector("tr.ticket_index_tr ul.tcktdetails li a"));
                firstTicket.click();
                logger.info("Clicked first dynamic ticket link.");
                Thread.sleep(10000);
                ScreenshotUtils.attachScreenshotToAllure(driver, "4_Selected_Ticket_Page");

                verification.copyurl.click();
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
            logStep("🌐 Redirecting the user to Property Verification page...");
            logStep("✅ Field Details screen for Property Verification");
            logToAllure("✅ The user Navigated to Property Verification page", "Property Verification Page redirected successfully");
            ScreenshotUtils.attachScreenshotToAllure(driver, "Property Verification");
        } catch (AssertionError e) {
            String msg = "❌ Exception during Accessing the Customer view : " + e.getMessage();
            logger.error(msg);
            logStep(msg);
            logToAllure("❌ Exception", msg);
            ScreenshotUtils.attachScreenshotToAllure(driver, "Property Verification_Exception");
            throw e;
        }
    }

    @When("the user enters the Basic Details of Property Verification")
    public void basicdetail() throws InterruptedException {
        logger.info("===== Starting Basic Details of Property Verification =====");

        try {
            logStep("🏢 Entering Seller and Buyer Information");
            Thread.sleep(2000);
            verification.Sellerdetails(ConfigReader.get("directorName1"));
            verification.Buyerdetails(ConfigReader.get("Address1"));
            logStep("🔍 Selecting Property Type and Purpose from Dropdowns");
            verification.typeofproperty();
            verification.purposeofregistration();
            logStep("📍 Entering Property Address and Locality");
            verification.propertyaddress(ConfigReader.get("Address1"));
            verification.location(ConfigReader.get("OPCLocality"));
            logToAllure("Navigation", "Basic verification details populated");
            ScreenshotUtils.attachScreenshotToAllure(driver, "Basic Verification Screen");
            verification.basicbutton();
            logStep("✅ Basic verification details submitted successfully");
            Thread.sleep(2000);
        } catch (AssertionError e) {
            logger.error("❌ Error during Property Verification data entry: " + e.getMessage());
            ScreenshotUtils.attachScreenshotToAllure(driver, "Verification_Error_Snapshot");
            throw e;
        }
    }

    }



