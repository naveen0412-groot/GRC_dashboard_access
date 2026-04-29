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
import pages.UploadDocumentPage;
import utils.AllureLoggerUtils;
import utils.ConfigReader;
import utils.LoggerUtils;
import utils.ScreenshotUtils;

import java.awt.*;
import java.awt.datatransfer.DataFlavor;
import java.time.Duration;

import static utils.AllureLoggerUtils.logToAllure;

public class CopyrightStepDefinitions {
    WebDriver driver = Hooks.driver;
    UploadDocumentPage uploadDocumentPage;
    Logger logger;
    WebDriverWait wait;
    AllureLoggerUtils allureLogging;
    SearchTicketStepdefs searchTicketsteps;
    LoginStepDefinitions loginSteps;


    public CopyrightStepDefinitions() {
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

    @Given("the user redirect to upload document page of Copyright Registration service")
    public void the_user_redirect_to_upload_document_page_of_copyright_registration_service() throws InterruptedException {
        try {

            searchTicketsteps.theUserRedirectToTicketListPage();
            searchTicketsteps.theUserEnterTheTicket();
            searchTicketsteps.theUserClickTheSearchCTA();
            searchTicketsteps.theUserViewTheTicketPageByClickingOnTheLink();
            uploadDocumentPage.crossSaleNew();
            uploadDocumentPage.createservice(ConfigReader.get("Copyright"));
            uploadDocumentPage.selectingtheservice5();
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

            logger.info("Navigated to URL: {}", ConfigReader.get("CopyrightURL"));
            logStep("✅ Open Copyright Registration Service Page");
            logToAllure("✅ Copyright Redirection Validation", "Copyright Page redirected successfully");
            ScreenshotUtils.attachScreenshotToAllure(driver, "CopyRightRegistrationPage");
        } catch (IllegalArgumentException | WebDriverException | AssertionError e) {
            String msg = "❌ Exception during Copyright Registration service page: " + e.getMessage();
            logger.error(msg);
            logStep(msg);
            logToAllure("❌ Exception", msg);
            ScreenshotUtils.attachScreenshotToAllure(driver, "CopyrightURL_Exception");
            throw e;
        }
    }
    @When("the user update the Applicant details")
    public void the_user_update_the_applicant_details() {
        try {
            logStep("🏢 Updating Applicant Details");
            uploadDocumentPage.typeApplicantNameForCopyRight(ConfigReader.get("ApplicantName"));
            uploadDocumentPage.typeCopyrightAddress(ConfigReader.get("ApplicantAddress"));
            uploadDocumentPage.typeCopyrightNationalityApplicant(ConfigReader.get("Nationality"));
            uploadDocumentPage.clickNextCTA();
            logger.info("✅ Updated Applicant Details successfully");
            logStep("✅ Completed entering Applicant details");
            logToAllure("✅ Applicant Details", "Updating the applicant details successfully");
            ScreenshotUtils.attachScreenshotToAllure(driver, "ApplicantDetails_Entered");
        } catch (AssertionError ae) {
            String message = "❌ Applicant details entry: " + ae.getMessage();
            logger.error(message);
            logStep(message);
            logToAllure("❌ Assertion Error", message);
            ScreenshotUtils.attachScreenshotToAllure(driver, "UpdateApplicantDetails_Failed");
            throw ae;
        }
    }
    @When("the user update the Author details")
    public void the_user_update_the_author_details() {
        try {
            logStep("🏢 Updating Author Details");
            uploadDocumentPage.typeCopyrightAuthorName(ConfigReader.get("InventorName"));
            uploadDocumentPage.typeCopyrightAuthorAddress(ConfigReader.get("InventorAddress"));
            uploadDocumentPage.typeCopyrightNationalityAuditor(ConfigReader.get("Nationality"));
            uploadDocumentPage.clickNextCTA();
            logger.info("✅ Updated Author Details successfully");
            logStep("✅ Completed entering Author details");
            logToAllure("✅ Author Details", "Updating the author details successfully");
            ScreenshotUtils.attachScreenshotToAllure(driver, "AuthorDetails_Entered");
        } catch (AssertionError ae) {
            String message = "❌ Author details entry: " + ae.getMessage();
            logger.error(message);
            logStep(message);
            logToAllure("❌ Assertion Error", message);
            ScreenshotUtils.attachScreenshotToAllure(driver, "UpdateAuthorDetails_Failed");
            throw ae;
        }
    }
    @When("the user update the Copyright work")
    public void the_user_update_the_copyright_work() {

        try {
            logStep("🏢 Updating Copyright work");
            uploadDocumentPage.typeWorkTitle(ConfigReader.get("WorkTitle"));
            uploadDocumentPage.typeWorkNature(ConfigReader.get("WorkNature"));
            uploadDocumentPage.typeWorkLanguage(ConfigReader.get("WorkLanguage"));
            uploadDocumentPage.typeWorkOrigin(ConfigReader.get("AnotherWork"));
            uploadDocumentPage.typeWorkSoftCopies(ConfigReader.get("SoftcopyWork"));
            uploadDocumentPage.clickNextCTA();
            logger.info("✅ Updated Copyright work successfully");
            logStep("✅ Completed entering Copyright work");
            logToAllure("✅ Copyright work", "Updating the Copyright work successfully");
            ScreenshotUtils.attachScreenshotToAllure(driver, "Copyrightwork_Entered");
        } catch (AssertionError ae) {
            String message = "❌ Copyright work entry: " + ae.getMessage();
            logger.error(message);
            logStep(message);
            logToAllure("❌ Assertion Error", message);
            ScreenshotUtils.attachScreenshotToAllure(driver, "Copyrightwork_Failed");
            throw ae;
        }
    }
    @Then("the user update the Publisher details")
    public void the_user_update_the_publisher_details() {
        // setSelectDate
        try {
            //
            logStep("🏢 Updating Publisher Details");
            uploadDocumentPage.setPublishedDate();
            uploadDocumentPage.typePublisherName(ConfigReader.get("InventorName"));
            uploadDocumentPage.setCopyRightDate();
            uploadDocumentPage.typePublisherCountry(ConfigReader.get("Nationality"));
            uploadDocumentPage.clickNextCTA();
            logger.info("✅ Updated Publisher Details successfully");
            logStep("✅ Completed entering Publisher Details");
            logToAllure("✅ Publisher Details", "Updating the Publisher Details successfully");
            ScreenshotUtils.attachScreenshotToAllure(driver, "Publisher Details_Entered");
        } catch (Exception ae) {
            String message = "❌ Publisher Details entry: " + ae.getMessage();
            logger.error(message);
            logStep(message);
            logToAllure("❌ Assertion Error", message);
            ScreenshotUtils.attachScreenshotToAllure(driver, "Publisher Details_Failed");
            throw ae;
        }
    }
}
