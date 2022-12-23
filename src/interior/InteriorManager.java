package interior;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;

public interface InteriorManager {

	public boolean addInterior(Interior i);
	public boolean editInterior(Interior i);
	public boolean delInterior(Interior i);
	
	public Interior searchInteriorById(String id);
	public List<Interior> searchInteriorByName(String name);
	public List<Interior> searchInteriorByPrice(double price);
	public List<Interior> searchInteriorByColor(String color);
	public List<Interior> searchInteriorByMaterial(String material);
	
	public List<Interior> sortedInteriorByPrice(double price) throws ClassNotFoundException, IOException;
	public List<Interior> sortedInteriorByTotal(boolean isINC) throws ClassNotFoundException, IOException;
	
}
