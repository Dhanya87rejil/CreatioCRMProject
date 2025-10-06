package com.creatio.crm.frameworks.listeners;

import org.testng.IRetryAnalyzer;
import org.testng.ITestResult;

//Listeners in TestNG ==> Listeners are used to Listen events and perform certain actions based on the events.

//RetryAnalyzer in TestNG ==> RetryAnalyzer is used to retry a failed test case a certain number of times before marking it as failed.
//ITestListener in TestNG ==> ITestListener is an interface that provides methods to listen to the events of a test case.


public class RetryTest implements IRetryAnalyzer {
	
	int count = 0;
	int retryLimit = 2;

	@Override
	public boolean retry(ITestResult result) {
		
		if(!result.isSuccess()) {
			if(count < retryLimit) {
				count++;
				return true;
			}			
		}		
		return false;
	}

}


