package com.aepl.sam.tests;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.aepl.sam.base.TestBase;
import com.aepl.sam.pages.UserManagementPage;
import com.aepl.sam.utils.ExcelUtility;

public class UserManagementPageTest extends TestBase{

	private ExcelUtility excelUtility;
	private UserManagementPage userManagement;

	@BeforeClass
	public void setUp() {
		super.setUp();
		this.userManagement = new UserManagementPage(driver, wait);
		this.excelUtility = new ExcelUtility();
		excelUtility.initializeExcel("User_Management_Test");
	}
	
	@Test( priority = 1, 
		   enabled = true,
		   description = "Test case to check the link of the user management page")
	
	public void testAddNewUser() {
		
	}
}
