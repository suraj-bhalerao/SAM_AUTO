package com.aepl.sam.tests;

import org.testng.annotations.BeforeClass;

import com.aepl.sam.base.TestBase;
import com.aepl.sam.pages.DeviceDetailsPage;
import com.aepl.sam.utils.CommonMethods;
import com.aepl.sam.utils.ExcelUtility;

public class DeviceDetailsPageTest extends TestBase{
	private DeviceDetailsPage deviceDetails;
	private ExcelUtility excelUtility;
	private CommonMethods comm;
	
	@BeforeClass
	public void setUp() {
		super.setUp();
		this.deviceDetails = new DeviceDetailsPage(driver, wait);
		this.comm = new CommonMethods(driver, wait);
		this.excelUtility = new ExcelUtility();
		excelUtility.initializeExcel("Device_Details_Test");
	}
}
