package com.aepl.sam.listeners;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.IRetryAnalyzer;
import org.testng.ITestResult;

public class RetryFailedTestListener implements IRetryAnalyzer {
	private int retryCount = 0;
	private static final int maxRetryCount = 3;
	private static final Logger logger = LogManager.getLogger(RetryFailedTestListener.class);

	@Override
	public boolean retry(ITestResult result) {
		String testName = result.getName();
		logger.info("Checking retry for test: {} | Attempt: {}", testName, retryCount + 1);

		if (retryCount < maxRetryCount) {
			retryCount++;
			logger.warn("Retrying test: {} | Retry Attempt: {}", testName, retryCount);
			return true;
		}

		logger.error("Test failed after {} retries: {}", retryCount, testName);
		return false;
	}
}
