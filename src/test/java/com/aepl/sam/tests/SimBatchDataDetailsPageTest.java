package com.aepl.sam.tests;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.openqa.selenium.JavascriptExecutor;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import com.aepl.sam.base.TestBase;
import com.aepl.sam.constants.Constants;
import com.aepl.sam.constants.SimBatchDataDetailsConstants;
import com.aepl.sam.pages.SimBatchDataDetailsPage;
import com.aepl.sam.utils.CommonMethods;
import com.aepl.sam.utils.ExcelUtility;

public class SimBatchDataDetailsPageTest extends TestBase implements SimBatchDataDetailsConstants {
	private ExcelUtility excelUtility;
	private SimBatchDataDetailsPage simBatch;
	private CommonMethods comm;
	private SoftAssert softAssert;
	private Executor executor;

	@BeforeClass
	public void setUp() {
		super.setUp();
		this.comm = new CommonMethods(driver, wait);
		this.simBatch = new SimBatchDataDetailsPage(driver, wait, comm);
		this.excelUtility = new ExcelUtility();
		this.softAssert = new SoftAssert();
		excelUtility.initializeExcel(SHEET_NAME);
		this.executor = new Executor(excelUtility, softAssert);
	}

	@Test(priority = 1)
	public void testCompanyLogo() {
		executor.executeTest("Test Company logo", true, () -> comm.verifyWebpageLogo() ? true : "Logo Not Displayed");
	}

	@Test(priority = 2)
	public void testNavBarLink() {
		executor.executeTest("Test Nav Bar Link for {Sim Batch Data Details}", true, simBatch::navBarLink);
	}

	@Test(priority = 3)
	public void testPageTitle() {
		executor.executeTest("Test page title for {Sim Batch Data Details}", "Sensorise SIM Data Details",
				simBatch::verifyPageTitle);
	}

	@Test(priority = 4)
	public void testComponentTitle() {
		executor.executeTest("Test Page Component Title", "SIM Data Details", () -> comm.validateComponentTitle());
	}

	@Test(priority = 5)
	public void testButtons() {
		executor.executeTest("Test all button on page {Sim Batch Data Details}",
				"All buttons are displayed and enabled successfully.", () -> comm.validateButtons());
	}

	@Test(priority = 6)
	public void testComponents() {
		executor.executeTest("Test All Components on the page {Sim Batch Data Details}",
				"All components are displayed and validated successfully.", () -> comm.validateComponents());
	}

	// bulk upload
	// sample download test

	@Test(priority = 7)
	public void testSampleFile() {
		executor.executeTest("Test the sample file on page {Sim Batch Data Details}", "File downloaded successfully.",
				comm::clickSampleFileButton);
	}

	// upload file test
	@Test(priority = 8)
	public void testUploadFile() {
		executor.executeTest("Test upload file", true, simBatch::validateUpload);
	}

	// submit button test
	@Test(priority = 9)
	public void testSubmitButton() {
		executor.executeTest("Test Submit button", true, () -> simBatch.validateSubmitButton());
	}

	// validate all components
	@Test(priority = 10)
	public void testComponents2() {
		executor.executeTest("Test All Components on the page {Sim Batch Data Details}",
				"All components are displayed and validated successfully.", () -> comm.validateComponents());
	}

	@Test(priority = 11)
	public void testButtons2() {
		executor.executeTest("Test all button on page {Sim Batch Data Details}",
				"All buttons are displayed and enabled successfully.", () -> comm.validateButtons());
	}

	@Test(priority = 12)
	public void testUploadedSimDataDetailsComponentTitle() {
		executor.executeTest("Test Page Component Title", "SIM Data Details", () -> comm.validateComponentTitle());
	}

	@Test(priority = 13)
	public void testUploadSimDataDetailsComponentsTableHeaders() {
		String typeofTableValidation = "upload";
		List<String> expected_headers = Arrays.asList("ICCID", "CARD STATE", "CARD STATUS", "PRIMARY TSP",
				"FALLBACK TSP", "PRIMARY STATUS", "PRIMARY MSISDN", "FALLBACK STATUS", "FALLBACK MSISDN",
				"ACTIVE PROFILES", "CARD EXPIRY DATE", "PRODUCT NAME", "IS RSU REQUIRED", "IS IMSI REQUIRED",
				"ACTIVE SR NUMBER");

		List<String> actualHeaders = simBatch.validateTableHeaders(typeofTableValidation);

		softAssert.assertTrue(actualHeaders.containsAll(expected_headers));

		executor.executeTest("Test Upload Sim Data Details Components Table Headers", expected_headers,
				() -> simBatch.validateTableHeaders(typeofTableValidation));
	}

