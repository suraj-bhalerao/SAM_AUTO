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
		this.govServerPage = new GovernmentServerPage(driver, wait, action);
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
			System.out.println("Checking for gov link");
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

	@Test(priority = 2)
	public void testBackButton() {
		String testCaseName = "Back btn on government page";
		String expectedResult = Constants.GOV_LINK;
		String actualResult = "";
		String result = "";

		try {
			actualResult = govServerPage.backButton();
			System.out.println("Checking for back button to gov link");
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

	@Test(priority = 3)
	public void testRefreshButton() {
		String testCaseName = "Refresh btn on government page";
		String expectedResult = "Government Server";
		String actualResult = "";
		String result = "";

		try {
			actualResult = govServerPage.refreshButton();
			System.out.println(actualResult);
			softAssert.assertEquals(expectedResult, actualResult);
			result = actualResult.contains(expectedResult) ? "PASS" : "FAIL";
		} catch (Exception e) {
			result = expectedResult.equals(actualResult) ? "PASS" : "FAIL";
			e.getMessage();
		} finally {
			softAssert.assertAll();
			excelUtility.writeTestDataToExcel(testCaseName, expectedResult, actualResult, result);
		}
	}
	
	
}
