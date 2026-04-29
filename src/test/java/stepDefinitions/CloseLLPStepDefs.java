package stepDefinitions;

import hooks.Hooks;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import io.qameta.allure.Step;
import org.apache.logging.log4j.Logger;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.WebElement;
import pages.CloseLLPServicePage;
import utils.ConfigReader;
import utils.LoggerUtils;
import utils.ScreenshotUtils;
import utils.WaitUtils;

import java.awt.*;
import java.awt.datatransfer.DataFlavor;

import static utils.AllureLoggerUtils.logToAllure;

public class CloseLLPStepDefs {
    WebDriver driver = Hooks.driver;
    CloseLLPServicePage closeLLPServicePage;
    Logger logger;
    WaitUtils wait;
    SearchTicketStepdefs searchTicketsteps;
    LoginStepDefinitions loginSteps;

    public CloseLLPStepDefs() {
        this.driver = Hooks.driver;
        this.closeLLPServicePage = new CloseLLPServicePage(Hooks.driver);
        this.logger = LoggerUtils.getLogger(getClass());
        this.wait = new WaitUtils(driver,30);
        this.searchTicketsteps = new SearchTicketStepdefs(Hooks.driver);
        this.loginSteps = new LoginStepDefinitions();

    }

    @Step("{message}")
    public void logStep(String message) {
        // Allure will log this message as a step
    }
    @Given("the user redirect to close your LLP service")
    public void the_user_redirect_to_close_your_llp_service() throws InterruptedException {
        try {

            searchTicketsteps.theUserRedirectToTicketListPage();
            searchTicketsteps.theUserEnterTheTicket();
            searchTicketsteps.theUserClickTheSearchCTA();
            searchTicketsteps.theUserViewTheTicketPageByClickingOnTheLink();

            closeLLPServicePage.crossSaleNew();
            closeLLPServicePage.createservice(ConfigReader.get("CloseyourLLP"));
            closeLLPServicePage.selectingtheservice();
            Thread.sleep(2000);
            closeLLPServicePage.createbutton();
            logger.info("Service creation initiated.");
            ScreenshotUtils.attachScreenshotToAllure(driver, "2_Service_Created");

            Thread.sleep(15000);
            closeLLPServicePage.recenttickets();
            Thread.sleep(4000);
            logger.info("Recent Tickets modal opened.");
            ScreenshotUtils.attachScreenshotToAllure(driver, "3_Recent_Tickets_Modal");

            try {
                WebElement firstTicket = driver.findElement(By.cssSelector("tr.ticket_index_tr ul.tcktdetails li a"));
                firstTicket.click();
                logger.info("Clicked first dynamic ticket link.");
                Thread.sleep(10000);
                ScreenshotUtils.attachScreenshotToAllure(driver, "4_Selected_Ticket_Page");

                closeLLPServicePage.copyurl.click();
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




            logStep("🌐 Navigating to the Close your Limited Liability Partnership service page...");
            logStep("✅ Close your Limited Liability Partnership");
            logToAllure("✅ Close your Limited Liability Partnership page redirection Validation", "Page redirected successfully");
            ScreenshotUtils.attachScreenshotToAllure(driver, "CloseLLP");
        } catch (IllegalArgumentException | WebDriverException | AssertionError e) {
            String msg = "❌ Exception during Close your Limited Liability Partnership service page: " + e.getMessage();
            logger.error(msg);
            logStep(msg);
            logToAllure("❌ Exception", msg);
            ScreenshotUtils.attachScreenshotToAllure(driver, "CloseLLP_Exception");
            throw e;
        }
    }
    @When("the user update the LLP Info Details")
    public void the_user_update_the_llp_info_details() {
        try{
            closeLLPServicePage.typeLLPName(ConfigReader.get("LLPName"));
            closeLLPServicePage.typeLLPIN(ConfigReader.get("LLPIN"));
            logStep("✅ Change LLP Info details");
            logToAllure("✅ Change LLP Info", "Updated details successfully");
            ScreenshotUtils.attachScreenshotToAllure(driver, "ChangeLLPInfoDetails");
            Thread.sleep(5000);
            closeLLPServicePage.clickNextButton();
        } catch (IllegalArgumentException | AssertionError | InterruptedException e) {
            String msg = "❌ Exception during Change LLP Info: " + e.getMessage();
            logStep(msg);
            logToAllure("❌ Exception", msg);
            ScreenshotUtils.attachScreenshotToAllure(driver, "ChangeLLPInfoDetails_Exception");
        }
    }
    @When("the user update the LLP Closure Information")
    public void the_user_update_the_llp_closure_information() {
        try {
            String currentUse = ConfigReader.get("CurrentUse");
            String branchName = ConfigReader.get("BranchName");
            String essentialFeature = ConfigReader.get("EssentialFeature");

            closeLLPServicePage.typeLastOneStatus(currentUse);
            closeLLPServicePage.selectLiabilityAsset(currentUse);
            closeLLPServicePage.typeBankAccountStatus(branchName);
            closeLLPServicePage.typeAllRegistrationsStatus(essentialFeature);

            closeLLPServicePage.clickNextButton();

            /*closeLLPServicePage.selectAnnualFilingsStatus(currentUse);
            closeLLPServicePage.selectIncomeTaxReturnStatus(currentUse);
            closeLLPServicePage.selectStampPaperStatus(currentUse);

            closeLLPServicePage.clickNextButton();*/
            Thread.sleep(2000);
            logStep("✅ LLP Closer details updated successfully");
            logToAllure("✅ LLP Closer", "Updated details successfully");
            ScreenshotUtils.attachScreenshotToAllure(driver, "LLP_Closer_Success");

        } catch (IllegalArgumentException | AssertionError | InterruptedException e) {
            String msg = "❌ Exception during LLP Closer flow";
            logStep(msg);
            logToAllure("❌ Exception", msg + "\n" + e);
            ScreenshotUtils.attachScreenshotToAllure(driver, "LLP_Closer_Exception");
        }

    }
}
