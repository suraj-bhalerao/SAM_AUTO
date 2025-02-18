package com.aepl.sam.base;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;

import com.aepl.sam.utils.WebDriverFactory;

public class TestBase {
	protected WebDriver driver;

	@BeforeMethod
	public void zoomChrome() {
		if (driver != null) {
			((JavascriptExecutor) driver).executeScript("document.body.style.zoom='75%'");
		}
	}

	@BeforeClass
	public void setUp() {
		driver = WebDriverFactory.getWebDriver("Firefox");
		driver.manage().window().maximize();
		driver.get("http://aepltest.accoladeelectronics.com:6102/login");
	}

	@AfterClass
	public void tearDown() {
		if (driver != null)
			driver.quit();
	}
}
