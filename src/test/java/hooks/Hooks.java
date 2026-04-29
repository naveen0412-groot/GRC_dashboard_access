package hooks;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.devtools.DevTools;
import org.openqa.selenium.devtools.HasDevTools;
import org.openqa.selenium.devtools.v120.browser.Browser;
import utils.AllureEnvironmentWriter;
import utils.AllureTrendUtils;
import utils.ConfigReader;
import utils.ScreenshotUtils;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.Duration;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Stream;

/**
 * Hooks.java
 * Purpose:
 * This class contains Cucumber Hooks for setting up and tearing down the WebDriver
 * before and after each test scenario. It supports:
 * ✅ WebDriver initialization with Chrome (headless or headed)
 * ✅ Screenshot folder cleanup (once per test run)
 * ✅ Page timeouts and window sizing
 * ✅ Auto-login before non-login scenarios
 * ✅ ExtentReports & Allure reporting integration
 * ✅ Screenshot capture and embedding for failed scenarios
 */


public class Hooks {

    public static WebDriver driver;
    private static final Logger logger = LogManager.getLogger(Hooks.class);

    static {
        // Create Allure environment.properties once before all tests
        AllureEnvironmentWriter.createEnvironmentFile();
    }

    @Before
    public void setup(Scenario scenario) throws InterruptedException {
        // ---- one-time project bootstrapping
        if (System.getProperty("init.once") == null) {
            ScreenshotUtils.clearScreenshotFolder();
            AllureTrendUtils.preserveTrendHistory();
            AllureEnvironmentWriter.createEnvironmentFile();
            System.setProperty("init.once", "true");
            logger.info("✅ One-time setup done: screenshots, trend, environment file created.");
        }

//        if (scenario.getSourceTagNames().contains("@compatibility")) {
//            logger.info("🔧 Compatibility scenario detected — skipping default Chrome setup & auto-login.");
//            return;
//        }
        WebDriverManager.chromedriver().setup();

        // ---- downloads dir (native absolute path, Windows-safe)
        Path downloadDirPath = Paths.get(System.getProperty("user.dir"), "downloads");
        try { Files.createDirectories(downloadDirPath); } catch (IOException ignored) {}
        String downloadDir = downloadDirPath.toAbsolutePath().toString();

        System.setProperty("download.dir", downloadDir);
        logger.info("📂 Using download dir: {}", downloadDir);

        // ---- clean downloads BEFORE any scenario runs
        try (Stream<Path> paths = Files.list(downloadDirPath)) {
            paths.filter(Files::isRegularFile).forEach(p -> {
                try { Files.deleteIfExists(p); } catch (IOException ignored) {}
            });
            logger.info("🧹 Download folder cleaned: {}", downloadDir);
        } catch (IOException e) {
            logger.warn("⚠️ Failed to clean download folder: {}", e.getMessage());
        }

        // ---- clean downloads BEFORE any scenario runs
        try (Stream<Path> paths = Files.list(downloadDirPath)) {
            paths.filter(Files::isRegularFile).forEach(p -> {
                try { Files.deleteIfExists(p); } catch (IOException ignored) {}
            });
            logger.info("🧹 Download folder cleaned: {}", downloadDir);
        } catch (IOException e) {
            logger.warn("⚠️ Failed to clean download folder: {}", e.getMessage());
        }

        // ---- Chrome options & prefs
        ChromeOptions options = new ChromeOptions();

        String headless = System.getProperty("headless", ConfigReader.get("headless"));

        Map<String, Object> prefs = new HashMap<>();
        prefs.put("download.default_directory", downloadDir);             // absolute native path
        prefs.put("download.prompt_for_download", false);
        prefs.put("download.directory_upgrade", true);
        prefs.put("profile.default_content_setting_values.automatic_downloads", 1);
        prefs.put("safebrowsing.enabled", true);
        options.setExperimentalOption("prefs", prefs);

        if (Boolean.parseBoolean(headless)) {
            options.addArguments("--headless=new");
            logger.info("🔧 Running in headless mode (system or config).");
        } else {
            logger.info("🖥️ Running in visible (headed) mode.");
        }
        options.addArguments(
                "--disable-gpu",
                "--no-sandbox",
                "--disable-dev-shm-usage",
                "--window-size=1920,1080",
                "--force-device-scale-factor=1",
                "--hide-scrollbars",
                "--remote-allow-origins=*"
        );

        logger.info("🔧 ChromeOptions set for 1920x1080 headless/visual run");

        // ---- Create driver
        driver = new ChromeDriver(options);

        // ---- Allow downloads via DevTools (works in headless=new; harmless in headed)
        // Requires selenium-devtools-v139; adjust v### if your devtools artifact differs.
        try {
            HasDevTools devToolsDriver = (HasDevTools) driver;
            DevTools devTools = devToolsDriver.getDevTools();
            devTools.createSession();

            devTools.send(Browser.setDownloadBehavior(
                    Browser.SetDownloadBehaviorBehavior.ALLOW,
                    java.util.Optional.empty(),          // BrowserContextID (none)
                    java.util.Optional.of(downloadDir),  // your downloads folder
                    java.util.Optional.of(true)          // eventsEnabled
            ));
            logger.info("✅ DevTools download behavior set to ALLOW → {}", downloadDir);

        } catch (Throwable t) {
            // If the devtools module/version isn’t on classpath, we still proceed with prefs.
            logger.warn("⚠️ Could not set DevTools download behavior. Using Chrome prefs only. {}", t.toString());
        }

        // ---- window & timeouts
        driver.manage().window().setSize(new Dimension(1920, 1080));
        driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(
                Long.parseLong(ConfigReader.get("pageLoadTimeout"))));
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(
                Long.parseLong(ConfigReader.get("implicitWait"))));

        logger.info("🚀 WebDriver setup complete for scenario: {}", scenario.getName());

    }

    @After
    public void tearDown(Scenario scenario) {
        String scenarioName = scenario.getName().replace(" ", "_");

        try {
            if (scenario.isFailed() && driver instanceof TakesScreenshot) {
                try {
                    String screenshotName = "Failure_" + scenarioName;
                    ScreenshotUtils.takeScreenshot(driver, screenshotName);
                    ScreenshotUtils.attachScreenshotToAllure(driver, screenshotName);
                } catch (WebDriverException e) {
                    logger.warn("Could not capture failure screenshot: {}", e.getMessage());
                }
            }
        } finally {
            try {
                if (driver != null) {
                    driver.quit();
                    logger.info("🪚 Browser closed after scenario: {}", scenario.getName());
                }
            } catch (Exception e) {
                logger.warn("Error during driver.quit(): {}", e.getMessage());
            }
        }
    }


}