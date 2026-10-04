import org.junit.jupiter.api.*;
import org.openqa.selenium.*;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class DynamicWebTableSortingTest {

    WebDriver driver;
    WebDriverWait wait;

    @BeforeEach
    void setUp() {

        driver = new FirefoxDriver();

        driver.manage().window().maximize();

        wait = new WebDriverWait(
                driver,
                Duration.ofSeconds(10)
        );

        // Change this path according to your computer
        driver.get("file:///C:/Selenium/dynamic.html");
    }

    @Test
    void dynamicTableSortingAndFiltering() {

        // -------------------------------------------------
        // 1. EXTRACT ORIGINAL TABLE DATA
        // -------------------------------------------------

        List<WebElement> rows =
                driver.findElements(
                        By.cssSelector("#studentTable tbody tr")
                );

        System.out.println("===== ORIGINAL TABLE DATA =====");

        for (WebElement row : rows) {

            List<WebElement> cells =
                    row.findElements(By.tagName("td"));

            System.out.println(
                    cells.get(0).getText() + " | " +
                            cells.get(1).getText() + " | " +
                            cells.get(2).getText()
            );
        }

        // Verify total number of rows
        assertEquals(
                5,
                rows.size(),
                "Incorrect number of students"
        );

        // -------------------------------------------------
        // 2. CLICK JAVA COLUMN FOR SORTING
        // -------------------------------------------------

        WebElement javaHeader =
                driver.findElement(
                        By.xpath("//th[contains(text(),'Java')]")
                );

        javaHeader.click();

        // -------------------------------------------------
        // 3. EXTRACT SORTED JAVA MARKS
        // -------------------------------------------------

        List<WebElement> sortedRows =
                driver.findElements(
                        By.cssSelector("#studentTable tbody tr")
                );

        List<Integer> actualMarks = new ArrayList<>();

        System.out.println("\n===== SORTED JAVA MARKS =====");

        for (WebElement row : sortedRows) {

            List<WebElement> cells =
                    row.findElements(By.tagName("td"));

            int marks =
                    Integer.parseInt(cells.get(1).getText());

            actualMarks.add(marks);

            System.out.println(
                    cells.get(0).getText()
                            + " = "
                            + marks
            );
        }

        // -------------------------------------------------
        // 4. VALIDATE SORTING
        // -------------------------------------------------

        List<Integer> expectedMarks =
                new ArrayList<>(actualMarks);

        Collections.sort(expectedMarks);

        System.out.println("\nExpected: " + expectedMarks);
        System.out.println("Actual:   " + actualMarks);

        assertEquals(
                expectedMarks,
                actualMarks,
                "Java column is NOT sorted correctly"
        );

        System.out.println(
                "PASS: Java column sorted correctly."
        );

        // -------------------------------------------------
        // 5. FILTER TABLE
        // -------------------------------------------------

        WebElement searchBox =
                driver.findElement(By.id("search"));

        searchBox.sendKeys("Rahul");

        // -------------------------------------------------
        // 6. VERIFY FILTERED RESULTS
        // -------------------------------------------------

        List<WebElement> filteredRows =
                driver.findElements(
                        By.cssSelector("#studentTable tbody tr")
                );

        boolean found = false;

        System.out.println("\n===== FILTERED RESULTS =====");

        for (WebElement row : filteredRows) {

            if (row.isDisplayed()) {

                String studentName =
                        row.findElements(By.tagName("td"))
                                .get(0)
                                .getText();

                System.out.println(studentName);

                assertTrue(
                        studentName.toLowerCase()
                                .contains("rahul"),
                        "Incorrect filtered result"
                );

                found = true;
            }
        }

        assertTrue(
                found,
                "No matching student found"
        );

        System.out.println(
                "PASS: Filtering results are correct."
        );
    }

    @AfterEach
    void tearDown() {

        if (driver != null) {
            driver.quit();
        }
    }
}
