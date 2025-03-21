package com.aepl.sam.tests;

import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.aepl.sam.base.TestBase;
import com.aepl.sam.constants.Constants;
import com.aepl.sam.enums.Result;
import com.aepl.sam.pages.LoginPage;
import com.aepl.sam.utils.ConfigProperties;
import com.aepl.sam.utils.ExcelUtility;

public class LoginPageTest extends TestBase {

    private LoginPage loginPage;
    private ExcelUtility excelUtility;
    private final Logger logger = LogManager.getLogger(this.getClass().getSimpleName());

    @BeforeClass
    public void setUp() {
        super.setUp();
        logger.info("Setting up test class: {}", this.getClass().getSimpleName());
        
        this.loginPage = new LoginPage(driver);
        this.excelUtility = new ExcelUtility();
        excelUtility.initializeExcel("Login_Page_Test");
    }

    @Test(priority = 1, dataProvider = "loginData")
    public void testLogin(String username, String password, String expectedErrorMessage, String testCaseName) {
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
                Assert.assertEquals(errorMessage.getText(), expectedErrorMessage, "Error message mismatch.");
                logger.info("Error message '{}' displayed as expected.", expectedErrorMessage);
            }

            excelUtility.writeTestDataToExcel(testCaseName, expectedErrorMessage, "Login success", Result.PASS.getValue());
        } catch (TimeoutException e) {
            logger.error("Page did not load as expected for test: {}", testCaseName, e);
            excelUtility.writeTestDataToExcel(testCaseName, expectedErrorMessage, "Page did not load", Result.FAIL.getValue());
            Assert.fail("Page did not load as expected.");
        } catch (Exception e) {
            logger.error("Unexpected error during login test: {}", testCaseName, e);
            excelUtility.writeTestDataToExcel(testCaseName, expectedErrorMessage, "Unexpected error: " + e.getMessage(), Result.FAIL.getValue());
            Assert.fail("Unexpected error: " + e.getMessage());
        }
    }

    private By getErrorLocator(String expectedErrorMessage) {
        logger.debug("Finding error locator for message: {}", expectedErrorMessage);
        if (expectedErrorMessage.equals(Constants.email_error_msg_01)
                || expectedErrorMessage.equals(Constants.email_error_msg_02)) {
            return By.xpath("//mat-error[contains(text(), '" + expectedErrorMessage + "')]");
        } else if (expectedErrorMessage.equals(Constants.password_error_msg_01)
                || expectedErrorMessage.equals(Constants.password_error_msg_02)) {
            return By.xpath("//mat-error[contains(text(), '" + expectedErrorMessage + "')]");
        } else if (expectedErrorMessage.equals(Constants.toast_error_msg)
                || expectedErrorMessage.equals(Constants.toast_error_msg_03)) {
            return By.xpath("//div[contains(text(), '" + expectedErrorMessage + "')]");
        } else {
            logger.warn("Unknown error message encountered: {}", expectedErrorMessage);
            throw new IllegalArgumentException("Unknown error message: " + expectedErrorMessage);
        }
    }

    @Test(priority = 2)
    public void testForgotPasswordLink() {
        String testCaseName = "Forgot Password Link Test";
        logger.info("Starting test: {}", testCaseName);

        try {
            loginPage.clickForgotPassword();
            String actualResult = driver.getCurrentUrl();
            logger.info("Navigated to: {}", actualResult);

            softAssert.assertEquals(Constants.EXP_FRGT_PWD_URL, actualResult);
            String result = Constants.EXP_FRGT_PWD_URL.equalsIgnoreCase(actualResult) ? Result.PASS.getValue() : Result.FAIL.getValue();
            excelUtility.writeTestDataToExcel(testCaseName, Constants.EXP_FRGT_PWD_URL, actualResult, result);
        } catch (Exception e) {
            logger.error("Exception in testForgotPasswordLink: {}", e.getMessage(), e);
            excelUtility.writeTestDataToExcel(testCaseName, Constants.EXP_FRGT_PWD_URL, e.getMessage(), Result.ERROR.getValue());
        }
    }

    @DataProvider(name = "loginData", parallel = false)
    public Object[][] loginData() {
        return new Object[][]{
                // Empty username with valid password → Should show email error message
                {" ", ConfigProperties.getProperty("password"), Constants.email_error_msg_01, "Empty Username With Valid Password"},

                // Valid username with overly long password → Should show toast error message
                {ConfigProperties.getProperty("username"), "aaaaaaaaaaaaaaaaa", Constants.toast_error_msg, "Valid Username With Long Password"},

                // Valid username with empty password → Should show toast error message
                {ConfigProperties.getProperty("username"), "", Constants.toast_error_msg, "Valid Username With Empty Password"},

                // Invalid username with valid password → Should show toast error message
                {"invalid.email@domain.com", ConfigProperties.getProperty("password"), Constants.toast_error_msg, "Invalid Username With Valid Password"},

                // Empty username and empty password → Should show toast error message
                {"", "", Constants.toast_error_msg, "Empty Username With Empty Password"},

                // Invalid username with invalid password → Should show toast error message
                {"invalid.email@domain.com", "invalid", Constants.toast_error_msg, "Invalid Username With Invalid Password"},

                // Valid username with short password → Should show password length error message
                {ConfigProperties.getProperty("username"), "short", Constants.password_error_msg_02, "Valid Username With Short Password"},

                // Valid username with only whitespace in password → Should show toast error message
                {ConfigProperties.getProperty("username"), "       ", Constants.toast_error_msg_03, "Valid Username With White Spaces in Password"},

                // Valid username with SQL injection attempt → Should show toast error message (should be handled securely)
                {ConfigProperties.getProperty("username"), "' OR '1'='1", Constants.toast_error_msg, "SQL Injection in Password"},

                // Valid username with XSS attack attempt → Should show toast error message (should be sanitized)
                {ConfigProperties.getProperty("username"), "<script>alert('XSS');</script>", Constants.toast_error_msg_03, "XSS Attempt in Password"},
        };
    }

    @Test(priority = 3)
    public void testInputErrMessage() {
        String testCaseName = "Input Error Message Test";
        logger.info("Starting test: {}", testCaseName);

        try {
            String actualResult = loginPage.inputErrMessage();
            softAssert.assertEquals(" This field is mandatory. ", actualResult);
            excelUtility.writeTestDataToExcel(testCaseName, " This field is mandatory. ", actualResult, Result.PASS.getValue());
        } catch (Exception e) {
            logger.error("Exception in testInputErrMessage: {}", e.getMessage(), e);
        }
    }

    @Test(priority = 4)
    public void testResetPassword() {
        String testCaseName = "Reset Password Test";
        logger.info("Starting test: {}", testCaseName);

        try {
            loginPage.resetPassword();
            excelUtility.writeTestDataToExcel(testCaseName, "Password sent to given email id.", "Success", Result.PASS.getValue());
        } catch (Exception e) {
            logger.error("Exception in testResetPassword: {}", e.getMessage(), e);
        }
    }

    @Test(priority = 5)
    public void loginSuccess() {
        logger.info("Executing final login test with valid credentials.");
        driver.navigate().refresh();
        loginPage.enterUsername(ConfigProperties.getProperty("username"))
                .enterPassword(ConfigProperties.getProperty("password")).clickLogin();
        logger.info("Login attempt with valid credentials completed.");
    }
}
