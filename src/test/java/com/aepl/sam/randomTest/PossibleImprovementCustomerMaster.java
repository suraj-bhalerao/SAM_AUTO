package com.aepl.sam.randomTest;

import java.util.function.Supplier;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import com.aepl.sam.base.TestBase;
import com.aepl.sam.constants.CustomerMasterConstants;
import com.aepl.sam.enums.Result;
import com.aepl.sam.pages.CustomerMasterPage;
import com.aepl.sam.utils.CommonMethods;
import com.aepl.sam.utils.ExcelUtility;

public class PossibleImprovementCustomerMaster extends TestBase implements CustomerMasterConstants {
    private CustomerMasterPage customerMasterPage;
    private CommonMethods comm;
    private ExcelUtility excelUtility;

    @BeforeClass
    public void setUp() {
        super.setUp();
        this.customerMasterPage = new CustomerMasterPage(driver, wait);
        this.comm = new CommonMethods(driver, wait);
        this.excelUtility = new ExcelUtility();
        this.excelUtility.initializeExcel(CUSTOMER_MASTER_EXCEL_SHEET);
        logger.info("Setup completed for CustomerMasterPageTest");
    }

    private void executeTest(String testCaseName, String expected, Supplier<String> actualSupplier, SoftAssert softAssert) {
        String actual = "";
        String result = Result.FAIL.getValue();
        logger.info("Executing test case: {}", testCaseName);

        try {
            actual = actualSupplier.get();
            softAssert.assertEquals(actual, expected, testCaseName + " failed!");
            result = expected.equalsIgnoreCase(actual) ? Result.PASS.getValue() : Result.FAIL.getValue();
        } catch (Exception e) {
            logger.error("Error in test case {}: {}", testCaseName, e.getMessage(), e);
            result = Result.ERROR.getValue();
        } finally {
            synchronized (excelUtility) {
                excelUtility.writeTestDataToExcel(testCaseName, expected, actual, result);
            }
        }
    }

    @DataProvider(name = "customerMasterTests")
    public Object[][] provideTests() {
        return new Object[][] {
            {TC_PAGE_LOGO, EXP_LOGO_DISPLAYED, (Supplier<String>) () -> comm.verifyWebpageLogo() ? EXP_LOGO_DISPLAYED : "Logo Not Displayed"},
            {TC_PAGE_TITILE, EXP_PAGE_TITLE, (Supplier<String>) comm::verifyPageTitle},
            {TC_NAV_BAR, EXP_NAV_BAR_URL, (Supplier<String>) customerMasterPage::navBarLink},
            {TC_VALIDATE_BUTTONS, EXP_VALIDATE_BUTTONS, (Supplier<String>) comm::validateButtons},
            {TC_EMPTY_INPUT_ERROR, EXP_EMPTY_INPUT_ERROR, (Supplier<String>) customerMasterPage::emptyInputBoxErrorValidation},
            {TC_WRONG_INPUT_ERROR, EXP_WRONG_INPUT_ERROR, (Supplier<String>) customerMasterPage::wrongInputBoxErrorValidation},
            {TC_ADD_CUSTOMER, EXP_ADD_CUSTOMER, (Supplier<String>) customerMasterPage::addNewCustomer},
            {TC_COMPONENT_TITLE, EXP_COMPONENT_TITLE, (Supplier<String>) comm::validateComponentTitle},
            {TC_SEARCH_CUSTOMER, EXP_SEARCH_CUSTOMER, (Supplier<String>) customerMasterPage::searchCustomer},
            {TC_SEARCH_INPUT_ENABLED, EXP_SEARCH_INPUT_ENABLED, (Supplier<String>) () -> customerMasterPage.isSearchInputEnabled() ? EXP_SEARCH_INPUT_ENABLED : EXP_SEARCH_INPUT_NOT_ENABLED},
            {TC_SEARCH_INPUT_VISIBLE, EXP_SEARCH_INPUT_VISIBLE, (Supplier<String>) () -> customerMasterPage.isSearchInputVisible() ? EXP_SEARCH_INPUT_VISIBLE : EXP_SEARCH_INPUT_NOT_VISIBLE},
            {TC_SEARCH_BUTTON_ENABLED, EXP_SEARCH_BUTTON_ENABLED, (Supplier<String>) () -> customerMasterPage.isSearchButtonEnabled() ? EXP_SEARCH_BUTTON_ENABLED : EXP_SEARCH_BUTTON_NOT_ENABLED},
            {TC_SEARCH_BUTTON_VISIBLE, EXP_SEARCH_BUTTON_VISIBLE, (Supplier<String>) () -> customerMasterPage.isSearchButtonVisible() ? EXP_SEARCH_BUTTON_VISIBLE : EXP_SEARCH_BUTTON_NOT_VISIBLE},
            {TC_EDIT_CUSTOMER, EXP_EDIT_CUSTOMER, (Supplier<String>) () -> { customerMasterPage.editCustomer(); return EXP_EDIT_CUSTOMER; }},
            {TC_EDIT_BUTTON_ENABLED, EXP_EDIT_BUTTON_VISIBLE, (Supplier<String>) () -> customerMasterPage.isEditButtonEnabled() ? EXP_EDIT_BUTTON_VISIBLE : EXP_EDIT_BUTTON_NOT_VISIBLE},
            {TC_EDIT_BUTTON_VISIBLE, EXP_EDIT_BUTTON_VISIBLE, (Supplier<String>) () -> customerMasterPage.isEditButtonDisplayed() ? EXP_EDIT_BUTTON_VISIBLE : EXP_EDIT_BUTTON_NOT_VISIBLE},
            {TC_VALIDATE_CUST, EXP_VALIDATION, (Supplier<String>) () -> { customerMasterPage.validateCustomerTable(); return EXP_VALIDATION; }},
            {TC_DELETE_BUTTON_ENABLED, EXP_DELETE_BUTTON_VISIBLE, (Supplier<String>) () -> customerMasterPage.isDeleteButtonEnabled() ? EXP_DELETE_BUTTON_VISIBLE : EXP_DELETE_BUTTON_NOT_VISIBLE},
            {TC_DELETE_BUTTON_VISIBLE, EXP_DELETE_BUTTON_VISIBLE, (Supplier<String>) () -> customerMasterPage.isDeleteButtonDisplayed() ? EXP_DELETE_BUTTON_VISIBLE : EXP_DELETE_BUTTON_NOT_VISIBLE},
            {TC_DELETE_CUSTOMER, EXP_DELETE_CUSTOMER, (Supplier<String>) () -> { customerMasterPage.deleteCustomer(); return EXP_DELETE_CUSTOMER; }},
            {TC_VALIDATE_COMPONENTS, EXP_VALIDATE_COMPONENTS, (Supplier<String>) comm::validateComponents},
            {TC_PAGINATION, EXP_PAGINATION, (Supplier<String>) () -> { comm.checkPagination(); return EXP_PAGINATION; }},
            {TC_VERSION, EXP_VERSION, (Supplier<String>) comm::checkVersion},
            {TC_COPYRIGHT, EXP_COPYRIGHT, (Supplier<String>) comm::checkCopyright}
        };
    }

    @Test(dataProvider = "customerMasterTests")
    public void runCustomerMasterTests(String testCaseName, String expected, Supplier<String> actualSupplier) {
        SoftAssert softAssert = new SoftAssert();
        executeTest(testCaseName, expected, actualSupplier, softAssert);
        softAssert.assertAll();
    }
}
