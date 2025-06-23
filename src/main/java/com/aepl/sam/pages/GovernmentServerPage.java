package com.aepl.sam.pages;

import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.datatransfer.StringSelection;
import java.awt.event.KeyEvent;
import java.time.Duration;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

import org.openqa.selenium.Alert;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.NoAlertPresentException;
import org.openqa.selenium.TimeoutException;
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
		this.loginPage = new LoginPage(driver, wait, null);
		this.comm = new CommonMethods(driver, wait);
	}

	public String navBarLink() {
		try {
			WebElement deviceUtil = wait.until(ExpectedConditions.visibilityOfElementLocated(DEVICE_UTILITY));
			comm.highlightElement(deviceUtil, "GREEN");
			deviceUtil.click();

			WebElement govServer = wait.until(ExpectedConditions.visibilityOfElementLocated(GOVERNMENT_NAV_LINK));
			Thread.sleep(100);
			govServer.click();
		} catch (Exception e) {
			e.getLocalizedMessage();
		}
		return driver.getCurrentUrl();
	}

	// check the back button
	public String backButton() {
		try {
			WebElement element = wait.until(ExpectedConditions.visibilityOfElementLocated(BACK_BUTTON));

			comm.highlightElement(element, "GREEN");

			element.click();
			Thread.sleep(10);

			System.out.println("Clicked on back button : " + element.getText());

		} catch (Exception e) {
			e.getLocalizedMessage();
		}
		// calling again to visit that page.
		return navBarLink();
	}

	// check the refresh button
	public String refreshButton() {
		try {
			WebElement refreshBtn = wait.until(ExpectedConditions.elementToBeClickable(REFRESH_BUTTON));

			JavascriptExecutor js = (JavascriptExecutor) driver;
			js.executeScript("arguments[0].style.border = 'red'", refreshBtn);

			Thread.sleep(20);

			refreshBtn.click();

			WebElement page_title = wait.until(ExpectedConditions.visibilityOfElementLocated(PAGE_TITLE));
			String pageTitle = page_title.getText();
			return pageTitle;

		} catch (Exception e) {
			e.getLocalizedMessage();
		}
		return "No Data Found!!!";
	}

	// add gov button
	public String addGovernmentServer() {
		try {
			JavascriptExecutor js = (JavascriptExecutor) driver;
			js.executeScript("window.scrollTo(0, 0);");

			WebElement addGovButton = wait.until(ExpectedConditions.elementToBeClickable(ADD_GOV_SER));
			comm.highlightElement(addGovButton, "Violet");
			Thread.sleep(500);

			addGovButton.click();

			WebElement componentTitle = driver.findElement(COMPONENT_TITLE);
			return componentTitle.getText();
		} catch (Exception e) {
			System.err.println("An error occurred while adding the government server: " + e.getMessage());
		}
		return "Failed to click the add government server button or navigate to the next page.";
	}

