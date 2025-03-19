package com.aepl.sam.pages;

import org.openqa.selenium.Alert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.aepl.sam.actions.MouseActions;
import com.aepl.sam.locators.DeviceModelsPageLocators;
import com.aepl.sam.utils.CommonMethods;

public class DeviceModelsPage extends DeviceModelsPageLocators {

	private WebDriver driver;
	private WebDriverWait wait;
	private CommonMethods commonMethods;
	private MouseActions action;

	public DeviceModelsPage(WebDriver driver, WebDriverWait wait, MouseActions action) {
		this.driver = driver;
		this.wait = wait;
		this.commonMethods = new CommonMethods(driver, wait);
		this.action = action;
	}

	public String navBarLink() throws InterruptedException {
		action.hoverOverElement(wait.until(ExpectedConditions.visibilityOfElementLocated(DEVICE_UTILITY)));

		WebElement devModel = wait.until(ExpectedConditions.visibilityOfElementLocated(DEVICE_MODELS));
		devModel.click();
		Thread.sleep(2000);
		return driver.getCurrentUrl();
	}

	public String ClickAddDeviceModel() throws InterruptedException {

		WebElement AddDeviceModel = wait.until(ExpectedConditions.visibilityOfElementLocated(ADD_DEVICE_MODELS));
		AddDeviceModel.click();
		Thread.sleep(2000);
		WebElement addDeviceModelPageTitle = driver.findElement(PAGE_TITLE);
		return addDeviceModelPageTitle.getText();

	}

	public String NewInputFields(String para) throws InterruptedException {
		if (para.equalsIgnoreCase("add")) {
			WebElement AddModelName = wait.until(ExpectedConditions.visibilityOfElementLocated(ADD_MODEL_NAME));
			AddModelName.sendKeys("Add Model");

			WebElement AddModelDescription = wait
					.until(ExpectedConditions.visibilityOfElementLocated(ADD_MODEL_DESCRIPTION));
			AddModelDescription.sendKeys("Add Description");

			WebElement AddModelSerialSequence = wait
					.until(ExpectedConditions.visibilityOfElementLocated(ADD_MODEL_SERIAL_SEQUENCE));
			AddModelSerialSequence.sendKeys("Add Sequence");

			WebElement AddHardwareVersion = wait
					.until(ExpectedConditions.visibilityOfElementLocated(ADD_HARDWARE_VERSION));
			AddHardwareVersion.sendKeys("Add Version");
			Thread.sleep(2000);
			WebElement AddSubmitButton = wait.until(ExpectedConditions.visibilityOfElementLocated(ADD_SUBMIT_BUTTON));
			AddSubmitButton.click();
			Thread.sleep(2000);
			WebElement DeviceModelPageTitle = driver.findElement(PAGE_TITLE);
			return DeviceModelPageTitle.getText();

		} else if (para.equalsIgnoreCase("update")) {
			WebElement UpdateModelName = wait.until(ExpectedConditions.visibilityOfElementLocated(ADD_MODEL_NAME));
			UpdateModelName.clear();
			UpdateModelName.sendKeys("Update Model");

			WebElement UpdateModelDescription = wait
					.until(ExpectedConditions.visibilityOfElementLocated(ADD_MODEL_DESCRIPTION));
			UpdateModelDescription.clear();
			UpdateModelDescription.sendKeys("Update Description");

			WebElement UpdateModelSerialSequence = wait
					.until(ExpectedConditions.visibilityOfElementLocated(ADD_MODEL_SERIAL_SEQUENCE));
			UpdateModelSerialSequence.clear();
			UpdateModelSerialSequence.sendKeys("Upadte Sequence");

			WebElement UpdateHardwareVersion = wait
					.until(ExpectedConditions.visibilityOfElementLocated(ADD_HARDWARE_VERSION));
			UpdateHardwareVersion.clear();
			UpdateHardwareVersion.sendKeys("Update Version");

			WebElement UpdateButton = wait.until(ExpectedConditions.visibilityOfElementLocated(ADD_UPDATE_BUTTON));
			UpdateButton.click();
			Thread.sleep(2000);
			WebElement DeviceModelPageTitle = driver.findElement(PAGE_TITLE);
			return DeviceModelPageTitle.getText();
		}

		return "Not either devce model is added or updated";

	}

	public String searchModel() throws InterruptedException {

		WebElement modelTpSearch = driver.findElement(MODEL_TO_SEARCH1);
		String model = modelTpSearch.getText();
		WebElement search = wait.until(ExpectedConditions.visibilityOfElementLocated(SEARCH_FIELD));
		search.sendKeys(model);
		WebElement searchButton = wait.until(ExpectedConditions.visibilityOfElementLocated(SEARCH_BUTTON));
		searchButton.click();

		Thread.sleep(2000);
		WebElement DeviceModelPageTitle = driver.findElement(PAGE_TITLE);
		return DeviceModelPageTitle.getText();

	}

	public String viewModel() throws InterruptedException {
		WebElement viewButton = wait.until(ExpectedConditions.visibilityOfElementLocated(EYE_ICON));
		viewButton.click();
		Thread.sleep(2000);
		WebElement DeviceModelPageTitle = driver.findElement(PAGE_TITLE);
		return DeviceModelPageTitle.getText();
	}
	
	public String searchModel2() throws InterruptedException {

		WebElement modelTpSearch = driver.findElement(MODEL_TO_SEARCH2);
		String model = modelTpSearch.getText();
		WebElement search = wait.until(ExpectedConditions.visibilityOfElementLocated(SEARCH_FIELD));
		search.sendKeys(model);
		WebElement searchButton = wait.until(ExpectedConditions.visibilityOfElementLocated(SEARCH_BUTTON));
		searchButton.click();

		Thread.sleep(2000);
		WebElement DeviceModelPageTitle = driver.findElement(PAGE_TITLE);
		return DeviceModelPageTitle.getText();

	}

	public String DeleteModel() throws InterruptedException {
		WebElement DeleteButton = wait.until(ExpectedConditions.visibilityOfElementLocated(DELETE_ICON));
		DeleteButton.click();
		Thread.sleep(1000);
		Alert alert = driver.switchTo().alert();  

		Thread.sleep(2000);// Switch to the alert
		alert.accept();  // Click OK

		
		Thread.sleep(2000);
		WebElement DeviceModelPageTitle = driver.findElement(PAGE_TITLE);
		return DeviceModelPageTitle.getText();
	}

}
