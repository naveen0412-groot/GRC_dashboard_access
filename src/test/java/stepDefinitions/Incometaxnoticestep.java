package stepDefinitions;

import hooks.Hooks;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import io.qameta.allure.Step;
import org.apache.logging.log4j.Logger;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;
import pages.Incometaxpage;
import utils.ConfigReader;
import utils.LoggerUtils;
import utils.ScreenshotUtils;

import java.awt.*;
import java.awt.datatransfer.DataFlavor;
import java.time.Duration;

import static utils.AllureLoggerUtils.logToAllure;

public class Incometaxnoticestep {

    WebDriver driver = Hooks.driver;
    Incometaxpage income;
    Logger logger;
    WebDriverWait wait;
    SearchTicketStepdefs searchTicketsteps;
    LoginStepDefinitions loginSteps;


    public Incometaxnoticestep()  {
        this.driver = Hooks.driver;
        try {
            this.income = new Incometaxpage(Hooks.driver);
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
    @Given("the user redirect to Income Tax Notice")
    public void incomeTaxNotice() throws InterruptedException {
        try {

            searchTicketsteps.theUserRedirectToTicketListPage();
            searchTicketsteps.theUserEnterTheTicket();
            searchTicketsteps.theUserClickTheSearchCTA();
            searchTicketsteps.theUserViewTheTicketPageByClickingOnTheLink();

            income.crossSaleNew();
            income.createservice(ConfigReader.get("Incometaxnotice"));
            income.selectingtheservice();
            Thread.sleep(2000);
            income.createbutton();
            logger.info("Service creation initiated.");
            ScreenshotUtils.attachScreenshotToAllure(driver, "2_Service_Created");

            Thread.sleep(15000);
            income.recenttickets();
            Thread.sleep(4000);
            logger.info("Recent Tickets modal opened.");
            ScreenshotUtils.attachScreenshotToAllure(driver, "3_Recent_Tickets_Modal");

            try {
                WebElement firstTicket = driver.findElement(By.cssSelector("tr.ticket_index_tr ul.tcktdetails li a"));
                firstTicket.click();
                logger.info("Clicked first dynamic ticket link.");
                Thread.sleep(10000);
                ScreenshotUtils.attachScreenshotToAllure(driver, "4_Selected_Ticket_Page");

                income.copyurl.click();
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
            logStep("🌐 Redirecting the user to Income Tax Notice page...");
            logStep("✅ Field Details screen for Income Tax Notice ");
            logToAllure("✅ The user Navigated to Income Tax Notice page ", "Income Tax Notice Page redirected successfully");
            ScreenshotUtils.attachScreenshotToAllure(driver, "Income Tax Notice");

        } catch (AssertionError e) {
            String msg = "❌ Exception during Accessing the Customer view : " + e.getMessage();
            logger.error(msg);
            logStep(msg);
            logToAllure("❌ Exception", msg);
            ScreenshotUtils.attachScreenshotToAllure(driver, "Income Tax Notice_Exception");
            throw e;
        }
    }

    @When("the user update the Bank Details")
    public void the_user_update_the_bank_Details() throws InterruptedException {
        logger.info("===== Starting Bank Details Update Flow =====");

        try {
            logStep("📂 Beginning bank details update section");
            ScreenshotUtils.attachScreenshotToAllure(driver, "Bank_Details_Section_Initial");

            String cityValue = ConfigReader.get("city");
            logStep("🏙 Entering City/Income Tax Detail: " + cityValue);
            income.incometax(cityValue);

            String complainVal = ConfigReader.get("complainvalue");
            logStep("📊 Entering Bank Statement/Complain Value: " + complainVal);
            income.bankstatement(complainVal);

            String description = ConfigReader.get("descrip");
            logStep("📝 Entering Other Information: " + description);
            income.otherinformation(description);

            logStep("🖱 Clicking on the Submit/Next button");
            income.buttonnt();

            Thread.sleep(2000);
            logToAllure("Success", "Bank details fields populated and button clicked");
            ScreenshotUtils.attachScreenshotToAllure(driver, "Bank_Details_Updated_Successfully");
            logger.info("✅ Bank Details Update Flow completed successfully.");

        } catch (AssertionError e) {
            logger.error("❌ Error occurred while updating Bank Details: " + e.getMessage());
            ScreenshotUtils.attachScreenshotToAllure(driver, "Error_Bank_Details_Update");
            throw e;
        }
    }
}


