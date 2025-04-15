package com.aepl.sam.pages;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.event.KeyEvent;
import java.time.Duration;
import java.util.List;
import java.util.NoSuchElementException;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.aepl.sam.actions.MouseActions;
import com.aepl.sam.locators.DeviceDashboardPageLocators;
import com.aepl.sam.utils.CommonMethods;
import com.google.common.base.Function;

import groovyjarjarantlr4.v4.parse.ANTLRParser.action_return;

public class DeviceDashboardPage extends DeviceDashboardPageLocators {

	private WebDriver driver;
	private WebDriverWait wait;
	private CommonMethods commonMethods;

	public DeviceDashboardPage(WebDriver driver, WebDriverWait wait,MouseActions action) {
		this.driver = driver;
		this.wait = wait;
		this.commonMethods = new CommonMethods(driver, wait);
	}

	public void clickNavBar() {
		// Wait for the navigation bar links to be visible
		JavascriptExecutor js = (JavascriptExecutor) driver;
		List<WebElement> navBarLinks = wait
				.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(DEVICE_DASHBOARD));
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


	
/*
	public void verifyWebpageLogo() {
		// Wait for the logo element to be visible
		WebElement logo = wait.until(ExpectedConditions.visibilityOfElementLocated(PAGE_LOGO));

<<<<<<< HEAD
=======
		// Verify if the logo is displayed
		if (logo.isDisplayed()) {
//		        System.out.println("Webpage logo is visible.");
		} else {
			throw new RuntimeException("Webpage logo is not visible.");
		}
	}

>>>>>>> f4575472028490262b133b85d71691d53979a381

	/*
	 * public void verifyWebpageLogo() { // Wait for the logo element to be visible
	 * WebElement logo =
	 * wait.until(ExpectedConditions.visibilityOfElementLocated(PAGE_LOGO));
	 * 
	 * // Verify if the logo is displayed if (logo.isDisplayed()) { //
	 * System.out.println("Webpage logo is visible."); } else { throw new
	 * RuntimeException("Webpage logo is not visible."); } }
	 * 

	 * /* public void verifyWebpageLogo() { // Wait for the logo element to be
	 * visible WebElement logo =
	 * wait.until(ExpectedConditions.visibilityOfElementLocated(PAGE_LOGO));
	 * 
	 * // Verify if the logo is displayed if (logo.isDisplayed()) { //
	 * System.out.println("Webpage logo is visible."); } else { throw new
	 * RuntimeException("Webpage logo is not visible."); } }
	 * 

<<<<<<< HEAD

	/*
	 * public void verifyWebpageLogo() { // Wait for the logo element to be visible
	 * WebElement logo =
	 * wait.until(ExpectedConditions.visibilityOfElementLocated(PAGE_LOGO));
	 * 
	 * // Verify if the logo is displayed if (logo.isDisplayed()) { //
	 * System.out.println("Webpage logo is visible."); } else { throw new
	 * RuntimeException("Webpage logo is not visible."); } }
	 * 

	 * public String verifyPageTitle() { String expectedTitle =
	 * "AEPL Sampark_Diet Diagnostic Cloud"; // Wait for the title element to be
	 * visible WebElement titleElement =
	 * wait.until(ExpectedConditions.visibilityOfElementLocated(PAGETITLE)); //
	 * Extract the text of the title element String actualTitle =
	 * titleElement.getText().trim(); // Verify if the title matches the expected
	 * title if (actualTitle.equalsIgnoreCase(expectedTitle)) {
	 * System.out.println("Page title is visible and matches: " + actualTitle); }
	 * else { throw new RuntimeException( "Page title does not match. Expected: " +
	 * expectedTitle + ", but found: " + actualTitle); } return actualTitle; }
	 * 
	 * public void clickRefreshButton() { try { // Wait for the refresh button to be
	 * visible and clickable WebElement refreshButton =
	 * wait.until(ExpectedConditions.elementToBeClickable(
	 * DEVICE_DASHBOARD_REFRESHBTN)); // Click on the refresh button
	 * refreshButton.click(); Thread.sleep(5000); //
	 * System.out.println("Refresh button clicked successfully."); } catch
	 * (Exception e) { throw new
	 * RuntimeException("Failed to click on the refresh button.", e); } }
	 */

