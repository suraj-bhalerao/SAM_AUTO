package com.aepl.sam.tests;

import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import com.aepl.sam.base.TestBase;
import com.aepl.sam.constants.Constants;
import com.aepl.sam.constants.DeviceDetailsConstants;
import com.aepl.sam.pages.DeviceDetailsPage;
import com.aepl.sam.utils.CommonMethods;
import com.aepl.sam.utils.ExcelUtility;

public class DeviceDetailsPageTest extends TestBase implements DeviceDetailsConstants {
	private DeviceDetailsPage deviceDetails;
	private CommonMethods comm;
	private ExcelUtility excelUtility;
	private SoftAssert softAssert;
	private Executor executor;

	@BeforeClass
	public void setUp() {
		super.setUp();
		this.comm = new CommonMethods(driver, wait);
		this.deviceDetails = new DeviceDetailsPage(driver, wait, comm);
		this.excelUtility = new ExcelUtility();
		this.softAssert = new SoftAssert();
		this.executor = new Executor(excelUtility, softAssert);
		excelUtility.initializeExcel(DEVICE_DETAILS_EXCEL_SHEET);
		logger.info("Setup completed for DeviceDetailsPageTest");
	}

	// Testing for company logo display
	@Test(priority = 1)
	public void testCompanyLogo() {
		executor.executeTest(TC_LOGO, LOGO_DISPLAYED,
				() -> comm.verifyWebpageLogo() ? LOGO_DISPLAYED : LOGO_NOT_DISPLAYED);
	}

	// Testing for page title
	@Test(priority = 2)
	public void testPageTitle() {
		executor.executeTest(TC_PAGE_TITLE, EXP_PAGE_TITLE, comm::verifyPageTitle);
	}

	// Testing for refresh button functionality
	@Test(priority = 3)
	public void testRefreshButton() {
		executor.executeTest(TC_REFRESH_BUTTON, EXP_REFRESH_CLICKED, () -> {
			comm.clickRefreshButton();
			return EXP_REFRESH_CLICKED;
		});
	}

	// Testing for all buttons on the page
	@Test(priority = 4)
	public void testButtons() {
		executor.executeTest(TC_ALL_BUTTONS, EXP_ALL_BUTTONS, comm::validateButtons);
	}

	// Testing for component titles on the page
	@Test(priority = 5)
	public void testComponentTitles() {
		executor.executeTest(TC_COMPONENT_TITLES, EXP_COMPONENT_TITLES, comm::validateComponents);
	}

	// Testing for the bar graph on the page
	@Test(priority = 6)
	public void testClickOnDeviceActivityBarGraph() {
		executor.executeTest(TC_BAR_GRAPH, EXP_BAR_GRAPH, () -> {
			if (deviceDetails.isBarGraphVisible()) {
				String actual = deviceDetails.clickOnDeviceActivityBarGraph();
				return actual;
			} else {
				logger.info("Bar Graph is not visible, skipping test.");
				return "Bar Graph not visible";
			}
		});
	}

	// ************************************************************************* //

	// validate search button is visible
	@Test(priority = 7)
	public void testIsSearchButtonVisible() {
		executor.executeTest(TC_SEARCH_BUTTON_VISIBLE, EXP_SEARCH_BUTTON_VISIBLE,
				() -> deviceDetails.isSearchButtonVisible() ? EXP_SEARCH_BUTTON_VISIBLE
						: EXP_SEARCH_BUTTON_NOT_VISIBLE);
	}

	// validate search button is enabled
	@Test(priority = 8)
	public void testIsSearchButtonEnabled() {
		executor.executeTest(TC_SEARCH_BUTTON_ENABLED, EXP_SEARCH_BUTTON_ENABLED,
				() -> deviceDetails.isSearchButtonEnabled() ? EXP_SEARCH_BUTTON_ENABLED
						: EXP_SEARCH_BUTTON_NOT_ENABLED);
	}

	// validate search input is visible
	@Test(priority = 9)
	public void testIsSearchInputVisible() {
		executor.executeTest(TC_SEARCH_INPUT_VISIBLE, EXP_SEARCH_INPUT_VISIBLE,
				() -> deviceDetails.isSearchInputVisible() ? EXP_SEARCH_INPUT_VISIBLE : EXP_SEARCH_INPUT_NOT_VISIBLE);
	}

	// validate search input is enabled
	@Test(priority = 10)
	public void testIsSearchInputEnabled() {
		executor.executeTest(TC_SEARCH_INPUT_ENABLED, EXP_SEARCH_INPUT_ENABLED,
				() -> deviceDetails.isSearchInputEnabled() ? EXP_SEARCH_INPUT_ENABLED : EXP_SEARCH_INPUT_NOT_ENABLED);
	}

