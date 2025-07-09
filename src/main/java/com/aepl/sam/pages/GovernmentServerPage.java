package com.aepl.sam.pages;

import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.datatransfer.StringSelection;
import java.awt.event.KeyEvent;
import java.time.Duration;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.aepl.sam.actions.CalendarActions;
import com.aepl.sam.actions.MouseActions;
import com.aepl.sam.constants.Constants;
import com.aepl.sam.locators.GovernmentServerPageLocators;
import com.aepl.sam.utils.CommonMethods;
import com.aepl.sam.utils.ConfigProperties;

public class GovernmentServerPage extends GovernmentServerPageLocators {

	private static final Logger logger = LogManager.getLogger(GovernmentServerPage.class);

	private WebDriver driver;
	private WebDriverWait wait;
	private CalendarActions calAct;
	private LoginPage loginPage;
	private CommonMethods comm;

	private String randomStateName;
	private String randomStateAbr;

	public GovernmentServerPage(WebDriver driver, WebDriverWait wait, MouseActions action) {
		this.driver = driver;
		this.wait = wait;
		this.calAct = new CalendarActions(this.driver, this.wait);
		this.loginPage = new LoginPage(driver, wait);
		this.comm = new CommonMethods(driver, wait);
	}

	public String navBarLink() {
		try {
			WebElement deviceUtil = wait.until(ExpectedConditions.visibilityOfElementLocated(DEVICE_UTILITY));
			comm.highlightElement(deviceUtil, "solid purple");
			deviceUtil.click();

			WebElement govServer = wait.until(ExpectedConditions.visibilityOfElementLocated(GOVERNMENT_NAV_LINK));
			Thread.sleep(100);
			govServer.click();
			logger.info("Navigated to Government Server page.");
		} catch (Exception e) {
			logger.error("Error in navBarLink: {}", e.getMessage());
		}
		return driver.getCurrentUrl();
	}

	public String backButton() {
		try {
			WebElement element = wait.until(ExpectedConditions.visibilityOfElementLocated(BACK_BUTTON));
			comm.highlightElement(element, "solid purple");
			element.click();
			Thread.sleep(10);
			logger.info("Clicked on back button: {}", element.getText());
		} catch (Exception e) {
			logger.error("Error in backButton: {}", e.getMessage());
		}
		return navBarLink();
	}

	public String refreshButton() {
		try {
			WebElement refreshBtn = wait.until(ExpectedConditions.elementToBeClickable(REFRESH_BUTTON));
			JavascriptExecutor js = (JavascriptExecutor) driver;
			js.executeScript("arguments[0].style.border = 'solid purple'", refreshBtn);
			Thread.sleep(20);
			refreshBtn.click();

			WebElement page_title = wait.until(ExpectedConditions.visibilityOfElementLocated(PAGE_TITLE));
			String pageTitle = page_title.getText();
			logger.info("Page refreshed, title: {}", pageTitle);
			return pageTitle;
		} catch (Exception e) {
			logger.error("Error in refreshButton: {}", e.getMessage());
		}
		return "No Data Found!!!";
	}

	public String addGovernmentServer() {
		try {
			JavascriptExecutor js = (JavascriptExecutor) driver;
			js.executeScript("window.scrollTo(0, 0);");
			WebElement addGovButton = wait.until(ExpectedConditions.elementToBeClickable(ADD_GOV_SER));
			comm.highlightElement(addGovButton, "solid purple");
			Thread.sleep(500);
			addGovButton.click();

			WebElement componentTitle = driver.findElement(COMPONENT_TITLE);
			logger.info("Navigated to Add Government Server form.");
			return componentTitle.getText();
		} catch (Exception e) {
			logger.error("Error adding government server: {}", e.getMessage());
		}
		return "Failed to click the add government server button or navigate to the next page.";
	}

