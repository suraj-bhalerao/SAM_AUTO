package com.aepl.sam.tests;

import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import com.aepl.sam.base.TestBase;
import com.aepl.sam.constants.DealearsManagementConstants;
import com.aepl.sam.pages.CommonMethods;
import com.aepl.sam.pages.DealersManagementPage;
import com.aepl.sam.utils.ExcelUtility;

public class DealersManagementPageTest extends TestBase implements DealearsManagementConstants {
	private ExcelUtility excelUtility;
	private DealersManagementPage dealerPage;
	private CommonMethods comm;
	private SoftAssert softAssert;
	private Executor executor;

	@Override
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
		executor.executeTest(TC_COMPANY_LOGO, EXP_LOGO_DISPLAYED, () -> comm.verifyWebpageLogo());
	}

	@Test(priority = 2)
	public void testNavBarLink() {
		executor.executeTest(TC_NAV_BAR_LINK, EXP_NAV_BAR_LINK, dealerPage::navBarLink);
	}

	@Test(priority = 3)
	public void testPageTitle() {
		executor.executeTest(TC_PAGE_TITLE, EXP_PAGE_TITLE, dealerPage::verifyPageTitle);
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
	public void testSearchButtonIsEnabled() {
		executor.executeTest(TC_SEARCH_BUTTON_ENABLED, EXP_SEARCH_BUTTON_ENABLED, dealerPage::isSearchButtonEnabled);
	}

	@Test(priority = 8)
	public void testSearchBoxIsEnabled() {
		executor.executeTest(TC_SEARCH_BOX_ENABLED, EXP_SEARCH_BOX_ENABLED, dealerPage::isSearchBoxEnabled);
	}

	@Test(priority = 9)
	public void testSearchBoxByMultipleInputs() {
		executor.executeTest(TC_SEARCH_BOX_MULTIPLE_INPUTS, EXP_SEARCH_BOX_MULTIPLE_INPUTS,
				dealerPage::validateSearchBoxWithMultipleInputs);
	}

	@Test(priority = 10)
	public void testTableHeadersOfDealerManagement() {
		executor.executeTest(TC_TABLE_HEADERS, EXP_TABLE_HEADERS, dealerPage::validateTableHeaders);
	}

	@Test(priority = 11)
	public void testTableDataOfDealerManagement() {
		executor.executeTest(TC_TABLE_DATA, EXP_TABLE_DATA, dealerPage::validateTableData);
	}

	@Test(priority = 12)
	public void testViewButtonsEnabled() {
		executor.executeTest(TC_VIEW_BUTTONS, EXP_VIEW_BUTTONS, dealerPage::validateViewButtons);
	}

	@Test(priority = 13)
	public void testDeleteButtonsStatusWise() {
		executor.executeTest(TC_DELETE_BUTTONS, EXP_DELETE_BUTTONS, dealerPage::validateDeleteButtons);
	}

	@Test(priority = 14)
	public void testPaginationOnDealersManagementPage() {
		executor.executeTest(TC_PAGINATION, EXP_PAGINATION, () -> {
			try {
				comm.checkPagination();
				return true;
			} catch (Exception e) {
				logger.error("Pagination test failed: {}", e.getMessage(), e);
				return false;
			}
		});
	}

	@Test(priority = 15)
	public void testAddDealersButtonEnabled() {
		executor.executeTest(TC_ADD_DEALER_BUTTON_ENABLED, EXP_ADD_DEALER_BUTTON_ENABLED,
				dealerPage::isAddDealerButtonEnabled);
	}

	@Test(priority = 16)
	public void testAddDealersButtonVisible() {
		executor.executeTest(TC_ADD_DEALER_BUTTON_VISIBLE, EXP_ADD_DEALER_BUTTON_VISIBLE,
				dealerPage::isAddDealerButtonVisible);
	}

	@Test(priority = 17)
	public void testClickOnAddDealerBtn() {
		executor.executeTest(TC_CLICK_ADD_DEALER_BUTTON, EXP_CLICK_ADD_DEALER_BUTTON, dealerPage::clickAddDealerButton);
	}

	@Test(priority = 18)
	public void testAllInputBoxesEnabled() {
		executor.executeTest(TC_ALL_INPUT_BOXES, EXP_ALL_INPUT_BOXES, dealerPage::testAllFormFields);
	}

	@Test(priority = 19)
	public void testDealerFormValidations() {
		executor.executeTest(TC_FORM_VALIDATIONS, EXP_FORM_VALIDATIONS, dealerPage::testAllFormFieldsErrors);
	}

	@Test(priority = 20)
	public void testSubmitButtonVisibleIfNoDataInputed() {
		executor.executeTest(TC_SUBMIT_BUTTON_NO_DATA, EXP_SUBMIT_BUTTON_NO_DATA,
				dealerPage::isSubmitButtonIsVisibleIfNoDataIsInputed);
	}

	@Test(priority = 21)
	public void testSubmitButton() {
		executor.executeTest(TC_SUBMIT_BUTTON, EXP_SUBMIT_BUTTON, dealerPage::isDataSubmittedSuccessfully);
	}

	@AfterClass
	public void tearDownAssertions() {
		softAssert.assertAll();
	}
}
