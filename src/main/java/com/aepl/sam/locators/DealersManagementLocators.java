package com.aepl.sam.locators;

import org.openqa.selenium.By;

public class DealersManagementLocators extends CommonPageLocators{
	public static final By DEALER = By.xpath("//li/a[contains(text(),'DEALERS UTILITY')]");
	public static final By DEALER_MANAGEMENT = By.xpath("//li/a[contains(text(),'DEALERS MANAGEMENT')]");
	public static final By SEARCH_BTN = By.className("search-btn");
	public static final By SEARCH_BOX = By.xpath("//input[@formcontrolname='searchInput'] ");
	public static final By TOAST_MESSAGE = By.tagName("simple-snack-bar");
	public static final By TABLE = By.xpath("//div[@class='component-body']/table");
	public static final By TABLE_ROWS = By.xpath(".//table/tbody/tr");
	public static final By ADD_DEALER = By.xpath("//button[@class='primary-button' and contains(text(), 'Add Dealers')]");
	public static final By INPUT_BOXES = By.tagName("//input");
	public static final By SUBMIT_BTN = By.tagName("//div/button[contains(text(), 'Save User Details')]");
}
