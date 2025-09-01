package com.aepl.sam.tests;

import java.util.function.Supplier;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import com.aepl.sam.base.TestBase;
import com.aepl.sam.constants.Constants;
import com.aepl.sam.constants.DispatchDeviceConstants;
import com.aepl.sam.enums.Result;
import com.aepl.sam.pages.DispatchedDevicesPage;
import com.aepl.sam.utils.CommonMethods;
import com.aepl.sam.utils.ExcelUtility;

public class DispatchedDevicesPageTest extends TestBase implements DispatchDeviceConstants {
	private ExcelUtility excelUtility;
	private DispatchedDevicesPage dispatchedDevicePage;
	private CommonMethods comm;
	private SoftAssert softAssert;

	@BeforeClass
	public void setUp() {
		super.setUp();
		this.comm = new CommonMethods(driver, wait);
		this.dispatchedDevicePage = new DispatchedDevicesPage(driver, wait, comm);
		this.excelUtility = new ExcelUtility();
		this.softAssert = new SoftAssert();
		excelUtility.initializeExcel("Dispatched_Devices_Test");
	}

	private void executeTest(String testCaseName, String expected, Supplier<String> actualSupplier) {
		String actual = "";
		String result = Result.FAIL.getValue();

		logger.info("Executing test case: {}", testCaseName);

		try {
			actual = actualSupplier.get();
			softAssert.assertEquals(actual, expected, testCaseName + " failed!");
			result = expected.equalsIgnoreCase(actual) ? Result.PASS.getValue() : Result.FAIL.getValue();
		} catch (Exception e) {
			logger.error("Error in test case {}: {}", testCaseName, e.getMessage(), e);
			result = Result.ERROR.getValue();
		} finally {
			excelUtility.writeTestDataToExcel(testCaseName, expected, actual, result);
			softAssert.assertAll();
		}
	}

	@Test(priority = -1)
	public void testCompanyLogo() {
		executeTest(TC_LOGO, LOGO_DISPLAYED, () -> comm.verifyWebpageLogo() ? LOGO_DISPLAYED : LOGO_NOT_DISPLAYED);
	}

	@Test(priority = 0)
	public void testPageTitle() {
		executeTest(TC_PAGE_TITLE, EXP_PAGE_TITLE, comm::verifyPageTitle);
	}

	@Test(priority = 1)
	public void navBarLinkTest() {
		executeTest(TC_NAV_BAR_LINK, Constants.DEVICE_LINK, dispatchedDevicePage::navBarLink);
	}

	@Test(priority = 2)
	public void clickAddDisDeviceTest() {
		executeTest(TC_ADD_DISPATCH_DEVICE, EXP_ADD_DISPATCH_DEVICE_PAGE, dispatchedDevicePage::ClickAddDisDevice);
	}

	@Test(priority = 3)
	public void addDisDeviceTest() {
		executeTest(TC_NEW_INPUT_FIELDS, EXP_NEW_INPUT_FIELDS, () -> {
			try {
				return dispatchedDevicePage.NewInputFields("add");
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			return "Not able to add device";
		});
	}

	@Test(priority = 4)
	public void searchDeviceTest() {
		executeTest(TC_SEARCH_DEVICE, EXP_SEARCH_DEVICE, dispatchedDevicePage::SearchDevice);
	}

	@Test(priority = 5)
	public void viewDeviceTest() {
		executeTest(TC_VIEW_DEVICE, EXP_VIEW_DEVICE, dispatchedDevicePage::viewDevice);
	}

	@Test(priority = 6)
	public void updateDeviceTest() {
		executeTest(TC_UPDATE_DEVICE, EXP_UPDATE_DEVICE, () -> {
			try {
				return dispatchedDevicePage.NewInputFields("update");
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			return "Not able to update device";
		});
	}

	@Test(priority = 7)
	public void searchDeviceTest2() {
		executeTest(TC_SEARCH_DEVICE, EXP_SEARCH_DEVICE, dispatchedDevicePage::SearchDevice);
	}

	@Test(priority = 8)
	public void deleteDeviceTest() {
		executeTest(TC_DELETE_DEVICE, EXP_DELETE_DEVICE, dispatchedDevicePage::DeleteDevice);
	}

	@Test(priority = 9)
	public void testAllButtons() {
		executeTest(TC_VALIDATE_BUTTONS, EXP_VALIDATE_BUTTONS, comm::validateButtons);
	}

	@Test(priority = 10)
	public void testAllComponents() {
		executeTest(TC_COMPONENT_TITLES, EXP_COMPONENT_TITLES, comm::validateComponents);
	}

	@Test(priority = 11)
	public void testPagination() {
		executeTest(TC_PAGINATION, EXP_PAGINATION, () -> {
			comm.checkPagination();
			return EXP_PAGINATION;
		});
	}

	@Test(priority = 12)
	public void testVersion() {
		executeTest(TC_VERSION, EXP_VERSION, comm::checkVersion);
	}

	@Test(priority = 13)
	public void testCopyright() {
		executeTest(TC_COPYRIGHT, EXP_COPYRIGHT, comm::checkCopyright);
	}
}
