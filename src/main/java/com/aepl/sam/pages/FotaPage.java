package com.aepl.sam.pages;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.aepl.sam.actions.MouseActions;
import com.aepl.sam.locators.FotaPageLocators;
import com.aepl.sam.utils.CommonMethods;

public class FotaPage extends FotaPageLocators {
	private WebDriver driver;
	private WebDriverWait wait;
	private MouseActions action;
	private CommonMethods comm;

	public FotaPage(WebDriver driver, WebDriverWait wait, MouseActions action) {
		this.driver = driver;
		this.wait = wait;
		this.action = action;
		this.comm = new CommonMethods(driver, wait);
	}

	// Click on Device Utility
	public void clickDeviceUtility() {
		comm.highlightElement(driver.findElement(DEVICE_UTILITY), "RED");
		driver.findElement(DEVICE_UTILITY).click();
	}

	// Click on FOTA
	public void clickFota() {
		comm.highlightElement(driver.findElement(FOTA_LINK), "RED");
		driver.findElement(FOTA_LINK).click();
	}

	public void clickManualFotaButton() {
		WebElement manualFOTA = driver.findElement(MANUAL_FOTA_BTN);
		comm.highlightElement(manualFOTA, "RED");

		if (manualFOTA.isDisplayed() && manualFOTA.isEnabled()) {
			manualFOTA.click();
		} else {
			throw new RuntimeException("Manual FOTA button is not displayed or enabled.");
		}
	}

	// Create a new {Full} FOTA Batch
	public void createManualFotaBatch(String imeiNumber) {
		try {
			WebElement manualFOTA = driver.findElement(MANUAL_FOTA);
			manualFOTA.click();
			manualFOTA.clear();
			manualFOTA.sendKeys(imeiNumber);

			comm.highlightElement(driver.findElement(MANUAL_FOTA), "GREEN");

			WebElement search = driver.findElement(SEARCH_BTN);

			if (search.isDisplayed() && search.isEnabled()) {
				search.click();
			} else {
				throw new RuntimeException("Search button is not displayed or enabled.");
			}
			comm.highlightElement(search, "RED");

			Thread.sleep(2000);

			// Getting Device Details
			getDeviceDetails();

			Thread.sleep(2000);
			
			// Click on Create FOTA Batch
			createNewFOTA();
			
			// Click on FOTA History
			getFotaHistory();

		} catch (Exception e) {
			e.printStackTrace();
		}
	}


	public void getDeviceDetails() {
		try {
			extractAndPrintDetail(IMEI, "IMEI");

			JavascriptExecutor js = (JavascriptExecutor) driver;
			js.executeScript("arguments[0].scrollIntoView({block: 'center'});", driver.findElement(IMEI));

			extractAndPrintDetail(UIN, "UIN");
			extractAndPrintDetail(VIN, "VIN");
			extractAndPrintDetail(ICCID, "ICCID");
			extractAndPrintDetail(LOGGED_IN_AT, "Logged In At");
			extractAndPrintDetail(JOINED_AT, "Joined At");
			extractAndPrintDetail(VERSION, "Version");
			extractAndPrintDetail(UFW, "UFW");
		} catch (Exception e) {
			System.out.println("Error in getDeviceDetails: " + e.getMessage());
			e.printStackTrace();
		}
	}

	private void extractAndPrintDetail(By locator, String label) {
		try {
			WebElement element = wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
			((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", element);
			comm.highlightElement(element, "GREEN");
			String text = element.getText().trim();
			System.out.println(label + ": " + text);
		} catch (Exception e) {
			System.out.println("Could not find or read element for: " + label);
		}
	}

	public void createNewFOTA() {
		WebElement newFota = driver.findElement(NEW_FOTA_BTN);
		comm.highlightElement(newFota, "RED");

		if (newFota.isDisplayed() && newFota.isEnabled()) {
			newFota.click();
		} else {
			throw new RuntimeException("New FOTA button is not displayed or enabled.");
		}
		
		// Select state 
		WebElement state = wait.until(ExpectedConditions.elementToBeClickable(STATE));
		state.click();
		
		comm.highlightElement(state, "GREEN");
		
		WebElement state_name = wait.until(ExpectedConditions.elementToBeClickable(STATE_NAME));
		state_name.click();
		comm.highlightElement(state_name, "GREEN");
		
		
		// Select UFW
		WebElement ufw = wait.until(ExpectedConditions.elementToBeClickable(NEW_UFW));
		ufw.click();
		
		List<WebElement> elements = driver.findElements(NEW_UFW_NAME);
		elements.getLast().click();
		comm.highlightElement(elements.getLast(), "GREEN");
		
		// Select FOTA Type
		WebElement fota_type = wait.until(ExpectedConditions.elementToBeClickable(FOTA_TYPE));
		fota_type.click();
		comm.highlightElement(fota_type, "GREEN");
		
		WebElement fota_type_name = wait.until(ExpectedConditions.elementToBeClickable(FOTA_TYPE_NAME));
		fota_type_name.click();
		comm.highlightElement(fota_type_name, "GREEN");
		
		
		// Click on Start FOTA
		WebElement startFota = wait.until(ExpectedConditions.elementToBeClickable(START_FOTA));
		comm.highlightElement(startFota, "RED");
		
		if (startFota.isDisplayed() && startFota.isEnabled()) {
			startFota.click();
		} else {
			throw new RuntimeException("Start FOTA button is not displayed or enabled.");
		}
	}
	private void getFotaHistory() {
		((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", FOTA_HISTORY);

		List<WebElement> fota_history = wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(FOTA_HISTORY));
		
//		List<WebElement> theads = wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(FOTA_HISTORY_TABLE_HEADERS));
//		
//		for (WebElement header : theads) {
//			System.out.println("Header: " + header.getText());
//			for (WebElement history : fota_history) {
//				if(header.getText().equalsIgnoreCase("Created At")) {
//					System.out.println(history.getText());
//				}
//			}
//		}
		
		for(WebElement history : fota_history) {
			System.out.println("FOTA History: " + history.getText());
		}
	}
}
