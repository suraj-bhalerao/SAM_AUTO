package com.aepl.sam.pages;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.aepl.sam.actions.MouseActions;
import com.aepl.sam.constants.Constants;
import com.aepl.sam.locators.CommonPageLocators;

public class CommonPage extends CommonPageLocators {
	private WebDriver driver;
	private WebDriverWait wait;
	private MouseActions action;

	public CommonPage(WebDriver driver, WebDriverWait wait, MouseActions action) {
		this.driver = driver;
		this.wait = wait;
		this.action = action;
	}

	// Hover over navigation bar
	public String navBarLink(By locator) {
		try {
			action.hoverOverElement(wait.until(ExpectedConditions.visibilityOfElementLocated(DEVICE_UTILITY)));
			
			WebElement govServer = wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
			
			Thread.sleep(100);
			govServer.click();
		} catch (Exception e) {
			e.getLocalizedMessage();
		}
		return driver.getCurrentUrl();
	}

	// Back Button
	public void backButton() {
		try {
			WebElement element = wait.until(ExpectedConditions.visibilityOfElementLocated(BACK_BUTTON));
			element.click();

			Thread.sleep(2000);
			driver.navigate().to(Constants.GOV_LINK);
		} catch (Exception e) {
			e.getLocalizedMessage();
		}
	}

	// Refresh Button
	public void refreshButton() {
		try {
			WebElement refreshBtn = wait.until(ExpectedConditions.elementToBeClickable(REFRESH_BUTTON));
			Thread.sleep(2000);
			refreshBtn.click();
		} catch (Exception e) {
			e.getLocalizedMessage();
		}
	}

	// Search functionality
	public void searchItem() {
		wait.until(ExpectedConditions.urlToBe(Constants.GOV_LINK));
		WebElement search = driver.findElement(SEARCH_BOX_INPUT);
		List<WebElement> stateNames = driver.findElements(TABLE_DATA);
		WebElement searchBtn = driver.findElement(SEARCH_BOX_BTN);
		try {
			Thread.sleep(2000);
			WebElement itemToSearch = stateNames.get(0);
			search.sendKeys(itemToSearch.getText());
			searchBtn.click();

			Thread.sleep(2000);
			WebElement viewIcons = driver.findElement(EYE_ICON);
			viewIcons.click();
		} catch (Exception e) {
			e.getLocalizedMessage();
		}

	}
	// Eye button click
	// Delete button click
	// table validation method
	// Pagination Section
	public void paginationCheck() {
		WebElement rowsPerPage = driver.findElement(ROW_PER_PAGE);
		WebElement pagesPerRow = driver.findElement(PAGINATION);
	}
	
	// Footer
}
