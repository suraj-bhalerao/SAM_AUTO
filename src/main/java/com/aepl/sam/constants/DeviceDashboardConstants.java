package com.aepl.sam.constants;

public interface DeviceDashboardConstants {

    // Excel sheet name
    String DEVICE_DASHBOARD_EXCEL_SHEET = "DeviceDashboardTests";

    // Test case names
    String TC_PAGE_LOGO = "Verify Company Logo";
    String TC_PAGE_TITLE = "Verify Page Title";
    String TC_NAV_BAR = "Verify Navigation Bar Link";
    String TC_VALIDATE_BUTTONS = "Validate Buttons";
    String TC_SEARCH_DEVICE = "Search Device";
    String TC_SEARCH_INPUT_ENABLED = "Search Input Enabled";
    String TC_SEARCH_INPUT_VISIBLE = "Search Input Visible";
    String TC_SEARCH_BUTTON_ENABLED = "Search Button Enabled";
    String TC_SEARCH_BUTTON_VISIBLE = "Search Button Visible";
    String TC_DEVICE_STATUS = "Verify Device Status";
    String TC_VALIDATE_COMPONENTS = "Validate Components";
    String TC_PAGINATION = "Verify Pagination";
    String TC_VERSION = "Verify Application Version";
    String TC_COPYRIGHT = "Verify Copyright";

    // Expected results
    Boolean EXP_LOGO_DISPLAYED = true;
    String EXP_PAGE_TITLE = "AEPL Sampark Diagnostic Cloud";
    String EXP_NAV_BAR_URL = "Link is verified";
    String EXP_VALIDATE_BUTTONS = "All buttons are displayed and enabled successfully.";
    String EXP_SEARCH_DEVICE = "Device Found";
    String EXP_SEARCH_INPUT_ENABLED = "Search Input Enabled";
    String EXP_SEARCH_INPUT_NOT_ENABLED = "Search Input Not Enabled";
    String EXP_SEARCH_INPUT_VISIBLE = "Search Input Visible";
    String EXP_SEARCH_INPUT_NOT_VISIBLE = "Search Input Not Visible";
    String EXP_SEARCH_BUTTON_ENABLED = "Search Button Enabled";
    String EXP_SEARCH_BUTTON_NOT_ENABLED = "Search Button Not Enabled";
    String EXP_SEARCH_BUTTON_VISIBLE = "Search Button Visible";
    String EXP_SEARCH_BUTTON_NOT_VISIBLE = "Search Button Not Visible";
    String EXP_DEVICE_STATUS = "Device Status Verified";
    String EXP_VALIDATE_COMPONENTS = "Components Validated";
    String EXP_PAGINATION = "Pagination Working";
    String EXP_VERSION = "Version Verified";
    String EXP_COPYRIGHT = "Copyright Verified";
}
