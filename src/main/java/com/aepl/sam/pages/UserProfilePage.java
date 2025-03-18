package com.aepl.sam.pages;

import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.datatransfer.StringSelection;
import java.awt.event.KeyEvent;
import java.time.Duration;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.aepl.sam.actions.MouseActions;
import com.aepl.sam.constants.Constants;
import com.aepl.sam.locators.UserProfilePageLocators;

public class UserProfilePage extends UserProfilePageLocators {
	private WebDriver driver;
	private WebDriverWait wait;
	private MouseActions action;

	public UserProfilePage(WebDriver driver, WebDriverWait wait, MouseActions action) {
		this.driver = driver;
		this.wait = wait;
		this.action = action;
	}

	public String navBarLink() {
		try {
			action.hoverOverElement(wait.until(ExpectedConditions.visibilityOfElementLocated(USER_PROFILE)));

			WebElement govServer = wait.until(ExpectedConditions.visibilityOfElementLocated(PROFILE_LINK));
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

	public void changePassword() {
		try {
			WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
			JavascriptExecutor js = (JavascriptExecutor) driver;

			js.executeScript("window.scrollTo(0, document.body.scrollHeight);");

			WebElement changePass = wait.until(ExpectedConditions.elementToBeClickable(CHANGE_PASS));
			changePass.click();

			driver.switchTo().activeElement();

			WebElement curPass = wait.until(ExpectedConditions.visibilityOfElementLocated(CUR_PASS));
			WebElement newPass = wait.until(ExpectedConditions.visibilityOfElementLocated(NEW_PASS));
			WebElement changePassword = wait.until(ExpectedConditions.elementToBeClickable(CHANGE_BTN));

			curPass.sendKeys(Constants.CUR_PASS);
			newPass.sendKeys(Constants.NEW_PASS);

			js.executeScript("arguments[0].scrollIntoView(true);", changePassword);
			changePassword.click();

			wait.until(ExpectedConditions.invisibilityOf(changePassword));

			driver.close();

		} catch (Exception e) {
			System.err.println("Error changing password: " + e.getMessage());
			e.printStackTrace();
		}
	}

	public void uploadProfilePicture() {
		try {
			WebElement uploadProfile = driver.findElement(UPLOAD_PROFILE);
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

		}
	}

	// profile updated
	public void updateProfileDetails() {
		WebElement firstName = driver.findElement(FIRST_NAME);
		WebElement lastName = driver.findElement(LAST_NAME);
		WebElement email = driver.findElement(EMAIL);
		WebElement mobileNumber = driver.findElement(MOBILE_NUMBER);
		WebElement country = driver.findElement(COUNTRY);
		WebElement state = driver.findElement(STATE);
		WebElement updateBtn = driver.findElement(UPDATE);

		try {
			String firstNameValue = firstName.getAttribute("value");
			String lastNameValue = lastName.getAttribute("value");
			String emailValue = email.getAttribute("value");
			String mobileNumberValue = mobileNumber.getAttribute("value");
			String countryValue = country.getAttribute("value");
			String stateValue = state.getAttribute("value");

			if (firstNameValue.equals(firstNameValue)) {
				firstName.clear();
				firstName.sendKeys(firstNameValue);
			}
			if (lastNameValue.equals(lastNameValue)) {
				lastName.clear();
				lastName.sendKeys(lastNameValue);
			}
			if (emailValue.equals(emailValue)) {
				email.clear();
				email.sendKeys(emailValue);
			}
			if (mobileNumberValue.equals(mobileNumberValue)) {
				mobileNumber.clear();
				mobileNumber.sendKeys(mobileNumberValue);
			}
			if (countryValue.equals(countryValue)) {
				country.clear();
				country.sendKeys(countryValue);
			}
			if (stateValue.equals(stateValue)) {
				state.clear();
				state.sendKeys(stateValue);
			}

			JavascriptExecutor js = (JavascriptExecutor) driver;
			js.executeScript("window.scrollTo(0, document.body.scrollHeight);");

			Thread.sleep(5000);

			updateBtn.click();
			System.out.println("Profile updated successfully !!!");
		} catch (Exception e) {
			e.getMessage();
		}
	}

}