	public String verifyDashPageTitle() {
		String expectedTitle = "Device Dashboard";
		JavascriptExecutor js = (JavascriptExecutor) driver;
		// Wait for the title element to be visible
		WebElement titleElement = wait.until(ExpectedConditions.visibilityOfElementLocated(DEVICE_DASHBOARD_TITLE));
		js.executeScript("arguments[0].style.border='3px solid purple'", titleElement);
		// Extract the text of the title element
		String actualTitle = titleElement.getText().trim();
		// Verify if the title matches the expected title
		if (actualTitle.equalsIgnoreCase(expectedTitle)) {
			System.out.println("Page Name is visible and matches: " + actualTitle);
		} else {
			throw new RuntimeException(
					"Page title does not match. Expected: " + expectedTitle + ", but found: " + actualTitle);
		}
		return actualTitle;
	}


	public String verifyAndClickKPITotalProDevWithCount() {
		String expectedTitle = "TOTAL PRODUCTION DEVICES";
		JavascriptExecutor js = (JavascriptExecutor) driver;

		try {
			// Wait for the KPI Title element to be visible
			WebElement titleElement = wait
					.until(ExpectedConditions.visibilityOfElementLocated(DEVICE_DASHBOARD_TOTALPRODUCTIONDEVICESKPI));
			WebElement countElement = wait.until(
					ExpectedConditions.visibilityOfElementLocated(DEVICE_DASHBOARD_TOTALPRODUCTIONDEVICESKPICOUNT));
			WebElement tableElement = wait
					.until(ExpectedConditions.visibilityOfElementLocated(DEVICEDASHBOARDKPITABLE));

			// Highlight KPI Title and Count elements
			js.executeScript("arguments[0].style.border='3px solid purple'", titleElement);
			js.executeScript("arguments[0].style.border='3px solid cyan'", countElement);
			js.executeScript("arguments[0].style.border='3px solid blue'", tableElement);

			// Click on the KPI title element
			titleElement.click();
			System.out.println("✅ Clicked on the KPI element.");

			// Extract text from the KPI title
			String actualTitle = titleElement.getText().trim();
			System.out.println("🔹 Extracted KPI Title: " + actualTitle);

			// Extract KPI count
			String actualCount = countElement.getText().trim();
			System.out.println("🔹 Extracted KPI Count: " + actualCount);

			// Verify KPI Title
			if (!actualTitle.equalsIgnoreCase(expectedTitle)) {
				throw new AssertionError("❌ Page title does not match. Expected: '" + expectedTitle + "', but found: '"
						+ actualTitle + "'");
			}

			tableElement.click();
			System.out.println("✅ Clicked on the KPI Table.");

			// Extract KPI table name
			String tablename = tableElement.getText().trim();
			System.out.println("🔹 Extracted KPI Table Name: " + tablename);

			System.out.println("✅ KPI Name is visible and matches: " + actualTitle);
			System.out.println("✅ KPI Count is visible and matches: " + actualCount);
			System.out.println("✅ KPI Table is visible and open: " + tablename);
			// Return combined KPI Title and Count
			return "KPI Title: " + actualTitle + ", KPI Count: " + actualCount + ",KPI Table: " + tablename;

		} catch (NoSuchElementException ne) {
			throw new RuntimeException("🚨 Element not found: " + DEVICE_DASHBOARD_TOTALPRODUCTIONDEVICESKPI, ne);
		} catch (Exception e) {
			throw new RuntimeException("❌ Unexpected error while verifying KPI title and count.", e);
		}
	}

