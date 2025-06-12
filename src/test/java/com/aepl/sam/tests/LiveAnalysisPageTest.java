package com.aepl.sam.tests;

import org.testng.annotations.BeforeClass;

import com.aepl.sam.base.TestBase;
import com.aepl.sam.pages.LiveAnalysisPage;
import com.aepl.sam.utils.CommonMethods;
import com.aepl.sam.utils.ExcelUtility;

public class LiveAnalysisPageTest extends TestBase {
	private LiveAnalysisPage liveAnalysis ;
	private ExcelUtility excelUtility;
	private CommonMethods comm;
	
	@BeforeClass
	public void setUp() {
		super.setUp();
		this.liveAnalysis = new LiveAnalysisPage();
		this.comm = new CommonMethods(driver, wait);
		this.excelUtility = new ExcelUtility();
		excelUtility.initializeExcel("Live_Analysis_Test");
	}
}
