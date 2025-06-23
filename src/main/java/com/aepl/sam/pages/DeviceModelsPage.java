package com.aepl.sam.pages;

import org.openqa.selenium.Alert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.aepl.sam.locators.DeviceModelsPageLocators;
import com.aepl.sam.utils.CommonMethods;

public class DeviceModelsPage extends DeviceModelsPageLocators {
	private WebDriver driver;
	private WebDriverWait wait;
	private CommonMethods comm;
	String randomModelCode;

	public DeviceModelsPage(WebDriver driver, WebDriverWait wait, CommonMethods comm) {
		this.driver = driver;
		this.wait = wait;
		this.comm = comm;
	}

	public String navBarLink() {
		WebElement device_utility = wait.until(ExpectedConditions.visibilityOfElementLocated(DEVICE_UTILITY));
		comm.highlightElement(device_utility, "GREEN");
		device_utility.click();

		WebElement devModel = wait.until(ExpectedConditions.visibilityOfElementLocated(DEVICE_MODELS));
		comm.highlightElement(devModel, "GREEN");
		devModel.click();

		return driver.getCurrentUrl();
	}

	public String ClickAddDeviceModel() {
		WebElement AddDeviceModel = wait.until(ExpectedConditions.visibilityOfElementLocated(ADD_DEVICE_MODELS));
		comm.highlightElement(AddDeviceModel, "GREEN");
		AddDeviceModel.click();

		WebElement addDeviceModelPageTitle = driver.findElement(PAGE_TITLE);
		comm.highlightElement(addDeviceModelPageTitle, "GREEN");
		return addDeviceModelPageTitle.getText();

	}

	public String NewInputFields(String para) throws InterruptedException {
		randomModelCode = comm.generateRandomString(3);
		String randomModelName = comm.generateRandomString(6);
		String randomSerialSeq = comm.generateRandomNumber(4);
		String randomHardwareVer = comm.generateRandomNumber(4);

		if (para.equalsIgnoreCase("add")) {
			WebElement modelCode = wait.until(ExpectedConditions.visibilityOfElementLocated(MODEL_CODE));
			comm.highlightElement(modelCode, "GREEN");
			modelCode.sendKeys("SAM" + randomModelCode);

			WebElement AddModelName = wait.until(ExpectedConditions.visibilityOfElementLocated(ADD_MODEL_NAME));
			comm.highlightElement(AddModelName, "GREEN");
			AddModelName.sendKeys(randomModelName);

			WebElement AddModelSerialSequence = wait
					.until(ExpectedConditions.visibilityOfElementLocated(ADD_MODEL_SERIAL_SEQUENCE));
			comm.highlightElement(AddModelSerialSequence, "GREEN");
			AddModelSerialSequence.sendKeys(randomSerialSeq);

			WebElement AddHardwareVersion = wait
					.until(ExpectedConditions.visibilityOfElementLocated(ADD_HARDWARE_VERSION));
			comm.highlightElement(AddHardwareVersion, "GREEN");
			AddHardwareVersion.sendKeys(randomHardwareVer);

			Thread.sleep(500);

			WebElement AddSubmitButton = wait.until(ExpectedConditions.visibilityOfElementLocated(ADD_SUBMIT_BUTTON));
			comm.highlightElement(AddSubmitButton, "GREEN");
			AddSubmitButton.click();

			Thread.sleep(500);

			WebElement DeviceModelPageTitle = driver.findElement(PAGE_TITLE);
			comm.highlightElement(DeviceModelPageTitle, "GREEN");
			return DeviceModelPageTitle.getText();

		} else if (para.equalsIgnoreCase("update")) {
			WebElement modelCode = wait.until(ExpectedConditions.visibilityOfElementLocated(MODEL_CODE));
			comm.highlightElement(modelCode, "GREEN");
			modelCode.clear();
			modelCode.sendKeys("SAM" + randomModelCode + "Updated");

			WebElement UpdateModelName = wait.until(ExpectedConditions.visibilityOfElementLocated(ADD_MODEL_NAME));
			UpdateModelName.clear();
			comm.highlightElement(UpdateModelName, "GREEN");
			UpdateModelName.sendKeys(randomModelName + "Updated");

			WebElement UpdateModelSerialSequence = wait
					.until(ExpectedConditions.visibilityOfElementLocated(ADD_MODEL_SERIAL_SEQUENCE));
			UpdateModelSerialSequence.clear();
			comm.highlightElement(UpdateModelSerialSequence, "GREEN");
			UpdateModelSerialSequence.sendKeys(randomSerialSeq + "Updated");

			WebElement UpdateHardwareVersion = wait
					.until(ExpectedConditions.visibilityOfElementLocated(ADD_HARDWARE_VERSION));
			UpdateHardwareVersion.clear();
			comm.highlightElement(UpdateHardwareVersion, "GREEN");
			UpdateHardwareVersion.sendKeys(randomHardwareVer + "Updated");

			WebElement UpdateButton = wait.until(ExpectedConditions.visibilityOfElementLocated(ADD_UPDATE_BUTTON));
			comm.highlightElement(UpdateButton, "GREEN");
			UpdateButton.click();

			Thread.sleep(500);

			WebElement DeviceModelPageTitle = driver.findElement(PAGE_TITLE);
			return DeviceModelPageTitle.getText();
		}

		return "Not either devce model is added or updated";

	}

