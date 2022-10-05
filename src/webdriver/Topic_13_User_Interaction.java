package webdriver;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.Color;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_13_User_Interaction {
	WebDriver driver;
	JavascriptExecutor jsExcutor;
	Actions action;
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
		jsExcutor = (JavascriptExecutor) driver;
		action = new Actions(driver);
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
	}

//	@Test
	public void TC_01_Tooltip() {
		driver.get("https://automationfc.github.io/jquery-tooltip/");

		action.moveToElement(driver.findElement(By.id("age"))).perform();
		sleepInSecond(3);
		Assert.assertEquals(driver.findElement(By.cssSelector(".ui-tooltip-content")).getText(),
				"We ask for your age only for statistical purposes.");
	}

//	@Test
	public void TC_02_Myntra() {
		driver.get("http://www.myntra.com/");

		action.moveToElement(driver.findElement(By.xpath("//a[@class='desktop-main' and text()='Kids']"))).perform();
		sleepInSecond(3);
		driver.findElement(By.xpath("//a[@class='desktop-categoryName' and text()='Home & Bath']")).click();
		Assert.assertEquals(driver.findElement(By.xpath("//h1[text()='Kids Home Bath']")).getText(), "Kids Home Bath");
	}

//	@Test
	public void TC_03_Fahasa() {
		driver.get("https://www.fahasa.com/");

		action.moveToElement(driver.findElement(By.xpath("//span[@class='icon_menu']"))).perform();
		sleepInSecond(3);
		action.moveToElement(driver.findElement(By.xpath("//a[@title='VPP - Dụng Cụ Học Sinh']"))).perform();
		sleepInSecond(3);
		Assert.assertEquals(driver.findElement(By.xpath("//input[@class='fhs_menu_content fhs_column_left']//a[text()='Gôm - Tẩy']")).getText(), "Gôm - Tẩy");
	}
	
//	@Test
	public void TC_04_Click_And_Hold() {
		driver.get("https://automationfc.github.io/jquery-selectable/");
		List<WebElement> listNumber = driver.findElements(By.cssSelector("#selectable>li"));
		
		//1 - Click vào số bắt đầu và giữ chuột trái
		//2 - Di chuột đến số kết thúc
		//3 - Nhả chuột trái ra
		action.clickAndHold(listNumber.get(0)).moveToElement(listNumber.get(7)).release().perform();
		sleepInSecond(3);
		//Verify
//		List<WebElement> listNumberSelected = driver.findElements(By.cssSelector("#selecable>li.ui-selectd"));
//		Assert.assertEquals(listNumberSelected.size(), 6);
		String rgbaColor = driver.findElement(By.cssSelector("#selectable>li.ui-selected")).getCssValue("background-color");
		//Convert to Hexa color
		String hexaColor = Color.fromString(rgbaColor).asHex().toUpperCase();
		//Verify background color
		Assert.assertEquals(hexaColor, "#F39814");
	}
	
//	@Test
	public void TC_05_Click_Random() {
		driver.get("https://automationfc.github.io/jquery-selectable/");
		//Lấy ra 12 số cần thao tác
		List<WebElement> listNumber = driver.findElements(By.cssSelector("#selectable>li"));
		//1 - Nhấn phím Ctrl và không nhả phím
		Keys key = null;
		if (osName.contains("Mac")) {
			key = Keys.COMMAND; //MAC
		} else {
			key = Keys.CONTROL; //WIN
		}
		action.keyDown(key).perform();
		
		//2 - Chọn random số
		action.click(listNumber.get(1))
		.click(listNumber.get(4))
		.click(listNumber.get(6))
		.click(listNumber.get(10)).perform();
		sleepInSecond(3);
		
		//3 - Nhả phím Ctrl
		action.release();
		
		//Verify
		String rgbaColor = driver.findElement(By.cssSelector("#selectable>li.ui-selected")).getCssValue("background-color");
		//Convert to Hexa color
		String hexaColor = Color.fromString(rgbaColor).asHex().toUpperCase();
		//Verify background color
		Assert.assertEquals(hexaColor, "#F39814");
	}
	
//	@Test
	public void TC_06_Double_Click() {
		driver.get("https://automationfc.github.io/basic-form/index.html");
		jsExcutor.executeScript("arguments[0].scrollIntoView(true);",driver.findElement(By.xpath("//button[text()='Double click me']")));
		action.doubleClick(driver.findElement(By.xpath("//button[text()='Double click me']"))).perform();
		
		Assert.assertEquals(driver.findElement(By.cssSelector("#demo")).getText(), "Hello Automation Guys!");
	}
	
//	@Test
	public void TC_07_Right_Click() {
		driver.get("http://swisnl.github.io/jQuery-contextMenu/demo.html");
		
		//Click chuột phải vào btn
		action.contextClick(driver.findElement(By.cssSelector(".context-menu-one"))).perform();
		sleepInSecond(3);
		
		//Hover vào 1 item bất kỳ
		action.moveToElement(driver.findElement(By.cssSelector(".context-menu-icon-paste"))).perform();
		
		//Verify Paste có trạng thái hover và visible
		Assert.assertTrue(driver.findElement(By.cssSelector(".context-menu-icon-paste.context-menu-hover.context-menu-visible")).isDisplayed());
		
		//Click vào Paste
		action.click(driver.findElement(By.cssSelector(".context-menu-icon-paste"))).perform();
		
		//Handle alert
		driver.switchTo().alert().accept();
		
		//Verify Paste có trạng thái: invisible
		Assert.assertFalse(driver.findElement(By.cssSelector(".context-menu-icon-paste")).isDisplayed());
	}

	@Test
	public void TC_08_Drag_And_Drop_HTML4() {
		driver.get("https://automationfc.github.io/kendo-drag-drop/");
		
		WebElement smallCircle = driver.findElement(By.cssSelector("#draggable"));
		WebElement bigCircle = driver.findElement(By.cssSelector("#droptarget"));
		
		action.dragAndDrop(smallCircle, bigCircle).perform();
		sleepInSecond(3);
		
		Assert.assertEquals(bigCircle.getText(), "You did great!");
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
}