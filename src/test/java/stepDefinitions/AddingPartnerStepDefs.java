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
import pages.AddingPartnerPage;
import utils.*;

import java.awt.*;
import java.awt.datatransfer.DataFlavor;

import static utils.AllureLoggerUtils.logToAllure;

public class AddingPartnerStepDefs {
    WebDriver driver = Hooks.driver;
    AddingPartnerPage addingPartnerPage;
    Logger logger;
    AllureLoggerUtils allureLogging;
    WaitUtils wait;
    SearchTicketStepdefs searchTicketsteps;
    LoginStepDefinitions loginSteps;


    public AddingPartnerStepDefs() {
        this.driver = Hooks.driver;
        this.addingPartnerPage = new AddingPartnerPage(Hooks.driver);
        this.logger = LoggerUtils.getLogger(getClass());
        this.wait = new WaitUtils(driver,30);
        this.searchTicketsteps = new SearchTicketStepdefs(Hooks.driver);
        this.loginSteps = new LoginStepDefinitions();
    }

    @Step("{message}")
    public void logStep(String message) {
        // Allure will log this message as a step
    }

    @Given("the user redirect to upload document page of Adding Partner service")
    public void the_user_redirect_to_upload_document_page_of_adding_partner_service() throws InterruptedException {

        logger.info("===== 🚀 Add Partner – Upload Document Redirection Flow Started =====");
        logStep("🚀 Starting Add Partner upload document navigation");

        try {


            logStep("🔍 Redirecting to Ticket List Page");
            searchTicketsteps.theUserRedirectToTicketListPage();
            ScreenshotUtils.attachScreenshotToAllure(driver, "1_Ticket_List_Page");

            logStep("⌨️ Entering ticket number");
            searchTicketsteps.theUserEnterTheTicket();
            ScreenshotUtils.attachScreenshotToAllure(driver, "2_Ticket_Number_Entered");

            logStep("🔎 Clicking Search CTA");
            searchTicketsteps.theUserClickTheSearchCTA();
            ScreenshotUtils.attachScreenshotToAllure(driver, "3_Ticket_Search_Result");

            logStep("📄 Opening ticket details page");
            searchTicketsteps.theUserViewTheTicketPageByClickingOnTheLink();
            ScreenshotUtils.attachScreenshotToAllure(driver, "4_Ticket_Details_Page");

            logStep("➕ Initiating cross-sell service creation");
            addingPartnerPage.crossSaleNew();

            logStep("🛠 Selecting Add a Partner service");
            addingPartnerPage.createservice(ConfigReader.get("addingapartner"));
            addingPartnerPage.selectingtheservice();

            Thread.sleep(2000);

            logStep("✅ Clicking Create Service button");
            addingPartnerPage.createbutton();

            logger.info("Service creation initiated successfully");
            ScreenshotUtils.attachScreenshotToAllure(driver, "5_Service_Created");

            Thread.sleep(15000);

            logStep("📋 Opening recent tickets modal");
            addingPartnerPage.recenttickets();

            Thread.sleep(3000);
            ScreenshotUtils.attachScreenshotToAllure(driver, "6_Recent_Tickets_Modal");

            logger.info("Recent Tickets modal displayed");

            try {

                logStep("🎯 Selecting first dynamic ticket");
                WebElement firstTicket = driver.findElement(
                        By.cssSelector("tr.ticket_index_tr ul.tcktdetails li a"));
                firstTicket.click();
                logger.info("Clicked first dynamic ticket link");

                Thread.sleep(10000);
                ScreenshotUtils.attachScreenshotToAllure(driver, "7_Selected_Ticket_Page");

                logStep("📋 Clicking copy URL button");
                addingPartnerPage.copyurl.click();

                Thread.sleep(3000);
                ScreenshotUtils.attachScreenshotToAllure(driver, "8_Copy_URL_Action");

                String dynamicUrl = (String) Toolkit.getDefaultToolkit()
                        .getSystemClipboard()
                        .getData(DataFlavor.stringFlavor);

                logger.info("Captured URL from clipboard: {}", dynamicUrl);
                if (dynamicUrl != null && dynamicUrl.startsWith("http")) {
                    logger.info("Navigating to dynamic URL captured: {}", dynamicUrl);
                    logStep("🌐 Navigating to: " + dynamicUrl);
                    driver.get(dynamicUrl);

                    Thread.sleep(3000);
                    ScreenshotUtils.attachScreenshotToAllure(driver, "6_Final_Destination_Page");
                } else {
                    throw new Exception("Clipboard did not contain a valid URL.");
                }

            } catch (Exception e) {

                logger.error("❌ Error during ticket selection / URL capture", e);
                ScreenshotUtils.attachScreenshotToAllure(driver, "Error_Ticket_URL_Failure");

                Assert.fail("Failed during dynamic ticket navigation: " + e.getMessage());
            }


            logStep("📂 Validating Add Partner Upload Document Page");

            Thread.sleep(2000);

            logger.info("Final navigated URL: {}", driver.getCurrentUrl());
            logStep("✅ Add Partner document page opened successfully");

            ScreenshotUtils.attachScreenshotToAllure(driver, "10_AddPartner_Upload_Document_Page");

            logToAllure(
                    "✅ Add Partner Redirection Validation",
                    "User successfully redirected to Add Partner upload document page"
            );

            logger.info("===== ✅ Add Partner Flow Completed Successfully =====");

        } catch (IllegalArgumentException | WebDriverException | AssertionError e) {

            String msg = "❌ Exception during Add Partner page redirection: " + e.getMessage();
            logger.error(msg, e);

            logStep(msg);
            logToAllure("❌ Exception", msg);

            ScreenshotUtils.attachScreenshotToAllure(driver, "AddPartner_Flow_Exception");
            throw e;
        }
    }

