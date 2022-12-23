package housing;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;

import common.FileUtil;
import common.MainView;

public class HousingManagerImpl implements HousingManager {

	public final Scanner sc = new Scanner(System.in);

	@Override
	public boolean addHousing(Housing h) {
		add(h, MainView.housings);
		try {
			FileUtil.binaryOutputFile("housing.bin", MainView.housings, null, null, null);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return true;
	}

	@Override
	public boolean editHousing(Housing h) {
		System.out.println("Sản phẩm bạn muốn sửa là: " + h.getProduct_id());
		System.out.println("Nhập Tên BDS mới: ");
		String newName = sc.nextLine();
		h.setProduct_name(newName);
		System.out.println("Nhập giá mới: ");
		double newPrice = sc.nextDouble();
		h.setProduct_price(newPrice);
		System.out.println("Nhập số lượng mới: ");
		int newTotal = sc.nextInt();
		sc.nextLine();
		h.setProduct_total(newTotal);
		System.out.println("Nhập diện tích mới: ");
		double newArea = sc.nextDouble();
		sc.nextLine();
		h.setArea(newArea);
		System.out.println("Nhập địa điểm mới: ");
		String newLocation = sc.nextLine();
		h.setLocation(newLocation);
		System.out.println("Nhập chủ sở hữu mới: ");
		String newOwner = sc.nextLine();
		h.setOwner(newOwner);
		System.out.println("Sửa BĐS thành công");
		return true;
	}

	@Override
	public boolean delHousing(Housing h) {
		for (Housing hs : MainView.housings) {
			if (hs.equals(h)) {
				MainView.housings.remove(h);
				return true;
			}
		}
		return false;
	}

	@Override
	public List<Housing> searchHousingByName(String name) {
		List<Housing> housings = new ArrayList<>();
		name = name.toLowerCase();
		for (Housing h : MainView.housings) {
			if (h.getProduct_name().toLowerCase().contains(name)) {
				housings.add(h);
			}
		}
		return housings;
	}

	@Override
	public List<Housing> searchHousingByPrice(double price) {
		List<Housing> housings = new ArrayList<>();
		for (Housing h : MainView.housings) {
			if (h.getProduct_price() >= price) {
				housings.add(h);
			}
		}
		return housings;
	}

	@Override
	public List<Housing> searchHousingByArea(double area) {
		List<Housing> housings = new ArrayList<>();
		for (Housing h : MainView.housings) {
			if (h.getArea() >= area) {
				housings.add(h);
			}
		}
		return housings;
	}

	@Override
	public List<Housing> searchHousingByOwner(String owner) {
		List<Housing> housings = new ArrayList<>();
		for (Housing h : MainView.housings) {
			if (h.getOwner().compareToIgnoreCase(owner) == 0) {
				housings.add(h);
			}
		}
		return housings;
	}

	@Override
	public List<Housing> searchHousingByLocation(String location) {
		List<Housing> housings = new ArrayList<>();
		for (Housing h : MainView.housings) {
			if (h.getLocation().compareToIgnoreCase(location) == 0) {
				housings.add(h);
			}
		}
		return housings;
	}

	public boolean add(Housing h, List<Housing> list) {
			list.add(h);
			return true;
	}

	/**
	 * This method is used to check Exists Housing
	 * 
	 * @param h
	 * @param list
	 * @return
	 */
	public boolean checkExsistHousing(Housing h, List<Housing> list) {
		for (Housing hs : list) {
			if (h.getProduct_id().compareToIgnoreCase(hs.getProduct_id()) == 0) {
				return true;
			}
		}
		return false;
	}

	/*
	 * This method is used to find Housing by Id
	 */
	public Housing findById(List<Housing> list, String id) {
		for (Housing h : list) {
			if (h.getProduct_id().compareToIgnoreCase(id) == 0) {
				return h;
			}
		}
		return null;
	}

	/**
	 * This method is used to display List Housing
	 */
	public void displayListHousing(List<Housing> list) {
		title();
		for (Housing h : list) {
			h.output();
			System.out.println();
		}
	}

	/**
	 * This method is used to sort List Housing by price that has price more than or
	 * equal intermediate price (Chỉ lấy các price lớn hơn tham số price truyền vào)
	 * 
	 * @param price
	 * @return
	 */
	@Override
	public List<Housing> sortedHousingByPrice(double price, boolean isNC) {
		List<Housing> list = new ArrayList<>();
		for (Housing h : MainView.housings) {
			if (h.getProduct_price() >= price) {
				list.add(h);
			}
		}
		if (list.isEmpty()) {
			return list;
		} else {
			if(isNC) {
				Collections.sort(list, new sortedByPrice());
			} else {
				Collections.sort(list, new sortedByPrice().reversed());
			}
		}
		return list;
	}

	/**
	 * This method is used to sort list housing by area that has area more than area
	 * or equal area param
	 * @param area
	 * @return
	 */
	@Override
	public List<Housing> sortedHousingByArea(double area, boolean isNC) {
		List<Housing> list = new ArrayList<>();
		for (Housing h : MainView.housings) {
			if (h.getArea() >= area) {
				list.add(h);
			}
		}
		if (list.isEmpty()) {
			return list;
		} else {
			if(isNC) {
				Collections.sort(list, new sortedByArea());
			} else {
				Collections.sort(list, new sortedByArea().reversed());
			}
		}
		return list;
	}
	
	public static void title() {
		System.out.println();
		System.out.printf("%-10s%-10s%-15s%-15s%-15s%-15s%-15s%-15s","", "ID", "Tên BĐS", "Giá", "Số lượng", "Diện tích", "Địa điểm", "Chủ sở hữu");
		System.out.println();
	}

}

class sortedByPrice implements Comparator<Housing> {

	@Override
	public int compare(Housing o1, Housing o2) {
		return (int) (o1.getProduct_price() - o2.getProduct_price());
	}
}

class sortedByArea implements Comparator<Housing> {

	@Override
	public int compare(Housing o1, Housing o2) {
		return (int) (o1.getArea() - o2.getArea());
	}
}