package com.aepl.sam.pages;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.aepl.sam.actions.MouseActions;
import com.aepl.sam.locators.LoginPageLocators;

public class LoginPage extends LoginPageLocators {

	private WebDriver driver;
	private MouseActions actions;

	public LoginPage(WebDriver driver) {
		this.actions = new MouseActions(driver);
		this.driver = driver;
	}

	public LoginPage enterUsername(String username) {
		WebElement usernameInput = waitForVisibility(LOGIN_FLD);
		usernameInput.clear();
		usernameInput.sendKeys(username);
		return this;
	}

	public LoginPage enterPassword(String password) {
		WebElement passwordInput = waitForVisibility(PASSWORD_FLD);
		passwordInput.clear();
		passwordInput.sendKeys(password);
		return this;
	}

	public LoginPage clickLogin() {
		waitForVisibility(SIGN_IN_BTN).click();
		return this;
	}

	public void clickForgotPassword() {
		waitForVisibility(FORGOT_PASSWORD_LNK).click();
	}
	
	public void clickLogout() {
		actions.moveToElement(waitForVisibility(PROFILE_ICON));
		waitForVisibility(LOGOUT_BTN).click();
	}
	
	// helper method
	public WebElement waitForVisibility(By locator) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		return wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
	}
}