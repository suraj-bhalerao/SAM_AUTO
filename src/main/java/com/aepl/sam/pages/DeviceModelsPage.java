package com.aepl.sam.pages;

import org.openqa.selenium.Alert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.aepl.sam.locators.DeviceModelsPageLocators;
import com.aepl.sam.utils.CommonMethods;
import com.aepl.sam.utils.RandomGeneratorUtils;

public class DeviceModelsPage extends DeviceModelsPageLocators {
	private WebDriver driver;
	private WebDriverWait wait;
	private CommonMethods comm;
	private String randomModelCode;
	private RandomGeneratorUtils random;

	private static final Logger logger = LogManager.getLogger(DeviceModelsPage.class);

	public DeviceModelsPage(WebDriver driver, WebDriverWait wait, CommonMethods comm) {
		this.driver = driver;
		this.wait = wait;
		this.comm = comm;
		this.random = new RandomGeneratorUtils();
	}

	public String navBarLink() {
		logger.info("Navigating to Device Models via navbar.");
		WebElement device_utility = wait.until(ExpectedConditions.visibilityOfElementLocated(DEVICE_UTILITY));
		comm.highlightElement(device_utility, "solid purple");
		device_utility.click();

		WebElement devModel = wait.until(ExpectedConditions.visibilityOfElementLocated(DEVICE_MODELS));
		comm.highlightElement(devModel, "solid purple");
		devModel.click();

		String url = driver.getCurrentUrl();
		logger.debug("Navigated to URL: {}", url);
		return url;
	}

	public String ClickAddDeviceModel() {
		logger.info("Clicking on 'Add Device Model' button.");
		WebElement AddDeviceModel = wait.until(ExpectedConditions.visibilityOfElementLocated(ADD_DEVICE_MODELS));
		comm.highlightElement(AddDeviceModel, "solid purple");
		AddDeviceModel.click();

		WebElement addDeviceModelPageTitle = driver.findElement(PAGE_TITLE);
		comm.highlightElement(addDeviceModelPageTitle, "solid purple");
		String title = addDeviceModelPageTitle.getText();
		logger.debug("Add Device Model Page Title: {}", title);
		return title;
	}

	public String NewInputFields(String para) throws InterruptedException {
		randomModelCode = random.generateRandomString(3);
		String randomModelName = random.generateRandomString(6);
		String randomSerialSeq = random.generateRandomNumber(4);
		String randomHardwareVer = random.generateRandomNumber(4);

		logger.info("Filling input fields for '{}'", para);

		try {
			if (para.equalsIgnoreCase("add")) {
				logger.debug("Model code: SAM{}", randomModelCode);
				WebElement modelCode = wait.until(ExpectedConditions.visibilityOfElementLocated(MODEL_CODE));
				comm.highlightElement(modelCode, "solid purple");
				modelCode.sendKeys("SAM" + randomModelCode);

				WebElement AddModelName = wait.until(ExpectedConditions.visibilityOfElementLocated(ADD_MODEL_NAME));
				comm.highlightElement(AddModelName, "solid purple");
				AddModelName.sendKeys(randomModelName);

				WebElement AddModelSerialSequence = wait
						.until(ExpectedConditions.visibilityOfElementLocated(ADD_MODEL_SERIAL_SEQUENCE));
				comm.highlightElement(AddModelSerialSequence, "solid purple");
				AddModelSerialSequence.sendKeys(randomSerialSeq);

				WebElement AddHardwareVersion = wait
						.until(ExpectedConditions.visibilityOfElementLocated(ADD_HARDWARE_VERSION));
				comm.highlightElement(AddHardwareVersion, "solid purple");
				AddHardwareVersion.sendKeys(randomHardwareVer);

				Thread.sleep(500);

				WebElement AddSubmitButton = wait
						.until(ExpectedConditions.visibilityOfElementLocated(ADD_SUBMIT_BUTTON));
				comm.highlightElement(AddSubmitButton, "solid purple");
				AddSubmitButton.click();

				Thread.sleep(500);

				WebElement DeviceModelPageTitle = driver.findElement(PAGE_TITLE);
				comm.highlightElement(DeviceModelPageTitle, "solid purple");
				String title = DeviceModelPageTitle.getText();
				logger.info("Device model added successfully. Page title: {}", title);
				return title;

			} else if (para.equalsIgnoreCase("update")) {
				logger.debug("Updating model: SAM{}Updated", randomModelCode);
				WebElement modelCode = wait.until(ExpectedConditions.visibilityOfElementLocated(MODEL_CODE));
				comm.highlightElement(modelCode, "GREEN");
				modelCode.clear();
				modelCode.sendKeys("SAM" + randomModelCode + "Updated");

				WebElement UpdateModelName = wait.until(ExpectedConditions.visibilityOfElementLocated(ADD_MODEL_NAME));
				UpdateModelName.clear();
				comm.highlightElement(UpdateModelName, "solid purple");
				UpdateModelName.sendKeys(randomModelName + "Updated");

				WebElement UpdateModelSerialSequence = wait
						.until(ExpectedConditions.visibilityOfElementLocated(ADD_MODEL_SERIAL_SEQUENCE));
				UpdateModelSerialSequence.clear();
				comm.highlightElement(UpdateModelSerialSequence, "solid purple");
				UpdateModelSerialSequence.sendKeys(randomSerialSeq + "Updated");

				WebElement UpdateHardwareVersion = wait
						.until(ExpectedConditions.visibilityOfElementLocated(ADD_HARDWARE_VERSION));
				UpdateHardwareVersion.clear();
				comm.highlightElement(UpdateHardwareVersion, "solid purple");
				UpdateHardwareVersion.sendKeys(randomHardwareVer + "Updated");

				WebElement UpdateButton = wait.until(ExpectedConditions.visibilityOfElementLocated(ADD_UPDATE_BUTTON));
				comm.highlightElement(UpdateButton, "solid purple");
				UpdateButton.click();

				Thread.sleep(500);

				WebElement DeviceModelPageTitle = driver.findElement(PAGE_TITLE);
				String title = DeviceModelPageTitle.getText();
				logger.info("Device model updated successfully. Page title: {}", title);
				return title;
			}
		} catch (Exception e) {
			logger.error("Error while filling form for '{}': {}", para, e.getMessage(), e);
		}

		return "Not either device model is added or updated";
	}

