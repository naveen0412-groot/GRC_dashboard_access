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
import pages.Ngodisclosepage;
import utils.ConfigReader;
import utils.LoggerUtils;
import utils.ScreenshotUtils;

import java.awt.*;
import java.time.Duration;
import java.awt.datatransfer.DataFlavor;


import static utils.AllureLoggerUtils.logToAllure;

public class Ngodiscloseagreementstep {

    WebDriver driver = Hooks.driver;
    Ngodisclosepage ngo;
    Logger logger;
    WebDriverWait wait;
    SearchTicketStepdefs searchTicketsteps;
    LoginStepDefinitions loginSteps;


    public Ngodiscloseagreementstep() throws AWTException {
        this.driver = Hooks.driver;
        this.ngo = new Ngodisclosepage(Hooks.driver);
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        this.logger = LoggerUtils.getLogger(getClass());
        this.searchTicketsteps = new SearchTicketStepdefs(Hooks.driver);
        this.loginSteps = new LoginStepDefinitions();
    }

    @Step("{message}")
    public void logStep(String message) {
    }

    @Given("the user redirect to NGO Disclose Agreement")
    public void ngodisclosure() throws InterruptedException {

        try {

            searchTicketsteps.theUserRedirectToTicketListPage();
            searchTicketsteps.theUserEnterTheTicket();
            searchTicketsteps.theUserClickTheSearchCTA();
            searchTicketsteps.theUserViewTheTicketPageByClickingOnTheLink();
            ngo.crossSaleNew();
            ngo.createservice(ConfigReader.get("NGOAgreement"));
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
            logStep("🌐 Redirecting the user to the NGO Disclose Agreement page...");
            logStep("✅ Field Details screen for NGO Disclose Agreement page");
            logToAllure("✅ The user Navigated to NGO Disclose Agreement page", "NGO Disclose Agreement page redirected successfully");
            ScreenshotUtils.attachScreenshotToAllure(driver, "NGO Disclose Agreement page redirected successfully");

        } catch (WebDriverException | AssertionError e) {
            String msg = "❌ Exception during Accessing the Customer view : " + e.getMessage();
            logger.error(msg);
            logStep(msg);
            logToAllure("❌ Exception", msg);
            ScreenshotUtils.attachScreenshotToAllure(driver, "NGO Disclose Agreement page_Exception");
            throw e;
        }
    }

    @When("the user entering the Party Details in NGO Disclose Agreement")
    public void partydetails() throws InterruptedException {
        logger.info("===== NGO Disclose Agreement: Party Details Started =====");
        try {
            logStep("🏢 Entering Legal Full Name & Address");
            ngo.legalfullname(ConfigReader.get("CompanyName"));
            ngo.legaladdress(ConfigReader.get("Address1"));

            logStep("👤 Entering Primary Contact Information");
            ngo.Primaryname(ConfigReader.get("directorName1"));
            ngo.Primaryemail(ConfigReader.get("DirectorEmail1"));
            ngo.primarycontact(ConfigReader.get("DirectorMobile1"));

            logToAllure("Input", "Party Details entered for: " + ConfigReader.get("CompanyName"));

            logStep("🖱️ Clicking Party Details Button");
            ngo.partydetailsbutton();
            Thread.sleep(2000);

            ScreenshotUtils.attachScreenshotToAllure(driver, "Party_Details_Submitted");
        } catch (AssertionError e) {
            logger.error("❌ Failed in Party Details: " + e.getMessage());
            ScreenshotUtils.attachScreenshotToAllure(driver, "Error_PartyDetails");
            throw e;
        }
    }

    @When("the user entering the receiver details and NDA in NGO Disclose Agreement")
    public void receiverdetails() throws InterruptedException {
        logger.info("===== NGO Disclose Agreement: Receiver & NDA Details Started =====");
        try {
            logStep("📩 Entering Receiver Party Details");
            ngo.partyname(ConfigReader.get("CompanyName"));
            ngo.partyaddress(ConfigReader.get("Address1"));
            ngo.partyprimaryname(ConfigReader.get("directorName1"));
            ngo.partyemail(ConfigReader.get("DirectorEmail1"));
            ngo.partycontact(ConfigReader.get("DirectorMobile1"));

            ngo.recieverbutton();
            Thread.sleep(2000);

            logStep("📜 Selecting NDA Type using Keyboard Actions");
            ngo.nda(ConfigReader.get("TypeofNDA"));

            logStep("🖱️ Clicking NDA Submit Button");
            ngo.ndabutton();
            Thread.sleep(2000);

            ScreenshotUtils.attachScreenshotToAllure(driver, "Receiver_NDA_Completed");
        } catch (AssertionError e) {
            logger.error("❌ Failed in Receiver/NDA Details: " + e.getMessage());
            ScreenshotUtils.attachScreenshotToAllure(driver, "Error_ReceiverDetails");
            throw e;
        }
    }

    @When("the user enters the confidential destruction")
    public void confdestruction() throws InterruptedException {
        logger.info("===== NGO Disclose Agreement: Confidentiality Details Started =====");
        try {
            logStep("🔒 Entering Confidential Info & Methods of Disclosure");
            ngo.confidentialinformation(ConfigReader.get("state"));
            ngo.typesofincluded(ConfigReader.get("naturebusiness"));
            ngo.methodsofdisclosure(ConfigReader.get("SubDivision"));

            logStep("🖱️ Clicking Confidentiality Submit Button");
            ngo.confidentialbutton();
            Thread.sleep(2000);

            ScreenshotUtils.attachScreenshotToAllure(driver, "Confidential_Destruction_Submitted");
        } catch (AssertionError e) {
            logger.error("❌ Failed in Confidential Destruction: " + e.getMessage());
            ScreenshotUtils.attachScreenshotToAllure(driver, "Error_ConfDestruction");
            throw e;
        }
    }

    @Then("the user enters the Term Termination")
    public void termtermination() throws InterruptedException {
        logger.info("===== NGO Disclose Agreement: Term Termination Started =====");
        try {
            logStep("⏳ Handling Term of Agreement & Certification");
            Thread.sleep(2000);

            logStep("⌨️ Typing Term of Agreement: " + ConfigReader.get("State"));
            ngo.termofagreement(ConfigReader.get("State"));

            logStep("📋 Selecting Certification of Destruction");
            ngo.CertificationDestruction(ConfigReader.get("CertificationDestruct"));

            logStep("🖱️ Clicking Final Term Button");
            ngo.termbutton();
            Thread.sleep(2000);

            logToAllure("Success", "Full NGO Disclosure Agreement flow completed");
            ScreenshotUtils.attachScreenshotToAllure(driver, "Term_Termination_Final");
            logger.info("===== NGO Disclose Agreement Flow Completed Successfully =====");
        } catch (AssertionError e) {
            logger.error("❌ Failed in Term Termination: " + e.getMessage());
            ScreenshotUtils.attachScreenshotToAllure(driver, "Error_TermTermination");
            throw e;
        }
    }
}




