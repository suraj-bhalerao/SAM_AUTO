package com.aepl.sam.constants;

public interface DispatchDeviceConstants {

    // Test Case Names (TC_)
    String TC_LOGO = "Verify Company Logo on Webpage";
    String TC_PAGE_TITLE = "Verify Page Title on Webpage";
    String TC_NAV_BAR_LINK = "Test Navigation to Device Utility Tab";
    String TC_ADD_DISPATCH_DEVICE = "Test Navigate to Add Dispatch Device button";
    String TC_NEW_INPUT_FIELDS = "Test Adding a New Dispatch Device";
    String TC_SEARCH_DEVICE = "Test Search Functionality for Dispatched Devices";
    String TC_VIEW_DEVICE = "Test View Dispatched Device";
    String TC_UPDATE_DEVICE = "Test Update Dispatched Device";
    String TC_DELETE_DEVICE = "Test Delete Dispatched Device";
    String TC_VALIDATE_BUTTONS = "Test All Buttons";
    String TC_COMPONENT_TITLES = "Test All Components";
    String TC_PAGINATION = "Verify Pagination Functionality";
    String TC_VERSION = "Verify Application Version Display";
    String TC_COPYRIGHT = "Verify Copyright Text";

    // Expected Values (EXP_)
    String LOGO_DISPLAYED = "Logo Displayed";
    String LOGO_NOT_DISPLAYED = "Logo Not Displayed";
    String EXP_PAGE_TITLE = "AEPL Sampark Diagnostic Cloud";
    String EXP_ADD_DISPATCH_DEVICE_PAGE = "Create Dispatch Device";
    String EXP_NEW_INPUT_FIELDS = "Create Dispatched Device";
    String EXP_SEARCH_DEVICE = "Dispatched Devices";
    String EXP_VIEW_DEVICE = "Update Dispatched Device";
    String EXP_UPDATE_DEVICE = "Update Dispatched Device";
    String EXP_DELETE_DEVICE = "Dispatched Devices";
    String EXP_VALIDATE_BUTTONS = "All buttons are displayed and enabled successfully.";
    String EXP_COMPONENT_TITLES = "All components are displayed and validated successfully.";
    String EXP_PAGINATION = "Pagination works correctly";
    String EXP_VERSION = "v1.0.0"; // Replace with actual version
    String EXP_COPYRIGHT = "© 2025 AEPL. All rights reserved."; // Replace with actual UI footer text
}
