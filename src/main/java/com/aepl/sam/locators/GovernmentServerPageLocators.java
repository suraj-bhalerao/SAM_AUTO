package com.aepl.sam.locators;

import org.openqa.selenium.By;

public class GovernmentServerPageLocators extends CommonLocatorsPage {

	// Dropdown Link
	public static final By GOVERNMENT_NAV_LINK = By.xpath("//a[@routerlink='govt-servers' and @href='/govt-servers']");

	// Page Headers Links
	public static final By SEARCH_BOX_INPUT = By.xpath("//input[contains(@placeholder, 'Search')]");
	public static final By SEARCH_BOX_BTN = By.xpath("//button[@class='search-btn']");
	public static final By PAGE_TITLE = By.xpath("//span[@class=\"page-title\"]");
	public static final By COMPONENT_TITLE = By.xpath("//div[@class=\"component-header\"]/h6");
	
	// Table data
	public static final By TABLE_DATA = By.xpath("//tbody/tr/td[1]");
	// Toast messages
	public static final By TOAST_MSG = By.xpath("//simple-snack-bar/div[2]");

	// Page Footer Link
	public static final By PAGE_PER_ROW = By.id("id=\"rowsSelect\"");
	public static final By PAGE_NUM = By.xpath("//div[@class=\"currentPage-numbers\"]");
	public static final By VERSION = By.xpath("//b[@_ngcontent-ng-c1879679298='' and text()='Version:']");

	// Action Buttons
	public static final By EYE_BTN = By.xpath("//tr[@class=\"ng-star-inserted\"]/td[6]/button[1]");
	public static final By DEL_BTN = By.xpath("//tr[@class=\"ng-star-inserted\"]/td[6]/button[2]");

	// State IP : PORT
	public static final By PRM_IP_PORT = By.xpath("//tr[@class=\"ng-star-inserted\"]/td[4]");
	public static final By SEC_IP_PORT = By.xpath("//tr[@class=\"ng-star-inserted\"]/td[5]");

	// Add Government Server
	public static final By ADD_GOV_SER = By.xpath("//button[@class=\"primary-button\"]");
	public static final By STATE = By.xpath("//input[@formcontrolname='state']");
	public static final By STATE_ABR = By.xpath("//input[@formcontrolname='stateCode']");
	public static final By GOV_IP1 = By.xpath("//input[@formcontrolname='govtIp1']");
	public static final By GOV_PORT1 = By.xpath("//input[@formcontrolname='port1']");
	public static final By GOV_IP2 = By.xpath("//input[@formcontrolname='govtIp2']");
	public static final By GOV_PORT2 = By.xpath("//input[@formcontrolname='port2']");
	public static final By STATE_ENABLED = By.xpath("//input[@formcontrolname='stateEnable']");
	
	// Buttons
	public static final By SUBMIT = By.xpath("//button[contains(text(),'Submit')]");
	public static final By UPDATE = By.xpath("//button[contains(text(),'Update')]");
	
	
	// Add Firmware and Firmware list section
	public static final By ADD_FIRM = By.xpath("//button[contains(text(),'Add')]");
	public static final By DRP_MENU = By.tagName("//select");
	
	// Adding new firmware
	public static final By FRM_NAME = By.xpath("//input[@id='firmwareName']");
	public static final By FRM_DSC = By.xpath("//input[@id='description']");
	public static final By FILE_UPLOAD = By.xpath("//input[contains(@placeholder, 'File Upload')]");
	public static final By CAL_BTN = By.xpath("//button[@aria-label='Open calendar']");
	public static final By MANAGER_SELECT = By.tagName("mat-select");
}
