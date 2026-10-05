import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.net.URI;

public class CrossBrowserTesting {
   public static void main (String[] args) throws Exception{
       for(String browser : new String[] {"firefox" , "MicrosoftEdge"}){
         long intitialTime = System.currentTimeMillis();
           WebDriver driver = null ;


           try {
               DesiredCapabilities capabilities = new DesiredCapabilities();
               capabilities.setBrowserName(browser);


               driver = new RemoteWebDriver(URI.create("http://localhost:4444").toURL(),capabilities);
               driver.get("https://google.com");


               System.out.println("\nBrowser : " + browser);
               System.out.println(("\nTitle:" + driver.getTitle()));
               System.out.println("\nTime:" + (System.currentTimeMillis() - intitialTime) + "ms");
               System.out.println("\n Status : PASSED");
           }catch (Exception e){
               System.out.println("Exception" + e );
           }
       };
   };
}
