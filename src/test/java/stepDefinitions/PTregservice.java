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
import pages.ptregpage;
import utils.*;

import java.awt.*;
import java.awt.datatransfer.DataFlavor;
import java.time.Duration;

import static utils.AllureLoggerUtils.logToAllure;

public class PTregservice {
    WebDriver driver = Hooks.driver;
    ptregpage pt;
    Logger logger;
    AllureLoggerUtils allureLogging;
    WaitUtils wait;
    SeleniumHelperMethods seleniumHelperMethods;
    SearchTicketStepdefs searchTicketsteps;
    LoginStepDefinitions loginSteps;

    public PTregservice() {
        this.driver = Hooks.driver;
        this.pt = new ptregpage(Hooks.driver);
        this.logger = LoggerUtils.getLogger(getClass());
        this.wait = new WaitUtils(driver, 30);
        this.searchTicketsteps = new SearchTicketStepdefs(Hooks.driver);
        this.loginSteps = new LoginStepDefinitions();
    }

    @Step("{message}")
    public void logStep(String message) {
    }

    @Given("the user redirect to Company details page of PT service")
    public void the_user_redirect_to_Company_details_page_of_PT_service() throws InterruptedException {
        try {

            searchTicketsteps.theUserRedirectToTicketListPage();
            searchTicketsteps.theUserEnterTheTicket();
            searchTicketsteps.theUserClickTheSearchCTA();
            searchTicketsteps.theUserViewTheTicketPageByClickingOnTheLink();

            pt.crossSaleNew();
            pt.createservice(ConfigReader.get("ProfessionTax"));
            pt.selectingtheservice();
            Thread.sleep(2000);
            pt.createbutton();
            logger.info("Service creation initiated.");
            ScreenshotUtils.attachScreenshotToAllure(driver, "2_Service_Created");

            Thread.sleep(15000);
            pt.recenttickets();
            Thread.sleep(4000);
            logger.info("Recent Tickets modal opened.");
            ScreenshotUtils.attachScreenshotToAllure(driver, "3_Recent_Tickets_Modal");

            try {
                WebElement firstTicket = driver.findElement(By.cssSelector("tr.ticket_index_tr ul.tcktdetails li a"));
                firstTicket.click();
                logger.info("Clicked first dynamic ticket link.");
                Thread.sleep(10000);
                ScreenshotUtils.attachScreenshotToAllure(driver, "4_Selected_Ticket_Page");

                pt.copyurl.click();
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
            logStep("🌐 Navigating to the Professional tax registration Customer view...");
            logStep("✅ Open PT Registration Customer view");
            logToAllure("✅ PT Registration - Redirection Validation", "PT Registration Page redirected successfully");
            ScreenshotUtils.attachScreenshotToAllure(driver, "ptregpage");

        } catch (AssertionError e) {
            String msg = "❌ Exception during PT Registration page: " + e.getMessage();
            logger.error(msg);
            logStep(msg);
            logToAllure("❌ Exception", msg);
            ScreenshotUtils.attachScreenshotToAllure(driver, "proftaxurl_Exception");
            throw e;
        }
    }

    @When("the user selecting the new company type")
    public void companyselection() throws InterruptedException{

        logStep("🔍 Selecting company type (No – not another company)");
        pt.noitsanothercompany();

        logger.info("Company selection clicked → No");
        logToAllure("Company Selection", "Selected No – not another company");
        ScreenshotUtils.attachScreenshotToAllure(driver, "Company selection - No");

    }

    @When("the user updates the bank and address in PT")
    public void the_user_updates_the_bank_and_address_in_PT() throws InterruptedException {

        logStep("🏢 Starting company & bank details update in PT");
        logger.info("===== PT Company Update Flow Started =====");

        try {

            logStep("🏷 Selecting company type");
            String companyType = ConfigReader.get("companytype");
            pt.Comptype(companyType);

            logger.info("Company type selected → {}", companyType);
            logToAllure("Company Type", companyType);

            logStep("🏢 Entering company name");
            String companyName = ConfigReader.get("companynamemsme");

            if (companyName == null || companyName.isEmpty()) {
                throw new RuntimeException("❌ Company name is empty in config");
            }

           pt.com_name(companyName);
            logger.info("Company name entered → {}", companyName);

            logToAllure("Company Name Entered", companyName);
            ScreenshotUtils.attachScreenshotToAllure(driver, "Company name entered");

            Thread.sleep(3000);

            logStep("📅 Selecting company DOB");
           String dob = ConfigReader.get("DOB2");

            pt.selectDate();
            logger.info("Company DOB selected → {}", dob);
            Thread.sleep(2000);
            logStep("🏭 Selecting nature of business");
            pt.nature();
            Thread.sleep(2000);
            String businessSearch = ConfigReader.get("searchbus");
            pt.searchbusiness(businessSearch);
            Thread.sleep(2000);
            pt.businessselect();

            logger.info("Business selected → {}", businessSearch);
            ScreenshotUtils.attachScreenshotToAllure(driver, "Business selected");

            Thread.sleep(2000);

            logStep("✅ Company basic details entered successfully");
            logToAllure("Company Details", "Company basic details completed");

            logStep("👥 Selecting employee count");
            String employeeCount = ConfigReader.get("employeecount");

           pt.clickEmployeeCountDropdown();
           pt.enterEmployeeCount(employeeCount);
            pt.selectEmployeeCountValue(employeeCount);

            logger.info("Employee count selected → {}", employeeCount);

            logStep("📱 Entering company mobile number");
            String mobile = ConfigReader.get("companymobile");

            if (mobile == null || mobile.length() < 10) {
                throw new RuntimeException("❌ Invalid company mobile number");
            }
            Thread.sleep(2000);
            pt.com_mobile(mobile);
            logger.info("Company mobile number entered → {}", mobile);

            logStep("📧 Entering company email");
            String email = ConfigReader.get("companyemail");

            if (email == null || !email.contains("@")) {
                throw new RuntimeException("❌ Invalid company email");
            }
            Thread.sleep(2000);
            pt.com_email(email);
            logger.info("Company email entered → {}", email);

            logStep("✅ Company employee & contact details entered");
            logToAllure("Employee & Contact Details", "Employee count, mobile & email entered");
            ScreenshotUtils.attachScreenshotToAllure(driver, "Employee & contact details");

            logStep("➡ Clicking Next CTA");
            pt.nextcta();

            logger.info("Next CTA clicked successfully");
            ScreenshotUtils.attachScreenshotToAllure(driver, "Next CTA clicked");

            Thread.sleep(3000);

            logStep("🎉 PT company update flow completed successfully");
            logger.info("===== PT Company Update Flow Completed =====");

        } catch (AssertionError e) {

            logger.error("❌ PT company update flow failed", e);
            ScreenshotUtils.attachScreenshotToAllure(driver, "Failure Screenshot");

            throw new RuntimeException("❌ PT Company update failed → " + e.getMessage(), e);
        }
    }


    @When("the user updates the second subtab and address in PT")
    public void the_user_updates_the_second_subtab_and_address_in_PT() throws InterruptedException {

        logStep("🏢 Starting address & bank details update in PT (Second Subtab)");
        logger.info("===== PT Address & Bank Update Flow Started =====");

        try {

            logStep("📍 Navigated to Company address screen");
            logToAllure("Navigation", "Navigated to Company address screen");
            ScreenshotUtils.attachScreenshotToAllure(driver, "Company address screen");

            logStep("🏠 Entering flat / door number");
            String flatNo = ConfigReader.get("flatno");
            if (flatNo == null || flatNo.isEmpty()) {
                throw new RuntimeException("❌ Flat number is empty in config");
            }
            pt.flatno(flatNo);
            logger.info("Flat number entered → {}", flatNo);
            Thread.sleep(2000);
            logStep("🛣 Entering road / street");
            String road = ConfigReader.get("Roadstreet");
            pt.road(road);
            logger.info("Road / Street entered → {}", road);

            logStep("📍 Entering area / locality");
            String area = ConfigReader.get("AreaLocality");
            pt.area(area);
            logger.info("Area / Locality entered → {}", area);

            logStep("📮 Entering pincode");
            String pincode = ConfigReader.get("Pincode");
            if (pincode == null || pincode.length() < 6) {
                throw new RuntimeException("❌ Invalid pincode provided");
            }
            pt.pincodecomp(pincode);
            Thread.sleep(2000);
            logger.info("Pincode entered → {}", pincode);

            logStep("✅ Company address basic fields entered");
            logToAllure("Address Details", "Flat, Road, Area, Pincode entered");
            ScreenshotUtils.attachScreenshotToAllure(driver, "Address basic details");

            logStep("🏙 Selecting city");
            String city = ConfigReader.get("city");
            pt.citycomp(city);
            logger.info("City selected → {}", city);

            logStep("🗺 Selecting state");
            String state = ConfigReader.get("state");
            pt.statecompany(state);
            logger.info("State selected → {}", state);

            Thread.sleep(3000);

            logStep("🚓 Entering police station");
            String policeStation = ConfigReader.get("PoliceStation");
            pt.police(policeStation);
            logger.info("Police station entered → {}", policeStation);

            logStep("🏦 Entering bank account number");
            String accountNo = ConfigReader.get("account");
            if (accountNo == null || accountNo.length() < 6) {
                throw new RuntimeException("❌ Invalid bank account number");
            }
            pt.accountno(accountNo);
            logger.info("Bank account number entered");

            logStep("🏦 Entering IFSC code");
            String ifsc = ConfigReader.get("bankifsc");
            if (ifsc == null || ifsc.length() < 5) {
                throw new RuntimeException("❌ Invalid IFSC code");
            }
            pt.ifsc(ifsc);
            Thread.sleep(2000);
            logger.info("IFSC code entered → {}", ifsc);

            Thread.sleep(4000);

            logStep("✅ Bank details entered successfully");
            logToAllure("Bank Details", "Account number & IFSC entered");
            ScreenshotUtils.attachScreenshotToAllure(driver, "Bank details entered");

            logStep("➡ Clicking Company Next CTA");
            pt.companynext();
            logger.info("Company Next CTA clicked");

            ScreenshotUtils.attachScreenshotToAllure(driver, "Next CTA clicked");
            Thread.sleep(3000);

            logStep("🎉 Address & bank details update completed successfully");
            logger.info("===== PT Address & Bank Update Flow Completed =====");

        } catch (AssertionError e) {

            logger.error("❌ PT address & bank update failed", e);
            ScreenshotUtils.attachScreenshotToAllure(driver, "Failure Screenshot");

            throw new RuntimeException("❌ PT address & bank update failed → " + e.getMessage(), e);
        }
    }

    @When("The user adding the director details in PT")
    public void The_user_adding_the_director_details_in_PT() throws InterruptedException {

        logStep("👤 Starting Director details entry in PT");
        logger.info("===== PT Director Details Flow Started =====");

        try {

            logStep("➡ Navigating to Director details screen");
            logToAllure("Navigation", "Navigated to Director details screen");
            ScreenshotUtils.attachScreenshotToAllure(driver, "Director details screen");

            logStep("📝 Entering Director name");
            String directorName = ConfigReader.get("directorName2");
            if (directorName == null || directorName.isEmpty()) {
                throw new RuntimeException("❌ Director name is empty in config");
            }
            pt.name(directorName);
            logger.info("Director name entered → {}", directorName);

            logStep("📧 Entering Director email");
            String directorEmail = ConfigReader.get("DirectorEmail2");
            if (directorEmail == null || !directorEmail.contains("@")) {
                throw new RuntimeException("❌ Invalid Director email");
            }
            pt.memberemail(directorEmail);
            logger.info("Director email entered → {}", directorEmail);

            logStep("📱 Entering Director mobile number");
            String directorMobile = ConfigReader.get("DirectorMobile2");
            if (directorMobile == null || directorMobile.length() < 10) {
                throw new RuntimeException("❌ Invalid Director mobile number");
            }
            pt.membermobile(directorMobile);
            logger.info("Director mobile entered → {}", directorMobile);

            logStep("📅 Selecting Director DOB");
            String directorDob = ConfigReader.get("directorDob");
            if (directorDob == null || directorDob.isEmpty()) {
                throw new RuntimeException("❌ Director DOB is empty");
            }

            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
            pt.directordate();
            logger.info("Director DOB selected → {}", directorDob);

            Thread.sleep(3000);

            logStep("✅ Director details entered successfully");
            logToAllure("Director Details", "Name, Email, Mobile & DOB entered");
            ScreenshotUtils.attachScreenshotToAllure(driver, "Director details entered");

            logStep("➡ Clicking Director Next CTA");
            pt.directornext();
            logger.info("Director Next CTA clicked");

            logStep("🎉 Director details flow completed successfully");
            logger.info("===== PT Director Details Flow Completed =====");

        } catch (AssertionError e) {

            logger.error("❌ PT Director details update failed", e);
            ScreenshotUtils.attachScreenshotToAllure(driver, "Director Details Failure");

            throw new RuntimeException("❌ PT Director details update failed → " + e.getMessage(), e);
        }
    }


    @When("The user uploading director documents in PT")
    public void The_user_uploading_director_documents_in_PT() throws InterruptedException {

        logStep("📂 Starting Director document upload in PT");
        logger.info("===== PT Director Document Upload Flow Started =====");

        try {

            logStep("➡ Navigated to Director document upload section");
            logToAllure("Navigation", "Navigated to Director document upload section");
            ScreenshotUtils.attachScreenshotToAllure(driver, "Director document upload section");

            logStep("🪪 Uploading Aadhaar Front");
            String aadhaarFront = ConfigReader.get("AadharUploadPic1");
            if (aadhaarFront == null || aadhaarFront.isEmpty()) {
                throw new RuntimeException("❌ Aadhaar front file path is empty");
            }
            pt.selectAadharFront.sendKeys(aadhaarFront);
            logger.info("Aadhaar front uploaded → {}", aadhaarFront);
            Thread.sleep(2000);

            logStep("🪪 Uploading Aadhaar Back");
            String aadhaarBack = ConfigReader.get("AadharUploadBack");
            if (aadhaarBack == null || aadhaarBack.isEmpty()) {
                throw new RuntimeException("❌ Aadhaar back file path is empty");
            }
            pt.selectAadharBack.sendKeys(aadhaarBack);
            logger.info("Aadhaar back uploaded → {}", aadhaarBack);
            Thread.sleep(2000);

            logStep("🧾 Uploading PAN document");
            String pan = ConfigReader.get("PAN2");
            if (pan == null || pan.isEmpty()) {
                throw new RuntimeException("❌ PAN document path is empty");
            }
            pt.uploadPAN.sendKeys(pan);
            logger.info("PAN document uploaded → {}", pan);
            Thread.sleep(2000);

            logStep("📸 Uploading passport size photo");
            String passportPhoto = ConfigReader.get("PassportPhoto1");
            if (passportPhoto == null || passportPhoto.isEmpty()) {
                throw new RuntimeException("❌ Passport size photo path is empty");
            }
            pt.uploadPassportSize.sendKeys(passportPhoto);
            logger.info("Passport size photo uploaded → {}", passportPhoto);
            Thread.sleep(2000);

            logStep("✍ Uploading signature");
            String signature = ConfigReader.get("PassportPhoto2");
            if (signature == null || signature.isEmpty()) {
                throw new RuntimeException("❌ Signature file path is empty");
            }
            pt.signature.sendKeys(signature);
            logger.info("Signature uploaded → {}", signature);
            Thread.sleep(2000);

            logStep("✅ All Director documents uploaded successfully");
            logToAllure("Director Document Upload", "Aadhaar, PAN, Photo & Signature uploaded");
            ScreenshotUtils.attachScreenshotToAllure(driver, "Director documents uploaded");

            logStep("➡ Clicking Director Next CTA after document upload");
            pt.directornext();
            logger.info("Director Next CTA clicked");

            Thread.sleep(2000);

            logStep("🎉 Director document upload flow completed successfully");
            logger.info("===== PT Director Document Upload Flow Completed =====");

        } catch (AssertionError e) {

            logger.error("❌ PT Director document upload failed", e);
            ScreenshotUtils.attachScreenshotToAllure(driver, "Director Document Upload Failure");

            throw new RuntimeException("❌ PT Director document upload failed → " + e.getMessage(), e);
        }
    }

    @Then("The user uploading company documents for PT")
    public void The_user_uploading_company_documents_for_PT() throws InterruptedException {

        logStep("📂 Starting company document upload in PT");
        logger.info("===== PT Company Document Upload Flow Started =====");

        try {

            logStep("➡ Navigating to company documents page");
            logToAllure("Navigation", "Navigated to company documents page");
            ScreenshotUtils.attachScreenshotToAllure(driver, "Company documents page");

            Thread.sleep(2000);

            logStep("🏠 Uploading Rental Agreement");
            String rentalAgreement = ConfigReader.get("PassportPhoto1");
            if (rentalAgreement == null || rentalAgreement.isEmpty()) {
                throw new RuntimeException("❌ Rental agreement file path is empty");
            }
            pt.rentalagreement.sendKeys(rentalAgreement);
            logger.info("Rental agreement uploaded → {}", rentalAgreement);
            Thread.sleep(2000);

            logStep("💡 Uploading Electricity Bill");
            String electricityBill = ConfigReader.get("BankStatement");
            if (electricityBill == null || electricityBill.isEmpty()) {
                throw new RuntimeException("❌ Electricity bill file path is empty");
            }
            pt.electricitybill.sendKeys(electricityBill);
            logger.info("Electricity bill uploaded → {}", electricityBill);
            Thread.sleep(2000);

            logStep("💡 Uploading Electricity Bill");
            String employeebasicdetail = ConfigReader.get("BankStatement");
            if (employeebasicdetail == null || employeebasicdetail.isEmpty()) {
                throw new RuntimeException("❌ Employee basic details path is empty");
            }

            pt.employeebasicdetail.sendKeys(electricityBill);
            logger.info("Employee basic details uploaded → {}", employeebasicdetail);
            Thread.sleep(2000);

            logStep("➡ Clicking Next CTA after company document upload");
            pt.directornext();
            logger.info("Next CTA clicked after company document upload");

            logStep("🎉 PT registration successfully completed");
            logToAllure("PT Registration Completion", "PT registration successfully completed");
            ScreenshotUtils.attachScreenshotToAllure(driver, "PT registration successfully completed");

            logger.info("===== PT Company Document Upload Flow Completed =====");

        } catch (AssertionError e) {

            logger.error("❌ PT company document upload failed", e);
            ScreenshotUtils.attachScreenshotToAllure(driver, "Company Document Upload Failure");

            throw new RuntimeException("❌ PT company document upload failed → " + e.getMessage(), e);
        }
    }


}