package com.utils;

import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.remote.RemoteWebDriver;

public class Setup {

	protected RemoteWebDriver driver;

	public RemoteWebDriver callDriver() {
		System.setProperty("webdriver.chrome.driver",
				"C:\\Users\\ganga\\eclipse-workspace\\SeleniumTrainingSept2020\\drivers\\chromedriver.exe");
		driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		return driver;
	}

	@AfterMethod (alwaysRun=true)
	public void afterMethod(ITestResult itestResult) {
		if(itestResult.getStatus() == 1) {
			System.out.println("Testcase is Passed");
		} else if (itestResult.getStatus() == 2) {
			System.out.println("Testcase is Failed");
		} else {
			System.out.println("Testcase is Skipped");
		}
		if(driver!=null) {			
			driver.close();
			driver.quit();			
		} else {
			System.out.println("Driver is null");
		}
	}

}
