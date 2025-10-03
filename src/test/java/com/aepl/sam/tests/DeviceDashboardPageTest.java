package com.aepl.sam.tests;

import java.util.Arrays;
import java.util.List;

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
		executor.executeTest(TC_PAGE_LOGO, EXP_LOGO_DISPLAYED, () -> comm.verifyWebpageLogo() ? true : false);
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
	public void testAllComponents() {
		executor.executeTest("Verify all components on page {Device Dashboard} ",
				"All components are displayed and validated successfully.", comm::validateComponents);
	}

//	@Test(priority = 5)
	public void testValidateButtons() {
		executor.executeTest(TC_VALIDATE_BUTTONS, EXP_VALIDATE_BUTTONS, comm::validateButtons);
	}

	// Validate all cards
	@Test(priority = 6)
	public void testValidateAllCards() {
		executor.executeTest("Test all cards visible", true, deviceDashboardPage::validateCardAreVisible);
	}

	// Validate the graph visibility
	@Test(priority = 7)
	public void testValidateGraph() {
		executor.executeTest("Test graph visible", true, deviceDashboardPage::validateGraphIsVisible);
	}

	// validate the graph click and headers matches with the tables headers
	@Test(priority = 8)
	public void testValidateGraphClick() {
		executor.executeTest("Test graph click and headers", true, deviceDashboardPage::validateGraphClick);
	}

	// Validate the total production devices table
	@Test(priority = 9)
	public void testTotalProductionDevicesTableHeaders() {
		List<String> totalProductionDevicesTableHeadersExpected = Arrays.asList("UIN NO.", "IMEI NO.", "ICCID NO.",
				"MODEL NAME.", "ACTION");
		executor.executeTest("Test total production devices table", totalProductionDevicesTableHeadersExpected,
				deviceDashboardPage::validateTotalProductionDevicesTableHeaders);
	}

	@Test(priority = 10)
	public void testTotalProductionDevicesTableButtons() {
		executor.executeTest("Test total production devices table buttons", true,
				deviceDashboardPage::validateTotalProductionDevicesTableButtons);
	}

	// validate search button is visible
	@Test(priority = 11)
	public void testIsSearchButtonVisible() {
		executor.executeTest(TC_SEARCH_BUTTON_VISIBLE, EXP_SEARCH_BUTTON_VISIBLE,
				() -> deviceDashboardPage.isSearchButtonVisible() ? EXP_SEARCH_BUTTON_VISIBLE
						: EXP_SEARCH_BUTTON_NOT_VISIBLE);
	}

	// validate search button is enabled
	@Test(priority = 12)
	public void testIsSearchButtonEnabled() {
		executor.executeTest(TC_SEARCH_BUTTON_ENABLED, EXP_SEARCH_BUTTON_ENABLED,
				() -> deviceDashboardPage.isSearchButtonEnabled() ? EXP_SEARCH_BUTTON_ENABLED
						: EXP_SEARCH_BUTTON_NOT_ENABLED);
	}

	// validate search input is visible
	@Test(priority = 13)
	public void testIsSearchInputVisible() {
		executor.executeTest(TC_SEARCH_INPUT_VISIBLE, EXP_SEARCH_INPUT_VISIBLE,
				() -> deviceDashboardPage.isSearchInputVisible() ? EXP_SEARCH_INPUT_VISIBLE
						: EXP_SEARCH_INPUT_NOT_VISIBLE);
	}

	// validate search input is enabled
	@Test(priority = 14)
	public void testIsSearchInputEnabled() {
		executor.executeTest(TC_SEARCH_INPUT_ENABLED, EXP_SEARCH_INPUT_ENABLED,
				() -> deviceDashboardPage.isSearchInputEnabled() ? EXP_SEARCH_INPUT_ENABLED
						: EXP_SEARCH_INPUT_NOT_ENABLED);
	}

	// validate the search functionality
	@Test(priority = 15)
	public void testDeviceSearch() {
		executor.executeTest("Test search functionality", true, deviceDashboardPage::searchDevice);
	}

	// validate the export button is visible
	@Test(priority = 16)
	public void testIsExportButtonVisible() {
		executor.executeTest("Test export button visible", true, deviceDashboardPage::isExportButtonVisible);
	}

	// validate the export button is enabled
	@Test(priority = 17)
	public void testIsExportButtonEnabled() {
		executor.executeTest("Test export button enabled", true, deviceDashboardPage::isExportButtonEnabled);
	}

	// validate the export functionality
	@Test(priority = 18)
	public void testExportFunctionality() {
		executor.executeTest("Test export functionality", true, comm::validateExportButton);
	}

	// validate the pagination of the total production devices table
	@Test(priority = 19)
	public void testPagination() {
		executor.executeTest(TC_PAGINATION, EXP_PAGINATION, () -> {
			comm.checkPagination();
			return EXP_PAGINATION;
		});
	}

	// from test case 9 to 19 is for the total production devices table and also
	// need to add for total dispached devices table. those are the same

	@Test(priority = 20)
	public void testTotalDispatchedDevicesTableHeaders() {
		List<String> totalDispatchedDevicesTableHeadersExpected = Arrays.asList("UIN NO.", "IMEI NO.", "ICCID NO.",
				"MODEL NAME.", "ACTION");
		executor.executeTest("Test total dispatched devices table", totalDispatchedDevicesTableHeadersExpected,
				deviceDashboardPage::validateTotalDispatchedDevicesTableHeaders);
	}

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
