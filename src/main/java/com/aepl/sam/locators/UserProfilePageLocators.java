package com.aepl.sam.locators;

import org.openqa.selenium.By;

public class UserProfilePageLocators extends CommonPageLocators {
	public static final By PROFILE_LINK = By.xpath("//a[@routerlink = 'profile']");
	public static final By ADM_NAME = By.xpath("//input[contains(@formcontrolname, 'adminName')]");
	public static final By FIRST_NAME = By.xpath("//input[contains(@formcontrolname, 'firstName')]");
	public static final By LAST_NAME = By.xpath("//input[contains(@formcontrolname, 'lastName')]");
	public static final By EMAIL = By.xpath("//input[contains(@formcontrolname, 'userEmail')]");
	public static final By MOBILE_NUMBER = By.xpath("//input[contains(@formcontrolname, 'mobileNumber')]");
	public static final By COUNTRY = By.xpath("//input[contains(@formcontrolname, 'country')]");
	public static final By STATE = By.xpath("//input[contains(@formcontrolname, 'state')]");
	public static final By USR_ROLE = By.xpath("//input[contains(@formcontrolname, 'userRole')]");
	public static final By STATUS = By.xpath("//input[contains(@formcontrolname, 'status')]");

	public static final By CHANGE_PASS = By.xpath("//div[@class= 'image-section']/button[2]");
	public static final By UPDATE = By.xpath("//button[contains(@class, 'edit-button')]");

	// Active element
	public static final By CUR_PASS = By.xpath("//input[@formcontrolname='currentpassword']");
	public static final By NEW_PASS = By.xpath("//input[@formcontrolname='password']");
	public static final By CHANGE_BTN = By.xpath("//button[contains(text(), 'Change password')]");
	public static final By UPLOAD_PROFILE = By.xpath("//button[contains(text(), 'Upload Profile Icon')]");
}
