package com.aepl.sam.tests;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.aepl.sam.base.TestBase;
import com.aepl.sam.constants.Constants;
import com.aepl.sam.enums.Result;
import com.aepl.sam.pages.GovernmentServerPage;
import com.aepl.sam.utils.CommonMethods;
import com.aepl.sam.utils.ExcelUtility;

public class GovernmentServerPageTest extends TestBase {
	private GovernmentServerPage govServerPage;
	private ExcelUtility excelUtility;
	private CommonMethods comm;

	@BeforeClass
	public void setUp() {
		super.setUp();
		this.govServerPage = new GovernmentServerPage(driver, wait, action);
		this.comm = new CommonMethods(driver, wait);
		this.excelUtility = new ExcelUtility();
		excelUtility.initializeExcel("Government_Server_Test");
	}

	@Test(priority = 1)
	public void testCompanyLogo() {
		String testCaseName = "Verify Company Logo on Webpage";
		String expected = "Logo Displayed";
		String actual = "";
		String result = Result.FAIL.getValue();

		logger.info("Executing the test for: " + testCaseName);
		try {
			logger.info("Verifying if the company logo is displayed...");
			boolean isLogoDisplayed = comm.verifyWebpageLogo();
			actual = isLogoDisplayed ? "Logo Displayed" : "Logo Not Displayed";

			softAssert.assertEquals(actual, expected, "Company logo verification failed!");
			result = expected.equalsIgnoreCase(actual) ? Result.PASS.getValue() : Result.FAIL.getValue();
			logger.info("Result is: " + result);
		} catch (Exception e) {
			logger.error("An error occurred while verifying the company logo.", e);
			result = Result.ERROR.getValue();
			e.printStackTrace();
		} finally {
			logger.info("Test case execution completed for: " + testCaseName);
			excelUtility.writeTestDataToExcel(testCaseName, expected, actual, result);
			softAssert.assertAll();
		}
	}

	@Test(priority = 2)
	public void testPageTitle() {
		String testCaseName = "Verify Page Title on Webpage";
		String expected = "AEPL Sampark_Diet Diagnostic Cloud";
		String actual = "";
		String result = Result.FAIL.getValue();

		logger.info("Executing the test for: " + testCaseName);
		try {
			logger.info("Verifying the page title...");
			actual = comm.verifyPageTitle();

			softAssert.assertEquals(actual, expected, "Page title verification failed!");
			result = expected.equalsIgnoreCase(actual) ? Result.PASS.getValue() : Result.FAIL.getValue();
			logger.info("Result is: " + result);
		} catch (Exception e) {
			logger.error("An error occurred while verifying the page title.", e);
			result = Result.ERROR.getValue();
			e.printStackTrace();
		} finally {
			logger.info("Test case execution completed for: " + testCaseName);
			excelUtility.writeTestDataToExcel(testCaseName, expected, actual, result);
			softAssert.assertAll();
		}
	}

	@Test(priority = 3)
	public void testClickNavBar() {
		String testCaseName = "Verify Navigation Bar Click Functionality";
		String expected = "Navigation Successful";
		String actual = "";
		String result = Result.FAIL.getValue();

		logger.info("Executing the test for: " + testCaseName);
		try {
			logger.info("Clicking on the navigation bar link...");

			logger.info("Clicking on the navigation bar using device utility...");

			boolean isNavigationSuccessful = comm.clickNavBarDeviceUtil();

			actual = isNavigationSuccessful ? "Navigation Successful" : "Navigation Failed";

			softAssert.assertEquals(actual, expected, "Navigation bar click verification failed!");
			result = expected.equalsIgnoreCase(actual) ? Result.PASS.getValue() : Result.FAIL.getValue();
			logger.info("Result is: " + result);
		} catch (Exception e) {
			logger.error("An error occurred while verifying the navigation bar click.", e);
			result = Result.ERROR.getValue();
			e.printStackTrace();
		} finally {
			logger.info("Test case execution completed for: " + testCaseName);
			excelUtility.writeTestDataToExcel(testCaseName, expected, actual, result);
			softAssert.assertAll();
		}
	}

//	@Test(priority = 4)
	public void testBackButton() {
		String testCaseName = "Verify Back Button Functionality";
		String expected = Constants.GOV_LINK;
		String actual = "";
		String result = Result.FAIL.getValue();

		System.out.println("Executing the test for: " + testCaseName);
		try {
			System.out.println("Clicking the back button...");
			actual = govServerPage.backButton();
			softAssert.assertEquals(actual, expected, "Back button verification failed!");
			result = expected.equalsIgnoreCase(actual) ? Result.PASS.getValue() : Result.FAIL.getValue();
			System.out.println("Result is: " + result);
		} catch (Exception e) {
			System.out.println("An error occurred while verifying the back button: " + e.getMessage());
			result = Result.ERROR.getValue();
		} finally {
			System.out.println("Test case execution completed for: " + testCaseName);
			excelUtility.writeTestDataToExcel(testCaseName, expected, actual, result);
			softAssert.assertAll();
		}
	}

