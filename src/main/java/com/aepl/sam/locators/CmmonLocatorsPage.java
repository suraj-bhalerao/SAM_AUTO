package com.aepl.sam.locators;

import org.openqa.selenium.By;

public class CmmonLocatorsPage {

	public static final By ORG_LOGO = By.cssSelector(".header-logo img");
	public static final By PROJECT_TITLE = By.xpath("//div[contains(@class,'header-main-title')]/h6");
	public static final By REFRESHBTN = By.xpath("//app-device-dashboard//mat-icon[text()='refresh']");
	
}
