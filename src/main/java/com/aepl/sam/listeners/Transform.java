package com.aepl.sam.listeners;

import java.lang.reflect.Constructor;
import java.lang.reflect.Method;

import org.testng.IAnnotationTransformer;
import org.testng.annotations.ITestAnnotation;

public class Transform implements IAnnotationTransformer {

	@Override
	public void transform(ITestAnnotation annotation, Class testClass, Constructor testConstructor, Method testMethod) {
		// Set Retry Analyzer only if it is not already set
		if (annotation.getRetryAnalyzerClass() == null) {
			annotation.setRetryAnalyzer(RetryFailedTestListener.class);
		}
	}
}
