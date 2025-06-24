package com.aepl.sam.tests;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import com.aepl.sam.base.TestBase;
import com.aepl.sam.constants.Constants;
import com.aepl.sam.enums.Result;
import com.aepl.sam.pages.DispatchedDevicesPage;
import com.aepl.sam.utils.CommonMethods;
import com.aepl.sam.utils.ExcelUtility;

public class DispatchedDevicesPageTest extends TestBase {
	private ExcelUtility excelUtility;
	private DispatchedDevicesPage dispatchedDevicePage;
	private CommonMethods comm;
	private SoftAssert softAssert;

	@BeforeClass
	public void setUp() {
		super.setUp();
		this.comm = new CommonMethods(driver, wait);
		this.dispatchedDevicePage = new DispatchedDevicesPage(driver, wait, comm);
		this.excelUtility = new ExcelUtility();
		this.softAssert = new SoftAssert();
		excelUtility.initializeExcel("Dispached_Devices_Test");
	}

	@Test(priority = -1)
	public void testCompanyLogo() {
		String testCaseName = "Verify Company Logo on Webpage";
		String expected = "Logo Displayed";
		String actual = "";
		String result = Result.FAIL.getValue();

		logger.info("Executing the test Visible Page Name for test case: { " + testCaseName + " }");
		try {
			logger.info("Verifying if the company logo is displayed...");
			boolean isLogoDisplayed = comm.verifyWebpageLogo();
			actual = isLogoDisplayed ? "Logo Displayed" : "Logo Not Displayed";
			softAssert.assertEquals(actual, expected, "Company logo verification failed!");
			result = expected.equalsIgnoreCase(actual) ? Result.PASS.getValue() : Result.FAIL.getValue();
		} catch (Exception e) {
			logger.error("An error occurred while verifying the company logo.", e);
			result = Result.ERROR.getValue();
			e.printStackTrace();
		} finally {
			excelUtility.writeTestDataToExcel(testCaseName, expected, actual, result);
			softAssert.assertAll();
		}
	}

	@Test(priority = 0)
	public void testPageTitle() {
		String testCaseName = "Verify Page Title on Webpage";
		String expected = "AEPL Sampark Diagnostic Cloud";
		String actual = "";
		String result = Result.FAIL.getValue();

		logger.info("Executing the test Visible Page Name for test case: { " + testCaseName + " }");
		try {
			actual = comm.verifyPageTitle();
			softAssert.assertEquals(actual, expected, "Page title verification failed!");
			result = expected.equalsIgnoreCase(actual) ? Result.PASS.getValue() : Result.FAIL.getValue();
		} catch (Exception e) {
			logger.error("An error occurred while verifying the page title.", e);
			result = Result.ERROR.getValue();
			e.printStackTrace();
		} finally {
			excelUtility.writeTestDataToExcel(testCaseName, expected, actual, result);
			softAssert.assertAll();
		}
	}

	@Test(priority = 1)
	public void TestNavBarLink() {
		String testCaseName = "Test Navigate to Device Utility Tab";
		String expected = Constants.DISP_DEVICE_LINK;
		String actual = "";
		String result = Result.FAIL.getValue();

		logger.info("Executing the test Visible Page Name for test case: { " + testCaseName + " }");
		try {
			logger.info("Attempting to Visible Element ...");
			actual = dispatchedDevicePage.navBarLink();
			softAssert.assertEquals(actual, expected, "URL Mismatch: Navigation failed!");
			result = expected.equalsIgnoreCase(actual) ? "PASS" : "FAIL";
			logger.info("Result is: " + result);
		} catch (Exception e) {
			logger.error("An error occurred while Element not visible.", e);
			result = Result.ERROR.getValue();
			e.printStackTrace();
		} finally {
			excelUtility.writeTestDataToExcel(testCaseName, expected, actual, result);
			softAssert.assertAll();
		}
	}

	@Test(priority = 2)
	public void TestClickAddDisDevice() {
		String testCaseName = "Test Navigate to Add Device Model button";
		String expected = "Create Dispatched Device";
		String actual = "";
		String result = Result.FAIL.getValue();

		logger.info("Executing the test Visible Page Name for test case: { " + testCaseName + " }");
		try {
			actual = dispatchedDevicePage.ClickAddDisDevice();
			softAssert.assertEquals(actual, expected, "URL Mismatch: Navigation failed!");
			result = expected.equalsIgnoreCase(actual) ? "PASS" : "FAIL";
		} catch (Exception e) {
			logger.error("An error occurred while Element not visible.", e);
			result = Result.ERROR.getValue();
			e.printStackTrace();
		} finally {
			excelUtility.writeTestDataToExcel(testCaseName, expected, actual, result);
			softAssert.assertAll();
		}
	}

	@Test(priority = 3)
	public void TestAddDisDevice() throws InterruptedException {
		String testCaseName = "Test input fields by entering values";
		String expected = "Dispatched Devices";
		String actual = "";
		String result = Result.FAIL.getValue();

		logger.info("Executing the test Visible Page Name for test case: { " + testCaseName + " }");
		try {
			actual = dispatchedDevicePage.NewInputFields("add");
			softAssert.assertEquals(actual, expected, "Model addition failed!");
			result = expected.equalsIgnoreCase(actual) ? "PASS" : "FAIL";
		} catch (Exception e) {
			logger.error("An error occurred while adding the device.", e);
			result = Result.ERROR.getValue();
			e.printStackTrace();
		} finally {
			excelUtility.writeTestDataToExcel(testCaseName, expected, actual, result);
			softAssert.assertAll();
		}
	}

