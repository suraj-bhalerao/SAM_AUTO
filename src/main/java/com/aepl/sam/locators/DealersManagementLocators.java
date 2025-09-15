package com.aepl.sam.locators;

import org.openqa.selenium.By;

public class DealersManagementLocators {
	public static final By DEALER = By.xpath("//li/a[contains(text(),'DEALERS UTILITY')]");
	public static final By DEALER_MANAGEMENT = By.xpath("//li/a[contains(text(),'DEALERS MANAGEMENT')]");
	public static final By SEARCH_BTN = By.className("search-btn");
	public static final By SEARCH_BOX = By.xpath("//input[@formcontrolname='searchInput'] ");
	public static final By TOAST_MESSAGE = By.tagName("simple-snack-bar");
	public static final By TABLE = By.tagName("table");
}
