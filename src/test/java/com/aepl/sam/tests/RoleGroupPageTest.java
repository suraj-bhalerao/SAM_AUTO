package com.aepl.sam.tests;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.aepl.sam.base.TestBase;
import com.aepl.sam.pages.GovernmentServerPage;
import com.aepl.sam.pages.RoleGroupPage;
import com.aepl.sam.utils.ExcelUtility;

public class RoleGroupPageTest extends TestBase{
	private ExcelUtility excelUtility;
	private RoleGroupPage roleGroup;

	@BeforeClass
	public void setUp() {
		super.setUp();
		this.roleGroup = new RoleGroupPage(driver, wait, action);
		this.excelUtility = new ExcelUtility();
		excelUtility.initializeExcel("Role_Group_Test");
	}
	
	@Test(priority = 1)
	public void testNavBarLink() {
		roleGroup.navBarLink();
	}

	@Test(priority = 2)
	public void testBackButton() {
		roleGroup.backButton();
	}

	@Test(priority = 3)
	public void testRefreshButton() {
		roleGroup.refreshButton();
	}
	
	@Test(priority = 4)
	public void testAddUserRole() {
		roleGroup.addUserRole();
	}
	@Test(priority = 5)
	public void testSearchRoleGroup() {
		roleGroup.searchRoleGroup();
	}
}
