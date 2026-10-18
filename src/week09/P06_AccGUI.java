package week09;

import java.awt.*;
import java.awt.event.*;
import java.time.LocalDate;
import java.time.YearMonth;
import javax.swing.*;

public class P06_AccGUI {
	public static void main(String[] args) {
		SwingUtilities.invokeLater(() -> {
			AccountGUI frame = new AccountGUI();
			frame.setTitle("Show Detail of Account");
			frame.setSize(500, 430);
			frame.setLocationRelativeTo(null);
			frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			frame.setVisible(true);
		});
	}
}

class AccountGUI extends JFrame {
	private JTextField txtID = new JTextField(10);
	private JTextField txtBalance = new JTextField(10);
	private JTextField txtRate = new JTextField(10);
	private JTextField txtFirstName = new JTextField(15);
	private JTextField txtLastName = new JTextField(15);
	private JTextField txtAge = new JTextField(5);

	private JComboBox<Integer> openYear = new JComboBox<>();
	private JComboBox<Integer> openMonth = new JComboBox<>();
	private JComboBox<Integer> openDay = new JComboBox<>();

	private JComboBox<Integer> birthYear = new JComboBox<>();
	private JComboBox<Integer> birthMonth = new JComboBox<>();
	private JComboBox<Integer> birthDay = new JComboBox<>();

	private JButton btnSave = new JButton("SAVE");
	private JButton btnShow = new JButton("SHOW");

	private Account account;

	public AccountGUI() {
		setLayout(new BorderLayout(5, 5));

		JLabel title = new JLabel(
			"ACCOUNT MONEY", JLabel.CENTER
		);
		add(title, BorderLayout.NORTH);

		JPanel fields = new JPanel(new GridLayout(0, 2, 5, 8));
		fields.setBorder(BorderFactory.createTitledBorder(
			"Enter Data Account Money"
		));

		fields.add(new JLabel("ID:"));
		fields.add(txtID);

		fields.add(new JLabel("MONEY (BAHT):"));
		fields.add(txtBalance);

		fields.add(new JLabel("ANNUAL INTEREST RATE (%):"));
		fields.add(txtRate);

		fields.add(new JLabel("DATE OPEN ACCOUNT:"));
		fields.add(createDatePanel(
			openYear, openMonth, openDay
		));

		fields.add(new JLabel("FIRST NAME:"));
		fields.add(txtFirstName);

		fields.add(new JLabel("LAST NAME:"));
		fields.add(txtLastName);

		fields.add(new JLabel("BIRTH DATE:"));
		fields.add(createDatePanel(
			birthYear, birthMonth, birthDay
		));

		fields.add(new JLabel("AGE (YEARS):"));
		txtAge.setEditable(false);
		fields.add(txtAge);

		add(fields, BorderLayout.CENTER);

		JPanel buttons = new JPanel(
			new FlowLayout(FlowLayout.RIGHT)
		);
		buttons.add(btnSave);
		buttons.add(btnShow);
		add(buttons, BorderLayout.SOUTH);

		setupDateSelectors(openYear, openMonth, openDay);
		setupDateSelectors(birthYear, birthMonth, birthDay);

		// Update the number of days when year or month changes.
		openYear.addActionListener(e ->
			updateDays(openYear, openMonth, openDay));
		openMonth.addActionListener(e ->
			updateDays(openYear, openMonth, openDay));

		birthYear.addActionListener(e ->
			updateDays(birthYear, birthMonth, birthDay));
		birthMonth.addActionListener(e ->
			updateDays(birthYear, birthMonth, birthDay));

		// Update age when the birth date changes.
		birthYear.addActionListener(e -> updateAge());
		birthMonth.addActionListener(e -> updateAge());
		birthDay.addActionListener(e -> updateAge());

		btnSave.addActionListener(e -> saveAccount());
		btnShow.addActionListener(e -> showAccount());

		// Default dates: today.
		LocalDate today = LocalDate.now();
		setDate(openYear, openMonth, openDay, today);
		setDate(birthYear, birthMonth, birthDay,
				today.minusYears(20));

		updateAge();
	}

	private JPanel createDatePanel(
			JComboBox<Integer> year,
			JComboBox<Integer> month,
			JComboBox<Integer> day) {

		JPanel panel = new JPanel(new FlowLayout(FlowLayout.LEFT, 3, 0));
		panel.add(year);
		panel.add(month);
		panel.add(day);
		return panel;
	}

