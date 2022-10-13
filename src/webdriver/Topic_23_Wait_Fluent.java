package webdriver;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.FluentWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.google.common.base.Function;

public class Topic_23_Wait_Fluent {
	WebDriver driver;
	JavascriptExecutor jsExecutor;
	String projectPath = System.getProperty("user.dir");
	String osName = System.getProperty("os.name");
	FluentWait<WebDriver> fluentDriver;
	FluentWait<WebElement> fluentElement;
	long allTime = 15; // Second
	long pollingTime = 100; // Milisecond

	@BeforeClass
	public void beforeClass() {
		if (osName.contains("Mac OS")) {
			System.setProperty("webdriver.gecko.driver", projectPath + "/browserDrivers/geckodriver");
		} else {
			System.setProperty("webdriver.gecko.driver", projectPath + "\\browserDrivers\\geckodriver.exe");
		}
		driver = new FirefoxDriver();
		jsExecutor = (JavascriptExecutor) driver;
	}

//	@Test
	public void TC_01_Fluent() {
		driver.get("https://automationfc.github.io/dynamic-loading/");
		findElement("//div[@id='start']/button").click();

		Assert.assertEquals(findElement("//div[@id='finish']/h4").getText(), "Hello World!");
	}

	@Test
	public void TC_02_Countdown() {
		driver.get("https://automationfc.github.io/fluent-wait/");
		
		WebElement countdownTime = findElement("//div[@id='javascript_countdown_time']");
		fluentElement = new FluentWait<WebElement>(countdownTime);
		
		fluentElement.withTimeout(Duration.ofSeconds(allTime)).pollingEvery(Duration.ofMillis(pollingTime)).ignoring(NoSuchElementException.class);
		
		fluentElement.until(new Function<WebElement, Boolean>() {

			@Override
			public Boolean apply(WebElement element) {
				return element.getText().endsWith("00");
			}
		});
	}

//	@Test
	public void TC_03_() {

	}

	@AfterClass
	public void afterClass() {
		driver.quit();
	}

	public WebElement findElement(String xpathLocator) {
		fluentDriver = new FluentWait<WebDriver>(driver);

		// Set tổng tgian và tần số
		fluentDriver.withTimeout(Duration.ofSeconds(allTime))
				// 1/3 giây check 1 lần
				.pollingEvery(Duration.ofMillis(pollingTime))
				// Ignore exception
				.ignoring(NoSuchElementException.class);

		// Aplly điều kiện
		return fluentDriver.until(new Function<WebDriver, WebElement>() {

			@Override
			public WebElement apply(WebDriver driver) {
				return driver.findElement(By.xpath(xpathLocator));
			}
		});
	}
}