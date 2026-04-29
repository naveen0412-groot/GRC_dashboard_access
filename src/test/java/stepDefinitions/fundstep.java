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
import pages.Fundtermdraftpage;
import utils.ConfigReader;
import utils.LoggerUtils;
import utils.ScreenshotUtils;

import java.awt.*;
import java.awt.datatransfer.DataFlavor;
import java.time.Duration;

import static utils.AllureLoggerUtils.logToAllure;

public class fundstep {

    WebDriver driver = Hooks.driver;
    Fundtermdraftpage fund;
    Logger logger;
    WebDriverWait wait;
    SearchTicketStepdefs searchTicketsteps;
    LoginStepDefinitions loginSteps;


    public fundstep()  {
        this.driver = Hooks.driver;
        try {
            this.fund = new Fundtermdraftpage(Hooks.driver);
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
    @Given("the user redirect to Fundraise-Term Sheet drafting")
    public void fundraiseterm() throws InterruptedException {
        try {

            searchTicketsteps.theUserRedirectToTicketListPage();
            searchTicketsteps.theUserEnterTheTicket();
            searchTicketsteps.theUserClickTheSearchCTA();
            searchTicketsteps.theUserViewTheTicketPageByClickingOnTheLink();

            fund.crossSaleNew();
            fund.createservice(ConfigReader.get("Funddraft"));
            fund.selectingtheservice();
            Thread.sleep(2000);
            fund.createbutton();
            logger.info("Service creation initiated.");
            ScreenshotUtils.attachScreenshotToAllure(driver, "2_Service_Created");

            Thread.sleep(15000);
            fund.recenttickets();
            Thread.sleep(4000);
            logger.info("Recent Tickets modal opened.");
            ScreenshotUtils.attachScreenshotToAllure(driver, "3_Recent_Tickets_Modal");

            try {
                WebElement firstTicket = driver.findElement(By.cssSelector("tr.ticket_index_tr ul.tcktdetails li a"));
                firstTicket.click();
                logger.info("Clicked first dynamic ticket link.");
                Thread.sleep(10000);
                ScreenshotUtils.attachScreenshotToAllure(driver, "4_Selected_Ticket_Page");

                fund.copyurl.click();
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
            logStep("🌐 Redirecting the user to Fundraise-Term Sheet drafting page...");

            logStep("✅ Field Details screen for Fundraise-Term Sheet drafting ");
            logToAllure("✅ The user Navigated to Fundraise-Term Sheet drafting page ", "Fundraise-Term Sheet drafting redirected successfully");
            ScreenshotUtils.attachScreenshotToAllure(driver, "Fundraise-Term Sheet drafting");

        } catch (AssertionError e) {
            String msg = "❌ Exception during Accessing the Customer view : " + e.getMessage();
            logger.error(msg);
            logStep(msg);
            logToAllure("❌ Exception", msg);
            ScreenshotUtils.attachScreenshotToAllure(driver, "Fundraise-Term Sheet drafting_Exception");
            throw e;
        }
    }

    @When("the user update Investement Information the Fundraise-Term Sheet drafting")
    public void investmentinformation() throws InterruptedException {
        logStep("💰 Starting Investment Information update (Fundraise-Term Sheet)");
        logger.info("===== Investment Information Update Flow Started =====");

        try {
            logStep("📝 Entering Investment Details for: " + ConfigReader.get("directorName2"));
            fund.investorName(ConfigReader.get("directorName2"));
            fund.investorAge(ConfigReader.get("complainvalue"));
            fund.addressofinvestor(ConfigReader.get("DirectorEmail2"));
            fund.mailingaddress(ConfigReader.get("DirectorEmail1"));
            fund.scopeandambit(ConfigReader.get("descrip"));
            fund.investmentamount(ConfigReader.get("DebtAmount"));
            fund.periodofinvestment(ConfigReader.get("OPCCompanyName1"));
            fund.termofinvestment(ConfigReader.get("descrip"));
            fund.specificinvestor(ConfigReader.get("descrip"));

            logToAllure("Data Entry", "All Investment Information fields populated");
            ScreenshotUtils.attachScreenshotToAllure(driver, "Investment_Information_Entered");

            logStep("🖱️ Clicking Investor button to save details");
            fund.investorbutton();
            Thread.sleep(2000);

            logStep("✅ Validating Investment Information submission");
            logger.info("===== Investment Information Update Flow Completed Successfully =====");

        } catch (AssertionError e) {
            logger.error("❌ Error in Investment Information flow: " + e.getMessage());
            ScreenshotUtils.attachScreenshotToAllure(driver, "Investment_Info_Error");
            throw e;
        }
    }

    @When("the user update Share details the Fundraise-Term Sheet drafting")
    public void sharedetails() throws InterruptedException {
        logStep("📊 Starting Share Details update (Fundraise-Term Sheet)");
        logger.info("===== Share Details Update Flow Started =====");

        try {
            Thread.sleep(2000);
            logStep("📝 Entering Share Details and Vesting Schedule");
            fund.numberandnatureofshares(ConfigReader.get("directorName2"));
            fund.visitingscheduleapplicable(ConfigReader.get("complainvalue"));
            fund.rightsofthepromoters(ConfigReader.get("descrip"));
            fund.agreements(ConfigReader.get("DebtAmount"));
            fund.Contingent(ConfigReader.get("OPCCompanyName1"));

            logToAllure("Data Entry", "All Share Details fields populated");
            ScreenshotUtils.attachScreenshotToAllure(driver, "Share_Details_Entered");

            logStep("🖱️ Clicking Share button to save details");
            fund.sharebutton();
            Thread.sleep(2000);

            logStep("✅ Validating Share Details submission");
            logger.info("===== Share Details Update Flow Completed Successfully =====");

        } catch (AssertionError e) {
            logger.error("❌ Error in Share Details flow: " + e.getMessage());
            ScreenshotUtils.attachScreenshotToAllure(driver, "Share_Details_Error");
            throw e;
        }
    }
}


