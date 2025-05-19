package com.aepl.sam.pages;

import java.util.List;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.locators.RelativeLocator;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.aepl.sam.actions.CalendarActions;
import com.aepl.sam.locators.DispatchedDevicesPageLocators;
import com.aepl.sam.utils.CommonMethods;

public class DispatchedDevicesPage extends DispatchedDevicesPageLocators {

	private WebDriver driver;
	private WebDriverWait wait;
	private CommonMethods comm;
	private CalendarActions CalAct;
	JavascriptExecutor js = (JavascriptExecutor) driver;

	public DispatchedDevicesPage(WebDriver driver, WebDriverWait wait, CommonMethods comm) {
		this.driver = driver;
		this.wait = wait;
		this.comm = comm;
		this.CalAct = new CalendarActions(driver, wait);

	}

	public String navBarLink() {
		WebElement device_utility = wait.until(ExpectedConditions.visibilityOfElementLocated(DEVICE_UTILITY));
		comm.highlightElement(device_utility, "GREEN");
		device_utility.click();

		WebElement devModel = wait.until(ExpectedConditions.visibilityOfElementLocated(DISPATCHED_DEVICE));
		comm.highlightElement(devModel, "GREEN");
		devModel.click();

		return driver.getCurrentUrl();
	}

	public String ClickAddDisDevice() {
		WebElement AddDisDevice = wait.until(ExpectedConditions.visibilityOfElementLocated(ADD_DISPATCHED_DEVICE));
		comm.highlightElement(AddDisDevice, "GREEN");
		AddDisDevice.click();

		WebElement AddDisDevicePageTitle = driver.findElement(PAGE_TITLE);
		comm.highlightElement(AddDisDevicePageTitle, "GREEN");
		return AddDisDevicePageTitle.getText();
	}

