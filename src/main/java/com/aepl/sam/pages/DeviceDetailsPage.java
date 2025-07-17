package com.aepl.sam.pages;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.time.Duration;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.json.JSONObject;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.aepl.sam.constants.Constants;
import com.aepl.sam.locators.DeviceDetailsPageLocators;
import com.aepl.sam.utils.CommonMethods;

public class DeviceDetailsPage extends DeviceDetailsPageLocators {
	private WebDriver driver;
	private WebDriverWait wait;
	private CommonMethods comm;

	private final Logger logger = LogManager.getLogger(DeviceDetailsPage.class);

	public DeviceDetailsPage(WebDriver driver, WebDriverWait wait, CommonMethods comm) {
		this.driver = driver;
		this.wait = wait;
		this.comm = comm;
	}

	public void searchAndViewDevice() {
		try {
			logger.info("Attempting to search and view device with IMEI: {}", Constants.IMEI);

			WebElement canvas = driver.findElement(By.tagName("canvas"));
			logger.debug("Canvas element located for scrolling.");
			((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", canvas);
			Thread.sleep(500);

			WebElement searchField = driver.findElement(SEARCH_BOX_INPUT);
			comm.highlightElement(searchField, "solid purple");
			searchField.clear();
			searchField.sendKeys(Constants.IMEI);
			logger.debug("Entered IMEI in search field: {}", Constants.IMEI);

			((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", canvas);
			Thread.sleep(500);
			WebElement searchButton = driver.findElement(SEARCH_BOX_BTN);
			searchButton.click();
			logger.debug("Clicked on search button.");
			Thread.sleep(500);

			WebElement eyeIcon = driver.findElement(EYE_ICON);
			eyeIcon.click();
			logger.info("Clicked on eye icon to view device.");
		} catch (Exception e) {
			logger.error("An error occurred while searching and viewing the device: {}", e.getMessage(), e);
		}
	}

	public boolean allComponentDetails() {
		try {
			logger.info("Verifying if component details contain the IMEI: {}", Constants.IMEI);

			List<WebElement> listOfComponents = wait
					.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(ALL_COMPONENT));
			logger.debug("All component elements located successfully.");

			WebElement componentElement = listOfComponents.get(0);
			String componentText = componentElement.getText();
			logger.debug("First component text: {}", componentText);

			boolean result = componentText.contains(Constants.IMEI);

			if (result) {
				logger.info("Component details verification passed.");
			} else {
				logger.warn("Component details do not contain expected IMEI.");
			}
			return result;
		} catch (Exception e) {
			logger.error("Error while verifying component details: {}", e.getMessage(), e);
			return false;
		}
	}

	public void validateExportButton() {
		try {
			logger.info("Validating Export button functionality.");

			WebElement exportBtn = driver.findElement(EXPORT_BTN);
			((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", exportBtn);
			Thread.sleep(500);
			comm.highlightElement(exportBtn, "solid purple");

			for (int i = 0; i < 3; i++) {
				if (exportBtn.isDisplayed()) {
					exportBtn.click();
					logger.info("Clicked Export button attempt: {}", i + 1);

					Alert alert = driver.switchTo().alert();
					logger.debug("Alert detected with text: {}", alert.getText());
					alert.accept();
					logger.info("Alert accepted successfully.");
					Thread.sleep(500);
				}
			}
		} catch (Exception e) {
			logger.error("An error occurred while validating the Export button: {}", e.getMessage(), e);
		}
	}

	public String viewLoginPacket() {
		try {
			logger.info("Attempting to view login packet details.");

			List<WebElement> eyeIcons = driver.findElements(EYE_ICON);
			logger.debug("Found {} eye icons on the page.", eyeIcons.size());

			WebElement eyeElement = eyeIcons.get(5);
			((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", eyeElement);
			Thread.sleep(500);
			eyeElement.click();
			logger.info("Clicked 6th eye icon to open login packet details.");

			WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
			WebElement modal = wait.until(ExpectedConditions
					.visibilityOfElementLocated(By.xpath("//div[contains(@class, 'component-body')]")));

			List<WebElement> detailsElements = driver
					.findElements(By.xpath("//div[@class='component-body'][.//table]"));
			logger.debug("Found {} component-body elements containing tables.", detailsElements.size());

			WebElement frameElement = detailsElements.get(detailsElements.size() - 1);
			((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", frameElement);
			Thread.sleep(500);

			String loginPacketDetails = frameElement.getText();
			logger.debug("Raw login packet text:\n{}", loginPacketDetails);

			String[] lines = loginPacketDetails.split("\n");
			Map<String, String> dataMap = new LinkedHashMap<>();

			for (int i = 0; i < lines.length - 1; i++) {
				if (lines[i].endsWith(":")) {
					String key = lines[i].replace(":", "").trim();
					String value = lines[i + 1].trim();
					dataMap.put(key, value);
					i++; // skip value line
				}
			}

			JSONObject json = new JSONObject(dataMap);

			String directoryPath = "D:\\Sampark_Automation\\SAM_AUTO\\test-results\\outputFiles";
			String filePath = directoryPath + "\\login_packet.json";

			File directory = new File(directoryPath);
			if (!directory.exists()) {
				if (directory.mkdirs()) {
					logger.info("Created missing directory: {}", directoryPath);
				} else {
					logger.error("Failed to create directory: {}", directoryPath);
					return "Failed to create output directory";
				}
			}

			try (FileWriter file = new FileWriter(filePath)) {
				file.write(json.toString(4)); // pretty print
			} catch (IOException ioe) {
				logger.error("Failed to write JSON file: {}", ioe.getMessage(), ioe);
				return "Failed to write login packet to file";
			}

			driver.findElement(By.xpath("//button[contains(@class, 'custom-close-btn')]")).click();
			logger.info("Login packet details viewed and saved as structured JSON.");
			return "Login packet details are displayed successfully";

		} catch (Exception e) {
			logger.error("Failed to display login packet details: {}", e.getMessage(), e);
			return "Failed to display login packet details";
		}
	}

	public String viewHealthPacket() {
		try {
			logger.info("Attempting to view health packet details.");

			((JavascriptExecutor) driver).executeScript("window.scrollBy(0, -250);");

			List<WebElement> eyeIcons = driver.findElements(EYE_ICON);
			logger.debug("Found {} eye icons on the page.", eyeIcons.size());

			WebElement eyeElement = eyeIcons.get(5);
			((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", eyeElement);
			Thread.sleep(500);
			eyeElement.click();
			logger.info("Clicked 6th eye icon to open health packet details.");

			WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
			WebElement modal = wait.until(ExpectedConditions
					.visibilityOfElementLocated(By.xpath("//div[contains(@class, 'component-body')]")));

			List<WebElement> detailsElements = driver
					.findElements(By.xpath("//div[@class='component-body'][.//table]"));
			logger.debug("Found {} component-body elements containing tables.", detailsElements.size());

			WebElement frameElement = detailsElements.get(detailsElements.size() - 1);
			((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", frameElement);
			Thread.sleep(500);

			String healthPacketDetails = frameElement.getText();
			logger.debug("Raw health packet text:\n{}", healthPacketDetails);

			// Parse the string into key-value pairs
			String[] lines = healthPacketDetails.split("\n");
			Map<String, String> dataMap = new LinkedHashMap<>();

			for (int i = 0; i < lines.length - 1; i++) {
				if (lines[i].endsWith(":")) {
					String key = lines[i].replace(":", "").trim();
					String value = lines[i + 1].trim();
					dataMap.put(key, value);
					i++; // skip value line
				}
			}

			JSONObject json = new JSONObject(dataMap);

			// Directory and file setup
			String directoryPath = "D:\\Sampark_Automation\\SAM_AUTO\\test-results\\outputFiles";
			String filePath = directoryPath + "\\health_packet.json";

			File directory = new File(directoryPath);
			if (!directory.exists()) {
				if (directory.mkdirs()) {
					logger.info("Created missing directory: {}", directoryPath);
				} else {
					logger.error("Failed to create directory: {}", directoryPath);
					return "Failed to create output directory";
				}
			}

			// Write to file
			try (FileWriter file = new FileWriter(filePath)) {
				file.write(json.toString(4)); // pretty print
			} catch (IOException ioe) {
				logger.error("Failed to write JSON file: {}", ioe.getMessage(), ioe);
				return "Failed to write health packet to file";
			}

			driver.findElement(By.xpath("//button[contains(@class, 'custom-close-btn')]")).click();
			logger.info("Health packet details viewed and saved as structured JSON.");
			return "Health packet details are displayed successfully";

		} catch (Exception e) {
			logger.error("Failed to display health packet details: {}", e.getMessage(), e);
			return "Failed to display health packet details";
		}
	}

	public boolean isHealthPacketVisible() {
		try {
			logger.info("Checking if Health Packet is visible on the page.");

			WebElement healthPacket = driver.findElement(HEALTH_PACKET);
			boolean isVisible = healthPacket.isDisplayed();
			logger.debug("Health Packet visibility: {}", isVisible);

			return isVisible;
		} catch (Exception e) {
			logger.error("Error while checking Health Packet visibility: {}", e.getMessage(), e);
		}
		return false;
	}

	public boolean isBarGraphVisible() {
		try {
			logger.info("Checking if Bar Graph is visible on the page.");

			List<WebElement> bar_graphs = driver.findElements(BAR_GRAPH);
			boolean isVisible = bar_graphs.get(0).isDisplayed();
			logger.debug("Bar Graph visibility: {}", isVisible);

			return isVisible;
		} catch (Exception e) {
			logger.error("Error while checking Bar Graph visibility: {}", e.getMessage(), e);
		}
		return false;
	}

	public String clickOnDeviceActivityBarGraph() {
		try {
			logger.info("Clicking on Device Activity Bar Graph.");

			List<WebElement> bar_graphs = driver.findElements(BAR_GRAPH);
			((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", bar_graphs.get(0));
			Thread.sleep(500);
			bar_graphs.get(0).click();
			logger.info("Clicked on Device Activity Bar Graph successfully.");
			return bar_graphs.get(0).getText();
		} catch (Exception e) {
			logger.error("Failed to click on Device Activity Bar Graph: {}", e.getMessage(), e);
		}
		return "No Bar Graph visible";
	}
}
