package com.aepl.sam.locators;

import org.openqa.selenium.By;

public class DeviceModelsPageLocators extends CommonPageLocators{

	
	// Page Header
	
	public static final By ADD_DEVICE_MODELS = By.xpath("//button[@class='primary-button']");
	public static final By DEVICE_UTILITY = By.xpath("//a[normalize-space()='Device Utility']");
	public static final By DEVICE_MODELS = By.xpath("//a[@routerlink='model']");
	
	
	
	//Add Models
	
	public static final By ADD_MODEL_NAME = By.xpath("//input[@placeholder='Model Code']");
	public static final By ADD_MODEL_DESCRIPTION = By.xpath("//input[@placeholder='Model Name']");
	public static final By ADD_MODEL_SERIAL_SEQUENCE = By.xpath("//input[@placeholder='Model Serial Sequence']");
	public static final By ADD_HARDWARE_VERSION = By.xpath("//input[@placeholder='Hardware Version']");
	public static final By ADD_SUBMIT_BUTTON = By.xpath("//button[@class='submit-button ng-star-inserted']");
	public static final By ADD_UPDATE_BUTTON = By.xpath("//button[@class='edit-button ng-star-inserted']");

	
	public static final By MODEL_TO_SEARCH = By.xpath("//*[@id=\"DataTables_Table_0\"]/tbody/tr[3]/td[1]");
	
	
	
	
	
	
	
	
	
	
	
}
