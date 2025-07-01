package com.aepl.sam.pages;

import java.util.List;

import org.openqa.selenium.Alert;
import org.openqa.selenium.JavascriptExecutor;
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

	public CustomerMasterPage(WebDriver driver, WebDriverWait wait) {
		this.driver = driver;
		this.wait = wait;
		this.comm = new CommonMethods(driver, wait);
	}

	public String navBarLink() {
		WebElement util = wait.until(ExpectedConditions.visibilityOfElementLocated(DEVICE_UTILITY));
		util.click();

		WebElement govServer = wait.until(ExpectedConditions.visibilityOfElementLocated(CUSTOMER_MASTER_LINK));
		govServer.click();

		return driver.getCurrentUrl();
	}

	public String addNewCustomer() {
		try {
			WebElement addCustomerButton = driver.findElement(ADD_CUSTOMER_BTN);
			comm.highlightElement(addCustomerButton, "solid purple");
			addCustomerButton.click();
			Thread.sleep(2000);

			WebElement customerNameField = driver.findElement(CUSTOMER_NAME);
			comm.highlightElement(customerNameField, "solid purple");
			customerNameField.sendKeys(comm.generateRandomString(4).toUpperCase());

			WebElement saveButton = driver.findElement(SAVE_BTN);
			comm.highlightElement(saveButton, "solid purple");
			if (saveButton.isEnabled()) {
				saveButton.click();
			} else {
				System.out.println("Save button is not enabled.");
			}

			return "Customer Added Successfully";
		} catch (Exception e) {
			System.err.println("Error adding new customer: " + e.getMessage());
		}
		return "No Customer Added";
	}

	public String searchCustomer(String input) {
		try {
			WebElement searchField = driver.findElement(SEARCH_CUSTOMER);
			comm.highlightElement(searchField, "solid purple");
			searchField.sendKeys(input);

			WebElement searchButton = driver.findElement(SEARCH_BTN);
			comm.highlightElement(searchButton, "solid purple");
			searchButton.click();

			Thread.sleep(2000);

			List<WebElement> customer_data = wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(DATA));

			if (customer_data.size() > 0) {
				for (WebElement customer : customer_data) {
					String customerName = customer.getText();
					if (customerName.equalsIgnoreCase(input)) {
						System.out.println("Customer found: " + customerName);
						return "Customer Found";
					}
				}
			} else {
				System.out.println("No customers found.");
			}

		} catch (Exception e) {
			System.err.println("Error searching for customer: " + e.getMessage());
		}

		return "No Customer Found";
	}

	public void editCustomer() {
		editedUser = comm.generateRandomString(4).toUpperCase();
		try {
			WebElement editButton = driver.findElement(EDIT_BTN);
			comm.highlightElement(editButton, "solid purple");
			editButton.click();
			Thread.sleep(2000);

			WebElement customerNameField = driver.findElement(CUSTOMER_NAME);
			comm.highlightElement(customerNameField, "solid purple");
			customerNameField.clear();
			customerNameField.sendKeys(editedUser);

			WebElement saveButton = driver.findElement(UPDATE_BTN);
			comm.highlightElement(saveButton, "solid purple");
			if (saveButton.isEnabled()) {
				saveButton.click();
			} else {
				System.out.println("Save button is not enabled.");
			}

		} catch (Exception e) {
			System.err.println("Error editing customer: " + e.getMessage());
		}
	}

	public void deleteCustomer() {
		try {
			this.driver.navigate().refresh();
			
			Thread.sleep(2000);

			List<WebElement> customer_data = wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(D_DATA));

			if (customer_data.size() > 0) {
				for (WebElement customer : customer_data) {
					String customerName = customer.getText();
					
					if (customerName.equalsIgnoreCase(editedUser)) {
						List<WebElement> del_btns = driver.findElements(DELETE_BTN);
						
						JavascriptExecutor js = (JavascriptExecutor) driver;
						js.executeScript("window.scrollBy(0, 250)");
						
						Thread.sleep(2000);
						comm.highlightElement(del_btns.get(del_btns.size()-1), "solid purple");
						del_btns.get(del_btns.size()-1).click();
						
						Alert alert = wait.until(ExpectedConditions.alertIsPresent());
						alert.accept();
						
						Thread.sleep(2000);
					}
				}
			} 
		} catch (Exception e) {
			System.err.println("Error deleting customer: " + e.getMessage());
		}
	}
}
