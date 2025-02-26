package com.aepl.sam.base;

import java.time.Duration;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.asserts.SoftAssert;

import com.aepl.sam.constants.Constants;
import com.aepl.sam.pages.LoginPage;
import com.aepl.sam.utils.ConfigProperties;
import com.aepl.sam.utils.WebDriverFactory;

public class TestBase {

    protected static WebDriver driver; 
    protected static WebDriverWait wait;
    protected static SoftAssert softAssert;
    protected static LoginPage loginPage;
    protected final Logger logger = LogManager.getLogger(TestBase.class);

    @BeforeSuite
    public void setUp() {
        if (driver == null) {  
            ConfigProperties.initialize("qa");
            String browserType = ConfigProperties.getProperty("browser");
            System.out.println("Setting up WebDriver for " + browserType + " browser.");
            driver = WebDriverFactory.getWebDriver(browserType);
            driver.manage().window().maximize();
            driver.get(Constants.BASE_URL);

            loginPage = new LoginPage(driver);
            wait = new WebDriverWait(driver, Duration.ofSeconds(10));
            softAssert = new SoftAssert();
            logger.info("Navigated to: " + Constants.BASE_URL);

            // First Login here
            login();
        }
    }

    @BeforeMethod
    public void zoomChrome() {
        if (driver != null) {
            ((JavascriptExecutor) driver).executeScript("document.body.style.zoom='80%'");
        }
    }

    @AfterSuite
    public void tearDown() {
        if (driver != null) {
            logger.info("Closing the browser after all test classes and suite.");
            
            // Logged out after suite here
            logout();
            driver.quit();
            driver = null;  
        } else {
            logger.warn("Driver was null; no browser to close.");
        }
    }

    // Helper Functions
    public void login() {
        loginPage.enterUsername(ConfigProperties.getProperty("username"))
                 .enterPassword("password")
                 .clickLogin();
        System.out.println("Login Successfully");
    }

    public void logout() {
        loginPage.clickLogout();
        System.out.println("Logout Successfully");
    }
}
