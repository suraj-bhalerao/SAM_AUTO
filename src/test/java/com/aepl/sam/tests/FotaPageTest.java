package com.aepl.sam.tests;

import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import com.aepl.sam.base.TestBase;
import com.aepl.sam.constants.Constants;
import com.aepl.sam.constants.FotaConstants;
import com.aepl.sam.pages.CommonMethods;
import com.aepl.sam.pages.FotaPage;
import com.aepl.sam.utils.ExcelUtility;

public class FotaPageTest extends TestBase implements FotaConstants {
	private FotaPage fota;
	private ExcelUtility excelUtility;
	private CommonMethods comm;
	private SoftAssert softAssert;
	private Executor executor;

	@Override
	@BeforeClass
	public void setUp() {
		super.setUp();
		this.comm = new CommonMethods(driver, wait);
		this.fota = new FotaPage(driver, wait, comm);
		this.excelUtility = new ExcelUtility();
		this.softAssert = new SoftAssert();
		this.executor = new Executor(excelUtility, softAssert);
		excelUtility.initializeExcel(FOTA_EXCEL_SHEET);
		logger.info("Setup completed for FotaPageTest");
	}

	@Test(priority = 1)
	public void testCompanyLogo() {
		executor.executeTest(TC_PAGE_LOGO, EXP_LOGO_DISPLAYED,
				() -> comm.verifyWebpageLogo() ? EXP_LOGO_DISPLAYED : "Logo Not Displayed");
	}

	@Test(priority = 2)
	public void testPageTitle() {
		executor.executeTest(TC_PAGE_TITLE, EXP_PAGE_TITLE, comm::verifyPageTitle);
	}

	@Test(priority = 3)
	public void testRefreshButton() {
		executor.executeTest(TC_REFRESH_BTN, EXP_REFRESH_BTN, () -> {
			comm.clickRefreshButton();
			return EXP_REFRESH_BTN;
		});
	}

	@Test(priority = 4)
	public void testDeviceUtility() {
		executor.executeTest(TC_DEVICE_UTILITY, EXP_DEVICE_UTILITY, () -> {
			fota.clickDeviceUtility();
			return EXP_DEVICE_UTILITY;
		});
	}

	@Test(priority = 5)
	public void testFota() {
		executor.executeTest(TC_FOTA, EXP_FOTA, () -> {
			fota.clickFota();
			return EXP_FOTA;
		});
	}

	@Test(priority = 6)
	public void testAllComponents() {
		executor.executeTest(TC_VALIDATE_COMPONENTS, EXP_VALIDATE_COMPONENTS, comm::validateComponents);
	}

	@Test(priority = 7)
	public void testAllButtons() {
		executor.executeTest(TC_VALIDATE_BUTTONS, EXP_VALIDATE_BUTTONS, comm::validateButtons);
	}

	@Test(priority = 8)
	public void testCreateManualFotaBatch() {
		executor.executeTest(TC_CREATE_MANUAL_BATCH, EXP_CREATE_MANUAL_BATCH, () -> {
			fota.selectFOTATypeButton("manual");
			fota.createManualFotaBatch(Constants.IMEI);
			return EXP_CREATE_MANUAL_BATCH;
		});
	}

	@Test(priority = 9)
	public void testCreateBulkFotaBatch() {
		executor.executeTest(TC_CREATE_BULK_BATCH, EXP_CREATE_BULK_BATCH, () -> {
			fota.selectFOTATypeButton("bulk");
			comm.clickSampleFileButton();
			fota.createBulkFotaBatch();
			return EXP_CREATE_BULK_BATCH;
		});
	}

	@Test(priority = 10)
	public void testPagination() {
		executor.executeTest(TC_PAGINATION, EXP_PAGINATION, () -> {
			comm.checkPagination();
			return EXP_PAGINATION;
		});
	}

	@Test(priority = 11)
	public void testGetFotaBatchList() {
		executor.executeTest(TC_BATCH_LIST, EXP_BATCH_LIST, fota::getFotaBatchList);
	}

	@Test(priority = 12)
	public void testFotaBatchButtons() {
		executor.executeTest(TC_VALIDATE_BUTTONS, EXP_VALIDATE_BUTTONS, comm::validateButtons);
	}

	@Test(priority = 13)
	public void testVersion() {
		executor.executeTest(TC_VERSION, Constants.EXP_VERSION_TEXT, comm::checkVersion);
	}

	@Test(priority = 14)
	public void testCopyright() {
		executor.executeTest(TC_COPYRIGHT, Constants.EXP_COPYRIGHT_TEXT, comm::checkCopyright);
	}

	@AfterClass
	public void tearDownAssertions() {
		softAssert.assertAll();
	}
}
