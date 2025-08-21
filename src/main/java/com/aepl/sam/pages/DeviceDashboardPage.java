package com.aepl.sam.pages;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.event.KeyEvent;
import java.time.Duration;
import java.util.List;
import java.util.NoSuchElementException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.ElementClickInterceptedException;
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

public class DeviceDashboardPage extends DeviceDashboardPageLocators {
	private WebDriver driver;
	private WebDriverWait wait;
	private CommonMethods comm;

	private static final Logger logger = LogManager.getLogger(DeviceDashboardPage.class);

	public DeviceDashboardPage(WebDriver driver, WebDriverWait wait, MouseActions action) {
		this.driver = driver;
		this.wait = wait;
		this.comm = new CommonMethods(driver, wait);
	}

	public String clickNavBar() {
		if (driver.findElement(DEVICE_DASHBOARD).isDisplayed() && driver.findElement(DEVICE_DASHBOARD).isEnabled()) {
			wait.until(ExpectedConditions.visibilityOfElementLocated(DEVICE_DASHBOARD)).click();
			return "Link is verified";
		}
		return "No link is visible";
	}

	public String verifyDashPageTitle() throws InterruptedException {
		String expectedTitle = "Device Dashboard";
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("window.scrollTo(0, 0);");
		Thread.sleep(500);

		WebElement titleElement = wait.until(ExpectedConditions.visibilityOfElementLocated(DEVICE_DASHBOARD_TITLE));
		js.executeScript("arguments[0].style.border='3px solid purple'", titleElement);

		String actualTitle = titleElement.getText().trim();
		if (!actualTitle.equalsIgnoreCase(expectedTitle)) {
			throw new RuntimeException(
					"Page title does not match. Expected: " + expectedTitle + ", but found: " + actualTitle);
		}
		return actualTitle;
	}

	public String verifyAndClickKPITotalProDevWithCount() {
		String expectedTitle = "TOTAL PRODUCTION DEVICES";
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("window.scrollBy(0,-1500)");

		try {
			WebElement titleElement = wait
					.until(ExpectedConditions.visibilityOfElementLocated(DEVICE_DASHBOARD_TOTALPRODUCTIONDEVICESKPI));
			WebElement countElement = wait.until(
					ExpectedConditions.visibilityOfElementLocated(DEVICE_DASHBOARD_TOTALPRODUCTIONDEVICESKPICOUNT));
			WebElement tableElement = wait
					.until(ExpectedConditions.visibilityOfElementLocated(DEVICEDASHBOARDKPITABLE));

			js.executeScript("window.scrollBy(0,300)");
			js.executeScript("arguments[0].style.border='3px solid purple'", titleElement);
			js.executeScript("arguments[0].style.border='3px solid cyan'", countElement);
			js.executeScript("arguments[0].style.border='3px solid blue'", tableElement);

			titleElement.click();

			String actualTitle = titleElement.getText().trim();
			String actualCount = countElement.getText().trim();

			if (!actualTitle.equalsIgnoreCase(expectedTitle)) {
				throw new AssertionError("Page title does not match. Expected: '" + expectedTitle + "', but found: '"
						+ actualTitle + "'");
			}

			String tablename = tableElement.getText().trim();

			return "KPI Title: " + actualTitle + ", KPI Count: " + actualCount + ",KPI Table: " + tablename;

		} catch (NoSuchElementException ne) {
			throw new RuntimeException("Element not found: " + DEVICE_DASHBOARD_TOTALPRODUCTIONDEVICESKPI, ne);
		} catch (Exception e) {
			throw new RuntimeException("Unexpected error while verifying KPI title and count.", e);
		}
	}

	public String verifyAndClickKPITotalDisDevWithCount() throws InterruptedException {
		String expectedTitle = "TOTAL DISPATCHED DEVICES";
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("window.scrollTo(0, 0);");
		Thread.sleep(500);

		try {
			WebElement titleElement = wait
					.until(ExpectedConditions.visibilityOfElementLocated(DEVICE_DASHBOARD_TOTALDISPATCHEDDEVICESKPI));
			WebElement countElement = wait.until(
					ExpectedConditions.visibilityOfElementLocated(DEVICE_DASHBOARD_TOTALDISPATCHEDDEVICESKPICOUNT));
			WebElement tableElement = wait
					.until(ExpectedConditions.visibilityOfElementLocated(DEVICEDASHBOARDKPITABLE));

			js.executeScript("arguments[0].style.border='3px solid purple'", titleElement);
			js.executeScript("arguments[0].style.border='3px solid cyan'", countElement);
			js.executeScript("arguments[0].style.border='3px solid blue'", tableElement);

			titleElement.click();
			String actualTitle = titleElement.getText().trim();
			String actualCount = countElement.getText().trim();

			if (!actualTitle.equalsIgnoreCase(expectedTitle)) {
				throw new AssertionError("Page title does not match. Expected: '" + expectedTitle + "', but found: '"
						+ actualTitle + "'");
			}

			String tablename = tableElement.getText().trim();
			return "KPI Title: " + actualTitle + ", KPI Count: " + actualCount + ",KPI Table: " + tablename;
		} catch (NoSuchElementException ne) {
			throw new RuntimeException("Element not found: " + DEVICE_DASHBOARD_TOTALDISPATCHEDDEVICESKPI, ne);
		} catch (Exception e) {
			throw new RuntimeException("Unexpected error while verifying KPI title and count.", e);
		}
	}

