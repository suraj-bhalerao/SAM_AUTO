package com.aepl.sam.locators;

import org.openqa.selenium.By;

public class DeviceDetailsPageLocators extends CommonPageLocators {
	public static final By COMPONENT_TITLE = By.xpath("//h6[@class=\"component-title\"]");

	public static final By FOTA_BTN = By.xpath("//button[contains(text(),'FOTA')]");
	public static final By OTA_BTN = By.xpath("//button[contains(text(),'OTA')][2]");

	public static final By ALL_COMPONENT = By.xpath("//div[@class='row']/div/div[@class='component-container']");
	public static final By EXPORT_BTN = By.xpath("//div/button[contains(text(),'Export')]");
	public static final By HEALTH_PACKET = By.xpath("");
	public static final By BAR_GRAPH = By.xpath("//div[contains(@class, 'graph-card')]");

	//
	public static final By DEVICE_DETAILS_SEARCHBTN = By.xpath("//button[contains(@class, 'search-btn')]");
	public static final By DEVICE_DETAILS_SEARCHBOX = By.xpath("//input[contains(@formcontrolname, 'searchInput')]");
	public static final By DEVICE_DETAILS_EXPORTBTN = By.xpath("//button[contains(text(),'Export')]");
	public static final By WRONG_IMEI_TOAST_MSG = By.xpath("//simple-snack-bar/div[1]");

	public static final By MAINS_ON_OFF_CARD = By
			.xpath("//span[contains(@class, 'kpi-content') and contains(text(), 'Mains On/Off')]");
	public static final By GPS_DETAILS_CARD = By
			.xpath("//h6[contains(@class, 'component-title') and contains(text(), 'GPS')]");

	//
	public static final By TRACK_DEVICE_BTN = By.xpath("//button/mat-icon[contains(text(), 'directions')]");
	public static final By VIEW_ON_MAP_BTN = By.xpath("//button/mat-icon[contains(text(), 'location_on')]");
	public static final By LAST_50_LOGIN_PACKETS_COMPONENT = By.xpath("//button[contains(text(),'View Login Packet')]");
	public static final By TABLE_ROWS = By.xpath(
			"//div[contains(@class,'component-container')][div[contains(@class,'component-header') and contains(normalize-space(.),'Last 50 Login Packets')]]//div[contains(@class,'component-body')]//table//tr");
	public static final By LAST_50_LOGIN_PACKETS_COMPONENT_CARD = By.xpath("//div[contains(@class , 'component-container')]/div/h6[contains(text(), 'Last 50 Login Packets')]");

	//
	public static final By LAST_50_HEALTH_PACKETS_COMPONENT_CARD = By.xpath("//div[contains(@class , 'component-container')]/div/h6[contains(text(), 'Last 50 Health Packets')]");
	public static final By HEALTH_EXPORT_BTN = By.xpath("//div/button[contains(text(),'Export')]");
}
