
import org.openqa.selenium.*;
import org.openqa.selenium.firefox.FirefoxDriver;
import java.util.List;

public class DataExtraction {
    public static void main(String[] args) {
        WebDriver d = new FirefoxDriver();

        try {
            d.get("https://aldercrest.catlium.in/students.html");

            List<WebElement> rows = d.findElements(
                    By.cssSelector("#studentTable tbody tr"));

            int total = 0, above60 = 0;

            for (WebElement row : rows) {
                List<WebElement> cells = row.findElements(By.tagName("td"));

                String name = cells.get(0).getText();
                int java = Integer.parseInt(cells.get(1).getText());
                int dbms = Integer.parseInt(cells.get(2).getText());

                total++;

                if (java > 60 || dbms > 60)
                    above60++;

                System.out.println(name + " | Java: " + java +
                        " | DBMS: " + dbms);
            }

            double percentage = total == 0 ? 0 : above60 * 100.0 / total;

            System.out.println("Total Students: " + total);
            System.out.println("Students above 60: " + above60);
            System.out.println("Percentage: " + percentage + "%");

        } finally {
            d.quit();
        }
    }
}
