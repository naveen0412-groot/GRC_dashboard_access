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
import pages.Ipinfringepage;
import utils.ConfigReader;
import utils.LoggerUtils;
import utils.ScreenshotUtils;

import java.awt.*;
import java.awt.datatransfer.DataFlavor;
import java.time.Duration;

import static utils.AllureLoggerUtils.logToAllure;

public class Ipinfringement {

    WebDriver driver = Hooks.driver;
    Ipinfringepage ip;
    Logger logger;
    WebDriverWait wait;
    SearchTicketStepdefs searchTicketsteps;
    LoginStepDefinitions loginSteps;


    public Ipinfringement() throws AWTException {
        this.driver = Hooks.driver;
        this.ip = new Ipinfringepage(Hooks.driver);
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        this.logger = LoggerUtils.getLogger(getClass());
        this.searchTicketsteps = new SearchTicketStepdefs(Hooks.driver);
        this.loginSteps = new LoginStepDefinitions();
    }

    @Step("{message}")
    public void logStep(String message) {
    }

    @Given("The user navigating to the Ip Infringement page")
    public void ipinfringe() throws InterruptedException {
        try {

            searchTicketsteps.theUserRedirectToTicketListPage();
            searchTicketsteps.theUserEnterTheTicket();
            searchTicketsteps.theUserClickTheSearchCTA();
            searchTicketsteps.theUserViewTheTicketPageByClickingOnTheLink();

            ip.crossSaleNew();
            ip.createservice(ConfigReader.get("Ipinfringment"));
            ip.selectingtheservice();
            Thread.sleep(2000);
            ip.createbutton();
            logger.info("Service creation initiated.");
            ScreenshotUtils.attachScreenshotToAllure(driver, "2_Service_Created");
            Thread.sleep(15000);
            ip.recenttickets();
            Thread.sleep(4000);
            logger.info("Recent Tickets modal opened.");
            ScreenshotUtils.attachScreenshotToAllure(driver, "3_Recent_Tickets_Modal");

            try {
                WebElement firstTicket = driver.findElement(By.cssSelector("tr.ticket_index_tr ul.tcktdetails li a"));
                firstTicket.click();
                logger.info("Clicked first dynamic ticket link.");
                Thread.sleep(10000);
                ScreenshotUtils.attachScreenshotToAllure(driver, "4_Selected_Ticket_Page");
                ip.copyurl.click();
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

            logStep("🌐 Redirecting the user to the IP Infringement page...");
            logStep("✅ Field Details screen for IP Infringement page");
            logToAllure("✅ The user Navigated to IP Infringement page", "IP Infringement page redirected successfully");
            ScreenshotUtils.attachScreenshotToAllure(driver, "IP Infringement page redirected successfully");

        } catch (AssertionError e) {
            String msg = "❌ Exception during Accessing the Customer view : " + e.getMessage();
            logger.error(msg);
            logStep(msg);
            logToAllure("❌ Exception", msg);
            ScreenshotUtils.attachScreenshotToAllure(driver, "Legal Metrology page_Exception");
            throw e;
        }
    }

    @When("the user entering the Infringer Details")
    public void InfringerDetails() throws InterruptedException {

        logStep("🔍 Starting Infringer and Applicant details entry");
        logger.info("===== Infringer Details Flow Started =====");

        try {
            logStep("📝 Entering Infringer Information");
            String infName = ConfigReader.get("ApplicantName");
            ip.infringerName(infName);
            ip.applicantNumber(ConfigReader.get("DirectorMobile2"));
            ip.infringerAddress(ConfigReader.get("ApplicantAddress"));

            logger.info("Infringer Name entered → {}", infName);
            Thread.sleep(2000);
            ip.infringebutton();

            logStep("📝 Entering Applicant Information");
            String appName = ConfigReader.get("InventorName");
            ip.applicationNumber(ConfigReader.get("TMApplicationNumber"));
            ip.applicantName(appName);
            ip.applicantAddress(ConfigReader.get("AddressLine1"));

            logger.info("Applicant Name entered → {}", appName);
            Thread.sleep(2000);
            ip.applicantbutton();

            logStep("✅ Infringer and Applicant details submitted");
            ScreenshotUtils.attachScreenshotToAllure(driver, "Infringer_Details_Success");
            Thread.sleep(2000);

        } catch (AssertionError e) {
            logStep("❌ Error entering Infringer Details");
            logger.error("Infringer Flow failed. Check Config for ApplicantName and InventorName");
            ScreenshotUtils.attachScreenshotToAllure(driver, "Infringer_Details_Failure");
            throw e;
        }
    }

    @When("the user entering the client and upload")
    public void InfringerClientAndUpload() throws InterruptedException {

        logStep("👤 Starting Client details and final upload");
        logger.info("===== Client Upload Flow Started =====");

        try {
            logStep("📝 Entering Client Information");
            String clientName = ConfigReader.get("CompanyName1");
            if (clientName == null || clientName.isEmpty()) {
                throw new RuntimeException("❌ Client Name (CompanyName1) is missing in config");
            }

            ip.clientName(clientName);
            ip.clientAddress(ConfigReader.get("Jurisdiction"));
            logger.info("Client Name entered → {}", clientName);

            ip.clientbutton();
            Thread.sleep(2000);

            logStep("🖱 Clicking final Upload button");
            ip.uploadbutton();

            Thread.sleep(2000);
            logStep("✅ Client details and upload completed");
            ScreenshotUtils.attachScreenshotToAllure(driver, "Client_Upload_Success");

        } catch (AssertionError e) {
            logStep("❌ Error during Client details or Upload");
            logger.error("Client flow failed for Company: {}", ConfigReader.get("CompanyName1"));
            ScreenshotUtils.attachScreenshotToAllure(driver, "Client_Upload_Failure");
            throw e;
        }
    }
}




