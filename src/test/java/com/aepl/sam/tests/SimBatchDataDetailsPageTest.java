package com.aepl.sam.tests;

import java.util.Arrays;
import java.util.List;

import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import com.aepl.sam.base.TestBase;
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

	// validate all components agains
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
		List<String> expected_headers = Arrays.asList("ICCID", "CARD STATE", "CARD STATUS", "PRIMARY TSP",
				"FALLBACK TSP", "PRIMARY STATUS", "PRIMARY MSISDN", "FALLBACK STATUS", "FALLBACK MSISDN",
				"ACTIVE PROFILES", "CARD EXPIRY DATE", "PRODUCT NAME", "IS RSU REQUIRED", "IS IMSI REQUIRED",
				"ACTIVE SR NUMBER");

		List<String> actualHeaders = simBatch.validateComponentHeades();

		softAssert.assertTrue(actualHeaders.containsAll(expected_headers));

		executor.executeTest("Test Upload Sim Data Details Components Table Headers", expected_headers,
				() -> simBatch.validateComponentHeades());
	}

	// pagination for the UploadSimDataDetailsComponentsTable
//	@Test(priority = 14)
	public void testPaginationOfUploadSimDataDetailsComponentsTable() {
		executor.executeTest("Test pagination of {Upload Sim Data Details Components Table}", true, () -> {
			try {
				comm.checkPagination();
				return true;
			} catch (Exception e) {
				return false;
			}
		});
	}

	// manual upload
	// input box enable
	// input box only taking iccid
	// input box have validations
	// submit button test

	@AfterClass
	public void tearDownAssertions() {
		softAssert.assertAll();
	}
}
