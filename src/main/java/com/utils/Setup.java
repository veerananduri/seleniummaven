package com.utils;

import org.testng.ITestResult;
import org.testng.Reporter;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.service.ExtentTestManager;
import com.framework.Constants;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import javax.imageio.ImageIO;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import io.github.bonigarcia.wdm.WebDriverManager;
import ru.yandex.qatools.ashot.AShot;
import ru.yandex.qatools.ashot.Screenshot;
import ru.yandex.qatools.ashot.shooting.ShootingStrategies;


public class Setup implements Constants {
	
	protected static WebDriver driver;
	ExtentReports extentReports = new ExtentReports();
	static Map<Integer, ExtentTest> extentTestMap = new HashMap<>();
	public static ThreadLocal<WebDriver> wd = new ThreadLocal<WebDriver>();
	public static ExtentTest test;

	static WebDriver callDriver() {
		String browser = PropertyReaderUtil.getProperty("browser");
		
		if(browser.equalsIgnoreCase("Chrome")) {
			/*
			 * System.setProperty("webdriver.chrome.driver",
			 * System.getProperty("user.dir")+"\\drivers\\chromedriver.exe"); 
			 * driver = new ChromeDriver();
			 */
			WebDriverManager.chromedriver().setup();
			ChromeOptions options = new ChromeOptions();
			options.addArguments("--disable-infobars");
			driver = new ChromeDriver();
			driver.manage().window().maximize();
			driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		} else if (browser.equalsIgnoreCase("Firefox")) {
			/*
			 * System.setProperty("webdriver.gecko.driver",
			 * "C:\\Users\\ganga\\eclipse-workspace\\SeleniumTrainingSept2020\\drivers\\geckodriver.exe"
			 * ); driver = new FirefoxDriver();
			 */
			WebDriverManager.firefoxdriver().setup();
			driver = new FirefoxDriver();
			driver.manage().window().maximize();
			driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		}
		
		return driver;
	}
	
	@BeforeSuite
	public void beforeSuite() {
		File report_directory = new File(OUTPUT_FOLDER);
		File image_directory = new File(IMAGES_FOLDER);
		
		if(!report_directory.exists())
			report_directory.mkdirs();
		
		if(!image_directory.exists())
			image_directory.mkdirs();
	}
	
	@BeforeMethod (alwaysRun=true)
	public void beforeMethod(Method method) {
		 test = extentReports.createTest(method.getName());
		extentTestMap.put((int) (long) (Thread.currentThread().getId()), test);
		WebDriver driver = callDriver();
		setWebDriver(driver);
	}
	
	public void setWebDriver(WebDriver driver) {
		wd.set(driver);
	}

	public static WebDriver getWebDriver() {
		return wd.get();
	}
	
	public static synchronized ExtentTest getTest() {
		return extentTestMap.get((int) (long) Thread.currentThread().getId());
	}
	

	@AfterMethod (alwaysRun=true)
	public void afterMethod(ITestResult itestResult) {
		if(itestResult.getStatus() == 1) {
			getTest().log(Status.PASS, "Test Passed");
			System.out.println("Testcase is Passed");
		} else if (itestResult.getStatus() == 2) {
			getTest().log(Status.FAIL, getStackTrace(itestResult.getThrowable()));
			test.log(Status.INFO, "********************************************<br>Testcase Failed<br>********************************************<br>");
			Reporter.log("********************************************<br>Testcase Failed<br>********************************************<br><br>");
			//takeScreenshot(itestResult);
			try {
				saveFullPageScreenshot(IMAGES_FOLDER + itestResult.getTestClass().getName() + "."
						+ itestResult.getMethod().getMethodName() + ".png");
			} catch (IOException e) {
				getTest().log(Status.FAIL, "Screenshot Exception");
			}
			System.out.println("Testcase is Failed");
		} else {
			getTest().skip("ITestResult.SKIP, event afterMethod");
			System.out.println("Testcase is Skipped");
		}
		if(driver!=null) {			
			driver.close();
			driver.quit();			
		} else {
			System.out.println("Driver is null");
		}
	}
	
	private String getStackTrace(Throwable throwable) {
		StringWriter sw = new StringWriter();
		PrintWriter pw = new PrintWriter(sw);
		throwable.printStackTrace(pw);
		return sw.toString();
	}	
	
	public void takeScreenshot(ITestResult result) {
		try {
			File destFile = new File(String.format(IMG_NAME, 
					System.currentTimeMillis()));
			File srcFile = getScreenShotAs(OutputType.FILE);
			FileUtils.copyFile(srcFile, destFile);
			if(destFile != null) {
				getTest().fail(MediaEntityBuilder.createScreenCaptureFromPath(destFile.getAbsolutePath()).build());
				getTest().addScreenCaptureFromPath(destFile.getAbsolutePath());
			}			
		} catch (Exception e) {
			ExtentTestManager.getTest().info("Exception in taking the screen shot");
		}
		
	}
	
	public static void saveFullPageScreenshot(String name) throws IOException {
		Screenshot screenshot = new AShot().shootingStrategy(ShootingStrategies.viewportPasting(1000))
				.takeScreenshot(getWebDriver());
		ImageIO.write(screenshot.getImage(), "PNG", new File(name));
	}

	private <T> T getScreenShotAs(OutputType<T> file) {
		return ((TakesScreenshot) getWebDriver()).getScreenshotAs(file);
	}
	
	@AfterSuite(alwaysRun=true)
	public void afterSuite() {
		getTest().getExtent().flush();		
	}
	
}
