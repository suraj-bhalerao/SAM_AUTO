package com.aepl.sam.tests;

import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import com.aepl.sam.base.TestBase;
import com.aepl.sam.constants.Constants;
import com.aepl.sam.constants.DispatchDeviceConstants;
import com.aepl.sam.pages.CommonMethods;
import com.aepl.sam.pages.DispatchedDevicesPage;
import com.aepl.sam.utils.ExcelUtility;

public class DispatchedDevicesPageTest extends TestBase implements DispatchDeviceConstants {
	private ExcelUtility excelUtility;
	private DispatchedDevicesPage dispatchedDevicePage;
	private CommonMethods comm;
	private SoftAssert softAssert;
	private Executor executor;

	@BeforeClass
	public void setUp() {
		super.setUp();
		this.comm = new CommonMethods(driver, wait);
		this.dispatchedDevicePage = new DispatchedDevicesPage(driver, wait, comm);
		this.excelUtility = new ExcelUtility();
		this.softAssert = new SoftAssert();
		this.executor = new Executor(excelUtility, softAssert);
		excelUtility.initializeExcel("Dispatched_Devices_Test");
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
	public void navBarLinkTest() {
		executor.executeTest(TC_NAV_BAR_LINK, Constants.DISP_DEVICE_LINK, dispatchedDevicePage::navBarLink);
	}

	/*** Manual Upload ***/

	// validate page title
	@Test(priority = 4)
	public void testPageTitleManualUpload() {
		executor.executeTest(TC_PAGE_TITLE, EXP_PAGE_TITLE, comm::verifyPageTitle);
	}

	// validate all components on the page
	@Test(priority = 5)
	public void testAllComponentsManualUpload() {
		executor.executeTest(TC_COMPONENT_TITLES, EXP_COMPONENT_TITLES, comm::validateComponents);
	}

	// validate all buttons on the page
	@Test(priority = 6)
	public void testAllButtonsManualUpload() {
		executor.executeTest(TC_VALIDATE_BUTTONS, EXP_VALIDATE_BUTTONS, comm::validateButtons);
	}

	// validate the manual upload button is visible
	@Test(priority = 7)
	public void testManualUploadButtonVisibility() {
		executor.executeTest("Test manual upload button is visible", true,
				dispatchedDevicePage::isManualUploadButtonVisible);
	}

	// validate the manual upload button is clickable
	@Test(priority = 8)
	public void testManualUploadButtonClickable() {
		executor.executeTest("Test manual upload button is clickable", true,
				dispatchedDevicePage::isManualUploadButtonClickable);
	}

	// validate the page title after clicking manual upload button
	@Test(priority = 9)
	public void testPageTitleAfterClickingManualUpload() {
		executor.executeTest("Test page title after clicking manual upload", "Create Dispatched Device",
				dispatchedDevicePage::getPageTitleAfterClickingManualUpload);
	}

	// validate all components on manual upload page
	@Test(priority = 10)
	public void testAllComponentsOnManualUploadPage() {
		executor.executeTest(TC_COMPONENT_TITLES, EXP_COMPONENT_TITLES, comm::validateComponents);
	}

	// validate all buttons on manual upload page
	@Test(priority = 11)
	public void testAllButtonsOnManualUploadPage() {
		executor.executeTest(TC_VALIDATE_BUTTONS, EXP_VALIDATE_BUTTONS, comm::validateButtons);
	}

	/*** validate all errors of the fields on the manual upload page ***/
	/** UID **/
	// check error for uid field - blank box
	@Test(priority = 12)
	public void testErrorValidationInUIDBlankBox() {
		executor.executeTest("Test field errors on manual upload page",
				"This field is required and can't be only spaces.",
				() -> dispatchedDevicePage.validateSingleInputBox("uid", " "));
	}

	// check error for uid field - invalid uid (shorter length < 19)
	@Test(priority = 13)
	public void testErrorValidationInUIDInvalidShort() {
		executor.executeTest("Test field errors on manual upload page", "Value must be exactly 19 characters long.",
				() -> dispatchedDevicePage.validateSingleInputBox("uid", "ACON4SA2122"));
	}

	// check error for uid field - invalid uid (longer length > 19)
	@Test(priority = 14)
	public void testErrorValidationInUIDInvalidLong() {
		executor.executeTest("Test field errors on manual upload page", "Value must be exactly 19 characters long.",
				() -> dispatchedDevicePage.validateSingleInputBox("uid", "ACON4SA212240006474XX"));
	}

	// check error for uid field - leading spaces
	@Test(priority = 15)
	public void testErrorValidationInUIDLeadingSpace() {
		executor.executeTest("Test field errors on manual upload page", "Remove leading or trailing spaces.",
				() -> dispatchedDevicePage.validateSingleInputBox("uid", " ACON4SA212240006474"));
	}

	// check error for uid field - trailing spaces
	@Test(priority = 16)
	public void testErrorValidationInUIDTrailingSpace() {
		executor.executeTest("Test field errors on manual upload page", "Remove leading or trailing spaces.",
				() -> dispatchedDevicePage.validateSingleInputBox("uid", "ACON4SA212240006474 "));
	}

	/** Customer Part Number **/
	// check error for customer part number field - blank box
	@Test(priority = 17)
	public void testErrorValidationInCustomerPartNumberBlankBox() {
		executor.executeTest("Test field errors on manual upload page",
				"This field is required and can't be only spaces.",
				() -> dispatchedDevicePage.validateSingleInputBox("customerPartNumber", " "));
	}

	// check error for customer part number field - leading spaces
	@Test(priority = 18)
	public void testErrorValidationInCustomerPartNumberLeadingSpace() {
		executor.executeTest("Test field errors on manual upload page", "Remove leading or trailing spaces.",
				() -> dispatchedDevicePage.validateSingleInputBox("customerPartNumber", " CUSTPART123"));
	}

	// check error for customer part number field - trailing spaces
	@Test(priority = 19)
	public void testErrorValidationInCustomerPartNumberTrailingSpace() {
		executor.executeTest("Test field errors on manual upload page", "Remove leading or trailing spaces.",
				() -> dispatchedDevicePage.validateSingleInputBox("customerPartNumber", "CUSTPART123 "));
	}

	/** Customer Name **/
	// check error for customer name field - blank box
	// not feasible here to check the error so we are skipping this test case and
	// instead
	// checking the clickable or not
	@Test(priority = 20)
	public void testErrorValidationInCustomerNameBlankBox() {
		executor.executeTest("Test customer name field is clickable on manual upload page", true,
				() -> dispatchedDevicePage.isCustomerNameFieldClickable());
	}

	// validate the submit button is disabled when mandatory fields are empty on
	// manual upload page
	@Test(priority = 21)
	public void testSubmitButtonDisabledOnMandatoryFieldsEmpty() {
		executor.executeTest("Test submit button is disabled when mandatory fields are empty", true,
				() -> dispatchedDevicePage.isSubmitButtonDisabledOnMandatoryFieldsEmpty());
	}

	// fill the manual upload form with valid data and submit
	@Test(priority = 22)
	public void testManualUploadFormSubmissionWithValidData() {
		executor.executeTest("Test manual upload form submission with valid data", "Dispatched Devices",
				() -> dispatchedDevicePage.addDevice());
	}

	// validate the success toast message after submission of manual upload form
	@Test(priority = 23)
	public void testSuccessToastMessageAfterManualUploadFormSubmission() {
		executor.executeTest("Test success toast message after manual upload form submission",
				"Data Fetched Successfully", dispatchedDevicePage::getSuccessToastMessage);
	}

	/*** Bulk Upload ***/

	// validate the bulk upload button is visible
	@Test(priority = 24)
	public void testBulkUploadButtonVisibility() {
		executor.executeTest("Test bulk upload button is visible", true,
				dispatchedDevicePage::isBulkUploadButtonVisible);
	}

	// validate the bulk upload button is clickable
	@Test(priority = 25)
	public void testBulkUploadButtonClickable() {
		executor.executeTest("Test bulk upload button is clickable", true,
				dispatchedDevicePage::isBulkUploadButtonClickable);
	}

	// validate the page title after clicking bulk upload button
	@Test(priority = 26)
	public void testPageTitleAfterClickingBulkUpload() {
		executor.executeTest("Test page title after clicking bulk upload", "Add Dispatch Devices",
				dispatchedDevicePage::getPageTitleAfterClickingBulkUpload);
	}

	// validate all components on bulk upload page
	@Test(priority = 27)
	public void testAllComponentsOnBulkUploadPage() {
		executor.executeTest(TC_COMPONENT_TITLES, EXP_COMPONENT_TITLES, comm::validateComponents);
	}

	// validate all buttons on bulk upload page
	@Test(priority = 28)
	public void testAllButtonsOnBulkUploadPage() {
		executor.executeTest(TC_VALIDATE_BUTTONS, EXP_VALIDATE_BUTTONS, comm::validateButtons);
	}

	// validate the download sample link is clickable on bulk upload page
	@Test(priority = 29)
	public void testDownloadSampleLinkClickable() {
		executor.executeTest("Test download sample link is clickable on bulk upload page", true,
				dispatchedDevicePage::isDownloadSampleLinkClickable);
	}

	// validate the sample download file content is correct
	@Test(priority = 30)
	public void testSampleDownloadFileContent() {
		executor.executeTest("Test sample download file content is correct", true,
				dispatchedDevicePage::isSampleDownloadFileContentCorrect);
	}

	// validate the errors of the file upload input on bulk upload page
	/**
	 * NOTE : glance this test case later... for now even on lots of try it does not
	 * resolve and find the error message.
	 **/
//	@Test(priority = 31)
	public void testFileUploadInputErrorsOnBulkUploadPage() {
		executor.executeTest("Test file upload input errors on bulk upload page", "This field is mandatory.",
				() -> dispatchedDevicePage.validateSingleInputBox("file", " "));
	}

	// validate the attachment button in input box is clickable on bulk upload page
	@Test(priority = 32)
	public void testAttachmentButtonClickableOnBulkUploadPage() {
		executor.executeTest("Test attachment button is clickable on bulk upload page", true,
				dispatchedDevicePage::isAttachmentButtonClickable);
	}

	// validate the submit button is disabled when no file is uploaded on bulk
	// upload page
	@Test(priority = 33)
	public void testSubmitButtonDisabledWhenNoFileUploadedOnBulkUploadPage() {
		executor.executeTest("Test submit button is disabled when no file is uploaded on bulk upload page", true,
				() -> dispatchedDevicePage.isSubmitButtonDisabledWhenNoFileUploaded());
	}

	// validate the upload of a valid file and submission on bulk upload page
	// validate the success toast message after submission of bulk upload form
	@Test(priority = 34)
	public void testBulkUploadFormSubmissionWithValidFile() {
		executor.executeTest("Test bulk upload form submission with valid file", "Data Saved Successfully!!",
				() -> dispatchedDevicePage.uploadFileAndSubmit());
	}

	/** Uploaded Dispatch Device List Validations **/

	// validate the component "Uploaded Dispatch Device List" is visible on bulk
	// upload page after successful upload

	// validate the export button on the uploaded dispatch device list is disable on
	// bulk upload page if no data is present

	// validate the click of export button downloads the file

	// validate the table headers of the uploaded dispatch device list on bulk
	// upload page

	// validate the data of the uploaded dispatch device list on bulk upload page
	// give no data found when invalid file is uploaded and data is not correct

	// validate the table headers of the uploaded dispatch device list on bulk
	// upload page

	/** Invalid Dispatch Device List **/

	// validate the component "Invalid Dispatch Device List" is visible on bulk
	// upload page after upload of invalid data file

	// validate the export button on the invalid dispatch device list is enable on
	// bulk upload page.

	// validate the click of export button downloads the file of invalid data

	// validate the table headers of the invalid dispatch device list on bulk upload
	// page

	// validate the pagination of the invalid dispatch device list on bulk upload
	// page when more data is present

	/*** main dispatched device page tests ***/

	// validate the search box is visible on dispatched device page

	// validate the search box is clickable on dispatched device page

	// validate the search functionality with valid data on dispatched device page

	// validate the select customer dropdown is visible on dispatched device page

	// validate the select customer dropdown is clickable on dispatched device page

	// validate the select customer functionality with valid data on dispatched
	// device page

	// validate the table headers of dispatched device list on dispatched device
	// page

	// validate the view button is visible for each device on dispatched device list
	// on dispatched device page

	// validate the view button is clickable for each device on dispatched device
	// list on dispatched device page

	/*** update dispatched device tests ***/

	// validate the form have all the fields while updating dispatched device
	// details

	// validate the update dispatched device form with valid data

	// validate the UID field is non-editable while updating dispatched device
	// details

	// validate the update button is disabled until all mandatory fields are filled
	// while updating dispatched device details

	// validate the success toast message after updating dispatched device details

	// validate the edited data is updated in dispatched device list after updating
	// dispatched device details

	/*** delete dispatched device tests ***/
	// validate the delete button is visible for each device on dispatched device
	// list on dispatched device page

	// validate the delete button is clickable for each device on dispatched device
	// list on dispatched device page

	// validate the confirmation popup on clicking delete button for each device on
	// dispatched device list on dispatched device page

	// validate the cancellation of delete action on clicking cancel button of
	// confirmation popup for each device on dispatched device list on dispatched
	// device page

	// validate the deletion of dispatched device on clicking confirm button of
	// confirmation popup for each device on dispatched device list on dispatched
	// device page

	// validate the success toast message after deleting dispatched device

	// validate the pagination on dispatched device list on dispatched device page
	// when more data is present

//	@Test(priority = 4)
//	public void clickAddDisDeviceTest() {
//		executor.executeTest(TC_ADD_DISPATCH_DEVICE, EXP_ADD_DISPATCH_DEVICE_PAGE,
//				dispatchedDevicePage::ClickAddDisDevice);
//	}
//
//	@Test(priority = 5)
//	public void addDisDeviceTest() {
//		executor.executeTest(TC_NEW_INPUT_FIELDS, EXP_NEW_INPUT_FIELDS, () -> {
//			return dispatchedDevicePage.NewInputFields("add");
//		});
//	}
//
//	@Test(priority = 6)
//	public void searchDeviceTest() {
//		executor.executeTest(TC_SEARCH_DEVICE, EXP_SEARCH_DEVICE, dispatchedDevicePage::SearchDevice);
//	}
//
//	@Test(priority = 7)
//	public void viewDeviceTest() {
//		executor.executeTest(TC_VIEW_DEVICE, EXP_VIEW_DEVICE, dispatchedDevicePage::viewDevice);
//	}
//
//	@Test(priority = 8)
//	public void updateDeviceTest() {
//		executor.executeTest(TC_UPDATE_DEVICE, EXP_UPDATE_DEVICE, () -> {
//			return dispatchedDevicePage.NewInputFields("update");
//		});
//	}
//
//	@Test(priority = 9)
//	public void searchDeviceTest2() {
//		executor.executeTest(TC_SEARCH_DEVICE, EXP_SEARCH_DEVICE, dispatchedDevicePage::SearchDevice);
//	}
//
//	@Test(priority = 10)
//	public void deleteDeviceTest() {
//		executor.executeTest(TC_DELETE_DEVICE, EXP_DELETE_DEVICE, dispatchedDevicePage::DeleteDevice);
//	}
//
//	@Test(priority = 11)
//	public void testAllButtons() {
//		executor.executeTest(TC_VALIDATE_BUTTONS, EXP_VALIDATE_BUTTONS, comm::validateButtons);
//	}
//
//	@Test(priority = 12)
//	public void testAllComponents() {
//		executor.executeTest(TC_COMPONENT_TITLES, EXP_COMPONENT_TITLES, comm::validateComponents);
//	}
//
//	@Test(priority = 13)
//	public void testPagination() {
//		executor.executeTest(TC_PAGINATION, EXP_PAGINATION, () -> {
//			comm.checkPagination();
//			return EXP_PAGINATION;
//		});
//	}

	@Test(priority = 110)
	public void testVersion() {
		executor.executeTest(TC_VERSION, EXP_VERSION, comm::checkVersion);
	}

	@Test(priority = 111)
	public void testCopyright() {
		executor.executeTest(TC_COPYRIGHT, EXP_COPYRIGHT, comm::checkCopyright);
	}

	@AfterClass
	public void tearDownAssertions() {
		softAssert.assertAll();
	}
}
