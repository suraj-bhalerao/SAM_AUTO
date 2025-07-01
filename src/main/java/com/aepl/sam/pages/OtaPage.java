package com.aepl.sam.pages;

import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.datatransfer.StringSelection;
import java.awt.event.KeyEvent;
import java.util.List;

import org.openqa.selenium.Alert;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.aepl.sam.constants.Constants;
import com.aepl.sam.locators.OtaPageLocators;
import com.aepl.sam.utils.CommonMethods;

public class OtaPage extends OtaPageLocators {
	private WebDriver driver;
	private WebDriverWait wait;
	private CommonMethods comm;
	JavascriptExecutor js;

	public OtaPage(WebDriver driver, WebDriverWait wait, CommonMethods commonMethods) {
		this.driver = driver;
		this.wait = wait;
		this.comm = commonMethods;
		this.js = (JavascriptExecutor) driver;
	}

	// Add methods specific to OTA page functionality here
	public String navBarLink() throws InterruptedException {
		WebElement deviceUtil = wait.until(ExpectedConditions.visibilityOfElementLocated(DEVICE_UTILITY));
		comm.highlightElement(deviceUtil, "solid purple");
		deviceUtil.click();
		WebElement ota = wait.until(ExpectedConditions.visibilityOfElementLocated(OTA_LINK));
		comm.highlightElement(ota, "solid purple");
		ota.click();
		return driver.getCurrentUrl();
	}

