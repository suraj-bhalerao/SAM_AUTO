package com.aepl.sam.tests;

import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import com.aepl.sam.base.TestBase;
import com.aepl.sam.constants.CustomerMasterConstants;
import com.aepl.sam.pages.CommonMethods;
import com.aepl.sam.pages.CustomerMasterPage;
import com.aepl.sam.utils.ExcelUtility;

public class CustomerMasterPageTest extends TestBase implements CustomerMasterConstants {
	private CustomerMasterPage customerMasterPage;
	private CommonMethods comm;
	private ExcelUtility excelUtility;
	private SoftAssert softAssert;
	private Executor executor;

	@Override
	@BeforeClass
	public void setUp() {
		super.setUp();
		this.customerMasterPage = new CustomerMasterPage(driver, wait);
		this.comm = new CommonMethods(driver, wait);
		this.excelUtility = new ExcelUtility();
		this.softAssert = new SoftAssert();
		this.excelUtility.initializeExcel(CUSTOMER_MASTER_EXCEL_SHEET);
		this.executor = new Executor(excelUtility, softAssert);
		logger.info("Setup completed for CustomerMasterPageTest");
	}

	@Test(priority = 1)
	public void testCompanyLogo() {
		executor.executeTest(TC_PAGE_LOGO, EXP_LOGO_DISPLAYED,
				() -> comm.verifyWebpageLogo() ? EXP_LOGO_DISPLAYED : "Logo Not Displayed");
	}

	@Test(priority = 2)
	public void testPageTitle() {
		executor.executeTest(TC_PAGE_TITILE, EXP_PAGE_TITLE, comm::verifyPageTitle);
	}

	@Test(priority = 3)
	public void testClickNavBar() {
		executor.executeTest(TC_NAV_BAR, EXP_NAV_BAR_URL, customerMasterPage::navBarLink);
	}

	@Test(priority = 4)
	public void testButtons1() {
		executor.executeTest(TC_VALIDATE_BUTTONS, EXP_VALIDATE_BUTTONS, comm::validateButtons);
	}

	@Test(priority = 5)
	public void testEmptyInputBoxError() {
		executor.executeTest(TC_EMPTY_INPUT_ERROR, EXP_EMPTY_INPUT_ERROR,
				customerMasterPage::emptyInputBoxErrorValidation);
	}

	@Test(priority = 6)
	public void testWrongInputBoxError() {
		executor.executeTest(TC_WRONG_INPUT_ERROR, EXP_WRONG_INPUT_ERROR,
				customerMasterPage::wrongInputBoxErrorValidation);
	}

	@Test(priority = 7)
	public void testAddNewCustomer() {
		executor.executeTest(TC_ADD_CUSTOMER, EXP_ADD_CUSTOMER, customerMasterPage::addNewCustomer);
	}

	@Test(priority = 8)
	public void testComponentTitle() {
		executor.executeTest(TC_COMPONENT_TITLE, EXP_COMPONENT_TITLE, comm::validateComponentTitle);
	}

	@Test(priority = 9)
	public void testButtons2() {
		executor.executeTest(TC_VALIDATE_BUTTONS, EXP_VALIDATE_BUTTONS, comm::validateButtons);
	}

	@Test(priority = 10, enabled = false)
	public void testInputBoxError() {
		executor.executeTest(TC_INPUT_BOX_ERROR, EXP_INPUT_BOX_ERROR, () -> {
			String res = comm.validateInputBoxError();
			if (res.equals(EXP_INPUT_BOX_ERROR)) {
				return res;
			}
			return "Input Box Error Not Displayed";
		});
	}

	@Test(priority = 11)
	public void testSearchCustomer() {
		executor.executeTest(TC_SEARCH_CUSTOMER, EXP_SEARCH_CUSTOMER, customerMasterPage::searchCustomer);
	}

	@Test(priority = 12)
	public void testIsSearchInputEnabled() {
		executor.executeTest(TC_SEARCH_INPUT_ENABLED, EXP_SEARCH_INPUT_ENABLED, () -> {
			return customerMasterPage.isSearchInputEnabled() ? EXP_SEARCH_INPUT_ENABLED : EXP_SEARCH_INPUT_NOT_ENABLED;
		});
	}

	@Test(priority = 13)
	public void testIsSearchInputVisible() {
		executor.executeTest(TC_SEARCH_INPUT_VISIBLE, EXP_SEARCH_INPUT_VISIBLE, () -> {
			return customerMasterPage.isSearchInputVisible() ? EXP_SEARCH_INPUT_VISIBLE : EXP_SEARCH_INPUT_NOT_VISIBLE;
		});
	}

