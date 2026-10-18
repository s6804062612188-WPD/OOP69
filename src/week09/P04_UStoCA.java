package week09;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class P04_UStoCA {
	public static void main(String[] args) {
		CurrencyConverter frame = new CurrencyConverter();
		frame.setSize(400, 200);
		frame.setTitle("Convert US Dollars to Canadian Dollars");
		frame.setLocationRelativeTo(null);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
	}
}

class CurrencyConverter extends JFrame {
	// Exchange rate
	private final double exchangeRate = 1.5;

	// Labels
	private JLabel jlblUSD = new JLabel("US Dollars:");
	private JLabel jlblCAD = new JLabel("Canadian Dollars:");

	// Text fields
	private JTextField jtfUSD = new JTextField(15);
	private JTextField jtfCAD = new JTextField(15);

	// Convert button
	private JButton jbtConvert = new JButton("Convert");

	public CurrencyConverter() {
		// Panel to hold labels
		JPanel p1 = new JPanel(new GridLayout(2, 1));
		p1.add(jlblUSD);
		p1.add(jlblCAD);
		jtfCAD.setEditable(false);

		// Panel to hold text fields
		JPanel p2 = new JPanel(new GridLayout(2, 1));
		p2.add(jtfUSD);
		p2.add(jtfCAD);

		// Panel to hold both panels
		JPanel p3 = new JPanel(new GridLayout(1, 2));
		p3.add(p1);
		p3.add(p2);

		// Add the input/output panel to the center
		add(p3, BorderLayout.CENTER);

		// Button panel at the bottom
		JPanel p4 = new JPanel(new FlowLayout(FlowLayout.RIGHT));
		p4.add(jbtConvert);
		add(p4, BorderLayout.SOUTH);

		// Register the button listener
		jbtConvert.addActionListener(new ButtonListener());
	}

	// Handle Convert button clicks
	private class ButtonListener implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			double usd = Double.parseDouble(jtfUSD.getText());

			double cad = usd * exchangeRate;

			jtfCAD.setText(String.format("%.2f", cad));
		}
	}
}