//	public String manageGovServer(String actionType) {
//		randomStateName = comm.generateRandomString(5);
//		String randomStateAbr = comm.generateRandomString(3);
//		JavascriptExecutor js = (JavascriptExecutor) driver;
//		
//		try {
//			if (actionType.equalsIgnoreCase("add")) {
//				WebElement state = driver.findElement(STATE);
//				state.sendKeys(randomStateName);
//				
//				WebElement stateAbr = driver.findElement(STATE_ABR);
//				stateAbr.sendKeys(randomStateAbr);
//				
//				WebElement ip1 = driver.findElement(GOV_IP1);
//				ip1.sendKeys("255.255.255.255");
//				
//				WebElement port1 = driver.findElement(GOV_PORT1);
//				port1.sendKeys("8888");
//				
//				WebElement ip2 = driver.findElement(GOV_IP2);
//				ip2.sendKeys("255.255.255.255");
//				
//				WebElement port2 = driver.findElement(GOV_PORT2);
//				port2.sendKeys("7777");
//				
//				WebElement stateEnabled = driver.findElement(STATE_ENABLED);
//				stateEnabled.sendKeys("TRUE");
//
//				js.executeScript("window.scrollTo(0, document.body.scrollHeight);");
//				Thread.sleep(500);
//
//				WebElement submit = wait.until(ExpectedConditions.elementToBeClickable(SUBMIT));
//				comm.highlightElement(submit, "VIOLET");
//				submit.click();
//
//				WebElement confirmationToastMsg = wait.until(ExpectedConditions.visibilityOfElementLocated(TOAST_MSG));
//				return confirmationToastMsg.getText();
//			} else if (actionType.equalsIgnoreCase("update")) {
//				WebElement state = driver.findElement(STATE);
//				state.clear();
//				state.sendKeys(randomStateName + "Updated");
//				
//				WebElement stateAbr = driver.findElement(STATE_ABR);
//				stateAbr.clear();
//				stateAbr.sendKeys(randomStateAbr + "Updated");
//				
//				WebElement ip1 = driver.findElement(GOV_IP1);
//				ip1.clear();
//				ip1.sendKeys("255.255.255.001");
//				
//				WebElement port1 = driver.findElement(GOV_PORT1);
//				port1.clear();
//				port1.sendKeys("9999");
//				
//				WebElement ip2 = driver.findElement(GOV_IP2);
//				ip2.clear();
//				ip2.sendKeys("255.255.255.001");
//				
//				WebElement port2 = driver.findElement(GOV_PORT2);
//				port2.clear();
//				port2.sendKeys("6666");
//				
//				WebElement stateEnabled = driver.findElement(STATE_ENABLED);
//				stateEnabled.clear();
//				stateEnabled.sendKeys("FALSE");
//
//				js.executeScript("window.scrollTo({ top: document.body.scrollHeight, behavior: 'smooth' });");
//
//				WebElement update = wait.until(ExpectedConditions.elementToBeClickable(UPDATE));
//				update.click();
//				
//				Thread.sleep(500);
//				
//				WebElement confirmationToastMsg = wait.until(ExpectedConditions.visibilityOfElementLocated(TOAST_MSG));
//				return confirmationToastMsg.getText();
//			} else {
//				System.out.println("Invalid actionType: " + actionType);
//				return "Invalid action type";
//			}
//		} catch (Exception e) {
//			e.printStackTrace();
//			return "Error: " + e.getMessage();
//		}
//	}

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
			comm.highlightElement(button, "VIOLET");
			button.click();

			WebElement toast = wait.until(ExpectedConditions.visibilityOfElementLocated(TOAST_MSG));
			return toast.getText();

		} catch (Exception e) {
			e.printStackTrace();
			return "Error: " + e.getMessage();
		}
	}

	private void fillGovServerForm(String actionType, String stateName, String stateAbr) {
		boolean isUpdate = actionType.equalsIgnoreCase("update");

		if (!isUpdate) {
			WebElement state = driver.findElement(STATE);
			state.sendKeys(stateName);

			WebElement stateAbbr = driver.findElement(STATE_ABR);
			stateAbbr.sendKeys(stateAbr);
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
	}

	public void searchGovServer(String name) {
		try {
			WebElement search = driver.findElement(SEARCH_BOX_INPUT);
			search.clear();
			search.sendKeys(name);

			Thread.sleep(500);

			WebElement searchBtn = driver.findElement(SEARCH_BOX_BTN);
			searchBtn.click();
		} catch (Exception e) {
			System.out.println(e);
		}
	}

	// Search And View
	public boolean searchAndView() {
		try {
			wait.until(ExpectedConditions.urlToBe(Constants.GOV_LINK));

			WebElement search = driver.findElement(SEARCH_BOX_INPUT);
			comm.highlightElement(search, "purple");

			System.out.println("State in search " + randomStateName);
			search.sendKeys(randomStateName);
			System.out.println("State in search " + randomStateName);

			WebElement searchBtn = driver.findElement(SEARCH_BOX_BTN);
			comm.highlightElement(searchBtn, "purple");
			searchBtn.click();

			Thread.sleep(500);

			WebElement viewIcon = driver.findElement(EYE_ICON);
			viewIcon.click();

			Thread.sleep(500);

			System.out.println("Search and view operation completed successfully.");
			return true;
		} catch (Exception e) {
			System.out.println("Error occurred during search and view: " + e.getMessage());
			return false;
		}
	}

	public boolean addFirmware() {
		try {
			JavascriptExecutor js = (JavascriptExecutor) driver;
			js.executeScript("window.scrollTo(0, document.body.scrollHeight);");

			WebElement addFirmwareButton = wait.until(ExpectedConditions.elementToBeClickable(ADD_FIRM));
			comm.highlightElement(addFirmwareButton, "GREEN");
			addFirmwareButton.click();

			WebElement firmName = driver.findElement(FRM_NAME);
			firmName.sendKeys(Constants.FIRMWARE);

			WebElement firmDesc = driver.findElement(FRM_DSC);
			firmDesc.sendKeys("This is an auto generated desc from selenium");

			// Upload the file
			WebElement file = driver.findElement(FILE_UPLOAD);
			file.click();
			Thread.sleep(500);

			StringSelection selection = new StringSelection(
					"D:\\Sampark_Automation\\SAM_AUTO\\src\\test\\resources\\SampleUpload\\TCP01.bin");
			Toolkit.getDefaultToolkit().getSystemClipboard().setContents(selection, null);

			Robot fileHandler = new Robot();
			Thread.sleep(500);

			fileHandler.keyPress(KeyEvent.VK_CONTROL);
			fileHandler.keyPress(KeyEvent.VK_V);
			fileHandler.keyRelease(KeyEvent.VK_V);
			fileHandler.keyRelease(KeyEvent.VK_CONTROL);
			Thread.sleep(500);
			fileHandler.keyPress(KeyEvent.VK_ENTER);
			fileHandler.keyRelease(KeyEvent.VK_ENTER);

			// To select the date from the calendar
			LocalDateTime date = LocalDateTime.now();
			int day = date.getDayOfMonth();
			int month = date.getMonthValue();
			int year = date.getYear();
			String currentDate = String.format("%02d-%02d-%04d", day, month, year);

			calAct.selectDate(CAL_BTN, currentDate);

			js.executeScript("window.scrollTo(0, document.body.scrollHeight);");

			// Qa manager selection
			WebElement Qa = wait.until(ExpectedConditions.visibilityOfElementLocated(QA_MANAGER_SELECT));
			Qa.click();

			// Wait for options to appear
			List<WebElement> qaDropOptions = wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(DRP_OPTIONS));

			for (WebElement option : qaDropOptions) {
				if (option.getText().trim().equals(Constants.QA_MAN)) {
					option.click();
					Thread.sleep(500); // Optional UI sync
					break;
				}
			}

			// Software manager selection
			WebElement soft = wait.until(ExpectedConditions.visibilityOfElementLocated(SOFT_MANAGER_SELECT));
			soft.click();

			// Wait for options to appear
			List<WebElement> softDropOptions = wait
					.until(ExpectedConditions.presenceOfAllElementsLocatedBy(DRP_OPTIONS));

			for (WebElement option : softDropOptions) {
				if (option.getText().trim().equals(Constants.SOFT_MAN)) {
					option.click();
					Thread.sleep(500); // Optional UI sync
					break;
				}
			}

			js.executeScript("window.scrollTo(0, document.body.scrollHeight);");
			Thread.sleep(500);

			WebElement submit = wait.until(ExpectedConditions.elementToBeClickable(SUBMIT));
			submit.click();

			System.out.println("Firmware added successfully.");

			driver.navigate().back();
			Thread.sleep(500);
			return true;
		} catch (

		Exception e) {
			System.out.println("Error occurred while adding firmware: " + e.getMessage());
			return false;
		}
	}

