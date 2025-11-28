package com.aepl.sam.locators;

import org.openqa.selenium.By;

public class RoleManagementLocators extends CommonPageLocators {

	public static final By USER_ROLE_LINK = By.cssSelector("a[ng-reflect-router-link='user-role']");
	public static final By ADD_USER = By.xpath("//button[contains(text(), 'Add Role')]");
	public static final By ROLE_NAME = By.xpath("//input[ contains(@placeholder, 'Role Name')]");
	public static final By ROLE_TYPE = By.xpath("//mat-select[@formcontrolname='roleType']");
	public static final By ROLE_GRP = By.xpath("//mat-select[@formcontrolname='roleGroup']");
	public static final By SELECT_ALL = By.xpath("//table/tr/th[2]/mat-checkbox");
	public static final By VIEW = By.xpath("//table/tr/th[3]/mat-checkbox");
	public static final By SUBMIT_BTN = By.xpath("//button[contains(@class,  'submit-button')]");
	public static final By UPDATE_BTN = By.xpath("//button[contains(@class,  'edit-button')]");

	// Search
	public static final By USER_ROLE_SEARCH = By.xpath("//table/tbody/tr/td[1]");
	public static final By TOAST_MESSAGE = By.xpath("//simple-snack-bar/div[1]");
}