	public String manageGovServer(String actionType) {
		JavascriptExecutor js = (JavascriptExecutor) driver;
		try {
			if (actionType.equalsIgnoreCase("add")) {
				randomStateName = comm.generateRandomString(5);
				randomStateAbr = comm.generateRandomString(3);
			}

			fillGovServerForm(actionType, randomStateName, randomStateAbr);
			js.executeScript("window.scrollTo(0, document.body.scrollHeight);");

			WebElement button = wait.until(
					ExpectedConditions.elementToBeClickable(actionType.equalsIgnoreCase("add") ? SUBMIT : UPDATE));
			comm.highlightElement(button, "solid purple");
			button.click();

			WebElement toast = wait.until(ExpectedConditions.visibilityOfElementLocated(TOAST_MSG));
			String message = toast.getText();
			logger.info("Manage government server result: {}", message);
			return message;
		} catch (Exception e) {
			logger.error("Error in manageGovServer: {}", e.getMessage());
			return "Error: " + e.getMessage();
		}
	}

	private void fillGovServerForm(String actionType, String stateName, String stateAbr) {
		boolean isUpdate = actionType.equalsIgnoreCase("update");

		try {
			if (!isUpdate) {
				driver.findElement(STATE).sendKeys(stateName);
				driver.findElement(STATE_ABR).sendKeys(stateAbr);
			}

			WebElement ip1 = driver.findElement(GOV_IP1);
			if (isUpdate)
				ip1.clear();
			ip1.sendKeys(isUpdate ? "255.255.255.001" : "255.255.255.255");

			WebElement port1 = driver.findElement(GOV_PORT1);
			if (isUpdate)
				port1.clear();
			port1.sendKeys(isUpdate ? "9999" : "8888");

			WebElement ip2 = driver.findElement(GOV_IP2);
			if (isUpdate)
				ip2.clear();
			ip2.sendKeys(isUpdate ? "255.255.255.001" : "255.255.255.255");

			WebElement port2 = driver.findElement(GOV_PORT2);
			if (isUpdate)
				port2.clear();
			port2.sendKeys(isUpdate ? "6666" : "7777");

			WebElement enabled = driver.findElement(STATE_ENABLED);
			if (isUpdate)
				enabled.clear();
			enabled.sendKeys(isUpdate ? "FALSE" : "TRUE");

			logger.debug("Filled form with state: {}, abbreviation: {}", stateName, stateAbr);
		} catch (Exception e) {
			logger.error("Error filling government server form: {}", e.getMessage());
		}
	}

	public void searchGovServer(String name) {
		try {
			WebElement search = driver.findElement(SEARCH_BOX_INPUT);
			search.clear();
			search.sendKeys(name);
			Thread.sleep(500);
			driver.findElement(SEARCH_BOX_BTN).click();
			logger.info("Searched for government server: {}", name);
		} catch (Exception e) {
			logger.error("Error searching government server: {}", e.getMessage());
		}
	}

	public boolean searchAndView() {
		try {
			wait.until(ExpectedConditions.urlToBe(Constants.GOV_LINK));

			WebElement search = driver.findElement(SEARCH_BOX_INPUT);
			comm.highlightElement(search, "solid purple");

			search.sendKeys(randomStateName);

			WebElement searchBtn = driver.findElement(SEARCH_BOX_BTN);
			comm.highlightElement(searchBtn, "solid purple");
			searchBtn.click();

			Thread.sleep(500);
			driver.findElement(EYE_ICON).click();

			Thread.sleep(500);
			logger.info("Search and view completed for: {}", randomStateName);
			return true;
		} catch (Exception e) {
			logger.error("Error in searchAndView: {}", e.getMessage());
			return false;
		}
	}

