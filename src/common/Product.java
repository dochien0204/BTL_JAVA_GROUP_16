package common;

import java.io.Serializable;
import java.util.Scanner;

public class Product implements Serializable {
	
//	Constants
	public static final String PRODUCT_ID = "No product id";
	public static final String PRODUCT_NAME = "No product name";
	public static final double PRODUCT_PRICE = 0;
	public static final int PRODUCT_TOTAL = 0;

//	Object's properties
	private String product_id;
	private String product_name;
	private double product_price;
	private int product_total;

//	Construstor methods
	public Product() {
		this(Product.PRODUCT_ID, Product.PRODUCT_NAME, Product.PRODUCT_PRICE, Product.PRODUCT_TOTAL);
	}
	
	public Product(String product_id, String product_name, double product_price, int product_total) {
		super();
		this.product_id = product_id;
		this.product_name = product_name;
		this.product_price = product_price;
		this.product_total = product_total;
	}

//	Other methods
	public void Input() {
		Scanner sc = new Scanner(System.in);
		System.out.print("Input id: ");
		product_id = sc.nextLine();
		System.out.print("Input name: ");
		product_name = sc.nextLine();
		System.out.print("Input price: ");
		product_price = sc.nextDouble();
		System.out.print("Input total: ");
		product_total = sc.nextInt();
	}
	
	public void Output() {
        System.out.printf("%-10s %-20s %-20s %-11d", product_id, product_name, FormatDouble(product_price), product_total);
	}
	
	public void OutputAProduct() {
		System.out.println("Id: " + product_id);
		System.out.println("Name: " + product_name);
		System.out.println("Price: " + FormatDouble(product_price));
		System.out.println("Total: " + product_total);
	}
	
	public String getProduct_id() {
		return product_id;
	}
	
	public void setProduct_id(String product_id) {
		this.product_id = product_id;
	}
	
	public String getProduct_name() {
		return product_name;
	}
	
	public void setProduct_name(String product_name) {
		this.product_name = product_name;
	}
	
	public double list() {
		return product_price;
	}
	
	public double getProduct_price() {
		return product_price;
	}
	
	public void setProduct_price(double product_price) {
		this.product_price = product_price;
	}
	
	public int getProduct_total() {
		return product_total;
	}
	
	public void setProduct_total(int product_total) {
		this.product_total = product_total;
	}
	
	@Override
	public String toString() {
		return "Product [product_id=" + product_id + ", product_name=" + product_name + ", product_price="
				+ product_price + ", product_total=" + product_total + "]";
	}
	
	public String FormatDouble(double price)
	{
	    if(price == (long) price)
	        return String.format("%d",(long)price);
	    else
	        return String.format("%f",price);
	}
}
