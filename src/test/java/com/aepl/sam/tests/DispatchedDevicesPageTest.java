package com.aepl.sam.tests;

import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import com.aepl.sam.base.TestBase;
import com.aepl.sam.constants.Constants;
import com.aepl.sam.constants.DispatchDeviceConstants;
import com.aepl.sam.pages.DispatchedDevicesPage;
import com.aepl.sam.utils.CommonMethods;
import com.aepl.sam.utils.ExcelUtility;

public class DispatchedDevicesPageTest extends TestBase implements DispatchDeviceConstants {
	private ExcelUtility excelUtility;
	private DispatchedDevicesPage dispatchedDevicePage;
	private CommonMethods comm;
	private SoftAssert softAssert;
	private Executor executor;

	@BeforeClass
	public void setUp() {
		super.setUp();
		this.comm = new CommonMethods(driver, wait);
		this.dispatchedDevicePage = new DispatchedDevicesPage(driver, wait, comm);
		this.excelUtility = new ExcelUtility();
		this.softAssert = new SoftAssert();
		this.executor = new Executor(excelUtility, softAssert);
		excelUtility.initializeExcel("Dispatched_Devices_Test");
	}

	@Test(priority = 1)
	public void testCompanyLogo() {
		executor.executeTest(TC_LOGO, LOGO_DISPLAYED,
				() -> comm.verifyWebpageLogo() ? LOGO_DISPLAYED : LOGO_NOT_DISPLAYED);
	}

	@Test(priority = 2)
	public void testPageTitle() {
		executor.executeTest(TC_PAGE_TITLE, EXP_PAGE_TITLE, comm::verifyPageTitle);
	}

	@Test(priority = 3)
	public void navBarLinkTest() {
		executor.executeTest(TC_NAV_BAR_LINK, Constants.DISP_DEVICE_LINK, dispatchedDevicePage::navBarLink);
	}

	@Test(priority = 4)
	public void clickAddDisDeviceTest() {
		executor.executeTest(TC_ADD_DISPATCH_DEVICE, EXP_ADD_DISPATCH_DEVICE_PAGE,
				dispatchedDevicePage::ClickAddDisDevice);
	}

	@Test(priority = 5)
	public void addDisDeviceTest() {
		executor.executeTest(TC_NEW_INPUT_FIELDS, EXP_NEW_INPUT_FIELDS, () -> {
			return dispatchedDevicePage.NewInputFields("add");
		});
	}

	@Test(priority = 6)
	public void searchDeviceTest() {
		executor.executeTest(TC_SEARCH_DEVICE, EXP_SEARCH_DEVICE, dispatchedDevicePage::SearchDevice);
	}

	@Test(priority = 7)
	public void viewDeviceTest() {
		executor.executeTest(TC_VIEW_DEVICE, EXP_VIEW_DEVICE, dispatchedDevicePage::viewDevice);
	}

	@Test(priority = 8)
	public void updateDeviceTest() {
		executor.executeTest(TC_UPDATE_DEVICE, EXP_UPDATE_DEVICE, () -> {
			return dispatchedDevicePage.NewInputFields("update");
		});
	}

	@Test(priority = 9)
	public void searchDeviceTest2() {
		executor.executeTest(TC_SEARCH_DEVICE, EXP_SEARCH_DEVICE, dispatchedDevicePage::SearchDevice);
	}

	@Test(priority = 10)
	public void deleteDeviceTest() {
		executor.executeTest(TC_DELETE_DEVICE, EXP_DELETE_DEVICE, dispatchedDevicePage::DeleteDevice);
	}

	@Test(priority = 11)
	public void testAllButtons() {
		executor.executeTest(TC_VALIDATE_BUTTONS, EXP_VALIDATE_BUTTONS, comm::validateButtons);
	}

	@Test(priority = 12)
	public void testAllComponents() {
		executor.executeTest(TC_COMPONENT_TITLES, EXP_COMPONENT_TITLES, comm::validateComponents);
	}

	@Test(priority = 13)
	public void testPagination() {
		executor.executeTest(TC_PAGINATION, EXP_PAGINATION, () -> {
			comm.checkPagination();
			return EXP_PAGINATION;
		});
	}

	@Test(priority = 14)
	public void testVersion() {
		executor.executeTest(TC_VERSION, EXP_VERSION, comm::checkVersion);
	}

	@Test(priority = 15)
	public void testCopyright() {
		executor.executeTest(TC_COPYRIGHT, EXP_COPYRIGHT, comm::checkCopyright);
	}

	@AfterClass
	public void tearDownAssertions() {
		softAssert.assertAll();
	}
}
