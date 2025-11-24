package com.aepl.sam.tests;

import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import com.aepl.sam.base.TestBase;
import com.aepl.sam.constants.OtaConstants;
import com.aepl.sam.pages.CommonMethods;
import com.aepl.sam.pages.OtaPage;
import com.aepl.sam.utils.ExcelUtility;

public class OtaPageTest extends TestBase implements OtaConstants {
	private ExcelUtility excelUtility;
	private OtaPage ota;
	private CommonMethods comm;
	private SoftAssert softAssert;
	private Executor executor;

	@Override
	@BeforeClass
	public void setUp() {
		super.setUp();
		this.comm = new CommonMethods(driver, wait);
		this.ota = new OtaPage(driver, wait, comm);
		this.excelUtility = new ExcelUtility();
		this.softAssert = new SoftAssert();
		this.executor = new Executor(excelUtility, softAssert);
		excelUtility.initializeExcel(SHEET_NAME);
	}

	@Test(priority = -1)
	public void testCompanyLogo() {
		executor.executeTest(TC_LOGO, EXP_LOGO_DISPLAYED,
				() -> comm.verifyWebpageLogo() ? EXP_LOGO_DISPLAYED : "Logo Not Displayed");
	}

	@Test(priority = 0)
	public void testPageTitle() {
		executor.executeTest(TC_PAGE_TITLE, EXP_PAGE_TITLE, comm::verifyPageTitle);
	}

	@Test(priority = 1)
	public void testNavBarLink() {
		executor.executeTest(TC_NAV_BAR, EXP_NAV_BAR, ota::navBarLink);
	}

	@Test(priority = 2)
	public void testAllButtons() {
		executor.executeTest(TC_VALIDATE_BUTTONS, EXP_VALIDATE_BUTTONS, comm::validateButtons);
	}

	@Test(priority = 3)
	public void testAllComponents() {
		executor.executeTest(TC_VALIDATE_COMPONENTS, EXP_VALIDATE_COMPONENTS, comm::validateComponents);
	}

	@Test(priority = 4)
	public void testOtaPagePagination() {
		executor.executeTest(TC_PAGINATION, EXP_PAGINATION, () -> {
			comm.checkPagination();
			return EXP_PAGINATION;
		});
	}

	@Test(priority = 5)
	public void testManualOtaFeature() {
		executor.executeTest(TC_MANUAL_OTA, EXP_MANUAL_OTA, ota::testManualOtaFeature);
	}

	@Test(priority = 6)
	public void testOtaDetails() {
		executor.executeTest(TC_OTA_DETAILS, EXP_OTA_DETAILS, ota::testOtaDetails);
	}

	@Test(priority = 7)
	public void testOtaPagination() {
		executor.executeTest(TC_OTA_PAGINATION, EXP_PAGINATION, () -> {
			comm.checkPagination();
			return EXP_PAGINATION;
		});
	}

	@Test(priority = 8)
	public void testExportButton() {
		executor.executeTest(TC_EXPORT, EXP_EXPORT, () -> {
			return comm.validateExportButton() ? EXP_EXPORT : "Export functionality failed.";
		});
	}

	@Test(priority = 9)
	public void testAbortButton() {
		executor.executeTest(TC_ABORT, EXP_ABORT, () -> {
			return ota.testAbortButton() ? EXP_ABORT : "Abort functionality failed.";
		});
	}

	@Test(priority = 10)
	public void testOtaBatch() {
		executor.executeTest(TC_BATCH, EXP_BATCH, ota::testOtaBatch);
	}

	@AfterClass
	public void tearDownAssertions() {
		softAssert.assertAll();
	}
}
