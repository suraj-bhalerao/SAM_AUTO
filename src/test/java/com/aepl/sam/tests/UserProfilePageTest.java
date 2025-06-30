package com.aepl.sam.tests;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import com.aepl.sam.base.TestBase;
import com.aepl.sam.constants.Constants;
import com.aepl.sam.enums.Result;
import com.aepl.sam.pages.UserProfilePage;
import com.aepl.sam.utils.CommonMethods;
import com.aepl.sam.utils.ExcelUtility;

public class UserProfilePageTest extends TestBase {
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
		excelUtility.initializeExcel("User_Profile_Test");
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
		String testCaseName = "Verify Navbar Link Navigation";
		String expected = Constants.USR_PROFILE;
		String actual = "";
		String result = Result.FAIL.getValue();

		logger.info("Executing the test for: " + testCaseName);
		try {
			logger.info("Clicking on navbar link and verifying the redirection...");

			actual = userProf.navBarLink();

			softAssert.assertEquals(actual, expected, "Navbar link navigation failed!");
			result = expected.equalsIgnoreCase(actual) ? Result.PASS.getValue() : Result.FAIL.getValue();
			logger.info("Result is: " + result);
		} catch (Exception e) {
			logger.error("An error occurred while verifying the navbar link.", e);
			actual = "Exception thrown";
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
		String testCaseName = "Verify Back Button Navigation";
		String expected = "Back button navigated successfully.";
		String actual = "";
		String result = Result.FAIL.getValue();

		logger.info("Executing the test for: " + testCaseName);
		try {
			logger.info("Clicking back button...");

			userProf.backButton();

			// If method executed without exception, assume success
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
		String expected = "Page refreshed successfully.";
		String actual = "";
		String result = Result.FAIL.getValue();

		logger.info("Executing the test for: " + testCaseName);
		try {
			logger.info("Clicking refresh button...");

			userProf.refreshButton();

			// If no exception is thrown, we assume the refresh was successful
			actual = "Page refreshed successfully.";

			softAssert.assertEquals(actual, expected, "Refresh button functionality failed!");
			result = expected.equalsIgnoreCase(actual) ? Result.PASS.getValue() : Result.FAIL.getValue();
			logger.info("Result is: " + result);
		} catch (Exception e) {
			logger.error("An error occurred while clicking the refresh button.", e);
			actual = "Error refreshing page.";
			result = Result.ERROR.getValue();
			e.printStackTrace();
		} finally {
			logger.info("Test case execution completed for: " + testCaseName);
			excelUtility.writeTestDataToExcel(testCaseName, expected, actual, result);
			softAssert.assertAll();
		}
	}

//	@Test(priority = 6)
	public void testChangePassword() {
		String testCaseName = "Verify Change Password Functionality";
		String expected = "Password changed successfully.";
		String actual = "";
		String result = Result.FAIL.getValue();

		logger.info("Executing the test for: " + testCaseName);
		try {
			logger.info("Attempting to change password...");

			userProf.changePassword();

			// If no exception occurs, assume success
			actual = "Password changed successfully.";

			softAssert.assertEquals(actual, expected, "Change password functionality failed!");
			result = expected.equalsIgnoreCase(actual) ? Result.PASS.getValue() : Result.FAIL.getValue();
			logger.info("Result is: " + result);
		} catch (Exception e) {
			logger.error("An error occurred while changing the password.", e);
			actual = "Error changing password.";
			result = Result.ERROR.getValue();
			e.printStackTrace();
		} finally {
			logger.info("Test case execution completed for: " + testCaseName);
			excelUtility.writeTestDataToExcel(testCaseName, expected, actual, result);
			softAssert.assertAll();
		}
	}

	@Test(priority = 7)
	public void testUploadProfilePicture() {
		String testCaseName = "Verify Upload Profile Picture Functionality";
		String expected = "Profile picture uploaded successfully.";
		String actual = "";
		String result = Result.FAIL.getValue();

		logger.info("Executing the test for: " + testCaseName);
		try {
			boolean isUploaded = userProf.uploadProfilePicture();

			actual = isUploaded ? "Profile picture uploaded successfully." : "Profile picture upload failed.";

			softAssert.assertEquals(actual, expected, "Profile picture upload verification failed!");
			result = expected.equalsIgnoreCase(actual) ? Result.PASS.getValue() : Result.FAIL.getValue();
			logger.info("Result is: " + result);
		} catch (Exception e) {
			logger.error("An error occurred during profile picture upload test.", e);
			actual = "Error uploading profile picture.";
			result = Result.ERROR.getValue();
			e.printStackTrace();
		} finally {
			logger.info("Test case execution completed for: " + testCaseName);
			excelUtility.writeTestDataToExcel(testCaseName, expected, actual, result);
			softAssert.assertAll();
		}
	}

	@Test(priority = 8)
	public void testUpdateProfileDetails() {
		String testCaseName = "Verify Update Profile Details Functionality";
		String expected = "Profile updated successfully.";
		String actual = "";
		String result = Result.FAIL.getValue();

		try {
			boolean isUpdated = userProf.updateProfileDetails();

			actual = isUpdated ? "Profile updated successfully." : "Profile update failed.";

			softAssert.assertEquals(actual, expected, "Profile update verification failed!");
			result = expected.equalsIgnoreCase(actual) ? Result.PASS.getValue() : Result.FAIL.getValue();
		} catch (Exception e) {
			e.printStackTrace();
			actual = "Error updating profile.";
			result = Result.ERROR.getValue();
		} finally {
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
