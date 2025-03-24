package com.aepl.sam.tests;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.aepl.sam.base.TestBase;
import com.aepl.sam.pages.FotaPage;
import com.aepl.sam.utils.ExcelUtility;

public class FotaPageTest extends TestBase {
	private FotaPage fota;
	private ExcelUtility excelUtility;

	@BeforeClass
	public void setUp() {
		super.setUp();
		this.fota = new FotaPage(driver, wait, action);
		this.excelUtility = new ExcelUtility();
		excelUtility.initializeExcel("FOTA_Test");
	}

	@Test
	public void test() {
		Assert.fail("Failed");
	}
}
