
import org.openqa.selenium.*;
import org.openqa.selenium.firefox.FirefoxDriver;
import static org.junit.jupiter.api.Assertions.*;
import java.time.Duration;

public class LoginAutomation {
    public static void main(String[] args) {

        WebDriver driver = new FirefoxDriver();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));

        try {
            driver.get("https://www.saucedemo.com/");

            // Login
            driver.findElement(By.id("user-name")).sendKeys("standard_user");
            driver.findElement(By.id("password")).sendKeys("secret_sauce");
            driver.findElement(By.id("login-button")).click();

            // Check URL
            assertEquals("https://www.saucedemo.com/inventory.html",
                    driver.getCurrentUrl());
            System.out.println("URL: PASS");

            // Check Products page
            assertEquals("Products",
                    driver.findElement(By.className("title")).getText());
            System.out.println("Products: PASS");

            // Check Logout button
            driver.findElement(By.id("react-burger-menu-btn")).click();
            assertTrue(driver.findElement(By.id("logout_sidebar_link")).isDisplayed());
            System.out.println("Logout: PASS");

            System.out.println("LOGIN TEST PASSED");

        } finally {
            driver.quit();
        }
    }
}
