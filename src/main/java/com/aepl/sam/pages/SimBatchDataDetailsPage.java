package com.aepl.sam.pages;

import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.datatransfer.StringSelection;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import com.aepl.sam.locators.SimBatchDataDetailsPageLocators;
import com.aepl.sam.utils.CommonMethods;

public class SimBatchDataDetailsPage extends SimBatchDataDetailsPageLocators {
	private WebDriver driver;
	private WebDriverWait wait;
	private CommonMethods comm;
	private final Logger logger = LogManager.getLogger(this.getClass().getSimpleName());

	public SimBatchDataDetailsPage(WebDriver driver, WebDriverWait wait, CommonMethods comm) {
		this.driver = driver;
		this.wait = wait;
		this.comm = comm;
	}

	public Boolean navBarLink() {
		boolean isViewed = false;
		try {
			logger.info("Navigating to User Management page...");
			WebElement reports = wait.until(ExpectedConditions.visibilityOfElementLocated(REPORTS));
			Assert.assertTrue(reports.getText().equalsIgnoreCase("reports"));
			reports.click();

			WebElement sim_batch = wait.until(ExpectedConditions.visibilityOfElementLocated(SIM_BATCH_DATA));
			sim_batch.click();

			if (driver.getCurrentUrl()
					.equals("http://aepltest.accoladeelectronics.com:6102/sensorise-sim-data-details")) {
				isViewed = true;
			}
			logger.info("Successfully navigated to User Management page.");
		} catch (Exception e) {
			logger.error("Error navigating to User Management: {}", e.getMessage(), e);
		}
		return isViewed;
	}

	public String verifyPageTitle() {
		WebElement pageTitle = driver.findElement(By.className("page-title"));
		comm.highlightElement(pageTitle, "violet");
		return pageTitle.getText();
	}

	public Boolean validateUpload() {
		try {
			logger.info("Starting profile picture upload...");

			WebElement uploadFile = driver.findElement(By.xpath("//button/mat-icon[contains(text(),'attach')]"));
			uploadFile.click();
			logger.info("Upload button clicked.");

			StringSelection selection = new StringSelection(
					"D:\\AEPL_AUTOMATION\\SAM_AUTO\\src\\test\\resources\\SampleUpload\\Sensorise_SIM_data_Details.xlsx");
			Toolkit.getDefaultToolkit().getSystemClipboard().setContents(selection, null);
			logger.info("Image path copied to clipboard.");

			Robot fileHandler = new Robot();
			Thread.sleep(500);

			fileHandler.keyPress(KeyEvent.VK_CONTROL);
			fileHandler.keyPress(KeyEvent.VK_V);
			fileHandler.keyRelease(KeyEvent.VK_V);
			fileHandler.keyRelease(KeyEvent.VK_CONTROL);
			logger.info("Image path pasted.");

			Thread.sleep(500);
			fileHandler.keyPress(KeyEvent.VK_ENTER);
			fileHandler.keyRelease(KeyEvent.VK_ENTER);
			logger.info("ENTER key pressed to confirm upload.");

			logger.info("Profile picture uploaded successfully.");
			return true;
		} catch (Exception e) {
			logger.error("Error uploading profile picture: {}", e.getMessage(), e);
			return false;
		}
	}

	public Boolean validateSubmitButton() {
		try {
			WebElement submit = wait
					.until(ExpectedConditions.visibilityOfElementLocated(By.className("submit-button")));

			if (submit.isDisplayed() && submit.isEnabled()) {
				logger.info("✅ Submit button is displayed and enabled. Clicking now...");
				submit.click();
				return true;
			} else {
				logger.warn("⚠️ Submit button is either not displayed or disabled.");
				return false;
			}

		} catch (Exception e) {
			logger.error("❌ Unexpected error while validating Submit button: {}", e.getMessage());
			return false;
		}
	}

	public List<String> validateComponentHeades() {
		List<WebElement> actual_headers = driver.findElements(TABLE_HEADERS);
		List<String> head = new ArrayList<>();
		for (WebElement header : actual_headers) {
			String text = header.getText();
			head.add(text);
		}
		logger.info("Extracted Table Headers: {}", head);
		return head;
	}

}
