
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.remote.RemoteWebDriver;
import java.net.URL;

public class SeleniumGrid {
    public static void main(String[] args) throws Exception {

        String browser = "firefox";
        WebDriver driver;

        if (browser.equalsIgnoreCase("chrome")) {
            driver = new RemoteWebDriver(
                    new URL("http://localhost:4444"),
                    new ChromeOptions());
        } else {
            driver = new RemoteWebDriver(
                    new URL("http://localhost:4444"),
                    new FirefoxOptions());
        }

        driver.get("https://www.google.com");
        System.out.println("Browser: " + browser);
        System.out.println("Page Title: " + driver.getTitle());

        driver.quit();
    }
}