	public String NewInputFields(String para) throws InterruptedException {
		if (para.equalsIgnoreCase("add")) {

			WebElement AddUID = wait.until(ExpectedConditions.visibilityOfElementLocated(UID));
			comm.highlightElement(AddUID, "GREEN");
//			String uid = "ACCON4NA" + String.format("%11d", (int) (Math.random() * 1_000_0000));
//			System.out.println("UID: " + uid);
			AddUID.sendKeys("ACON4SA212240006474");

			WebElement customerPartNo = wait.until(ExpectedConditions.elementToBeClickable(CUST_PART_NO));
			comm.highlightElement(customerPartNo, "GREEN");
			customerPartNo.sendKeys("PART001");

			driver.findElement(RelativeLocator.with(By.tagName("span")).toRightOf(CUST_PART_NO)).click();
			List<WebElement> cutomer_options = wait
					.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(CUST_OPTIONS));
			for (WebElement option : cutomer_options) {
				if (option.getText().equals("AEPL")) {
					option.click();
					break;
				}
			}

//			WebElement AddIMEI = wait.until(ExpectedConditions.visibilityOfElementLocated(IMEI));
//			AddIMEI.sendKeys("AAAAAAAAAAAAAAA");
//
//			WebElement AddICCID = wait.until(ExpectedConditions.visibilityOfElementLocated(ICCID));
//			AddICCID.sendKeys("23425245877637749328");
//
//			WebElement Add_DEVICE_MODEL_NAME = wait
//					.until(ExpectedConditions.visibilityOfElementLocated(DEVICE_MODEL_NAME));
//			Add_DEVICE_MODEL_NAME.sendKeys("SamparkLite");
//			Thread.sleep(2000);
//			
//			wait.until(ExpectedConditions.elementToBeClickable(BOOTSTRAP_EXPIRY_DATE));
//			CalAct.selectDate(BOOTSTRAP_EXPIRY_DATE, "17-04-2025");
//
//			wait.until(ExpectedConditions.elementToBeClickable(PRODUCTION_DATE));
//			CalAct.selectDate(PRODUCTION_DATE, "17-04-2025");
//
//			WebElement Add_COMPANY_PART_NO = wait.until(ExpectedConditions.visibilityOfElementLocated(COMPANY_PART_NO));
//			Add_COMPANY_PART_NO.sendKeys("9876543219");
//
//			WebElement Add_SAMPARK_FIRMWARE = wait
//					.until(ExpectedConditions.visibilityOfElementLocated(SAMPARK_FIRMWARE));
//			Add_SAMPARK_FIRMWARE.sendKeys("Airtel");
//
//			WebElement Add_SAMPARK_TEST_STATUS = wait
//					.until(ExpectedConditions.visibilityOfElementLocated(SAMPARK_TEST_STATUS));
//			Add_SAMPARK_TEST_STATUS.sendKeys("BSNL");
//
//			WebElement Add_EMISSION_TYPE = wait.until(ExpectedConditions.visibilityOfElementLocated(EMISSION_TYPE));
//			Add_EMISSION_TYPE.sendKeys("BSNL");
//
//			WebElement Add_SIM_OPERATOR = wait.until(ExpectedConditions.visibilityOfElementLocated(SIM_OPERATOR));
//			Add_SIM_OPERATOR.sendKeys("BSNL");
//
//			WebElement Add_SIM_VENDOR = wait.until(ExpectedConditions.visibilityOfElementLocated(SIM_VENDOR));
//			Add_SIM_VENDOR.sendKeys("BSNL");

			WebElement SubmitButton = wait.until(ExpectedConditions.visibilityOfElementLocated(SUBMIT_BTN));
			Thread.sleep(1000);
			((JavascriptExecutor) driver).executeScript("window.scrollBy(0,5000);");
			Thread.sleep(1000);
			SubmitButton.click();

			Thread.sleep(2000);
			WebElement AddDisDevicePageTitle = driver.findElement(PAGE_TITLE);
			return AddDisDevicePageTitle.getText();

		} else if (para.equalsIgnoreCase("update")) {
//			WebElement AddUID = wait.until(ExpectedConditions.visibilityOfElementLocated(UID));
//			AddUID.clear();
//			AddUID.sendKeys("ACON4SA212240006474");

			WebElement customerPartNo = wait.until(ExpectedConditions.elementToBeClickable(CUST_PART_NO));
			customerPartNo.sendKeys("PART002");

			driver.findElement(RelativeLocator.with(By.tagName("span")).toRightOf(CUST_PART_NO)).click();
			List<WebElement> cutomer_options = wait
					.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(CUST_OPTIONS));
			for (WebElement option : cutomer_options) {
				if (option.getText().equals("TML")) {
					option.click();
					break;
				}
			}

//			WebElement AddIMEI = wait.until(ExpectedConditions.visibilityOfElementLocated(IMEI));
//			AddIMEI.clear();
//			AddIMEI.sendKeys("123456789000000");
//
//			WebElement AddICCID = wait.until(ExpectedConditions.visibilityOfElementLocated(ICCID));
//			AddICCID.clear();
//			AddICCID.sendKeys("89916430134726500000");
//
//			WebElement Add_DEVICE_MODEL_NAME = wait
//					.until(ExpectedConditions.visibilityOfElementLocated(DEVICE_MODEL_NAME));
//			Add_DEVICE_MODEL_NAME.clear();
//			Add_DEVICE_MODEL_NAME.sendKeys("SamparkDiet");
//			Thread.sleep(2000);
//
//			wait.until(ExpectedConditions.elementToBeClickable(BOOTSTRAP_EXPIRY_DATE));
//			CalAct.selectDate(BOOTSTRAP_EXPIRY_DATE, "17-03-2025");
//
//			wait.until(ExpectedConditions.elementToBeClickable(PRODUCTION_DATE));
//			CalAct.selectDate(PRODUCTION_DATE, "18-03-2025");
//
//			WebElement Add_COMPANY_PART_NO = wait.until(ExpectedConditions.visibilityOfElementLocated(COMPANY_PART_NO));
//			Add_COMPANY_PART_NO.clear();
//			Thread.sleep(1000);
//			Add_COMPANY_PART_NO.sendKeys("SAM001");
//
//			WebElement Add_SAMPARK_FIRMWARE = wait
//					.until(ExpectedConditions.visibilityOfElementLocated(SAMPARK_FIRMWARE));
//			Add_SAMPARK_FIRMWARE.clear();
//			Add_SAMPARK_FIRMWARE.sendKeys("TST_10");
//
//			WebElement Add_SAMPARK_TEST_STATUS = wait
//					.until(ExpectedConditions.visibilityOfElementLocated(SAMPARK_TEST_STATUS));
//			Add_SAMPARK_TEST_STATUS.clear();
//			Add_SAMPARK_TEST_STATUS.sendKeys("FAIL");
//
//			WebElement Add_EMISSION_TYPE = wait.until(ExpectedConditions.visibilityOfElementLocated(EMISSION_TYPE));
//			Add_EMISSION_TYPE.clear();
//			Add_EMISSION_TYPE.sendKeys("A4G");
//
//			WebElement Add_SIM_OPERATOR = wait.until(ExpectedConditions.visibilityOfElementLocated(SIM_OPERATOR));
//			Add_SIM_OPERATOR.clear();
//			Add_SIM_OPERATOR.sendKeys("BSNL");
//
//			WebElement Add_SIM_VENDOR = wait.until(ExpectedConditions.visibilityOfElementLocated(SIM_VENDOR));
//			Add_SIM_VENDOR.clear();
//			Add_SIM_VENDOR.sendKeys("Sensorise");
//
//			WebElement RefreshButton = wait.until(ExpectedConditions.visibilityOfElementLocated(REFRESH_BUTTON));
//			RefreshButton.click();
//			Thread.sleep(1000);
//
//			WebElement BackButton = wait.until(ExpectedConditions.visibilityOfElementLocated(BACK_BUTTON));
//			BackButton.click();
//			Thread.sleep(1000);

