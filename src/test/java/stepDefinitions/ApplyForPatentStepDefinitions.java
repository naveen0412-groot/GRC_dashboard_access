package stepDefinitions;

import hooks.Hooks;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.qameta.allure.Step;
import org.apache.logging.log4j.Logger;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;
import pages.UploadDocumentPage;
import utils.AllureLoggerUtils;
import utils.ConfigReader;
import utils.LoggerUtils;
import utils.ScreenshotUtils;

import java.awt.*;
import java.awt.datatransfer.DataFlavor;
import java.time.Duration;

import static utils.AllureLoggerUtils.logToAllure;

public class ApplyForPatentStepDefinitions {
    WebDriver driver = Hooks.driver;
    UploadDocumentPage uploadDocumentPage;
    Logger logger;
    WebDriverWait wait;
    AllureLoggerUtils allureLogging;
    SearchTicketStepdefs searchTicketsteps;
    LoginStepDefinitions loginSteps;


    public ApplyForPatentStepDefinitions() {
        this.driver = Hooks.driver;
        this.uploadDocumentPage = new UploadDocumentPage(Hooks.driver);
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        this.logger = LoggerUtils.getLogger(getClass());
        this.searchTicketsteps = new SearchTicketStepdefs(Hooks.driver);
        this.loginSteps = new LoginStepDefinitions();
    }

