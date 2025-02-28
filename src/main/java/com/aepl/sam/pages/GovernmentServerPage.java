package com.aepl.sam.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.aepl.sam.locators.GovernmentServerPageLocators;

public class GovernmentServerPage extends GovernmentServerPageLocators{
	
	private WebDriver driver;
	private WebDriverWait wait;
	
	
	public GovernmentServerPage(WebDriver driver, WebDriverWait wait) {
		this.driver = driver;
		this.wait = wait;
	}

	public void clickOnNavBar() {
		WebElement navBarLink = wait.until(ExpectedConditions.visibilityOfElementLocated(GOVERNMENT_NAV_LINK));
		navBarLink.click();
	}
}
