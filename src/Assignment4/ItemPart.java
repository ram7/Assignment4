package Assignment4;



import java.util.ArrayList;

public class ItemPart {
	private int id;
	private String partName;
	private String location;
	private int quantity;
	private String vendor;

	private ArrayList<ItemObserver> observers;

	public ItemPart(int id, String pName, String l, int q) {
		this(id, pName, l, q, "");
	}

	public ItemPart(int idNum, String pName, String l, int q, String v) {
		if (pName == null || pName.length() < 1)
			throw new IllegalArgumentException("Part Name cannot be blank");
		if (q < 1)
			throw new IllegalArgumentException("Quantity cannot be < 1");
		if (l == null || l.length() < 1)
			throw new IllegalArgumentException("Location cannot be unknown");
		id = idNum;
		partName = pName;
		vendor = v;
		quantity = q;
		location = l;
		observers = new ArrayList<ItemObserver>();
	}

	public int getPartId() {
		return id;
	}

	public void setPartId(int id) {
		this.id = id;
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
	public void registerObserver(ItemObserver o) {
		observers.add(o);
	}

	public void setFields(int pId, String pName, String l, int q) {
		setPartName(pName);
//		setVendor(v);
		setQuantity(q);
		setLocation(l);
		updateObservers();
	}

	public void updateDeleted() {
		for (ItemObserver o : observers) {
			try {
				o.modelDeleted2();
			} catch (Exception e) {
				// ignore
			}
		}
	}

	private void updateObservers() {
		for (ItemObserver o : observers) {
			try {
				o.updateObserver2(this);
			} catch (Exception e) {
				// ignore
			}
		}
	}
}

