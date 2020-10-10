package com.testcases;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.pageobjects.SalesForceNewLeadPageObjects;
import com.utils.Commons;
import com.utils.ExcelUtils;
import com.utils.PropertyReaderUtil;
import com.utils.Setup;

public class SalesForceNewLeadFlowTest extends Setup {
	
	Commons commons;
	PropertyReaderUtil propUtil = new PropertyReaderUtil();
	SalesForceNewLeadPageObjects salesForceNewLeadPageObjects;
	
	public void initTest() {
		commons = new Commons(getWebDriver());
		salesForceNewLeadPageObjects = new SalesForceNewLeadPageObjects(getWebDriver());
	}	
	
	
	@Test (dataProvider = "StudentData")
	public void createNewLead(String username, String password, String studentProgram, String date) {
		
		//Initialize the driver
		initTest();
		
		//Login
		salesForceNewLeadPageObjects.loginToSalesforce(username, password);
		
		//Navigate to classic view
		salesForceNewLeadPageObjects.navigateToClassicView();
		
		//Create new lead
		salesForceNewLeadPageObjects.createNewLead(studentProgram, date);
	}
	
	
	@DataProvider(name = "StudentData")
	public static Object[][] StudentData() throws Exception{
		 
        Object[][] testObjArray = ExcelUtils.readExcel(System.getProperty("user.dir")+"\\src\\test\\resources\\TestData.xlsx",
       		 "Sheet1");

        return (testObjArray);
	 }
}
