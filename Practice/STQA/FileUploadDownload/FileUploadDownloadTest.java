import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;

import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.datatransfer.StringSelection;
import java.io.File;

public class FileUploadDownloadTest {

    public static void main(String[] args) throws Exception {

        // -------------------------------
        // DOWNLOAD DIRECTORY
        // -------------------------------

        String downloadPath = "C:\\selenium\\downloads";

        File downloadFolder = new File(downloadPath);

        if (!downloadFolder.exists()) {
            downloadFolder.mkdir();
        }

        // -------------------------------
        // FIREFOX CONFIGURATION
        // -------------------------------

        FirefoxOptions options = new FirefoxOptions();

        options.addPreference(
                "browser.download.folderList", 2);

        options.addPreference(
                "browser.download.dir", downloadPath);

        options.addPreference(
                "browser.helperApps.neverAsk.saveToDisk",
                "text/plain");

        options.addPreference(
                "browser.download.manager.showWhenStarting",
                false);

        // -------------------------------
        // START FIREFOX
        // -------------------------------

        WebDriver driver = new FirefoxDriver(options);

        driver.manage().window().maximize();

        // Open HTML page
        driver.get(
                "file:///C:/selenium/file_upload_download.html");

        System.out.println("Page opened successfully.");

        // ==================================================
        // UPLOAD
        // ==================================================

        driver.findElement(By.id("fileUpload")).click();

        Thread.sleep(2000);

        // File path
        String filePath =
                "C:\\selenium\\upload_test.txt";

        // Copy path to clipboard
        StringSelection selection =
                new StringSelection(filePath);

        Toolkit.getDefaultToolkit()
                .getSystemClipboard()
                .setContents(selection, null);

        // Robot Class
        Robot robot = new Robot();

        // CTRL + V
        robot.keyPress(java.awt.event.KeyEvent.VK_CONTROL);
        robot.keyPress(java.awt.event.KeyEvent.VK_V);

        robot.keyRelease(java.awt.event.KeyEvent.VK_V);
        robot.keyRelease(java.awt.event.KeyEvent.VK_CONTROL);

        // ENTER
        robot.keyPress(java.awt.event.KeyEvent.VK_ENTER);
        robot.keyRelease(java.awt.event.KeyEvent.VK_ENTER);

        Thread.sleep(1500);

        // Click Upload
        driver.findElement(
                By.xpath("//button[text()='Upload']")
        ).click();

        Thread.sleep(1000);

        // Validate upload
        String status =
                driver.findElement(By.id("uploadStatus"))
                        .getText();

        System.out.println("Upload Status: " + status);

        if (status.contains("uploaded successfully")) {

            System.out.println(
                    "PASS: File upload successful."
            );

        } else {

            System.out.println(
                    "FAIL: File upload failed."
            );
        }

        // ==================================================
        // DOWNLOAD
        // ==================================================

        driver.findElement(
                By.xpath("//button[text()='Download File']")
        ).click();

        System.out.println(
                "Download started..."
        );

        // Wait for download to complete
        Thread.sleep(3000);

        // Check file
        File downloadedFile =
                new File(downloadPath + "\\sample.txt");

        if (downloadedFile.exists()) {

            System.out.println(
                    "PASS: File downloaded successfully."
            );

            System.out.println(
                    "File Path: "
                            + downloadedFile.getAbsolutePath()
            );

        } else {

            System.out.println(
                    "FAIL: Downloaded file not found."
            );
        }

        // Close browser
        driver.quit();

        System.out.println(
                "Test execution completed."
        );
    }
}
