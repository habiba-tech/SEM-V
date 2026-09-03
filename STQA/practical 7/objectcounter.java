import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class objectcounter {

    public static void main(String[] args) {

        // Start Firefox
        WebDriver driver = new FirefoxDriver();

        // Open local HTML page
        driver.get("file:///C:/selenium/object.html");

        // Count different objects
        int links = driver.findElements(By.tagName("a")).size();
        int images = driver.findElements(By.tagName("img")).size();
        int buttons = driver.findElements(By.tagName("button")).size();
        int inputs = driver.findElements(By.tagName("input")).size();

        // Calculate total
        int total = links + images + buttons + inputs;

        // Display results
        System.out.println("=================================");
        System.out.println("     WEB PAGE OBJECT COUNT");
        System.out.println("=================================");

        System.out.println("Number of Links   : " + links);
        System.out.println("Number of Images  : " + images);
        System.out.println("Number of Buttons : " + buttons);
        System.out.println("Number of Inputs  : " + inputs);

        System.out.println("---------------------------------");
        System.out.println("Total Objects     : " + total);
        System.out.println("=================================");

        // Close Firefox
        driver.quit();
    }
}
