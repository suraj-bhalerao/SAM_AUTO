package com.aepl.sam.pages;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.aepl.sam.locators.LoginPageLocators;
import com.aepl.sam.utils.CommonMethods;
import com.aepl.sam.utils.ConfigProperties;

public class LoginPage extends LoginPageLocators {
	private WebDriver driver;
	private WebDriverWait wait;
	private CommonMethods comm;
	private static final Logger logger = LogManager.getLogger(LoginPage.class);

	public LoginPage(WebDriver driver, WebDriverWait wait) {
		this.driver = driver;
		this.wait = wait;
		this.comm = new CommonMethods(driver, wait);
	}

	public LoginPage enterUsername(String username) {
		logger.info("Entering username: {}", username);
		WebElement usernameInput = waitForVisibility(LOGIN_FLD);
		comm.highlightElement(usernameInput, "solid purple");
		usernameInput.clear();
		usernameInput.sendKeys(username);
		return this;
	}

	public LoginPage enterPassword(String password) {
		logger.info("Entering password: (masked)");
		WebElement passwordInput = waitForVisibility(PASSWORD_FLD);
		comm.highlightElement(passwordInput, "solid purple");
		passwordInput.clear();
		passwordInput.sendKeys(password);
		return this;
	}

	public LoginPage clickLogin() {
		logger.info("Clicking login button...");
		waitForVisibility(SIGN_IN_BTN).click();
		return this;
	}

	public void clickForgotPassword() {
		logger.info("Clicking 'Forgot Password' link...");
		waitForVisibility(FORGOT_PASSWORD_LNK).click();
	}

	public void clickLogout() {
		logger.info("Logging out...");
		driver.findElement(PROFILE_ICON).click();
		waitForVisibility(LOGOUT_BTN).click();
		logger.info("Successfully logged out.");
	}

	public String inputErrMessage() {
		try {
			logger.info("Checking input field error message...");
			waitForVisibility(FORGOT_INPUT_FLD).sendKeys(Keys.ENTER);
			waitForVisibility(FORGOT_INPUT_FLD).sendKeys(Keys.TAB);
			WebElement err = wait.until(ExpectedConditions.visibilityOfElementLocated(FORGOT_ERROR_MSG));
			comm.highlightElement(err, "solid purple");
			logger.info("Error message displayed: {}", err.getText());
			return err.getText();
		} catch (Exception e) {
			logger.error("Exception while checking error message: {}", e.getMessage(), e);
		}
		return "No error message is displayed on the page!!!";
	}

	public String resetPassword() {
		try {
			logger.info("Attempting password reset...");
			waitForVisibility(FORGOT_INPUT_FLD).sendKeys(ConfigProperties.getProperty("username"));
			waitForVisibility(RESET_BTN).click();

			Thread.sleep(1000);

			WebElement toastMsg = wait.until(ExpectedConditions.visibilityOfElementLocated(RESET_TOAST_MSG));
			comm.highlightElement(toastMsg, "solid purple");

			String passwordFromOutlook = CommonMethods.getPasswordFromOutlook();
			logger.info("Retrieved password from Outlook");

			ConfigProperties.setProperty("password", passwordFromOutlook);
			Thread.sleep(5000);

			logger.info("Password reset successful, updated ConfigProperties");

			String confirmationToastMessage = toastMsg.getText();
			logger.info("Password reset message: {}", confirmationToastMessage);
			return confirmationToastMessage;
		} catch (Exception e) {
			logger.error("Error during password reset: {}", e.getMessage(), e);
		}
		return "No Password is changed...";
	}

	// Helper
	public WebElement waitForVisibility(By locator) {
		return wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
	}
}
