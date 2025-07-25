package com.aepl.sam.constants;

public interface FotaConstants {
	String FOTA_EXCEL_SHEET = "FOTA_Test";

	// TC
	String TC_PAGE_LOGO = "Verify Company Logo on Webpage";
	String TC_PAGE_TITLE = "Verify Page Title on Webpage";
	String TC_REFRESH_BTN = "Verify Refresh Button Functionality";
	String TC_DEVICE_UTILITY = "Verify Device Utility Functionality";
	String TC_FOTA = "Verify FOTA Functionality";
	String TC_VALIDATE_COMPONENTS = "Verify All Components on Webpage";
	String TC_VALIDATE_BUTTONS = "Verify All Buttons on Webpage";
	String TC_CREATE_MANUAL_BATCH = "Create FOTA Batch";
	String TC_CREATE_BULK_BATCH = "Create Bulk FOTA Batch";
	String TC_PAGINATION = "Pagination Check";
	String TC_BATCH_LIST = "Get FOTA Batch List";
	String TC_VERSION = "Verify Version Functionality";
	String TC_COPYRIGHT = "Verify Copyright Functionality";

	// Expected Results
	String EXP_LOGO_DISPLAYED = "Logo Displayed";
	String EXP_PAGE_TITLE = "AEPL Sampark Diagnostic Cloud";
	String EXP_REFRESH_BTN = "Clicked on the refreshed button";
	String EXP_DEVICE_UTILITY = "Clicked on Device Utility";
	String EXP_FOTA = "Clicked on FOTA";
	String EXP_VALIDATE_COMPONENTS = "All components are displayed and validated successfully.";
	String EXP_VALIDATE_BUTTONS = "All buttons are displayed and enabled successfully.";
	String EXP_CREATE_MANUAL_BATCH = "FOTA batch created successfully.";
	String EXP_CREATE_BULK_BATCH = "Bulk FOTA batch created successfully.";
	String EXP_PAGINATION = "Pagination verified successfully!";
	String EXP_BATCH_LIST = "Batch seted successfully!";
}
