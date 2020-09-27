package com.framework;

import java.util.List;

import org.testng.IReporter;
import org.testng.ISuite;
import org.testng.xml.XmlSuite;

public class MyReportListener implements IReporter {

	/** Creates summary of the run */
	public void generateReport(List<XmlSuite> xml, List<ISuite> suites, String outdir) {}

	
	
}
