package com.aepl.sam.locators;

import org.openqa.selenium.By;

public class OtaPageLocators extends CommonPageLocators {
	// Manual OTA Page Locators
	public static final By OTA_LINK = By.xpath("//a[@routerlink='ota-batch-page']");
	public static final By MANUAL_OTA_BUTTON = By.xpath("//button[@class='primary-button' and contains(text(), 'Manual OTA')]");
	public static final By SEARCH_FIELD = By.xpath("//input[@formcontrolname='imeiOrUin']");
	public static final By SEARCH_BUTTON = By.xpath("//button[ contains(text(), 'Search')]");
	public static final By OTA_INFO = By.xpath("//div/input");
	public static final By NEW_OTA_BUTTON = By.xpath("//button[contains(text(), 'New OTA')]");
	public static final By ABORT_BTN = By.xpath("//button[contains(@class,'delete-button' ) and contains(text(),'ABORT')]");
	public static final By COMMAND_LIST = By.xpath("//mat-checkbox"); 
	public static final By SET_BATCH_BTN = By.xpath("//button[contains(text(), 'Set Batch')]");
	public static final By BATCH_SUBMIT_BTN = By.xpath("//button[contains(text(), 'Submit')]");
	public static final By OTA_DETAILS = By.xpath("//table/tbody/tr[1]/td");
	
	// Batch OTA Page Locators
	public static final By BATCH_OTA_LINK = By.xpath("//button[@class='primary-button' and contains(text(), 'Create New Batch')]");
	public static final By BATCH_NAME_FIELD = By.xpath("//input[@formcontrolname='batchName']");
	public static final By DESCRIPTION_FIELD = By.xpath("//input[@formcontrolname='batchDescription']");
	public static final By FILE_INPUT = By.xpath("//div/button/mat-icon[contains(text(), 'attach')]");
}