	public String verifyAndClickKPITotalInsDevWithCount() {
		String expectedTitle = "TOTAL INSTALLED DEVICES";
		JavascriptExecutor js = (JavascriptExecutor) driver;
		try {
			WebElement titleElement = wait
					.until(ExpectedConditions.visibilityOfElementLocated(DEVICE_DASHBOARD_TOTALINSTALLEDDEVICESKPI));
			WebElement countElement = wait.until(
					ExpectedConditions.visibilityOfElementLocated(DEVICE_DASHBOARD_TOTALINSTALLEDDEVICESKPICOUNT));
			WebElement tableElement = wait
					.until(ExpectedConditions.visibilityOfElementLocated(DEVICEDASHBOARDKPITABLE));

			js.executeScript("arguments[0].style.border='3px solid purple'", titleElement);
			js.executeScript("arguments[0].style.border='3px solid cyan'", countElement);
			js.executeScript("arguments[0].style.border='3px solid blue'", tableElement);

			titleElement.click();
			String actualTitle = titleElement.getText().trim();
			String actualCount = countElement.getText().trim();

			if (!actualTitle.equalsIgnoreCase(expectedTitle)) {
				throw new AssertionError("Page title does not match. Expected: '" + expectedTitle + "', but found: '"
						+ actualTitle + "'");
			}

			String tablename = tableElement.getText().trim();
			return "KPI Title: " + actualTitle + ", KPI Count: " + actualCount + ",KPI Table: " + tablename;
		} catch (NoSuchElementException ne) {
			throw new RuntimeException("Element not found: " + DEVICE_DASHBOARD_TOTALINSTALLEDDEVICESKPI, ne);
		} catch (Exception e) {
			throw new RuntimeException("Unexpected error while verifying KPI title and count.", e);
		}
	}

	public String verifyAndClickKPITotalDiscardDevWithCount() {
		String expectedTitle = "TOTAL DISCARDED DEVICES";
		JavascriptExecutor js = (JavascriptExecutor) driver;
		try {
			WebElement titleElement = wait
					.until(ExpectedConditions.visibilityOfElementLocated(DEVICE_DASHBOARD_TOTALDISCARDEDDEVICESKPI));
			WebElement countElement = wait.until(
					ExpectedConditions.visibilityOfElementLocated(DEVICE_DASHBOARD_TOTALDISCARDEDDEVICESKPICOUNT));
			WebElement tableElement = wait
					.until(ExpectedConditions.visibilityOfElementLocated(DEVICEDASHBOARDKPITABLE));

			js.executeScript("arguments[0].style.border='3px solid purple'", titleElement);
			js.executeScript("arguments[0].style.border='3px solid cyan'", countElement);
			js.executeScript("arguments[0].style.border='3px solid blue'", tableElement);

			titleElement.click();
			String actualTitle = titleElement.getText().trim();
			String actualCount = countElement.getText().trim();

			if (!actualTitle.equalsIgnoreCase(expectedTitle)) {
				throw new AssertionError("Page title does not match. Expected: '" + expectedTitle + "', but found: '"
						+ actualTitle + "'");
			}

			String tablename = tableElement.getText().trim();
			return "KPI Title: " + actualTitle + ", KPI Count: " + actualCount + ",KPI Table: " + tablename;
		} catch (NoSuchElementException ne) {
			throw new RuntimeException("Element not found: " + DEVICE_DASHBOARD_TOTALDISCARDEDDEVICESKPI, ne);
		} catch (Exception e) {
			throw new RuntimeException("Unexpected error while verifying KPI title and count.", e);
		}
	}

	public String clickAndEnterTextInSearchBoxProd() {
		String expectedIMEI = "867409079963166";
		String expectedICCID = "89916431144821180029";
		String expectedUIN = "ACON4IA202200096315";
		try {
			JavascriptExecutor js = (JavascriptExecutor) driver;
			WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));

