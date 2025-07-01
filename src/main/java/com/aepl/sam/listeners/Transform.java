package com.aepl.sam.listeners;

import java.lang.reflect.Constructor;
import java.lang.reflect.Method;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.IAnnotationTransformer;
import org.testng.annotations.ITestAnnotation;

public class Transform implements IAnnotationTransformer {

	private static final Logger logger = LogManager.getLogger(Transform.class);

	@Override
	public void transform(ITestAnnotation annotation, Class testClass, Constructor testConstructor, Method testMethod) {
		String methodName = (testMethod != null) ? testMethod.getName() : "Unknown Method";
		logger.info("Transforming test method: {}", methodName);

		if (annotation.getRetryAnalyzerClass() == null) {
			annotation.setRetryAnalyzer(RetryFailedTestListener.class);
			logger.info("Retry analyzer set for method: {}", methodName);
		} else {
			logger.warn("Retry analyzer already exists for method: {}", methodName);
		}
	}
}
