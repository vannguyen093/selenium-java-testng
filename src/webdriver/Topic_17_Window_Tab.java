package webdriver;

import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_17_Window_Tab {
	WebDriver driver;
	JavascriptExecutor jsExecutor;
	Alert alert;
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
	public void TC_01_AutomationFC_Window() {
		driver.get("https://automationfc.github.io/basic-form/index.html");
		String parentID = driver.getWindowHandle();
		
		driver.findElement(By.xpath("//a[text()='GOOGLE']")).click();
		switchtoPageTitle("Google");
		Assert.assertEquals(driver.getTitle(), "Google");
		
		switchtoPageTitle("SELENIUM WEBDRIVER FORM DEMO");
		driver.findElement(By.xpath("//a[text()='FACEBOOK']")).click();
		switchtoPageTitle("Facebook – log in or sign up");
		Assert.assertEquals(driver.getTitle(), "Facebook – log in or sign up");
		
		switchtoPageTitle("SELENIUM WEBDRIVER FORM DEMO");
		driver.findElement(By.xpath("//a[text()='TIKI']")).click();
		switchtoPageTitle("Tiki - Mua hàng online giá tốt, hàng chuẩn, ship nhanh");
		Assert.assertEquals(driver.getTitle(), "Tiki - Mua hàng online giá tốt, hàng chuẩn, ship nhanh");
		
		closeAllWindowWithoutParent(parentID);
		Assert.assertEquals(driver.getCurrentUrl(), "https://automationfc.github.io/basic-form/index.html");
	}

//	@Test
	public void TC_02_Kyna_Window() {
		driver.get("https://kyna.vn/");
		String parentID = driver.getWindowHandle();
		sleepInSecond(3);
		
		Assert.assertTrue(driver.findElement(By.cssSelector("div.fancybox-inner")).isDisplayed());
		driver.findElement(By.cssSelector("a[title='Close']")).click();
		sleepInSecond(2);
		
		clickToElementByJS("//img[@alt='facebook']");
		sleepInSecond(3);
		switchtoPageTitle("Kyna.vn - Home | Facebook");
		Assert.assertEquals(driver.getTitle(), "Kyna.vn - Home | Facebook");
		
		switchtoPageTitle("Kyna.vn - Học online cùng chuyên gia");
		clickToElementByJS("//img[@alt='youtube']");
		switchtoPageTitle("Kyna.vn - YouTube");
		Assert.assertEquals(driver.getTitle(), "Kyna.vn - YouTube");
		
		switchtoPageTitle("Kyna.vn - Học online cùng chuyên gia");
		clickToElementByJS("//a[text()='Điều khoản dịch vụ']/ancestor::ul//preceding-sibling::a[2]");
		switchtoPageTitle("Thông tin website thương mại điện tử - Online.Gov.VN");
		Assert.assertEquals(driver.getCurrentUrl(), "http://online.gov.vn/Home/WebDetails/61473");
		
		switchtoPageTitle("Kyna.vn - Học online cùng chuyên gia");
		clickToElementByJS("//a[text()='Điều khoản dịch vụ']/ancestor::ul//preceding-sibling::a[1]");
		switchtoPageTitle("Thông tin website thương mại điện tử - Online.Gov.VN");
		Assert.assertEquals(driver.getCurrentUrl(), "http://online.gov.vn/Home/WebDetails/60140");
		
		closeAllWindowWithoutParent(parentID);
		Assert.assertEquals(driver.getCurrentUrl(), "https://kyna.vn/");
	}

//	@Test
	public void TC_03_TechPanda_Window() {
		driver.get("http://live.techpanda.org/");
		String parentID = driver.getWindowHandle();
		driver.findElement(By.xpath("//a[text()='Mobile']")).click();
		
		driver.findElement(By.xpath("//a[text()='Sony Xperia']/parent::h2/following-sibling::div[@class='actions']//a[@class='link-compare']")).click();
		Assert.assertEquals(driver.findElement(By.cssSelector("ul.messages span")).getText(), "The product Sony Xperia has been added to comparison list.");
		driver.findElement(By.xpath("//a[text()='Samsung Galaxy']/parent::h2/following-sibling::div[@class='actions']//a[@class='link-compare']")).click();
		Assert.assertEquals(driver.findElement(By.cssSelector("ul.messages span")).getText(), "The product Samsung Galaxy has been added to comparison list.");
		driver.findElement(By.cssSelector("button[title='Compare']")).click();
		sleepInSecond(5);
		
		switchtoPageTitle("Products Comparison List - Magento Commerce");
		sleepInSecond(3);
		Assert.assertEquals(driver.getTitle(), "Products Comparison List - Magento Commerce");
		closeAllWindowWithoutParent(parentID);
		sleepInSecond(2);
		
		driver.findElement(By.xpath("//a[text()='Clear All']")).click();
		sleepInSecond(3);
		alert = driver.switchTo().alert();
		alert.accept();
		driver.switchTo().defaultContent();
		sleepInSecond(3);
		Assert.assertEquals(driver.findElement(By.cssSelector("ul.messages span")).getText(), "The comparison list was cleared.");
	}
	
	@Test
	public void TC_04_Cambridge_Window() {
		driver.get("https://dictionary.cambridge.org/vi/");
		sleepInSecond(2);
		driver.findElement(By.cssSelector("span.cdo-login-button span")).click();
		sleepInSecond(5);
		
		switchtoPageTitle("Login");
		driver.findElement(By.cssSelector("input[value='Log in']")).click();
		sleepInSecond(3);
		Assert.assertEquals(driver.findElement(By.xpath("//input[@placeholder='Email *']/following-sibling::span")).getText(), "This field is required");
		Assert.assertEquals(driver.findElement(By.xpath("//input[@placeholder='Password *']/following-sibling::span")).getText(), "This field is required");
		driver.findElement(By.xpath("//input[@placeholder='Email *']")).sendKeys("automationfc.com@gmail.com");
		driver.findElement(By.xpath("//input[@placeholder='Password *']")).sendKeys("Automation000***");
		sleepInSecond(2);
		driver.findElement(By.cssSelector("input[value='Log in']")).click();
		sleepInSecond(5);
		
		switchtoPageTitle("Cambridge Dictionary | Từ điển tiếng Anh, Bản dịch & Từ điển từ đồng nghĩa");
		sleepInSecond(3);
		Assert.assertEquals(driver.findElement(By.cssSelector("div[amp-access='loggedIn'] span.cdo-username")).getText(), "Automation FC");
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
	public void switchtoPageTitle(String expectedPageTitle) {
		Set<String> allWindowsID = driver.getWindowHandles();
		sleepInSecond(2);
		
		for (String id : allWindowsID) {
			driver.switchTo().window(id);
			String actualPageTitle = driver.getTitle();
			if (actualPageTitle.equals(expectedPageTitle)) {
				sleepInSecond(3);
				break;
			}
		}
	}
	
	public void closeAllWindowWithoutParent(String parentID) {
		Set<String> allWindowsID = driver.getWindowHandles();
		sleepInSecond(2);
		
		for (String id : allWindowsID) {
			if (!id.equals(parentID)) {
				driver.switchTo().window(id);
				driver.close();
				sleepInSecond(3);
			}
		}
		driver.switchTo().window(parentID);
	}
	
	public void clickToElementByJS(String locator) {
		WebElement itemButton = driver.findElement(By.xpath(locator));
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("arguments[0].click()", itemButton);
	}
}