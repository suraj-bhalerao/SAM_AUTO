package com.aepl.sam.utils;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.aepl.sam.locators.CommonLocatorsPage;

public class CommonMethods extends CommonLocatorsPage {
	// Fields
	public WebDriver driver;
	private WebDriverWait wait;
	
	
	// Constructor
	public CommonMethods(WebDriver driver,WebDriverWait wait) {
		this.driver = driver;
		this.wait = wait;
		
	}

	// Methods
	public void captureScreenshot(String testCaseName) {
		if (driver == null) {
			throw new RuntimeException("WebDriver initialization failed in: Commonmethods");
		}

		String timestamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
		String screenshotName = testCaseName + "_" + timestamp + ".png";
		String screenshotPath = "screenshots/" + screenshotName;

		try {
			File screenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
			FileUtils.copyFile(screenshot, new File(screenshotPath));
		} catch (IOException e) {
			System.err.println("Error " + e);
		}
	}
	
	public void verifyWebpageLogo() {
		
		JavascriptExecutor js = (JavascriptExecutor) driver;	    
	    // Wait for the logo element to be visible
	    WebElement logo = wait.until(ExpectedConditions.visibilityOfElementLocated(ORG_LOGO));
	    js.executeScript("arguments[0].style.border='3px solid purple'", logo);

	    // Verify if the logo is displayed
	    if (logo.isDisplayed()) {
//	        System.out.println("Webpage logo is visible.");
	    } else {
	        throw new RuntimeException("Webpage logo is not visible.");
	    }
	}

	public String verifyPageTitle() {
	    String expectedTitle = "AEPL Sampark_Diet Diagnostic Cloud";	 
	    JavascriptExecutor js = (JavascriptExecutor) driver;
	    // Wait for the title element to be visible
	    WebElement titleElement = wait.until(ExpectedConditions.visibilityOfElementLocated(PROJECT_TITLE));		    
	    js.executeScript("arguments[0].style.border='3px solid purple'", titleElement);
	    // Extract the text of the title element
	    String actualTitle = titleElement.getText();
	    // Verify if the title matches the expected title
	    if (actualTitle.equalsIgnoreCase(expectedTitle)) {
	        System.out.println("Page title is visible and matches: " + actualTitle);
	    } else {
	        throw new RuntimeException("Page title does not match. Expected: " + expectedTitle + ", but found: " + actualTitle);
	    }
		return actualTitle;
	}	
	
	public void clickRefreshButton() {
	    try {
	    	 JavascriptExecutor js = (JavascriptExecutor) driver;
	        // Wait for the refresh button to be visible and clickable
	        WebElement refreshButton = wait.until(ExpectedConditions.elementToBeClickable(REFRESHBTN));
	        js.executeScript("arguments[0].style.border='3px solid purple'", refreshButton);
	        // Click on the refresh button
	        refreshButton.click();
	        Thread.sleep(5000);
//	        System.out.println("Refresh button clicked successfully.");
	    } catch (Exception e) {
	        throw new RuntimeException("Failed to click on the refresh button.", e);
	    }
	}
	
	public void clickNavBarDash() {
		// Wait for the navigation bar links to be visible
		 JavascriptExecutor js = (JavascriptExecutor) driver;
		List<WebElement> navBarLinks = wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(DASHBOARD));
		 js.executeScript("arguments[0].style.border='3px solid purple'", navBarLinks);
		boolean isClicked = false;
		for (WebElement link : navBarLinks) {
			if (link.getText().equalsIgnoreCase("Dashboard")) {
				link.click();
//					System.out.println("Clicked On Element On Nav: " +link.getAccessibleName());
				isClicked = true;
//					break;
			}
		}
		if (!isClicked) {
			throw new RuntimeException("Failed to find and click on 'Dashboard' in the navigation bar.");
		}
	}
	
	public void clickNavBarDeviceUtil() {
		// Wait for the navigation bar links to be visible
		 JavascriptExecutor js = (JavascriptExecutor) driver;
		List<WebElement> navBarLinks = wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(DEVICE_UTILITY));
		 js.executeScript("arguments[0].style.border='3px solid purple'", navBarLinks);
		boolean isClicked = false;
		for (WebElement link : navBarLinks) {
			if (link.getText().equalsIgnoreCase("Device Utility")) {
				link.click();
//					System.out.println("Clicked On Element On Nav: " +link.getAccessibleName());
				isClicked = true;
//					break;
			}
		}
		if (!isClicked) {
			throw new RuntimeException("Failed to find and click on 'Device Utility' in the navigation bar.");
		}
	}
	
	public void clickNavBarUser() {
		// Wait for the navigation bar links to be visible
		 JavascriptExecutor js = (JavascriptExecutor) driver;
		List<WebElement> navBarLinks = wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(USER));
		 js.executeScript("arguments[0].style.border='3px solid purple'", navBarLinks);
		boolean isClicked = false;
		for (WebElement link : navBarLinks) {
			if (link.getText().equalsIgnoreCase("User")) {
				link.click();
//					System.out.println("Clicked On Element On Nav: " +link.getAccessibleName());
				isClicked = true;
//					break;
			}
		}
		if (!isClicked) {
			throw new RuntimeException("Failed to find and click on 'User' in the navigation bar.");
		}
	}
}
