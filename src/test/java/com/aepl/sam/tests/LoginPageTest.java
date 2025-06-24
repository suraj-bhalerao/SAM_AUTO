package com.aepl.sam.tests;

import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import com.aepl.sam.base.TestBase;
import com.aepl.sam.constants.Constants;
import com.aepl.sam.enums.Result;
import com.aepl.sam.pages.LoginPage;
import com.aepl.sam.utils.CommonMethods;
import com.aepl.sam.utils.ConfigProperties;
import com.aepl.sam.utils.ExcelUtility;

public class LoginPageTest extends TestBase {

	private LoginPage loginPage;
	private ExcelUtility excelUtility;
	private CommonMethods comm;
	private SoftAssert softAssert;

	@BeforeClass
	public void setUp() {
		super.setUp();
		this.loginPage = new LoginPage(driver, wait, logger);
		this.comm = new CommonMethods(driver, wait);
		this.excelUtility = new ExcelUtility();
		this.softAssert = new SoftAssert();
		excelUtility.initializeExcel("Login_Page_Test");
	}

	@Test(priority = 1, dataProvider = "loginData")
	public void testLogin(String username, String password, String expectedErrorMessage, String testCaseName) {
		String actualErr = "";
		logger.info("Executing test: {}", testCaseName);
		logger.info("Attempting login with Username: [{}] and Password: [******]", username);

		loginPage.enterUsername(username).enterPassword(password).clickLogin();

		try {
			if (expectedErrorMessage.isEmpty()) {
				boolean isDashboardURL = wait.until(ExpectedConditions.urlContains("dashboard"));
				Assert.assertTrue(isDashboardURL, "Login did not redirect to dashboard.");
				logger.info("Login successful. Redirected to dashboard.");
			} else {
				By errorLocator = getErrorLocator(expectedErrorMessage);
				WebElement errorMessage = loginPage.waitForVisibility(errorLocator);
				actualErr = errorMessage.getText();
				Assert.assertEquals(actualErr, expectedErrorMessage, "Error message mismatch.");

				logger.info("Error message '{}' displayed as expected.", expectedErrorMessage);
				excelUtility.writeTestDataToExcel(testCaseName, expectedErrorMessage, actualErr,
						Result.PASS.getValue());
			}

		} catch (TimeoutException e) {
			logger.error("Page did not load as expected for test: {}", testCaseName, e);
			excelUtility.writeTestDataToExcel(testCaseName, expectedErrorMessage, "Page did not load",
					Result.FAIL.getValue());
			Assert.fail("Page did not load as expected.");
		} catch (Exception e) {
			logger.error("Unexpected error during login test: {}", testCaseName, e);
			excelUtility.writeTestDataToExcel(testCaseName, expectedErrorMessage, "Unexpected error: " + e.getMessage(),
					Result.FAIL.getValue());
			Assert.fail("Unexpected error: " + e.getMessage());
		}
	}

//	private By getErrorLocator(String expectedErrorMessage) {
//		if (expectedErrorMessage.equals(Constants.email_error_msg_01)
//				|| expectedErrorMessage.equals(Constants.email_error_msg_02)) {
//			// "//mat-error[contains(text(), \"This field is required and can't be only spaces.\")]";
//			return By.xpath("//mat-error[contains(text(), '\" + expectedErrorMessage + \"')]");
//		} else if (expectedErrorMessage.equals(Constants.password_error_msg_01)
//				|| expectedErrorMessage.equals(Constants.password_error_msg_02)) {
//			return By.xpath("//mat-error[contains(text(), '" + expectedErrorMessage + "')]");
//		} else if (expectedErrorMessage.equals(Constants.toast_error_msg_01)
//				|| expectedErrorMessage.equals(Constants.toast_error_msg_02)) {
//			return By.xpath("//span[text()='" + expectedErrorMessage + "']");
//		} else {
//			throw new IllegalArgumentException("Unknown error message: " + expectedErrorMessage);
//		}
//	}

	private By getErrorLocator(String expectedErrorMessage) {
		logger.debug("Finding error locator for message: {}", expectedErrorMessage);

		String safeMessage;
		if (expectedErrorMessage.contains("'")) {
			String[] parts = expectedErrorMessage.split("'");
			safeMessage = "concat('" + String.join("', \"'\", '", parts) + "')";
		} else {
			safeMessage = "'" + expectedErrorMessage + "'";
		}

		if (expectedErrorMessage.equals(Constants.email_error_msg_01)
				|| expectedErrorMessage.equals(Constants.email_error_msg_02)) {
			return By.xpath("//mat-error[contains(text(), " + safeMessage + ")]");
		} else if (expectedErrorMessage.equals(Constants.password_error_msg_01)
				|| expectedErrorMessage.equals(Constants.password_error_msg_02)) {
			return By.xpath("//mat-error[contains(text()," + safeMessage + ")]");
		} else if (expectedErrorMessage.equals(Constants.toast_error_msg)
				|| expectedErrorMessage.equals(Constants.toast_error_msg_03)) {
			return By.xpath("//simple-snack-bar/div[contains(text()," + safeMessage + ")]");
		} else {
			logger.warn("Unknown error message encountered: {}", expectedErrorMessage);
			throw new IllegalArgumentException("Unknown error message: " + expectedErrorMessage);
		}
	}

