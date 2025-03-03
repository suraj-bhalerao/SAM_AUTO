package com.aepl.sam.locators;

import org.openqa.selenium.By;

public class DispatchedDevicesPageLocators {

	// Production Device Page

	public static final By ADD_DISPATCHED_DEVICE = By.xpath("//button[@class='primary-button']");

	// Create Production Device

	public static final By UID = By.xpath("//input[@id='mat-input-0']");
	public static final By IMEI = By.xpath("//input[@id='mat-input-1']");
	public static final By ICCID = By.xpath("//input[@id='mat-input-2']");
	public static final By DEVICE_MODEL_NAME = By.xpath("//input[@id='mat-input-3']");
	public static final By BOOTSTRAP_EXPIRY_DATE = By.xpath("//input[@id='mat-input-4']");
	public static final By PRODUCTION_DATE = By.xpath("//input[@id='mat-input-5']");
	public static final By COMPANY_PART_NO = By.xpath("//input[@id='mat-input-6']");
	public static final By SAMPARK_FIRMWARE = By.xpath("//input[@id='mat-input-7']");
	public static final By SAMPARK_TEST_STATUS = By.xpath("//input[@id='mat-input-8']");
	public static final By EMISSION_TYPE = By.xpath("//input[@id='mat-input-9']");
	public static final By SIM_OPERATOR = By.xpath("//input[@id='mat-input-10']");
	public static final By SIM_VENDOR = By.xpath("//input[@id='mat-input-11']");

	// Submit Button

	public static final By SUBMIT_BTN = By.xpath("//button[@class='submit-button ng-star-inserted']");

}
