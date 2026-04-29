package stepDefinitions;

import hooks.Hooks;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import io.qameta.allure.Step;
import org.apache.logging.log4j.Logger;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import pages.ChequeBounceNoticePage;
import utils.*;

import java.awt.*;
import java.awt.datatransfer.DataFlavor;

import static utils.AllureLoggerUtils.logToAllure;

public class ChequeBounceNoticeStepDefs {
    WebDriver driver = Hooks.driver;
    ChequeBounceNoticePage chequeBounceNoticePage;
    Logger logger;
    WaitUtils wait;
    SearchTicketStepdefs searchTicketsteps;
    LoginStepDefinitions loginSteps;

    public ChequeBounceNoticeStepDefs() {
        this.driver = Hooks.driver;
        this.chequeBounceNoticePage = new ChequeBounceNoticePage(Hooks.driver);
        this.logger = LoggerUtils.getLogger(getClass());
        this.wait = new WaitUtils(driver,30);
        this.searchTicketsteps = new SearchTicketStepdefs(Hooks.driver);
        this.loginSteps = new LoginStepDefinitions();
    }

    @Step("{message}")
    public void logStep(String message) {
        // Allure will log this message as a step
    }
    @Given("the user redirect to cheque bounce notice service")
    public void the_user_redirect_to_cheque_bounce_notice_service() throws InterruptedException {
        try {

            searchTicketsteps.theUserRedirectToTicketListPage();
            searchTicketsteps.theUserEnterTheTicket();
            searchTicketsteps.theUserClickTheSearchCTA();
            searchTicketsteps.theUserViewTheTicketPageByClickingOnTheLink();

            chequeBounceNoticePage.crossSaleNew();
            chequeBounceNoticePage.createservice(ConfigReader.get("ChequeBounce"));
            chequeBounceNoticePage.selectingtheservice();
            Thread.sleep(2000);
            chequeBounceNoticePage.createbutton();
            logger.info("Service creation initiated.");
            ScreenshotUtils.attachScreenshotToAllure(driver, "2_Service_Created");

            Thread.sleep(15000);
            chequeBounceNoticePage.recenttickets();
            Thread.sleep(4000);
            logger.info("Recent Tickets modal opened.");
            ScreenshotUtils.attachScreenshotToAllure(driver, "3_Recent_Tickets_Modal");

            try {
                WebElement firstTicket = driver.findElement(By.cssSelector("tr.ticket_index_tr ul.tcktdetails li a"));
                firstTicket.click();
                logger.info("Clicked first dynamic ticket link.");
                Thread.sleep(10000);
                ScreenshotUtils.attachScreenshotToAllure(driver, "4_Selected_Ticket_Page");

                chequeBounceNoticePage.copyurl.click();
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
            logStep("🌐 Navigating to the ChequeBounceNotice service page...");
            logger.info("Navigated to URL: {}", ConfigReader.get("ChequeBounceNoticeURL"));
            logStep("✅ Change your company name");
            logToAllure("✅ Change your company name page redirection Validation", "Page redirected successfully");
            ScreenshotUtils.attachScreenshotToAllure(driver, "ChangeYourCompanyName");
        } catch (IllegalArgumentException | AssertionError e) {
            String msg = "❌ Exception during Change the Official Address of Your Company (Within the State) service page: " + e.getMessage();
            logger.error(msg);
            logStep(msg);
            logToAllure("❌ Exception", msg);
            ScreenshotUtils.attachScreenshotToAllure(driver, "ChangeYourCompanyName_Exception");
            throw e;
        }
    }
    @When("the user update the Party details")
    public void the_user_update_the_party_details() {
        try{
            chequeBounceNoticePage.typeName(ConfigReader.get("directorName1"));
            chequeBounceNoticePage.typeAddress(ConfigReader.get("Address1"));
            Thread.sleep(2000);
            chequeBounceNoticePage.clickNextButton();
            Thread.sleep(2000);
            chequeBounceNoticePage.typeName(ConfigReader.get("directorName2"));
            chequeBounceNoticePage.typeAddress(ConfigReader.get("Address2"));
            logStep("✅ Change your Party details");
            logToAllure("✅ Change your Party name", "Updated Party details successfully");
            ScreenshotUtils.attachScreenshotToAllure(driver, "ChangeCompanyName");
            chequeBounceNoticePage.clickNextButton();
            Thread.sleep(2000);
        } catch (IllegalArgumentException | AssertionError | InterruptedException e) {
            String msg = "❌ Exception during Change your company name: " + e.getMessage();
            logStep(msg);
            logToAllure("❌ Exception", msg);
            ScreenshotUtils.attachScreenshotToAllure(driver, "ChangeCompanyName_Exception");
        }
    }
    @When("the user update the Notice details")
    public void the_user_update_the_notice_details() throws InterruptedException {
        try{
            Thread.sleep(2000);
            chequeBounceNoticePage.typeDebtDescription(ConfigReader.get("BriefDescription"));
        chequeBounceNoticePage.typeProofOfDebtDescription(ConfigReader.get("DescribeBusiness"));
        chequeBounceNoticePage.typeTotalDebtAmount(ConfigReader.get("DebtAmount"));
            chequeBounceNoticePage.clickNextButton();
            Thread.sleep(3000);
            chequeBounceNoticePage.typeChequeNumber(ConfigReader.get("ChequeNumber"));
        chequeBounceNoticePage.selectChequeDate();
        chequeBounceNoticePage.typeChequeAmount(ConfigReader.get("ChequeAmount"));
        chequeBounceNoticePage.typeBranchName(ConfigReader.get("BranchName"));
        chequeBounceNoticePage.typeDepositedBranch(ConfigReader.get("DepositedBranch"));
        chequeBounceNoticePage.selectMemoReturnDate();
        chequeBounceNoticePage.typeReasonForReturn(ConfigReader.get("ReasonForReturn"));
        chequeBounceNoticePage.clickNextButton();
            Thread.sleep(3000);
            chequeBounceNoticePage.typeOtherDetails(ConfigReader.get("CurrentUse"));
        logStep("✅ Change your company details");
        logToAllure("✅ Change your company name", "Updated details successfully");
        ScreenshotUtils.attachScreenshotToAllure(driver, "ChangeCompanyName");
        chequeBounceNoticePage.clickNextButton();
            Thread.sleep(2000);
        } catch (IllegalArgumentException | AssertionError e) {
        String msg = "❌ Exception during Change your company name: " + e.getMessage();
        logStep(msg);
        logToAllure("❌ Exception", msg);
        ScreenshotUtils.attachScreenshotToAllure(driver, "ChangeCompanyName_Exception");
    }
    }
}
