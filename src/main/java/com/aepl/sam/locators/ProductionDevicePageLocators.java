package com.aepl.sam.locators;

import org.openqa.selenium.By;

public class ProductionDevicePageLocators {

	// Production Device Page
	
	public static final By ADD_PRODUCTION_DEVICE = By.xpath("//button[@class='primary-button']");
	
	//Create Production Device
	
	public static final By UID = By.xpath("//input[@id='uid']");
	public static final By IMEI = By.xpath("//input[@id='imei']");
	public static final By ICCID = By.xpath("//input[@id='iccid']");
	public static final By DEVICE_MODEL_NAME = By.xpath("//input[@id='modelName']");
	public static final By OPERATOR_NUMBER = By.xpath("//input[@id='operatNo']");
	public static final By MOBILE_NUMBER = By.xpath("//input[@id='mobile']");
	public static final By ALT_MOBILE_NO = By.xpath("//input[@id='altMobile']");
	public static final By SERVICE_PROVIDER = By.xpath("//input[@id='serviceProvider']");
	public static final By ALT_SERVICE_PROVIDER = By.xpath("//input[@id='altServiceProvider']");
	public static final By BOOTSTRAP_EXPIRY_DATE = By.xpath("//input[@id='bootstrapExpDate1']");
	
	//Submit Button
	
	public static final By SUBMIT_BTN = By.xpath("//button[@class='submit-button ng-star-inserted']");
	
	
	
	
}
