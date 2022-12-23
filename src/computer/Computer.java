package computer;

import java.io.Serializable;
import java.util.Scanner;

import common.Product;

public class Computer extends Product implements Serializable {

	private int ram;
	private String typeOfChip;
	private String hardDrive;
	private String operatingSystem;

	public Computer() {

	}

	public Computer(String product_id, String product_name, double product_price, int product_total, String resolution,
			String typeOfChip, int ram, String hardDrive, String operatingSystem, Double weight) {
		super(product_id, product_name, product_price, product_total);
		this.typeOfChip = typeOfChip;
		this.ram = ram;
		this.hardDrive = hardDrive;
		this.operatingSystem = operatingSystem;
	}

	public String getTypeOfChip() {
		return typeOfChip;
	}

	public void setTypeOfChip(String typeOfChip) {
		this.typeOfChip = typeOfChip;
	}

	public int getRam() {
		return ram;
	}

	public void setRam(int ram) {
		this.ram = ram;
	}

	public String getHardDrive() {
		return hardDrive;
	}

	public void setHardDrive(String hardDrive) {
		this.hardDrive = hardDrive;
	}

	public String getOperatingSystem() {
		return operatingSystem;
	}

	public void setOperatingSystem(String operatingSystem) {
		this.operatingSystem = operatingSystem;
	}

	
	@Override
	public String toString() {
		return "Computer [typeOfChip=" + typeOfChip + ", ram=" + ram + ", hardDrive=" + hardDrive + ", operatingSystem="
				+ operatingSystem + ", product_id=" + getProduct_id() + ", product_name=" + getProduct_name() + ", product_price="
				+ getProduct_price() + ", product_total=" + getProduct_total() + "]\n";
	}

	public void input() {
		Scanner scanner = new Scanner(System.in);
		super.Input();
		
		System.out.print("Enter type of chip: ");
		typeOfChip = scanner.nextLine();

		System.out.print("Enter capacity ram: ");
		ram = scanner.nextInt();
		scanner.nextLine();

		System.out.print("Enter capacity hard drive: ");
		hardDrive = scanner.nextLine();

		System.out.print("Enter operating system: ");
		operatingSystem = scanner.nextLine();
	}

	public void output() {
		super.Input();
		System.out.printf("%-15s %-5s %-12s %-20s", typeOfChip, ram + "GB", hardDrive,
				operatingSystem);
		System.out.println();
	}

}
