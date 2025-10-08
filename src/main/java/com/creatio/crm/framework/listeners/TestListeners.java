package com.creatio.crm.framework.listeners;

import java.io.IOException;

import org.testng.ITestListener;
import org.testng.ITestResult;

import com.creatio.crm.framework.base.BasePage;
import com.creatio.crm.framework.reports.Reports;
import com.creatio.crm.framework.web.commons.WebCommons;

public class TestListeners implements ITestListener {

	String testName;

	public void onTestStart(ITestResult result) {
		testName = result.getMethod().getMethodName();
		Reports.StartReporting(testName);
	}


	public void onTestSuccess(ITestResult result) {
		testName = result.getMethod().getMethodName();
		Reports.logger.pass("The execution of the test case " + testName + " is passed");
		Reports.StopReporting();
	}

	public void onTestFailure(ITestResult result) {
		testName = result.getMethod().getMethodName();
		Reports.logger.fail("The execution of the test case " + testName + " is failed");
		Reports.logger.fail("The reason for the failure is: " + result.getThrowable().getMessage());
		try {
			Reports.logger.addScreenCaptureFromPath(WebCommons.getScreenshot(BasePage.getDriver(),testName));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Reports.StopReporting();
	}

	public void onTestSkipped(ITestResult result) {
		testName = result.getMethod().getMethodName();
		System.out.println("The execution of the test case " + testName + " is skipped");
		System.out.println("The reason for the skipping is: " + result.getThrowable().getMessage());
	}

}