package com.syntax.class01;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class Task3 {

	// TC 1: HRMS Application Login:

	// Open chrome browser
	// Go to “http://166.62.36.207/humanresources/symfony/web/index.php/auth/login”
	// Enter valid username and password
	// Click on login button
	// Then verify Syntax Logo is displayed
	// Close the browser

	public static WebDriver driver;

	@BeforeMethod
	public void openBrowser() {
		System.setProperty("webdriver.chrome.driver", "drivers/chromedriver.exe");
		driver = new ChromeDriver();
		driver.get("http://166.62.36.207/humanresources/symfony/web/index.php/auth/login");
		driver.manage().window().maximize();

	}

	@Test(priority = 2)
	public void logoIsDisplayed() {
		driver.findElement(By.id("txtUsername")).sendKeys("Admin");
		driver.findElement(By.id("txtPassword")).sendKeys("Hum@nhrm123");
		driver.findElement(By.id("btnLogin")).click();
		WebElement logo = driver.findElement(By.xpath("//img[@alt='OrangeHRM']"));

		if (logo.isDisplayed()) {
			System.out.println("Logo is displayed");
		} else {
			System.out.println("you need try more");
		}

	}

	@Test(priority = 1)
	public void message() {
		driver.findElement(By.id("txtUsername")).sendKeys("Admin");
		driver.findElement(By.id("btnLogin")).click();
		WebElement actualMessage = driver.findElement(By.id("spanMessage"));

		String expectedMessage = "Password cannot be empty";

		if (actualMessage.getText().equals(expectedMessage)) {
			System.out.println("Error message is: " + actualMessage.getText());
		} else {
			System.out.println("you need try more");
		}

	}

	@AfterMethod
	public void closeBrowser() {
		driver.quit();

	}

}