	@Test(priority = 14)
	public void testDuplicateICCIDInUploadedExcelSheetTableHeaders() {
		String typeofTableValidation = "duplicate";
		List<String> expected_headers = Arrays.asList("ICCID", "MESSAGE");
		List<String> actual_headers = simBatch.validateTableHeaders(typeofTableValidation);
		softAssert.assertTrue(actual_headers.containsAll(expected_headers));
		executor.executeTest("Test Duplicate ICCID's In Uploaded File Components Table Headers", expected_headers,
				() -> simBatch.validateTableHeaders(typeofTableValidation));
	}

	@Test(priority = 15)
	public void testICCIDNotPresentInSensoriseDatabaseTableHeaders() {
		String typeofTableValidation = "not present";
		List<String> expected_headers = Arrays.asList("ICCID", "MESSAGE");
		List<String> actual_headers = simBatch.validateTableHeaders(typeofTableValidation);

		softAssert.assertTrue(actual_headers.containsAll(expected_headers));

		executor.executeTest("Test Duplicate ICCID's In Uploaded File Components Table Headers", expected_headers,
				() -> simBatch.validateTableHeaders(typeofTableValidation));
	}

	@Test(priority = 16)
	public void testICCIDNotPresentInSensoriseDatabaseExportButton() {
		executor.executeTest("Test Export button of ICCID Not present", true, () -> simBatch.validateExportButton());
	}

	@Test(priority = 17)
	public void testDuplicateICCIDInUploadedExcelSheetExportButton() {
		executor.executeTest("Test Export button of Duplicate ICCID Uploaded", true,
				() -> simBatch.validateExportButton());
	}

	@Test(priority = 18)
	public void testUploadSimDataDetailsComponentsExportButton() {
		executor.executeTest("Test Export button of Sim Data Details", true, () -> simBatch.validateExportButton());
	}

//	@Test(priority = 19)
	public void testPaginationofSimDataDetailsWholePage() {
		executor.executeTest("Test pagination of the whole {Sim Data Details Page}", true, () -> {
			try {
				((JavascriptExecutor) driver).executeScript("window.scrollTo(0, 0);");
				Thread.sleep(500);
				comm.checkPagination();
				return true;
			} catch (Exception e) {
				return false;
			}
		});
	}

	// manual upload
	@Test(priority = 20)
	public void testManualUploadButtonIsVisible() {
		executor.executeTest("Test manual upload button is visible", true,
				() -> simBatch.isManualUploadButtonsVisible());
	}

	@Test(priority = 21)
	public void testManualUploadButtonIsClickable() {
		executor.executeTest("Test manual upload button is clickable", true,
				() -> simBatch.isManualUploadButtonsClickable());
	}

	@Test(priority = 22)
	public void testManualUploadClickAndOpen() {
		List<String> expected_results = new ArrayList<>();
		expected_results.add(Constants.SIM_MANUAL_UPLOAD);
		expected_results.add("SIM Data Details");

		executor.executeTest("Test manual upload button is clicked and opened", expected_results,
				() -> simBatch.manualUploadButtonClickedAndOpened());
	}

	@Test(priority = 23)
	public void testButtons3() {
		executor.executeTest("Test all button on page {Sim Batch Data Details}",
				"All buttons are displayed and enabled successfully.", () -> comm.validateButtons());
	}

	@Test(priority = 24)
	public void testComponents3() {
		executor.executeTest("Test All Components on the page {Sim Batch Data Details}",
				"All components are displayed and validated successfully.", () -> comm.validateComponents());
	}

	// input box enable
	@Test(priority = 25)
	public void testInputBoxEnabled() {
		executor.executeTest("Test input box enabled", true, simBatch::isInputBoxEnabled);
	}

