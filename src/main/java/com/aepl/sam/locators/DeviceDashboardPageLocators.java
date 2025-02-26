package com.aepl.sam.locators;

import org.openqa.selenium.By;

public class DeviceDashboardPageLocators {
	// Shital
	
	//	navigation bar
	public static final By device_dashboard = By.xpath("/html/body/app-root/app-header/div/div/nav/ul/li[1]/a");
	public static final By device_dashboard_backbtn = By.xpath("/html/body/app-root/app-device-dashboard/div/form/div[1]/div/div[1]/mat-icon");
	public static final By device_dashboard_refreshbtn = By.xpath("/html/body/app-root/app-device-dashboard/div/form/div[1]/div/div[2]/mat-icon");
	public static final By device_dashboard_pagetitle = By.xpath("/html/body/app-root/app-device-dashboard/div/form/div[1]/span");
	public static final By device_dashboard_navbar = By.xpath("/html/body/app-root/app-header/div/div/nav/ul/li[1]/a");
	public static final By device_dashboard_navbar_deviceutility = By.xpath("/html/body/app-root/app-header/div/div/nav/ul/li[2]/a");
	public static final By device_dashboard_navbar_user = By.xpath("/html/body/app-root/app-header/div/div/nav/ul/li[3]/a");
	public static final By device_dashboard_navbar_userprofile = By.xpath("/html/body/app-root/app-header/div/div/nav/ul/li[4]/a/span");
	public static final By device_dashboard_TotalProductionDevicesKPI = By.id("TotalProductionDevices");
	public static final By device_dashboard_TotalDispatchedDevicesKPI = By.id("DispatchedDevices");
	public static final By device_dashboard_ProductionDevicesTableHeader = By.xpath("/html/body/app-root/app-device-dashboard/div/form/div[4]/form/div[1]/div/div/div[1]/div/h6");
	public static final By device_dashboard_DispatchedDevicesTableHeader = By.xpath("/html/body/app-root/app-device-dashboard/div/form/div[4]/form/div[1]/div/div/div[1]/div/h6");
	public static final By device_dashboard_searchbox = By.xpath("/html/body/app-root/app-device-dashboard/div/form/div[4]/form/div[1]/div/div/div[3]/app-common-component-search/div/div/div/input");
	public static final By device_dashboard_searchclick = By.xpath("/html/body/app-root/app-device-dashboard/div/form/div[4]/form/div[1]/div/div/div[3]/app-common-component-search/div/div/button");
	public static final By device_dashboard_searchclear = By.xpath("/html/body/app-root/app-device-dashboard/div/form/div[4]/form/div[1]/div/div/div[3]/app-common-component-search/div/div/div/input");
	
	
}
