package com.aepl.sam.tests;

import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
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
	private Executor executor;

	@BeforeClass
	public void setUp() {
		super.setUp();
		this.loginPage = new LoginPage(driver, wait);
		this.comm = new CommonMethods(driver, wait);
		this.excelUtility = new ExcelUtility();
		this.softAssert = new SoftAssert();
		this.executor = new Executor(excelUtility, softAssert);
		excelUtility.initializeExcel("Login_Page_Test");
	}

//	private void executor.executeTest(String testCaseName, String expected, Supplier<String> actualSupplier) {
//		String actual = "";
//		String result = Result.FAIL.getValue();
//		logger.info("Executing test: {}", testCaseName);
//
//		try {
//			actual = actualSupplier.get();
//			softAssert.assertEquals(actual.trim(), expected.trim(), testCaseName + " failed!");
//			result = expected.trim().equalsIgnoreCase(actual.trim()) ? Result.PASS.getValue() : Result.FAIL.getValue();
//			logger.info("Test result: {}", result);
//		} catch (Exception e) {
//			logger.error("Exception during test {}: {}", testCaseName, e.getMessage(), e);
//			actual = e.getMessage();
//			result = Result.ERROR.getValue();
//		} finally {
//			excelUtility.writeTestDataToExcel(testCaseName, expected, actual, result);
//			softAssert.assertAll();
//		}
//	}

// --------------------------- old error getter -------------------------

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

	@Test(priority = 2)
	public void testForgotPasswordLink() {
		executor.executeTest("Forgot Password Link Test", Constants.EXP_FRGT_PWD_URL, () -> {
			loginPage.clickForgotPassword();
			return driver.getCurrentUrl();
		});
	}

	@Test(priority = 3)
	public void testInputErrMessage() {
		executor.executeTest("Input Error Message Test", "This field is required and can't be only spaces.",
				loginPage::inputErrMessage);
	}

//	@Test(priority = 4)
	public void testResetPassword() {
		executor.executeTest("Reset Password Test", "Password reset link sent to your email.",
				loginPage::resetPassword);
	}

	@Test(priority = 5)
	public void testCopyright() {
		executor.executeTest("Copyright Verification Test", Constants.EXP_COPYRIGHT_TEXT, comm::checkCopyright);
	}

	@Test(priority = 6)
	public void testVersion() {
		executor.executeTest("Version Verification Test", Constants.EXP_VERSION_TEXT, comm::checkVersion);
	}

	@Test(priority = 7)
	public void loginSuccess() {
		executor.executeTest("Login Success Test", Constants.DASH_URL, () -> {
			driver.navigate().refresh();
			loginPage.enterUsername(ConfigProperties.getProperty("username"))
					.enterPassword(ConfigProperties.getProperty("password")).clickLogin();
			try {
				Thread.sleep(2000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			return driver.getCurrentUrl();
		});
	}

	@AfterClass
	public void tearDownAssertions() {
		softAssert.assertAll();
	}

}
