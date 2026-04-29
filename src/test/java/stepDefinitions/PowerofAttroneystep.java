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
import pages.PowerofAttroneypage;
import utils.ConfigReader;
import utils.LoggerUtils;
import utils.ScreenshotUtils;

import java.awt.*;
import java.awt.datatransfer.DataFlavor;
import java.time.Duration;

import static utils.AllureLoggerUtils.logToAllure;

public class PowerofAttroneystep {

    WebDriver driver = Hooks.driver;
    PowerofAttroneypage power;
    Logger logger;
    WebDriverWait wait;
    SearchTicketStepdefs searchTicketsteps;
    LoginStepDefinitions loginSteps;


    public PowerofAttroneystep()  {
        this.driver = Hooks.driver;
        try {
            this.power = new PowerofAttroneypage(Hooks.driver);
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

    @Given("the user redirect to Power of Attroney")
    public void redirectionofpower() throws InterruptedException {
        try {

            searchTicketsteps.theUserRedirectToTicketListPage();
            searchTicketsteps.theUserEnterTheTicket();
            searchTicketsteps.theUserClickTheSearchCTA();
            searchTicketsteps.theUserViewTheTicketPageByClickingOnTheLink();

            power.crossSaleNew();
            power.createservice(ConfigReader.get("Powerofattroney"));
            power.selectingtheservice();
            Thread.sleep(2000);
            power.createbutton();
            logger.info("Service creation initiated.");
            ScreenshotUtils.attachScreenshotToAllure(driver, "2_Service_Created");

            Thread.sleep(15000);
            power.recenttickets();
            Thread.sleep(4000);
            logger.info("Recent Tickets modal opened.");
            ScreenshotUtils.attachScreenshotToAllure(driver, "3_Recent_Tickets_Modal");

            try {
                WebElement firstTicket = driver.findElement(By.cssSelector("tr.ticket_index_tr ul.tcktdetails li a"));
                firstTicket.click();
                logger.info("Clicked first dynamic ticket link.");
                Thread.sleep(10000);
                ScreenshotUtils.attachScreenshotToAllure(driver, "4_Selected_Ticket_Page");

                power.copyurl.click();
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

            logStep("🌐 Redirecting the user to Power of Attroney page...");
            logStep("✅ Field Details screen for Power of Attroney");
            logToAllure("✅ The user Navigated to Power of Attroney page", "Power of Attroney Page redirected successfully");
            ScreenshotUtils.attachScreenshotToAllure(driver, "Power of Attroney");

        } catch (AssertionError e) {
            String msg = "❌ Exception during Accessing the Customer view : " + e.getMessage();
            logger.error(msg);
            logStep(msg);
            logToAllure("❌ Exception", msg);
            ScreenshotUtils.attachScreenshotToAllure(driver, "Power of Attroney_Exception");
            throw e;
        }
    }

        @When("the user enters the Details of Principal in Power of Attroney")
        public void detailsofprincipal() throws InterruptedException {
            logger.info("===== Starting Details of Principal Section =====");
            try {
                logStep("🏢 Entering Principal Information");
                Thread.sleep(2000);

                power.fullnameofprincipal(ConfigReader.get("directorName1"));
                power.address(ConfigReader.get("Address1"));
                power.contactnumber(ConfigReader.get("DirectorMobile1"));
                power.emailid(ConfigReader.get("DirectorEmail1"));
                power.Panidentificationnumber(ConfigReader.get("Pan1"));
                power.entity(ConfigReader.get("EntityType"));
                power.Authorizedsignatory(ConfigReader.get("AuthorizedSignatoryName"));

                logToAllure("Data Entry", "Principal details populated from Config");
                ScreenshotUtils.attachScreenshotToAllure(driver, "Principal details entered");

                power.principalbutton();
                logStep("✅ Principal details submitted");
                Thread.sleep(2000);
            } catch (AssertionError e) {
                logger.error("❌ Error in Details of Principal: " + e.getMessage());
                ScreenshotUtils.attachScreenshotToAllure(driver, "Error_Principal_Section");
                throw e;
            }
        }

        @When("the user enters the Details of Agent in Power of Attroney")
        public void detailsofagent() throws InterruptedException {
            logger.info("===== Starting Details of Agent Section =====");
            try {
                logStep("👤 Entering Agent/Attorney Information");
                Thread.sleep(2000);

                power.attroneyname(ConfigReader.get("FatherName2"));
                power.attroneyaddress(ConfigReader.get("Address2"));
                power.attroneycontactnumber(ConfigReader.get("DirectorMobile2"));
                power.attroneyemailid(ConfigReader.get("DirectorEmail2"));
                power.relationshipwithprincipal(ConfigReader.get("natureofact"));
                power.natureofentity(ConfigReader.get("EntityType"));

                logToAllure("Data Entry", "Agent details populated from Config");
                ScreenshotUtils.attachScreenshotToAllure(driver, "Agent details entered");

                power.agentbutton();
                logStep("✅ Agent details submitted");
                Thread.sleep(2000);
            } catch (AssertionError e) {
                logger.error("❌ Error in Details of Agent: " + e.getMessage());
                ScreenshotUtils.attachScreenshotToAllure(driver, "Error_Agent_Section");
                throw e;
            }
        }

        @When("the user enters the Purpose in Power of Attroney")
        public void purpose() throws InterruptedException {
            logger.info("===== Starting Purpose Section =====");
            try {
                logStep("📝 Entering Transaction Purpose");
                Thread.sleep(2000);

                power.Poa(ConfigReader.get("city"));
                power.purposeoftransaction(ConfigReader.get("descrip"));

                logToAllure("Data Entry", "Purpose details populated: " + ConfigReader.get("city"));
                ScreenshotUtils.attachScreenshotToAllure(driver, "Purpose details entered");

                power.purposebutton();
                logStep("✅ Purpose section submitted");
                Thread.sleep(2000);
            } catch (AssertionError e) {
                logger.error("❌ Error in Purpose section: " + e.getMessage());
                throw e;
            }
        }

        @Then("the user enters the Term in Power of Attroney")
        public void term() throws InterruptedException {
            logger.info("===== Starting Term/Expiry Section =====");
            try {
                logStep("⏳ Entering Validity and Term details");
                Thread.sleep(2000);

                power.dateofpoa(ConfigReader.get("city"));
                power.expirydate(ConfigReader.get("descrip"));
                power.revocable(ConfigReader.get("descrip"));

                logToAllure("Data Entry", "Term and Expiry dates entered");
                ScreenshotUtils.attachScreenshotToAllure(driver, "Term details entered");

                power.termbutton();
                logStep("🏁 Power of Attorney Process Completed");
                Thread.sleep(2000);
            } catch (AssertionError e) {
                logger.error("❌ Error in Term section: " + e.getMessage());
                ScreenshotUtils.attachScreenshotToAllure(driver, "Error_Term_Section");
                throw e;
            }
        }
    }



