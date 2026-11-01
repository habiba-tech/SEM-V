import org.openqa.selenium.*;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class Object {
    public static void main(String[] args) {
        WebDriver driver = new FirefoxDriver();
        driver.get("https://aldercrest.catlium.in/elements.html");

        int links = driver.findElements(By.tagName("a")).size();
        int image = driver.findElements(By.tagName("img")).size();
        int button = driver.findElements(By.tagName("button")).size();
        int input = driver.findElements(By.tagName("input")).size();

        int total = links + image + button + input;

        System.out.println("Links : " + links);
        System.out.println("img : " + image);
        System.out.println("Button : " + button);
        System.out.println("inputs : " + input);
        System.out.println("Total Object : " + total);

        driver.quit();
    }
}


