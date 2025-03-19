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

	public void testClickAddUserBtn() {
		userManagement.clickAddUserBtn();
	}

	@Test(priority = 5)

	public void testAddUserProfilepicture() {
		userManagement.addUserProfilepicture();
	}


	@Test(priority = 5)
	public void testAddnewUser() {
		userManagement.addnewUser();

	@Test(priority = 6)
	public void testAddnewUser() {
		userManagement.addAndUpdateUser("add");
	}

	@Test(priority = 7)
	public void testCheckDropdown() {
		userManagement.checkDropdown();
	}

	@Test(priority = 8)
	public void testSearchUser() {
		userManagement.searchAndViewUser();
	}

	@Test(priority = 9)
	public void testViewAndUpdateUser() {
		userManagement.addAndUpdateUser("update");

	}
}
