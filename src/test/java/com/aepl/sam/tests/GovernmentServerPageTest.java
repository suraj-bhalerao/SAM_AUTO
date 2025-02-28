package com.aepl.sam.tests;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.aepl.sam.base.TestBase;
import com.aepl.sam.pages.GovernmentServerPage;
import com.aepl.sam.utils.ExcelUtility;

public class GovernmentServerPageTest extends TestBase {
	private ExcelUtility excelUtility;
	private GovernmentServerPage govServerPage;

	@BeforeClass
	public void setUp() {
		super.setUp();
		this.govServerPage = new GovernmentServerPage(driver, wait);
		this.excelUtility = new ExcelUtility();
		excelUtility.initializeExcel("Government_Server_Test");
	}
	
	
	@Test(priority = 1)
	public void testClickNavBar() {
		govServerPage.clickOnNavBar();
	}
}
