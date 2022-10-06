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

public class Topic_14_Fixed_Popup {
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
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
	}

//	@Test
	public void TC_01_Ngoaingu24h() {
		driver.get("https://ngoaingu24h.vn/");
		
		Assert.assertFalse(driver.findElement(By.xpath("//div[@id='modal-login-v1' and @role='dialog']")).isDisplayed());
		
		driver.findElement(By.cssSelector("button.login_")).click();
		
		Assert.assertTrue(driver.findElement(By.xpath("//div[@id='modal-login-v1' and @role='dialog']")).isDisplayed());
		
		driver.findElement(By.xpath("//div[@id='modal-login-v1' and @role='dialog']//input[@id='account-input']")).sendKeys("automationfc");
		driver.findElement(By.xpath("//div[@id='modal-login-v1' and @role='dialog']//input[@id='password-input']")).sendKeys("automationfc");
		driver.findElement(By.xpath("//div[@id='modal-login-v1' and @role='dialog']//button[@data-text='Đăng nhập']")).click();
		
		Assert.assertEquals(driver.findElement(By.xpath("//div[@id='modal-login-v1' and @role='dialog']//div[@class='row error-login-panel']")).getText(), "Tài khoản không tồn tại!");
	}

//	@Test
	public void TC_02_Kyna() {
		driver.get("https://kyna.vn/");
		sleepInSecond(3);
		
		Assert.assertTrue(driver.findElement(By.cssSelector("a.fancybox-item.fancybox-close")).isDisplayed());
		driver.findElement(By.cssSelector("a[title='Close']")).click();
		sleepInSecond(3);
		
		driver.findElement(By.cssSelector("a.login-btn")).click();
		sleepInSecond(3);
		
		Assert.assertTrue(driver.findElement(By.xpath("//h4[contains(text(),'Bạn chưa có')]/ancestor::div[@class='modal-content']")).isDisplayed());
		
		driver.findElement(By.cssSelector("input#user-login")).sendKeys("vannguyen@gmail.com");
		driver.findElement(By.cssSelector("input#user-password")).sendKeys("123456");
		driver.findElement(By.cssSelector("button#btn-submit-login")).click();
		sleepInSecond(3);
		
		Assert.assertEquals(driver.findElement(By.cssSelector("div#password-form-login-message")).getText(), "Sai tên đăng nhập hoặc mật khẩu");
		
		driver.findElement(By.cssSelector("button.k-popup-account-close")).click();
		Assert.assertFalse(driver.findElement(By.xpath("//h4[contains(text(),'Bạn chưa có')]/ancestor::div[@class='modal-content']")).isDisplayed());
	}

//	@Test
	public void TC_03_Tiki() {
		driver.get("https://tiki.vn/");
		
		driver.findElement(By.xpath("//span[@class='account-label']/preceding-sibling::span")).click();
		sleepInSecond(3);
		Assert.assertTrue(driver.findElement(By.cssSelector("div[role='dialog']>div")).isDisplayed());
		
		driver.findElement(By.cssSelector("p.login-with-email")).click();
		sleepInSecond(3);
		
		driver.findElement(By.cssSelector("form button")).click();
		sleepInSecond(3);
		Assert.assertTrue(driver.findElement(By.xpath("//span[text()='Email không được để trống']")).isDisplayed());
		Assert.assertTrue(driver.findElement(By.xpath("//span[text()='Mật khẩu không được để trống']")).isDisplayed());
		
		driver.findElement(By.cssSelector("button.btn-close")).click();
		sleepInSecond(3);
		Assert.assertEquals(driver.findElements(By.cssSelector("div[role='dialog']>div")).size(), 0);
	}
	
	@Test
	public void TC_04_Facebook() {
		driver.get("https://www.facebook.com/");
		
		driver.findElement(By.cssSelector("a[data-testid='open-registration-form-button']")).click();
		sleepInSecond(3);
		Assert.assertTrue(driver.findElement(By.xpath("//div[text()='Đăng ký']/parent::div/parent::div")).isDisplayed());
		
		driver.findElement(By.xpath("//div[text()='Đăng ký']/parent::div/preceding-sibling::img")).click();
		sleepInSecond(3);
		
		Assert.assertEquals(driver.findElements(By.xpath("//div[text()='Đăng ký']/parent::div/parent::div")).size(), 0);
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