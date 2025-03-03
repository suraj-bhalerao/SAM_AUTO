package com.aepl.sam.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.aepl.sam.locators.UserManagementPageLocators;

public class UserManagementPage extends UserManagementPageLocators {
	private WebDriver driver;
	private WebDriverWait wait;
	
	public UserManagementPage(WebDriver driver, WebDriverWait wait) {
		super();
		this.driver = driver;
		this.wait = wait;
	}

	// Adding new user in the list
	public void addNewUser() {
		WebElement addBtn = wait.until(ExpectedConditions.elementToBeClickable(ADD_USR_BTN));
		addBtn.click();
	}
}