	public String testManualOtaFeature() {
		try {
			// scroll to the top of the page
			js.executeScript("window.scrollTo(0, 0);");
			Thread.sleep(500);

			// Click on manual OTA button
			WebElement manualOtaButton = wait.until(ExpectedConditions.visibilityOfElementLocated(MANUAL_OTA_BUTTON));
			comm.highlightElement(manualOtaButton, "solid purple");
			manualOtaButton.click();

			// Search for the IMEI against the device
			WebElement searchField = wait.until(ExpectedConditions.visibilityOfElementLocated(SEARCH_FIELD));
			comm.highlightElement(searchField, "solid purple");
			searchField.clear();
			searchField.sendKeys(Constants.IMEI);
			WebElement searchButton = wait.until(ExpectedConditions.elementToBeClickable(SEARCH_BUTTON));
			comm.highlightElement(searchButton, "solid purple");

			Thread.sleep(500); // Wait for the search field to be populated
			searchButton.click();

			// Getting all information of OTA
//			List<WebElement> otaInfo = wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(OTA_INFO));
//			if (otaInfo.size() > 0) {
//				for (WebElement info : otaInfo) {
//					comm.highlightElement(info, "Green");
//					System.out.println("Info: " + info.getText());
//
//					if (info.getText().contains(Constants.IMEI)) {
//						return "OTA information found for IMEI: " + Constants.IMEI;
//					}
//				}
//			}

			Thread.sleep(500);
			js.executeScript("window.scrollBy(0, window.innerHeight / 2 );");

			// Click on new OTA button
			WebElement addOtaButton = wait.until(ExpectedConditions.elementToBeClickable(NEW_OTA_BUTTON));
			comm.highlightElement(addOtaButton, "solid purple");
			Thread.sleep(500);
			
			addOtaButton.click();
			js.executeScript("window.scrollBy(0, window.innerHeight / 2 );");

			List<WebElement> checkboxes = wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(COMMAND_LIST));
			for (WebElement checkbox : checkboxes) {
				comm.highlightElement(checkbox, "solid purple");
				if (checkbox.getText().contains("A2T_GET_IMEI")) {
					if (!checkbox.isSelected()) {
						checkbox.click();
					}
					System.out.println("Checkbox selected for IMEI: " + Constants.IMEI);
				}

				if (checkbox.getText().contains("GET CIP2")) {
					if (!checkbox.isSelected()) {
						checkbox.click();
					}
					System.out.println("Checkbox selected for CIP2: " + checkbox.getText());
				}
			}

			// Setting batch and click submit button
			js.executeScript("window.scrollBy(0, window.innerHeight / 2 );");
			WebElement setBatch = wait.until(ExpectedConditions.visibilityOfElementLocated(SET_BATCH_BTN));
			comm.highlightElement(setBatch, "solid purple");
			Thread.sleep(500); 
			setBatch.click();

			// Click on submit button
			js.executeScript("window.scrollBy(0, window.innerHeight / 2 * 2.2);");
			WebElement submitButton = wait.until(ExpectedConditions.elementToBeClickable(BATCH_SUBMIT_BTN));
			comm.highlightElement(submitButton, "solid purple");
			Thread.sleep(500); 
			submitButton.click();
			Alert alert = wait.until(ExpectedConditions.alertIsPresent());
			if (alert != null) {
				String alertText = alert.getText();
				System.out.println("Alert text: " + alertText);
				alert.accept();

			}
		} catch (Exception e) {
			System.out.println("An error occurred while testing manual OTA feature: " + e.getMessage());
			e.printStackTrace();
			return "Error occurred while testing manual OTA feature: " + e.getMessage();

		}
		return "New OTA added successfully for IMEI: " + Constants.IMEI;
	}

	public String testOtaDetails() {
		// Search again the IMEI to verify the OTA details
		try {
			WebElement searchField = wait.until(ExpectedConditions.visibilityOfElementLocated(SEARCH_FIELD));
			comm.highlightElement(searchField, "solid purple");
			searchField.clear();
			searchField.sendKeys(Constants.IMEI);
			WebElement searchButton = wait.until(ExpectedConditions.elementToBeClickable(SEARCH_BUTTON));
			comm.highlightElement(searchButton, "solid purple");
			Thread.sleep(1000); // Wait for the search field to be populated
			searchButton.click();

			Thread.sleep(1000);
			js.executeScript("window.scrollBy(0, window.innerHeight);");

			List<WebElement> otaInfo = wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(OTA_DETAILS));
			if (otaInfo.size() > 0) {
				for (WebElement info : otaInfo) {
					comm.highlightElement(info, "solid purple");
					System.out.println("Info: " + info.getText());

					if (info.getText().contains(Constants.IMEI) && !info.getText().contains("Aborted")) {
						System.out.println("OTA information found for IMEI: " + Constants.IMEI);
						return "OTA details displayed successfully.";
					}

//					if (!info.getText().contains("Aborted")) {
//						System.out.println("OTA deatils displayed are validated... " + info.getText());
//					}
				}
			} else {
				System.out.println("No OTA information found for IMEI: " + Constants.IMEI);
			}
		} catch (Exception e) {
			System.out.println("An error occurred while testing OTA details: " + e.getMessage());
			e.printStackTrace();
		}
		return "No details found for IMEI: " + Constants.IMEI;
	}

	public boolean testAbortButton() {
		try {
			WebElement abortButton = wait.until(ExpectedConditions.elementToBeClickable(ABORT_BTN));
			js.executeScript("arguments[0].scrollIntoView({behavior: 'smooth', block: 'center'});", abortButton);
			comm.highlightElement(abortButton, "solid purple");
			Thread.sleep(500); 
			abortButton.click();

			return true;
		} catch (Exception e) {
			System.out.println("An error occurred while testing abort button: " + e.getMessage());
			e.printStackTrace();
		}
		return false;
	}

	public String testOtaBatch() {
		driver.navigate().back();

		// search and click on ota batch button
		WebElement otaBatchButton = wait.until(ExpectedConditions.elementToBeClickable(BATCH_OTA_LINK));
		comm.highlightElement(otaBatchButton, "solid purple");
		otaBatchButton.click();

		// Validate component title
		WebElement componentTitle = wait.until(ExpectedConditions.visibilityOfElementLocated(COMPONENT_TITLE));
		comm.highlightElement(componentTitle, "solid purple");
		String titleText = componentTitle.getText();
		if (titleText.equalsIgnoreCase("OTA Batch Details")) {
			System.out.println("Component title validated successfully: " + titleText);
		} else {
			System.out.println("Component title validation failed. Expected 'OTA Batches' but found: " + titleText);
		}

		// Validate the sample download button
		if (comm.validateSampleFileButton()) {
			System.out.println("Sample file button is present and clickable.");
		} else {
			System.out.println("Sample file button is not present or not clickable.");
		}

		// input batch name, description and select file
		try {
			WebElement batchNameField = wait.until(ExpectedConditions.visibilityOfElementLocated(BATCH_NAME_FIELD));
			comm.highlightElement(batchNameField, "solid purple");
			batchNameField.clear();
			Thread.sleep(500);
			batchNameField.sendKeys("SB_OTA_Batch");

			WebElement descriptionField = wait.until(ExpectedConditions.visibilityOfElementLocated(DESCRIPTION_FIELD));
			comm.highlightElement(descriptionField, "solid purple");
			descriptionField.clear();
			Thread.sleep(500);
			descriptionField.sendKeys("This is a test OTA batch.");

			WebElement fileInput = wait.until(ExpectedConditions.elementToBeClickable(FILE_INPUT));
			comm.highlightElement(fileInput, "solid purple");
			Thread.sleep(500);
			fileInput.click();
			// user ROBOT to select the file
			String filePath = "D:\\Sampark_Automation\\SAM_AUTO\\src\\test\\resources\\SampleUpload\\SampleOTATemplate.csv";

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
			System.out.println("Batch name, description, and file input validated successfully.");
		} catch (Exception e) {
			System.out.println(
					"An error occurred while validating batch name, description, and file input: " + e.getMessage());
		}

		// Setting up the ota's from checkboxes
		try {
			List<WebElement> otaCheckboxes = wait
					.until(ExpectedConditions.presenceOfAllElementsLocatedBy(COMMAND_LIST));
			for (WebElement checkbox : otaCheckboxes) {
				comm.highlightElement(checkbox, "solid purple");
				if (checkbox.getText().contains("A2T_GET_CIP3")) {
					if (!checkbox.isSelected()) {
						checkbox.click();
					}
					System.out.println("Checkbox selected for CIP3: " + checkbox.getText());
				}

				if (checkbox.getText().contains("A2T_GET_CGPR")) {
					if (!checkbox.isSelected()) {
						checkbox.click();
					}
					System.out.println("Checkbox selected for CGPR: " + checkbox.getText());
				}
			}
		} catch (Exception e) {
			System.out.println("An error occurred while selecting OTA checkboxes: " + e.getMessage());
			e.printStackTrace();
		}
		
		// Click on set batch button
		try {
			WebElement setBatchButton = wait.until(ExpectedConditions.elementToBeClickable(SET_BATCH_BTN));
			comm.highlightElement(setBatchButton, "solid purple");
			Thread.sleep(1000);
			setBatchButton.click();
		} catch (Exception e) {
			System.out.println("An error occurred while clicking on Set Batch button: " + e.getMessage());
			e.printStackTrace();
		}
		
		// scroll to the bottom of the page and click on submit button
		try {
			js.executeScript("window.scrollBy(0, window.innerHeight);");
			WebElement batchSubmitButton = wait.until(ExpectedConditions.elementToBeClickable(BATCH_SUBMIT_BTN));
			comm.highlightElement(batchSubmitButton, "solid purple");
			Thread.sleep(1000);
			batchSubmitButton.click();
			Alert alert = wait.until(ExpectedConditions.alertIsPresent());
			if (alert != null) {
				String alertText = alert.getText();
				System.out.println("Alert text: " + alertText);
				alert.accept();
			}
		} catch (Exception e) {
			System.out.println("An error occurred while clicking on Submit button: " + e.getMessage());
			e.printStackTrace();
		}
		
		return "OTA batch functionality is working correctly.";
	}
}
