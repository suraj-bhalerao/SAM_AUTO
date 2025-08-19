package com.aepl.sam.pages;

import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.datatransfer.StringSelection;
import java.awt.event.KeyEvent;
import java.time.Duration;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.aepl.sam.constants.Constants;
import com.aepl.sam.locators.UserProfilePageLocators;

public class UserProfilePage extends UserProfilePageLocators {
	private WebDriver driver;
	private WebDriverWait wait;
	private final Logger logger = LogManager.getLogger(this.getClass().getSimpleName());

	public UserProfilePage(WebDriver driver, WebDriverWait wait) {
		this.driver = driver;
		this.wait = wait;
	}

	public String navBarLink() {
		try {
			logger.info("Navigating to User Profile page...");
			WebElement user = wait.until(ExpectedConditions.visibilityOfElementLocated(USER_PROFILE));
			user.click();

			WebElement govServer = wait.until(ExpectedConditions.visibilityOfElementLocated(PROFILE_LINK));
			govServer.click();
			logger.info("Successfully navigated to User Profile page.");
		} catch (Exception e) {
			logger.error("Error navigating to User Profile page: {}", e.getMessage(), e);
		}
		return driver.getCurrentUrl();
	}

	public String backButton() {
		try {
			logger.info("Clicking back button...");
			WebElement element = wait.until(ExpectedConditions.visibilityOfElementLocated(BACK_BUTTON));

			JavascriptExecutor js = (JavascriptExecutor) driver;
			js.executeScript("arguments[0].style.border = 'solid purple'", element);

			element.click();
			Thread.sleep(10);

			logger.info("Clicked on back button: {}", element.getText());
		} catch (Exception e) {
			logger.error("Error clicking back button: {}", e.getMessage(), e);
		}
		return navBarLink();
	}

	public String refreshButton() {
		try {
			logger.info("Clicking refresh button...");
			WebElement refreshBtn = wait.until(ExpectedConditions.elementToBeClickable(REFRESH_BUTTON));

			JavascriptExecutor js = (JavascriptExecutor) driver;
			js.executeScript("arguments[0].style.border = 'solid purple'", refreshBtn);

			Thread.sleep(20);
			refreshBtn.click();

			WebElement page_title = wait.until(ExpectedConditions.visibilityOfElementLocated(PAGE_TITLE));
			String pageTitle = page_title.getText();
			logger.info("Page refreshed, current title: {}", pageTitle);
			return pageTitle;
		} catch (Exception e) {
			logger.error("Error clicking refresh button: {}", e.getMessage(), e);
		}
		return "No Data Found!!!";
	}

	public void changePassword() {
		try {
			logger.info("Attempting to change password...");
			WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
			JavascriptExecutor js = (JavascriptExecutor) driver;

			js.executeScript("window.scrollTo(0, document.body.scrollHeight);");

			WebElement changePass = wait.until(ExpectedConditions.elementToBeClickable(CHANGE_PASS));
			changePass.click();

			WebElement curPass = wait.until(ExpectedConditions.visibilityOfElementLocated(CUR_PASS));
			curPass.sendKeys(Constants.CUR_PASS);

			WebElement newPass = wait.until(ExpectedConditions.visibilityOfElementLocated(NEW_PASS));
			newPass.sendKeys(Constants.NEW_PASS);

			WebElement changePassword = wait.until(ExpectedConditions.elementToBeClickable(CHANGE_BTN));
			js.executeScript("arguments[0].scrollIntoView(true);", changePassword);
			changePassword.click();

			wait.until(ExpectedConditions.invisibilityOf(changePassword));

			logger.info("Password changed successfully.");
		} catch (Exception e) {
			logger.error("Error changing password: {}", e.getMessage(), e);
		}
	}

	public boolean uploadProfilePicture() {
		try {
			logger.info("Starting profile picture upload...");

			WebElement uploadProfile = driver.findElement(UPLOAD_PROFILE);
			uploadProfile.click();
			logger.info("Upload button clicked.");

			StringSelection selection = new StringSelection(FILE_PATH);
			Toolkit.getDefaultToolkit().getSystemClipboard().setContents(selection, null);
			logger.info("Image path copied to clipboard.");

			Robot fileHandler = new Robot();
			Thread.sleep(500);

			fileHandler.keyPress(KeyEvent.VK_CONTROL);
			fileHandler.keyPress(KeyEvent.VK_V);
			fileHandler.keyRelease(KeyEvent.VK_V);
			fileHandler.keyRelease(KeyEvent.VK_CONTROL);
			logger.info("Image path pasted.");

			Thread.sleep(500);
			fileHandler.keyPress(KeyEvent.VK_ENTER);
			fileHandler.keyRelease(KeyEvent.VK_ENTER);
			logger.info("ENTER key pressed to confirm upload.");

			logger.info("Profile picture uploaded successfully.");
			return true;
		} catch (Exception e) {
			logger.error("Error uploading profile picture: {}", e.getMessage(), e);
			return false;
		}
	}

	public boolean updateProfileDetails() {
		try {
			logger.info("Updating profile details...");

			WebElement firstName = driver.findElement(FIRST_NAME);
			WebElement lastName = driver.findElement(LAST_NAME);
			WebElement email = driver.findElement(EMAIL);
			WebElement mobileNumber = driver.findElement(MOBILE_NUMBER);
			WebElement country = driver.findElement(COUNTRY);
			WebElement state = driver.findElement(STATE);
			WebElement updateBtn = driver.findElement(UPDATE);

			String firstNameValue = firstName.getAttribute("value");
			String lastNameValue = lastName.getAttribute("value");
			String emailValue = email.getAttribute("value");
			String mobileNumberValue = mobileNumber.getAttribute("value");
			String countryValue = country.getAttribute("value");
			String stateValue = state.getAttribute("value");

			firstName.clear();
			firstName.sendKeys(firstNameValue);
			lastName.clear();
			lastName.sendKeys(lastNameValue);
			email.clear();
			email.sendKeys(emailValue);
			mobileNumber.clear();
			mobileNumber.sendKeys(mobileNumberValue);
			country.clear();
			country.sendKeys(countryValue);
			state.clear();
			state.sendKeys(stateValue);

			JavascriptExecutor js = (JavascriptExecutor) driver;
			js.executeScript("window.scrollTo(0, document.body.scrollHeight);");

			Thread.sleep(2000);

			updateBtn.click();
			logger.info("Profile details updated successfully.");
			return true;
		} catch (Exception e) {
			logger.error("Error updating profile details: {}", e.getMessage(), e);
			return false;
		}
	}

	public Map<String, String> validateUserData() {
		Map<String, String> userInfo = new HashMap<>();
		List<WebElement> userData = driver.findElements(By.xpath("//div/input"));
		for (WebElement data : userData) {
			String info = data.getText();
			System.out.println("The info is : " + info);
			userInfo.put("dummy ", info);
		}
		return userInfo;
	}

	// need to modify this function to have the user info from the api and then
	// validate to the actual data that is
	// represented on the ui
}
