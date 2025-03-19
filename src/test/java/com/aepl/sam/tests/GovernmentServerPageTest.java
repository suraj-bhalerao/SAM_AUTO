package com.aepl.sam.tests;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.aepl.sam.base.TestBase;
import com.aepl.sam.constants.Constants;
import com.aepl.sam.locators.GovernmentServerPageLocators;
import com.aepl.sam.pages.CommonPage;
import com.aepl.sam.pages.GovernmentServerPage;
import com.aepl.sam.utils.CommonMethods;
import com.aepl.sam.utils.ExcelUtility;

public class GovernmentServerPageTest extends TestBase {
	private ExcelUtility excelUtility;
	private GovernmentServerPage govServerPage;
	private CommonMethods comm;

	@BeforeClass
	public void setUp() {
		super.setUp();
		this.govServerPage = new GovernmentServerPage(driver, wait, action);
		this.excelUtility = new ExcelUtility();
		this.comm = new CommonMethods(driver, wait, action);
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

			actualResult = comm.navBarLink(GovernmentServerPageLocators.GOVERNMENT_NAV_LINK);

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

		comm.backButton();

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

		comm.refreshButton();

	}

	@Test(priority = 4)
	public void testAddGovernmentServer() {
		String governmentServer = govServerPage.addGovernmentServer();

		System.out.println("Clicked on the goverment server add button : " + governmentServer);

	}

	@Test(priority = 5)
	public void testFillForm() {
		govServerPage.manageGovServer("add");
	}

	// Search and view
	@Test(priority = 6)
	public void testSearchAndView() {

		govServerPage.searchAndView();

//		govServerPage.searchAndView();
		comm.searchItem();

	}

	// Update
	@Test(priority = 7)
	public void testUpdateGovServer() {
		govServerPage.manageGovServer("update");
	}

	@Test(priority = 8)
	public void testAddFirmware() {
		govServerPage.addFirmware();
	}

	@Test(priority = 9)
	public void testDeleteGovServer() {
		govServerPage.deleteGovServer();
	}
}
