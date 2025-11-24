package com.aepl.sam.pages;

import java.time.Duration;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.NoAlertPresentException;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.aepl.sam.locators.RoleManagementLocators;

public class RoleManagementPage extends RoleManagementLocators {
	private WebDriver driver;
	private WebDriverWait wait;
	private final Logger logger = LogManager.getLogger(this.getClass().getSimpleName());

	public RoleManagementPage(WebDriver driver, WebDriverWait wait) {
		this.driver = driver;
		this.wait = wait;
	}

	public String navBarLink() {
		try {
			WebElement user = wait.until(ExpectedConditions.visibilityOfElementLocated(USER));
			user.click();
			logger.info("Clicked on USER navbar link");

			WebElement userRole = wait.until(ExpectedConditions.visibilityOfElementLocated(USER_ROLE_LINK));
			userRole.click();
			logger.info("Clicked on USER ROLE link");
		} catch (Exception e) {
			logger.error("Error navigating to User Role page: {}", e.getMessage());
		}
		return driver.getCurrentUrl();
	}

	public String backButton() {
		try {
			WebElement element = wait.until(ExpectedConditions.visibilityOfElementLocated(BACK_BUTTON));
			((JavascriptExecutor) driver).executeScript("arguments[0].style.border = 'solid purple'", element);
			element.click();
			logger.info("Clicked on Back button: {}", element.getText());
		} catch (Exception e) {
			logger.error("Error clicking back button: {}", e.getMessage());
		}
		return navBarLink();
	}

	public String refreshButton() {
		try {
			WebElement refreshBtn = wait.until(ExpectedConditions.elementToBeClickable(REFRESH_BUTTON));
			((JavascriptExecutor) driver).executeScript("arguments[0].style.border = 'solid purple'", refreshBtn);
			Thread.sleep(20);
			refreshBtn.click();
			logger.info("Clicked on Refresh button");

			WebElement page_title = wait.until(ExpectedConditions.visibilityOfElementLocated(PAGE_TITLE));
			return page_title.getText();
		} catch (Exception e) {
			logger.error("Error clicking refresh button: {}", e.getMessage());
		}
		return "No Data Found!!!";
	}

	public String clickAddUserRoleBtn() {
		try {
			WebElement addUserRole = wait.until(ExpectedConditions.visibilityOfElementLocated(ADD_USER));
			addUserRole.click();
			logger.info("Clicked on Add User Role button");

			WebElement header = wait.until(ExpectedConditions.visibilityOfElementLocated(PAGE_TITLE));
			return header.getText();
		} catch (Exception e) {
			logger.error("Unable to click Add User Role: {}", e.getMessage());
			return "Error: Unable to click Add User Role";
		}
	}

	public void selectingOptions() {
		try {
			WebElement addUser = wait.until(ExpectedConditions.visibilityOfElementLocated(ROLE_NAME));
			addUser.sendKeys("DEMO");
			logger.info("Entered role name: DEMO");

			selectMatOption(ROLE_TYPE, "User");
			logger.info("Selected Role Type: User");

			selectMatOption(ROLE_GRP, "QA");
			logger.info("Selected Role Group: QA");
		} catch (Exception e) {
			logger.error("Error selecting options: {}", e.getMessage());
		}
	}

	public void selectOptionsAndSubmit() {
		try {
			JavascriptExecutor js = (JavascriptExecutor) driver;

			WebElement selectAllCheckBox = wait.until(ExpectedConditions.elementToBeClickable(SELECT_ALL));
			selectAllCheckBox.click();
			logger.info("Selected all permissions");

			WebElement submitBtn = wait.until(ExpectedConditions.elementToBeClickable(SUBMIT_BTN));
			js.executeScript("arguments[0].scrollIntoView({block: 'center'});", submitBtn);

			try {
				submitBtn.click();
			} catch (org.openqa.selenium.ElementClickInterceptedException e) {
				js.executeScript("arguments[0].click();", submitBtn);
				logger.warn("Submit button click intercepted; using JS click.");
			}
			logger.info("Submitted new user role");

			backButton();
		} catch (Exception e) {
			logger.error("Error during selectOptionsAndSubmit: {}", e.getMessage());
			throw new RuntimeException("Failed to select options and submit user role");
		}
	}

