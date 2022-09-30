package webdriver;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_05_Web_Browser_B1 {
	// Khai báo
	WebDriver driver;
	WebElement element;

	// Khai báo + Khởi tạo
	String projectPath = System.getProperty("user.dir");
	String osName = System.getProperty("os.name");

	@BeforeClass
	public void beforeClass() {
		if (osName.contains("Mac OS")) {
			System.setProperty("webdriver.gecko.driver", projectPath + "/browserDrivers/geckodriver");
		} else {
			System.setProperty("webdriver.gecko.driver", projectPath + "\\browserDrivers\\geckodriver.exe");
		}

		// Khởi tạo
		driver = new FirefoxDriver();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
	}

	@Test
	public void TC_01_Browser() {
		// Các hàm tương tác với Browser sẽ thông qua biến driver

		// Đóng 1 tab/ window đang active
		driver.close(); //**
		// Đóng browser
		driver.quit(); //**

		// Tìm ra 1 element (single)
		WebElement loginButton = driver.findElement(By.cssSelector("")); //**
		// Tìm ra nhiều element (multiple)
		List <WebElement> links = driver.findElements(By.cssSelector("")); //**
		
		//Mở ra cái Url truyền vào
		driver.get("https://www.facebook.com/"); //**
		//Trả về 1 Url tại page đang đứng
		String gamePageUrl = driver.getCurrentUrl();
		
		//Lấy Page Source
		String gamePageSourceCode = driver.getPageSource();
		
		//Lấy ra cái ID của tab/ window đang active(Windows/Tab)
		driver.getWindowHandle(); //1 //**
		driver.getWindowHandles(); //Tất cả //**
		
		driver.manage().getCookies(); //Bài Cookies (Framework) //**
		driver.manage().logs().getAvailableLogTypes(); //Log (Framework)
		driver.manage().window().fullscreen(); //full viền
		driver.manage().window().maximize(); //không full viền //**
	
		//Test GUI 
		//Font/ Size/ Color/ Position/ Location/...
		//Ưu tiên làm chức năng trước (Functional UI), làm giao diện sau (Graphic UI)
		
		//Chờ cho element được tìm thấy trong khoảng time xx giây
		driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);//**
		//Chờ cho page load thành công sau xx giây
		driver.manage().timeouts().pageLoadTimeout(15, TimeUnit.SECONDS);
		//Chờ cho script được inject thành công vào browser/ element sau xx giây (JSExecutor)
		driver.manage().timeouts().setScriptTimeout(15, TimeUnit.SECONDS);
		
		//Navigate
		driver.navigate().back();
		driver.navigate().forward();
		driver.navigate().refresh();
		driver.navigate().to("https://www.facebook.com/");
		
		//Alert/ Frame (Iframe)/ Window (Tab)
		driver.switchTo().alert();//**
		
		driver.switchTo().frame(0);//**
		
		driver.switchTo().window("");//**
		
	}

	@Test
	public void TC_02_Element() {
		driver.get("https://www.facebook.com/");
		// Các hàm tương tác với Element sẽ thông qua cái class WebElement (biến nào đó)
		
		//2 cách để thao tác
		//1 - Khai báo biến và dùng lại
		//Dùng đi dùng lại nhiều lần - ít nhất là 2 lần mới khai báo lại
		
		//Khai báo biến cùng với kiểu dữ liệu trả về của hàm findElement
		WebElement emailAddressTextbox = driver.findElement(By.id("email"));
		emailAddressTextbox.clear();
		emailAddressTextbox.sendKeys("vannguyen093@gmail.com");
		
		//2 - Dùng trực tiếp
		//Dùng 1 lần
		driver.findElement(By.id("email")).sendKeys("vannguyen093@gmail.com");
		
		//Xóa dữ liệu trong 1 field dạng editable
		//Textbox/ Text area/ Editable dropdown
		element.clear();
		
		//Nhập dữ liệu vào field dạng editable
		element.sendKeys("vannguyen093@gmail.com");
		element.sendKeys(Keys.ENTER);
		
		//Trả về giá trị nằm trong cái attribute của element
		element.getAttribute("placeholder");
		element.findElement(By.id("firstname")).getAttribute("value");
		
		//Trả về thuộc tính Css của element này
		//Trả về mà nền của element
		element.getCssValue("background-color");
		
		//Test GUI: Point/ Rectangle/ Size (Visualize Testing)
		element.getLocation();
		element.getRect();
		element.getSize();
		
		//Chụp hình và attach vào HTML Report
		element.getScreenshotAs(OutputType.FILE);
		
		//Trả về thẻ HTML của element
		WebElement emailAddressTextbox1 = driver.findElement(By.xpath("//*[@id=email]"));
		emailAddressTextbox1.getTagName();
		
		//Trả về text của 1 element (Link/ Header/ Err.Mess/ Message Success/...)
		element.getText();
		
		//Trả về giá trị đúng hoặc sai của 1 element có hiển thị hoặc không
		element.isDisplayed();
		
		//Trả về giá trị đúng hoặc sai của 1 element có thể thao tác được hay không
		element.isEnabled();
		
		//Trả về giá trị đúng hoặc sai của 1 element đã được chọn rồi hay chưa
		//Checkbox/ Radio
		element.isSelected();
		
		//Dropdown có 1 thư viện riêng để xử lí (Select)
		
		//Chỉ làm việc được với form (register/ login/ search/...)
		//Submit = ENTER ở 1 field nào đó
		//Submit vào 1 field nào đó ở trong form
		element.submit();
	}

	@Test
	public void TC_03_() {

	}

	@AfterClass
	public void afterClass() {
		driver.quit();
	}
}