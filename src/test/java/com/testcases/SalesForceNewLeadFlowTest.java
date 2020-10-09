package com.testcases;

import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import com.pageobjects.SalesForceNewLeadPageObjects;
import com.utils.Commons;
import com.utils.ExcelUtils;
import com.utils.PropertyReaderUtil;
import com.utils.Setup;
import com.aventstack.extentreports.testng.listener.ExtentITestListenerClassAdapter;

@Listeners({ ExtentITestListenerClassAdapter.class })
public class SalesForceNewLeadFlowTest extends Setup {
	
	Commons commons;
	PropertyReaderUtil propUtil = new PropertyReaderUtil();
	SalesForceNewLeadPageObjects salesForceNewLeadPageObjects;
	
	public void initTest() {
		RemoteWebDriver driver = callDriver();
		super.driver = driver;
		commons = new Commons(driver);
		salesForceNewLeadPageObjects = new SalesForceNewLeadPageObjects(driver);
	}	
	
	
	@Test (dataProvider = "StudentData")
	public void createNewLead(String username, String password, String studentProgram) {
		
		//Initialize the driver
		initTest();
		
		//Login
		salesForceNewLeadPageObjects.loginToSalesforce(username, password);
		
		//Navigate to classic view
		salesForceNewLeadPageObjects.navigateToClassicView();
		
		//Create new lead
		salesForceNewLeadPageObjects.createNewLead(studentProgram);
	}
	
	
	@DataProvider(name = "StudentData")
	public static Object[][] StudentData() throws Exception{
		 
        Object[][] testObjArray = ExcelUtils.readExcel(System.getProperty("user.dir")+"\\src\\test\\resources\\TestData.xlsx",
       		 "Sheet1");

        return (testObjArray);
	 }
}
