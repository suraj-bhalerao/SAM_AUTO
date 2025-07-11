package com.aepl.sam.tests;

import java.util.function.Supplier;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import com.aepl.sam.base.TestBase;
import com.aepl.sam.constants.Constants;
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
		executeTest("Verify Company Logo on Webpage", LOGO_DISPLAYED,
				() -> comm.verifyWebpageLogo() ? LOGO_DISPLAYED : LOGO_NOT_DISPLAYED);
	}

	@Test(priority = 2)
	public void testPageTitle() {
		executeTest("Verify Page Title on Webpage", PAGE_TITLE, comm::verifyPageTitle);
	}

	@Test(priority = 3)
	public void testClickNavBar() {
		executeTest("Verify Navigation Bar Click Functionality", CUSTOMER_MASTER_URL, customerMasterPage::navBarLink);
	}

	@Test(priority = 4)
	public void testAddNewCustomer() {
		executeTest("Verify Add New Customer Functionality", CUSTOMER_ADDED_SUCCESS,
				customerMasterPage::addNewCustomer);
	}

	@Test(priority = 5)
	public void testSearchCustomer() {
		executeTest("Verify Search Customer Functionality", CUSTOMER_FOUND, customerMasterPage::searchCustomer);
	}

	@Test(priority = 6)
	public void testEditCustomer() {
		executeTest("Verify Edit Customer Functionality", CUSTOMER_EDITED_SUCCESS, () -> {
			customerMasterPage.editCustomer();
			return CUSTOMER_EDITED_SUCCESS;
		});
	}

	@Test(priority = 7)
	public void testDeleteCustomer() {
		executeTest("Verify Delete Customer Functionality", CUSTOMER_DELETED_SUCCESS, () -> {
			customerMasterPage.deleteCustomer();
			return CUSTOMER_DELETED_SUCCESS;
		});
	}

	@Test(priority = 8)
	public void testValidateComponents() {
		executeTest("Verify Components on Customer Master Page", COMPONENTS_VALIDATED, comm::validateComponents);
	}

	@Test(priority = 9)
	public void testPagination() {
		executeTest("Verify Pagination Functionality", PAGINATION_SUCCESS, () -> {
			comm.checkPagination();
			return PAGINATION_SUCCESS;
		});
	}

	@Test(priority = 10)
	public void testVersion() {
		executeTest("Verify Version Functionality", Constants.EXP_VERSION_TEXT, comm::checkVersion);
	}

	@Test(priority = 11)
	public void testCopyright() {
		executeTest("Verify Copyright Functionality", Constants.EXP_COPYRIGHT_TEXT, comm::checkCopyright);
	}
}
