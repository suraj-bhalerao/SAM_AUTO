package com.aepl.sam.tests;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.aepl.sam.base.TestBase;
import com.aepl.sam.pages.UserManagementPage;
import com.aepl.sam.utils.ExcelUtility;

public class UserManagementPageTest extends TestBase {

	private ExcelUtility excelUtility;
	private UserManagementPage userManagement;

	@BeforeClass
	public void setUp() {
		super.setUp();
		this.userManagement = new UserManagementPage(driver, wait, action);
		this.excelUtility = new ExcelUtility();
		excelUtility.initializeExcel("User_Management_Test");
	}

	@Test(priority = 1)
	public void testNavBarLink() {
		userManagement.navBarLink();
	}

	@Test(priority = 2)
	public void testBackButton() {
		userManagement.backButton();
	}

	@Test(priority = 3)
	public void testRefreshButton() {
		userManagement.refreshButton();
	}

	@Test(priority = 4)
	public void testAddUserProfilepicture() {
		userManagement.addUserProfilepicture();
	}

	@Test(priority = 5)
	public void testAddnewUser() {
		userManagement.addnewUser();
	}
}