	public void searchUserRole() {
		try {
			List<WebElement> listOfRoles = wait
					.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(USER_ROLE_SEARCH));

			for (WebElement role : listOfRoles) {
				if (role.getText().trim().equals("DEMO")) {
					WebElement searchBox = wait.until(ExpectedConditions.elementToBeClickable(SEARCH_FIELD));
					searchBox.sendKeys(role.getText().trim());
					searchBox.sendKeys(Keys.ENTER);
					logger.info("Searched for user role: DEMO");
					break;
				}
			}
		} catch (Exception e) {
			logger.error("Error searching user role: {}", e.getMessage());
		}
	}

	public void updateUserRole() {
		try {
			Thread.sleep(2000);
			JavascriptExecutor js = (JavascriptExecutor) driver;

			WebElement updateBtn = driver.findElement(EYE_ICON);
			updateBtn.click();
			logger.info("Clicked on update (eye) icon");

			Thread.sleep(2000);

			WebElement addUser = wait.until(ExpectedConditions.visibilityOfElementLocated(ROLE_NAME));
			addUser.sendKeys("SURAJ");
			logger.info("Appended name with 'SURAJ'");

			selectMatOption(ROLE_TYPE, "External");
			logger.info("Updated Role Type to External");

			selectMatOption(ROLE_GRP, "QA");
			logger.info("Updated Role Group to QA");

			wait.until(ExpectedConditions.elementToBeClickable(SELECT_ALL)).click();
			wait.until(ExpectedConditions.elementToBeClickable(VIEW)).click();
			logger.info("Adjusted permissions");

			WebElement updateButton = wait.until(ExpectedConditions.elementToBeClickable(UPDATE_BTN));
			try {
				updateButton.click();
			} catch (org.openqa.selenium.ElementClickInterceptedException e) {
				js.executeScript("arguments[0].click();", updateButton);
				logger.warn("Update button click intercepted; using JS click.");
			}
			logger.info("Updated user role");

		} catch (Exception e) {
			logger.error("Error updating user role: {}", e.getMessage());
		}
	}

	public void deleteUserRole() {
		try {
			driver.navigate().back();
			logger.info("Navigated back before deletion");

			searchUserRole();
			Thread.sleep(500);

			WebElement delIcon = wait.until(ExpectedConditions.elementToBeClickable(DELETE_ICON));
			delIcon.click();

			WebDriverWait alertWait = new WebDriverWait(driver, Duration.ofSeconds(5));
			Alert alert = alertWait.until(ExpectedConditions.alertIsPresent());
			alert.dismiss();
			logger.info("Cancelled delete action via alert dismiss");

			delIcon = wait.until(ExpectedConditions.elementToBeClickable(DELETE_ICON));
			delIcon.click();
			alert = alertWait.until(ExpectedConditions.alertIsPresent());
			alert.accept();
			logger.info("Confirmed delete action via alert accept");

			Thread.sleep(1000);
			List<WebElement> toasts = driver.findElements(TOAST_MESSAGE);
			if (toasts.isEmpty()) {
				logger.warn("No toast message appeared after delete action");
			} else {
				for (WebElement toast : toasts) {
					if (toast.isDisplayed()) {
						String text = toast.getText();
						logger.info("Toast message: {}", text);
						if (text.contains("Internal Server Error")) {
							logger.error("Deletion failed: Internal Server Error");
						} else {
							logger.info("User role deleted successfully");
						}
						break;
					}
				}
			}
		} catch (TimeoutException e) {
			logger.error("Timeout while waiting for alert or toast: {}", e.getMessage());
		} catch (NoAlertPresentException e) {
			logger.warn("Expected alert was not present: {}", e.getMessage());
		} catch (Exception e) {
			logger.error("Unexpected error during user role deletion: {}", e.getMessage());
		}
	}

	private void selectMatOption(By dropdownLocator, String optionText) {
		try {
			WebElement dropdown = wait.until(ExpectedConditions.elementToBeClickable(dropdownLocator));
			dropdown.click();

			List<WebElement> options = wait
					.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.xpath("//mat-option")));

			for (WebElement option : options) {
				if (option.getText().trim().equals(optionText)) {
					option.click();
					logger.info("Selected option '{}' from dropdown", optionText);
					break;
				}
			}
		} catch (Exception e) {
			logger.error("Error selecting option '{}': {}", optionText, e.getMessage());
		}
	}
}
