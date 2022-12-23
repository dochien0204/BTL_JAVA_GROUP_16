package housing;

import java.io.Serializable;
import java.util.Scanner;

import common.Product;

public class Housing extends Product implements Serializable {

	private static final double AREA = 0.0d;
	private static final String LOCATION = "No Location";
	private static final String OWNER = "No Owner";

	private double area;
	private String location;
	private String owner;

	public Housing() {
		this(Housing.PRODUCT_ID, Housing.PRODUCT_NAME, Housing.PRODUCT_PRICE, Housing.PRODUCT_TOTAL, Housing.AREA,
				Housing.LOCATION, Housing.OWNER);
	}

	public Housing(String product_id, String product_name, double product_price, int product_total, double area,
			String location, String owner) {
		super(product_id, product_name, product_price, product_total);
		this.area = area;
		this.location = location;
		this.owner = owner;
	}

	public double getArea() {
		return area;
	}

	public void setArea(double area) {
		this.area = area;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public String getOwner() {
		return owner;
	}

	public void setOwner(String owner) {
		this.owner = owner;
	}

	@Override
	public String toString() {
		return "Housing [ " + super.toString() + "area=" + area + ", location=" + location + ", owner=" + owner + "]";
	}

	public void input() {
		Scanner sc = new Scanner(System.in);
		super.Input();
		System.out.println("Nhập Diện tích: ");
		area = sc.nextDouble();
		sc.nextLine();
		System.out.println("Nhập địa điểm: ");
		location = sc.nextLine();
		System.out.println("Nhập chủ sở hữu: ");
		owner = sc.nextLine();
	}

	public void output() {
		super.Output();
		System.out.printf("%-15s%-15s%-15s", String.format("%.3f", area), location, owner);
	}
	

}
