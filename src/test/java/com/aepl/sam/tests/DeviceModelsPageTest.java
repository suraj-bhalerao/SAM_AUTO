package com.aepl.sam.tests;

import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import com.aepl.sam.base.TestBase;
import com.aepl.sam.constants.Constants;
import com.aepl.sam.constants.DeviceModelConstants;
import com.aepl.sam.pages.DeviceModelsPage;
import com.aepl.sam.utils.CommonMethods;
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

	@Test(priority = 0)
	public void testCompanyLogo() {
		executor.executeTest(TC_LOGO, LOGO_DISPLAYED,
				() -> comm.verifyWebpageLogo() ? LOGO_DISPLAYED : LOGO_NOT_DISPLAYED);
	}

	@Test(priority = 1)
	public void testPageTitle() {
		executor.executeTest(TC_PAGE_TITLE, EXP_PAGE_TITLE, comm::verifyPageTitle);
	}

	@Test(priority = 2)
	public void testButtons() {
		executor.executeTest(TC_VALIDATE_BUTTONS, EXP_VALIDATE_BUTTONS, comm::validateButtons);
	}

	@Test(priority = 3)
	public void testComponentTitles() {
		executor.executeTest(TC_COMPONENT_TITLES, EXP_COMPONENT_TITLES, comm::validateComponents);
	}

	@Test(priority = 4)
	public void navBarLinkTest() {
		executor.executeTest(TC_NAV_BAR_LINK, Constants.DEVICE_LINK, deviceModelsPage::navBarLink);
	}

	@Test(priority = 5)
	public void clickAddDeviceModelTest() {
		executor.executeTest(TC_CLICK_ADD_MODEL, EXP_ADD_MODEL_PAGE, deviceModelsPage::ClickAddDeviceModel);
	}

	@Test(priority = 6)
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

	@Test(priority = 7)
	public void testButtons2() {
		executor.executeTest(TC_VALIDATE_BUTTONS, EXP_VALIDATE_BUTTONS, comm::validateButtons);
	}

	@Test(priority = 8)
	public void testComponentTitles2() {
		executor.executeTest(TC_COMPONENT_TITLES, EXP_COMPONENT_TITLES, comm::validateComponents);
	}

	@Test(priority = 9)
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

	@Test(priority = 10)
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

	@Test(priority = 11)
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

	@Test(priority = 12)
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

	@Test(priority = 13)
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

	@Test(priority = 14)
	public void testPagination() {
		executor.executeTest(TC_PAGINATION, EXP_PAGINATION, () -> {
			comm.checkPagination();
			return EXP_PAGINATION;
		});
	}

	@Test(priority = 15)
	public void testVersion() {
		executor.executeTest(TC_VERSION, Constants.EXP_VERSION_TEXT, comm::checkVersion);
	}

	@Test(priority = 16)
	public void testCopyright() {
		executor.executeTest(TC_COPYRIGHT, Constants.EXP_COPYRIGHT_TEXT, comm::checkCopyright);
	}

	@AfterClass
	public void tearDownAssertions() {
		softAssert.assertAll();
	}
}
