
import org.junit.jupiter.api.*;
import org.openqa.selenium.*;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;
import java.util.*;
import static org.junit.jupiter.api.Assertions.*;

public class DynamicWebTableSortingTest {

    WebDriver d;

    @BeforeEach
    void setup() {
        d = new FirefoxDriver();
        d.get("file:///C:/Selenium/dynamic.html");
    }

    @Test
    void testTable() {
        List<WebElement> rows =
                d.findElements(By.cssSelector("#studentTable tbody tr"));

        System.out.println("Total Students: " + rows.size());
        assertEquals(5, rows.size());

        // Sort Java column
        d.findElement(By.xpath("//th[contains(.,'Java')]")).click();

        new WebDriverWait(d, Duration.ofSeconds(10)).until(w -> {
            List<Integer> marks = new ArrayList<>();
            for (WebElement r : w.findElements(
                    By.cssSelector("#studentTable tbody tr")))
                marks.add(Integer.parseInt(
                        r.findElements(By.tagName("td")).get(1).getText()));

            List<Integer> sorted = new ArrayList<>(marks);
            Collections.sort(sorted);
            return marks.equals(sorted);
        });

        List<Integer> actual = new ArrayList<>();
        for (WebElement r : d.findElements(
                By.cssSelector("#studentTable tbody tr")))
            actual.add(Integer.parseInt(
                    r.findElements(By.tagName("td")).get(1).getText()));

        List<Integer> expected = new ArrayList<>(actual);
        Collections.sort(expected);

        assertEquals(expected, actual);
        System.out.println("PASS: Sorting " + actual);

        // Filter Rahul
        d.findElement(By.id("search")).sendKeys("Rahul");

        boolean found = false;
        for (WebElement r : d.findElements(
                By.cssSelector("#studentTable tbody tr"))) {
            if (r.isDisplayed()) {
                String name = r.findElements(By.tagName("td")).get(0).getText();
                assertTrue(name.toLowerCase().contains("rahul"));
                System.out.println("Filtered: " + name);
                found = true;
            }
        }

        assertTrue(found);
        System.out.println("PASS: Filtering");
    }

    @AfterEach
    void close() {
        if (d != null) d.quit();
    }
}
