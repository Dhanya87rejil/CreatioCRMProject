package com.creatio.crm.frameworks.listeners;

import org.testng.ITestListener;
import org.testng.ITestResult;

public class TestListeners implements ITestListener {
	
	
	String testName;

	public void onTestStart(ITestResult result) {
		testName =result.getMethod().getMethodName();
		System.out.println("The execution of the test case " + testName + " is started");	
	}

	public void onTestSuccess(ITestResult result) {
		testName =result.getMethod().getMethodName();
		System.out.println("The execution of the test case " + testName + " is successfully completed without any issues");
	}

	public void onTestFailure(ITestResult result) {
		testName =result.getMethod().getMethodName();
		System.out.println("The execution of the test case " + testName + " is failed");
		System.out.println("The reason for the failure is: " + result.getThrowable().getMessage());
	}

	public void onTestSkipped(ITestResult result) {
		testName =result.getMethod().getMethodName();
		System.out.println("The execution of the test case " + testName + " is skipped");
		System.out.println("The reason for the skipping is: " + result.getThrowable().getMessage());		
	}

}