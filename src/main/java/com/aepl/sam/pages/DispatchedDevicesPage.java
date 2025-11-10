package com.aepl.sam.pages;

import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.datatransfer.StringSelection;
import java.awt.event.KeyEvent;
import java.nio.file.Paths;
import java.time.Duration;
import java.util.List;
import java.util.NoSuchElementException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.locators.RelativeLocator;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.aepl.sam.constants.Constants;
import com.aepl.sam.locators.DispatchedDevicesPageLocators;

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

	public String validateSingleInputBox(String fieldName, String inputValue) {
		// Search for both input and mat-select fields
		String xpath = String.format("//*[@id='%1$s' or @name='%1$s' or @class='%1$s' or @formcontrolname='%1$s']",
				fieldName);
//		System.out.println("XPath used for locating the field: " + xpath);

		List<WebElement> elements = driver.findElements(By.xpath(xpath));

		if (elements.isEmpty()) {
			throw new NoSuchElementException("No input/mat-select found for: " + fieldName);
		}

		WebElement element = elements.get(0);
		String tagName = element.getTagName();

//		System.out.println("Tag name of the located element: " + tagName);
		if ("mat-select".equalsIgnoreCase(tagName)) {
			return validateMatSelectField(element, inputValue);
		} else {
			return validateInputField(element, inputValue);
		}
	}

	// neglect this... this is not working as expected so we skipped mat-select
	// validation
	private String validateMatSelectField(WebElement matSelect, String optionText) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
		WebElement submitButton = driver.findElement(By.className("submit-button"));

		try {
			wait.until(ExpectedConditions.elementToBeClickable(matSelect));
			matSelect.click();

			// Wait for dropdown panel (mat-option) to appear
			By optionLocator = By.xpath(String.format("//mat-select[@formcontrolname='%1$s']", optionText));
			WebElement option = wait.until(ExpectedConditions.elementToBeClickable(optionLocator));
			option.click();

			try {
				Thread.sleep(300);
				option.click();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}

			submitButton.click();

			WebElement parentField = matSelect.findElement(By.xpath("./ancestor::mat-form-field"));
			List<WebElement> errorElements = parentField.findElements(By.tagName("mat-error"));
			if (!errorElements.isEmpty()) {
				return errorElements.get(0).getText().trim();
			}

			return "✅ No validation error";

		} catch (org.openqa.selenium.TimeoutException e) {
			return "⚠️ No validation message appeared";
		}
	}

	private String validateInputField(WebElement inputBox, String inputValue) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
		WebElement submitButton = wait.until(ExpectedConditions.visibilityOfElementLocated(SUBMIT_BTN));

		try {
			wait.until(ExpectedConditions.visibilityOf(inputBox));
			String type = inputBox.getAttribute("type");

			if ("file".equalsIgnoreCase(type)) {
				// 1. Click inside file input to enable it
				inputBox.click();

				// 2. Optionally: sendKeys the file path here (if you want to upload), or skip
				// for validation
				if (inputValue != null && !inputValue.trim().isEmpty()) {
					inputBox.sendKeys(inputValue);
				}

				// 3. Click outside on the submit button to trigger validation
				submitButton.click();

				// 4. Get error messages if present
				WebElement parentField = inputBox
						.findElement(By.xpath("./ancestor::div[contains(@class,'form-field')]"));
				List<WebElement> errorElements = parentField.findElements(By.tagName("mat-error"));

				if (!errorElements.isEmpty()) {
					return errorElements.get(0).getText().trim();
				}
				return "✅ File upload validated successfully";
			}

			// Handle non-file inputs as before.
			wait.until(ExpectedConditions.elementToBeClickable(inputBox));
			inputBox.clear();
			if (inputValue != null && !inputValue.isEmpty()) {
				inputBox.sendKeys(inputValue);
			}
			submitButton.click();

			WebElement parentField = inputBox.findElement(By.xpath("./ancestor::mat-form-field"));
			WebElement errorElement = wait
					.until(ExpectedConditions.visibilityOfNestedElementsLocatedBy(parentField, By.tagName("mat-error")))
					.get(0);
			return errorElement.getText().trim();

		} catch (org.openqa.selenium.TimeoutException e) {
			return "⚠️ No validation message appeared";
		}
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

	public String addDevice() {
		logger.info("Filling input fields to ADD dispatched device");

		driver.findElement(REFRESHBTN).click();

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

		String title = wait.until(ExpectedConditions.elementToBeClickable(PAGE_TITLE)).getText();
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

	public boolean isManualUploadButtonVisible() {
		return wait.until(ExpectedConditions.visibilityOfElementLocated(MANUAL_UPLOAD)).isDisplayed();
	}

	public boolean isManualUploadButtonClickable() {
		return wait.until(ExpectedConditions.elementToBeClickable(MANUAL_UPLOAD)).isEnabled();
	}

	public String getPageTitleAfterClickingManualUpload() {
		JavascriptExecutor js = (JavascriptExecutor) driver;
		// scroll upwards to make the button visible
		js.executeScript("window.scrollTo(0, 0);");
		wait.until(ExpectedConditions.elementToBeClickable(MANUAL_UPLOAD)).click();
		return wait.until(ExpectedConditions.visibilityOfElementLocated(PAGE_TITLE)).getText();
	}

	public boolean isCustomerNameFieldClickable() {
		return wait.until(ExpectedConditions.visibilityOfElementLocated(CUST_PART_NO)).isEnabled()
				&& driver.findElement(RelativeLocator.with(By.tagName("span")).toRightOf(CUST_PART_NO)).isDisplayed();
	}

	public boolean isSubmitButtonDisabledOnMandatoryFieldsEmpty() {
		WebElement submitButton = wait.until(ExpectedConditions.visibilityOfElementLocated(SUBMIT_BTN));
		return !submitButton.isEnabled();
	}

	public String getSuccessToastMessage() {
		WebElement toastMessage = wait.until(ExpectedConditions.visibilityOfElementLocated(TOAST_MSG));
		comm.highlightElement(toastMessage, "solid purple");
		return toastMessage.getText().trim().toString();
	}

	public boolean isBulkUploadButtonVisible() {
		return wait.until(ExpectedConditions.visibilityOfElementLocated(BULK_UPLOAD)).isDisplayed();
	}

	public boolean isBulkUploadButtonClickable() {
		return wait.until(ExpectedConditions.elementToBeClickable(BULK_UPLOAD)).isEnabled();
	}

	public String getPageTitleAfterClickingBulkUpload() {
		JavascriptExecutor js = (JavascriptExecutor) driver;
		// scroll upwards to make the button visible
		js.executeScript("window.scrollTo(0, 0);");
		wait.until(ExpectedConditions.elementToBeClickable(BULK_UPLOAD)).click();
		return wait.until(ExpectedConditions.visibilityOfElementLocated(PAGE_TITLE)).getText();
	}

	public boolean isDownloadSampleLinkClickable() {
		for (int i = 0; i < 3; i++) {
			try {
				WebElement downloadLink = wait.until(ExpectedConditions.elementToBeClickable(DOWNLOAD_SAMPLE_LINK));
				comm.highlightElement(downloadLink, "solid purple");
				downloadLink.click();

				// Handle alert if present
				try {
					wait.until(ExpectedConditions.alertIsPresent()).accept();
				} catch (TimeoutException e) {
					// no alert shown, continue
				}

				return downloadLink.isEnabled();
			} catch (StaleElementReferenceException e) {
				logger.warn("StaleElementReferenceException caught. Retrying... Attempt: " + (i + 1));
			}
		}
		return false;
	}

	public boolean isSampleDownloadFileContentCorrect() {
		return comm.verifyCSVHeader(
				Paths.get(System.getProperty("user.home"), "Downloads", "Sample_Dispatch_Sheet.xlsx").toString(),
				"UID,Customer Name,TML Part Number");
	}

	public boolean isAttachmentButtonClickable() {
		return wait.until(ExpectedConditions.elementToBeClickable(ATTACHMENT_BTN)).isEnabled();
	}

	public boolean isSubmitButtonDisabledWhenNoFileUploaded() {
		// 1. Find the file input field (no click)
		WebElement fileInput = wait
				.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@formcontrolname='file']")));

		// 2. Check that it is empty
		String filePath = fileInput.getAttribute("value");
		if (filePath != null && !filePath.isEmpty()) {
			logger.warn("File input is not empty. Current value: {}", filePath);
		} else {
			logger.info("File input is empty as expected.");
		}

		// 3. Locate the submit button
		WebElement submitButton = wait.until(ExpectedConditions.presenceOfElementLocated(SUBMIT_BTN));

		// 4. Check if button is disabled
		boolean isEnabled = submitButton.isEnabled();

		// 5. Optional: Double-check using the 'disabled' attribute (covers
		// Angular/React cases)
		String disabledAttr = submitButton.getAttribute("disabled");
		boolean isDisabledAttr = disabledAttr != null
				&& (disabledAttr.equals("true") || disabledAttr.equals("disabled"));

		boolean isDisabled = !isEnabled || isDisabledAttr;

		logger.info("Submit button disabled state: {}", isDisabled);
		return isDisabled;
	}

	public String uploadFileAndSubmit() {
		String message;
		try {
			// 1. Locate the file input field
			WebElement fileInput = wait.until(ExpectedConditions.elementToBeClickable(ATTACHMENT_BTN));
			fileInput.click();

			// 2. Upload the file by sending the file path
			String filePath = "D:\\AEPL_AUTOMATION\\SAM_AUTO\\src\\test\\resources\\SampleUpload\\Sample_Dispatch_Sheet.xlsx";
			StringSelection selection = new StringSelection(filePath);
			Toolkit.getDefaultToolkit().getSystemClipboard().setContents(selection, null);

			Robot robot = new Robot();
			robot.delay(500);
			robot.keyPress(KeyEvent.VK_CONTROL);
			robot.keyPress(KeyEvent.VK_V);
			robot.keyRelease(KeyEvent.VK_V);
			robot.keyRelease(KeyEvent.VK_CONTROL);
			robot.keyPress(KeyEvent.VK_ENTER);
			robot.keyRelease(KeyEvent.VK_ENTER);

			// 3. Locate and click the submit button
			WebElement submitButton = wait.until(ExpectedConditions.elementToBeClickable(SUBMIT_BTN));
			submitButton.click();
			logger.info("Submit button clicked.");

			// 4. Get and return the toast message text
			WebElement toastMessage = wait.until(ExpectedConditions.visibilityOfElementLocated(TOAST_MSG));
			comm.highlightElement(toastMessage, "solid purple");
			message = toastMessage.getText().trim().toString();
			logger.info("Toast message received: {}", message);
		} catch (Exception e) {
			logger.error("Error during file upload and submission: {}", e.getMessage(), e);
			return "Error during file upload and submission.";

		}
		return message;
	}
}