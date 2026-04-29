package utils;


import io.qameta.allure.Step;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;

import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.text.Normalizer;
import java.time.Duration;
import java.util.function.Supplier;

/**
 * @author Sherwin
 * @since 29-06-2025
 */

public class ReusableCommonMethods {
    private final WebDriver driver;
    private final Logger logger;
    private final WaitUtils wait;
    // Login-only thresholds
    public static final long LOGIN_WARN_MS = getLong("threshold.login.warn.ms", 30_000L);
    public static final long LOGIN_FAIL_MS = getLong("threshold.login.fail.ms", 60_000L);

    // All other navigations (menus, tabs, etc.)
    public static final long NAV_WARN_MS   = getLong("threshold.nav.warn.ms", 10_000L);
    public static final long NAV_FAIL_MS   = getLong("threshold.nav.fail.ms", 60_000L);

    // NEW: Get Started → Mandatory Compliances
    public static final long GETSTARTED_WARN1_MS = getLong("threshold.getstarted.warn1.ms", 60_000L);
    public static final long GETSTARTED_WARN2_MS = getLong("threshold.getstarted.warn2.ms", 90_000L);
    public static final long GETSTARTED_FAIL_MS  = getLong("threshold.getstarted.fail.ms",  150_000L);



    public ReusableCommonMethods(WebDriver driver) {
        this.driver = driver;
        this.logger = LoggerUtils.getLogger(ReusableCommonMethods.class);
        this.wait = new WaitUtils(driver,15);
    }

    @Step("{message}")
    public void logStep(String message) {
        logger.info(message);
    }

    /**
     * Refreshes the page and re-applies a given action.
     * Logs each step, captures screenshots on failure, and rethrows exceptions.
     *
     * @param actionLabel   A descriptive label for the action being retried
     * @param reapplyAction The action logic to execute after refresh
     */
    public void refreshAndReapply(String actionLabel, Runnable reapplyAction) {
        try {
            logger.info("🔄 Refreshing the page before reapplying: {}", actionLabel);
            driver.navigate().refresh();

            wait.waitForPageToLoad();
            logger.info("⏳ Page refresh completed. Ready to reapply: {}", actionLabel);

            logger.info("🎯 Reapplying after refresh: {}", actionLabel);
            reapplyAction.run();

            logger.info("✅ Successfully completed reapplying: {}", actionLabel);

        } catch (Exception e) {
            logger.error("❌ Error during '{}'. Exception: {}", actionLabel, e.toString(), e);
            logger.info("📸 Capturing screenshot for failure in '{}'", actionLabel);
            ScreenshotUtils.attachScreenshotToAllure(driver, "Error_Reapply_" + actionLabel.replaceAll("\\s+", "_"));
            throw e;
        }
    }


