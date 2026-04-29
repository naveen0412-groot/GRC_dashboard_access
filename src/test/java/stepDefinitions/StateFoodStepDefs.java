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
import pages.StateFoodPage;
import utils.*;

import java.awt.*;
import java.awt.datatransfer.DataFlavor;
import java.util.NoSuchElementException;

import static utils.AllureLoggerUtils.logToAllure;

public class StateFoodStepDefs {
    WebDriver driver = Hooks.driver;
    StateFoodPage stateFoodPage;
    Logger logger;
    AllureLoggerUtils allureLogging;
    WaitUtils wait;
    SeleniumHelperMethods seleniumHelperMethods;
    SearchTicketStepdefs searchTicketsteps;
    LoginStepDefinitions loginSteps;

    public StateFoodStepDefs() {
        this.driver = Hooks.driver;
        this.stateFoodPage = new StateFoodPage(Hooks.driver);
        this.logger = LoggerUtils.getLogger(getClass());
        this.wait = new WaitUtils(driver, 30);
        this.seleniumHelperMethods = new SeleniumHelperMethods();
        this.searchTicketsteps = new SearchTicketStepdefs(Hooks.driver);
        this.loginSteps = new LoginStepDefinitions();
    }

    @Step("{message}")
    public void logStep(String message) {
        // Allure will log this message as a step
    }

