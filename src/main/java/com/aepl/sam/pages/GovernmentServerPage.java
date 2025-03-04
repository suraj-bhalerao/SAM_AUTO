package com.aepl.sam.pages;

import org.openqa.selenium.JavascriptExecutor;
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
		try {
			action.hoverOverElement(wait.until(ExpectedConditions.visibilityOfElementLocated(DEVICE_UTILITY)));

			WebElement govServer = wait.until(ExpectedConditions.visibilityOfElementLocated(GOVERNMENT_NAV_LINK));
			Thread.sleep(10);
			govServer.click();
		} catch (Exception e) {
			e.getLocalizedMessage();
		}
		return driver.getCurrentUrl();
	}

	// check the back button
	public String backButton() {
		try {
			WebElement element = wait.until(ExpectedConditions.visibilityOfElementLocated(BACK_BUTTON));

			JavascriptExecutor js = (JavascriptExecutor) driver;
			js.executeScript("arguments[0].style.backgroundColor = 'GREEN'", element);

			element.click();
			Thread.sleep(10);

			System.out.println("Clicked on back button : " + element.getText());

		} catch (Exception e) {
			e.getLocalizedMessage();
		}
		// calling again to visit that page.
		return navBarLink();
	}

	// check the refresh button
	public String refreshButton() {
		try {
			WebElement refreshBtn = wait.until(ExpectedConditions.elementToBeClickable(REFRESH_BUTTON));

			JavascriptExecutor js = (JavascriptExecutor) driver;
			js.executeScript("arguments[0].style.backgroundColor = 'GREEN'", refreshBtn);

			Thread.sleep(20);

			refreshBtn.click();

			WebElement toastElement = wait.until(ExpectedConditions.visibilityOfElementLocated(TOAST_MSG));
			String toastMsg = toastElement.getText();

			return toastMsg;
			
		} catch (Exception e) {
			e.getLocalizedMessage();
		}
		return "No Data Found!!!";
	}
}
