package com.aepl.sam.pages;

import java.util.List;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.aepl.sam.constants.Constants;
import com.aepl.sam.locators.DeviceDetailsPageLocators;
import com.aepl.sam.utils.CommonMethods;

public class DeviceDetailsPage extends DeviceDetailsPageLocators {
	private WebDriver driver;
	private WebDriverWait wait;
	private CommonMethods comm;

	private static final Logger logger = LoggerFactory.getLogger(DeviceDetailsPage.class);

	public DeviceDetailsPage(WebDriver driver, WebDriverWait wait, CommonMethods comm) {
		this.driver = driver;
		this.wait = wait;
		this.comm = comm;
	}

	public void searchAndViewDevice() {
		try {
			logger.info("Attempting to search and view device with IMEI: {}", Constants.IMEI);
			WebElement canvas = driver.findElement(By.tagName("canvas"));
			JavascriptExecutor js = (JavascriptExecutor) driver;
			js.executeScript("arguments[0].scrollIntoView(true);", canvas);

			WebElement serachField = driver.findElement(SEARCH_BOX_INPUT);
			Thread.sleep(500);
			comm.highlightElement(serachField, "solid purple");
			serachField.clear();
			serachField.sendKeys(Constants.IMEI);

			WebElement searchButton = driver.findElement(SEARCH_BOX_BTN);
			searchButton.click();
			comm.highlightElement(serachField, "solid purple");
			Thread.sleep(500);

			WebElement eyeIcon = driver.findElement(EYE_ICON);
			eyeIcon.click();
			logger.info("Successfully searched and clicked view icon for the device.");
		} catch (Exception e) {
			logger.error("An error occurred while searching and viewing the device: {}", e.getMessage(), e);
		}
	}

	public boolean allComponentDetails() {
		try {
			logger.info("Verifying if component details contain the IMEI.");
			List<WebElement> listOfComponents = wait
					.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(ALL_COMPONENT));

			WebElement componentElement = listOfComponents.get(0);
			String componentText = componentElement.getText();
			logger.debug("Component Text: {}", componentText);

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
			JavascriptExecutor js = (JavascriptExecutor) driver;
			WebElement exportBtn = driver.findElement(EXPORT_BTN);
			js.executeScript("arguments[0].scrollIntoView(true);", exportBtn);
			Thread.sleep(500);

			comm.highlightElement(exportBtn, "solid purple");

			for (int i = 0; i < 3; i++) {
				if (exportBtn.isDisplayed()) {
					exportBtn.click();
					logger.info("Clicked Export button attempt {}", i + 1);

					Alert alert = driver.switchTo().alert();
					alert.accept();
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
			JavascriptExecutor js = (JavascriptExecutor) driver;
			js.executeScript("window.scrollBy(0, window.innerHeight / 2 * 2.2);");
			Thread.sleep(500);

			List<WebElement> eyeIcon = driver.findElements(EYE_ICON);
			eyeIcon.get(0).click();
			Thread.sleep(500);

			wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(By.id("loginPacketDetails")));
			logger.info("Switched to login packet frame.");

			List<WebElement> detailsElement = driver.findElements(By.xpath("//div[@class='component-body'][.//table]"));
			WebElement frameElement = detailsElement.get(detailsElement.size() - 1);

			String loginPacketDetails = frameElement.getText();
			logger.debug("Login Packet Details:\n{}", loginPacketDetails);

			driver.switchTo().defaultContent();
			logger.info("Login packet details viewed successfully.");
			return "Login packet details are displayed successfully";
		} catch (Exception e) {
			logger.error("Failed to display login packet details: {}", e.getMessage(), e);
			driver.switchTo().defaultContent();
			return "Failed to display login packet details";
		}
	}
}
