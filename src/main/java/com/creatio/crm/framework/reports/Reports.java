package com.creatio.crm.framework.reports;

import org.testng.annotations.BeforeSuite;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;

public class Reports {
	
	//This class will contain all the common methods to generate HTML report by publishing all the test results

	public static ExtentHtmlReporter html;
	public static ExtentReports extent;
	public static ExtentTest logger;
	
	//common method to setup the report
	@BeforeSuite(alwaysRun=true)
	public static void setupReport(String reportName)
	{
		html=new ExtentHtmlReporter(System.getProperty("user.dir")+"//Reports//+AutomationReport.html");
		extent=new ExtentReports();
		extent.attachReporter(html);
	}
	//common method to start printing the test case details in the report
	public static void StartReporting(String testName)
	{
		logger=extent.createTest(testName);
	}
	//common method to stop printing the test case details in the report
	public static void StopReporting()
	{
		extent.flush();
	}
	
}
