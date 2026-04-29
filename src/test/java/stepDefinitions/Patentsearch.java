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
import pages.Patentsearchpage;
import utils.ConfigReader;
import utils.LoggerUtils;
import utils.ScreenshotUtils;

import java.awt.*;
import java.awt.datatransfer.DataFlavor;
import java.time.Duration;

import static utils.AllureLoggerUtils.logToAllure;

public class Patentsearch {

    WebDriver driver = Hooks.driver;
    Patentsearchpage patent;
    Logger logger;
    WebDriverWait wait;
    SearchTicketStepdefs searchTicketsteps;
    LoginStepDefinitions loginSteps;


    public Patentsearch() {
        this.driver = Hooks.driver;
        this.patent = new Patentsearchpage(Hooks.driver);
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        this.logger = LoggerUtils.getLogger(getClass());
        this.searchTicketsteps = new SearchTicketStepdefs(Hooks.driver);
        this.loginSteps = new LoginStepDefinitions();
    }

    @Step("{message}")
    public void logStep(String message) {
    }

    @Given("The user navigating to the Patent search page")

    public void patentsearch() throws InterruptedException {
        try {

            searchTicketsteps.theUserRedirectToTicketListPage();
            searchTicketsteps.theUserEnterTheTicket();
            searchTicketsteps.theUserClickTheSearchCTA();
            searchTicketsteps.theUserViewTheTicketPageByClickingOnTheLink();

            patent.crossSaleNew();
            patent.createservice(ConfigReader.get("Patentsearch"));
            patent.selectingtheservice();
            Thread.sleep(2000);
            patent.createbutton();
            logger.info("Service creation initiated.");
            ScreenshotUtils.attachScreenshotToAllure(driver, "2_Service_Created");

            Thread.sleep(15000);
            patent.recenttickets();
            Thread.sleep(4000);
            logger.info("Recent Tickets modal opened.");
            ScreenshotUtils.attachScreenshotToAllure(driver, "3_Recent_Tickets_Modal");

            try {
                WebElement firstTicket = driver.findElement(By.cssSelector("tr.ticket_index_tr ul.tcktdetails li a"));
                firstTicket.click();
                logger.info("Clicked first dynamic ticket link.");
                Thread.sleep(10000);
                ScreenshotUtils.attachScreenshotToAllure(driver, "4_Selected_Ticket_Page");

                patent.copyurl.click();
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
            logStep("🌐 Redirecting the user to the Patent search page...");
            logStep("✅ Field Details screen for Patent search page");
            logToAllure("✅ The user Navigated to Patent search page", "Patent search page redirected successfully");
            ScreenshotUtils.attachScreenshotToAllure(driver, "Patent search page redirected successfully");

        } catch (AssertionError e) {
            String msg = "❌ Exception during Accessing the Customer view : " + e.getMessage();
            logger.error(msg);
            logStep(msg);
            logToAllure("❌ Exception", msg);
            ScreenshotUtils.attachScreenshotToAllure(driver, "Patent search page_Exception");
            throw e;
        }
    }

    @When("the user entering the details in Disclosure of the Invention")
    public void disclouseoftheinvention() throws InterruptedException {

        logStep("📝 Starting Disclosure of the Invention process");
        logger.info("===== Disclosure of Invention Flow Started =====");

        try {
            Thread.sleep(2000);
            logStep("📄 Processing Page 1: Basic Invention Details");
            ScreenshotUtils.attachScreenshotToAllure(driver, "Disclosure Page 1 Initial");

            String title = ConfigReader.get("InventionTitle");
            if (title == null || title.isEmpty()) throw new RuntimeException("❌ Invention Title is missing in config");
            patent.titleofinvention(title);
            logger.info("Invention Title entered → {}", title);

            String briefDesc = ConfigReader.get("BriefDescription");
            patent.briefdescription(briefDesc);
            logger.info("Brief Description entered");

            String idea = ConfigReader.get("InventionIdea");
            patent.comeupwithininvention(idea);
            logger.info("Invention Idea source entered");

            String novelty = ConfigReader.get("NoveltyNewness");
            patent.noveltynewness(novelty);
            logger.info("Novelty/Newness details entered");

            String detailedDesc = ConfigReader.get("DetailedDescription");
            patent.descriptionofinvention(detailedDesc);
            logger.info("Detailed Description entered");

            logToAllure("Page 1 Data", "Invention Title, Brief Desc, Idea, Novelty, and Detailed Desc entered");
            ScreenshotUtils.attachScreenshotToAllure(driver, "Disclosure Page 1 Data Entered");

            logStep("➡ Clicking Page 1 Button");
            patent.page1button();
            Thread.sleep(2000);

            logStep("📄 Processing Page 2: Technical Features & Prior Art");

            patent.briefdescription(ConfigReader.get("BriefDescription"));
            logger.info("Brief Description (P2) re-entered");

            String advantages = ConfigReader.get("InventionIdea"); // Using InventionIdea for advantages as per original
            patent.advantagesofinvention(advantages);
            logger.info("Advantages of Invention entered");

            String diff = ConfigReader.get("HowInventionDiffer");
            patent.differentinvention(diff);
            logger.info("Differentiation details entered");

            String features = ConfigReader.get("EssentialFeature");
            if (features == null || features.isEmpty()) throw new RuntimeException("❌ Essential Features are missing");
            patent.essentialfeature(features);
            logger.info("Essential Features entered");

            String priorArt = ConfigReader.get("PriorArt");
            patent.priorartsearch(priorArt);
            logger.info("Prior Art Search details entered");

            String status = ConfigReader.get("InventionStatus");
            patent.statusofinvention(status);
            logger.info("Invention Status entered → {}", status);

            logToAllure("Page 2 Data", "Advantages, Differentiation, Features, and Status entered");
            ScreenshotUtils.attachScreenshotToAllure(driver, "Disclosure Page 2 Data Entered");

            logStep("➡ Clicking Page 2 Button");
            patent.page2button();
            Thread.sleep(2000);

            logStep("🎉 Disclosure of Invention details completed successfully");
            logger.info("===== Disclosure of Invention Flow Completed =====");

        } catch (AssertionError e) {
            logger.error("❌ Disclosure of Invention entry failed", e);
            ScreenshotUtils.attachScreenshotToAllure(driver, "Disclosure Failure Screenshot");
            throw new RuntimeException("❌ Disclosure of Invention entry failed → " + e.getMessage(), e);
        }
    }

    @When("the user enters the other information")
    public void otherinformation() throws InterruptedException {

        logStep("ℹ️ Entering Other Information subtab");
        logger.info("===== Other Information Flow Started =====");

        try {
            logStep("👥 Entering number of inventors");
            String noOfInventors = ConfigReader.get("NoOfInventor");
            if (noOfInventors == null || noOfInventors.isEmpty()) {
                throw new RuntimeException("❌ Number of inventors is missing in config");
            }
            patent.otherdetails(noOfInventors);
            logger.info("Number of inventors entered → {}", noOfInventors);

            logStep("🖱 Selecting No and Proceeding");
            patent.selectNoAndProceed();
            logger.info("Selected 'No' and clicked Proceed");

            logStep("➡ Clicking Other Information Button");
            patent.otherbutton();
            logger.info("Other Button clicked");

            ScreenshotUtils.attachScreenshotToAllure(driver, "Other Information Completed");
            Thread.sleep(2000);

            logStep("✅ Other information section completed");
            logger.info("===== Other Information Flow Completed =====");

        } catch (AssertionError e) {
            logger.error("❌ Failed to enter other information", e);
            ScreenshotUtils.attachScreenshotToAllure(driver, "Other_Information_Failure");
            throw new RuntimeException("❌ Other information entry failed → " + e.getMessage(), e);
        }
    }

    @Then("the user uploads the documents for patent search")
    public void uploaddocs() throws InterruptedException {

        logStep("📂 Starting Document Upload process");
        logger.info("===== Document Upload Flow Started =====");

        try {
            logStep("📄 Validating and preparing for final submission");
            ScreenshotUtils.attachScreenshotToAllure(driver, "Pre-upload state");


             logStep("📤 Uploading Softcopy and Other Documents");
             patent.softcopy.sendKeys(ConfigReader.get("BankStatement"));
             patent.otherdocument.sendKeys(ConfigReader.get("PassportPhoto1"));
             logger.info("Documents sent to upload fields");

            logStep("🏁 Clicking Final Submission button");
            patent.finalbutton();
            logger.info("Final Button clicked");

            Thread.sleep(2000);
            ScreenshotUtils.attachScreenshotToAllure(driver, "Final submission completed");

            logStep("🎉 Patent search document submission completed successfully");
            logger.info("===== Document Upload Flow Completed =====");

        } catch (AssertionError e) {
            logger.error("❌ Failed during document upload / final submission", e);
            ScreenshotUtils.attachScreenshotToAllure(driver, "Upload_Failure_Screenshot");
            throw new RuntimeException("❌ Document upload failed → " + e.getMessage(), e);
        }
    }
}




