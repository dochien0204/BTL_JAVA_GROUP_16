package common;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;

import account.Account;
import computer.Computer;
import housing.Housing;
import interior.Interior;

public class FileUtil {

	public static void binaryOutputFile(String fileName, List<Housing> housings, List<Computer> computers,
			List<Interior> interiors, List<Account> accounts) throws IOException {
		// Xác định đối tượng tệp tin để xuất dữ liệu
		FileOutputStream outFile = new FileOutputStream(fileName);
		// Khai báo đối tượng thực hiện xuất
		ObjectOutputStream out = new ObjectOutputStream(outFile);

		// Xuất dữ liệu ra file
		if (fileName.compareTo("housing.bin") == 0) {
			out.writeInt(housings.size());
			for (int i = 0; i < housings.size(); i++) {
				out.writeObject(housings.get(i));
			}
		} else if (fileName.compareTo("computer.bin") == 0) {
			out.writeInt(computers.size());
			for (int i = 0; i < computers.size(); i++) {
				out.writeObject(computers.get(i));
			}
		} else if (fileName.compareTo("interior.bin") == 0) {
			out.writeInt(interiors.size());
			for (int i = 0; i < interiors.size(); i++) {
				out.writeObject(interiors.get(i));
			}
		} else if (fileName.compareTo("account.bin") == 0) {
			out.writeInt(accounts.size());
			for(int i = 0; i < accounts.size(); i++) {
				out.writeObject(accounts.get(i));
			}
		} else if (fileName.compareTo("report.bin") == 0) {
			out.writeInt(housings.size());
			for(int i = 0; i < housings.size(); i++) {
				out.writeObject(housings.get(i));
			}
		}

		out.close(); // Đóng đối tượng thực thi
	}

	public static List<Housing> binaryInputFileHousing(String fileName, int n)
			throws IOException, ClassNotFoundException {
		// Xác định đối tượng tệp tin để xuất dữ liệu
		FileInputStream inFile = new FileInputStream(fileName);
		// Khai báo đối tượng thực hiện xuất
		ObjectInputStream in = new ObjectInputStream(inFile);

		in.readInt();
		List<Housing> list = new ArrayList<>();
		for (int i = 0; i < n; i++) {
			list.add((Housing) in.readObject());
		}
		in.close();
		return list;
	}

	public static List<Computer> binaryInputFileComputer(String fileName, int n)
			throws IOException, ClassNotFoundException {
		// Xác định đối tượng tệp tin để xuất dữ liệu
		FileInputStream inFile = new FileInputStream(fileName);
		// Khai báo đối tượng thực hiện xuất
		ObjectInputStream in = new ObjectInputStream(inFile);

		in.readInt();
		List<Computer> list = new ArrayList<>();
		for (int i = 0; i < n; i++) {
			list.add((Computer) in.readObject());
		}
		in.close();
		return list;
	}

	public static List<Interior> binaryInputFileInterior(String fileName, int n)
			throws IOException, ClassNotFoundException {
		// Xác định đối tượng tệp tin để xuất dữ liệu
		FileInputStream inFile = new FileInputStream(fileName);
		// Khai báo đối tượng thực hiện xuất
		ObjectInputStream in = new ObjectInputStream(inFile);

		in.readInt();
		List<Interior> list = new ArrayList<>();
		for (int i = 0; i < n; i++) {
			list.add((Interior) in.readObject());
		}
		in.close();
		return list;
	}
	
	public static List<Account> binaryInputFileAccount(String fileName, int n)
			throws IOException, ClassNotFoundException {
		// Xác định đối tượng tệp tin để xuất dữ liệu
		FileInputStream inFile = new FileInputStream(fileName);
		// Khai báo đối tượng thực hiện xuất
		ObjectInputStream in = new ObjectInputStream(inFile);

		in.readInt();
		List<Account> list = new ArrayList<>();
		for (int i = 0; i < n; i++) {
			list.add((Account) in.readObject());
		}
		in.close();
		return list;
	}
	
	
	public static int countObject(String fileName) throws IOException {
		// Xác định đối tượng tệp tin để xuất dữ liệu
		FileInputStream inFile = new FileInputStream(fileName);
		// Khai báo đối tượng thực hiện xuất
		ObjectInputStream in = new ObjectInputStream(inFile);
		int count = in.readInt();
		in.close();
		return count;
	}
}
