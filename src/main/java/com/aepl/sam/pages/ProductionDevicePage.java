package com.aepl.sam.pages;

import java.util.List;

import org.openqa.selenium.Alert;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.aepl.sam.actions.CalendarActions;
import com.aepl.sam.constants.Constants;
import com.aepl.sam.locators.ProductionDevicePageLocators;
import com.aepl.sam.utils.CommonMethods;
import com.aepl.sam.utils.RandomGeneratorUtils;

public class ProductionDevicePage extends ProductionDevicePageLocators {
	private WebDriver driver;
	private WebDriverWait wait;
	private CommonMethods commonMethods;
	private CalendarActions CalAct;
	JavascriptExecutor js;
	private String randomUIN;
	private RandomGeneratorUtils random;
	private static final Logger logger = LogManager.getLogger(ProductionDevicePage.class);

	public ProductionDevicePage(WebDriver driver, WebDriverWait wait) {
		this.driver = driver;
		this.wait = wait;
		this.commonMethods = new CommonMethods(driver, wait);
		this.CalAct = new CalendarActions(driver, wait);
		this.js = (JavascriptExecutor) driver;
	}

	public String navBarLink() throws InterruptedException {
		logger.info("Navigating to Production Devices via Device Utility...");
		WebElement deviceUtil = wait.until(ExpectedConditions.visibilityOfElementLocated(DEVICE_UTILITY));
		deviceUtil.click();
		WebElement prodDevice = wait.until(ExpectedConditions.visibilityOfElementLocated(PRODUCTION_DEVICES));
		prodDevice.click();
		Thread.sleep(500);
		logger.info("Current URL after navigation: {}", driver.getCurrentUrl());
		return driver.getCurrentUrl();
	}

	public String ClickAddProdDevice() throws InterruptedException {
		logger.info("Clicking on Add Production Device button...");
		WebElement AddProdDevice = wait.until(ExpectedConditions.visibilityOfElementLocated(ADD_PROD_DEVICE));
		AddProdDevice.click();

		WebElement addProdDevicePageTitle = wait.until(ExpectedConditions.visibilityOfElementLocated(PAGE_TITLE));
		logger.info("Add Production Device Page Title: {}", addProdDevicePageTitle.getText());
		return addProdDevicePageTitle.getText();
	}

	public String NewInputFields(String para) throws InterruptedException {
		randomUIN = random.generateRandomUIN();
		logger.info("Generated random UIN: {}", randomUIN);

		if (para.equalsIgnoreCase("add")) {
			logger.info("Filling Add Production Device form...");

			WebElement AddUID = wait.until(ExpectedConditions.visibilityOfElementLocated(UID));
			commonMethods.highlightElement(AddUID, "solid purple");
			AddUID.sendKeys(randomUIN);

			WebElement AddIMEI = wait.until(ExpectedConditions.visibilityOfElementLocated(IMEI));
			commonMethods.highlightElement(AddIMEI, "solid purple");
			AddIMEI.sendKeys(Constants.IMEI);

			WebElement AddICCID = wait.until(ExpectedConditions.visibilityOfElementLocated(ICCID));
			commonMethods.highlightElement(AddICCID, "solid purple");
			AddICCID.sendKeys(Constants.ICCID);

			WebElement deviceModelDropdown = wait.until(ExpectedConditions.visibilityOfElementLocated(DEVICE_MODEL_NAME));
			js.executeScript("arguments[0].click();", deviceModelDropdown);

			List<WebElement> deviceModelOptions = wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(DEVICE_MODEL_OPTIONS));
			boolean modelFound = false;
			for (WebElement modelOption : deviceModelOptions) {
				String modelText = modelOption.getText().trim();
				js.executeScript("arguments[0].scrollIntoView({block: 'center'});", modelOption);
				commonMethods.highlightElement(modelOption, "solid purple");

				if (modelText.equals(Constants.DEVICE_MODEL)) {
					modelOption.click();
					Thread.sleep(300);
					logger.info("Selected device model: {}", modelText);
					modelFound = true;
					break;
				}
			}
			if (!modelFound) {
				logger.warn("Device model {} not found in options.", Constants.DEVICE_MODEL);
			}

			wait.until(ExpectedConditions.visibilityOfElementLocated(MOBILE_NUMBER)).sendKeys(Constants.MOBILE_NUMBER + "00000");
			wait.until(ExpectedConditions.visibilityOfElementLocated(SERVICE_PROVIDER)).sendKeys(Constants.ISP_1);
			wait.until(ExpectedConditions.visibilityOfElementLocated(ALT_MOBILE_NO)).sendKeys(Constants.ALT_MOBILE_NUMBER);
			wait.until(ExpectedConditions.visibilityOfElementLocated(ALT_SERVICE_PROVIDER)).sendKeys(Constants.ISP_2);
			wait.until(ExpectedConditions.visibilityOfElementLocated(FIRMWARE)).sendKeys(Constants.FIRMWARE);
			wait.until(ExpectedConditions.visibilityOfElementLocated(SIM_VENDOR)).sendKeys(Constants.ISP_2);

			CalAct.selectDate(CAL_BTN, "04-04-2025");

			WebElement SubmitButton = wait.until(ExpectedConditions.visibilityOfElementLocated(SUBMIT_BTN));
			commonMethods.highlightElement(SubmitButton, "solid purple");
			SubmitButton.click();

			WebElement ProdDevicePageTitle = wait.until(ExpectedConditions.visibilityOfElementLocated(PAGE_TITLE));
			logger.info("Production device added, page title: {}", ProdDevicePageTitle.getText());
			return ProdDevicePageTitle.getText();

		} else if (para.equalsIgnoreCase("update")) {
			logger.info("Updating Production Device details...");

			WebElement deviceModelDropdown = wait.until(ExpectedConditions.elementToBeClickable(DEVICE_MODEL_NAME));
			js.executeScript("arguments[0].click();", deviceModelDropdown);

			List<WebElement> deviceModelOptions = wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(DEVICE_MODEL_OPTIONS));
			boolean modelFound = false;
			for (WebElement modelOption : deviceModelOptions) {
				String modelText = modelOption.getText().trim();
				js.executeScript("arguments[0].scrollIntoView({block: 'center'});", modelOption);
				commonMethods.highlightElement(modelOption, "solid purple");

				if (modelText.equals(Constants.DEVICE_MODEL)) {
					modelOption.click();
					Thread.sleep(300);
					logger.info("Selected device model: {}", modelText);
					modelFound = true;
					break;
				}
			}
			if (!modelFound) {
				logger.warn("Device model {} not found in options.", Constants.DEVICE_MODEL);
			}

