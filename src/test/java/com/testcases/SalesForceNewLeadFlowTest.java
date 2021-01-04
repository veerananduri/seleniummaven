package com.testcases;

import java.util.Map;

import org.testng.Reporter;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.pageobjects.SalesForceNewLeadPageObjects;
import com.utils.Commons;
import com.utils.ExcelUtils;
import com.utils.PropertyReaderUtil;
import com.utils.Setup;
import com.utils.TestNGGroups;

public class SalesForceNewLeadFlowTest extends Setup {
	
	Commons commons;
	PropertyReaderUtil propUtil = new PropertyReaderUtil();
	SalesForceNewLeadPageObjects salesForceNewLeadPageObjects;
	
	public void initTest() {
		commons = new Commons(getWebDriver());
		salesForceNewLeadPageObjects = new SalesForceNewLeadPageObjects(getWebDriver());
	}	
	
	
	@Test (groups = TestNGGroups.L2, dataProvider = "StudentDataL2")
	public void createNewLeadL2(Map<String, String> data) {
		
		Reporter.log("Test case with data : " + data.get("StudentProgram") + "<br><br>");
		
		//Initialize the driver
		initTest();
		
		//Login
		salesForceNewLeadPageObjects.loginToSalesforce(data.get("Login"), data.get("Password"));
		
		//Navigate to classic view
		salesForceNewLeadPageObjects.navigateToClassicView();
		
		//Create new lead
		salesForceNewLeadPageObjects.createNewLead(data.get("StudentProgram"), data.get("StartDate"));
	}
	
	@Test (groups = TestNGGroups.L1, dataProvider = "StudentDataL1")
	public void createNewLeadL1(Map<String, String> data) {
		
		Reporter.log("Test case with data : " + data.get("StudentProgram") + "<br><br>");
		
		//Initialize the driver
		initTest();
		
		//Login
		salesForceNewLeadPageObjects.loginToSalesforce(data.get("Login"), data.get("Password"));
		
		//Navigate to classic view
		salesForceNewLeadPageObjects.navigateToClassicView();
		
		//Create new lead
		salesForceNewLeadPageObjects.createNewLeadL1(data.get("StudentProgram"));
	}
	
	@DataProvider(name = "StudentDataL2")
	public static Object[][] StudentDataL2() throws Exception{
		 
        Object[][] testObjArray = ExcelUtils.getData(TESTRESOURCES+"/TestData.xlsx",
       		 "Sheet1");

        return (testObjArray);
	 }
	
	@DataProvider(name = "StudentDataL1")
	public static Object[][] StudentDataL1() throws Exception{
		 
        Object[][] testObjArray = ExcelUtils.getData(TESTRESOURCES+"/TestData.xlsx",
       		 "Sheet3");

        return (testObjArray);
	 }
}
