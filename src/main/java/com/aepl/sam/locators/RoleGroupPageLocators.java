package com.aepl.sam.locators;

import org.openqa.selenium.By;

public class RoleGroupPageLocators extends CommonPageLocators {
	public static final By USER_ROLE_LINK = By.xpath("//a[contains(@routerlink, 'role-group')]");
	
	public static final By ADD_ROLE_GRP = By.xpath("//button[contains(@class, 'primary-button')]");
	public static final By ROLE_GRP_NAME = By.xpath("//input[contains(@formcontrolname, 'groupName')]");
	public static final By SUBMIT_BTN = By.xpath("//button[contains(@class, 'submit-button')]");
	
	public static final By ROLE_TABLE = By.xpath("//table/tbody/tr/td[1]");
}
