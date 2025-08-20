package com.aepl.sam.randomTest;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.aepl.sam.pages.LoginPage;
import com.aepl.sam.utils.ConfigProperties;

public class SimBatchDataDetails {
	private WebDriver driver;
	private LoginPage loginPage;
	private WebDriverWait wait;

	@BeforeTest
	public void setUp() {
		driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.get("http://aepltest.accoladeelectronics.com:6102/login");
		((JavascriptExecutor) driver).executeScript("document.body.style.zoom='70%'");
		wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		loginPage = new LoginPage(driver, wait);
		ConfigProperties.initialize("qa");
	}

	@Test(priority = 1, testName = "login")
	public void login() {
		loginPage.enterUsername(ConfigProperties.getProperty("username"))
				.enterPassword(ConfigProperties.getProperty("password")).clickLogin();

		WebElement deviceUtilityLink = wait
				.until(ExpectedConditions.elementToBeClickable(By.xpath("//a[contains(text(), 'REPORTS')]")));
		deviceUtilityLink.click();

		WebElement dealerFotaLink = wait.until(ExpectedConditions
				.elementToBeClickable(By.xpath("//a[contains(text(), 'SENSORISE SIM DATA DETAILS')]")));
		dealerFotaLink.click();

		Assert.assertEquals(driver.getCurrentUrl(),
				"http://aepltest.accoladeelectronics.com:6102/sensorise-sim-data-details");
	}

//	@Test(priority = 2, enabled = true, dependsOnMethods = "login", testName = "Page opened or not")
//	public void testManualUpload() {
//		String iccid = "89916430134726579000";
//		WebElement iccidInput;
//		String error_text;
//
//		WebElement button = driver.findElement(By.xpath("//button[contains(text(),'Manual Upload')]"));
////		Assert.assertTrue(button.getText().trim().equals("Manual Upload"));
//		if (button.isDisplayed()) {
//			button.click();
//		}
//
//		// Click, Get error message on empty
//		Assert.assertEquals(
//				wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div/label[@class='form-label']")))
//						.getText(),
//				"Enter ICCID's to get SIM Data Details");
//		iccidInput = driver.findElement(By.id("iccid"));
//		iccidInput.clear();
//		iccidInput.click();
//		driver.findElement(By.xpath("//div[@class='component-separator'][1]")).click();
//		error_text = driver.findElement(By.tagName("mat-error")).getText();
//		Assert.assertEquals(error_text, "This field is required and can't be only spaces.", "Not error for empty box");
//
//		// get error for the short/long iccid
//		iccidInput = driver.findElement(By.id("iccid"));
//		iccidInput.clear();
//		iccidInput.click();
//		iccidInput.sendKeys("asdf");
//		driver.findElement(By.xpath("//div[@class='component-separator'][1]")).click();
//		error_text = driver.findElement(By.tagName("mat-error")).getText();
//		Assert.assertEquals(error_text, "Value must be exactly 20 characters long.", "Not error for short/long iccid");
//
//		// send actual iccid
//		iccidInput = driver.findElement(By.id("iccid"));
//		iccidInput.clear();
//		iccidInput.click();
//		iccidInput.sendKeys(iccid);
//		Assert.assertTrue(driver.findElement(By.tagName("button")).isDisplayed());
//
//		driver.findElement(By.tagName("button")).click();
//	}

	@Test(priority = 2, enabled = true, dependsOnMethods = "login", testName = "Manual Upload Button Presence")
	public void testManualUploadButtonPresence() {
		WebElement button = driver.findElement(By.xpath("//button[contains(text(),'Manual Upload')]"));
		Assert.assertTrue(button.isDisplayed(), "'Manual Upload' button is not displayed");
		button.click();
		Assert.assertEquals(
				wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div/label[@class='form-label']")))
						.getText(),
				"Enter ICCID's to get SIM Data Details");
	}

	@Test(priority = 3, enabled = true, dependsOnMethods = "testManualUploadButtonPresence", testName = "Empty ICCID Validation")
	public void testEmptyICCIDValidation() {
		WebElement iccidInput = driver.findElement(By.id("iccid"));
		iccidInput.clear();
		iccidInput.click();

		driver.findElement(By.xpath("//div[@class='component-separator'][1]")).click(); // Click outside to trigger
																						// validation

		String errorText = driver.findElement(By.tagName("mat-error")).getText();
		Assert.assertEquals(errorText, "This field is required and can't be only spaces.",
				"Validation failed for empty ICCID input");
	}

	@Test(priority = 4, enabled = true, dependsOnMethods = "testManualUploadButtonPresence", testName = "Invalid ICCID Length Validation")
	public void testInvalidICCIDLengthValidation() {
		WebElement iccidInput = driver.findElement(By.id("iccid"));
		iccidInput.clear();
		iccidInput.click();
		iccidInput.sendKeys("asdf");

		driver.findElement(By.xpath("//div[@class='component-separator'][1]")).click(); // Click outside

		String errorText = driver.findElement(By.tagName("mat-error")).getText();
		Assert.assertEquals(errorText, "Value must be exactly 20 characters long.",
				"Validation failed for invalid ICCID length");
	}

	@Test(priority = 5, enabled = true, dependsOnMethods = "testManualUploadButtonPresence", testName = "Valid ICCID Submission")
	public void testValidICCIDSubmission() {
		String iccid = "89916430134726579000";

		WebElement iccidInput = driver.findElement(By.id("iccid"));
		iccidInput.clear();
		iccidInput.click();
		iccidInput.sendKeys(iccid);

		WebElement submitButton = driver.findElement(By.tagName("button"));
		Assert.assertTrue(submitButton.isDisplayed(), "Submit button not displayed after valid ICCID input");

		submitButton.click();
	}

	@AfterTest
	public void tearDown() {
		if (driver != null) {
			driver.quit();
		}
	}
}