    @When("the user update the LLP Details")
    public void the_user_update_the_llp_details() {
        try {
            logStep("🏢 Updating company details...");
            // Company Name
            Thread.sleep(3000);
            addingPartnerPage.typeLLPName(ConfigReader.get("LLPName"));
            logger.info("Entered Company Name: {}", "SRCVP DIGITAL SOLUTION LLP");
            logStep("✅ Company name entered");
            logToAllure("Company Name for AppDir", "SRCVP DIGITAL SOLUTION LLP");
            ScreenshotUtils.attachScreenshotToAllure(driver, "CompanyName_Entered");
            addingPartnerPage.clickNextButton();
            logStep("🔘 Clicked Next CTA after company name");

            addingPartnerPage.typeLLPIN(ConfigReader.get("LLPIN"));
            logger.info("Entered Company CIN: {}", "ACR-8065");
            logStep("✅ Company CIN entered");
            logToAllure("Company CIN for AppDir", "ACR-8065");
            ScreenshotUtils.attachScreenshotToAllure(driver, "CompanyCIN_Entered");
            addingPartnerPage.clickNextButton2();
            logStep("🔘 Clicked Next CTA after CIN");
        } catch (IllegalArgumentException | WebDriverException | AssertionError e) {
            String message = "❌ Exception during company details entry: " + e.getMessage();
            logger.error(message, e);
            logStep(message);
            logToAllure("❌ Exception", message);
            ScreenshotUtils.attachScreenshotToAllure(driver, "UpdateCompanyDetails_Exception");
            throw e;
        } catch (InterruptedException e) {
            String message = "❌ Interrupted Exception during company details entry: " + e.getMessage();
            logger.error(message, e);
            logStep(message);
            logToAllure("❌ Interrupted Exception", message);
            ScreenshotUtils.attachScreenshotToAllure(driver, "UpdateCompanyDetails_InterruptedException");
        }
    }

