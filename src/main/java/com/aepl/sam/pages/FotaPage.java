package com.aepl.sam.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.aepl.sam.actions.MouseActions;
import com.aepl.sam.locators.FotaPageLocators;
import com.aepl.sam.utils.CommonMethods;

public class FotaPage extends FotaPageLocators {
	private WebDriver driver;
	private WebDriverWait wait;
	private MouseActions action;
	private CommonMethods comm;

	public FotaPage(WebDriver driver, WebDriverWait wait, MouseActions action) {
		this.driver = driver;
		this.wait = wait;
		this.action = action;
		this.comm = new CommonMethods(driver, wait);
	}

	// Click on Device Utility
	public void clickDeviceUtility() {
		comm.highlightElement(driver.findElement(DEVICE_UTILITY), "RED");
		driver.findElement(DEVICE_UTILITY).click();
	}

	// Click on FOTA
	public void clickFota() {
		comm.highlightElement(driver.findElement(FOTA_LINK), "RED");
		driver.findElement(FOTA_LINK).click();
	}

	// Create a new {Full} FOTA Batch
	public void createNewFotaBatch() {

	}
}
