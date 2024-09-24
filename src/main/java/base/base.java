//package base;
//
//import java.io.File;
//import java.io.FileInputStream;
//import java.io.IOException;
//import java.time.Duration;
//import java.util.Properties;
//
//import org.openqa.selenium.WebDriver;
//import org.openqa.selenium.edge.EdgeDriver;
//import org.openqa.selenium.support.PageFactory;
// 
//import org.testng.*;
//import org.testng.annotations.*;
//
//import Practo_pages.Configuration;
//
//import com.aventstack.extentreports.ExtentReports;
//import com.aventstack.extentreports.ExtentTest;
//import com.aventstack.extentreports.reporter.ExtentReporter;
//import com.aventstack.extentreports.reporter.ExtentSparkReporter;
//import com.aventstack.extentreports.reporter.configuration.Theme;
//
////import sauce_labs_main.configReader;
//
// 
//public class base {
//
//	public static EdgeDriver driver;
//	public static ExtentSparkReporter htmlreport;
//	public static ExtentReports report;
//	public static ExtentTest test;
//	public static String url="https://www.practo.com/";
////	 configReader configReader;
//	public static Configuration configuration;
//
//	
//	
//	public static void Browser_Setup() {
//		htmlreport=new ExtentSparkReporter(new File("C:\\data\\sauce_labs.html"));
//		htmlreport.config().setReportName("practo");
//		htmlreport.config().setDocumentTitle("practo");
//		htmlreport.config().setTheme(Theme.DARK);
//		report=new ExtentReports();
//		report.setSystemInfo("Environment", "TestEnv");
//		report.setSystemInfo("TesterName", "practo");
//		report.attachReporter(htmlreport);
//		driver=new EdgeDriver();
//		driver.manage().window().maximize();
//
//	}
//	
//	public static void openurl() throws InterruptedException {
//		
////		configuration = new Configuration();
//		
////		driver.get(configuration.getProperty("url"));
//		driver.get(url);
//		Thread.sleep(3000);
//	}
//	public void close_browser() {
//
//		driver.quit();
//	}
//	
//	public void report_flush() {
//		report.flush();
//	}
//}
//



package base;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.time.Duration;
import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.support.PageFactory;

import org.testng.annotations.*;

import Practo_pages.Configuration;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;
import com.aventstack.extentreports.util.Assert;

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
//            Assert.fail("Failed to load configuration file");
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