	public String verifyAndClickKPITotalDisDevWithCount() {
		String expectedTitle = "TOTAL DISPATCHED DEVICES";
		JavascriptExecutor js = (JavascriptExecutor) driver;

		try {
			// Wait for the KPI Title element to be visible
			WebElement titleElement = wait
					.until(ExpectedConditions.visibilityOfElementLocated(DEVICE_DASHBOARD_TOTALDISPATCHEDDEVICESKPI));
			WebElement countElement = wait.until(
					ExpectedConditions.visibilityOfElementLocated(DEVICE_DASHBOARD_TOTALDISPATCHEDDEVICESKPICOUNT));
			WebElement tableElement = wait
					.until(ExpectedConditions.visibilityOfElementLocated(DEVICEDASHBOARDKPITABLE));

			// Highlight KPI Title and Count elements
			js.executeScript("arguments[0].style.border='3px solid purple'", titleElement);
			js.executeScript("arguments[0].style.border='3px solid cyan'", countElement);
			js.executeScript("arguments[0].style.border='3px solid blue'", tableElement);

			// Click on the KPI title element
			titleElement.click();
			System.out.println("✅ Clicked on the KPI element.");
			// Extract text from the KPI title
			String actualTitle = titleElement.getText().trim();
			System.out.println("🔹 Extracted KPI Title: " + actualTitle);
			// Extract KPI count
			String actualCount = countElement.getText().trim();
			System.out.println("🔹 Extracted KPI Count: " + actualCount);
			// Verify KPI Title
			if (!actualTitle.equalsIgnoreCase(expectedTitle)) {
				throw new AssertionError("❌ Page title does not match. Expected: '" + expectedTitle + "', but found: '"
						+ actualTitle + "'");
			}

			// Extract KPI table name
			String tablename = tableElement.getText().trim();
			System.out.println("🔹 Extracted KPI Table Name: " + tablename);

			System.out.println("✅ KPI Name is visible and matches: " + actualTitle);
			System.out.println("✅ KPI Count is visible and matches: " + actualCount);
			System.out.println("✅ KPI Table is visible and open: " + tablename);
			// Return combined KPI Title and Count
			return "KPI Title: " + actualTitle + ", KPI Count: " + actualCount + ",KPI Table: " + tablename;
		} catch (NoSuchElementException ne) {
			throw new RuntimeException("🚨 Element not found: " + DEVICE_DASHBOARD_TOTALDISPATCHEDDEVICESKPI, ne);
		} catch (Exception e) {
			throw new RuntimeException("❌ Unexpected error while verifying KPI title and count.", e);
		}
	}

	public String verifyAndClickKPITotalInsDevWithCount() {

	    String expectedTitle = "TOTAL INSTALLED DEVICES";
	    JavascriptExecutor js = (JavascriptExecutor) driver;
	    try {
	        // Wait for the KPI Title element to be visible
	        WebElement titleElement = wait.until(ExpectedConditions.visibilityOfElementLocated(DEVICE_DASHBOARD_TOTALINSTALLEDDEVICESKPI));
	        WebElement countElement = wait.until(ExpectedConditions.visibilityOfElementLocated(DEVICE_DASHBOARD_TOTALINSTALLEDDEVICESKPICOUNT));
	        WebElement tableElement = wait.until(ExpectedConditions.visibilityOfElementLocated(DEVICEDASHBOARDKPITABLE));
	        
	        // Highlight KPI Title and Count elements
	        js.executeScript("arguments[0].style.border='3px solid purple'", titleElement);
	        js.executeScript("arguments[0].style.border='3px solid cyan'", countElement);
	        js.executeScript("arguments[0].style.border='3px solid blue'", tableElement);
	        
	        // Click on the KPI title element
	        titleElement.click();
	        System.out.println("✅ Clicked on the KPI element.");
	        // Extract text from the KPI title
	        String actualTitle = titleElement.getText().trim();
	        System.out.println("🔹 Extracted KPI Title: " + actualTitle);
	        // Extract KPI count
	        String actualCount = countElement.getText().trim();
	        System.out.println("🔹 Extracted KPI Count: " + actualCount);
	        // Verify KPI Title
	        if (!actualTitle.equalsIgnoreCase(expectedTitle)) {
	            throw new AssertionError("❌ Page title does not match. Expected: '" + expectedTitle + "', but found: '" + actualTitle + "'");
	        }
	        
	     // Extract KPI table name
	        String  tablename = tableElement.getText().trim();
	        System.out.println("🔹 Extracted KPI Table Name: " + tablename);
	        
	        System.out.println("✅ KPI Name is visible and matches: " + actualTitle);
	        System.out.println("✅ KPI Count is visible and matches: " + actualCount);
	        System.out.println("✅ KPI Table is visible and open: " + tablename);
	        // Return combined KPI Title and Count
	        return "KPI Title: " + actualTitle + ", KPI Count: " + actualCount;
	    } catch (NoSuchElementException ne) {
	        throw new RuntimeException("🚨 Element not found: " + DEVICE_DASHBOARD_TOTALINSTALLEDDEVICESKPI, ne);
	    } catch (Exception e) {
	        throw new RuntimeException("❌ Unexpected error while verifying KPI title and count.", e);
	    }
	} 
	
