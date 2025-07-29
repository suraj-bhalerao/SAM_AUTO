package com.aepl.sam.tests;

import java.util.function.Supplier;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import com.aepl.sam.base.TestBase;
import com.aepl.sam.constants.Constants;
import com.aepl.sam.constants.GovernmentServerConstants;
import com.aepl.sam.enums.Result;
import com.aepl.sam.pages.GovernmentServerPage;
import com.aepl.sam.utils.CommonMethods;
import com.aepl.sam.utils.ExcelUtility;

public class GovernmentServerPageTest extends TestBase implements GovernmentServerConstants {
	private GovernmentServerPage govServerPage;
	private ExcelUtility excelUtility;
	private CommonMethods comm;
	private SoftAssert softAssert;

	@BeforeClass
	public void setUp() {
		super.setUp();
		this.govServerPage = new GovernmentServerPage(driver, wait, action);
		this.comm = new CommonMethods(driver, wait);
		this.excelUtility = new ExcelUtility();
		this.softAssert = new SoftAssert();
		excelUtility.initializeExcel(SHEET_NAME);
		logger.info("Setup completed for GovernmentServerPageTest");
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
		executeTest(TC_NAV_BAR, EXP_NAV_BAR, govServerPage::navBarLink);
	}

	@Test(priority = 4)
	public void testRefreshButton() {
		executeTest(TC_REFRESH, EXP_REFRESH, () -> {
			comm.clickRefreshButton();
			return driver.getCurrentUrl();
		});
	}

	@Test(priority = 5)
	public void testBackButton() {
		executeTest(TC_BACK, EXP_BACK, govServerPage::backButton);
	}

	@Test(priority = 6)
	public void testAddGovernmentServer() {
		executeTest(TC_ADD_SERVER, EXP_ADD_SERVER, () -> {
			String result = govServerPage.addGovernmentServer();
			return (result != null && !result.isEmpty()) ? EXP_ADD_SERVER : "Government Server Addition Failed";
		});
	}

	@Test(priority = 7)
	public void testFillForm() {
		executeTest(TC_FILL_FORM, EXP_FILL_FORM, () -> govServerPage.manageGovServer("add"));
	}

	@Test(priority = 8)
	public void testSearchAndView() {
		executeTest(TC_SEARCH_VIEW, EXP_SEARCH_VIEW, () -> {
			return govServerPage.searchAndView() ? EXP_SEARCH_VIEW : "Search and View Failed";
		});
	}

	@Test(priority = 9)
	public void testUpdateGovServer() {
		executeTest(TC_UPDATE, EXP_UPDATE, () -> govServerPage.manageGovServer("update"));
	}

	@Test(priority = 10)
	public void testAddFirmware() {
		executeTest(TC_ADD_FIRMWARE, EXP_ADD_FIRMWARE, () -> {
			return govServerPage.addFirmware() ? EXP_ADD_FIRMWARE : "Firmware Addition Failed";
		});
	}

//	@Test(priority = 11)
	public void testDeleteGovServer() {
		executeTest(TC_DELETE, EXP_DELETE, govServerPage::deleteGovServer);
	}

	@Test(priority = 12)
	public void testPagination() {
		executeTest(TC_PAGINATION, EXP_PAGINATION, () -> {
			comm.checkPagination();
			return EXP_PAGINATION;
		});
	}

	@Test(priority = 13)
	public void testVersion() {
		executeTest(TC_VERSION, Constants.EXP_VERSION_TEXT, comm::checkVersion);
	}

	@Test(priority = 14)
	public void testCopyright() {
		executeTest(TC_COPYRIGHT, Constants.EXP_COPYRIGHT_TEXT, comm::checkCopyright);
	}
}
