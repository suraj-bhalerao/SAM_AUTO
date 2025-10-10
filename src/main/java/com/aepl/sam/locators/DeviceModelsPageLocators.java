package com.aepl.sam.locators;

import org.openqa.selenium.By;

public class DeviceModelsPageLocators extends CommonPageLocators {
	// Page Header
	public static final By DEVICE_UTILITY = By.xpath(
			"//a[contains(translate(text(), 'ABCDEFGHIJKLMNOPQRSTUVWXYZ', 'abcdefghijklmnopqrstuvwxyz'), 'device utility')]");
	public static final By DEVICE_MODELS = By.xpath(
			"//a[translate(@ng-reflect-router-link, 'ABCDEFGHIJKLMNOPQRSTUVWXYZ', 'abcdefghijklmnopqrstuvwxyz')='model' and contains(text(), \"MODEL\")]");
	public static final By ADD_DEVICE_MODELS = By.xpath("//button[@class='primary-button']");

	// Add Models
	public static final By MODEL_CODE = By.xpath("//input[@placeholder='Model Code']");
	public static final By ADD_MODEL_NAME = By.xpath("//input[@placeholder='Model Name']");
	public static final By ADD_MODEL_SERIAL_SEQUENCE = By.xpath("//input[@placeholder='Model Serial Sequence']");
	public static final By ADD_HARDWARE_VERSION = By.xpath("//input[@placeholder='Hardware Version']");
	public static final By ADD_SUBMIT_BUTTON = By.xpath("//button[@class='submit-button ng-star-inserted']");
	public static final By ADD_UPDATE_BUTTON = By.xpath("//button[@class='edit-button ng-star-inserted']");

	public static final By MODEL_TO_SEARCH1 = By.xpath("//td[normalize-space()='Add Model']");
	public static final By MODEL_TO_SEARCH2 = By.xpath("//td[normalize-space()='Update Model']");

	// new
	protected static final By MODEL_CODE_INPUT = By.xpath("//label[contains(., 'Model Code')]/following::input[1]");
	protected static final By MODEL_NAME_INPUT = By.xpath("//label[contains(., 'Model Name')]/following::input[1]");
	protected static final By MODEL_SERIAL_INPUT = By
			.xpath("//label[contains(., 'Model Serial Sequence')]/following::input[1]");
	protected static final By HARDWARE_VERSION_INPUT = By
			.xpath("//label[contains(., 'Hardware Version')]/following::input[1]");

}
