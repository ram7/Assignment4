package Assignment4;

public class ItemInventoryController {
	private ItemInventory inv;

	public ItemInventoryController(ItemInventory i) {
		inv = i;
	}

	public ItemPart getPartByIndex(int index) {
		if (index < inv.getNumParts())
			return (ItemPart) inv.getParts().get(index);
		else
			return null;
	}

	public void deletePart(ItemPart p) {
		// delete p from inv
		inv.deletePart(p);
	}

	public int getNumParts() {
		return inv.getNumParts();
	}

	public ItemPart addPart(ItemView view, ItemPart p, int id, String pName,
			String l, int q, String v) {
		// if p is null then create a new Part
		// but first validate pName does not already exist
		if (p == null) {
			try {
				return inv.addPart(p, id, pName, l, q, v);
			} catch (IllegalArgumentException ex) {
				view.showError(ex.getMessage());
			}
		} else {
			try {
				if (inv.partNameExists(pName, p)) {
					view.showError("Part Name already exists!");
					return null;
				}
				p.setFields(id, pName, l, q);
				inv.updateObservers();
				return p;
			} catch (IllegalArgumentException ex) {
				view.showError(ex.getMessage());
			}
		}
		return null;
	}
}
