package com.syntax.class04;

import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

public class Listeners implements ITestListener {

	// invoked when we start every @Test method
	public void onTestStart(ITestResult result) {
		System.out.println("Test started " + result.getName());
	}

	public void onTestSuccess(ITestResult result) {
		System.out.println("Test passed " + result.getName());
	}

	public void onTestFailure(ITestResult result) {
		System.out.println("Test Failed " + result.getName());
	}

	// invoked when we start <test> based on xml file
	public void onStart(ITestContext context) {
		System.out.println("Starting test inside xml " + context.getName());
	}

	public void onFinish(ITestContext context) {
		System.out.println("Ending test inside xml " + context.getName());
	}
}
