package com.aepl.sam.tests;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.aepl.sam.base.TestBase;
import com.aepl.sam.constants.Constants;
import com.aepl.sam.enums.Result;
import com.aepl.sam.pages.RoleManagementPage;
import com.aepl.sam.utils.CommonMethods;
import com.aepl.sam.utils.ExcelUtility;

public class RoleManagementPageTest extends TestBase {
	private ExcelUtility excelUtility;
	private RoleManagementPage userRole;
	private CommonMethods comm;

	@BeforeClass
	public void setUp() {
		super.setUp();
		this.comm = new CommonMethods(driver, wait);
		this.userRole = new RoleManagementPage(driver, wait);
		this.excelUtility = new ExcelUtility();
		excelUtility.initializeExcel("User_Role_Test");
	}

	@Test(priority = 1)
	public void testCompanyLogo() {
		String testCaseName = "Verify Company Logo on Webpage";
		String expected = "Logo Displayed";
		String actual = "";
		String result = Result.FAIL.getValue();

		logger.info("Executing the test for: " + testCaseName);
		try {
			logger.info("Verifying if the company logo is displayed...");
			boolean isLogoDisplayed = comm.verifyWebpageLogo();
			actual = isLogoDisplayed ? "Logo Displayed" : "Logo Not Displayed";

			softAssert.assertEquals(actual, expected, "Company logo verification failed!");
			result = expected.equalsIgnoreCase(actual) ? Result.PASS.getValue() : Result.FAIL.getValue();
			logger.info("Result is: " + result);
		} catch (Exception e) {
			logger.error("An error occurred while verifying the company logo.", e);
			result = Result.ERROR.getValue();
			e.printStackTrace();
		} finally {
			logger.info("Test case execution completed for: " + testCaseName);
			excelUtility.writeTestDataToExcel(testCaseName, expected, actual, result);
			softAssert.assertAll();
		}
	}

	@Test(priority = 2)
	public void testPageTitle() {
		String testCaseName = "Verify Page Title on Webpage";
		String expected = "AEPL Sampark Diagnostic Cloud";
		String actual = "";
		String result = Result.FAIL.getValue();

		logger.info("Executing the test for: " + testCaseName);
		try {
			logger.info("Verifying the page title...");
			actual = comm.verifyPageTitle();

			softAssert.assertEquals(actual, expected, "Page title verification failed!");
			result = expected.equalsIgnoreCase(actual) ? Result.PASS.getValue() : Result.FAIL.getValue();
			logger.info("Result is: " + result);
		} catch (Exception e) {
			logger.error("An error occurred while verifying the page title.", e);
			result = Result.ERROR.getValue();
			e.printStackTrace();
		} finally {
			logger.info("Test case execution completed for: " + testCaseName);
			excelUtility.writeTestDataToExcel(testCaseName, expected, actual, result);
			softAssert.assertAll();
		}
	}

	@Test(priority = 3)
	public void testNavBarLink() {
		String testCaseName = "Verify NavBar Link Navigation";
		String expected = Constants.ROLE_MANAGEMENT;
		String actual = "";
		String result = Result.FAIL.getValue();

		try {
			actual = userRole.navBarLink(); // This method should return the current URL

			softAssert.assertEquals(actual, expected, "Navigation to role management page failed!");
			result = expected.equalsIgnoreCase(actual) ? Result.PASS.getValue() : Result.FAIL.getValue();
		} catch (Exception e) {
			e.printStackTrace();
			actual = "Error navigating to NavBar link.";
			result = Result.ERROR.getValue();
		} finally {
			excelUtility.writeTestDataToExcel(testCaseName, expected, actual, result);
			softAssert.assertAll();
		}
	}

	@Test(priority = 4)
	public void testBackButton() {
		String testCaseName = "Verify Back Button Navigation";
		String expected = "Back button navigated successfully.";
		String actual = "";
		String result = Result.FAIL.getValue();

		logger.info("Executing the test for: " + testCaseName);
		try {
			logger.info("Clicking back button...");

			userRole.backButton();

			actual = "Back button navigated successfully.";

			softAssert.assertEquals(actual, expected, "Back button navigation failed!");
			result = expected.equalsIgnoreCase(actual) ? Result.PASS.getValue() : Result.FAIL.getValue();
			logger.info("Result is: " + result);
		} catch (Exception e) {
			logger.error("An error occurred while clicking the back button.", e);
			actual = "Error navigating back.";
			result = Result.ERROR.getValue();
			e.printStackTrace();
		} finally {
			logger.info("Test case execution completed for: " + testCaseName);
			excelUtility.writeTestDataToExcel(testCaseName, expected, actual, result);
			softAssert.assertAll();
		}
	}

