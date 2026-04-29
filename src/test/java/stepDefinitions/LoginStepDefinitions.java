package stepDefinitions;

import hooks.Hooks;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.qameta.allure.Step;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import pages.LoginPage;
import utils.ConfigReader;
import utils.LoggerUtils;
import utils.ScreenshotUtils;

import java.io.File;
import java.time.Duration;
import java.util.*;
import java.util.NoSuchElementException;

import static utils.AllureLoggerUtils.logToAllure;

public class LoginStepDefinitions {
    WebDriver driver = Hooks.driver;
    LoginPage loginPage;
    Logger logger;
    WebDriverWait wait;


    public LoginStepDefinitions() {
        this.driver = Hooks.driver;
        this.loginPage = new LoginPage(Hooks.driver);
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        this.logger = LoggerUtils.getLogger(getClass());
    }

    @Step("{message}")
    public void logStep(String message) {
    }

    @Given("the user redirect to helpdesk site")
    public void the_user_redirect_to_helpdesk_site() {
        try {
            logStep("🌐 Navigating to the Helpdesk Site...");
            driver.get(ConfigReader.get("helpdeskURL"));
            logger.info("Navigated to URL: {}", ConfigReader.get("helpdeskURL"));
            logStep("✅ Open Helpdesk Site");
            logToAllure("✅ Redirection Validation", "Page redirected successfully");
            ScreenshotUtils.attachScreenshotToAllure(driver, "HelpdeskSite");
        } catch (IllegalArgumentException | WebDriverException | AssertionError e) {
            String msg = "❌ Exception while accessing helpdeskURL: " + e.getMessage();
            logger.error(msg);
            logStep(msg);
            logToAllure("❌ Exception", msg);
            ScreenshotUtils.attachScreenshotToAllure(driver, "helpdeskURL_Exception");
            throw e;
        }
    }

    @When("the user enter Username")
    public void the_user_enter_username() throws InterruptedException {
        try {
            logStep("✍️ Updating Username...");
            Thread.sleep(2000);
            loginPage.typeEmail(ConfigReader.get("HelpdeskEmail"));
            logStep("✅ Enter Email Address");
            logToAllure("✅ Email Address Validation", "Email address entered successfully");
            ScreenshotUtils.attachScreenshotToAllure(driver, "EmailAddress");
        } catch (AssertionError e)  {
            String msg = "❌ Exception while entering Email address: " + e.getMessage();
            logger.error(msg);
            logStep(msg);
            logToAllure("❌ Exception", msg);
            ScreenshotUtils.attachScreenshotToAllure(driver, "emailID_Exception");
            throw e;
        }
    }

    @When("the user enter Password")
    public void the_user_enter_password() {
        try {
            logStep("✍️ Updating Password...");
            loginPage.typePassword(ConfigReader.get("HelpdeskPassword"));
            logStep("✅ Enter Password");
            logToAllure("✅ Password Validation", "Password successfully");
            ScreenshotUtils.attachScreenshotToAllure(driver, "Password");
        } catch (AssertionError e)  {
            String msg = "❌ Exception while entering Password: " + e.getMessage();
            logger.error(msg);
            logStep(msg);
            logToAllure("❌ Exception", msg);
            ScreenshotUtils.attachScreenshotToAllure(driver, "Password_Exception");
            throw e;
        }
    }

    @Then("the user clicks SignIn CTA")
    public void the_user_clicks_sign_in_cta() throws InterruptedException {
        try {
            logStep("✍️ Clicking Login Button...");
            loginPage.clickLoginButton();

            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

            boolean isOtpRequired = false;
            try {
                wait.until(ExpectedConditions.visibilityOf(loginPage.otptrigger));
                isOtpRequired = true;
                logStep("Session expired. OTP flow required.");
            } catch (TimeoutException e) {
                logStep("OTP trigger not found. Checking if dashboard is visible...");
                try {
                    wait.until(ExpectedConditions.visibilityOf(loginPage.dashboardElement));
                    logStep("✅ Already logged in. Skipping OTP flow.");
                    return;
                } catch (TimeoutException ex) {
                    throw new RuntimeException("Neither OTP screen nor Dashboard appeared after login click.");
                }
            }

            if (isOtpRequired) {
                performOtpFlow();
            }

        } catch (Exception e) {
            logStep("❌ Error during Login/OTP flow: " + e.getMessage());
            ScreenshotUtils.attachScreenshotToAllure(driver, "SignIn_Error");
            throw e;
        }
    }

    private void performOtpFlow() throws InterruptedException {
        loginPage.otptrigger();
        Thread.sleep(2000);
        loginPage.sendotpbtn();
        String registrationTab = driver.getWindowHandle();
        driver.switchTo().newWindow(WindowType.TAB);
        driver.get(ConfigReader.get("Gmaillogin"));
        loginPage.email(ConfigReader.get("gmaillogin"));
        loginPage.setNextbutton();
        Thread.sleep(3000);
        loginPage.password(ConfigReader.get("HelpdeskPassword"));
        loginPage.setNextbutton();
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
        wait.until(ExpectedConditions.elementToBeClickable(loginPage.chatclick));
        loginPage.chatclick();
        driver.switchTo().defaultContent();
        wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(By.id("gtn-roster-iframe-id")));
        loginPage.chatbotclicked();
        driver.switchTo().defaultContent();
        String otp = loginPage.fetchotp();
        logStep("✅ OTP Extracted: " + otp);
        driver.close();
        driver.switchTo().window(registrationTab);
        wait.until(ExpectedConditions.visibilityOf(loginPage.otpinput));
        loginPage.otpinput.clear();
        loginPage.otpinput.sendKeys(otp);
        logStep("✅ OTP pasted and Gmail tab closed.");
        Thread.sleep(2000);
        loginPage.otpsubmit();
    }

    }



