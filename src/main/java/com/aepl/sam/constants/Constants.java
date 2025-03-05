package com.aepl.sam.constants;

public interface Constants {
	String BASE_URL = "http://aepltest.accoladeelectronics.com:6102";
	String EXP_FRGT_PWD_URL = BASE_URL + "/forgot-password";
	String DASH_URL =  BASE_URL + "/dashboard";
	String GOV_LINK = BASE_URL + "/govt-servers"; 
	
	
	String email_error_msg_01="Please Enter Email ID.";
	String email_error_msg_02="Please Enter Valid Email ID.";

	String password_error_msg_01="Please Enter Password."; 
	String password_error_msg_02="Please Enter Minimum 6 Characters.";

	String toast_error_msg="Invalid credentials!!";  
}
