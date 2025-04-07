package com.aepl.sam.listeners;

import org.testng.IRetryAnalyzer;
import org.testng.ITestResult;

public class RetryFailedTestListener implements IRetryAnalyzer {
	private int retryCount = 0;
	private static final int maxRetryCount = 3;

	@Override
	public boolean retry(ITestResult result) {
		System.out.println("🔄 Checking retry for test: " + result.getName() + " | Attempt: " + (retryCount + 1));

		if (retryCount < maxRetryCount) {
			retryCount++;
			System.out.println("🔄 Retrying test: " + result.getName() + " | Retry Attempt: " + retryCount);
			return true;
		}

		System.out.println("❌ Test failed after " + retryCount + " retries: " + result.getName());
		return false;
	}
}