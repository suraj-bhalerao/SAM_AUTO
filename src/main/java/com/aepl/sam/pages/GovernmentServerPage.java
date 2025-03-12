package com.aepl.sam.pages;

import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.datatransfer.StringSelection;
import java.awt.event.KeyEvent;
import java.util.List;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.aepl.sam.actions.CalendarActions;
import com.aepl.sam.actions.MouseActions;
import com.aepl.sam.constants.Constants;
import com.aepl.sam.locators.GovernmentServerPageLocators;

public class GovernmentServerPage extends GovernmentServerPageLocators {

	private WebDriver driver;
	private WebDriverWait wait;
	private MouseActions action;
	private CalendarActions calAct;

	public GovernmentServerPage(WebDriver driver, WebDriverWait wait, MouseActions action) {
		this.driver = driver;
		this.wait = wait;
		this.action = action;
		this.calAct = new CalendarActions(this.driver);
	}

	public String navBarLink() {
		try {
			action.hoverOverElement(wait.until(ExpectedConditions.visibilityOfElementLocated(DEVICE_UTILITY)));

			WebElement govServer = wait.until(ExpectedConditions.visibilityOfElementLocated(GOVERNMENT_NAV_LINK));
			Thread.sleep(10);
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

			JavascriptExecutor js = (JavascriptExecutor) driver;
			js.executeScript("arguments[0].style.border = 'red'", element);

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

			JavascriptExecutor js = (JavascriptExecutor) driver;
			js.executeScript("arguments[0].style.border = 'red'", addGovButton);

			addGovButton.click();

			WebElement componentTitle = driver.findElement(COMPONENT_TITLE);
			return componentTitle.getText();
		} catch (Exception e) {
			System.err.println("An error occurred while adding the government server: " + e.getMessage());
		}
		return "Failed to click the add government server button or navigate to the next page.";
	}

	public void manageGovServer(String actionType) {
		WebElement state = driver.findElement(STATE);
		WebElement stateAbr = driver.findElement(STATE_ABR);
		WebElement ip1 = driver.findElement(GOV_IP1);
		WebElement ip2 = driver.findElement(GOV_IP2);
		WebElement port1 = driver.findElement(GOV_PORT1);
		WebElement port2 = driver.findElement(GOV_PORT2);
		WebElement stateEnabled = driver.findElement(STATE_ENABLED);

		JavascriptExecutor js = (JavascriptExecutor) driver;

		try {
			if (actionType.equalsIgnoreCase("add")) {
				state.sendKeys("DEFAULT");
				stateAbr.sendKeys("DF");
				ip1.sendKeys("255.255.255.255");
				port1.sendKeys("8888");
				ip2.sendKeys("255.255.255.255");
				port2.sendKeys("7777");
				stateEnabled.sendKeys("TRUE");

				js.executeScript("window.scrollTo(0, document.body.scrollHeight);");

				WebElement confirmationToastMsg = driver.findElement(TOAST_MSG);
				confirmationToastMsg.click();

				Thread.sleep(2000);

				WebElement submit = wait.until(ExpectedConditions.elementToBeClickable(SUBMIT));
				submit.click();

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

				WebElement confirmationToastMsg = driver.findElement(TOAST_MSG);
				confirmationToastMsg.click();

				Thread.sleep(2000);

				WebElement update = wait.until(ExpectedConditions.elementToBeClickable(UPDATE));
				update.click();
			} else {
				System.out.println("Invalid actionType: " + actionType);
			}
		} catch (Exception e) {
			System.out.println("Error: " + e.getMessage());
		}
	}

	// Search And View
	public void searchAndView() {
		wait.until(ExpectedConditions.urlToBe(Constants.GOV_LINK));
		WebElement search = driver.findElement(SEARCH_BOX_INPUT);
		List<WebElement> stateNames = driver.findElements(TABLE_DATA);
		WebElement searchBtn = driver.findElement(SEARCH_BOX_BTN);
		try {
			Thread.sleep(2000);
			WebElement itemToSearch = stateNames.get(0);
			search.sendKeys(itemToSearch.getText());
			searchBtn.click();

			Thread.sleep(2000);
			WebElement viewIcons = driver.findElement(EYE_BTN);
			viewIcons.click();
		} catch (Exception e) {
			e.getLocalizedMessage();
		}

	}

	public void addFirmware() {
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("window.scrollTo(0, document.body.scrollHeight);");

		try {
			WebElement addFirmwareButton = wait.until(ExpectedConditions.elementToBeClickable(ADD_FIRM));
			addFirmwareButton.click();

			WebElement firmName = driver.findElement(FRM_NAME);
			firmName.sendKeys("2.2.1");

			WebElement firmDesc = driver.findElement(FRM_DSC);
			firmDesc.sendKeys("Practice...");

//			WebElement file = driver.findElement(FILE_UPLOAD);
//			file.click();
//			Thread.sleep(20000);
//			
//			Robot fileHandler = new Robot();
//			String filePath = "D:\\Bin Files\\TCP01.bin";
//
//			StringSelection selection = new StringSelection(filePath);
//			Toolkit.getDefaultToolkit().getSystemClipboard().setContents(selection, null);
//
//			fileHandler.keyPress(KeyEvent.VK_CONTROL);
//			fileHandler.keyPress(KeyEvent.VK_V);
//			fileHandler.keyRelease(KeyEvent.VK_V);
//			fileHandler.keyRelease(KeyEvent.VK_CONTROL);
//			Thread.sleep(1000);
//
//			fileHandler.keyPress(KeyEvent.VK_ENTER);
//			fileHandler.keyRelease(KeyEvent.VK_ENTER);
//			Thread.sleep(2000);

			WebElement calendar = driver.findElement(CAL_BTN);
			calendar.click();
			wait.until(ExpectedConditions.elementToBeClickable(CAL_BTN));
			calAct.selectDate(CAL_BTN, "1-1-2025");

			List<WebElement> managerSelection = driver.findElements(MANAGER_SELECT);
			for (WebElement man : managerSelection) {
				man.click();
				man.sendKeys(Keys.ENTER);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
