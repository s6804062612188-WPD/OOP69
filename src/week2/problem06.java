package week2;

import java.util.Scanner;
import java.util.Date;

class Account {
	private int id;
	private double balance;
	private double AnnualInterestRate;
	private Date dateCreated;
	
	public Account() {
		this.id = 0;
		this.balance = 0;
		this.AnnualInterestRate = 0;
		this.dateCreated = new Date();
	}
	public Account(int id, double balance, double AIR, Date dated) {
		this.id = id;
		this.balance = balance;
		this.AnnualInterestRate = AIR;
		this.dateCreated = dated;
	}
	
	public int accessID() {
		return this.id;
	}
	public double accessBalance() {
		return this.balance;
	}
	public double accessAIR() {
		return this.AnnualInterestRate;
	}
	public Date accessDate() {
		return this.dateCreated;
	}
	
	public double getMonthlyInterestRate() {
		return this.AnnualInterestRate/12.0;
	}
	
	public double getMonthlyInterest() {
		return getMonthlyInterestRate() * this.balance;
	}
	
	public void withdraw(double w) {
		this.balance -= w;
	}
	public void deposit(double d) {
		this.balance += d;
	}
}

public class problem06 {
	public static void main(String[] args) {
		Date date = new Date();
		Account ac = new Account(1122, 20000, 4.5, date);
		ac.withdraw(2500);
		ac.deposit(3000);
		System.out.printf("Your current balance:\t%f\n", ac.accessBalance());
		System.out.printf("Your MONTHLY interest rate:\t%f\n", ac.getMonthlyInterestRate() );
	}
}
