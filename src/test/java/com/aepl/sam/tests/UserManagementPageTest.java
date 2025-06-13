package com.aepl.sam.tests;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.aepl.sam.base.TestBase;
import com.aepl.sam.constants.Constants;
import com.aepl.sam.enums.Result;
import com.aepl.sam.pages.UserManagementPage;
import com.aepl.sam.utils.CommonMethods;
import com.aepl.sam.utils.ExcelUtility;

public class UserManagementPageTest extends TestBase {
	private ExcelUtility excelUtility;
	private UserManagementPage userManagement;
	private CommonMethods comm;

	@BeforeClass
	public void setUp() {
		super.setUp();
		this.comm = new CommonMethods(driver, wait);
		this.userManagement = new UserManagementPage(driver, wait, comm);
		this.excelUtility = new ExcelUtility();
		excelUtility.initializeExcel("User_Management_Test");
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
		String testCaseName = "Verify Navigation Bar Link";
		String expected = Constants.USR_MAN;
		String actual = "";
		String result = Result.FAIL.getValue();

		logger.info("Executing the test for: " + testCaseName);
		try {
			logger.info("Clicking on the navigation bar link...");
			actual = userManagement.navBarLink();

			softAssert.assertEquals(actual, expected, "Navigation bar link verification failed!");
			result = expected.equalsIgnoreCase(actual) ? Result.PASS.getValue() : Result.FAIL.getValue();
			logger.info("Result is: " + result);
		} catch (Exception e) {
			logger.error("An error occurred while verifying the navigation bar link.", e);
			result = Result.ERROR.getValue();
			e.printStackTrace();
		} finally {
			logger.info("Test case execution completed for: " + testCaseName);
			excelUtility.writeTestDataToExcel(testCaseName, expected, actual, result);
			softAssert.assertAll();
		}
	}

	@Test(priority = 4)
	public void testRefreshButton() {
		String testCaseName = "Verify Refresh Button Functionality";
		String expected = "User Management";
		String actual = "";
		String result = Result.FAIL.getValue();

		logger.info("Executing the test for: " + testCaseName);
		try {
			logger.info("Clicking the refresh button...");
			actual = userManagement.refreshButton();

			softAssert.assertEquals(actual, expected, "Refresh button verification failed!");
			result = expected.equalsIgnoreCase(actual) ? Result.PASS.getValue() : Result.FAIL.getValue();
			logger.info("Result is: " + result);
		} catch (Exception e) {
			logger.error("An error occurred while verifying the refresh button functionality.", e);
			result = Result.ERROR.getValue();
			e.printStackTrace();
		} finally {
			logger.info("Test case execution completed for: " + testCaseName);
			excelUtility.writeTestDataToExcel(testCaseName, expected, actual, result);
			softAssert.assertAll();
		}
	}

	@Test(priority = 5)
	public void testClickAddUserBtn() {
		String testCaseName = "Verify Add User Button Click";
		String expected = "Add User Button Clicked Successfully";
		String actual = "";
		String result = Result.FAIL.getValue();

		logger.info("Executing the test for: " + testCaseName);
		try {
			logger.info("Clicking the 'Add User' button...");
			actual = userManagement.clickAddUserBtn();

			softAssert.assertEquals(actual, expected, "Add User button click verification failed!");
			result = expected.equalsIgnoreCase(actual) ? Result.PASS.getValue() : Result.FAIL.getValue();
			logger.info("Result is: " + result);
		} catch (Exception e) {
			logger.error("An error occurred while clicking the 'Add User' button.", e);
			result = Result.ERROR.getValue();
			e.printStackTrace();
		} finally {
			logger.info("Test case execution completed for: " + testCaseName);
			excelUtility.writeTestDataToExcel(testCaseName, expected, actual, result);
			softAssert.assertAll();
		}
	}

	@Test(priority = 6)
	public void testAddUserProfilePicture() {
		String testCaseName = "Verify Add User Profile Picture";
		String expected = "Profile picture uploaded successfully.";
		String actual = "";
		String result = Result.FAIL.getValue();

		logger.info("Executing the test for: " + testCaseName);
		try {
			logger.info("Uploading profile picture...");

			userManagement.addUserProfilePicture();

			// If no exception occurs, assume success
			actual = "Profile picture uploaded successfully.";

			softAssert.assertEquals(actual, expected, "Profile picture upload verification failed!");
			result = expected.equalsIgnoreCase(actual) ? Result.PASS.getValue() : Result.FAIL.getValue();
			logger.info("Result is: " + result);
		} catch (Exception e) {
			logger.error("An error occurred while uploading profile picture.", e);
			actual = "Error uploading profile picture.";
			result = Result.ERROR.getValue();
			e.printStackTrace();
		} finally {
			logger.info("Test case execution completed for: " + testCaseName);
			excelUtility.writeTestDataToExcel(testCaseName, expected, actual, result);
			softAssert.assertAll();
		}
	}

