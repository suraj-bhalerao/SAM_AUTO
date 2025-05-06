package com.aepl.sam.locators;

import org.openqa.selenium.By;

public class CustomerMasterLocators extends CommonPageLocators {
	public static final By CUSTOMER_MASTER_LINK = By.xpath("//a[contains(text(), 'Customer Master')]");
	public static final By ADD_CUSTOMER_BTN = By.xpath("//button[contains(text(), 'Add')]");
	public static final By CUSTOMER_NAME = By
			.xpath("//input[@formcontrolname= 'customerName' and @placeholder= 'Customer Name'] ");
	public static final By SAVE_BTN = By.xpath("//button[contains(text(), 'Submit')]");
	
	public static final By SEARCH_CUSTOMER = By.xpath("//input[@formcontrolname= 'searchInput']");
	public static final By SEARCH_BTN = By.xpath("//button/mat-icon[contains(text(),'search')]");
	
	public static final By DATA = By.xpath("//table/tbody/tr/td[1]");
	public static final By D_DATA = By.xpath("//table/tbody/tr/td[1]");
	
	public static final By EDIT_BTN = By.xpath("//button/mat-icon[contains(text(),'visibility')]");
	public static final By UPDATE_BTN = By.xpath("//button[contains(text(),'Update')]");
	
	public static final By DELETE_BTN = By.xpath("//button/mat-icon[contains(text(),'delete')]");
}