	private void setupDateSelectors(
			JComboBox<Integer> year,
			JComboBox<Integer> month,
			JComboBox<Integer> day) {

		int currentYear = LocalDate.now().getYear();

		// Allow dates from 1900 through the current year.
		for (int y = 1900; y <= currentYear; y++) {
			year.addItem(y);
		}

		for (int m = 1; m <= 12; m++) {
			month.addItem(m);
		}

		updateDays(year, month, day);
	}

	private void updateDays(
			JComboBox<Integer> year,
			JComboBox<Integer> month,
			JComboBox<Integer> day) {

		Integer y = (Integer) year.getSelectedItem();
		Integer m = (Integer) month.getSelectedItem();

		if (y == null || m == null) {
			return;
		}

		Integer previousDay = (Integer) day.getSelectedItem();
		int maxDays = YearMonth.of(y, m).lengthOfMonth();

		day.removeAllItems();

		for (int d = 1; d <= maxDays; d++) {
			day.addItem(d);
		}

		if (previousDay != null) {
			day.setSelectedItem(Math.min(previousDay, maxDays));
		}
	}

	private LocalDate getDate(
			JComboBox<Integer> year,
			JComboBox<Integer> month,
			JComboBox<Integer> day) {

		return LocalDate.of(
			(Integer) year.getSelectedItem(),
			(Integer) month.getSelectedItem(),
			(Integer) day.getSelectedItem()
		);
	}

	private void setDate(
			JComboBox<Integer> year,
			JComboBox<Integer> month,
			JComboBox<Integer> day,
			LocalDate date) {

		year.setSelectedItem(date.getYear());
		month.setSelectedItem(date.getMonthValue());
		updateDays(year, month, day);
		day.setSelectedItem(date.getDayOfMonth());
	}

	private void updateAge() {
		try {
			LocalDate birth = getDate(
				birthYear, birthMonth, birthDay
			);

			int age = java.time.Period.between(
				birth, LocalDate.now()
			).getYears();

			txtAge.setText(age >= 0 ? String.valueOf(age) : "");
		} catch (Exception ex) {
			txtAge.setText("");
		}
	}

	private void saveAccount() {
		try {
			int id = Integer.parseInt(txtID.getText().trim());
			double balance = Double.parseDouble(
				txtBalance.getText().trim()
			);
			double rate = Double.parseDouble(
				txtRate.getText().trim()
			);

			LocalDate dateCreated = getDate(
				openYear, openMonth, openDay
			);
			LocalDate birth = getDate(
				birthYear, birthMonth, birthDay
			);

			if (birth.isAfter(LocalDate.now())) {
				JOptionPane.showMessageDialog(
					this, "Birth date cannot be in the future."
				);
				return;
			}

			if (dateCreated.isAfter(LocalDate.now())) {
				JOptionPane.showMessageDialog(
					this, "Account opening date cannot be in the future."
				);
				return;
			}

			if (balance < 0 || rate < 0) {
				JOptionPane.showMessageDialog(
					this, "Balance and interest rate cannot be negative."
				);
				return;
			}

			account = new Account(
				id, balance, rate, dateCreated,
				txtFirstName.getText().trim(),
				txtLastName.getText().trim(),
				birth
			);

			updateAge();

			JOptionPane.showMessageDialog(
				this, "Account saved successfully!"
			);

		} catch (NumberFormatException ex) {
			JOptionPane.showMessageDialog(
				this, "Please enter valid numbers for ID, money, and interest rate."
			);
		}
	}

	private void showAccount() {
		if (account == null) {
			JOptionPane.showMessageDialog(
				this, "Please save the account first."
			);
			return;
		}

		String details =
			"ID: " + account.getId() +
			"\nBalance: " + String.format("%.2f", account.getBalance())
			+ " BAHT" +
			"\nAnnual Interest Rate: "
			+ account.getAnnualInterestRate() + "%" +
			"\nAccount Opening Date: " + account.getDateCreated() +
			"\nFirst Name: " + account.getFirstName() +
			"\nLast Name: " + account.getLastName() +
			"\nBirth Date: " + account.getBirthDate() +
			"\nAge: " + account.getAge();

		JOptionPane.showMessageDialog(
			this, details, "Account Details",
			JOptionPane.INFORMATION_MESSAGE
		);
	}
}
