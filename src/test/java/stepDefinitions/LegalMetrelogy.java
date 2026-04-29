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
import pages.Legalmeterpage;
import utils.ConfigReader;
import utils.LoggerUtils;
import utils.ScreenshotUtils;

import java.awt.*;
import java.awt.datatransfer.DataFlavor;
import java.time.Duration;

import static utils.AllureLoggerUtils.logToAllure;

public class LegalMetrelogy {

    WebDriver driver = Hooks.driver;
    Legalmeterpage legal;
    Logger logger;
    WebDriverWait wait;
    SearchTicketStepdefs searchTicketsteps;
    LoginStepDefinitions loginSteps;


    public LegalMetrelogy() throws AWTException {
        this.driver = Hooks.driver;
        this.legal = new Legalmeterpage(Hooks.driver);
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        this.logger = LoggerUtils.getLogger(getClass());
        this.searchTicketsteps = new SearchTicketStepdefs(Hooks.driver);
        this.loginSteps = new LoginStepDefinitions();
    }

    @Step("{message}")
    public void logStep(String message) {
    }

    @Given("The user navigating to the Legal Metrology page")
    public void legalmetrelogy() throws InterruptedException {
        try {

            searchTicketsteps.theUserRedirectToTicketListPage();
            searchTicketsteps.theUserEnterTheTicket();
            searchTicketsteps.theUserClickTheSearchCTA();
            searchTicketsteps.theUserViewTheTicketPageByClickingOnTheLink();

            legal.crossSaleNew();
            legal.createservice(ConfigReader.get("Legalmetrology"));
            legal.selectingtheservice();
            Thread.sleep(2000);
            legal.createbutton();
            logger.info("Service creation initiated.");
            ScreenshotUtils.attachScreenshotToAllure(driver, "2_Service_Created");

            Thread.sleep(10000);
            legal.recenttickets();
            Thread.sleep(4000);
            logger.info("Recent Tickets modal opened.");
            ScreenshotUtils.attachScreenshotToAllure(driver, "3_Recent_Tickets_Modal");

            try {
                WebElement firstTicket = driver.findElement(By.cssSelector("tr.ticket_index_tr ul.tcktdetails li a"));
                firstTicket.click();
                logger.info("Clicked first dynamic ticket link.");
                Thread.sleep(10000);
                ScreenshotUtils.attachScreenshotToAllure(driver, "4_Selected_Ticket_Page");

                legal.copyurl.click();
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




            logStep("🌐 Redirecting the user to the Legal Metrology page...");
            logStep("✅ Field Details screen for Legal Metrology page");
            logToAllure("✅ The user Navigated to Legal Metrology page", "Legal Metrology page redirected successfully");
            ScreenshotUtils.attachScreenshotToAllure(driver, "Legal Metrology page redirected successfully");

        } catch (AssertionError e) {
            String msg = "❌ Exception during Accessing the Customer view : " + e.getMessage();
            logger.error(msg);
            logStep(msg);
            logToAllure("❌ Exception", msg);
            ScreenshotUtils.attachScreenshotToAllure(driver, "Legal Metrology page_Exception");
            throw e;
        }
    }

    @When("the user selecting the entity type for legal metrology")
    public void legalmetrelogy_entity() throws InterruptedException {
        logStep("🏢 Starting Legal Entity selection for Legal Metrology");
        logger.info("===== Legal Entity Selection Flow Started =====");
        Thread.sleep(2000);
        legal.entitytype(ConfigReader.get("legalentity"));
        Thread.sleep(2000);
    }
    @When("the user updates the address and office details for legal metrology")
    public void addressandoffice() throws InterruptedException {

        logStep("📍 Starting Address and Office details entry");
        logger.info("===== Legal Metrology Address Flow Started =====");

        try {
            logStep("🏠 Entering Address details");
            String addr1 = ConfigReader.get("Address1");
            String pin = ConfigReader.get("FoodAddressPinCode");

            if (addr1 == null || pin == null) {
                throw new RuntimeException("❌ Critical Address data (Line 1 or Pincode) is missing in config");
            }

            legal.addressline1(addr1);
            legal.addressline2(ConfigReader.get("Address2"));
            legal.pincode(pin);
            logger.info("Address entered: {}, Pincode: {}", addr1, pin);

            Thread.sleep(200);
            legal.listofproducts(ConfigReader.get("HowInventionDiffer"));
            legal.addressnext();

            logStep("👤 Entering Authorized Person details");
            String contact = ConfigReader.get("ApplicantPhone");
            String email = ConfigReader.get("ApplicantEmail");

            legal.authorizedpersonname(ConfigReader.get("BrandName"));
            legal.authorizedpersoncontact(contact);
            legal.authorizedpersonemail(email);

            logger.info("Contact info entered → Phone: {}, Email: {}", contact, email);

            logStep("🏢 Selecting Industry and finishing Office details");
            legal.industry(ConfigReader.get("legalentity"));
            legal.officebutton();

            Thread.sleep(200);
            logStep("✅ Address and Office details updated successfully");
            ScreenshotUtils.attachScreenshotToAllure(driver, "Address_Office_Details_Success");

        } catch (AssertionError e) {
            logStep("❌ Error during Address/Office details entry");
            logger.error("Address flow failed. Current Address1: {}", ConfigReader.get("Address1"));

            ScreenshotUtils.attachScreenshotToAllure(driver, "Address_Office_Failure");
            throw e;
        }
    }

    @Then("the user uploading docs in the Legal metrology")
    public void legalmetrelogy_uploadingdocs() throws InterruptedException {

        logStep("📂 Starting Document Upload for Legal Metrology");
        logger.info("===== Document Upload Flow Started =====");

        try {
            logStep("📤 Uploading Business Documents");

            String panPath = ConfigReader.get("PAN1");
            validateFilePath(panPath, "PAN Card");
            legal.pancard.sendKeys(panPath);
            logger.info("PAN Card uploaded from path → {}", panPath);
            Thread.sleep(2000);

            String coiPath = ConfigReader.get("COI");
            validateFilePath(coiPath, "COI");
            legal.coi.sendKeys(coiPath);
            logger.info("COI uploaded from path → {}", coiPath);
            Thread.sleep(2000);

            String gstPath = ConfigReader.get("DrivingLicense");
            validateFilePath(gstPath, "GST/License");
            legal.gstcertificate.sendKeys(gstPath);
            logger.info("GST Certificate/License uploaded → {}", gstPath);
            Thread.sleep(2000);

            logStep("📤 Uploading Identity Documents (Aadhar)");

            String aadharFront = ConfigReader.get("AadharUploadPic1");
            String aadharBack = ConfigReader.get("AadharUploadBack");

            legal.aadharcardfront.sendKeys(aadharFront);
            logger.info("Aadhar Front uploaded.");
            Thread.sleep(2000);

            legal.aadharcardback.sendKeys(aadharBack);
            logger.info("Aadhar Back uploaded.");
            Thread.sleep(2000);

            logStep("🖱 Clicking Upload Button");
            legal.uploadbutton();

            Thread.sleep(2000);
            logStep("✅ All documents uploaded successfully");
            ScreenshotUtils.attachScreenshotToAllure(driver, "Document_Upload_Success");

        } catch (AssertionError e) {
            logStep("❌ Error during Document Upload");
            logger.error("Document upload failed. Check file paths in config.");
            ScreenshotUtils.attachScreenshotToAllure(driver, "Document_Upload_Failure");
            throw e;
        }
    }

    private void validateFilePath(String path, String docName) {
        if (path == null || path.isEmpty()) {
            throw new RuntimeException("❌ File path for " + docName + " is missing in config");
        }
    }
}




