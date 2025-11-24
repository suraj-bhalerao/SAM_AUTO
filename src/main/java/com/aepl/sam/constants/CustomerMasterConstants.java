package com.aepl.sam.constants;

public interface CustomerMasterConstants {
	// Excel Sheet Name
	String CUSTOMER_MASTER_EXCEL_SHEET = "Customer_Master_Test";

	// Test Case Names
	String TC_PAGE_LOGO = "Verify Company Logo on Webpage";

	String TC_PAGE_TITILE = "Verify Page Title on Webpage";

	String TC_NAV_BAR = "Verify Navigation Bar Click Functionality";

	String TC_ADD_CUSTOMER = "Verify Add New Customer Functionality";

	String TC_VALIDATE_BUTTONS = "Verify Buttons on Customer Master Page";

	String TC_INPUT_BOX_ERROR = "Verify Input Box Error Handling";

	String TC_SEARCH_CUSTOMER = "Verify Search Customer Functionality";

	String TC_EDIT_CUSTOMER = "Verify Edit Customer Functionality";

	String TC_DELETE_CUSTOMER = "Verify Delete Customer Functionality";

	String TC_VALIDATE_COMPONENTS = "Verify Components on Customer Master Page";

	String TC_PAGINATION = "Verify Pagination Functionality";

	String TC_VERSION = "Verify Version Functionality";

	String TC_COPYRIGHT = "Verify Copyright Functionality";

	String TC_VALIDATE_CUST = "Verify Edited Customer Validation";

	// Additional Test Case Names
	String TC_EMPTY_INPUT_ERROR = "Verify Empty Input Box Error";

	String TC_WRONG_INPUT_ERROR = "Verify Wrong Input Box Error";

	String TC_COMPONENT_TITLE = "Verify Component Title";

	String TC_SEARCH_INPUT_ENABLED = "Verify Search Input Enabled";

	String TC_SEARCH_INPUT_VISIBLE = "Verify Search Input Visible";

	String TC_SEARCH_BUTTON_ENABLED = "Verify Search Button Enabled";

	String TC_SEARCH_BUTTON_VISIBLE = "Verify Search Button Visible";

	String TC_EDIT_BUTTON_ENABLED = "Verify Edit Button Enabled";

	String TC_EDIT_BUTTON_VISIBLE = "Verify Edit Button Visible";

	String TC_DELETE_BUTTON_ENABLED = "Verify Delete Button Enabled";

	String TC_DELETE_BUTTON_VISIBLE = "Verify Delete Button Visible";

	// Expected Results
	String EXP_LOGO_DISPLAYED = "Logo Displayed";

	String EXP_PAGE_TITLE = "AEPL Sampark Diagnostic Cloud";

	String EXP_NAV_BAR_URL = "http://aepltest.accoladeelectronics.com:6102/customer-master";

	String EXP_ADD_CUSTOMER = "Customer Added Successfully";

	String EXP_VALIDATE_BUTTONS = "All buttons are displayed and enabled successfully.";

	String EXP_INPUT_BOX_ERROR = " This field is required and can't be empty.";

	String EXP_SEARCH_CUSTOMER = "Customer Found";

	String EXP_EDIT_CUSTOMER = "Customer Edited Successfully";

	String EXP_DELETE_CUSTOMER = "Customer Deleted Successfully";

	String EXP_VALIDATE_COMPONENTS = "All components are displayed and validated successfully.";

	String EXP_PAGINATION = "Pagination works correctly";

	String EXP_VERSION = Constants.EXP_VERSION_TEXT;

	String EXP_COPYRIGHT = Constants.EXP_COPYRIGHT_TEXT;

	String EXP_VALIDATION = "Customer data validated successfully";

	// Additional Expected Results
	String EXP_EMPTY_INPUT_ERROR = "This field is required and can't be empty.";

	String EXP_WRONG_INPUT_ERROR = "Only alphabets and spaces are allowed.";

	String EXP_COMPONENT_TITLE = "Customer List";

	String EXP_SEARCH_INPUT_ENABLED = "input box is enabled";

	String EXP_SEARCH_INPUT_NOT_ENABLED = "input box is not enabled";

	String EXP_SEARCH_INPUT_VISIBLE = "input box is displayed";

	String EXP_SEARCH_INPUT_NOT_VISIBLE = "input box is not displayed";

	String EXP_SEARCH_BUTTON_ENABLED = "search button is enabled";

	String EXP_SEARCH_BUTTON_NOT_ENABLED = "search button is not enabled";

	String EXP_SEARCH_BUTTON_VISIBLE = "search button is visible";

	String EXP_SEARCH_BUTTON_NOT_VISIBLE = "search button is not visible";

	String EXP_EDIT_BUTTON_VISIBLE = "edit button is visible";

	String EXP_EDIT_BUTTON_NOT_VISIBLE = "edit button is not visible";

	String EXP_DELETE_BUTTON_VISIBLE = "delete button is visible";

	String EXP_DELETE_BUTTON_NOT_VISIBLE = "delete button is not visible";
}
