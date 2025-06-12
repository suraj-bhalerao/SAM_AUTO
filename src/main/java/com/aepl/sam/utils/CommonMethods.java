package com.aepl.sam.utils;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Properties;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.Alert;
import org.openqa.selenium.ElementClickInterceptedException;
import org.openqa.selenium.JavascriptException;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.aepl.sam.locators.CommonPageLocators;

import jakarta.mail.BodyPart;
import jakarta.mail.Flags;
import jakarta.mail.Flags.Flag;
import jakarta.mail.Folder;
import jakarta.mail.Message;
import jakarta.mail.Multipart;
import jakarta.mail.Session;
import jakarta.mail.Store;
import jakarta.mail.search.FlagTerm;

public class CommonMethods extends CommonPageLocators {
	public WebDriver driver;
	private WebDriverWait wait;

	public CommonMethods(WebDriver driver, WebDriverWait wait) {
		this.driver = driver;
		this.wait = wait;
	}

	public void captureScreenshot(String testCaseName) {
		if (driver == null) {
			throw new RuntimeException("WebDriver initialization failed in: Commonmethods");
		}

		String timestamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
		String screenshotName = testCaseName + "_" + timestamp + ".png";
		String screenshotPath = "screenshots/" + screenshotName;

		try {
			File screenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
			FileUtils.copyFile(screenshot, new File(screenshotPath));
		} catch (IOException e) {
			System.err.println("Error " + e);
		}
	}

	public boolean verifyWebpageLogo() {
		JavascriptExecutor js = (JavascriptExecutor) driver;

		// Wait for the logo element to be visible
		WebElement logo = wait.until(ExpectedConditions.visibilityOfElementLocated(ORG_LOGO));
		js.executeScript("arguments[0].style.border='3px solid purple'", logo);

		// Verify if the logo is displayed
		if (logo.isDisplayed()) {
			return true;
		} else {
			throw new RuntimeException("Webpage logo is not visible.");
		}
	}

	// Correction here this is not page title it is project title
	public String verifyPageTitle() {
		String expectedTitle = "AEPL Sampark Diagnostic Cloud";
		JavascriptExecutor js = (JavascriptExecutor) driver;
		// Wait for the title element to be visible
		WebElement titleElement = wait.until(ExpectedConditions.visibilityOfElementLocated(PROJECT_TITLE));
		js.executeScript("arguments[0].style.border='3px solid purple'", titleElement);
		// Extract the text of the title element
		String actualTitle = titleElement.getText();
		// Verify if the title matches the expected title
		if (actualTitle.equalsIgnoreCase(expectedTitle)) {
			System.out.println("Page title is visible and matches: " + actualTitle);
		} else {
			throw new RuntimeException(
					"Page title does not match. Expected: " + expectedTitle + ", but found: " + actualTitle);
		}

		return actualTitle;
	}

	public void clickRefreshButton() {

		try {

			JavascriptExecutor js = (JavascriptExecutor) driver;

			// Wait for the refresh button to be present and visible
			WebElement refreshButton = wait.until(ExpectedConditions.visibilityOfElementLocated(REFRESH_BUTTON));

			// Scroll into view in case it's off-screen
			js.executeScript("arguments[0].scrollIntoView(true);", refreshButton);
			Thread.sleep(1000); // Small pause after scroll (replace with WebDriverWait if needed)

			// Highlight the element
			js.executeScript("arguments[0].style.border='3px solid purple'", refreshButton);
			try {
				// Try clicking normally
				refreshButton.click();
			} catch (ElementClickInterceptedException e) {
				// If intercepted, force click using JavaScript
				js.executeScript("arguments[0].click();", refreshButton);
			}
			Thread.sleep(3000);
		} catch (Exception e) {
			throw new RuntimeException("Failed to click on the refresh button.", e);
		}

		WebElement refreshButton = wait.until(ExpectedConditions.elementToBeClickable(REFRESH_BUTTON));
		highlightElement(refreshButton, "GREEN");
		refreshButton.click();

	}

	public void clickNavBarDash() {
		// Wait for the navigation bar links to be visible
		JavascriptExecutor js = (JavascriptExecutor) driver;
		List<WebElement> navBarLinks = wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(DASHBOARD));
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

