import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;

import java.time.Duration;
import java.util.List;

public class WebTableScoreAnalysis {

    public static void main(String[] args) {

        // Step 1: Launch Firefox browser
        WebDriver driver = new FirefoxDriver();

        try {

            // Step 2: Set implicit wait
            driver.manage().timeouts()
                    .implicitlyWait(Duration.ofSeconds(10));

            // Step 3: Maximize browser window
            driver.manage().window().maximize();

            // Step 4: Open student marks HTML page
            driver.get("file:///C:/Selenium/student_marks.html");

            // Step 5: Find all rows of the table
            List<WebElement> rows = driver.findElements(
                    By.xpath("//table[@id='studentTable']/tbody/tr")
            );

            // Variables for calculation
            int totalStudents = 0;
            int studentsAbove60 = 0;

            System.out.println("==============================================");
            System.out.println("          STUDENT SCORE ANALYSIS");
            System.out.println("==============================================");

            // Step 6: Extract data from each row
            for (WebElement row : rows) {

                // Find all cells in current row
                List<WebElement> columns =
                        row.findElements(By.tagName("td"));

                // Extract student name
                String studentName =
                        columns.get(0).getText();

                // Extract Java marks
                int javaMarks =
                        Integer.parseInt(columns.get(1).getText());

                // Extract DBMS marks
                int dbmsMarks =
                        Integer.parseInt(columns.get(2).getText());

                // Increase total student count
                totalStudents++;

                // Check if marks are above 60
                // in at least one subject
                boolean above60 =
                        javaMarks > 60 || dbmsMarks > 60;

                // If condition is true, increase count
                if (above60) {
                    studentsAbove60++;
                }

                // Display extracted data
                System.out.println(
                        "Student: " + studentName +
                                " | Java: " + javaMarks +
                                " | DBMS: " + dbmsMarks +
                                " | Above 60: " + above60
                );
            }

            // Step 7: Calculate percentage
            double percentage = 0;

            if (totalStudents > 0) {

                percentage =
                        ((double) studentsAbove60 /
                                totalStudents) * 100;
            }

            // Step 8: Display final result
            System.out.println("==============================================");
            System.out.println("Total Students           : "
                    + totalStudents);

            System.out.println("Students Above 60        : "
                    + studentsAbove60);

            System.out.printf(
                    "Percentage               : %.2f%%%n",
                    percentage
            );

            System.out.println("==============================================");

        } finally {

            // Step 9: Close browser
            driver.quit();
        }
    }
}
