package computer;

import java.io.IOException;
import java.util.*;

import common.FileUtil;


public class ComputerManagerImpl implements ComputerManager {

	static List<Computer> computers = new ArrayList<>();

	@Override
	public boolean addComputer(Computer c) {

		for (Computer computer : computers) {
			if (computer.getProduct_id().equals(c.getProduct_id())) {
				System.out.println("==== Id product exists! ====");
				return false;
			}
		}
		computers.add(c);
		System.out.println("==== Add computer successfully! ====");
		return true;
	}

	@Override
	public boolean editComputer(Computer c) {
		try {
			getList();
		} catch (ClassNotFoundException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		for (Computer computer : computers) {
			if (computer.getProduct_id().equals(c.getProduct_id())) {

				computer.setProduct_name(c.getProduct_name());
				computer.setProduct_price(c.getProduct_price());
				computer.setProduct_total(c.getProduct_total());
				computer.setTypeOfChip(c.getTypeOfChip());
				computer.setRam(c.getRam());
				computer.setHardDrive(c.getHardDrive());
				computer.setOperatingSystem(c.getOperatingSystem());

				System.out.println("==== Edit computer successfully! ====");
				return true;
			}
		}
		System.out.println("==== Id product invalid! ====");
		return false;
	}

	@Override
	public boolean delComputer(Computer c) {
		try {
			getList();
		} catch (ClassNotFoundException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		for (Computer computer : computers) {
			if (computer.getProduct_id().equals(c.getProduct_id())) {
				computers.remove(computer);
				System.out.println("==== Delete computer successfully! ====");
				return true;
			}
		}
		System.out.println("==== Id product invalid! ====");
		return false;
	}

	@Override
	public List<Computer> searchComputer(String name) {
		try {
			getList();
		} catch (ClassNotFoundException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		List<Computer> computersHasName = new ArrayList<>();
		for (Computer computer : computers) {
			if (computer.getProduct_name().toLowerCase().contains(name)) {
				computersHasName.add(computer);
			}
		}
		return computersHasName;
	}


	@Override
	public List<Computer> searchComputerByRam(int ram) {
		try {
			getList();
		} catch (ClassNotFoundException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		List<Computer> list = new ArrayList<>();
		for (Computer computer : computers) {
			if (computer.getRam() == ram) {
				list.add(computer);
			}
		}
		return list;
	}

	@Override
	public List<Computer> searchComputerByHardDrive(String hardDrive) {
		try {
			getList();
		} catch (ClassNotFoundException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		List<Computer> list = new ArrayList<>();
		for (Computer computer : computers) {
			if (computer.getHardDrive().equals(hardDrive)) {
				list.add(computer);
			}
		}
		return list;
	}

	@Override
	public List<Computer> searchComputerByCPU(String cpu) {
		try {
			getList();
		} catch (ClassNotFoundException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		List<Computer> list = new ArrayList<>();
		for (Computer computer : computers) {
			if (computer.getTypeOfChip().toLowerCase().contains(cpu)) {
				list.add(computer);
			}
		}
		return list;
	}

	@Override
	public List<Computer> sortedComputer(double price) {
		try {
			getList();
		} catch (ClassNotFoundException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		List<Computer> list = new ArrayList<>();
		for (Computer computer : computers) {
			if (computer.getProduct_price() >= price) {
				list.add(computer);
			}
		}
		Collections.sort(list, new sortByPrice());
		return list;
	}

	@Override
	public List<Computer> sortedComputerByName(boolean isINC) {
		try {
			getList();
		} catch (ClassNotFoundException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		List<Computer> list = computers;
		if (isINC) {
			Collections.sort(list, new sortByName());
		} else {
			Collections.sort(list, new sortByName().reversed());
		}
		return list;
	}

	@Override
	public List<Computer> sortedComputerByTotalQuantity(boolean isINC) {
		try {
			getList();
		} catch (ClassNotFoundException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		List<Computer> list = computers;
		if (isINC) {
			Collections.sort(list, new sortByTotalQuantity());
		} else {
			Collections.sort(list, new sortByTotalQuantity().reversed());
		}
		return list;
	}

	public void getList() throws ClassNotFoundException, IOException {
		computers = FileUtil.binaryInputFileComputer("computer.bin", FileUtil.countObject("computer.bin"));
	}
}


class sortByPrice implements Comparator<Computer> {
	public int compare(Computer o1, Computer o2) {
		return Double.compare(o1.getProduct_price(), o2.getProduct_price());
	}
}

class sortByTotalQuantity implements Comparator<Computer> {
	public int compare(Computer o1, Computer o2) {
		return o1.getProduct_total() - o2.getProduct_total();
	}
}

class sortByName implements Comparator<Computer> {
	public int compare(Computer o1, Computer o2) {
		return o1.getProduct_name().compareToIgnoreCase(o2.getProduct_name());
	}
}
