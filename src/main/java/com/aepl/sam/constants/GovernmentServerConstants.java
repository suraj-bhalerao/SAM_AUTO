package com.aepl.sam.constants;

public interface GovernmentServerConstants {
	String SHEET_NAME = "Government_Server_Test";

	// Test Cases
	String TC_LOGO = "Verify Company Logo on Webpage";

	String TC_PAGE_TITLE = "Verify Page Title on Webpage";

	String TC_NAV_BAR = "Verify Navigation Bar Click Functionality";

	String TC_REFRESH = "Verify Refresh Button Functionality";

	String TC_BACK = "Verify Back Button Functionality";

	String TC_ADD_SERVER = "Verify Add Government Server Functionality";

	String TC_FILL_FORM = "Verify Government Server Form Filling";

	String TC_SEARCH_VIEW = "Verify Search and View Functionality";

	String TC_UPDATE = "Verify Update Government Server Functionality";

	String TC_ADD_FIRMWARE = "Verify Add Firmware Functionality";

	String TC_DELETE = "Verify Delete Government Server Functionality";

	String TC_PAGINATION = "Verify Pagination Functionality";

	String TC_VERSION = "Verify Version Functionality";

	String TC_COPYRIGHT = "Verify Copyright Functionality";

	String TC_COMPONENT_TITLE = "Test Page Component Title";

	String TC_VALIDATE_BUTTONS = "Test all button on page {Government Server}";

	String TC_VALIDATE_COMPONENTS = "Test All Components on the page {Government Server}";

	// Expected Results
	boolean EXP_LOGO_DISPLAYED = true;

	String EXP_PAGE_TITLE = "Government Server";

	boolean EXP_NAV_BAR = true;

	String EXP_REFRESH = "Government Server";

	String EXP_BACK = Constants.GOV_LINK;

	String EXP_ADD_SERVER = "Government Servers Details";

	String EXP_FILL_FORM = "Data Saved successfully!!";

	String EXP_SEARCH_VIEW = "Search and View Successful";

	String EXP_UPDATE = "Data not found !!";

	String EXP_ADD_FIRMWARE = "Firmware Added Successfully";

	String EXP_DELETE = "Data Fetched Successfully";

	String EXP_PAGINATION = "Pagination works correctly";

	String EXP_COMPONENT_TITLE = "Government Servers List";

	String EXP_VALIDATE_BUTTONS = "All buttons are displayed and enabled successfully.";

	String EXP_VALIDATE_COMPONENTS = "All components are displayed and validated successfully.";
}
