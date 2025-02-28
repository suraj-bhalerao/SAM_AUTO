package com.aepl.sam.pages;

import java.util.List;

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

	public void clickNavBar() {
		// Wait for the navigation bar links to be visible
		List<WebElement> navBarLinks = wait
				.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(DEVICE_DASHBOARD));

		boolean isClicked = false;
		for (WebElement link : navBarLinks) {
			if (link.getText().equalsIgnoreCase("Dashboard")) {
				link.click();
//					System.out.println("Clicked On Element On Nav: " +link.getAccessibleName());
				isClicked = true;
//					break;
			}
		}
		if (!isClicked) {
			throw new RuntimeException("Failed to find and click on 'Dashboard' in the navigation bar.");
		}
	}
}
