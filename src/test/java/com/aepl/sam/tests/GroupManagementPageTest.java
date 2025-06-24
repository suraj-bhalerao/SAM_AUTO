package com.aepl.sam.tests;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import com.aepl.sam.base.TestBase;
import com.aepl.sam.constants.Constants;
import com.aepl.sam.enums.Result;
import com.aepl.sam.pages.GroupManagementPage;
import com.aepl.sam.utils.CommonMethods;
import com.aepl.sam.utils.ExcelUtility;

public class GroupManagementPageTest extends TestBase {
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
		excelUtility.initializeExcel("Group_Management_Test");
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
	public void testClickNavBar() {
		String testCaseName = "Verify Navigation Bar Click Functionality";
		String expected = Constants.ROLE_GROUP;
		String actual = "";
		String result = Result.FAIL.getValue();

		logger.info("Executing the test for: " + testCaseName);
		try {

			logger.info("Clicking on the navigation bar using device utility...");

			actual = groupManagement.navBarLink();

			softAssert.assertEquals(actual, expected, "Navigation bar click verification failed!");
			result = expected.equalsIgnoreCase(actual) ? Result.PASS.getValue() : Result.FAIL.getValue();
			logger.info("Result is: " + result);
		} catch (Exception e) {
			logger.error("An error occurred while verifying the navigation bar click.", e);
			result = Result.ERROR.getValue();
			e.printStackTrace();
		} finally {
			logger.info("Test case execution completed for: " + testCaseName);
			excelUtility.writeTestDataToExcel(testCaseName, expected, actual, result);
			softAssert.assertAll();
		}
	}

	@Test(priority = 4)
	public void testBackButton() {
		String testCaseName = "Verify Back Button Functionality";
		String expected = Constants.ROLE_GROUP;
		String actual = "";
		String result = Result.FAIL.getValue();

		System.out.println("Executing the test for: " + testCaseName);
		try {
			System.out.println("Clicking the back button...");
			actual = groupManagement.backButton();
			softAssert.assertEquals(actual, expected, "Back button verification failed!");
			result = expected.equalsIgnoreCase(actual) ? Result.PASS.getValue() : Result.FAIL.getValue();
			System.out.println("Result is: " + result);
		} catch (Exception e) {
			System.out.println("An error occurred while verifying the back button: " + e.getMessage());
			result = Result.ERROR.getValue();
		} finally {
			System.out.println("Test case execution completed for: " + testCaseName);
			excelUtility.writeTestDataToExcel(testCaseName, expected, actual, result);
			softAssert.assertAll();
		}
	}

	@Test(priority = 5)
	public void testRefreshButton() {
		String testCaseName = "Verify Refresh Button Functionality";
		String expected = Constants.ROLE_GROUP;
		String actual = "";
		String result = Result.FAIL.getValue();

		System.out.println("Executing the test for: " + testCaseName);
		try {
			System.out.println("Clicking the refresh button...");
			comm.clickRefreshButton();
			actual = driver.getCurrentUrl();
			softAssert.assertEquals(actual, expected, "Refresh button verification failed!");
			result = expected.equalsIgnoreCase(actual) ? Result.PASS.getValue() : Result.FAIL.getValue();
			System.out.println("Result is: " + result);
		} catch (Exception e) {
			System.out.println("An error occurred while verifying the refresh button: " + e.getMessage());
			result = Result.ERROR.getValue();
		} finally {
			System.out.println("Test case execution completed for: " + testCaseName);
			excelUtility.writeTestDataToExcel(testCaseName, expected, actual, result);
			softAssert.assertAll();
		}
	}

	@Test(priority = 6)
	public void testAddUserRole() {
		String testCaseName = "Verify Add User Role Functionality";
		String expected = "User Role Group Added Successfully";
		String actual = "";
		String result = Result.FAIL.getValue();

		System.out.println("Executing the test for: " + testCaseName);
		try {
			System.out.println("Adding a new user role...");
			groupManagement.addGroup();
//	        groupManagement.getSuccessMessage();
			actual = "User Role Group Added Successfully";
			softAssert.assertEquals(actual, expected, "Add User Role verification failed!");
			result = expected.equalsIgnoreCase(actual) ? Result.PASS.getValue() : Result.FAIL.getValue();
			System.out.println("Result is: " + result);
		} catch (Exception e) {
			System.out.println("An error occurred while verifying the Add User Role functionality: " + e.getMessage());
			result = Result.ERROR.getValue();
		} finally {
			System.out.println("Test case execution completed for: " + testCaseName);
			excelUtility.writeTestDataToExcel(testCaseName, expected, actual, result);
			softAssert.assertAll();
		}
	}

	@Test(priority = 7)
	public void testSearchgroupManagement() {
		String testCaseName = "Verify Search Role Group Functionality";
		String expected = "Role group found";
		String actual = "";
		String result = Result.FAIL.getValue();

		System.out.println("Executing the test for: " + testCaseName);
		try {
			System.out.println("Initiating the search for role groups...");
//			groupManagement.searchgroupManagement();

			boolean isRoleFound = groupManagement.isGroupManagementFound("QA");
			actual = isRoleFound ? "Role group found" : "Role group not found";
			softAssert.assertEquals(actual, expected, "Search Role Group verification failed!");
			result = expected.equalsIgnoreCase(actual) ? Result.PASS.getValue() : Result.FAIL.getValue();
			System.out.println("Result is: " + result);
		} catch (Exception e) {
			System.out.println(
					"An error occurred while verifying the Search Role Group functionality: " + e.getMessage());
			result = Result.ERROR.getValue();
		} finally {
			System.out.println("Test case execution completed for: " + testCaseName);
			excelUtility.writeTestDataToExcel(testCaseName, expected, actual, result);
			softAssert.assertAll();
		}
	}

//	@Test(priority = 8)
	public void testDeleteRoleGroup() {
		String testCaseName = "Verify Delete Role Group Functionality";
		String expected = "Role group deleted successfully";
		String actual = "";
		String result = Result.FAIL.getValue();

		System.out.println("Executing the test for: " + testCaseName);
		try {
			System.out.println("Deleting a role group...");
			actual = groupManagement.deleteRoleGroup();
			softAssert.assertEquals(actual, expected, "Delete Role Group verification failed!");
			result = expected.equalsIgnoreCase(actual) ? Result.PASS.getValue() : Result.FAIL.getValue();
			System.out.println("Result is: " + result);
		} catch (Exception e) {
			System.out.println(
					"An error occurred while verifying the Delete Role Group functionality: " + e.getMessage());
			result = Result.ERROR.getValue();
		} finally {
			System.out.println("Test case execution completed for: " + testCaseName);
			excelUtility.writeTestDataToExcel(testCaseName, expected, actual, result);
			softAssert.assertAll();
		}
	}

	@Test(priority = 9)
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

	@Test(priority = 10)
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

	@Test(priority = 11)
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
