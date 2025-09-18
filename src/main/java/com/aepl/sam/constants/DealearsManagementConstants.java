package com.aepl.sam.constants;

import java.util.List;

public interface DealearsManagementConstants {

	// Excel Sheet Name
	String SHEET_NAME = "Dealer_Management_Test";

	// Test Case Names
	String TC_COMPANY_LOGO = "Test Company logo";
	String TC_NAV_BAR_LINK = "Test Nav Bar Link for {Dealer Management}";
	String TC_PAGE_TITLE = "Test page title for {Sim Batch Data Details}";
	String TC_COMPONENT_TITLE = "Test Page Component Title";
	String TC_VALIDATE_BUTTONS = "Test all button on page {Sim Batch Data Details}";
	String TC_VALIDATE_COMPONENTS = "Test All Components on the page {Sim Batch Data Details}";
	String TC_SEARCH_BUTTON_ENABLED = "Test search button is enabled? ";
	String TC_SEARCH_BOX_ENABLED = "Test search box is enabled? ";
	String TC_SEARCH_BOX_MULTIPLE_INPUTS = "Test input box with multiple inputs";
	String TC_TABLE_HEADERS = "Test Table headers of {Dealer Management Page}";
	String TC_TABLE_DATA = "Validate table data of Dealer Management";
	String TC_VIEW_BUTTONS = "Validate View Buttons in Dealer Management";
	String TC_DELETE_BUTTONS = "Validate Delete Buttons in Dealer Management";
	String TC_PAGINATION = "Test Pagination";
	String TC_ADD_DEALER_BUTTON_ENABLED = "Test add dealer button enabled";
	String TC_ADD_DEALER_BUTTON_VISIBLE = "Test add dealer button enabled"; // Duplicate in test class
	String TC_CLICK_ADD_DEALER_BUTTON = "Test Add Dealer Button";
	String TC_ALL_INPUT_BOXES = "Test all input boxes for adding new dealer details";
	String TC_FORM_VALIDATIONS = "Test all input boxes and validations for dealer form";
	String TC_SUBMIT_BUTTON_NO_DATA = "Test submit button is visible on if not data is visible";
	String TC_SUBMIT_BUTTON = "Test the submit button";

	// Expected Results
	boolean EXP_LOGO_DISPLAYED = true;
	boolean EXP_NAV_BAR_LINK = true;
	String EXP_PAGE_TITLE = "Sensorise SIM Data Details";
	String EXP_COMPONENT_TITLE = "SIM Data Details";
	String EXP_VALIDATE_BUTTONS = "All buttons are displayed and enabled successfully.";
	String EXP_VALIDATE_COMPONENTS = "All components are displayed and validated successfully.";
	boolean EXP_SEARCH_BUTTON_ENABLED = true;
	boolean EXP_SEARCH_BOX_ENABLED = true;
	boolean EXP_SEARCH_BOX_MULTIPLE_INPUTS = true;
	List<String> EXP_TABLE_HEADERS = List.of("FULL NAME", "EMAIL", "MOBILE NO.", "CREATED BY", "STATUS", "ACTION");
	boolean EXP_TABLE_DATA = true;
	boolean EXP_VIEW_BUTTONS = true;
	boolean EXP_DELETE_BUTTONS = true;
	boolean EXP_PAGINATION = true;
	boolean EXP_ADD_DEALER_BUTTON_ENABLED = true;
	boolean EXP_ADD_DEALER_BUTTON_VISIBLE = true;
	String EXP_CLICK_ADD_DEALER_BUTTON = "Save Dealers Details";
	boolean EXP_ALL_INPUT_BOXES = true;
	boolean EXP_FORM_VALIDATIONS = true;
	boolean EXP_SUBMIT_BUTTON_NO_DATA = true;
	boolean EXP_SUBMIT_BUTTON = true;
}