			WebElement updateButton = wait.until(ExpectedConditions.visibilityOfElementLocated(UPDATE_BTN));
			Thread.sleep(1000);
			((JavascriptExecutor) driver).executeScript("window.scrollBy(0,5000);");
			Thread.sleep(1000);
			updateButton.click();

			WebElement AddDisDevicePageTitle = driver.findElement(PAGE_TITLE);
			return AddDisDevicePageTitle.getText();
		}

		return "Not either production device is added or updated";

	}

	public String SearchDevice() {
		WebElement search = wait.until(ExpectedConditions.visibilityOfElementLocated(SEARCH_FIELD));
		comm.highlightElement(search, "GREEN");
		search.sendKeys("ACON4SA212240006474");

		WebElement searchButton = wait.until(ExpectedConditions.visibilityOfElementLocated(SEARCH_BUTTON));
		comm.highlightElement(searchButton, "GREEN");
		searchButton.click();

		((JavascriptExecutor) driver).executeScript("window.scrollBy(0,5000);");

		WebElement AddDisDevicePageTitle = driver.findElement(PAGE_TITLE);
		return AddDisDevicePageTitle.getText();

	}

	public String viewDevice() {
		WebElement viewButton = wait.until(ExpectedConditions.visibilityOfElementLocated(EYE_ICON));
		viewButton.click();

		WebElement AddDisDevicePageTitle = driver.findElement(PAGE_TITLE);
		return AddDisDevicePageTitle.getText();
	}

	public String DeleteDevice() {
		WebElement DeleteButton = wait.until(ExpectedConditions.visibilityOfElementLocated(DELETE_ICON));
		comm.highlightElement(DeleteButton, "GREEN");
		DeleteButton.click();

		Alert alert = driver.switchTo().alert();
		alert.accept();

		WebElement AddDisDevicePageTitle = driver.findElement(PAGE_TITLE);
		return AddDisDevicePageTitle.getText();
	}
}
