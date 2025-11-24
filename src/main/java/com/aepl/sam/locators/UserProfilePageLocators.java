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

	public static final By CHANGE_PASS = By.xpath("//button[contains(text(),'Change Password')]");
	public static final By UPDATE = By.xpath("//button[contains(@class, 'edit-button')]");

	public static final By MODAL_LOCATOR = By.xpath("//*[@id=\"changePassword\"]");

	// Active element
	public static final By CUR_PASS = By.xpath("//*[@id='changePassword']//input[@formcontrolname='currentPassword']");
	public static final By NEW_PASS  = By.xpath("//*[@id='changePassword']//input[@formcontrolname='newPassword']");
	public static final By CHANGE_BTN = By.xpath("//button[@class='submit-button']");
	public static final By UPLOAD_PROFILE = By.xpath("//button[contains(text(), 'Upload Profile Icon')]");

	public static final String FILE_PATH = "D:\\AEPL_AUTOMATION\\SAM_AUTO\\src\\test\\resources\\SampleUpload\\dp.jpg";
}
