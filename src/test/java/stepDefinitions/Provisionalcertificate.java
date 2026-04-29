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
import pages.Provisionalcerpage;
import utils.ConfigReader;
import utils.LoggerUtils;
import utils.ScreenshotUtils;

import java.awt.*;
import java.awt.datatransfer.DataFlavor;
import java.time.Duration;

import static utils.AllureLoggerUtils.logToAllure;

public class Provisionalcertificate {

    WebDriver driver = Hooks.driver;
    Provisionalcerpage provisional;
    Logger logger;
    WebDriverWait wait;
    SearchTicketStepdefs searchTicketsteps;
    LoginStepDefinitions loginSteps;


    public Provisionalcertificate() throws AWTException {
        this.driver = Hooks.driver;
        this.provisional = new Provisionalcerpage(Hooks.driver);
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        this.logger = LoggerUtils.getLogger(getClass());
        this.searchTicketsteps = new SearchTicketStepdefs(Hooks.driver);
        this.loginSteps = new LoginStepDefinitions();
    }

    @Step("{message}")
    public void logStep(String message) {
    }

    @Given("the user redirect to Provisional certificate services")
    public void setProvisional() throws InterruptedException{

        try {

            searchTicketsteps.theUserRedirectToTicketListPage();
            searchTicketsteps.theUserEnterTheTicket();
            searchTicketsteps.theUserClickTheSearchCTA();
            searchTicketsteps.theUserViewTheTicketPageByClickingOnTheLink();

            provisional.crossSaleNew();
            provisional.createservice(ConfigReader.get("Provisional12gservice"));
            provisional.selectingtheservice();
            Thread.sleep(2000);
            provisional.createbutton();
            logger.info("Service creation initiated.");
            ScreenshotUtils.attachScreenshotToAllure(driver, "2_Service_Created");

            Thread.sleep(15000);
            provisional.recenttickets();
            Thread.sleep(4000);
            logger.info("Recent Tickets modal opened.");
            ScreenshotUtils.attachScreenshotToAllure(driver, "3_Recent_Tickets_Modal");

            try {
                WebElement firstTicket = driver.findElement(By.cssSelector("tr.ticket_index_tr ul.tcktdetails li a"));
                firstTicket.click();
                logger.info("Clicked first dynamic ticket link.");
                Thread.sleep(10000);
                ScreenshotUtils.attachScreenshotToAllure(driver, "4_Selected_Ticket_Page");

                provisional.copyurl.click();
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
            logStep("🌐 Redirecting the user to the Provisional certificate page...");
            logStep("✅ Field Details screen for Provisional certificate page");
            logToAllure("✅ The user Navigated to Provisional certificate page", "Provisional certificate page redirected successfully");
            ScreenshotUtils.attachScreenshotToAllure(driver, "Provisional certificate page redirected successfully");

        } catch (AssertionError e) {
            String msg = "❌ Exception during Accessing the Customer view : " + e.getMessage();
            logger.error(msg);
            logStep(msg);
            logToAllure("❌ Exception", msg);
            ScreenshotUtils.attachScreenshotToAllure(driver, "Provisional certificate page_Exception");
            throw e;
        }
    }

    @When("the user entering the Details of NGO")
    public void detailsofngo() throws InterruptedException {
        logStep("🏢 Filling NGO General and Dropdown Details");
        logger.info("===== NGO Details Flow Started =====");

        try {
            provisional.FoundationName(ConfigReader.get("directorName1"));
            provisional.mobileNumber(ConfigReader.get("DirectorMobile1"));
            provisional.emailID(ConfigReader.get("DirectorEmail2"));
            provisional.address(ConfigReader.get("ApplicantAddress"));
            provisional.incomeTaxLoginID(ConfigReader.get("GSTDetails"));
            provisional.incomeTaxLoginPassword(ConfigReader.get("lutpassword"));
            logger.info("Filled Basic NGO text fields successfully");

            String natureVal = ConfigReader.get("natureofact");
            String constVal = ConfigReader.get("typeofconstitution");
            String deedVal = ConfigReader.get("trustded");

            provisional.selectReactDropdown(provisional.natureofactivity, natureVal, "NatureOfActivity");
            provisional.selectReactDropdown(provisional.typeofconstution, constVal, "ConstitutionType");
            provisional.selectReactDropdown(provisional.trustdeed, deedVal, "TrustDeed");

            ScreenshotUtils.attachScreenshotToAllure(driver, "NGO_Details_Full_Form");

            provisional.DetailsofNgobutton();
            Thread.sleep(2000);
            logger.info("===== NGO Details Selection Completed Successfully =====");
        } catch (AssertionError e) {
            logger.error("Critical failure in NGO details entry: " + e.getMessage());
            ScreenshotUtils.attachScreenshotToAllure(driver, "NGO_Details_Failure");
            throw e;
        }
    }

    @When("the user entering the Details of Auth Person")
    public void detailsofauthperson() throws InterruptedException {
        logStep("👤 Entering Authorized Person Details");
        logger.info("===== Auth Person Flow Started =====");

        try {
            String authName = ConfigReader.get("directorName1");
            logger.info("Entering details for Auth Person: [{}]", authName);

            provisional.name(authName);
            provisional.getEmailID(ConfigReader.get("DirectorEmail2"));
            provisional.getMobileNumber(ConfigReader.get("DirectorMobile1"));
            provisional.getIncomeTaxLoginID(ConfigReader.get("GSTDetails"));
            provisional.getIncomeTaxLoginPassword(ConfigReader.get("lutpassword"));
            provisional.address(ConfigReader.get("ApplicantAddress"));

            ScreenshotUtils.attachScreenshotToAllure(driver, "Auth_Person_Details_Entered");

            provisional.detailsofauthbutton();
            Thread.sleep(2000);
            logger.info("Auth person details submitted successfully");
        } catch (AssertionError e) {
            logger.error("Error in detailsofauthperson: " + e.getMessage());
            throw e;
        }
    }

    @Then("the user uploading docs for Provisional certificate")
    public void theUserUploadingDocsForprovisional() throws InterruptedException {
        logStep("📂 Uploading Documents for Provisional Certificate");
        logger.info("===== Document Upload Flow Started =====");

        try {

            String panPath = ConfigReader.get("PAN1");
            Assert.assertNotNull(panPath, "PAN card upload path is missing in Config!");

            logger.info("Uploading PAN Card from: [{}]", panPath);
            provisional.Pancard.sendKeys(panPath);
            Thread.sleep(2000);

            logger.info("Uploading Aadhar Front and Back");
            provisional.Aadharcard.sendKeys(ConfigReader.get("AadharUploadPic1"));
            Thread.sleep(2000);

            provisional.aadharcardback.sendKeys(ConfigReader.get("AadharUploadBack"));
            Thread.sleep(2000);

            ScreenshotUtils.attachScreenshotToAllure(driver, "Provisional_Docs_Selected");

            provisional.uploadbbutton();
            logger.info("All documents uploaded and Submit clicked");
            Thread.sleep(2000);

        } catch (AssertionError e) {
            logger.error("Error during document upload: " + e.getMessage());
            ScreenshotUtils.attachScreenshotToAllure(driver, "Document_Upload_Error");
            throw e;
        }
    }
}




