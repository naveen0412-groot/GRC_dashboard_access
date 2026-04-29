package base;

import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import utils.LoggerUtils;
import utils.ReusableCommonMethods;
import utils.SeleniumHelperMethods;
import utils.WaitUtils;

/**
 * BasePage is an abstract foundation for all page objects.
 * It initializes commonly used utilities like WebDriver, wait handling, helper methods, and logging.
 * All other page classes should extend this class to inherit shared behavior.
 *
 * @kavitha Jagatheeswaran
 */

public class BasePage {
    protected WebDriver driver;
    protected WaitUtils wait;
    protected SeleniumHelperMethods helpers;
    protected Logger logger;
    protected ReusableCommonMethods commonMethods;

    public BasePage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WaitUtils(driver, 15);
        PageFactory.initElements(driver, this);
        // wait = new WaitUtils(driver);
        this.commonMethods = new ReusableCommonMethods(driver);
        helpers = new SeleniumHelperMethods();
        logger = LoggerUtils.getLogger(getClass());
    }
}

