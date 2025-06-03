package com.aepl.sam.pages;

import java.time.Duration;
import java.util.List;

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

	public RoleManagementPage(WebDriver driver, WebDriverWait wait) {
		this.driver = driver;
		this.wait = wait;
	}

	public String navBarLink() {
		try {
			WebElement user = wait.until(ExpectedConditions.visibilityOfElementLocated(USER));
			user.click();

			WebElement userRole = wait.until(ExpectedConditions.visibilityOfElementLocated(USER_ROLE_LINK));
			userRole.click();
		} catch (Exception e) {
			e.getLocalizedMessage();
		}
		return driver.getCurrentUrl();
	}

	// check the back button
	public String backButton() {
		try {
			WebElement element = wait.until(ExpectedConditions.visibilityOfElementLocated(BACK_BUTTON));

			JavascriptExecutor js = (JavascriptExecutor) driver;
			js.executeScript("arguments[0].style.border = 'red'", element);

			element.click();
			Thread.sleep(10);

			System.out.println("Clicked on back button : " + element.getText());

		} catch (Exception e) {
			e.getLocalizedMessage();
		}
		// calling again to visit that page.
		return navBarLink();
	}

	// check the refresh button
	public String refreshButton() {
		try {
			WebElement refreshBtn = wait.until(ExpectedConditions.elementToBeClickable(REFRESH_BUTTON));

			JavascriptExecutor js = (JavascriptExecutor) driver;
			js.executeScript("arguments[0].style.border = 'red'", refreshBtn);

			Thread.sleep(20);

			refreshBtn.click();

			WebElement page_title = wait.until(ExpectedConditions.visibilityOfElementLocated(PAGE_TITLE));
			String pageTitle = page_title.getText();
			return pageTitle;

		} catch (Exception e) {
			e.getLocalizedMessage();
		}
		return "No Data Found!!!";
	}

	public String clickAddUserRoleBtn() {
		try {
			WebElement addUserRole = wait.until(ExpectedConditions.visibilityOfElementLocated(ADD_USER));
			addUserRole.click();

			// Wait for the heading or form to appear after clicking
			WebElement header = wait.until(ExpectedConditions.visibilityOfElementLocated(PAGE_TITLE));
			return header.getText();

		} catch (Exception e) {
			e.printStackTrace();
			return "Error: Unable to click Add User Role";
		}
	}

	public void selectingOptions() {
		WebElement addUser = wait.until(ExpectedConditions.visibilityOfElementLocated(ROLE_NAME));
		addUser.sendKeys("DEMO");

		selectMatOption(ROLE_TYPE, "User");

		selectMatOption(ROLE_GRP, "QA");
	}

	public void selectOptionsAndSubmit() {
		try {
			JavascriptExecutor js = (JavascriptExecutor) driver;

			WebElement selectAllCheckBox = wait.until(ExpectedConditions.elementToBeClickable(SELECT_ALL));
			selectAllCheckBox.click();

			WebElement submitBtn = wait.until(ExpectedConditions.elementToBeClickable(SUBMIT_BTN));
			js.executeScript("arguments[0].scrollIntoView({block: 'center'});", submitBtn);

			try {
				submitBtn.click();
			} catch (org.openqa.selenium.ElementClickInterceptedException e) {
				js.executeScript("arguments[0].click();", submitBtn);
			}

			backButton();
		} catch (Exception e) {
			System.err.println("Error during selectOptionsAndSubmit: " + e.getMessage());
			e.printStackTrace();
			throw new RuntimeException("Failed to select options and submit user role");
		}
	}

	public void searchUserRole() {
		try {
			List<WebElement> listOfRoles = wait
					.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(USER_ROLE_SEARCH));

			for (WebElement role : listOfRoles) {
				if (role.getText().trim().equals("DEMO")) {
					Thread.sleep(2000);
					WebElement searchBox = wait.until(ExpectedConditions.elementToBeClickable(SEARCH_FIELD));
					searchBox.sendKeys(role.getText().trim());
					searchBox.sendKeys(Keys.ENTER);
					break;
				}
			}
		} catch (Exception e) {
			e.getMessage();
		}
	}

	public void updateUserRole() {
		try {
			Thread.sleep(2000);
			JavascriptExecutor js = (JavascriptExecutor) driver;

			WebElement updateBtn = driver.findElement(EYE_ICON);
			updateBtn.click();

			Thread.sleep(2000);

			WebElement addUser = wait.until(ExpectedConditions.visibilityOfElementLocated(ROLE_NAME));
			addUser.sendKeys("SURAJ");

			selectMatOption(ROLE_TYPE, "External");

			selectMatOption(ROLE_GRP, "QA");

			WebElement selectAllCheckBox = wait.until(ExpectedConditions.elementToBeClickable(SELECT_ALL));
			selectAllCheckBox.click();
			WebElement viewCheckBox = wait.until(ExpectedConditions.elementToBeClickable(VIEW));
			viewCheckBox.click();

			WebElement updateButton = wait.until(ExpectedConditions.elementToBeClickable(UPDATE_BTN));

			try {
				updateButton.click();
			} catch (org.openqa.selenium.ElementClickInterceptedException e) {
				js.executeScript("arguments[0].click();", updateButton);
			}

		} catch (Exception e) {
			e.getMessage();
		}
	}

	public void deleteUserRole() {
		backButton();

		WebElement delIcon = wait.until(ExpectedConditions.elementToBeClickable(DELETE_ICON));
		delIcon.click();

		try {
			WebDriverWait alertWait = new WebDriverWait(driver, Duration.ofSeconds(5));
			Alert alert = alertWait.until(ExpectedConditions.alertIsPresent());

			alert.dismiss();

			delIcon = wait.until(ExpectedConditions.elementToBeClickable(DELETE_ICON));
			delIcon.click();

			alert = alertWait.until(ExpectedConditions.alertIsPresent());
			alert.accept();
		} catch (NoAlertPresentException | TimeoutException e) {
			System.out.println("No alert found: " + e.getMessage());
		}
	}

	// helper method
	private void selectMatOption(By dropdownLocator, String optionText) {
		WebElement dropdown = wait.until(ExpectedConditions.elementToBeClickable(dropdownLocator));
		dropdown.click();

		List<WebElement> options = wait
				.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.xpath("//mat-option")));

		for (WebElement option : options) {
			if (option.getText().trim().equals(optionText)) {
				option.click();
				break;
			}
		}
	}

	// Pagination is pending here add it later
}
