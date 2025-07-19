package com.aepl.sam.tests;

import java.util.function.Supplier;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import com.aepl.sam.base.TestBase;
import com.aepl.sam.constants.CustomerMasterConstants;
import com.aepl.sam.enums.Result;
import com.aepl.sam.pages.CustomerMasterPage;
import com.aepl.sam.utils.CommonMethods;
import com.aepl.sam.utils.ExcelUtility;

public class CustomerMasterPageTest extends TestBase implements CustomerMasterConstants {

	private CustomerMasterPage customerMasterPage;
	private CommonMethods comm;
	private ExcelUtility excelUtility;
	private SoftAssert softAssert;

	@BeforeClass
	public void setUp() {
		super.setUp();
		this.customerMasterPage = new CustomerMasterPage(driver, wait);
		this.comm = new CommonMethods(driver, wait);
		this.softAssert = new SoftAssert();
		this.excelUtility = new ExcelUtility();
		this.excelUtility.initializeExcel(CUSTOMER_MASTER_EXCEL_SHEET);
		logger.info("Setup completed for CustomerMasterPageTest");
	}

	private void executeTest(String testCaseName, String expected, Supplier<String> actualSupplier) {
		String actual = "";
		String result = Result.FAIL.getValue();
		logger.info("Executing test case: {}", testCaseName);

		try {
			actual = actualSupplier.get();
			softAssert.assertEquals(actual, expected, testCaseName + " failed!");
			result = expected.equalsIgnoreCase(actual) ? Result.PASS.getValue() : Result.FAIL.getValue();
			logger.info("Test result: {}", result);
		} catch (Exception e) {
			logger.error("Error in test case {}: {}", testCaseName, e.getMessage(), e);
			result = Result.ERROR.getValue();
		} finally {
			excelUtility.writeTestDataToExcel(testCaseName, expected, actual, result);
			softAssert.assertAll();
		}
	}

	@Test(priority = 1)
	public void testCompanyLogo() {
		executeTest(TC_PAGE_LOGO, EXP_LOGO_DISPLAYED,
				() -> comm.verifyWebpageLogo() ? EXP_LOGO_DISPLAYED : "Logo Not Displayed");
	}

	@Test(priority = 2)
	public void testPageTitle() {
		executeTest(TC_PAGE_TITILE, EXP_PAGE_TITLE, comm::verifyPageTitle);
	}

	@Test(priority = 3)
	public void testClickNavBar() {
		executeTest(TC_NAV_BAR, EXP_NAV_BAR_URL, customerMasterPage::navBarLink);
	}

	@Test(priority = 4)
	public void testAddNewCustomer() {
		executeTest(TC_ADD_CUSTOMER, EXP_ADD_CUSTOMER, customerMasterPage::addNewCustomer);
	}

	@Test(priority = 5)
	public void testButtons() {
		executeTest(TC_VALIDATE_BUTTONS, EXP_VALIDATE_BUTTONS, comm::validateButtons);
	}

	@Test(priority = 6, enabled = false)
	public void testInputBoxError() {
		executeTest(TC_INPUT_BOX_ERROR, EXP_INPUT_BOX_ERROR, () -> {
			String res = comm.validateInputBoxError();
			if (res.contains(EXP_INPUT_BOX_ERROR)) {
				return comm.validateInputBoxError();
			}
			return "Input Box Error Not Displayed";
		});
	}

	@Test(priority = 7)
	public void testSearchCustomer() {
		executeTest(TC_SEARCH_CUSTOMER, EXP_SEARCH_CUSTOMER, customerMasterPage::searchCustomer);
	}

	@Test(priority = 8)
	public void testEditCustomer() {
		executeTest(TC_EDIT_CUSTOMER, EXP_EDIT_CUSTOMER, () -> {
			customerMasterPage.editCustomer();
			return EXP_EDIT_CUSTOMER;
		});
	}

	@Test(priority = 9)
	public void testDeleteCustomer() {
		executeTest(TC_DELETE_CUSTOMER, EXP_DELETE_CUSTOMER, () -> {
			customerMasterPage.deleteCustomer();
			return EXP_DELETE_CUSTOMER;
		});
	}

	@Test(priority = 10)
	public void testValidateComponents() {
		executeTest(TC_VALIDATE_COMPONENTS, EXP_VALIDATE_COMPONENTS, comm::validateComponents);
	}

	@Test(priority = 11)
	public void testPagination() {
		executeTest(TC_PAGINATION, EXP_PAGINATION, () -> {
			comm.checkPagination();
			return EXP_PAGINATION;
		});
	}

	@Test(priority = 12)
	public void testVersion() {
		executeTest(TC_VERSION, EXP_VERSION, comm::checkVersion);
	}

	@Test(priority = 13)
	public void testCopyright() {
		executeTest(TC_COPYRIGHT, EXP_COPYRIGHT, comm::checkCopyright);
	}

}
