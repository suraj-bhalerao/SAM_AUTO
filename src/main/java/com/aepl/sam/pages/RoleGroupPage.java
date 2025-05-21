package com.aepl.sam.pages;

import java.util.List;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.aepl.sam.actions.MouseActions;
import com.aepl.sam.locators.RoleGroupPageLocators;

public class RoleGroupPage extends RoleGroupPageLocators {
	private WebDriver driver;
	private WebDriverWait wait;

	public RoleGroupPage(WebDriver driver, WebDriverWait wait) {
		this.driver = driver;
		this.wait = wait;
	}

	public String navBarLink() {
		try {
			WebElement device_utils = wait.until(ExpectedConditions.visibilityOfElementLocated(USER));
			device_utils.click();
			
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
			Thread.sleep(1000);

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

			Thread.sleep(1000);

			refreshBtn.click();

			WebElement page_title = wait.until(ExpectedConditions.visibilityOfElementLocated(PAGE_TITLE));
			String pageTitle = page_title.getText();
			return pageTitle;

		} catch (Exception e) {
			e.getLocalizedMessage();
		}
		return "No Data Found!!!";
	}

	public void addUserRole() {
		WebElement addUserRole = driver.findElement(ADD_ROLE_GRP);
		addUserRole.click();

		WebElement roleName = driver.findElement(ROLE_GRP_NAME);
		roleName.sendKeys("AEP");

		WebElement submitBtn = driver.findElement(SUBMIT_BTN);
		submitBtn.click();

		backButton();
	}

	public void searchRoleGroup() {
		WebElement search;
		List<WebElement> roleList;

		try {
			search = driver.findElement(SEARCH_FIELD);

			roleList = driver.findElements(ROLE_TABLE);

			if (roleList.isEmpty()) {
				System.out.println("No roles found.");
				return;
			}

			System.out.println("Starting role group search...");

			for (int i = 0; i < roleList.size(); i++) {
				try {
					WebElement role = roleList.get(i); 
					String roleText = role.getText().trim();

					System.out.println("Searching for role: " + roleText);

					search.clear();
					wait.until(ExpectedConditions.elementToBeClickable(search)).sendKeys(roleText);
					search.sendKeys(Keys.ENTER);
					Thread.sleep(500);

					roleList = driver.findElements(ROLE_TABLE); // Refresh the role list
					boolean roleFound = roleList.stream().anyMatch(r -> r.getText().trim().equals(roleText));

					if (roleFound) {
						System.out.println("Role found: " + roleText);
					} else {
						System.out.println("Role not found: " + roleText);
					}

					search.clear();
					Thread.sleep(500);

				} catch (Exception e) {
					System.err.println("Error processing role at index " + i + ": " + e.getMessage());
				}
			}
			System.out.println("Role group search completed successfully.");
		} catch (Exception e) {
			System.err.println("An error occurred during role group search: " + e.getMessage());
		}
	}

	public boolean isRoleGroupFound(String roleName) {
		try {
			List<WebElement> roleList = driver.findElements(ROLE_TABLE);

			for (WebElement role : roleList) {
				if (role.getText().trim().equalsIgnoreCase(roleName)) {
					System.out.println("Role group found: " + roleName);
					return true;
				}
			}

			System.out.println("Role group not found: " + roleName);
			return false;

		} catch (Exception e) {
			System.err.println("Error while checking if role group is found: " + e.getMessage());
			return false;
		}
	}

}
