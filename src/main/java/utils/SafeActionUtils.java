package utils;

import base.BasePage;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.interactions.MoveTargetOutOfBoundsException;
import java.time.Duration;

public class SafeActionUtils extends BasePage {
    private final WaitUtils wait;


    public SafeActionUtils(WebDriver driver) {
        super(driver);
        this.wait = new WaitUtils(driver, 15);
    }

    public void safeType(WebElement element, String value, String fieldName) {
        int attempts = 0;
        boolean typed = false;

        logger.info("⌨️ Attempting to type '{}' into {}", value, fieldName);

        while (attempts < 3 && !typed) {
            attempts++;
            try {
                // ✅ Ensure element is visible before interacting
                wait.waitForVisibility(element);

                if (element != null && element.isDisplayed() && element.isEnabled()) {
                    element.clear();
                    element.sendKeys(value);
                    logger.info("✅ Successfully typed '{}' into {} on attempt {}", value, fieldName, attempts);
                    typed = true;
                } else {
                    logger.warn("⚠️ {} not interactable (Displayed: {}, Enabled: {})",
                            fieldName, element.isDisplayed(), element.isEnabled());
                }

            } catch (StaleElementReferenceException e) {
                logger.warn("♻️ Stale element for {} on attempt {}. Retrying...", fieldName, attempts, e);
            } catch (Exception e) {
                logger.error("❌ Error typing into {} on attempt {}.", fieldName, attempts, e);
            }

            if (!typed) {
                try {
                    Thread.sleep(250);
                } catch (InterruptedException ignored) {
                }
            }
        }

        if (!typed) {
            throw new RuntimeException("Failed to type into " + fieldName + " after " + attempts + " attempts.");
        }
    }

    public void safeClick(WebElement element, String fieldName) {
        int attempts = 0;
        boolean clicked = false;

        logger.info("🖱️ Attempting to click on {}", fieldName);

        while (attempts < 3 && !clicked) {
            attempts++;
            try {
                if (element == null) throw new RuntimeException("Null WebElement for: " + fieldName);

                // Bring into view (center) to avoid sticky headers/overlaps
                ((JavascriptExecutor) driver).executeScript(
                        "arguments[0].scrollIntoView({block:'center', inline:'center'});", element
                );

                // Wait until Selenium thinks it's clickable
                wait.waitForElementToBeClickable(element);

                // Small pause for CSS transitions
                Thread.sleep(120);

                // Try native click → Actions → JS
                try {
                    element.click();
                } catch (ElementClickInterceptedException | MoveTargetOutOfBoundsException e1) {
                    try {
                        new Actions(driver).moveToElement(element).pause(Duration.ofMillis(80)).click().perform();
                    } catch (Exception e2) {
                        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", element);
                    }
                }

                logger.info("✅ Clicked '{}' (attempt {})", fieldName, attempts);
                clicked = true;

            } catch (StaleElementReferenceException e) {
                logger.warn("♻️ Stale element for '{}' on attempt {}. Retrying...", fieldName, attempts);
                sleep(200);
            } catch (TimeoutException e) {
                logger.warn("⏳ Timeout waiting for '{}' on attempt {}. Retrying...", fieldName, attempts);
                sleep(200);
            } catch (Exception e) {
                logger.warn("🚧 Click issue on '{}' attempt {}: {}. Retrying...", fieldName, attempts, e.getClass().getSimpleName());
                sleep(250);
            }
        }

        if (!clicked) {
            try {
                ScreenshotUtils.attachScreenshotToAllure(driver, "ClickFail_" + fieldName);
            } catch (Exception ignored) {
            }
            throw new RuntimeException("Failed to click on " + fieldName + " after " + attempts + " attempts.");
        }
    }

    private void sleep(long ms) {
        try {
            Thread.sleep(ms);
        } catch (InterruptedException ignored) {
        }
    }
}
