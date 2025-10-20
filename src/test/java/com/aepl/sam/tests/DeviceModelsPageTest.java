package com.aepl.sam.tests;

import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.openqa.selenium.By;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import com.aepl.sam.base.TestBase;
import com.aepl.sam.constants.Constants;
import com.aepl.sam.constants.DeviceModelConstants;
import com.aepl.sam.pages.CommonMethods;
import com.aepl.sam.pages.DeviceModelsPage;
import com.aepl.sam.utils.ExcelUtility;

public class DeviceModelsPageTest extends TestBase implements DeviceModelConstants {
	private ExcelUtility excelUtility;
	private DeviceModelsPage deviceModelsPage;
	private CommonMethods comm;
	private SoftAssert softAssert;
	private Executor executor;

	@BeforeClass
	public void setUp() {
		super.setUp();
		this.comm = new CommonMethods(driver, wait);
		this.deviceModelsPage = new DeviceModelsPage(driver, wait, comm);
		this.excelUtility = new ExcelUtility();
		this.softAssert = new SoftAssert();
		this.executor = new Executor(excelUtility, softAssert);
		excelUtility.initializeExcel(DEVICE_MODELS_EXCEL_SHEET);
	}

	@Test(priority = 1)
	public void testCompanyLogo() {
		executor.executeTest(TC_LOGO, LOGO_DISPLAYED,
				() -> comm.verifyWebpageLogo() ? LOGO_DISPLAYED : LOGO_NOT_DISPLAYED);
	}

	@Test(priority = 2)
	public void testPageTitle() {
		executor.executeTest(TC_PAGE_TITLE, EXP_PAGE_TITLE, comm::verifyPageTitle);
	}

	@Test(priority = 3)
	public void testButtons() {
		executor.executeTest(TC_VALIDATE_BUTTONS, EXP_VALIDATE_BUTTONS, comm::validateButtons);
	}

	@Test(priority = 4)
	public void testComponents() {
		executor.executeTest(TC_COMPONENT_TITLES, EXP_COMPONENT_TITLES, comm::validateComponents);
	}

	@Test(priority = 5)
	public void navBarLinkTest() {
		executor.executeTest(TC_NAV_BAR_LINK, Constants.DEVICE_LINK, deviceModelsPage::navBarLink);
	}

	// Page title test case
	@Test(priority = 6)
	public void testPageTitleOfTheDeviceModelPage() {
		executor.executeTest("Test the page title of the device model page", "Device Models",
				deviceModelsPage::ValidatePageTitle);
	}

	// Add Device model visibility
	@Test(priority = 7)
	public void testAddDeviceModelButtonIsVisible() {
		executor.executeTest("Test the add device model button is visible", true,
				deviceModelsPage::isAddDeviceModelButtonVisible);
	}

	// Add Device model button enability
	@Test(priority = 8)
	public void testAddDeviceModelButtonIsEnabled() {
		executor.executeTest("Test the add device model button is enabled", true,
				deviceModelsPage::isAddDeviceModelButtonEnable);
	}

	@Test(priority = 9)
	public void clickAddDeviceModelTest() {
		executor.executeTest(TC_CLICK_ADD_MODEL, EXP_ADD_MODEL_PAGE, deviceModelsPage::ClickAddDeviceModel);
	}

	// Validate the component title for the page after it gets clicked
	@Test(priority = 10)
	public void testComponentTitleOfAddDeviceModelPage() {
		executor.executeTest("Test the component title of the add device model page", "Fill Device Model Details",
				deviceModelsPage::validateComponentTitle);
	}

	// Validate the error messages for the empty inputs and all input errors
	@Test(priority = 11, dataProvider = "fieldValidationData", dataProviderClass = DeviceModelsPage.class)
	public void testInputValidations(By locator, String input, String expectedError) {
		executor.executeTest("Field Validation", expectedError,
				() -> deviceModelsPage.isInputBoxHaveProperValidations(locator, input));
	}

	// Validate the submit button is disabled if there is no input in all fields
	@Test(priority = 12)
	public void testSubmitButtonDisabledWhenAllFieldsEmpty() {
		executor.executeTest("Test the submit button is disabled if no input boxes are filled", true,
				deviceModelsPage::isSubmitButtonIsDisabled);
	}

