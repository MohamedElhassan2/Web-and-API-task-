package Tests;

import Configuration.ConfigurationFile;
import io.cucumber.testng.AbstractTestNGCucumberTests;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.ITestResult;
import org.testng.annotations.*;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.concurrent.TimeUnit;

public class TestBase {

    public static WebDriver driver;
    ConfigurationFile configurationFile;
    // this method will run before each suite
    @BeforeSuite
    // the browser name will be taken from the TestNG file and if it is not exist the chrome browser will be the optional one.
    @Parameters({"browser"})
    public void StartDriver (@Optional("chrome") String browserName) throws IOException
    {
        configurationFile = new ConfigurationFile();
        // ignorecase -- ignore case from name B or b
        if (browserName.equalsIgnoreCase("chrome"))
        {
            System.setProperty("webdriver.chrome.driver",configurationFile.ChromeDriverPath);
            driver = new ChromeDriver();
        }
        else if (browserName.equalsIgnoreCase("Firefox"))
        {
            String path = System.getProperty("user.dir");   // return project folder path
            System.setProperty("webdriver.gecko.driver",configurationFile.FirefoxDriverPath );
            driver = new FirefoxDriver();
        }
        driver.manage().window().maximize();
        driver.get(configurationFile.WebsiteURL);
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
    }

    // this method will run after each suite
    @AfterSuite(enabled = false)
    public void StopDriver()
    {
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
        driver.quit();
    }

    // Take screen shot when testcase fail and add it in the screenshots folder
    @AfterMethod
    public void ScreenShot (ITestResult result)
    {
        if (result.getStatus() == ITestResult.FAILURE)
        {
            System.out.println("Failed!");

        }
    }
}