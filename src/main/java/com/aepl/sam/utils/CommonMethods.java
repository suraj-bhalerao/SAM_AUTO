package com.aepl.sam.utils;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

public class CommonMethods {
	// Fields
	public WebDriver driver;

	// Constructor
	public CommonMethods(WebDriver driver) {
		this.driver = driver;
	}

	// Methods
	public void captureScreenshot(String testCaseName) {
		if (driver == null) {
			return;
		}

		String timestamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
		String screenshotName = testCaseName + "_" + timestamp + ".png";
		String screenshotPath = "screenshots/" + screenshotName;

		try {
			File screenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
			FileUtils.copyFile(screenshot, new File(screenshotPath));
		} catch (IOException e) {
			System.err.println("Error " + e);
		}
	}
}
