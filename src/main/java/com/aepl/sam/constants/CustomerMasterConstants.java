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
	String TC_VALIDATE_CUST = "";

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
	String EXP_VALIDATION = "";
}
