package com.aepl.sam.tests;

import java.util.List;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import com.aepl.sam.base.TestBase;
import com.aepl.sam.constants.DealearsManagementConstants;
import com.aepl.sam.pages.DealersManagementPage;
import com.aepl.sam.utils.CommonMethods;
import com.aepl.sam.utils.ExcelUtility;

public class DealersManagementPageTest extends TestBase implements DealearsManagementConstants {
	private ExcelUtility excelUtility;
	private DealersManagementPage dealerPage;
	private CommonMethods comm;
	private SoftAssert softAssert;
	private Executor executor;

	@BeforeClass
	public void setUp() {
		super.setUp();
		this.comm = new CommonMethods(driver, wait);
		this.dealerPage = new DealersManagementPage(driver, wait, comm);
		this.excelUtility = new ExcelUtility();
		this.softAssert = new SoftAssert();
		excelUtility.initializeExcel(SHEET_NAME);
		this.executor = new Executor(excelUtility, softAssert);
	}

	@Test(priority = 1)
	public void testCompanyLogo() {
		executor.executeTest("Test Company logo", true, () -> comm.verifyWebpageLogo() ? true : false);
	}

	@Test(priority = 2)
	public void testNavBarLink() {
		executor.executeTest("Test Nav Bar Link for {Dealer Management}", true, dealerPage::navBarLink);
	}

	@Test(priority = 3)
	public void testPageTitle() {
		executor.executeTest("Test page title for {Sim Batch Data Details}", "Sensorise SIM Data Details",
				dealerPage::verifyPageTitle);
	}

	@Test(priority = 4)
	public void testComponentTitle() {
		executor.executeTest("Test Page Component Title", "SIM Data Details", () -> comm.validateComponentTitle());
	}

	@Test(priority = 5)
	public void testButtons() {
		executor.executeTest("Test all button on page {Sim Batch Data Details}",
				"All buttons are displayed and enabled successfully.", () -> comm.validateButtons());
	}

	@Test(priority = 6)
	public void testComponents() {
		executor.executeTest("Test All Components on the page {Sim Batch Data Details}",
				"All components are displayed and validated successfully.", () -> comm.validateComponents());
	}

	// validate the search button
	@Test(priority = 7)
	public void testSearchButtonIsEnabled() {
		executor.executeTest("Test search button is enabled? ", true, () -> dealerPage.isSearchButtonEnabled());
	}

	// validate the search box is enabled
	@Test(priority = 8)
	public void testSearchBoxIsEnabled() {
		executor.executeTest("Test search box is enabled? ", true, () -> dealerPage.isSearchBoxEnabled());
	}

	// validate the search box with data provider - give multiple inputs
	@Test(priority = 9)
	public void testSearchBoxByMultipleInputs() {
		executor.executeTest("Test input box with multiple inputs", true,
				() -> dealerPage.validateSearchBoxWithMultipleInputs());
	}

	// validate the table headers
	@Test(priority = 10)
	public void testTableHeadersOfDealerManagement() {
		List<String> expectedHeaders = List.of("FULL NAME", "EMAIL", "MOBILE NO.", "CREATED BY", "STATUS", "ACTION");

		executor.executeTest("Test Table headers of {Dealer Management Page}", expectedHeaders,
				dealerPage::validateTableHeaders);
	}

	// validate table data. if no data then check for no data image
	@Test(priority = 11)
	public void testTableDataOfDealerManagement() {
		executor.executeTest("Validate table data of Dealer Management", true, dealerPage::validateTableData);
	}

	// validate all view buttons should be enabled
	@Test(priority = 12)
	public void testViewButtonsEnabled() {
		executor.executeTest("Validate View Buttons in Dealer Management", true, dealerPage::validateViewButtons);
	}

	@Test(priority = 13)
	public void testDeleteButtonsStatusWise() {
		executor.executeTest("Validate Delete Buttons in Dealer Management", true, dealerPage::validateDeleteButtons);
	}

//	@Test(priority = )
//	public void testMobileNumbersOfDealerManagement() {
//		executor.executeTest("Validate mobile numbers in Dealer Management table", true, () -> {
//			dealerPage.validateMobileNumbers();
//			return true;
//		});
//	}
//
//	@Test(priority = )
//	public void testEmailsOfDealerManagement() {
//		executor.executeTest("Validate emails in Dealer Management table", true, () -> {
//			dealerPage.validateEmails();
//			return true;
//		});
//	}

	// ✅ validate pagination
	@Test(priority = 14)
	public void testPaginationOnDealersManagementPage() {
		executor.executeTest("Test Pagination", true, () -> {
			try {
				comm.checkPagination();
				return true; // ✅ success case
			} catch (Exception e) {
				logger.error("Pagination test failed: {}", e.getMessage(), e);
				return false; // ✅ fail case
			}
		});
	}

	// validate add dealers button enabled
	@Test(priority = 15)
	public void testAddDealersButtonEnabled() {
		executor.executeTest("Test add dealer button enabled", true, () -> dealerPage.isAddDealerButtonEnabled());
	}

	// validate add dealers button visible
	@Test(priority = 16)
	public void testAddDealersButtonVisible() {
		executor.executeTest("Test add dealer button enabled", true, () -> dealerPage.isAddDealerButtonVisible());
	}

	// validate clicking on add dealer button opens valid page using component title
	@Test(priority = 17)
	public void testClickOnAddDealerBtn() {
		executor.executeTest("Test Add Dealer Button", "Save Dealers Details", dealerPage::clickAddDealerButton);
	}

//	 validate all input boxes enabled and visible
	@Test(priority = 18)
	public void testAllInputBoxesEnabled() {
		executor.executeTest("Test all input boxes for adding new dealer details", true, dealerPage::testAllFormFields);
	}

	// validate all error messages for all input boxes
	@Test(priority = 19)
	public void testDealerFormValidations() {
		executor.executeTest("Test all input boxes and validations for dealer form", true,
				dealerPage::testAllFormFieldsErrors);
	}

	// validate save button visible if no or all input boxes empty
	@Test(priority = 20)
	public void testSubmitButtonVisibleIfNoDataInputed() {
		executor.executeTest("Test submit button is visible on if not data is visible", true,
				dealerPage::isSubmitButtonIsVisibleIfNoDataIsInputed);
	}

	// validate save button visible if all input box are filled
	@Test(priority = 21)
	public void testSubmitButton() {
		executor.executeTest("Test the submit button", true, dealerPage::isDataSubmittedSuccessfully);
	}
}
