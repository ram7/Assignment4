package Assignment4;

import javax.swing.*;
import javax.swing.JFrame;

public class ProjectMain {

	public static void main(String[] args) throws ClassNotFoundException {

		ItemInventory inv = new ItemInventory();
		PartInventory inv2 = new PartInventory();

		Gateway gateway = new Gateway(inv);
		Gateway gateway2 = new Gateway(inv2);
		gateway.getData();


		ItemInventoryController invC = new ItemInventoryController(inv);
		PartInventoryController invC2 = new PartInventoryController(inv2);

		// views
		ItemsListView pView = new ItemsListView(invC, inv);
		pView.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		pView.setTitle("Item Inventory View");
		pView.setSize(400, 300);
		pView.setVisible(true);
		inv.registerObserver(pView);

		PartsListView pView2 = new PartsListView(invC2, inv2);
		pView2.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		pView2.setTitle("Part Inventory View");
		pView2.setSize(400, 300);
		pView2.setLocation(450, 0);
		pView2.setVisible(true);
		inv2.registerObserver(pView2);

	}
}