	@Test(priority = 13)
	public void addModelTest() {
		executor.executeTest(TC_ADD_MODEL, EXP_MODELS_PAGE, () -> {
			try {
				return deviceModelsPage.NewInputFields("add");
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			return "Not able to add the user";
		});
	}

	@Test(priority = 14)
	public void testButtons2() {
		executor.executeTest(TC_VALIDATE_BUTTONS, EXP_VALIDATE_BUTTONS, comm::validateButtons);
	}

	@Test(priority = 15)
	public void testComponentTitles2() {
		executor.executeTest(TC_COMPONENT_TITLES, EXP_COMPONENT_TITLES, comm::validateComponents);
	}

	// Validate the search input box is visible
	@Test(priority = 16)
	public void testSearchInputBoxIsVisible() {
		executor.executeTest("Test the search input box is visible", true, deviceModelsPage::isSearchInputVisible);
	}

	// Validate the search input box is enabled
	@Test(priority = 17)
	public void testSearchInputBoxIsEnabled() {
		executor.executeTest("Test the search input box is enabled", true, deviceModelsPage::isSearchInputEnabled);
	}

	// Validate the search button is visible
	@Test(priority = 18)
	public void testSearchButtonIsVisible() {
		executor.executeTest("Test the search button is visible", true, deviceModelsPage::isSearchButtonVisible);
	}

	// Validate the search button is enabled
	@Test(priority = 19)
	public void testSearchButtonIsEnabled() {
		executor.executeTest("Test the search button is enabled", true, deviceModelsPage::isSearchButtonEnabled);
	}

	@Test(priority = 20)
	public void searchModelTest() {
		executor.executeTest(TC_SEARCH_MODEL, EXP_MODELS_PAGE, () -> {
			try {
				return deviceModelsPage.searchModel();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			return null;
		});
	}

	// Validate the table headers of the device model list table
	@Test(priority = 21)
	public void testTableHeadersOfDeviceModelListTable() {
		List<String> actualHeaders = Arrays.asList("MODEL CODE", "MODEL NAME", "MODEL SERIAL SEQUENCE",
				"HARDWARE VERSION", "ACTION");
		executor.executeTest("Test the table headers", actualHeaders, deviceModelsPage::validateTableHeaders);
	}

	// Validate the table data on the page of device model list table
	/** this below test is passed but the data that this method gets is real time dynamic
	 *  so it does not able to validate the actual and expected data 
	 *  
	 *  NOTE : modify in future 
	 *  
	 *  */
//	@Test(priority = 22)
	public void testTableDataOfDeviceModelListTable() {
		Map<String, String> expectedRowPatterns = new LinkedHashMap<>();
		expectedRowPatterns.put("MODEL CODE", "[A-Za-z]{6}"); // 6-letter code
		expectedRowPatterns.put("MODEL NAME", ".*"); // any text
		expectedRowPatterns.put("MODEL SERIAL SEQUENCE", "\\d+"); // numbers only
		expectedRowPatterns.put("HARDWARE VERSION", "\\d+"); // numbers only
		expectedRowPatterns.put("ACTION", "visibility"); // exact match

		List<Map<String, String>> expectedPatterns = Collections.singletonList(expectedRowPatterns);
		executor.executeTest("Test table data of the Device Model List with regex", expectedPatterns,
				() -> deviceModelsPage.validateTableDataWithRegex(expectedPatterns));
	}

	@Test(priority = 23)
	public void viewModelTest() {
		executor.executeTest(TC_VIEW_MODEL, EXP_VIEW_MODEL_PAGE, () -> {
			try {
				return deviceModelsPage.viewModel();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			return null;
		});
	}

	// Validate the update button is disabled default if not changes is done
	/** change the false to true in future */
	@Test(priority = 24)
	public void testUpdateButtonIsDisabled() {
		executor.executeTest("Test update button is disabled if no input is added while updating", false,
				deviceModelsPage::isUpdateButtonEnabled);
	}

	@Test(priority = 25)
	public void updateModelTest() {
		executor.executeTest(TC_UPDATE_MODEL, EXP_MODELS_PAGE, () -> {
			try {
				return deviceModelsPage.NewInputFields("update");
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			return null;
		});
	}

	@Test(priority = 26)
	public void searchModelTest2() {
		executor.executeTest(TC_SEARCH_MODEL_2, EXP_MODELS_PAGE, () -> {
			try {
				return deviceModelsPage.searchModel2();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			return null;
		});
	}

	@Test(priority = 27)
	public void deleteModelTest() {
		executor.executeTest(TC_DELETE_MODEL, EXP_MODELS_PAGE, () -> {
			try {
				return deviceModelsPage.DeleteModel();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			return null;
		});
	}

	@Test(priority = 28)
	public void testPagination() {
		executor.executeTest(TC_PAGINATION, EXP_PAGINATION, () -> {
			comm.checkPagination();
			return EXP_PAGINATION;
		});
	}

	@Test(priority = 29)
	public void testVersion() {
		executor.executeTest(TC_VERSION, Constants.EXP_VERSION_TEXT, comm::checkVersion);
	}

	@Test(priority = 30)
	public void testCopyright() {
		executor.executeTest(TC_COPYRIGHT, Constants.EXP_COPYRIGHT_TEXT, comm::checkCopyright);
	}

	@AfterClass
	public void tearDownAssertions() {
		softAssert.assertAll();
	}
}