	public String searchModel() throws InterruptedException {
		logger.info("Searching for model: SAM{}", randomModelCode);
		WebElement search = wait.until(ExpectedConditions.visibilityOfElementLocated(SEARCH_FIELD));
		comm.highlightElement(search, "solid purple");
		search.sendKeys("SAM" + randomModelCode);

		WebElement searchButton = wait.until(ExpectedConditions.visibilityOfElementLocated(SEARCH_BUTTON));
		comm.highlightElement(searchButton, "solid purple");
		searchButton.click();

		Thread.sleep(500);

		WebElement DeviceModelPageTitle = driver.findElement(PAGE_TITLE);
		comm.highlightElement(DeviceModelPageTitle, "solid purple");
		String title = DeviceModelPageTitle.getText();
		logger.info("Search completed. Page title: {}", title);
		return title;
	}

	public String viewModel() throws InterruptedException {
		logger.info("Viewing device model details.");
		WebElement viewButton = wait.until(ExpectedConditions.visibilityOfElementLocated(EYE_ICON));
		comm.highlightElement(viewButton, "solid purple");
		viewButton.click();

		Thread.sleep(500);

		WebElement DeviceModelPageTitle = driver.findElement(PAGE_TITLE);
		comm.highlightElement(DeviceModelPageTitle, "solid purple");
		String title = DeviceModelPageTitle.getText();
		logger.debug("View page title: {}", title);
		return title;
	}

	public String searchModel2() throws InterruptedException {
		logger.info("Searching for updated model: SAM{}Updated", randomModelCode);
		WebElement search = wait.until(ExpectedConditions.visibilityOfElementLocated(SEARCH_FIELD));
		comm.highlightElement(search, "solid purple");
		search.clear();
		search.sendKeys("SAM" + randomModelCode + "Updated");

		WebElement searchButton = wait.until(ExpectedConditions.visibilityOfElementLocated(SEARCH_BUTTON));
		comm.highlightElement(searchButton, "solid purple");
		searchButton.click();

		Thread.sleep(500);

		WebElement DeviceModelPageTitle = driver.findElement(PAGE_TITLE);
		comm.highlightElement(DeviceModelPageTitle, "solid purple");
		String title = DeviceModelPageTitle.getText();
		logger.info("Updated model search completed. Page title: {}", title);
		return title;
	}

	public String DeleteModel() throws InterruptedException {
		logger.info("Attempting to delete the model: SAM{}", randomModelCode);
		WebElement DeleteButton = wait.until(ExpectedConditions.visibilityOfElementLocated(DELETE_ICON));
		comm.highlightElement(DeleteButton, "solid purple");
		DeleteButton.click();

		Alert alert = driver.switchTo().alert();
		alert.accept();
		driver.navigate().refresh();
		Thread.sleep(500);

		WebElement DeviceModelPageTitle = driver.findElement(PAGE_TITLE);
		String title = DeviceModelPageTitle.getText();
		logger.info("Model deleted. Page title: {}", title);
		return title;
	}
}
