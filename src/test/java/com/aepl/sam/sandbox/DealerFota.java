package com.aepl.sam.sandbox;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.aepl.sam.base.TestBase;
import com.aepl.sam.pages.LoginPage;
import com.aepl.sam.utils.ConfigProperties;

public class DealerFota extends TestBase {
	private WebDriver driver;
	private LoginPage loginPage;

	@Override
	@BeforeTest
	public void setUp() {
		driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.get("http://20.219.88.214:6102/login");
		((JavascriptExecutor) driver).executeScript("document.body.style.zoom='70%'");
		loginPage = new LoginPage(driver, new WebDriverWait(driver, Duration.ofSeconds(10)));
	}

	@Override
	@Test(priority = 1, testName = "login")
	public void login() {
		loginPage.enterUsername(ConfigProperties.getProperty("username"))
		.enterPassword(ConfigProperties.getProperty("password")).clickLogin();

		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

		WebElement deviceUtilityLink = wait
				.until(ExpectedConditions.elementToBeClickable(By.xpath("//a[contains(text(), 'Device Utility' )]")));
		deviceUtilityLink.click();

		WebElement dealerFotaLink = wait
				.until(ExpectedConditions.elementToBeClickable(By.xpath("//a[contains(text(), 'Dealer FOTA' )]")));
		dealerFotaLink.click();
	}

	@Test(priority = 2, testName = "Dealer Fota Export Button")
	public void testDealerFotaExportButton() {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

		for (int i = 0; i < 10; i++) {
			WebElement exportButton = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[contains(text(), 'Export')]")));
			exportButton.click();

			Alert alert = wait.until(ExpectedConditions.alertIsPresent());
			alert.accept();
		}
	}

	@Test(priority = 3, testName = "Add Button and then back")
	public void testAddButtonAndBack() {
		for (int i = 0; i < 10; i++) {
			WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
			WebElement addButton = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[contains(text(), 'Add')]")));
			addButton.click();

			WebElement backButton = wait.until(
					ExpectedConditions.elementToBeClickable(By.xpath("//div/mat-icon[contains(text(), 'arrow')]")));
			backButton.click();
		}
	}

	@Test(priority = 4, testName = "Add Dealer Fota File")
	public void testAddDealerFotaFile() {
		for (int i = 0; i < 10; i++) {
			WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

			WebElement deviceUtilityLink = wait.until(
					ExpectedConditions.elementToBeClickable(By.xpath("//a[contains(text(), 'Device Utility' )]")));
			deviceUtilityLink.click();

			WebElement dealerFotaLink = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath("//a[contains(text(), 'Dealer FOTA' )]")));
			dealerFotaLink.click();

			WebElement addButton = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[contains(text(), 'Add')]")));
			addButton.click();

			WebElement fileInput = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath("//input[@id='fileName']")));
			fileInput.clear();
			fileInput.sendKeys("Demo " + i + ".txt");

			WebElement saveButton = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[contains(text(), 'Submit')]")));

			if (saveButton.isEnabled()) {
				saveButton.click();
			} else {
				System.out.println("Save button is not displayed for iteration: " + i);
			}
		}
	}

	@Test(priority = 5, testName = "Delete Dealer Fota File")
	public void testDeleteDealerFotaFile() throws InterruptedException {
		for (int i = 0; i < 10; i++) {
			WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

			WebElement deviceUtilityLink = wait.until(
					ExpectedConditions.elementToBeClickable(By.xpath("//a[contains(text(), 'Device Utility' )]")));
			deviceUtilityLink.click();

			WebElement dealerFotaLink = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath("//a[contains(text(), 'Dealer FOTA' )]")));
			dealerFotaLink.click();

			WebElement addButton = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[contains(text(), 'Add')]")));
			addButton.click();

			Thread.sleep(500);

			JavascriptExecutor js = (JavascriptExecutor) driver;
			js.executeScript("window.scrollBy(0, 2000);");

			Thread.sleep(500);

			List<WebElement> deleteButton = wait.until(ExpectedConditions
					.visibilityOfAllElementsLocatedBy(By.xpath("//button/mat-icon[contains(text(), 'delete')]")));

			if (deleteButton.size() > 0) {
				deleteButton.get(2).click();

				Alert alert = wait.until(ExpectedConditions.alertIsPresent());
				alert.accept();
			} else {
				System.out.println("Delete button is not displayed for iteration: " + i);
			}
		}
	}

	@Override
	@AfterTest
	public void tearDown() {
		if (driver != null) {
			driver.quit();
		}
	}
}
