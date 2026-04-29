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
import pages.Section12gpage;
import utils.*;

import java.awt.*;
import java.awt.datatransfer.DataFlavor;

import static utils.AllureLoggerUtils.logToAllure;

public class Section12gstep {
    WebDriver driver = Hooks.driver;
    Section12gpage section12;
    Logger logger;
    AllureLoggerUtils allureLogging;
    WaitUtils wait;
    SearchTicketStepdefs searchTicketsteps;
    LoginStepDefinitions loginSteps;

    public Section12gstep() {
        this.driver = Hooks.driver;
        this.section12 = new Section12gpage(Hooks.driver);
        this.logger = LoggerUtils.getLogger(getClass());
        this.wait = new WaitUtils(driver,30);
        this.searchTicketsteps = new SearchTicketStepdefs(Hooks.driver);
        this.loginSteps = new LoginStepDefinitions();

    }

    @Step("{message}")
    public void logStep(String message) {
    }
    @Given("the user redirect to Section 12A and 80G")
    public void section12redirection() throws InterruptedException {
        try {
            searchTicketsteps.theUserRedirectToTicketListPage();
            searchTicketsteps.theUserEnterTheTicket();
            searchTicketsteps.theUserClickTheSearchCTA();
            searchTicketsteps.theUserViewTheTicketPageByClickingOnTheLink();
            logger.info("Search and navigation to ticket page completed.");
            ScreenshotUtils.attachScreenshotToAllure(driver, "1_Ticket_Navigation_Done");

            section12.crossSaleNew();
            section12.createservice(ConfigReader.get("section12"));
            section12.selectingtheservice();
            Thread.sleep(2000);
            section12.createbutton();
            logger.info("Service creation initiated for Section 12A and 80G.");
            ScreenshotUtils.attachScreenshotToAllure(driver, "2_Service_Created");

            Thread.sleep(15000);
            section12.recenttickets();
            Thread.sleep(4000);
            logger.info("Recent Tickets modal opened.");
            ScreenshotUtils.attachScreenshotToAllure(driver, "3_Recent_Tickets_Modal");

            try {
                WebElement firstTicket = driver.findElement(By.cssSelector("tr.ticket_index_tr ul.tcktdetails li a"));
                firstTicket.click();
                logger.info("Clicked first dynamic ticket link in the modal.");
                Thread.sleep(10000);
                ScreenshotUtils.attachScreenshotToAllure(driver, "4_Selected_Ticket_Page");

                section12.copyurl.click();
                logger.info("Copy button clicked.");
                Thread.sleep(4000);
                ScreenshotUtils.attachScreenshotToAllure(driver, "5_After_Copy_Action");

                String dynamicUrl = (String) Toolkit.getDefaultToolkit().getSystemClipboard().getData(DataFlavor.stringFlavor);

                if (dynamicUrl != null && dynamicUrl.startsWith("http")) {
                    logger.info("Captured Dynamic URL: {}", dynamicUrl);
                    logStep("🌐 Navigating to captured URL: " + dynamicUrl);
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

            logger.info("Navigated to URL: {}", ConfigReader.get("Section12g"));
            logStep("✅ Open Section 12A and 80G Page");
            logToAllure("✅ Section 12A and 80G Validation", "Section 12A and 80G Page redirected successfully");
            ScreenshotUtils.attachScreenshotToAllure(driver, "Section_12A_80G_Final");

        } catch (WebDriverException | AssertionError e) {
            String msg = "❌ Exception during Accessing the Customer view: " + e.getMessage();
            logger.error(msg);
            logStep(msg);
            logToAllure("❌ Exception", msg);
            ScreenshotUtils.attachScreenshotToAllure(driver, "Section12g_Exception");
            throw e;
        }
    }

    @When("the user entering the Details of NGO in Section 12")
    public void detailsofNGOinSection12() throws InterruptedException {
        logStep("🏢 Starting Details of NGO entry in Section 12");
        logger.info("===== NGO Details Section Started =====");

        try {
            logStep("📝 Filling basic NGO text fields");
            section12.FoundationName(ConfigReader.get("directorName1"));
            section12.mobileNumber(ConfigReader.get("DirectorMobile1"));
            section12.emailID(ConfigReader.get("DirectorEmail2"));
            section12.address(ConfigReader.get("ApplicantAddress"));
            section12.incomeTaxLoginID(ConfigReader.get("GSTDetails"));
            section12.incomeTaxLoginPassword(ConfigReader.get("lutpassword"));

            logger.info("Basic NGO text fields populated");

            logStep("🖱️ Selecting NGO nature, constitution, and deed details from dropdowns");
            String natureVal = ConfigReader.get("natureofact");
            String constVal = ConfigReader.get("typeofconstitution");
            String deedVal = ConfigReader.get("trustded");

            section12.selectReactDropdown(section12.natureofactivity, natureVal, "NatureOfActivity");
            section12.selectReactDropdown(section12.typeofconstution, constVal, "ConstitutionType");
            section12.selectReactDropdown(section12.trustdeed, deedVal, "TrustDeed");

            ScreenshotUtils.attachScreenshotToAllure(driver, "NGO_Details_Form_Filled");
            logToAllure("NGO Details Form", "Verified all fields are entered before clicking submit");

            logStep("🔘 Clicking 'Details of NGO' button to proceed");
            section12.DetailsofNgobutton();
            Thread.sleep(2000);

            logger.info("===== NGO Details Section Completed Successfully =====");

        } catch (AssertionError e) {
            logger.error("❌ Error in NGO Details Section: " + e.getMessage());
            ScreenshotUtils.attachScreenshotToAllure(driver, "NGO_Details_Error");
            throw e;
        }
    }
    @When("the user enters the member details in Section 12")
    public void memberdetailssection12() throws InterruptedException {
        logger.info("===== Member Details Section Started =====");

        logStep("🧹 Cleaning up existing partner entries");
//        for(int i=0; i<2; i++){
//            section12.clickDeletePartnerButton();
//        }
        section12.clickAddDirectorButton();

        // PARTNER 1
        try {
            logStep("👤 Updating Partner Details - 1");
            section12.clickFillPartnerButton();

            logStep("⌨️ Entering Partner 1 personal and tax details");
            section12.typePartnerName(ConfigReader.get("directorName1"));
            section12.typeEmail(ConfigReader.get("DirectorEmail1"));
            section12.typeApplicantContactNumber(ConfigReader.get("DirectorMobile1"));
            section12.directorincometax(ConfigReader.get("GSTDetails"));
            section12.directorincometaxpass(ConfigReader.get("lutpassword"));
            section12.typeAddressLine1(ConfigReader.get("Address1"));

            section12.direprofilenext();
            Thread.sleep(2000);

            logStep("📤 Uploading Partner 1 Documents (PAN & Aadhar)");
            section12.Pancard.sendKeys(ConfigReader.get("PAN1"));
            Thread.sleep(5000);

            section12.aadharcardfront.sendKeys(ConfigReader.get("AadharUploadPic1"));
            Thread.sleep(5000);

            section12.aadharcardback.sendKeys(ConfigReader.get("AadharUploadBack"));
            Thread.sleep(5000);

            ScreenshotUtils.attachScreenshotToAllure(driver, "FirstPartnerDetails_Uploaded");
            logStep("🔘 Clicking upload for Partner 1");
            section12.diruploadbutton();
            Thread.sleep(5000);

            logger.info("✅ First Partner details updated and uploaded successfully");
            logToAllure("Success", "Partner 1 verification complete");

        } catch (AssertionError ae) {
            handleAssertionError("First Partner", ae);
        }

        // PARTNER 2
        try {
            logStep("👤 Updating Partner Details - 2");
            section12.clickFillPartnerButton();

            logStep("⌨️ Entering Partner 2 personal and tax details");
            section12.typePartnerName(ConfigReader.get("directorName2"));
            section12.typeAddressLine1(ConfigReader.get("Address2"));
            section12.typeEmail(ConfigReader.get("DirectorEmail2"));
            section12.directorincometax(ConfigReader.get("GSTDetails"));
            section12.directorincometaxpass(ConfigReader.get("lutpassword"));
            section12.typeApplicantContactNumber(ConfigReader.get("DirectorMobile2"));

            section12.direprofilenext();
            Thread.sleep(5000);

            logStep("📤 Uploading Partner 2 Documents");
            section12.Pancard.sendKeys(ConfigReader.get("PAN2"));
            Thread.sleep(5000);

            section12.aadharcardfront.sendKeys(ConfigReader.get("AadharUploadPic1"));
            Thread.sleep(5000);

            section12.aadharcardback.sendKeys(ConfigReader.get("AadharUploadBack"));
            Thread.sleep(5000);

            ScreenshotUtils.attachScreenshotToAllure(driver, "SecondPartnerDetails_Uploaded");
            logStep("🔘 Finalizing Partner 2 and clicking main upload");
            section12.diruploadbutton();
            section12.uploadbbutton();

            Thread.sleep(3000);
            logger.info("✅ Second Partner details and final form submission successful");

        } catch (AssertionError ae) {
            handleAssertionError("Second Partner", ae);
        }
    }

    private void handleAssertionError(String partner, AssertionError ae) {
        String message = "❌ " + partner + " details entry failed: " + ae.getMessage();
        logger.error(message);
        logStep(message);
        logToAllure("❌ Assertion Error", message);
        ScreenshotUtils.attachScreenshotToAllure(driver, partner + "_Failed");
        throw ae;
    }
}