	@DataProvider(name = "loginData", parallel = false)
	public Object[][] loginData() {
		return new Object[][] {
				// Empty username with valid password → Should show email error message
				{ " ", ConfigProperties.getProperty("password"), Constants.email_error_msg_01,
						"Empty Username With Valid Password" },

				// Valid username with overly long password → Should show toast error message
				{ ConfigProperties.getProperty("username"), "aaaaaaaaaaaaaaaaa", Constants.toast_error_msg,
						"Valid Username With Long Password" },

				// Valid username with empty password → Should show toast error message
				{ ConfigProperties.getProperty("username"), " ", Constants.password_error_msg_02,
						"Valid Username With Empty Password" },

				// Invalid username with valid password → Should show toast error message
				{ "invalid.email!@domain.com", ConfigProperties.getProperty("password"), Constants.email_error_msg_02,
						"Invalid Username With Valid Password" },

				// Empty username and empty password → Should show toast error message
				{ " ", " ", Constants.email_error_msg_01, "Empty Username With Empty Password" },

				// Invalid username with invalid password → Should show toast error message
				{ "invalid.email!@domain.com", "uu7k2", Constants.toast_error_msg,
						"Invalid Username With Invalid Password" },

				// Valid username with short password → Should show password length error
				// message
				{ ConfigProperties.getProperty("username"), "short", Constants.password_error_msg_02,
						"Valid Username With Short Password" },

				// Valid username with only whitespace in password → Should show toast error
				// message
				{ ConfigProperties.getProperty("username"), "       ", Constants.toast_error_msg_03,
						"Valid Username With White Spaces in Password" } };
	}

	@Test(priority = 2)
	public void testForgotPasswordLink() {
		String testCaseName = "Forgot Password Link Test";
		String expected = Constants.EXP_FRGT_PWD_URL;
		String actual = "";
		String result = Result.FAIL.getValue();

		logger.info("Executing the test for: {}", testCaseName);
		try {
			logger.info("Clicking on 'Forgot Password' link...");
			loginPage.clickForgotPassword();
			actual = driver.getCurrentUrl();
			logger.info("Navigated to URL: {}", actual);
			System.out.println("Actual: " + actual);

			softAssert.assertEquals(actual.trim(), expected, "Forgot Password link validation failed!");
			result = expected.equalsIgnoreCase(actual.trim()) ? Result.PASS.getValue() : Result.FAIL.getValue();
			logger.info("Result is: {}", result);
		} catch (Exception e) {
			logger.error("Exception in testForgotPasswordLink: {}", e.getMessage(), e);
			actual = e.getMessage();
			result = Result.ERROR.getValue();
		} finally {
			excelUtility.writeTestDataToExcel(testCaseName, expected, actual, result);
			logger.info("Test case execution completed for: {}", testCaseName);
			System.out.println("Forgot Password link test execution completed.");
			softAssert.assertAll();
		}
	}

	@Test(priority = 3)
	public void testInputErrMessage() {
		String testCaseName = "Input Error Message Test";
		String expected = "This field is required and can't be only spaces.";
		String actual = "";
		String result = Result.FAIL.getValue();

		logger.info("Executing the test for: " + testCaseName);
		try {
			logger.info("Verifying input field error message...");
			actual = loginPage.inputErrMessage();
			System.out.println("Actual: " + actual);

			softAssert.assertEquals(actual.trim(), expected, "Error message validation failed!");
			result = expected.equalsIgnoreCase(actual.trim()) ? Result.PASS.getValue() : Result.FAIL.getValue();
			logger.info("Result is: " + result);
		} catch (Exception e) {
			logger.error("An error occurred while verifying the input error message.", e);
			e.printStackTrace();
			result = Result.ERROR.getValue();
		} finally {
			excelUtility.writeTestDataToExcel(testCaseName, expected, actual, result);
			logger.info("Test case execution completed for: " + testCaseName);
			System.out.println("Input error message verification completed.");
			softAssert.assertAll();
		}
	}

//	@Test(priority = 4)
	public void testResetPassword() {
		String testCaseName = "Reset Password Test";
		String expected = "Password reset link sent to your email.";
		String actual = "";
		String result = Result.FAIL.getValue();

		logger.info("Executing the test for: {}", testCaseName);
		try {
			logger.info("Attempting to reset the password...");
			actual = loginPage.resetPassword();
			System.out.println("Actual: " + actual);

			softAssert.assertEquals(actual.trim(), expected, "Reset password validation failed!");
			result = expected.equalsIgnoreCase(actual.trim()) ? Result.PASS.getValue() : Result.FAIL.getValue();
			logger.info("Result is: {}", result);
		} catch (Exception e) {
			logger.error("Exception in testResetPassword: {}", e.getMessage(), e);
			result = Result.ERROR.getValue();
		} finally {
			excelUtility.writeTestDataToExcel(testCaseName, expected, actual, result);
			logger.info("Test case execution completed for: {}", testCaseName);
			System.out.println("Reset password test execution completed.");
			softAssert.assertAll();
		}
	}

