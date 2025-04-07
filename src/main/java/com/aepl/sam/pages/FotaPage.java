package com.aepl.sam.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.aepl.sam.actions.MouseActions;
import com.aepl.sam.locators.FotaPageLocators;

public class FotaPage extends FotaPageLocators {
	private WebDriver driver;
	private WebDriverWait wait;
	private MouseActions action;

	public FotaPage(WebDriver driver, WebDriverWait wait, MouseActions action) {
		this.driver = driver;
		this.wait = wait;
		this.action = action;
	}
}
