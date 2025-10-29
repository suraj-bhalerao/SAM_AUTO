package com.aepl.sam.locators;

import org.openqa.selenium.By;

public class GovernmentServerPageLocators extends CommonPageLocators {

	// Dropdown Link
	public static final By GOVERNMENT_NAV_LINK = By.xpath("//a[contains(@href,'govt-servers')]");

	// Toast messages
	public static final By TOAST_MSG = By.xpath("//simple-snack-bar/div[1]");
	public static final By TOAST_MSG_2 = By.xpath("//simple-snack-bar/div");

	// Add Government Server
	public static final By ADD_GOV_SER = By.xpath("//button[contains(text(), 'Add Government Server')]");
	public static final By STATE = By.xpath("//input[@formcontrolname='state']");
	public static final By STATE_ABR = By.xpath("//input[@formcontrolname='stateCode']");
	public static final By GOV_IP1 = By.xpath("//input[@formcontrolname='govtIp1']");
	public static final By GOV_PORT1 = By.xpath("//input[@formcontrolname='port1']");
	public static final By GOV_IP2 = By.xpath("//input[@formcontrolname='govtIp2']");
	public static final By GOV_PORT2 = By.xpath("//input[@formcontrolname='port2']");
	public static final By STATE_ENABLED = By.xpath("//input[@formcontrolname='stateEnable']");

	// Buttons
	public static final By SUBMIT = By.xpath("//button[contains(text(),'Submit')]");
	public static final By UPDATE = By.xpath("//button[contains(text(),'Update')]");

	// Adding new firmware
	public static final By ADD_FIRM = By
			.xpath("//button[@class = 'primary-button' and contains(text(), 'Add Device Firmware')]");
	public static final By ADD_FIRM_FIRM_MASTER = By
			.xpath("//button[@class = 'primary-button' and contains(text(), 'Add Firmware')]");
	public static final By FRM_NAME = By.xpath("//input[@id='firmwareName']");
	public static final By FRM_DSC = By.xpath("//input[@id='description']");
	public static final By FILE_UPLOAD = By.xpath("//button/mat-icon[contains(text(), 'attach_file')]");
	public static final By CAL_BTN = By.xpath("//button[@aria-label='Open calendar']");
	public static final By QA_MANAGER_SELECT = By.xpath("//div/label/mat-label[contains(text(), 'QA')]");
	public static final By SOFT_MANAGER_SELECT = By.xpath("//div/label/mat-label[contains(text(), 'Soft')]");
	public static By DRP_OPTIONS = By.xpath("//div/child::mat-option");

	// new
	public static final By CLOSE_BUTTON = By
			.xpath("//button[@class= 'primary-button']/mat-icon[contains(text(), 'close')]");
	public static final By CHECK_BOX = By.xpath(".//input[@type='checkbox']");

	public static final By FIRMWARE_MASTER_BTN = By.xpath("//button[contains(text(), 'Firmware Master')]");

	/// Un-used
	public static final By PRM_IP_PORT = By.xpath("//tr[@class=\"ng-star-inserted\"]/td[4]");
	public static final By SEC_IP_PORT = By.xpath("//tr[@class=\"ng-star-inserted\"]/td[5]");
	public static final By DRP_MENU = By.tagName("//select");
}
