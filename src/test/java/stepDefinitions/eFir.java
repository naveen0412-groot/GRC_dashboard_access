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
import org.openqa.selenium.support.ui.WebDriverWait;
import pages.efirpageobject;
import utils.ConfigReader;
import utils.LoggerUtils;
import utils.ScreenshotUtils;

import java.awt.*;
import java.awt.datatransfer.DataFlavor;
import java.time.Duration;

import static utils.AllureLoggerUtils.logToAllure;

public class eFir {

    WebDriver driver = Hooks.driver;
    efirpageobject efirpageobject;
    Logger logger;
    WebDriverWait wait;
    SearchTicketStepdefs searchTicketsteps;
    LoginStepDefinitions loginSteps;


    public  eFir()  {
        this.driver = Hooks.driver;
        try {
            this.efirpageobject = new efirpageobject(Hooks.driver);
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
    @Given("the user redirect to eFIR Details page of eFir service")
    public void the_user_redirect_to_eFIR_Details_page_of_eFir_service() throws InterruptedException {
        try {

            searchTicketsteps.theUserRedirectToTicketListPage();
            searchTicketsteps.theUserEnterTheTicket();
            searchTicketsteps.theUserClickTheSearchCTA();
            searchTicketsteps.theUserViewTheTicketPageByClickingOnTheLink();

            efirpageobject.crossSaleNew();
            efirpageobject.createservice(ConfigReader.get("Efir"));
            efirpageobject.selectingtheservice();
            Thread.sleep(2000);
            efirpageobject.createbutton();
            logger.info("Service creation initiated.");
            ScreenshotUtils.attachScreenshotToAllure(driver, "2_Service_Created");

            Thread.sleep(15000);
            efirpageobject.recenttickets();
            Thread.sleep(4000);
            logger.info("Recent Tickets modal opened.");
            ScreenshotUtils.attachScreenshotToAllure(driver, "3_Recent_Tickets_Modal");

            try {
                WebElement firstTicket = driver.findElement(By.cssSelector("tr.ticket_index_tr ul.tcktdetails li a"));
                firstTicket.click();
                logger.info("Clicked first dynamic ticket link.");
                Thread.sleep(10000);
                ScreenshotUtils.attachScreenshotToAllure(driver, "4_Selected_Ticket_Page");

                efirpageobject.copyurl.click();
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




            logStep("🌐 Redirecting the user to E-fir page...");
                Thread.sleep(1000);
            logStep("✅ Field Details screen for E-Fir ");
            logToAllure("✅ The user Navigated to E-fir page ", "E-Fir Page redirected successfully");
            ScreenshotUtils.attachScreenshotToAllure(driver, "E-Fir");

        } catch (IllegalArgumentException | WebDriverException | AssertionError e) {
            String msg = "❌ Exception during Accessing the Customer view : " + e.getMessage();
            logger.error(msg);
            logStep(msg);
            logToAllure("❌ Exception", msg);
            ScreenshotUtils.attachScreenshotToAllure(driver, "E-Fir_Exception");
            throw e;
        }
    }

    @When("the user update the common Details")
    public void the_user_update_the_common_Details() {
        try{
            efirpageobject.city(ConfigReader.get("city"));
            efirpageobject.cType(ConfigReader.get("complainvalue"));
            // efirpageobject.cvalue(ConfigReader.get("complainvalue"));
            efirpageobject.description(ConfigReader.get("descrip"));
            efirpageobject.buttonnt();
            Thread.sleep(2000);
            logger.info("✅ Enter E- Fir Details successfully");
            logStep("✅ Completed All the details");
            logToAllure("✅ E-Fir Details Validation", "E-Fir details successfully updated");
            ScreenshotUtils.attachScreenshotToAllure(driver, "EFir");
        }catch (IllegalArgumentException | InterruptedException | AssertionError e){
            String message = "❌ Exception during Filing E-Fir details entry: " + e.getMessage();
            logger.error(message, e);
            logStep(message);
            logToAllure("❌ Exception", message);
            ScreenshotUtils.attachScreenshotToAllure(driver, "Fir_Details_Exception");
        }

    }
}


