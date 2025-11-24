package com.aepl.sam.constants;

public interface UserProfileConstants {

	String SHEET_NAME = "User_Profile_Test";

	String TC_LOGO = "Verify Company Logo on Webpage";
	String EXP_LOGO = "Logo Displayed";

	String TC_PAGE_TITLE = "Verify Page Title on Webpage";
	String EXP_PAGE_TITLE = "AEPL Sampark Diagnostic Cloud";

	String TC_NAVBAR = "Verify Navbar Link Navigation";
	String EXP_NAVBAR = Constants.USR_PROFILE;

	String TC_BACK_BUTTON = "Verify Back Button Navigation";
	String EXP_BACK_BUTTON = "Back button navigated successfully.";

	String TC_REFRESH = "Verify Refresh Button Functionality";
	String EXP_REFRESH = "Page refreshed successfully.";

	String TC_CHANGE_PASSWORD = "Verify Change Password Functionality";
	String EXP_CHANGE_PASSWORD = "Password changed successfully.";

	String TC_UPLOAD_PROFILE_PIC = "Verify Upload Profile Picture Functionality";
	String EXP_UPLOAD_PROFILE_PIC = "Profile picture uploaded successfully.";

	String TC_UPDATE_PROFILE = "Verify Update Profile Details Functionality";
	String EXP_UPDATE_PROFILE = "Profile updated successfully.";

	String TC_PAGINATION = "Verify Pagination Functionality";
	String EXP_PAGINATION = "Pagination works correctly";

	String TC_VERSION = "Verify Version Functionality";
	String EXP_VERSION = Constants.EXP_VERSION_TEXT;

	String TC_COPYRIGHT = "Verify Copyright Functionality";
	String EXP_COPYRIGHT = Constants.EXP_COPYRIGHT_TEXT;

	String TC_VALIDATE_BUTTONS = "Verify Buttons on Customer Master Page";
	String EXP_VALIDATE_BUTTONS = "All buttons are displayed and enabled successfully.";

	String TC_USER_PROFILE_DATA = "Test the user profile data";
	String EXP_USER_PROFILE_DATA = "User Verified successfully";
}
