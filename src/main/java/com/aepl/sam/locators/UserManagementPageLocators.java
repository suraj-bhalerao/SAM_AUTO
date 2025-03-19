package com.aepl.sam.locators;

import org.openqa.selenium.By;

public class UserManagementPageLocators extends CommonPageLocators {

	// User Management
	public static final By USR_MANAGEMENT_LINK = By.xpath("//a[@routerlink='user-tab' and @href='/user-tab']");
	public static final By ADD_USR_BTN = By.xpath("//button[contains(text(), 'Add')]");
	public static final By PAGE_TITLE = By.xpath("//span[@class='page-title']");
	public static final By COMPONENT_TITLE = By.xpath("//h6[@class=\"component-title\"]");
	public static final By DRP_DOWN_BTN = By.xpath("//mat-select[@role=\"combobox\"]");																				// the from

	// Add user Button
	public static final By USR_TYPE = By.xpath("//mat-select[@formcontrolname=\"roleId\"]");
	public static final By FIRST_NAME = By.xpath("//input[contains(@formcontrolname, 'firstName')]"); 
	public static final By LAST_NAME = By.xpath("//input[contains(@formcontrolname, 'lastName')]"); 
	public static final By EMAIL = By.xpath("//input[contains(@formcontrolname, 'userEmail')]"); 
	public static final By MOBILE = By.xpath("//input[contains(@formcontrolname, 'mobileNumber')]"); 
	public static final By COUNTRY = By.xpath("//input[contains(@formcontrolname, 'country')]");
	public static final By STATE = By.xpath("//input[contains(@formcontrolname, 'state')]");
	public static final By STATUS = By.xpath("//mat-select[contains(@formcontrolname, 'status')]");

	// Update Profile Section
	public static final By PROF_BTN = By.xpath("//button[contains(text(), 'Update User Profile')]");
	
	// Submit Button 
	public static final By SUBMIT_BTN = By.xpath("//button[contains(text(), 'Submit')]");
	public static final By UPDATE_BTN = By.xpath("//button[contains(text(), 'Update')]");
	
	public static final By DRP_OPTION = By.xpath("//select[contains(@formcontrolname, 'roleIdInFilter')]/option");
}
