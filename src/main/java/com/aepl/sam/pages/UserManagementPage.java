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

import com.aepl.sam.actions.MouseActions;
import com.aepl.sam.constants.Constants;
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
			Thread.sleep(1000);
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

	public void addAndUpdateUser(String param) {
		if (param.equalsIgnoreCase("add")) {
			try {
				WebElement type = driver.findElement(USR_TYPE);
				type.click();
				type.sendKeys(Keys.DOWN);
				type.sendKeys(Keys.ENTER);

				WebElement firstName = driver.findElement(FIRST_NAME);
				firstName.clear();
				firstName.sendKeys("Dummy");

				WebElement lastName = driver.findElement(LAST_NAME);
				lastName.clear();
				lastName.sendKeys("Demo");

				WebElement email = driver.findElement(EMAIL);
				email.clear();
				email.sendKeys("email@gmail.com");

				WebElement mobile = driver.findElement(MOBILE);
				mobile.clear();
				mobile.sendKeys("8888888888");

				WebElement country = driver.findElement(COUNTRY);
				country.clear();
				country.sendKeys("IND");

				WebElement state = driver.findElement(STATE);
				state.clear();
				state.sendKeys("MAH");

				WebElement status = driver.findElement(STATUS);
				status.click();
				status.sendKeys(Keys.DOWN);
				status.sendKeys(Keys.ENTER);

				((JavascriptExecutor) driver).executeScript("window.scrollTo(0, document.body.scrollHeight);");

				WebElement submitBtn = driver.findElement(SUBMIT_BTN);
				submitBtn.click();

				Thread.sleep(2000);
				driver.navigate().to(Constants.USR_MAN);
			} catch (Exception e) {
				e.getMessage();
			}
		}
		if (param.equalsIgnoreCase("update")) {
			try {
				WebElement type = driver.findElement(USR_TYPE);
				type.click();
				type.sendKeys(Keys.DOWN);
				type.sendKeys(Keys.ENTER);

				WebElement firstName = driver.findElement(FIRST_NAME);
				firstName.clear();
				firstName.sendKeys("UPDATE");

				WebElement lastName = driver.findElement(LAST_NAME);
				lastName.clear();
				lastName.sendKeys("DEMO");

				WebElement email = driver.findElement(EMAIL);
				email.clear();
				email.sendKeys("dhananjay.jagtap@accoladeelectronics.com");

				WebElement mobile = driver.findElement(MOBILE);
				mobile.clear();
				mobile.sendKeys("9172571295");

				WebElement country = driver.findElement(COUNTRY);
				country.clear();
				country.sendKeys("IND");

				WebElement state = driver.findElement(STATE);
				state.clear();
				state.sendKeys("MAH");

				WebElement status = driver.findElement(STATUS);
				status.click();
				status.sendKeys(Keys.DOWN);
				status.sendKeys(Keys.ENTER);

				((JavascriptExecutor) driver).executeScript("window.scrollTo(0, document.body.scrollHeight);");

				WebElement updateBtn = driver.findElement(UPDATE_BTN);
				updateBtn.click();

				Thread.sleep(2000);
//				driver.navigate().to(Constants.USR_MAN);
			} catch (Exception e) {
				e.getMessage();
			}
		}
	}

	public void checkDropdown() {
		try {
			List<WebElement> options = wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(DRP_OPTION));

			for (WebElement op : options) {
				Thread.sleep(1000);
				op.click();
			}

			for (int i = options.size() - 1; i >= 0; i--) {
				Thread.sleep(1000);
				options.get(i).click();
			}

		} catch (Exception e) {
			System.out.println("Error: " + e.getMessage());
		}
	}

	public void searchAndViewUser() {
		try {
			WebElement search = driver.findElement(SEARCH_FIELD);
			search.sendKeys("Dhananjay Jagtap");
			Thread.sleep(2000);

			search.sendKeys(Keys.ENTER);
			Thread.sleep(2000);

			WebElement viewBtn = driver.findElement(EYE_ICON);
			viewBtn.click();
			Thread.sleep(2000);
		} catch (Exception e) {
			e.getMessage();
		}
	}

	// Pagination and rows per page pending here
}
