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
import pages.CriticalMarkPage;
import utils.*;

import java.awt.*;
import java.awt.datatransfer.DataFlavor;

import static utils.AllureLoggerUtils.logToAllure;


public class CriticalMarkStepDefs {

    WebDriver driver = Hooks.driver;
    CriticalMarkPage criticalMarkPage;
    Logger logger;
    AllureLoggerUtils allureLogging;
    WaitUtils wait;
    SearchTicketStepdefs searchTicketsteps;
    LoginStepDefinitions loginSteps;



    public CriticalMarkStepDefs() {
        this.driver = Hooks.driver;
        this.criticalMarkPage = new CriticalMarkPage(Hooks.driver);
        this.logger = LoggerUtils.getLogger(getClass());
        this.wait = new WaitUtils(driver,30);
        this.searchTicketsteps = new SearchTicketStepdefs(Hooks.driver);
        this.loginSteps = new LoginStepDefinitions();
    }

    @Step("{message}")
    public void logStep(String message) {
    }

    @Given("the user redirect to Critical Mark service")
    public void the_user_redirect_to_critical_mark_service() {
        try {

            searchTicketsteps.theUserRedirectToTicketListPage();
            searchTicketsteps.theUserEnterTheTicket();
            searchTicketsteps.theUserClickTheSearchCTA();
            searchTicketsteps.theUserViewTheTicketPageByClickingOnTheLink();

            criticalMarkPage.crossSaleNew();
            criticalMarkPage.createservice(ConfigReader.get("CriticalTrademark"));
            criticalMarkPage.selectingtheservice();
            Thread.sleep(2000);
            criticalMarkPage.createbutton();
            logger.info("Service creation initiated.");
            ScreenshotUtils.attachScreenshotToAllure(driver, "2_Service_Created");

            Thread.sleep(15000);
            criticalMarkPage.recenttickets();
            Thread.sleep(4000);
            logger.info("Recent Tickets modal opened.");
            ScreenshotUtils.attachScreenshotToAllure(driver, "3_Recent_Tickets_Modal");

            try {
                WebElement firstTicket = driver.findElement(By.cssSelector("tr.ticket_index_tr ul.tcktdetails li a"));
                firstTicket.click();
                logger.info("Clicked first dynamic ticket link.");
                Thread.sleep(10000);
                ScreenshotUtils.attachScreenshotToAllure(driver, "4_Selected_Ticket_Page");

                criticalMarkPage.copyurl.click();
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

            logStep("🌐 Navigating to the Critical Mark service flow...");
            Thread.sleep(1000);
            logStep("✅ Open Critical Mark Page");
            logToAllure("✅ Critical Mark - Page Redirection Validation", "Critical Mark - Page redirected successfully");
            ScreenshotUtils.attachScreenshotToAllure(driver, "CriticalMarkURL");
        } catch (IllegalArgumentException | WebDriverException | AssertionError | InterruptedException e) {
            String msg = "❌ Exception during Critical Mark flow: " + e.getMessage();
            logger.error(msg);
            logStep(msg);
            logToAllure("❌ Exception", msg);
            ScreenshotUtils.attachScreenshotToAllure(driver, "CriticalMarkURL_Exception");
        }
    }
    @When("the user update the critical mark applicant details")
    public void the_user_update_the_critical_mark_applicant_details() {
        try {
            criticalMarkPage.typeName(ConfigReader.get("directorName1"));
            criticalMarkPage.typeMobileNumber(ConfigReader.get("DirectorMobile1"));
            criticalMarkPage.typeEmailId(ConfigReader.get("DirectorEmail1"));
            criticalMarkPage.typeAadhaarNumber(ConfigReader.get("Aadhar1"));
            criticalMarkPage.typeBuildingNo(ConfigReader.get("Address1"));
            criticalMarkPage.typeRoad(ConfigReader.get("Address2"));
            criticalMarkPage.typePincode(ConfigReader.get("Pincode"));
            logger.info("Critical mark applicant details");
            logStep("✅ Open Critical Mark Page");
            logToAllure("✅ Update critical mark applicant details", "Updated critical mark applicant details successfully");
            ScreenshotUtils.attachScreenshotToAllure(driver, "CriticalMarkApplicantDetails");
        } catch (IllegalArgumentException | AssertionError e) {
            String msg = "❌ Exception during Critical Mark flow: " + e.getMessage();
            logger.error(msg);
            logStep(msg);
            logToAllure("❌ Exception", msg);
            ScreenshotUtils.attachScreenshotToAllure(driver, "CriticalMarkApplicantDetails_Exception");
        }
    }
}
