package com.aepl.sam.tests;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.aepl.sam.base.TestBase;
import com.aepl.sam.enums.Result;
import com.aepl.sam.pages.GovernmentServerPage;
import com.aepl.sam.utils.CommonMethods;
import com.aepl.sam.utils.ExcelUtility;

public class GovernmentServerPageTest extends TestBase {
	private ExcelUtility excelUtility;
	private GovernmentServerPage govServerPage;
	private CommonMethods comm;
	private final Logger logger = LogManager.getLogger(this.getClass().getSimpleName());

	
	@BeforeClass
	public void setUp() {
		super.setUp();
		this.govServerPage = new GovernmentServerPage(driver, wait, action);
		this.comm = new CommonMethods(driver, wait);
		this.excelUtility = new ExcelUtility();
		excelUtility.initializeExcel("Government_Server_Test");
	}

	@Test(priority = 1)
	public void testCompanyLogo() {
	    String testCaseName = "Verify Company Logo on Webpage";
	    String expected = "Logo Displayed"; 
	    String actual = "";
	    String result = Result.FAIL.getValue(); 

	    logger.info("Executing the test for: " + testCaseName);
	    try {
	        logger.info("Verifying if the company logo is displayed...");
	        boolean isLogoDisplayed = comm.verifyWebpageLogo();
	        actual = isLogoDisplayed ? "Logo Displayed" : "Logo Not Displayed";
	        
	        System.out.println(actual);
	        softAssert.assertEquals(actual, expected, "Company logo verification failed!");
	        result = expected.equalsIgnoreCase(actual) ? Result.PASS.getValue() : Result.FAIL.getValue();
	        logger.info("Result is: " + result);
	    } catch (Exception e) {
	        logger.error("An error occurred while verifying the company logo.", e);
	        e.printStackTrace();
	    } finally {
	        excelUtility.writeTestDataToExcel(testCaseName, expected, actual, result);
	        logger.info("Test case execution completed for: " + testCaseName);
	        System.out.println("Company logo verification completed.");
	        softAssert.assertAll();
	    }
	}


//	@Test(priority = 2)
//	public void testPageTitle() {
//		comm.verifyPageTitle();
//	}
//
//	@Test(priority = 3)
//	public void testClickNavBar() {
////		govServerPage.navBarLink();
//		comm.clickNavBarDeviceUtil(); 
//	}
//
//	@Test(priority = 4)
//	public void testBackButton() { 
//		govServerPage.backButton();
//	} 
//
//	@Test(priority = 3)
//	public void testRefreshButton() {
//		govServerPage.refreshButton();
//
//	}
//
//	@Test(priority = 4)
//	public void testAddGovernmentServer() {
//		String governmentServer = govServerPage.addGovernmentServer();
//
//		System.out.println("Clicked on the goverment server add button : " + governmentServer);
//
//	}
//
//	@Test(priority = 5)
//	public void testFillForm() {
//		govServerPage.manageGovServer("add");
//	}
//
//	// Search and view
//	@Test(priority = 6)
//	public void testSearchAndView() {
//		govServerPage.searchAndView();
//	}
//
//	// Update
//	@Test(priority = 7)
//	public void testUpdateGovServer() {
//		govServerPage.manageGovServer("update");
//	}
//
//	@Test(priority = 8)
//	public void testAddFirmware() {
//		govServerPage.addFirmware();
//	}
//
//	@Test(priority = 9)
//	public void testDeleteGovServer() {
//		govServerPage.deleteGovServer();
//	}
}
