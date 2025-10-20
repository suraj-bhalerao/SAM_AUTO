package com.aepl.sam.tests;

import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import com.aepl.sam.base.TestBase;
import com.aepl.sam.constants.Constants;
import com.aepl.sam.constants.ProductionDeviceConstants;
import com.aepl.sam.pages.CommonMethods;
import com.aepl.sam.pages.ProductionDevicePage;
import com.aepl.sam.utils.ExcelUtility;

public class ProductionDevicePageTest extends TestBase implements ProductionDeviceConstants {

	private ProductionDevicePage productionDevicePage;
	private CommonMethods comm;
	private ExcelUtility excelUtility;
	private SoftAssert softAssert;
	private Executor executor;

	@BeforeClass
	public void setUp() {
		super.setUp();
		this.productionDevicePage = new ProductionDevicePage(driver, wait);
		this.comm = new CommonMethods(driver, wait);
		this.excelUtility = new ExcelUtility();
		this.softAssert = new SoftAssert();
		this.executor = new Executor(excelUtility, softAssert);
		this.excelUtility.initializeExcel(DEVICE_EXCEL_SHEET);
		logger.info("Setup completed for ProductionDevicePageTest");
	}

	@Test(priority = -1)
	public void testCompanyLogo() {
		executor.executeTest(TC_PAGE_LOGO, EXP_LOGO_DISPLAYED,
				() -> comm.verifyWebpageLogo() ? EXP_LOGO_DISPLAYED : "Logo Not Displayed");
	}

	@Test(priority = 0)
	public void testPageTitle() {
		executor.executeTest(TC_PAGE_TITLE, EXP_PAGE_TITLE, comm::verifyPageTitle);
	}

	@Test(priority = 1)
	public void testNavBarLink() {
		executor.executeTest(TC_NAV_BAR, Constants.PROD_DEVICE_LINK, () -> {
			return productionDevicePage.navBarLink();
		});
	}

	@Test(priority = 2)
	public void testClickAddProdDevice() {
		executor.executeTest(TC_CLICK_ADD_DEVICE, EXP_CLICK_ADD_DEVICE, productionDevicePage::ClickAddProdDevice);
	}

	@Test(priority = 3)
	public void testAddProdDevice() {
		executor.executeTest(TC_ADD_DEVICE, EXP_ADD_DEVICE, () -> productionDevicePage.NewInputFields("add"));
	}

	@Test(priority = 4)
	public void testSearchDevice() {
		executor.executeTest(TC_SEARCH_DEVICE, EXP_SEARCH_DEVICE, productionDevicePage::searchDevice);
	}

	@Test(priority = 5)
	public void testViewDevice() {
		executor.executeTest(TC_VIEW_DEVICE, EXP_VIEW_DEVICE, productionDevicePage::viewDevice);
	}

	@Test(priority = 6)
	public void testUpdateDevice() {
		executor.executeTest(TC_UPDATE_DEVICE, EXP_UPDATE_DEVICE, () -> productionDevicePage.NewInputFields("update"));
	}

	@Test(priority = 7)
	public void testSearchDeviceAgain() {
		executor.executeTest(TC_SEARCH_DEVICE, EXP_SEARCH_DEVICE, productionDevicePage::searchDevice);
	}

	@Test(priority = 8)
	public void testDeleteDevice() {
		executor.executeTest(TC_DELETE_DEVICE, EXP_DELETE_DEVICE, productionDevicePage::DeleteDevice);
	}

	@Test(priority = 9)
	public void testPagination() {
		executor.executeTest(TC_PAGINATION, EXP_PAGINATION, () -> {
			comm.checkPagination();
			return EXP_PAGINATION;
		});
	}

	@Test(priority = 10)
	public void testVersion() {
		executor.executeTest(TC_VERSION, Constants.EXP_VERSION_TEXT, comm::checkVersion);
	}

	@Test(priority = 11)
	public void testCopyright() {
		executor.executeTest(TC_COPYRIGHT, Constants.EXP_COPYRIGHT_TEXT, comm::checkCopyright);
	}

	@AfterClass
	public void tearDownAssertions() {
		softAssert.assertAll();
	}
}
