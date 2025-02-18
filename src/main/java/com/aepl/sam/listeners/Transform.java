package com.aepl.sam.listeners;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import org.testng.IAnnotationTransformer;
import org.testng.IRetryAnalyzer;
import org.testng.annotations.ITestAnnotation;

public class Transform implements IAnnotationTransformer {

	@Override
	public void transform(ITestAnnotation testAnnotation, Class testClass, Constructor testConstructor,
			Method testMethod) {
		try {
			Method getRetryMethod = ITestAnnotation.class.getMethod("getRetryAnalyzer");
			IRetryAnalyzer retry = (IRetryAnalyzer) getRetryMethod.invoke(testAnnotation);

			if (retry == null) {
				testAnnotation.setRetryAnalyzer(RetryFailedTestListner.class);
			}
		} catch (NoSuchMethodException | IllegalAccessException | InvocationTargetException e) {
			System.err.println("TestNG version does not support getRetryAnalyzer(): " + e.getMessage());
		}
	}
}

