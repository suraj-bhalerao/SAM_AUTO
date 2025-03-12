package com.aepl.sam.pages;

import java.util.List;
import java.util.NoSuchElementException;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.aepl.sam.locators.DeviceDashboardPageLocators;
import com.aepl.sam.utils.CommonMethods;

//import io.restassured.RestAssured;

public class DeviceDashboardPage extends DeviceDashboardPageLocators {

	private WebDriver driver;
	private WebDriverWait wait;
	private CommonMethods commonMethods;

	public DeviceDashboardPage(WebDriver driver, WebDriverWait wait) {
		this.driver = driver;
		this.wait = wait;
		this.commonMethods = new CommonMethods(driver, wait);
	}

	public void clickNavBar() {
		// Wait for the navigation bar links to be visible
		JavascriptExecutor js = (JavascriptExecutor) driver;
		List<WebElement> navBarLinks = wait
				.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(DEVICE_DASHBOARD));
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
	/*
	 * public void verifyWebpageLogo() { // Wait for the logo element to be visible
	 * WebElement logo =
	 * wait.until(ExpectedConditions.visibilityOfElementLocated(PAGE_LOGO));
	 * 
	 * // Verify if the logo is displayed if (logo.isDisplayed()) { //
	 * System.out.println("Webpage logo is visible."); } else { throw new
	 * RuntimeException("Webpage logo is not visible."); } }
	 * 
	 * public String verifyPageTitle() { String expectedTitle =
	 * "AEPL Sampark_Diet Diagnostic Cloud"; // Wait for the title element to be
	 * visible WebElement titleElement =
	 * wait.until(ExpectedConditions.visibilityOfElementLocated(PAGETITLE)); //
	 * Extract the text of the title element String actualTitle =
	 * titleElement.getText().trim(); // Verify if the title matches the expected
	 * title if (actualTitle.equalsIgnoreCase(expectedTitle)) {
	 * System.out.println("Page title is visible and matches: " + actualTitle); }
	 * else { throw new RuntimeException( "Page title does not match. Expected: " +
	 * expectedTitle + ", but found: " + actualTitle); } return actualTitle; }
	 * 
	 * public void clickRefreshButton() { try { // Wait for the refresh button to be
	 * visible and clickable WebElement refreshButton =
	 * wait.until(ExpectedConditions.elementToBeClickable(
	 * DEVICE_DASHBOARD_REFRESHBTN)); // Click on the refresh button
	 * refreshButton.click(); Thread.sleep(5000); //
	 * System.out.println("Refresh button clicked successfully."); } catch
	 * (Exception e) { throw new
	 * RuntimeException("Failed to click on the refresh button.", e); } }
	 */

	public String verifyDashPageTitle() {
		String expectedTitle = "Device Dashboard";
		JavascriptExecutor js = (JavascriptExecutor) driver;
		// Wait for the title element to be visible
		WebElement titleElement = wait.until(ExpectedConditions.visibilityOfElementLocated(DEVICE_DASHBOARD_TITLE));
		js.executeScript("arguments[0].style.border='3px solid purple'", titleElement);
		// Extract the text of the title element
		String actualTitle = titleElement.getText().trim();
		// Verify if the title matches the expected title
		if (actualTitle.equalsIgnoreCase(expectedTitle)) {
			System.out.println("Page Name is visible and matches: " + actualTitle);
		} else {
			throw new RuntimeException(
					"Page title does not match. Expected: " + expectedTitle + ", but found: " + actualTitle);
		}
		return actualTitle;
	}

	public String verifyAndClickKPITotalProDev() {
		String expectedTitle = "TOTAL PRODUCTION DEVICES";
		JavascriptExecutor js = (JavascriptExecutor) driver;

		try {
			// Wait for the KPI element to be visible
			WebElement titleElement = wait
					.until(ExpectedConditions.visibilityOfElementLocated(DEVICE_DASHBOARD_TOTALPRODUCTIONDEVICESKPI));
			// Highlight the KPI element
			js.executeScript("arguments[0].style.border='3px solid purple'", titleElement);
			// Click on the KPI element
			titleElement.click();
			System.out.println("✅ Clicked on the KPI element.");

			// Extract text from the clicked KPI element
			String actualTitle = titleElement.getText();
			System.out.println("Extracted KPI Text: " + actualTitle);

			// Verify if the title matches the expected title
			if (actualTitle.equalsIgnoreCase(expectedTitle)) {
				System.out.println("✅ Page Name is visible and matches: " + actualTitle);
			} else {
				throw new AssertionError("❌ Page title does not match. Expected: '" + expectedTitle + "', but found: '"
						+ actualTitle + "'");
			}
			return actualTitle;
		} catch (Exception e) {
			throw new RuntimeException(
					"❌ Failed to locate the KPI element: " + DEVICE_DASHBOARD_TOTALPRODUCTIONDEVICESKPICOUNT, e);
		}
	}

	public String verifyCountKPITotalProDev() {

		JavascriptExecutor js = (JavascriptExecutor) driver;

		try {
			// Wait for the KPI element to be visible
			WebElement kpiElement = wait
					.until(ExpectedConditions.visibilityOfElementLocated(DEVICE_DASHBOARD_TOTALPRODUCTIONDEVICESKPI));

			// Highlight the KPI element
			js.executeScript("arguments[0].style.border='3px solid purple'", kpiElement);

			// Click on the KPI element
			kpiElement.click();
			System.out.println("✅ Clicked on the KPI element.");
			String expectedCount = kpiElement.getText().trim();
			// Extract text from the KPI element
			String actualCount = kpiElement.getText().trim();
			System.out.println("🔹 Extracted KPI Count: " + actualCount);

			// Verify KPI count with expected value
			if (actualCount.equalsIgnoreCase(expectedCount)) {
				System.out.println("✅ KPI count matches expected value: " + actualCount);
			} else {
				throw new AssertionError(
						"❌ KPI count mismatch! Expected: '" + expectedCount + "', but found: '" + actualCount + "'");
			}

			return actualCount;
		} catch (NoSuchElementException ne) {
			throw new RuntimeException("🚨 Element not found: " + DEVICE_DASHBOARD_TOTALPRODUCTIONDEVICESKPI, ne);
		} catch (Exception e) {
			throw new RuntimeException("❌ Unexpected error while verifying KPI count.", e);
		}
	}

}

//		public void deviceDetails() {
//			Map<String, Map<String, List<String>>> deviceDetails = new HashMap<>();
//			
//			deviceDetails.put("Device Details", new Map<"DevDet_KPI", Arrays.asList(null)>());
//		}
