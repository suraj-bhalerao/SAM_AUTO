package com.aepl.sam.locators;

import org.openqa.selenium.By;

public class LoginPageLocators {
	// Login Page
	public static final By LOGIN_FLD = By.id("mat-input-0");
	public static final By PASSWORD_FLD = By.id("mat-input-1");
	public static final By SIGN_IN_BTN = By.xpath("//button[contains(@class, 'btn') and contains(@class, 'btn-primary') and contains(text(), 'Sign in')]");

	//LogOut  
	public static final By PROFILE_ICON = By.xpath("//span[contains(text(), 'Hi,')]");
	public static final By LOGOUT_BTN = By.xpath("//a[contains(text(),'Log')]");
	
	// Forgot Password
	public static final By FORGOT_PASSWORD_LNK = By.xpath("//a[contains(text(), 'Forgot')]");
	public static final By FORGOT_INPUT_FLD = By.xpath("//input[contains(@placeholder, 'Your Email')]");
	public static final By FORGOT_ERROR_MSG = By.tagName("mat-error");
	public static final By RESET_BTN = By.tagName("button");
	public static final By RESET_TOAST_ERR = By.xpath("//simple-snack-bar/div[1]");
	
	
	/// Unused Locators
	public static final By LOGIN_ERROR_MSG = By.id("mat-mdc-error-0");
	public static final By PASSWORD_ERROR_MSG = By.id("mat-mdc-error-1");
	public static final By EYE_ICON = By.xpath("//button[contains(text(), 'Visibility')]");
	public static final By BACK_TO_LOGIN = By.xpath("//a[contains(text(), 'Back')]");
}