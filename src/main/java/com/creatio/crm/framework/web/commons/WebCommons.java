package com.creatio.crm.framework.web.commons;

import java.io.File;
import java.io.IOException;
import java.time.Duration;
import java.util.Properties;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import com.creatio.crm.framework.base.BasePage;
import com.creatio.crm.framework.utilities.PropUtil;

public class WebCommons {
	// This class will have all the common methods related to web application
	// automation using selenium

	public WebDriver driver = BasePage.getDriver();
	public Properties prop = PropUtil.readData("config.properties");

	// Common method to launch application and verify title
	public void launchApplicaton() {
		driver.get(prop.getProperty("APP_UR"));
		String actualTitle = driver.getTitle();
		if (!actualTitle.equals(prop.getProperty("APP_TITLE"))) {
			Assert.fail("Application did not launch successfully." + "Expected Title: " + prop.getProperty("APP_TITLE")
					+ "But Actual Title: " + actualTitle);

		}

	}

	// Common method to scroll to the Element
	public void ScrollToElement(WebElement element) {
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("arguments[0].scrollIntoView(true)", element);
	}

	// Common method to click on the Element
	public void click(WebElement element) {
		ScrollToElement(element);
		element.click();
	}

	// Common method to click on an Element using JavaScript
	public void JSClick(WebElement element) {
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("arguments[0].click();", element);
	}

	// common method to perform double click on an element
	public void doubleClick(WebElement element) {
		ScrollToElement(element);
		Actions actions = new Actions(driver);
		actions.doubleClick(element).perform();
	}

	// common method to perform right click on an element
	public void rightClick(WebElement element) {
		ScrollToElement(element);
		Actions actions = new Actions(driver);
		actions.contextClick(element).perform();
	}

	// Common method to hover on an element
	public void hoverOnElement(WebElement element) {
		ScrollToElement(element);
		Actions actions = new Actions(driver);
		actions.moveToElement(element).perform();
	}

	// Common method to enter text in a text box
	public void enterText(WebElement element, String text) {
		ScrollToElement(element);
		element.clear();
		element.sendKeys(text);
	}

	// Common method to select the checkbox
	public void selectCheckbox(WebElement element, boolean status) {
		ScrollToElement(element);
		if (element.isSelected() != status) {
			element.click();
		}
	}

	// common method to select option from dropdown
	public void selectDropdownOption(WebElement dropdown, String option, String selectBy) {

		Select optionSelect = new Select(dropdown);
		switch (selectBy.toLowerCase()) {
		case "value":
			optionSelect.selectByValue(option);
			break;
		case "index":
			optionSelect.selectByIndex(Integer.parseInt(option));
			break;
		case "visibletext":
			optionSelect.selectByVisibleText(option);
			break;
		default:
			Assert.fail("Option not found in the dropdown:" + selectBy);
		}

	}

	// common method to get the text of an element
	public String getElementText(WebElement element) {
		
		return element.getText();
	}

	// common method to get the attribute value of an element
	public String getElementAttribute(WebElement element, String attribute) {

		return element.getAttribute(attribute);
	}

	// common method to get the current page URL
	public String getCurrentPageURL() {
		return driver.getCurrentUrl();
	}

	// common method to refresh web page
	public void refreshPage() {
		driver.navigate().refresh();
	}

	// common method to get window handle
	public String getWindowHandle() {
		return driver.getWindowHandle();
	}

	// common method to wait by using Thread.sleep
	public void waitFor(int seconds) {
		try {
			Thread.sleep(seconds * 1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	// common method to wait by using implicit wait
	public void implicitWait(int seconds) {
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(seconds));
	}

	// common method to wait by using explicit wait (wait for visibility of element)
	public void explicitWait(int seconds, WebElement element) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(seconds));
		wait.until(ExpectedConditions.visibilityof(element));
	}
//common method to wait by using explicit wait (wait for element to be visible)
	// common method to wait by using explicit wait (wait for locator )
	public void explicitWaitToBeClickable(int seconds, By locator) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(seconds));
		wait.until(ExpectedConditions.numberOfElementsToBeMoreThan(locator,0));
	}

	// common method to take screenshot of complete page
	public static String getScreenshot(WebDriver driver, String screenshotName) throws IOException {
		String destination = System.getProperty("user.dir") + "\\Screenshots\\" + screenshotName + ".png";
		File screenshotFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
		FileUtils.copyFile(screenshotFile, new File(destination));
		return destination;
	}

	// common method to take screenshot of specific element in a page
	public static String getScreenshot(WebElement element, String screenshotName) throws IOException {
		String destination = System.getProperty("user.dir") + "\\Screenshots\\" + screenshotName + ".png";
		File screenshotFile = element.getScreenshotAs(OutputType.FILE);
		FileUtils.copyFile(screenshotFile, new File(destination));
		return destination;
	}
	// common method to switch to alert and accept it

	public void switchToAlertAndAccept() {
		Alert alert = driver.switchTo().alert();
		alert.accept();

	}

	// common method to switch to alert and dismiss it
	public void switchToAlertAndDismiss() {
		Alert alert = driver.switchTo().alert();
		alert.dismiss();

	}

	// common method to switch to specific frame using web element
	public void switchToFrame(WebElement frameElement) {
		driver.switchTo().frame(frameElement);
	}

	// common method to switch to main window from frame
	public void switchToMainwindow() {
		driver.switchTo().defaultContent();
	}

	// common method to switch to specific frame using frame id or name
	public void switchToFrame(String frameIdOrName) {
		driver.switchTo().frame(frameIdOrName);
	}

}
