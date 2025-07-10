package com.aepl.sam.pages;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.Alert;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.aepl.sam.locators.CustomerMasterLocators;
import com.aepl.sam.utils.CommonMethods;

public class CustomerMasterPage extends CustomerMasterLocators {
	private WebDriver driver;
	private WebDriverWait wait;
	private CommonMethods comm;
	private String editedUser;
	private String randomName;

	private static final Logger logger = LogManager.getLogger(CustomerMasterPage.class);

	public CustomerMasterPage(WebDriver driver, WebDriverWait wait) {
		this.driver = driver;
		this.wait = wait;
		this.comm = new CommonMethods(driver, wait);
	}

	public String navBarLink() {
		logger.info("Navigating to Customer Master from Navbar");
		WebElement util = wait.until(ExpectedConditions.visibilityOfElementLocated(DEVICE_UTILITY));
		util.click();

		WebElement govServer = wait.until(ExpectedConditions.visibilityOfElementLocated(CUSTOMER_MASTER_LINK));
		govServer.click();

		String url = driver.getCurrentUrl();
		logger.info("Navigation successful, current URL: {}", url);
		return url;
	}

	public String addNewCustomer() {
		randomName = comm.generateRandomString(4).toUpperCase();

		try {
			logger.info("Attempting to add a new customer");

			// Click 'Add Customer' button
			WebElement addCustomerButton = wait.until(ExpectedConditions.elementToBeClickable(ADD_CUSTOMER_BTN));
			comm.highlightElement(addCustomerButton, "solid purple");
			addCustomerButton.click();
			logger.debug("Clicked Add Customer button");

			// Enter customer name
			WebElement customerNameField = wait.until(ExpectedConditions.visibilityOfElementLocated(CUSTOMER_NAME));
			comm.highlightElement(customerNameField, "solid purple");
			customerNameField.clear();
			customerNameField.sendKeys(randomName);
			logger.info("Entered customer name: {}", randomName);

			// Click 'Save' button
			WebElement saveButton = wait.until(ExpectedConditions.elementToBeClickable(SAVE_BTN));
			comm.highlightElement(saveButton, "solid purple");

			if (saveButton.isEnabled()) {
				saveButton.click();
				logger.info("Save button clicked");

				// Optional: wait for success message or page update
				wait.until(ExpectedConditions.invisibilityOf(saveButton)); 
				logger.info("Customer added successfully: {}", randomName);
				return "Customer Added Successfully: " + randomName;
			} else {
				logger.warn("Save button is not enabled.");
				return "Save Button Disabled - Customer Not Added";
			}

		} catch (TimeoutException te) {
			logger.error("Timeout while adding customer: {}", te.getMessage(), te);
			return "Operation Timed Out";
		} catch (Exception e) {
			logger.error("Error adding new customer: {}", e.getMessage(), e);
			return "No Customer Added";
		}
	}

	public String searchCustomer() {
		try {
			logger.info("Searching for customer: {}", randomName);
			WebElement searchField = driver.findElement(SEARCH_CUSTOMER);
			comm.highlightElement(searchField, "solid purple");
			searchField.sendKeys(randomName);

			WebElement searchButton = driver.findElement(SEARCH_BTN);
			comm.highlightElement(searchButton, "solid purple");
			searchButton.click();

			Thread.sleep(500);

			List<WebElement> customer_data = wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(DATA));

			if (customer_data.size() > 0) {
				for (WebElement customer : customer_data) {
					String customerName = customer.getText();
					if (customerName.equalsIgnoreCase(randomName)) {
						logger.info("Customer found: {}", customerName);
						return "Customer Found";
					}
				}
				logger.info("No exact match found among listed customers.");
			} else {
				logger.warn("No customers found in the search results.");
			}

		} catch (Exception e) {
			logger.error("Error searching for customer: {}", e.getMessage(), e);
		}

		return "No Customer Found";
	}

	public void editCustomer() {
		editedUser = comm.generateRandomString(4).toUpperCase();
		logger.info("Editing customer. New name will be: {}", editedUser);
		try {
			WebElement editButton = driver.findElement(EDIT_BTN);
			comm.highlightElement(editButton, "solid purple");
			editButton.click();
			Thread.sleep(500);

			WebElement customerNameField = driver.findElement(CUSTOMER_NAME);
			comm.highlightElement(customerNameField, "solid purple");
			customerNameField.clear();
			customerNameField.sendKeys(editedUser);
			logger.info("Updated customer name field.");

			WebElement saveButton = driver.findElement(UPDATE_BTN);
			comm.highlightElement(saveButton, "solid purple");
			if (saveButton.isEnabled()) {
				saveButton.click();
				logger.info("Update button clicked.");
			} else {
				logger.warn("Update button is not enabled.");
			}

		} catch (Exception e) {
			logger.error("Error editing customer: {}", e.getMessage(), e);
		}
	}

	public void deleteCustomer() {
		logger.info("Attempting to delete customer: {}", editedUser);
		try {
			this.driver.navigate().refresh();
			Thread.sleep(500);

			List<WebElement> customer_data = wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(D_DATA));

			if (customer_data.size() > 0) {
				for (WebElement customer : customer_data) {
					String customerName = customer.getText();

					if (customerName.equalsIgnoreCase(editedUser)) {
						List<WebElement> del_btns = driver.findElements(DELETE_BTN);

						JavascriptExecutor js = (JavascriptExecutor) driver;
						js.executeScript("window.scrollBy(0, 250)");
						Thread.sleep(500);

						WebElement deleteBtn = del_btns.get(del_btns.size() - 1);
						comm.highlightElement(deleteBtn, "solid purple");
						deleteBtn.click();
						logger.info("Delete button clicked for customer: {}", editedUser);

						Alert alert = wait.until(ExpectedConditions.alertIsPresent());
						alert.accept();
						logger.info("Alert accepted for deletion.");

						Thread.sleep(500);
					}
				}
			} else {
				logger.warn("No customer data rows found during deletion.");
			}
		} catch (Exception e) {
			logger.error("Error deleting customer: {}", e.getMessage(), e);
		}
	}
}