//	public String deleteGovServer() {
//		WebElement toast_confirmation;
//		
//		try {
//			searchGovServer(randomStateName + "Updated");
//
//			WebElement delIcon = wait.until(ExpectedConditions.elementToBeClickable(DELETE_ICON));
//			delIcon.click();
//
//			WebDriverWait alertWait = new WebDriverWait(driver, Duration.ofSeconds(5));
//			try {
//				Alert alert = alertWait.until(ExpectedConditions.alertIsPresent());
//				alert.dismiss();
//
//				delIcon = wait.until(ExpectedConditions.elementToBeClickable(DELETE_ICON));
//				delIcon.click();
//
//				alert = alertWait.until(ExpectedConditions.alertIsPresent());
//				alert.accept();
//
//				toast_confirmation = wait.until(ExpectedConditions.visibilityOfElementLocated(TOAST_MSG));
//			} catch (NoAlertPresentException | TimeoutException e) {
//				System.out.println("No alert found: " + e.getMessage());
//				return "Not found !! " + e.getMessage();
//			}
//
//			System.out.println("Government server deleted successfully.");
//			return toast_confirmation.getText();
//		} catch (Exception e) {
//			System.out.println("Error occurred while deleting government server: " + e.getMessage());
//			return "Not found!!!";
//		}
//	}

	public String deleteGovServer() {
		try {
			searchGovServer(randomStateName);

			WebElement delIcon = wait.until(ExpectedConditions.elementToBeClickable(DELETE_ICON));
			delIcon.click();

			WebDriverWait alertWait = new WebDriverWait(driver, Duration.ofSeconds(5));
			try {
				Alert alert = alertWait.until(ExpectedConditions.alertIsPresent());
				alert.accept();

				WebElement toastConfirmation = wait.until(ExpectedConditions.visibilityOfElementLocated(TOAST_MSG));
				String msg = toastConfirmation.getText();
				System.out.println("Government server deleted successfully.");
				
				driver.navigate().refresh();
				
				return msg;

			} catch (NoAlertPresentException | TimeoutException e) {
				System.out.println("Alert not found or took too long: " + e.getMessage());
				return "Deletion failed: No alert found.";
			}	
		} catch (NoSuchElementException | TimeoutException e) {
			System.out.println("Element not found or timed out: " + e.getMessage());
			return "Not found !! " + e.getMessage();
		} catch (Exception e) {
			System.out.println("Unexpected error: " + e.getMessage());
			return "Unexpected error occurred.";
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
//			approveFirmware();

			js.executeScript("window.open()");
			tabs = new ArrayList<>(driver.getWindowHandles());
			driver.switchTo().window(tabs.get(2));
			driver.get(Constants.BASE_URL);
			loginAsManager("Software Manager");
//			approveFirmware();

			driver.close();
			driver.switchTo().window(tabs.get(1));
			driver.close();
			driver.switchTo().window(tabs.get(0));

			// Wait for system to confirm approval
//			WebDriverWait approvalWait = new WebDriverWait(driver, Duration.ofMinutes(2));
//			return approvalWait.until(ExpectedConditions.textToBePresentInElementLocated(FIRMWARE_STATUS, "Approved"));
			return true;
		} catch (Exception e) {
			System.out.println("Approval waiting failed: " + e.getMessage());
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

		System.out.println(role + " logged in successfully.");
	}

//	public boolean approveFirmware() {
//		try {
//			WebElement approveButton = wait.until(ExpectedConditions.elementToBeClickable(APPROVE_BUTTON));
//			approveButton.click();
//			System.out.println("Firmware approved.");
//			return true;
//		} catch (Exception e) {
//			System.out.println("Approval failed: " + e.getMessage());
//			return false;
//		}
//	}
}
