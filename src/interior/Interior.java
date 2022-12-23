package interior;

import java.io.Serializable;
import java.util.List;
import java.util.Scanner;

import common.Product;

public class Interior extends Product implements Serializable {

//	Constants
	public static final String COLOR = "No color";
	public static final String SIZE = "No size";
	public static final String MATERIAL = "No material";

//	Object's properties
	private String color;
	private String size;
	private String material;

//	Construstor methods	
	public Interior() {
		this(Interior.PRODUCT_ID, Interior.PRODUCT_NAME, Interior.PRODUCT_PRICE, Interior.PRODUCT_TOTAL, Interior.COLOR,
				Interior.SIZE, Interior.MATERIAL);
	}

	public Interior(String product_id, String product_name, double product_price, int product_total, String color,
			String size, String material) {
		super(product_id, product_name, product_price, product_total);
		this.color = color;
		this.size = size;
		this.material = material;
	}

//	Other methods
	public void Input() {
		super.Input();
		Scanner sc = new Scanner(System.in);
		System.out.print("Input color: ");
		color = sc.nextLine();
		System.out.print("Input size: ");
		size = sc.nextLine();
		System.out.print("Input material: ");
		material = sc.nextLine();
	}

	public void Output() {
		super.Output();
		System.out.printf("%-30s %-25s %-30s", color, size, material);
		System.out.println();
	}

	public void OutputAInterior() {
		super.OutputAProduct();
		System.out.println("Color: " + color);
		System.out.println("Size: " + size);
		System.out.println("Material: " + material);
	}

	public String getColor() {
		return color;
	}

	public void setColor(String color) {
		this.color = color;
	}

	public String getSize() {
		return size;
	}

	public void setSize(String size) {
		this.size = size;
	}

	public String getMaterial() {
		return material;
	}

	public void setMaterial(String material) {
		this.material = material;
	}

	@Override
	public String toString() {
		return "Interior [id=" + getProduct_id() + ", name=" + getProduct_name() + ", price=" + getProduct_price()
				+ ", total=" + getProduct_total() + ", color=" + color + ", size=" + size + ", material=" + material
				+ "]";
	}
}
