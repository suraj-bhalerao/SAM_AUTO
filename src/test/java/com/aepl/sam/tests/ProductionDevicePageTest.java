package com.aepl.sam.tests;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.aepl.sam.base.TestBase;
import com.aepl.sam.constants.Constants;
import com.aepl.sam.pages.DeviceModelsPage;
import com.aepl.sam.pages.ProductionDevicePage;
import com.aepl.sam.utils.CommonMethods;
import com.aepl.sam.utils.ExcelUtility;

public class ProductionDevicePageTest extends TestBase{

	private ExcelUtility excelUtility;
	private ProductionDevicePage productionDevicePage;
	private CommonMethods commonMethods;

	@BeforeClass
	public void setUp() {
		super.setUp();
		this.productionDevicePage = new ProductionDevicePage(driver, wait, action);
//		this.DeviceModelsPage = new DeviceModelsPage(driver, wait);
		this.commonMethods = new CommonMethods(driver, wait);
		this.excelUtility = new ExcelUtility();
		excelUtility.initializeExcel("Device_Dashboard_Test");
	}
	
	@Test(priority = 1)
	public void TestNavBarLink() throws InterruptedException {
		String testCaseName = "Test Navigate to Device Utility Tab";
		String expected = Constants.PROD_DEVICE_LINK;
		String actual = "";
		String result = "FAIL"; // Default failure status

		logger.info("Executing the test Visible Page Name for test case: " + testCaseName);
		try {
			logger.info("Attempting to Visible Element ...");
			actual = productionDevicePage.navBarLink();
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
	public void TestClickAddProdDevice() throws InterruptedException {
		String testCaseName = "Test Navigate to Add Device Model button";
		String expected = "Create Production Device";
		String actual = "";
		String result = "FAIL"; // Default failure status

		logger.info("Executing the test Visible Page Name for test case: " + testCaseName);
		try {
			logger.info("Attempting to Visible Element ...");
			actual = productionDevicePage.ClickAddProdDevice();
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
	public void TestAddProdDevice() throws InterruptedException {
		String testCaseName = "Test input fields by entering values";
		String expected = "Production Device"; // Update expected value as per actual output
		String actual = "";
		String result = "FAIL"; // Default failure status

		logger.info("Executing the test for: " + testCaseName);
		try {
			logger.info("Attempting to add a new device...");
			actual = productionDevicePage.NewInputFields("add");
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
	
	
	
	
	
	
}
