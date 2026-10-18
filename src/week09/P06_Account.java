package week09;

import java.time.LocalDate;

class Account {
	private int id;
	private double balance;
	private double annualInterestRate;
	private LocalDate dateCreated;
	private String firstName;
	private String lastName;
	private LocalDate birthDate;

	public Account(
			int id,
			double balance,
			double annualInterestRate,
			LocalDate dateCreated,
			String firstName,
			String lastName,
			LocalDate birthDate) {

		this.id = id;
		this.balance = balance;
		this.annualInterestRate = annualInterestRate;
		this.dateCreated = dateCreated;
		this.firstName = firstName;
		this.lastName = lastName;
		this.birthDate = birthDate;
	}

	public int getId() { return id; }
	public double getBalance() { return balance; }
	public double getAnnualInterestRate() { return annualInterestRate; }
	public LocalDate getDateCreated() { return dateCreated; }
	public String getFirstName() { return firstName; }
	public String getLastName() { return lastName; }
	public LocalDate getBirthDate() { return birthDate; }
	
	public int getAge() {
		return java.time.Period.between(
			birthDate, LocalDate.now()
		).getYears();
	}

	public double getMonthlyInterestRate() { return annualInterestRate / 12.0; }
	public double getMonthlyInterest() { return balance * getMonthlyInterestRate() / 100.0; }
	public void withdraw(double amount) { balance -= amount; }
	public void deposit(double amount) { balance += amount; }
}
