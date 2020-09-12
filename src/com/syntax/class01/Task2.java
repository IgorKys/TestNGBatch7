package com.syntax.class01;


import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class Task2 {
	//Create class that will have:
	// Before and After Class annotation
	// Before and After Method annotation
	// 2 methods with Test annotation
	//
	// Observe the results!
	
	@BeforeMethod
	public void beforeMethod() {
		System.out.println("This is before method");
	}

	@Test
	public void student1() {
		System.out.println("This is student one");
	}

	@Test
	public void student2() {
		System.out.println("This is student two");
	}


	@AfterMethod
	public void afterMethod() {
		System.out.println("This is after method");
	}
	
	@BeforeClass
	public void beforeClass() {
		System.out.println("--- before class annotation ---");
	}
	
	@AfterClass
	public void afterClass() {
		System.out.println("--- after class annotation ---");
	}

}
