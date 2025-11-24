package com.aepl.sam.locators;

import org.openqa.selenium.By;

public class DeviceDashboardPageLocators {
	// Shital

//	common locators
	public static final By ORG_LOGO = By.cssSelector(".header-logo img");
	public static final By PROJECT_TITLE = By.xpath("//div[contains(@class,'header-main-title')]/h6");
	public static final By REFRESHBTN = By.xpath("//app-device-dashboard//mat-icon[text()='refresh']");

	// navigation bar
	public static final By DEVICE_DASHBOARD = By.xpath("//a[contains(text(), 'DASHBOARD')]");
	public static final By DEVICE_DASHBOARD_BACKBTN = By
			.xpath("/html/body/app-root/app-device-dashboard/div/form/div[1]/div/div[1]/mat-icon");
	public static final By DEVICE_DASHBOARD_TITLE = By.xpath("//span[normalize-space()='Device Dashboard']");

	// KPI's
	// Total Production Devices
	public static final By DEVICE_DASHBOARD_TOTALPRODUCTIONDEVICESKPI = By
			.xpath("//span[text()='Total Production Devices']");
	public static final By DEVICE_DASHBOARD_TOTALPRODUCTIONDEVICESKPICOUNT = By
			.xpath("//span[contains(text(),'Total Production Devices')]/following-sibling::span[1]");
	public static final By DEVICEDASHBOARDKPITABLE = By.cssSelector("form h6.component-title");

	// Total Dispatched Devices
	public static final By DEVICE_DASHBOARD_TOTALDISPATCHEDDEVICESKPI = By
			.xpath("//span[contains(text(),'Total Dispatched Devices')]");
	public static final By DEVICE_DASHBOARD_TOTALDISPATCHEDDEVICESKPICOUNT = By
			.xpath("//span[contains(text(),'Total Dispatched Devices')]/following-sibling::span[1]");

	// Total Installed Devices
	public static final By DEVICE_DASHBOARD_TOTALINSTALLEDDEVICESKPI = By
			.xpath("//app-device-dashboard//div[2]/form/div[1]/div[3]/div[1]/span[1]");
	public static final By DEVICE_DASHBOARD_TOTALINSTALLEDDEVICESKPICOUNT = By
			.xpath("//app-device-dashboard//div[2]/form/div[1]/div[3]/div[1]/span[2]");

	// Total Discarded Devices
	public static final By DEVICE_DASHBOARD_TOTALDISCARDEDDEVICESKPI = By
			.xpath("//app-device-dashboard//div[2]/form/div[1]/div[4]/div[1]/span[1]");
	public static final By DEVICE_DASHBOARD_TOTALDISCARDEDDEVICESKPICOUNT = By
			.xpath("//app-device-dashboard//div[2]/form/div[1]/div[4]/div[1]/span[2]");

//	Table
//search box
//	public static final By DEVICE_DASHBOARD_SEARCHBOX = By.xpath("//app-common-component-search//input");
//	public static final By DEVICE_DASHBOARD_EXPORTBTN = By.xpath("//button[contains(text(),'Export')]");
	public static final By DEVICE_DASHBOARD_EXPORTBTN2 = By.xpath("//button[contains(text(),'Export')]");

	public static final By DEVICE_DASHBOARD_PRODUCTIONDEVICETABLEHEADER = By
			.xpath("/html/body/app-root/app-device-dashboard/div/form/div[4]/form/div[1]/div/div/div[1]/div/h6");
	public static final By DEVICE_DASHBOARD_DISPATCHEDDEVICESTABLEHEADER = By
			.xpath("/html/body/app-root/app-device-dashboard/div/form/div[4]/form/div[1]/div/div/div[1]/div/h6");

	public static final By DEVICE_DASHBOARD_SEARCHCLICK = By.xpath(
			"/html/body/app-root/app-device-dashboard/div/form/div[4]/form/div[1]/div/div/div[3]/app-common-component-search/div/div/button");
	public static final By DEVICE_DASHBOARD_SEARCHCLEAR = By.xpath(
			"/html/body/app-root/app-device-dashboard/div/form/div[4]/form/div[1]/div/div/div[3]/app-common-component-search/div/div/div/input");

	/// new locators
	public static final By DEVICE_DASHBOARD_SEARCHBTN = By.className("search-btn");
	public static final By DEVICE_DASHBOARD_SEARCHBOX = By.xpath("//input[contains(@formcontrolname, 'searchInput')]");
	public static final By DEVICE_DASHBOARD_EXPORTBTN = By.xpath("//button[contains(text(),'Export')]");
}
