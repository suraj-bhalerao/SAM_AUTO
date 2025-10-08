package com.aepl.sam.pages;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.time.Duration;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.json.JSONObject;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.ElementClickInterceptedException;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import com.aepl.sam.constants.Constants;
import com.aepl.sam.locators.DeviceDetailsPageLocators;
import com.aepl.sam.utils.CommonMethods;
import com.aepl.sam.utils.TableUtils;

public class DeviceDetailsPage extends DeviceDetailsPageLocators {
	private WebDriver driver;
	private WebDriverWait wait;
	private CommonMethods comm;
	private TableUtils tableUtils;

	private final Logger logger = LogManager.getLogger(DeviceDetailsPage.class);

	public DeviceDetailsPage(WebDriver driver, WebDriverWait wait, CommonMethods comm) {
		this.driver = driver;
		this.wait = wait;
		this.comm = comm;
		this.tableUtils = new TableUtils(wait);
	}

	public void viewDevice() {
		WebElement eyeIcon = driver.findElement(EYE_ICON);
		comm.highlightElement(eyeIcon, "solid purple");
		Assert.assertTrue(eyeIcon.isEnabled(), "Eye icon is not enabled.");
		eyeIcon.click();
		logger.info("Clicked on eye icon to view device.");
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

	public Boolean isSearchButtonVisible() {
		try {
			WebElement searchBtn = wait.until(ExpectedConditions.visibilityOfElementLocated(DEVICE_DETAILS_SEARCHBTN));
			comm.highlightElement(searchBtn, "green");
			Assert.assertTrue(searchBtn.isDisplayed(), "Search button is not displayed");
			return searchBtn.isDisplayed();
		} catch (TimeoutException e) {
			System.err.println("Search button not found: " + e.getMessage());
			return false;
		} catch (Exception e) {
			System.err.println("Unexpected error while checking search button visibility: " + e.getMessage());
			return false;
		}
	}

	public boolean isSearchButtonEnabled() {
		try {
			WebElement searchBtn = wait.until(ExpectedConditions.visibilityOfElementLocated(DEVICE_DETAILS_SEARCHBTN));
			comm.highlightElement(searchBtn, "green");
			Assert.assertTrue(searchBtn.isEnabled(), "Search button is not displayed");
			return searchBtn.isEnabled();
		} catch (TimeoutException e) {
			System.err.println("Search button not found: " + e.getMessage());
			return false;
		} catch (Exception e) {
			System.err.println("Unexpected error while checking search button enabled state: " + e.getMessage());
			return false;
		}
	}

	public boolean isSearchInputVisible() {

		try {
			WebElement searchInput = wait
					.until(ExpectedConditions.visibilityOfElementLocated(DEVICE_DETAILS_SEARCHBOX));
			comm.highlightElement(searchInput, "green");
			return searchInput.isDisplayed();
		} catch (TimeoutException e) {
			System.err.println("Search input not found: " + e.getMessage());
			return false;
		} catch (Exception e) {
			System.err.println("Unexpected error while checking search input visibility: " + e.getMessage());
			return false;
		}
	}

	public boolean isSearchInputEnabled() {
		try {
			WebElement searchInput = wait
					.until(ExpectedConditions.visibilityOfElementLocated(DEVICE_DETAILS_SEARCHBOX));
			comm.highlightElement(searchInput, "green");
			return searchInput.isEnabled();
		} catch (TimeoutException e) {
			System.err.println("Search input not found: " + e.getMessage());
			return false;
		} catch (Exception e) {
			System.err.println("Unexpected error while checking search input enabled state: " + e.getMessage());
			return false;
		}
	}

	public boolean searchDevice() {
		try {
			WebElement searchInput = wait
					.until(ExpectedConditions.visibilityOfElementLocated(DEVICE_DETAILS_SEARCHBOX));
			comm.highlightElement(searchInput, "solid purple");
			searchInput.clear();
			searchInput.sendKeys(Constants.IMEI);

			Thread.sleep(500);

			WebElement searchBtn = wait.until(ExpectedConditions.elementToBeClickable(DEVICE_DETAILS_SEARCHBTN));
			comm.highlightElement(searchBtn, "solid purple");

			// Scroll into view first
			((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView({block: 'center'});", searchBtn);

			// Wait briefly for layout to settle
			Thread.sleep(300);

			try {
				searchBtn.click();
			} catch (ElementClickInterceptedException e) {
				// Fallback to JS click if intercepted
				((JavascriptExecutor) driver).executeScript("arguments[0].click();", searchBtn);
				System.out.println("⚠️ Performed JS click due to intercept.");
			}

			return true;

		} catch (TimeoutException e) {
			System.err.println("Search input or button not found: " + e.getMessage());
			return false;
		} catch (Exception e) {
			System.err.println("Unexpected error during device search: " + e.getMessage());
			return false;
		}
	}

	public List<String> validateTableHeaders() {
		return tableUtils.getTableHeaders(By.xpath("//table"));
	}

	public boolean isViewButtonEnabled() {
		return tableUtils.areViewButtonsEnabled(By.xpath("//table"));
	}

	public List<Map<String, String>> getTableData() {
		return tableUtils.getTableData(By.xpath("//table"), validateTableHeaders());
	}

	public String validatePageTitle() {
		String pageTitle = driver.findElement(PAGE_TITLE).getText();
		comm.highlightElement(driver.findElement(PAGE_TITLE), "solid purple");
		Assert.assertEquals(pageTitle, "Device Details", "Page title does not match expected");
		return pageTitle;
	}

	// get the attribute value of the search input on device details page
	public String validateIMEIOnDeviceDetailsPage() {
		WebElement imeiValue = driver.findElement(By.xpath("//input[contains(@formcontrolname,'searchInput')]"));
		String actImeiValue = imeiValue.getAttribute("value");
		comm.highlightElement(imeiValue, "solid purple");

		Assert.assertFalse(actImeiValue.isEmpty(), "IMEI value is empty");
		Assert.assertEquals(actImeiValue, Constants.IMEI, "IMEI value does not match expected");
		return actImeiValue;
	}

	public boolean isIMEIInputVisible() {
		WebElement imeiInput = wait.until(ExpectedConditions
				.visibilityOfElementLocated(By.xpath("//input[contains(@formcontrolname,'searchInput')]")));
		comm.highlightElement(imeiInput, "solid purple");
		Assert.assertTrue(imeiInput.isDisplayed(), "IMEI input field is not enabled");
		return imeiInput.isDisplayed();
	}

	public boolean isIMEIInputClickable() {
		try {
			WebElement imeiInput = wait.until(ExpectedConditions
					.elementToBeClickable(By.xpath("//input[contains(@formcontrolname,'searchInput')]")));
			comm.highlightElement(imeiInput, "solid purple");
			return imeiInput.isEnabled();
		} catch (TimeoutException e) {
			System.err.println("IMEI input field is not clickable: " + e.getMessage());
			return false;
		} catch (Exception e) {
			System.err.println("Unexpected error while checking IMEI input clickable state: " + e.getMessage());
			return false;
		}
	}

	public boolean validateInvalidIMEIInput() {
		try {
			WebElement imeiInput = wait.until(ExpectedConditions
					.visibilityOfElementLocated(By.xpath("//input[contains(@formcontrolname,'searchInput')]")));
			comm.highlightElement(imeiInput, "solid purple");
			imeiInput.clear();
			imeiInput.sendKeys("12345");
			Thread.sleep(500);

			WebElement searchBtn = wait.until(ExpectedConditions.visibilityOfElementLocated(DEVICE_DETAILS_SEARCHBTN));
			comm.highlightElement(searchBtn, "solid purple");
			searchBtn.click();
			Thread.sleep(500);

			WebElement toastMsg = wait.until(ExpectedConditions.visibilityOfElementLocated(WRONG_IMEI_TOAST_MSG));
			comm.highlightElement(toastMsg, "solid purple");
			String toastText = toastMsg.getText();
			Assert.assertEquals(toastText, "Invalid IMEI. Please try again.", "Toast message does not match expected");
			return true;
		} catch (TimeoutException e) {
			System.err.println("Search input or button not found: " + e.getMessage());
			return false;
		} catch (Exception e) {
			System.err.println("Unexpected error during device search: " + e.getMessage());
			return false;
		}
	}

	public String validateValidIMEISearch() {
		try {
			By element = By.xpath("//input[contains(@formcontrolname,'searchInput')]");

			WebElement imeiInput = wait.until(ExpectedConditions.visibilityOfElementLocated(element));
			comm.highlightElement(imeiInput, "solid purple");
			imeiInput.clear();
			imeiInput.sendKeys(Constants.IMEI);
			Thread.sleep(500);

			WebElement searchBtn = wait.until(ExpectedConditions.visibilityOfElementLocated(DEVICE_DETAILS_SEARCHBTN));
			comm.highlightElement(searchBtn, "solid purple");
			searchBtn.click();
			Thread.sleep(500);

			String searchedImei = driver.findElement(element).getAttribute("value");
			Assert.assertEquals(searchedImei, Constants.IMEI, "Searched IMEI does not match expected");

			return searchedImei;
		} catch (TimeoutException e) {
			System.err.println("Search input or button not found: " + e.getMessage());
			return "Search input or button not found";
		} catch (Exception e) {
			System.err.println("Unexpected error during device search: " + e.getMessage());
			return "Unexpected error during device search";
		}
	}

	public boolean isFOTAButtonEnabled() {
		try {
			WebElement fotaBtn = wait.until(ExpectedConditions.visibilityOfElementLocated(FOTA_BTN));
			comm.highlightElement(fotaBtn, "solid purple");
			Assert.assertTrue(fotaBtn.isEnabled(), "FOTA button is not enabled");
			return fotaBtn.isEnabled();
		} catch (TimeoutException e) {
			System.err.println("FOTA button not found: " + e.getMessage());
			return false;
		} catch (Exception e) {
			System.err.println("Unexpected error while checking FOTA button enabled state: " + e.getMessage());
			return false;
		}
	}

	public boolean isFOTAButtonClickable() {
		try {
			WebElement fotaBtn = wait.until(ExpectedConditions.elementToBeClickable(FOTA_BTN));
			comm.highlightElement(fotaBtn, "solid purple");
			fotaBtn.click();
			Thread.sleep(500);

			String currentUrl = driver.getCurrentUrl();
			Assert.assertTrue(currentUrl.contains("manual-fota"), "FOTA page URL does not contain 'fota'");
			Thread.sleep(500);
			driver.navigate().back();
			return true;
		} catch (TimeoutException e) {
			System.err.println("FOTA button not found or not clickable: " + e.getMessage());
			return false;
		} catch (Exception e) {
			System.err.println("Unexpected error while checking FOTA button clickable state: " + e.getMessage());
			return false;
		}
	}

	public boolean isOTAButtonEnabled() {
		try {
			WebElement otaBtn = wait.until(ExpectedConditions.visibilityOfElementLocated(OTA_BTN));
			comm.highlightElement(otaBtn, "solid purple");
			Assert.assertTrue(otaBtn.isEnabled(), "OTA button is not enabled");
			return otaBtn.isEnabled();
		} catch (TimeoutException e) {
			System.err.println("OTA button not found: " + e.getMessage());
			return false;
		} catch (Exception e) {
			System.err.println("Unexpected error while checking OTA button enabled state: " + e.getMessage());
			return false;
		}
	}

	public boolean isOTAButtonClickable() {
		try {
			WebElement otaBtn = wait.until(ExpectedConditions.elementToBeClickable(OTA_BTN));
			comm.highlightElement(otaBtn, "solid purple");
			otaBtn.click();

			String currentUrl = driver.getCurrentUrl();
			System.out.println("Current URL after clicking OTA button: " + currentUrl);
			Assert.assertTrue(currentUrl.contains("manual-ota"), "OTA page URL does not contain 'ota'");

			driver.navigate().back();
			return true;
		} catch (TimeoutException e) {
			System.err.println("OTA button not found or not clickable: " + e.getMessage());
			return false;
		} catch (Exception e) {
			System.err.println("Unexpected error while checking OTA button clickable state: " + e.getMessage());
			return false;
		}
	}

	public boolean areInfoCardsVisible() {
		try {
			List<WebElement> infoCards = wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(ALL_CARDS));
			for (WebElement card : infoCards) {
				comm.highlightElement(card, "solid purple");
				Assert.assertTrue(card.isDisplayed(), "Info card is not displayed");
			}
			return true;
		} catch (TimeoutException e) {
			System.err.println("Info cards not found: " + e.getMessage());
			return false;
		} catch (Exception e) {
			System.err.println("Unexpected error while checking info cards visibility: " + e.getMessage());
			return false;
		}
	}

	public boolean areInfoCardsEnabled() {
		try {
			List<WebElement> infoCards = wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(ALL_CARDS));
			for (WebElement card : infoCards) {
				comm.highlightElement(card, "solid purple");
				Assert.assertTrue(card.isEnabled(), "Info card is not enabled");
			}
			return true;
		} catch (TimeoutException e) {
			System.err.println("Info cards not found: " + e.getMessage());
			return false;
		} catch (Exception e) {
			System.err.println("Unexpected error while checking info cards enabled state: " + e.getMessage());
			return false;
		}
	}

	public List<String> validateAllCardsHeaders() {
		List<String> cardHeaders = new ArrayList<>();
		List<WebElement> card_contents = driver.findElements(By.xpath("//span[@class = 'kpi-content']"));
		for (WebElement card : card_contents) {
			String content_text = card.getText();
			cardHeaders.add(content_text);
		}
		return cardHeaders;
	}
}
