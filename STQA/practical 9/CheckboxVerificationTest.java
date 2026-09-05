import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.time.Duration;
import java.util.List;

public class CheckboxVerificationTest {

    WebDriver driver;

    @BeforeMethod
    public void setUp() {

        // Launch Firefox
        driver = new FirefoxDriver();

        // Maximize browser
        driver.manage().window().maximize();

        // Implicit wait
        driver.manage().timeouts()
                .implicitlyWait(Duration.ofSeconds(10));

        // Open webpage
        driver.get(
                "https://the-internet.herokuapp.com/checkboxes"
        );
    }

    @Test
    public void verifyCheckboxes() {

        // Step 1: Identify all checkboxes
        List<WebElement> checkboxes =
                driver.findElements(
                        By.cssSelector("input[type='checkbox']")
                );

        // Step 2: Count total checkboxes
        int totalCheckboxes = checkboxes.size();

        System.out.println(
                "Total checkboxes: " + totalCheckboxes
        );

        // Step 3: Count checked and unchecked
        int checkedCount = 0;
        int uncheckedCount = 0;

        for (WebElement checkbox : checkboxes) {

            if (checkbox.isSelected()) {
                checkedCount++;
            } else {
                uncheckedCount++;
            }
        }

        System.out.println(
                "Checked checkboxes: " + checkedCount
        );

        System.out.println(
                "Unchecked checkboxes: " + uncheckedCount
        );

        // Step 4: Validate count
        Assert.assertEquals(
                totalCheckboxes,
                checkedCount + uncheckedCount,
                "Checkbox count is incorrect"
        );

        // Step 5: Dynamic selection
        for (WebElement checkbox : checkboxes) {

            if (!checkbox.isSelected()) {
                checkbox.click();
            }
        }

        // Step 6: Validate all checkboxes are selected
        for (WebElement checkbox : checkboxes) {

            Assert.assertTrue(
                    checkbox.isSelected(),
                    "Checkbox was not selected"
            );
        }

        // Step 7: Display final status
        System.out.println("After dynamic selection:");

        for (int i = 0; i < checkboxes.size(); i++) {

            System.out.println(
                    "Checkbox " + (i + 1) +
                            " selected: " +
                            checkboxes.get(i).isSelected()
            );
        }
    }

    @AfterMethod
    public void tearDown() {

        // Close browser
        if (driver != null) {
            driver.quit();
        }
    }
}
