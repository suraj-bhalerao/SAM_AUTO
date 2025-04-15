package com.aepl.sam.pages;

import org.openqa.selenium.Alert;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.aepl.sam.actions.CalendarActions;
import com.aepl.sam.actions.MouseActions;
import com.aepl.sam.locators.DispatchedDevicesPageLocators;
import com.aepl.sam.utils.CommonMethods;

public class DispatchedDevicesPage extends DispatchedDevicesPageLocators {

	private WebDriver driver;
	private WebDriverWait wait;
	private CommonMethods commonMethods;
	private MouseActions action;
	private CalendarActions CalAct;
	JavascriptExecutor js = (JavascriptExecutor) driver;

	public DispatchedDevicesPage(WebDriver driver, WebDriverWait wait, MouseActions action) {
		this.driver = driver;
		this.wait = wait;
		this.commonMethods = new CommonMethods(driver, wait);
		this.action = action;
		this.CalAct = new CalendarActions(driver, wait);

	}

	public String NavBarLink() throws InterruptedException {
		action.hoverOverElement(wait.until(ExpectedConditions.visibilityOfElementLocated(DEVICE_UTILITY)));

		WebElement DisDevice = wait.until(ExpectedConditions.visibilityOfElementLocated(DISPATCHED_DEVICE));
		DisDevice.click();
		Thread.sleep(2000);
		return driver.getCurrentUrl();
	}

	public String ClickAddDisDevice() throws InterruptedException {

		WebElement AddDisDevice = wait.until(ExpectedConditions.visibilityOfElementLocated(ADD_DISPATCHED_DEVICE));
		AddDisDevice.click();
		Thread.sleep(2000);
		
		WebElement AddDisDevicePageTitle = driver.findElement(PAGE_TITLE);
		return AddDisDevicePageTitle.getText();

	}

