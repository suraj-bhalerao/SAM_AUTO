package com.aepl.sam.listeners;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.commons.io.FileUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aepl.sam.base.TestBase;
import com.aepl.sam.reports.ExtentManager;
import com.aepl.sam.reports.ExtentTestManager;
import com.aventstack.extentreports.Status;

public class TestListener extends TestBase implements ITestListener {

	private static final Logger logger = LogManager.getLogger(TestListener.class);

	@Override
	public void onTestStart(ITestResult result) {
		String testName = result.getMethod().getMethodName();
		logger.info("Test started: {}", testName);
		ExtentTestManager.startTest(testName);
		ExtentTestManager.getTest().log(Status.INFO, "Test Started: " + testName);
	}

	@Override
	public void onTestSuccess(ITestResult result) {
		String testName = result.getMethod().getMethodName();
		logger.info("Test passed: {}", testName);
		ExtentTestManager.getTest().log(Status.PASS, "Test Passed: " + testName);
	}

	@Override
	public void onTestFailure(ITestResult result) {
		String testName = result.getMethod().getMethodName();
		Throwable throwable = result.getThrowable();

		logger.error("Test failed: {} | Reason: {}", testName,
				(throwable != null ? throwable.getMessage() : "Unknown error"));
		ExtentTestManager.getTest().log(Status.FAIL, "Test Failed: " + testName);
		ExtentTestManager.getTest().log(Status.FAIL,
				"Cause: " + (throwable != null ? throwable.getMessage() : "Unknown"));

		try {
			if (driver == null) {
				throw new RuntimeException("WebDriver is null. Screenshot cannot be captured.");
			}

			String timestamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
			String screenshotName = testName + "_" + timestamp + ".png";

			String screenshotDir = System.getProperty("user.dir") + File.separator + "screenshots";
			String screenshotPath = screenshotDir + File.separator + screenshotName;

			new File(screenshotDir).mkdirs();

			File screenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
			FileUtils.copyFile(screenshot, new File(screenshotPath));

			logger.info("Screenshot captured for failed test: {}", screenshotPath);
			ExtentTestManager.getTest().log(Status.FAIL, "Screenshot captured: " + screenshotPath);
			ExtentTestManager.getTest().addScreenCaptureFromPath(screenshotPath);

		} catch (Exception e) {
			logger.warn("Failed to capture screenshot: {}", e.getMessage(), e);
			ExtentTestManager.getTest().log(Status.WARNING,
					"Failed to capture screenshot due to: " + e.getMessage());
		}
	}

	@Override
	public void onTestSkipped(ITestResult result) {
		String testName = result.getMethod().getMethodName();
		logger.warn("Test skipped: {}", testName);
		ExtentTestManager.getTest().log(Status.SKIP, "Test Skipped: " + testName);
	}

	@Override
	public void onStart(ITestContext context) {
		logger.info("Test suite started: {}", context.getName());
		ExtentManager.createInstance();
	}

	@Override
	public void onFinish(ITestContext context) {
		logger.info("Test suite finished: {}", context.getName());
		ExtentTestManager.getTest().log(Status.INFO, "Test Suite Finished: " + context.getName());
		ExtentManager.flush();
	}

	@Override
	public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
		logger.info("Test failed but within success percentage: {}", result.getMethod().getMethodName());
	}

	@Override
	public void onTestFailedWithTimeout(ITestResult result) {
		logger.error("Test failed due to timeout: {}", result.getMethod().getMethodName());
		onTestFailure(result); // Treat timeout as a failure
	}
}
