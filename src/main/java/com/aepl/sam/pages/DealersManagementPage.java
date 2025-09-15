package com.aepl.sam.pages;

import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import com.aepl.sam.locators.DealersManagementLocators;
import com.aepl.sam.utils.CommonMethods;
import com.aepl.sam.utils.TableUtils;

public class DealersManagementPage extends DealersManagementLocators {
	private WebDriver driver;
	private WebDriverWait wait;
	private CommonMethods comm;
	private final Logger logger = LogManager.getLogger(this.getClass().getSimpleName());

	public DealersManagementPage(WebDriver driver, WebDriverWait wait, CommonMethods comm) {
		this.driver = driver;
		this.wait = wait;
		this.comm = comm;
	}

	public Boolean navBarLink() {
		boolean isViewed = false;
		try {
			logger.info("Navigating to Dealer Management page...");
			WebElement dealer = wait.until(ExpectedConditions.visibilityOfElementLocated(DEALER));
			comm.highlightElement(dealer, "violet");
			Assert.assertTrue(dealer.getText().trim().equals("DEALERS UTILITY"));
			dealer.click();

			WebElement dealer_management_link = wait
					.until(ExpectedConditions.visibilityOfElementLocated(DEALER_MANAGEMENT));
			comm.highlightElement(dealer_management_link, "violet");
			dealer_management_link.click();

			wait.until(ExpectedConditions.urlToBe("http://aepltest.accoladeelectronics.com:6102/dealers-management"));

			if (driver.getCurrentUrl().equals("http://aepltest.accoladeelectronics.com:6102/dealers-management")) {
				isViewed = true;
			}
			logger.info("Successfully navigated to Dealer Management page.");
		} catch (Exception e) {
			logger.error("Error navigating to Dealer Management: {}", e.getMessage(), e);
		}
		return isViewed;
	}

	public String verifyPageTitle() {
		WebElement pageTitle = driver.findElement(By.className("page-title"));
		comm.highlightElement(pageTitle, "violet");
		return pageTitle.getText();
	}

	public Boolean isSearchButtonEnabled() {
		WebElement search_btn = driver.findElement(SEARCH_BTN);
		Assert.assertTrue(search_btn.isEnabled());
		return search_btn.isEnabled();
	}

	public Object isSearchBoxEnabled() {
		WebElement search_box = driver.findElement(SEARCH_BOX);
		Assert.assertTrue(search_box.isEnabled());
		return search_box.isEnabled();
	}

	public Boolean validateSearchBoxWithMultipleInputs() {
		List<String> list_of_inputs = List.of("Suraj", "Dhananjay", "Sharukh", "QA", "Manager", "Admin");
		for (String input : list_of_inputs) {
			WebElement search_box = driver.findElement(SEARCH_BOX);
			if (search_box.isEnabled()) {
				search_box.clear();
				search_box.sendKeys(input);
				driver.findElement(SEARCH_BTN).click();
			} else {
				throw new NoSuchElementException();
			}
		}
		Assert.assertTrue(wait.until(ExpectedConditions.visibilityOfElementLocated(TOAST_MESSAGE)).getText()
				.contains("Data Fetched Successfully"));
		return true;
	}

	public Object validateTableHeders() {
		TableUtils table = new TableUtils(wait);
		List<Map<String, String>> tableDetails = table.getTableDetails(TABLE);
		return tableDetails;
	}
}
