package com.aepl.sam.locators;

import org.openqa.selenium.By;

public class DispatchedDevicesPageLocators extends CommonPageLocators {
	// Production Device Page
	public static final By DISPATCHED_DEVICE = By.xpath("//a[@ng-reflect-router-link='dispatch-device-page']");
	public static final By MANUAL_UPLOAD = By.xpath("//div[@class='page-header']//button[1]");
	public static final By BULK_UPLOAD = By.xpath("//div[@class='page-header']//button[2]");
	public static final By MODEL_TO_SEARCH = By.xpath("//td[normalize-space()='AAAAAAAAAAAAAAAAAAA']");

	// Create Production Device
	public static By UID = By.xpath("//input[contains(@formcontrolname, 'uid')]");
	public static By CUST_PART_NO = By.xpath("//input[contains(@formcontrolname, 'customerPartNumber')]");
	public static By CUST_OPTIONS = By.xpath("//div/mat-option");
	// Submit Button
	public static final By SUBMIT_BTN = By.xpath("//button[contains(text(), 'Submit')]");
	public static final By UPDATE_BTN = By.xpath("//button[contains(text(), 'Update')]");
	public static final By TOAST_MSG = By.xpath("//simple-snack-bar/div[1]");

	/// new 
	public static final By DOWNLOAD_SAMPLE_LINK = By.xpath("//button[contains(text(), 'Download Sample')]");
	public static final By ATTACHMENT_BTN = By.xpath("//button/mat-icon[contains(text(),'attach')]");
	
}
