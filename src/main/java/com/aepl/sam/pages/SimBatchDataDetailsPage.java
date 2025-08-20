package com.aepl.sam.pages;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.aepl.sam.locators.SimBatchDataDetailsPageLocators;

public class SimBatchDataDetailsPage extends SimBatchDataDetailsPageLocators {
	private WebDriver driver;
	private WebDriverWait wait;
	private final Logger logger = LogManager.getLogger(this.getClass().getSimpleName());

	public SimBatchDataDetailsPage(WebDriver driver, WebDriverWait wait) {
		this.driver = driver;
		this.wait = wait;
	}

}
