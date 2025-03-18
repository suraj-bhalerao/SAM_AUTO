package com.aepl.sam.tests;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.aepl.sam.base.TestBase;
import com.aepl.sam.pages.UserProfilePage;
import com.aepl.sam.utils.ExcelUtility;

public class UserProfilePageTest extends TestBase {
	private ExcelUtility excelUtility;
	private UserProfilePage userProf;

	@BeforeClass
	public void setUp() {
		super.setUp();
		this.userProf = new UserProfilePage(driver, wait, action);
		this.excelUtility = new ExcelUtility();
		excelUtility.initializeExcel("User_Profile_Test");
	}

	@Test(priority = 1)
	public void testNavBarLink() {
		String url = userProf.navBarLink();
	}

	@Test(priority = 2)
	public void testBackButton() {
		userProf.backButton();
	}

	@Test(priority = 3)
	public void testRefreshButton() {
		userProf.refreshButton();
	}

	@Test(priority = 4)
	public void testChangePassword() {
		userProf.changePassword();
	}

	@Test(priority = 6)
	public void testUploadProfilePicture() {
		userProf.uploadProfilePicture();
	}

	@Test(priority = 5)
	public void testUpdateProfileDetails() {
		userProf.updateProfileDetails();
	}

}
