package com.aepl.sam.constants;

public interface RoleConstants {
	String SHEET_NAME = "User_Role_Test";

	// Test Case Names
	String TC_LOGO = "Verify Company Logo on Webpage";
	String TC_PAGE_TITLE = "Verify Page Title on Webpage";
	String TC_NAV_BAR = "Verify NavBar Link Navigation";
	String TC_BACK_BTN = "Verify Back Button Navigation";
	String TC_REFRESH_BTN = "Verify Refresh Button Functionality";
	String TC_ADD_USER_ROLE = "Verify 'Add User Role' Button Click";
	String TC_SELECT_OPTIONS = "Verify Selecting Options in Add User Role";
	String TC_SUBMIT_ROLE = "Verify Select All Permissions and Submit Role";
	String TC_SEARCH_ROLE = "Verify Search User Role Functionality";
	String TC_UPDATE_ROLE = "Verify Update User Role Functionality";
	String TC_DELETE_ROLE = "Verify Delete User Role Functionality";
	String TC_PAGINATION = "Verify Pagination Functionality";
	String TC_VERSION = "Verify Version Functionality";
	String TC_COPYRIGHT = "Verify Copyright Functionality";

	// Expected Results
	String EXP_LOGO_DISPLAYED = "Logo Displayed";
	String EXP_PAGE_TITLE = "AEPL Sampark Diagnostic Cloud";
	String EXP_NAV_BAR = "Role Management"; // Use `Constants.ROLE_MANAGEMENT` if needed
	String EXP_BACK_NAVIGATION = "Back button navigated successfully.";
	String EXP_REFRESH_TITLE = "Role Management";
	String EXP_ADD_ROLE_SCREEN = "Role Management";
	String EXP_SELECT_OPTIONS = "Options selected successfully";
	String EXP_SUBMIT_ROLE = "User role permissions selected and submitted";
	String EXP_SEARCH_ROLE = "Role 'DEMO' should be found and searched successfully";
	String EXP_UPDATE_ROLE = "User role should be updated successfully";
	String EXP_DELETE_ROLE = "User role should be deleted successfully";
	String EXP_PAGINATION = "Pagination works correctly";
}
