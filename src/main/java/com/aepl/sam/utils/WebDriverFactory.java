package com.aepl.sam.utils;

import java.util.HashMap;
import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import io.github.bonigarcia.wdm.WebDriverManager;

public class WebDriverFactory {
	private static final Logger logger = LogManager.getLogger(WebDriverFactory.class);

	public static WebDriver getWebDriver(String browserName) {
		logger.info("Requested WebDriver for browser: {}", browserName);

		WebDriver driver;
		switch (browserName.toLowerCase()) {
			case "chrome":
				driver = getChromeDriver();
				break;
			case "firefox":
				driver = getFirefoxDriver();
				break;
			case "edge":
				driver = getEdgeDriver();
				break;
			case "brave":
				driver = getBraveDriver();
				break;
			default:
				logger.error("Unsupported browser requested: {}", browserName);
				throw new IllegalArgumentException("Browser not supported: " + browserName);
		}

		logger.info("Successfully set up WebDriver for browser: {}", browserName);
		return driver;
	}

	private static WebDriver getChromeDriver() {
		String specificVersion = "138.0.7204.51";
		try {
			logger.debug("Initializing ChromeDriver version: {}", specificVersion);

			ChromeOptions options = new ChromeOptions();
			options.addArguments("--allow-running-insecure-content");
			options.addArguments("--safebrowsing-disable-download-protection");
			options.addArguments("--disable-popup-blocking");
			options.addArguments("--disable-infobars");
			options.addArguments("--disable-notifications");
			options.addArguments("--disable-extensions");
			options.addArguments("--disable-gpu");
//			options.addArguments("--headless", "--no-sandbox", "--disable-dev-shm-usage");

			Map<String, Object> prefs = new HashMap<>();
			prefs.put("download.default_directory", "C:\\Users\\Suraj Bhaleroa\\Downloads");
			prefs.put("download.prompt_for_download", false);
			prefs.put("download.directory_upgrade", true);
			prefs.put("safebrowsing.enabled", true);
			prefs.put("profile.default_content_settings.popups", 0);
			options.setExperimentalOption("prefs", prefs);

			WebDriverManager.chromedriver().driverVersion(specificVersion).setup();
			logger.info("ChromeDriver (v{}) setup completed.", specificVersion);

			return new ChromeDriver(options);

		} catch (Exception e) {
			logger.error("Error initializing ChromeDriver version: {}", specificVersion, e);
			throw new RuntimeException("Failed to initialize ChromeDriver version: " + specificVersion, e);
		}
	}

	private static WebDriver getFirefoxDriver() {
		try {
			logger.debug("Initializing FirefoxDriver");
			WebDriverManager.firefoxdriver().setup();
			logger.info("FirefoxDriver setup completed.");
			return new FirefoxDriver();
		} catch (Exception e) {
			logger.error("Error initializing FirefoxDriver", e);
			throw new RuntimeException("Failed to initialize FirefoxDriver", e);
		}
	}

	private static WebDriver getEdgeDriver() {
		try {
			logger.debug("Initializing EdgeDriver");
			WebDriverManager.edgedriver().setup();
			logger.info("EdgeDriver setup completed.");
			return new EdgeDriver();
		} catch (Exception e) {
			logger.error("Error initializing EdgeDriver", e);
			throw new RuntimeException("Failed to initialize EdgeDriver", e);
		}
	}

	private static WebDriver getBraveDriver() {
		try {
			logger.debug("Initializing BraveDriver");
			WebDriverManager.chromedriver().setup();
			logger.info("Chromedriver setup for Brave browser completed.");

			String braveExecutablePath = "C:\\Program Files\\BraveSoftware\\Brave-Browser\\Application\\brave.exe";
			logger.debug("Setting Brave binary path: {}", braveExecutablePath);

			ChromeOptions options = new ChromeOptions();
			options.setBinary(braveExecutablePath);
			options.addArguments("--remote-allow-origins=*");
			options.addArguments("--start-maximized");

			logger.info("Launching Brave browser with configured options.");
			return new ChromeDriver(options);

		} catch (Exception e) {
			logger.error("Error initializing Brave WebDriver", e);
			throw new RuntimeException("Failed to initialize Brave WebDriver.", e);
		}
	}
}
