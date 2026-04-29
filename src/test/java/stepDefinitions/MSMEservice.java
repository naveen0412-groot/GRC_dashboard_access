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
import pages.msmepage;
import utils.*;

import java.awt.*;
import java.awt.datatransfer.DataFlavor;

import static utils.AllureLoggerUtils.logToAllure;

public class MSMEservice {
    WebDriver driver = Hooks.driver;
    msmepage msme;
    Logger logger;
    AllureLoggerUtils allureLogging;
    WaitUtils wait;
    SeleniumHelperMethods seleniumHelperMethods;
    SearchTicketStepdefs searchTicketsteps;
    LoginStepDefinitions loginSteps;

    public MSMEservice() {
        this.driver = Hooks.driver;
        this.msme = new msmepage(Hooks.driver);
        this.logger = LoggerUtils.getLogger(getClass());
        this.wait = new WaitUtils(driver, 30);
        this.searchTicketsteps = new SearchTicketStepdefs(Hooks.driver);
        this.loginSteps = new LoginStepDefinitions();
    }

    @Step("{message}")
    public void logStep(String message) {
    }

    @Given("the user redirect to Company details page of msme service")
    public void the_user_redirect_to_Company_details_page_of_msme_service() throws InterruptedException {
        try {

            searchTicketsteps.theUserRedirectToTicketListPage();
            searchTicketsteps.theUserEnterTheTicket();
            searchTicketsteps.theUserClickTheSearchCTA();
            searchTicketsteps.theUserViewTheTicketPageByClickingOnTheLink();

            msme.crossSaleNew();
            msme.createservice(ConfigReader.get("MSME"));
            msme.selectingtheservice();
            Thread.sleep(2000);
            msme.createbutton();
            logger.info("Service creation initiated.");
            ScreenshotUtils.attachScreenshotToAllure(driver, "2_Service_Created");

            Thread.sleep(15000);
            msme.recenttickets();
            Thread.sleep(4000);
            logger.info("Recent Tickets modal opened.");
            ScreenshotUtils.attachScreenshotToAllure(driver, "3_Recent_Tickets_Modal");

            try {
                WebElement firstTicket = driver.findElement(By.cssSelector("tr.ticket_index_tr ul.tcktdetails li a"));
                firstTicket.click();
                logger.info("Clicked first dynamic ticket link.");
                Thread.sleep(10000);
                ScreenshotUtils.attachScreenshotToAllure(driver, "4_Selected_Ticket_Page");

                msme.copyurl.click();
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
            logStep("🌐 Navigating to the MSME Customer view...");
            logStep("✅ Open MSME Customer view");
            logToAllure("✅ MSME Certification - Redirection Validation", "MSME Page redirected successfully");
            ScreenshotUtils.attachScreenshotToAllure(driver, "MSMEpage");

        } catch (AssertionError e) {
            String msg = "❌ Exception during MSME page: " + e.getMessage();
            logger.error(msg);
            logStep(msg);
            logToAllure("❌ Exception", msg);
            ScreenshotUtils.attachScreenshotToAllure(driver, "msmeurl_Exception");
            throw e;
        }
    }

    @When("the user selecting the company type")
    public void Msmecompanyselection() throws InterruptedException {

        logStep("🔍 Selecting company type → No (Not another company)");
        msme.noitsanothercompany();
        logger.info("Company selection successful → No");
        Thread.sleep(2000);
        ScreenshotUtils.attachScreenshotToAllure(driver, "Company selection - No");
    }

    @When("the user updates the bank and address in msme")
    public void the_user_updates_the_bank_and_address_in_msme() {

        logStep("🏢 Starting MSME company basic details update");
        logger.info("===== MSME Company Details Flow Started =====");

        try {

            String companyType = ConfigReader.get("companyvaluemsme");
            String companyName = ConfigReader.get("companynamemsme");

            if (companyType == null || companyType.isEmpty())
                throw new RuntimeException("❌ Company type missing in config");

            if (companyName == null || companyName.isEmpty())
                throw new RuntimeException("❌ Company name missing in config");

             msme.Comptype(companyType);
            Thread.sleep(2000);
            msme.com_name(companyName);
            Thread.sleep(2000);

            logger.info("Company type selected → {}", companyType);
            logger.info("Company name entered → {}", companyName);
            Thread.sleep(2000);
            ScreenshotUtils.attachScreenshotToAllure(driver, "Company name & type entered");

            String mobile = ConfigReader.get("companymobile");
            String email = ConfigReader.get("companyemail");

            if (mobile == null || mobile.length() < 10)
                throw new RuntimeException("❌ Invalid company mobile number");

            if (email == null || !email.contains("@"))
                throw new RuntimeException("❌ Invalid company email");

            msme.com_mobile(mobile);
            Thread.sleep(2000);
            msme.com_email(email);
            Thread.sleep(2000);
            msme.com_dob(ConfigReader.get("DOB2"));
            Thread.sleep(2000);

            logger.info("Company mobile entered → {}", mobile);
            logger.info("Company email entered → {}", email);

            msme.nature(ConfigReader.get("naturebusiness"));
            Thread.sleep(2000);
            msme.gstinvalue();
            msme.manufacture();

            logStep("✅ Company basic & business details updated");
            ScreenshotUtils.attachScreenshotToAllure(driver, "Company details updated");

            msme.nextcta();
            logger.info("Clicked Next CTA");

        } catch (AssertionError | InterruptedException e) {
            logger.error("❌ MSME company details update failed", e);
            ScreenshotUtils.attachScreenshotToAllure(driver, "Company details failure");
            throw new RuntimeException("❌ MSME company details update failed → " + e.getMessage(), e);
        }
    }

    @When("the user updates the second subtab and address in msme")
    public void the_user_updates_the_second_subtab_and_address_in_msme() throws InterruptedException {

        logStep("🏠 Starting MSME address & bank update");
        logger.info("===== MSME Address & Bank Flow Started =====");

        try {

            ScreenshotUtils.attachScreenshotToAllure(driver, "Address screen opened");
            msme.flatno(ConfigReader.get("flatno"));
            msme.area(ConfigReader.get("AreaLocality"));
            msme.road(ConfigReader.get("Roadstreet"));
            Thread.sleep(2000);
            msme.pincodecomp(ConfigReader.get("Pincode"));
            Thread.sleep(2000);
            msme.citycomp(ConfigReader.get("city"));
            Thread.sleep(2000);
             msme.citycomp(ConfigReader.get("city"));
             Thread.sleep(2000);
            msme.statecompany(ConfigReader.get("state"));
            Thread.sleep(2000);

            Thread.sleep(3000);
            msme.Districtcomp(ConfigReader.get("district"));

            logStep("✅ Address details entered");
            ScreenshotUtils.attachScreenshotToAllure(driver, "Address details entered");

            String account = ConfigReader.get("account");
            String ifsc = ConfigReader.get("bankifsc");

            if (account == null || account.length() < 6)
                throw new RuntimeException("❌ Invalid bank account number");

            if (ifsc == null || ifsc.length() < 5)
                throw new RuntimeException("❌ Invalid IFSC code");

            msme.accountno(account);
            Thread.sleep(2000);
            msme.ifsc(ifsc);

            Thread.sleep(4000);

            logStep("✅ Bank details entered");
            ScreenshotUtils.attachScreenshotToAllure(driver, "Bank details entered");

            msme.companynext();
            Thread.sleep(3000);

        } catch (AssertionError e) {
            logger.error("❌ MSME address/bank update failed", e);
            ScreenshotUtils.attachScreenshotToAllure(driver, "Address & Bank failure");
            throw new RuntimeException("❌ MSME address/bank update failed → " + e.getMessage(), e);
        }
    }

    @When("The user adding the director details in msme")
    public void The_user_adding_the_director_details_in_msme() throws InterruptedException {

        logStep("👤 Adding MSME Director details");
        logger.info("===== MSME Director Details Started =====");

        try {

            String name = ConfigReader.get("directorName2");
            String email = ConfigReader.get("DirectorEmail2");
            String mobile = ConfigReader.get("DirectorMobile2");
            String dob = ConfigReader.get("directorDob");
            String social = ConfigReader.get("Socialmsme");

            if (name == null || name.isEmpty())
                throw new RuntimeException("❌ Director name missing");

            msme.aadhar(name);
            msme.memberemail(email);
            msme.membermobile(mobile);

            msme.directordate();
            Thread.sleep(3000);

            msme.selectSocialCategory(social);
            Thread.sleep(3000);

            logStep("✅ Director details entered");
            ScreenshotUtils.attachScreenshotToAllure(driver, "Director details entered");

            msme.directornext();

        } catch (AssertionError e) {
            logger.error("❌ MSME director details update failed", e);
            ScreenshotUtils.attachScreenshotToAllure(driver, "Director details failure");
            throw new RuntimeException("❌ MSME director details update failed → " + e.getMessage(), e);
        }
    }

    @Then("The user uploading director documents in msme")
    public void The_user_uploading_director_documents_in_msme() throws InterruptedException {

        logStep("📂 Uploading MSME Director documents");
        logger.info("===== MSME Director Document Upload Started =====");

        try {

            String aadharFront = ConfigReader.get("AadharUploadPic1");
            String aadharBack = ConfigReader.get("AadharUploadBack");
            String pan = ConfigReader.get("PAN2");

            if (aadharFront == null || aadharBack == null || pan == null)
                throw new RuntimeException("❌ One or more document paths missing");

            msme.selectAadharFront.sendKeys(aadharFront);
            Thread.sleep(2000);

            msme.selectAadharBack.sendKeys(aadharBack);
            Thread.sleep(2000);

            msme.uploadPAN.sendKeys(pan);
            Thread.sleep(2000);

            logStep("✅ Director documents uploaded");
            ScreenshotUtils.attachScreenshotToAllure(driver, "Director documents uploaded");

            msme.directornext();

            logStep("🎉 MSME service completed successfully");
            ScreenshotUtils.attachScreenshotToAllure(driver, "MSME completed");

        } catch (AssertionError e) {
            logger.error("❌ MSME director document upload failed", e);
            ScreenshotUtils.attachScreenshotToAllure(driver, "Director document failure");
            throw new RuntimeException("❌ MSME director document upload failed → " + e.getMessage(), e);
        }
    }


}