    public void refreshPage() {
        driver.navigate().refresh();
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            System.out.println("Thread interrupted during page refresh.");
        }
    }


    /**
     * Safely clicks a WebElement with multiple fallbacks:
     * normal click → Actions click → JS click.
     * Validates visibility & clickability, scrolls into view,
     * checks for overlap, handles stale refs, and retries.
     *
     * @param driver     WebDriver instance
     * @param element    WebElement to click
     * @param name       readable element name for logs
     * @param timeoutSec per-attempt wait timeout (visibility/clickable)
     */
    public void safeClick(WebDriver driver, WebElement element, String name, int timeoutSec) {
        final int MAX_ATTEMPTS = 3;
        int attempt = 0;

        while (attempt < MAX_ATTEMPTS) {
            attempt++;
            logger.info("🖱️ Attempt {} to click '{}'", Integer.valueOf(attempt), name);

            try {
                wait.waitForVisibility(element);
                wait.waitForElementToBeClickable(element);

                // Scroll into centered view
                try {
                    ((JavascriptExecutor) driver).executeScript(
                            "arguments[0].scrollIntoView({block:'center', inline:'center'});", element);
                    Thread.sleep(120);
                } catch (Exception ignore) {
                }

                // Overlap check (best-effort)
                try {
                    Object result = ((JavascriptExecutor) driver).executeScript(
                            "var r=arguments[0].getBoundingClientRect();" +
                                    "var x=Math.floor(r.left+r.width/2), y=Math.floor(r.top+r.height/2);" +
                                    "var el=document.elementFromPoint(x,y); return el===arguments[0];", element);
                    if (result instanceof Boolean && !((Boolean) result).booleanValue()) {
                        logger.warn("🧱 '{}' center point overlapped; nudging scroll and retrying this attempt.", name);
                        ((JavascriptExecutor) driver).executeScript("window.scrollBy(0,-80);");
                        Thread.sleep(120);
                    }
                } catch (Exception ignore) {
                }

                // Normal click
                try {
                    element.click();
                    logger.info("✅ Clicked '{}' via normal click.", name);
                    return;
                } catch (ElementClickInterceptedException e) {
                    logger.warn("⚠️ Normal click intercepted for '{}': {}", name, e.toString());
                } catch (Exception e) {
                    logger.warn("⚠️ Normal click failed for '{}': {}", name, e.toString());
                }

                // Actions click
                try {
                    new Actions(driver).moveToElement(element).pause(Duration.ofMillis(80)).click().perform();
                    logger.info("✅ Clicked '{}' via Actions.", name);
                    return;
                } catch (Exception e) {
                    logger.warn("⚠️ Actions click failed for '{}': {}", name, e.toString());
                }

                // JS click
                try {
                    ((JavascriptExecutor) driver).executeScript("arguments[0].click();", element);
                    logger.info("✅ Clicked '{}' via JavaScript.", name);
                    return;
                } catch (Exception e) {
                    logger.warn("⚠️ JavaScript click failed for '{}': {}", name, e.toString());
                }

            } catch (StaleElementReferenceException sere) {
                logger.warn("♻️ Element '{}' went stale on attempt {}. You may need to re-locate it before retrying.",
                        name, Integer.valueOf(attempt));
            } catch (TimeoutException te) {
                logger.warn("⏳ Timeout waiting for '{}' to be visible/clickable on attempt {}.",
                        name, Integer.valueOf(attempt));
            } catch (Exception e) {
                logger.warn("⚠️ Unexpected exception on attempt {} for '{}': {}", Integer.valueOf(attempt), name, e.toString());
            }

            try {
                Thread.sleep(250);
            } catch (InterruptedException ignored) {
            }
        }

        logger.error("❌ Failed to click '{}' after {} attempts.", name, Integer.valueOf(MAX_ATTEMPTS));
        throw new RuntimeException("Failed to click '" + name + "' after " + MAX_ATTEMPTS + " attempts.");
    }


    /**
     * Scrolls an element smoothly into view (center of viewport).
     *
     * @param el WebElement to scroll into view
     */

    public void scrollIntoView(WebElement el) {
        try {
            ((JavascriptExecutor) driver).executeScript(
                    "arguments[0].scrollIntoView({block:'center', inline:'nearest'})", el);
        } catch (Throwable ex) {
            logger.warn("scrollIntoView failed: {}", ex.getMessage());
        }
    }

    /**
     * Asserts that an element is visible, clickable, displayed, and enabled.
     * <p>Also validates non-zero size and performs a best-effort overlap check.</p>
     *
     * @param element WebElement to validate
     * @param name    Descriptive element name for logs
     * @throws AssertionError   if any validation fails
     * @throws RuntimeException wrapping underlying Selenium exceptions
     */

    public void assertVisibleAndClickable(WebElement element, String name) {
        try {
            logger.info("Checking visibility of {}", name);
            wait.waitForVisibility(element);
            logger.info("{} is visible", name);

            wait.waitForElementToBeClickable(element);
            logger.info("{} is clickable", name);

            if (!element.isDisplayed()) throw new AssertionError(name + " not displayed");
            if (!element.isEnabled()) throw new AssertionError(name + " is disabled");

            assertRenderedAndUnobstructed(element, name);
        } catch (Throwable t) {
            logger.error("❌ Validation failed for {}: {}", name, t.getMessage());
            try {
                ScreenshotUtils.attachScreenshotToAllure(driver, "Failed_" + safeFile(name));
            } catch (Throwable ignore) {
            }
            throw asRuntime("Validation failed for " + name, t);
        }
    }

    /**
     * Asserts that an element's text (or aria-label) matches an expected value.
     * <p>Normalizes whitespace and performs a case-insensitive comparison.</p>
     *
     * @param element  WebElement whose text should be checked
     * @param expected Expected text value
     * @param name     Descriptive element name for logs
     * @throws AssertionError if the text does not match
     */

    public void assertText(WebElement element, String expected, String name) {
        try {
            String actual = normalize(textOrAria(element));
            String expNorm = normalize(expected);
            logger.info("Checking text of {} → Expected: '{}', Actual: '{}'", name, expNorm, actual);
            if (!actual.equalsIgnoreCase(expNorm)) {
                throw new AssertionError(name + " text mismatch. Expected '" + expNorm + "', got '" + actual + "'");
            }
        } catch (Throwable t) {
            logger.error("❌ Text validation failed for {}: {}", name, t.getMessage());
            try {
                ScreenshotUtils.attachScreenshotToAllure(driver, "FailedText_" + safeFile(name));
            } catch (Throwable ignore) {
            }
            throw asRuntime("Text validation failed for " + name, t);
        }
    }

    /**
     * Ensures that an element has non-zero size and is not obviously obscured.
     * <p>Fails if size is 0x0, warns if another element is overlapping.</p>
     *
     * @param el   WebElement to check
     * @param name Descriptive element name for logs
     * @throws AssertionError if the element has zero size
     */

    public void assertRenderedAndUnobstructed(WebElement el, String name) {
        Rectangle r = el.getRect();
        logger.info("{} rect: {}", name, r);
        if (r.getHeight() <= 0 || r.getWidth() <= 0) {
            throw new AssertionError(name + " has zero size");
        }

        // Best-effort obstruction signal (won’t fail unless clearly hidden)
        try {
            Long scrollY = (Long) ((JavascriptExecutor) driver).executeScript("return window.scrollY || 0;");
            int cx = r.getX() + r.getWidth() / 2;
            int cy = r.getY() - (scrollY == null ? 0 : scrollY.intValue()) + r.getHeight() / 2;

            Object topAtPoint = ((JavascriptExecutor) driver)
                    .executeScript("return document.elementFromPoint(arguments[0], arguments[1]);", cx, cy);

            if (topAtPoint instanceof WebElement top) {
                if (!el.equals(top) && !el.isDisplayed()) {
                    logger.warn("{} may be obscured by element: {}", name, top);
                }
            }
        } catch (Throwable ex) {
            logger.warn("elementFromPoint check failed for {}: {}", name, ex.getMessage());
        }
    }

    /**
     * Validates that an element follows tab semantics:
     * <ul>
     *   <li>role="tab"</li>
     *   <li>aria-selected attribute present</li>
     * </ul>
     *
     * @param el   WebElement representing a tab
     * @param name Descriptive element name for logs
     * @throws AssertionError if semantics are not satisfied
     */

    public void assertTabSemantics(WebElement el, String name) {
        String role = attr(el, "role");
        logger.info("{} role: {}", name, role);
        if (role != null && !role.equalsIgnoreCase("tab")) {
            throw new AssertionError(name + " should have role='tab' but was '" + role + "'");
        }
        if (attr(el, "aria-selected") == null) {
            throw new AssertionError(name + " missing aria-selected");
        }
    }

    /**
     * Validates that an element follows button semantics:
     * <ul>
     *   <li>tagName is "button" or "a"</li>
     *   <li>role is "button" or "link" (if present)</li>
     * </ul>
     *
     * @param el   WebElement representing a button
     * @param name Descriptive element name for logs
     * @throws AssertionError if semantics are not satisfied
     */
    public void assertButtonSemantics(WebElement el, String name) {
        String tag = safe(() -> el.getTagName());
        if (tag != null) {
            logger.info("{} tagName={}", name, tag);
            if (!(tag.equalsIgnoreCase("button") || tag.equalsIgnoreCase("a"))) {
                throw new AssertionError(name + " should be <button> or <a>, but was <" + tag + ">");
            }
        }
        String role = attr(el, "role");
        if (role != null && !(role.equalsIgnoreCase("button") || role.equalsIgnoreCase("link"))) {
            throw new AssertionError(name + " expected role 'button' or 'link' but was '" + role + "'");
        }
    }

    /**
     * Executes an action with retries.
     *
     * @param attempts Number of retry attempts
     * @param action   Lambda/Supplier to execute
     * @param name     Descriptive name for logging
     * @param <T>      Return type of the action
     * @return Result of the action if successful
     * @throws RuntimeException if all attempts fail
     */
    public <T> T retry(int attempts, Supplier<T> action, String name) {
        Throwable last = null;
        for (int i = 1; i <= attempts; i++) {
            try {
                return action.get();
            } catch (Throwable t) {
                last = t;
                logger.warn("Attempt {}/{} failed for {}: {}", i, attempts, name, t.getMessage());
            }
        }
        throw asRuntime("All attempts failed for " + name, last);
    }

    // ========================================================================
    // Internal Utilities
    // ========================================================================

    private String textOrAria(WebElement el) {
        String t = safe(el::getText);
        if (t != null && !t.trim().isEmpty()) return t;
        String aria = attr(el, "aria-label");
        return aria == null ? "" : aria;
    }

    private String attr(WebElement el, String name) {
        return safe(() -> el.getAttribute(name));
    }

    private String normalize(String s) {
        if (s == null) return "";
        return Normalizer.normalize(s, Normalizer.Form.NFKC)
                .replace('\u00A0', ' ')
                .replaceAll("\\s+", " ")
                .trim();
    }

    private RuntimeException asRuntime(String msg, Throwable t) {
        return (t instanceof RuntimeException) ? (RuntimeException) t : new RuntimeException(msg, t);
    }

    private String safeFile(String name) {
        return name.replaceAll("[^a-zA-Z0-9-_\\.]", "_");
    }

    private <T> T safe(Supplier<T> s) {
        try {
            return s.get();
        } catch (Throwable ignore) {
            return null;
        }
    }


    public void pauseForScreenshot() {
        try {
            waitForDomReady(5000);      // up to ~5s for readyState=complete
            Thread.sleep(600);          // small buffer for UI paints/animations
        } catch (InterruptedException ie) {
            Thread.currentThread().interrupt();
        } catch (Exception ignored) {
            // ignore and proceed to screenshot
        }
    }

    /**
     * Polls document.readyState until 'complete' or timeoutMillis elapses.
     */
    public void waitForDomReady(long timeoutMillis) {
        long end = System.currentTimeMillis() + timeoutMillis;
        while (System.currentTimeMillis() < end) {
            try {
                Object state = ((JavascriptExecutor) driver).executeScript("return document.readyState");
                if ("complete".equals(String.valueOf(state))) return;
            } catch (Exception ignored) {
            }
            try {
                Thread.sleep(200);
            } catch (InterruptedException ie) {
                Thread.currentThread().interrupt();
                return;
            }
        }
    }

    public boolean isVisible(WebElement el) {
        try {
            return el != null && el.isDisplayed();
        } catch (NoSuchElementException | StaleElementReferenceException e) {
            return false;
        }
    }

    public String safeGetText(WebElement el) {
        try {
            return el.getText() == null ? "" : el.getText().trim();
        } catch (StaleElementReferenceException e) {
            logger.warn("singleValue became stale while reading text.", e);
            return "";
        }
    }


    private static long getLong(String key, long def) {
        try {
            String v = ConfigReader.get(key);
            if (v == null || v.trim().isEmpty()) return def;
            return Long.parseLong(v.trim());
        } catch (Throwable t) {
            // keep it simple; fall back to default if parsing fails
            System.err.println("[Thresholds] Using default for " + key + " due to: " + t.getMessage());
            return def;
        }
    }

