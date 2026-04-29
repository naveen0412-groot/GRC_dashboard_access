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
import pages.CompanyAuditPage;
import pages.PVTPage;
import utils.*;

import java.awt.*;
import java.awt.datatransfer.DataFlavor;

import static utils.AllureLoggerUtils.logToAllure;

public class CompanyAuditStepDefs {

    WebDriver driver = Hooks.driver;
    CompanyAuditPage companyAuditPage;
    Logger logger;
    AllureLoggerUtils allureLogging;
    WaitUtils wait;
    SeleniumHelperMethods seleniumHelperMethods;
    SearchTicketStepdefs searchTicketsteps;
    LoginStepDefinitions loginSteps;


    public CompanyAuditStepDefs() {
        this.driver = Hooks.driver;
        this.companyAuditPage = new CompanyAuditPage(Hooks.driver);
        this.logger = LoggerUtils.getLogger(getClass());
        this.wait = new WaitUtils(driver, 30);
        this.searchTicketsteps = new SearchTicketStepdefs(Hooks.driver);
        this.loginSteps = new LoginStepDefinitions();
    }

    @Step("{message}")
    public void logStep(String message) {
        // Allure will log this message as a step
    }

    @Given("the user redirect to the Company Audit service page")
    public void the_user_redirect_to_the_company_audit_service_page() {
        try {

            searchTicketsteps.theUserRedirectToTicketListPage();
            searchTicketsteps.theUserEnterTheTicket();
            searchTicketsteps.theUserClickTheSearchCTA();
            searchTicketsteps.theUserViewTheTicketPageByClickingOnTheLink();

            companyAuditPage.crossSaleNew();
            companyAuditPage.createservice(ConfigReader.get("Companyaudit"));
            companyAuditPage.selectingtheservice();
            Thread.sleep(2000);
            companyAuditPage.createbutton();
            logger.info("Service creation initiated.");
            ScreenshotUtils.attachScreenshotToAllure(driver, "2_Service_Created");

            Thread.sleep(15000);
            companyAuditPage.recenttickets();
            Thread.sleep(4000);
            logger.info("Recent Tickets modal opened.");
            ScreenshotUtils.attachScreenshotToAllure(driver, "3_Recent_Tickets_Modal");

            try {
                WebElement firstTicket = driver.findElement(By.cssSelector("tr.ticket_index_tr ul.tcktdetails li a"));
                firstTicket.click();
                logger.info("Clicked first dynamic ticket link.");
                Thread.sleep(10000);
                ScreenshotUtils.attachScreenshotToAllure(driver, "4_Selected_Ticket_Page");

                companyAuditPage.copyurl.click();
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

            logStep("🌐 Navigating to the Company Audit service flow...");
            Thread.sleep(1000);
           logStep("✅ Company Audit service Page");
            logToAllure("✅ Company Audit service - Page Redirection Validation", "Company Audit service - Page redirected successfully");
            ScreenshotUtils.attachScreenshotToAllure(driver, "CompanyAuditURL");
        } catch (IllegalArgumentException | WebDriverException | AssertionError e) {
            String msg = "❌ Exception during Company Audit service: " + e.getMessage();
            logger.error(msg);
            logStep(msg);
            logToAllure("❌ Exception", msg);
            ScreenshotUtils.attachScreenshotToAllure(driver, "CompanyAuditURL_Exception");
        }
        catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
    @When("the user enter the Company Audit Details")
    public void the_user_enter_the_company_audit_details() {
        try {
            companyAuditPage.typeCompanyName(ConfigReader.get("CompanyName1"));
            companyAuditPage.typeGSTDetails(ConfigReader.get("GSTDetails"));
            companyAuditPage.typeBooksOfAccounts(ConfigReader.get("BooksOfAccounts"));
            companyAuditPage.typeTDSChallans(ConfigReader.get("TDSChallans"));
            companyAuditPage.typePFChallans(ConfigReader.get("PFChallans"));
            companyAuditPage.typeProfessionalTax(ConfigReader.get("ProfessionalTax"));
            companyAuditPage.typeLabourWelfareFund(ConfigReader.get("LabourWelfareFund"));
            companyAuditPage.typeSignedFinancials(ConfigReader.get("SignedFinancials"));
            companyAuditPage.typeMSMEDetails(ConfigReader.get("MSMEDetails"));
            companyAuditPage.typePANOfDirectors(ConfigReader.get("PANOfDirectors"));
            companyAuditPage.typePANOfCreditors(ConfigReader.get("PANOfCreditors"));
            companyAuditPage.typeGratuityDetails(ConfigReader.get("GratuityDetails"));
            companyAuditPage.typeTPAuditApplicability(ConfigReader.get("TPAuditApplicability"));
            companyAuditPage.typePurchaseOfShares(ConfigReader.get("PurchaseOfShares"));
            companyAuditPage.typeIssueOfShares(ConfigReader.get("IssueOfShares"));
            companyAuditPage.typeChangeInShareholding(ConfigReader.get("ChangeInShareholding"));
            companyAuditPage.typeOtherDocuments(ConfigReader.get("OtherDocuments"));
            logStep("✅ Company Audit Details update");
            logToAllure("✅ Updated Company Audit Details", "Company Audit details updated successfully");
            ScreenshotUtils.attachScreenshotToAllure(driver, "CompanyAuditDetailsURL");
            Thread.sleep(2000);
            companyAuditPage.clickNextButton();
        } catch (IllegalArgumentException | AssertionError | InterruptedException e) {
            String msg = "❌ Exception during Updating Company Audit Details: " + e.getMessage();
            logger.error(msg);
            logStep(msg);
            logToAllure("❌ Exception", msg);
            ScreenshotUtils.attachScreenshotToAllure(driver, "CompanyAuditDetailsURL_Exception");
        }
    }
}
