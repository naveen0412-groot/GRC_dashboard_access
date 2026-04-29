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
import pages.Genderchangepage;
import utils.ConfigReader;
import utils.LoggerUtils;
import utils.ScreenshotUtils;
import java.awt.*;
import java.awt.datatransfer.DataFlavor;
import java.time.Duration;

import static utils.AllureLoggerUtils.logToAllure;

public class Genderchangestep {

    WebDriver driver = Hooks.driver;
    Genderchangepage gender;
    Logger logger;
    WebDriverWait wait;
    SearchTicketStepdefs searchTicketsteps;
    LoginStepDefinitions loginSteps;


    public Genderchangestep() {
        this.driver = Hooks.driver;
        try {
            this.gender = new Genderchangepage(Hooks.driver);
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

    @Given("the user redirect to Gender change service page")
    public void genderchange() throws InterruptedException {
        try {

            searchTicketsteps.theUserRedirectToTicketListPage();
            searchTicketsteps.theUserEnterTheTicket();
            searchTicketsteps.theUserClickTheSearchCTA();
            searchTicketsteps.theUserViewTheTicketPageByClickingOnTheLink();
            gender.crossSaleNew();
            gender.createservice(ConfigReader.get("Genderchange"));
            gender.selectingtheservice();
            Thread.sleep(2000);
            gender.createbutton();
            logger.info("Service creation initiated.");
            ScreenshotUtils.attachScreenshotToAllure(driver, "2_Service_Created");
            Thread.sleep(15000);
            gender.recenttickets();
            Thread.sleep(4000);
            logger.info("Recent Tickets modal opened.");
            ScreenshotUtils.attachScreenshotToAllure(driver, "3_Recent_Tickets_Modal");
            try {
                WebElement firstTicket = driver.findElement(By.cssSelector("tr.ticket_index_tr ul.tcktdetails li a"));
                firstTicket.click();
                logger.info("Clicked first dynamic ticket link.");
                Thread.sleep(10000);
                ScreenshotUtils.attachScreenshotToAllure(driver, "4_Selected_Ticket_Page");

                gender.copyurl.click();
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
            logStep("🌐 Redirecting the user to Gender Change page...");
            logStep("✅ Field Details screen for Gender Change ");
            logToAllure("✅ The user Navigated to Gender Change page ", "Gender Change Page redirected successfully");
            ScreenshotUtils.attachScreenshotToAllure(driver, "Gender Change Notice");
        } catch (Exception e) {
            String msg = "❌ Exception during Accessing the Customer view : " + e.getMessage();
            logger.error(msg);
            logStep(msg);
            logToAllure("❌ Exception", msg);
            ScreenshotUtils.attachScreenshotToAllure(driver, "Gender Change_Exception");
            throw e;
        }
    }
    @When("the user validate the Personal Details in gender change")
    public void the_user_update_the_bank_Details() throws InterruptedException {
        logger.info("Starting validation of Personal Details.");

        gender.name(ConfigReader.get("companyvaluemsme"));
        logger.info("Entered Name: " + ConfigReader.get("companyvaluemsme"));

        gender.fatherName(ConfigReader.get("companynamemsme"));
        logger.info("Entered Father Name: " + ConfigReader.get("companynamemsme"));

        // Selection of Current Gender
        gender.selectCurrentGenderMale();
        logger.info("Selected Current Gender: Male");
        ScreenshotUtils.attachScreenshotToAllure(driver, "Gender_Current_Selected");

        // Selection of New Gender
        gender.selectNewGenderMale();
        logger.info("Selected New Gender: Male");
        ScreenshotUtils.attachScreenshotToAllure(driver, "Gender_New_Selected");

        // Address logic
        gender.selectPermanentAddressSameAsPresent();
        logger.info("Address toggle selected: Yes");
        ScreenshotUtils.attachScreenshotToAllure(driver, "Address_Same_Selected");

        gender.PermanentAddress(ConfigReader.get("Roadstreet"));
        gender.PresentAddress(ConfigReader.get("naturebusiness"));
        logger.info("Address details filled.");

        gender.personalbutton();
        logger.info("Personal details submitted.");
        ScreenshotUtils.attachScreenshotToAllure(driver, "Personal_Details_Submitted");

        Thread.sleep(2000);
    }

    @When("the user entering the Witness details")
    public void witnessupdate() throws InterruptedException {
        logger.info("Initiating Witness 1 details entry.");

        gender.witnessName1(ConfigReader.get("shareholderfathername"));
        gender.witnessfatherName(ConfigReader.get("shareholderfathername2"));
        gender.witnessContactNumber1(ConfigReader.get("DirectorMobile1"));
        gender.witness1address(ConfigReader.get("Address1"));

        gender.witness1button();
        logger.info("Witness 1 details submitted.");
        ScreenshotUtils.attachScreenshotToAllure(driver, "Witness1_Created");
        Thread.sleep(2000);

        logger.info("Initiating Witness 2 details entry.");
        gender.witnessAddress2(ConfigReader.get("directorName2"));
        gender.witnessMobile(ConfigReader.get("DirectorMobile2"));
        gender.witnessname2(ConfigReader.get("Address2"));
        gender.witnessname3(ConfigReader.get("FatherName2"));

        gender.witness2button();
        logger.info("Witness 2 details submitted.");
        ScreenshotUtils.attachScreenshotToAllure(driver, "Witness2_Created");
        Thread.sleep(2000);
    }

    @Then("The user uploading the documents for gender change")
    public void uploaddoc() throws InterruptedException {
        logger.info("Navigating to document upload.");
        gender.docbutton();
        Thread.sleep(2000);
        logger.info("Document section opened.");
        ScreenshotUtils.attachScreenshotToAllure(driver, "Document_Page_Loaded");
    }
    }


