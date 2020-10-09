package com.framework;

import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Map;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Protocol;
import com.aventstack.extentreports.reporter.configuration.Theme;
import com.utils.PropertyReaderUtil;

import org.testng.*;

public class ExtentTestNGIReportListener implements IReporter {
	
	private static final String OUTPUT_FOLDER = "./src/test/resources/Reports/";
	private static final String FILE_NAME = "Extent_Report.html";
	ExtentReports extent = new ExtentReports();
	ExtentTest test;

	@Override
	public void generateReport(List xmlSuites, List suites, String outputDirectory) {
        init();
        for (Object suite : suites) {
            Map<String, ISuiteResult> result = ((ISuite) suite).getResults();

            for (Object res : result.values()) {
                ITestContext context = ((ISuiteResult) res).getTestContext();

                try {
                    buildTestNodes(context.getFailedTests(), Status.FAIL);
                    buildTestNodes(context.getSkippedTests(), Status.SKIP);
                    buildTestNodes(context.getPassedTests(), Status.PASS);
                } catch (Exception e) {
                }
            }
        }
        for (String s : Reporter.getOutput()) {
            extent.addTestRunnerOutput(s);
        }
        
        extent.setSystemInfo("Browser", PropertyReaderUtil.getProperty("browser"));
        extent.setSystemInfo("OS", System.getProperty("os.name"));
        extent.setSystemInfo("JAVA Version", System.getProperty("java.version"));
        
        extent.flush();
    }
	
	private void init() {
        ExtentSparkReporter sparkReporter = new ExtentSparkReporter(OUTPUT_FOLDER + FILE_NAME);
        sparkReporter.config().setDocumentTitle("Extent Report_Lauriete");
        sparkReporter.config().setReportName("Extent Report_Lauriete");
        sparkReporter.config().setTimeStampFormat("HH:mm:ss");
        sparkReporter.config().setEncoding("utf-8");
        sparkReporter.config().setProtocol(Protocol.HTTPS);
        sparkReporter.config().setTheme(Theme.DARK);
        extent = new ExtentReports();
        extent.attachReporter(sparkReporter);
        extent.setReportUsesManualConfiguration(true);
    }
	
	private void buildTestNodes(IResultMap tests, Status status) throws Exception {
        if (tests.size() > 0) {
            for (ITestResult result : tests.getAllResults()) {
                test = extent.createTest(result.getMethod().getMethodName());
                for (String group : result.getMethod().getGroups())
                    test.assignCategory(group);
                if (result.getThrowable() != null) {
                    test.log(status, result.getThrowable().getMessage());
                } else {
                    test.log(status, "Test " + status.toString().toLowerCase() + "ed");
                }
                if (result.getStatus() == ITestResult.FAILURE) {
                	test.fail(result.getTestClass().getName()
                            + "." + result.getMethod().getMethodName(),
                    MediaEntityBuilder.createScreenCaptureFromPath(System.getProperty("user.dir")
                            + "/src/test/resources/Reports/Images/" + result.getTestClass().getName()
                            + "." + result.getMethod().getMethodName() + ".png").build());

                	test.addScreenCaptureFromPath(System.getProperty("user.dir")
                    + "./src/test/resources/Reports/Images/"
                    + result.getTestClass().getName()
                    + "." + result.getMethod().getMethodName() + ".png");

                }
                test.getModel().setStartTime(getTime(result.getStartMillis()));
                test.getModel().setEndTime(getTime(result.getEndMillis()));
            }
        }
    }

    private Date getTime(long millis) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(millis);
        return calendar.getTime();
    }
}
