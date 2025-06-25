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

import com.aepl.sam.actions.MouseActions;
import com.aepl.sam.constants.Constants;
import com.aepl.sam.pages.LoginPage;
import com.aepl.sam.utils.ConfigProperties;
import com.aepl.sam.utils.WebDriverFactory;

public class TestBase {

	protected static WebDriver driver;
	protected static WebDriverWait wait;
	protected MouseActions action;
	protected LoginPage loginPage;

	protected final Logger logger = LogManager.getLogger(this.getClass().getSimpleName());

	// ------------------ Setup Methods ------------------

	@BeforeSuite
	public void setUp() {
		if (driver == null) {
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

				driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
				wait = new WebDriverWait(driver, Duration.ofSeconds(10));

				driver.manage().window().maximize();
				driver.get(Constants.BASE_URL);

				loginPage = new LoginPage(driver, wait, logger);
				logger.info("Navigated to: {}", Constants.BASE_URL);

				if (!this.getClass().getSimpleName().equals("LoginPageTest")) {
					logger.info("Performing login setup for tests.");
					login();
				}

			} catch (Exception e) {
				logger.error("Error during test setup: {}", e.getMessage(), e);
				throw e;
			}
		}
	}

	@BeforeMethod
	public void zoomChrome() {
		if (driver != null) {
			logger.info("Applying zoom level 67% on Chrome.");
			((JavascriptExecutor) driver).executeScript("document.body.style.zoom='67%'");
		} else {
			logger.warn("Zoom not applied as driver is null.");
		}
	}

	@AfterSuite
	public void tearDown() {
		if (driver != null) {
			logger.info("Logging out and closing the browser after test suite execution.");
			try {
				logout();
				driver.quit();
				driver = null;
				logger.info("Browser closed successfully.");
			} catch (Exception e) {
				logger.error("Error while closing the browser: {}", e.getMessage(), e);
			}
		} else {
			logger.warn("Driver is already null; no browser to close.");
		}
	}

	// ------------------ Helper Methods ------------------

	public void login() {
		try {
			logger.info("Attempting to login.");

			loginPage.enterUsername(ConfigProperties.getProperty("username"))
					.enterPassword(ConfigProperties.getProperty("password")).clickLogin();

			logger.info("Login successful.");
		} catch (Exception e) {
			logger.error("Login failed: {}", e.getMessage(), e);
			throw e;
		}
	}

	public void logout() {
		try {
			logger.info("Logging out of the application.");
			loginPage.clickLogout();
			logger.info("Logout successful.");
		} catch (Exception e) {
			logger.error("Logout failed: {}", e.getMessage(), e);
			throw e;
		}
	}
}
