package com.aepl.sam.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.aepl.sam.actions.CalendarActions;
import com.aepl.sam.actions.MouseActions;
import com.aepl.sam.locators.ProductionDevicePageLocators;
import com.aepl.sam.utils.CommonMethods;

public class ProductionDevicePage extends ProductionDevicePageLocators{

	private WebDriver driver;
	private WebDriverWait wait;
	private CommonMethods commonMethods;
	private MouseActions action;
	private CalendarActions CalAct;

	public ProductionDevicePage(WebDriver driver, WebDriverWait wait, MouseActions action) {
		this.driver = driver;
		this.wait = wait;
		this.commonMethods = new CommonMethods(driver, wait);
		this.action = action;
		this.CalAct = new CalendarActions(driver, wait);
	}
	
	public String navBarLink() throws InterruptedException {
		action.hoverOverElement(wait.until(ExpectedConditions.visibilityOfElementLocated(DEVICE_UTILITY)));

		WebElement prodDevice = wait.until(ExpectedConditions.visibilityOfElementLocated(PRODUCTION_DEVICES));
		prodDevice.click();
		Thread.sleep(2000);
		return driver.getCurrentUrl();
	}
	
	public String ClickAddProdDevice() throws InterruptedException {

		WebElement AddProdDevice = wait.until(ExpectedConditions.visibilityOfElementLocated(ADD_PROD_DEVICE));
		AddProdDevice.click();
		Thread.sleep(2000);
		WebElement addProdDevicePageTitle = driver.findElement(commonMethods.PAGE_TITLE);
		return addProdDevicePageTitle.getText();

	}
	
	public String NewInputFields(String para) throws InterruptedException {
		if (para.equalsIgnoreCase("add")) {
			
			WebElement AddUID = wait.until(ExpectedConditions.visibilityOfElementLocated(UID));
			AddUID.sendKeys("AddingUID Character");

			WebElement AddIMEI = wait
					.until(ExpectedConditions.visibilityOfElementLocated(IMEI));
			AddIMEI.sendKeys("Add Description");

			WebElement AddICCID = wait
					.until(ExpectedConditions.visibilityOfElementLocated(ICCID));
			AddICCID.sendKeys("AddingICCID Sequence");

			WebElement Add_DEVICE_MODEL_NAME = wait
					.until(ExpectedConditions.visibilityOfElementLocated(DEVICE_MODEL_NAME));
			Add_DEVICE_MODEL_NAME.sendKeys("Add Version");
			Thread.sleep(2000);
			WebElement Add_OPERATOR_NUMBER = wait.until(ExpectedConditions.visibilityOfElementLocated(OPERATOR_NUMBER));
			Add_OPERATOR_NUMBER.sendKeys("Add Operator");
			
			WebElement Add_MOBILE_NUMBER = wait.until(ExpectedConditions.visibilityOfElementLocated(MOBILE_NUMBER));
			Add_MOBILE_NUMBER.sendKeys("Add Operator");
			
			WebElement Add_ALT_MOBILE_NO = wait.until(ExpectedConditions.visibilityOfElementLocated(ALT_MOBILE_NO));
			Add_ALT_MOBILE_NO.sendKeys("Add Operator");
			
			WebElement Add_SERVICE_PROVIDER = wait.until(ExpectedConditions.visibilityOfElementLocated(SERVICE_PROVIDER));
			Add_SERVICE_PROVIDER.sendKeys("Add Operator");
			
			WebElement Add_ALT_SERVICE_PROVIDER = wait.until(ExpectedConditions.visibilityOfElementLocated(ALT_SERVICE_PROVIDER));
			Add_ALT_SERVICE_PROVIDER.sendKeys("Add Operator");
			
//			WebElement calendar = driver.findElement(CAL_BTN);
//			calendar.click();
			
			wait.until(ExpectedConditions.elementToBeClickable(CAL_BTN));
			CalAct.selectDate(CAL_BTN, "1-1-2025");
			
			WebElement SubmitButton = wait.until(ExpectedConditions.visibilityOfElementLocated(SUBMIT_BTN));
			SubmitButton.click();
			
			Thread.sleep(2000);
			WebElement DeviceModelPageTitle = driver.findElement(commonMethods.PAGE_TITLE);
			return DeviceModelPageTitle.getText();

		} else if (para.equalsIgnoreCase("update")) {
			WebElement AddUID = wait.until(ExpectedConditions.visibilityOfElementLocated(UID));
			AddUID.clear();
			AddUID.sendKeys("Add Model");

			WebElement AddIMEI = wait
					.until(ExpectedConditions.visibilityOfElementLocated(IMEI));
			AddIMEI.clear();
			AddIMEI.sendKeys("Add Description");

			WebElement AddICCID = wait
					.until(ExpectedConditions.visibilityOfElementLocated(ICCID));
			AddICCID.clear();
			AddICCID.sendKeys("Add Sequence");

			WebElement Add_DEVICE_MODEL_NAME = wait
					.until(ExpectedConditions.visibilityOfElementLocated(DEVICE_MODEL_NAME));
			Add_DEVICE_MODEL_NAME.clear();
			Add_DEVICE_MODEL_NAME.sendKeys("Add Version");
			Thread.sleep(2000);
			WebElement Add_OPERATOR_NUMBER = wait.until(ExpectedConditions.visibilityOfElementLocated(OPERATOR_NUMBER));
			Add_OPERATOR_NUMBER.clear();
			Add_OPERATOR_NUMBER.sendKeys("Add Operator");
			
			WebElement Add_MOBILE_NUMBER = wait.until(ExpectedConditions.visibilityOfElementLocated(MOBILE_NUMBER));
			Add_MOBILE_NUMBER.clear();
			Add_MOBILE_NUMBER.sendKeys("Add Operator");
			
			WebElement Add_ALT_MOBILE_NO = wait.until(ExpectedConditions.visibilityOfElementLocated(ALT_MOBILE_NO));
			Add_ALT_MOBILE_NO.clear();
			Add_ALT_MOBILE_NO.sendKeys("Add Operator");
			
			WebElement Add_SERVICE_PROVIDER = wait.until(ExpectedConditions.visibilityOfElementLocated(SERVICE_PROVIDER));
			Add_SERVICE_PROVIDER.clear();
			Add_SERVICE_PROVIDER.sendKeys("Add Operator");
			
			WebElement Add_ALT_SERVICE_PROVIDER = wait.until(ExpectedConditions.visibilityOfElementLocated(ALT_SERVICE_PROVIDER));
			Add_ALT_SERVICE_PROVIDER.clear();
			Add_ALT_SERVICE_PROVIDER.sendKeys("Add Operator");
			
			WebElement calendar = driver.findElement(CAL_BTN);
			calendar.click();
			wait.until(ExpectedConditions.elementToBeClickable(CAL_BTN));
			CalAct.selectDate(CAL_BTN, "1-1-2025");
			
			WebElement RefreshButton = wait.until(ExpectedConditions.visibilityOfElementLocated(REFRESH_BTN));
			RefreshButton.click();
			Thread.sleep(2000);
			WebElement ProdDevicePageTitle = driver.findElement(commonMethods.PAGE_TITLE);
			return ProdDevicePageTitle.getText();
		}

		return "Not either production device is added or updated";

	}
	
	
	
	
	
	
	
	
	
	
	
}
