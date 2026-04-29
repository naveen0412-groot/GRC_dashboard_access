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
import pages.IncreaseAuthCap;
import utils.ConfigReader;
import utils.LoggerUtils;
import utils.ScreenshotUtils;

import java.awt.*;
import java.awt.datatransfer.DataFlavor;
import java.time.Duration;

import static utils.AllureLoggerUtils.logToAllure;

public class IncreaseinAuthorizedCapital {

    WebDriver driver = Hooks.driver;
    IncreaseAuthCap authCap;
    Logger logger;
    WebDriverWait wait;
    SearchTicketStepdefs searchTicketsteps;
    LoginStepDefinitions loginSteps;


    public IncreaseinAuthorizedCapital() {
        this.driver = Hooks.driver;
        try {
            this.authCap = new IncreaseAuthCap(Hooks.driver);
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

    @Given("the user redirect to Increase in Auth captial services")

    public void increasecaptial() throws InterruptedException {
        try {

            searchTicketsteps.theUserRedirectToTicketListPage();
            searchTicketsteps.theUserEnterTheTicket();
            searchTicketsteps.theUserClickTheSearchCTA();
            searchTicketsteps.theUserViewTheTicketPageByClickingOnTheLink();
            authCap.crossSaleNew();
            authCap.createservice(ConfigReader.get("Increaseincap"));
            authCap.selectingtheservice();
            Thread.sleep(2000);
            authCap.createbutton();
            logger.info("Service creation initiated.");
            ScreenshotUtils.attachScreenshotToAllure(driver, "2_Service_Created");
            Thread.sleep(15000);
            authCap.recenttickets();
            Thread.sleep(4000);
            logger.info("Recent Tickets modal opened.");
            ScreenshotUtils.attachScreenshotToAllure(driver, "3_Recent_Tickets_Modal");
            try {
                WebElement firstTicket = driver.findElement(By.cssSelector("tr.ticket_index_tr ul.tcktdetails li a"));
                firstTicket.click();
                logger.info("Clicked first dynamic ticket link.");
                Thread.sleep(10000);
                ScreenshotUtils.attachScreenshotToAllure(driver, "4_Selected_Ticket_Page");

                authCap.copyurl.click();
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
            logStep("🌐 Redirecting the user to Increase in Auth captial page...");
            logStep("✅ Field Details screen for Increase in Auth captial page");
            logToAllure("✅ The user Navigated to Increase in Auth captial page", "Increase in Auth captial page redirected successfully");
            ScreenshotUtils.attachScreenshotToAllure(driver, "Increase in Auth captial page redirected successfully");

        } catch (AssertionError e) {
            String msg = "❌ Exception during Accessing the Customer view : " + e.getMessage();
            logger.error(msg);
            logStep(msg);
            logToAllure("❌ Exception", msg);
            ScreenshotUtils.attachScreenshotToAllure(driver, "Increase in Auth captial page_Exception");
            throw e;
        }
    }
    @When("the user enters the Company Information")
    public void theCompanyInformation() throws InterruptedException {
        try {
            logStep("🏢 Entering Company Name: " + ConfigReader.get("CompanyName"));
            authCap.companyName(ConfigReader.get("CompanyName"));

            logStep("🔢 Entering Company CIN: " + ConfigReader.get("CompanyCIN"));
            authCap.companyCIN(ConfigReader.get("CompanyCIN"));

            logStep("🖱️ Clicking Company Info button");
            authCap.companyinfobutton();
            Thread.sleep(2000);
            captureScreenshot("Company_Basic_Details");

            logStep("💰 Filling Authorised Capital details");
            authCap.existingAuthorisedCapital(ConfigReader.get("CompanyName"));
            authCap.revisedAuthorisedCapital(ConfigReader.get("CompanyName"));
            authCap.increasedAuthorisedCapital(ConfigReader.get("CompanyName"));

            logStep("🖱️ Clicking Company Info button for next section");
            authCap.companyinfobutton();
            Thread.sleep(2000);
            captureScreenshot("Auth_Capital_Section_Complete");
        } catch (AssertionError e) {
            logger.error("❌ Error in entering Company Information: " + e.getMessage());
            captureScreenshot("Error_CompanyInformation");
            throw e;
        }
    }

    @Then("the user enters the auth captial information")
    public void setAuthCap() throws InterruptedException {
        try {
            logStep("✅ Selecting Auth Capital option: " + ConfigReader.get("CompanyName"));
            authCap.SelectOption(ConfigReader.get("CompanyName"));

            captureScreenshot("Auth_Capital_Option_Selected");

            logStep("❌ Clicking Close button");
            authCap.closebutton();
            Thread.sleep(2000);

            logger.info("🎯 Auth Capital information flow completed successfully.");
        } catch (AssertionError e) {
            logger.error("❌ Error in setAuthCap: " + e.getMessage());
            captureScreenshot("Error_SetAuthCap");
            throw e;
        }
    }

    private void captureScreenshot(String name) {

    }

    }




