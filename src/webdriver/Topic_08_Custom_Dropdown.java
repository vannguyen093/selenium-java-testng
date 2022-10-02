package webdriver;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_08_Custom_Dropdown {
	WebDriver driver;
	WebDriverWait explicitWait;
	JavascriptExecutor jsExecutor;

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
		jsExecutor = (JavascriptExecutor) driver;
//		driver.manage().window().setSize(new Dimension(1366, 768));
		explicitWait = new WebDriverWait(driver, 30);
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
	}

	@Test
	public void TC_01_JQuery() {
		driver.get("https://jqueryui.com/resources/demos/selectmenu/default.html");

		// Gọi hàm: 1 hàm có thể gọi 1 hàm khác để dùng trong cùng 1 class

		// Number
		selectItemInCustomDropdow("#number-button", "#number-menu div", "7");
		sleepInSecond(2);
		Assert.assertEquals(driver.findElement(By.cssSelector("#number-button>.ui-selectmenu-text")).getText(), "7");
		selectItemInCustomDropdow("#number-button", "#number-menu div", "5");
		sleepInSecond(2);
		Assert.assertEquals(driver.findElement(By.cssSelector("#number-button>.ui-selectmenu-text")).getText(), "5");

		// Speed
		selectItemInCustomDropdow("#speed-button", "#speed-menu div", "Fast");
		sleepInSecond(2);
		Assert.assertEquals(driver.findElement(By.cssSelector("#speed-button>.ui-selectmenu-text")).getText(), "Fast");
		selectItemInCustomDropdow("#speed-button", "#speed-menu div", "Faster");
		sleepInSecond(2);
		Assert.assertEquals(driver.findElement(By.cssSelector("#speed-button>.ui-selectmenu-text")).getText(),
				"Faster");

		// Title
		selectItemInCustomDropdow("#salutation-button", "#salutation-menu div", "Prof.");
		sleepInSecond(2);
		Assert.assertEquals(driver.findElement(By.cssSelector("#salutation-button>.ui-selectmenu-text")).getText(),
				"Prof.");
		selectItemInCustomDropdow("#salutation-button", "#salutation-menu div", "Other");
		sleepInSecond(2);
		Assert.assertEquals(driver.findElement(By.cssSelector("#salutation-button>.ui-selectmenu-text")).getText(),
				"Other");
	}

	@Test
	public void TC_02_JQuery_2() {
		driver.get("https://www.honda.com.vn/o-to/du-toan-chi-phi");

		scrollToElement("div.carousel-item");
		sleepInSecond(3);

		selectItemInCustomDropdow("#selectize-input", "#selectize-input+div>a", "CITY L");
		sleepInSecond(2);
		Assert.assertEquals(driver.findElement(By.cssSelector("#selectize-input")).getText(), "CITY L");

		scrollToElement("div.container");
		sleepInSecond(3);

		Select select = new Select(driver.findElement(By.cssSelector("select#province")));
		select.selectByVisibleText("Đà Nẵng");
		sleepInSecond(2);
		Assert.assertEquals(select.getFirstSelectedOption().getText(), "Đà Nẵng");

		select = new Select(driver.findElement(By.cssSelector("select#registration_fee")));
		select.selectByVisibleText("Khu vực II");
		sleepInSecond(2);
		Assert.assertEquals(select.getFirstSelectedOption().getText(), "Khu vực II");
	}

	@Test
	public void TC_03_React() {
		driver.get("https://react.semantic-ui.com/maximize/dropdown-example-selection/");

		selectItemInCustomDropdow(".dropdown", ".menu .text", "Matt");
		sleepInSecond(2);
		Assert.assertEquals(driver.findElement(By.cssSelector(".dropdown")).getText(), "Matt");
	}

	@Test
	public void TC_04_VueJS() {
		driver.get("https://mikerodham.github.io/vue-dropdowns/");

		selectItemInCustomDropdow(".dropdown-toggle", ".dropdown-menu a", "Second Option");
		sleepInSecond(2);
		Assert.assertEquals(driver.findElement(By.cssSelector(".dropdown-toggle")).getText(), "Second Option");
	}
	
	@Test
	public void TC_05_Editable() {
		driver.get("https://react.semantic-ui.com/maximize/dropdown-example-search-selection/");
		
		enterItemInCustomDropdow("input.search", ".active .text", "American Samoa");
		sleepInSecond(2);
		Assert.assertEquals(driver.findElement(By.cssSelector(".divider")).getText(), "American Samoa");
	}

	@AfterClass
	public void afterClass() {
		driver.quit();
	}

	public void scrollToElement(String cssLocator) {
		jsExecutor.executeScript("arguments[0].scrollIntoView(true);", driver.findElement(By.cssSelector(cssLocator)));
	}

	public void selectItemInCustomDropdow(String parentLocator, String childLocator, String textExpectedItem) {
		// 1 - Click vào 1 phần tử nào đó thuộc dropdown để nó xổ ra
		driver.findElement(By.cssSelector(parentLocator)).click();

		// 2 - Chờ cho tất cả các item trong dropdown được load ra xong
		// Lưu ý: Không dùng sleep cứng được
		// Phải có 1 hàm wait nào để nó linh động
		// Nếu như chưa tìm thấy thì sẽ tìm lại trong khoảng time được set
		// Nếu như tìm thấy rồi thì không cần phải chờ hết khoảng time này
		// Bắt được 1 locator để đại diện cho tất cả các item
		explicitWait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.cssSelector(childLocator)));

		// 3.1 - Nếu item cần chọn đang hiển thị
		// 3.2 - Nếu item cần chọn không hiển thị thì cần scroll
		// 4 - Kiểm tra text của item - nếu đúng vs cái mình cần thì click vào
		// Viết code để duyệt qua từng item và kiểm tra theo điều kiện

		// Lưu trữ tất cả các item lại thì mới duyệt qua được
		List<WebElement> allItem = driver.findElements(By.cssSelector(childLocator));

		// Duyệt qua từng element trong List trên
		// Vòng lặp
		for (WebElement item : allItem) {
			// Lấy ra text
			String textActualItem = item.getText();
			// Kiểm tra nếu nó bằng với text mình mong muốn thì click vào
			if (textActualItem.equals(textExpectedItem)) {
				item.click();
				// Khi đã tìm thấy và thỏa mãn điều kiện rồi thì không cần duyệt tiếp nữa
				break;
			}
		}
	}
	
	public void enterItemInCustomDropdow(String parentLocator, String childLocator, String textExpectedItem) {
		// 1 - Click vào 1 phần tử nào đó thuộc dropdown để nó xổ ra
		driver.findElement(By.cssSelector(parentLocator)).clear();
		driver.findElement(By.cssSelector(parentLocator)).sendKeys(textExpectedItem);
		sleepInSecond(1);

		// 2 - Chờ cho tất cả các item trong dropdown được load ra xong
		// Lưu ý: Không dùng sleep cứng được
		// Phải có 1 hàm wait nào để nó linh động
		// Nếu như chưa tìm thấy thì sẽ tìm lại trong khoảng time được set
		// Nếu như tìm thấy rồi thì không cần phải chờ hết khoảng time này
		// Bắt được 1 locator để đại diện cho tất cả các item
		explicitWait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.cssSelector(childLocator)));

		// 3.1 - Nếu item cần chọn đang hiển thị
		// 3.2 - Nếu item cần chọn không hiển thị thì cần scroll
		// 4 - Kiểm tra text của item - nếu đúng vs cái mình cần thì click vào
		// Viết code để duyệt qua từng item và kiểm tra theo điều kiện

		// Lưu trữ tất cả các item lại thì mới duyệt qua được
		List<WebElement> allItem = driver.findElements(By.cssSelector(childLocator));

		// Duyệt qua từng element trong List trên
		// Vòng lặp
		for (WebElement item : allItem) {
			// Lấy ra text
			String textActualItem = item.getText();
			// Kiểm tra nếu nó bằng với text mình mong muốn thì click vào
			if (textActualItem.equals(textExpectedItem)) {
				item.click();
				// Khi đã tìm thấy và thỏa mãn điều kiện rồi thì không cần duyệt tiếp nữa
				break;
			}
		}
	}

	public void sleepInSecond(long timeInSecond) {
		try {
			Thread.sleep(timeInSecond * 1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}