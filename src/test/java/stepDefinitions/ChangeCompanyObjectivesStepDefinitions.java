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

public class ChangeCompanyObjectivesStepDefinitions {
    WebDriver driver = Hooks.driver;
    UploadDocumentPage uploadDocumentPage;
    Logger logger;
    WebDriverWait wait;
    AllureLoggerUtils allureLogging;
    SearchTicketStepdefs searchTicketsteps;
    LoginStepDefinitions loginSteps;


    public ChangeCompanyObjectivesStepDefinitions() {
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
    @Given("the user redirect to upload document page of Change the Objectives of Your Company service")
    public void the_user_redirect_to_upload_document_page_of_change_the_objectives_of_your_company_service() throws InterruptedException {
        try {

            searchTicketsteps.theUserRedirectToTicketListPage();
            searchTicketsteps.theUserEnterTheTicket();
            searchTicketsteps.theUserClickTheSearchCTA();
            searchTicketsteps.theUserViewTheTicketPageByClickingOnTheLink();

            uploadDocumentPage.crossSaleNew();
            uploadDocumentPage.createservice(ConfigReader.get("Changeobjofcompany"));
            uploadDocumentPage.selectingtheservice4();
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




            logStep("🌐 Navigating to the Change the Objectives of Your Company service page...");
            logger.info("Navigated to URL: {}", ConfigReader.get("ChangeObjURL"));
            logStep("✅ Open Change Objective Of Company Document Page");
            logToAllure("✅ Change the Objectives Redirection Validation", "Page redirected successfully");
            ScreenshotUtils.attachScreenshotToAllure(driver, "ChangeObjectiveOfCompanyPage");
        } catch (IllegalArgumentException | WebDriverException | AssertionError e) {
            String msg = "❌ Exception during Change the Objectives of Your Company service page: " + e.getMessage();
            logger.error(msg);
            logStep(msg);
            logToAllure("❌ Exception", msg);
            ScreenshotUtils.attachScreenshotToAllure(driver, "ChangeObjURL_Exception");
            throw e;
        }
    }
    @When("the user update company details for Change Objectives of Your Company service")
    public void the_user_update_company_details_for_change_objectives_of_your_company_service() {
        try {
            logStep("🏢 Updating company details...");
            // Company Name
            uploadDocumentPage.typeCompanyNameForChangeCmpyObj(ConfigReader.get("CompanyName"));
            logger.info("Entered Company Name: {}", "ABC Private Limited");
            logStep("✅ Company name entered");
            logToAllure("Company Name", "ABC Private Limited");
            ScreenshotUtils.attachScreenshotToAllure(driver, "CompanyName_Entered");
            uploadDocumentPage.clickNextCTA();
            logStep("🔘 Clicked Next CTA after company name");

            // CIN
            uploadDocumentPage.typeCompanyCIN(ConfigReader.get("CompanyCIN"));
            logger.info("Entered Company CIN: {}", "U12345TN2024PTC000111");
            logStep("✅ Company CIN entered");
            logToAllure("Company CIN", "U12345TN2024PTC000111");
            ScreenshotUtils.attachScreenshotToAllure(driver, "CompanyCIN_Entered");
            uploadDocumentPage.clickNextCTA();
            logStep("🔘 Clicked Next CTA after CIN");

        } catch (IllegalArgumentException | WebDriverException | AssertionError e) {
            String message = "❌ Exception during company details entry: " + e.getMessage();
            logger.error(message, e);
            logStep(message);
            logToAllure("❌ Exception", message);
            ScreenshotUtils.attachScreenshotToAllure(driver, "UpdateCompanyDetails_Exception");
            throw e;
        }
    }
    @Then("the user update objective details for Change Objectives of Your Company service")
    public void the_user_update_objective_details_for_change_objectives_of_your_company_service() {
        try {
            logStep("🏢 Updating Objective Details");
            uploadDocumentPage.typeObjectiveName(ConfigReader.get("AddObjective"));
            uploadDocumentPage.typeMainObjective(ConfigReader.get("AddObjective"));
            uploadDocumentPage.typeOldObjective(ConfigReader.get("AddObjective"));
            uploadDocumentPage.typeNewObjective(ConfigReader.get("AddObjective"));
            uploadDocumentPage.clickNextCTA();
//            uploadDocumentPage.chooseShareTransfer();
//            uploadDocumentPage.chooseROCFiled();
//            uploadDocumentPage.clickNextCTA2();
            logger.info("✅ Updated Objective Details successfully");
            logStep("✅ Completed entering Objective details");
            logToAllure("Objective Details", "Updating the objective details");
            ScreenshotUtils.attachScreenshotToAllure(driver, "ObjectiveDetails_Entered");
        } catch (AssertionError ae) {
            String message = "❌ Objective details entry: " + ae.getMessage();
            logger.error(message);
            logStep(message);
            logToAllure("❌ Assertion Error", message);
            ScreenshotUtils.attachScreenshotToAllure(driver, "UpdateObjectiveDetails_Failed");
            throw ae;
        }
    }
}
