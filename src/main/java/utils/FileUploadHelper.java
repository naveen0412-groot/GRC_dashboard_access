package utils;

import base.BasePage;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.*;
import org.sikuli.script.*;
import java.awt.*;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.StringSelection;
import java.awt.event.KeyEvent;

public class FileUploadHelper extends BasePage {

    Logger logger;
    WaitUtils wait;

    // Method to upload file using Robot and clipboard
    public FileUploadHelper(WebDriver driver) {
        super(driver);
        this.logger = LoggerUtils.getLogger(getClass());
        this.wait = new WaitUtils(driver, 30);
    }

    public static void uploadFile(String filePath) {
        try {
            // Copy the file path to the clipboard
            StringSelection stringSelection = new StringSelection(filePath);
            Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
            clipboard.setContents(stringSelection, null);

            // Use Robot class to perform Ctrl+V and Enter
            Robot robot = new Robot();
            robot.delay(5000); // Delay to allow the file dialog to open

            // Press Ctrl+V to paste the file path
            robot.keyPress(KeyEvent.VK_CONTROL);
            robot.keyPress(KeyEvent.VK_V);
            robot.keyRelease(KeyEvent.VK_V);
            robot.keyRelease(KeyEvent.VK_CONTROL);

            // Press Enter to confirm the file selection
            robot.keyPress(KeyEvent.VK_ENTER);
            robot.keyRelease(KeyEvent.VK_ENTER);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
