package stepDefinitions;

import hooks.Hooks;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import io.qameta.allure.Step;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import pages.EmployeeContractpage;
import utils.*;

import java.awt.*;
import java.awt.datatransfer.DataFlavor;

import static utils.AllureLoggerUtils.logToAllure;

public class EmployeeContractstepdef {

    WebDriver driver = Hooks.driver;
    EmployeeContractpage employee;
    Logger logger;
    AllureLoggerUtils allureLogging;
    WaitUtils wait;
    SearchTicketStepdefs searchTicketsteps;
    LoginStepDefinitions loginSteps;

    public EmployeeContractstepdef() {
        this.driver = Hooks.driver;
        this.employee = new EmployeeContractpage(Hooks.driver);
        this.logger = LoggerUtils.getLogger(getClass());
        this.wait = new WaitUtils(driver, 30);
        this.searchTicketsteps = new SearchTicketStepdefs(Hooks.driver);
        this.loginSteps = new LoginStepDefinitions();

    }

    @Step("{message}")
    public void logStep(String message) {
        // Allure will log this message as a step
    }

    @Given("the user redirect to Employee Contract service")
    public void the_user_redirect_to_Employee_Contract_service() throws InterruptedException {
        try {
            searchTicketsteps.theUserRedirectToTicketListPage();
            searchTicketsteps.theUserEnterTheTicket();
            searchTicketsteps.theUserClickTheSearchCTA();
            searchTicketsteps.theUserViewTheTicketPageByClickingOnTheLink();
            Thread.sleep(2000);
            ScreenshotUtils.attachScreenshotToAllure(driver, "1_Ticket_List_View");

            employee.crossSaleNew();
            employee.createservice(ConfigReader.get("EmployeeContract"));
            employee.selectingtheservice();
            Thread.sleep(2000);
            employee.createbutton();
            logger.info("Service creation initiated.");
            ScreenshotUtils.attachScreenshotToAllure(driver, "2_Service_Created");

            Thread.sleep(15000);
            employee.recenttickets();
            Thread.sleep(4000);
            logger.info("Recent Tickets modal opened.");
            ScreenshotUtils.attachScreenshotToAllure(driver, "3_Recent_Tickets_Modal");

            try {
                WebElement firstTicket = driver.findElement(By.cssSelector("tr.ticket_index_tr ul.tcktdetails li a"));
                firstTicket.click();
                logger.info("Clicked first dynamic ticket link.");
                Thread.sleep(10000);
                ScreenshotUtils.attachScreenshotToAllure(driver, "4_Selected_Ticket_Page");

                employee.copyurl.click();
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
            logStep("✅ Redirection to US Trademark Upload Document Page successful");
            logToAllure("Success Validation", "User successfully redirected to the dynamic flow.");
            logger.info("Step completed successfully.");
        }

             catch (WebDriverException | AssertionError | InterruptedException e) {
            String msg = "❌ Exception during US Trademark flow: " + e.getMessage();
            logger.error(msg);
            logToAllure("Step Failure", msg);
            ScreenshotUtils.attachScreenshotToAllure(driver, "Critical_Flow_Failure");
            Assert.fail(msg);
        }
        }

    @When("the user updates the Details of Employer Your Details in employee")
    public void detailsofemployeryourdetails() throws InterruptedException {

    employee.nameofbusiness(ConfigReader.get("directorName1"));
    employee.address(ConfigReader.get("Address1"));
    employee.authsignatory(ConfigReader.get("directorName1"));
    employee.selectEntityType(ConfigReader.get("EntityType"));
    employee.detailsofempbutton();
    Thread.sleep(2000);
}

    @When("the user updates the Details of Employee Your Details in employee")
    public void detailsofemploye() throws InterruptedException {

        employee.nameoftheemployee(ConfigReader.get("directorName2"));
        employee.addressofemployee(ConfigReader.get("Address2"));
        employee.Fathername(ConfigReader.get("FatherName2"));
        employee.aadharno(ConfigReader.get("Aadhar1"));
        employee.panno(ConfigReader.get("Pan1"));
        employee.employeebutton();
        Thread.sleep(2000);
    }

    @When("the user updates the Employee Details in employee")
    public void employeedetails() throws InterruptedException {

        employee.designation(ConfigReader.get("directorName2"));
        employee.Jobdescription(ConfigReader.get("Address2"));
        employee.annualctc(ConfigReader.get("FatherName2"));
        employee.salarybreakup(ConfigReader.get("Aadhar1"));
        employee.Employeewillreport(ConfigReader.get("Aadhar1"));
        employee.noofdays(ConfigReader.get("Aadhar1"));
        employee.workinghours(ConfigReader.get("Aadhar1"));
        employee.directordate();
        employee.detailsempbutton();
        Thread.sleep(2000);
    }

    @When("the user updates the Description of business in employee")
    public void description() throws InterruptedException {
        employee.DescriptionofyourBusiness(ConfigReader.get("Aadhar1"));
        employee.descriptionofbusiness();
        Thread.sleep(4000);
    }

    @When("the user updates the Probation in employee")
    public void probation() throws InterruptedException {
        employee.Tenure(ConfigReader.get("Aadhar1"));
        employee.noticeperiod(ConfigReader.get("Aadhar1"));
        employee.legalentitle(ConfigReader.get("Aadhar1"));
        employee.probationbutton();
        Thread.sleep(4000);
    }

    }





