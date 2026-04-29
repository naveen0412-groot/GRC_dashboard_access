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
import pages.Oppositioninfringepage;
import utils.ConfigReader;
import utils.LoggerUtils;
import utils.ScreenshotUtils;

import java.awt.*;
import java.awt.datatransfer.DataFlavor;
import java.time.Duration;

import static utils.AllureLoggerUtils.logToAllure;

public class Oppositioninfringestep {

    WebDriver driver = Hooks.driver;
    Oppositioninfringepage opposition;
    Logger logger;
    WebDriverWait wait;
    SearchTicketStepdefs searchTicketsteps;
    LoginStepDefinitions loginSteps;


    public Oppositioninfringestep()  {
        this.driver = Hooks.driver;
        try {
            this.opposition = new Oppositioninfringepage(Hooks.driver);
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

    @Given("the user redirect to File an Opposition for Brand Infringement")
    public void setOpposition() throws InterruptedException {
        try {

            searchTicketsteps.theUserRedirectToTicketListPage();
            searchTicketsteps.theUserEnterTheTicket();
            searchTicketsteps.theUserClickTheSearchCTA();
            searchTicketsteps.theUserViewTheTicketPageByClickingOnTheLink();

            opposition.crossSaleNew();
            opposition.createservice(ConfigReader.get("Fileanoppositioninfringe"));
            opposition.selectingtheservice();
            Thread.sleep(2000);
            opposition.createbutton();
            logger.info("Service creation initiated.");
            ScreenshotUtils.attachScreenshotToAllure(driver, "2_Service_Created");

            Thread.sleep(15000);
            opposition.recenttickets();
            Thread.sleep(4000);
            logger.info("Recent Tickets modal opened.");
            ScreenshotUtils.attachScreenshotToAllure(driver, "3_Recent_Tickets_Modal");

            try {
                WebElement firstTicket = driver.findElement(By.cssSelector("tr.ticket_index_tr ul.tcktdetails li a"));
                firstTicket.click();
                logger.info("Clicked first dynamic ticket link.");
                Thread.sleep(10000);
                ScreenshotUtils.attachScreenshotToAllure(driver, "4_Selected_Ticket_Page");

                opposition.copyurl.click();
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

            logStep("🌐 Redirecting the user to File an Opposition for Brand Infringement page...");
            logStep("✅ Field Details screen for File an Opposition for Brand Infringement");
            logToAllure("✅ The user Navigated to File an Opposition for Brand Infringement page", "File an Opposition for Brand Infringement Page redirected successfully");
            ScreenshotUtils.attachScreenshotToAllure(driver, "File an Opposition for Brand Infringement");
        } catch (AssertionError e) {
            String msg = "❌ Exception during Accessing the Customer view : " + e.getMessage();
            logger.error(msg);
            logStep(msg);
            logToAllure("❌ Exception", msg);
            ScreenshotUtils.attachScreenshotToAllure(driver, "File an Opposition for Brand Infringement_Exception");
            throw e;
        }
    }

    @When("the user enters the Client Application number of File an Opposition for Brand Infringement")
    public void clientapplication() throws InterruptedException {

        Thread.sleep(2000);
        opposition.applicationNumber(ConfigReader.get("TMApplicationNumber"));
        opposition.appbutton();
        Thread.sleep(2000);
    }

    @When("the user enters the Application no. to be opposed of File an Opposition")
    public void applicationopposed() throws InterruptedException {

        Thread.sleep(2000);
        opposition.applicationNumber(ConfigReader.get("TMApplicationNumber"));
        opposition.opposebutton();
        Thread.sleep(2000);
    }

    @When("the user enters the First Date of use of the mark of File an Opposition")
    public void firstdatemark() throws InterruptedException {

        Thread.sleep(2000);
        opposition.selectDate();
        opposition.firstdatebutton();
        Thread.sleep(2000);
    }
    @Then("the user enters the Any prior disputes with the other party of File an Opposition")
    public void priordisputes() throws InterruptedException {

        Thread.sleep(2000);
        opposition.priordisputes(ConfigReader.get("shareholderfathername2"));
        opposition.priorbutton();
        Thread.sleep(2000);
    }

}



