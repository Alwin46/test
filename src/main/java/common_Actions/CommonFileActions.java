package common_Actions;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import org.apache.logging.log4j.LogManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.slf4j.Logger;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;

import java.io.FileInputStream;
import java.io.IOException;
import java.time.Duration;
import java.util.Properties;

public class CommonFileActions {

    public static WebDriver driver;
    public static Properties properties;
    public static ExtentReports reports;

    public void loadProperties() throws IOException {
        FileInputStream stream=new FileInputStream("config.properties");
        properties=new Properties();
        properties.load(stream);

        ExtentSparkReporter reporter=new ExtentSparkReporter("TestReport.html");
        reports=new ExtentReports();
        reports.attachReporter(reporter);
    }

    @BeforeSuite
    public void setUp() throws IOException {
        loadProperties();
        String browser=properties.getProperty("browser");
        String url=properties.getProperty("url");

        if (browser.equals("chrome")){
            driver=new ChromeDriver();
        }else if(browser.equals("firefox")){
            driver=new FirefoxDriver();
        }

        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
        driver.get(url);
    }

    @AfterSuite
    public void tearDown(){
        driver.quit();
        reports.flush();
    }
}
