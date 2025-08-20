package com.aepl.sam.tests;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.aepl.sam.base.TestBase;
import com.aepl.sam.constants.Constants;
import com.aepl.sam.constants.DispatchDeviceConstants;
import com.aepl.sam.enums.Result;
import com.aepl.sam.pages.DispatchedDevicesPage;
import com.aepl.sam.utils.CommonMethods;
import com.aepl.sam.utils.ExcelUtility;
import com.google.common.base.Supplier;

public class DispatchedDevicesPageTest extends TestBase implements DispatchDeviceConstants {
	private ExcelUtility excelUtility;
	private DispatchedDevicesPage dispatchedDevicePage;
	private CommonMethods comm;

	@BeforeClass
	public void setUp() {
		super.setUp();
		this.comm = new CommonMethods(driver, wait);
		this.dispatchedDevicePage = new DispatchedDevicesPage(driver, wait, comm);
		this.excelUtility = new ExcelUtility();
		excelUtility.initializeExcel("Dispached_Devices_Test");
	}

	public void executeTest(String testCaseName, String expected, Supplier<String> actualSupplier) {
		String actual = "";
		String result = Result.FAIL.getValue();

		logger.info("Executing test case: { " + testCaseName + " }");
		try {
			actual = actualSupplier.get();
			Assert.assertEquals(actual, expected, testCaseName + " failed!");
			result = expected.equalsIgnoreCase(actual) ? Result.PASS.getValue() : Result.FAIL.getValue();
		} catch (Exception e) {
			logger.error("Exception during execution of: " + testCaseName, e);
			result = Result.ERROR.getValue();
			e.printStackTrace();
		} finally {
			excelUtility.writeTestDataToExcel(testCaseName, expected, actual, result);
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
		executeTest(TC_NAV_BAR_LINK, Constants.DISP_DEVICE_LINK, dispatchedDevicePage::navBarLink);
	}

	@Test(priority = 2)
	public void ClickAddDisDeviceTest() {
		executeTest(TC_ADD_DISPATCH_DEVICE, EXP_ADD_DISPATCH_DEVICE_PAGE, dispatchedDevicePage::ClickManualUpload);

	}

	@Test(priority = 3)
	public void TestAddDisDevice() {

		executeTest(TC_NEW_INPUT_FIELDS, EXP_NEW_INPUT_FIELDS, () -> {
			try {
				return dispatchedDevicePage.NewInputFields("add");
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return "Not able to add device";
		});
	}

	@Test(priority = 4)
	public void TestSearchDevice() {

		executeTest(TC_SEARCH_DIVICE, EXP_SEARCH_DIVICE, () -> {
			return dispatchedDevicePage.SearchDevice();
		});

	}

	@Test(priority = 5)
	public void TestViewDevice() {

		executeTest(TC_VIEW_DEVICE, EXP_VIEW_DEVICE, () -> {
			return dispatchedDevicePage.viewDevice();
		});

	}

	@Test(priority = 6)
	public void TestUpdateDevice() throws InterruptedException {

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
	public void testAllButtons() {
		executeTest(TC_ALL_BTN, EXP_ALL_BTN, comm::validateButtons);
	}

	@Test(priority = 8)
	public void testAllComponents() {

		executeTest(TC_ALL_COMP, EXP_ALL_COMP, comm::validateComponents);

	}
}

/* must have to implement the bulk add of dispatched devices */
