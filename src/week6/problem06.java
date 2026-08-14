//DIRECTLY COPIED FROM week2.problem06;
package week6;

import static java.lang.System.out;
import java.time.LocalDate;


class Date {
	private int day;
	private String month;	
	private int year;

	public Date() {
		this(-1,"",-1);
	}
	public Date(int day, String month, int year) {
		if (year > 0) { this.year = year; }
		else { this.year = LocalDate.now().getYear(); }

		if (month == null) {
			month = "";
		}
		month = month.toLowerCase().trim();

		int maxDays = switch(month) {
			case "january", "march", "may", "july", "august", "october", "december" -> 31;
			case "april", "june", "september", "november" -> 30;
			case "february" -> isLeapYear(this.year) ? 29 : 28;
			default -> -1;
		};

		if (maxDays != -1 && day >= 1 && day <= maxDays) {
			this.day = day;
			this.month = month.toUpperCase();
		} else {
			System.out.println("Invalid datetime / Uninitialized datetime");
			LocalDate now = LocalDate.now();
			this.day = now.getDayOfMonth();
			this.month = now.getMonth().name();
			this.year = now.getYear();
		}
	}

	private boolean isLeapYear(int year) {
		return (year % 4 == 0 && year % 100 != 0) || (year % 400 == 0);
	}
	
	public int getDay() { return day; }
	public void setDay(int day) { this.day = day; }

	public String getMonth() { return month; }
	public void setMonth(String month) { this.month = month; }
	public int getNumericMonth() {
		switch(month) {
			case "JANUARY": return 1;
			case "FEBRUARY": return 2;
			case "MARCH": return 3;
			case "APRIL": return 4;
			case "MAY": return 5;
			case "JUNE": return 6;
			case "JULY": return 7;
			case "AUGUST": return 8;
			case "SEPTEMBER": return 9;
			case "OCTOBER": return 10;
			case "NOVEMBER": return 11;
			case "DECEMBER": return 12;
		}
		return 0;
	}

	public int getYear() { return year; }
	public void setYear(int year) { this.year = year; }
	
	public String printDate() {
		return day + " " + month + " " + year;
	}
}

class Person {
	private String name;
	private String surname;
	private int age;
	private Date bDate;
	
	public Person() {
		this("[UNNAMED FIRST NAME]", "[UNNAMED LAST NAME]", -1, new Date());
	}
	public Person(String name, String surname, Date bDate) {
		this(name, surname, -1, bDate);
	}
	public Person(String name, String surname, int age, Date bDate) {
		this.name = name;
		this.surname = surname;
		this.bDate = bDate;
		this.age = 2026-bDate.getYear();
	}
	public String getName() { return name; }
	public void setName(String name) { this.name = name; }
	
	public String getSurname() { return surname; }
	public void setSurname(String surname) { this.surname = surname; }
	
	public int getAge() { return age; }
	public void setAge(int age) { this.age = age; }
	
	public Date getbDate() { return bDate; }
	public void setbDate(Date bDate) { this.bDate = bDate; }
	
}

class Account {
	private Person owner;
	private int id;
	private double balance;
	private double AnnualInterestRate;
	private Date dateCreated;
	
	public Account() {
        this(new Person(), 0,0,0, new Date());
	}
	public Account(Person owner, int id, double balance, double AIR, Date dated) {
		this.owner = owner;
		this.id = id;
		this.balance = balance;
		this.AnnualInterestRate = AIR;
		this.dateCreated = dated;
	}
    
	public int getID() {return this.id;}
	public double getBalance() {return this.balance;}
	public double getAIR() {return this.AnnualInterestRate;}
	public Date getDate() {return this.dateCreated;}
	
	public double getMonthlyInterestRate() {return this.AnnualInterestRate/12.0;}
	public double getMonthlyInterest() {return balance*getMonthlyInterestRate();}
	
	public double getInterest() {
		Date now = new Date();
		
		int interval = ( now.getYear() - dateCreated.getYear() )*12;
		interval += dateCreated.getNumericMonth() - now.getNumericMonth();
		
		return interval*getMonthlyInterest();
	}
	public double getCompoundInterest() {
		Date now = new Date();
		
		int interval = ( now.getYear() - dateCreated.getYear() )*12;
		interval += dateCreated.getNumericMonth() - now.getNumericMonth();
		
		double compound = balance;
		for (int i=0; i<interval; i=i+1) {
			compound += compound*getMonthlyInterestRate();
		}
		return compound;
	}
	
	public void withdraw(double w) {this.balance -= w;}
	public void deposit(double d) {this.balance += d;}
	
	public void transferMoney(Account acc1, double amount) {
		if (this.balance < amount) {
			out.println("You're broke, transfer unsuccessful.");
			return;
		}
		
		this.withdraw(amount);
		acc1.deposit(amount);
	}
	
	@Override
	public String toString() {
		String ret = "---------w----------------------\n";
		ret += String.format("%s %s\n", owner.getName(), owner.getSurname());
		ret += String.format("Birth Date: %s | Age : %s\n", owner.getbDate(), owner.getSurname() );
		ret += String.format("\n");
		ret += String.format("ID: %d", id);
		ret += String.format("Date created: %s\n", dateCreated.printDate() );
		ret += String.format("Balance: %f\n", balance);
		ret += String.format("Annual Interest Rate: %f\n", AnnualInterestRate);
		return ret;
	}
}

class SavingAccount extends Account {
	public SavingAccount() {
        this(new Person(), 0,0,0, new Date());
	}
	public SavingAccount(Person owner, int id, double balance, double AIR, Date dated) {
		super(owner, id, balance, AIR, dated);
	}
	@Override
	public void transferMoney(Account acc1, double amount) {
		if (super.getBalance() < amount) {
			out.println("You're broke, transfer unsuccessful.");
			return;
		}
		
		this.withdraw(amount+20);	
		acc1.deposit(amount);
	}
}
class FixAccount extends Account {
	public FixAccount() {
        this(new Person(), 0,0,0, new Date());
	}
	public FixAccount(Person owner, int id, double balance, double AIR, Date dated) {
		super(owner, id, balance, AIR, dated);
	}
	@Override
	public void transferMoney(Account acc1, double amount) {
		this.transferMoney();
	}
	public void transferMoney() {
		out.println("Nuh uh. You may not transfer, this is a Fixed account.");
	}
}

public class problem06 {
	public static void main(String[] args) {
	}
}