//
//    /**
//     * Kept for compatibility: logs with NAV thresholds (menus/tabs/other nav).
//     */
//    public void logLoadTime(String label, Instant start) {
//        logLoadTimeAndReturnMs(
//                label, start,
//                ReusableCommonMethods.NAV_WARN_MS,
//                ReusableCommonMethods.NAV_FAIL_MS
//        );
//    }

//    /**
//     * Kept for compatibility: returns ms using NAV thresholds.
//     * (Prefer the 4-arg overload when you need custom limits, e.g., login.)
//     */
//    public long logLoadTimeAndReturnMs(String label, Instant start) {
//        return logLoadTimeAndReturnMs(
//                label, start,
//                ReusableCommonMethods.NAV_WARN_MS,
//                ReusableCommonMethods.NAV_FAIL_MS
//        );
//    }

//    /**
//     * New overload: specify WARN/FAIL thresholds explicitly.
//     * Used by login flow (e.g., WARN=30s, FAIL=40s) while other nav keeps 12s/20s.
//     */
//    public long logLoadTimeAndReturnMs(String label, Instant start, long warnMs, long failMs) {
//        Duration nav = NavContext.stopDuration();
//        long elapsedMs = nav.toMillis() > 0
//                ? nav.toMillis()
//                : Duration.between(start, Instant.now()).toMillis();
//
//        double elapsedSec = elapsedMs / 1000.0;
//        String secStr = String.format("%.2f", elapsedSec);
//        long warnSec = warnMs / 1000;
//        long failSec = failMs / 1000;
//
//        // Always attach simple raw timing (no thresholds in the line)
//        logToAllure("⏱️ Load Time (" + label + ")", secStr + " seconds");
//        logStep(label + " took " + secStr + " s");
//
//        // Threshold-based messages (clear, human-readable)
//        if (elapsedMs >= failMs) {
//            String msg = String.format("%s took %s s — more than %d s. Failing (SLA %ds).",
//                    label, secStr, failSec, failSec);
//            logger.error(msg);
//            logToAllure("❌ Load Time Failure", msg);
//        } else if (elapsedMs >= warnMs) {
//            String msg = String.format("%s took %s s — more than %d s.",
//                    label, secStr, warnSec);
//            logger.warn(msg);
//            logToAllure("⚠️ Load Time Warning", msg);
//        } else {
//            logger.info("{} completed in {} s (≤ {} s).", label, secStr, warnSec);
//        }
//
//        return elapsedMs;
//    }

    public int httpStatus(String url) throws Exception {
        HttpURLConnection conn = (HttpURLConnection) new URL(url).openConnection();
        conn.setInstanceFollowRedirects(true);
        conn.setConnectTimeout(10_000);
        conn.setReadTimeout(15_000);
        conn.setRequestMethod("GET");
        conn.connect();
        int code = conn.getResponseCode();
        conn.disconnect();
        return code;
    }

    public boolean isFirefoxPresent() {
        String[] candidates = new String[] {
                "C:\\\\Program Files\\\\Mozilla Firefox\\\\firefox.exe",
                "C:\\\\Program Files (x86)\\\\Mozilla Firefox\\\\firefox.exe"
        };
        for (String p : candidates) {
            if (Files.exists(Paths.get(p))) return true;
        }
        // Many setups have Firefox on PATH; we’ll be lenient:
        return true; // set to false if you want strict skip unless path exists.
    }

    public long readLong(String key, long def) {
        try {
            // robust against inline comments e.g., "30000   # 30s"
            String raw = ConfigReader.get(key);
            if (raw == null) return def;
            String digits = raw.split("#")[0].replaceAll("[^0-9]", "");
            return digits.isEmpty() ? def : Long.parseLong(digits);
        } catch (Exception e) {
            return def;
        }
    }

//    public String switchToNewTab(String parent) {
//        wait.waitForNumberOfWindowsToBe(driver.getWindowHandles().size() + 1);
//
//        Set<String> handles = driver.getWindowHandles();
//        for (String h : handles) {
//            if (!h.equals(parent)) {
//                driver.switchTo().window(h);
//                return h;
//            }
//        }
//        throw new IllegalStateException("New tab did not appear.");
//    }



}