	public String verifyAndClickKPITotalDiscardDevWithCount() {
	    String expectedTitle = "TOTAL DISCARDED DEVICES";
	    JavascriptExecutor js = (JavascriptExecutor) driver;
	    try {
	        // Wait for the KPI Title element to be visible
	        WebElement titleElement = wait.until(ExpectedConditions.visibilityOfElementLocated(DEVICE_DASHBOARD_TOTALDISCARDEDDEVICESKPI));
	        WebElement countElement = wait.until(ExpectedConditions.visibilityOfElementLocated(DEVICE_DASHBOARD_TOTALDISCARDEDDEVICESKPICOUNT));
	        WebElement tableElement = wait.until(ExpectedConditions.visibilityOfElementLocated(DEVICEDASHBOARDKPITABLE));
	        
	        // Highlight KPI Title and Count elements
	        js.executeScript("arguments[0].style.border='3px solid purple'", titleElement);
	        js.executeScript("arguments[0].style.border='3px solid cyan'", countElement);
	        js.executeScript("arguments[0].style.border='3px solid blue'", tableElement);
	        
	        // Click on the KPI title element
	        titleElement.click();
	        System.out.println("✅ Clicked on the KPI element.");
	        // Extract text from the KPI title
	        String actualTitle = titleElement.getText().trim();
	        System.out.println("🔹 Extracted KPI Title: " + actualTitle);
	        // Extract KPI count
	        String actualCount = countElement.getText().trim();
	        System.out.println("🔹 Extracted KPI Count: " + actualCount);
	        // Verify KPI Title
	        if (!actualTitle.equalsIgnoreCase(expectedTitle)) {
	            throw new AssertionError("❌ Page title does not match. Expected: '" + expectedTitle + "', but found: '" + actualTitle + "'");
	        }
	        
	     // Extract KPI table name
	        String  tablename = tableElement.getText().trim();
	        System.out.println("🔹 Extracted KPI Table Name: " + tablename);
	        
	        System.out.println("✅ KPI Name is visible and matches: " + actualTitle);
	        System.out.println("✅ KPI Count is visible and matches: " + actualCount);
	        System.out.println("✅ KPI Table is visible and open: " + tablename);
	        // Return combined KPI Title and Count
	        return "KPI Title: " + actualTitle + ", KPI Count: " + actualCount;
	    } catch (NoSuchElementException ne) {
	        throw new RuntimeException("🚨 Element not found: " + DEVICE_DASHBOARD_TOTALDISCARDEDDEVICESKPI, ne);
	    } catch (Exception e) {
	        throw new RuntimeException("❌ Unexpected error while verifying KPI title and count.", e);
	    }
	}
	
	
	public String clickAndEnterTextInSearchBoxProd() {
		String expectedIMEI = "867409079963166";
		String expectedICCID = "89916431144821180029";
		String expectedUIN = "ACON4IA202200096315";
		try {
	        JavascriptExecutor js = (JavascriptExecutor) driver;
	        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
	        // Wait for the search box to be visible
	        List<WebElement> navBarLinks = wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(DEVICE_DASHBOARD_SEARCHBOX));
	        boolean isClicked = false;

	        for (WebElement link : navBarLinks) {
	            // Highlight the search box element
	            js.executeScript("arguments[0].style.border='5px solid cyan'", link);

	            // Ensure it's the correct search box element before clicking
	            if (link.getAttribute("placeholder").equalsIgnoreCase("Search") || link.getTagName().equalsIgnoreCase("input")) {
	                link.click();
	                isClicked = true;
	                System.out.println("✅ Clicked on search box placeholder like: " + link.getAccessibleName());
	                break;
	            }
	        }
	        if (!isClicked) {
	            throw new RuntimeException("🚨 Failed to find and click the search box.");
	        }	        
	        WebElement titleElement = wait.until(ExpectedConditions.visibilityOfElementLocated(DEVICE_DASHBOARD_TOTALPRODUCTIONDEVICESKPI));
	        titleElement.click();
	        Thread.sleep(2000);

	        // Wait for search box to be interactive
	        WebElement searchBox = wait.until(ExpectedConditions.elementToBeClickable(DEVICE_DASHBOARD_SEARCHBOX));
	        
	        // Clear existing text and enter the new input
	        searchBox.clear();
	        
	        searchBox.sendKeys(expectedIMEI, Keys.ENTER);
	        
	        // Extract entered text from search box
	        String enteredIMEI = searchBox.getAttribute("value");
	        System.out.println("Extracted IMEI : " + enteredIMEI);
	        	        
	        searchBox.clear();
	        searchBox.sendKeys(Keys.ENTER);
	        searchBox.sendKeys(expectedICCID, Keys.ENTER);
	        String enteredICCID = searchBox.getAttribute("value");
	        System.out.println("Extracted ICCID : " + enteredICCID);
	        
	        searchBox.clear();
	        searchBox.sendKeys(expectedUIN, Keys.ENTER);	
	        searchBox.sendKeys(Keys.ENTER);
	        searchBox.clear();
	        searchBox.sendKeys(Keys.ENTER);
	        
	        String enteredUIN = searchBox.getAttribute("value");
	        System.out.println("Extracted UIN : " + enteredUIN);
	        
	        System.out.println("✅ Entered IMEI & Extracted IMEI Matches with: " + expectedIMEI);
	        System.out.println("✅ Entered IMEI & Extracted ICCID Matches with: " + expectedICCID);
	        System.out.println("✅ Entered IMEI & Extracted UIN Matches with: " + expectedUIN);
	        
	        // Return the values in an object
	        return expectedIMEI + " | " + expectedICCID + " | " + expectedUIN;

	    } catch (TimeoutException te) {
	        throw new RuntimeException("🚨 Timeout: Search box not found within the expected wait time.", te);
	    } catch (NoSuchElementException ne) {
	        throw new RuntimeException("🚨 Element not found: Search box is missing.", ne);
	    } catch (Exception e) {
	        throw new RuntimeException("❌ Unexpected error while interacting with the search box.", e);
	    }
		}
		
	
	public void clickExportBtn1() {
	    
	    	WebElement titleElement = wait
					.until(ExpectedConditions.visibilityOfElementLocated(DEVICE_DASHBOARD_TOTALDISPATCHEDDEVICESKPI));
	    titleElement.click();
	}
	
	public String clickAndEnterTextInSearchBoxdis() {
	String expectedIMEI = "867409079963166";
	String expectedICCID = "89916431144821180029";
	String expectedUIN = "ACON4SA212240006474";
	try {
        JavascriptExecutor js = (JavascriptExecutor) driver;

        // Wait for the search box to be visible
        List<WebElement> navBarLinks = wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(DEVICE_DASHBOARD_SEARCHBOX));
        boolean isClicked = false;

        for (WebElement link : navBarLinks) {
            // Highlight the search box element
            js.executeScript("arguments[0].style.border='5px solid cyan'", link);

            // Ensure it's the correct search box element before clicking
            if (link.getAttribute("placeholder").equalsIgnoreCase("Search") || link.getTagName().equalsIgnoreCase("input")) {
                link.click();
                isClicked = true;
                System.out.println("✅ Clicked on search box placeholder like: " + link.getAccessibleName());
                break;
            }
        }
        if (!isClicked) {
            throw new RuntimeException("🚨 Failed to find and click the search box.");
        }	        
        WebElement titleElement = wait.until(ExpectedConditions.visibilityOfElementLocated(DEVICE_DASHBOARD_TOTALDISPATCHEDDEVICESKPI));
        titleElement.click();
        Thread.sleep(2000);

        // Wait for search box to be interactive
        WebElement searchBox = wait.until(ExpectedConditions.elementToBeClickable(DEVICE_DASHBOARD_SEARCHBOX));
        
        // Clear existing text and enter the new input
        searchBox.clear();
        searchBox.sendKeys(expectedIMEI, Keys.ENTER);
        
        // Extract entered text from search box
        String enteredIMEI = searchBox.getAttribute("value");
        Thread.sleep(2000);
        System.out.println("Extracted IMEI : " + enteredIMEI);
        	        
        searchBox.clear();
        searchBox.sendKeys(Keys.ENTER);
        searchBox.sendKeys(expectedICCID, Keys.ENTER);
        Thread.sleep(2000);
        String enteredICCID = searchBox.getAttribute("value");
        System.out.println("Extracted ICCID : " + enteredICCID);
        
        searchBox.clear();
        searchBox.sendKeys(Keys.ENTER);
        Thread.sleep(3000);
        searchBox.sendKeys(expectedUIN, Keys.ENTER);
        searchBox.clear();      
        searchBox.sendKeys(Keys.ENTER);
       
        String enteredUIN = searchBox.getAttribute("value");
        System.out.println("Extracted UIN : " + enteredUIN);
        
        System.out.println("✅ Entered IMEI & Extracted IMEI Matches with: " + expectedIMEI);
        System.out.println("✅ Entered IMEI & Extracted ICCID Matches with: " + expectedICCID);
        System.out.println("✅ Entered IMEI & Extracted UIN Matches with: " + expectedUIN);
        
        // Return the values in an object
        return expectedIMEI + " | " + expectedICCID + " | " + expectedUIN;

    } catch (TimeoutException te) {
        throw new RuntimeException("🚨 Timeout: Search box not found within the expected wait time.", te);
    } catch (NoSuchElementException ne) {
        throw new RuntimeException("🚨 Element not found: Search box is missing.", ne);
    } catch (Exception e) {
        throw new RuntimeException("❌ Unexpected error while interacting with the search box.", e);
    }
	}

	public String clickAndEnterTextInSearchBoxIns() {
        String expectedIMEI = "867409079963166";
        String expectedICCID = "89916431144821180029";
        String expectedUIN = "ACON4IA202200096315";
        try {
            JavascriptExecutor js = (JavascriptExecutor) driver;

            // Wait for the search box to be visible
            List<WebElement> navBarLinks = wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(DEVICE_DASHBOARD_SEARCHBOX));
            boolean isClicked = false;

            for (WebElement link : navBarLinks) {
                // Highlight the search box element
                js.executeScript("arguments[0].style.border='5px solid cyan'", link);

                // Ensure it's the correct search box element before clicking
                if (link.getAttribute("placeholder").equalsIgnoreCase("Search") || link.getTagName().equalsIgnoreCase("input")) {
                    link.click();
                    isClicked = true;
                    System.out.println("✅ Clicked on search box placeholder like: " + link.getAccessibleName());
                    break;
                }
            }
            if (!isClicked) {
                throw new RuntimeException("🚨 Failed to find and click the search box.");
            }	        
            WebElement titleElement = wait.until(ExpectedConditions.visibilityOfElementLocated(DEVICE_DASHBOARD_TOTALINSTALLEDDEVICESKPI));
            titleElement.click();
            Thread.sleep(2000);

            // Wait for search box to be interactive
            WebElement searchBox = wait.until(ExpectedConditions.elementToBeClickable(DEVICE_DASHBOARD_SEARCHBOX));
            
            // Clear existing text and enter the new input
            searchBox.clear();
            
            searchBox.sendKeys(expectedIMEI, Keys.ENTER);
            
            // Extract entered text from search box
            String enteredIMEI = searchBox.getAttribute("value");
            System.out.println("Extracted IMEI : " + enteredIMEI);
            	        
            searchBox.clear();
            searchBox.sendKeys(Keys.ENTER);
            searchBox.sendKeys(expectedICCID, Keys.ENTER);
            String enteredICCID = searchBox.getAttribute("value");
            System.out.println("Extracted ICCID : " + enteredICCID);
            
            searchBox.clear();
            searchBox.sendKeys(Keys.ENTER);
            searchBox.sendKeys(expectedUIN, Keys.ENTER);
            String enteredUIN = searchBox.getAttribute("value");
            System.out.println("Extracted UIN : " + enteredUIN);
            
            System.out.println("✅ Entered IMEI & Extracted IMEI Matches with: " + expectedIMEI);
            System.out.println("✅ Entered IMEI & Extracted ICCID Matches with: " + expectedICCID);
            System.out.println("✅ Entered IMEI & Extracted UIN Matches with: " + expectedUIN);
            
            // Return the values in an object
            return expectedIMEI + " | " + expectedICCID + " | " + expectedUIN;

        } catch (TimeoutException te) {
            throw new RuntimeException("🚨 Timeout: Search box not found within the expected wait time.", te);
        } catch (NoSuchElementException ne) {
            throw new RuntimeException("🚨 Element not found: Search box is missing.", ne);
        } catch (Exception e) {
            throw new RuntimeException("❌ Unexpected error while interacting with the search box.", e);
        }
    	}


	
	public void clickExportBtn() {
	    try {
	        // Wait for the Export button to be visible
//	        WebDriverWait wait = new WebDriverWait(driver, Duration.ofMillis(5000));
	        WebElement exportBtn = wait.until(ExpectedConditions.visibilityOfElementLocated(DEVICE_DASHBOARD_EXPORTBTN));
	        
	        // Highlight the Export button (optional)
	        JavascriptExecutor js = (JavascriptExecutor) driver;
	        js.executeScript("arguments[0].style.border='3px solid purple'", exportBtn);
	        
	        // Click the Export button
	        exportBtn.click();
	        System.out.println("✅ Clicked on Export button successfully.");

	        // Press Enter using Robot to handle any pop-up
	        Thread.sleep(1000); // Small delay to ensure the pop-up appears
	        Robot robot = new Robot();
	        robot.keyPress(KeyEvent.VK_ENTER);
	        robot.keyPress(KeyEvent.VK_ENTER);
	              
	        robot.keyPress(KeyEvent.VK_TAB);
	        robot.keyRelease(KeyEvent.VK_TAB);
	        Thread.sleep(500);
	        robot.keyPress(KeyEvent.VK_ENTER);
	        
	        
	        // Press TAB to move to "Save" (if needed based on system)
//	        robot.keyPress(KeyEvent.VK_TAB);
//	        robot.keyPress(KeyEvent.VK_ENTER);
//	        robot.keyRelease(KeyEvent.VK_TAB);
	        Thread.sleep(500);

	        // Press ENTER to confirm Save
	        robot.keyPress(KeyEvent.VK_ENTER);
	        robot.keyRelease(KeyEvent.VK_ENTER);

	        System.out.println("💾 Pressed Enter to click Save on download popup.");
	      
	        System.out.println("✅ Pressed Enter on popup successfully.");
	        Thread.sleep(5000); // Adjust based on download time
	        System.out.println("📥 File download should be triggered now.");
	        
	    } catch (AWTException e) {
	        System.err.println("❌ Robot class failed to initialize.");
	        e.printStackTrace();
	    } catch (InterruptedException e) {
	        System.err.println("❌ Thread interrupted during sleep.");
	        e.printStackTrace();
	    } catch (Exception e) {
	        System.err.println("❌ Unexpected error occurred while clicking Export button.");
	        e.printStackTrace();
	    }
	}


}
//		public void deviceDetails() {
//			Map<String, Map<String, List<String>>> deviceDetails = new HashMap<>();
//			
//			deviceDetails.put("Device Details", new Map<"DevDet_KPI", Arrays.asList(null)>());
//		}