    @Step("{message}")
    public void logStep(String message) {
        // Allure will log this message as a step
    }
    @Given("the user redirect to upload document page of Apply for a patent service")
    public void the_user_redirect_to_upload_document_page_of_apply_for_a_patent_service() throws InterruptedException {
        try {

            searchTicketsteps.theUserRedirectToTicketListPage();
            searchTicketsteps.theUserEnterTheTicket();
            searchTicketsteps.theUserClickTheSearchCTA();
            searchTicketsteps.theUserViewTheTicketPageByClickingOnTheLink();

            uploadDocumentPage.crossSaleNew();
            uploadDocumentPage.createservice(ConfigReader.get("applyforpatent"));
            uploadDocumentPage.selectingtheservice2();
            Thread.sleep(2000);
            uploadDocumentPage.createbutton();
            logger.info("Service creation initiated.");
            ScreenshotUtils.attachScreenshotToAllure(driver, "2_Service_Created");

            Thread.sleep(15000);
            uploadDocumentPage.recenttickets();
            Thread.sleep(4000);
            logger.info("Recent Tickets modal opened.");
            ScreenshotUtils.attachScreenshotToAllure(driver, "3_Recent_Tickets_Modal");

            try {
                WebElement firstTicket = driver.findElement(By.cssSelector("tr.ticket_index_tr ul.tcktdetails li a"));
                firstTicket.click();
                logger.info("Clicked first dynamic ticket link.");
                Thread.sleep(10000);
                ScreenshotUtils.attachScreenshotToAllure(driver, "4_Selected_Ticket_Page");

                uploadDocumentPage.copyurl.click();
                logger.info("Copy button clicked.");
                Thread.sleep(4000);
                ScreenshotUtils.attachScreenshotToAllure(driver, "5_After_Copy_Action");

                String dynamicUrl = (String) Toolkit.getDefaultToolkit().getSystemClipboard().getData(DataFlavor.stringFlavor);

                if (dynamicUrl != null && dynamicUrl.startsWith("http")) {
                    logger.info("Navigating to dynamic URL captured: {}", dynamicUrl);
                    logStep("🌐 Navigating to: " + dynamicUrl);
                    driver.get(dynamicUrl);

                    Thread.sleep(3000);
                    ScreenshotUtils.attachScreenshotToAllure(driver, "6_Final_Destination_Page");
                } else {
                    throw new Exception("Clipboard did not contain a valid URL.");
                }

            } catch (Exception e) {
                logger.error("❌ Error during ticket selection/URL capture: {}", e.getMessage());
                ScreenshotUtils.attachScreenshotToAllure(driver, "Error_Capture_Failure");
                Assert.fail("Failed during dynamic link capture: " + e.getMessage());
            }




            logStep("🌐 Navigating to the Apply for a patent Document page...");
            logger.info("Navigated to URL: {}", ConfigReader.get("ApplyPatentURL"));
            logStep("✅ Open Apply for a patent Document Page");
            logToAllure("✅ Apply for a patent Redirection Validation", "Page redirected successfully");
            ScreenshotUtils.attachScreenshotToAllure(driver, "ApplyPatentPage");
        } catch (Exception e) {
            String msg = "❌ Exception during Apply for a patent page: " + e.getMessage();
            logger.error(msg);
            logStep(msg);
            logToAllure("❌ Exception", msg);
            ScreenshotUtils.attachScreenshotToAllure(driver, "ApplyPatentURL_Exception");
            throw e;
        }
    }
    @When("the user update the Applicant details and its type")
    public void the_user_update_the_applicant_details_and_its_type() {
        try {
            logStep("🏢 Updating Applicant details and its type");
            logStep("✍️ Update Applicant details");
            uploadDocumentPage.ChooseApplicantType();
            uploadDocumentPage.clickNextCTA();
            uploadDocumentPage.typeApplicantName(ConfigReader.get("ApplicantName"));
            uploadDocumentPage.typApplyPatentApplicantGender(ConfigReader.get("Gender")+ Keys.ENTER);
            uploadDocumentPage.typeApplicantAddress(ConfigReader.get("ApplicantAddress"));
            uploadDocumentPage.typeApplicantContactNumber(ConfigReader.get("ApplicantContactNumber"));
            uploadDocumentPage.typeApplicantEmailID(ConfigReader.get("ApplicantEmailId"));
            logger.info("✅ Applicant details entered successfully");
            logStep("✅ Completed Applicant details");
            logToAllure("✅ Applicant details", "Applicant details updated successfully");
            ScreenshotUtils.attachScreenshotToAllure(driver, "ApplicantDetails_Entered");
            uploadDocumentPage.clickNextCTA();
        } catch (AssertionError ae) {
            String message = "❌ Applicant details entry: " + ae.getMessage();
            logger.error(message);
            logStep(message);
            logToAllure("❌ Assertion Error", message);
            ScreenshotUtils.attachScreenshotToAllure(driver, "ApplicantDetails_Failed");
            throw ae;
        }
    }
    @When("the user update the Inventor Details")
    public void the_user_update_the_inventor_details() {
        try {
            logStep("🏢 Updating Inventor details");
            logStep("✍️ Update Inventor details");
            uploadDocumentPage.typeInventorName(ConfigReader.get("InventorName"));
            uploadDocumentPage.typeInventorAddress(ConfigReader.get("InventorAddress"));
            uploadDocumentPage.typeInventorEmailID(ConfigReader.get("InventorEmailId"));
            uploadDocumentPage.typeInventorContactNumber(ConfigReader.get("InventorContactNumber"));
            uploadDocumentPage.typeInventorGender(ConfigReader.get("Gender")+ Keys.ENTER);
            uploadDocumentPage.typeInventorSignature(ConfigReader.get("InventorName"));
            logger.info("✅ Inventor details entered successfully");
            logStep("✅ Completed Inventor details");
            logToAllure("✅Completed Inventor", "Completed Inventor updated successfully");
            ScreenshotUtils.attachScreenshotToAllure(driver, "InventorDetails_Entered");
            uploadDocumentPage.clickNextCTA();
        } catch (AssertionError ae) {
            String message = "❌ Inventor details entry: " + ae.getMessage();
            logger.error(message);
            logStep(message);
            logToAllure("❌ Assertion Error", message);
            ScreenshotUtils.attachScreenshotToAllure(driver, "InventorDetails_Failed");
            throw ae;
        }
    }
    @When("the user update the Type of patent application")
    public void the_user_update_the_type_of_patent_application() {
        try {
            logStep("🏢 Updating type of patent application");
            logStep("✍️ Update Type  of patent application");
            uploadDocumentPage.ChoosePatentApplicant(ConfigReader.get("PatentType"));
            logger.info("✅ Type of Patent application selected successfully");
            logStep("✅ Selected Type of Patent application");
            logToAllure("✅ Type of Patent", "Type of Patent updated successfully");
            ScreenshotUtils.attachScreenshotToAllure(driver, "TypeOfPatent_Entered");
            uploadDocumentPage.clickNextCTA();
        } catch (AssertionError ae) {
            String message = "❌ TypeOfPatent entry: " + ae.getMessage();
            logger.error(message);
            logStep(message);
            logToAllure("❌ Assertion Error", message);
            ScreenshotUtils.attachScreenshotToAllure(driver, "TypeOfPatent_Failed");
            throw ae;
        }
    }
    @Then("the user update the Publication and Examination preference")
    public void the_user_update_the_publication_and_examination_preference() {
        try {
            logStep("🏢 Updating Publication preference");
            logStep("✍️ Update Publication preference");
            uploadDocumentPage.ChooseNormPublication();
            uploadDocumentPage.ChooseEarlyPublication();
            logger.info("✅ Publication preference selected successfully");
            logStep("✅ Selected Publication preference");
            ScreenshotUtils.attachScreenshotToAllure(driver, "Publication_selected");
            uploadDocumentPage.clickNextCTA();
        } catch (AssertionError ae) {
            String message = "❌ Publication entry: " + ae.getMessage();
            logger.error(message);
            logStep(message);
            logToAllure("❌ Assertion Error", message);
            ScreenshotUtils.attachScreenshotToAllure(driver, "Publication_Failed");
            throw ae;
        }
        try {
            logStep("🏢 Updating Examination preference");
            logStep("✍️ Update Examination preference");
            uploadDocumentPage.ChooseNormExamination();
            uploadDocumentPage.ChooseExpeditedExamination();
            logger.info("✅ Examination preference selected successfully");
            logStep("✅ Selected Examination preference");
            ScreenshotUtils.attachScreenshotToAllure(driver, "Examination_selected");
            uploadDocumentPage.clickNextCTA();
        } catch (AssertionError ae) {
            String message = "❌ Examination entry: " + ae.getMessage();
            logger.error(message);
            logStep(message);
            logToAllure("❌ Assertion Error", message);
            ScreenshotUtils.attachScreenshotToAllure(driver, "Examination_Failed");
            throw ae;
        }
    }
}
