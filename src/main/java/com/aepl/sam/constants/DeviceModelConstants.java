package com.aepl.sam.constants;

public interface DeviceModelConstants {
	String DEVICE_MODELS_EXCEL_SHEET= "Device_Models_Test";

	// Test Case Names (TC_)
	String TC_LOGO = "Verify Company Logo on Webpage";
	String TC_PAGE_TITLE = "Verify Page Title on Webpage";
	String TC_NAV_BAR_LINK = "Test Navigation to Device Utility Tab";
	String TC_CLICK_ADD_MODEL = "Test Clicking Add Device Model Button";
	String TC_ADD_MODEL = "Test Adding a New Device Model";
	String TC_SEARCH_MODEL = "Test Search Functionality for Device Model";
	String TC_VIEW_MODEL = "Test Viewing a Device Model";
	String TC_UPDATE_MODEL = "Test Updating an Existing Device Model";
	String TC_SEARCH_MODEL_2 = "Test Search Again for Device Model";
	String TC_DELETE_MODEL = "Test Deleting a Device Model";
	String TC_PAGINATION = "Verify Pagination Functionality";
	String TC_VERSION = "Verify Application Version Display";
	String TC_COPYRIGHT = "Verify Copyright Text";
	String TC_VALIDATE_BUTTONS = "Verify All Buttons on Device Details Page";
	String TC_COMPONENT_TITLES = "Verify All Component Title on Device Details Page";

	// Expected Values (EXP_)
	String LOGO_DISPLAYED = "Logo Displayed";
	String LOGO_NOT_DISPLAYED = "Logo Not Displayed";
	String EXP_PAGE_TITLE = "AEPL Sampark Diagnostic Cloud";
	String EXP_ADD_MODEL_PAGE = "Create Device Model";
	String EXP_MODELS_PAGE = "Device Models";
	String EXP_VIEW_MODEL_PAGE = "View/Update Device Model";
	String EXP_PAGINATION = "Pagination works correctly";
	String EXP_VALIDATE_BUTTONS = "All buttons are displayed and enabled successfully.";
	String EXP_COMPONENT_TITLES = "All components are displayed and validated successfully.";
}
