package webdriver;

import java.util.List;
import java.util.Random;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_15_Random_Popup {
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
	public void TC_01_JavaCodeGeeks_In_DOM() {
		driver.get("https://www.javacodegeeks.com/");
		
		WebElement popup = driver.findElement(By.cssSelector("div.lepopup-form"));
		
		if (popup.isDisplayed()) {
			driver.findElement(By.cssSelector("input.lepopup-ta-left ")).sendKeys(randomEmail());
			sleepInSecond(3);
			driver.findElement(By.cssSelector("a[data-label='Get the Books']")).click();
			sleepInSecond(3);
		}
		
		driver.findElement(By.cssSelector("input#s")).sendKeys("Selenium");
		driver.findElement(By.cssSelector("button.search-button")).click();
		sleepInSecond(3);
		
		Assert.assertEquals(driver.findElement(By.cssSelector("h2.post-title a[title*='How To Perform']")).getText(), "How To Perform Modern Web Testing With TestCafe Using JavaScript And Selenium");
	}

//	@Test
	public void TC_02_VnkEdu_In_DOM() {
		driver.get("https://vnk.edu.vn/");
		WebElement popup = driver.findElement(By.cssSelector("div[data-tl-type='lightbox']"));
		
		if (popup.isDisplayed()) {
			sleepInSecond(3);
			driver.findElement(By.cssSelector("svg[data-name='closeclose']")).click();
			sleepInSecond(3);
		}
		
		driver.findElement(By.cssSelector("a[title='Sổ tay kỹ sư']")).click();
		sleepInSecond(3);
		Assert.assertEquals(driver.findElement(By.cssSelector("div.title-content>h1")).getText(), "Sổ Tay Kỹ Sư – Tài Liệu Thiết Kế, Thi Công A-Z Cho Kỹ Sư M&E");
	}

	@Test
	public void TC_03_Dehieu_Not_In_DOM() {
		driver.get("https://dehieu.vn/");
		sleepInSecond(5);
		List<WebElement> popup = driver.findElements(By.cssSelector("section#popup"));
		
		if (popup.size() > 0 && popup.get(0).isDisplayed()) {
			driver.findElement(By.cssSelector("input#popup-name")).sendKeys("Van Nguyen");
			driver.findElement(By.cssSelector("input#popup-email")).sendKeys(randomEmail());
			driver.findElement(By.cssSelector("input#popup-phone")).sendKeys("0969888359");
			driver.findElement(By.cssSelector("button#close-popup")).click();
			sleepInSecond(3);
		}
		
		driver.findElement(By.xpath("//a[text()='Tất cả khóa học']")).click();
		sleepInSecond(3);
		driver.findElement(By.cssSelector("input#search-courses")).sendKeys("Khóa học Thiết kế tủ điện");
		driver.findElement(By.cssSelector("button#search-course-button")).click();
		sleepInSecond(3);
		Assert.assertEquals(driver.findElement(By.cssSelector("h4.name-course")), "Khóa học Thiết kế tủ điện");
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
	public String randomEmail() {
		Random rand = new Random();
		return "vannguyen" + rand.nextInt(99999) + "@gmail.com";
	}
}