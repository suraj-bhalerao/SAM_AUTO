package com.aepl.sam.pages;

import java.util.List;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import com.aepl.sam.constants.Constants;
import com.aepl.sam.locators.DeviceDetailsPageLocators;
import com.aepl.sam.utils.CommonMethods;

public class DeviceDetailsPage extends DeviceDetailsPageLocators {
	private WebDriver driver;
	private WebDriverWait wait;
	private CommonMethods comm;

	private static final Logger logger = LogManager.getLogger(DeviceDetailsPage.class);

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
			((JavascriptExecutor) driver).executeScript("window.scrollBy(0, window.innerHeight / 2 * 2.2);");
			Thread.sleep(500);

			List<WebElement> eyeIcons = driver.findElements(EYE_ICON);
			logger.debug("Found {} eye icons on the page.", eyeIcons.size());
			eyeIcons.get(0).click();
			logger.info("Clicked first eye icon to open login packet details.");

			wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(By.id("loginPacketDetails")));
			logger.info("Switched to login packet iframe.");

			List<WebElement> detailsElements = driver.findElements(By.xpath("//div[@class='component-body'][.//table]"));
			logger.debug("Found {} component-body elements containing tables.", detailsElements.size());

			WebElement frameElement = detailsElements.get(detailsElements.size() - 1);
			String loginPacketDetails = frameElement.getText();
			logger.debug("Login Packet Details:\n{}", loginPacketDetails);

			driver.switchTo().defaultContent();
			logger.info("Login packet details viewed and switched back to main content.");
			return "Login packet details are displayed successfully";
		} catch (Exception e) {
			logger.error("Failed to display login packet details: {}", e.getMessage(), e);
			driver.switchTo().defaultContent();
			return "Failed to display login packet details";
		}
	}
}
