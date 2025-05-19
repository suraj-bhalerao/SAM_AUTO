package com.aepl.sam.tests;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.aepl.sam.base.TestBase;
import com.aepl.sam.constants.Constants;
import com.aepl.sam.enums.Result;
import com.aepl.sam.pages.DeviceModelsPage;
import com.aepl.sam.utils.CommonMethods;
import com.aepl.sam.utils.ExcelUtility;

public class DeviceModelsPageTest extends TestBase {
	private ExcelUtility excelUtility;
	private DeviceModelsPage deviceModelsPage;
	private CommonMethods comm;

	@BeforeClass
	public void setUp() {
		super.setUp();
		this.comm = new CommonMethods(driver, wait);
		this.deviceModelsPage = new DeviceModelsPage(driver, wait, comm);
		this.excelUtility = new ExcelUtility();
		excelUtility.initializeExcel("Device_Models_Test");
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
	public void navBarLinkTest1() {
		String testCaseName = "Test Navigate to Device Utility Tab";
		String expected = Constants.DEVICE_LINK;
		String actual = "";
		String result = Result.FAIL.getValue();

		logger.info("Executing the test Visible Page Name for test case: { " + testCaseName + " }");
		try {
			actual = deviceModelsPage.navBarLink();
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

	@Test(priority = 2)
	public void ClickAddDeviceModelTest() {
		String testCaseName = "Test Navigate to Add Device Model button";
		String expected = "Create Device Model";
		String actual = "";
		String result = Result.FAIL.getValue();

		logger.info("Executing the test Visible Page Name for test case: { " + testCaseName + " }");
		try {
			actual = deviceModelsPage.ClickAddDeviceModel();
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
	public void addModelTest() throws InterruptedException {
		String testCaseName = "Test input fields by entering values";
		String expected = "Device Models";
		String actual = "";
		String result = Result.FAIL.getValue();

		logger.info("Executing the test Visible Page Name for test case: { " + testCaseName + " }");
		try {
			actual = deviceModelsPage.NewInputFields("add");
			softAssert.assertEquals(actual, expected, "Model addition failed!");
			result = expected.equalsIgnoreCase(actual) ? "PASS" : "FAIL";
		} catch (Exception e) {
			logger.error("An error occurred while adding the model.", e);
			result = Result.ERROR.getValue();
			e.printStackTrace();
		} finally {
			excelUtility.writeTestDataToExcel(testCaseName, expected, actual, result);
			softAssert.assertAll();
		}
	}

	@Test(priority = 4)
	public void searchModelTest() throws InterruptedException {
		String testCaseName = "Test search functionality for device models";
		String expected = "Device Models";
		String actual = "";
		String result = Result.FAIL.getValue();

		logger.info("Executing the test Visible Page Name for test case: { " + testCaseName + " }");
		try {
			actual = deviceModelsPage.searchModel();
			softAssert.assertEquals(actual, expected, "Search operation failed!");
			result = expected.equalsIgnoreCase(actual) ? "PASS" : "FAIL";
		} catch (Exception e) {
			logger.error("An error occurred while adding the model.", e);
			result = Result.ERROR.getValue();
			e.printStackTrace();
		} finally {
			excelUtility.writeTestDataToExcel(testCaseName, expected, actual, result);
			softAssert.assertAll();
		}
	}

	@Test(priority = 5)
	public void viewModelTest() throws InterruptedException {
		String testCaseName = "Test View Device Model";
		String expected = "View/Update Device Model";
		String actual = "";
		String result = Result.FAIL.getValue();

		logger.info("Executing the test Visible Page Name for test case: { " + testCaseName + " }");
		try {
			actual = deviceModelsPage.viewModel();
			softAssert.assertEquals(actual, expected, "Model viewing failed!");
			result = expected.equalsIgnoreCase(actual) ? "PASS" : "FAIL";
		} catch (Exception e) {
			logger.error("An error occurred while viewing the model.", e);
			result = Result.ERROR.getValue();
			e.printStackTrace();
		} finally {
			excelUtility.writeTestDataToExcel(testCaseName, expected, actual, result);
			softAssert.assertAll();
		}
	}

	@Test(priority = 6)
	public void UpdateModelTest() throws InterruptedException {
		String testCaseName = "Test input fields by updating values";
		String expected = "Device Models";
		String actual = "";
		String result = Result.FAIL.getValue();

		logger.info("Executing the test Visible Page Name for test case: { " + testCaseName + " }");
		try {
			actual = deviceModelsPage.NewInputFields("update");
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
	public void searchModelTest1() throws InterruptedException {
		String testCaseName = "Test search functionality for device models";
		String expected = "Device Models";
		String actual = "";
		String result = Result.FAIL.getValue();

		logger.info("Executing the test Visible Page Name for test case: { " + testCaseName + " }");
		try {
			actual = deviceModelsPage.searchModel2();
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
	public void DeleteModelTest() throws InterruptedException {
		String testCaseName = "Test View Device Model";
		String expected = "Device Models";
		String actual = "";
		String result = Result.FAIL.getValue();

		logger.info("Executing the test Visible Page Name for test case: { " + testCaseName + " }");
		try {
			actual = deviceModelsPage.DeleteModel();
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
	public void testValidateComponents() {
		String testCaseName = "Test Validate Components";
		String expected = "All components are displayed and validated successfully.";
		String actual = "";
		String result = Result.FAIL.getValue();

		logger.info("Executing the test Visible Page Name for test case: { " + testCaseName + " }");
		try {
			actual = comm.validateComponents();
			softAssert.assertEquals(actual, expected, "Component validation failed!");
			result = expected.equalsIgnoreCase(actual) ? "PASS" : "FAIL";
		} catch (Exception e) {
			logger.error("An error occurred while validating components.", e);
			result = Result.ERROR.getValue();
			e.printStackTrace();
		} finally {
			excelUtility.writeTestDataToExcel(testCaseName, expected, actual, result);
			softAssert.assertAll();
		}
	}
}
