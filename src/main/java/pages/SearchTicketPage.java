package pages;

import base.BasePage;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import utils.SafeActionUtils;


import java.time.Duration;
import java.util.NoSuchElementException;


public class SearchTicketPage extends BasePage {
    SafeActionUtils safeAction = new SafeActionUtils(driver);

    public SearchTicketPage(WebDriver driver) {
        super(driver);
    }

    @FindBy(xpath = "//button[@id='continueButton2']")
    public WebElement startButton;

    @FindBy(xpath = "//input[@id='search_display_number']")
    private WebElement enterTicket;

    @FindBy(xpath = "//input[(@type='submit') and (@value='Search')]")
    private WebElement searchCTA;

    @FindBy(xpath = "//a[contains(text(),'Legal Notice')]")
    private WebElement ticketLink;

    public void typeTicketID(String ticketID) {
        safeAction.safeType(enterTicket, ticketID, "Enter Ticket");
    }

    public void clickSearchCTA() {
        safeAction.safeClick(searchCTA, "Search CTA");
    }

    public void clickTicketLink() {
        safeAction.safeClick(ticketLink, "Ticket Link");
    }

    public void clickstartButton() {
        safeAction.safeClick(startButton, "Click to Start Working");
    }

//    public void handleFreezePopupIfPresent() {
//        try {
//            WebDriverWait shortWait = new WebDriverWait(driver, Duration.ofSeconds(5));
//
//            WebElement btn = shortWait.until(ExpectedConditions.elementToBeClickable(startButton));
//
//            btn.click();
//        } catch (TimeoutException | NoSuchElementException e) {
//        }
//    }



}