	@Test(priority = 14)
	public void testIsSearchButtonEnabled() {
		executor.executeTest(TC_SEARCH_BUTTON_ENABLED, EXP_SEARCH_BUTTON_ENABLED, () -> {
			return customerMasterPage.isSearchButtonEnabled() ? EXP_SEARCH_BUTTON_ENABLED
					: EXP_SEARCH_BUTTON_NOT_ENABLED;
		});
	}

	@Test(priority = 15)
	public void testIsSearchButtonVisible() {
		executor.executeTest(TC_SEARCH_BUTTON_VISIBLE, EXP_SEARCH_BUTTON_VISIBLE, () -> {
			return customerMasterPage.isSearchButtonVisible() ? EXP_SEARCH_BUTTON_VISIBLE
					: EXP_SEARCH_BUTTON_NOT_VISIBLE;
		});
	}

	@Test(priority = 16)
	public void testEditCustomer() {
		executor.executeTest(TC_EDIT_CUSTOMER, EXP_EDIT_CUSTOMER, () -> {
			customerMasterPage.editCustomer();
			return EXP_EDIT_CUSTOMER;
		});
	}

	@Test(priority = 17)
	public void testIsEditButtonEnabled() {
		executor.executeTest(TC_EDIT_BUTTON_ENABLED, EXP_EDIT_BUTTON_VISIBLE, () -> {
			return customerMasterPage.isEditButtonEnabled() ? EXP_EDIT_BUTTON_VISIBLE : EXP_EDIT_BUTTON_NOT_VISIBLE;
		});
	}

	@Test(priority = 18)
	public void testIsEditButtonDisplayed() {
		executor.executeTest(TC_EDIT_BUTTON_VISIBLE, EXP_EDIT_BUTTON_VISIBLE, () -> {
			return customerMasterPage.isEditButtonDisplayed() ? EXP_EDIT_BUTTON_VISIBLE : EXP_EDIT_BUTTON_NOT_VISIBLE;
		});
	}

	@Test(priority = 19)
	public void testValidateEditedCustomer() {
		executor.executeTest(TC_VALIDATE_CUST, EXP_VALIDATION, () -> {
			customerMasterPage.validateCustomerTable();
			return EXP_VALIDATION;
		});
	}

	@Test(priority = 20)
	public void testIsDeleteButtonEnabled() {
		executor.executeTest(TC_DELETE_BUTTON_ENABLED, EXP_DELETE_BUTTON_VISIBLE, () -> {
			return customerMasterPage.isDeleteButtonEnabled() ? EXP_DELETE_BUTTON_VISIBLE
					: EXP_DELETE_BUTTON_NOT_VISIBLE;
		});
	}

	@Test(priority = 21)
	public void testIsDeleteButtonDisplayed() {
		executor.executeTest(TC_DELETE_BUTTON_VISIBLE, EXP_DELETE_BUTTON_VISIBLE, () -> {
			return customerMasterPage.isDeleteButtonDisplayed() ? EXP_DELETE_BUTTON_VISIBLE
					: EXP_DELETE_BUTTON_NOT_VISIBLE;
		});
	}

	@Test(priority = 22)
	public void testDeleteCustomer() {
		executor.executeTest(TC_DELETE_CUSTOMER, EXP_DELETE_CUSTOMER, () -> {
			customerMasterPage.deleteCustomer();
			return EXP_DELETE_CUSTOMER;
		});
	}

	@Test(priority = 23)
	public void testValidateComponents() {
		executor.executeTest(TC_VALIDATE_COMPONENTS, EXP_VALIDATE_COMPONENTS, comm::validateComponents);
	}

	@Test(priority = 24)
	public void testPagination() {
		executor.executeTest(TC_PAGINATION, EXP_PAGINATION, () -> {
			comm.checkPagination();
			return EXP_PAGINATION;
		});
	}

	@Test(priority = 25)
	public void testVersion() {
		executor.executeTest(TC_VERSION, EXP_VERSION, comm::checkVersion);
	}

	@Test(priority = 26)
	public void testCopyright() {
		executor.executeTest(TC_COPYRIGHT, EXP_COPYRIGHT, comm::checkCopyright);
	}

	@AfterClass
	public void tearDownAssertions() {
		softAssert.assertAll();
	}
}
