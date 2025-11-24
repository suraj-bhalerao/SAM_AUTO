package com.aepl.sam.tests;

import org.openqa.selenium.JavascriptExecutor;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import com.aepl.sam.base.TestBase;
import com.aepl.sam.constants.SimBatchDataDetailsConstants;
import com.aepl.sam.pages.CommonMethods;
import com.aepl.sam.pages.SimBatchDataDetailsPage;
import com.aepl.sam.utils.ExcelUtility;

public class SimBatchDataDetailsPageTest extends TestBase implements SimBatchDataDetailsConstants {
	private ExcelUtility excelUtility;
	private SimBatchDataDetailsPage simBatch;
	private CommonMethods comm;
	private SoftAssert softAssert;
	private Executor executor;

	@Override
	@BeforeClass
	public void setUp() {
		super.setUp();
		this.comm = new CommonMethods(driver, wait);
		this.simBatch = new SimBatchDataDetailsPage(driver, wait, comm);
		this.excelUtility = new ExcelUtility();
		this.softAssert = new SoftAssert();
		excelUtility.initializeExcel(SHEET_NAME);
		this.executor = new Executor(excelUtility, softAssert);
	}

	@Test(priority = 1)
	public void testCompanyLogo() {
		executor.executeTest(TC_COMPANY_LOGO, EXP_LOGO_DISPLAYED, comm::verifyWebpageLogo);
	}

	@Test(priority = 2)
	public void testNavBarLink() {
		executor.executeTest(TC_NAV_BAR_LINK, EXP_NAV_BAR_LINK, simBatch::navBarLink);
	}

