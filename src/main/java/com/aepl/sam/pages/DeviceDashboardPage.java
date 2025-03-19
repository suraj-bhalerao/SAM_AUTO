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
	public void verifyWebpageLogo() {
		// Wait for the logo element to be visible
		WebElement logo = wait.until(ExpectedConditions.visibilityOfElementLocated(PAGE_LOGO));

<<<<<<< HEAD
=======
		// Verify if the logo is displayed
		if (logo.isDisplayed()) {
//		        System.out.println("Webpage logo is visible.");
		} else {
			throw new RuntimeException("Webpage logo is not visible.");
		}
	}

>>>>>>> f4575472028490262b133b85d71691d53979a381

	/*
	 * public void verifyWebpageLogo() { // Wait for the logo element to be visible
	 * WebElement logo =
	 * wait.until(ExpectedConditions.visibilityOfElementLocated(PAGE_LOGO));
	 * 
	 * // Verify if the logo is displayed if (logo.isDisplayed()) { //
	 * System.out.println("Webpage logo is visible."); } else { throw new
	 * RuntimeException("Webpage logo is not visible."); } }
	 * 

	 * /* public void verifyWebpageLogo() { // Wait for the logo element to be
	 * visible WebElement logo =
	 * wait.until(ExpectedConditions.visibilityOfElementLocated(PAGE_LOGO));
	 * 
	 * // Verify if the logo is displayed if (logo.isDisplayed()) { //
	 * System.out.println("Webpage logo is visible."); } else { throw new
	 * RuntimeException("Webpage logo is not visible."); } }
	 * 

<<<<<<< HEAD

	/*
	 * public void verifyWebpageLogo() { // Wait for the logo element to be visible
	 * WebElement logo =
	 * wait.until(ExpectedConditions.visibilityOfElementLocated(PAGE_LOGO));
	 * 
	 * // Verify if the logo is displayed if (logo.isDisplayed()) { //
	 * System.out.println("Webpage logo is visible."); } else { throw new
	 * RuntimeException("Webpage logo is not visible."); } }
	 * 
=======
>>>>>>> f4575472028490262b133b85d71691d53979a381
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


<<<<<<< HEAD

=======
>>>>>>> f4575472028490262b133b85d71691d53979a381
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


	public String verifyAndClickKPITotalProDevWithCount() {
		String expectedTitle = "TOTAL PRODUCTION DEVICES";
		JavascriptExecutor js = (JavascriptExecutor) driver;

		try {
			// Wait for the KPI Title element to be visible
			WebElement titleElement = wait
					.until(ExpectedConditions.visibilityOfElementLocated(DEVICE_DASHBOARD_TOTALPRODUCTIONDEVICESKPI));
			WebElement countElement = wait.until(
					ExpectedConditions.visibilityOfElementLocated(DEVICE_DASHBOARD_TOTALPRODUCTIONDEVICESKPICOUNT));
			WebElement tableElement = wait
					.until(ExpectedConditions.visibilityOfElementLocated(DEVICEDASHBOARDKPITABLE));

			// Highlight KPI Title and Count elements
			js.executeScript("arguments[0].style.border='3px solid purple'", titleElement);
			js.executeScript("arguments[0].style.border='3px solid blue'", countElement);
			js.executeScript("arguments[0].style.border='3px solid blue'", tableElement);

			// Click on the KPI title element
			titleElement.click();
			System.out.println("✅ Clicked on the KPI element.");

			// Extract text from the KPI title
			String actualTitle = titleElement.getText().trim();
			System.out.println("🔹 Extracted KPI Title: " + actualTitle);

			// Extract KPI count
			String actualCount = countElement.getText().trim();
			System.out.println("🔹 Extracted KPI Count: " + actualCount);

			// Verify KPI Title
			if (!actualTitle.equalsIgnoreCase(expectedTitle)) {
				throw new AssertionError("❌ Page title does not match. Expected: '" + expectedTitle + "', but found: '"
						+ actualTitle + "'");
			}

			tableElement.click();
			System.out.println("✅ Clicked on the KPI Table.");

			// Extract KPI table name
			String tablename = tableElement.getText().trim();
			System.out.println("🔹 Extracted KPI Table Name: " + tablename);

			System.out.println("✅ KPI Name is visible and matches: " + actualTitle);
			System.out.println("✅ KPI Count is visible and matches: " + actualCount);
			System.out.println("✅ KPI Table is visible and open: " + tablename);
			// Return combined KPI Title and Count
			return "KPI Title: " + actualTitle + ", KPI Count: " + actualCount + ",KPI Table: " + tablename;

		} catch (NoSuchElementException ne) {
			throw new RuntimeException("🚨 Element not found: " + DEVICE_DASHBOARD_TOTALPRODUCTIONDEVICESKPI, ne);
		} catch (Exception e) {
			throw new RuntimeException("❌ Unexpected error while verifying KPI title and count.", e);
		}
	}

	public String verifyAndClickKPITotalDisDevWithCount() {
		String expectedTitle = "TOTAL DISPATCHED DEVICES";
		JavascriptExecutor js = (JavascriptExecutor) driver;

		try {
			// Wait for the KPI Title element to be visible
			WebElement titleElement = wait
					.until(ExpectedConditions.visibilityOfElementLocated(DEVICE_DASHBOARD_TOTALDISPATCHEDDEVICESKPI));
			WebElement countElement = wait.until(
					ExpectedConditions.visibilityOfElementLocated(DEVICE_DASHBOARD_TOTALDISPATCHEDDEVICESKPICOUNT));
			WebElement tableElement = wait
					.until(ExpectedConditions.visibilityOfElementLocated(DEVICEDASHBOARDKPITABLE));

			// Highlight KPI Title and Count elements
			js.executeScript("arguments[0].style.border='3px solid purple'", titleElement);
			js.executeScript("arguments[0].style.border='3px solid blue'", countElement);
			js.executeScript("arguments[0].style.border='3px solid blue'", tableElement);

			// Click on the KPI title element
			titleElement.click();
			System.out.println("✅ Clicked on the KPI element.");
			// Extract text from the KPI title
			String actualTitle = titleElement.getText().trim();
			System.out.println("🔹 Extracted KPI Title: " + actualTitle);
			// Extract KPI count
			String actualCount = countElement.getText().trim();
			System.out.println("🔹 Extracted KPI Count: " + actualCount);
			// Verify KPI Title
			if (!actualTitle.equalsIgnoreCase(expectedTitle)) {
				throw new AssertionError("❌ Page title does not match. Expected: '" + expectedTitle + "', but found: '"
						+ actualTitle + "'");
			}

			// Extract KPI table name
			String tablename = tableElement.getText().trim();
			System.out.println("🔹 Extracted KPI Table Name: " + tablename);

			System.out.println("✅ KPI Name is visible and matches: " + actualTitle);
			System.out.println("✅ KPI Count is visible and matches: " + actualCount);
			System.out.println("✅ KPI Table is visible and open: " + tablename);
			// Return combined KPI Title and Count
			return "KPI Title: " + actualTitle + ", KPI Count: " + actualCount + ",KPI Table: " + tablename;
		} catch (NoSuchElementException ne) {
			throw new RuntimeException("🚨 Element not found: " + DEVICE_DASHBOARD_TOTALDISPATCHEDDEVICESKPI, ne);
		} catch (Exception e) {
			throw new RuntimeException("❌ Unexpected error while verifying KPI title and count.", e);
		}
	}

	public String verifyAndClickKPITotalInsDevWithCount() {

	    String expectedTitle = "TOTAL INSTALLED DEVICES";
	    JavascriptExecutor js = (JavascriptExecutor) driver;
	    try {
	        // Wait for the KPI Title element to be visible
	        WebElement titleElement = wait.until(ExpectedConditions.visibilityOfElementLocated(DEVICE_DASHBOARD_TOTALINSTALLEDDEVICESKPI));
	        WebElement countElement = wait.until(ExpectedConditions.visibilityOfElementLocated(DEVICE_DASHBOARD_TOTALINSTALLEDDEVICESKPICOUNT));
	        WebElement tableElement = wait.until(ExpectedConditions.visibilityOfElementLocated(DEVICEDASHBOARDKPITABLE));
	        
	        // Highlight KPI Title and Count elements
	        js.executeScript("arguments[0].style.border='3px solid purple'", titleElement);
	        js.executeScript("arguments[0].style.border='3px solid blue'", countElement);
	        js.executeScript("arguments[0].style.border='3px solid blue'", tableElement);
	        
	        // Click on the KPI title element
	        titleElement.click();
	        System.out.println("✅ Clicked on the KPI element.");
	        // Extract text from the KPI title
	        String actualTitle = titleElement.getText().trim();
	        System.out.println("🔹 Extracted KPI Title: " + actualTitle);
	        // Extract KPI count
	        String actualCount = countElement.getText().trim();
	        System.out.println("🔹 Extracted KPI Count: " + actualCount);
	        // Verify KPI Title
	        if (!actualTitle.equalsIgnoreCase(expectedTitle)) {
	            throw new AssertionError("❌ Page title does not match. Expected: '" + expectedTitle + "', but found: '" + actualTitle + "'");
	        }
	        
	     // Extract KPI table name
	        String  tablename = tableElement.getText().trim();
	        System.out.println("🔹 Extracted KPI Table Name: " + tablename);
	        
	        System.out.println("✅ KPI Name is visible and matches: " + actualTitle);
	        System.out.println("✅ KPI Count is visible and matches: " + actualCount);
	        System.out.println("✅ KPI Table is visible and open: " + tablename);
	        // Return combined KPI Title and Count
	        return "KPI Title: " + actualTitle + ", KPI Count: " + actualCount;
	    } catch (NoSuchElementException ne) {
	        throw new RuntimeException("🚨 Element not found: " + DEVICE_DASHBOARD_TOTALINSTALLEDDEVICESKPI, ne);
	    } catch (Exception e) {
	        throw new RuntimeException("❌ Unexpected error while verifying KPI title and count.", e);
	    }
<<<<<<< HEAD
=======

		String expectedTitle = "TOTAL INSTALLED DEVICES";
		JavascriptExecutor js = (JavascriptExecutor) driver;
		try {
			// Wait for the KPI Title element to be visible
			WebElement titleElement = wait
					.until(ExpectedConditions.visibilityOfElementLocated(DEVICE_DASHBOARD_TOTALINSTALLEDDEVICESKPI));
			WebElement countElement = wait.until(
					ExpectedConditions.visibilityOfElementLocated(DEVICE_DASHBOARD_TOTALINSTALLEDDEVICESKPICOUNT));

			// Highlight KPI Title and Count elements
			js.executeScript("arguments[0].style.border='3px solid purple'", titleElement);
			js.executeScript("arguments[0].style.border='3px solid blue'", countElement);
			// Click on the KPI title element
			titleElement.click();
			System.out.println("✅ Clicked on the KPI element.");
			// Extract text from the KPI title
			String actualTitle = titleElement.getText().trim();
			System.out.println("🔹 Extracted KPI Title: " + actualTitle);
			// Extract KPI count
			String actualCount = countElement.getText().trim();
			System.out.println("🔹 Extracted KPI Count: " + actualCount);
			// Verify KPI Title
			if (!actualTitle.equalsIgnoreCase(expectedTitle)) {
				throw new AssertionError("❌ Page title does not match. Expected: '" + expectedTitle + "', but found: '"
						+ actualTitle + "'");
			}
			System.out.println("✅ KPI Name is visible and matches: " + actualTitle);
			System.out.println("✅ KPI Count is visible and matches: " + actualCount);
			// Return combined KPI Title and Count
			return "KPI Title: " + actualTitle + ", KPI Count: " + actualCount;
		} catch (NoSuchElementException ne) {
			throw new RuntimeException("🚨 Element not found: " + DEVICE_DASHBOARD_TOTALINSTALLEDDEVICESKPI, ne);
		} catch (Exception e) {
			throw new RuntimeException("❌ Unexpected error while verifying KPI title and count.", e);
		}

>>>>>>> f4575472028490262b133b85d71691d53979a381
	}
	   
	public String verifyAndClickKPITotalDiscardDevWithCount() {
<<<<<<< HEAD
=======

>>>>>>> f4575472028490262b133b85d71691d53979a381
	    String expectedTitle = "TOTAL DISCARDED DEVICES";
	    JavascriptExecutor js = (JavascriptExecutor) driver;
	    try {
	        // Wait for the KPI Title element to be visible
	        WebElement titleElement = wait.until(ExpectedConditions.visibilityOfElementLocated(DEVICE_DASHBOARD_TOTALDISCARDEDDEVICESKPI));
	        WebElement countElement = wait.until(ExpectedConditions.visibilityOfElementLocated(DEVICE_DASHBOARD_TOTALDISCARDEDDEVICESKPICOUNT));
	        WebElement tableElement = wait.until(ExpectedConditions.visibilityOfElementLocated(DEVICEDASHBOARDKPITABLE));
	        
	        // Highlight KPI Title and Count elements
	        js.executeScript("arguments[0].style.border='3px solid purple'", titleElement);
	        js.executeScript("arguments[0].style.border='3px solid blue'", countElement);
	        js.executeScript("arguments[0].style.border='3px solid blue'", tableElement);
	        
	        // Click on the KPI title element
	        titleElement.click();
	        System.out.println("✅ Clicked on the KPI element.");
	        // Extract text from the KPI title
	        String actualTitle = titleElement.getText().trim();
	        System.out.println("🔹 Extracted KPI Title: " + actualTitle);
	        // Extract KPI count
	        String actualCount = countElement.getText().trim();
	        System.out.println("🔹 Extracted KPI Count: " + actualCount);
	        // Verify KPI Title
	        if (!actualTitle.equalsIgnoreCase(expectedTitle)) {
	            throw new AssertionError("❌ Page title does not match. Expected: '" + expectedTitle + "', but found: '" + actualTitle + "'");
	        }
	        
	     // Extract KPI table name
	        String  tablename = tableElement.getText().trim();
	        System.out.println("🔹 Extracted KPI Table Name: " + tablename);
	        
	        System.out.println("✅ KPI Name is visible and matches: " + actualTitle);
	        System.out.println("✅ KPI Count is visible and matches: " + actualCount);
	        System.out.println("✅ KPI Table is visible and open: " + tablename);
	        // Return combined KPI Title and Count
	        return "KPI Title: " + actualTitle + ", KPI Count: " + actualCount;
	    } catch (NoSuchElementException ne) {
	        throw new RuntimeException("🚨 Element not found: " + DEVICE_DASHBOARD_TOTALDISCARDEDDEVICESKPI, ne);
	    } catch (Exception e) {
	        throw new RuntimeException("❌ Unexpected error while verifying KPI title and count.", e);
	    }
	}

	public void clicSearchBox() {
		// Wait for the navigation bar links to be visible
		JavascriptExecutor js = (JavascriptExecutor) driver;
		List<WebElement> navBarLinks = wait
				.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(DEVICE_DASHBOARD_SEARCHBOX));
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
			throw new RuntimeException("Failed to find and click in search box.");
<<<<<<< HEAD
=======

		String expectedTitle = "TOTAL DISCARDED DEVICES";
		JavascriptExecutor js = (JavascriptExecutor) driver;
		try {
			// Wait for the KPI Title element to be visible
			WebElement titleElement = wait
					.until(ExpectedConditions.visibilityOfElementLocated(DEVICE_DASHBOARD_TOTALDISCARDEDDEVICESKPI));
			WebElement countElement = wait.until(
					ExpectedConditions.visibilityOfElementLocated(DEVICE_DASHBOARD_TOTALDISCARDEDDEVICESKPICOUNT));

			// Highlight KPI Title and Count elements
			js.executeScript("arguments[0].style.border='3px solid purple'", titleElement);
			js.executeScript("arguments[0].style.border='3px solid blue'", countElement);
			// Click on the KPI title element
			titleElement.click();
			System.out.println("✅ Clicked on the KPI element.");
			// Extract text from the KPI title
			String actualTitle = titleElement.getText().trim();
			System.out.println("🔹 Extracted KPI Title: " + actualTitle);
			// Extract KPI count
			String actualCount = countElement.getText().trim();
			System.out.println("🔹 Extracted KPI Count: " + actualCount);
			// Verify KPI Title
			if (!actualTitle.equalsIgnoreCase(expectedTitle)) {
				throw new AssertionError("❌ Page title does not match. Expected: '" + expectedTitle + "', but found: '"
						+ actualTitle + "'");
			}
			System.out.println("✅ KPI Name is visible and matches: " + actualTitle);
			System.out.println("✅ KPI Count is visible and matches: " + actualCount);
			// Return combined KPI Title and Count
			return "KPI Title: " + actualTitle + ", KPI Count: " + actualCount;
		} catch (NoSuchElementException ne) {
			throw new RuntimeException("🚨 Element not found: " + DEVICE_DASHBOARD_TOTALDISCARDEDDEVICESKPI, ne);
		} catch (Exception e) {
			throw new RuntimeException("❌ Unexpected error while verifying KPI title and count.", e);
>>>>>>> f4575472028490262b133b85d71691d53979a381
		}
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

	public String ClickedKPITotalProdDevTable() {
		String expectedTitle = "Total Production Devices";
		JavascriptExecutor js = (JavascriptExecutor) driver;
		try {
			// Wait for the KPI Title element to be visible
			WebElement titleElement = wait
					.until(ExpectedConditions.visibilityOfElementLocated(DEVICE_DASHBOARD_TOTALPRODUCTIONDEVICESKPI));

			WebElement countElement = wait
					.until(ExpectedConditions.visibilityOfElementLocated(DEVICE_DASHBOARD_TOTALDISPATCHEDDEVICESKPICOUNT));
			WebElement tableElement = wait
					.until(ExpectedConditions.visibilityOfElementLocated(DEVICEDASHBOARDKPITABLE));

			// Highlight KPI Title and Count elements
//	        js.executeScript("arguments[0].style.border='3px solid purple'", titleElement);
			js.executeScript("arguments[0].style.border='3px solid blue'", countElement);
			// Click on the KPI title element
			titleElement.click();
			System.out.println("✅ Clicked on the KPI element.");
			// Extract text from the KPI title
			String actualTitle = titleElement.getText().trim();
			System.out.println("🔹 Extracted KPI Title: " + actualTitle);
			// Extract KPI count
			String actualCount = countElement.getText().trim();
			System.out.println("🔹 Extracted KPI Count: " + actualCount);
			// Verify KPI Title
			if (!actualTitle.equalsIgnoreCase(expectedTitle)) {
				throw new AssertionError("❌ Page title does not match. Expected: '" + expectedTitle + "', but found: '"
						+ actualTitle + "'");
			}
			System.out.println("✅ KPI Name is visible and matches: " + actualTitle);
			System.out.println("✅ KPI Count is visible and matches: " + actualCount);
			// Return combined KPI Title and Count
			return "KPI Title: " + actualTitle + ", KPI Count: " + actualCount;
		} catch (NoSuchElementException ne) {

			throw new RuntimeException("🚨 Element not found: " + DEVICEDASHBOARDKPITABLE, ne);

		} catch (Exception e) {
			throw new RuntimeException("❌ Unexpected error while verifying KPI title and count.", e);

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
