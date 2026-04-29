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
import pages.closepvtltdpage;
import utils.ConfigReader;
import utils.LoggerUtils;
import utils.ScreenshotUtils;

import java.awt.*;
import java.awt.datatransfer.DataFlavor;
import java.time.Duration;

import static utils.AllureLoggerUtils.logToAllure;

public class CloseyourPrivateLimitedCompany {

    WebDriver driver = Hooks.driver;
    closepvtltdpage close;
    Logger logger;
    WebDriverWait wait;
    SearchTicketStepdefs searchTicketsteps;
    LoginStepDefinitions loginSteps;



    public CloseyourPrivateLimitedCompany() {
        this.driver = Hooks.driver;
        try {
            this.close = new closepvtltdpage(Hooks.driver);
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

    @Given("the user redirect to Company details page of Close your pvt company")
    public void the_user_redirect_to_closepvtltdpage() throws InterruptedException {
        try {


            searchTicketsteps.theUserRedirectToTicketListPage();
            searchTicketsteps.theUserEnterTheTicket();
            searchTicketsteps.theUserClickTheSearchCTA();
            searchTicketsteps.theUserViewTheTicketPageByClickingOnTheLink();

            close.crossSaleNew();
            close.createservice(ConfigReader.get("Closepvt"));
            close.selectingtheservice();
            Thread.sleep(2000);
            close.createbutton();
            logger.info("Service creation initiated.");
            ScreenshotUtils.attachScreenshotToAllure(driver, "2_Service_Created");

            Thread.sleep(15000);
            close.recenttickets();
            Thread.sleep(4000);
            logger.info("Recent Tickets modal opened.");
            ScreenshotUtils.attachScreenshotToAllure(driver, "3_Recent_Tickets_Modal");

            try {
                WebElement firstTicket = driver.findElement(By.cssSelector("tr.ticket_index_tr ul.tcktdetails li a"));
                firstTicket.click();
                logger.info("Clicked first dynamic ticket link.");
                Thread.sleep(10000);
                ScreenshotUtils.attachScreenshotToAllure(driver, "4_Selected_Ticket_Page");

                close.copyurl.click();
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




            logStep("🌐 Redirecting the user to close pvt company page...");
            logger.info("Navigated to URL: {}", ConfigReader.get("closepvt"));
            logStep("✅ Field Details screen for Close your pvt company ");
            logToAllure("✅ The user Navigated to Close your pvt company ", "Close your pvt company redirected successfully");
            ScreenshotUtils.attachScreenshotToAllure(driver, "Close your pvt company redirected successfully");

        } catch (IllegalArgumentException | WebDriverException | AssertionError e) {
            String msg = "❌ Exception during Accessing the Customer view : " + e.getMessage();
            logger.error(msg);
            logStep(msg);
            logToAllure("❌ Exception", msg);
            ScreenshotUtils.attachScreenshotToAllure(driver, "Close your pvt company_Exception");
            throw e;
        }
    }

    @When("The user enters the details in closure form")
    public void The_user_enters_the_details_in_closure_form() {
        logStep("🏢 Entering Closure form details");
        logger.info("===== Closure Form Details Flow Started =====");

        try {
            logToAllure("Closure Form", "Entering company closure details");
            ScreenshotUtils.attachScreenshotToAllure(driver, "Closure form - entering details");
            Thread.sleep(2000);
            String bankIfsc = ConfigReader.get("bankifsc");
            String registration = ConfigReader.get("Roadstreet");
            String description = ConfigReader.get("descrip");
            Thread.sleep(2000);
            close.setCompany(bankIfsc);
            Thread.sleep(2000);
            close.setregistration(registration);
            Thread.sleep(2000);
            close.bank(description);
            Thread.sleep(2000);
            close.noradio();

            logStep("✅ Basic closure details entered");
            logToAllure("Closure Form - Basic Details", "Bank IFSC, registration, description entered");
            ScreenshotUtils.attachScreenshotToAllure(driver, "Closure form - basic details");

            close.nextbuttonnt();

            logStep("➡ Entering closure subtab information");
            close.annualandroc();
            Thread.sleep(2000);
            close.incometax();
            Thread.sleep(2000);
            close.setStampPaper();
            Thread.sleep(2000);
            close.closurenext();

            logStep("✅ Closure info completed successfully");
            logToAllure("Closure Form Subtab", "Annual, ROC, IT, Stamp Paper info entered");
            ScreenshotUtils.attachScreenshotToAllure(driver, "Closure info completed");

            logger.info("===== Closure Form Details Flow Completed =====");

        } catch (IllegalArgumentException | AssertionError | InterruptedException e) {
            logger.error("❌ Closure form details entry failed", e);
            ScreenshotUtils.attachScreenshotToAllure(driver, "Closure Form Failure");
            throw new RuntimeException("❌ Closure form details entry failed → " + e.getMessage(), e);
        }
    }


    @When("The user enters the company details")
    public void The_user_enters_the_company_details() {
        logStep("🏢 Entering Company Details for closure");
        logger.info("===== Closure Company Details Flow Started =====");

        try {
            logToAllure("Company Details", "Entering closure company details");
            ScreenshotUtils.attachScreenshotToAllure(driver, "Closure - Company Details");

            close.companyCin(ConfigReader.get("CompanyCIN"));
            Thread.sleep(2000);
            close.companyName(ConfigReader.get("CompanyName"));
            Thread.sleep(2000);
            close.companyEmail(ConfigReader.get("companyemail"));
            close.reason(ConfigReader.get("companynamemsme"));
            close.ROCOffice(ConfigReader.get("companyvaluemsme"));

            logStep("✅ Company details entered");
            logToAllure("Company Details", "CIN, Name, Email, Reason, ROC Office entered");
            ScreenshotUtils.attachScreenshotToAllure(driver, "Company details entered");

            close.companydetailsnext();

            logger.info("===== Closure Company Details Flow Completed =====");

        } catch (IllegalArgumentException | AssertionError | InterruptedException e) {
            logger.error("❌ Closure company details entry failed", e);
            ScreenshotUtils.attachScreenshotToAllure(driver, "Company Details Failure");
            throw new RuntimeException("❌ Closure company details entry failed → " + e.getMessage(), e);
        }
    }


    @When("the user enter the Director Details in close your pvt")
    public void The_user_enter_the_director_details() {
        logStep("👤 Entering Director 1 details for closure");
        logger.info("===== Closure Director 1 Flow Started =====");

        try {
            logToAllure("Director 1 Details", "Entering first director details");
            ScreenshotUtils.attachScreenshotToAllure(driver, "Director 1 details screen");
            Thread.sleep(2000);
            close.direct_1DIN(ConfigReader.get("din1"));
            Thread.sleep(2000);
            close.directorname(ConfigReader.get("directorName1"));
            close.fathername(ConfigReader.get("FatherName1"));
            close.director1_address(ConfigReader.get("Address1"));
            close.Director1_residence(ConfigReader.get("CurrentOccupation2"));
            close.director1_gender(ConfigReader.get("Gender"));

            logStep("✅ Director 1 details entered");
            logToAllure("Director 1 Details", "DIN, Name, Father, Address, Residence, Gender entered");
            ScreenshotUtils.attachScreenshotToAllure(driver, "Director 1 details entered");

            close.closurenext();

            logger.info("===== Closure Director 1 Flow Completed =====");

        } catch (IllegalArgumentException | AssertionError | InterruptedException e) {
            logger.error("❌ Closure Director 1 details entry failed", e);
            ScreenshotUtils.attachScreenshotToAllure(driver, "Director 1 Details Failure");
            throw new RuntimeException("❌ Closure Director 1 details entry failed → " + e.getMessage(), e);
        }
    }


    @When("the user enter the Second Director Details in close your pvt")
    public void The_user_enter_the_second_director_details() {
        logStep("👤 Entering Director 2 details for closure");
        logger.info("===== Closure Director 2 Flow Started =====");

        try {
            logToAllure("Director 2 Details", "Entering second director details");
            ScreenshotUtils.attachScreenshotToAllure(driver, "Director 2 details screen");
            Thread.sleep(2000);
            close.direct_2DIN(ConfigReader.get("din2"));
            close.director_2_name(ConfigReader.get("directorName2"));
            close.Dir_2fathername(ConfigReader.get("FatherName2"));
            close.director1_address(ConfigReader.get("Address2"));
            close.Director1_residence(ConfigReader.get("CurrentOccupation2"));
            close.director2_gender(ConfigReader.get("Director2Gender"));

            logStep("✅ Director 2 details entered");
            logToAllure("Director 2 Details", "DIN, Name, Father, Address, Residence, Gender entered");
            ScreenshotUtils.attachScreenshotToAllure(driver, "Director 2 details entered");

            close.director2next();

            logger.info("===== Closure Director 2 Flow Completed =====");

        } catch (IllegalArgumentException | AssertionError | InterruptedException e) {
            logger.error("❌ Closure Director 2 details entry failed", e);
            ScreenshotUtils.attachScreenshotToAllure(driver, "Director 2 Details Failure");
            throw new RuntimeException("❌ Closure Director 2 details entry failed → " + e.getMessage(), e);
        }
    }

    @When("the user enter the first shareholder in close your pvt")
    public void The_user_enter_the_first_shareholder_details() throws InterruptedException {

        logStep("👤 Entering first shareholder details");
        logger.info("===== FIRST SHAREHOLDER DETAILS ENTRY STARTED =====");

        try {
            Thread.sleep(2000);
            logger.info("📝 Entering shareholder name");
            close.shareholdername(ConfigReader.get("OldName"));
            ScreenshotUtils.attachScreenshotToAllure(driver, "First_Shareholder_Name");

            logger.info("📊 Entering shareholding value");
            close.sharehold(ConfigReader.get("Sharehold1"));

            logger.info("📈 Entering share percentage");
            close.sharepercentage(ConfigReader.get("Sharepercentage1"));

            logger.info("👨 Entering father name");
            close.sharefathername(ConfigReader.get("shareholderfathername"));

            logger.info("🏠 Entering address");
            close.shareaddress(ConfigReader.get("FullAddress"));

            logger.info("📍 Entering place of residence");
            close.shareplaceofresidence(ConfigReader.get("AddressLine1"));

            logger.info("⚧ Selecting gender");
            close.shareholder1gender(ConfigReader.get("Gender"));
            ScreenshotUtils.attachScreenshotToAllure(driver, "First_Shareholder_Details_Filled");

            logger.info("➡️ Clicking Next after first shareholder");
            close.director2next();

            logger.info("✅ First shareholder details entered successfully");
            ScreenshotUtils.attachScreenshotToAllure(driver, "First_Shareholder_Submitted");

        } catch (IllegalArgumentException | AssertionError | InterruptedException e) {
            logger.error("❌ Failed while entering first shareholder details", e);
            ScreenshotUtils.attachScreenshotToAllure(driver, "First_Shareholder_Error");
            throw e;
        }
    }

    @Then("the user enter second shareholder and board date")
    public void The_user_enter_second_shareholder_and_board_date() throws InterruptedException {

        logStep("👥 Entering second shareholder and board meeting details");
        logger.info("===== SECOND SHAREHOLDER DETAILS ENTRY STARTED =====");

        try {

            Thread.sleep(2000);
            logger.info("📝 Entering second shareholder name");
            close.shareholder2name(ConfigReader.get("NewName"));
            ScreenshotUtils.attachScreenshotToAllure(driver, "Second_Shareholder_Name");

            logger.info("📊 Entering second shareholding value");
            close.sharehold2(ConfigReader.get("Sharehold2"));

            logger.info("📈 Entering second share percentage");
            close.sharepercentage(ConfigReader.get("Sharepercentage2"));

            logger.info("👨 Entering second shareholder father name");
            close.sharefathername(ConfigReader.get("shareholderfathername2"));

            logger.info("🏠 Entering second shareholder address");
            close.shareaddress(ConfigReader.get("Address2"));

            logger.info("📍 Entering second shareholder place of residence");
            close.shareplaceofresidence(ConfigReader.get("InventorAddress"));

            logger.info("⚧ Selecting second shareholder gender");
            close.shareholder2_gender(ConfigReader.get("Gender"));
            ScreenshotUtils.attachScreenshotToAllure(driver, "Second_Shareholder_Details_Filled");

            logger.info("➡️ Clicking Next after second shareholder");
            close.director2next();

            Thread.sleep(2000);
            logger.info("📅 Entering board meeting date");
            close.Boardmeeting(ConfigReader.get("Boarddate"));

            logger.info("📄 Entering SOA date");
            close.soadate(ConfigReader.get("SOAdate"));
            ScreenshotUtils.attachScreenshotToAllure(driver, "Board_and_SOA_Dates_Filled");

            logger.info("➡️ Clicking Next after board & SOA dates");
            close.director2next();

            Thread.sleep(2000);
            logger.info("✅ Close your pvt company successfully completed");
            ScreenshotUtils.attachScreenshotToAllure(driver, "Close your pvt company successfully completed");

        } catch (IllegalArgumentException | AssertionError | InterruptedException e) {
            logger.error("❌ Failed while entering second shareholder or board meeting details", e);
            ScreenshotUtils.attachScreenshotToAllure(driver, "Second_Shareholder_Error");
            throw e;
        }
    }

}