	@Test(priority = 3)
	public void testPageTitle() {
		executor.executeTest(TC_PAGE_TITLE, EXP_PAGE_TITLE, simBatch::verifyPageTitle);
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
	public void testDownloadSampleFile() {
		executor.executeTest(TC_DOWNLOAD_SAMPLE_FILE, EXP_DOWNLOAD_SAMPLE_FILE, comm::clickSampleFileButton);
	}

	@Test(priority = 8)
	public void testUploadBoxIsCorrect() {
		executor.executeTest(TC_UPLOAD_BOX_CORRECT, EXP_UPLOAD_BOX_CORRECT, simBatch::validateCorrectBox);
	}

	@Test(priority = 9)
	public void testErrorMessageOfTheInputBox() {
		executor.executeTest(TC_ERROR_MSG_INPUT_BOX, EXP_ERROR_MSG_INPUT_BOX,
				() -> simBatch.isInputBoxHaveProperValidations("error-msg"));
	}

	@Test(priority = 10)
	public void testUploadButtonIsEnabled() {
		executor.executeTest(TC_UPLOAD_BUTTON_ENABLED, EXP_UPLOAD_BUTTON_ENABLED, simBatch::isUploadButtonIsEnabled);
	}

	@Test(priority = 11)
	public void testUploadFile() {
		executor.executeTest(TC_UPLOAD_FILE, EXP_UPLOAD_FILE, simBatch::validateUpload);
	}

	@Test(priority = 12)
	public void testSubmitButton() {
		executor.executeTest(TC_SUBMIT_BUTTON, EXP_SUBMIT_BUTTON, simBatch::validateSubmitButton);
	}

	@Test(priority = 16)
	public void testUploadSimDataDetailsComponentsTableHeaders() {
		executor.executeTest(TC_UPLOAD_TABLE_HEADERS, EXP_UPLOAD_TABLE_HEADERS,
				() -> simBatch.validateTableHeaders("upload"));
	}

	@Test(priority = 17)
	public void testDuplicateICCIDInUploadedExcelSheetTableHeaders() {
		executor.executeTest(TC_DUPLICATE_ICCID_HEADERS, EXP_DUPLICATE_ICCID_HEADERS,
				() -> simBatch.validateTableHeaders("duplicate"));
	}

	@Test(priority = 18)
	public void testICCIDNotPresentInSensoriseDatabaseTableHeaders() {
		executor.executeTest(TC_NOT_PRESENT_ICCID_HEADERS, EXP_NOT_PRESENT_ICCID_HEADERS,
				() -> simBatch.validateTableHeaders("not present"));
	}

	@Test(priority = 19)
	public void testICCIDNotPresentInSensoriseDatabaseExportButton() {
		executor.executeTest(TC_EXPORT_BUTTON_NOT_PRESENT, EXP_EXPORT_BUTTON, simBatch::validateExportButton);
	}

	@Test(priority = 20)
	public void testDuplicateICCIDInUploadedExcelSheetExportButton() {
		executor.executeTest(TC_EXPORT_BUTTON_DUPLICATE, EXP_EXPORT_BUTTON, simBatch::validateExportButton);
	}

	@Test(priority = 21)
	public void testUploadSimDataDetailsComponentsExportButton() {
		executor.executeTest(TC_EXPORT_BUTTON_UPLOAD, EXP_EXPORT_BUTTON, simBatch::validateExportButton);
	}

	@Test(priority = 22)
	public void testManualUploadButtonIsVisible() {
		executor.executeTest(TC_MANUAL_UPLOAD_VISIBLE, EXP_MANUAL_UPLOAD_VISIBLE,
				simBatch::isManualUploadButtonsVisible);
	}

	@Test(priority = 23)
	public void testManualUploadButtonIsClickable() {
		executor.executeTest(TC_MANUAL_UPLOAD_CLICKABLE, EXP_MANUAL_UPLOAD_CLICKABLE,
				simBatch::isManualUploadButtonsClickable);
	}

	@Test(priority = 24)
	public void testManualUploadClickAndOpen() {
		executor.executeTest(TC_MANUAL_UPLOAD_CLICKED_OPENED, EXP_MANUAL_UPLOAD_CLICKED_OPENED,
				simBatch::manualUploadButtonClickedAndOpened);
	}

	@Test(priority = 27)
	public void testInputBoxEnabled() {
		executor.executeTest(TC_INPUT_BOX_ENABLED, EXP_INPUT_BOX_ENABLED, simBatch::isInputBoxEnabled);
	}

	@Test(priority = 28)
	public void testEmptyInputValidation() {
		executor.executeTest(TC_EMPTY_INPUT_VALIDATION, EXP_EMPTY_INPUT_VALIDATION,
				() -> simBatch.isInputBoxHaveProperValidations(" "));
	}

	@Test(priority = 29)
	public void testShortInputValidation() {
		executor.executeTest(TC_SHORT_INPUT_VALIDATION, EXP_SHORT_INPUT_VALIDATION,
				() -> simBatch.isInputBoxHaveProperValidations("shortText"));
	}

	@Test(priority = 30)
	public void testLongInputValidation() {
		executor.executeTest(TC_LONG_INPUT_VALIDATION, EXP_LONG_INPUT_VALIDATION,
				() -> simBatch.isInputBoxHaveProperValidations("thisIsMoreThan20CharactersInput"));
	}

	@Test(priority = 31)
	public void testSpecialCharValidation() {
		executor.executeTest(TC_SPECIAL_CHAR_VALIDATION, EXP_SPECIAL_CHAR_VALIDATION,
				() -> simBatch.isInputBoxHaveProperValidations("Invalid@#%CharsInput"));
	}

	@Test(priority = 32)
	public void testSubmitButtonEnabled() {
		executor.executeTest(TC_SUBMIT_BUTTON_ENABLED, EXP_SUBMIT_BUTTON_ENABLED, simBatch::isSubmitButtonEnabled);
	}

	@Test(priority = 33)
	public void testClickSubmitButton() {
		executor.executeTest(TC_CLICK_SUBMIT_BUTTON, EXP_CLICK_SUBMIT_BUTTON, simBatch::clickSubmitButton);
	}

	@Test(priority = 35)
	public void testPaginationofSimDataDetailsWholePage2() {
		executor.executeTest(TC_PAGINATION, EXP_PAGINATION, () -> {
			try {
				((JavascriptExecutor) driver).executeScript("window.scrollTo(0, 0);");
				Thread.sleep(500);
				comm.checkPagination();
				return true;
			} catch (Exception e) {
				return false;
			}
		});
	}

	@Test(priority = 36)
	public void testVersion() {
		executor.executeTest(TC_VERSION, EXP_VERSION, comm::checkVersion);
	}

	@Test(priority = 37)
	public void testCopyright() {
		executor.executeTest(TC_COPYRIGHT, EXP_COPYRIGHT, comm::checkCopyright);
	}

	@AfterClass
	public void tearDownAssertions() {
		softAssert.assertAll();
	}
}
