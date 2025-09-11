package com.aepl.sam.locators;

import org.openqa.selenium.By;

public class SimBatchDataDetailsPageLocators {
	public static final By REPORTS = By.xpath("//li/a[contains(text(),'REPORTS')]");
	public static final By SIM_BATCH_DATA = By.xpath("//li/a[contains(text(),'SENSORISE SIM')]");
	public static final By UPLOADED_ICCID_TABLE_HEADERS = By.xpath("//app-production-device/div/div[2]//table/thead/tr/th");
	public static final By DUPLICATE_ICCID_TABLE_HEADERS = By.xpath("//app-production-device/div/div[3]//table/thead/tr/th");
	public static final By NOT_PRESENT_ICCID_TABLE_HEADERS = By.xpath("//app-production-device/div/div[4]//table/thead/tr/th");
	public static By DOWNLOAD_EXCEL_BUTTONS = By.className("primary-button");
	public static By MANUAL_UPLOAD_BUTTON = By.xpath("//button[@class='primary-button' and contains(text(), 'Manual')]");
	public By INPUT_BOX = By.tagName("input");
	public By SUBMIT_BUTTON = By.className("submit-button");
}
