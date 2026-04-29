package stepDefinitions;

import hooks.Hooks;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.qameta.allure.Step;
import org.apache.logging.log4j.Logger;
import org.junit.Assert;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.WebDriverWait;
import pages.UploadDocumentPage;
import utils.*;

import java.awt.*;
import java.awt.datatransfer.DataFlavor;
import java.time.Duration;

import static utils.AllureLoggerUtils.logToAllure;

public class RemoveDirStepDefinitions {
    WebDriver driver = Hooks.driver;
    UploadDocumentPage uploadDocumentPage;
    Logger logger;
    WebDriverWait wait;
    AllureLoggerUtils allureLogging;
    SearchTicketStepdefs searchTicketsteps;
    LoginStepDefinitions loginSteps;


    public RemoveDirStepDefinitions() {
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

    @Given("the user redirect to upload document page of Remove director service")
    public void the_user_redirect_to_upload_document_page_of_remove_director_service() throws InterruptedException {
        try {

            searchTicketsteps.theUserRedirectToTicketListPage();
            searchTicketsteps.theUserEnterTheTicket();
            searchTicketsteps.theUserClickTheSearchCTA();
            searchTicketsteps.theUserViewTheTicketPageByClickingOnTheLink();

            uploadDocumentPage.crossSaleNew();
            uploadDocumentPage.createservice(ConfigReader.get("RemovalofDir"));
            uploadDocumentPage.selectingtheservice3();
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
            logStep("🌐 Navigating to the Remove Director Document page...");
            logStep("✅ Open Remove Director Document Page");
            logToAllure("✅ Remove Director Redirection Validation", "Remove Director Page redirected successfully");
            ScreenshotUtils.attachScreenshotToAllure(driver, "RemoveDirectorPage");
        } catch (IllegalArgumentException | WebDriverException | AssertionError e) {
            String msg = "❌ Exception during Remove Director page: " + e.getMessage();
            logger.error(msg);
            logStep(msg);
            logToAllure("❌ Exception", msg);
            ScreenshotUtils.attachScreenshotToAllure(driver, "RemoveDirectorURL_Exception");
            throw e;
        }
    }
    @When("the user update company details for Remove director service")
    public void the_user_update_company_details_for_remove_director_service() {
        try {
            logStep("🏢 Updating company details...");
            // Company Name
            uploadDocumentPage.typeCompanyName(ConfigReader.get("CompanyName"));
            logger.info("Entered Company Name: {}", "ABC Private Limited");
            logStep("✅ Company name entered");
            logToAllure("Company Name for Removal", "Entered Company Name for removal");
            ScreenshotUtils.attachScreenshotToAllure(driver, "CompanyName_Entered");
            uploadDocumentPage.clickNextCTA();
            logStep("🔘 Clicked Next CTA after company name");

            // CIN
            uploadDocumentPage.typeCompanyCIN(ConfigReader.get("CompanyCIN"));
            logger.info("Entered Company CIN: {}", "U12345TN2024PTC000111");
            logStep("✅ Company CIN entered");
            logToAllure("Company CIN for Removal", "Entered Company CIN for removal");
            ScreenshotUtils.attachScreenshotToAllure(driver, "CompanyCIN_Entered");
            uploadDocumentPage.clickNextCTA();
            logStep("🔘 Clicked Next CTA after CIN");
        } catch (AssertionError e) {
            String message = "❌ Exception during company details entry: " + e.getMessage();
            logger.error(message, e);
            logStep(message);
            logToAllure("❌ Exception", message);
            ScreenshotUtils.attachScreenshotToAllure(driver, "UpdateCompanyDetails_Exception");
            throw e;
        }
    }
    @When("the user update director details for Remove director service")
    public void the_user_update_director_details_for_remove_director_service() {

        try {
//            for (int i = 0; i < 2; i++) {
//                uploadDocumentPage.deleteDirector();
//            }
            uploadDocumentPage.clickAddDirectorCTA();
            logStep("👤 Clicked Remove Director CTA");
            logStep("🏢 Updating Director details - 1");
            uploadDocumentPage.clickFillDetailsCTA();
            logStep("✍️ Clicked Fill Details CTA for Director 1");
            // Director Details - 1
            uploadDocumentPage.typeDirectorName(ConfigReader.get("directorName1"));
            uploadDocumentPage.typeDin(ConfigReader.get("din1"));
            uploadDocumentPage.typeDscAvailabilityRemDir(ConfigReader.get("DSCAvailability"));
            uploadDocumentPage.typeResignationReason(ConfigReader.get("ReasonForResignation"));
            uploadDocumentPage.clickNextCTA();
            logger.info("✅ First director details entered successfully");
            logStep("✅ Completed entering first director details");
            logToAllure("✅ Validation for First director details", "First director details validated successfully");
            ScreenshotUtils.attachScreenshotToAllure(driver, "FirstDirectorDetails_Entered");
        } catch (AssertionError ae) {
            String message = "❌ First director details entry: " + ae.getMessage();
            logger.error(message);
            logStep(message);
            logToAllure("❌ Assertion Error", message);
            ScreenshotUtils.attachScreenshotToAllure(driver, "UpdateFirstDirectorDetails_Failed");
            throw ae;
        }
        //Director 2
        try {
            logStep("🏢 Updating Director details - 2");
            uploadDocumentPage.clickFillDetailsCTA();
            logStep("✍️ Clicked Fill Details CTA for Director 2");
            // Director Details - 2
            uploadDocumentPage.typeDirectorName(ConfigReader.get("directorName2"));
            uploadDocumentPage.typeDin(ConfigReader.get("din2"));
            uploadDocumentPage.typeDscAvailabilityRemDir(ConfigReader.get("DSCAvailability"));
            uploadDocumentPage.typeResignationReason(ConfigReader.get("ReasonForResignation2"));
            uploadDocumentPage.clickNextCTA();
            logger.info("✅ Second director details entered successfully");
            logStep("✅ Completed entering second director details");
            logToAllure("✅ Validation for Second director details", "Second director details validated successfully");
            ScreenshotUtils.attachScreenshotToAllure(driver, "SecondDirectorDetails_Entered");
            try {
                Thread.sleep(5000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            uploadDocumentPage.clickNextCTA();
        } catch (AssertionError ae) {
            String message = "❌ Second director details entry: " + ae.getMessage();
            logger.error(message);
            logStep(message);
            logToAllure("❌ Assertion Error", message);
            ScreenshotUtils.attachScreenshotToAllure(driver, "UpdateSecondDirectorDetails_Failed");
            throw ae;
        }
    }
    @Then("the user upload the documents for Remove director service")
    public void the_user_upload_the_documents_for_remove_director_service() {
        try {
            logStep("🏢 Uploading the company documents");
            uploadDocumentPage.AOAUpload.sendKeys(ConfigReader.get("COI"));
            try {
                Thread.sleep(5000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            uploadDocumentPage.MOAUploadRemove.sendKeys(ConfigReader.get("BankStatement"));
            try {
                Thread.sleep(5000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            uploadDocumentPage.COIUploadRemove.sendKeys(ConfigReader.get("DrivingLicense"));
            try {
                Thread.sleep(5000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
                logger.info("✅ Company documents uploaded successfully");
                logStep("✅ Uploaded the company documents");
                logToAllure("✅ Company doc upload validation for RemoveDir", "Company document upload validated successfully");
                ScreenshotUtils.attachScreenshotToAllure(driver, "CompanyDocuments_Uploaded");

        } catch (AssertionError ae) {
            String message = "❌ Company documents upload: " + ae.getMessage();
            logger.error(message);
            logStep(message);
            logToAllure("❌ Assertion Error", message);
            ScreenshotUtils.attachScreenshotToAllure(driver, "CompanyDocumentUpload_Failed");
            throw ae;
        }
    }
}
