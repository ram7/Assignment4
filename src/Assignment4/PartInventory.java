package Assignment4;



import java.util.ArrayList;

public class PartInventory {
	private ArrayList<Part> parts;
	private ArrayList<PartInventoryObserver> observers;
	
	public PartInventory() {
		parts = new ArrayList<Part>();
		observers = new ArrayList<PartInventoryObserver>();
	}
	
	public void deletePart(Part p) {
		parts.remove(p);
		updateObservers();
		p.updateDeleted();//signal observing views that part has been deleted
	}
	
	public ArrayList<Part> getParts() {
		return parts;
	}
	
	public int getNumParts() {
		return parts.size();
	}
	
	public boolean partNumberExists(String pNum, Part part) {
		for(Part p : parts) {
			if(pNum.equalsIgnoreCase(p.getPartNumber()))
				return true;
		}
		return false;
	}
	
	public boolean checkUnitQuantity(String u, Part part){
		if(u.equals("Linear Feet") || u.equals("Pieces")){
			return false;
		}
		return true;
	}
	
	public Part addPart(Part part, int id, String pNum, String pName, String v, String u, String e) throws IllegalArgumentException {
		if(partNumberExists(pNum, part))
			throw new IllegalArgumentException("Part Number Already Exists breh!");
		if(checkUnitQuantity(u, part))
			throw new IllegalArgumentException("Unit of Quantity cannot be Uknown! Must be set to Linear Feet or Pieces");
		Part p = new Part(id, pNum, pName, v, u, e);
		parts.add(p);
		updateObservers();
		return p;
	}
	
	public void registerObserver(PartInventoryObserver o) {
		observers.add(o);
	}
	
	public void updateObservers() {
		for(PartInventoryObserver o : observers) {
			try {
				o.updateObserver(this);
			} catch(Exception e) {
				//ignore for now
			}
		}
	}
}

