package javaTester;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;

public class Topic_02_Data_Type {

	public static void main(String[] args) {
		//Cách khai báo 1 biến
		//1 - Kiểu dữ liệu của biến
		//2 - Tên biến
		//3 - Giá trị của biến
		
		//2 cách khai báo và gán giá trị
		
		//1 - Vừa khai báo vừa gán trực tiếp luôn:
		//Strting name = "Automation Testing";
		
		//2 - Khai báo trước rồi gán sau
		//String name;
		//name = "Automation Testing";
		//name = "Automation FC";
		
		//I - Kiểu dữ liệu nguyên thủy (Primitive)
		//1. Số nguyên (không có phần thập phân): byte short int long
		byte bNumber = 127;
		short sNumber = 32000;
		int iNumber = 49999329; 
		long lNumber = 325766324;
		
		//2. Số thực (có phần thập phân): float double
		float studentPoint = 9.5f;
		double employeeSalary = 35.6d;
		
		//3. Logic: boolean
		boolean status = true; //Nam
		boolean status1 = false; //Nữ
		
		//4. Kí tự: char
		char a = 'A';
		
		//II - Kiểu dữ liệu tham chiếu (Reference)
		//Class
		FirefoxDriver driver = new FirefoxDriver();
		
		//Interface
		WebElement firstNameTextbox;
		
		//String
		String firstName = "Automation Testing";
		
		//Object
		Object people;
		
		//Array
		String[] studentName = {"Nguyễn Lê Văn","Nguyễn Lê","Văn Nguyễn"};
		
		//Collection: List/ Set/ Queue
		List <WebElement> checkboxes = driver.findElements(By.cssSelector(""));
		
		//Map
		Map<String, Integer> student = new HashMap<String, Integer>();
	}

}
