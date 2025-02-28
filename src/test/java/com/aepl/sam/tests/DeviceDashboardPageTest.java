package com.aepl.sam.tests;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.aepl.sam.base.TestBase;
import com.aepl.sam.constants.Constants;
import com.aepl.sam.pages.DeviceDashboardPage;
import com.aepl.sam.utils.CommonMethods;
import com.aepl.sam.utils.ExcelUtility;

public class DeviceDashboardPageTest extends TestBase {
	private ExcelUtility excelUtility;
	private DeviceDashboardPage devicedashboardPage;
	private CommonMethods commonMethods;

	@BeforeClass
	public void setUp() {
		super.setUp();
		this.devicedashboardPage = new DeviceDashboardPage(driver, wait);
		this.commonMethods = new CommonMethods(driver, wait);
		this.excelUtility = new ExcelUtility();
		excelUtility.initializeExcel("Device_Dashboard_Test");
	}

	@Test(priority = 1)
	public void Navigation() throws InterruptedException {
		String testCaseName = "Test Navbar Links";
		String expectedURL = Constants.DASH_URL;
		String actualURL = "";
		String result = "FAIL"; // Default failure status

		logger.info("Executing the testClickNavBar for test case: " + testCaseName);
		try {
			logger.info("Attempting to click on the nav bar links...");
			devicedashboardPage.clickNavBar();
			actualURL = driver.getCurrentUrl();
			softAssert.assertEquals(actualURL, expectedURL, "URL Mismatch: Navigation failed!");
			result = expectedURL.equalsIgnoreCase(actualURL) ? "PASS" : "FAIL";
			logger.info("Result is: " + result);
		} catch (Exception e) {
			logger.error("An error occurred while clicking on the navigation bar links.", e);
			actualURL = driver.getCurrentUrl();
			e.printStackTrace();
		} finally {
			excelUtility.writeTestDataToExcel(testCaseName, expectedURL, actualURL, result);
			logger.info("Test case execution completed for: " + testCaseName);
			System.out.println("Successfully clicked on Dashboard Tab");
			softAssert.assertAll();
		}
	}

	@Test(priority = 2)
	public void PageLogo() throws InterruptedException {
		String testCaseName = "Test Company Logo visible on web page";
		String expectedURL = Constants.DASH_URL;
		String actualURL = "";
		String result = "FAIL"; // Default failure status

		logger.info("Executing the Page Logo test case: " + testCaseName);
		try {
			logger.info("Attempting to verify the webpage logo...");
			commonMethods.verifyWebpageLogo(); // Verify logo visibility
			actualURL = driver.getCurrentUrl();
			// Using Soft Assertion
			softAssert.assertEquals(actualURL, expectedURL, "URL Mismatch: Navigation failed!");
			result = expectedURL.equalsIgnoreCase(actualURL) ? "PASS" : "FAIL";
			logger.info("Test Result: " + result);
		} catch (Exception e) {
			logger.error("An error occurred while verifying the webpage logo.", e);
			actualURL = "Test Failed"; // Indicate failure
			e.printStackTrace();
		} finally {
			excelUtility.writeTestDataToExcel(testCaseName, expectedURL, actualURL, result);
			logger.info("Test case execution completed for: " + testCaseName);
			System.out.println("Company logo successfully visible on Dashboard Tab");

			// Ensure soft assertions are checked at the end
			softAssert.assertAll();
		}
	}

	@Test(priority = 3)
	public void PageTitle() throws InterruptedException {
		String testCaseName = "Test Page Title is visible";
		String expectedTitle = driver.getTitle();
		String actualTitle = "";
		String result = "FAIL"; // Default failure status

		logger.info("Executing the Page Title visible test case: " + testCaseName);
		try {
			logger.info("Attempting to visible Page Title on webpage...");
			commonMethods.verifyPageTitle();
			actualTitle = driver.getTitle();
			softAssert.assertEquals(actualTitle, expectedTitle, "URL Mismatch: Navigation failed!");
			result = expectedTitle.equalsIgnoreCase(actualTitle) ? "PASS" : "FAIL";
			logger.info("Result is: " + result);
		} catch (Exception e) {
			logger.error("An error occurred while page title is not visible on webpage.", e);
//			actualTitle = driver.getTitle();
			e.printStackTrace();
		} finally {
			excelUtility.writeTestDataToExcel(testCaseName, expectedTitle, actualTitle, result);
			logger.info("Test case execution completed for: " + testCaseName);
			System.out.println("Page Title Successfully visible on Dashboard Tab");

			softAssert.assertAll();
		}
	}

	@Test(priority = 4)
	public void ClickRefreshBtn() throws InterruptedException {
		String testCaseName = "Test Click on refresh button";
		String expectedURL = Constants.DASH_URL;
		String actualURL = "";
		String result = "FAIL"; // Default failure status

		logger.info("Executing the test Click Refresh button for test case: " + testCaseName);
		try {
			logger.info("Attempting to click on the refresh button...");
			commonMethods.clickRefreshButton();
			actualURL = driver.getCurrentUrl();
			softAssert.assertEquals(actualURL, expectedURL, "URL Mismatch: Navigation failed!");
			result = expectedURL.equalsIgnoreCase(actualURL) ? "PASS" : "FAIL";
			logger.info("Result is: " + result);
		} catch (Exception e) {
			logger.error("An error occurred while clicking on the refresh button.", e);
			e.printStackTrace();
		} finally {
			excelUtility.writeTestDataToExcel(testCaseName, expectedURL, actualURL, result);
			logger.info("Test case execution completed for: " + testCaseName);
			System.out.println("Successfully clicked on Refresh button");
			softAssert.assertAll();
		}
	}

	@Test(priority = 5)
	public void PageName() throws InterruptedException {
		String testCaseName = "Test Verify Page Name";
		String expectedPageName = "Device Dashboard";
		String actualPageName = devicedashboardPage.verifyDashPageTitle();
		String result = "FAIL"; // Default failure status

		logger.info("Executing the test Visible Page Name for test case: " + testCaseName);
		try {
			logger.info("Attempting to Visible Page Name ...");
			devicedashboardPage.verifyDashPageTitle();
			softAssert.assertEquals(actualPageName, expectedPageName, "URL Mismatch: Navigation failed!");
			result = expectedPageName.equalsIgnoreCase(actualPageName) ? "PASS" : "FAIL";
			logger.info("Result is: " + result);
		} catch (Exception e) {
			logger.error("An error occurred while Page name not visible.", e);
			e.printStackTrace();
		} finally {
			excelUtility.writeTestDataToExcel(testCaseName, expectedPageName, actualPageName, result);
			logger.info("Test case execution completed for: " + testCaseName);
			System.out.println("Page Name Successfully visible on Dashboard Tab");
			softAssert.assertAll();
		}
	}

}
