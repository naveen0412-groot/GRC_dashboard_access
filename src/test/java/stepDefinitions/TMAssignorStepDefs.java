package stepDefinitions;

import hooks.Hooks;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import io.qameta.allure.Step;
import org.apache.logging.log4j.Logger;
import org.junit.Assert;
import org.openqa.selenium.*;
import pages.TMAssignorPage;
import utils.*;

import java.awt.*;
import java.awt.datatransfer.DataFlavor;

import static utils.AllureLoggerUtils.logToAllure;

public class TMAssignorStepDefs {

    WebDriver driver = Hooks.driver;
    TMAssignorPage tmAssignorPage;
    Logger logger;
    AllureLoggerUtils allureLogging;
    WaitUtils wait;
    SearchTicketStepdefs searchTicketsteps;
    LoginStepDefinitions loginSteps;



    public TMAssignorStepDefs() {
        this.driver = Hooks.driver;
        this.tmAssignorPage = new TMAssignorPage(Hooks.driver);
        this.logger = LoggerUtils.getLogger(getClass());
        this.wait = new WaitUtils(driver,30);
        this.searchTicketsteps = new SearchTicketStepdefs(driver);
        this.loginSteps = new LoginStepDefinitions();
    }

    @Step("{message}")
    public void logStep(String message) {
        // Allure will log this message as a step
    }
    @Given("the user redirect to Assign your Trademark service")
    public void the_user_redirect_to_assign_your_trademark_service() {
        try {
            searchTicketsteps.theUserRedirectToTicketListPage();
            searchTicketsteps.theUserEnterTheTicket();
            searchTicketsteps.theUserClickTheSearchCTA();
            searchTicketsteps.theUserViewTheTicketPageByClickingOnTheLink();
            ScreenshotUtils.attachScreenshotToAllure(driver, "1_Ticket_Page_Loaded");

            tmAssignorPage.crossSaleNew();
            tmAssignorPage.createservice(ConfigReader.get("assigntrademark"));
            tmAssignorPage.selectingtheservice();
            Thread.sleep(2000);
            tmAssignorPage.createbutton();
            logger.info("Service creation initiated for Assign Trademark.");
            ScreenshotUtils.attachScreenshotToAllure(driver, "2_Service_Created");

            Thread.sleep(15000);
            tmAssignorPage.recenttickets();
            Thread.sleep(4000);
            logger.info("Recent Tickets modal opened.");
            ScreenshotUtils.attachScreenshotToAllure(driver, "3_Recent_Tickets_Modal");

            try {
                WebElement firstTicket = driver.findElement(By.cssSelector("tr.ticket_index_tr ul.tcktdetails li a"));
                firstTicket.click();
                logger.info("Clicked first dynamic ticket link.");
                Thread.sleep(10000);
                ScreenshotUtils.attachScreenshotToAllure(driver, "4_Ticket_Details_Page");

                tmAssignorPage.copyurl.click();
                logger.info("Copy button clicked to capture dynamic URL.");
                Thread.sleep(4000);
                ScreenshotUtils.attachScreenshotToAllure(driver, "5_After_Copy_Action");

                String dynamicUrl = (String) Toolkit.getDefaultToolkit().getSystemClipboard().getData(DataFlavor.stringFlavor);

                if (dynamicUrl != null && dynamicUrl.startsWith("http")) {
                    logger.info("Captured Dynamic URL: {}", dynamicUrl);
                    logStep("🌐 Navigating to dynamic clipboard URL: " + dynamicUrl);
                    driver.get(dynamicUrl);

                    Thread.sleep(4000);
                    ScreenshotUtils.attachScreenshotToAllure(driver, "6_Final_Service_Page");

                    logToAllure("✅ Redirection Success", "Successfully navigated to dynamic flow: " + dynamicUrl);
                } else {
                    throw new Exception("Clipboard data is empty or invalid.");
                }

            } catch (Exception e) {
                logger.error("❌ Failed to capture or navigate to dynamic URL: {}", e.getMessage());
                ScreenshotUtils.attachScreenshotToAllure(driver, "Error_URL_Capture");
                Assert.fail("Dynamic URL capture failed: " + e.getMessage());
            }

            logStep("✅ Assign your Trademark service - Page redirected successfully");

        } catch (Exception e) {
            String msg = "❌ Critical Exception in Assign Trademark flow: " + e.getMessage();
            logger.error(msg);
            logStep(msg);
            logToAllure("❌ Step Failure", msg);
            ScreenshotUtils.attachScreenshotToAllure(driver, "Critical_Flow_Exception");
            Assert.fail(msg);
        }
    }
    @When("the user update the Trademark details")
    public void the_user_update_the_trademark_details() {
        try {
            tmAssignorPage.typeAadharNumber(ConfigReader.get("Aadhar1"));
            tmAssignorPage.typeTrademarkApplicationNumber(ConfigReader.get("TMApplicationNumber"));
            logger.info("Trademark details");
            logStep("✅ Update Trademark details");
            logToAllure("✅ Update Trademark service", "Updated Trademark details successfully");
            ScreenshotUtils.attachScreenshotToAllure(driver, "TrademarkDetails");
            tmAssignorPage.clickNextButton();
        } catch (AssertionError e) {
            String msg = "❌ Exception during Trademark flow: " + e.getMessage();
            logger.error(msg);
            logStep(msg);
            logToAllure("❌ Exception", msg);
            ScreenshotUtils.attachScreenshotToAllure(driver, "TrademarkDetails_Exception");
        }
    }
    @When("the user update the Details of Assignor")
    public void the_user_update_the_details_of_assignor() {
        try {
           /* for(int i=0;i<2;i++){
                tmAssignorPage.clickDeleteButton();
            }*/
            tmAssignorPage.clickAddDirectorsButton();
            //Director 1
            tmAssignorPage.clickFillDetailsButton();
            tmAssignorPage.typeApplicantName(ConfigReader.get("directorName1"));
            tmAssignorPage.typeAddress(ConfigReader.get("Address1"));
            tmAssignorPage.typeEmail(ConfigReader.get("DirectorEmail1"));
            tmAssignorPage.typeMobileNumber(ConfigReader.get("DirectorMobile1"));
            tmAssignorPage.enterRole.click();
            tmAssignorPage.enterRole.sendKeys(ConfigReader.get("Role"));
            tmAssignorPage.enterRole.sendKeys(Keys.ENTER);
            logger.info("Assignor details");
            logStep("✅ Update Assignor details");
            logToAllure("✅ Update Assignor service", "Updated Assignor details successfully");
            ScreenshotUtils.attachScreenshotToAllure(driver, "AssignorDetails");
            Thread.sleep(2000);
            tmAssignorPage.clickNextButton();
            Thread.sleep(2000);
            //Director 2
            tmAssignorPage.clickFillDetailsButton();
            tmAssignorPage.typeApplicantName(ConfigReader.get("directorName2"));
            tmAssignorPage.typeAddress(ConfigReader.get("Address2"));
            tmAssignorPage.typeEmail(ConfigReader.get("DirectorEmail2"));
            tmAssignorPage.typeMobileNumber(ConfigReader.get("DirectorMobile2"));
            tmAssignorPage.enterRole.click();
            tmAssignorPage.enterRole.sendKeys(ConfigReader.get("Role"));
            tmAssignorPage.enterRole.sendKeys(Keys.ENTER);
            logger.info("Assignor details");
            logStep("✅ Update Assignor details");
            logToAllure("✅ Update Assignor service", "Updated Assignor details successfully");
            ScreenshotUtils.attachScreenshotToAllure(driver, "AssignorDetails");
            Thread.sleep(2000);
            tmAssignorPage.clickNextButton();
            Thread.sleep(2000);
        } catch (AssertionError | InterruptedException | NoSuchElementException e) {
            String msg = "❌ Exception during Assignor flow: " + e.getMessage();
            logger.error(msg);
            logStep(msg);
            logToAllure("❌ Exception", msg);
            ScreenshotUtils.attachScreenshotToAllure(driver, "AssignorDetails_Exception");
        }
    }
    @When("the user uploads the documents")
    public void the_user_uploads_the_documents() {
        try {
            logger.info("TM Assignor document upload flow");
            tmAssignorPage.clickDocumentsTab();
            /*for(int i=0;i<2;i++){
                tmAssignorPage.deleteDocument();
            }*/
            Thread.sleep(2000);
            tmAssignorPage.uploadPartnershipDeed.sendKeys(ConfigReader.get("COI"));
            Thread.sleep(2000);
            tmAssignorPage.uploadTmDocument.sendKeys(ConfigReader.get("BankStatement"));
            Thread.sleep(2000);
            logStep("✅ Update TM Assignor document upload");
            logToAllure("✅ Update TM Assignor document upload", "TM Assignor document uploaded successfully");
            ScreenshotUtils.attachScreenshotToAllure(driver, "TMAssignorDocument");
        } catch (AssertionError | InterruptedException | NoSuchElementException e) {
            String msg = "❌ Exception during TM Assignor document upload flow: " + e.getMessage();
            logger.error(msg);
            logStep(msg);
            logToAllure("❌ Exception", msg);
            ScreenshotUtils.attachScreenshotToAllure(driver, "TMAssignorDocument_Exception");
        }
    }
}
