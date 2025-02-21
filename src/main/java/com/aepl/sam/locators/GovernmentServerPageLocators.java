package com.aepl.sam.locators;

import org.openqa.selenium.By;

public class GovernmentServerPageLocators {
	
	// Dropdown Link
	public static final By GOVERNMENT_DRP_LINK = By.xpath("//a[@routerlink='govt-servers' and @href='/govt-servers']");
	
	// Page Headers Links
	public static final By BACK_BTN = By.className("action-button back-button");
	public static final By REFRESH_BTN = By.className("action-button reload-button");
}