	public boolean addFirmware() {
		try {
			JavascriptExecutor js = (JavascriptExecutor) driver;
			js.executeScript("window.scrollTo(0, document.body.scrollHeight);");

			driver.findElement(ADD_FIRM).click();
			driver.findElement(FRM_NAME).sendKeys(Constants.FIRMWARE);
			driver.findElement(FRM_DSC).sendKeys("This is an auto generated desc from selenium");

			driver.findElement(FILE_UPLOAD).click();
			Thread.sleep(500);

			StringSelection selection = new StringSelection(
					"D:\\Sampark_Automation\\SAM_AUTO\\src\\test\\resources\\SampleUpload\\TCP01.bin");
			Toolkit.getDefaultToolkit().getSystemClipboard().setContents(selection, null);
			Robot robot = new Robot();
			Thread.sleep(500);
			robot.keyPress(KeyEvent.VK_CONTROL);
			robot.keyPress(KeyEvent.VK_V);
			robot.keyRelease(KeyEvent.VK_V);
			robot.keyRelease(KeyEvent.VK_CONTROL);
			Thread.sleep(500);
			robot.keyPress(KeyEvent.VK_ENTER);
			robot.keyRelease(KeyEvent.VK_ENTER);

			LocalDateTime date = LocalDateTime.now();
			String currentDate = String.format("%02d-%02d-%04d", date.getDayOfMonth(), date.getMonthValue(),
					date.getYear());
			calAct.selectDate(CAL_BTN, currentDate);

			selectManager(QA_MANAGER_SELECT, Constants.QA_MAN);
			selectManager(SOFT_MANAGER_SELECT, Constants.SOFT_MAN);

			js.executeScript("window.scrollTo(0, document.body.scrollHeight);");
			Thread.sleep(500);
			driver.findElement(SUBMIT).click();

			logger.info("Firmware added successfully.");
			driver.navigate().back();
			return true;
		} catch (Exception e) {
			logger.error("Error in addFirmware: {}", e.getMessage());
			return false;
		}
	}

	private void selectManager(By dropdown, String name) {
		try {
			WebElement element = wait.until(ExpectedConditions.visibilityOfElementLocated(dropdown));
			element.click();
			List<WebElement> options = wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(DRP_OPTIONS));
			for (WebElement option : options) {
				if (option.getText().trim().equals(name)) {
					option.click();
					Thread.sleep(500);
					break;
				}
			}
			logger.debug("{} selected from dropdown", name);
		} catch (Exception e) {
			logger.error("Manager selection failed for {}: {}", name, e.getMessage());
		}
	}

	public String deleteGovServer() {
		try {
			searchGovServer(randomStateName);
			driver.findElement(DELETE_ICON).click();

			WebDriverWait alertWait = new WebDriverWait(driver, Duration.ofSeconds(5));
			Alert alert = alertWait.until(ExpectedConditions.alertIsPresent());
			alert.accept();

			WebElement toast = wait.until(ExpectedConditions.visibilityOfElementLocated(TOAST_MSG));
			String msg = toast.getText();
			logger.info("Deleted government server. Message: {}", msg);

			driver.navigate().refresh();
			return msg;
		} catch (Exception e) {
			logger.error("Error deleting government server: {}", e.getMessage());
			return "Deletion failed.";
		}
	}

	public boolean waitForApprovalMultipleWindows() {
		try {
			JavascriptExecutor js = (JavascriptExecutor) driver;
			js.executeScript("window.open()");
			ArrayList<String> tabs = new ArrayList<>(driver.getWindowHandles());

			driver.switchTo().window(tabs.get(1));
			driver.get(Constants.BASE_URL);
			loginAsManager("QA Manager");

			js.executeScript("window.open()");
			tabs = new ArrayList<>(driver.getWindowHandles());
			driver.switchTo().window(tabs.get(2));
			driver.get(Constants.BASE_URL);
			loginAsManager("Software Manager");

			driver.close();
			driver.switchTo().window(tabs.get(1));
			driver.close();
			driver.switchTo().window(tabs.get(0));

			logger.info("Approval check completed in multiple windows.");
			return true;
		} catch (Exception e) {
			logger.error("Approval waiting failed: {}", e.getMessage());
			return false;
		}
	}

	public void loginAsManager(String role) {
		if (role.equalsIgnoreCase("QA Manager")) {
			loginPage.enterUsername(ConfigProperties.getProperty("qa_man"))
					.enterPassword(ConfigProperties.getProperty("qa_pass"));
		} else if (role.equalsIgnoreCase("Software Manager")) {
			loginPage.enterUsername(ConfigProperties.getProperty("soft_man"))
					.enterPassword(ConfigProperties.getProperty("soft_pass"));
		}
		loginPage.clickLogin();
		logger.info("{} logged in successfully.", role);
	}
}
