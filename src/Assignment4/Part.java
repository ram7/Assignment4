package Assignment4;



import java.util.ArrayList;

public class Part {
	private int id;
	private int quantity;
	private String location;
	private String partNumber;
	private String partName;
	private String vendor;
	private String unit;
	private String externalNumber;

	private ArrayList<PartObserver> observers;
	private ItemPart other;

	
	public Part(int id, String pNum, String pName, String u, String e) {
		this(id, pNum, pName, "", u, e);
	}
	
	
	public Part(int idNum, String pNum, String pName, String v, String u, String e) {
		if (pNum == null || pNum.length() < 1)
			throw new IllegalArgumentException("Part # cannot be blank");
		if (pName == null || pName.length() < 1)
			throw new IllegalArgumentException("Part Name cannot be blank");
		if (u == null || u.length() < 1)
			throw new IllegalArgumentException(
					"Unit of Quantity cannot be empty");
		if (e == null || e.length() < 1 || e.length() > 50 )
			throw new IllegalArgumentException(
					"External Part # can not be empty or more then 50");
		id = idNum;
		partNumber = pNum;
		partName = pName;
		vendor = v;
		unit = u;
		externalNumber = e;
		observers = new ArrayList<PartObserver>();
	}

	public int getPartId() {
		return id;
	}

	public void setPartId(int id) {
		this.id = id;
		updateObservers();
	}

	public String getPartNumber() {
		return partNumber;
	}

	public void setPartNumber(String partNumber) {
		this.partNumber = partNumber;
		updateObservers();
	}

	public String getPartName() {
		return partName;
	}

	public void setPartName(String partName) {
		this.partName = partName;
		updateObservers();
	}

	public String getVendor() {
		return vendor;
	}

	public void setVendor(String vendor) {
		this.vendor = vendor;
		updateObservers();
	}

	public String getUnitQuantity() {
		return unit;
	}

	public void setUnitQuantity(String unit) {
		this.unit = unit;
		updateObservers();
	}

	public String getExternalNumber() {
		return externalNumber;
	}

	public void setExternalNumber(String externalNumber) {
		this.externalNumber = externalNumber;
		updateObservers();
	}	
	
	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
		updateObservers();
	}
		
	public String getLocation() {
		return location;
	}
	
	public void setLocation(String location) {
		this.location = location;
		updateObservers();
	}

	public void registerObserver(PartObserver o) {
		observers.add(o);
	}
	


	public void setFields(int pId, String pNum, String pName, String v, String u, String e) {
		setPartNumber(pNum);
		setPartName(pName);
		setVendor(v);
		setUnitQuantity(u);
		setExternalNumber(e);
		updateObservers();
	}
	

	public void updateDeleted() {
		for (PartObserver o : observers) {
			try {
				o.modelDeleted();
			} catch (Exception e) {
				// ignore
			}
		}
	}

	private void updateObservers() {
		for (PartObserver o : observers) {
			try {
				o.updateObserver(this);
			} catch (Exception e) {
				// ignore
			}
		}
	}
	
}
