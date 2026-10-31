
import org.openqa.selenium.*;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.Test;
import java.util.List;

public class CheckboxVerificationTest {

    @Test
    public void verifyCheckboxes() {
        WebDriver d = new FirefoxDriver();

        try {
            d.get("https://the-internet.herokuapp.com/checkboxes");

            List<WebElement> boxes = d.findElements(By.cssSelector("input[type='checkbox']"));

            int checked = 0, unchecked = 0;

            for (WebElement b : boxes) {
                if (b.isSelected())
                    checked++;
                else
                    unchecked++;
            }

            System.out.println("Total: " + boxes.size());
            System.out.println("Checked: " + checked);
            System.out.println("Unchecked: " + unchecked);

            Assert.assertEquals(boxes.size(), checked + unchecked);

            for (WebElement b : boxes) {
                if (!b.isSelected())
                    b.click();

                Assert.assertTrue(b.isSelected());
            }

            System.out.println("All Checkboxes Selected");

        } finally {
            d.quit();
        }
    }
}
