package week09;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.util.HashMap;
import java.util.Map;

public class P05_Calculator {
	public static void main(String[] args) {
		Calculator frame = new Calculator();
		frame.setTitle("Simple Calculator");
		frame.setSize(350, 300);
		frame.setLocationRelativeTo(null);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
	}
}

class Calculator extends JFrame {
	private final JTextField display = new JTextField("0");

	private final String[] labels = {
		"sqrt", "^2", "+-", "C",
		"7", "8", "9", "+",
		"4", "5", "6", "-",
		"1", "2", "3", "*",
		"0", ".", "=", "/"
	};

	private Map<String, JButton> buttons = new HashMap<>();

	private double firstNumber = 0;
	private String operator = "";
	private boolean startNewNumber = true;

	public Calculator() {
		// Display
		display.setEditable(false);
		display.setHorizontalAlignment(JTextField.RIGHT);
		display.setFont(new Font("Arial", Font.PLAIN, 24));
		add(display, BorderLayout.NORTH);

		// Button panel
		JPanel panel = new JPanel(new GridLayout(5, 4, 5, 5));

		for (String label : labels) {
			JButton button = new JButton(label);

			buttons.put(label, button);
			panel.add(button);
			button.addActionListener(new ButtonListener());
		}
		buttons.get("C").setForeground(Color.RED);

		add(panel, BorderLayout.CENTER);
	}

	private class ButtonListener implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			String command = e.getActionCommand();

			switch (command) {
				case "C":
					display.setText("0");
					firstNumber = 0;
					operator = "";
					startNewNumber = true;
					break;

				case "sqrt":
					double value = Double.parseDouble(display.getText());

					if (value < 0) {
						display.setText("Error");
					} else {
						display.setText(format(Math.sqrt(value)));
					}

					startNewNumber = true;
					break;

				case "^2":
					value = Double.parseDouble(display.getText());
					display.setText(format(value * value));
					startNewNumber = true;
					break;

				case "+-":
					value = Double.parseDouble(display.getText());
					display.setText(format(-value));
					break;

				case "+":
				case "-":
				case "*":
				case "/":
					firstNumber = Double.parseDouble(display.getText());
					operator = command;
					startNewNumber = true;
					break;

				case "=":
					if (!operator.isEmpty()) {
						double secondNumber =
							Double.parseDouble(display.getText());

						double result = 0;

						switch (operator) {
							case "+":
								result = firstNumber + secondNumber;
								break;
							case "-":
								result = firstNumber - secondNumber;
								break;
							case "*":
								result = firstNumber * secondNumber;
								break;
							case "/":
								if (secondNumber == 0) {
									display.setText("Error");
									operator = "";
									startNewNumber = true;
									return;
								}
								result = firstNumber / secondNumber;
								break;
						}

						display.setText(format(result));
						operator = "";
						startNewNumber = true;
					}
					break;

				case ".":
					if (startNewNumber || display.getText().equals("Error")) {
						display.setText("0.");
						startNewNumber = false;
					} else if (!display.getText().contains(".")) {
						display.setText(display.getText() + ".");
					}
					break;

				default: // Digits 0–9
					if (startNewNumber || display.getText().equals("Error")) {
						display.setText(command);
						startNewNumber = false;
					} else {
						display.setText(display.getText() + command);
					}
					break;
			}
		}
	}

	private String format(double number) {
		if (number == (long) number) {
			return String.valueOf((long) number);
		}
		return String.valueOf(number);
	}
}
