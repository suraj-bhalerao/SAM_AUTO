package com.aepl.sam.utils;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.JavascriptException;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.aepl.sam.locators.CommonPageLocators;

public class CommonMethods extends CommonPageLocators {
	// Fields
	public WebDriver driver;
	private WebDriverWait wait;

	// Constructor
	public CommonMethods(WebDriver driver, WebDriverWait wait) {
		this.driver = driver;
		this.wait = wait;
	}

	// Methods
	public void captureScreenshot(String testCaseName) {
		if (driver == null) {
			throw new RuntimeException("WebDriver initialization failed in: Commonmethods");
		}

		String timestamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
		String screenshotName = testCaseName + "_" + timestamp + ".png";
		String screenshotPath = "screenshots/" + screenshotName;

		try {
			File screenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
			FileUtils.copyFile(screenshot, new File(screenshotPath));
		} catch (IOException e) {
			System.err.println("Error " + e);
		}
	}

	public boolean verifyWebpageLogo() {
		JavascriptExecutor js = (JavascriptExecutor) driver;
		// Wait for the logo element to be visible
		WebElement logo = wait.until(ExpectedConditions.visibilityOfElementLocated(ORG_LOGO));
		js.executeScript("arguments[0].style.border='3px solid purple'", logo);

		// Verify if the logo is displayed
		if (logo.isDisplayed()) {
			return true;
		} else {
			throw new RuntimeException("Webpage logo is not visible.");
		}
	}

	// Correction here this is not page title it is project title
	public String verifyPageTitle() {
		String expectedTitle = "AEPL Sampark_Diet Diagnostic Cloud";
		JavascriptExecutor js = (JavascriptExecutor) driver;
		// Wait for the title element to be visible
		WebElement titleElement = wait.until(ExpectedConditions.visibilityOfElementLocated(PROJECT_TITLE));
		js.executeScript("arguments[0].style.border='3px solid purple'", titleElement);
		// Extract the text of the title element
		String actualTitle = titleElement.getText();
		// Verify if the title matches the expected title
		if (actualTitle.equalsIgnoreCase(expectedTitle)) {
			System.out.println("Page title is visible and matches: " + actualTitle);
		} else {
			throw new RuntimeException(
					"Page title does not match. Expected: " + expectedTitle + ", but found: " + actualTitle);
		}

		return actualTitle;
	}

	public void clickRefreshButton() {
		try {
			JavascriptExecutor js = (JavascriptExecutor) driver;
			// Wait for the refresh button to be visible and clickable
			WebElement refreshButton = wait.until(ExpectedConditions.elementToBeClickable(REFRESH_BUTTON));
			js.executeScript("arguments[0].style.border='3px solid purple'", refreshButton);
			// Click on the refresh button
			refreshButton.click();
			Thread.sleep(3000);
		} catch (Exception e) {
			throw new RuntimeException("Failed to click on the refresh button.", e);
		}
	}