	@Test(priority = 4)
	public void TestSearchDeviceTest() {
		String testCaseName = "Test search functionality for device models";
		String expected = "Dispatched Devices";
		String actual = "";
		String result = Result.FAIL.getValue();

		logger.info("Executing the test Visible Page Name for test case: { " + testCaseName + " }");
		try {
			actual = dispatchedDevicePage.SearchDevice();
			softAssert.assertEquals(actual, expected, "Search operation failed!");
			result = expected.equalsIgnoreCase(actual) ? "PASS" : "FAIL";
		} catch (Exception e) {
			logger.error("An error occurred while searching for the model.", e);
			result = Result.ERROR.getValue();
			e.printStackTrace();
		} finally {
			excelUtility.writeTestDataToExcel(testCaseName, expected, actual, result);
			softAssert.assertAll();
		}
	}

	@Test(priority = 5)
	public void TestViewDevice() {
		String testCaseName = "Test View Device Model";
		String expected = "Update Dispatched Device";
		String actual = "";
		String result = Result.FAIL.getValue();

		logger.info("Executing the test Visible Page Name for test case: { " + testCaseName + " }");
		try {
			actual = dispatchedDevicePage.viewDevice();
			softAssert.assertEquals(actual, expected, "Device viewing failed!");
			result = expected.equalsIgnoreCase(actual) ? "PASS" : "FAIL";
		} catch (Exception e) {
			logger.error("An error occurred while viewing the device.", e);
			result = Result.ERROR.getValue();
			e.printStackTrace();
		} finally {
			excelUtility.writeTestDataToExcel(testCaseName, expected, actual, result);
			softAssert.assertAll();
		}
	}

	@Test(priority = 6)
	public void TestUpdateDevice() throws InterruptedException {
		String testCaseName = "Test input fields by updating values";
		String expected = "Dispatched Devices";
		String actual = "";
		String result = Result.FAIL.getValue();

		logger.info("Executing the test Visible Page Name for test case: { " + testCaseName + " }");
		try {
			actual = dispatchedDevicePage.NewInputFields("update");
			softAssert.assertEquals(actual, expected, "Model update failed!");
			result = expected.equalsIgnoreCase(actual) ? "PASS" : "FAIL";
		} catch (Exception e) {
			logger.error("An error occurred while updating the model.", e);
			result = Result.ERROR.getValue();
			e.printStackTrace();
		} finally {
			excelUtility.writeTestDataToExcel(testCaseName, expected, actual, result);
			softAssert.assertAll();
		}
	}

	@Test(priority = 7)
	public void TestSearchDeviceTest1() {
		String testCaseName = "Test search functionality for device models";
		String expected = "Dispatched Devices";
		String actual = "";
		String result = Result.FAIL.getValue();

		logger.info("Executing the test Visible Page Name for test case: { " + testCaseName + " }");
		try {
			actual = dispatchedDevicePage.SearchDevice();
			softAssert.assertEquals(actual, expected, "Search operation failed!");
			result = expected.equalsIgnoreCase(actual) ? "PASS" : "FAIL";
		} catch (Exception e) {
			logger.error("An error occurred while searching for the model.", e);
			result = Result.ERROR.getValue();
			e.printStackTrace();
		} finally {
			excelUtility.writeTestDataToExcel(testCaseName, expected, actual, result);
			softAssert.assertAll();
		}
	}

	@Test(priority = 8)
	public void DeleteDeviceTest() {
		String testCaseName = "Test View Device Model";
		String expected = "Dispatched Devices";
		String actual = "";
		String result = Result.FAIL.getValue();

		logger.info("Executing the test Visible Page Name for test case: { " + testCaseName + " }");
		try {
			actual = dispatchedDevicePage.DeleteDevice();
			softAssert.assertEquals(actual, expected, "Model deleting failed!");
			result = expected.equalsIgnoreCase(actual) ? "PASS" : "FAIL";
		} catch (Exception e) {
			logger.error("An error occurred while deleting the model.", e);
			result = Result.ERROR.getValue();
			e.printStackTrace();
		} finally {
			excelUtility.writeTestDataToExcel(testCaseName, expected, actual, result);
			softAssert.assertAll();
		}
	}

	@Test(priority = 9)
	public void testAllButtons() {
		String testCaseName = "Test All Buttons";
		String expected = "All buttons are displayed and enabled successfully.";
		String actual = "";
		String result = Result.FAIL.getValue();
		logger.info("Executing the test Visible Page Name for test case: { " + testCaseName + " }");
		try {
			actual = comm.validateButtons();
			softAssert.assertEquals(actual, expected, "Model deleting failed!");
			result = expected.equalsIgnoreCase(actual) ? "PASS" : "FAIL";
		} catch (Exception e) {
			logger.error("An error occurred while deleting the model.", e);
			result = Result.ERROR.getValue();
			e.printStackTrace();
		} finally {
			excelUtility.writeTestDataToExcel(testCaseName, expected, actual, result);
			softAssert.assertAll();
		}
	}

	@Test(priority = 10)
	public void testAllComponents() {
		String testCaseName = "Test All Components";
		String expected = "All components are displayed and validated successfully.";
		String actual = "";
		String result = Result.FAIL.getValue();
		logger.info("Executing the test Visible Page Name for test case: { " + testCaseName + " }");

		try {
			actual = comm.validateComponents();
			softAssert.assertEquals(actual, expected, "Model deleting failed!");
			result = expected.equalsIgnoreCase(actual) ? "PASS" : "FAIL";
		} catch (Exception e) {
			logger.error("An error occurred while deleting the model.", e);
			result = Result.ERROR.getValue();
			e.printStackTrace();
		} finally {
			excelUtility.writeTestDataToExcel(testCaseName, expected, actual, result);
			softAssert.assertAll();
		}
	}

	/* must have to implement the bulk add of dispatched devices */

}