			wait.until(ExpectedConditions.visibilityOfElementLocated(MOBILE_NUMBER)).sendKeys(Constants.MOBILE_NUMBER + "00000");
			wait.until(ExpectedConditions.visibilityOfElementLocated(SERVICE_PROVIDER)).sendKeys(Constants.ISP_2);
			wait.until(ExpectedConditions.visibilityOfElementLocated(ALT_MOBILE_NO)).sendKeys(Constants.ALT_MOBILE_NUMBER);
			wait.until(ExpectedConditions.visibilityOfElementLocated(ALT_SERVICE_PROVIDER)).sendKeys(Constants.ISP_1);
			wait.until(ExpectedConditions.visibilityOfElementLocated(FIRMWARE)).sendKeys(Constants.UP_FIRMWARE);
			wait.until(ExpectedConditions.visibilityOfElementLocated(SIM_VENDOR)).sendKeys(Constants.ISP_1);

			CalAct.selectDate(CAL_BTN, "26-06-2025");

			WebElement update_btn = wait.until(ExpectedConditions.visibilityOfElementLocated(UPDATE_BTN));
			commonMethods.highlightElement(update_btn, "solid purple");
			update_btn.click();

			WebElement ProdDevicePageTitle = wait.until(ExpectedConditions.visibilityOfElementLocated(PAGE_TITLE));
			logger.info("Production device updated, page title: {}", ProdDevicePageTitle.getText());
			return ProdDevicePageTitle.getText();
		}

		driver.navigate().back();
		logger.warn("No valid operation was selected for NewInputFields.");
		return "Not either production device is added or updated";
	}

	public String searchDevice() throws InterruptedException {
		logger.info("Searching for a device using IMEI: {}", Constants.IMEI);
		driver.navigate().back();
		WebElement search = wait.until(ExpectedConditions.visibilityOfElementLocated(SEARCH_FIELD));
		search.sendKeys(Constants.IMEI);
		WebElement searchButton = wait.until(ExpectedConditions.visibilityOfElementLocated(SEARCH_BUTTON));
		searchButton.click();
		js.executeScript("window.scrollBy(0,5000);");
		Thread.sleep(500);
		WebElement searchPageTitle = wait.until(ExpectedConditions.visibilityOfElementLocated(PAGE_TITLE));
		logger.info("Search result page title: {}", searchPageTitle.getText());
		return searchPageTitle.getText();
	}

	public String viewDevice() throws InterruptedException {
		logger.info("Viewing device details...");
		WebElement viewButton = wait.until(ExpectedConditions.visibilityOfElementLocated(EYE_ICON));
		viewButton.click();
		Thread.sleep(2000);
		WebElement UpdatePageTitle = wait.until(ExpectedConditions.visibilityOfElementLocated(PAGE_TITLE));
		logger.info("View page title: {}", UpdatePageTitle.getText());
		return UpdatePageTitle.getText();
	}

	public String DeleteDevice() throws InterruptedException {
		logger.info("Deleting device...");
		WebElement DeleteButton = wait.until(ExpectedConditions.visibilityOfElementLocated(DELETE_ICON));
		DeleteButton.click();
		Thread.sleep(1000);
		Alert alert = driver.switchTo().alert();
		alert.accept();
		Thread.sleep(2000);
		WebElement DeletePageTitle = wait.until(ExpectedConditions.visibilityOfElementLocated(PAGE_TITLE));
		logger.info("Device deleted, page title: {}", DeletePageTitle.getText());
		return DeletePageTitle.getText();
	}
}