	// validate the search functionality
	@Test(priority = 11)
	public void testDeviceSearch() {
		executor.executeTest("Test search functionality", true, deviceDetails::searchDevice);
	}

	// validate the table headers
	@Test(priority = 12)
	public void testTableHeaders() {
		List<String> expectedHeaders = Arrays.asList("UIN NO.", "IMEI NO.", "ICCID NO.", "MODEL NAME.", "LOG IN TIME",
				"ACTION");
		executor.executeTest("Test Table Headers of the searched device: ", expectedHeaders,
				deviceDetails::validateTableHeaders);
	}

	// vlaidate the table data
	@Test(priority = 13)
	public void testTableData() {
		Map<String, String> expectedRow = new LinkedHashMap<>();
		expectedRow.put("UIN NO.", "ACON4SA310213796709");
		expectedRow.put("IMEI NO.", "867950076683091");
		expectedRow.put("ICCID NO.", "89916440844825969900");
		expectedRow.put("MODEL NAME.", "Sam");
		expectedRow.put("LOG IN TIME", "--");
		expectedRow.put("ACTION", "visibility");

		List<Map<String, String>> expectedData = Collections.singletonList(expectedRow);
		executor.executeTest("Test Table Data of the searched device: ", expectedData, deviceDetails::getTableData);
	}

	// validate the view button functionality
	@Test(priority = 14)
	public void testViewButtonEnabled() {
		executor.executeTest("Test view button functionality: ", true, deviceDetails::isViewButtonEnabled);
	}

	// Testing for searching and viewing a device
	@Test(priority = 15)
	public void testViewDevice() {
		executor.executeTest(TC_SEARCH_VIEW_DEVICE, EXP_SEARCH_VIEW, () -> {
			deviceDetails.viewDevice();
			return EXP_SEARCH_VIEW;
		});
	}

	// Validate the page title after viewing a device
	@Test(priority = 16)
	public void testPageTitleAfterViewingDevice() {
		executor.executeTest("Test Page Title of the device details page ", "Device Details",
				deviceDetails::validatePageTitle);
	}

	// Testing for all buttons on the page again after viewing a device
	@Test(priority = 17)
	public void testAllButtons() {
		executor.executeTest(TC_ALL_BUTTONS, EXP_ALL_BUTTONS, comm::validateButtons);
	}

	// Validationg all components on the page
	@Test(priority = 18)
	public void testComponentTitle() {
		executor.executeTest(TC_COMPONENT_TITLES, EXP_COMPONENT_TITLES, comm::validateComponents);
	}

	// Validate the imei displayed on the device details page is correct
	@Test(priority = 19)
	public void testValidateIMEIOnDeviceDetailsPage() {
		executor.executeTest("Validate IMEI on Device Details Page", Constants.IMEI,
				deviceDetails::validateIMEIOnDeviceDetailsPage);
	}

	// Validate the input field of the imei is enabled
	@Test(priority = 20)
	public void testIsIMEIInputEnabled() {
		executor.executeTest("Validate IMEI input field is enabled", true, deviceDetails::isIMEIInputVisible);
	}

	// Validate the input field of the imei is clickable
	@Test(priority = 21)
	public void testIsIMEIInputClickable() {
		executor.executeTest("Validate IMEI input field is clickable", true, deviceDetails::isIMEIInputClickable);
	}

	// Validate the wrong input in imei field gives error/ toast message error
	@Test(priority = 22)
	public void testInvalidIMEIInput() {
		executor.executeTest("Validate invalid IMEI input gives error", true, deviceDetails::validateInvalidIMEIInput);
	}

	// Validate the search button is visible
	@Test(priority = 23)
	public void testIsSearchButtonVisibleAfterViewingDevice() {
		executor.executeTest(TC_SEARCH_BUTTON_VISIBLE, EXP_SEARCH_BUTTON_VISIBLE,
				() -> deviceDetails.isSearchButtonVisible() ? EXP_SEARCH_BUTTON_VISIBLE
						: EXP_SEARCH_BUTTON_NOT_VISIBLE);
	}

	// Validate the search button is enabled
	@Test(priority = 24)
	public void testIsSearchButtonEnabledAfterViewingDevice() {
		executor.executeTest(TC_SEARCH_BUTTON_ENABLED, EXP_SEARCH_BUTTON_ENABLED,
				() -> deviceDetails.isSearchButtonEnabled() ? EXP_SEARCH_BUTTON_ENABLED
						: EXP_SEARCH_BUTTON_NOT_ENABLED);
	}

	// Validate the valid imei searched
	@Test(priority = 25)
	public void testValidIMEISearch() {
		executor.executeTest("Validate valid IMEI search", Constants.IMEI, deviceDetails::validateValidIMEISearch);
	}

