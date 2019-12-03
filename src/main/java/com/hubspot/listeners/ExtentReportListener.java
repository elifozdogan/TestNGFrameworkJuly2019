package com.hubspot.listeners;

import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;
import org.testng.Reporter;

public class ExtentReportListener implements ITestListener {

	@Override
	public void onTestStart(ITestResult result) {
		
		System.out.println("Test is started..."+result.getName());
	}

	@Override
	public void onTestSuccess(ITestResult result) {
		
		
	}

	@Override
	public void onTestFailure(ITestResult result) {
		System.out.println("failed test case is: "+result.getName());
	}

	@Override
	public void onTestSkipped(ITestResult skipped) {	
		System.out.println(skipped.getName());
	}

	@Override
	public void onTestFailedButWithinSuccessPercentage(ITestResult result) {	
	}

	@Override
	public void onStart(ITestContext context) {	
		Reporter.log("About to begin executing test "+context.getName(), true);
	}

	@Override
	public void onFinish(ITestContext context) {
		System.out.println("Test is completed...");
		
	}

	
	
	
}
