package com.aepl.sam.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.aepl.sam.locators.DeviceDashboardPageLocators;
import com.aepl.sam.utils.CommonMethods;

public class DeviceDashboardPage extends DeviceDashboardPageLocators {
	
//	public class DeviceDashboardPage extends DeviceDashboardPageLocators {

		private WebDriver driver;
		private WebDriverWait wait;
		private CommonMethods commonMethods;

		public DeviceDashboardPage(WebDriver driver, WebDriverWait wait) {
			this.driver = driver;
			this.wait = wait;
			this.commonMethods = new CommonMethods(driver);
		}

		public void clickOnNavBar() {
			WebElement navBarLink = wait.until(ExpectedConditions.visibilityOfElementLocated(DEVICE_DASHBOARD));
			navBarLink.click();
		}
}
