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
 
import com.aepl.sam.constants.Constants;
import com.aepl.sam.locators.DispatchedDevicesPageLocators;
import com.aepl.sam.utils.CommonMethods;
 
public class DispatchedDevicesPage extends DispatchedDevicesPageLocators {
	private static final Logger logger = LogManager.getLogger(DispatchedDevicesPage.class);
 
	private WebDriver driver;
	private WebDriverWait wait;
	private CommonMethods comm;
	private JavascriptExecutor js;
 
	public DispatchedDevicesPage(WebDriver driver, WebDriverWait wait, CommonMethods comm) {
		this.driver = driver;
		this.wait = wait;
		this.comm = comm;
		this.js = (JavascriptExecutor) driver;
		logger.info("Initialized DispatchedDevicesPage");
	}
 
	public String navBarLink() {
		logger.info("Navigating to Dispatched Devices page via nav bar");
		WebElement device_utility = wait.until(ExpectedConditions.visibilityOfElementLocated(DEVICE_UTILITY));
		comm.highlightElement(device_utility, "solid purple");
		device_utility.click();
 
//		WebElement devModel = wait.until(ExpectedConditions.visibilityOfElementLocated(DISPATCHED_DEVICE));
		WebElement DisDevice = driver.findElement(DISPATCHED_DEVICE);
		comm.highlightElement(DisDevice, "solid purple");
		DisDevice.click();
 
		String currentUrl = driver.getCurrentUrl();
		logger.info("Navigation successful. URL: " + currentUrl);
		return currentUrl;
	}
 
	public String ClickAddDisDevice() {
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
 
	public String NewInputFields(String action) {
		switch (action.toLowerCase()) {
		case "add":
			return addDevice();
		case "update":
			return updateDevice();
		default:
			logger.warn("Invalid operation type: " + action);
			throw new IllegalArgumentException("Invalid action: " + action);
		}
	}
 
	private String addDevice() {
		logger.info("Filling input fields to ADD dispatched device");
 
		WebElement addUID = wait.until(ExpectedConditions.visibilityOfElementLocated(UID));
		comm.highlightElement(addUID, "GREEN");
		addUID.sendKeys(Constants.DEVICE_UID);
 
		WebElement customerPartNo = wait.until(ExpectedConditions.elementToBeClickable(CUST_PART_NO));
		comm.highlightElement(customerPartNo, "solid purple");
		customerPartNo.sendKeys(Constants.PART_NO_ADD);
 
		selectCustomer("AEPL");
 
		WebElement submitButton = wait.until(ExpectedConditions.elementToBeClickable(SUBMIT_BTN));
		js.executeScript("arguments[0].scrollIntoView(true);", submitButton);
		submitButton.click();
 
		String title = driver.findElement(PAGE_TITLE).getText();
		logger.info("Device added. Page title: " + title);
		return title;
	}
 
	private String updateDevice() {
		logger.info("Updating dispatched device");
 
		WebElement customerPartNo = wait.until(ExpectedConditions.elementToBeClickable(CUST_PART_NO));
		customerPartNo.sendKeys(Constants.PART_NO_UPDATE);
 
		selectCustomer("TML");
 
		WebElement updateButton = wait.until(ExpectedConditions.elementToBeClickable(UPDATE_BTN));
		js.executeScript("arguments[0].scrollIntoView(true);", updateButton);
		updateButton.click();
 
		return driver.findElement(PAGE_TITLE).getText();
	}
 
	private void selectCustomer(String customerName) {
		driver.findElement(RelativeLocator.with(By.tagName("span")).toRightOf(CUST_PART_NO)).click();
		List<WebElement> options = wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(CUST_OPTIONS));
		for (WebElement option : options) {
			if (option.getText().equals(customerName)) {
				option.click();
				break;
			}
		}
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
 
	public String DeleteDevice() {
		logger.info("Deleting dispatched device");
		WebElement DeleteButton = wait.until(ExpectedConditions.visibilityOfElementLocated(DELETE_ICON));
		comm.highlightElement(DeleteButton, "solid purple");
		DeleteButton.click();
 
		Alert alert = driver.switchTo().alert();
		alert.accept();
 
		WebElement AddDisDevicePageTitle = driver.findElement(PAGE_TITLE);
		String title = AddDisDevicePageTitle.getText();
		logger.info("Device deleted. Page title: " + title);
		return title;
	}
}