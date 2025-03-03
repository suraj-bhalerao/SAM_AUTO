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

			if (driver == null) {
				throw new RuntimeException("WebDriver initialization failed. Driver is null.");
			}

			wait = new WebDriverWait(driver, Duration.ofSeconds(10));

			driver.manage().window().maximize();
			driver.get(Constants.BASE_URL);

			System.out.println("Navigated to: " + Constants.BASE_URL);

			loginPage = new LoginPage(driver);
			softAssert = new SoftAssert();

			// First Login
			login();
		}
	}

	@BeforeMethod
	public void zoomChrome() {
		if (driver != null) {
			((JavascriptExecutor) driver).executeScript("document.body.style.zoom='80%'");
//			System.out.println("Chrome zoom level set to 80%");
		} else {
			System.out.println("Zoom not applied as driver is null.");
		}
	}

	@AfterSuite
	public void tearDown() {
		if (driver != null) {
			System.out.println("Logging out and closing the browser after test suite execution.");

			logout();
			driver.quit();
			driver = null;
		} else {
			System.out.println("Driver is already null; no browser to close.");
		}
	}

	// Login Helper Function
	public void login() {
		loginPage.enterUsername(ConfigProperties.getProperty("username")).enterPassword("password").clickLogin();
	}

	// Logout Helper Function
	public void logout() {
		loginPage.clickLogout();
	}
}
