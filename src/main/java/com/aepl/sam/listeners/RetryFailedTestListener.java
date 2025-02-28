package com.aepl.sam.listeners;

import org.testng.IRetryAnalyzer;
import org.testng.ITestResult;

public class RetryFailedTestListener implements IRetryAnalyzer {
    private int retryCount = 0;
    private static final int maxRetryCount = 2; // Change this value for more retries

    @Override
    public boolean retry(ITestResult result) {
        if (retryCount < maxRetryCount) {
            retryCount++;
            System.out.println("🔄 [Retry] Retrying failed test: " + result.getName() + " | Attempt: " + retryCount);
            return true;
        }
        return false;
    }
}
