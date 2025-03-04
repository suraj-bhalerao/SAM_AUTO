package com.aepl.sam.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.aepl.sam.locators.DeviceModelsPageLocators;
import com.aepl.sam.utils.CommonMethods;

public class DeviceModelsPage extends DeviceModelsPageLocators {

	private WebDriver driver;
	private WebDriverWait wait;
	private CommonMethods commonMethods;
	
	public DeviceModelsPage(WebDriver driver, WebDriverWait wait) {
		this.driver = driver;
		this.wait = wait;
		this.commonMethods = new CommonMethods(driver, wait);
	}
	
	
	
	
}
