package com.aepl.sam.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.aepl.sam.actions.MouseActions;
import com.aepl.sam.locators.GovernmentServerPageLocators;

public class GovernmentServerPage extends GovernmentServerPageLocators {

	private WebDriver driver;
	private WebDriverWait wait;
	private MouseActions action;

	public GovernmentServerPage(WebDriver driver, WebDriverWait wait, MouseActions action) {
		this.driver = driver;
		this.wait = wait;
		this.action = action;
	}

	public String navBarLink() {
		action.hoverOverElement(wait.until(ExpectedConditions.visibilityOfElementLocated(DEVICE_UTILITY)));

		WebElement govServer = wait.until(ExpectedConditions.visibilityOfElementLocated(GOVERNMENT_NAV_LINK));
		govServer.click();
		return driver.getCurrentUrl();
	}
}
