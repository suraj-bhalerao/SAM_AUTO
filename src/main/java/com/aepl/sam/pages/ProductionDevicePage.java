package com.aepl.sam.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.aepl.sam.actions.MouseActions;
import com.aepl.sam.locators.ProductionDevicePageLocators;
import com.aepl.sam.utils.CommonMethods;

public class ProductionDevicePage extends ProductionDevicePageLocators{

	private WebDriver driver;
	private WebDriverWait wait;
	private CommonMethods commonMethods;
	private MouseActions action;

	public ProductionDevicePage(WebDriver driver, WebDriverWait wait, MouseActions action) {
		this.driver = driver;
		this.wait = wait;
		this.commonMethods = new CommonMethods(driver, wait);
		this.action = action;
	}
	
	public String navBarLink() throws InterruptedException {
		action.hoverOverElement(wait.until(ExpectedConditions.visibilityOfElementLocated(DEVICE_UTILITY)));

		WebElement prodDevice = wait.until(ExpectedConditions.visibilityOfElementLocated(PRODUCTION_DEVICES));
		prodDevice.click();
		Thread.sleep(2000);
		return driver.getCurrentUrl();
	}
	
	public String ClickAddProdDevice() throws InterruptedException {

		WebElement AddProdDevice = wait.until(ExpectedConditions.visibilityOfElementLocated(ADD_PROD_DEVICE));
		AddProdDevice.click();
		Thread.sleep(2000);
		WebElement addProdDevicePageTitle = driver.findElement(commonMethods.PAGE_TITLE);
		return addProdDevicePageTitle.getText();

	}
	
	
	
	
	
	
	
	
	
	
	
	
	
}
