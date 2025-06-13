package com.aepl.sam.pages;

import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.datatransfer.StringSelection;
import java.awt.event.KeyEvent;
import java.time.Duration;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.aepl.sam.constants.Constants;
import com.aepl.sam.enums.Result;
import com.aepl.sam.locators.UserManagementPageLocators;
import com.aepl.sam.utils.CommonMethods;

public class UserManagementPage extends UserManagementPageLocators {
	private WebDriver driver;
	private WebDriverWait wait;
	private CommonMethods comm;
	private final Logger logger = LogManager.getLogger(this.getClass().getSimpleName());

	public UserManagementPage(WebDriver driver, WebDriverWait wait, CommonMethods comm) {
		this.driver = driver;
		this.wait = wait;
		this.comm = comm;
	}

	public String navBarLink() {
		try {
			logger.info("Navigating to User Management page...");
			WebElement user = wait.until(ExpectedConditions.visibilityOfElementLocated(USER));
			user.click();

			WebElement user_man = wait.until(ExpectedConditions.visibilityOfElementLocated(USR_MANAGEMENT_LINK));
			user_man.click();

			logger.info("Successfully navigated to User Management page.");
		} catch (Exception e) {
			logger.error("Error navigating to User Management: {}", e.getMessage(), e);
		}
		return driver.getCurrentUrl();
	}

	public String backButton() {
		try {
			logger.info("Clicking back button...");
			WebElement element = wait.until(ExpectedConditions.visibilityOfElementLocated(BACK_BUTTON));

			JavascriptExecutor js = (JavascriptExecutor) driver;
			js.executeScript("arguments[0].style.border = 'red'", element);

			element.click();
			Thread.sleep(1000);

			logger.info("Clicked on back button: {}", element.getText());
		} catch (Exception e) {
			logger.error("Error clicking back button: {}", e.getMessage(), e);
		}
		return navBarLink();
	}

	public String refreshButton() {
		try {
			logger.info("Clicking refresh button...");
			WebElement refreshBtn = wait.until(ExpectedConditions.elementToBeClickable(REFRESH_BUTTON));

			JavascriptExecutor js = (JavascriptExecutor) driver;
			js.executeScript("arguments[0].style.border = 'red'", refreshBtn);

			Thread.sleep(20);
			refreshBtn.click();

			WebElement pageTitleElement = wait.until(ExpectedConditions.visibilityOfElementLocated(PAGE_TITLE));
			String pageTitle = pageTitleElement.getText();
			logger.info("Page refreshed, current title: {}", pageTitle);
			return pageTitle;
		} catch (Exception e) {
			logger.error("Error clicking refresh button: {}", e.getMessage(), e);
		}
		return "No Data Found!!!";
	}

	public String clickAddUserBtn() {
		String result = Result.FAIL.getValue();
		try {
			logger.info("Clicking 'Add User' button...");
			Thread.sleep(1000);

			WebElement addUser = driver.findElement(ADD_USR_BTN);
			addUser.click();
			Thread.sleep(2000);

			logger.info("'Add User' button clicked successfully.");
			result = "Add User Button Clicked Successfully";
		} catch (Exception e) {
			logger.error("Error clicking 'Add User' button: {}", e.getMessage(), e);
			result = "Error clicking 'Add User' button.";
		}
		return result;
	}

	public void addUserProfilePicture() {
		try {
			logger.info("Uploading user profile picture...");
			JavascriptExecutor js = (JavascriptExecutor) driver;
			js.executeScript("window.scrollTo(0, document.body.scrollHeight);");

			WebElement uploadProfile = driver.findElement(PROF_BTN);
			uploadProfile.click();

			StringSelection selection = new StringSelection(
					"D:\\Sampark_Automation\\SAM_AUTO\\src\\test\\resources\\SampleUpload\\dp.jpg");
			Toolkit.getDefaultToolkit().getSystemClipboard().setContents(selection, null);

			Robot fileHandler = new Robot();
			Thread.sleep(500);

			fileHandler.keyPress(KeyEvent.VK_CONTROL);
			fileHandler.keyPress(KeyEvent.VK_V);
			fileHandler.keyRelease(KeyEvent.VK_V);
			fileHandler.keyRelease(KeyEvent.VK_CONTROL);
			Thread.sleep(500);
			fileHandler.keyPress(KeyEvent.VK_ENTER);
			fileHandler.keyRelease(KeyEvent.VK_ENTER);
		} catch (Exception e) {
			logger.error("Error uploading user profile picture: {}", e.getMessage(), e);
		}
	}

	public void addAndUpdateUser(String param) {
		try {
			logger.info("Performing '{}' operation for user...", param);

			// Click the mat-select to open the dropdown
			WebElement userType = driver.findElement(USR_TYPE);
			userType.click();

			// Wait until options are visible
			WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
			wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("mat-option")));

			// Get all options
			List<WebElement> options = driver.findElements(By.cssSelector("mat-option"));

			// Iterate through options and click the one that matches
			boolean optionFound = false;
			for (WebElement option : options) {
				String text = option.getText().trim();
				if (text.equalsIgnoreCase(Constants.ROLE_TYPE)) {
					option.click();
					optionFound = true;
					break;
				}
			}

			if (!optionFound) {
				logger.warn("Option '{}' not found in mat-select", Constants.ROLE_TYPE);
			}

			WebElement firstName = driver.findElement(FIRST_NAME);
			firstName.clear();
			String randomFirstName = comm.generateRandomString(4);
			String randomFirstName2 = comm.generateRandomString(5);
			firstName.sendKeys(param.equalsIgnoreCase("add") ? randomFirstName : randomFirstName2);

