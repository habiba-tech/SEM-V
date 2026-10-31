
import org.openqa.selenium.*;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.Test;

public class DropdownVerificationTest {

    @Test
    public void verifyDropdown() {
        WebDriver d = new FirefoxDriver();

        try {
            d.get("https://the-internet.herokuapp.com/dropdown");

            Select s = new Select(d.findElement(By.id("dropdown")));

            // Count options
            Assert.assertEquals(s.getOptions().size(), 3);
            System.out.println("Total Options: " + s.getOptions().size());

            // Check default option
            Assert.assertEquals(s.getFirstSelectedOption().getText(),
                    "Please select an option");

            // Select by text
            s.selectByVisibleText("Option 1");
            Assert.assertEquals(s.getFirstSelectedOption().getText(), "Option 1");

            // Select by index
            s.selectByIndex(2);
            Assert.assertEquals(s.getFirstSelectedOption().getText(), "Option 2");

            // Select by value
            s.selectByValue("1");
            Assert.assertEquals(s.getFirstSelectedOption().getText(), "Option 1");

            System.out.println("Dropdown Test PASSED");

        } finally {
            d.quit();
        }
    }
}
