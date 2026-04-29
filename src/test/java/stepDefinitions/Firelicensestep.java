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
import pages.Firelicensepage;
import utils.ConfigReader;
import utils.LoggerUtils;
import utils.ScreenshotUtils;

import java.awt.*;
import java.awt.datatransfer.DataFlavor;
import java.time.Duration;

import static utils.AllureLoggerUtils.logToAllure;

public class Firelicensestep {

    WebDriver driver = Hooks.driver;
    Firelicensepage fire;
    Logger logger;
    WebDriverWait wait;
    SearchTicketStepdefs searchTicketsteps;
    LoginStepDefinitions loginSteps;


    public Firelicensestep() throws AWTException {
        this.driver = Hooks.driver;
        this.fire = new Firelicensepage(Hooks.driver);
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        this.logger = LoggerUtils.getLogger(getClass());
        this.searchTicketsteps = new SearchTicketStepdefs(Hooks.driver);
        this.loginSteps = new LoginStepDefinitions();
    }

    @Step("{message}")
    public void logStep(String message) {
    }

    @Given("the user redirect to Fire and Factory License")
    public void fireandfactory() throws InterruptedException{

        try {

            searchTicketsteps.theUserRedirectToTicketListPage();
            searchTicketsteps.theUserEnterTheTicket();
            searchTicketsteps.theUserClickTheSearchCTA();
            searchTicketsteps.theUserViewTheTicketPageByClickingOnTheLink();

            fire.crossSaleNew();
            fire.createservice(ConfigReader.get("Fireandfactory"));
            fire.selectingtheservice();
            Thread.sleep(2000);
            fire.createbutton();
            logger.info("Service creation initiated.");
            ScreenshotUtils.attachScreenshotToAllure(driver, "2_Service_Created");

            Thread.sleep(15000);
            fire.recenttickets();
            Thread.sleep(4000);
            logger.info("Recent Tickets modal opened.");
            ScreenshotUtils.attachScreenshotToAllure(driver, "3_Recent_Tickets_Modal");

            try {
                WebElement firstTicket = driver.findElement(By.cssSelector("tr.ticket_index_tr ul.tcktdetails li a"));
                firstTicket.click();
                logger.info("Clicked first dynamic ticket link.");
                Thread.sleep(10000);
                ScreenshotUtils.attachScreenshotToAllure(driver, "4_Selected_Ticket_Page");

                fire.copyurl.click();
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




            logStep("🌐 Redirecting the user to the Fire and Factory License page...");
            Thread.sleep(2000);
            logStep("✅ Field Details screen for Fire and Factory License page");
            logToAllure("✅ The user Navigated to Fire and Factory License page", "Fire and Factory License page redirected successfully");
            ScreenshotUtils.attachScreenshotToAllure(driver, "Fire and Factory License page redirected successfully");

        } catch (IllegalArgumentException | WebDriverException | AssertionError e) {
            String msg = "❌ Exception during Accessing the Customer view : " + e.getMessage();
            logger.error(msg);
            logStep(msg);
            logToAllure("❌ Exception", msg);
            ScreenshotUtils.attachScreenshotToAllure(driver, "Fire and Factory License page_Exception");
            throw e;
        }
    }

    @When("the user enters the Company details of Fire and Factory License")
    public void companydetailsfire() throws InterruptedException {
        logger.info("===== Starting Fire & Factory: Company Details Flow =====");
        try {
            Thread.sleep(2000);
            logStep("📝 Entering Company Name");
            fire.companyName(ConfigReader.get("CompanyName"));

//            logStep("🔡 Selecting Company Type");
//          fire.companytype(ConfigReader.get("firecompanytype"));

            logStep("📧 Entering Contact Info: Email & Mobile");
            fire.emailID1(ConfigReader.get("DirectorEmail1"));
            fire.mobileNumber(ConfigReader.get("DirectorMobile1"));

            logStep("📍 Entering Address and CIN");
            fire.address1(ConfigReader.get("Address1"));
            fire.cinNumber(ConfigReader.get("CompanyCIN"));

            ScreenshotUtils.attachScreenshotToAllure(driver, "Company_Details_Filled");

            logStep("🔘 Clicking Company Submit Button");
            fire.companybutton();

            logger.info("✅ Company details submitted successfully.");
        } catch (IllegalArgumentException | AssertionError e) {
            logger.error("❌ Error in companydetailsfire: " + e.getMessage());
            ScreenshotUtils.attachScreenshotToAllure(driver, "Error_CompanyDetails");
            throw e;
        }
        Thread.sleep(2000);
    }

    @When("the user enters the authorised person details for Fire and Factory License")
    public void authdetails() throws InterruptedException {
        logger.info("===== Starting Fire & Factory: Authorised Person Flow =====");
        Thread.sleep(2000);
        try {
            logStep("👤 Entering Authorised Person Name");
            fire.authorizedname(ConfigReader.get("directorName1"));

            logStep("📧 Entering Authorised Email");
            fire.emailID2(ConfigReader.get("DirectorEmail2"));

            logStep("🏠 Entering Authorised Address");
            fire.address2(ConfigReader.get("Address1"));

            ScreenshotUtils.attachScreenshotToAllure(driver, "Auth_Details_Filled");

            logStep("🔘 Clicking Authorised Button");
            fire.authButton();

            logger.info("✅ Authorised person details submitted.");
        } catch (AssertionError e) {
            logger.error("❌ Error in authdetails: " + e.getMessage());
            ScreenshotUtils.attachScreenshotToAllure(driver, "Error_AuthDetails");
            throw e;
        }
        Thread.sleep(2000);
    }

    @Then("the user upload documents for Fire and Factory License")
    public void uploaddocuments() throws InterruptedException {
        logger.info("===== Starting Fire & Factory: Document Upload Flow =====");
        Thread.sleep(2000);
        try {
//            logStep("📂 Uploading Aadhar Front");
//            fire.selectAadharFront.sendKeys(ConfigReader.get("AadharUploadPic1"));
//            Thread.sleep(2000);
//            logStep("📂 Uploading Aadhar Back");
//            fire.selectAadharBack.sendKeys(ConfigReader.get("AadharUploadBack"));
//            Thread.sleep(2000);
//            logStep("📂 Uploading PAN Card");
//            fire.uploadPAN.sendKeys(ConfigReader.get("PAN1"));
//            ScreenshotUtils.attachScreenshotToAllure(driver, "Docs_Uploaded_Before_Close");
            logStep("✖️ Clicking Close Button");
            fire.closeButton();
            logger.info("✅ Document upload flow completed.");
        } catch (AssertionError e) {
            logger.error("❌ Error during document upload: " + e.getMessage());
            ScreenshotUtils.attachScreenshotToAllure(driver, "Error_UploadDocs");
            throw e;
        }
    }

}




