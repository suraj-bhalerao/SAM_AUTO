package com.aepl.sam.locators;

import org.openqa.selenium.By;

public class DispatchedDevicesPageLocators extends CommonPageLocators {
	// Production Device Page
	public static final By DISPATCHED_DEVICE = By.xpath("//a[@ng-reflect-router-link='dispatch-device-page']");
	public static final By MANUAL_UPLOAD = By.xpath("//div[@class='page-header']//button[1]");
	public static final By MODEL_TO_SEARCH = By.xpath("//td[normalize-space()='AAAAAAAAAAAAAAAAAAA']");

	// Create Production Device
	public static By UID = By.xpath("//input[contains(@formcontrolname, 'uid')]");
	public static final By IMEI = By.xpath("//input[contains(@formcontrolname, 'imei')]");
	public static final By ICCID = By.xpath("//input[contains(@formcontrolname, 'iccid')]");
	public static final By DEVICE_MODEL_NAME = By.xpath("//input[contains(@formcontrolname, 'modelName')]");
	public static final By BOOTSTRAP_EXPIRY_DATE = By.xpath("(//span[@class='mat-mdc-button-touch-target'])[1]");
	public static final By PRODUCTION_DATE = By.xpath("(//span[@class='mat-mdc-button-touch-target'])[2]");
	public static final By COMPANY_PART_NO = By.xpath("//input[contains(@formcontrolname, 'tmlPartNumber')]");
	public static final By SAMPARK_FIRMWARE = By.xpath("//input[contains(@formcontrolname, 'samparkFirmware')]");
	public static final By SAMPARK_TEST_STATUS = By.xpath("//input[contains(@formcontrolname, 'samparkTestStatus')]");
	public static final By EMISSION_TYPE = By.xpath("//input[contains(@formcontrolname, 'emissionType')]");
	public static final By SIM_OPERATOR = By.xpath("//input[contains(@formcontrolname, 'simOperator')]");
	public static final By SIM_VENDOR = By.xpath("//input[contains(@formcontrolname, 'simVendor')]");
	public static By CUST_PART_NO = By.xpath("//input[contains(@formcontrolname, 'customerPartNumber')]");
	public static By CUST_OPTIONS = By.xpath("//div/mat-option");
	// Submit Button
	public static final By SUBMIT_BTN = By.xpath("//button[@class='submit-button ng-star-inserted']");
	public static final By UPDATE_BTN = By.xpath("//button[contains(text(), 'Update')]");

}
