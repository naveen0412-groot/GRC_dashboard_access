package stepDefinitions;

import hooks.Hooks;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import io.qameta.allure.Step;
import org.apache.logging.log4j.Logger;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import pages.ProvisionalPatentPage;
import utils.*;

import java.awt.*;
import java.awt.datatransfer.DataFlavor;

import static utils.AllureLoggerUtils.logToAllure;

public class ProvisionalPatentStepDefs {
    WebDriver driver = Hooks.driver;
    ProvisionalPatentPage provisionalPatentPage;
    Logger logger;
    AllureLoggerUtils allureLogging;
    WaitUtils wait;
    SearchTicketStepdefs searchTicketsteps;
    LoginStepDefinitions loginSteps;

    public ProvisionalPatentStepDefs() {
        this.driver = Hooks.driver;
        this.provisionalPatentPage = new ProvisionalPatentPage(Hooks.driver);
        this.logger = LoggerUtils.getLogger(getClass());
        this.wait = new WaitUtils(driver, 30);
        this.searchTicketsteps = new SearchTicketStepdefs(Hooks.driver);
        this.loginSteps = new LoginStepDefinitions();
    }

    @Step("{message}")
    public void logStep(String message) {
        // Allure will log this message as a step
    }
    @Given("the user redirect to Provisional Patent Service page")
    public void the_user_redirect_to_provisional_patent_service_page() throws InterruptedException {
        try {

            searchTicketsteps.theUserRedirectToTicketListPage();
            searchTicketsteps.theUserEnterTheTicket();
            searchTicketsteps.theUserClickTheSearchCTA();
            searchTicketsteps.theUserViewTheTicketPageByClickingOnTheLink();

            provisionalPatentPage.crossSaleNew();
            provisionalPatentPage.createservice(ConfigReader.get("Provisionalpatent"));
            provisionalPatentPage.selectingtheservice();
            Thread.sleep(2000);
            provisionalPatentPage.createbutton();
            logger.info("Service creation initiated.");
            ScreenshotUtils.attachScreenshotToAllure(driver, "2_Service_Created");

            Thread.sleep(15000);
            provisionalPatentPage.recenttickets();
            Thread.sleep(4000);
            logger.info("Recent Tickets modal opened.");
            ScreenshotUtils.attachScreenshotToAllure(driver, "3_Recent_Tickets_Modal");

            try {
                WebElement firstTicket = driver.findElement(By.cssSelector("tr.ticket_index_tr ul.tcktdetails li a"));
                firstTicket.click();
                logger.info("Clicked first dynamic ticket link.");
                Thread.sleep(10000);
                ScreenshotUtils.attachScreenshotToAllure(driver, "4_Selected_Ticket_Page");

                provisionalPatentPage.copyurl.click();
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
            logStep("🌐 Navigating to the Provisional Patent Service page...");
            logStep("✅ Open Provisional Patent Service Page");
            logToAllure("✅ Provisional Patent Redirection Validation", "Provisional Patent Page redirected successfully");
            ScreenshotUtils.attachScreenshotToAllure(driver, "ProvisionalPatentPage");

        } catch (AssertionError e) {
            String msg = "❌ Exception during Provisional Patent page: " + e.getMessage();
            logger.error(msg);
            logStep(msg);
            logToAllure("❌ Exception", msg);
            ScreenshotUtils.attachScreenshotToAllure(driver, "ProvisionalPatentURL_Exception");
            throw e;
        }
    }

    @When("the user enter Applicant details for Provisional Patent")
    public void the_user_enter_applicant_details_for_provisional_patent() {
        try {
            provisionalPatentPage.typeApplicantType(ConfigReader.get("ApplicantType")+ Keys.ENTER);
            provisionalPatentPage.typeApplicantName(ConfigReader.get("ApplicantName"));
            provisionalPatentPage.typeApplicantGender(ConfigReader.get("Gender"));
            provisionalPatentPage.typeApplicantAddress(ConfigReader.get("ApplicantAddress"));
            provisionalPatentPage.typeApplicantContactNumber(ConfigReader.get("ApplicantContactNumber"));
            provisionalPatentPage.typeApplicantEmailId(ConfigReader.get("ApplicantEmailId"));
            logger.info("✅ Provisional Patent - Applicant Details updated successfully");
            logStep("✅ Completed updating Provisional Patent - Applicant Details details");
            logToAllure("✅ Update Applicant Details", "Provisional Patent- Applicant details updated successfully");
            ScreenshotUtils.attachScreenshotToAllure(driver, "ProvisionalPatent_ApplicantDetails_Updated");
            provisionalPatentPage.clickNextCTA();
        } catch (AssertionError e) {
            String message = "❌ Provisional Patent - Applicant Details entry: " + e.getMessage();
            logger.error(message);
            logStep(message);
            logToAllure("❌ Assertion Error", message);
            ScreenshotUtils.attachScreenshotToAllure(driver, "ProvisionalPatent_ApplicantDetails_Failed");
        }
    }

    @When("the user enter the No of inventor and its details")
    public void the_user_enter_the_no_of_inventor_and_its_details() {
        try {
            provisionalPatentPage.typeNoOfInventor(ConfigReader.get("NoOfInventor"));
            logger.info("✅ Provisional Patent -No Of Inventor updated successfully");
            logStep("✅ Completed updating Provisional Patent - No Of Inventor details");
            logToAllure("✅ Update NoOfInventor Details", "Provisional Patent- NoOfInventor updated successfully");
            ScreenshotUtils.attachScreenshotToAllure(driver, "NoOfInventor_Updated");
            provisionalPatentPage.clickNextCTA();
            provisionalPatentPage.typeApplicantNature(ConfigReader.get("ApplicantType")+ Keys.ENTER);
            provisionalPatentPage.typeInventorName(ConfigReader.get("InventorName"));
            provisionalPatentPage.typeInventorAddress(ConfigReader.get("InventorAddress"));
            provisionalPatentPage.typeInventorEmailId(ConfigReader.get("InventorEmailId"));
            provisionalPatentPage.typeInventorContactNumber(ConfigReader.get("InventorContactNumber"));
            provisionalPatentPage.typeInventorGender(ConfigReader.get("Gender"));
            logger.info("✅ Provisional Patent - Inventor Details updated successfully");
            logStep("✅ Completed updating Provisional Patent - Inventor Details details");
            logToAllure("✅ Update Inventor Details", "Provisional Patent- Inventor details updated successfully");
            ScreenshotUtils.attachScreenshotToAllure(driver, "ProvisionalPatent_InventorDetails_Updated");
            provisionalPatentPage.clickNextCTA();
        } catch (AssertionError e) {
            String message = "❌ Provisional Patent - No Of Inventor entry: " + e.getMessage();
            logger.error(message);
            logStep(message);
            logToAllure("❌ Assertion Error", message);
            ScreenshotUtils.attachScreenshotToAllure(driver, "ProvisionalPatent_NoOfInventor_Failed");
        }
    }

    @When("the user enter the Type of application")
    public void the_user_enter_the_type_of_application() {
        try {
            provisionalPatentPage.typePatentApplicantType(ConfigReader.get("PatentType")+ Keys.ENTER);
            logger.info("✅ Provisional Patent - Type of application updated successfully");
            logStep("✅ Completed updating Provisional Patent - Type of application");
            logToAllure("✅ Update Type of application", "Provisional Patent- Type of application updated successfully");
            ScreenshotUtils.attachScreenshotToAllure(driver, "ProvisionalPatent_ApplicationType_Updated");
            provisionalPatentPage.clickNextCTA();
        } catch (AssertionError e) {
            String message = "❌ Provisional Patent - Type of applications entry: " + e.getMessage();
            logger.error(message);
            logStep(message);
            logToAllure("❌ Assertion Error", message);
            ScreenshotUtils.attachScreenshotToAllure(driver, "ProvisionalPatent_ApplicationType_Failed");
        }
    }

    @When("the user enter the Disclosure of the Invention")
    public void the_user_enter_the_disclosure_of_the_invention() {
        try {
            provisionalPatentPage.typeInventionTitle(ConfigReader.get("InventionTitle"));
            provisionalPatentPage.typeBriefDescription(ConfigReader.get("BriefDescription"));
            provisionalPatentPage.typeInventionIdea(ConfigReader.get("InventionIdea"));
            provisionalPatentPage.typeNoveltyNewness(ConfigReader.get("NoveltyNewness"));
            provisionalPatentPage.typeInventionDescription(ConfigReader.get("DetailedDescription"));
            provisionalPatentPage.typeFiguresDrawing(ConfigReader.get("DSCAvailability")+ Keys.ENTER);
            provisionalPatentPage.typeInventionAdvantage(ConfigReader.get("InventionAdvantage"));
            provisionalPatentPage.typeHowInventionDiffer(ConfigReader.get("HowInventionDiffer"));
            provisionalPatentPage.typeEssentialFeature(ConfigReader.get("EssentialFeature"));
            provisionalPatentPage.typePriorArt(ConfigReader.get("PriorArt"));
            provisionalPatentPage.typeInventionStatus(ConfigReader.get("InventionStatus"));
            logger.info("✅ Provisional Patent -  Disclosure of the Invention updated successfully");
            logStep("✅ Completed updating Provisional Patent -  Disclosure of the Invention details");
            logToAllure("✅ Update  Disclosure of the Invention", "Provisional Patent-  Disclosure of the Invention updated successfully");
            ScreenshotUtils.attachScreenshotToAllure(driver, "ProvisionalPatent_InventionDisclosure_Updated");
            provisionalPatentPage.clickNextCTA();
        } catch (AssertionError e) {
            String message = "❌ Provisional Patent -  Disclosure of the Invention entry: " + e.getMessage();
            logger.error(message);
            logStep(message);
            logToAllure("❌ Assertion Error", message);
            ScreenshotUtils.attachScreenshotToAllure(driver, "ProvisionalPatent_InventionDisclosure_Failed");
        }
    }

    @When("the user enter the Other Details for Provisional Patent")
    public void the_user_enter_the_other_details_for_provisional_patent() {
        try {
            provisionalPatentPage.typeSignatureAttached(ConfigReader.get("DSCAvailability")+ Keys.ENTER);
            logger.info("✅ Provisional Patent - Other Details updated successfully");
            logStep("✅ Completed updating Provisional Patent - Other Details details");
            logToAllure("✅ Update Other Details", "Provisional Patent- Other details updated successfully");
            ScreenshotUtils.attachScreenshotToAllure(driver, "ProvisionalPatent_OtherDetails_Updated");
            provisionalPatentPage.clickNextCTA();
        } catch (AssertionError e) {
            String message = "❌ Provisional Patent - Other Details entry: " + e.getMessage();
            logger.error(message);
            logStep(message);
            logToAllure("❌ Assertion Error", message);
            ScreenshotUtils.attachScreenshotToAllure(driver, "ProvisionalPatent_OtherDetails_Failed");
        }
    }
}
