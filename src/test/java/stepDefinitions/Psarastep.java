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
import pages.Psarapage;
import utils.ConfigReader;
import utils.LoggerUtils;
import utils.ScreenshotUtils;

import java.awt.*;
import java.awt.datatransfer.DataFlavor;
import java.time.Duration;

import static utils.AllureLoggerUtils.logToAllure;

public class Psarastep {

    WebDriver driver = Hooks.driver;
    Psarapage psara;
    Logger logger;
    WebDriverWait wait;
    SearchTicketStepdefs searchTicketsteps;
    LoginStepDefinitions loginSteps;


    public Psarastep() throws AWTException {
        this.driver = Hooks.driver;
        this.psara = new Psarapage(Hooks.driver);
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        this.logger = LoggerUtils.getLogger(getClass());
        this.searchTicketsteps = new SearchTicketStepdefs(Hooks.driver);
        this.loginSteps = new LoginStepDefinitions();
    }

    @Step("{message}")
    public void logStep(String message) {
    }

    @Given("the user redirect to Psara license service")
    public void theUserRedirectToPsaraLicenseService() throws InterruptedException{

        try {

            searchTicketsteps.theUserRedirectToTicketListPage();
            searchTicketsteps.theUserEnterTheTicket();
            searchTicketsteps.theUserClickTheSearchCTA();
            searchTicketsteps.theUserViewTheTicketPageByClickingOnTheLink();
            psara.crossSaleNew();
            psara.createservice(ConfigReader.get("Psaraservice"));
            psara.selectingtheservice();
            Thread.sleep(2000);
            psara.createbutton();
            logger.info("Service creation initiated.");
            ScreenshotUtils.attachScreenshotToAllure(driver, "2_Service_Created");

            Thread.sleep(15000);
            psara.recenttickets();
            Thread.sleep(4000);
            logger.info("Recent Tickets modal opened.");
            ScreenshotUtils.attachScreenshotToAllure(driver, "3_Recent_Tickets_Modal");

            try {
                WebElement firstTicket = driver.findElement(By.cssSelector("tr.ticket_index_tr ul.tcktdetails li a"));
                firstTicket.click();
                logger.info("Clicked first dynamic ticket link.");
                Thread.sleep(10000);
                ScreenshotUtils.attachScreenshotToAllure(driver, "4_Selected_Ticket_Page");

                psara.copyurl.click();
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
            logStep("🌐 Redirecting the user to the Psara License page...");
            logStep("✅ Field Details screen for Psara License page");
            logToAllure("✅ The user Navigated to Psara License page", "Psara License page redirected successfully");
            ScreenshotUtils.attachScreenshotToAllure(driver, "Psara License page redirected successfully");

        } catch (Exception e) {
            String msg = "❌ Exception during Accessing the Customer view : " + e.getMessage();
            logger.error(msg);
            logStep(msg);
            logToAllure("❌ Exception", msg);
            ScreenshotUtils.attachScreenshotToAllure(driver, "Psara License page_Exception");
            throw e;
        }
    }

    @When("the user entering the company details for Psara service")
    public void PsaraService() throws InterruptedException {
        logStep("🏢 Starting PSARA Company Details entry");
        logger.info("===== PSARA Company Details Flow Started =====");

        try {
            String company = ConfigReader.get("CompanyName");
            String email = ConfigReader.get("ApplicantEmailId");

            logger.info("Entering Company: [{}], Email: [{}]", company, email);

            psara.companyName(company);
            psara.emailID(email);
            psara.mobileNumber(ConfigReader.get("companymobile"));
            psara.address(ConfigReader.get("Address1"));
            psara.noofEmployees(ConfigReader.get("employeecount"));

            psara.companybutton();
            logger.info("Clicked Company Details submission button");

            Thread.sleep(2000);
            ScreenshotUtils.attachScreenshotToAllure(driver, "PSARA_Company_Details_Entered");

        } catch (AssertionError e) {
            logger.error("Error in PSARA Company Details: " + e.getMessage());
            throw e;
        }
    }

    @When("the user entering the Authorized person details")
    public void PsaraServiceAuthorized() throws InterruptedException {
        logStep("👤 Entering PSARA Authorized Person Details");
        logger.info("===== PSARA Authorized Person Flow Started =====");

        try {
            String authName = ConfigReader.get("shareholderfathername2");
            logger.info("Entering Authorized Person: [{}]", authName);

            psara.name(authName);
            psara.selectDate();
            Thread.sleep(2000);

            psara.authmobileno(ConfigReader.get("companymobile"));
            psara.authemail(ConfigReader.get("ApplicantEmailId"));
            psara.authAddress(ConfigReader.get("Address1"));

            psara.authbutton();
            logger.info("Authorized person details submitted");

            Thread.sleep(2000);
            ScreenshotUtils.attachScreenshotToAllure(driver, "PSARA_Authorized_Person_Details");

        } catch (AssertionError e) {
            logger.error("Error in PSARA Authorized Person Details: " + e.getMessage());
            throw e;
        }
    }

    @Then("the user uploading docs for psara")
    public void theUserUploadingDocsForPsara() throws InterruptedException {
        logStep("📂 Uploading PSARA Documents");
        logger.info("===== PSARA Document Upload Started =====");

        try {
            String rentalPath = ConfigReader.get("DrivingLicense");
            Assert.assertNotNull(rentalPath, "Document path for Rental Agreement is missing!");

            logger.info("Uploading Rental Agreement from path: [{}]", rentalPath);
//            psara.rentalagreement.sendKeys(ConfigReader.get("DrivingLicense"));
//            Thread.sleep(2000);

            psara.Electricitybill.sendKeys(ConfigReader.get("BankStatement"));
            Thread.sleep(2000);

//            psara.incorporationcertificate.sendKeys(ConfigReader.get("COI"));
//            Thread.sleep(2000);

            psara.Gstcertificate.sendKeys(ConfigReader.get("PassportPhoto2"));
            Thread.sleep(2000);

//            psara.employeecertificate.sendKeys(ConfigReader.get("PassportPhoto1"));
//            Thread.sleep(2000);
            Thread.sleep(2000);
            psara.uploadbbutton();
            logger.info("All documents uploaded and Submit clicked");

            Thread.sleep(2000);
            ScreenshotUtils.attachScreenshotToAllure(driver, "PSARA_Documents_Uploaded");

        } catch (AssertionError e) {
            logger.error("Error during PSARA document upload: " + e.getMessage());
            throw e;
        }
    }
}




