package com.aepl.sam.sandbox;

import java.io.FileInputStream;
import java.io.IOException;
import java.time.Duration;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.aepl.sam.base.TestBase;
import com.aepl.sam.pages.LoginPage;

public class LCT_A4G_OTA_AUTO extends TestBase {
	private WebDriver driver;
	private LoginPage loginPage;
	private WebDriverWait wait;

	@Override
	@BeforeTest
	public void setUp() {
		driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.get("http://lct-a4g-qa.accoladeelectronics.com/login");
		((JavascriptExecutor) driver).executeScript("document.body.style.zoom='70%'");
		loginPage = new LoginPage(driver, new WebDriverWait(driver, Duration.ofSeconds(10)));
	}

	@Override
	@Test(priority = 1, testName = "login")
	public void login() {
		loginPage.enterUsername("suraj.bhalerao@accoladeelectronics.com").enterPassword("oG&ghlpK").clickLogin();

		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

		WebElement deviceUtilityLink = wait
				.until(ExpectedConditions.elementToBeClickable(By.xpath("//a[contains(text(), 'DEVICE UTILITY')]")));
		deviceUtilityLink.click();

		WebElement ota = wait.until(
				ExpectedConditions.elementToBeClickable(By.cssSelector("a[ng-reflect-router-link='ota-batch-page']")));
		ota.click();

		WebElement ota_master = wait.until(
				ExpectedConditions.visibilityOfElementLocated(By.xpath("//button[contains(text(),'OTA Master')]")));
		ota_master.click();
		WebElement add_ota = wait.until(ExpectedConditions
				.visibilityOfElementLocated(By.xpath("//button[contains(text(),'Add OTA Command')]")));
		add_ota.click();
	}

	@Test(priority = 2)
	public void ota() throws IOException, InterruptedException {
		this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		FileInputStream file = new FileInputStream("data.xlsx");
		Workbook workbook = new XSSFWorkbook(file);
		Sheet sheet = workbook.getSheetAt(0);

		// 3. Loop through each row (skip header row at index 0)
		for (int i = 1; i <= sheet.getLastRowNum(); i++) {

			Row row = sheet.getRow(i);

			String ota_name = row.getCell(0).getStringCellValue();
			String ota_command = row.getCell(1).getStringCellValue();
			String ota_type = row.getCell(2).getStringCellValue();
			String example = row.getCell(3).getStringCellValue();
			String input_field = row.getCell(4).getStringCellValue();

			// Fill form fields (UPDATE LOCATORS as needed)
			WebElement otaName = wait.until(
					ExpectedConditions.visibilityOfElementLocated((By.xpath("//input[@formcontrolname = 'name']"))));
			otaName.clear();
			otaName.sendKeys(ota_name);

			WebElement otaCommand = wait.until(ExpectedConditions
					.visibilityOfElementLocated((By.xpath("//input[@formcontrolname = 'otaCommand']"))));
			otaCommand.clear();
			otaCommand.sendKeys(ota_command);

			// ota type is a dropdown
			WebElement otaType = wait.until(ExpectedConditions
					.visibilityOfElementLocated((By.xpath("//mat-select[@formcontrolname = 'otaCommandType']"))));
			otaType.click();
			WebElement otaTypeOption = wait.until(ExpectedConditions
					.visibilityOfElementLocated((By.xpath("//mat-option/span[contains(text(), '" + ota_type + "')]"))));
			otaTypeOption.click();

			WebElement exampleField = wait.until(ExpectedConditions
					.visibilityOfElementLocated((By.xpath("//input[@formcontrolname = 'otaCommandExample']"))));
			exampleField.clear();
			exampleField.sendKeys(example);

			// input field is a dropdown
			WebElement dropdown = wait.until(ExpectedConditions
					.elementToBeClickable(By.xpath("//mat-select[@formcontrolname='isInputFieldRequired']")));
			((JavascriptExecutor) driver).executeScript("arguments[0].click();", dropdown);

			WebElement option = wait.until(ExpectedConditions
					.elementToBeClickable(By.xpath("//mat-option//span[contains(text(),'" + input_field + "')]")));
			((JavascriptExecutor) driver).executeScript("arguments[0].click();", option);

			// Click submit
			wait.until(
					ExpectedConditions.visibilityOfElementLocated((By.xpath("//button[contains(text(), 'Submit')]"))))
					.click();

			WebElement toastMessage = wait
					.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//simple-snack-bar/div[1]")));
			String messageText = toastMessage.getText().trim().strip().toString();

			if (messageText.equals("OTA Command or Name already exists in the system")) {
//				driver.findElement(By.xpath(".//mat-icon[contains(text(),'back')]")).click();
				driver.navigate().refresh();
				((JavascriptExecutor) driver).executeScript("document.body.style.zoom='70%'");
				continue;
			}

			// Wait for form to submit / page to reload
			Thread.sleep(5000);

			WebElement add_ota = wait.until(ExpectedConditions
					.visibilityOfElementLocated(By.xpath("//button[contains(text(),'Add OTA Command')]")));
			add_ota.click();
		}
		workbook.close();
		driver.quit();
	}
}