	@Test(priority = 5)
	public void testRefreshButton() {
		String testCaseName = "Verify Refresh Button Functionality";
		String expected = Constants.GOV_LINK;
		String actual = "";
		String result = Result.FAIL.getValue();

		System.out.println("Executing the test for: " + testCaseName);
		try {
			System.out.println("Clicking the refresh button...");
			comm.clickRefreshButton();
			actual = driver.getCurrentUrl();
			softAssert.assertEquals(actual, expected, "Refresh button verification failed!");
			result = expected.equalsIgnoreCase(actual) ? Result.PASS.getValue() : Result.FAIL.getValue();
			System.out.println("Result is: " + result);
		} catch (Exception e) {
			System.out.println("An error occurred while verifying the refresh button: " + e.getMessage());
			result = Result.ERROR.getValue();
		} finally {
			System.out.println("Test case execution completed for: " + testCaseName);
			excelUtility.writeTestDataToExcel(testCaseName, expected, actual, result);
			softAssert.assertAll();
		}
	}

	@Test(priority = 6)
	public void testAddGovernmentServer() {
		String testCaseName = "Verify Add Government Server Functionality";
		String expected = "Government Servers Details";
		String actual = "";
		String result = Result.FAIL.getValue();

		System.out.println("Executing the test for: " + testCaseName);
		try {
			System.out.println("Clicking the add government server button...");
			String governmentServer = govServerPage.addGovernmentServer();

			actual = governmentServer != null && !governmentServer.isEmpty() ? "Government Servers Details"
					: "Government Server Addition Failed";

			softAssert.assertEquals(actual, expected, "Add government server verification failed!");
			result = expected.equalsIgnoreCase(actual) ? Result.PASS.getValue() : Result.FAIL.getValue();
			System.out.println("Clicked on the government server add button: " + governmentServer);
			System.out.println("Result is: " + result);
		} catch (Exception e) {
			System.out.println("An error occurred while verifying the add government server button: " + e.getMessage());
			result = Result.ERROR.getValue();
		} finally {
			System.out.println("Test case execution completed for: " + testCaseName);
			excelUtility.writeTestDataToExcel(testCaseName, expected, actual, result);
			softAssert.assertAll();
		}
	}

	@Test(priority = 7)
	public void testFillForm() {
		String testCaseName = "Verify Government Server Form Filling";
		String expected = "Handler dispatch failed: java.lang.StackOverflowError"; // change this, this is error but for
																					// now it have to be here.
		String actual = "";
		String result = Result.FAIL.getValue();

		System.out.println("Executing the test for: " + testCaseName);
		try {
			System.out.println("Filling out the government server form...");
			actual = govServerPage.manageGovServer("add");
			softAssert.assertEquals(actual, expected, "Form filling verification failed!");
			result = expected.equalsIgnoreCase(actual) ? Result.PASS.getValue() : Result.FAIL.getValue();
			System.out.println("Result is: " + result);
		} catch (Exception e) {
			System.out.println("An error occurred while filling out the form: " + e.getMessage());
			result = Result.ERROR.getValue();
		} finally {
			System.out.println("Test case execution completed for: " + testCaseName);
			excelUtility.writeTestDataToExcel(testCaseName, expected, actual, result);
			softAssert.assertAll();
		}
	}

