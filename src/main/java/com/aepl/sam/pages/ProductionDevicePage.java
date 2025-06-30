package com.aepl.sam.pages;

import java.util.List;

import org.openqa.selenium.Alert;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.aepl.sam.actions.CalendarActions;
import com.aepl.sam.constants.Constants;
import com.aepl.sam.locators.ProductionDevicePageLocators;
import com.aepl.sam.utils.CommonMethods;

public class ProductionDevicePage extends ProductionDevicePageLocators {

	private WebDriver driver;
	private WebDriverWait wait;
	private CommonMethods commonMethods;
	private CalendarActions CalAct;
	JavascriptExecutor js = (JavascriptExecutor) driver;
	private String randomUIN;

	public ProductionDevicePage(WebDriver driver, WebDriverWait wait) {
		this.driver = driver;
		this.wait = wait;
		this.commonMethods = new CommonMethods(driver, wait);
		this.CalAct = new CalendarActions(driver, wait);

	}

	public String navBarLink() throws InterruptedException {
		WebElement deviceUtil = wait.until(ExpectedConditions.visibilityOfElementLocated(DEVICE_UTILITY));
		deviceUtil.click();
		WebElement prodDevice = wait.until(ExpectedConditions.visibilityOfElementLocated(PRODUCTION_DEVICES));
		prodDevice.click();
		Thread.sleep(500);
		return driver.getCurrentUrl();
	}

	public String ClickAddProdDevice() throws InterruptedException {
		WebElement AddProdDevice = wait.until(ExpectedConditions.visibilityOfElementLocated(ADD_PROD_DEVICE));
		AddProdDevice.click();

		WebElement addProdDevicePageTitle = wait.until(ExpectedConditions.visibilityOfElementLocated(PAGE_TITLE));
		return addProdDevicePageTitle.getText();
	}

