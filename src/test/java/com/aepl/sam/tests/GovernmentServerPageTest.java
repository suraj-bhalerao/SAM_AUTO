package com.aepl.sam.tests;

import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import com.aepl.sam.base.TestBase;
import com.aepl.sam.constants.Constants;
import com.aepl.sam.constants.GovernmentServerConstants;
import com.aepl.sam.pages.GovernmentServerPage;
import com.aepl.sam.utils.CommonMethods;
import com.aepl.sam.utils.ExcelUtility;

public class GovernmentServerPageTest extends TestBase implements GovernmentServerConstants {
	private GovernmentServerPage govServerPage;
	private ExcelUtility excelUtility;
	private CommonMethods comm;
	private SoftAssert softAssert;
	private Executor executor;

	@BeforeClass
	public void setUp() {
		super.setUp();
		this.govServerPage = new GovernmentServerPage(driver, wait, action);
		this.comm = new CommonMethods(driver, wait);
		this.excelUtility = new ExcelUtility();
		this.softAssert = new SoftAssert();
		this.executor = new Executor(excelUtility, softAssert);
		excelUtility.initializeExcel(SHEET_NAME);
		logger.info("Setup completed for GovernmentServerPageTest");
	}

	@Test(priority = 1)
	public void testCompanyLogo() {
		executor.executeTest(TC_LOGO, EXP_LOGO_DISPLAYED, () -> comm.verifyWebpageLogo());
	}

	@Test(priority = 2)
	public void testClickNavBar() {
		executor.executeTest(TC_NAV_BAR, EXP_NAV_BAR, govServerPage::navBarLink);
	}

	@Test(priority = 3)
	public void testPageTitle() {
		executor.executeTest(TC_PAGE_TITLE, EXP_PAGE_TITLE, govServerPage::verifyPageTitle);
	}

	@Test(priority = 4)
	public void testComponentTitle() {
		executor.executeTest(TC_COMPONENT_TITLE, EXP_COMPONENT_TITLE, comm::validateComponentTitle);
	}

	@Test(priority = 5)
	public void testButtons() {
		executor.executeTest(TC_VALIDATE_BUTTONS, EXP_VALIDATE_BUTTONS, comm::validateButtons);
	}

	@Test(priority = 6)
	public void testComponents() {
		executor.executeTest(TC_VALIDATE_COMPONENTS, EXP_VALIDATE_COMPONENTS, comm::validateComponents);
	}

	@Test(priority = 7)
	public void testRefreshButton() {
		executor.executeTest(TC_REFRESH, EXP_REFRESH, govServerPage::refreshButton);
	}

	// Search box is enabled

	// Search box is visible

	// Search button is enabled

	// Search button is visible

	// Check pagination on the gov server's list page

	// Check table headers

	// Check table data

	// Check eye buttons are visible enabled for all table data

	// Check delete buttons are visible enabele for all table data

	// Check pagination of the page

	// Click on add government server button - by checking the availability and
	// clickability or else give error

	// Check for page title to verify the - add button is clicked correctly - it
	// must be - {Add Government Servers}

	// Check all input fields are clickable and enabled

	// Check errors for all the input boxes

	// fill the correct data enters

	// Check the submit button's visibility - if no data is entered in all input
	// boxes

	// Click on the submit button if all/ Or jsut two state and abr of state data is
	// entered correctly without any errors in input boxes

	// Verify the given details are added is correctly displyed on the table

	// Click view button in front of the details entered

	// Check all the details get from the table displayed in the input boxes or not

	// Check if no changes in the input boxes then the update button should not be
	// visible

	// fill any data into the input box and then try to update the record

	// scroll down to verify the page title as {Device Firmware List}

	// Verify the buttons

	// Verify the components

	// Search box is enabled

	// Search box is visible

	// Search button is enabled

	// Search button is visible

	// Check table headers

	// Check table data

	// Click to add firmware device

	// Validate the component title - {Firmware Master List}

	// Click on the "X" button to replace the component to the {Device Firmware
	// List}

