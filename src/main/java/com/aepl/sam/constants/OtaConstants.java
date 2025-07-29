package com.aepl.sam.constants;

public interface OtaConstants {
	String SHEET_NAME = "OTA_Test";

	String TC_LOGO = "Verify Company Logo on Webpage";
	String TC_PAGE_TITLE = "Verify Page Title on Webpage";
	String TC_NAV_BAR = "Test Navigate to Device Utility Tab";
	String TC_VALIDATE_BUTTONS = "Test All Buttons on OTA Page";
	String TC_VALIDATE_COMPONENTS = "Test All Components on OTA Page";
	String TC_PAGINATION = "Test Pagination on OTA Page";
	String TC_MANUAL_OTA = "Test Manual OTA Feature";
	String TC_OTA_DETAILS = "Test OTA Details";
	String TC_OTA_PAGINATION = "Test OTA Pagination";
	String TC_EXPORT = "Test Export Button on OTA Page";
	String TC_ABORT = "Test Abort Button on OTA Page";
	String TC_BATCH = "Test OTA Batch Functionality";

	String EXP_LOGO_DISPLAYED = "Logo Displayed";
	String EXP_PAGE_TITLE = "AEPL Sampark Diagnostic Cloud";
	String EXP_NAV_BAR = Constants.OTA_LINK;
	String EXP_VALIDATE_BUTTONS = "All buttons are displayed and enabled successfully.";
	String EXP_VALIDATE_COMPONENTS = "All components are displayed and validated successfully.";
	String EXP_PAGINATION = "Pagination is working correctly.";
	String EXP_MANUAL_OTA = "New OTA added successfully for IMEI: " + Constants.IMEI;
	String EXP_OTA_DETAILS = "OTA details displayed successfully.";
	String EXP_EXPORT = "Export functionality is working correctly.";
	String EXP_ABORT = "Abort functionality is working correctly.";
	String EXP_BATCH = "OTA batch functionality is working correctly.";
}
