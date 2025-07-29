package com.aepl.sam.tests;

import java.util.function.Supplier;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import com.aepl.sam.base.TestBase;
import com.aepl.sam.constants.Constants;
import com.aepl.sam.constants.GroupManagementConstants;
import com.aepl.sam.enums.Result;
import com.aepl.sam.pages.GroupManagementPage;
import com.aepl.sam.utils.CommonMethods;
import com.aepl.sam.utils.ExcelUtility;

public class GroupManagementPageTest extends TestBase implements GroupManagementConstants {
	private ExcelUtility excelUtility;
	private GroupManagementPage groupManagement;
	private CommonMethods comm;
	private SoftAssert softAssert;

	@BeforeClass
	public void setUp() {
		super.setUp();
		this.comm = new CommonMethods(driver, wait);
		this.groupManagement = new GroupManagementPage(driver, wait, comm);
		this.excelUtility = new ExcelUtility();
		this.softAssert = new SoftAssert();
		excelUtility.initializeExcel(SHEET_NAME);
		logger.info("Setup completed for GroupManagementPageTest");
	}

	private void executeTest(String testCaseName, String expected, Supplier<String> actualSupplier) {
		String actual = "";
		String result = Result.FAIL.getValue();
		logger.info("Executing test case: {}", testCaseName);
		try {
			actual = actualSupplier.get();
			softAssert.assertEquals(actual, expected, testCaseName + " failed!");
			result = expected.equalsIgnoreCase(actual) ? Result.PASS.getValue() : Result.FAIL.getValue();
			logger.info("Test result: {}", result);
		} catch (Exception e) {
			logger.error("Error in test case {}: {}", testCaseName, e.getMessage(), e);
			result = Result.ERROR.getValue();
		} finally {
			excelUtility.writeTestDataToExcel(testCaseName, expected, actual, result);
			softAssert.assertAll();
		}
	}

	@Test(priority = 1)
	public void testCompanyLogo() {
		executeTest(TC_LOGO, EXP_LOGO_DISPLAYED,
				() -> comm.verifyWebpageLogo() ? EXP_LOGO_DISPLAYED : "Logo Not Displayed");
	}

	@Test(priority = 2)
	public void testPageTitle() {
		executeTest(TC_PAGE_TITLE, EXP_PAGE_TITLE, comm::verifyPageTitle);
	}

	@Test(priority = 3)
	public void testClickNavBar() {
		executeTest(TC_NAV_BAR, EXP_NAV_BAR, groupManagement::navBarLink);
	}

	@Test(priority = 4)
	public void testBackButton() {
		executeTest(TC_BACK, EXP_BACK, groupManagement::backButton);
	}

	@Test(priority = 5)
	public void testRefreshButton() {
		executeTest(TC_REFRESH, EXP_REFRESH, () -> {
			comm.clickRefreshButton();
			return driver.getCurrentUrl();
		});
	}

	@Test(priority = 6)
	public void testAddUserRole() {
		executeTest(TC_ADD_ROLE, EXP_ADD_ROLE, () -> {
			groupManagement.addGroup();
			return EXP_ADD_ROLE;
		});
	}

	@Test(priority = 7)
	public void testSearchgroupManagement() {
		executeTest(TC_SEARCH_ROLE, EXP_SEARCH_ROLE, () -> {
			return groupManagement.isGroupManagementFound("QA") ? EXP_SEARCH_ROLE : "Role group not found";
		});
	}

	// Uncomment and enable this test if deletion functionality is required
	// @Test(priority = 8)
	public void testDeleteRoleGroup() {
		executeTest(TC_DELETE_ROLE, EXP_DELETE_ROLE, groupManagement::deleteRoleGroup);
	}

	@Test(priority = 9)
	public void testPagination() {
		executeTest(TC_PAGINATION, EXP_PAGINATION, () -> {
			comm.checkPagination();
			return EXP_PAGINATION;
		});
	}

	@Test(priority = 10)
	public void testVersion() {
		executeTest(TC_VERSION, Constants.EXP_VERSION_TEXT, comm::checkVersion);
	}

	@Test(priority = 11)
	public void testCopyright() {
		executeTest(TC_COPYRIGHT, Constants.EXP_COPYRIGHT_TEXT, comm::checkCopyright);
	}
}
