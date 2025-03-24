package com.aepl.sam.constants;

public interface Constants {
	// Url's
	String BASE_URL = "http://aepltest.accoladeelectronics.com:6102";
	String EXP_FRGT_PWD_URL = "http://aepltest.accoladeelectronics.com:6102/login/forgot-password";

	String DASH_URL = BASE_URL + "/dashboard";
	String GOV_LINK = BASE_URL + "/govt-servers";

	String DEVICE_LINK = BASE_URL + "/model";
	String ADD_MODEL_LINK = BASE_URL + "/model-firmware";
	String USR_MAN = BASE_URL + "/user-tab";

	String PROD_DEVICE_LINK = BASE_URL + "/production-device-page";
	String DISP_DEVICE_LINK = BASE_URL + "/dispatch-device-page";
	String CREATE_DIS_DEVICE_LINK = BASE_URL + "/dispatch-device-add-page";
	
	// Version and Copyright
	String EXP_VERSION_TEXT = "Version: 1.2.0";
	String EXP_COPYRIGHT_TEXT = "Accolade Electronics Pvt. Ltd.";

	// Credentials
	String CUR_PASS = "password";
	String NEW_PASS = "password";

	// Errors
	String email_error_msg_01 = "Please Enter Email ID.";
	String email_error_msg_02 = "Please Enter Valid Email ID.";

	String password_error_msg_01 = "Please Enter Password.";
	String password_error_msg_02 = "Please Enter Minimum 6 Characters.";

	String toast_error_msg = "Invalid credentials!!";
	String toast_error_msg_01 = "User Not Found";
	String toast_error_msg_02 = "login Failed due to Incorrect email or password";
	String toast_error_msg_03 = "Validation Error";
}