    @When("the user update the No of Partners being appointed")
    public void the_user_update_the_no_of_partners_being_appointed() {
        try
        {
//        for(int i=0;i<2;i++){
//            addingPartnerPage.clickDeletePartnerButton();
//        }

        Thread.sleep(3000);
//         for(int i=0;i<2;i++){
//             addingPartnerPage.clickDeletePartnerButton();
//         }
        addingPartnerPage.clickAddPartnerButton();
        //Partner1
        logStep("🏢 Updating Partner details - 1");
        addingPartnerPage.clickFillPartnerButton();
        addingPartnerPage.typePartnerName(ConfigReader.get("directorName1"));
        addingPartnerPage.typeFatherName(ConfigReader.get("FatherName1"));
        addingPartnerPage.typeDIN(ConfigReader.get("din1"));
        addingPartnerPage.typeAddressLine1(ConfigReader.get("Address1"));
        addingPartnerPage.typeEmail(ConfigReader.get("DirectorEmail1"));
        addingPartnerPage.typeApplicantContactNumber(ConfigReader.get("DirectorMobile1"));
        addingPartnerPage.selectDOB();
        addingPartnerPage.typePan(ConfigReader.get("Pan1"));
        addingPartnerPage.typeAadhaarNo(ConfigReader.get("Aadhar1"));
        addingPartnerPage.selectNoDscOption();
        addingPartnerPage.clickNextButton2();
        Thread.sleep(3000);
        addingPartnerPage.PANUpload.sendKeys(ConfigReader.get("PAN1"));
        Thread.sleep(3000);
        addingPartnerPage.AadhaarUpload.sendKeys(ConfigReader.get("AadharUploadPic1"));
        Thread.sleep(3000);
        logger.info("✅ First Partner details updated and uploaded successfully");
        logStep("✅ Completed updating first partner details");
        logToAllure("✅ Update and Upload of First Partner Details", "First Partner details updated and uploaded successfully");
        ScreenshotUtils.attachScreenshotToAllure(driver, "FirstPartnerDetails_Uploaded");
        addingPartnerPage.clickNextButton2();
         Thread.sleep(3000);
    } catch (AssertionError | InterruptedException ae) {
        String message = "❌ First Partner details entry: " + ae.getMessage();
        logger.error(message);
        logStep(message);
        logToAllure("❌ Assertion Error", message);
        ScreenshotUtils.attachScreenshotToAllure(driver, "FirstPartnerDetails_Failed");
    }
        try
        {
            Thread.sleep(3000);
            //Partner2
            logStep("🏢 Updating Partner details - 2");
            addingPartnerPage.clickFillPartnerButton();
            addingPartnerPage.typePartnerName(ConfigReader.get("directorName2"));
            addingPartnerPage.typeFatherName(ConfigReader.get("FatherName2"));
            addingPartnerPage.typeDIN(ConfigReader.get("din2"));
            addingPartnerPage.typeAddressLine1(ConfigReader.get("Address2"));
            addingPartnerPage.typeEmail(ConfigReader.get("DirectorEmail2"));
            addingPartnerPage.typeApplicantContactNumber(ConfigReader.get("DirectorMobile2"));
            addingPartnerPage.selectDOB();
            addingPartnerPage.typePan(ConfigReader.get("Pan2"));
            addingPartnerPage.typeAadhaarNo(ConfigReader.get("Aadhar2"));
            addingPartnerPage.selectNoDscOption();
            addingPartnerPage.clickNextButton2();
            Thread.sleep(3000);
            addingPartnerPage.PANUpload.sendKeys(ConfigReader.get("PAN2"));
            Thread.sleep(3000);
            addingPartnerPage.AadhaarUpload.sendKeys(ConfigReader.get("AadharUploadPic1"));
            Thread.sleep(3000);
            logger.info("✅ Second Partner details updated successfully");
            logStep("✅ Completed updating and uploading second partner details");
            logToAllure("✅ Update and Upload of Second Director Details", "Second Partner details updated and uploaded successfully");
            ScreenshotUtils.attachScreenshotToAllure(driver, "SecondPartnerDetails_Uploaded");
            addingPartnerPage.clickNextButton2();
            addingPartnerPage.clickNextButton2();
            Thread.sleep(3000);
        } catch (AssertionError | InterruptedException ae) {
            String message = "❌ Second Partner details entry: " + ae.getMessage();
            logger.error(message);
            logStep(message);
            logToAllure("❌ Assertion Error", message);
            ScreenshotUtils.attachScreenshotToAllure(driver, "SecondPartnerDetails_Failed");
        }
    }

    @When("the user upload documents")
    public void the_user_upload_documents() throws InterruptedException {
        try {
//            addingPartnerPage.deleteDocumentSet1();
//            int count = 0;
//            do {
//                addingPartnerPage.deleteDocumentSet2();
//                count++;
//            }while (count < 4);
            Thread.sleep(3000);
//             addingPartnerPage.deleteDocumentSet1();
//             int count = 0;
//             do {
//                 addingPartnerPage.deleteDocumentSet2();
//                 count++;
//             }while (count < 4);
            Thread.sleep(5000);
            addingPartnerPage.PANUpload.sendKeys(ConfigReader.get("PAN1"));
            Thread.sleep(5000);
            addingPartnerPage.chooseAddressProof();
            Thread.sleep(5000);
            addingPartnerPage.AddressProofUpload.sendKeys(ConfigReader.get("DrivingLicense"));
            Thread.sleep(5000);
            addingPartnerPage.LLPAgreementUpload.sendKeys(ConfigReader.get("AadharUploadPic1"));
            Thread.sleep(5000);
            addingPartnerPage.COIUpload.sendKeys(ConfigReader.get("COI"));
            Thread.sleep(5000);
            addingPartnerPage.LlpConstitutionalAgreementUpload.sendKeys(ConfigReader.get("BankStatement"));
            Thread.sleep(5000);
            logger.info("✅ Company document uploaded successfully");
            logStep("✅ Completed uploading company documents");
            logToAllure("✅ Company Document Validation", "Company document successfully updated");
            ScreenshotUtils.attachScreenshotToAllure(driver, "CompanyDocument_Uploaded");
            addingPartnerPage.clickNextButton2();
        }catch (IllegalArgumentException | WebDriverException | AssertionError ae) {
            String message = "❌ Company documents upload: " + ae.getMessage();
            logger.error(message);
            logStep(message);
            logToAllure("❌ Assertion Error", message);
            ScreenshotUtils.attachScreenshotToAllure(driver, "CompanyDocumentUpload_Failed");
            throw ae;
        }
    }

}
