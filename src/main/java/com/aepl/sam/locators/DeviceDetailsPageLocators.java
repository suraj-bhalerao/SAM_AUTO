package com.aepl.sam.locators;

import org.openqa.selenium.By;

public class DeviceDetailsPageLocators extends CommonPageLocators {
	public static final By COMPONENT_TITLE = By.xpath("//h6[@class=\"component-title\"]");
	
	public static final By FOTA_BTN = By.xpath("//button[contains(text(),'FOTA')]");
	public static final By OTA_BTN = By.xpath("//button[contains(text(),'OTA')]");
	
	public static final By ALL_COMPONENT = By.xpath("//div[@class='row']/div/div[@class='component-container']");
	public static final By EXPORT_BTN = By.xpath("//button[contains(text(),'Export')]");
}
