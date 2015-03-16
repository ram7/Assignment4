package Assignment4;



import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class PartView extends JFrame implements PartObserver {
	private JTextField tfId;
	private JTextField tfPartNum;
	private JTextField tfPartName;
	private JTextField tfVendor;
	private JTextField tfUnOfQty;
	private JTextField tfExternalNum;

	private Part part;
	private PartInventoryController invC;

	public PartView(PartInventoryController i, Part p) {
		part = p;
		invC = i;

		this.setLayout(new BorderLayout());

		JPanel panel = new JPanel();
		panel.setLayout(new GridLayout(6, 2));

		panel.add(new JLabel("Part ID"));
		tfId = new JTextField();
		panel.add(tfId);

		panel.add(new JLabel("Part #"));
		tfPartNum = new JTextField();
		panel.add(tfPartNum);

		panel.add(new JLabel("Part Name"));
		tfPartName = new JTextField();
		panel.add(tfPartName);

		panel.add(new JLabel("Vendor"));
		tfVendor = new JTextField();
		panel.add(tfVendor);

		panel.add(new JLabel("Unit of Quantity"));
		tfUnOfQty = new JTextField("Unknown");
		panel.add(tfUnOfQty);
		
		panel.add(new JLabel("External Part #"));
		tfExternalNum = new JTextField();
		panel.add(tfExternalNum);

		this.add(panel, BorderLayout.CENTER);

		panel = new JPanel();
		panel.setLayout(new GridLayout(1, 2));
		JButton button = new JButton("Save");
		button.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {

				Part p = invC.addPart(PartView.this, part, Integer.parseInt(tfId.getText().trim()), tfPartNum.getText(),
						tfPartName.getText(), tfVendor.getText(),
						tfUnOfQty.getText(), tfExternalNum.getText());
				if (p != null) {
					if (part == null) {
						part = p;
						part.registerObserver(PartView.this);
						PartView.this.setTitle("Editing " + part.getPartName());
					} else
						part = p;
				}
			}
		});
		panel.add(button);

		this.add(panel, BorderLayout.SOUTH);

		// set for edit mode
		if (p != null) {
			tfId.setText(Integer.toString(part.getPartId()));
			tfPartNum.setText(part.getPartNumber());
			tfPartName.setText(part.getPartName());
			tfVendor.setText(part.getVendor());
			tfUnOfQty.setText(part.getUnitQuantity());
			tfExternalNum.setText(part.getExternalNumber());
			this.setTitle("Editing " + p.getPartName());
		} else
			this.setTitle("Adding new part");
	}

	public void showError(String msg) {
		JOptionPane.showMessageDialog(this, msg, "Error!",
				JOptionPane.ERROR_MESSAGE);
	}

	@Override
	public void updateObserver(Part part) {
		if (part != null) {
			tfId.setText(Integer.toString(part.getPartId()));
			tfPartNum.setText(part.getPartNumber());
			tfPartName.setText(part.getPartName());
			tfVendor.setText(part.getVendor());
			tfUnOfQty.setText(part.getUnitQuantity());
			tfExternalNum.setText(part.getExternalNumber());
			this.setTitle("Editing " + part.getPartName());
		}
	}

	@Override
	public void modelDeleted() {
		this.setVisible(false);

	}

}