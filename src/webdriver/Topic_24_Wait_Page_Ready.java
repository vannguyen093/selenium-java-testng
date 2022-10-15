package webdriver;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_24_Wait_Page_Ready {
	WebDriver driver;
	JavascriptExecutor jsExecutor;
	String projectPath = System.getProperty("user.dir");
	String osName = System.getProperty("os.name");
	WebDriverWait explicitWait;
	Actions action;
	String employeeID;

	@BeforeClass
	public void beforeClass() {
		if(osName.contains("Mac OS")) {
			System.setProperty("webdriver.gecko.driver", projectPath + "/browserDrivers/geckodriver");
		} else {
			System.setProperty("webdriver.gecko.driver", projectPath + "\\browserDrivers\\geckodriver.exe");
		}
		driver = new FirefoxDriver();
		action = new Actions(driver);
//		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
	}

	@Test
	public void TC_01_OrangeHRM() {
		driver.get("https://opensource-demo.orangehrmlive.com/web/index.php/auth/login");
		Assert.assertTrue(isPageLoadedSuccess());
		driver.findElement(By.xpath("//input[@name='username']")).sendKeys("Admin");
		driver.findElement(By.xpath("//input[@name='password']")).sendKeys("admin123");
		driver.findElement(By.cssSelector(".orangehrm-login-button")).click();
		
		Assert.assertTrue(isPageLoadedSuccess());
		
		Assert.assertEquals(driver.findElement(By.cssSelector("div.oxd-table-filter-header-title h5")).getText(), "Employee Information");
		
		driver.findElement(By.xpath("//nav[@role='navigation']//a[text()='Add Employee']")).click();
		Assert.assertTrue(isPageLoadedSuccess());
		
		driver.findElement(By.xpath("//input[@name='firstName']")).sendKeys("Van");
		driver.findElement(By.xpath("//input[@name='lastName']")).sendKeys("Nguyen");
		employeeID = driver.findElement(By.xpath("//label[text()='Employee Id']/parent::input/following-sibling::input/input")).getAttribute("value");
		driver.findElement(By.xpath("//button[@type='submit']")).click();
		
		Assert.assertTrue(isPageLoadedSuccess());
		
		Assert.assertEquals(driver.findElement(By.xpath("//input[@name='firstName']")).getAttribute("value"), "Van");
		Assert.assertEquals(driver.findElement(By.xpath("//input[@name='lastName']")).getAttribute("value"), "Nguyen");
		Assert.assertEquals(driver.findElement(By.xpath("//label[text()='Employee Id']/parent::input/following-sibling::input/input")).getAttribute("value"), employeeID);
	}

	@Test
	public void TC_02_testProject() {
		driver.get("https://blog.testproject.io/");
		String keyword ="Selenium";
		if (driver.findElement(By.cssSelector("div.mailch-wrap")).isDisplayed()) {
			driver.findElement(By.cssSelector("div#mailch-close")).click();
		}
		
		action.moveToElement(driver.findElement(By.cssSelector("aside#secondary input.search-field"))).perform();
		Assert.assertTrue(isPageLoadedSuccess());
		
		driver.findElement(By.cssSelector("aside#secondary input.search-field")).sendKeys(keyword);
		driver.findElement(By.cssSelector("aside#secondary span.glass")).click();
		Assert.assertTrue(isPageLoadedSuccess());
		
		List<WebElement> postTitle = driver.findElements(By.cssSelector("h3.post-title>a"));
		for (WebElement title : postTitle) {
			Assert.assertTrue(title.getText().contains(keyword));
		}
	}

//	@Test
	public void TC_03_() {
		
	}

	@AfterClass
	public void afterClass() {
		driver.quit();
	}
	public boolean isPageLoadedSuccess() {
		jsExecutor = (JavascriptExecutor) driver;
		explicitWait = new WebDriverWait(driver, 30);
		ExpectedCondition<Boolean> jQueryLoad = new ExpectedCondition<Boolean>() {

			@Override
			public Boolean apply(WebDriver driver) {
				return (Boolean) jsExecutor.executeScript("return (window.jQuery != null) && (jQuery.active === 0);");
			}
		};
		
		ExpectedCondition<Boolean> jsLoad = new ExpectedCondition<Boolean>() {

			@Override
			public Boolean apply(WebDriver driver) {
				return jsExecutor.executeScript("return document.readyState").toString().equals("complete");
			}
		};
		return explicitWait.until(jQueryLoad) && explicitWait.until(jsLoad);
	}
}