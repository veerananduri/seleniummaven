package com.testcases;

import java.util.Map;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.pageobjects.WaldenUniversityPurchaseCoursePageObjects;
import com.utils.Commons;
import com.utils.ExcelUtils;
import com.utils.PropertyReaderUtil;
import com.utils.Setup;
import com.utils.TestNGGroups;

public class WaldenUniversityPurchaseCourseTest extends Setup {
	Commons commons;
	PropertyReaderUtil propUtil = new PropertyReaderUtil();
	WaldenUniversityPurchaseCoursePageObjects waldenUniversityPurchaseCoursePageObjects;
	
	public void initTest() {
		commons = new Commons(getWebDriver());
		waldenUniversityPurchaseCoursePageObjects = new WaldenUniversityPurchaseCoursePageObjects(getWebDriver());
	}
	
	@Test (groups = TestNGGroups.L3, dataProvider = "CourseData")
	public void walden_purchase_course(Map<String, String> data) {
		
		//Initialize the driver
		initTest();
		
		waldenUniversityPurchaseCoursePageObjects.launchWaldenUniversityUrl();
		
		waldenUniversityPurchaseCoursePageObjects.searchCourse(data);
		
		data = waldenUniversityPurchaseCoursePageObjects.addToCartandCheckout(data);
		
		data = waldenUniversityPurchaseCoursePageObjects.oktaSignupForCheckout(data);
		
		waldenUniversityPurchaseCoursePageObjects.continueCheckout();
		
		waldenUniversityPurchaseCoursePageObjects.clickPaypalPayment(data);
	}
	
	@DataProvider(name = "CourseData")
	public static Object[][] courseData() throws Exception{
		 
        Object[][] testObjArray = ExcelUtils.getData(TESTRESOURCES+"/TestData.xlsx",
       		 "Sheet2");

        return (testObjArray);
	 }
}
