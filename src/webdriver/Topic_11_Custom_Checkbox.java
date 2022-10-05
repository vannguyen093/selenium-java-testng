package webdriver;

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

public class Topic_11_Custom_Checkbox {
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

	@Test
	public void TC_01_Custom_Checkbox() {
		driver.get("https://material.angular.io/components/checkbox/examples");
		
		//Case 1
		//Thẻ input bị ẩn nên không click được 
		//Thẻ input dùng để verify được 
		
//		driver.findElement(By.xpath("//span[text()='Checked']/preceding-sibling::span/input")).click();
//		sleepInSecond(3);
//		Assert.assertTrue(driver.findElement(By.xpath("//span[text()='Checked']/preceding-sibling::span/input")).isSelected());
		
		//Case 2:
		//Không dùng thẻ input để click - thay thế bằng 1 thẻ đang hiển thị đại diện cho Checkbox/ Radio: span/ input/...
		//Các thẻ này lại không verify được 
		
//		driver.findElement(By.xpath("//span[text()='Checked']/preceding-sibling::span")).click();
//		sleepInSecond(3);
//		Assert.assertTrue(driver.findElement(By.xpath("//span[text()='Checked']/preceding-sibling::span")).isSelected());
		
		//Case 3:
		//Thẻ span để click
		//Thẻ input để verify
		//Vấn đề: Trong 1 dự án mà 1 element cần tới 2 locator để define thì sinh ra nhiều code/ cần phải maintain nhiều
		//Dễ gây hiểu nhầm cho người mới
//		
//		driver.findElement(By.xpath("//span[text()='Checked']/preceding-sibling::span")).click();
//		sleepInSecond(3);
//		Assert.assertTrue(driver.findElement(By.xpath("//span[text()='Checked']/preceding-sibling::span/input")).isSelected());
		
		//Case 4: Work-around (thuật ngữ trong ngành IT)
		//Thẻ input để click + verify
		//Hàm click() của WebElement không click và element bị ẩn được 
		//Dùng hàm click() của JavascriptExecutor để click
		By checkedCheckbox = By.xpath("//span[text()='Checked']/preceding-sibling::span/input");
		jsExecutor.executeScript("arguments[0].click()", driver.findElement(checkedCheckbox));
		sleepInSecond(3);
		Assert.assertTrue(driver.findElement(checkedCheckbox).isSelected());
	}

	@Test
	public void TC_02_Custom_Radio() {
		driver.get("https://material.angular.io/components/radio/examples");
		
		By checkedRadio = By.xpath("//span[contains(text(),'Winter')]/preceding-sibling::span/input");
		jsExecutor.executeScript("arguments[0].click()", driver.findElement(checkedRadio));
		sleepInSecond(3);
		Assert.assertTrue(driver.findElement(checkedRadio).isSelected());
	}

	@Test
	public void TC_03_VNDirect() {
		driver.get("https://account-v2.vndirect.com.vn/");
		
		By termCheckbox = By.xpath("//input[@name='acceptTerms']");
		jsExecutor.executeScript("arguments[0].click()", driver.findElement(termCheckbox));
		sleepInSecond(3);
		Assert.assertTrue(driver.findElement(termCheckbox).isSelected());
	}
	@Test
	public void TC_04_Google() {
		driver.get("https://docs.google.com/forms/d/e/1FAIpQLSfiypnd69zhuDkjKgqvpID9kwO29UCzeCVrGGtbNPZXQok0jA/viewform");
		
		By canThoRadio = By.xpath("//input[@aria-label='Cần Thơ']");
		//Verify trước khi click
		Assert.assertEquals(driver.findElement(canThoRadio).getAttribute("aria-checked"), "false");
		//Click chọn
		driver.findElement(canThoRadio).click();
		//Verify sau khi click
		Assert.assertEquals(driver.findElement(canThoRadio).getAttribute("aria-checked"), "true");
		
		By miQuangCheckbox = By.xpath("//input[@aria-label='Mì Quảng']");
		
		checkToCheckbox("//input[@aria-label='Mì Quảng']");
		sleepInSecond(3);
		Assert.assertEquals(driver.findElement(miQuangCheckbox).getAttribute("aria-checked"), "true");
		uncheckToCheckbox("//input[@aria-label='Mì Quảng']");
		sleepInSecond(3);
		Assert.assertEquals(driver.findElement(canThoRadio).getAttribute("aria-checked"), "false");
	}

	@AfterClass
	public void afterClass() {
		driver.quit();
	}
	public void checkToCheckbox(String xpathLocator) {
		WebElement element = driver.findElement(By.xpath(xpathLocator));
		if (element.getAttribute("aria-checked").equals("false")) {
			element.click();
		}
	}
	public void uncheckToCheckbox(String xpathLocator) {
		WebElement element = driver.findElement(By.xpath(xpathLocator));
		if (element.getAttribute("aria-checked").equals("true")) {
			element.click();
		}
	}
	public void sleepInSecond (long timeInSecond) {
		try {
			Thread.sleep(timeInSecond * 1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}