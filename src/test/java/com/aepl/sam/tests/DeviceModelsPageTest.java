package com.aepl.sam.tests;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.aepl.sam.base.TestBase;
import com.aepl.sam.constants.Constants;
import com.aepl.sam.pages.DeviceModelsPage;
import com.aepl.sam.utils.ExcelUtility;

public class DeviceModelsPageTest extends TestBase {

	private ExcelUtility excelUtility;
	private DeviceModelsPage deviceModelsPage;

	@BeforeClass
	public void setUp() {
		super.setUp();
		this.deviceModelsPage = new DeviceModelsPage(driver, wait, action);
		this.excelUtility = new ExcelUtility();
		excelUtility.initializeExcel("Device_Dashboard_Test");
	}

	@Test(priority = 1)
	public void navBarLinkTest1() throws InterruptedException {
		String testCaseName = "Test Navigate to Device Utility Tab";
		String expected = Constants.DEVICE_LINK;
		String actual = "";
		String result = "FAIL"; // Default failure status

		logger.info("Executing the test Visible Page Name for test case: " + testCaseName);
		try {
			logger.info("Attempting to Visible Element ...");
			actual = deviceModelsPage.navBarLink();
			softAssert.assertEquals(actual, expected, "URL Mismatch: Navigation failed!");
			result = expected.equalsIgnoreCase(actual) ? "PASS" : "FAIL";
			logger.info("Result is: " + result);
		} catch (Exception e) {
			logger.error("An error occurred while Element not visible.", e);
			e.printStackTrace();
		} finally {
			excelUtility.writeTestDataToExcel(testCaseName, expected, actual, result);
			logger.info("Test case execution completed for: " + testCaseName);
//			System.out.println("\"Successfully Navigated to Device Utility Tab");
			softAssert.assertAll();
		}
	}

	@Test(priority = 2)
	public void ClickAddDeviceModelTest() throws InterruptedException {
		String testCaseName = "Test Navigate to Add Device Model button";
		String expected = "Create Device Model";
		String actual = "";
		String result = "FAIL"; // Default failure status

		logger.info("Executing the test Visible Page Name for test case: " + testCaseName);
		try {
			logger.info("Attempting to Visible Element ...");
			actual = deviceModelsPage.ClickAddDeviceModel();
			System.out.println("Actual: " + actual);
			softAssert.assertEquals(actual, expected, "URL Mismatch: Navigation failed!");
			result = expected.equalsIgnoreCase(actual) ? "PASS" : "FAIL";
			logger.info("Result is: " + result);
		} catch (Exception e) {
			logger.error("An error occurred while Element not visible.", e);
			e.printStackTrace();
		} finally {
			excelUtility.writeTestDataToExcel(testCaseName, expected, actual, result);
			logger.info("Test case execution completed for: " + testCaseName);
			System.out.println("\"Successfully Navigated to Device Utility Tab");
			softAssert.assertAll();
		}
	}

	@Test(priority = 3)
	public void addModelTest() throws InterruptedException {
		String testCaseName = "Test input fields by entering values";
		String expected = "Device Models"; // Update expected value as per actual output
		String actual = "";
		String result = "FAIL"; // Default failure status

		logger.info("Executing the test for: " + testCaseName);
		try {
			logger.info("Attempting to add a new device model...");
			actual = deviceModelsPage.NewInputFields("add");
			System.out.println("Actual: " + actual);
			softAssert.assertEquals(actual, expected, "Model addition failed!");
			result = expected.equalsIgnoreCase(actual) ? "PASS" : "FAIL";
			logger.info("Result is: " + result);
		} catch (Exception e) {
			logger.error("An error occurred while adding the model.", e);
			e.printStackTrace();
		} finally {
			excelUtility.writeTestDataToExcel(testCaseName, expected, actual, result);
			logger.info("Test case execution completed for: " + testCaseName);
			System.out.println("Successfully added a new device model.");
			softAssert.assertAll();
		}
	}

	// test search
	@Test(priority = 4)
	public void searchModelTest() throws InterruptedException {
		String testCaseName = "Test search functionality for device models";
		String expected = "Device Models"; // Update expected value as per actual output
		String actual = "";
		String result = "FAIL"; // Default failure status

		logger.info("Executing the test for: " + testCaseName);
		try {
			logger.info("Attempting to search for a device model...");
			actual = deviceModelsPage.searchModel();
			System.out.println("Actual: " + actual);
			softAssert.assertEquals(actual, expected, "Search operation failed!");
			result = expected.equalsIgnoreCase(actual) ? "PASS" : "FAIL";
			logger.info("Result is: " + result);
		} catch (Exception e) {
			logger.error("An error occurred while searching for the model.", e);
			e.printStackTrace();
		} finally {
			excelUtility.writeTestDataToExcel(testCaseName, expected, actual, result);
			logger.info("Test case execution completed for: " + testCaseName);
			System.out.println("Successfully searched for the device model.");
			softAssert.assertAll();
		}
	}

