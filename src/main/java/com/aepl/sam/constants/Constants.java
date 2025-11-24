package com.aepl.sam.constants;

public interface Constants {

	// Base URL
	String BASE_URL = "http://aepltest.accoladeelectronics.com:6102";

	// General URLs
	String LOGIN_URL = BASE_URL + "/login";
	String EXP_FRGT_PWD_URL = BASE_URL + "/login";
	String DASH_URL = BASE_URL + "/device-dashboard-page";
	String GOV_LINK = BASE_URL + "/govt-servers";
	String DEVICE_LINK = BASE_URL + "/model";
	String ADD_MODEL_LINK = BASE_URL + "/model-firmware";
	String USR_MAN = BASE_URL + "/user-tab";
	String USR_PROFILE = BASE_URL + "/profile";
	String ROLE_MANAGEMENT = BASE_URL + "/user-role";
	String PROD_DEVICE_LINK = BASE_URL + "/production-device-page";
	String DISP_DEVICE_LINK = BASE_URL + "/dispatch-device-page";
	String CREATE_DIS_DEVICE_LINK = BASE_URL + "/dispatch-device-add-page";
	String ROLE_GROUP = BASE_URL + "/role-group";
	String OTA_LINK = BASE_URL + "/ota-batch-page";
	String SIM_MANUAL_UPLOAD = BASE_URL + "/sensorise-sim-manual-upload";

	// Footer
	String EXP_VERSION_TEXT = "Version: 1.5.0";
	String EXP_COPYRIGHT_TEXT = "Accolade Electronics Pvt. Ltd.";

	// Login Credentials
	String CUR_PASS = "KZTVvTtE";
	String NEW_PASS = "KZTVvTtE";

	// Validation Error Messages
	String email_error_msg_01 = "This field is required and can't be only spaces.";
	String email_error_msg_02 = "Please enter a valid Email ID.";
	String password_error_msg_01 = "Please Enter Password";
	String password_error_msg_02 = "Minimum 6 characters required.";

	// Toast messages
	String toast_error_msg = "Invalid credentials!!";
	String toast_error_msg_01 = "User Not Found";
	String toast_error_msg_02 = "login Failed due to Incorrect email or password";
	String toast_error_msg_03 = "Validation Error";

	// Test Device Data
	String IMEI = "867950076683091";
	String ICCID = "89916440844825969900";
	String UIN = "ACON4SA162426683091";
	String VIN = "ACCDEV12242083091";

	// Generic Test Data
	String DEVICE_MODEL = "Testing";
	String USER = "suraj";
	String MOBILE_NUMBER = "7385862781";
	String ALT_MOBILE_NUMBER = "9730922327";
	String ISP_1 = "BSNL";
	String ISP_2 = "Airtel";
	String FIRMWARE = "1.2.3";
	String UP_FIRMWARE = "2.0.0";
	String COUNTRY = "India";
	String STATE = "Maharashtra";
	String STATE_ABR = "MH";
	String EMAIL = "surajbhalerao2024@gmail.com";
	String ALT_EMAIL = "surajbhalerao2024@gmail.com";
	String ROLE_TYPE = "PAE MANAGER";
	String QA_MAN = "Shital Shingare";
	String SOFT_MAN = "Abhijeet Jawale";

	// Device info
	String DEVICE_UID = "ACON4SA212240006474";
	String PART_NO_ADD = "PART001";
	String PART_NO_UPDATE = "PART002";
	String CUSTOMER_ADD = "AEPL";
	String CUSTOMER_UPDATE = "TML";

	// Server info
	String GOV_SERVER_NAME = "SURAJ";
	String GOV_STATE_CODE = "SA";
}