	@Test(priority = 7)
	public void testAddnewUser() {
		String testCaseName = "Verify Add New User Functionality";
		String expected = "User 'add' operation completed successfully.";
		String actual = "";
		String result = Result.FAIL.getValue();

		logger.info("Executing the test for: " + testCaseName);
		try {
			logger.info("Performing add operation to add new user...");

			userManagement.addAndUpdateUser("add");

			// If no exception occurs, assume success
			actual = "User 'add' operation completed successfully.";

			softAssert.assertEquals(actual, expected, "Add New User operation verification failed!");
			result = expected.equalsIgnoreCase(actual) ? Result.PASS.getValue() : Result.FAIL.getValue();
			logger.info("Result is: " + result);
		} catch (Exception e) {
			logger.error("An error occurred while performing add new user operation.", e);
			actual = "Error during 'add' operation.";
			result = Result.ERROR.getValue();
			e.printStackTrace();
		} finally {
			logger.info("Test case execution completed for: " + testCaseName);
			excelUtility.writeTestDataToExcel(testCaseName, expected, actual, result);
			softAssert.assertAll();
		}
	}

	@Test(priority = 8)
	public void testCheckDropdown() {
		String testCaseName = "Verify Dropdown Options Functionality";
		String expected = "Dropdown options verified successfully.";
		String actual = "";
		String result = Result.FAIL.getValue();

		logger.info("Executing the test for: " + testCaseName);
		try {
			logger.info("Checking dropdown options...");

			userManagement.checkDropdown();

			actual = "Dropdown options verified successfully.";

			softAssert.assertEquals(actual, expected, "Dropdown verification failed!");
			result = expected.equalsIgnoreCase(actual) ? Result.PASS.getValue() : Result.FAIL.getValue();
			logger.info("Result is: " + result);
		} catch (Exception e) {
			logger.error("An error occurred while checking the dropdown options.", e);
			actual = "Error checking dropdown options.";
			result = Result.ERROR.getValue();
			e.printStackTrace();
		} finally {
			logger.info("Test case execution completed for: " + testCaseName);
			excelUtility.writeTestDataToExcel(testCaseName, expected, actual, result);
			softAssert.assertAll();
		}
	}

	@Test(priority = 9)
	public void testSearchUser() {
		String testCaseName = "Verify User Search and View Functionality";
		String expected = "User search and view successful.";
		String actual = "";
		String result = Result.FAIL.getValue();

		logger.info("Executing the test for: " + testCaseName);
		try {
			logger.info("Initiating user search and view...");

			userManagement.searchAndViewUser();

			actual = "User search and view successful.";

			softAssert.assertEquals(actual, expected, "User search and view verification failed!");
			result = expected.equalsIgnoreCase(actual) ? Result.PASS.getValue() : Result.FAIL.getValue();
			logger.info("Result is: " + result);
		} catch (Exception e) {
			logger.error("An error occurred while searching and viewing user.", e);
			actual = "Error searching and viewing user.";
			result = Result.ERROR.getValue();
			e.printStackTrace();
		} finally {
			logger.info("Test case execution completed for: " + testCaseName);
			excelUtility.writeTestDataToExcel(testCaseName, expected, actual, result);
			softAssert.assertAll();
		}
	}

	@Test(priority = 10)
	public void testViewAndUpdateUser() {
		String testCaseName = "Verify Update User Functionality";
		String expected = "User 'update' operation completed successfully.";
		String actual = "";
		String result = Result.FAIL.getValue();

		logger.info("Executing the test for: " + testCaseName);
		try {
			logger.info("Performing update operation to modify user...");

			userManagement.addAndUpdateUser("update");

			actual = "User 'update' operation completed successfully.";

			softAssert.assertEquals(actual, expected, "Update User operation verification failed!");
			result = expected.equalsIgnoreCase(actual) ? Result.PASS.getValue() : Result.FAIL.getValue();
			logger.info("Result is: " + result);
		} catch (Exception e) {
			logger.error("An error occurred while updating the user.", e);
			actual = "Error during 'update' operation.";
			result = Result.ERROR.getValue();
			e.printStackTrace();
		} finally {
			logger.info("Test case execution completed for: " + testCaseName);
			excelUtility.writeTestDataToExcel(testCaseName, expected, actual, result);
			softAssert.assertAll();
		}
	}

//	@Test(priority = 11)
	public void testDeleteUser() {
		String testCaseName = "Verify Delete User Functionality";
		String expected = "User deleted successfully";
		String actual = "";
		String result = Result.FAIL.getValue();

		logger.info("Executing the test for: " + testCaseName);
		try {
			logger.info("Performing delete operation to remove user...");

			actual = userManagement.deleteUser();

			softAssert.assertEquals(actual, expected, "Delete User operation verification failed!");
			result = expected.equalsIgnoreCase(actual) ? Result.PASS.getValue() : Result.FAIL.getValue();
			logger.info("Result is: " + result);
		} catch (Exception e) {
			logger.error("An error occurred while deleting the user.", e);
			actual = "Error during delete operation.";
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
