package housing;

import java.util.List;

public interface HousingManager {

	public boolean addHousing(Housing h);
	public boolean editHousing(Housing h);
	public boolean delHousing(Housing h);
	public List<Housing> searchHousingByName(String name);
	public List<Housing> searchHousingByPrice(double price);
	public List<Housing> searchHousingByArea(double area);
	public List<Housing> searchHousingByOwner(String owner);
	public List<Housing> searchHousingByLocation(String location); 
	public List<Housing> sortedHousingByPrice(double price, boolean isNC);
	public List<Housing> sortedHousingByArea(double area, boolean isNC);
		
}
