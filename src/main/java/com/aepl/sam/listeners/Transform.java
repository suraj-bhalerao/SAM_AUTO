package com.aepl.sam.listeners;

import java.lang.reflect.Constructor;
import java.lang.reflect.Method;

import org.testng.IAnnotationTransformer;
import org.testng.annotations.ITestAnnotation;

public class Transform implements IAnnotationTransformer {
	@Override
	public void transform(ITestAnnotation annotation, Class testClass, Constructor testConstructor, Method testMethod) {
		System.out.println("🔄 Transforming: " + (testMethod != null ? testMethod.getName() : "Unknown Method"));

		if (annotation.getRetryAnalyzerClass() == null) {
			annotation.setRetryAnalyzer(RetryFailedTestListener.class);
			System.out.println("✅ Retry Analyzer Set for: " + testMethod.getName());
		} else {
			System.out.println("⚠️ Retry Analyzer Already Exists for: " + testMethod.getName());
		}
	}
}
