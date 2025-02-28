package com.aepl.sam.locators;

import org.openqa.selenium.By;

public class UserManagementPageLocators {

	// User Management
	public static final By USR_MANAGEMENT_LINK = By.xpath("//a[@routerlink='user-tab' and @href='/user-tab']");
	public static final By ADD_USR_BTN = By.xpath("//button[contains(text(), 'Add')]");
	public static final By PAGE_TITLE = By.xpath("//span[@class='page-title']");
	public static final By COMPONENT_TITLE = By.xpath("//h6[@class=\"component-title\"]");
	public static final By DRP_DOWN_BTN = By.xpath("//mat-select[@role=\"combobox\"]"); // it selects both dropdown on																					// the from

	// Add user Button
	public static final By USR_TYPE = By.xpath("//mat-select[@formcontrolname=\"roleId\"]");
	public static final By FORM_INPUT = By.xpath("//input"); // it will gives the items that are in the form all 8

	// Update Profile Section
	public static final By PROF_BTN = By.xpath("//button[text()='Update User Profile']");
	
	// Submit Button 
	public static final By SUBMIT_BTN = By.xpath("//button[text()=' Submit for Approval']");
}
