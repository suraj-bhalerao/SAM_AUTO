package com.aepl.sam.constants;

import java.util.List;

public interface SimBatchDataDetailsConstants {

	// Excel Sheet Name
	String SHEET_NAME = "Sim_Batch_Data_Details_Test";

	// ---- Test Case Names ----
	String TC_COMPANY_LOGO = "Test Company logo";

	String TC_NAV_BAR_LINK = "Test Nav Bar Link for {Sim Batch Data Details}";

	String TC_PAGE_TITLE = "Test page title for {Sim Batch Data Details}";

	String TC_COMPONENT_TITLE = "Test Page Component Title";

	String TC_VALIDATE_BUTTONS = "Test all button on page {Sim Batch Data Details}";

	String TC_VALIDATE_COMPONENTS = "Test All Components on the page {Sim Batch Data Details}";

	String TC_DOWNLOAD_SAMPLE_FILE = "Test the sample file on page {Sim Batch Data Details}";

	String TC_UPLOAD_BOX_CORRECT = "Test the input box is correct";

	String TC_ERROR_MSG_INPUT_BOX = "Test error message for blank input";

	String TC_UPLOAD_BUTTON_ENABLED = "Test the upload button is enabled";

	String TC_UPLOAD_FILE = "Test upload file";

	String TC_SUBMIT_BUTTON = "Test Submit button";

	String TC_UPLOAD_TABLE_HEADERS = "Test Upload Sim Data Details Components Table Headers";

	String TC_DUPLICATE_ICCID_HEADERS = "Test Duplicate ICCID's In Uploaded File Components Table Headers";

	String TC_NOT_PRESENT_ICCID_HEADERS = "Test ICCID Not Present In Sensorise Database Components Table Headers";

	String TC_EXPORT_BUTTON_NOT_PRESENT = "Test Export button of ICCID Not present";

	String TC_EXPORT_BUTTON_DUPLICATE = "Test Export button of Duplicate ICCID Uploaded";

	String TC_EXPORT_BUTTON_UPLOAD = "Test Export button of Sim Data Details";

	String TC_MANUAL_UPLOAD_VISIBLE = "Test manual upload button is visible";

	String TC_MANUAL_UPLOAD_CLICKABLE = "Test manual upload button is clickable";

	String TC_MANUAL_UPLOAD_CLICKED_OPENED = "Test manual upload button is clicked and opened";

	String TC_INPUT_BOX_ENABLED = "Test input box enabled";

	String TC_EMPTY_INPUT_VALIDATION = "Empty input validation";

	String TC_SHORT_INPUT_VALIDATION = "Short input validation";

	String TC_LONG_INPUT_VALIDATION = "Long input validation";

	String TC_SPECIAL_CHAR_VALIDATION = "Special char validation";

	String TC_SUBMIT_BUTTON_ENABLED = "Test submit button enabled";

	String TC_CLICK_SUBMIT_BUTTON = "Test Clicked the submit button";

	String TC_PAGINATION = "Test pagination of the whole {Sim Data Details Page}";

	String TC_VERSION = "Verify Application Version Display";

	String TC_COPYRIGHT = "Verify Copyright Text";

	// ---- Expected Results ----
	boolean EXP_LOGO_DISPLAYED = true;

	boolean EXP_NAV_BAR_LINK = true;

	String EXP_PAGE_TITLE = "Sensorise SIM Data Details";

	String EXP_COMPONENT_TITLE = "SIM Data Details";

	String EXP_VALIDATE_BUTTONS = "All buttons are displayed and enabled successfully.";

	String EXP_VALIDATE_COMPONENTS = "All components are displayed and validated successfully.";

	String EXP_DOWNLOAD_SAMPLE_FILE = "File downloaded successfully.";

	String EXP_UPLOAD_BOX_CORRECT = "Upload ICCID's to get SIM Data Details";

	String EXP_ERROR_MSG_INPUT_BOX = "This field is mandatory.";

	boolean EXP_UPLOAD_BUTTON_ENABLED = true;

	boolean EXP_UPLOAD_FILE = true;

	boolean EXP_SUBMIT_BUTTON = true;

	List<String> EXP_UPLOAD_TABLE_HEADERS = List.of("ICCID", "CARD STATE", "CARD STATUS", "PRIMARY TSP", "FALLBACK TSP",
			"PRIMARY STATUS", "PRIMARY MSISDN", "FALLBACK STATUS", "FALLBACK MSISDN", "ACTIVE PROFILES",
			"CARD EXPIRY DATE", "PRODUCT NAME", "IS RSU REQUIRED", "IS IMSI REQUIRED", "ACTIVE SR NUMBER");

	List<String> EXP_DUPLICATE_ICCID_HEADERS = List.of("ICCID", "MESSAGE");

	List<String> EXP_NOT_PRESENT_ICCID_HEADERS = List.of("ICCID", "MESSAGE");

	boolean EXP_EXPORT_BUTTON = true;

	boolean EXP_MANUAL_UPLOAD_VISIBLE = true;

	boolean EXP_MANUAL_UPLOAD_CLICKABLE = true;

	List<String> EXP_MANUAL_UPLOAD_CLICKED_OPENED = List.of("SIM Manual Upload", "SIM Data Details");

	boolean EXP_INPUT_BOX_ENABLED = true;

	String EXP_EMPTY_INPUT_VALIDATION = "This field is required and can't be only spaces.";

	String EXP_SHORT_INPUT_VALIDATION = "Value must be exactly 20 characters long.";

	String EXP_LONG_INPUT_VALIDATION = "Value must be exactly 20 characters long.";

	String EXP_SPECIAL_CHAR_VALIDATION = "Special characters are not allowed.";

	boolean EXP_SUBMIT_BUTTON_ENABLED = true;

	boolean EXP_CLICK_SUBMIT_BUTTON = true;

	boolean EXP_PAGINATION = true;

	String EXP_VERSION = "Version: 1.5.0";

	String EXP_COPYRIGHT = "Accolade Electronics Pvt. Ltd.";
}
