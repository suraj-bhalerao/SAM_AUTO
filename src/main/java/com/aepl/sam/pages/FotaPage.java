package com.aepl.sam.pages;

import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.datatransfer.StringSelection;
import java.awt.event.KeyEvent;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.aepl.sam.locators.FotaPageLocators;
import com.aepl.sam.utils.CommonMethods;

public class FotaPage extends FotaPageLocators {
	private WebDriver driver;
	private WebDriverWait wait;
	private CommonMethods comm;

	public FotaPage(WebDriver driver, WebDriverWait wait, CommonMethods comm) {
		this.driver = driver;
		this.wait = wait;
		this.comm = comm;
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

	public void selectFOTATypeButton(String type) {
		if (type.equalsIgnoreCase("manual")) {
			WebElement manualFOTA = driver.findElement(MANUAL_FOTA_BTN);
			comm.highlightElement(manualFOTA, "RED");

			if (manualFOTA.isDisplayed() && manualFOTA.isEnabled()) {
				manualFOTA.click();
			} else {
				throw new RuntimeException("Manual FOTA button is not displayed or enabled.");
			}
		} else if (type.equalsIgnoreCase("bulk")) {
			this.driver.navigate().back();

			JavascriptExecutor js = (JavascriptExecutor) driver;
			js.executeScript("window.scrollBy(0, -500);");

			WebElement bulkFOTA = wait.until(ExpectedConditions.elementToBeClickable(BULK_FOTA_BTN));
			comm.highlightElement(bulkFOTA, "RED");

			if (bulkFOTA.isDisplayed() && bulkFOTA.isEnabled()) {
				bulkFOTA.click();
			} else {
				throw new RuntimeException("Bulk FOTA button is not displayed or enabled.");
			}
		} else {
			throw new IllegalArgumentException("Invalid FOTA type: " + type);
		}
	}

	// Create a new {Full} FOTA Batch
	public void createManualFotaBatch(String imeiNumber) {
		try {
			WebElement manualFOTA = wait.until(ExpectedConditions.visibilityOfElementLocated(MANUAL_FOTA));
			manualFOTA.click();
			manualFOTA.clear();
			manualFOTA.sendKeys(imeiNumber);

			WebElement search = driver.findElement(SEARCH_BTN);

			if (search.isDisplayed() && search.isEnabled()) {
				search.click();
			} else {
				throw new RuntimeException("Search button is not displayed or enabled.");
			}
			comm.highlightElement(search, "RED");

			Thread.sleep(500);

			// Getting Device Details
			getDeviceFirmwareDetails();
			Thread.sleep(500);

			// Click on Create FOTA Batch
			createNewFOTA();
			Thread.sleep(500);

			// Click on FOTA History
			getFotaHistoryFromTable();
			Thread.sleep(500);

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void getDeviceFirmwareDetails() {
		try {
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

			String text = element.getText();

			if (text == null || text.isEmpty()) {
				text = element.getAttribute("value");
			}
			System.out.println("Devive Firmware Details: ");
			System.err.println(label + ": " + text);
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
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("arguments[0].scrollIntoView(true);", elements.get(elements.size() - 1));
		comm.highlightElement(elements.get(elements.size() - 1), "GREEN");
		elements.get(elements.size() - 1).click();

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

//		WebElement abortFota = wait.until(ExpectedConditions.visibilityOfElementLocated(ABORT_FOTA));
//		comm.highlightElement(abortFota, "RED");

		if (startFota.isDisplayed() && startFota.isEnabled()) {
			startFota.click();
			System.out.println();
			System.out.println("FOTA is started successfully.");
		}
//		else if (abortFota.isDisplayed() && abortFota.isEnabled()) {
//			abortFota.click();
//			System.out.println("FOTA is already in progress, so aborting it.");
//
//		} 
		else {
			throw new RuntimeException("Start FOTA button is not displayed or enabled.");
		}
	}

	private void getFotaHistoryFromTable() {
		JavascriptExecutor js = (JavascriptExecutor) driver;

		js.executeScript("window.scrollBy(0, 20);");

		List<WebElement> fota_history = wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(FOTA_HISTORY));

		for (int i = 0; i < fota_history.size(); i++) {
			WebElement freshRow = driver.findElements(FOTA_HISTORY).get(i);
			System.err.println("Fota Detail - " + freshRow.getText());
		}
	}

	public void createBulkFotaBatch() {
		try {
			WebElement batch_name = driver.findElement(FOTA_BATCH_NAME);
			comm.highlightElement(batch_name, "GREEN");
			batch_name.clear();
			batch_name.sendKeys("DEMO FOTA BATCH");

			WebElement fota_desc = driver.findElement(FOTA_BATCH_DESC);
			comm.highlightElement(fota_desc, "GREEN");
			fota_desc.clear();
			fota_desc.sendKeys("DEMO FOTA BATCH DESCRIPTION");

			WebElement fota_type = wait.until(ExpectedConditions.elementToBeClickable(B_FOTA_TYPE));
			fota_type.click();
			comm.highlightElement(fota_type, "GREEN");

			WebElement fota_type_name = wait.until(ExpectedConditions.elementToBeClickable(B_FOTA_TYPE_NAME));
			fota_type_name.click();
			comm.highlightElement(fota_type_name, "GREEN");

			WebElement upload_file = wait.until(ExpectedConditions.elementToBeClickable(UPLOAD_FILE));
			comm.highlightElement(upload_file, "GREEN");

			if (upload_file.isDisplayed() && upload_file.isEnabled()) {
				upload_file.click();

				Thread.sleep(500);

				String filePath = "D:\\Sampark_Automation\\SAM_AUTO\\src\\test\\resources\\SampleUpload\\SampleFOTATemplate.csv";

				StringSelection selection = new StringSelection(filePath);
				Toolkit.getDefaultToolkit().getSystemClipboard().setContents(selection, null);

				Robot robot = new Robot();
				robot.delay(500);

				robot.keyPress(KeyEvent.VK_CONTROL);
				robot.keyPress(KeyEvent.VK_V);
				robot.keyRelease(KeyEvent.VK_V);
				robot.keyRelease(KeyEvent.VK_CONTROL);

				robot.delay(500);
				robot.keyPress(KeyEvent.VK_ENTER);
				robot.keyRelease(KeyEvent.VK_ENTER);
			} else {
				throw new RuntimeException("Upload file button is not displayed or enabled.");
			}

			// Clicking on the submit button
			WebElement submit = wait.until(ExpectedConditions.elementToBeClickable(SUBMIT_BTN));
			comm.highlightElement(submit, "GREEN");
			if (submit.isDisplayed() && submit.isEnabled()) {
				submit.click();
				System.out.println("FOTA Batch is created successfully.");
			} else {
				throw new RuntimeException("Submit button is not displayed or enabled.");
			}

		} catch (Exception e) {
			System.out.println("Error in createBulkFotaBatch: " + e.getMessage());
			e.printStackTrace();
		}
	}

	public String getFotaBatchList() {
		try {
			JavascriptExecutor js = (JavascriptExecutor) driver;
			js.executeScript("window.scrollTo(0, 0);");

			// Wait for the FOTA history table to be visible
			List<WebElement> topFotaHistory = wait
					.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(FOTA_HISTORY_TABLE));

			// Iterate through each row in the table
			for (WebElement fota : topFotaHistory) {
				comm.highlightElement(fota, "GREEN");

				// Extract text from specific columns
				String batchName = fota.findElement(By.xpath(".//td[2]")).getText().trim();
				String batchDescription = fota.findElement(By.xpath(".//td[3]")).getText().trim();
				String createdBy = fota.findElement(By.xpath(".//td[4]")).getText().trim();

				// Check for matching values
				if ("DEMO FOTA BATCH".equals(batchName) && "DEMO FOTA BATCH DESCRIPTION".equals(batchDescription)
						&& "Suraj Bhalerao".equals(createdBy)) {
					return "Batch found successfully!";
				}
			}

			// If no match was found
			return "No batch with valid details is found.";
		} catch (Exception e) {
			// Print full error details for debugging
			System.out.println("Error in getFotaBatchList: " + e.getClass().getSimpleName() + " - " + e.getMessage());
			e.printStackTrace();
			return "An error occurred while fetching the FOTA batch list.";
		}
	}

}
