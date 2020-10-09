package com.utils;

import org.openqa.selenium.By;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class Commons extends Setup {

	protected RemoteWebDriver driver;
	
	public Commons(RemoteWebDriver driver) {		
		this.driver = driver;
	}
	
	public void typeValue(By locator, String value) {
		WebDriverWait wait = new WebDriverWait(driver, 10);
		wait.until(ExpectedConditions.elementToBeClickable(locator));
		driver.findElement(locator).click();
		driver.findElement(locator).clear();
		driver.findElement(locator).sendKeys(value);		
	}
	
	public void clickElement(By locator) {
		WebDriverWait wait = new WebDriverWait(driver, 10);
		wait.until(ExpectedConditions.elementToBeClickable(locator));
		driver.findElement(locator).click();
	}
	
	public void quitDriver() {
		driver.close();
		driver.quit();
	}

	/**
	 * If you pass 1 it is 1sec
	 * @param i
	 */
	public void sleep(int i) {		
		try {
			Thread.sleep(i * 1000);
		} catch (InterruptedException e) {			
			e.printStackTrace();
		}
	}
	
	
	public void focusToAnotherWindow() {
		// Store the current window handle
		String winHandleBefore = driver.getWindowHandle();

		// Perform the click operation that opens new window

		// Switch to new window opened
		for(String winHandle : driver.getWindowHandles()){
		    driver.switchTo().window(winHandle);
		}
	}
	 
}
