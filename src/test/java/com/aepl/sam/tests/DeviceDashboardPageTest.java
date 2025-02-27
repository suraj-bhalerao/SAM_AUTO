package com.aepl.sam.tests;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.aepl.sam.base.TestBase;
import com.aepl.sam.constants.Constants;
import com.aepl.sam.pages.DeviceDashboardPage;
import com.aepl.sam.utils.ExcelUtility;

public class DeviceDashboardPageTest extends TestBase {
	private ExcelUtility excelUtility;
	private DeviceDashboardPage devicedashboardPage;

	@BeforeClass
	public void setUp() {
		super.setUp();
		this.devicedashboardPage = new DeviceDashboardPage(driver, wait);
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
			System.out.println("Actual URL is: " + actualURL);
			// Using Soft Assertion
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
}
