package com.creatio.crm.framework.base;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;

public class BasePage {
//This class will have all the common methods and configurations to begin the test execution
// Like browser setup, teardown, webdriver instance share etc.


	private static WebDriver driver;

	// Common method to launch a browser
@Parameters("BROWSER")
@BeforeTest(alwaysRun = true)
	public void launchBrowser(String browserName) {
		if (browserName.equalsIgnoreCase("chrome")) {
			driver = new ChromeDriver();

		} else if (browserName.equalsIgnoreCase("firefox")) {
			driver = new FirefoxDriver();

		} else if (browserName.equalsIgnoreCase("edge")) {

			driver = new EdgeDriver();

		} else {
			Assert.fail("Browser not supported");

		}
		driver.manage().window().maximize();
		driver.manage().deleteAllCookies();

	}

	// Common method to tear down the driver
@AfterMethod(alwaysRun = true)
	public void tearDown() {
		if (driver != null) {
			driver.quit();

		}
	}
	
	//common method to share the Web driver instance to other classes
	
	public static WebDriver getDriver()
	{
	return driver;
	}
	
	//common method to update the web driver instance from other classes (This is useful to find the active browser window)
	
	public static void setDriver(WebDriver driver)
	{
	BasePage.driver = driver;
	}
	
}