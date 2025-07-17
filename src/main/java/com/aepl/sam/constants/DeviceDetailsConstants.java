package com.aepl.sam.constants;

public interface DeviceDetailsConstants {

	// Excel Sheet Name
	String DEVICE_DETAILS_EXCEL_SHEET = "Device_Details_Test";

	// Test Case Names
	String TC_LOGO = "Verify Company Logo on Webpage";
	String TC_PAGE_TITLE = "Verify Page Title on Webpage";
	String TC_REFRESH_BUTTON = "Verify Refresh Button Functionality";
	String TC_SEARCH_VIEW_DEVICE = "Search and View Device";
	String TC_ALL_BUTTONS = "Verify All Buttons on Device Details Page";
	String TC_COMPONENT_TITLES = "Verify All Component Title on Device Details Page";
	String TC_ALL_CARDS = "Verify All Cards on Device Details Page";
	String TC_COMPONENT_DETAILS = "Verify All Component Details on Device Details Page";
	String TC_EXPORT_BUTTON = "Verify Last 50 Login Packets on Device Details Page";
	String TC_VIEW_LOGIN_PACKET = "Verify View Login Packet on Device Details Page";
	String TC_PAGINATION = "Verify Pagination on Device Details Page";
	String TC_VERSION = "Verify Version on Device Details Page";
	String TC_COPYRIGHT = "Verify Copyright on Device Details Page";
	String TC_HEALTH_PACKET = "Verify Health Packet";
	String TC_BAR_GRAPH = "Verify Bar Graph on Device Details Page";
	
	// Expected Results
	String LOGO_DISPLAYED = "Logo Displayed";
	String LOGO_NOT_DISPLAYED = "Logo Not Displayed";
	String EXP_PAGE_TITLE = "AEPL Sampark Diagnostic Cloud";
	String EXP_REFRESH_CLICKED = "Clicked on the refreshed button";
	String EXP_SEARCH_VIEW = "Device details displayed successfully";
	String EXP_ALL_BUTTONS = "All buttons are displayed and enabled successfully.";
	String EXP_COMPONENT_TITLES = "All components are displayed and validated successfully.";
	String EXP_CARDS = "All cards are displayed and validated successfully.";
	String EXP_COMPONENT_DETAILS = "All component details are displayed successfully";
	String EXP_EXPORT = "Last 50 login packets are displayed successfully";
	String EXP_VIEW_LOGIN = "Login packet details are displayed successfully";
	String EXP_PAGINATION = "Pagination is displayed and functional";
	String EXP_HEALTH_RESULT = "Health packet details are displayed successfully";
	String EXP_BAR_GRAPH = "Device Activity Overview";
}
