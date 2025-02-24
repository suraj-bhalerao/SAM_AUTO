package com.aepl.sam.locators;

import org.openqa.selenium.By;

public class GovernmentServerPageLocators {
	
	
	
	
	

	// Dropdown Link
	public static final By GOVERNMENT_NAV_LINK = By.xpath("//a[@routerlink='govt-servers' and @href='/govt-servers']");

	// Page Headers Links
//	public static final By BACK_BTN = By.className("action-button back-button");
//	public static final By REFRESH_BTN = By.className("action-button reload-button");
	public static final By SEARCH_BOX_INPUT = By.tagName("//input");
	public static final By SEARCH_BOX_BTN = By.xpath("//button[@class='search-btn']");
	public static final By PAGE_TITLE = By.xpath("//span[@class=\"page-title\"]");

	// Page Footer Link
	public static final By PAGE_PER_ROW = By.id("id=\"rowsSelect\"");
	public static final By PAGE_NUM = By.xpath("//div[@class=\"currentPage-numbers\"]");
//	public static final By FOOTER_LINK = By.className("class=\"footer-link\"");
	public static final By VERSION = By.xpath("//b[@_ngcontent-ng-c1879679298='' and text()='Version:']");

	// Action Buttons
	public static final By EYE_BTN = By.xpath("//tr[@class=\"ng-star-inserted\"]/td[6]/button[1]");
	public static final By DEL_BTN = By.xpath("//tr[@class=\"ng-star-inserted\"]/td[6]/button[2]");
	
	// State IP : PORT
	public static final By PRM_IP_PORT = By.xpath("//tr[@class=\"ng-star-inserted\"]/td[4]");
	public static final By SEC_IP_PORT = By.xpath("//tr[@class=\"ng-star-inserted\"]/td[5]");
	
	// Add Government Server
	public static final By ADD_GOV_SER = By.xpath("//button[@class=\"primary-button\"]");
	public static final By INPUT_FORM = By.xpath("//div[@class=\"component-body\"]");
	public static final By INPUT_BOXES = By.tagName("//input");
}
