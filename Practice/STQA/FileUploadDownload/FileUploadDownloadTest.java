import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;


import java.io.File;
import java.util.Scanner;


public class FileUploadDownloadTest{
   public static void main(String[] args) throws Exception{
       String folder = "C://selenium//downloads";
       new File(folder).mkdirs();


       FirefoxOptions option = new FirefoxOptions();
       option.addPreference("browser.download.folderList",2);
       option.addPreference("browser.download.dir",folder);


       WebDriver driver = new FirefoxDriver(option);


       try{
           driver.get("file:///C:/selenium/index.html");


           File file = new File( folder + "sample.txt");


           if(file.exists()){ 
               System.out.println("File downloaded SUccessfullt ");
           }


           new Scanner(System.in).next();
       }finally{
           driver.quit();
       }
   }
}