			WebElement lastName = driver.findElement(LAST_NAME);
			lastName.clear();
			String randomLastName = comm.generateRandomString(4);
			String randomLastName2 = comm.generateRandomString(5);
			lastName.sendKeys(param.equalsIgnoreCase("add") ? randomLastName : randomLastName2);

			WebElement email = driver.findElement(EMAIL);
			email.clear();
			email.sendKeys(param.equalsIgnoreCase("add") ? comm.generateRandomEmail().toLowerCase()
					: comm.generateRandomEmail());

			WebElement mobile = driver.findElement(MOBILE);
			mobile.clear();
			mobile.sendKeys(
					param.equalsIgnoreCase("add") ? comm.generateRandomNumber(10) : comm.generateRandomNumber(10));

			WebElement country = driver.findElement(COUNTRY);
			country.clear();
			country.sendKeys(Constants.COUNTRY);

			WebElement state = driver.findElement(STATE);
			state.clear();
			state.sendKeys(Constants.STATE);

			WebElement status = driver.findElement(STATUS);
			status.click();
			status.sendKeys(Keys.DOWN);
			status.sendKeys(Keys.ENTER);

			((JavascriptExecutor) driver).executeScript("window.scrollTo(0, document.body.scrollHeight);");

			WebElement actionBtn = driver.findElement(param.equalsIgnoreCase("add") ? SUBMIT_BTN : UPDATE_BTN);
			actionBtn.click();
			Thread.sleep(2000);

			driver.get(Constants.USR_MAN);

			logger.info("User '{}' operation completed successfully.", param);
		} catch (Exception e) {
			logger.error("Error during '{}' operation: {}", param, e.getMessage(), e);
		}
	}

	public void checkDropdown() {
		try {
			logger.info("Checking dropdown options...");
			WebElement select = wait.until(ExpectedConditions.visibilityOfElementLocated(SELECT));
			select.click();
			Thread.sleep(1000);
			
			List<WebElement> options = wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(DRP_OPTION));
			for (WebElement op : options) {
//				if (op.getText().trim().equalsIgnoreCase("ALL")) {
//					op.click();
//				
//					return;
//				}
				Thread.sleep(1000);
				op.click();
			}

			for (int i = options.size() - 1; i >= 0; i--) {
				Thread.sleep(1000);
				options.get(i).click();
			}

			logger.info("Dropdown options verified successfully.");
		} catch (Exception e) {
			logger.error("Error checking dropdown: {}", e.getMessage(), e);
		}
	}

//	public void checkDropdown() {
//		try {
//			logger.info("Checking dropdown options...");
//
//			WebElement select = wait.until(ExpectedConditions.elementToBeClickable(SELECT));
//			select.click(); // Open the dropdown
//
//			List<WebElement> options = wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(DRP_OPTION));
//
//			// Forward through the list
//			for (int i = 0; i < options.size(); i++) {
//				WebElement option = wait.until(ExpectedConditions.elementToBeClickable(options.get(i)));
//				option.click();
//
//				Thread.sleep(500); // Optional delay for visibility/debug
//
//				// Reopen dropdown for next selection
//				select = wait.until(ExpectedConditions.elementToBeClickable(SELECT));
//				select.click();
//				options = wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(DRP_OPTION));
//			}
//
//			// Backward through the list
//			for (int i = options.size() - 1; i >= 0; i--) {
//				select = wait.until(ExpectedConditions.elementToBeClickable(SELECT));
//				select.click();
//
//				options = wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(DRP_OPTION));
//				WebElement option = wait.until(ExpectedConditions.elementToBeClickable(options.get(i)));
//				option.click();
//
//				Thread.sleep(500); // Optional delay
//			}
//
//			// Final selection: "ALL"
//			select = wait.until(ExpectedConditions.elementToBeClickable(SELECT));
//			select.click();
//
//			List<WebElement> finalOptions = wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(DRP_OPTION));
//
//			boolean allFound = false;
//			for (WebElement option : finalOptions) {
//				if (option.getText().trim().equalsIgnoreCase("ALL")) {
//					wait.until(ExpectedConditions.elementToBeClickable(option)).click();
//					allFound = true;
//					logger.info("\"ALL\" option selected successfully.");
//					break;
//				}
//			}
//
//			if (!allFound) {
//				logger.warn("\"ALL\" option was not found in dropdown.");
//			} else {
//				logger.info("Dropdown options verified successfully.");
//			}
//
//		} catch (Exception e) {
//			logger.error("Error checking dropdown: {}", e.getMessage(), e);
//		}
//	}


	public void searchAndViewUser() {
		try {
			logger.info("Searching and viewing user...");
			WebElement search = driver.findElement(SEARCH_FIELD);
			search.sendKeys(Constants.USER);
			Thread.sleep(1000);

			search.sendKeys(Keys.ENTER);
			Thread.sleep(1000);

			WebElement viewBtn = driver.findElement(EYE_ICON);
			viewBtn.click();
			Thread.sleep(1000);
			logger.info("User search and view successful.");
		} catch (Exception e) {
			logger.error("Error searching and viewing user: {}", e.getMessage(), e);
		}
	}

	public String deleteUser() {
		try {
			searchAndViewUser();
			Thread.sleep(1000);

			List<WebElement> deleteBtn = driver.findElements(DELETE_ICON);
			deleteBtn.get(2).click();

			Alert alert = driver.switchTo().alert();
			alert.accept();

			Thread.sleep(1000);

			logger.info("User deleted successfully.");

			return "User deleted successfully";
		} catch (Exception e) {
			logger.error("Error deleting user: {}", e.getMessage(), e);
		}
		return "Not able to delete user";
	}
}