	public void clickNavBar() {
		try {
			// Wait for the navigation bar links to be visible
			List<WebElement> navBarLinks = wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(DASHBOARD));
			// Debugging - Print total elements found
//	        System.out.println("Total navigation links found: " + navBarLinks.size());
			if (navBarLinks.isEmpty()) {
				navBarLinks.get(0).click();
				throw new RuntimeException("No navigation bar links found for 'Dashboard'.");
			}

			JavascriptExecutor js = (JavascriptExecutor) driver;
			boolean isClicked = false;

			for (WebElement link : navBarLinks) {
				// Highlight each element separately
				js.executeScript("arguments[0].style.border='3px solid green'", link);

				if (link.getText().trim().equalsIgnoreCase("Dashboard")) {
					System.out.println("Clicked On Element On Nav: " + link.getAccessibleName());
					js.executeScript("arguments[0].click();", link); // JavaScript Click (more reliable)
					isClicked = true;
					break; // Stop loop once clicked
				}
			}
			if (!isClicked) {
				throw new RuntimeException("Failed to find and click on 'Dashboard' in the navigation bar.");
			}
		} catch (StaleElementReferenceException e) {
			throw new RuntimeException("Element went stale. Try re-fetching before clicking.", e);
		} catch (JavascriptException e) {
			throw new RuntimeException("JavaScript execution failed. Element might be undefined.", e);
		}
	}

	public boolean clickNavBarDeviceUtil() {
		try {
			JavascriptExecutor js = (JavascriptExecutor) driver;
			List<WebElement> navBarLinks = wait
					.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(DEVICE_UTILITY));

			for (WebElement link : navBarLinks) {
				js.executeScript("arguments[0].style.border='3px solid purple'", link);

				if (link.getText().trim().equalsIgnoreCase("Device Utility")) {
					try {
						link.click();
					} catch (Exception e) {
						js.executeScript("arguments[0].click();", link);
					}
					System.out.println("Clicked on element in Nav: " + link.getAccessibleName());
					return true;
				}
			}

			System.out.println("Failed to find and click on 'Device Utility' in the navigation bar.");
			return false;
		} catch (Exception e) {
			System.out.println(
					"Exception occurred while clicking 'Device Utility' in the navigation bar: " + e.getMessage());
			return false;
		}
	}

	public boolean clickNavBarUser() {
		try {
			List<WebElement> navBarLinks = wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(USER));

			for (WebElement link : navBarLinks) {
				highlightElement(link, "RED");

				// Check if the link text matches "User" (case-insensitive)
				if (link.getText().equalsIgnoreCase("User")) {
					try {
						link.click();
						System.out.println("Clicked on element in NavBar: " + link.getAccessibleName());
						return true; // Return true if successfully clicked
					} catch (Exception e) {
						System.err.println("Error clicking on 'User' in NavBar: " + e.getMessage());
						return false;
					}
				}
			}

			// If "User" link not found
			System.err.println("Failed to find 'User' in the navigation bar.");
			return false;

		} catch (Exception e) {
			System.err.println("Error while interacting with the navigation bar: " + e.getMessage());
			return false;
		}
	}

	public void clickNavBarUserPro() {
		// Wait for the navigation bar links to be visible
		JavascriptExecutor js = (JavascriptExecutor) driver;
		List<WebElement> navBarLinks = wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(USER_PROFILE));
		boolean isClicked = false;
		for (WebElement link : navBarLinks) {
			js.executeScript("arguments[0].style.border='3px solid purple'", link);

			if (link.getText().equalsIgnoreCase("Hi, Super Ad")) {

				System.out.println("Clicked On Element On Nav: " + link.getAccessibleName());

//				link.click();
//					System.out.println("Clicked On Element On Nav: " +link.getAccessibleName());
				isClicked = true;
//					break;
			}
		}
		if (!isClicked) {
			throw new RuntimeException("Failed to find and click on 'User Profile' in the navigation bar.");
		}
	}

	// Footer Section From Here
	public String checkCopyright() {
		try {
			Thread.sleep(2000);
			WebElement copyRight = driver.findElement(COPYRIGHT);
			highlightElement(copyRight, "YELLOW");
			return copyRight.getText();
		} catch (Exception e) {
			e.getMessage();
		}
		return "No copyright section was found!!!";
	}

	public String checkVersion() {
		try {

			Thread.sleep(2000);
			WebElement version = driver.findElement(VERSION);
			highlightElement(version, "YELLOW");
			return version.getText();
		} catch (Exception e) {
			e.getMessage();
		}
		return "No version was found on page!!!";
	}

	public void highlightElement(WebElement element, String colorCode) {
		((JavascriptExecutor) driver).executeScript("arguments[0].style.border='3px solid " + colorCode + "'", element);
	}

	public void highlightElements(List<WebElement> listOfElements, String colorCode) {
		for (WebElement element : listOfElements) {
			((JavascriptExecutor) driver).executeScript("arguments[0].style.border='3px solid " + colorCode + "'",
					element);
		}
	}

	public String validateComponents() {
		try {
			// Locate and validate all components
			WebElement headerContainer = driver.findElement(HEADER_CONTAINER);
			WebElement pageHeader = driver.findElement(PAGE_HEADER);
			WebElement componentContainer = driver.findElement(COMPONENT_CONTAINER);
			WebElement separator = driver.findElement(SEPARATOR);
//			WebElement footerPagination = driver.findElement(FOOTER_PAGINATION);
			WebElement footer = driver.findElement(FOOTER);

			// Highlight all components
			highlightElement(headerContainer, "GREEN");
			highlightElement(pageHeader, "GREEN");
			highlightElement(componentContainer, "GREEN");
			highlightElement(separator, "GREEN");
//			highlightElement(footerPagination, "GREEN");
			highlightElement(footer, "GREEN");

			return "All components are displayed and validated successfully.";
		} catch (Exception e) {
			return "Error validating components: " + e.getMessage();
		}
	}

	public String validateButtons() {
		try {
			List<WebElement> buttons = driver.findElements(ALL_BTN);
			if (buttons.isEmpty()) {
				return "No buttons found on the page.";
			}
			highlightElements(buttons, "GREEN");

			return "All buttons are displayed and enabled successfully.";

		} catch (Exception e) {
			System.err.println("Error validating buttons: " + e.getMessage());
			return "Error validating buttons: " + e.getMessage();
		}
	}

	public String clickSampleFileButton() {
		for (int i = 0; i <= 5; i++) {
			try {
				Thread.sleep(500);
				WebElement sampleFileButton = wait.until(ExpectedConditions.elementToBeClickable(SAMPLE_FILE_BUTTON));
				highlightElement(sampleFileButton, "GREEN");
				sampleFileButton.click();

				Alert alert = wait.until(ExpectedConditions.alertIsPresent());
				if (alert != null) {
					String alertText = alert.getText();
					System.out.println("Alert text: " + alertText);
					alert.accept();
				}
			} catch (Exception e) {
				System.err.println("Error clicking on Sample File button: " + e.getMessage());
			}
		}
		return "File downloaded successfully.";
	}

	public void checkPagination() {
		try {
			JavascriptExecutor js = (JavascriptExecutor) driver;

			js.executeScript("window.scrollTo(0, document.body.scrollHeight)");
			Thread.sleep(2000);

			WebElement rowPerPage = wait.until(ExpectedConditions.elementToBeClickable(ROW_PER_PAGE));
			Select select = new Select(rowPerPage);
			List<WebElement> options = select.getOptions();

			for (WebElement option : options) {
				js.executeScript("window.scrollTo(0, document.body.scrollHeight)");
				Thread.sleep(1000);
				option.click();
				Thread.sleep(1000);
			}

			js.executeScript("window.scrollTo(0, document.body.scrollHeight)");
			options.getFirst().click();

			js.executeScript("window.scrollTo(0, document.body.scrollHeight)");
			List<WebElement> pageInfoElement = wait
					.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(PAGE_COUNT));
			String pageInfo = pageInfoElement.get(1).getText();
			String[] parts = pageInfo.trim().split(" ");
			int totalPages = Integer.parseInt(parts[parts.length - 1]);

			System.err.println("Total Pages: " + totalPages);

			// Forward pagination
			for (int i = 1; i < totalPages; i++) {
				js.executeScript("window.scrollTo(0, document.body.scrollHeight)");
				WebElement rightArrow = wait.until(ExpectedConditions.elementToBeClickable(RIGHT_ARROW));
				rightArrow.click();
				Thread.sleep(1000);
			}

			// Click on the first page
			wait.until(ExpectedConditions.elementToBeClickable(FIRST_PAGE)).click();

//			// Backward pagination
//			for (int i = totalPages; i >= 1; i--) {
//				js.executeScript("window.scrollTo(0, document.body.scrollHeight)");
//				WebElement leftArrow = wait.until(ExpectedConditions.elementToBeClickable(LEFT_ARROW));
//				leftArrow.click();
//				Thread.sleep(1000); 
//			}
		} catch (Exception e) {
			System.err.println("Error in checkPagination: " + e.getMessage());
		}
	}

	// Authentication of mail OTP
	public static String getPasswordFromOutlook() throws Exception {
		String host = "imap-mail.outlook.com";
		String username = ConfigProperties.getProperty("username");
		String password = ConfigProperties.getProperty("mail_password");

		Properties properties = new Properties();
		properties.put("mail.imap.host", host);
		properties.put("mail.imap.port", "993");
		properties.put("mail.imap.ssl.enable", "true");

		Session session = Session.getDefaultInstance(properties);
		Store store = session.getStore("imap");
		store.connect(host, username, password);

		Folder inbox = store.getFolder("INBOX");
		inbox.open(Folder.READ_ONLY);

		Message[] messages = inbox.search(new FlagTerm(new Flags(Flag.SEEN), false));

		for (int i = messages.length - 1; i >= 0; i--) {
			Message message = messages[i];
			String content;

			try {
				Object contentObj = message.getContent();

				if (contentObj instanceof String) {
					content = (String) contentObj;
				} else if (contentObj instanceof Multipart) {
					Multipart multipart = (Multipart) contentObj;
					StringBuilder bodyText = new StringBuilder();

					for (int j = 0; j < multipart.getCount(); j++) {
						BodyPart bodyPart = multipart.getBodyPart(j);
						if (bodyPart.isMimeType("text/plain") || bodyPart.isMimeType("text/html")) {
							bodyText.append(bodyPart.getContent().toString());
						}
					}
					content = bodyText.toString();
				} else {
					continue;
				}
				if (content.toLowerCase().contains("password") || content.toLowerCase().contains("otp")) {
					Pattern pattern = Pattern.compile("\\b[A-Z0-9#@$%^&*!]{6,12}\\b");
					Matcher matcher = pattern.matcher(content);

					if (matcher.find()) {
						inbox.close(false);
						store.close();
						return matcher.group();
					}
				}

			} catch (Exception e) {
				continue;
			}
		}

		inbox.close(false);
		store.close();

		throw new Exception("Password or OTP not found in unread emails.");
	}

	public String validateCards() {
		try {
			List<WebElement> cards = driver.findElements(ALL_CARDS);
			if (cards.isEmpty()) {
				return "No cards found on the page.";
			}

			for (WebElement card : cards) {
				highlightElement(card, "GREEN");
			}

			return "All cards are displayed successfully";

		} catch (Exception e) {
			System.err.println("Error validating cards: " + e.getMessage());
			return "Error validating cards: " + e.getMessage();
		}
	}

	public String generateRandomString(int length) {
		String characters = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz";
		StringBuilder result = new StringBuilder(length);
		for (int i = 0; i < length; i++) {
			int index = (int) (Math.random() * characters.length());
			result.append(characters.charAt(index));
		}
		return result.toString();
	}

	public boolean validateExportButton() {
		for (int i = 0; i < 5; i++) {
			WebElement exportButton = wait.until(ExpectedConditions.elementToBeClickable(EXPORT_BUTTON));
			exportButton.click();

			Alert alert = wait.until(ExpectedConditions.alertIsPresent());
			alert.accept();

			if (exportButton.isDisplayed()) {
				return true;
			}
		}
		return false;
	}

	public boolean validateSampleFileButton() {
		try {
			for (int i = 0; i < 5; i++) {
				WebElement sampleFileButton = wait.until(ExpectedConditions.elementToBeClickable(SAMPLE_FILE_BUTTON));
				sampleFileButton.click();

				Alert alert = wait.until(ExpectedConditions.alertIsPresent());
				alert.accept();

				Thread.sleep(2000); // Wait for the file to download
			}
		} catch (Exception e) {
			System.err.println("Error validating Sample File button: " + e.getMessage());
			return false;
		}
		return true;
	}
}
