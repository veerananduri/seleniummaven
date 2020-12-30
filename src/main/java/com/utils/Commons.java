package com.utils;

import java.security.SecureRandom;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.TimeZone;
import java.util.stream.IntStream;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
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

	public boolean isElementAvailable(By locator) {
		boolean flag = false;
		try {
			WebElement element = driver.findElement(locator);
			if (element.isDisplayed())
				flag = true;
			else
				return flag;
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
	 * 
	 * @param i
	 */
	public void sleep(int i) {
		try {
			Thread.sleep(i * 1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	// Show steps in the report
	public void reportLog(String message) {
		test.log(Status.INFO, message);
		Reporter.log(message + "<br>");
	}

	// Select by visible text
	public void selectByVisibleText(By locator, String text) {
//		WebDriverWait wait = new WebDriverWait(driver, 10);
//		wait.until(ExpectedConditions.elementToBeClickable(locator));
		WebElement element = driver.findElement(locator);
		Select select = new Select(element);
		select.selectByVisibleText(text);
	}

	// Select by value
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

	// Get Text
	public String getText(By locator) {
		WebDriverWait wait = new WebDriverWait(driver, 10);
		wait.until(ExpectedConditions.elementToBeClickable(locator));
		WebElement element = driver.findElement(locator);
		return element.getText();
	}
	
	//Perform with Actions class
	public void clickElementWithAction(By locator) {
		Actions actions = new Actions(driver);
		WebElement element = driver.findElement(locator);
		actions.moveToElement(element).click().perform();
		sleep(1);
		actions.sendKeys(Keys.DOWN).build().perform();
		actions.sendKeys(Keys.ENTER).build().perform();
	}
	
	public void actionClickElement(By locator) {
		Actions actions = new Actions(driver);
		WebElement element = driver.findElement(locator);
		sleep(1);
		actions.moveToElement(element).click().perform();
		sleep(1);
	}
	
	public void jseClickElement(By locator) {
		JavascriptExecutor jse = (JavascriptExecutor) driver;
		WebElement ele = driver.findElement(locator);
		jse.executeScript("arguments[0].click();", ele);
	}
	
	public int getListSize(By locator) {
		try {
			if(isElementDisplayed(locator)) {
				return driver.findElements(locator).size();
			} else {
				return 0;
			}
		} catch (Exception e) {
			return 0;
		}
	}
	
	/**
	 * Method to return date of birth
	 * @return mmddyyyy
	 */
	public String dateOfBirth() {
		Calendar calendar = Calendar.getInstance(TimeZone.getTimeZone("UTC"));
		int year = 1950 + new SecureRandom().nextInt(2000 - 1950 + 1);
		calendar.set(Calendar.YEAR, year);
		int day = 1 + new SecureRandom().nextInt(calendar.getActualMaximum(Calendar.DAY_OF_WEEK_IN_MONTH));
		calendar.set(Calendar.DAY_OF_WEEK_IN_MONTH, day);
		int month = 1 + new SecureRandom().nextInt(calendar.getActualMaximum(Calendar.DAY_OF_MONTH));
		calendar.set(Calendar.DAY_OF_MONTH, month);
		Date time = calendar.getTime();
		String date = new SimpleDateFormat("mmddyyyy").format(time);
		return date;
	}
	
	public Map<String, String> validateTableDataandReturnAsKeyValuePair(By locator1, By locator2, int count1) {
		int count2 = 0;
		if(count1 > 0) {
			count2 = count1;
		} else {
			count1 = driver.findElements(locator1).size();
			count2 = driver.findElements(locator2).size();
		}
		List<String> keyData = new ArrayList<>();
		List<String> valuesData = new ArrayList<>();
		
		for(int i=0; i<count1; i++) {
			List<WebElement> info = driver.findElements(locator1);
			try {
				keyData.add(info.get(i).getText());
			} catch(Exception e) {
				if(info.get(i).getText().isEmpty()) {
					keyData.add("No Keys Identified");
				}
			}
		}
		
		for(int i=0; i<count2; i++) {
			List<WebElement> info = driver.findElements(locator2);
			try {
				valuesData.add(info.get(i).getText());
			} catch(Exception e) {
				if(info.get(i).getText().isEmpty()) {
					valuesData.add("No Values Identified");
				}
			}
		}
		
		return IntStream.range(0, keyData.size())
				.collect(
						LinkedHashMap::new, 
						(m, i) -> m.put(keyData.get(i), valuesData.get(i)),
						Map::putAll
				);
	}
	
	public List<WebElement> getListValues(By element) {
		if(isElementDisplayed(element)) {
			return driver.findElements(element);
		} else {
			return null;
		}		
	}
}