	// input box have validations --- way one to get all validations
//	@Test(priority = 26)
//	public void testInputBoxHaveValidations() {
//	    // Expected validation messages
//	    Map<String, String> expectedValidations = new HashMap<>();
//	    expectedValidations.put("empty click", "This field is required and can't be only spaces");
//	    expectedValidations.put("short input", "Value must be exactly 20 characters long.");
//	    expectedValidations.put("long input", "Value must be exactly 20 characters long.");
//	    expectedValidations.put("special char", "Special characters are not allowed.");
//
//	    // Actual validation messages from app
//	    @SuppressWarnings("unchecked")
//	    Map<String, String> actualValidations =
//	            (Map<String, String>) simBatch.isInputBoxHaveProperValidations();
//
//	    // Assert each validation dynamically
//	    for (Map.Entry<String, String> entry : expectedValidations.entrySet()) {
//	        String caseName = entry.getKey();
//	        String expected = entry.getValue();
//	        String actual = actualValidations.get(caseName);
//
//	        Assert.assertEquals(actual, expected,
//	                "Validation failed for case: " + caseName);
//	    }
//	}

	@Test(priority = 26)
	public void testEmptyInputValidation() {
		executor.executeTest("Empty input validation", "This field is required and can't be only spaces.",
				() -> simBatch.isInputBoxHaveProperValidations(" "));
	}

	@Test(priority = 27)
	public void testShortInputValidation() {
		executor.executeTest("Short input validation", "Value must be exactly 20 characters long.",
				() -> simBatch.isInputBoxHaveProperValidations("shortText"));
	}

	@Test(priority = 28)
	public void testLongInputValidation() {
		executor.executeTest("Long input validation", "Value must be exactly 20 characters long.",
				() -> simBatch.isInputBoxHaveProperValidations("thisIsMoreThan20CharactersInput"));
	}

	@Test(priority = 29)
	public void testSpecialCharValidation() {
		executor.executeTest("Special char validation", "Special characters are not allowed.",
				() -> simBatch.isInputBoxHaveProperValidations("Invalid@#%CharsInput"));
	}

	// input box only taking iccid
//	@Test(priority = 30)
	public void testInputBoxTakingOnlyValidICCID() {
		executor.executeTest("Test input box only taking ICCID (valid)", null, null);
	}

	// submit button test
	@Test(priority = 31)
	public void testSubmitButtonEnabled() {
		executor.executeTest("Test submit button enabled", true, () -> simBatch.isSubmitButtonEnabled());
	}

	@Test(priority = 32)
	public void testclickSubmitButton() {
		executor.executeTest("Test Clicked the submit button", true, () -> simBatch.clickSubmitButton());
	}

	@Test(priority = 33)
	public void testUploadSimDataDetailsComponentsTableHeaders2() {
		String typeofTableValidation = "upload";
		List<String> expected_headers = Arrays.asList("ICCID", "CARD STATE", "CARD STATUS", "PRIMARY TSP",
				"FALLBACK TSP", "PRIMARY STATUS", "PRIMARY MSISDN", "FALLBACK STATUS", "FALLBACK MSISDN",
				"ACTIVE PROFILES", "CARD EXPIRY DATE", "PRODUCT NAME", "IS RSU REQUIRED", "IS IMSI REQUIRED",
				"ACTIVE SR NUMBER");

		List<String> actualHeaders = simBatch.validateTableHeaders(typeofTableValidation);

		softAssert.assertTrue(actualHeaders.containsAll(expected_headers));

		executor.executeTest("Test Upload Sim Data Details Components Table Headers", expected_headers,
				() -> simBatch.validateTableHeaders(typeofTableValidation));
	}

	@Test(priority = 35)
	public void testPaginationofSimDataDetailsWholePage2() {
		executor.executeTest("Test pagination of the whole {Sim Data Details Page}", true, () -> {
			try {
				((JavascriptExecutor) driver).executeScript("window.scrollTo(0, 0);");
				Thread.sleep(500);
				comm.checkPagination();
				return true;
			} catch (Exception e) {
				return false;
			}
		});
	}

	@AfterClass
	public void tearDownAssertions() {
		softAssert.assertAll();
	}
}
