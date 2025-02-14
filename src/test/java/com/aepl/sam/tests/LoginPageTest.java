package com.aepl.sam.tests;

import org.testng.annotations.Test;

public class LoginPageTest {
	@Test
	void run() {
		int a = 0;
		while (a < 3) {
			System.out.println("Hi FROM TEST" + (++a));
			for (int i = a;i<=3; i++) {
				System.out.println("Hello from inner for loop");
			}
		}
	}
}
