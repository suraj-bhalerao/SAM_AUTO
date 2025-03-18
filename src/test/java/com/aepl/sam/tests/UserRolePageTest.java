package com.aepl.sam.tests;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.aepl.sam.base.TestBase;
import com.aepl.sam.pages.UserRolePage;
import com.aepl.sam.utils.ExcelUtility;

public class UserRolePageTest extends TestBase {
	private ExcelUtility excelUtility;
	private UserRolePage userRole;

	@BeforeClass
	public void setUp() {
		super.setUp();
		this.userRole = new UserRolePage(driver, wait, action);
		this.excelUtility = new ExcelUtility();
		excelUtility.initializeExcel("Government_Server_Test");
	}

	@Test(priority = 1)
	public void testNavBarLink() {
		userRole.navBarLink();
	}

	@Test(priority = 2)
	public void testBackButton() {
		userRole.backButton();
	}

	@Test(priority = 3)
	public void testRefreshButton() {
		userRole.refreshButton();
	}

	@Test(priority = 4)
	public void testClickAddUserRole() {
		userRole.clickAddUserRoleBtn();
	}

	@Test(priority = 5)
	public void testSelectingOptions() {
		userRole.selectingOptions();
	}

	@Test(priority = 6)
	public void testSelectOptionsAndSubmit() {
		userRole.selectOptionsAndSubmit();
	}

	@Test(priority = 7)
	public void testSearchUserRole() {
		userRole.searchUserRole();
	}

	@Test(priority = 8)
	public void testUpdateUserRole() {
		userRole.updateUserRole();
	}

	@Test(priority = 9)
	public void testDeleteUserRole() {
		userRole.deleteUserRole();
	}
}
