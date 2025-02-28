package com.aepl.sam.locators;


import org.openqa.selenium.By;

public class CommonLocatorsPage {

	// NavBar Links

	public static final By DASHBOARD = By.xpath("//a[normalize-space()='Dashboard']");
	public static final By DEVICE_UTILITY = By.xpath("//a[normalize-space()='Device Utility']");
	public static final By USER = By.xpath("//a[normalize-space()='User']");
	public static final By USER_PROFILE = By.xpath("//span[normalize-space()='Hi, Super Adm']");

	// Page Header

	public static final By BACK_BUTTON = By.xpath("//mat-icon[normalize-space()='arrow_back']");
	public static final By REFRESH_BUTTON = By.xpath("//mat-icon[normalize-space()='refresh']");
	public static final By PAGE_TITLE = By.xpath("//span[@class='page-title']");

	// Component Section

	public static final By SEARCH_FIELD = By.xpath("//input[@placeholder='Search and Press Enter']");
	public static final By SEARCH_BUTTON = By.xpath("//button[@class='search-btn']");
	public static final By SEARCH_CLEAR = By.xpath("//input[@placeholder='Search and Press Enter']");
	public static final By EYE_ICON = By.xpath("//button[contains(@class, 'primary-button') and contains(@class, 'view-button')]");
	public static final By DELETE_ICON = By.xpath("//button[contains(@class, 'primary-button') and contains(@class, 'delete-button')]");
	public static final By ROW_PER_PAGE = By.xpath("//select[@id='rowsSelect']");
	public static final By PAGINATION = By.xpath("//div[@class='currentPage-numbers']");
	
	// Page Footer
	
	public static final By COPYRIGTH_LINK = By.xpath("//b[text()='Accolade Electronics Pvt. Ltd.']");
	public static final By NET_SPEED = By.xpath("//span[@id='netSpeed-indicator']");
	public static final By VERSION = By.xpath("//div[@class='footer-col footer-right']//span[1]");
}

