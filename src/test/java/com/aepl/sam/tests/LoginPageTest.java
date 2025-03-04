package com.aepl.sam.tests;

import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.aepl.sam.base.TestBase;
import com.aepl.sam.constants.Constants;
import com.aepl.sam.pages.LoginPage;
import com.aepl.sam.utils.ConfigProperties;
import com.aepl.sam.utils.ExcelUtility;

public class LoginPageTest extends TestBase{
	private LoginPage loginPage;
	private ExcelUtility excelUtility;
	
	@BeforeClass
	public void setUp() {
		super.setUp();
		this.loginPage = new LoginPage(driver);
		this.excelUtility = new ExcelUtility();
		excelUtility.initializeExcel("Login_Page_Test");
	}

	@Test(priority = 1,dataProvider = "loginData")
	public void testLogin(String username, String password, String expectedErrorMessage, String testCaseName) {
		logger.warn("Executing test case: " + testCaseName);

		loginPage.enterUsername(username).enterPassword(password).clickLogin();

		try {
			if (expectedErrorMessage.isEmpty()) {
				boolean isDashboardURL = wait.until(ExpectedConditions.urlContains("dashboard"));
				Assert.assertTrue(isDashboardURL, "Login did not redirect to dashboard.");
			} else {
				By errorLocator = getErrorLocator(expectedErrorMessage);
				WebElement errorMessage = loginPage.waitForVisibility(errorLocator);
				Assert.assertEquals(errorMessage.getText(), expectedErrorMessage, "Error message mismatch.");
			}

			excelUtility.writeTestDataToExcel(testCaseName, expectedErrorMessage, "Login success", "Pass");
		} catch (TimeoutException e) {
			logger.error("Timeout occurred waiting for dashboard or error message.", e);
			excelUtility.writeTestDataToExcel(testCaseName, expectedErrorMessage, "Page did not load as expected",
					"Fail");
			Assert.fail("Page did not load as expected.");
		} catch (Exception e) {
			logger.error("Unexpected error in test case: " + testCaseName, e);
			excelUtility.writeTestDataToExcel(testCaseName, expectedErrorMessage, "Unexpected error: " + e.getMessage(),
					"Fail");
			Assert.fail("Unexpected error: " + e.getMessage());
		}
	}

	private By getErrorLocator(String expectedErrorMessage) {
		if (expectedErrorMessage.equals(Constants.email_error_msg_01)
				|| expectedErrorMessage.equals(Constants.email_error_msg_02)) {
			return By.xpath("//mat-error[contains(text(), '" + expectedErrorMessage + "')]");
		} else if (expectedErrorMessage.equals(Constants.password_error_msg_01)
				|| expectedErrorMessage.equals(Constants.password_error_msg_02)) {
			return By.xpath("//mat-error[contains(text(), '" + expectedErrorMessage + "')]");
		} else if (expectedErrorMessage.equals(Constants.toast_error_msg_01)
				|| expectedErrorMessage.equals(Constants.toast_error_msg_02)) {
			return By.xpath("//span[text()='" + expectedErrorMessage + "']");
		} else {
			throw new IllegalArgumentException("Unknown error message: " + expectedErrorMessage);
		}
	}

	@DataProvider(name = "loginData", parallel = false)
	public Object[][] loginData() {
		return new Object[][] {
				// Empty user valid pass
				{ " ", ConfigProperties.getProperty("valid.password"),
						Constants.email_error_msg_02, "Empty Username With Valid Password" },

				// Valid user long pass
				{ ConfigProperties.getProperty("valid.username"), "a".repeat(16),
						Constants.toast_error_msg_02, "Valid Username With Long Password" },

				// Valid user empty pass
				{ ConfigProperties.getProperty("valid.username"), " ",
						Constants.password_error_msg_02, "Valid Username With Empty Password" },

				// Invalid user valid pass
				{ "invalid.email@domain.com", ConfigProperties.getProperty("valid.password"),
						Constants.toast_error_msg_01, "Invalid Username With Valid Password" },

				// Empty user empty pass
				{ " ", " ", Constants.password_error_msg_02, "Empty Username With Empty Password" },

				// Invalid user invalid pass
				{ "invalid.email@domain.com", "invalid", Constants.toast_error_msg_01,
						"Invalid Username With Invalid Password" },

				// Valid user short pass
				{ ConfigProperties.getProperty("valid.username"), "short",
						Constants.password_error_msg_02, "Valid Username With Short Password" },

				// Valid user with white space
				{ ConfigProperties.getProperty("valid.username"), "       ",
						Constants.toast_error_msg_02,
						"Valid Username With White Spaces in Password" },

				// Valid user with sql injection
				{ ConfigProperties.getProperty("valid.username"), "' OR '1'='1",
						Constants.toast_error_msg_02, "SQL Injection in Password" },

				// Valid user with xss
				{ ConfigProperties.getProperty("valid.username"), "<script>alert('XSS');</script>",
						Constants.toast_error_msg_02, "XSS Attempt in Password" },

				// Valid user valid pass
				{ ConfigProperties.getProperty("valid.username"), ConfigProperties.getProperty("valid.password"), "",
						"Valid Username With Valid Password" }, };
	}
	
	@Test(priority = 2)
	public void testForgotPasswordLink() {
		String testCaseName = "Forgot Password Link Test";
		String expectedResult = Constants.EXP_FRGT_PWD_URL;
		String actualResult = "";
		try {
			loginPage.clickForgotPassword();
			actualResult = driver.getCurrentUrl();
		}catch(Exception e) {
			excelUtility.writeTestDataToExcel(testCaseName, expectedResult, actualResult,"Fail");
		}finally {
			excelUtility.writeTestDataToExcel(testCaseName, expectedResult, actualResult,"Pass");
		}
	}
	
//	@Test(priority = 3)
//	public void testInputErrMessage() {
//		String errMessage = loginPage.inputErrMessage();
//		System.out.println(errMessage);
//	}
//	@Test(priority = 4)
//	public void testResetPassword() {
//		loginPage.resetPassword();
//	}
}
