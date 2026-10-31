
import org.openqa.selenium.*;
import org.openqa.selenium.firefox.FirefoxDriver;

public class objectcounter {
    public static void main(String[] args) {
        WebDriver d = new FirefoxDriver();

        try {
            d.get("file:///C:/selenium/object.html");

            int links = d.findElements(By.tagName("a")).size();
            int images = d.findElements(By.tagName("img")).size();
            int buttons = d.findElements(By.tagName("button")).size();
            int inputs = d.findElements(By.tagName("input")).size();

            System.out.println("Links: " + links);
            System.out.println("Images: " + images);
            System.out.println("Buttons: " + buttons);
            System.out.println("Inputs: " + inputs);
            System.out.println("Total: " + (links + images + buttons + inputs));

        } finally {
            d.quit();
        }
    }
}
