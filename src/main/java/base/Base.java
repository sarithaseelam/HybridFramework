package base;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

public class Base {

    public WebDriver driver;
    public Properties prop;

    public WebDriver initializeDriver() throws IOException {

        prop=new Properties();
        FileInputStream fis=new FileInputStream("./src/main/resources/data.properties");

        prop.load(fis);

        String browserName=prop.getProperty("browser");

        System.out.println(browserName);

        if(browserName.contains("chrome")){
            /*If pulling from maven dependency github bonigarcia*/
            WebDriverManager.chromedriver().setup();
            //System.setProperty("webdriver.chrome.driver",".\\drivers\\chromedriver.exe");
            driver=new ChromeDriver();
        }
        else if(browserName.contains("ie")){
            /*IE property instantiation*/
        }
        else if(browserName.contains("ff")){
            /*firefox property instantiation*/
        }

        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        driver.manage().window().maximize();
        return driver;
    }

    public String getScreenShotPath(String testCase,WebDriver driver) throws IOException {
        TakesScreenshot ts=(TakesScreenshot)driver;
        File source=ts.getScreenshotAs(OutputType.FILE);
        String destinationFile="C:\\Users\\SiddiqAb\\OneDrive - Government of Ontario\\Desktop\\hybridframework\\reports\\"+testCase+".png";
        FileUtils.copyFile(source,new File(destinationFile));
        return destinationFile;
    }
}