	public String NewInputFields(String para) throws InterruptedException {
		if (para.equalsIgnoreCase("add")) {

			WebElement AddUID = wait.until(ExpectedConditions.visibilityOfElementLocated(UID));
			AddUID.sendKeys("AAAAAAAAAAAAAAAAAAA");

			WebElement AddIMEI = wait.until(ExpectedConditions.visibilityOfElementLocated(IMEI));
			AddIMEI.sendKeys("AAAAAAAAAAAAAAA");

			WebElement AddICCID = wait.until(ExpectedConditions.visibilityOfElementLocated(ICCID));
			AddICCID.sendKeys("23425245877637749328");

			WebElement Add_DEVICE_MODEL_NAME = wait
					.until(ExpectedConditions.visibilityOfElementLocated(DEVICE_MODEL_NAME));
			Add_DEVICE_MODEL_NAME.sendKeys("SamparkLite");
			Thread.sleep(2000);
			
			wait.until(ExpectedConditions.elementToBeClickable(BOOTSTRAP_EXPIRY_DATE));
			CalAct.selectDate(BOOTSTRAP_EXPIRY_DATE, "17-04-2025");

			wait.until(ExpectedConditions.elementToBeClickable(PRODUCTION_DATE));
			CalAct.selectDate(PRODUCTION_DATE, "17-04-2025");

			WebElement Add_COMPANY_PART_NO = wait.until(ExpectedConditions.visibilityOfElementLocated(COMPANY_PART_NO));
			Add_COMPANY_PART_NO.sendKeys("9876543219");

			WebElement Add_SAMPARK_FIRMWARE = wait
					.until(ExpectedConditions.visibilityOfElementLocated(SAMPARK_FIRMWARE));
			Add_SAMPARK_FIRMWARE.sendKeys("Airtel");

			WebElement Add_SAMPARK_TEST_STATUS = wait
					.until(ExpectedConditions.visibilityOfElementLocated(SAMPARK_TEST_STATUS));
			Add_SAMPARK_TEST_STATUS.sendKeys("BSNL");

			WebElement Add_EMISSION_TYPE = wait.until(ExpectedConditions.visibilityOfElementLocated(EMISSION_TYPE));
			Add_EMISSION_TYPE.sendKeys("BSNL");

			WebElement Add_SIM_OPERATOR = wait.until(ExpectedConditions.visibilityOfElementLocated(SIM_OPERATOR));
			Add_SIM_OPERATOR.sendKeys("BSNL");

			WebElement Add_SIM_VENDOR = wait.until(ExpectedConditions.visibilityOfElementLocated(SIM_VENDOR));
			Add_SIM_VENDOR.sendKeys("BSNL");

			WebElement SubmitButton = wait.until(ExpectedConditions.visibilityOfElementLocated(SUBMIT_BTN));

			Thread.sleep(1000);
			((JavascriptExecutor) driver).executeScript("window.scrollBy(0,5000);");
			Thread.sleep(1000);
			SubmitButton.click();

			Thread.sleep(2000);
			WebElement AddDisDevicePageTitle = driver.findElement(PAGE_TITLE);
			return AddDisDevicePageTitle.getText();

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
			Add_DEVICE_MODEL_NAME.clear();
			Add_DEVICE_MODEL_NAME.sendKeys("SamparkDiet");
			Thread.sleep(2000);
			
			wait.until(ExpectedConditions.elementToBeClickable(BOOTSTRAP_EXPIRY_DATE));
			CalAct.selectDate(BOOTSTRAP_EXPIRY_DATE, "17-03-2025");

			wait.until(ExpectedConditions.elementToBeClickable(PRODUCTION_DATE));
			CalAct.selectDate(PRODUCTION_DATE, "18-03-2025");

			WebElement Add_COMPANY_PART_NO = wait.until(ExpectedConditions.visibilityOfElementLocated(COMPANY_PART_NO));
			Add_COMPANY_PART_NO.clear();
			Thread.sleep(1000);
			Add_COMPANY_PART_NO.sendKeys("SAM001");

			WebElement Add_SAMPARK_FIRMWARE = wait
					.until(ExpectedConditions.visibilityOfElementLocated(SAMPARK_FIRMWARE));
			Add_SAMPARK_FIRMWARE.clear();
			Add_SAMPARK_FIRMWARE.sendKeys("TST_10");

			WebElement Add_SAMPARK_TEST_STATUS = wait
					.until(ExpectedConditions.visibilityOfElementLocated(SAMPARK_TEST_STATUS));
			Add_SAMPARK_TEST_STATUS.clear();
			Add_SAMPARK_TEST_STATUS.sendKeys("FAIL");

			WebElement Add_EMISSION_TYPE = wait.until(ExpectedConditions.visibilityOfElementLocated(EMISSION_TYPE));
			Add_EMISSION_TYPE.clear();
			Add_EMISSION_TYPE.sendKeys("A4G");

			WebElement Add_SIM_OPERATOR = wait.until(ExpectedConditions.visibilityOfElementLocated(SIM_OPERATOR));
			Add_SIM_OPERATOR.clear();
			Add_SIM_OPERATOR.sendKeys("BSNL");

			WebElement Add_SIM_VENDOR = wait.until(ExpectedConditions.visibilityOfElementLocated(SIM_VENDOR));
			Add_SIM_VENDOR.clear();
			Add_SIM_VENDOR.sendKeys("Sensorise");

			WebElement RefreshButton = wait.until(ExpectedConditions.visibilityOfElementLocated(REFRESH_BUTTON));
			RefreshButton.click();
			Thread.sleep(1000);
			
			WebElement BackButton = wait.until(ExpectedConditions.visibilityOfElementLocated(BACK_BUTTON));
			BackButton.click();
			Thread.sleep(1000);
			
			WebElement AddDisDevicePageTitle = driver.findElement(PAGE_TITLE);
			return AddDisDevicePageTitle.getText();
		}

		return "Not either production device is added or updated";

	}
	
	public String SearchDevice() throws InterruptedException {

		WebElement DeviceToSearch = driver.findElement(MODEL_TO_SEARCH);
		String device = DeviceToSearch.getText();
		WebElement search = wait.until(ExpectedConditions.visibilityOfElementLocated(SEARCH_FIELD));
		search.sendKeys(device);
		WebElement searchButton = wait.until(ExpectedConditions.visibilityOfElementLocated(SEARCH_BUTTON));
		searchButton.click();
		((JavascriptExecutor) driver).executeScript("window.scrollBy(0,5000);");
		Thread.sleep(1000);
		WebElement AddDisDevicePageTitle = driver.findElement(PAGE_TITLE);
		return AddDisDevicePageTitle.getText();

	}
	
	public String viewDevice() throws InterruptedException {
		WebElement viewButton = wait.until(ExpectedConditions.visibilityOfElementLocated(EYE_ICON));
		viewButton.click();
		Thread.sleep(2000);
		WebElement AddDisDevicePageTitle = driver.findElement(PAGE_TITLE);
		return AddDisDevicePageTitle.getText();
	}
	
	public String DeleteDevice() throws InterruptedException {
		WebElement DeleteButton = wait.until(ExpectedConditions.visibilityOfElementLocated(DELETE_ICON));
		DeleteButton.click();
		Thread.sleep(1000);
		Alert alert = driver.switchTo().alert();  // Switch to the alert
		alert.accept();
		Thread.sleep(2000);
		WebElement AddDisDevicePageTitle = driver.findElement(PAGE_TITLE);
		return AddDisDevicePageTitle.getText();

}
	
	

}
