package com.aepl.sam.pages;

import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.datatransfer.StringSelection;
import java.awt.event.KeyEvent;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.aepl.sam.actions.MouseActions;
import com.aepl.sam.locators.UserManagementPageLocators;

public class UserManagementPage extends UserManagementPageLocators {
	private WebDriver driver;
	private WebDriverWait wait;
	private MouseActions action;

	public UserManagementPage(WebDriver driver, WebDriverWait wait, MouseActions action) {
		this.driver = driver;
		this.wait = wait;
		this.action = action;
	}

	public String navBarLink() {
		try {
			action.hoverOverElement(wait.until(ExpectedConditions.visibilityOfElementLocated(USER)));

			WebElement govServer = wait.until(ExpectedConditions.visibilityOfElementLocated(USR_MANAGEMENT_LINK));
			Thread.sleep(1000);
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
			Thread.sleep(1000);

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

	public void clickAddUserBtn() {
		try {
			WebElement addUser = driver.findElement(ADD_USR_BTN);
			addUser.click();
			Thread.sleep(2000);
		} catch (Exception e) {
			e.getLocalizedMessage();
		}
	}

	public void addUserProfilepicture() {
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("window.scrollTo(0, document.body.scrollHeight);");

		try {
			WebElement uploadProfile = driver.findElement(PROF_BTN);
			uploadProfile.click();

			StringSelection selection = new StringSelection("D:\\wallpaper\\1.jpg");
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
		} catch (Exception e) {
			e.getMessage();
		}
	}

	public void addnewUser() {

	}
}
