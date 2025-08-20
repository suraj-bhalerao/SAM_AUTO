package com.aepl.sam.pages;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

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

	private static final Logger logger = LogManager.getLogger(DispatchedDevicesPage.class);

	private WebDriver driver;
	private WebDriverWait wait;
	private CommonMethods comm;
	private CalendarActions CalAct;
	private JavascriptExecutor js;

	public DispatchedDevicesPage(WebDriver driver, WebDriverWait wait, CommonMethods comm) {
		this.driver = driver;
		this.wait = wait;
		this.comm = comm;
		this.CalAct = new CalendarActions(driver, wait);
		this.js = (JavascriptExecutor) driver;
		logger.info("Initialized DispatchedDevicesPage");
	}

	public String navBarLink() {
		logger.info("Navigating to Dispatched Devices page via nav bar");
		WebElement device_utility = wait.until(ExpectedConditions.visibilityOfElementLocated(DEVICE_UTILITY));
		comm.highlightElement(device_utility, "solid purple");
		device_utility.click();

		WebElement DisDevice = driver.findElement(DISPATCHED_DEVICE);
		comm.highlightElement(DisDevice, "solid purple");
		DisDevice.click();

		String currentUrl = driver.getCurrentUrl();
		logger.info("Navigation successful. URL: " + currentUrl);
		return currentUrl;
	}

	public String ClickManualUpload() {
		logger.info("Clicking Add Dispatched Device button");
		WebElement AddDisDevice = wait.until(ExpectedConditions.visibilityOfElementLocated(MANUAL_UPLOAD));
		comm.highlightElement(AddDisDevice, "solid purple");
		AddDisDevice.click();

		WebElement AddDisDevicePageTitle = driver.findElement(PAGE_TITLE);
		comm.highlightElement(AddDisDevicePageTitle, "solid purple");
		String title = AddDisDevicePageTitle.getText();
		logger.info("Page title after click: " + title);
		return title;
	}

	public String NewInputFields(String para) throws InterruptedException {
		if (para.equalsIgnoreCase("add")) {
			logger.info("Filling input fields to ADD dispatched device");

			WebElement AddUID = wait.until(ExpectedConditions.visibilityOfElementLocated(UID));
			comm.highlightElement(AddUID, "GREEN");
			AddUID.sendKeys("ACON4SA212240006474");

			WebElement customerPartNo = wait.until(ExpectedConditions.elementToBeClickable(CUST_PART_NO));
			comm.highlightElement(customerPartNo, "solid purple");
			customerPartNo.sendKeys("PART001");

			driver.findElement(RelativeLocator.with(By.tagName("span")).toRightOf(CUST_PART_NO)).click();
			List<WebElement> customer_names = wait
					.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(CUST_OPTIONS));
			for (WebElement option : customer_names) {
				if (option.getText().equals("AEPL")) {
					option.click();
					break;
				}
			}

			WebElement SubmitButton = wait.until(ExpectedConditions.visibilityOfElementLocated(SUBMIT_BTN));
			Thread.sleep(500);
			js.executeScript("window.scrollBy(0,5000);");
			Thread.sleep(500);
			SubmitButton.click();

			Thread.sleep(500);
			WebElement AddDisDevicePageTitle = driver.findElement(PAGE_TITLE);
			String title = AddDisDevicePageTitle.getText();
			logger.info("Device added. Page title: " + title);
			return title;

		} else if (para.equalsIgnoreCase("update")) {
			logger.info("Updating dispatched device");

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

			WebElement updateButton = wait.until(ExpectedConditions.visibilityOfElementLocated(UPDATE_BTN));
			Thread.sleep(500);
			js.executeScript("window.scrollBy(0,5000);");
			Thread.sleep(500);
			updateButton.click();

			WebElement AddDisDevicePageTitle = driver.findElement(PAGE_TITLE);
			String title = AddDisDevicePageTitle.getText();
			logger.info("Device updated. Page title: " + title);
			return title;
		}

		logger.warn("Invalid operation type passed to NewInputFields");
		return "Not either production device is added or updated";
	}
	
	public String ClickBulkUpload() {
		logger.info("Clicking Add Dispatched Device button");
		WebElement AddDisDevice = wait.until(ExpectedConditions.visibilityOfElementLocated(BULK_UPLOAD));
		comm.highlightElement(AddDisDevice, "solid purple");
		AddDisDevice.click();

		WebElement AddDisDevicePageTitle = driver.findElement(PAGE_TITLE);
		comm.highlightElement(AddDisDevicePageTitle, "solid purple");
		String title = AddDisDevicePageTitle.getText();
		logger.info("Page title after click: " + title);
		return title;
	}
	
	
	public String SearchDevice() {
		logger.info("Searching for dispatched device");
		WebElement search = wait.until(ExpectedConditions.visibilityOfElementLocated(SEARCH_FIELD));
		comm.highlightElement(search, "solid purple");
		search.sendKeys("ACON4SA212240006474");

		WebElement searchButton = wait.until(ExpectedConditions.visibilityOfElementLocated(SEARCH_BUTTON));
		comm.highlightElement(searchButton, "solid purple");
		searchButton.click();

		js.executeScript("window.scrollBy(0,5000);");

		WebElement AddDisDevicePageTitle = driver.findElement(PAGE_TITLE);
		String title = AddDisDevicePageTitle.getText();
		logger.info("Search completed. Page title: " + title);
		return title;
	}
	public String viewDevice() {
		logger.info("Viewing dispatched device details");
		WebElement viewButton = wait.until(ExpectedConditions.visibilityOfElementLocated(EYE_ICON));
		viewButton.click();

		WebElement AddDisDevicePageTitle = driver.findElement(PAGE_TITLE);
		String title = AddDisDevicePageTitle.getText();
		logger.info("View loaded. Page title: " + title);
		return title;
	}

}