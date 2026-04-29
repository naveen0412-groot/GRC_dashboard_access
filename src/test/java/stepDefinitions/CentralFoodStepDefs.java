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
import pages.CentralFoodPage;
import utils.*;

import java.awt.*;
import java.awt.datatransfer.DataFlavor;

import org.openqa.selenium.NoSuchElementException;
import pages.CentralFoodPage;
import utils.*;
import static utils.AllureLoggerUtils.logToAllure;

public class CentralFoodStepDefs {
    WebDriver driver = Hooks.driver;
    CentralFoodPage centralFoodPage;
    Logger logger;
    AllureLoggerUtils allureLogging;
    WaitUtils wait;
    SeleniumHelperMethods seleniumHelperMethods;
    SearchTicketStepdefs searchTicketsteps;
    LoginStepDefinitions loginSteps;

    public CentralFoodStepDefs() {
        this.driver = Hooks.driver;
        this.centralFoodPage = new CentralFoodPage(Hooks.driver);
        this.logger = LoggerUtils.getLogger(getClass());
        this.wait = new WaitUtils(driver, 30);
        this.searchTicketsteps = new SearchTicketStepdefs(Hooks.driver);
        this.loginSteps = new LoginStepDefinitions();
    }

    @Step("{message}")
    public void logStep(String message) {
        // Allure will log this message as a step
    }

