package com.aepl.sam.pages;

import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.datatransfer.StringSelection;
import java.awt.event.KeyEvent;
import java.time.Duration;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.asserts.SoftAssert;

import com.aepl.sam.actions.CalendarActions;
import com.aepl.sam.actions.MouseActions;
import com.aepl.sam.constants.Constants;
import com.aepl.sam.locators.GovernmentServerPageLocators;
import com.aepl.sam.utils.CommonMethods;
import com.aepl.sam.utils.ConfigProperties;
import com.aepl.sam.utils.RandomGeneratorUtils;
import com.aepl.sam.utils.TableUtils;

public class GovernmentServerPage extends GovernmentServerPageLocators {
	private WebDriver driver;
	private WebDriverWait wait;
	private CalendarActions calAct;
	private LoginPage loginPage;
	private CommonMethods comm;
	private String randomStateName;
	private String randomStateAbr;
	private RandomGeneratorUtils random;
	private TableUtils table;
	private static final Logger logger = LogManager.getLogger(GovernmentServerPage.class);
	private SoftAssert softAssert = new SoftAssert();

	public GovernmentServerPage(WebDriver driver, WebDriverWait wait, MouseActions action) {
		this.driver = driver;
		this.wait = wait;
		this.calAct = new CalendarActions(this.driver, this.wait);
		this.loginPage = new LoginPage(driver, wait);
		this.comm = new CommonMethods(driver, wait);
		this.random = new RandomGeneratorUtils();
		this.table = new TableUtils(wait);
	}

	public Boolean navBarLink() {
		boolean isViewed = false;
		try {
			logger.info("Navigating to Government Server page...");
			WebElement deviceUtil = wait.until(ExpectedConditions.visibilityOfElementLocated(DEVICE_UTILITY));
			softAssert.assertTrue(deviceUtil.getText().equalsIgnoreCase("DEVICE UTILITY"));
			deviceUtil.click();

			WebElement govServer = wait.until(ExpectedConditions.visibilityOfElementLocated(GOVERNMENT_NAV_LINK));
			govServer.click();

			if (driver.getCurrentUrl().equals(Constants.GOV_LINK)) {
				isViewed = true;
			}
			logger.info("Successfully navigated to User Management page.");
		} catch (Exception e) {
			logger.error("Error navigating to User Management: {}", e.getMessage(), e);
		}
		return isViewed;
	}