	public void clickNavBarDash() {
		// Wait for the navigation bar links to be visible
		JavascriptExecutor js = (JavascriptExecutor) driver;
		List<WebElement> navBarLinks = wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(DASHBOARD));
		js.executeScript("arguments[0].style.border='3px solid purple'", navBarLinks);
		boolean isClicked = false;
		for (WebElement link : navBarLinks) {
			if (link.getText().equalsIgnoreCase("Dashboard")) {
				link.click();
//					System.out.println("Clicked On Element On Nav: " +link.getAccessibleName());
				isClicked = true;
//					break;
			}
		}
		if (!isClicked) {
			throw new RuntimeException("Failed to find and click on 'Dashboard' in the navigation bar.");
		}
	}

	public void clickNavBar() {
		try {
			// Wait for the navigation bar links to be visible
			List<WebElement> navBarLinks = wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(DASHBOARD));
			// Debugging - Print total elements found
//	        System.out.println("Total navigation links found: " + navBarLinks.size());
			if (navBarLinks.isEmpty()) {
				navBarLinks.get(0).click();
				throw new RuntimeException("No navigation bar links found for 'Dashboard'.");
			}

			JavascriptExecutor js = (JavascriptExecutor) driver;
			boolean isClicked = false;

			for (WebElement link : navBarLinks) {
				// Highlight each element separately
				js.executeScript("arguments[0].style.border='3px solid green'", link);

				if (link.getText().trim().equalsIgnoreCase("Dashboard")) {
					System.out.println("Clicked On Element On Nav: " + link.getAccessibleName());
					js.executeScript("arguments[0].click();", link); // JavaScript Click (more reliable)
					isClicked = true;
					break; // Stop loop once clicked
				}
			}
			if (!isClicked) {
				throw new RuntimeException("Failed to find and click on 'Dashboard' in the navigation bar.");
			}
		} catch (StaleElementReferenceException e) {
			throw new RuntimeException("Element went stale. Try re-fetching before clicking.", e);
		} catch (JavascriptException e) {
			throw new RuntimeException("JavaScript execution failed. Element might be undefined.", e);
		}
	}

	public boolean clickNavBarDeviceUtil() {
	    try {
	        JavascriptExecutor js = (JavascriptExecutor) driver;
	        List<WebElement> navBarLinks = wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(DEVICE_UTILITY));

	        for (WebElement link : navBarLinks) {
	            js.executeScript("arguments[0].style.border='3px solid purple'", link);

	            if (link.getText().trim().equalsIgnoreCase("Device Utility")) {
	                try {
	                    link.click();
	                } catch (Exception e) {
	                    js.executeScript("arguments[0].click();", link);
	                }
	                System.out.println("Clicked on element in Nav: " + link.getAccessibleName());
	                return true;
	            }
	        }

	        System.out.println("Failed to find and click on 'Device Utility' in the navigation bar.");
	        return false;
	    } catch (Exception e) {
	        System.out.println("Exception occurred while clicking 'Device Utility' in the navigation bar: " + e.getMessage());
	        return false;
	    }
	}


	public boolean clickNavBarUser() {
	    try {
	        // Wait for the navigation bar links to be visible
	        JavascriptExecutor js = (JavascriptExecutor) driver;
	        List<WebElement> navBarLinks = wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(USER));

	        for (WebElement link : navBarLinks) {
	            // Highlight the element (optional, for debugging)
	            js.executeScript("arguments[0].style.border='3px solid purple'", link);

	            // Check if the link text matches "User" (case-insensitive)
	            if (link.getText().equalsIgnoreCase("User")) {
	                try {
	                    link.click();
	                    System.out.println("Clicked on element in NavBar: " + link.getAccessibleName());
	                    return true; // Return true if successfully clicked
	                } catch (Exception e) {
	                    System.err.println("Error clicking on 'User' in NavBar: " + e.getMessage());
	                    return false;
	                }
	            }
	        }

	        // If "User" link not found
	        System.err.println("Failed to find 'User' in the navigation bar.");
	        return false;

	    } catch (Exception e) {
	        System.err.println("Error while interacting with the navigation bar: " + e.getMessage());
	        return false;
	    }
	}


	public void clickNavBarUserPro() {
		// Wait for the navigation bar links to be visible
		JavascriptExecutor js = (JavascriptExecutor) driver;
		List<WebElement> navBarLinks = wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(USER_PROFILE));
		boolean isClicked = false;
		for (WebElement link : navBarLinks) {
			js.executeScript("arguments[0].style.border='3px solid purple'", link);

			if (link.getText().equalsIgnoreCase("Hi, Super Ad")) {

				System.out.println("Clicked On Element On Nav: " + link.getAccessibleName());
//				link.click();
//					System.out.println("Clicked On Element On Nav: " +link.getAccessibleName());
				isClicked = true;
//					break;
			}
		}
		if (!isClicked) {
			throw new RuntimeException("Failed to find and click on 'User Profile' in the navigation bar.");
		}
	}

	// Footer Section From Here
	public String checkCopyright() {
		try {
			JavascriptExecutor js = (JavascriptExecutor) driver;
			Thread.sleep(2000);
			WebElement copyRight = driver.findElement(COPYRIGHT);
			js.executeScript("arguments[0].style.border='3px solid purple'", copyRight);
			return copyRight.getText();
		} catch (Exception e) {
			e.getMessage();
		}
		return "No copyright section was found!!!";
	}

	public String checkVersion() {
		try {
			JavascriptExecutor js = (JavascriptExecutor) driver;
			Thread.sleep(2000);
			WebElement version = driver.findElement(VERSION);
			js.executeScript("arguments[0].style.border='3px solid purple'", version);
			return version.getText();
		} catch (Exception e) {
			e.getMessage();
		}
		return "No version was found on page!!!";
	}
}
