package com.aepl.sam.tests;

import java.util.function.Supplier;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import com.aepl.sam.base.TestBase;
import com.aepl.sam.constants.Constants;
import com.aepl.sam.constants.DeviceDetailsConstants;
import com.aepl.sam.enums.Result;
import com.aepl.sam.pages.DeviceDetailsPage;
import com.aepl.sam.utils.CommonMethods;
import com.aepl.sam.utils.ExcelUtility;

public class DeviceDetailsPageTest extends TestBase implements DeviceDetailsConstants {
	private DeviceDetailsPage deviceDetails;
	private CommonMethods comm;
	private ExcelUtility excelUtility;
	private SoftAssert softAssert;

	@BeforeClass
	public void setUp() {
		super.setUp();
		this.comm = new CommonMethods(driver, wait);
		this.deviceDetails = new DeviceDetailsPage(driver, wait, comm);
		this.excelUtility = new ExcelUtility();
		this.softAssert = new SoftAssert();
		excelUtility.initializeExcel(DEVICE_DETAILS_EXCEL_SHEET);
		logger.info("Setup completed for DeviceDetailsPageTest");
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

	@Test(priority = 1)
	public void testCompanyLogo() {
		executeTest(TC_LOGO, LOGO_DISPLAYED, () -> comm.verifyWebpageLogo() ? LOGO_DISPLAYED : LOGO_NOT_DISPLAYED);
	}

	@Test(priority = 2)
	public void testPageTitle() {
		executeTest(TC_PAGE_TITLE, EXP_PAGE_TITLE, comm::verifyPageTitle);
	}

	@Test(priority = 3)
	public void testRefreshButton() {
		executeTest(TC_REFRESH_BUTTON, EXP_REFRESH_CLICKED, () -> {
			comm.clickRefreshButton();
			return EXP_REFRESH_CLICKED;
		});
	}

	@Test(priority = 4)
	public void testButtons() {
		executeTest(TC_ALL_BUTTONS, EXP_ALL_BUTTONS, comm::validateButtons);
	}

	@Test(priority = 5)
	public void testComponentTitles() {
		executeTest(TC_COMPONENT_TITLES, EXP_COMPONENT_TITLES, comm::validateComponents);
	}

//	@Test(priority = 6)
//	public void testAllCards1() {
//		executeTest(TC_ALL_CARDS, EXP_CARDS, comm::validateCards);
//	}

	@Test(priority = 7)
	public void testClickOnDeviceActivityBarGraph() {
		executeTest(TC_BAR_GRAPH, EXP_BAR_GRAPH, () -> {
			if (deviceDetails.isBarGraphVisible()) {
				String actual = deviceDetails.clickOnDeviceActivityBarGraph();
				return actual;
			} else {
				logger.info("Bar Graph is not visible, skipping test.");
				return "Bar Graph not visible";
			}
		});
	}

	@Test(priority = 8)
	public void testSearchAndViewDevice() {
		executeTest(TC_SEARCH_VIEW_DEVICE, EXP_SEARCH_VIEW, () -> {
			deviceDetails.searchAndViewDevice();
			return EXP_SEARCH_VIEW;
		});
	}

	@Test(priority = 9)
	public void testAllButtons() {
		executeTest(TC_ALL_BUTTONS, EXP_ALL_BUTTONS, comm::validateButtons);
	}

	@Test(priority = 10)
	public void testComponentTitle() {
		executeTest(TC_COMPONENT_TITLES, EXP_COMPONENT_TITLES, comm::validateComponents);
	}

	@Test(priority = 11)
	public void testAllCards() {
		executeTest(TC_ALL_CARDS, EXP_CARDS, comm::validateCards);
	}

	@Test(priority = 12)
	public void testAllComponentDetails() {
		executeTest(TC_COMPONENT_DETAILS, EXP_COMPONENT_DETAILS,
				() -> deviceDetails.allComponentDetails() ? EXP_COMPONENT_DETAILS : "No Component visible");
	}

	@Test(priority = 13)
	public void testvalidateExportButton() {
		executeTest(TC_EXPORT_BUTTON, EXP_EXPORT, () -> comm.validateExportButton() ? EXP_EXPORT : "ERROR");
	}

	@Test(priority = 14)
	public void testViewLoginPacket() {
		executeTest(TC_VIEW_LOGIN_PACKET, EXP_VIEW_LOGIN, deviceDetails::viewLoginPacket);
	}

//	@Test(priority = 15)
	public void testHealthPacket() {
		executeTest(TC_HEALTH_PACKET, EXP_HEALTH_RESULT, () -> {
			if (deviceDetails.isHealthPacketVisible()) {
				return deviceDetails.viewHealthPacket();
			} else {
				logger.info("Health Packet is not visible, skipping test.");
				return "Health Packet not visible";
			}
		});
	}

	@Test(priority = 16)
	public void testPagination() {
		executeTest(TC_PAGINATION, EXP_PAGINATION, () -> {
			comm.checkPagination();
			return EXP_PAGINATION;
		});
	}

	@Test(priority = 17)
	public void testVersion() {
		executeTest(TC_VERSION, Constants.EXP_VERSION_TEXT, comm::checkVersion);
		logger.info("Version test executed successfully.");
	}

	@Test(priority = 18)
	public void testCopyright() {
		executeTest(TC_COPYRIGHT, Constants.EXP_COPYRIGHT_TEXT, comm::checkCopyright);
		logger.info("Copyright test executed successfully.");
	}
}
