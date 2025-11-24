package com.aepl.sam.locators;

import org.openqa.selenium.By;

public class FotaPageLocators extends CommonPageLocators {
	public static final By FOTA_LINK = By.xpath("//a[@ng-reflect-router-link=\"fota-batch\"] ");
	public static final By MANUAL_FOTA_BTN = By.xpath("//button[contains(text(), 'Manual FOTA')]");
	public static final By BULK_FOTA_BTN = By.xpath("//button[contains(text(), 'Create New Batch')]");
	public static final By MANUAL_FOTA = By.xpath("//input[contains(@placeholder, 'Search by IMEI')]");
	public static final By SEARCH_BTN = By.xpath("//button[contains(text(), 'Search')]");

	// Device details
	public static final By IMEI = By.id("imei");
	public static final By UIN = By.id("uin");
	public static final By VIN = By.id("modelName");
	public static final By ICCID = By.id("iccid");
	public static final By LOGGED_IN_AT = By.id("loggedInAt");
	public static final By JOINED_AT = By.id("joinedAt");
	public static final By VERSION = By.id("version");
	public static final By UFW = By.id("ufw");

	public static final By NEW_FOTA_BTN = By.xpath("//button[contains(text(),'New FOTA')]");
	public static final By STATE = By.xpath("//mat-select[contains(@formcontrolname, 'state')]");
	public static final By STATES_NAME = By.xpath("//div/div/mat-option");

	public static final By NEW_UFW = By.xpath("//mat-select[contains(@formcontrolname, 'newUfw')]");
	public static final By NEW_UFW_NAME = By.xpath("//mat-option/span");

	public static final By FOTA_TYPE = By.xpath("//mat-select[contains(@formcontrolname, 'fotaType')]");
	public static final By FOTA_TYPE_NAME = By.xpath("//mat-option/span[contains(text(), 'Full')]");

	public static final By B_FOTA_TYPE = By.xpath("//mat-select[contains(@formcontrolname, 'type')]");
	public static final By B_FOTA_TYPE_NAME = By.xpath("//mat-option/span[contains(text(), 'Full')]");

	public static final By START_FOTA = By.xpath("//button[contains(text(), 'Start FOTA')]");
	public static final By ABORT_FOTA = By.xpath("//button[contains(text(), 'ABORT')]");

	public static final By TABLE_HEADER = By.xpath("//h6[contains(@class, 'component-title') and text()='FOTA History']");
	public static final By FOTA_HISTORY = By.xpath("//table/tbody/tr[1]/td");
	public static final By FOTA_HISTORY_TABLE_HEADERS = By.xpath("//table/thead/tr[1]/th");

	public static final By FOTA_BATCH_NAME = By.xpath("//input[@formcontrolname='batchName']");
	public static final By FOTA_BATCH_DESC = By.xpath("//input[@formcontrolname='batchDescription']");
	public static final By UPLOAD_FILE = By.xpath("//button/mat-icon[contains(text(), 'attach_file')]");
	public static final By SUBMIT_BTN = By.xpath("//button[contains(text(), 'Submit')]");

	public static final By FOTA_HISTORY_TABLE = By.xpath("//table/tbody/tr[1]");
}
