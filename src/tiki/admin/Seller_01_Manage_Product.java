package tiki.admin;

import org.testng.annotations.Test;

public class Seller_01_Manage_Product {

	@Test(groups = {"admin", "product"})
	public void Product_01_Manage_Add_Product() {
	}
	
	@Test(groups = {"admin", "product"}, dependsOnMethods = "Product_01_Manage_Add_Product")
	public void Product_02_Manage_View_Product() {
	}
	
	@Test(groups = {"admin", "product"}, dependsOnMethods = "Product_01_Manage_Add_Product")
	public void Product_03_Manage_Edit_Product() {
	}
	
	@Test(groups = {"admin", "product"}, dependsOnMethods = "Product_01_Manage_Add_Product")
	public void Product_04_Manage_Delete_Product() {
	}

}