	public String searchModel() throws InterruptedException {
		WebElement search = wait.until(ExpectedConditions.visibilityOfElementLocated(SEARCH_FIELD));
		comm.highlightElement(search, "GREEN");
		search.sendKeys("SAM"+randomModelCode);

		WebElement searchButton = wait.until(ExpectedConditions.visibilityOfElementLocated(SEARCH_BUTTON));
		comm.highlightElement(searchButton, "GREEN");
		searchButton.click();

		Thread.sleep(500);
		
		WebElement DeviceModelPageTitle = driver.findElement(PAGE_TITLE);
		comm.highlightElement(DeviceModelPageTitle, "GREEN");
		return DeviceModelPageTitle.getText();
	}

	public String viewModel() throws InterruptedException {
		WebElement viewButton = wait.until(ExpectedConditions.visibilityOfElementLocated(EYE_ICON));
		comm.highlightElement(viewButton, "GREEN");
		viewButton.click();

		Thread.sleep(500);

		WebElement DeviceModelPageTitle = driver.findElement(PAGE_TITLE);
		comm.highlightElement(DeviceModelPageTitle, "GREEN");
		return DeviceModelPageTitle.getText();
	}

	public String searchModel2() throws InterruptedException {
		WebElement search = wait.until(ExpectedConditions.visibilityOfElementLocated(SEARCH_FIELD));
		comm.highlightElement(search, "GREEN");
		search.clear();
		search.sendKeys("SAM" + randomModelCode + "Updated");

		WebElement searchButton = wait.until(ExpectedConditions.visibilityOfElementLocated(SEARCH_BUTTON));
		comm.highlightElement(searchButton, "GREEN");
		searchButton.click();

		Thread.sleep(500);
		
		WebElement DeviceModelPageTitle = driver.findElement(PAGE_TITLE);
		comm.highlightElement(DeviceModelPageTitle, "GREEN");
		return DeviceModelPageTitle.getText();
	}

	public String DeleteModel() throws InterruptedException {
		WebElement DeleteButton = wait.until(ExpectedConditions.visibilityOfElementLocated(DELETE_ICON));
		comm.highlightElement(DeleteButton, "GREEN");
		DeleteButton.click();

		Alert alert = driver.switchTo().alert();
		alert.accept();
		driver.navigate().refresh();
		Thread.sleep(500);
		
		WebElement DeviceModelPageTitle = driver.findElement(PAGE_TITLE);
		return DeviceModelPageTitle.getText();
	}

}