    @Given("the user redirect to State Food License Service")
    public void the_user_redirect_to_state_food_license_service() {
        try {
            logStep("🌐 Navigating to the State Food License service page...");

            searchTicketsteps.theUserRedirectToTicketListPage();
            searchTicketsteps.theUserEnterTheTicket();
            searchTicketsteps.theUserClickTheSearchCTA();
            searchTicketsteps.theUserViewTheTicketPageByClickingOnTheLink();

            stateFoodPage.crossSaleNew();
            stateFoodPage.createservice(ConfigReader.get("Statefoodlicense"));
            stateFoodPage.selectingtheservice();
            Thread.sleep(2000);
            stateFoodPage.createbutton();
            logger.info("Service creation initiated.");
            ScreenshotUtils.attachScreenshotToAllure(driver, "2_Service_Created");

            Thread.sleep(15000);
            stateFoodPage.recenttickets();
            Thread.sleep(4000);
            logger.info("Recent Tickets modal opened.");
            ScreenshotUtils.attachScreenshotToAllure(driver, "3_Recent_Tickets_Modal");

            try {
                WebElement firstTicket = driver.findElement(By.cssSelector("tr.ticket_index_tr ul.tcktdetails li a"));
                firstTicket.click();
                logger.info("Clicked first dynamic ticket link.");
                Thread.sleep(10000);
                ScreenshotUtils.attachScreenshotToAllure(driver, "4_Selected_Ticket_Page");

                stateFoodPage.copyurl.click();
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
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
            logger.info("Navigated to URL: {}", ConfigReader.get("StateFoodURL"));
            logStep("✅ Open State Food License Page");
            logToAllure("✅ State Food License - Redirection Validation", "State Food License Page redirected successfully");
            Thread.sleep(4000);
            ScreenshotUtils.attachScreenshotToAllure(driver, "StateFood");

        } catch (WebDriverException | NoSuchElementException | AssertionError e) {
            String msg = "❌ Exception during State Food License page: " + e.getMessage();
            logger.error(msg);
            logStep(msg);
            logToAllure("❌ Exception", msg);
            ScreenshotUtils.attachScreenshotToAllure(driver, "StateFood_Exception");
            throw e;
        }
        catch (InterruptedException e) {
            String msg = "❌ Interrupted Exception during State Food License page: " + e.getMessage();
            logStep(msg);
            logToAllure("❌ Interrupted Exception", msg);
            ScreenshotUtils.attachScreenshotToAllure(driver, "StateFood_Exception");
        }
    }

    @When("the user update the Company State Food Details")
    public void the_user_update_the_company_state_food_details() {
        try {
            if (seleniumHelperMethods.isElementDisplayed(stateFoodPage.chooseCompanyType)) {
                stateFoodPage.clickChooseCompanyType();
            }
            stateFoodPage.typeCompanyName(ConfigReader.get("CompanyName"));
            stateFoodPage.typeAddressLine1(ConfigReader.get("Address1"));
            stateFoodPage.typeAddressLine2(ConfigReader.get("Address2"));
            stateFoodPage.typePinCode(ConfigReader.get("FoodAddressPinCode"));
            stateFoodPage.selectState(ConfigReader.get("State"));
            stateFoodPage.selectDistrict(ConfigReader.get("District"));
            stateFoodPage.selectSubDivision(ConfigReader.get("SubDivision"));
            stateFoodPage.selectVillage(ConfigReader.get("Village"));
            if(!seleniumHelperMethods.isElementEnabled(stateFoodPage.clickSameAsAddress)) {
                stateFoodPage.clickSameAsAddressCheckbox();
            }
            stateFoodPage.clickNextButton();
            stateFoodPage.selectTypeOfFood();
            if (seleniumHelperMethods.isElementDisplayed(stateFoodPage.chooseCompanyType)) {
                stateFoodPage.clickChooseCompanyType();
                stateFoodPage.clickSubmitButton();
            }
            if (seleniumHelperMethods.isElementDisplayed(stateFoodPage.selectCategory)) {
                stateFoodPage.clickSelectCategory();
            } else {
                stateFoodPage.clickChangeCategory();
                if(!seleniumHelperMethods.isElementEnabled(stateFoodPage.selectFoodCategory)){
                    stateFoodPage.selectFoodCategoryOption();
                    stateFoodPage.clickSaveFoodCategory();
                    stateFoodPage.clickNextButton();
                }
            }
            stateFoodPage.clickNextButton();
            logStep("✅ Company State Food Details successfully updated");
            logToAllure("Company State Food Details", "Company State Food Details successfully updated");
            ScreenshotUtils.attachScreenshotToAllure(driver, "StateFoodDetails_Updated");
            stateFoodPage.clickNextButton();
        } catch (AssertionError e) {
            String message = "❌ Exception during Company State Food Details: " + e.getMessage();
            logger.error(message, e);
            logStep(message);
            logToAllure("❌ Exception", message);
            ScreenshotUtils.attachScreenshotToAllure(driver, "StateFoodDetails_Exception");
        }
    }

    @When("the user update the Applicant State Food Details")
    public void the_user_update_the_applicant_state_food_details() {
        try {
            Thread.sleep(2000);
            try{

                if (stateFoodPage.deleteButton.isDisplayed() && stateFoodPage.deleteButton.isEnabled()) {
                    stateFoodPage.clickDeleteButton();
                    stateFoodPage.clickYesButton();

                }
            } catch (org.openqa.selenium.NoSuchElementException e) {
                logStep("ℹ️ Delete icon is not present, skipping the action");
            }
            stateFoodPage.clickAddApplicant();
            stateFoodPage.typeApplicantName(ConfigReader.get("ApplicantName"));
            stateFoodPage.typeApplicantMobileNumber(ConfigReader.get("ApplicantContactNumber"));
            if(seleniumHelperMethods.isElementEnabled(stateFoodPage.enableMakeCharge)){
                stateFoodPage.enableMakeChargeCheckbox();
            }
            stateFoodPage.clickDoneButton();
            stateFoodPage.clickFillDetailsByMyself();
            stateFoodPage.typeEmail(ConfigReader.get("ApplicantEmail"));
            stateFoodPage.selectEducationQualification(ConfigReader.get("PVTEducation"));
            stateFoodPage.typeFatherName(ConfigReader.get("FatherName1"));
            stateFoodPage.typeBuildingNumber(ConfigReader.get("Address1"));
            stateFoodPage.typeRoadOrStreet(ConfigReader.get("Address2"));
            stateFoodPage.typePinCode(ConfigReader.get("FoodAddressPinCode"));
            stateFoodPage.clickNextButton();
            stateFoodPage.selectAadharFront.sendKeys(ConfigReader.get("AadharUploadPic1"));
            Thread.sleep(5000);
            stateFoodPage.selectAadharBack.sendKeys(ConfigReader.get("AadharUploadBack"));
            Thread.sleep(5000);
            stateFoodPage.uploadPassportSize.sendKeys(ConfigReader.get("PassportPhoto1"));
            Thread.sleep(5000);
            stateFoodPage.uploadPAN.sendKeys(ConfigReader.get("PAN1"));
            Thread.sleep(5000);
            logStep("✅ Applicant State Food Details successfully updated");
            logToAllure("Applicant State Food Details", "Applicant State Food Details successfully updated");
            ScreenshotUtils.attachScreenshotToAllure(driver, "ApplicantStateFoodDetails_Updated");
            stateFoodPage.clickNextButton();
            stateFoodPage.clickSubmitButton();
        } catch (AssertionError | InterruptedException e) {
            String message = "❌ Exception during Applicant State Food Details: " + e.getMessage();
            logger.error(message, e);
            logStep(message);
            logToAllure("❌ Exception", message);
            ScreenshotUtils.attachScreenshotToAllure(driver, "ApplicantStateFoodDetails_Exception");
        }
    }

    @When("the user update the Office State Food Document")
    public void the_user_update_the_office_state_food_document() {
        try {
            stateFoodPage.uploadCOI.sendKeys(ConfigReader.get("COI"));
            Thread.sleep(5000);
            stateFoodPage.uploadMOA.sendKeys(ConfigReader.get("COI"));
            Thread.sleep(5000);
            stateFoodPage.uploadRentalAgreement.sendKeys(ConfigReader.get("BankStatement"));
            Thread.sleep(5000);
            stateFoodPage.uploadElectricityBill.sendKeys(ConfigReader.get("BankStatement"));
            Thread.sleep(5000);
            stateFoodPage.uploadNOCFromTheLandlord.sendKeys(ConfigReader.get("COI"));
            Thread.sleep(5000);
            logStep("✅ Office State Food Details successfully updated");
            logToAllure("Office State Food Details", "Office State Food Details successfully updated");
            ScreenshotUtils.attachScreenshotToAllure(driver, "OfficeStateFoodDetails_Updated");
            stateFoodPage.clickNextButton();
        } catch (AssertionError | InterruptedException e) {
            String message = "❌ Exception during Office Company State Food Details: " + e.getMessage();
            logger.error(message, e);
            logStep(message);
            logToAllure("❌ Exception", message);
            ScreenshotUtils.attachScreenshotToAllure(driver, "OfficeStateFoodDetails_Exception");
        }
    }

}