	// Validate the FOTA button is enalbed on the top of the page along with the
	// page header
	@Test(priority = 26)
	public void testIsFOTAButtonEnabled() {
		executor.executeTest("Validate FOTA button is enabled", true, deviceDetails::isFOTAButtonEnabled);
	}

	// Validate the FOTA button link is clickable on the top of the page along with
	// the
	@Test(priority = 27)
	public void testIsFOTAButtonClickable() {
		executor.executeTest("Validate FOTA button is clickable", true, deviceDetails::isFOTAButtonClickable);
	}

	// Validate the OTA button is enalbed on the top of the page along with the
	// page header
	@Test(priority = 28)
	public void testIsOTAButtonEnabled() {
		executor.executeTest("Validate OTA button is enabled", true, deviceDetails::isOTAButtonEnabled);
	}

	// Validate the OTA button link is clickable on the top of the page along with
	// the
//	@Test(priority = 29)
	public void testIsOTAButtonClickable() {
		executor.executeTest("Validate OTA button is clickable", true, deviceDetails::isOTAButtonClickable);
	}

	// Validate the info Cards are displayed on the top of the page
	@Test(priority = 30)
	public void testAreInfoCardsVisible() {
		executor.executeTest("Validate info cards are visible", true, deviceDetails::areInfoCardsVisible);
	}

	// Validate the info Cards are enabled on the top of the page
	@Test(priority = 31)
	public void testAreInfoCardsEnabled() {
		executor.executeTest("Validate info cards are enabled", true, deviceDetails::areInfoCardsEnabled);
	}

	// Testing for all cards on the page -- info cards on the top of the page which
	// shows the IGN,MAINS, TAMPER, PWR, etc
	@Test(priority = 32)
	public void testAllCards() {
		executor.executeTest(TC_ALL_CARDS, EXP_CARDS, comm::validateCards);
	}

	// Test all cards headers is valid with the expected headers
	@Test(priority = 33)
	public void testAllCardsHeaders() {
		List<String> expectedHeaders = Arrays.asList("IGNITION ON/OFF", "MAINS ON/OFF", "EMERGENCY ON/OFF",
				"TAMPER OPEN/CLOSE", "ACC CALIBRATION ON/OF", "WIRE CUT");
		executor.executeTest("Test All Cards Headers: ", expectedHeaders, deviceDetails::validateAllCardsHeaders);
	}

	// // Testing for all component details on the page
//	@Test(priority = 12)
//	public void testAllComponentDetails() {
//		executor.executeTest(TC_COMPONENT_DETAILS, EXP_COMPONENT_DETAILS,
//				() -> deviceDetails.allComponentDetails() ? EXP_COMPONENT_DETAILS : "No Component visible");
//	}
//
//	// Testing for export button functionality
//	@Test(priority = 13)
//	public void testvalidateExportButton() {
//		executor.executeTest(TC_EXPORT_BUTTON, EXP_EXPORT, () -> comm.validateExportButton() ? EXP_EXPORT : "ERROR");
//	}
//
//	// Testing for viewing the login packet of the device
//	@Test(priority = 14)
//	public void testViewLoginPacket() {
//		executor.executeTest(TC_VIEW_LOGIN_PACKET, EXP_VIEW_LOGIN, deviceDetails::viewLoginPacket);
//	}
//
//	// Testing for viewing the health packet of the device
// 	@Test(priority = 15)
//	public void testHealthPacket() {
//		executor.executeTest(TC_HEALTH_PACKET, EXP_HEALTH_RESULT, () -> {
//			if (deviceDetails.isHealthPacketVisible()) {
//				return deviceDetails.viewHealthPacket();
//			} else {
//				logger.info("Health Packet is not visible, skipping test.");
//				return "Health Packet not visible";
//			}
//		});
//	}
//
//	// Testing for pagination functionality
//	@Test(priority = 16)
//	public void testPagination() {
//		executor.executeTest(TC_PAGINATION, EXP_PAGINATION, () -> {
//			comm.checkPagination();
//			return EXP_PAGINATION;
//		});
//	}
//
//	// Testing for version text on the page
//	@Test(priority = 17)
//	public void testVersion() {
//		executor.executeTest(TC_VERSION, Constants.EXP_VERSION_TEXT, comm::checkVersion);
//		logger.info("Version test executed successfully.");
//	}
//
//	// Testing for copyright text on the page
//	@Test(priority = 18)
//	public void testCopyright() {
//		executor.executeTest(TC_COPYRIGHT, Constants.EXP_COPYRIGHT_TEXT, comm::checkCopyright);
//		logger.info("Copyright test executed successfully.");
//	}

	@AfterClass
	public void tearDownAssertions() {
		softAssert.assertAll();
	}
}
