package com.aepl.sam.locators;

import org.openqa.selenium.By;

public class LoginPageLocators {
	// Login Page
	public static final By LOGIN_FLD = By.id("mat-input-0");
	public static final By PASSWORD_FLD = By.id("mat-input-1");
	public static final By SIGN_IN_BTN = By.tagName("button");
	
	public static final By LOGIN_ERROR_MSG = By.id("mat-mdc-error-0");
	public static final By PASSWORD_ERROR_MSG = By.id("mat-mdc-error-1");
	public static final By EYE_ICON = By.tagName("button");
	
	// Forgot Password
	public static final By FORGOT_PASS = By.tagName("a");
	public static final By INPUT_FLD = By.id("mat-input-2");
	public static final By FORGOT_ERROR_MSG = By.id("mat-mdc-error-2");
	public static final By RESET_BTN = By.tagName("button");
	public static final By BACK_TO_LOGIN = By.tagName("a");
	
	// Footer Validations
	public static final By COPYRIGTH_LINK = By.tagName("a");
	public static final By NET_SPEED = By.id("netSpeed-indicator");
	public static final By VERSION = By.tagName("b");
}