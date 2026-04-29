package pages;

import base.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.WebDriverWait;
import utils.SafeActionUtils;
import utils.WaitUtils;

import java.time.Duration;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class LoginPage extends BasePage {
    private final WaitUtils wait;
    SafeActionUtils safeAction = new SafeActionUtils(driver);
    public LoginPage(WebDriver driver) {
        super(driver);
        this.wait = new WaitUtils(driver, 15);
    }
    @FindBy(xpath = "//input[@class='form-control floating-input email']")
    private WebElement enterEmail;

    @FindBy(xpath = "//input[@class='form-control floating-input password']")
    private WebElement enterPassword;

    @FindBy(xpath = "//button[text() = 'Sign In ']")
    private WebElement loginButton;


    public void typeEmail(String emailID) {
        safeAction.safeType(enterEmail, emailID, "Email ID");
    }

    public void typePassword(String password) {
        safeAction.safeType(enterPassword, password, "Password");
    }

    //loginButton
    public void clickLoginButton() {
        safeAction.safeClick(loginButton, "Login Button");
    }
    @FindBy(xpath = "//input[@type='email']")
    public WebElement email;


    @FindBy(xpath = "//input[@type='password']")
    public WebElement password;

    @FindBy(xpath = "//input[@id='auth_via_email']")
    public WebElement otptrigger;

    @FindBy(xpath = "//div[contains(@aria-label, 'Chat')] | //a[contains(@href, 'chat')]")
    public WebElement chatclick;

    @FindBy(xpath = "//span[text()='Vakilsearch Notifier']")
    public WebElement chatbotclicked;

    @FindBy(xpath = "//button[@class='btn btn-primary send_otp_btn']")
    public WebElement sendotpbtn;

    @FindBy(xpath = "//input[@id='email_otp_txt_field']")
    public WebElement otpinput;

    @FindBy(xpath = "//input[@name='ticket_number']")
    public WebElement dashboardElement;

    @FindBy(xpath = "//button[@class='VfPpkd-LgbsSe VfPpkd-LgbsSe-OWXEXe-k8QpJ VfPpkd-LgbsSe-OWXEXe-dgl2Hf nCP5yc AjY5Oe DuMIQc LQeN7 BqKGqe Jskylb TrZEUc lw1w4b']")
    public WebElement nextbutton;

    @FindBy(xpath = "//button[@class='btn btn-primary verify_btn']")
    public WebElement otpsubmit;



    public void email(String HelpdeskEmail) {
        safeAction.safeType(email, HelpdeskEmail, "Enter Email id");
    }

    public void setNextbutton() {
        safeAction.safeClick(nextbutton, "Gmail next");
    }

    public void otptrigger() {
        safeAction.safeClick(otptrigger, "Otp trigger");
    }


    public void password(String helpdeskPassword) {

        safeAction.safeType(password, helpdeskPassword, "Enter Email id");
    }

    public void chatclick() {

        safeAction.safeClick(chatclick, "chat click");
    }

    public void chatbotclicked() {

        safeAction.safeClick(chatbotclicked, "chat bot clicked");
    }

    public String fetchotp() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));

        driver.switchTo().defaultContent();

        List<WebElement> iframes = driver.findElements(By.tagName("iframe"));
        System.out.println("DEBUG: Total iframes found: " + iframes.size());

        for (int i = 0; i < iframes.size(); i++) {
            try {
                driver.switchTo().defaultContent();
                driver.switchTo().frame(i);

                List<WebElement> messages = driver.findElements(By.xpath("//*[contains(text(), 'OTP')]"));

                if (!messages.isEmpty()) {
                    String lastText = messages.get(messages.size() - 1).getText();
                    System.out.println("DEBUG: Found text in iframe " + i + ": " + lastText);

                    Pattern pattern = Pattern.compile("\\b\\d{6}\\b");
                    Matcher matcher = pattern.matcher(lastText);

                    if (matcher.find()) {
                        String foundOtp = matcher.group();
                        driver.switchTo().defaultContent();
                        return foundOtp;
                    }
                }
            } catch (Exception e) {
            }
        }

        driver.switchTo().defaultContent();
        return null;
    }

    public void sendotpbtn() {
        safeAction.safeClick(sendotpbtn, "Otp triggered click");
    }

    public void otpsubmit() {

        safeAction.safeClick(otpsubmit, "Otp submitted in CRM");

    }


}
