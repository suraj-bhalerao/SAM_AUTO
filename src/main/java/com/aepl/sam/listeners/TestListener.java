package com.aepl.sam.listeners;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aepl.sam.base.TestBase;
import com.aepl.sam.reports.ExtentManager;
import com.aepl.sam.reports.ExtentTestManager;
import com.aventstack.extentreports.Status;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;

public class TestListener extends TestBase implements ITestListener {

    @Override
    public void onTestStart(ITestResult result) {
        String testName = result.getMethod().getMethodName();
        ExtentTestManager.startTest(testName);
        ExtentTestManager.getTest().log(Status.INFO, "Test Started: " + testName);
    }

    @Override
    public void onTestSuccess(ITestResult result) {
        String testName = result.getMethod().getMethodName();
        ExtentTestManager.getTest().log(Status.PASS, "Test Passed: " + testName);
    }

    @Override
    public void onTestFailure(ITestResult result) {
        String testName = result.getMethod().getMethodName();
        Throwable throwable = result.getThrowable();

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

            ExtentTestManager.getTest().log(Status.FAIL, "Screenshot captured: " + screenshotPath);
            ExtentTestManager.getTest().addScreenCaptureFromPath(screenshotPath);

        } catch (Exception e) {
            ExtentTestManager.getTest().log(Status.WARNING,
                    "Failed to capture screenshot due to: " + e.getMessage());
        }
    }

    @Override
    public void onTestSkipped(ITestResult result) {
        String testName = result.getMethod().getMethodName();
        ExtentTestManager.getTest().log(Status.SKIP, "Test Skipped: " + testName);
    }

    @Override
    public void onStart(ITestContext context) {
        ExtentManager.createInstance();
        System.out.println("Test Suite Started: " + context.getName());
    }

    @Override
    public void onFinish(ITestContext context) {
        ExtentTestManager.getTest().log(Status.INFO, "Test Suite Finished: " + context.getName());
        ExtentManager.flush();
    }

    @Override
    public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
        // Optional implementation
    }

    @Override
    public void onTestFailedWithTimeout(ITestResult result) {
        onTestFailure(result); // Treat timeout as a failure
    }
}