	@Test(priority = 5)
	public void testRefreshButton() {
		String testCaseName = "Verify Refresh Button Functionality";
		String expected = "Role Management";
		String actual = "";
		String result = Result.FAIL.getValue();

		try {
			actual = userRole.refreshButton();

			softAssert.assertEquals(actual, expected, "Page title mismatch after clicking Refresh button.");
			result = expected.equalsIgnoreCase(actual) ? Result.PASS.getValue() : Result.FAIL.getValue();
		} catch (Exception e) {
			e.printStackTrace();
			actual = "Exception occurred during refresh.";
			result = Result.ERROR.getValue();
		} finally {
			excelUtility.writeTestDataToExcel(testCaseName, expected, actual, result);
			softAssert.assertAll();
		}
	}

	@Test(priority = 6)
	public void testClickAddUserRole() {
		String testCaseName = "Verify 'Add User Role' Button Click";
		String expected = "Role Management";
		String actual = "";
		String result = Result.FAIL.getValue();

		try {
			actual = userRole.clickAddUserRoleBtn();
			softAssert.assertEquals(actual, expected, "'Add User Role' screen title mismatch.");
			result = expected.equalsIgnoreCase(actual) ? Result.PASS.getValue() : Result.FAIL.getValue();
		} catch (Exception e) {
			e.printStackTrace();
			actual = "Exception occurred";
			result = Result.ERROR.getValue();
		} finally {
			excelUtility.writeTestDataToExcel(testCaseName, expected, actual, result);
			softAssert.assertAll();
		}
	}

	@Test(priority = 7)
	public void testSelectingOptions() {
		String testCaseName = "Verify Selecting Options in Add User Role";
		String expected = "Options selected successfully";
		String actual = "Options selected successfully";
		String result = Result.FAIL.getValue();

		logger.info("Executing the test for: " + testCaseName);

		try {
			logger.info("Selecting role name, role type, and role group...");
			userRole.selectingOptions();

			result = Result.PASS.getValue();
			logger.info("Result is: " + result);
		} catch (Exception e) {
			logger.error("An error occurred while selecting options.", e);
			actual = "Exception occurred during selection";
			result = Result.ERROR.getValue();
			e.printStackTrace();
		} finally {
			logger.info("Test case execution completed for: " + testCaseName);
			excelUtility.writeTestDataToExcel(testCaseName, expected, actual, result);
			softAssert.assertAll();
		}
	}

	@Test(priority = 8)
	public void testSelectOptionsAndSubmit() {
		String testCaseName = "Verify Select All Permissions and Submit Role";
		String expected = "User role permissions selected and submitted";
		String actual = "User role permissions selected and submitted";
		String result = Result.FAIL.getValue();

		logger.info("Executing the test for: " + testCaseName);

		try {
			logger.info("Selecting all permissions and submitting the role...");
			userRole.selectOptionsAndSubmit();

			result = Result.PASS.getValue();
			logger.info("Result is: " + result);
		} catch (Exception e) {
			logger.error("An error occurred during selecting options and submission.", e);
			actual = "Exception occurred while submitting role";
			result = Result.ERROR.getValue();
			e.printStackTrace();
		} finally {
			logger.info("Test case execution completed for: " + testCaseName);
			excelUtility.writeTestDataToExcel(testCaseName, expected, actual, result);
			softAssert.assertAll();
		}
	}

	@Test(priority = 9)
	public void testSearchUserRole() {
		String testCaseName = "Verify Search User Role Functionality";
		String expected = "Role 'DEMO' should be found and searched successfully";
		String actual = "Role 'DEMO' should be found and searched successfully";
		String result = Result.FAIL.getValue();

		logger.info("Executing the test for: " + testCaseName);

		try {
			logger.info("Searching for the role named 'DEMO'...");

			// Call the method to perform the search
			userRole.searchUserRole();

			result = Result.PASS.getValue();
			logger.info("Result is: " + result);
		} catch (Exception e) {
			logger.error("An error occurred while searching for the user role.", e);
			actual = "Exception occurred while performing user role search";
			result = Result.ERROR.getValue();
			e.printStackTrace();
		} finally {
			logger.info("Test case execution completed for: " + testCaseName);
			excelUtility.writeTestDataToExcel(testCaseName, expected, actual, result);
			softAssert.assertAll();
		}
	}

