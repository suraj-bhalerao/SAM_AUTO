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
    public static final By DEVICE_UTILITY = By.xpath("//a[text()='Device Utility']");
    public static final By USER = By.xpath("//a[text()='User']");
    public static final By USER_PROFILE = By.xpath("//span[contains(text(), 'Hi,')]");
    public static final By LOGOUT = By.xpath("//a[normalize-space()='Logout']"); 
	
    // Page Header
    public static final By BACK_BUTTON = By.xpath("//mat-icon[normalize-space()='arrow_back']");
    public static final By REFRESH_BUTTON = By.xpath("//mat-icon[normalize-space()='refresh']");
    public static final By PAGE_TITLE = By.xpath("//span[contains(@class, 'page-title')]");

    // Component Section
    public static final By COMPONENT_TITLE = By.xpath("//div[@class=\"component-header\"]/h6");
    public static final By SEARCH_FIELD = By.xpath("//input[@placeholder='Search and Press Enter']");
    public static final By SEARCH_BUTTON = By.xpath("//button[@class='search-btn']");
    public static final By SEARCH_CLEAR = By.xpath("//input[@placeholder='Search and Press Enter']");
    public static final By EYE_ICON = By.xpath("//button[contains(@class, 'primary-button') and contains(@class, 'view-button')]");
    public static final By DELETE_ICON = By.xpath("//button[contains(@class, 'primary-button') and contains(@class, 'delete-button')]");

    // Pagination
    public static final By PAGINATION = By.xpath("//div[@class='currentPage-numbers']");
    public static final By ROW_PER_PAGE = By.xpath("//select[@id='rowsSelect']");
    public static final By PAGE_NUM = By.xpath("//div[@class=\"currentPage-numbers\"]");

    // Page Footer
    public static final By COPYRIGHT = By.xpath("//b[text()='Accolade Electronics Pvt. Ltd.']");
    public static final By VERSION = By.xpath("//div[@class='footer-col footer-right']//span[1]");
}
