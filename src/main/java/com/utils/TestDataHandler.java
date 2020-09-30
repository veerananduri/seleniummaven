package com.utils;

import org.testng.annotations.DataProvider;

public class TestDataHandler {

	@DataProvider(name = "Authentication")
	public static Object[][] Authentication() throws Exception{
		 
        Object[][] testObjArray = ExcelUtils.readExcel("C:\\Users\\ganga\\eclipse-workspace\\seleniummaven\\src\\test\\resources\\TestData.xlsx",
       		 "Sheet1");

        return (testObjArray);
	 }
}