	// Click to add firmware device

	// Search box is enabled

	// Search box is visible

	// Search button is enabled

	// Search button is visible

	// Check table headers

	// Check table data

	// Check all checkbox buttons are unticked and not selected firsly

	// Check if no checkbox button is selected then submit button should not visible
	// under the same table

	// select any of the checkbox and then scroll down to the submit button and
	// click

	// after submitted the data then validate the component header is visible -
	// {Device Firmware List}

	// Validate the table data that is one which i am selected and submitted
	// previously

	// See for the delete button is visible and clickable in the table data of the
	// table.

	// After clicking the delete button the data should be gone and the "No data
	// found" img should be appeared on the screen

	// Validate the "TOAST" message after clicking the update button.

	// Click back button and then search for the details

	// Validate the details that inputs earlier

	// Come to the gov server list page and click on the firmware master button

	// Verify the buttons

	// Verify the components

	// Click on the add firmware button

	// Validate the component tiltle and the url

	// Validate all the input boxes are visible

	// Validate all errors on input boxes

	// Validate the Release date field should be already selected the currunt date

	// Validate the file upload button is visible

	// Validate the file upload button is clickable

	// Valiadate the submit button should disabled if no data is inputted in the all
	// fields

	// Validate if file is already present then toast message apppears on clicking
	// on submit. if no then successfully submit data

	// VAlidate the latest firmware added is added at the last of the page

	// View and delete buttons should enabled

	// VAlidate after clicking on the latest added bin/firmware it should opens the
	// firmware details page and then validate it is correct

	// VAlidate after clicking on the delete button the added firmware should be
	// delete and the Toast message should appears on the screen

	// Come back to government server page and check paginations

	// Validate the Version
	public void testVersion() {
		executor.executeTest(TC_VERSION, Constants.EXP_VERSION_TEXT, comm::checkVersion);
	}

	// Validate the copyright

	public void testCopyright() {
		executor.executeTest(TC_COPYRIGHT, Constants.EXP_COPYRIGHT_TEXT, comm::checkCopyright);
	}

//	
//
////	@Test(priority = 6)
//	public void testAddGovernmentServer() {
//		executor.executeTest(TC_ADD_SERVER, EXP_ADD_SERVER, () -> {
//			String result = govServerPage.addGovernmentServer();
//			return (result != null && !result.isEmpty()) ? EXP_ADD_SERVER : "Government Server Addition Failed";
//		});
//	}
//
////	@Test(priority = 7)
//	public void testFillForm() {
//		executor.executeTest(TC_FILL_FORM, EXP_FILL_FORM, () -> govServerPage.manageGovServer("add"));
//	}
//
////	@Test(priority = 8)
//	public void testSearchAndView() {
//		executor.executeTest(TC_SEARCH_VIEW, EXP_SEARCH_VIEW, () -> {
//			return govServerPage.searchAndView() ? EXP_SEARCH_VIEW : "Search and View Failed";
//		});
//	}
//
////	@Test(priority = 9)
//	public void testUpdateGovServer() {
//		executor.executeTest(TC_UPDATE, EXP_UPDATE, () -> govServerPage.manageGovServer("update"));
//	}
//
////	@Test(priority = 10)
//	public void testAddFirmware() {
//		executor.executeTest(TC_ADD_FIRMWARE, EXP_ADD_FIRMWARE, () -> {
//			return govServerPage.addFirmware() ? EXP_ADD_FIRMWARE : "Firmware Addition Failed";
//		});
//	}
//
////	@Test(priority = 11)
//	public void testDeleteGovServer() {
//		executor.executeTest(TC_DELETE, EXP_DELETE, govServerPage::deleteGovServer);
//	}
//
////	@Test(priority = 12)
//	public void testPagination() {
//		executor.executeTest(TC_PAGINATION, EXP_PAGINATION, () -> {
//			comm.checkPagination();
//			return EXP_PAGINATION;
//		});
//	}

	@AfterClass
	public void tearDownAssertions() {
		softAssert.assertAll();
	}
}
