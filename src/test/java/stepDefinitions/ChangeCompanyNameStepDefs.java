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
import pages.ChangeCompanyNamePage;
import utils.*;

import java.awt.*;
import java.awt.datatransfer.DataFlavor;

import static utils.AllureLoggerUtils.logToAllure;

public class ChangeCompanyNameStepDefs {
    WebDriver driver = Hooks.driver;
    ChangeCompanyNamePage changeCompanyNamePage;
    Logger logger;
    WaitUtils wait;
    SearchTicketStepdefs searchTicketsteps;
    LoginStepDefinitions loginSteps;


    public ChangeCompanyNameStepDefs() {
        this.driver = Hooks.driver;
        this.changeCompanyNamePage = new ChangeCompanyNamePage(Hooks.driver);
        this.logger = LoggerUtils.getLogger(getClass());
        this.wait = new WaitUtils(driver,30);
        this.searchTicketsteps = new SearchTicketStepdefs(Hooks.driver);
        this.loginSteps = new LoginStepDefinitions();
    }

    @Step("{message}")
    public void logStep(String message) {
        // Allure will log this message as a step
    }

    @Given("the user redirect to change your company name service")
    public void the_user_redirect_to_change_your_company_name_service() throws InterruptedException {
        try {

            searchTicketsteps.theUserRedirectToTicketListPage();
            searchTicketsteps.theUserEnterTheTicket();
            searchTicketsteps.theUserClickTheSearchCTA();
            searchTicketsteps.theUserViewTheTicketPageByClickingOnTheLink();

            changeCompanyNamePage.crossSaleNew();
            changeCompanyNamePage.createservice(ConfigReader.get("Changeyourcompanyname"));
            changeCompanyNamePage.selectingtheservice();
            Thread.sleep(2000);
            changeCompanyNamePage.createbutton();
            logger.info("Service creation initiated.");
            ScreenshotUtils.attachScreenshotToAllure(driver, "2_Service_Created");

            Thread.sleep(15000);
            changeCompanyNamePage.recenttickets();
            Thread.sleep(4000);
            logger.info("Recent Tickets modal opened.");
            ScreenshotUtils.attachScreenshotToAllure(driver, "3_Recent_Tickets_Modal");

            try {
                WebElement firstTicket = driver.findElement(By.cssSelector("tr.ticket_index_tr ul.tcktdetails li a"));
                firstTicket.click();
                logger.info("Clicked first dynamic ticket link.");
                Thread.sleep(10000);
                ScreenshotUtils.attachScreenshotToAllure(driver, "4_Selected_Ticket_Page");

                changeCompanyNamePage.copyurl.click();
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




            logStep("🌐 Navigating to the Change your company name service page...");
            logger.info("Navigated to URL: {}", ConfigReader.get("ChangeCompanyNameURL"));
            logStep("✅ Change your company name");
            logToAllure("✅ Change your company name page redirection Validation", "Page redirected successfully");
            ScreenshotUtils.attachScreenshotToAllure(driver, "ChangeYourCompanyName");
        } catch (IllegalArgumentException | WebDriverException | AssertionError e) {
            String msg = "❌ Exception during Change the Official Address of Your Company (Within the State) service page: " + e.getMessage();
            logger.error(msg);
            logStep(msg);
            logToAllure("❌ Exception", msg);
            ScreenshotUtils.attachScreenshotToAllure(driver, "ChangeYourCompanyName_Exception");
            throw e;
        }
    }
    @When("the user update the company details")
    public void the_user_update_the_company_details() {
        try{
            changeCompanyNamePage.typeCompanyName(ConfigReader.get("CompanyName"));
            changeCompanyNamePage.clickNextButton();
            changeCompanyNamePage.typeCompanyCIN(ConfigReader.get("CompanyCIN"));
            logStep("✅ Change your company details");
            logToAllure("✅ Change your company name", "Updated details successfully");
            ScreenshotUtils.attachScreenshotToAllure(driver, "ChangeCompanyName");
            changeCompanyNamePage.clickNextButton();
        } catch (IllegalArgumentException | WebDriverException | AssertionError e) {
            String msg = "❌ Exception during Change your company name: " + e.getMessage();
            logStep(msg);
            logToAllure("❌ Exception", msg);
            ScreenshotUtils.attachScreenshotToAllure(driver, "ChangeCompanyName_Exception");
        }
    }
    @When("the user update the new company name details")
    public void the_user_update_the_new_company_name_details() {
        try{
            changeCompanyNamePage.typeProposedName1(ConfigReader.get("SearchCompanyName"));
            changeCompanyNamePage.typeProposedName2(ConfigReader.get("CompanyName1"));
            changeCompanyNamePage.typeSignificanceName(ConfigReader.get("SignificanceDesc"));
            changeCompanyNamePage.typeReasonForChange(ConfigReader.get("Reason"));
            changeCompanyNamePage.typeTmAppliedForProposedName(ConfigReader.get("CurrentUse"));
            changeCompanyNamePage.typeTmName(ConfigReader.get("SearchCompanyName"));
            changeCompanyNamePage.typeChangeObjective(ConfigReader.get("CurrentUse"));
            changeCompanyNamePage.typeNewObjectProposed(ConfigReader.get("ReasonForResignation"));
            logStep("✅ Update LLP Details");
            logToAllure("✅ LLP Details", "Update LLP details successfully");
            ScreenshotUtils.attachScreenshotToAllure(driver, "LLPDetailsWithinState");
            changeCompanyNamePage.clickNextButton();
        } catch (IllegalArgumentException | WebDriverException | AssertionError e) {
            String msg = "❌ Exception during Change the LLP Details within the state: " + e.getMessage();
            logStep(msg);
            logToAllure("❌ Exception", msg);
            ScreenshotUtils.attachScreenshotToAllure(driver, "LLPDetailsWithinState_Exception");
        }
    }
    @When("the user update the other information")
    public void the_user_update_the_other_information() {
        try{
            changeCompanyNamePage.typeShareTransfer(ConfigReader.get("CurrentUse"));
            changeCompanyNamePage.typeRocAnnualFiling(ConfigReader.get("CurrentUse"));
            changeCompanyNamePage.typeActiveDIN(ConfigReader.get("CurrentUse"));
            changeCompanyNamePage.typeActiveDSC(ConfigReader.get("DSCAvailability"));
            logStep("✅ Update LLP Details");
            logToAllure("✅ LLP Details", "Update LLP details successfully");
            ScreenshotUtils.attachScreenshotToAllure(driver, "LLPDetailsWithinState");
            changeCompanyNamePage.clickNextButton();
        } catch (IllegalArgumentException | WebDriverException | AssertionError e) {
            String msg = "❌ Exception during Change the LLP Details within the state: " + e.getMessage();
            logStep(msg);
            logToAllure("❌ Exception", msg);
            ScreenshotUtils.attachScreenshotToAllure(driver, "LLPDetailsWithinState_Exception");
        }
    }

}
