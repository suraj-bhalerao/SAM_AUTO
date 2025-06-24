package com.aepl.sam.tests;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import com.aepl.sam.base.TestBase;
import com.aepl.sam.constants.Constants;
import com.aepl.sam.enums.Result;
import com.aepl.sam.pages.OtaPage;
import com.aepl.sam.utils.CommonMethods;
import com.aepl.sam.utils.ExcelUtility;

public class OtaPageTest extends TestBase {
	private ExcelUtility excelUtility;
	private OtaPage ota;
	private CommonMethods comm;
	private SoftAssert softAssert;

	@BeforeClass
	public void setUp() {
		super.setUp();
		this.comm = new CommonMethods(driver, wait);
		this.ota = new OtaPage(driver, wait, comm);
		this.excelUtility = new ExcelUtility();
		this.softAssert = new SoftAssert();
		excelUtility.initializeExcel("OTA_Test");
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
	public void TestNavBarLink() throws InterruptedException {
		String testCaseName = "Test Navigate to Device Utility Tab";
		String expected = Constants.OTA_LINK;
		String actual = "";
		String result = Result.FAIL.getValue();

		logger.info("Executing the test Visible Page Name for test case: " + testCaseName);
		try {
			logger.info("Attempting to Visible Element ...");
			actual = ota.navBarLink();
			softAssert.assertEquals(actual, expected, "URL Mismatch: Navigation failed!");
			result = expected.equalsIgnoreCase(actual) ? Result.PASS.getValue() : Result.FAIL.getValue();
			logger.info("Result is: " + result);
		} catch (Exception e) {
			logger.error("An error occurred while Element not visible.", e);
			e.printStackTrace();
		} finally {
			excelUtility.writeTestDataToExcel(testCaseName, expected, actual, result);
			logger.info("Test case execution completed for: " + testCaseName);
			softAssert.assertAll();
		}
	}

	@Test(priority = 2)
	public void testAllButtons() {
		String testCaseName = "Test All Buttons on OTA Page";
		String expected = "All buttons are displayed and enabled successfully.";
		String actual = "";
		String result = Result.FAIL.getValue();

		logger.info("Executing the test Visible Page Name for test case: " + testCaseName);
		try {
			actual = comm.validateButtons();
			softAssert.assertEquals(actual, expected, "Button functionality verification failed!");
			result = expected.equalsIgnoreCase(actual) ? Result.PASS.getValue() : Result.FAIL.getValue();
		} catch (Exception e) {
			logger.error("An error occurred while verifying button functionality.", e);
			result = Result.ERROR.getValue();
			e.printStackTrace();
		} finally {
			excelUtility.writeTestDataToExcel(testCaseName, expected, actual, result);
			logger.info("Test case execution completed for: " + testCaseName);
			softAssert.assertAll();
		}
	}

	@Test(priority = 3)
	public void testAllComponents() {

		String testCaseName = "Test All Components on OTA Page";
		String expected = "All components are displayed and validated successfully.";
		String actual = "";
		String result = Result.FAIL.getValue();

		logger.info("Executing the test Visible Page Name for test case: " + testCaseName);
		try {
			actual = comm.validateComponents();
			softAssert.assertEquals(actual, expected, "Component functionality verification failed!");
			result = expected.equalsIgnoreCase(actual) ? Result.PASS.getValue() : Result.FAIL.getValue();
		} catch (Exception e) {
			logger.error("An error occurred while verifying component functionality.", e);
			result = Result.ERROR.getValue();
			e.printStackTrace();
		} finally {
			excelUtility.writeTestDataToExcel(testCaseName, expected, actual, result);
			logger.info("Test case execution completed for: " + testCaseName);
			softAssert.assertAll();
		}
	}

	@Test(priority = 4)
	public void testOtaPagePagination() {
		String testCaseName = "Test Pagination on OTA Page";
		String expected = "Pagination is working correctly.";
		String actual = "";
		String result = Result.FAIL.getValue();

		logger.info("Executing the test Visible Page Name for test case: " + testCaseName);
		try {
			comm.checkPagination();
			actual = "Pagination is working correctly.";
			softAssert.assertEquals(actual, expected, "Pagination functionality verification failed!");
			result = expected.equalsIgnoreCase(actual) ? Result.PASS.getValue() : Result.FAIL.getValue();
		} catch (Exception e) {
			logger.error("An error occurred while verifying pagination functionality.", e);
			result = Result.ERROR.getValue();
			e.printStackTrace();
		} finally {
			excelUtility.writeTestDataToExcel(testCaseName, expected, actual, result);
			logger.info("Test case execution completed for: " + testCaseName);
			softAssert.assertAll();
		}
	}

	@Test(priority = 5)
	public void testManualOtaFeature() {
		String testCaseName = "Test Manual OTA Feature";
		String expected = "New OTA added successfully for IMEI: " + Constants.IMEI;
		String actual = "";
		String result = Result.FAIL.getValue();

		logger.info("Executing the test Visible Page Name for test case: " + testCaseName);
		try {
			actual = ota.testManualOtaFeature();
			softAssert.assertEquals(actual, expected, "Manual OTA feature verification failed!");
			result = expected.equalsIgnoreCase(actual) ? Result.PASS.getValue() : Result.FAIL.getValue();
		} catch (Exception e) {
			logger.error("An error occurred while verifying manual OTA feature.", e);
			result = Result.ERROR.getValue();
			e.printStackTrace();
		} finally {
			excelUtility.writeTestDataToExcel(testCaseName, expected, actual, result);
			logger.info("Test case execution completed for: " + testCaseName);
			softAssert.assertAll();
		}
	}

	@Test(priority = 6)
	public void testOtaDetails() {
		String testCaseName = "Test OTA Details";
		String expected = "OTA details displayed successfully.";
		String actual = "";
		String result = Result.FAIL.getValue();

		logger.info("Executing the test Visible Page Name for test case: " + testCaseName);
		try {
			actual = ota.testOtaDetails();
			softAssert.assertEquals(actual, expected, "OTA details verification failed!");
			result = expected.equalsIgnoreCase(actual) ? Result.PASS.getValue() : Result.FAIL.getValue();
		} catch (Exception e) {
			logger.error("An error occurred while verifying OTA details.", e);
			result = Result.ERROR.getValue();
			e.printStackTrace();
		} finally {
			excelUtility.writeTestDataToExcel(testCaseName, expected, actual, result);
			logger.info("Test case execution completed for: " + testCaseName);
			softAssert.assertAll();
		}
	}

	@Test(priority = 7)
	public void testOtaPagination() {
		String testCaseName = "Test OTA Pagination";
		String expected = "Pagination is working correctly.";
		String actual = "";
		String result = Result.FAIL.getValue();

		logger.info("Executing the test Visible Page Name for test case: " + testCaseName);
		try {
			comm.checkPagination();
			actual = "Pagination is working correctly.";
			softAssert.assertEquals(actual, expected, "OTA pagination verification failed!");
			result = expected.equalsIgnoreCase(actual) ? Result.PASS.getValue() : Result.FAIL.getValue();
		} catch (Exception e) {
			logger.error("An error occurred while verifying OTA pagination.", e);
			result = Result.ERROR.getValue();
			e.printStackTrace();
		} finally {
			excelUtility.writeTestDataToExcel(testCaseName, expected, actual, result);
			logger.info("Test case execution completed for: " + testCaseName);
			softAssert.assertAll();
		}
	}

	@Test(priority = 8)
	public void testExportButton() {

		String testCaseName = "Test Export Button on OTA Page";
		String expected = "Export functionality is working correctly.";
		String actual = "";
		String result = Result.FAIL.getValue();

		logger.info("Executing the test Visible Page Name for test case: " + testCaseName);
		try {
			actual = comm.validateExportButton() ? "Export functionality is working correctly."
					: "Export functionality failed.";
			softAssert.assertEquals(actual, expected, "Export button functionality verification failed!");
			result = expected.equalsIgnoreCase(actual) ? Result.PASS.getValue() : Result.FAIL.getValue();
		} catch (Exception e) {
			logger.error("An error occurred while verifying export button functionality.", e);
			result = Result.ERROR.getValue();
			e.printStackTrace();
		} finally {
			excelUtility.writeTestDataToExcel(testCaseName, expected, actual, result);
			logger.info("Test case execution completed for: " + testCaseName);
			softAssert.assertAll();
		}
	}

	@Test(priority = 9)
	public void testAbortButton() {
		String testCaseName = "Test Abort Button on OTA Page";
		String expected = "Abort functionality is working correctly.";
		String actual = "";
		String result = Result.FAIL.getValue();

		logger.info("Executing the test Visible Page Name for test case: " + testCaseName);
		try {
			actual = ota.testAbortButton() ? "Abort functionality is working correctly."
					: "Abort functionality failed.";
			softAssert.assertEquals(actual, expected, "Abort button functionality verification failed!");
			result = expected.equalsIgnoreCase(actual) ? Result.PASS.getValue() : Result.FAIL.getValue();
		} catch (Exception e) {
			logger.error("An error occurred while verifying abort button functionality.", e);
			result = Result.ERROR.getValue();
			e.printStackTrace();
		} finally {
			excelUtility.writeTestDataToExcel(testCaseName, expected, actual, result);
			logger.info("Test case execution completed for: " + testCaseName);
			softAssert.assertAll();
		}
	}

	// OTA BATCH TESTS
	@Test(priority = 10)
	public void testOtaBatch() {
		String testCaseName = "Test OTA Batch Functionality";
		String expected = "OTA batch functionality is working correctly.";
		String actual = "";
		String result = Result.FAIL.getValue();

		logger.info("Executing the test Visible Page Name for test case: " + testCaseName);
		try {
			actual = ota.testOtaBatch();
			softAssert.assertEquals(actual, expected, "OTA batch functionality verification failed!");
			result = expected.equalsIgnoreCase(actual) ? Result.PASS.getValue() : Result.FAIL.getValue();
		} catch (Exception e) {
			logger.error("An error occurred while verifying OTA batch functionality.", e);
			result = Result.ERROR.getValue();
			e.printStackTrace();
		} finally {
			excelUtility.writeTestDataToExcel(testCaseName, expected, actual, result);
			logger.info("Test case execution completed for: " + testCaseName);
			softAssert.assertAll();
		}
	}
}