			List<WebElement> navBarLinks = wait
					.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(DEVICE_DASHBOARD_SEARCHBOX));
			boolean isClicked = false;

			for (WebElement link : navBarLinks) {
				js.executeScript("arguments[0].style.border='5px solid cyan'", link);
				if (link.getAttribute("placeholder").equalsIgnoreCase("Search")
						|| link.getTagName().equalsIgnoreCase("input")) {
					link.click();
					isClicked = true;
					break;
				}
			}
			if (!isClicked) {
				throw new RuntimeException("Failed to find and click the search box.");
			}
			WebElement titleElement = wait
					.until(ExpectedConditions.visibilityOfElementLocated(DEVICE_DASHBOARD_TOTALPRODUCTIONDEVICESKPI));
			titleElement.click();
			Thread.sleep(2000);

			WebElement searchBox = wait.until(ExpectedConditions.elementToBeClickable(DEVICE_DASHBOARD_SEARCHBOX));

			searchBox.clear();
			searchBox.sendKeys(expectedIMEI, Keys.ENTER);
			String enteredIMEI = searchBox.getAttribute("value");

			searchBox.clear();
			searchBox.sendKeys(expectedICCID, Keys.ENTER);
			String enteredICCID = searchBox.getAttribute("value");

			searchBox.clear();
			searchBox.sendKeys(expectedUIN, Keys.ENTER);
			String enteredUIN = searchBox.getAttribute("value");

			return expectedIMEI + " | " + expectedICCID + " | " + expectedUIN;

		} catch (TimeoutException te) {
			throw new RuntimeException("Timeout: Search box not found within the expected wait time.", te);
		} catch (NoSuchElementException ne) {
			throw new RuntimeException("Element not found: Search box is missing.", ne);
		} catch (Exception e) {
			throw new RuntimeException("Unexpected error while interacting with the search box.", e);
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
			List<WebElement> navBarLinks = wait
					.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(DEVICE_DASHBOARD_SEARCHBOX));
			boolean isClicked = false;

			for (WebElement link : navBarLinks) {
				js.executeScript("arguments[0].style.border='5px solid cyan'", link);
				if (link.getAttribute("placeholder").equalsIgnoreCase("Search")
						|| link.getTagName().equalsIgnoreCase("input")) {
					link.click();
					isClicked = true;
					break;
				}
			}
			if (!isClicked) {
				throw new RuntimeException("Failed to find and click the search box.");
			}
			WebElement titleElement = wait
					.until(ExpectedConditions.visibilityOfElementLocated(DEVICE_DASHBOARD_TOTALDISPATCHEDDEVICESKPI));
			titleElement.click();
			Thread.sleep(2000);

			WebElement searchBox = wait.until(ExpectedConditions.elementToBeClickable(DEVICE_DASHBOARD_SEARCHBOX));

			searchBox.clear();
			searchBox.sendKeys(expectedIMEI, Keys.ENTER);
			String enteredIMEI = searchBox.getAttribute("value");

			searchBox.clear();
			searchBox.sendKeys(expectedICCID, Keys.ENTER);
			String enteredICCID = searchBox.getAttribute("value");

			searchBox.clear();
			searchBox.sendKeys(expectedUIN, Keys.ENTER);
			String enteredUIN = searchBox.getAttribute("value");

			return expectedIMEI + " | " + expectedICCID + " | " + expectedUIN;

		} catch (TimeoutException te) {
			throw new RuntimeException("Timeout: Search box not found within the expected wait time.", te);
		} catch (NoSuchElementException ne) {
			throw new RuntimeException("Element not found: Search box is missing.", ne);
		} catch (Exception e) {
			throw new RuntimeException("Unexpected error while interacting with the search box.", e);
		}
	}

	public String clickAndEnterTextInSearchBoxIns() {
		String expectedIMEI = "867409079963166";
		String expectedICCID = "89916431144821180029";
		String expectedUIN = "ACON4IA202200096315";
		try {
			JavascriptExecutor js = (JavascriptExecutor) driver;
			List<WebElement> navBarLinks = wait
					.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(DEVICE_DASHBOARD_SEARCHBOX));
			boolean isClicked = false;

			for (WebElement link : navBarLinks) {
				js.executeScript("arguments[0].style.border='5px solid cyan'", link);
				if (link.getAttribute("placeholder").equalsIgnoreCase("Search")
						|| link.getTagName().equalsIgnoreCase("input")) {
					link.click();
					isClicked = true;
					break;
				}
			}
			if (!isClicked) {
				throw new RuntimeException("Failed to find and click the search box.");
			}
			WebElement titleElement = wait
					.until(ExpectedConditions.visibilityOfElementLocated(DEVICE_DASHBOARD_TOTALINSTALLEDDEVICESKPI));
			titleElement.click();
			Thread.sleep(2000);

			WebElement searchBox = wait.until(ExpectedConditions.elementToBeClickable(DEVICE_DASHBOARD_SEARCHBOX));

			searchBox.clear();
			searchBox.sendKeys(expectedIMEI, Keys.ENTER);
			String enteredIMEI = searchBox.getAttribute("value");

			searchBox.clear();
			searchBox.sendKeys(expectedICCID, Keys.ENTER);
			String enteredICCID = searchBox.getAttribute("value");

			searchBox.clear();
			searchBox.sendKeys(expectedUIN, Keys.ENTER);
			String enteredUIN = searchBox.getAttribute("value");

			return expectedIMEI + " | " + expectedICCID + " | " + expectedUIN;

		} catch (TimeoutException te) {
			throw new RuntimeException("Timeout: Search box not found within the expected wait time.", te);
		} catch (NoSuchElementException ne) {
			throw new RuntimeException("Element not found: Search box is missing.", ne);
		} catch (Exception e) {
			throw new RuntimeException("Unexpected error while interacting with the search box.", e);
		}
	}

	public void clickExportBtn() {
		try {
			WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
			WebElement exportBtn = wait
					.until(ExpectedConditions.visibilityOfElementLocated(DEVICE_DASHBOARD_EXPORTBTN));

			JavascriptExecutor js = (JavascriptExecutor) driver;
			js.executeScript("arguments[0].style.border='3px solid purple'", exportBtn);

			exportBtn.click();

			Thread.sleep(1000);
			Robot robot = new Robot();
			robot.keyPress(KeyEvent.VK_ENTER);
			robot.keyPress(KeyEvent.VK_ENTER);
			robot.keyPress(KeyEvent.VK_TAB);
			robot.keyRelease(KeyEvent.VK_TAB);
			Thread.sleep(500);
			robot.keyPress(KeyEvent.VK_ENTER);
			Thread.sleep(500);
			robot.keyPress(KeyEvent.VK_ENTER);
			robot.keyRelease(KeyEvent.VK_ENTER);

			Thread.sleep(5000);

		} catch (AWTException | InterruptedException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void clickExportBtn2() {
		try {
			JavascriptExecutor js = (JavascriptExecutor) driver;
			WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
			WebElement exportBtn = wait
					.until(ExpectedConditions.visibilityOfElementLocated(DEVICE_DASHBOARD_EXPORTBTN2));

			js.executeScript("window.scrollTo(500, 0);");
			js.executeScript("arguments[0].scrollIntoView(true);", exportBtn);
			Thread.sleep(500);

			js.executeScript("arguments[0].style.border='3px solid purple'", exportBtn);

			try {
				exportBtn.click();
			} catch (ElementClickInterceptedException e) {
				js.executeScript("arguments[0].click();", exportBtn);
			}

			Thread.sleep(1000);
			Robot robot = new Robot();
			robot.keyPress(KeyEvent.VK_ENTER);
			robot.keyRelease(KeyEvent.VK_ENTER);

			robot.keyPress(KeyEvent.VK_TAB);
			robot.keyRelease(KeyEvent.VK_TAB);
			Thread.sleep(500);

			robot.keyPress(KeyEvent.VK_ENTER);
			robot.keyRelease(KeyEvent.VK_ENTER);

			Thread.sleep(500);
			robot.keyPress(KeyEvent.VK_ENTER);
			robot.keyRelease(KeyEvent.VK_ENTER);

			Thread.sleep(5000);

			robot.keyPress(KeyEvent.VK_TAB);
			robot.keyRelease(KeyEvent.VK_TAB);
			Thread.sleep(500);

			robot.keyPress(KeyEvent.VK_ENTER);
			robot.keyRelease(KeyEvent.VK_ENTER);

			Thread.sleep(500);
			robot.keyPress(KeyEvent.VK_ENTER);
			robot.keyRelease(KeyEvent.VK_ENTER);

			Thread.sleep(5000);

		} catch (AWTException | InterruptedException e) {
			e.printStackTrace();
		} catch (Exception e) {
		}
	}

	// ---------------------- Private Utility Methods -----------------------

	private void clickElement(By locator) {
		WebElement element = wait.until(ExpectedConditions.elementToBeClickable(locator));
		highlightElement(element);
		element.click();
	}

	private void fillInputField(By locator, String text) {
		WebElement field = wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
		highlightElement(field);
		field.clear();
		field.sendKeys(text);
	}

	private void highlightElement(WebElement element) {
		comm.highlightElement(element, "solid purple");
	}

	private void scrollDown(int value) {
		((JavascriptExecutor) driver).executeScript("window.scrollBy(0, " + value + ")");
		sleep(500);
	}

	private void sleep(long millis) {
		try {
			Thread.sleep(millis);
		} catch (InterruptedException e) {
			Thread.currentThread().interrupt();
			logger.warn("Sleep interrupted: {}", e.getMessage());
		}
	}
}