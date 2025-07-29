package com.aepl.sam.tests;

import java.util.function.Supplier;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import com.aepl.sam.base.TestBase;
import com.aepl.sam.constants.UserProfileConstants;
import com.aepl.sam.enums.Result;
import com.aepl.sam.pages.UserProfilePage;
import com.aepl.sam.utils.CommonMethods;
import com.aepl.sam.utils.ExcelUtility;

public class UserProfilePageTest extends TestBase implements UserProfileConstants {

	private ExcelUtility excelUtility;
	private UserProfilePage userProf;
	private CommonMethods comm;
	private SoftAssert softAssert;

	@BeforeClass
	public void setUp() {
		super.setUp();
		this.comm = new CommonMethods(driver, wait);
		this.userProf = new UserProfilePage(driver, wait);
		this.excelUtility = new ExcelUtility();
		this.softAssert = new SoftAssert();
		excelUtility.initializeExcel(SHEET_NAME);
	}

	private void executeTest(String testCaseName, String expected, Supplier<String> actualSupplier) {
		String actual = "";
		String result = Result.FAIL.getValue();

		logger.info("Executing test case: {}", testCaseName);
		try {
			actual = actualSupplier.get();
			softAssert.assertEquals(actual, expected, testCaseName + " failed!");
			result = expected.equalsIgnoreCase(actual) ? Result.PASS.getValue() : Result.FAIL.getValue();
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
		executeTest(TC_LOGO, EXP_LOGO, () -> comm.verifyWebpageLogo() ? EXP_LOGO : "Logo Not Displayed");
	}

	@Test(priority = 2)
	public void testPageTitle() {
		executeTest(TC_PAGE_TITLE, EXP_PAGE_TITLE, comm::verifyPageTitle);
	}

	@Test(priority = 3)
	public void testNavBarLink() {
		executeTest(TC_NAVBAR, EXP_NAVBAR, userProf::navBarLink);
	}

	@Test(priority = 4)
	public void testBackButton() {
		executeTest(TC_BACK_BUTTON, EXP_BACK_BUTTON, () -> {
			userProf.backButton();
			return EXP_BACK_BUTTON;
		});
	}

	@Test(priority = 5)
	public void testRefreshButton() {
		executeTest(TC_REFRESH, EXP_REFRESH, () -> {
			userProf.refreshButton();
			return EXP_REFRESH;
		});
	}

	@Test(priority = 6)
	public void testChangePassword() {
		executeTest(TC_CHANGE_PASSWORD, EXP_CHANGE_PASSWORD, () -> {
			userProf.changePassword();
			return EXP_CHANGE_PASSWORD;
		});
	}

	@Test(priority = 7)
	public void testUploadProfilePicture() {
		executeTest(TC_UPLOAD_PROFILE_PIC, EXP_UPLOAD_PROFILE_PIC, () -> {
			boolean isUploaded = userProf.uploadProfilePicture();
			return isUploaded ? EXP_UPLOAD_PROFILE_PIC : "Profile picture upload failed.";
		});
	}

	@Test(priority = 8)
	public void testUpdateProfileDetails() {
		executeTest(TC_UPDATE_PROFILE, EXP_UPDATE_PROFILE, () -> {
			boolean isUpdated = userProf.updateProfileDetails();
			return isUpdated ? EXP_UPDATE_PROFILE : "Profile update failed.";
		});
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
		executeTest(TC_VERSION, EXP_VERSION, comm::checkVersion);
	}

	@Test(priority = 11)
	public void testCopyright() {
		executeTest(TC_COPYRIGHT, EXP_COPYRIGHT, comm::checkCopyright);
	}
}