	// view device model
	@Test(priority = 5)
	public void viewModelTest() throws InterruptedException {
		String testCaseName = "Test View Device Model";
		String expected = "View/Update Device Model"; // Update expected value as per actual output
		String actual = "";
		String result = "FAIL"; // Default failure status

		logger.info("Executing the test for: " + testCaseName);
		try {
			logger.info("Attempting to view device model...");
			actual = deviceModelsPage.viewModel();
			System.out.println("Actual: " + actual);
			softAssert.assertEquals(actual, expected, "Model viewing failed!");
			result = expected.equalsIgnoreCase(actual) ? "PASS" : "FAIL";
			logger.info("Result is: " + result);
		} catch (Exception e) {
			logger.error("An error occurred while viewing the model.", e);
			e.printStackTrace();
		} finally {
			excelUtility.writeTestDataToExcel(testCaseName, expected, actual, result);
			logger.info("Test case execution completed for: " + testCaseName);
			System.out.println("Successfully viewed the device model.");
			softAssert.assertAll();
		}
	}

	@Test(priority = 6)
	public void UpdateModelTest() throws InterruptedException {
		String testCaseName = "Test input fields by updating values";
		String expected = "Device Models"; // Update expected value as per actual output
		String actual = "";
		String result = "FAIL"; // Default failure status

		logger.info("Executing the test for: " + testCaseName);
		try {
			logger.info("Attempting to update a device model...");
			actual = deviceModelsPage.NewInputFields("update");
			System.out.println("Actual: " + actual);
			softAssert.assertEquals(actual, expected, "Model update failed!");
			result = expected.equalsIgnoreCase(actual) ? "PASS" : "FAIL";
			logger.info("Result is: " + result);
		} catch (Exception e) {
			logger.error("An error occurred while updating the model.", e);
			e.printStackTrace();
		} finally {
			excelUtility.writeTestDataToExcel(testCaseName, expected, actual, result);
			logger.info("Test case execution completed for: " + testCaseName);
			System.out.println("Successfully updated the device model.");
			softAssert.assertAll();
		}
	}
	
	@Test(priority = 7)
	public void searchModelTest1() throws InterruptedException {
		String testCaseName = "Test search functionality for device models";
		String expected = "Device Models"; // Update expected value as per actual output
		String actual = "";
		String result = "FAIL"; // Default failure status

		logger.info("Executing the test for: " + testCaseName);
		try {
			logger.info("Attempting to search for a device model...");
			actual = deviceModelsPage.searchModel2();
			System.out.println("Actual: " + actual);
			softAssert.assertEquals(actual, expected, "Search operation failed!");
			result = expected.equalsIgnoreCase(actual) ? "PASS" : "FAIL";
			logger.info("Result is: " + result);
		} catch (Exception e) {
			logger.error("An error occurred while searching for the model.", e);
			e.printStackTrace();
		} finally {
			excelUtility.writeTestDataToExcel(testCaseName, expected, actual, result);
			logger.info("Test case execution completed for: " + testCaseName);
			System.out.println("Successfully searched for the device model.");
			softAssert.assertAll();
		}
	}
	
	@Test(priority = 8)
	public void DeleteModelTest() throws InterruptedException {
		String testCaseName = "Test View Device Model";
		String expected = "Device Models"; // Update expected value as per actual output
		String actual = "";
		String result = "FAIL"; // Default failure status

		logger.info("Executing the test for: " + testCaseName);
		try {
			logger.info("Attempting to view device model...");
			actual = deviceModelsPage.DeleteModel();
			
			System.out.println("Actual: " + actual);
			softAssert.assertEquals(actual, expected, "Model deleting failed!");
			result = expected.equalsIgnoreCase(actual) ? "PASS" : "FAIL";
			logger.info("Result is: " + result);
		} catch (Exception e) {
			logger.error("An error occurred while deleting the model.", e);
			e.printStackTrace();
		} finally {
			excelUtility.writeTestDataToExcel(testCaseName, expected, actual, result);
			logger.info("Test case execution completed for: " + testCaseName);
			System.out.println("Successfully deleted the device model.");
			softAssert.assertAll();
		}
	}

}
