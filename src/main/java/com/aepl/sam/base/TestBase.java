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

import com.aepl.sam.utils.ConfigProperties;
import com.aepl.sam.utils.WebDriverFactory;

public class TestBase {
	
	protected WebDriver driver;
	protected WebDriverWait wait;
	protected final Logger logger = LogManager.getLogger(TestBase.class);

	@BeforeMethod
	public void zoomChrome() {
		if (driver != null) {
			((JavascriptExecutor) driver).executeScript("document.body.style.zoom='75%'");
		}
	}

	@BeforeSuite
	public void setUp() {
		ConfigProperties.initialize("qa");
		String browserType = ConfigProperties.getProperty("browser.type");
		System.out.println("Setting up WebDriver for " + browserType + " browser.");
		driver = WebDriverFactory.getWebDriver(browserType);
		driver.manage().window().maximize();
		driver.get(ConfigProperties.getProperty("base.url"));
		
		// Initializing the wait
		this.wait =  new WebDriverWait(driver, Duration.ofSeconds(10));
		logger.info("Navigated to: " + ConfigProperties.getProperty("base.url"));
	}

	@AfterSuite
	public void tearDown() {
		if (driver != null) {
			logger.info("Closing the browser after all test classes and suite.");
			driver.quit();
		} else {
			logger.warn("Driver was null; no browser to close.");
		}
	}
}
