package stepDefinitions;

import hooks.Hooks;
import io.cucumber.java.PendingException;
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
import org.python.modules.thread.thread;
import pages.Firearmpage;
import utils.ConfigReader;
import utils.LoggerUtils;
import utils.ScreenshotUtils;

import java.awt.*;
import java.awt.datatransfer.DataFlavor;
import java.time.Duration;

import static utils.AllureLoggerUtils.logToAllure;

public class Firearmstep {

    WebDriver driver = Hooks.driver;
    Firearmpage firearm;
    Logger logger;
    WebDriverWait wait;
    SearchTicketStepdefs searchTicketsteps;
    LoginStepDefinitions loginSteps;


    public Firearmstep() throws AWTException {
        this.driver = Hooks.driver;
        this.firearm = new Firearmpage(Hooks.driver);
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        this.logger = LoggerUtils.getLogger(getClass());
        this.searchTicketsteps = new SearchTicketStepdefs(Hooks.driver);
        this.loginSteps = new LoginStepDefinitions();
    }

    @Step("{message}")
    public void logStep(String message) {
    }

    @Given("the user redirect to Firearm/Gun license services")
    public void fireandarm() throws InterruptedException {

        try {

            searchTicketsteps.theUserRedirectToTicketListPage();
            searchTicketsteps.theUserEnterTheTicket();
            searchTicketsteps.theUserClickTheSearchCTA();
            searchTicketsteps.theUserViewTheTicketPageByClickingOnTheLink();
            firearm.crossSaleNew();
            firearm.createservice(ConfigReader.get("firearmlicense"));
            firearm.selectingtheservice();
            Thread.sleep(2000);
            firearm.createbutton();
            logger.info("Service creation initiated.");
            ScreenshotUtils.attachScreenshotToAllure(driver, "2_Service_Created");
            Thread.sleep(15000);
            firearm.recenttickets();
            Thread.sleep(4000);
            logger.info("Recent Tickets modal opened.");
            ScreenshotUtils.attachScreenshotToAllure(driver, "3_Recent_Tickets_Modal");

            try {
                WebElement firstTicket = driver.findElement(By.cssSelector("tr.ticket_index_tr ul.tcktdetails li a"));
                firstTicket.click();
                logger.info("Clicked first dynamic ticket link.");
                Thread.sleep(10000);
                ScreenshotUtils.attachScreenshotToAllure(driver, "4_Selected_Ticket_Page");

                firearm.copyurl.click();
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
            logStep("🌐 Redirecting the user to the Firearm/Gun license page...");
            Thread.sleep(2000);
            logStep("✅ Field Details screen for Firearm/Gun license page");
            logToAllure("✅ The user Navigated to Firearm/Gun license page", "Firearm/Gun license page redirected successfully");
            ScreenshotUtils.attachScreenshotToAllure(driver, "Firearm/Gun license page redirected successfully");

        } catch (IllegalArgumentException | WebDriverException | AssertionError e) {
            String msg = "❌ Exception during Accessing the Customer view : " + e.getMessage();
            logger.error(msg);
            logStep(msg);
            logToAllure("❌ Exception", msg);
            ScreenshotUtils.attachScreenshotToAllure(driver, "Firearm/Gun license page_Exception");
            throw e;
        }
    }

    @When("the user enters the authorised person Firearm/Gun license services")
    public void fireandarm_authorised() {
        logger.info("===== Authorised Person Details Section Started =====");

        try {
            logStep("⌨️ Entering personal details for: " + ConfigReader.get("directorName1"));
            firearm.applicantName(ConfigReader.get("directorName1"));
            firearm.fatherName(ConfigReader.get("FatherName1"));
            firearm.emailID1(ConfigReader.get("DirectorEmail1"));
            firearm.mobileNumber(ConfigReader.get("DirectorMobile1"));

            logStep("📅 Selecting Date and Educational Qualification");
            firearm.selectDate();
            firearm.educationalQual(ConfigReader.get("EducationalQualification1"));

            logStep("🏠 Entering Address and Purpose");
            firearm.address(ConfigReader.get("Address1"));
            firearm.purpose(ConfigReader.get("Purposeofreg"));

            ScreenshotUtils.attachScreenshotToAllure(driver, "Authorised_Person_Details_Filled");

            logStep("🖱️ Clicking Authorised Person Button");
            firearm.authButton();

            logger.info("✅ Authorised person details submitted successfully");
        } catch (IllegalArgumentException | AssertionError e) {
            logger.error("❌ Error in Authorised Person Section: " + e.getMessage());
            ScreenshotUtils.attachScreenshotToAllure(driver, "Error_Authorised_Person");
            throw e;
        }
    }

    @When("the user upload documents Firearm/Gun license services")
    public void fireandarm_upload_documents() throws InterruptedException {
        logger.info("===== Document Upload Section Started =====");
        try {
            logStep("📤 Uploading Applicant Aadhar Card: " + ConfigReader.get("AadharUploadPic1"));
            firearm.applicantaadharcard.sendKeys(ConfigReader.get("AadharUploadPic1"));
            Thread.sleep(3000);
           logStep("📤 Uploading Mobile Bill: " + ConfigReader.get("AadharUploadPic1"));
            firearm.mobilebill.sendKeys(ConfigReader.get("AadharUploadPic1"));
            Thread.sleep(3000);
          ScreenshotUtils.attachScreenshotToAllure(driver, "Firearm_Documents_Attached");
            logStep("🔘 Clicking Final Upload Button");
            firearm.uploadbutton();
            logger.info("✅ Firearm license documents uploaded successfully");
        } catch (AssertionError e) {
            logger.error("❌ Failed to upload Firearm documents: " + e.getMessage());
            ScreenshotUtils.attachScreenshotToAllure(driver, "Error_Firearm_Upload");
            throw e;
        }
    }

}




