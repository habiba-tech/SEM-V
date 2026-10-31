
import org.openqa.selenium.*;
import org.openqa.selenium.firefox.FirefoxDriver;
import java.util.List;

public class WebTableScoreAnalysis {
    public static void main(String[] args) {
        WebDriver d = new FirefoxDriver();

        try {
            d.get("file:///C:/Selenium/student_marks.html");

            List<WebElement> rows = d.findElements(
                    By.xpath("//table[@id='studentTable']/tbody/tr"));

            int count = 0;

            for (WebElement row : rows) {
                List<WebElement> c = row.findElements(By.tagName("td"));

                String name = c.get(0).getText();
                int java = Integer.parseInt(c.get(1).getText());
                int dbms = Integer.parseInt(c.get(2).getText());

                if (java > 60 || dbms > 60)
                    count++;

                System.out.println(name + " Java: " + java + " DBMS: " + dbms);
            }

            double percentage = rows.isEmpty() ? 0 :
                    count * 100.0 / rows.size();

            System.out.println("Total Students: " + rows.size());
            System.out.println("Above 60: " + count);
            System.out.println("Percentage: " + percentage + "%");

        } finally {
            d.quit();
        }
    }
}
