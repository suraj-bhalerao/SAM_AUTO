package com.aepl.sam.tests;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import com.aepl.sam.base.TestBase;
import com.aepl.sam.constants.DealearsManagementConstants;
import com.aepl.sam.pages.DealearsManagementPage;
import com.aepl.sam.utils.CommonMethods;
import com.aepl.sam.utils.ExcelUtility;

public class DealearsManagementPageTest extends TestBase implements DealearsManagementConstants {
	private ExcelUtility excelUtility;
	private DealearsManagementPage dealerPage;
	private CommonMethods comm;
	private SoftAssert softAssert;
	private Executor executor;

	@BeforeClass
	public void setUp() {
		super.setUp();
		this.comm = new CommonMethods(driver, wait);
		this.dealerPage = new DealearsManagementPage(driver, wait, comm);
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
}