	@Test(priority = 5)
	public void testCopyright() {
		String testCaseName = "Copyright Verification Test";
		String expected = Constants.EXP_COPYRIGHT_TEXT;
		String actual = "";
		String result = Result.FAIL.getValue();

		logger.info("Executing the test for: {}", testCaseName);
		try {
			logger.info("Checking the copyright text...");
			actual = comm.checkCopyright();
			System.out.println("Actual: " + actual);

			softAssert.assertEquals(actual.trim(), expected, "Copyright text validation failed!");
			result = expected.equalsIgnoreCase(actual.trim()) ? Result.PASS.getValue() : Result.FAIL.getValue();
			logger.info("Result is: {}", result);
		} catch (Exception e) {
			logger.error("Exception in testCopyright: {}", e.getMessage(), e);
			actual = e.getMessage();
			result = Result.ERROR.getValue();
		} finally {
			excelUtility.writeTestDataToExcel(testCaseName, expected, actual, result);
			logger.info("Test case execution completed for: {}", testCaseName);
			System.out.println("Copyright verification test execution completed.");
			softAssert.assertAll();
		}
	}

	@Test(priority = 6)
	public void testVersion() {
		String testCaseName = "Version Verification Test";
		String expected = Constants.EXP_VERSION_TEXT;
		String actual = "";
		String result = Result.FAIL.getValue();

		logger.info("Executing the test for: {}", testCaseName);
		try {
			logger.info("Checking the application version...");
			actual = comm.checkVersion();
			System.out.println("Actual: " + actual);

			softAssert.assertEquals(actual.trim(), expected, "Version validation failed!");
			result = expected.equalsIgnoreCase(actual.trim()) ? Result.PASS.getValue() : Result.FAIL.getValue();
			logger.info("Result is: {}", result);
		} catch (Exception e) {
			logger.error("Exception in testVersion: {}", e.getMessage(), e);
			actual = e.getMessage();
			result = Result.ERROR.getValue();
		} finally {
			excelUtility.writeTestDataToExcel(testCaseName, expected, actual, result);
			logger.info("Test case execution completed for: {}", testCaseName);
			System.out.println("Version verification test execution completed.");
			softAssert.assertAll();
		}
	}

	@Test(priority = 7)
	public void loginSuccess() {
		String testCaseName = "Login Success Test";
		String expected = Constants.DASH_URL;
		String actual = "";
		String result = Result.FAIL.getValue();

		logger.info("Executing the test for: {}", testCaseName);
		try {
			logger.info("Refreshing the page before login...");
			driver.navigate().refresh();

			logger.info("Entering valid credentials and attempting login...");
			loginPage.enterUsername(ConfigProperties.getProperty("username"))
					.enterPassword(ConfigProperties.getProperty("password")).clickLogin();

			Thread.sleep(2000);
			actual = driver.getCurrentUrl();
			logger.info("Navigated to URL after login: {}", actual);
			System.out.println("Actual: " + actual);

			softAssert.assertEquals(actual.trim(), expected, "Login validation failed!");
			result = expected.equalsIgnoreCase(actual.trim()) ? Result.PASS.getValue() : Result.FAIL.getValue();
			logger.info("Result is: {}", result);
		} catch (Exception e) {
			logger.error("Exception in loginSuccess test: {}", e.getMessage(), e);
			actual = e.getMessage();
			result = Result.ERROR.getValue();
		} finally {
			excelUtility.writeTestDataToExcel(testCaseName, expected, actual, result);
			logger.info("Test case execution completed for: {}", testCaseName);
			System.out.println("Login success test execution completed.");
			softAssert.assertAll();
		}
	}
}