	public String NewInputFields(String para) throws InterruptedException {
		JavascriptExecutor js = (JavascriptExecutor) driver;
		randomUIN = commonMethods.generateRandomUIN();

		if (para.equalsIgnoreCase("add")) {

			WebElement AddUID = wait.until(ExpectedConditions.visibilityOfElementLocated(UID));
			commonMethods.highlightElement(AddUID, "GREEN");
			AddUID.sendKeys(randomUIN);

			WebElement AddIMEI = wait.until(ExpectedConditions.visibilityOfElementLocated(IMEI));
			commonMethods.highlightElement(AddIMEI, "GREEN");
			AddIMEI.sendKeys(Constants.IMEI);

			WebElement AddICCID = wait.until(ExpectedConditions.visibilityOfElementLocated(ICCID));
			commonMethods.highlightElement(AddICCID, "GREEN");
			AddICCID.sendKeys(Constants.ICCID);

			// Select device model
			WebElement deviceModelDropdown = wait
					.until(ExpectedConditions.visibilityOfElementLocated(DEVICE_MODEL_NAME));
//		deviceModelDropdown.click();
			js.executeScript("arguments[0].click();", deviceModelDropdown);

			List<WebElement> deviceModelOptions = wait
					.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(DEVICE_MODEL_OPTIONS));
			boolean modelFound = false;
			for (WebElement modelOption : deviceModelOptions) {
				String modelText = modelOption.getText().trim();
				js.executeScript("arguments[0].scrollIntoView({block: 'center'});", modelOption);
				commonMethods.highlightElement(modelOption, "YELLOW");

				if (modelText.equals(Constants.DEVICE_MODEL)) {
					modelOption.click();
					Thread.sleep(300);
					System.out.println("Selected device model: " + modelText);
					modelFound = true;
					break;
				}
			}
			if (!modelFound) {
				System.out.println("Device model " + Constants.DEVICE_MODEL + " not found in options.");
			}

			WebElement Add_MOBILE_NUMBER = wait.until(ExpectedConditions.visibilityOfElementLocated(MOBILE_NUMBER));
			commonMethods.highlightElement(Add_MOBILE_NUMBER, "GREEN");
			Add_MOBILE_NUMBER.sendKeys(Constants.MOBILE_NUMBER + "00000");

			WebElement Add_SERVICE_PROVIDER = wait
					.until(ExpectedConditions.visibilityOfElementLocated(SERVICE_PROVIDER));
			commonMethods.highlightElement(Add_SERVICE_PROVIDER, "GREEN");
			Add_SERVICE_PROVIDER.sendKeys(Constants.ISP_1);

			WebElement Add_ALT_MOBILE_NO = wait.until(ExpectedConditions.visibilityOfElementLocated(ALT_MOBILE_NO));
			commonMethods.highlightElement(Add_ALT_MOBILE_NO, "GREEN");
			Add_ALT_MOBILE_NO.sendKeys(Constants.ALT_MOBILE_NUMBER);

			WebElement Add_ALT_SERVICE_PROVIDER = wait
					.until(ExpectedConditions.visibilityOfElementLocated(ALT_SERVICE_PROVIDER));
			commonMethods.highlightElement(Add_ALT_SERVICE_PROVIDER, "GREEN");
			Add_ALT_SERVICE_PROVIDER.sendKeys(Constants.ISP_2);

			WebElement Add_FIRMWARE = wait.until(ExpectedConditions.visibilityOfElementLocated(FIRMWARE));
			commonMethods.highlightElement(Add_FIRMWARE, "GREEN");
			Add_FIRMWARE.sendKeys(Constants.FIRMWARE);

			WebElement sim_vendor = wait.until(ExpectedConditions.visibilityOfElementLocated(SIM_VENDOR));
			commonMethods.highlightElement(sim_vendor, "GREEN");
			sim_vendor.sendKeys(Constants.ISP_2);

			CalAct.selectDate(CAL_BTN, "04-04-2025");

			WebElement SubmitButton = wait.until(ExpectedConditions.visibilityOfElementLocated(SUBMIT_BTN));
			commonMethods.highlightElement(SubmitButton, "GREEN");
			SubmitButton.click();

			WebElement ProdDevicePageTitle = wait.until(ExpectedConditions.visibilityOfElementLocated(PAGE_TITLE));
			commonMethods.highlightElement(ProdDevicePageTitle, "GREEN");
			return ProdDevicePageTitle.getText();

		} else if (para.equalsIgnoreCase("update")) {
			WebElement deviceModelDropdown = wait.until(ExpectedConditions.elementToBeClickable(DEVICE_MODEL_NAME));
			js.executeScript("arguments[0].click();", deviceModelDropdown);

			List<WebElement> deviceModelOptions = wait
					.until(ExpectedConditions.presenceOfAllElementsLocatedBy(DEVICE_MODEL_OPTIONS));
			boolean modelFound = false;
			for (WebElement modelOption : deviceModelOptions) {
				String modelText = modelOption.getText().trim();
				js.executeScript("arguments[0].scrollIntoView({block: 'center'});", modelOption);
				commonMethods.highlightElement(modelOption, "YELLOW");

				if (modelText.equals(Constants.DEVICE_MODEL)) {
					modelOption.click();
					Thread.sleep(300);
					System.out.println("Selected device model: " + modelText);
					modelFound = true;
					break;
				}
			}
			if (!modelFound) {
				System.out.println("Device model " + Constants.DEVICE_MODEL + " not found in options.");
			}

			WebElement Add_MOBILE_NUMBER = wait.until(ExpectedConditions.visibilityOfElementLocated(MOBILE_NUMBER));
			Add_MOBILE_NUMBER.clear();
			commonMethods.highlightElement(Add_MOBILE_NUMBER, "GREEN");
			Add_MOBILE_NUMBER.sendKeys(Constants.MOBILE_NUMBER + "00000");

			WebElement Add_SERVICE_PROVIDER = wait
					.until(ExpectedConditions.visibilityOfElementLocated(SERVICE_PROVIDER));
			Add_SERVICE_PROVIDER.clear();
			commonMethods.highlightElement(Add_SERVICE_PROVIDER, "GREEN");
			Add_SERVICE_PROVIDER.sendKeys(Constants.ISP_2);

			WebElement Add_ALT_MOBILE_NO = wait.until(ExpectedConditions.visibilityOfElementLocated(ALT_MOBILE_NO));
			Add_ALT_MOBILE_NO.clear();
			commonMethods.highlightElement(Add_ALT_MOBILE_NO, "GREEN");
			Add_ALT_MOBILE_NO.sendKeys(Constants.ALT_MOBILE_NUMBER);

			WebElement Add_ALT_SERVICE_PROVIDER = wait
					.until(ExpectedConditions.visibilityOfElementLocated(ALT_SERVICE_PROVIDER));
			Add_ALT_SERVICE_PROVIDER.clear();
			commonMethods.highlightElement(Add_ALT_SERVICE_PROVIDER, "GREEN");
			Add_ALT_SERVICE_PROVIDER.sendKeys(Constants.ISP_1);

			WebElement Add_FIRMWARE = wait.until(ExpectedConditions.visibilityOfElementLocated(FIRMWARE));
			Add_FIRMWARE.clear();
			commonMethods.highlightElement(Add_FIRMWARE, "GREEN");
			Add_FIRMWARE.sendKeys(Constants.UP_FIRMWARE);

			WebElement sim_vendor = wait.until(ExpectedConditions.visibilityOfElementLocated(SIM_VENDOR));
			sim_vendor.clear();
			commonMethods.highlightElement(sim_vendor, "GREEN");
			sim_vendor.sendKeys(Constants.ISP_1);

			CalAct.selectDate(CAL_BTN, "26-06-2025");

			WebElement update_btn = wait.until(ExpectedConditions.visibilityOfElementLocated(UPDATE_BTN));
			commonMethods.highlightElement(update_btn, "GREEN");
			update_btn.click();

			WebElement ProdDevicePageTitle = wait.until(ExpectedConditions.visibilityOfElementLocated(PAGE_TITLE));
			commonMethods.highlightElement(ProdDevicePageTitle, "GREEN");
			return ProdDevicePageTitle.getText();

		}
		driver.navigate().back();

		return "Not either production device is added or updated";
	}

	public String searchDevice() throws InterruptedException {
		driver.navigate().back();
		WebElement search = wait.until(ExpectedConditions.visibilityOfElementLocated(SEARCH_FIELD));
		search.sendKeys(Constants.IMEI);
		WebElement searchButton = wait.until(ExpectedConditions.visibilityOfElementLocated(SEARCH_BUTTON));
		searchButton.click();
		((JavascriptExecutor) driver).executeScript("window.scrollBy(0,5000);");
		Thread.sleep(500);
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
		Alert alert = driver.switchTo().alert();
		alert.accept();
		Thread.sleep(2000);
		WebElement DeletePageTitle = wait.until(ExpectedConditions.visibilityOfElementLocated(PAGE_TITLE));
		return DeletePageTitle.getText();

	}
}
