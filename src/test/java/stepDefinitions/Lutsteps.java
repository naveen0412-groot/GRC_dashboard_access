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
import pages.Lutpage;
import utils.ConfigReader;
import utils.LoggerUtils;
import utils.ScreenshotUtils;

import java.awt.*;
import java.awt.datatransfer.DataFlavor;
import java.time.Duration;

import static utils.AllureLoggerUtils.logToAllure;

public class Lutsteps {

    WebDriver driver = Hooks.driver;
    Lutpage Lut;
    Logger logger;
    WebDriverWait wait;
    SearchTicketStepdefs searchTicketsteps;
    LoginStepDefinitions loginSteps;


    public Lutsteps() throws AWTException {
        this.driver = Hooks.driver;
        this.Lut = new Lutpage(Hooks.driver);
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        this.logger = LoggerUtils.getLogger(getClass());
        this.searchTicketsteps = new SearchTicketStepdefs(Hooks.driver);
        this.loginSteps = new LoginStepDefinitions();
    }

    @Step("{message}")
    public void logStep(String message) {
    }

    @Given("the user redirect to LUT application service")
    public void Lutapp() throws InterruptedException {
        try {

            searchTicketsteps.theUserRedirectToTicketListPage();
            searchTicketsteps.theUserEnterTheTicket();
            searchTicketsteps.theUserClickTheSearchCTA();
            searchTicketsteps.theUserViewTheTicketPageByClickingOnTheLink();

            Lut.crossSaleNew();
            Lut.createservice(ConfigReader.get("Lutservice"));
            Lut.selectingtheservice();
            Thread.sleep(2000);
            Lut.createbutton();
            logger.info("Service creation initiated.");
            ScreenshotUtils.attachScreenshotToAllure(driver, "2_Service_Created");

            Thread.sleep(15000);
            Lut.recenttickets();
            Thread.sleep(4000);
            logger.info("Recent Tickets modal opened.");
            ScreenshotUtils.attachScreenshotToAllure(driver, "3_Recent_Tickets_Modal");

            try {
                WebElement firstTicket = driver.findElement(By.cssSelector("tr.ticket_index_tr ul.tcktdetails li a"));
                firstTicket.click();
                logger.info("Clicked first dynamic ticket link.");
                Thread.sleep(10000);
                ScreenshotUtils.attachScreenshotToAllure(driver, "4_Selected_Ticket_Page");

                Lut.copyurl.click();
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
            logStep("🌐 Redirecting the user to the LUT application page...");
            ScreenshotUtils.attachScreenshotToAllure(driver, "LUT application page redirected successfully");

        } catch (AssertionError e) {
            String msg = "❌ Exception during Accessing the Customer view : " + e.getMessage();
            logger.error(msg);
            logStep(msg);
            logToAllure("❌ Exception", msg);
            ScreenshotUtils.attachScreenshotToAllure(driver, "LUT application page_Exception");
            throw e;
        }
    }

    @When("the user enters the entity details in LUT application")
    public void entitylut() throws InterruptedException {
        logStep("🏢 Starting entity details entry for LUT");
        logger.info("===== LUT Entity Details Flow Started =====");

        try {
            Thread.sleep(2000);
            String entityType = ConfigReader.get("Lutentity");

            logger.info("Selecting Entity Type: [{}]", entityType);
            Assert.assertNotNull(entityType, "Lutentity value is missing in Config!");

            Lut.entitytype(entityType);
            Thread.sleep(2000);

            Lut.entitynext();
            logger.info("Clicked Entity Next button");

            ScreenshotUtils.attachScreenshotToAllure(driver, "LUT_Entity_Details_Entered");
        } catch (AssertionError e) {
            logger.error("Error in entitylut: " + e.getMessage());
            throw e;
        }
    }

    @When("the user enters the gst login credential")
    public void gstlogin() throws InterruptedException {
        logStep("🔐 Entering GST Credentials");
        logger.info("===== GST Login Flow Started =====");

        try {
            Thread.sleep(2000);
            String user = ConfigReader.get("FatherName1");
            String pass = ConfigReader.get("lutpassword");

            logger.info("Entering credentials for user: [{}]", user);
            Lut.username(user);
            Lut.password(pass);

            Lut.gstloginbutton();
            logger.info("Clicked GST Login button");
            Thread.sleep(2000);

            ScreenshotUtils.attachScreenshotToAllure(driver, "GST_Login_Attempted");
        } catch (AssertionError e) {
            logger.error("Error in gstlogin: " + e.getMessage());
            throw e;
        }
    }

    @Then("the user adding the witness details in Lut")
    public void witnessdetails() throws InterruptedException {
        logStep("👥 Adding Witness Details");
        logger.info("===== LUT Witness Details Flow Started =====");

        try {
            String witnessName = ConfigReader.get("directorName2");
            logger.info("Adding Witness: [{}], Occupation: [{}]", witnessName, ConfigReader.get("AreaOfOccupation"));
            Thread.sleep(2000);
            Lut.nameofwitness(witnessName);
            Lut.occupation(ConfigReader.get("AreaOfOccupation"));
            Lut.address(ConfigReader.get("Address2"));
            Lut.pincode(ConfigReader.get("FoodAddressPinCode"));

            Lut.witnessbutton();
            Thread.sleep(2000);
            logger.info("Witness details submitted successfully");

            ScreenshotUtils.attachScreenshotToAllure(driver, "LUT_Witness_Details_Added");
        } catch (AssertionError e) {
            logger.error("Error in witnessdetails: " + e.getMessage());
            throw e;
        }
    }
}




