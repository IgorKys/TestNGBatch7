package com.syntax.class03;

import java.io.File;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class HW {

	public static WebDriver driver;

	@BeforeMethod
	public void openBrowser() {
		System.setProperty("webdriver.chrome.driver", "drivers/chromedriver.exe");
		driver = new ChromeDriver();
		driver.get("http://166.62.36.207/humanresources/symfony/web/index.php/auth/login");
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
	}

	@AfterMethod
	public void closeBrowser() {
		if (driver != null) {
			driver.quit();
		}
	}

	@Test(dataProvider = "getData")
	public void addEmployee(String firstName, String lastName, String userName, String password)
			throws InterruptedException {
		driver.findElement(By.id("txtUsername")).sendKeys("Admin");
		driver.findElement(By.id("txtPassword")).sendKeys("Hum@nhrm123");
		driver.findElement(By.id("btnLogin")).click();

		Actions action = new Actions(driver);
		WebDriverWait wait = new WebDriverWait(driver, 20);
		WebElement pim = driver.findElement(By.id("menu_pim_viewPimModule"));
		action.moveToElement(pim).perform();
		Thread.sleep(2000);
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//a[@id='menu_pim_addEmployee']")));
		WebElement addEmpBtn = driver.findElement(By.id("menu_pim_addEmployee"));
		Thread.sleep(3000);
		addEmpBtn.click();

		driver.findElement(By.id("firstName")).sendKeys(firstName);
		driver.findElement(By.id("lastName")).sendKeys(lastName);

		WebElement createLoginDetailsBtn = driver.findElement(By.id("chkLogin"));
		createLoginDetailsBtn.click();

		driver.findElement(By.id("user_name")).sendKeys(userName);
		driver.findElement(By.id("user_password")).sendKeys(password);
		driver.findElement(By.id("re_password")).sendKeys(password);

		driver.findElement(By.id("btnSave")).click();
		
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@id = 'profile-pic']/h1")));
		TakesScreenshot ts = (TakesScreenshot) driver;
		File file = ts.getScreenshotAs(OutputType.FILE);

		try {
			FileUtils.copyFile(file,
					new File("screenshots/AddEmployeeTest/" + userName + " - " + firstName + " " + lastName + ".png"));
		} catch (IOException e) {
			e.printStackTrace();
		}
		WebElement profileFirstLastName = driver.findElement(By.xpath("//div[@id = 'profile-pic']/h1"));
		Assert.assertEquals(profileFirstLastName.getText(), firstName + " " + lastName);	
		
		

	}

	@DataProvider
	public String[][] getData() {

		String[][] data = { { "Lionel", "Messi", "LeoMessi10", "BarcaLeo#10#" },
				{ "Cesco", "Totti", "Totti10", "RomaTotti#10#" }, { "Nicolo", "Zaniolo", "Nico22", "RomaNico#22#" },
				{ "Edin", "Dzeko", "EDzeko9", "RomaDzeko#9#" },
				{ "Andriy", "Shevchenko", "Sheva7", "UkraineCoach#1#" }, };

		return data;
	}

}