    @Given("the user redirect to Central Food License Service")
    public void the_user_redirect_to_central_food_license_service() {
        try {

            searchTicketsteps.theUserRedirectToTicketListPage();
            searchTicketsteps.theUserEnterTheTicket();
            searchTicketsteps.theUserClickTheSearchCTA();
            searchTicketsteps.theUserViewTheTicketPageByClickingOnTheLink();
            centralFoodPage.crossSaleNew();
            centralFoodPage.createservice(ConfigReader.get("Centralfood"));
            centralFoodPage.selectingtheservice();
            Thread.sleep(2000);
            centralFoodPage.createbutton();
            logger.info("Service creation initiated.");
            ScreenshotUtils.attachScreenshotToAllure(driver, "2_Service_Created");
            Thread.sleep(15000);
            centralFoodPage.recenttickets();
            Thread.sleep(4000);
            logger.info("Recent Tickets modal opened.");
            ScreenshotUtils.attachScreenshotToAllure(driver, "3_Recent_Tickets_Modal");
            try {
                WebElement firstTicket = driver.findElement(By.cssSelector("tr.ticket_index_tr ul.tcktdetails li a"));
                firstTicket.click();
                logger.info("Clicked first dynamic ticket link.");
                Thread.sleep(10000);
                ScreenshotUtils.attachScreenshotToAllure(driver, "4_Selected_Ticket_Page");
                centralFoodPage.copyurl.click();
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




            logStep("🌐 Navigating to the Central Food License service page...");
            Thread.sleep(1000);
            logger.info("Navigated to URL: {}", ConfigReader.get("CentralFoodURL"));
            logStep("✅ Open Central Food License Page");
            logToAllure("✅ Central Food License - Redirection Validation", "Central Food License Page redirected successfully");
            Thread.sleep(2000);
            ScreenshotUtils.attachScreenshotToAllure(driver, "CentralFood");
        } catch (IllegalArgumentException | WebDriverException | AssertionError | InterruptedException e) {
            String msg = "❌ Exception during Central Food License page: " + e.getMessage();
            logger.error(msg);
            logStep(msg);
            logToAllure("❌ Exception", msg);
            ScreenshotUtils.attachScreenshotToAllure(driver, "CentralFood_Exception");
        }
    }

    @When("the user update the Company Central Food Details")
    public void the_user_update_the_company_central_food_details() {
        try {
            Thread.sleep(2000);
            centralFoodPage.clickChooseCompanyType();
            centralFoodPage.typeCompanyName(ConfigReader.get("CompanyName"));
            centralFoodPage.clickImportExport();
            centralFoodPage.clickImportECommerce();
            centralFoodPage.typeAddressLine1(ConfigReader.get("Address1"));
            centralFoodPage.typeAddressLine2(ConfigReader.get("Address2"));
            centralFoodPage.typePinCode(ConfigReader.get("FoodAddressPinCode"));
            centralFoodPage.selectState(ConfigReader.get("State"));
            Thread.sleep(3000);
            centralFoodPage.selectDistrict(ConfigReader.get("District"));
            Thread.sleep(3000);
            centralFoodPage.selectSubDivision(ConfigReader.get("SubDivision"));
            Thread.sleep(3000);
            centralFoodPage.selectVillage(ConfigReader.get("Village"));
            Thread.sleep(3000);
            centralFoodPage.clickSameAsAddressCheckbox();
            Thread.sleep(2000);
            centralFoodPage.clickNextButton();
            try{
            if (centralFoodPage.selectTypeOfFood.isDisplayed()) {
                centralFoodPage.selectTypeOfFood();
                centralFoodPage.clickSubmitButton();
            } else {
                logStep("ℹ️ Type of Food checkbox not present, skipping selection");
            }} catch (NoSuchElementException e) {
                logStep("ℹ️ Type of Food checkbox not present, skipping selection");
            }
            try {
                logStep("Trying Select Category...");
                centralFoodPage.clickSelectCategory();
                centralFoodPage.selectFoodCategoryOption();
                centralFoodPage.clickSaveFoodCategory();
            } catch (Exception e) {
                logStep("Select Category not found. Using Change Category...");
                centralFoodPage.clickChangeCategory();
                centralFoodPage.changeFoodCategoryOption();
                centralFoodPage.clickSaveFoodCategory();
            }
            Thread.sleep(2000);
            logStep("✅ Company State Food Details successfully updated");
            logToAllure("Company State Food Details", "Company State Food Details successfully updated");
            ScreenshotUtils.attachScreenshotToAllure(driver, "StateFoodDetails_Updated");
            centralFoodPage.clickNextButton();
            centralFoodPage.clickNextButton(); //it works only twice CTA is clicked
        } catch (IllegalArgumentException | InterruptedException | AssertionError e) {
            String message = "❌ Exception during Company State Food Details: " + e.getMessage();
            logger.error(message, e);
            logStep(message);
            logToAllure("❌ Exception", message);
            ScreenshotUtils.attachScreenshotToAllure(driver, "StateFoodDetails_Exception");
        }
    }

    @When("the user update the Applicant Central Food Details")
    public void the_user_update_the_applicant_central_food_details() {
        try {
            Thread.sleep(2000);
//            try{
//
//                    if (centralFoodPage.deleteButton.isDisplayed() && centralFoodPage.deleteButton.isEnabled()) {
//                        centralFoodPage.clickDeleteButton();
//                        centralFoodPage.clickYesButton();
//
//                }
//            } catch (NoSuchElementException e) {
//                logStep("ℹ️ Delete icon is not present, skipping the action");
//            }
            centralFoodPage.clickAddApplicant();
            centralFoodPage.typeApplicantName(ConfigReader.get("ApplicantName"));
            centralFoodPage.typeApplicantMobileNumber(ConfigReader.get("ApplicantContactNumber"));
            try {
            if (centralFoodPage.enableMakeCharge.isDisplayed() && centralFoodPage.enableMakeCharge.isEnabled())
            {
                centralFoodPage.enableMakeChargeCheckbox();
            }
            }catch (NoSuchElementException e) {
            logStep("ℹ️ Make Charge checkbox not present. Skipping...");
            }
            centralFoodPage.clickDoneButton();
            centralFoodPage.clickFillDetailsByMyself();
            centralFoodPage.typeEmail(ConfigReader.get("ApplicantEmail"));
            centralFoodPage.selectEducationQualification(ConfigReader.get("PVTEducation"));
            centralFoodPage.typeFatherName(ConfigReader.get("FatherName1"));
            centralFoodPage.typeBuildingNumber(ConfigReader.get("Address1"));
            centralFoodPage.typeRoadOrStreet(ConfigReader.get("Address2"));
            centralFoodPage.typePinCode(ConfigReader.get("FoodAddressPinCode"));
            centralFoodPage.clickNextButton();
            centralFoodPage.selectAadharFront.sendKeys(ConfigReader.get("AadharUploadPic1"));
            Thread.sleep(5000);
            centralFoodPage.selectAadharBack.sendKeys(ConfigReader.get("AadharUploadBack"));
            Thread.sleep(5000);
            centralFoodPage.uploadPassportSize.sendKeys(ConfigReader.get("PassportPhoto1"));
            Thread.sleep(5000);
            centralFoodPage.uploadPAN.sendKeys(ConfigReader.get("PAN1"));
            Thread.sleep(5000);
            logStep("✅ Applicant State Food Details successfully updated");
            logToAllure("Applicant State Food Details", "Applicant State Food Details successfully updated");
            ScreenshotUtils.attachScreenshotToAllure(driver, "ApplicantStateFoodDetails_Updated");
            centralFoodPage.clickNextButton();
            centralFoodPage.clickSubmitButton();
        } catch (IllegalArgumentException | WebDriverException | AssertionError  e) {
            String message = "❌ Exception during Applicant State Food Details: " + e.getMessage();
            logger.error(message, e);
            logStep(message);
            logToAllure("❌ Exception", message);
            ScreenshotUtils.attachScreenshotToAllure(driver, "ApplicantStateFoodDetails_Exception");
        }
        catch (InterruptedException e) {
            String message = "❌ Interrupted Exception during Applicant State Food Details: " + e.getMessage();
            logger.error(message, e);
            logStep(message);
            logToAllure("❌ Interrupted Exception", message);
            ScreenshotUtils.attachScreenshotToAllure(driver, "ApplicantStateFoodDetails_Exception");
            throw new RuntimeException(e);
        }
    }

    @When("the user update the Office Central Food Document")
    public void the_user_update_the_office_central_food_document() throws InterruptedException {
        try {
            Thread.sleep(2000);
//            try{
//                for(int i=0;i<2;i++) {
//                    if (centralFoodPage.downArrow.isDisplayed() && centralFoodPage.downArrow.isEnabled()) {
//                        centralFoodPage.clickDownArrow();
//                        centralFoodPage.clickDeleteButton();
//                        centralFoodPage.clickYesButton();
//                    }
//                }
//                for(int i=0;i<3;i++) {
//                    if (centralFoodPage.propertyDocDownArrow.isDisplayed() && centralFoodPage.propertyDocDownArrow.isEnabled()) {
//                        centralFoodPage.clickPropertyDocDownArrow();
//                        centralFoodPage.clickDeleteButton();
//                        centralFoodPage.clickYesButton();
//                    }
//                }

//            } catch (NoSuchElementException e) {
//                logStep("ℹ️ Delete icon is not present, skipping the action");
//            }
            centralFoodPage.uploadCOI.sendKeys(ConfigReader.get("COI"));
            Thread.sleep(5000);
            centralFoodPage.uploadMOA.sendKeys(ConfigReader.get("COI"));
            Thread.sleep(5000);
            centralFoodPage.uploadRentalAgreement.sendKeys(ConfigReader.get("BankStatement"));
            Thread.sleep(5000);
            centralFoodPage.uploadElectricityBill.sendKeys(ConfigReader.get("BankStatement"));
            Thread.sleep(5000);
            centralFoodPage.uploadNOCFromTheLandlord.sendKeys(ConfigReader.get("COI"));
            Thread.sleep(5000);
            logStep("✅ Office State Food Details successfully updated");
            logToAllure("Office State Food Details", "Office State Food Details successfully updated");
            ScreenshotUtils.attachScreenshotToAllure(driver, "OfficeStateFoodDetails_Updated");
            centralFoodPage.clickNextButton();
        } catch (IllegalArgumentException | InterruptedException | AssertionError e) {
            String message = "❌ Exception during Office Company State Food Details: " + e.getMessage();
            logger.error(message, e);
            logStep(message);
            logToAllure("❌ Exception", message);
            ScreenshotUtils.attachScreenshotToAllure(driver, "OfficeStateFoodDetails_Exception");
        }
    }

}
