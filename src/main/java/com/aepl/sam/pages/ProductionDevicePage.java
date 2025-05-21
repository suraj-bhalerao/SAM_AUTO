package com.aepl.sam.pages;

import org.openqa.selenium.Alert;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.aepl.sam.actions.CalendarActions;
import com.aepl.sam.actions.MouseActions;
import com.aepl.sam.locators.ProductionDevicePageLocators;
import com.aepl.sam.utils.CommonMethods;

public class ProductionDevicePage extends ProductionDevicePageLocators {

	private WebDriver driver;
	private WebDriverWait wait;
	private CommonMethods commonMethods;
	private MouseActions action;
	private CalendarActions CalAct;
	JavascriptExecutor js = (JavascriptExecutor) driver;

	public ProductionDevicePage(WebDriver driver, WebDriverWait wait, MouseActions action) {
		this.driver = driver;
		this.wait = wait;
		this.commonMethods = new CommonMethods(driver, wait);
		this.action = action;
		this.CalAct = new CalendarActions(driver, wait);

	}

	public String navBarLink() throws InterruptedException {
		WebElement deviceUtil = wait.until(ExpectedConditions.visibilityOfElementLocated(DEVICE_UTILITY));
		deviceUtil.click();
		WebElement prodDevice = wait.until(ExpectedConditions.visibilityOfElementLocated(PRODUCTION_DEVICES));
		prodDevice.click();
		Thread.sleep(2000);
		return driver.getCurrentUrl();
	}

	public String ClickAddProdDevice() throws InterruptedException {
		WebElement AddProdDevice = wait.until(ExpectedConditions.visibilityOfElementLocated(ADD_PROD_DEVICE));
		AddProdDevice.click();

		WebElement addProdDevicePageTitle = wait.until(ExpectedConditions.visibilityOfElementLocated(PAGE_TITLE));
		return addProdDevicePageTitle.getText();
	}