	public String verifyPageTitle() {
		WebElement pageTitle = driver.findElement(By.className("page-title"));
		comm.highlightElement(pageTitle, "solid purple");
		softAssert.assertEquals(pageTitle, "Government Server", "The title of the page did not matched");
		return pageTitle.getText();
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
				randomStateName = random.generateRandomString(5);
				randomStateAbr = random.generateRandomString(3);
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

	public boolean isSearchBoxEnabled() {
		JavascriptExecutor js = (JavascriptExecutor) driver;
		WebElement searchBox = driver.findElement(By.xpath("//input[contains(@formcontrolname, 'searchInput')]"));
		js.executeScript("arguments[0].scrollIntoView(true);", searchBox);
		comm.highlightElement(searchBox, "solid purple");
		softAssert.assertTrue(searchBox.isEnabled(), "No search box is enabled");
		return searchBox.isEnabled();
	}

	public boolean isSeachBoxDisplayed() {
		JavascriptExecutor js = (JavascriptExecutor) driver;
		WebElement searchBox = driver.findElement(By.xpath("//input[contains(@formcontrolname, 'searchInput')]"));
		js.executeScript("arguments[0].scrollIntoView(true);", searchBox);
		comm.highlightElement(searchBox, "solid purple");
		softAssert.assertTrue(searchBox.isDisplayed(), "No search box is displayed");
		return searchBox.isDisplayed();
	}

	public boolean isSeachButtonEnabled() {
		JavascriptExecutor js = (JavascriptExecutor) driver;
		WebElement searchButton = driver.findElement(By.className("search-btn"));
		js.executeScript("arguments[0].scrollIntoView(true);", searchButton);
		comm.highlightElement(searchButton, "solid purple");
		softAssert.assertTrue(searchButton.isEnabled(), "No search button is enabled");
		return searchButton.isEnabled();
	}

	public boolean isSeachButtonDisplayed() {
		JavascriptExecutor js = (JavascriptExecutor) driver;
		WebElement searchButton = driver.findElement(By.className("search-btn"));
		js.executeScript("arguments[0].scrollIntoView(true);", searchButton);
		comm.highlightElement(searchButton, "solid purple");
		softAssert.assertTrue(searchButton.isDisplayed(), "No search button is displayed");
		return searchButton.isEnabled();
	}

	public List<String> validaterGovernmentServerListTableHeaders() {
		return table.getTableHeaders(By.xpath("//table"));
	}

	// Data Fetched Successfully
	public String searchGovernmentServer() {
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("window.scrollBy(0, -window.innerHeight);");
		WebElement searchBox = driver.findElement(By.xpath("//input[contains(@formcontrolname, 'searchInput')]"));
		searchBox.clear();
		searchBox.sendKeys(Constants.GOV_SERVER_NAME);

		WebElement searchButton = driver.findElement(By.className("search-btn"));
		searchButton.click();

		WebElement toastMessage = wait
				.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//simple-snack-bar/div[1]")));
		comm.highlightElement(toastMessage, "solid purple");
		String message = toastMessage.getText().trim();

		softAssert.assertEquals(message, "Data Fetched Successfully",
				"No toast bar is appeared on screen or not data is searched");
		return message;
	}

	public List<Map<String, String>> validateTableDataOfGovernmentServerTable() {
		List<Map<String, String>> data = table.getTableData(By.xpath("//table"),
				table.getTableHeaders(By.xpath("//table")));

		List<Map<String, String>> returnData = new ArrayList<>();
		if (data != null && !data.isEmpty()) {
			Map<String, String> normalizedRow = new LinkedHashMap<>();
			data.get(0).forEach((k, v) -> normalizedRow.put(k, String.valueOf(v)));
			returnData.add(normalizedRow);
		}

		return returnData;
	}

//	public List<Map<String, String>> validateTableDataOfGovernmentServerTable() {
//		List<Map<String, String>> data = table.getTableData(By.xpath("//table"),
//				table.getTableHeaders(By.xpath("//table")));
//
//		List<Map<String, String>> returnData = new ArrayList<>();
//		if (data != null && !data.isEmpty()) {
//			Map<String, String> normalizedRow = new LinkedHashMap<>();
//
//			data.get(0).forEach((k, v) -> {
//				if (!"ACTION".equalsIgnoreCase(k)) { // Ignore the ACTION column
//					normalizedRow.put(k, String.valueOf(v));
//				}
//			});
//
//			returnData.add(normalizedRow);
//		}
//
//		return returnData;
//	}

	public boolean isEyeButtonsAreVisibleOnTable() {
		return table.areViewButtonsEnabled(By.xpath("//table"));
	}

	public boolean isDeleteButtonsAreVisibleOnTable() {
		return table.areDeleteButtonsEnabled(By.xpath("//table"));
	}

	public boolean isAddGovernmentServerButtonIsVisible() {
		JavascriptExecutor js = (JavascriptExecutor) driver;
		WebElement add_gov = driver.findElement(By.xpath("//button[contains(text(), 'Add Government')]"));
		js.executeScript("arguments[0].scrollIntoView(true);", add_gov);
		comm.highlightElement(add_gov, "solid purple");

		return add_gov.isDisplayed();
	}

	public boolean isAddGovernmentServerButtonIsEnabled() {
		JavascriptExecutor js = (JavascriptExecutor) driver;
		WebElement add_gov = driver.findElement(By.xpath("//button[contains(text(), 'Add Government')]"));
		js.executeScript("arguments[0].scrollIntoView(true);", add_gov);
		return add_gov.isEnabled();
	}

	public String validateClickOnAddGovServerButton() {
		JavascriptExecutor js = (JavascriptExecutor) driver;
		WebElement add_gov = driver.findElement(By.xpath("//button[contains(text(), 'Add Government')]"));
		js.executeScript("arguments[0].scrollIntoView(true);", add_gov);

		add_gov.click();

		return driver.findElement(By.className("page-title")).getText();
	}

	public boolean validateGovernmentServerDetailsInputs() {
		boolean allEnabled = true;
		try {
			WebElement form = wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("form-section")));

			// Expected field names and placeholders
			Map<String, String> expectedFields = new LinkedHashMap<>();
			expectedFields.put("state", "State");
			expectedFields.put("stateCode", "State Abbreviation");
			expectedFields.put("govtIp1", "Govt. IP1");
			expectedFields.put("port1", "Port1");
			expectedFields.put("govtIp2", "Govt. IP2");
			expectedFields.put("port2", "Port2");
			expectedFields.put("stateEnable", "State Enable");

			for (Map.Entry<String, String> entry : expectedFields.entrySet()) {
				String controlName = entry.getKey();
				String placeholder = entry.getValue();

				// Locate input by its formcontrolname
				By inputLocator = By.xpath(".//input[@formcontrolname='" + controlName + "']");
				List<WebElement> inputs = form.findElements(inputLocator);

				softAssert.assertTrue(!inputs.isEmpty(),
						"❌ Input with formcontrolname='" + controlName + "' not found in the form!");

				if (!inputs.isEmpty()) {
					WebElement input = inputs.get(0);
					boolean displayed = input.isDisplayed();
					boolean enabled = input.isEnabled();

					// Verify visibility and enabled state
					softAssert.assertTrue(displayed, "❌ Input '" + placeholder + "' is not visible!");
					softAssert.assertTrue(enabled, "❌ Input '" + placeholder + "' is not enabled!");

					logger.info("✅ Verified input '{}' (formcontrolname='{}') - Visible: {}, Enabled: {}", placeholder,
							controlName, displayed, enabled);

					if (!displayed || !enabled) {
						allEnabled = false;
					}
				}
			}
		} catch (Exception e) {
			logger.error("Error validating Government Server Details form: {}", e.getMessage(), e);
			softAssert.fail("Exception during validation: " + e.getMessage());
			allEnabled = false;
		}
		return allEnabled;
	}

	public String validateSingleInputBox(String fieldName, String inputValue) {
		List<WebElement> inputBoxes = driver.findElements(By.xpath("//input[@formcontrolname='" + fieldName + "']"));
		if (inputBoxes.isEmpty()) {
			throw new NoSuchElementException("No input found for: " + fieldName);
		}
		return validateInputField(inputBoxes.get(0), inputValue);
	}

	private String validateInputField(WebElement inputBox, String inputValue) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
		WebElement submitButton = driver.findElement(By.className("submit-button"));

		try {
			inputBox.clear();
			if (inputValue != null && !inputValue.isEmpty()) {
				inputBox.sendKeys(inputValue);
			}
			submitButton.click();

			WebElement parentField = inputBox.findElement(By.xpath("./ancestor::mat-form-field"));
			WebElement errorElement = wait
					.until(ExpectedConditions.visibilityOfNestedElementsLocatedBy(parentField, By.tagName("mat-error")))
					.get(0);
			return errorElement.getText().trim();

		} catch (org.openqa.selenium.TimeoutException e) {
			return "⚠️ No validation message appeared";
		}
	}

	public boolean isSubmitButtonDisabledWhenRequiredFieldsEmpty() {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));

		// Locate required input boxes
		WebElement stateInput = wait
				.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@formcontrolname='state']")));
		WebElement stateAbrInput = wait.until(
				ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@formcontrolname='stateCode']")));
		WebElement submitButton = wait
				.until(ExpectedConditions.visibilityOfElementLocated(By.className("submit-button")));

		// Clear both required fields
		stateInput.clear();
		stateAbrInput.clear();

		// Small wait to let form validation trigger
		try {
			Thread.sleep(500);
		} catch (InterruptedException e) {
			Thread.currentThread().interrupt();
		}

		boolean isDisabled = !submitButton.isEnabled();
		logger.info("Submit button is {} when required fields are empty", isDisabled ? "disabled" : "enabled");

		return isDisabled;
	}

	public String validateDuplicateStateEntry(String existingStateName, String existingStateCode) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(8));

		try {
			// Locate the input fields
			WebElement reload = wait.until(
					ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".action-button.reload-button")));

			reload.click();

			Thread.sleep(200);

			WebElement stateInput = wait.until(
					ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@formcontrolname='state']")));
			// Clear and enter existing data
			stateInput.click();
			stateInput.clear();
			stateInput.sendKeys(existingStateName);

			WebElement stateCodeInput = wait.until(
					ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@formcontrolname='stateCode']")));
			stateCodeInput.click();
			stateCodeInput.clear();
			stateCodeInput.sendKeys(existingStateCode);

			WebElement submitButton = wait
					.until(ExpectedConditions.elementToBeClickable(By.className("submit-button")));

			// Submit the form
			submitButton.click();

			// Wait for toast/snackbar message to appear
			WebElement toastMessage = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(
					"//simple-snack-bar/div | //div[contains(@class,'mat-mdc-snack-bar-label') or contains(@class,'toast-message')]")));

			String messageText = toastMessage.getText().replaceAll("(?i)close$", "").trim();
			logger.info("Toast message appeared: {}", messageText);
			return messageText;

		} catch (TimeoutException e) {
			logger.error("❌ No toast message appeared after submitting duplicate state data");
			return "No toast message found";
		} catch (Exception e) {
			logger.error("❌ Error validating duplicate entry: {}", e.getMessage(), e);
			return "Error: " + e.getMessage();
		}
	}

}
