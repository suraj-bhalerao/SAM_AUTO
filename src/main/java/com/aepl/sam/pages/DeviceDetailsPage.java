package com.aepl.sam.pages;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.aepl.sam.locators.DeviceDetailsPageLocators;
import com.aepl.sam.utils.CommonMethods;

public class DeviceDetailsPage extends DeviceDetailsPageLocators {
	private WebDriver driver;
	private WebDriverWait wait;
	private CommonMethods comm;

	public DeviceDetailsPage(WebDriver driver, WebDriverWait wait, CommonMethods comm) {
		this.driver = driver;
		this.wait = wait;
		this.comm = comm;
	}

	public void searchAndViewDevice() {
		try {
			WebElement canvas = driver.findElement(By.tagName("canvas"));
			JavascriptExecutor js = (JavascriptExecutor) driver;			
			js.executeScript("arguments[0].scrollIntoView(true);", canvas);
			WebElement serachField = driver.findElement(SEARCH_BOX_INPUT);
			Thread.sleep(2000); 
			comm.highlightElement(serachField, "Green");
			serachField.clear();
			serachField.sendKeys("867950076681921");
			WebElement searchButton = driver.findElement(SEARCH_BOX_BTN);
			searchButton.click();
			comm.highlightElement(serachField, "Green");
			WebElement eyeIcon = driver.findElement(EYE_ICON);
			eyeIcon.click();
		} catch (Exception e) {
			System.out.println("An error occurred while searching and viewing the device.");
		}
	}
	
	public void allComponentDetails() {
		try {
			List<WebElement> listOfComponents = wait.until(driver -> driver.findElements(ALL_COMPONENT));
			if (listOfComponents.isEmpty()) {
				System.out.println("No components found.");
			} else {
				for (WebElement component : listOfComponents) {
					List<WebElement> datas = component.findElements(By.xpath("//table/tbody/tr/td/div"));
					for (WebElement data : datas) {
						String componentName = data.getText();
						if(componentName.equals("IMEI") && componentName.equals("867950076681921")) {
							System.out.println("Component Name: " + componentName);
						} 
					}
				}
			}
		}catch (Exception e) {
			System.out.println("An error occurred while retrieving all component details.");
		}
	}
}
