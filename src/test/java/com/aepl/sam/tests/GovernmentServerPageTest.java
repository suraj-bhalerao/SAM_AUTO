package com.aepl.sam.tests;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.aepl.sam.base.TestBase;
import com.aepl.sam.constants.Constants;
import com.aepl.sam.pages.GovernmentServerPage;
import com.aepl.sam.utils.ExcelUtility;

public class GovernmentServerPageTest extends TestBase {
	private ExcelUtility excelUtility;
	private GovernmentServerPage govServerPage;

	@BeforeClass
	public void setUp() {
		super.setUp();
		this.govServerPage = new GovernmentServerPage(driver, wait , action);
		this.excelUtility = new ExcelUtility();
		excelUtility.initializeExcel("Government_Server_Test");
	}

	@Test(priority = 1)
	public void testClickNavBar() {
		String testCaseName = "Checking the government server page";
		String expectedResult = Constants.GOV_LINK;
		String actualResult = "";
		String result = "";

		try {
			actualResult = govServerPage.navBarLink();
			softAssert.assertEquals(expectedResult, actualResult);
			result = expectedResult.equals(actualResult) ? "PASS" : "FAIL";
		} catch (Exception e) {
			result = expectedResult.equals(actualResult) ? "PASS" : "FAIL";
			e.getMessage();
		} finally {
			softAssert.assertAll();
			excelUtility.writeTestDataToExcel(testCaseName, expectedResult, actualResult, result);
		}
	}
}