	public String NewInputFields(String para) throws InterruptedException {
		if (para.equalsIgnoreCase("add")) {

			WebElement AddUID = wait.until(ExpectedConditions.visibilityOfElementLocated(UID));
			AddUID.sendKeys("ACON4IA202200049619");

			WebElement AddIMEI = wait.until(ExpectedConditions.visibilityOfElementLocated(IMEI));
			AddIMEI.sendKeys("123456789054353");

			WebElement AddICCID = wait.until(ExpectedConditions.visibilityOfElementLocated(ICCID));
			AddICCID.sendKeys("89916430134726531712");

			System.out.println("************************************");
			Thread.sleep(1000);
			WebElement Add_DEVICE_MODEL_NAME = driver.findElement(DEVICE_MODEL_NAME);
			Add_DEVICE_MODEL_NAME.click();
			System.out.println("************************************");

			WebElement Add_DEVICE_MODEL_OPTIONS = wait
					.until(ExpectedConditions.visibilityOfElementLocated(DEVICE_MODEL_OPTIONS));
			Add_DEVICE_MODEL_OPTIONS.click();

			WebElement Add_MOBILE_NUMBER = wait.until(ExpectedConditions.visibilityOfElementLocated(MOBILE_NUMBER));
			Add_MOBILE_NUMBER.sendKeys("9876543219");

			WebElement Add_SERVICE_PROVIDER = wait
					.until(ExpectedConditions.visibilityOfElementLocated(SERVICE_PROVIDER));
			Add_SERVICE_PROVIDER.sendKeys("BSNL");

			WebElement Add_ALT_MOBILE_NO = wait.until(ExpectedConditions.visibilityOfElementLocated(ALT_MOBILE_NO));
			Add_ALT_MOBILE_NO.sendKeys("0987654321");

			WebElement Add_ALT_SERVICE_PROVIDER = wait
					.until(ExpectedConditions.visibilityOfElementLocated(ALT_SERVICE_PROVIDER));
			Add_ALT_SERVICE_PROVIDER.sendKeys("Airtel");

			WebElement Add_FIRMWARE = wait.until(ExpectedConditions.visibilityOfElementLocated(FIRMWARE));
			Add_FIRMWARE.sendKeys("1.2.3");

			WebElement sim_vendor = wait.until(ExpectedConditions.visibilityOfElementLocated(SIM_VENDOR));
			sim_vendor.sendKeys("Airtel");

			CalAct.selectDate(CAL_BTN, "04-04-2025");

			WebElement SubmitButton = wait.until(ExpectedConditions.visibilityOfElementLocated(SUBMIT_BTN));
			SubmitButton.click();

			WebElement ProdDevicePageTitle = wait.until(ExpectedConditions.visibilityOfElementLocated(PAGE_TITLE));
			return ProdDevicePageTitle.getText();

		} else if (para.equalsIgnoreCase("update")) {
			WebElement AddUID = wait.until(ExpectedConditions.visibilityOfElementLocated(UID));
			AddUID.clear();
			AddUID.sendKeys("ACON4IA202200000000");

			WebElement AddIMEI = wait.until(ExpectedConditions.visibilityOfElementLocated(IMEI));
			AddIMEI.clear();
			AddIMEI.sendKeys("123456789000000");

			WebElement AddICCID = wait.until(ExpectedConditions.visibilityOfElementLocated(ICCID));
			AddICCID.clear();
			AddICCID.sendKeys("89916430134726500000");

			WebElement Add_DEVICE_MODEL_NAME = wait
					.until(ExpectedConditions.visibilityOfElementLocated(DEVICE_MODEL_NAME));
			Add_DEVICE_MODEL_NAME.click();

			WebElement Add_DEVICE_MODEL_OPTIONS = wait
					.until(ExpectedConditions.visibilityOfElementLocated(DEVICE_MODEL_OPTIONS));
			Add_DEVICE_MODEL_OPTIONS.click();

			WebElement Add_MOBILE_NUMBER = wait.until(ExpectedConditions.visibilityOfElementLocated(MOBILE_NUMBER));
			Add_MOBILE_NUMBER.clear();
			Add_MOBILE_NUMBER.sendKeys("0987654321");

			WebElement Add_SERVICE_PROVIDER = wait
					.until(ExpectedConditions.visibilityOfElementLocated(SERVICE_PROVIDER));
			Add_SERVICE_PROVIDER.clear();
			Add_SERVICE_PROVIDER.sendKeys("Airtel");

			WebElement Add_ALT_MOBILE_NO = wait.until(ExpectedConditions.visibilityOfElementLocated(ALT_MOBILE_NO));
			Add_ALT_MOBILE_NO.clear();
			Add_ALT_MOBILE_NO.sendKeys("9876543219");

			WebElement Add_ALT_SERVICE_PROVIDER = wait
					.until(ExpectedConditions.visibilityOfElementLocated(ALT_SERVICE_PROVIDER));
			Add_ALT_SERVICE_PROVIDER.clear();
			Add_ALT_SERVICE_PROVIDER.sendKeys("BSNL");

			WebElement Add_FIRMWARE = wait.until(ExpectedConditions.visibilityOfElementLocated(FIRMWARE));
			Add_FIRMWARE.clear();
			Add_FIRMWARE.sendKeys("2.0.0");

			WebElement sim_vendor = wait.until(ExpectedConditions.visibilityOfElementLocated(SIM_VENDOR));
			sim_vendor.clear();
			sim_vendor.sendKeys("Vodafone");

			CalAct.selectDate(CAL_BTN, "17-03-2025");

			WebElement update_btn = wait.until(ExpectedConditions.visibilityOfElementLocated(UPDATE_BTN));
			update_btn.click();

			WebElement ProdDevicePageTitle = wait.until(ExpectedConditions.visibilityOfElementLocated(PAGE_TITLE));
			return ProdDevicePageTitle.getText();
		}

		return "Not either production device is added or updated";

	}

	public String searchDevice() throws InterruptedException {
		WebElement DeviceToSearch = driver.findElement(MODEL_TO_SEARCH);
		String device = DeviceToSearch.getText();
		WebElement search = wait.until(ExpectedConditions.visibilityOfElementLocated(SEARCH_FIELD));
		search.sendKeys(device);
		WebElement searchButton = wait.until(ExpectedConditions.visibilityOfElementLocated(SEARCH_BUTTON));
		searchButton.click();
		((JavascriptExecutor) driver).executeScript("window.scrollBy(0,5000);");
		Thread.sleep(1000);
		WebElement searchPageTitle = wait.until(ExpectedConditions.visibilityOfElementLocated(PAGE_TITLE));
		return searchPageTitle.getText();

	}

	public String viewDevice() throws InterruptedException {
		WebElement viewButton = wait.until(ExpectedConditions.visibilityOfElementLocated(EYE_ICON));
		viewButton.click();
		Thread.sleep(2000);
		WebElement UpdatePageTitle = wait.until(ExpectedConditions.visibilityOfElementLocated(PAGE_TITLE));
		return UpdatePageTitle.getText();
	}

	public String DeleteDevice() throws InterruptedException {
		WebElement DeleteButton = wait.until(ExpectedConditions.visibilityOfElementLocated(DELETE_ICON));
		DeleteButton.click();
		Thread.sleep(1000);
		Alert alert = driver.switchTo().alert(); // Switch to the alert
		alert.accept();
		Thread.sleep(2000);
		WebElement DeletePageTitle = wait.until(ExpectedConditions.visibilityOfElementLocated(PAGE_TITLE));
		return DeletePageTitle.getText();

	}
}
