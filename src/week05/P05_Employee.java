package week05;

import java.util.ArrayList;

class Employee {
	private final String firstname;
	private final String lastname;
	private final String id;
	private final double salary;
	private int year;

	public Employee(String firstname, String lastname,
					String id, double sal, int year) {
		this.firstname = firstname;
		this.lastname = lastname;
		this.id = id;
		this.salary = sal;
		this.year = year;
	}

	public double earning() {
		return salary * 0.95;
	}

	public double bonus(int year) {
		if (year > 5) return salary * 12;
		return salary * 6;
	}

	public void printEmp() {
		System.out.printf("%s %s %.2f %.2f%n",
				firstname, lastname, earning(), bonus(year));
	}
}

public class P05_Employee {
	public static void printEmp(ArrayList a) {
		System.out.println("First name Last name Earning Bonus");

		for (Object obj : a) {
			Employee emp = (Employee) obj;
			emp.printEmp();
		}
	}

	public static void main(String[] args) {
		ArrayList<Employee> arrayEarn = new ArrayList<>();

		arrayEarn.add(new Employee("John", "Smith", "E001", 30000, 6));
		arrayEarn.add(new Employee("Jane", "Doe", "E002", 25000, 3));

		printEmp(arrayEarn);
	}
}