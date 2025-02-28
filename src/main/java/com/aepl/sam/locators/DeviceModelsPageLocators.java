package com.aepl.sam.locators;

import org.openqa.selenium.By;

public class DeviceModelsPageLocators {

	// Navbar 
	
	public static final By DASHBOARD = By.xpath("//a[normalize-space()='Dashboard']");
	public static final By DEVICE_UTILITY = By.xpath("//a[normalize-space()='Device Utility']");
	public static final By USER = By.xpath("//a[normalize-space()='User']");
	public static final By USER_PROFILE = By.xpath("//a[normalize-space()='User Profile']");
	public static final By LOGOUT = By.xpath("//a[normalize-space()='Logout']");
	
	// Page Header
	
	public static final By BACK_BUTTON = By.xpath("//mat-icon[normalize-space()='arrow_back']");
	public static final By REFRESH_BUTTON = By.xpath("//mat-icon[normalize-space()='refresh']");
	public static final By PAGE_TITLE = By.xpath("//span[@class='page-title']");
	public static final By ADD_DEVICE_MODELS = By.xpath("//button[@class='primary-button']");
	
	//Component Section
	
	public static final By SEARCH_FIELD = By.xpath("//input[@placeholder='Search and Press Enter']");
	public static final By SEARCH_BUTTON = By.xpath("//button[@class='search-btn']");
	public static final By SEARCH_CLEAR = By.xpath("//input[@placeholder='Search and Press Enter']");
	public static final By EYE_ICON = By.xpath("//button[contains(@class, 'primary-button') and contains(@class, 'view-button')]");
	public static final By DELETE_ICON = By.xpath("//button[contains(@class, 'primary-button') and contains(@class, 'delete-button')]");
	public static final By ROW_PER_PAGE = By.xpath("//select[@id='rowsSelect']");
	public static final By PAGINATION = By.xpath("//div[@class='currentPage-numbers']");
	
	//Add Models
	
	public static final By ADD_BACK_BUTTON = By.xpath("//mat-icon[normalize-space()='arrow_back']");
	public static final By ADD_REFRESH_BUTTON = By.xpath("//mat-icon[normalize-space()='refresh']");
	public static final By ADD_PAGE_TITLE = By.xpath("//span[normalize-space()='Create Device Model']");
	public static final By ADD_MODEL_NAME = By.xpath("//input[@placeholder='Model Code']");
	public static final By ADD_MODEL_DESCRIPTION = By.xpath("//input[@placeholder='Model Name']");
	public static final By ADD_MODEL_SERIAL_SEQUENCE = By.xpath("//input[@placeholder='Model Serial Sequence']");
	public static final By ADD_HARDWARE_VERSION = By.xpath("//input[@placeholder='Hardware Version']");
	public static final By ADD_SUBMIT_BUTTON = By.xpath("//button[@class='submit-button ng-star-inserted']");
	
	// Page Footer
	
	public static final By COPYRIGTH_LINK = By.xpath("//b[text()='Accolade Electronics Pvt. Ltd.']");
	public static final By NET_SPEED = By.xpath("//span[@id='netSpeed-indicator']");
	public static final By VERSION = By.xpath("//div[@class='footer-col footer-right']//span[1]");
	
	
	
	
	
	
	
}
