package webdriver;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_21_Wait_FindElement_FindElements {
	WebDriver driver;
	JavascriptExecutor jsExecutor;
	String projectPath = System.getProperty("user.dir");
	String osName = System.getProperty("os.name");

	@BeforeClass
	public void beforeClass() {
		if(osName.contains("Mac OS")) {
			System.setProperty("webdriver.gecko.driver", projectPath + "/browserDrivers/geckodriver");
		} else {
			System.setProperty("webdriver.gecko.driver", projectPath + "\\browserDrivers\\geckodriver.exe");
		}
		driver = new FirefoxDriver();
		jsExecutor = (JavascriptExecutor) driver;
	}

	@Test
	public void TC_01_Not_Enough_Time() {
		driver.get("https://automationfc.github.io/dynamic-loading/");
		driver.manage().timeouts().implicitlyWait(2, TimeUnit.SECONDS);
		
		driver.findElement(By.cssSelector("div#id button")).click();
		
		Assert.assertTrue(driver.findElement(By.cssSelector("div#finish h4")).isDisplayed());
	}

	@Test
	public void TC_02_Enough_Time() {
		driver.get("https://automationfc.github.io/dynamic-loading/");
		driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
		
		driver.findElement(By.cssSelector("div#id button")).click();
		
		Assert.assertTrue(driver.findElement(By.cssSelector("div#finish h4")).isDisplayed());
	}

	@Test
	public void TC_03_More_Than_Expected_Time() {
		driver.get("https://automationfc.github.io/dynamic-loading/");
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		
		driver.findElement(By.cssSelector("div#id button")).click();
		
		Assert.assertTrue(driver.findElement(By.cssSelector("div#finish h4")).isDisplayed());
	}

	@AfterClass
	public void afterClass() {
		driver.quit();
	}
}