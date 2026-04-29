package stepDefinitions;


import hooks.Hooks;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import io.qameta.allure.Step;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import pages.SearchTicketPage;
import utils.ConfigReader;
import utils.LoggerUtils;
import utils.ScreenshotUtils;

import java.time.Duration;
import java.util.NoSuchElementException;

import static utils.AllureLoggerUtils.logToAllure;

public class SearchTicketStepdefs {
    WebDriver driver = Hooks.driver;
    SearchTicketPage searchTicketPage;
    Logger logger;
    LoginStepDefinitions loginSteps;

    public SearchTicketStepdefs(WebDriver driver) {
        this.driver = Hooks.driver;
        this.searchTicketPage = new SearchTicketPage(Hooks.driver);
        this.logger = LoggerUtils.getLogger(getClass());
        this.loginSteps = new LoginStepDefinitions();
    }

    @Step("{message}")
    public void logStep(String message) {
    }

    @Given("the user redirect to ticket list page")
    public void theUserRedirectToTicketListPage() throws InterruptedException {
        try {
            loginSteps.the_user_redirect_to_helpdesk_site();
            loginSteps.the_user_enter_username();
            loginSteps.the_user_enter_password();
            loginSteps.the_user_clicks_sign_in_cta();
            Thread.sleep(4000);


            logStep("🌐 Navigating to the Ticket List page...");
            logStep("✅ Open Ticket List page...");
            try {
                if (searchTicketPage.startButton.isDisplayed()) {
                    searchTicketPage.clickstartButton();
                }
            }
            catch (NoSuchElementException e){throw e;
            }


//          searchTicketPage.handleFreezePopupIfPresent();
            logToAllure("✅ Ticket List Validation", "Accessed Ticket List Page successfully");
            ScreenshotUtils.attachScreenshotToAllure(driver, "AccessTicketListPage");
        } catch (TimeoutException | NoSuchElementException | AssertionError | IllegalArgumentException e) {
            String msg = "❌ Exception while accessing Ticket List Page: " + e.getMessage();
            logger.error(msg);
            logStep(msg);
            logToAllure("❌ Exception", msg);
            ScreenshotUtils.attachScreenshotToAllure(driver, "TicketList_Exception");
            throw e;
        }
    }

    @When("the user enter the ticket")
    public void theUserEnterTheTicket() {
        try {
            logStep("Entering Ticket ID...");
            searchTicketPage.typeTicketID(ConfigReader.get("enterTicket"));
            logStep("✅ Enter Ticket ID...");
            logToAllure("✅ Entered TicketID", "TicketID entered successfully");
            ScreenshotUtils.attachScreenshotToAllure(driver, "EnteredTicketID");
        } catch (TimeoutException | NoSuchElementException | AssertionError | IllegalArgumentException e) {
            String msg = "❌ Exception while entering and fetching TicketID: " + e.getMessage();
            logStep(msg);
            logToAllure("❌ Exception", msg);
            ScreenshotUtils.attachScreenshotToAllure(driver, "EnterTicketID_Exception");
        }
    }

    @When("the user click the search CTA")
    public void theUserClickTheSearchCTA() {
        try {
            logStep("Clicking on the search CTA...");
            searchTicketPage.clickSearchCTA();
            logStep("✅ Click Search CTA...");
            ScreenshotUtils.attachScreenshotToAllure(driver, "TicketSearch");
        } catch (TimeoutException | NoSuchElementException | AssertionError | IllegalArgumentException e) {
            String msg = "❌ Exception while c: " + e.getMessage();
            logger.error(msg);
            logStep(msg);
            logToAllure("❌ Exception", msg);
            ScreenshotUtils.attachScreenshotToAllure(driver, "TicketSearch_Exception");
        }
    }

    @When("the user view the ticket page by clicking on the link")
    public void theUserViewTheTicketPageByClickingOnTheLink() throws InterruptedException {
        try {
            logStep("Clicking on the Ticket Link...");

            String parentWindow = driver.getWindowHandle();

            searchTicketPage.clickTicketLink();

            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
            wait.until(d -> d.getWindowHandles().size() > 1);
            Thread.sleep(7000);
            for (String windowHandle : driver.getWindowHandles()) {
                if (!windowHandle.equals(parentWindow)) {
                    driver.switchTo().window(windowHandle);
                    break;
                }
            }
            logStep("✅ Switched to Ticket Detail Tab");
            logToAllure("✅ Ticket Link Validation", "Ticket Link opened and focused successfully");

            ScreenshotUtils.attachScreenshotToAllure(driver, "TicketDetailPage");

        } catch (Exception e) {
            String msg = "❌ Exception while clicking Ticket Link: " + e.getMessage();
            logger.error(msg);
            logStep(msg);
            logToAllure("❌ Exception", msg);
            ScreenshotUtils.attachScreenshotToAllure(driver, "TicketLink_Exception");
            throw e;
        }
    }


}
