package com.aepl.sam.tests;

import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import com.aepl.sam.base.TestBase;
import com.aepl.sam.constants.DeviceDashboardConstants;
import com.aepl.sam.pages.DeviceDashboardPage;
import com.aepl.sam.utils.CommonMethods;
import com.aepl.sam.utils.ExcelUtility;

public class DeviceDashboardPageTest extends TestBase implements DeviceDashboardConstants {
	private DeviceDashboardPage deviceDashboardPage;
	private CommonMethods comm;
	private ExcelUtility excelUtility;
	private SoftAssert softAssert;
	private Executor executor;

	@BeforeClass
	public void setUp() {
		super.setUp();
		this.deviceDashboardPage = new DeviceDashboardPage(driver, wait, action);
		this.comm = new CommonMethods(driver, wait);
		this.softAssert = new SoftAssert();
		this.excelUtility = new ExcelUtility();
		this.executor = new Executor(excelUtility, softAssert);
		this.excelUtility.initializeExcel(DEVICE_DASHBOARD_EXCEL_SHEET);
		logger.info("Setup completed for DeviceDashboardPageTest");
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
	public void testClickNavBar() {
		executor.executeTest(TC_NAV_BAR, EXP_NAV_BAR_URL, deviceDashboardPage::clickNavBar);
	}

	@Test(priority = 4)
	public void testValidateButtons() {
		executor.executeTest(TC_VALIDATE_BUTTONS, EXP_VALIDATE_BUTTONS, comm::validateButtons);
	}

//	@Test(priority = 5)
//	public void testDeviceSearch() {
//		executor.executeTest(TC_SEARCH_DEVICE, EXP_SEARCH_DEVICE, deviceDashboardPage::searchDevice);
//	}

//	@Test(priority = 6)
//	public void testIsSearchInputEnabled() {
//		executor.executeTest(TC_SEARCH_INPUT_ENABLED, EXP_SEARCH_INPUT_ENABLED,
//				() -> deviceDashboardPage.isSearchInputEnabled() ? EXP_SEARCH_INPUT_ENABLED
//						: EXP_SEARCH_INPUT_NOT_ENABLED);
//	}
//
//	@Test(priority = 7)
//	public void testIsSearchInputVisible() {
//		executor.executeTest(TC_SEARCH_INPUT_VISIBLE, EXP_SEARCH_INPUT_VISIBLE,
//				() -> deviceDashboardPage.isSearchInputVisible() ? EXP_SEARCH_INPUT_VISIBLE
//						: EXP_SEARCH_INPUT_NOT_VISIBLE);
//	}
//
//	@Test(priority = 8)
//	public void testIsSearchButtonEnabled() {
//		executor.executeTest(TC_SEARCH_BUTTON_ENABLED, EXP_SEARCH_BUTTON_ENABLED,
//				() -> deviceDashboardPage.isSearchButtonEnabled() ? EXP_SEARCH_BUTTON_ENABLED
//						: EXP_SEARCH_BUTTON_NOT_ENABLED);
//	}
//
//	@Test(priority = 9)
//	public void testIsSearchButtonVisible() {
//		executor.executeTest(TC_SEARCH_BUTTON_VISIBLE, EXP_SEARCH_BUTTON_VISIBLE,
//				() -> deviceDashboardPage.isSearchButtonVisible() ? EXP_SEARCH_BUTTON_VISIBLE
//						: EXP_SEARCH_BUTTON_NOT_VISIBLE);
//	}
//
//	@Test(priority = 10)
//	public void testDeviceStatus() {
//		executor.executeTest(TC_DEVICE_STATUS, EXP_DEVICE_STATUS, deviceDashboardPage::checkDeviceStatus);
//	}
//
//	@Test(priority = 11)
//	public void testValidateComponents() {
//		executor.executeTest(TC_VALIDATE_COMPONENTS, EXP_VALIDATE_COMPONENTS, comm::validateComponents);
//	}
//
//	@Test(priority = 12)
//	public void testPagination() {
//		executor.executeTest(TC_PAGINATION, EXP_PAGINATION, () -> {
//			comm.checkPagination();
//			return EXP_PAGINATION;
//		});
//	}
//
//	@Test(priority = 13)
//	public void testVersion() {
//		executor.executeTest(TC_VERSION, EXP_VERSION, comm::checkVersion);
//	}
//
//	@Test(priority = 14)
//	public void testCopyright() {
//		executor.executeTest(TC_COPYRIGHT, EXP_COPYRIGHT, comm::checkCopyright);
//	}
	@AfterClass
	public void tearDownAssertions() {
		softAssert.assertAll();
	}
}
