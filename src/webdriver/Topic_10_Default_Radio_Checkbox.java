package webdriver;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_10_Default_Radio_Checkbox {
	WebDriver driver;
	String projectPath = System.getProperty("user.dir");
	String osName = System.getProperty("os.name");

	@BeforeClass
	public void beforeClass() {
		if (osName.contains("Mac OS")) {
			System.setProperty("webdriver.gecko.driver", projectPath + "/browserDrivers/geckodriver");
		} else {
			System.setProperty("webdriver.gecko.driver", projectPath + "\\browserDrivers\\geckodriver.exe");
		}
		driver = new FirefoxDriver();
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
	}

	@Test
	public void TC_01_JotForm() {
		driver.get("https://automationfc.github.io/multiple-fields/");

		// Chọn checkbox: Cancer, Fainting Spells
		checkToCheckboxOrRadio("input[value*='Cancer']");
		checkToCheckboxOrRadio("input[value*='Fainting Spells']");

		// Verify nó được chọn thành công
		Assert.assertTrue(isElementSelected("input[value*='Cancer']"));
		Assert.assertTrue(isElementSelected("input[value*='Fainting Spells']"));

		// Chọn radio bất kỳ
		checkToCheckboxOrRadio("input[value*='5+ days']");
		checkToCheckboxOrRadio("input[value*='1-2 cups/day']");

		// Verify nó được chọn thành công
		Assert.assertTrue(isElementSelected("input[value*='5+ days']"));
		Assert.assertTrue(isElementSelected("input[value*='1-2 cups/day']"));

		// Bỏ chọn checkbox: Cancer, Fainting Spells
		// Chỉ cần click 1 lần nữa là bỏ chọn
		uncheckToCheckbox("input[value*='Cancer']");
		uncheckToCheckbox("input[value*='Fainting Spells']");

		// Verify nó được bỏ chọn thành công
		Assert.assertFalse(isElementSelected("input[value*='Cancer']"));
		Assert.assertFalse(isElementSelected("input[value*='Fainting Spells']"));

		// Bỏ chọn Radio
		// Phải chọn radio khác thì nó sẽ bỏ chọn radio đang được chọn đi
		driver.findElement(By.cssSelector("input[value*='1-2 days']")).click();
		driver.findElement(By.cssSelector("input[value*='5+ cups/day']")).click();

		// Verify nó được bỏ chọn thành công
		Assert.assertFalse(isElementSelected("input[value*='5+ days']"));
		Assert.assertFalse(isElementSelected("input[value*='1-2 cups/day']"));
	}

	@Test
	public void TC_02_Jotform_Select_All() {
		driver.get("https://automationfc.github.io/multiple-fields/");

		List<WebElement> allCheckboxes = driver.findElements(By.cssSelector("input[type='checkbox']"));

		// Dùng vòng lặp để duyệt qua và click chọn
		for (WebElement checkbox : allCheckboxes) {
			checkToCheckboxOrRadio(checkbox);
		}

//		// Dùng vòng lặp để duyệt qua và kiểm tra
//		for (WebElement checkbox : allCheckboxes) {
//			Assert.assertTrue(isElementSelected(checkbox));
//		}

		// Dùng vòng lặp để duyệt qua và click bỏ chọn
		for (WebElement checkbox : allCheckboxes) {
			uncheckToCheckbox(checkbox);
		}

//		// Dùng vòng lặp để duyệt qua và kiểm tra
//		for (WebElement checkbox : allCheckboxes) {
//			Assert.assertFalse(isElementSelected(checkbox));
//		}
	}

	@Test
	public void TC_03_Select_All() {
		driver.get("https://demos.telerik.com/kendo-ui/checkbox/index");
		sleepInSecond(5);
		List<WebElement> allCheckboxes = driver.findElements(By.cssSelector("#example input[type=checkbox]"));

		// Vừa chọn + Verify
		for (WebElement checkbox : allCheckboxes) {
			checkToCheckboxOrRadio(checkbox);
		}
		
		// Vừa bỏ chọn + Verify
		for (WebElement checkbox : allCheckboxes) {
			uncheckToCheckbox(checkbox);
		}
	}

	@AfterClass
	public void afterClass() {
		driver.quit();
	}

	public void sleepInSecond(long timeInSecond) {
		try {
			Thread.sleep(timeInSecond * 1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	public void checkToCheckboxOrRadio(String cssLocator) {
		// Kiểm tra trước nó đã chọn hay chưa
		// Nếu chọn rồi thì không cần click nữa
		// Nếu chưa chọn thì click vào -> Được chọn
		if (!driver.findElement(By.cssSelector(cssLocator)).isSelected()) {
			driver.findElement(By.cssSelector(cssLocator)).click();
		}
	}

	public void checkToCheckboxOrRadio(WebElement element) {
		if (!element.isSelected() && element.isEnabled()) {
			element.click();
			Assert.assertTrue(isElementSelected(element));
		}
	}

	public void uncheckToCheckbox(String cssLocator) {
		// Kiểm tra trước nó đã chọn hay chưa
		// Nếu chọn rồi thì không cần click nữa
		// Nếu chưa chọn thì click vào -> Được chọn
		if (driver.findElement(By.cssSelector(cssLocator)).isSelected()) {
			driver.findElement(By.cssSelector(cssLocator)).click();
		}
	}

	public void uncheckToCheckbox(WebElement element) {
		// Kiểm tra trước nó đã chọn hay chưa
		// Nếu chọn rồi thì không cần click nữa
		// Nếu chưa chọn thì click vào -> Được chọn
		if (element.isSelected() && element.isEnabled()) {
			element.click();
			Assert.assertFalse(isElementSelected(element));
		}
	}

	public boolean isElementSelected(String cssLocator) {
		return driver.findElement(By.cssSelector(cssLocator)).isSelected();
	}

	public boolean isElementSelected(WebElement element) {
		return element.isSelected();
	}
}