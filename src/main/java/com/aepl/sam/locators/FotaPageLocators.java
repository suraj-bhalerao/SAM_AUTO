package com.aepl.sam.locators;

import org.openqa.selenium.By;

public class FotaPageLocators extends CommonPageLocators {
	public static final By FOTA_LINK = By.xpath("//a[@routerlink=\"fota-batch\"]");
	public static final By MANUAL_FOTA_BTN = By.xpath("//button[contains(text(), 'Man')]");
	public static final By MANUAL_FOTA = By.xpath("//input[contains(@placeholder, 'Search by IMEI or UIN')]");
	public static final By SEARCH_BTN = By.xpath("//button[contains(text(), 'Search')]");
}
