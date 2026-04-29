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
import pages.Moupage;
import utils.ConfigReader;
import utils.LoggerUtils;
import utils.ScreenshotUtils;

import java.awt.*;
import java.awt.datatransfer.DataFlavor;
import java.time.Duration;

import static utils.AllureLoggerUtils.logToAllure;

public class MOUdef {

    WebDriver driver = Hooks.driver;
    Moupage moupage;
    Logger logger;
    WebDriverWait wait;
    SearchTicketStepdefs searchTicketsteps;
    LoginStepDefinitions loginSteps;



    public MOUdef() {
        this.driver = Hooks.driver;
        try {
            this.moupage = new Moupage(Hooks.driver);
        } catch (AWTException e) {
            throw new RuntimeException(e);
        }
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        this.logger = LoggerUtils.getLogger(getClass());
        this.searchTicketsteps = new SearchTicketStepdefs(Hooks.driver);
        this.loginSteps = new LoginStepDefinitions();
    }

    @Step("{message}")
    public void logStep(String message) {
    }

    @Given("the user redirect to Memorandum of Understanding services")
    public void MOUservices() throws InterruptedException {

        try {

            searchTicketsteps.theUserRedirectToTicketListPage();
            searchTicketsteps.theUserEnterTheTicket();
            searchTicketsteps.theUserClickTheSearchCTA();
            searchTicketsteps.theUserViewTheTicketPageByClickingOnTheLink();

            moupage.crossSaleNew();
            moupage.createservice(ConfigReader.get("Mouservice"));
            moupage.selectingtheservice();
            Thread.sleep(2000);
            moupage.createbutton();
            logger.info("Service creation initiated.");
            ScreenshotUtils.attachScreenshotToAllure(driver, "2_Service_Created");

            Thread.sleep(15000);
            moupage.recenttickets();
            Thread.sleep(4000);
            logger.info("Recent Tickets modal opened.");
            ScreenshotUtils.attachScreenshotToAllure(driver, "3_Recent_Tickets_Modal");

            try {
                WebElement firstTicket = driver.findElement(By.cssSelector("tr.ticket_index_tr ul.tcktdetails li a"));
                firstTicket.click();
                logger.info("Clicked first dynamic ticket link.");
                Thread.sleep(10000);
                ScreenshotUtils.attachScreenshotToAllure(driver, "4_Selected_Ticket_Page");

                moupage.copyurl.click();
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
            logStep("🌐 Redirecting the user to Memorandum of Understanding page...");
            logStep("✅ Field Details screen for Memorandum of Understanding page");
            logToAllure("✅ The user Navigated to Memorandum of Understanding page", "Memorandum of Understanding page redirected successfully");
            ScreenshotUtils.attachScreenshotToAllure(driver, "Memorandum of Understanding page redirected successfully");

        } catch (AssertionError e) {
            String msg = "❌ Exception during Accessing the Customer view : " + e.getMessage();
            logger.error(msg);
            logStep(msg);
            logToAllure("❌ Exception", msg);
            ScreenshotUtils.attachScreenshotToAllure(driver, "Commercial rental and lease agreement page_Exception");
            throw e;
        }
    }

    @When("The user enters the Basic Details of Parties")
    public void basicdtails() throws InterruptedException {
        logStep("🏢 Starting Basic details of Parties");
        logger.info("===== MOU Basic Details Update Flow Started =====");

        try {
            Thread.sleep(2000);
            String party1Name = ConfigReader.get("directorName2");
            String party1Address = ConfigReader.get("Address1");

            logger.info("Entering Party 1 Details: Name [{}], Address [{}]", party1Name, party1Address);
            moupage.nameofParty1(party1Name);
            moupage.registeredAddress(party1Address);

            moupage.authorizedSignatory(ConfigReader.get("AuthorizedSignatoryName"));
            moupage.natureOfEntity(ConfigReader.get("naturebusiness"));
            moupage.businessActivity(ConfigReader.get("DescribeBusiness"));

            ScreenshotUtils.attachScreenshotToAllure(driver, "MOU_Party1_Details_Entered");

            String party2Name = ConfigReader.get("directorName1");
            logger.info("Entering Party 2 Details: Name [{}]", party2Name);

            moupage.nameofParty2(party2Name);
            moupage.registeredaddress2(ConfigReader.get("Address2"));
            moupage.AuthorizedSignatory2(ConfigReader.get("AuthorizedSignatoryDesignation"));
            moupage.natureofEntity2(ConfigReader.get("WorkNature"));
            moupage.briefDescription2(ConfigReader.get("WorkStatus"));

            logStep("✅ All Party details populated from Config");
            logger.info("Successfully entered all basic details for Party 1 and Party 2");
            ScreenshotUtils.attachScreenshotToAllure(driver, "MOU_Party2_Details_Entered");

            Thread.sleep(2000);
            logger.info("Attempting to click the Close Button...");
            moupage.closeButton();

            Thread.sleep(2000);
            logStep("🎉 Basic Details section completed and closed");
            ScreenshotUtils.attachScreenshotToAllure(driver, "MOU_Section_Closed");

        } catch (AssertionError e) {
            String errorMsg = "❌ Failed to complete Basic Details of Parties: " + e.getMessage();
            logger.error(errorMsg, e);
            logStep(errorMsg);
            ScreenshotUtils.attachScreenshotToAllure(driver, "MOU_BasicDetails_Failure");
            throw e; // Re-throwing ensures the test fails in Allure/Jenkins
        }
    }

    @When("Updating the background of Mou service")
    public void background() throws InterruptedException {
        logStep("📝 Updating MOU Background details");
        logger.info("===== MOU Background Flow Started =====");

        try {
            String objective = ConfigReader.get("AddObjective");
            if (objective == null) throw new RuntimeException("Missing config key: AddObjective");

            logger.info("Entering Objective: {}", objective);
            moupage.objective(objective);
            Thread.sleep(1000); // Reduced slightly for efficiency, keeping your structure

            String workNature = ConfigReader.get("WorkNature");
            String oldName = ConfigReader.get("OldName");

            logger.info("Entering Collaboration [{}] and Binding [{}]", workNature, oldName);
            moupage.mouforcollaboration(workNature);
            Thread.sleep(1000);

            moupage.mouforbinding(oldName);

            logger.info("Entering Relationship and Intended Outcome details");
            moupage.relationship(ConfigReader.get("Reason"));
            moupage.intendedoutcome(ConfigReader.get("ApplicantType"));

            ScreenshotUtils.attachScreenshotToAllure(driver, "MOU_Background_Fields_Filled");
            logStep("✅ All background fields populated successfully");

            Thread.sleep(2000);
            logger.info("Clicking Close button on Background section");
            moupage.closeButton();

            Thread.sleep(2000);
            logStep("🎉 MOU Background update completed");
            ScreenshotUtils.attachScreenshotToAllure(driver, "MOU_Background_Section_Closed");

        } catch (AssertionError e) {
            String errorMsg = "❌ Error in MOU Background update: " + e.getMessage();
            logger.error(errorMsg, e);
            logStep(errorMsg);
            ScreenshotUtils.attachScreenshotToAllure(driver, "MOU_Background_Failure");
            throw e;
        }
    }

    @When("Updating the Scope of Mou service")
    public void Scope() throws InterruptedException {
        logStep("🎯 Updating MOU Scope, Roles, and Timelines");
        logger.info("===== MOU Scope Flow Started =====");

        try {
            String undertaking = ConfigReader.get("CurrentOccupation1");
            String roles = ConfigReader.get("Role");

            if (undertaking == null || roles == null) {
                throw new RuntimeException("❌ Required Config keys (CurrentOccupation1 or Role) are missing!");
            }

            logger.info("Entering Party Undertake: [{}]", undertaking);
            moupage.partyundertake(undertaking);

            logger.info("Entering Roles and Responsibilities: [{}]", roles);
            moupage.rolesandresponsibility(roles);

            String timelineVal = ConfigReader.get("pageLoadTimeout");
            logger.info("Entering Timelines using config value: [{}]", timelineVal);
            moupage.timelines(timelineVal);

            ScreenshotUtils.attachScreenshotToAllure(driver, "MOU_Scope_Fields_Filled");

            Thread.sleep(2000);
            logger.info("Clicking Close button on Scope section");
            moupage.closeButton();

            Thread.sleep(2000);
            logStep("✅ MOU Scope update completed successfully");
            ScreenshotUtils.attachScreenshotToAllure(driver, "MOU_Scope_Section_Closed");

        } catch (AssertionError e) {
            String errorMsg = "❌ Error in MOU Scope update: " + e.getMessage();
            logger.error(errorMsg, e);
            logStep(errorMsg);
            ScreenshotUtils.attachScreenshotToAllure(driver, "MOU_Scope_Failure");
            throw e;
        }
    }

    @When("Updating the Financial Terms of Mou service")
    public void financial() throws InterruptedException {
        logStep("💰 Updating MOU Financial Terms");
        logger.info("===== MOU Financial Terms Flow Started =====");

        try {
            Thread.sleep(2000);

            String contribution = ConfigReader.get("CurrentOccupation1");
            String amount = ConfigReader.get("DebtAmount");

            logger.info("Entering Financial Contribution: [{}]", contribution);
            moupage.financialcontributions(contribution);

            logger.info("Entering Amount and Purpose: [{}]", amount);
            moupage.specifyamountandpurpose(amount);

            String revenue = ConfigReader.get("ChequeNumber");
            String reimburse = ConfigReader.get("BranchName");

            logger.info("Entering Revenue Sharing: [{}] and Reimbursements: [{}]", revenue, reimburse);
            moupage.revenuesharing(revenue);
            moupage.reimbursements(reimburse);

            String invoiceDetails = ConfigReader.get("AnotherWork");
            logger.info("Entering Invoice details: [{}]", invoiceDetails);
            moupage.anyinvoices(invoiceDetails);

            ScreenshotUtils.attachScreenshotToAllure(driver, "MOU_Financial_Terms_Filled");

            logger.info("Clicking Close button on Financial section");
            moupage.closeButton();

            Thread.sleep(2000);
            logStep("✅ MOU Financial Terms update completed successfully");
            ScreenshotUtils.attachScreenshotToAllure(driver, "MOU_Financial_Section_Closed");

        } catch (AssertionError e) {
            String errorMsg = "❌ Error in MOU Financial Terms update: " + e.getMessage();
            logger.error(errorMsg, e);
            logStep(errorMsg);
            ScreenshotUtils.attachScreenshotToAllure(driver, "MOU_Financial_Failure");
            throw e;
        }
    }

    @Then("user updates the term and termination")
    public void term() throws InterruptedException {
        logStep("📅 Updating MOU Term and Termination details");
        logger.info("===== MOU Term & Termination Flow Started =====");

        try {
            Thread.sleep(2000);

            String effectiveDate = ConfigReader.get("RocUpToDate");
            String duration = ConfigReader.get("DurationYears");

            logger.info("Entering Effective Date: [{}]", effectiveDate);
            moupage.effectivedate(effectiveDate);

            logger.info("Entering Duration/Validity: [{}]", duration);
            moupage.durationvalidity(duration);

            String renewal = ConfigReader.get("CurrentUse");
            String termination = ConfigReader.get("ZipCode");

            logger.info("Entering Renewal details: [{}]", renewal);
            moupage.renewalpossible(renewal);

            logger.info("Entering Termination details (Key: ZipCode): [{}]", termination);
            moupage.partyterminate(termination);

            ScreenshotUtils.attachScreenshotToAllure(driver, "MOU_Term_Termination_Filled");

            logger.info("Clicking Close button on Term & Termination section");
            moupage.closeButton();

            Thread.sleep(2000);
            logStep("✅ MOU Term and Termination update completed successfully");
            ScreenshotUtils.attachScreenshotToAllure(driver, "MOU_Term_Section_Closed");

        } catch (AssertionError e) {
            String errorMsg = "❌ Error in MOU Term & Termination update: " + e.getMessage();
            logger.error(errorMsg, e);
            logStep(errorMsg);
            ScreenshotUtils.attachScreenshotToAllure(driver, "MOU_Term_Failure");
            throw e;
        }
    }

   }




