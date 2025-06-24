package com.aepl.sam.base;

import java.time.Duration;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.*;

import com.aepl.sam.actions.MouseActions;
import com.aepl.sam.constants.Constants;
import com.aepl.sam.pages.LoginPage;
import com.aepl.sam.utils.ConfigProperties;
import com.aepl.sam.utils.WebDriverFactory;

public class TestBase {

    protected WebDriver driver;
    protected WebDriverWait wait;
    protected MouseActions action;
    protected LoginPage loginPage;
    protected final Logger logger = LogManager.getLogger(this.getClass().getSimpleName());

    @BeforeClass
    public void setUp() {
        try {
            logger.info("Initializing configuration for QA environment.");
            ConfigProperties.initialize("qa");

            String browserType = ConfigProperties.getProperty("browser");
            logger.info("Setting up WebDriver for {} browser.", browserType);
            driver = WebDriverFactory.getWebDriver(browserType);

            if (driver == null) {
                logger.error("WebDriver initialization failed. Driver is null.");
                throw new RuntimeException("WebDriver initialization failed.");
            }

            // Browser setup
            driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
            wait = new WebDriverWait(driver, Duration.ofSeconds(10));
            driver.manage().window().maximize();
            driver.get(Constants.BASE_URL);
            logger.info("Navigated to: {}", Constants.BASE_URL);

            // Page + action setup
            loginPage = new LoginPage(driver, wait, logger);
            action = new MouseActions(driver);

            // Perform login for all test classes
            login();

        } catch (Exception e) {
            logger.error("Error during test setup: {}", e.getMessage(), e);
            throw e;
        }
    }

    @BeforeMethod
    public void applyZoom() {
        try {
            if (driver != null) {
                logger.info("Applying zoom level 67% on Chrome.");
                ((JavascriptExecutor) driver).executeScript("document.body.style.zoom='67%'");
            }
        } catch (Exception e) {
            logger.warn("Failed to apply zoom: {}", e.getMessage());
        }
    }

    @AfterClass(alwaysRun = true)
    public void tearDown() {
        try {
            logger.info("Starting teardown: logout and browser quit.");
            logout();
            if (driver != null) {
                driver.quit();
                logger.info("Browser closed successfully.");
            }
        } catch (Exception e) {
            logger.error("Error during teardown: {}", e.getMessage(), e);
        }
    }

    //  Login Helper
    protected void login() {
        try {
            logger.info("Attempting to log in.");
            loginPage.enterUsername(ConfigProperties.getProperty("username"))
                     .enterPassword(ConfigProperties.getProperty("password"))
                     .clickLogin();
            logger.info("Login successful.");
        } catch (Exception e) {
            logger.error("Login failed: {}", e.getMessage(), e);
            throw e;
        }
    }

    // Logout Helper
    protected void logout() {
        try {
            logger.info("Attempting to log out.");
            loginPage.clickLogout();
            logger.info("Logout successful.");
        } catch (Exception e) {
            logger.warn("Logout failed or already logged out: {}", e.getMessage());
        }
    }
}
