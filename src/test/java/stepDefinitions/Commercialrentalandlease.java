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
import pages.Commercialrentalpage;
import utils.ConfigReader;
import utils.LoggerUtils;
import utils.ScreenshotUtils;

import java.awt.*;
import java.awt.datatransfer.DataFlavor;
import java.time.Duration;

import static utils.AllureLoggerUtils.logToAllure;

public class Commercialrentalandlease {

    WebDriver driver = Hooks.driver;
    Commercialrentalpage commerce;
    Logger logger;
    WebDriverWait wait;
    SearchTicketStepdefs searchTicketsteps;
    LoginStepDefinitions loginSteps;


    public Commercialrentalandlease() {
        this.driver = Hooks.driver;
        try {
            this.commerce = new Commercialrentalpage(Hooks.driver);
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

    @Given("the user redirect to Commercial rental and lease services")

    public void Commercial_rental_and_lease_services_page() throws InterruptedException {
        try {

            searchTicketsteps.theUserRedirectToTicketListPage();
            searchTicketsteps.theUserEnterTheTicket();
            searchTicketsteps.theUserClickTheSearchCTA();
            searchTicketsteps.theUserViewTheTicketPageByClickingOnTheLink();

            commerce.crossSaleNew();
            commerce.createservice(ConfigReader.get("CommercialRental"));
            commerce.selectingtheservice();
            Thread.sleep(2000);
            commerce.createbutton();
            logger.info("Service creation initiated.");
            ScreenshotUtils.attachScreenshotToAllure(driver, "2_Service_Created");

            Thread.sleep(15000);
            commerce.recenttickets();
            Thread.sleep(4000);
            logger.info("Recent Tickets modal opened.");
            ScreenshotUtils.attachScreenshotToAllure(driver, "3_Recent_Tickets_Modal");

            try {
                WebElement firstTicket = driver.findElement(By.cssSelector("tr.ticket_index_tr ul.tcktdetails li a"));
                firstTicket.click();
                logger.info("Clicked first dynamic ticket link.");
                Thread.sleep(10000);
                ScreenshotUtils.attachScreenshotToAllure(driver, "4_Selected_Ticket_Page");

                commerce.copyurl.click();
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




            logStep("✅ Field Details screen for Commercial rental and lease agreement page");
            logToAllure("✅ The user Navigated to Commercial rental and lease agreement page", "Commercial rental and lease agreement page redirected successfully");
            ScreenshotUtils.attachScreenshotToAllure(driver, "Commercial rental and lease agreement page redirected successfully");

        } catch (IllegalArgumentException | WebDriverException | AssertionError e) {
            String msg = "❌ Exception during Accessing the Customer view : " + e.getMessage();
            logger.error(msg);
            logStep(msg);
            logToAllure("❌ Exception", msg);
            ScreenshotUtils.attachScreenshotToAllure(driver, "Commercial rental and lease agreement page_Exception");
            throw e;
        }
    }

    @When("the user updates the Term of Lease")
    public void termoflease() throws InterruptedException {

        logger.info("===== 🔐 Term of Lease Flow Started =====");
        logStep("📄 Updating Term of Lease details");

        logStep("🟦 Filling Basic Lease Details");
        Thread.sleep(2000);

        commerce.commencementDate(assertValue("companytype"));
        commerce.leaseDuration(assertValue("searchbus"));
        commerce.optionToRenew(assertValue("shareholderfathername"));
        commerce.rentCommencementDate(assertValue("shareholderfathername2"));
        commerce.lockInPeriod(assertValue("Roadstreet"));

        ScreenshotUtils.attachScreenshotToAllure(driver, "Basic Lease Details Filled");
        logToAllure("Basic Lease Details", "Entered all basic lease details");

        commerce.nextbuttonnt();
        Thread.sleep(3000);

    }

    @Then("the user updating other details in commercial rental service")
    public void otherinfos() throws InterruptedException {

        logStep("🟦 Filling Rent & Payment Details");

        commerce.monthlyRent(assertValue("FatherName1"));
        commerce.rentPerSq(assertValue("EducationalQualification1"));
        commerce.modeOfPayment(assertValue("Pan1"));
        commerce.dateofPayment(assertValue("Aadhar1"));
        commerce.gracePeriod(assertValue("EducationalQualification2"));
        commerce.penaltyofInterest(assertValue("AreaOfOccupation"));
        commerce.rentEscalation(assertValue("PoliceStation"));
        commerce.rentFreeOrNo(assertValue("ReasonForResignation"));

        ScreenshotUtils.attachScreenshotToAllure(driver, "Rent & Payment Details Filled");
        logToAllure("Rent & Payment", "Rent and payment details entered");

        commerce.rentandpaymentbutton();
        Thread.sleep(3000);

        logStep("🟦 Filling Security Deposit Details");

        commerce.securityDeposit(assertValue("ApplicantName"));
        commerce.depositinterest(assertValue("InventorAddress"));
        commerce.modeofpayment(assertValue("PatentType"));
        commerce.depositrefundable(assertValue("WorkTitle"));
        commerce.conditionsforrefund(assertValue("WorkNature"));
        commerce.depositcanthead(assertValue("LLPName"));

        ScreenshotUtils.attachScreenshotToAllure(driver, "Security Deposit Details Filled");
        logToAllure("Security Deposit", "Security deposit details entered");

        commerce.securitydepositbutton();
        Thread.sleep(3000);

        logStep("🟦 Filling Possession Details");

        commerce.handingoverpossession(assertValue("OldName"));
        commerce.lessorresponsible(assertValue("NewName"));
        commerce.fitoutperiodallowed(assertValue("Reason"));
        commerce.bearscostoffitout(assertValue("DocumentName"));
        commerce.lessorapproval(assertValue("InventionIdea"));

        ScreenshotUtils.attachScreenshotToAllure(driver, "Possession Details Filled");
        logToAllure("Possession", "Possession details entered");

        commerce.Possessionbutton();

        ScreenshotUtils.attachScreenshotToAllure(driver, "Term of Lease Completed");
        logStep("✅ Commercial service completed successfully");
        logger.info("===== ✅ Commercial service completed successfully =====");

        Thread.sleep(3000);
    }


    @When("the user update the Details of Parties in commercial rental service")
    public void details_of_parties() throws InterruptedException {

        logger.info("===== 🧾 Details of Parties Flow Started =====");
        logStep("🧾 Updating Details of Parties");

        logStep("🟦 Entering Lessor & Lessee Details");
        Thread.sleep(2000);
        commerce.lessorName(assertValue("directorName1"));
        commerce.addressoflessor(assertValue("Address1"));
        commerce.nameoflessee(assertValue("directorName2"));
        commerce.addressoflessee(assertValue("Address2"));
        commerce.natureofentity(assertValue("complainvalue"));
        commerce.authorizedSignatory(assertValue("AuthorizedSignatoryName"));
        commerce.contactDetailsEmail(assertValue("companyemail"));
        commerce.contactDetailsPhone(assertValue("companymobile"));
        commerce.natureOfBusinessOfLessee(assertValue("naturebusiness"));
        commerce.purposeForWhichPremisesWillBeUsed(assertValue("Roadstreet"));

        ScreenshotUtils.attachScreenshotToAllure(driver, "Lessor & Lessee Details Filled");
        logToAllure("Lessor & Lessee", "Lessor and Lessee details entered");

        commerce.basicpage1button();
        Thread.sleep(3000);

        logStep("🟦 Entering Property Details");

        commerce.typeofproperty(assertValue("typeofproperty"));
        commerce.addressofPremises(assertValue("InventorAddress"));
        commerce.totalBuiltupArea(assertValue("WorkTitle"));
        commerce.carpetArea(assertValue("WorkNature"));
        commerce.floorNumber(assertValue("ApplicantAddress"));
        commerce.parkingincluded(assertValue("LLPName"));
        commerce.premisefurnished(assertValue("ApplicantType"));
        commerce.inventorydetails(assertValue("InventionTitle"));

        ScreenshotUtils.attachScreenshotToAllure(driver, "Property Details Filled");
        logToAllure("Property Details", "Property information entered");

        commerce.basicpage2button();

        ScreenshotUtils.attachScreenshotToAllure(driver, "Details of Parties Completed");
        logStep("✅ Details of Parties updated successfully");
        logger.info("===== ✅ Details of Parties Flow Completed =====");

        Thread.sleep(3000);
    }


    private String assertValue(String key) {
        String value = ConfigReader.get(key);

        if (value == null || value.trim().isEmpty()) {
            logger.error("❌ Test data missing for key: {}", key);
            throw new RuntimeException("❌ Test data missing for key: " + key);
        }

        logger.info("✔ Test data validated → {} = {}", key, value);
        return value;
    }

    private void logAllureStep(String step) {
        io.qameta.allure.Allure.step(step);
        ScreenshotUtils.attachScreenshotToAllure(driver, step);
        logger.info("📸 Screenshot captured → {}", step);
    }
}


