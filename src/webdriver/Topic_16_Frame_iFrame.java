package webdriver;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_16_Frame_iFrame {
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
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
	}

//	@Test
	public void TC_01_Kyna_Iframe() {
		driver.get("https://kyna.vn/");
		sleepInSecond(3);
		
		Assert.assertTrue(driver.findElement(By.cssSelector("a.fancybox-item.fancybox-close")).isDisplayed());
		driver.findElement(By.cssSelector("a[title='Close']")).click();
		sleepInSecond(3);
		
		WebElement iFrameKyna = driver.findElement(By.xpath("//iframe[contains(@src,'kyna.vn')]"));
		Assert.assertTrue(iFrameKyna.isDisplayed());
		
		driver.switchTo().frame(iFrameKyna);
		Assert.assertEquals(driver.findElement(By.xpath("//a[@title='Kyna.vn']/parent::div/following-sibling::div")).getText(), "166K likes");
		driver.switchTo().defaultContent();
		
		driver.switchTo().frame(driver.findElement(By.cssSelector("iframe#cs_chat_iframe")));
		driver.findElement(By.cssSelector("div.meshim_widget_Widget")).click();
		driver.findElement(By.cssSelector("input.input_name")).sendKeys("Van Nguyen");
		driver.findElement(By.cssSelector("input.input_phone")).sendKeys("0969888359");
		new Select(driver.findElement(By.cssSelector("select#serviceSelect"))).selectByVisibleText("TƯ VẤN TUYỂN SINH");
		driver.findElement(By.name("message")).sendKeys("Tư vấn khóa học Excel");
		sleepInSecond(3);
		driver.switchTo().defaultContent();
		
		driver.findElement(By.cssSelector("input#live-search-bar")).sendKeys("Excel");
		driver.findElement(By.cssSelector("button.search-button")).click();
		
		List<WebElement> courseName = driver.findElements(By.cssSelector("div.content h4"));
		for (WebElement course : courseName) {
			Assert.assertTrue(course.getText().contains("Excel"));
		}
	}

	@Test
	public void TC_02_HDFC_Bank() {
		driver.get("https://netbanking.hdfcbank.com/netbanking/");
		
		driver.switchTo().frame(driver.findElement(By.name("login_page")));
		driver.findElement(By.name("fldLoginUserId")).sendKeys("vannguyen093");
		driver.findElement(By.cssSelector("a.login-btn")).click();
		
		Assert.assertTrue(driver.findElement(By.id("fldPasswordDispId")).isDisplayed());
		driver.findElement(By.id("fldPasswordDispId")).sendKeys("123456789");
	}

//	@Test
	public void TC_03_() {
		
	}

	@AfterClass
	public void afterClass() {
		driver.quit();
	}
	public void sleepInSecond (long timeInSecond) {
		try {
			Thread.sleep(timeInSecond * 1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}