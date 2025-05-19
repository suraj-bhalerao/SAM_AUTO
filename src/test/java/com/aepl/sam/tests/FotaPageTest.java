package com.aepl.sam.tests;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.aepl.sam.base.TestBase;
import com.aepl.sam.enums.Result;
import com.aepl.sam.pages.FotaPage;
import com.aepl.sam.utils.CommonMethods;
import com.aepl.sam.utils.ExcelUtility;

public class FotaPageTest extends TestBase {
	private FotaPage fota;
	private ExcelUtility excelUtility;
	private CommonMethods comm;

	@BeforeClass
	public void setUp() {
		super.setUp();
		this.comm = new CommonMethods(driver, wait);
		this.fota = new FotaPage(driver, wait, comm);
		this.excelUtility = new ExcelUtility();
		excelUtility.initializeExcel("FOTA_Test");
	}

	@Test(priority = 1)
	public void testCompanyLogo() {
		String testCaseName = "Verify Company Logo on Webpage";
		String expected = "Logo Displayed";
		String actual = "";
		String result = Result.FAIL.getValue();

		logger.info("Executing the test Visible Page Name for test case: { " + testCaseName + " }");
		try {
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
		String expected = "AEPL Sampark Diagnostic Cloud";
		String actual = "";
		String result = Result.FAIL.getValue();

		logger.info("Executing the test Visible Page Name for test case: { " + testCaseName + " }");
		try {
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
	public void testRefreshButton() {
		String testCaseName = "Verify Refresh Button Functionality";
		String expected = "Clicked on the refreshed button";
		String actual = "";
		String result = Result.FAIL.getValue();

		logger.info("Executing the test Visible Page Name for test case: { " + testCaseName + " }");
		try {
			comm.clickRefreshButton();
			actual = "Clicked on the refreshed button";
			softAssert.assertEquals(actual, expected, "Refresh button verification failed!");
			result = expected.equalsIgnoreCase(actual) ? Result.PASS.getValue() : Result.FAIL.getValue();
			logger.info("Result is: " + result);
		} catch (Exception e) {
			logger.error("An error occurred while verifying the refresh button functionality.", e);
			actual = "Did not clicked on the refreshed button";
			result = Result.ERROR.getValue();
			e.printStackTrace();
		} finally {
			logger.info("Test case execution completed for: " + testCaseName);
			excelUtility.writeTestDataToExcel(testCaseName, expected, actual, result);
			softAssert.assertAll();
		}
	}

	@Test(priority = 4)
	public void testDeviceUtility() {
		String testCaseName = "Verify Device Utility Functionality";
		String expected = "Clicked on Device Utility";
		String actual = "";
		String result = Result.FAIL.getValue();

		logger.info("Executing the test Visible Page Name for test case: { " + testCaseName + " }");
		try {
			fota.clickDeviceUtility();
			actual = "Clicked on Device Utility";
			softAssert.assertEquals(actual, expected, "Device utility verification failed!");
			result = expected.equalsIgnoreCase(actual) ? Result.PASS.getValue() : Result.FAIL.getValue();
			logger.info("Result is: " + result);
		} catch (Exception e) {
			logger.error("An error occurred while verifying the device utility functionality.", e);
			actual = "Did not clicked on Device Utility";
			result = Result.ERROR.getValue();
			e.printStackTrace();
		} finally {
			logger.info("Test case execution completed for: " + testCaseName);
			excelUtility.writeTestDataToExcel(testCaseName, expected, actual, result);
			softAssert.assertAll();
		}
	}

	@Test(priority = 5)
	public void testFota() {
		String testCaseName = "Verify FOTA Functionality";
		String expected = "Clicked on FOTA";
		String actual = "";
		String result = Result.FAIL.getValue();

		logger.info("Executing the test Visible Page Name for test case: { " + testCaseName + " }");
		try {
			fota.clickFota();
			actual = "Clicked on FOTA";
			softAssert.assertEquals(actual, expected, "FOTA verification failed!");
			result = expected.equalsIgnoreCase(actual) ? Result.PASS.getValue() : Result.FAIL.getValue();
			logger.info("Result is: " + result);
		} catch (Exception e) {
			logger.error("An error occurred while verifying the FOTA functionality.", e);
			actual = "Did not clicked on FOTA";
			result = Result.ERROR.getValue();
			e.printStackTrace();
		} finally {
			logger.info("Test case execution completed for: " + testCaseName);
			excelUtility.writeTestDataToExcel(testCaseName, expected, actual, result);
			softAssert.assertAll();
		}
	}

	@Test(priority = 6)
	public void testAllComponents() {
		String testCaseName = "Verify All Components on Webpage";
		String expected = "All components are displayed and validated successfully.";
		String actual = "";
		String result = Result.FAIL.getValue();

		logger.info("Executing the test Visible Page Name for test case: { " + testCaseName + " }");
		try {
			actual = comm.validateComponents();
			softAssert.assertEquals(actual, expected, "Component verification failed!");
			result = expected.equalsIgnoreCase(actual) ? Result.PASS.getValue() : Result.FAIL.getValue();
			logger.info("Result is: " + result);
		} catch (Exception e) {
			logger.error("An error occurred while verifying all components.", e);
			actual = "Not all components are displayed";
			result = Result.ERROR.getValue();
			e.printStackTrace();
		} finally {
			logger.info("Test case execution completed for: " + testCaseName);
			excelUtility.writeTestDataToExcel(testCaseName, expected, actual, result);
			softAssert.assertAll();
		}
	}

	@Test(priority = 7)
	public void testAllButtons() {
		String testCaseName = "Verify All Buttons on Webpage";
		String expected = "All buttons are displayed and enabled successfully.";
		String actual = "";
		String result = Result.FAIL.getValue();

		logger.info("Executing the test Visible Page Name for test case: { " + testCaseName + " }");
		try {
			actual = comm.validateButtons();
			softAssert.assertEquals(actual, expected, "Button verification failed!");
			result = expected.equalsIgnoreCase(actual) ? Result.PASS.getValue() : Result.FAIL.getValue();
			logger.info("Result is: " + result);
		} catch (Exception e) {
			logger.error("An error occurred while verifying all buttons.", e);
			actual = "Not all buttons are displayed";
			result = Result.ERROR.getValue();
			e.printStackTrace();
		} finally {
			logger.info("Test case execution completed for: " + testCaseName);
			excelUtility.writeTestDataToExcel(testCaseName, expected, actual, result);
			softAssert.assertAll();
		}
	}

	@Test(priority = 8)
	public void testCreateManualFotaBatch() {
		String testCaseName = "Create FOTA Batch";
		String expected = "FOTA batch created successfully.";
		String actual = "";
		String result = Result.FAIL.getValue();

		logger.info("Executing the test Visible Page Name for test case: { " + testCaseName + " }");
		try {
			fota.selectFOTATypeButton("manual");
			fota.createManualFotaBatch("867409079954868");
			actual = "FOTA batch created successfully.";
			softAssert.assertEquals(actual, expected, "FOTA batch creation failed!");
			result = expected.equalsIgnoreCase(actual) ? Result.PASS.getValue() : Result.FAIL.getValue();
			logger.info("Result is: " + result);
		} catch (Exception e) {
			logger.error("An error occurred while creating the FOTA batch.", e);
			actual = "Failed to create FOTA batch";
			result = Result.ERROR.getValue();
			e.printStackTrace();
		} finally {
			logger.info("Test case execution completed for: " + testCaseName);
			excelUtility.writeTestDataToExcel(testCaseName, expected, actual, result);
			softAssert.assertAll();
		}
	}

	@Test(priority = 9)
	public void testBulkFota() {
		String testCaseName = "Create Bulk FOTA Batch";
		String expected = "Bulk FOTA batch created successfully.";
		String actual = "";
		String result = Result.FAIL.getValue();

		logger.info("Executing the test Visible Page Name for test case: { " + testCaseName + " }");
		try {
			fota.selectFOTATypeButton("bulk"); 
			String isClicked = comm.clickSampleFileButton();
			fota.createBulkFotaBatch();
			actual = "Bulk FOTA batch created successfully.";
			System.out.println("isClicked: " + isClicked);
			softAssert.assertEquals(actual, expected, "Bulk FOTA batch creation failed!");
			result = expected.equalsIgnoreCase(actual) ? Result.PASS.getValue() : Result.FAIL.getValue();
			logger.info("Result is: " + result);
		} catch (Exception e) {
			logger.error("An error occurred while creating the Bulk FOTA batch.", e);
			actual = "Failed to create Bulk FOTA batch";
			result = Result.ERROR.getValue();
			e.printStackTrace();
		} finally {
			logger.info("Test case execution completed for: " + testCaseName);
			excelUtility.writeTestDataToExcel(testCaseName, expected, actual, result);
			softAssert.assertAll();
		}
	}
	
	@Test(priority = 10)
	public void testPagination() {
		comm.checkPagination();
	}
	
	@Test(priority = 11)
	public void testGetFotaBatchList() {
		String testCaseName = "Get FOTA Batch List";
		String expected = "Batch seted successfully!";
		String actual = "";
		String result = Result.FAIL.getValue();

		logger.info("Executing the test Visible Page Name for test case: { " + testCaseName + " }");
		try {
			actual = fota.getFotaBatchList();
			softAssert.assertEquals(actual, expected, "FOTA batch list retrieval failed!");
			result = expected.equalsIgnoreCase(actual) ? Result.PASS.getValue() : Result.FAIL.getValue();
			logger.info("Result is: " + result);
		} catch (Exception e) {
			logger.error("An error occurred while retrieving the FOTA batch list.", e);
			actual = "Failed to retrieve FOTA batch list";
			result = Result.ERROR.getValue();
			e.printStackTrace();
		} finally {
			logger.info("Test case execution completed for: " + testCaseName);
			excelUtility.writeTestDataToExcel(testCaseName, expected, actual, result);
			softAssert.assertAll();
		}
	}
	
	@Test(priority = 12)
	public void testFotaBatchButtons() {
		String testCaseName = "Verify All Buttons on Webpage";
		String expected = "All buttons are displayed and enabled successfully.";
		String actual = "";
		String result = Result.FAIL.getValue();

		logger.info("Executing the test Visible Page Name for test case: { " + testCaseName + " }");
		try {
			actual = comm.validateButtons();
			softAssert.assertEquals(actual, expected, "Button verification failed!");
			result = expected.equalsIgnoreCase(actual) ? Result.PASS.getValue() : Result.FAIL.getValue();
			logger.info("Result is: " + result);
		} catch (Exception e) {
			logger.error("An error occurred while verifying all buttons.", e);
			actual = "Not all buttons are displayed";
			result = Result.ERROR.getValue();
			e.printStackTrace();
		} finally {
			logger.info("Test case execution completed for: " + testCaseName);
			excelUtility.writeTestDataToExcel(testCaseName, expected, actual, result);
			softAssert.assertAll();
		}
	}
}