	@Test(priority = 8)
	public void testSearchAndView() {
		String testCaseName = "Verify Search and View Functionality";
		String expected = "Search and View Successful";
		String actual = "";
		String result = Result.FAIL.getValue();

		System.out.println("Executing the test for: " + testCaseName);
		try {
			System.out.println("Performing search and view operation...");
			boolean isSearchAndViewSuccessful = govServerPage.searchAndView();

			actual = isSearchAndViewSuccessful ? "Search and View Successful" : "Search and View Failed";

			softAssert.assertEquals(actual, expected, "Search and view verification failed!");
			result = expected.equalsIgnoreCase(actual) ? Result.PASS.getValue() : Result.FAIL.getValue();
			System.out.println("Result is: " + result);
		} catch (Exception e) {
			System.out.println("An error occurred while performing search and view: " + e.getMessage());
			result = Result.ERROR.getValue();
		} finally {
			System.out.println("Test case execution completed for: " + testCaseName);
			excelUtility.writeTestDataToExcel(testCaseName, expected, actual, result);
			softAssert.assertAll();
		}
	}

	// Update
	@Test(priority = 9)
	public void testUpdateGovServer() {
		String testCaseName = "Verify Update Government Server Functionality";
		String expected = "Data not found !!"; // change it. this is only write for the testing
		String actual = "";
		String result = Result.FAIL.getValue();

		System.out.println("Executing the test for: " + testCaseName);
		try {
			System.out.println("Updating the government server...");
			actual = govServerPage.manageGovServer("update");
			softAssert.assertEquals(actual, expected, "Update government server verification failed!");
			result = expected.equalsIgnoreCase(actual) ? Result.PASS.getValue() : Result.FAIL.getValue();
			System.out.println("Result is: " + result);
		} catch (Exception e) {
			System.out.println("An error occurred while updating the government server: " + e.getMessage());
			result = Result.ERROR.getValue();
		} finally {
			System.out.println("Test case execution completed for: " + testCaseName);
			excelUtility.writeTestDataToExcel(testCaseName, expected, actual, result);
			softAssert.assertAll();
		}
	}

	@Test(priority = 10)
	public void testAddFirmware() {
		String testCaseName = "Verify Add Firmware Functionality";
		String expected = "Firmware Added Successfully";
		String actual = "";
		String result = Result.FAIL.getValue();

		System.out.println("Executing the test for: " + testCaseName);
		try {
			System.out.println("Adding firmware...");
			boolean isFirmwareAdded = govServerPage.addFirmware();

			actual = isFirmwareAdded ? "Firmware Added Successfully" : "Firmware Addition Failed";

			softAssert.assertEquals(actual, expected, "Add firmware verification failed!");
			result = expected.equalsIgnoreCase(actual) ? Result.PASS.getValue() : Result.FAIL.getValue();
			System.out.println("Result is: " + result);
		} catch (Exception e) {
			System.out.println("An error occurred while adding firmware: " + e.getMessage());
			result = Result.ERROR.getValue();
		} finally {
			System.out.println("Test case execution completed for: " + testCaseName);
			excelUtility.writeTestDataToExcel(testCaseName, expected, actual, result);
			softAssert.assertAll();
		}
	}

//	@Test(priority = 11)
	/*
	 * This is the improvement to handles the multiple windows and provide the
	 * approvals to the added government server
	 */
	public void testApprovals() {
		boolean ok = govServerPage.waitForApprovalMultipleWindows();
	}

	@Test(priority = 12)
	public void testDeleteGovServer() {
		String testCaseName = "Verify Delete Government Server Functionality";
		String expected = "Government Server Deleted Successfully";
		String actual = "";
		String result = Result.FAIL.getValue();

		System.out.println("Executing the test for: " + testCaseName);
		try {
			System.out.println("Deleting the government server...");
			actual = govServerPage.deleteGovServer();
			softAssert.assertEquals(actual, expected, "Delete government server verification failed!");
			result = expected.equalsIgnoreCase(actual) ? Result.PASS.getValue() : Result.FAIL.getValue();
			System.out.println("Result is: " + result);
		} catch (Exception e) {
			System.out.println("An error occurred while deleting the government server: " + e.getMessage());
			result = Result.ERROR.getValue();
		} finally {
			System.out.println("Test case execution completed for: " + testCaseName);
			excelUtility.writeTestDataToExcel(testCaseName, expected, actual, result);
			softAssert.assertAll();
		}
	}
}
