
package base;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.time.Duration;
import java.util.Properties;

import org.openqa.selenium.edge.EdgeDriver;
import org.testng.annotations.*;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

public class base {

    public static EdgeDriver driver;
    public static ExtentSparkReporter htmlreport;
    public static ExtentReports report;
    public static ExtentTest test;
    public static String url;
    public static Properties config;

    @BeforeSuite
    public void loadConfig() {
        try {
            config = new Properties();
            FileInputStream fis = new FileInputStream("src/main/resources/Config/configuration.properties");
            config.load(fis);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    
    public static void report_setup() {
    	htmlreport = new ExtentSparkReporter(new File(config.getProperty("report.path")));
        htmlreport.config().setReportName("practo");
        htmlreport.config().setDocumentTitle("practo");
        htmlreport.config().setTheme(Theme.DARK);

        report = new ExtentReports();
        report.setSystemInfo("Environment", "TestEnv");
        report.setSystemInfo("TesterName", "practo");
        report.attachReporter(htmlreport);
    }
    public static void Browser_Setup() {
        

        // Initialize the EdgeDriver
        driver = new EdgeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(Integer.parseInt(config.getProperty("implicitWait"))));
    }

    public static void openurl() throws InterruptedException {
        url = config.getProperty("url");
        driver.get(url);
        Thread.sleep(3000);
    }

    public void close_browser() {
        driver.quit();
    }

    public void report_flush() {
        report.flush();
    }
}

