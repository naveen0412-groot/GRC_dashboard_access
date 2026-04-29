package stepDefinitions;

import hooks.Hooks;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.qameta.allure.Step;
import org.apache.logging.log4j.Logger;

import org.junit.Assert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.*;
import pages.UploadDocumentPage;
import utils.*;

import java.awt.*;
import java.awt.datatransfer.DataFlavor;

import static utils.AllureLoggerUtils.logToAllure;

public class AppDirStepDefinitions {
    WebDriver driver = Hooks.driver;
    UploadDocumentPage uploadDocumentPage;
    Logger logger;
    WaitUtils wait;
    SearchTicketStepdefs searchTicketsteps;
    LoginStepDefinitions loginSteps;

    public AppDirStepDefinitions() {
        this.driver = Hooks.driver;
        this.uploadDocumentPage = new UploadDocumentPage(Hooks.driver);
        this.logger = LoggerUtils.getLogger(getClass());
        this.wait = new WaitUtils(driver, 30);
        this.searchTicketsteps = new SearchTicketStepdefs(Hooks.driver);
        this.loginSteps = new LoginStepDefinitions();
    }

    @Step("{message}")
    public void logStep(String message) {
        // Allure will log this message as a step
    }

    @Given("the user open upload document link")
    public void the_user_open_upload_document_link() throws InterruptedException {
        try {
            searchTicketsteps.theUserRedirectToTicketListPage();
            searchTicketsteps.theUserEnterTheTicket();
            searchTicketsteps.theUserClickTheSearchCTA();
            searchTicketsteps.theUserViewTheTicketPageByClickingOnTheLink();
            logger.info("Search and navigation to ticket page completed.");
            ScreenshotUtils.attachScreenshotToAllure(driver, "1_Ticket_Navigation_Done");

            uploadDocumentPage.crossSaleNew();
            uploadDocumentPage.createservice(ConfigReader.get("AppofDirector"));
            uploadDocumentPage.selectingtheservice();
            Thread.sleep(2000);
            uploadDocumentPage.createbutton();
            logger.info("Service creation initiated for Appointment of Director.");
            ScreenshotUtils.attachScreenshotToAllure(driver, "2_Service_Created");

            Thread.sleep(20000);
            uploadDocumentPage.recenttickets();
            Thread.sleep(4000);
            logger.info("Recent Tickets modal opened.");
            ScreenshotUtils.attachScreenshotToAllure(driver, "3_Recent_Tickets_Modal");

            try {
                WebElement firstTicket = driver.findElement(By.cssSelector("tr.ticket_index_tr ul.tcktdetails li a"));
                firstTicket.click();
                logger.info("Clicked first dynamic ticket link.");
                Thread.sleep(10000);
                ScreenshotUtils.attachScreenshotToAllure(driver, "4_Selected_Ticket_Page");

                uploadDocumentPage.copyurl.click();
                logger.info("Copy button clicked.");
                Thread.sleep(4000);
                ScreenshotUtils.attachScreenshotToAllure(driver, "5_After_Copy_Action");

                String dynamicUrl = (String) Toolkit.getDefaultToolkit().getSystemClipboard().getData(DataFlavor.stringFlavor);

                if (dynamicUrl != null && dynamicUrl.startsWith("http")) {
                    logger.info("Captured Dynamic URL: {}", dynamicUrl);
                    logStep("🌐 Navigating to captured URL: " + dynamicUrl);
                    driver.get(dynamicUrl);

                    Thread.sleep(3000);
                    ScreenshotUtils.attachScreenshotToAllure(driver, "6_Final_Destination_Page");
                } else {
                    throw new Exception("Clipboard did not contain a valid URL.");
                }

            } catch (Exception e) {
                logger.error("❌ Error during ticket selection/URL capture: {}", e.getMessage());
                ScreenshotUtils.attachScreenshotToAllure(driver, "Error_Capture_Failure");
                Assert.fail("Failed during dynamic link capture: " + e.getMessage());
            }

            logger.info("Navigated to URL: {}", ConfigReader.get("AppointmentDirectorURL"));
            logStep("✅ Open Appointment Director Document Page");
            logToAllure("✅ Appointment Director Redirection Validation", "Appointment Director Page redirected successfully");
            ScreenshotUtils.attachScreenshotToAllure(driver, "AppointmentDirectorPage_Final");

        } catch (TimeoutException e) {
            String msg = "❌ Exception during Appointment Director page: " + e.getMessage();
            logger.error(msg);
            logStep(msg);
            logToAllure("❌ Exception", msg);
            ScreenshotUtils.attachScreenshotToAllure(driver, "AppointmentDirectorURL_Timeout_Exception");
            throw e;

        } catch (IllegalArgumentException | AssertionError e) {
            logToAllure("❌ Validation Failure", e.getMessage());
            ScreenshotUtils.attachScreenshotToAllure(driver, "AppointmentDirectorURL_Validation_Exception");
            throw e;

        } catch (WebDriverException e) {
            logToAllure("❌ WebDriver Failure", e.getMessage());
            ScreenshotUtils.attachScreenshotToAllure(driver, "AppointmentDirectorURL_WebDriver_Exception");
            throw e;
        }
    }

