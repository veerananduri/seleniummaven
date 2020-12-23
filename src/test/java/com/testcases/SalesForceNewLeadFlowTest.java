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
	
	
	@Test (groups = TestNGGroups.L2, dataProvider = "StudentData")
	public void createNewLead(Map<String, String> data) {
		
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
	
	
	@DataProvider(name = "StudentData")
	public static Object[][] StudentData() throws Exception{
		 
        Object[][] testObjArray = ExcelUtils.getData(TESTRESOURCES+"/TestData.xlsx",
       		 "Sheet1");

        return (testObjArray);
	 }
}
