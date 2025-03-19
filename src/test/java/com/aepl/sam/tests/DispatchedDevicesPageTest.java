package com.aepl.sam.tests;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.aepl.sam.base.TestBase;
import com.aepl.sam.constants.Constants;
import com.aepl.sam.pages.DispatchedDevicesPage;
import com.aepl.sam.pages.ProductionDevicePage;
import com.aepl.sam.utils.CommonMethods;
import com.aepl.sam.utils.ExcelUtility;

public class DispatchedDevicesPageTest extends TestBase{

		private ExcelUtility excelUtility;
		private DispatchedDevicesPage dispatchedDevicePage;
		private CommonMethods commonMethods;

		@BeforeClass
		public void setUp() {
			super.setUp();
			this.dispatchedDevicePage = new DispatchedDevicesPage(driver, wait, action);
//			this.DeviceModelsPage = new DeviceModelsPage(driver, wait);
			this.commonMethods = new CommonMethods(driver, wait);
			this.excelUtility = new ExcelUtility();
			excelUtility.initializeExcel("Device_Dashboard_Test");
	
}
		@Test(priority = 1)
		public void TestNavBarLink() throws InterruptedException {
			String testCaseName = "Test Navigate to Device Utility Tab";
			String expected = Constants.DISP_DEVICE_LINK;
			String actual = "";
			String result = "FAIL"; // Default failure status

			logger.info("Executing the test Visible Page Name for test case: " + testCaseName);
			try {
				logger.info("Attempting to Visible Element ...");
				actual = dispatchedDevicePage.NavBarLink();
				softAssert.assertEquals(actual, expected, "URL Mismatch: Navigation failed!");
				result = expected.equalsIgnoreCase(actual) ? "PASS" : "FAIL";
				logger.info("Result is: " + result);
			} catch (Exception e) {
				logger.error("An error occurred while Element not visible.", e);
				e.printStackTrace();
			} finally {
				excelUtility.writeTestDataToExcel(testCaseName, expected, actual, result);
				logger.info("Test case execution completed for: " + testCaseName);
//				System.out.println("\"Successfully Navigated to Device Utility Tab");
				softAssert.assertAll();
			}
		}
		
		@Test(priority = 2)
		public void TestClickAddDisDevice() throws InterruptedException {
			String testCaseName = "Test Navigate to Add Device Model button";
			String expected = "Create Dispatch Device";
			String actual = "";
			String result = "FAIL"; // Default failure status

			logger.info("Executing the test Visible Page Name for test case: " + testCaseName);
			try {
				logger.info("Attempting to Visible Element ...");
				actual = dispatchedDevicePage.ClickAddDisDevice();
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
		public void TestAddDisDevice() throws InterruptedException {
			String testCaseName = "Test input fields by entering values";
			String expected = "Dispatched Devices";// Update expected value as per actual output
			String actual = "";
			String result = "FAIL"; // Default failure status

			logger.info("Executing the test for: " + testCaseName);
			try {
				logger.info("Attempting to add a new device...");
				actual = dispatchedDevicePage.NewInputFields("add");
				System.out.println("Actual: " + actual);
				softAssert.assertEquals(actual, expected, "Model addition failed!");
				result = expected.equalsIgnoreCase(actual) ? "PASS" : "FAIL";
				logger.info("Result is: " + result);
			} catch (Exception e) {
				logger.error("An error occurred while adding the device.", e);
				e.printStackTrace();
			} finally {
				excelUtility.writeTestDataToExcel(testCaseName, expected, actual, result);
				logger.info("Test case execution completed for: " + testCaseName);
				System.out.println("Successfully added a new device.");
				softAssert.assertAll();
			}
		}
		
		@Test(priority = 4)
		public void TestSearchDeviceTest() throws InterruptedException {
			String testCaseName = "Test search functionality for device models";
			String expected = "Dispatched Devices"; // Update expected value as per actual output
			String actual = "";
			String result = "FAIL"; // Default failure status

			logger.info("Executing the test for: " + testCaseName);
			try {
				logger.info("Attempting to search for a device model...");
				actual = dispatchedDevicePage.SearchDevice();
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
		
		@Test(priority = 5)
		public void TestViewDevice() throws InterruptedException {
			String testCaseName = Constants.CREATE_DIS_DEVICE_LINK;
			String expected = "Update Dispatch Device"; // Update expected value as per actual output
			String actual = "";
			String result = "FAIL"; // Default failure status

			logger.info("Executing the test for: " + testCaseName);
			try {
				logger.info("Attempting to view device model...");
				actual = dispatchedDevicePage.viewDevice();
				System.out.println("Actual: " + actual);
				softAssert.assertEquals(actual, expected, "Device viewing failed!");
				result = expected.equalsIgnoreCase(actual) ? "PASS" : "FAIL";
				logger.info("Result is: " + result);
			} catch (Exception e) {
				logger.error("An error occurred while viewing the device.", e);
				e.printStackTrace();
			} finally {
				excelUtility.writeTestDataToExcel(testCaseName, expected, actual, result);
				logger.info("Test case execution completed for: " + testCaseName);
				System.out.println("Successfully viewed the device.");
				softAssert.assertAll();
			}
		}
		
		@Test(priority = 6)
		public void TestUpdateDevice() throws InterruptedException {
			String testCaseName = "Test input fields by updating values";
			String expected = "Dispatched Devices"; // Update expected value as per actual output
			String actual = "";
			String result = "FAIL"; // Default failure status

			logger.info("Executing the test for: " + testCaseName);
			try {
				logger.info("Attempting to update a device model...");
				actual = dispatchedDevicePage.NewInputFields("update");
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
		public void TestSearchDeviceTest1() throws InterruptedException {
			String testCaseName = "Test search functionality for device models";
			String expected = "Dispatched Devices"; // Update expected value as per actual output
			String actual = "";
			String result = "FAIL"; // Default failure status

			logger.info("Executing the test for: " + testCaseName);
			try {
				logger.info("Attempting to search for a device model...");
				actual = dispatchedDevicePage.SearchDevice();
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
		public void DeleteDeviceTest() throws InterruptedException {
			String testCaseName = "Test View Device Model";
			String expected = "Dispatched Devices";  // Update expected value as per actual output
			String actual = "";
			String result = "FAIL"; // Default failure status

			logger.info("Executing the test for: " + testCaseName);
			try {
				logger.info("Attempting to view device model...");
				actual = dispatchedDevicePage.DeleteDevice();
				
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