    @When("the user update company details")
    public void the_user_update_company_details() {
        try {
            logStep("🏢 Updating company details...");
            // Company Name
            uploadDocumentPage.typeCompanyName(ConfigReader.get("CompanyName"));
            logger.info("Entered Company Name: {}", "Uber9 Business Process Services Private Limited");
            logStep("✅ Company name entered");
            logToAllure("Company Name for AppDir", "Uber9 Business Process Services Private Limited");
            ScreenshotUtils.attachScreenshotToAllure(driver, "CompanyName_Entered");
            uploadDocumentPage.clickNextCTA();
            logStep("🔘 Clicked Next CTA after company name");

            // CIN
            uploadDocumentPage.typeCompanyCIN(ConfigReader.get("CompanyCIN"));
            logger.info("Entered Company CIN: {}", "U74900TN2014PTC098414");
            logStep("✅ Company CIN entered");
            logToAllure("Company CIN for AppDir", "U74900TN2014PTC098414");
            ScreenshotUtils.attachScreenshotToAllure(driver, "CompanyCIN_Entered");
            uploadDocumentPage.clickNextCTA();
            logStep("🔘 Clicked Next CTA after CIN");
        } catch (IllegalArgumentException | WebDriverException | AssertionError e) {
            String message = "❌ Exception during company details entry: " + e.getMessage();
            logger.error(message, e);
            logStep(message);
            logToAllure("❌ Exception", message);
            ScreenshotUtils.attachScreenshotToAllure(driver, "UpdateCompanyDetails_Exception");
            throw e;
        }
    }

