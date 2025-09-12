package com.aepl.sam.pages;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import com.aepl.sam.locators.DealearsManagementLocators;
import com.aepl.sam.utils.CommonMethods;

public class DealearsManagementPage extends DealearsManagementLocators {
	private WebDriver driver;
	private WebDriverWait wait;
	private CommonMethods comm;
	private final Logger logger = LogManager.getLogger(this.getClass().getSimpleName());

	public DealearsManagementPage(WebDriver driver, WebDriverWait wait, CommonMethods comm) {
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
}