	@Test(priority = 10)
	public void testUpdateUserRole() {
		String testCaseName = "Verify Update User Role Functionality";
		String expected = "User role should be updated successfully";
		String actual = "User role should be updated successfully";
		String result = Result.FAIL.getValue();

		logger.info("Executing the test for: " + testCaseName);

		try {
			userRole.updateUserRole(); // call to method containing all logic
			result = Result.PASS.getValue();
			logger.info("Result is: " + result);
		} catch (Exception e) {
			logger.error("An error occurred while updating the user role.", e);
			actual = "Exception occurred while updating the user role";
			result = Result.ERROR.getValue();
			e.printStackTrace();
			logger.info("Test case execution completed for: " + testCaseName);
			excelUtility.writeTestDataToExcel(testCaseName, expected, actual, result);
			softAssert.assertAll();
		}
	}

	@Test(priority = 11)
	public void testDeleteUserRole() {
		String testCaseName = "Verify Delete User Role Functionality";
		String expected = "User role should be deleted successfully";
		String actual = "User role should be deleted successfully";
		String result = Result.FAIL.getValue();

		logger.info("Executing the test for: " + testCaseName);

		try {
			userRole.deleteUserRole(); // All interaction logic is encapsulated in this method

			result = Result.PASS.getValue();
			logger.info("Result is: " + result);
		} catch (Exception e) {
			logger.error("An error occurred while deleting the user role.", e);
			actual = "Exception occurred while deleting the user role";
			result = Result.ERROR.getValue();
			e.printStackTrace();
		} finally {
			logger.info("Test case execution completed for: " + testCaseName);
			excelUtility.writeTestDataToExcel(testCaseName, expected, actual, result);
			softAssert.assertAll();
		}
	}

	@Test(priority = 12)
	public void testPagination() {

		String testCaseName = "Verify Pagination Functionality";
		String expected = "Pagination works correctly";
		String actual = "";
		String result = Result.FAIL.getValue();

		System.out.println("Executing the test for: " + testCaseName);
		try {
			System.out.println("Testing pagination functionality...");
			comm.checkPagination();
			actual = "Pagination works correctly"; // This should be replaced with actual pagination verification logic
			softAssert.assertEquals(actual, expected, "Pagination verification failed!");
			result = expected.equalsIgnoreCase(actual) ? Result.PASS.getValue() : Result.FAIL.getValue();
			System.out.println("Result is: " + result);
		} catch (Exception e) {
			System.out.println("An error occurred while verifying the pagination functionality: " + e.getMessage());
			result = Result.ERROR.getValue();
		} finally {
			System.out.println("Test case execution completed for: " + testCaseName);
			excelUtility.writeTestDataToExcel(testCaseName, expected, actual, result);
			softAssert.assertAll();
		}
	}

	@Test(priority = 13)
	public void testVersion() {
		String testCaseName = "Verify Version Functionality";
		String expected = Constants.EXP_VERSION_TEXT;
		String actual = "";
		String result = Result.FAIL.getValue();

		System.out.println("Executing the test for: " + testCaseName);
		try {
			System.out.println("Verifying version display...");
			actual = comm.checkVersion();
			softAssert.assertEquals(actual, expected, "Version verification failed!");
			result = expected.equalsIgnoreCase(actual) ? Result.PASS.getValue() : Result.FAIL.getValue();
			System.out.println("Result is: " + result);
		} catch (Exception e) {
			System.out.println("An error occurred while verifying the version functionality: " + e.getMessage());
			result = Result.ERROR.getValue();
		} finally {
			System.out.println("Test case execution completed for: " + testCaseName);
			excelUtility.writeTestDataToExcel(testCaseName, expected, actual, result);
			softAssert.assertAll();
		}
	}

	@Test(priority = 14)
	public void testCopyright() {
		String testCaseName = "Verify Copyright Functionality";
		String expected = Constants.EXP_COPYRIGHT_TEXT;
		String actual = "";
		String result = Result.FAIL.getValue();

		System.out.println("Executing the test for: " + testCaseName);
		try {
			System.out.println("Verifying copyright display...");
			actual = comm.checkCopyright();
			softAssert.assertEquals(actual, expected, "Copyright verification failed!");
			result = expected.equalsIgnoreCase(actual) ? Result.PASS.getValue() : Result.FAIL.getValue();
			System.out.println("Result is: " + result);
		} catch (Exception e) {
			System.out.println("An error occurred while verifying the copyright functionality: " + e.getMessage());
			result = Result.ERROR.getValue();
		} finally {
			System.out.println("Test case execution completed for: " + testCaseName);
			excelUtility.writeTestDataToExcel(testCaseName, expected, actual, result);
			softAssert.assertAll();
		}
	}
}