    @When("the user update director details")
    public void the_user_update_director_details() {
        try {
//            for (int i = 0; i < 2; i++) {
//                uploadDocumentPage.deleteDirector();
//            }
            //Director 1
            try {
                Thread.sleep(5000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            uploadDocumentPage.clickAddDirectorCTA();
            logStep("👤 Clicked Add Director CTA");
            // Add Director
            logStep("🏢 Updating Director details - 1");
            uploadDocumentPage.clickFillDetailsCTA();
            logStep("✍️ Clicked Fill Details CTA for Director 1");
            // Director Details - 1
            uploadDocumentPage.typeDirectorName(ConfigReader.get("directorName1"));
            uploadDocumentPage.typeDin(ConfigReader.get("din1"));
            uploadDocumentPage.typeFatherName(ConfigReader.get("FatherName1"));
            uploadDocumentPage.typeAddressLine1(ConfigReader.get("Address1"));
            uploadDocumentPage.typeDirectorEmail(ConfigReader.get("DirectorEmail1"));
            uploadDocumentPage.typeDirectorMobile(ConfigReader.get("DirectorMobile1"));
            uploadDocumentPage.typeCurrentOccupation(ConfigReader.get("CurrentOccupation1"));
            uploadDocumentPage.typeEducationalQualification(ConfigReader.get("EducationalQualification1"));
            uploadDocumentPage.selectDOB();
            uploadDocumentPage.typePan(ConfigReader.get("Pan1"));
            uploadDocumentPage.typeAadhaarNo(ConfigReader.get("Aadhar1"));
            uploadDocumentPage.typeOccupationType(ConfigReader.get("OccupationType"));
            uploadDocumentPage.typeAreaOfOccupation(ConfigReader.get("AreaOfOccupation"));
            uploadDocumentPage.typePoliceStation(ConfigReader.get("PoliceStation"));
            uploadDocumentPage.typeDscAvailabilityAppDir(ConfigReader.get("DSCAvailability"));
            uploadDocumentPage.clickNextCTA();
            uploadDocumentPage.PANUpload.sendKeys(ConfigReader.get("PAN1"));
            try {
                Thread.sleep(5000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            uploadDocumentPage.PassportSize.sendKeys(ConfigReader.get("PassportPhoto1"));
            try {
                Thread.sleep(5000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            logger.info("✅ Successful Update First Director Details");
            logStep("✅ Completed entering first director details");
            logToAllure("✅ Update and Upload of First Director Details", "First Director details and documents successfully updated");
            ScreenshotUtils.attachScreenshotToAllure(driver, "FirstDirectorDetails_Uploaded");
            uploadDocumentPage.clickNextCTA();
            try {
                Thread.sleep(5000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        } catch (AssertionError ae) {
            String message = "❌ First director details entry: " + ae.getMessage();
            logger.error(message);
            logStep(message);
            logToAllure("❌ Assertion Error", message);
            ScreenshotUtils.attachScreenshotToAllure(driver, "UpdateFirstDirector_Failed");
        }

        try {
            logStep("🏢 Updating Director details - 2");
            // Add Director
            uploadDocumentPage.clickFillDetailsCTA();
            logStep("✍️ Clicked Fill Details CTA for Director 2");
            // Director Details - 2
            uploadDocumentPage.typeDirectorName(ConfigReader.get("directorName2"));
            uploadDocumentPage.typeDin(ConfigReader.get("din2"));
            uploadDocumentPage.typeFatherName(ConfigReader.get("FatherName2"));
            uploadDocumentPage.typeAddressLine1(ConfigReader.get("Address2"));
            uploadDocumentPage.typeDirectorEmail(ConfigReader.get("DirectorEmail2"));
            uploadDocumentPage.typeDirectorMobile(ConfigReader.get("DirectorMobile2"));
            uploadDocumentPage.typeCurrentOccupation(ConfigReader.get("CurrentOccupation2"));
            uploadDocumentPage.typeEducationalQualification(ConfigReader.get("EducationalQualification2"));
            uploadDocumentPage.selectDOB();
            uploadDocumentPage.typePan(ConfigReader.get("Pan2"));
            uploadDocumentPage.typeAadhaarNo(ConfigReader.get("Aadhar2"));
            uploadDocumentPage.typeOccupationType(ConfigReader.get("OccupationType"));
            uploadDocumentPage.typeAreaOfOccupation(ConfigReader.get("AreaOfOccupation"));
            uploadDocumentPage.typePoliceStation(ConfigReader.get("PoliceStation"));
            uploadDocumentPage.typeDscAvailabilityAppDir(ConfigReader.get("DSCAvailability"));
            uploadDocumentPage.clickNextCTA();
            uploadDocumentPage.PANUpload.sendKeys(ConfigReader.get("PAN2"));
            try {
                Thread.sleep(5000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            uploadDocumentPage.PassportSize.sendKeys(ConfigReader.get("PassportPhoto2"));
            try {
                Thread.sleep(5000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            logger.info("✅ Successful Update Second Director Details");
            logStep("✅ Completed entering second director details");
            logToAllure("✅ Update and Upload of Second Director Details", "Second Director details updated and uploaded successfully");
            ScreenshotUtils.attachScreenshotToAllure(driver, "SecondDirectorDetails_Uploaded");
            uploadDocumentPage.clickNextCTA();
            try {
                Thread.sleep(5000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }

            uploadDocumentPage.clickNextCTA();
        } catch (AssertionError ae) {
            String message = "❌ Second director details entry: " + ae.getMessage();
            logger.error(message);
            logStep(message);
            logToAllure("❌ Assertion Error", message);
            ScreenshotUtils.attachScreenshotToAllure(driver, "UpdateSecondDirector_Failed");
        }

    }

    @Then("the user upload the documents")
    public void the_user_upload_the_documents() throws InterruptedException {
        try {
//            uploadDocumentPage.clickTab3();
//            int count = 0,count1 = 0;
//            do {
//                uploadDocumentPage.deleteDocumentSet1();
//                count++;
//            } while (count < 3);
//           do {
//                uploadDocumentPage.deleteDocumentSet2();
//                count1++;
//            }while (count1 < 2);
            Thread.sleep(5000);
            uploadDocumentPage.PANUpload.sendKeys(ConfigReader.get("PAN1"));
            Thread.sleep(7000);
            uploadDocumentPage.COIUpload.sendKeys(ConfigReader.get("COI"));
            Thread.sleep(5000);
            uploadDocumentPage.MOAUpload.sendKeys(ConfigReader.get("BankStatement"));
            Thread.sleep(5000);
            uploadDocumentPage.chooseAddressProof();
            Thread.sleep(5000);
            uploadDocumentPage.AddressProofUpload.sendKeys(ConfigReader.get("DrivingLicense"));
            Thread.sleep(5000);
            uploadDocumentPage.CERTIFICATE_OF_INCORPORATIONUpload.sendKeys(ConfigReader.get("COI"));
            Thread.sleep(5000);
            logStep("🏢 Uploading the company documents");
            logger.info("✅ Director Company document uploaded successfully");
            logStep("✅ Completed uploading director company documents");
            logToAllure("✅ Director Company Document Validation", "Director Company document successfully updated");
            ScreenshotUtils.attachScreenshotToAllure(driver, "DirCompanyDocument_Uploaded");
            uploadDocumentPage.clickNextCTA();

        } catch (AssertionError ae) {
            String message = "❌ Company documents upload: " + ae.getMessage();
            logger.error(message);
            logStep(message);
            logToAllure("❌ Assertion Error", message);
            ScreenshotUtils.attachScreenshotToAllure(driver, "DirCompanyDocumentUpload_Failed");
            throw ae;
        }
    }


}
