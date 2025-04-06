package com.aepl.sam.tests;

import org.testng.annotations.BeforeClass;

import com.aepl.sam.base.TestBase;
import com.aepl.sam.pages.FotaPage;
import com.aepl.sam.utils.CommonMethods;
import com.aepl.sam.utils.ExcelUtility;

public class FotaPageTest extends TestBase {
	private FotaPage fota;
	private ExcelUtility excelUtility;
	private CommonMethods comm;

	@BeforeClass
	public void setUp() {
		super.setUp();
		this.fota = new FotaPage(driver, wait, action);
		this.comm = new CommonMethods(driver, wait);
		this.excelUtility = new ExcelUtility();
		excelUtility.initializeExcel("FOTA_Test");
	}
	
	
}
