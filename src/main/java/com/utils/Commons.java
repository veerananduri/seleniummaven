package com.utils;


import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Reporter;

import com.aventstack.extentreports.Status;

public class Commons extends Setup {

	protected WebDriver driver;
	
	public Commons(WebDriver webDriver) {
		this.driver = webDriver;
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
	
	public boolean isElementDisplayed(By locator) {
		boolean flag = false;
		WebDriverWait wait = new WebDriverWait(driver, 5);
		try {
			wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
			flag = true;
		} catch (Exception e) {
			return flag;
		}
		return flag;
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

	//Show steps in the report
	public void reportLog(String message) {
		test.log(Status.INFO, message);
		Reporter.log(message + "<br>");
	}
	
	//Select by visible text
	public void selectByVisibleText(By locator, String text) {
		WebDriverWait wait = new WebDriverWait(driver, 10);
		wait.until(ExpectedConditions.elementToBeClickable(locator));
		WebElement element = driver.findElement(locator);
		Select select = new Select(element);
		select.selectByVisibleText(text);
	}
	
	//Select by value
	public void selectByValue(By locator, String value) {
		WebDriverWait wait = new WebDriverWait(driver, 10);
		wait.until(ExpectedConditions.elementToBeClickable(locator));
		WebElement element = driver.findElement(locator);
		Select select = new Select(element);
		select.selectByValue(value);
	}

	// Select by index
	public void selectByIndex(By locator, int index) {
		WebDriverWait wait = new WebDriverWait(driver, 10);
		wait.until(ExpectedConditions.elementToBeClickable(locator));
		WebElement element = driver.findElement(locator);
		Select select = new Select(element);
		select.selectByIndex(index);
	}
	
	//Get Text
	public String getText(By locator) {
		WebDriverWait wait = new WebDriverWait(driver, 10);
		wait.until(ExpectedConditions.elementToBeClickable(locator));
		WebElement element = driver.findElement(locator);
		return element.getText();
	}
}
