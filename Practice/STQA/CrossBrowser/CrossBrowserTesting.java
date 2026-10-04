import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.remote.RemoteWebDriver;
import java.net.URI;
public class CrossBrowserTesting {
	public static void main(String[] args) throws Exception {
    	String gridURL = "http://localhost:4444";
        testChrome(gridURL);
        testFirefox(gridURL);
        testEdge(gridURL);
	}
	public static void testChrome(String gridURL) throws Exception {
 
    	long start = System.currentTimeMillis();
 
    	ChromeOptions options = new ChromeOptions();
 
    	WebDriver driver = new RemoteWebDriver(
                URI.create(gridURL).toURL(),
                options
    	);
 
        runTest(driver, "Chrome", start);
	}
 
	public static void testFirefox(String gridURL) throws Exception {
 
    	long start = System.currentTimeMillis();
 
        FirefoxOptions options = new FirefoxOptions();
 
    	WebDriver driver = new RemoteWebDriver(
                URI.create(gridURL).toURL(),
                options
    	);
 
        runTest(driver, "Firefox", start);
	}
 
	public static void testEdge(String gridURL) throws Exception {
 
    	long start = System.currentTimeMillis();
 
    	EdgeOptions options = new EdgeOptions();
 
    	WebDriver driver = new RemoteWebDriver(
                URI.create(gridURL).toURL(),
                options
    	);
 
        runTest(driver, "Edge", start);
	}
 
	public static void runTest(
        	WebDriver driver,
        	String browser,
        	long start) {
 
    	try {
 
            driver.manage().window().maximize();
 
            driver.get("https://www.google.com");
 
            System.out.println(
                    "\nBrowser: " + browser
        	);
 
            System.out.println(
                    "Title: " + driver.getTitle()
        	);
 
            System.out.println(
                    "URL: " + driver.getCurrentUrl()
        	);
 
        	long end = System.currentTimeMillis();
 
            System.out.println(
                    "Execution Time: "
                            + (end - start)
                            + " ms"
        	);
 
            System.out.println("Result: PASS");
 
    	} catch (Exception e) {
 
            System.out.println("Result: FAIL");
            System.out.println(e.getMessage());
 
    	} finally {
 
            driver.quit();
    	}
	}
}
 
