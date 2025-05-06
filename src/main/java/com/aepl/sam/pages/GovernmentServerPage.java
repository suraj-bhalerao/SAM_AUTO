package com.aepl.sam.pages;

import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.datatransfer.StringSelection;
import java.awt.event.KeyEvent;
import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

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
	private MouseActions action;
	private CalendarActions calAct;
	private LoginPage loginPage;
	private CommonMethods comm;

	public GovernmentServerPage(WebDriver driver, WebDriverWait wait, MouseActions action) {
		this.driver = driver;
		this.wait = wait;
		this.action = action;
		this.calAct = new CalendarActions(this.driver, this.wait);
		this.loginPage = new LoginPage(driver, wait, null);
		this.comm = new CommonMethods(driver, wait);
	}

	public String navBarLink() {
		try {
			action.hoverOverElement(wait.until(ExpectedConditions.visibilityOfElementLocated(DEVICE_UTILITY)));

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
			WebElement addGovButton = wait.until(ExpectedConditions.elementToBeClickable(ADD_GOV_SER));

			comm.highlightElement(addGovButton, "RED");
			Thread.sleep(500);

			addGovButton.click();

			WebElement componentTitle = driver.findElement(COMPONENT_TITLE);
			return componentTitle.getText();
		} catch (Exception e) {
			System.err.println("An error occurred while adding the government server: " + e.getMessage());
		}
		return "Failed to click the add government server button or navigate to the next page.";
	}

	public String manageGovServer(String actionType) {
		System.out.println("Action Type Received: " + actionType);
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

		try {
			WebElement state = driver.findElement(STATE);
			WebElement stateAbr = driver.findElement(STATE_ABR);
			WebElement ip1 = driver.findElement(GOV_IP1);
			WebElement ip2 = driver.findElement(GOV_IP2);
			WebElement port1 = driver.findElement(GOV_PORT1);
			WebElement port2 = driver.findElement(GOV_PORT2);
			WebElement stateEnabled = driver.findElement(STATE_ENABLED);

			JavascriptExecutor js = (JavascriptExecutor) driver;

			if (actionType.equalsIgnoreCase("add")) {
				state.sendKeys("DEFAULT");
				stateAbr.sendKeys("DF");
				ip1.sendKeys("255.255.255.255");
				port1.sendKeys("8888");
				ip2.sendKeys("255.255.255.255");
				port2.sendKeys("7777");
				stateEnabled.sendKeys("TRUE");

				js.executeScript("window.scrollTo(0, document.body.scrollHeight);");
				Thread.sleep(2000);

				WebElement submit = wait.until(ExpectedConditions.elementToBeClickable(SUBMIT));
				comm.highlightElement(submit, "VIOLET");
				submit.click();

				WebElement confirmationToastMsg = wait.until(ExpectedConditions.visibilityOfElementLocated(TOAST_MSG));
				return confirmationToastMsg.getText();
			} else if (actionType.equalsIgnoreCase("update")) {
				ip1.clear();
				ip1.sendKeys("255.255.255.001");
				port1.clear();
				port1.sendKeys("9999");
				ip2.clear();
				ip2.sendKeys("255.255.255.001");
				port2.clear();
				port2.sendKeys("6666");
				stateEnabled.clear();
				stateEnabled.sendKeys("FALSE");

				js.executeScript("window.scrollTo({ top: document.body.scrollHeight, behavior: 'smooth' });");

				Thread.sleep(2000);
				WebElement update = wait.until(ExpectedConditions.elementToBeClickable(UPDATE));
				update.click();

				WebElement confirmationToastMsg = wait.until(ExpectedConditions.visibilityOfElementLocated(TOAST_MSG));
				return confirmationToastMsg.getText();
			} else {
				System.out.println("Invalid actionType: " + actionType);
				return "Invalid action type";
			}
		} catch (Exception e) {
			e.printStackTrace();
			return "Error: " + e.getMessage();
		}
	}

	public void searchGovServer(String name) {
		WebElement search = driver.findElement(SEARCH_BOX_INPUT);
		WebElement searchBtn = driver.findElement(SEARCH_BOX_BTN);

		search.sendKeys(name);
		searchBtn.click();
	}

	// Search And View
	public boolean searchAndView() {
		try {
//	        wait.until(ExpectedConditions.urlToBe(Constants.GOV_LINK)); 
			driver.navigate().to(Constants.GOV_LINK); // directly go to url coz for now there is no redirection happens
														// by clicking on the submit btn.
			WebElement search = driver.findElement(SEARCH_BOX_INPUT);
			comm.highlightElement(search, "RED");
//			List<WebElement> stateNames = driver.findElements(TABLE_DATA);
			WebElement searchBtn = driver.findElement(SEARCH_BOX_BTN);
			comm.highlightElement(searchBtn, "RED");

//			if (stateNames.isEmpty()) {
//				System.out.println("No data available to search.");
//				return false;
//			}

			Thread.sleep(2000);
//			WebElement itemToSearch = stateNames.get(0);
//			search.sendKeys(itemToSearch.getText());
			search.sendKeys("DEFAULT");
			searchBtn.click();

			Thread.sleep(2000);
			WebElement viewIcon = driver.findElement(EYE_ICON);
			viewIcon.click();

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
			firmName.sendKeys("2.2.1");

			WebElement firmDesc = driver.findElement(FRM_DSC);
			firmDesc.sendKeys("Practice...");
			
			comm.highlightElement(driver.findElement(CAL_BTN), "GREEN");
			calAct.selectDate(CAL_BTN, "01-03-2025");

			js.executeScript("window.scrollTo(0, document.body.scrollHeight);");

			List<WebElement> managerSelection = driver.findElements(MANAGER_SELECT);

			for (WebElement dropdown : managerSelection) {
				dropdown.click();
				Thread.sleep(500);

				List<WebElement> dropOptions = wait
						.until(ExpectedConditions.presenceOfAllElementsLocatedBy(DRP_OPTIONS));

				for (WebElement option : dropOptions) {
					String optionText = option.getText().trim();

					if (optionText.equals("Shital Shingare") || optionText.equals("Abhijeet Jawale")) {
						option.click();
						Thread.sleep(500);
						break;
					}
				}
			}

			WebElement file = driver.findElement(FILE_UPLOAD);
			action.moveToElement(file);
			action.clickElement(file);
			Thread.sleep(500);

			StringSelection selection = new StringSelection("D:\\Bin Files\\SAMPARK\\SAM01_LITE_APP_0.0.1_TST11.bin");
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

			js.executeScript("window.scrollTo(0, document.body.scrollHeight);");
			WebElement submit = wait.until(ExpectedConditions.elementToBeClickable(SUBMIT));
			submit.click();

			System.out.println("Firmware added successfully.");
			return true;
		} catch (Exception e) {
			System.out.println("Error occurred while adding firmware: " + e.getMessage());
			return false;
		}
	}

	public String deleteGovServer(String name) {
		WebElement toast_confirmation;
		try {
			searchGovServer(name);
			driver.navigate().to(Constants.GOV_LINK);
			WebElement delIcon = wait.until(ExpectedConditions.elementToBeClickable(DELETE_ICON));
			delIcon.click();

			WebDriverWait alertWait = new WebDriverWait(driver, Duration.ofSeconds(5));
			try {
				Alert alert = alertWait.until(ExpectedConditions.alertIsPresent());
				alert.dismiss();

				delIcon = wait.until(ExpectedConditions.elementToBeClickable(DELETE_ICON));
				delIcon.click();

				alert = alertWait.until(ExpectedConditions.alertIsPresent());
				alert.accept();

				toast_confirmation = wait.until(ExpectedConditions.visibilityOfElementLocated(TOAST_MSG));
			} catch (NoAlertPresentException | TimeoutException e) {
				System.out.println("No alert found: " + e.getMessage());
				return "Not found !! " + e.getMessage();
			}

			System.out.println("Government server deleted successfully.");
			return toast_confirmation.getText();
		} catch (Exception e) {
			System.out.println("Error occurred while deleting government server: " + e.getMessage());
			return "Not found!!!";
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
