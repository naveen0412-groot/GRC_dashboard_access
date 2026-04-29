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
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import pages.Propertyregpage;
import utils.ConfigReader;
import utils.LoggerUtils;
import utils.ScreenshotUtils;

import java.awt.*;
import java.awt.datatransfer.DataFlavor;
import java.time.Duration;

import static utils.AllureLoggerUtils.logToAllure;

public class Propertyregstep {

    WebDriver driver = Hooks.driver;
    Propertyregpage property;
    Logger logger;
    WebDriverWait wait;
    SearchTicketStepdefs searchTicketsteps;
    LoginStepDefinitions loginSteps;


    public Propertyregstep()  {
        this.driver = Hooks.driver;
        try {
            this.property = new Propertyregpage(Hooks.driver);
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

    @Given("the user redirect to Property Registration")
    public void redirectionofproperty() throws InterruptedException {
        try {

            searchTicketsteps.theUserRedirectToTicketListPage();
            searchTicketsteps.theUserEnterTheTicket();
            searchTicketsteps.theUserClickTheSearchCTA();
            searchTicketsteps.theUserViewTheTicketPageByClickingOnTheLink();

            property.crossSaleNew();
            property.createservice(ConfigReader.get("PropertyReg"));
            property.selectingtheservice(ConfigReader.get("PropertyReg"));
            Thread.sleep(2000);
            property.createbutton();
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
            logger.info("Service creation initiated.");
            ScreenshotUtils.attachScreenshotToAllure(driver, "2_Service_Created");

            Thread.sleep(20000);
            property.recenttickets();
            ScreenshotUtils.attachScreenshotToAllure(driver, "3_Recent_Tickets_Modal");

            try {
                WebElement firstTicket = wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("tr.ticket_index_tr ul.tcktdetails li a")));
                firstTicket.click();
//                WebElement firstTicket = driver.findElement(By.cssSelector("tr.ticket_index_tr ul.tcktdetails li a"));
                firstTicket.click();
                logger.info("Clicked first dynamic ticket link.");
                Thread.sleep(10000);
                ScreenshotUtils.attachScreenshotToAllure(driver, "4_Selected_Ticket_Page");

                property.copyurl.click();
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
            logStep("🌐 Redirecting the user to Property Registration page...");
            logStep("✅ Field Details screen for Property Registration");
            logToAllure("✅ The user Navigated to Property Registration page", "Property Registration Page redirected successfully");
            ScreenshotUtils.attachScreenshotToAllure(driver, "Property Registration");
        } catch (AssertionError e) {
            String msg = "❌ Exception during Accessing the Customer view : " + e.getMessage();
            logger.error(msg);
            logStep(msg);
            logToAllure("❌ Exception", msg);
            ScreenshotUtils.attachScreenshotToAllure(driver, "Property Registration_Exception");
            throw e;
        }
    }

    @When("the user enters the Basic Details of Property Registration")
    public void detailsofprincipal() throws InterruptedException {
        logger.info("===== Starting Basic Details of Property Registration =====");
        try {
            logStep("🏢 Entering Seller and Buyer Information");
            Thread.sleep(2000);
            property.Sellerdetails(ConfigReader.get("directorName1"));
            property.Buyerdetails(ConfigReader.get("Address1"));
            logStep("🔍 Handling Dropdowns: Property Type & Purpose");
            property.typeofproperty(ConfigReader.get("Typeofprop"));
            property.purpose(ConfigReader.get("Purposeofreg"));
            logToAllure("Validation", "Basic property details entered successfully");
            ScreenshotUtils.attachScreenshotToAllure(driver, "Basic_Property_Details_Entered");
            property.basicbutton();
            logStep("✅ Basic details submitted");
            Thread.sleep(2000);
        } catch (AssertionError e) {
            logger.error("❌ Error in Basic Details section: " + e.getMessage());
            ScreenshotUtils.attachScreenshotToAllure(driver, "Error_Basic_Details");
            throw e;
        }
    }

    @Then("the user enters the Property Address of Property Registration")
    public void propertyaddress() throws InterruptedException {
        logger.info("===== Starting Property Address Section =====");
        try {
            logStep("📍 Entering Property Location and Address");
            Thread.sleep(2000);
            property.locationofproperty(ConfigReader.get("typeofproperty"));
            property.plotno(ConfigReader.get("Address1"));
            property.landmark(ConfigReader.get("Typeofprop"));
            property.pincode(ConfigReader.get("Pincode"));
            logStep("🏘️ Selecting Taluk and Village");
            Thread.sleep(2000);
            property.taluk(ConfigReader.get("SubDivision"));
            property.Villages(ConfigReader.get("Village"));
            logToAllure("Navigation", "Property address details populated");
            ScreenshotUtils.attachScreenshotToAllure(driver, "Property_Address_Entered");
            property.propertybutton();
            logStep("✅ Property address submitted");
            Thread.sleep(2000);
        } catch (AssertionError e) {
            logger.error("❌ Error in Property Address section: " + e.getMessage());
            ScreenshotUtils.attachScreenshotToAllure(driver, "Error_Property_Address");
            throw e;
        }
    }
    }



