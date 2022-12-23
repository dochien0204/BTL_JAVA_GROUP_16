package computer;

import java.util.List;

public interface ComputerManager {

	public boolean addComputer(Computer c);
	public boolean editComputer(Computer c);
	public boolean delComputer(Computer c);
	
	public List<Computer> searchComputer(String name);
	public List<Computer> searchComputerByRam(int ram);
	public List<Computer> searchComputerByHardDrive(String hardDrive);
	public List<Computer> searchComputerByCPU(String cpu);
	
	public List<Computer> sortedComputer(double price);
	public List<Computer> sortedComputerByName(boolean isINC);
	public List<Computer> sortedComputerByTotalQuantity(boolean isINC);
}
