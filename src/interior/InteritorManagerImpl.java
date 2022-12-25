package interior;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;

import common.MainView;

public class InteritorManagerImpl implements InteriorManager {

	@Override
	public boolean addInterior(Interior i) {
		if (checkIdExist(i.getProduct_id())) {
			return false;
		}
		MainView.interiors.add(i);
		return true;
	}

	@Override
	public boolean editInterior(Interior i) {
		for (Interior interior : MainView.interiors) {
			if (interior.getProduct_id().compareTo(i.getProduct_id()) == 0) {
				interior.setProduct_name(i.getProduct_name());
				interior.setProduct_price(i.getProduct_price());
				interior.setProduct_total(i.getProduct_total());
				interior.setColor(i.getColor());
				interior.setSize(i.getSize());
				interior.setMaterial(i.getMaterial());
				return true;
			}
		}
		System.out.println("Interiors with id: " + i.getProduct_id() + " does not exist.");
		return false;
	}

	@Override
	public boolean delInterior(Interior i) {
		for (int index = 0; index < MainView.interiors.size(); index++) {
			if (MainView.interiors.get(index).getProduct_id().compareTo(i.getProduct_id()) == 0) {
				MainView.interiors.remove(index);
				return true;
			}
		}
		return false;
	}

	@Override
	public Interior searchInteriorById(String id) {
		for (Interior interior : MainView.interiors) {
			if (interior.getProduct_id().compareTo(id) == 0) {
				return interior;
			}
		}
		return null;
	}

	@Override
	public List<Interior> searchInteriorByName(String name) {
		List<Interior> interiorsByName = new ArrayList<>();
		for (Interior interior : MainView.interiors) {
			if (interior.getProduct_name().toLowerCase().contains(name.toLowerCase())) {
				interiorsByName.add(interior);
			}
		}
		return interiorsByName;
	}

	@Override
	public List<Interior> searchInteriorByPrice(double price) {
		List<Interior> interiorsByPrice = new ArrayList<>();
		for (Interior interior : MainView.interiors) {
			if (interior.getProduct_price() == price) {
				interiorsByPrice.add(interior);
			}
		}
		return interiorsByPrice;
	}

	@Override
	public List<Interior> searchInteriorByColor(String color) {
		List<Interior> interiorsByColor = new ArrayList<>();
		for (Interior interior : MainView.interiors) {
			if (interior.getColor().toLowerCase().contains(color.toLowerCase())) {
				interiorsByColor.add(interior);
			}
		}
		return interiorsByColor;
	}

	@Override
	public List<Interior> searchInteriorByMaterial(String material) {
		List<Interior> interiorsByMaterial = new ArrayList<>();
		for (Interior interior : MainView.interiors) {
			if (interior.getMaterial().toLowerCase().contains(material.toLowerCase())) {
				interiorsByMaterial.add(interior);
			}
		}
		return interiorsByMaterial;
	}

	@Override
	public List<Interior> sortedInteriorByPrice(double price) throws ClassNotFoundException, IOException {
		Scanner sc = new Scanner(System.in);
		List<Interior> interiorsSortedByPrice = new ArrayList<>();
		System.out.println("1. Price min to sort.");
		System.out.println("2. Price max to sort.");
		System.out.print("Your choose: ");
		int choose = Integer.parseInt(sc.nextLine());
		if (choose == 1) {
			for (Interior interior : MainView.interiors) {
				if (interior.getProduct_price() >= price) {
					interiorsSortedByPrice.add(interior);
				}
			}
		} else if (choose == 2) {
			for (Interior interior : MainView.interiors) {
				if (interior.getProduct_price() <= price) {
					interiorsSortedByPrice.add(interior);
				}
			}
		} else {
			throw new IllegalArgumentException("Unexpected value: " + choose);
		}

		if (sortType()) {
			Collections.sort(interiorsSortedByPrice, new sortByPrice());
		} else {
			Collections.sort(interiorsSortedByPrice, new sortByPrice().reversed());
		}
		return interiorsSortedByPrice;
	}

	@Override
	public List<Interior> sortedInteriorByTotal(boolean isINC) throws ClassNotFoundException, IOException {
		// Xác định đối tượng tệp tin để xuất dữ liệu
		FileInputStream inFile = new FileInputStream("interior.bin");
		// Khai báo đối tượng thực hiện xuất
		ObjectInputStream in = new ObjectInputStream(inFile);

		List<Interior> interiorSortByTotal = new ArrayList<>();
		Interior interior = new Interior();
		for (int i = 0; i < MainView.interiors.size(); i++) {
			interior = (Interior) in.readObject();
			interiorSortByTotal.add(interior);
		}
		in.close();

		if (isINC) {
			interiorSortByTotal.sort((i1, i2) -> i1.getProduct_total() - i2.getProduct_total());
		} else {
			interiorSortByTotal.sort((i1, i2) -> i2.getProduct_total() - i1.getProduct_total());
		}

//		// Xác định đối tượng tệp tin để cập nhật dữ liệu ngẫu nhiên
//		RandomAccessFile raf = new RandomAccessFile("interior.bin", "rw");
//
//		// Di chuyển xuống cuối file để thêm
//		raf.seek(raf.length());
//		raf.writeChars("Sort By Total");
//
//		long pos = raf.length();
//
//		interiorSortByTotal.forEach(item -> {
//			try {
//				raf.writeUTF(item.toString());
//			} catch (IOException e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			}
//		});
//
//		raf.close(); // Đóng đối tượng thực thi
		return interiorSortByTotal;
	}

	public boolean checkIdExist(String product_id) {
		for (Interior interior : MainView.interiors) {
			if (interior.getProduct_id().compareTo(product_id) == 0) {
				return true;
			}
		}
		return false;
	}

	public static boolean sortType() {
		Scanner sc = new Scanner(System.in);
		System.out.println("Sort type: ");
		System.out.println("1. ASC");
		System.out.println("2. DESC");
		System.out.print("Your choose: ");
		int choose = Integer.parseInt(sc.nextLine());
		if (choose == 1) {
			return true;
		} else if (choose == 2) {
			return false;
		}
		throw new IllegalArgumentException("Unexpected value: " + choose);
	}
}

class sortByPrice implements Comparator<Interior> {
	@Override
	public int compare(Interior i1, Interior i2) {
		return (int) (i1.getProduct_price() - i2.getProduct_price());
	}
}
