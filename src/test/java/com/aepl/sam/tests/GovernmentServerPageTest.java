package com.aepl.sam.tests;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.aepl.sam.base.TestBase;
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
		this.comm = new CommonMethods(driver, wait);
		excelUtility.initializeExcel("Government_Server_Test");
	}

	@Test(priority = 1)
	public void testClickNavBar() {
		govServerPage.navBarLink();
	}

	@Test(priority = 2)
	public void testBackButton() {
		govServerPage.backButton();
	}

	@Test(priority = 3)
	public void testRefreshButton() {
		govServerPage.refreshButton();

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
