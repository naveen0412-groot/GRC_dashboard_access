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
import pages.Giftdeedpage;
import utils.ConfigReader;
import utils.LoggerUtils;
import utils.ScreenshotUtils;
import java.awt.*;
import java.awt.datatransfer.DataFlavor;
import java.time.Duration;

import static utils.AllureLoggerUtils.logToAllure;

public class GiftDeedstep {

    WebDriver driver = Hooks.driver;
    Giftdeedpage gift;
    Logger logger;
    WebDriverWait wait;
    SearchTicketStepdefs searchTicketsteps;
    LoginStepDefinitions loginSteps;


    public GiftDeedstep()  {
        this.driver = Hooks.driver;
        try {
            this.gift = new Giftdeedpage(Hooks.driver);
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

    @Given("the user redirect to Gift Deed services")
    public void redirectionofgift() throws InterruptedException {
        try {

            searchTicketsteps.theUserRedirectToTicketListPage();
            searchTicketsteps.theUserEnterTheTicket();
            searchTicketsteps.theUserClickTheSearchCTA();
            searchTicketsteps.theUserViewTheTicketPageByClickingOnTheLink();

            gift.crossSaleNew();
            gift.createservice(ConfigReader.get("Giftdeed"));
            gift.selectingtheservice();
            Thread.sleep(2000);
            gift.createbutton();
            logger.info("Service creation initiated.");
            ScreenshotUtils.attachScreenshotToAllure(driver, "2_Service_Created");

            Thread.sleep(15000);
            gift.recenttickets();
            Thread.sleep(4000);
            logger.info("Recent Tickets modal opened.");
            ScreenshotUtils.attachScreenshotToAllure(driver, "3_Recent_Tickets_Modal");

            try {
                WebElement firstTicket = driver.findElement(By.cssSelector("tr.ticket_index_tr ul.tcktdetails li a"));
                firstTicket.click();
                logger.info("Clicked first dynamic ticket link.");
                Thread.sleep(10000);
                ScreenshotUtils.attachScreenshotToAllure(driver, "4_Selected_Ticket_Page");

                gift.copyurl.click();
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




            logStep("🌐 Redirecting the user to Gift Deed services page...");
            logger.info("Navigated to URL: {}", ConfigReader.get("giftdeed"));
            logStep("✅ Field Details screen for Gift Deed services");
            logToAllure("✅ The user Navigated to Gift Deed services page", "Gift Deed services Page redirected successfully");
            ScreenshotUtils.attachScreenshotToAllure(driver, "Gift Deed services");
        } catch (AssertionError e) {
            String msg = "❌ Exception during Accessing the Customer view : " + e.getMessage();
            logger.error(msg);
            logStep(msg);
            logToAllure("❌ Exception", msg);
            ScreenshotUtils.attachScreenshotToAllure(driver, "Gift Deed services_Exception");
            throw e;
        }
    }
    @When("the user enters the Basic Details of Gift Deed")
    public void basicdetailsofgift() throws InterruptedException {
        logger.info("===== Starting Basic Details of Gift Deed Entry =====");

        try {
            Thread.sleep(2000);
            ScreenshotUtils.attachScreenshotToAllure(driver, "Gift_Deed_Basic_Details_Initial_View");

            String seller = ConfigReader.get("directorName1");
            logStep("📝 Entering Seller Details: " + seller);
            gift.Sellerdetails(seller);

            String buyerAddr = ConfigReader.get("Address1");
            logStep("🏠 Entering Buyer Address: " + buyerAddr);
            gift.Buyerdetails(buyerAddr);

            String propType = ConfigReader.get("Typeofgift");
            logStep("🏗 Selecting Type of Property: " + propType);
            gift.typeofproperty(propType);

            String purpose = ConfigReader.get("Purposeofreg");
            logStep("🎯 Selecting Purpose: " + purpose);
            gift.purpose(purpose);

            String location = ConfigReader.get("typeofproperty");
            logStep("📍 Entering Property Location: " + location);
            gift.locationofproperty(location);

            String plot = ConfigReader.get("Address1");
            logStep("🔢 Entering Plot No: " + plot);
            gift.plotno(plot);

            logStep("🖱 Clicking Property Button");
            gift.propertybutton();

            Thread.sleep(2000);
            logToAllure("Success", "Basic details filled and form submitted");
            ScreenshotUtils.attachScreenshotToAllure(driver, "Gift_Deed_Details_Submitted");
            logger.info("✅ Basic Details of Gift Deed completed successfully.");

        } catch (AssertionError e) {
            logger.error("❌ Error in basicdetailsofgift: " + e.getMessage());
            ScreenshotUtils.attachScreenshotToAllure(driver, "Error_Basic_Details_Entry");
            throw e; // Rethrow to fail the Cucumber test
        }
    }

}



