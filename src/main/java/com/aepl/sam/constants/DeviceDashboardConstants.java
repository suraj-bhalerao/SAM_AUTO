package com.aepl.sam.constants;

import java.util.List;

public interface DeviceDashboardConstants {

	// =========================================================
	// 🧾 Excel Sheet Information
	// =========================================================
	String DEVICE_DASHBOARD_EXCEL_SHEET = "DeviceDashboardTests";

	// =========================================================
	// 🧪 TEST CASE NAMES (TC_)
	// =========================================================
	// --- General Tests ---
	String TC_PAGE_LOGO = "Verify Company Logo";
	String TC_PAGE_TITLE = "Verify Page Title";
	String TC_NAV_BAR = "Verify Navigation Bar Link";
	String TC_VALIDATE_BUTTONS = "Validate Buttons";
	String TC_VALIDATE_COMPONENTS = "Validate Components";
	String TC_PAGINATION = "Verify Pagination";
	String TC_VERSION = "Verify Application Version";
	String TC_COPYRIGHT = "Verify Copyright";
	String TC_ALL_CARDS_VISIBILITY = "Test all cards visible";
	String TC_ALL_GRAPHS_VISIBILITY = "Test graph visible";
	String TC_GRAPH_CLICK_HEADERS = "Test graph click and headers";

	// --- Search Functionality ---
	String TC_SEARCH_DEVICE = "Search Device";
	String TC_SEARCH_INPUT_VISIBLE = "Search Input Visible";
	String TC_SEARCH_INPUT_ENABLED = "Search Input Enabled";
	String TC_SEARCH_BUTTON_VISIBLE = "Search Button Visible";
	String TC_SEARCH_BUTTON_ENABLED = "Search Button Enabled";

	// =========================================================
	// 🎯 EXPECTED RESULTS (EXP_)
	// =========================================================
	// --- General Expectations ---
	Boolean EXP_LOGO_DISPLAYED = true;
	String EXP_PAGE_TITLE = "AEPL Sampark Diagnostic Cloud";
	String EXP_NAV_BAR_URL = "Link is verified";
	String EXP_VALIDATE_BUTTONS = "All buttons are displayed and enabled successfully.";
	String EXP_VALIDATE_COMPONENTS = "All components are displayed and validated successfully.";
	String EXP_PAGINATION = "Pagination Working";
	String EXP_VERSION = "Version: 1.5.0";
	String EXP_COPYRIGHT = "Accolade Electronics Pvt. Ltd.";
	String EXP_DEVICE_STATUS = "Device Status Verified";

	// --- Search Field Expectations ---
	String EXP_SEARCH_DEVICE = "Device Found";
	String EXP_SEARCH_INPUT_ENABLED = "Search Input Enabled";
	String EXP_SEARCH_INPUT_NOT_ENABLED = "Search Input Not Enabled";
	String EXP_SEARCH_INPUT_VISIBLE = "Search Input Visible";
	String EXP_SEARCH_INPUT_NOT_VISIBLE = "Search Input Not Visible";
	String EXP_SEARCH_BUTTON_ENABLED = "Search Button Enabled";
	String EXP_SEARCH_BUTTON_NOT_ENABLED = "Search Button Not Enabled";
	String EXP_SEARCH_BUTTON_VISIBLE = "Search Button Visible";
	String EXP_SEARCH_BUTTON_NOT_VISIBLE = "Search Button Not Visible";

	// --- Export Button Expectations ---
	String EXP_EXPORT_BUTTON_VISIBLE = "Export Button Visible";
	String EXP_EXPORT_BUTTON_ENABLED = "Export Button Enabled";
	String EXP_EXPORT_FUNCTIONALITY = "Export Functionality Working";

	// =========================================================
	// 📊 TABLE HEADERS
	// =========================================================
	List<String> TOTAL_PRODUCTION_DEVICES_HEADERS = List.of("UIN NO.", "IMEI NO.", "ICCID NO.", "MODEL NAME.",
			"ACTION");

	List<String> TOTAL_DISPATCHED_DEVICES_HEADERS = List.of("UIN NO.", "IMEI NO.", "ICCID NO.", "MODEL NAME.",
			"CUSTOMER NAME", "ACTION");

	List<String> TOTAL_INSTALLED_DEVICES_HEADERS = List.of("UIN NO.", "IMEI NO.", "ICCID NO.", "CHASSIS NO.",
			"MODEL NAME.", "CUSTOMER NAME", "ACTION");

	List<String> TOTAL_DISCARDED_DEVICES_HEADERS = List.of("UIN NO.", "IMEI NO.", "ICCID NO.", "CHASSIS NO.",
			"MODEL NAME.", "INSTALLED AT", "DISCARDED AT", "ACTION");

	List<String> DEVICE_ACTIVITY_OVERVIEW_HEADERS = List.of("UIN NO.", "IMEI NO.", "ICCID NO.", "MODEL NAME.",
			"LOG IN TIME", "ACTION");

	List<String> FIRMWARE_WISE_DEVICES_HEADERS = List.of("UIN NO.", "IMEI NO.", "ICCID.", "MODEL NAME.", "VERSION.",
			"ACTION");

	// =========================================================
	// ⏱️ DROPDOWN OPTIONS
	// =========================================================
	List<String> ACTIVITY_DURATION_DROPDOWN_OPTIONS = List.of("All", "Today", "Five Days", "Ten Days", "Fifteen Days",
			"More Than Fifteen", "Not Active");

	// =========================================================
	// 📈 GRAPH LABELS
	// =========================================================
	String GRAPH_TOTAL_DEVICES = "Total Devices Graph";
	String GRAPH_DEVICE_ACTIVITY_OVERVIEW = "Device Activity Overview Graph";
	String GRAPH_FIRMWARE_WISE_DEVICES = "Firmware Wise Devices Graph";
}
