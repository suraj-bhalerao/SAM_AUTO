package com.aepl.sam.tests;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.aepl.sam.base.TestBase;

public class UserRolePageTest extends TestBase {
	@Test()
	public void ex() {
		boolean actualResult = 1 == 1;
		System.out.println("Actual value: " + actualResult);
		Assert.assertEquals(actualResult, true);
	}
}
