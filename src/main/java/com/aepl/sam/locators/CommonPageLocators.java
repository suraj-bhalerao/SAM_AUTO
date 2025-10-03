package com.aepl.sam.locators;

import org.openqa.selenium.By;

public class CommonPageLocators {
	// Common locators
	public static final By ORG_LOGO = By.cssSelector(".header-logo img");
	public static final By PROJECT_TITLE = By.xpath("//div[contains(@class,'header-main-title')]/h6");

	// Table data
	public static final By TABLE_DATA = By.xpath("//tbody/tr/td[1]");
	public static final By SEARCH_BOX_INPUT = By.xpath("//input[contains(@placeholder, 'Search')]");
	public static final By SEARCH_BOX_BTN = By.xpath("//button[@class='search-btn']");

	// NavBar Links
	public static final By DASHBOARD = By.xpath("//a[text()='Dashboard']");
	public static final By DEVICE_UTILITY = By.xpath("//a[contains(text(), 'DEVICE UTILITY')]");
	public static final By USER = By
			.xpath("//a[contains(translate(text(),'ABCDEFGHIJKLMNOPQRSTUVWXYZ','abcdefghijklmnopqrstuvwxyz'),'user')]");
	public static final By USER_PROFILE = By.xpath("//li/a[contains(text(), 'Hi,')]");
	public static final By LOGOUT = By.xpath("//a[normalize-space()='Logout']");

	// Common locators
	public static final By REFRESHBTN = By.xpath("//div/mat-icon[contains(text(),'refresh')]");

	// Page Header
	public static final By BACK_BUTTON = By.xpath("//mat-icon[normalize-space()='arrow_back']");

	public static final By REFRESH_BUTTON = By.xpath("//div/mat-icon[contains(text(),'refresh')]");
	public static final By PAGE_TITLE = By.xpath("//span[contains(@class,'page-title')]");

	// Component Section
	public static final By SEARCH_FIELD = By.xpath("//input[@placeholder='Search and Press Enter']");
	public static final By SEARCH_BUTTON = By.xpath("//button[@class='search-btn']");
	public static final By SEARCH_CLEAR = By.xpath("//input[@placeholder='Search and Press Enter']");
	public static final By EYE_ICON = By.xpath("//button[contains(@class, 'view-button')]");
	public static final By DELETE_ICON = By.xpath("//button/mat-icon[contains(text(), \"delete\")]");

	// Pagination
	public static By ROW_PER_PAGE = By.xpath("//select[@id='rowsSelect']");
	public static final By PAGINATION = By.xpath("//div[@class='currentPage-numbers']");
	public static final By PAGE_COUNT = By.xpath("//span[@class='page-info']");
	public static final By RIGHT_ARROW = By.xpath("//button/mat-icon[contains(text(), 'chevron_right')]");
	public static final By LEFT_ARROW = By.xpath("//button/mat-icon[contains(text(), 'chevron_left')]");
	public static final By FIRST_PAGE = By.xpath("//button/mat-icon[contains(text(), 'first_page')]");

	// Page Footer
	public static final By COPYRIGTH_LINK = By.xpath("//b[text()='Accolade Electronics Pvt. Ltd.']");
	public static final By NET_SPEED = By.xpath("//span[@id='netSpeed-indicator']");
	public static final By VERSION = By.xpath("//div[@class='footer-col footer-right']//span[1]");

	// Component Section
	public By COMPONENT_TITLE = By.xpath("//div[@class=\"component-header\"]/h6");

	public static final By PAGE_NUM = By.xpath("//div[@class=\"currentPage-numbers\"]");

	// Page Footer
	public static final By COPYRIGHT = By.xpath("//b[text()='Accolade Electronics Pvt. Ltd.']");

	// Components
	public static final By HEADER_CONTAINER = By.xpath("//div[@class=\"header-container\"]");
	public static final By PAGE_HEADER = By.xpath("//div[contains(@class, 'page-header')]");
	public static final By COMPONENT_CONTAINER = By.xpath("//div[contains(@class, 'component-container')]");
	public static final By SEPARATOR = By.xpath("//div[@class=\"component-separator\"]");
	public static final By FOOTER_PAGINATION = By.xpath("//div[@class=\"component-footer\"]");
	public static final By FOOTER = By.xpath("//footer[@class=\"footer-content\"]");

	// buttons
	public By ALL_BTN = By.xpath("//button");

	// Cards
	public static final By ALL_CARDS = By.xpath("//div[@class='kpi-section']");

	// Sample File button
	public static final By SAMPLE_FILE_BUTTON = By.xpath("//button[contains(text(),'Download Sample')]");

	// export button
	public static final By EXPORT_BUTTON = By.xpath("//button[contains(text(), 'Export')]");

	public static final By INPUT_BOX = By.xpath("//input");
	public static final By INPUT_BOX_ERROR = By.xpath("//div/mat-error");
}
