package week07;

import java.util.ArrayList;
import java.lang.Object;

abstract class Employee{
	private String firstname;
	private String lastname;
	private String id;
	public Employee(String firstname, String lastname, String id) {
		this.firstname=firstname;
		this.lastname=lastname;
		this.id=id;
	}
	public abstract double earning();
	public abstract double bonus(int year);

	public String getFirstname() { return firstname; }
	public String getLastname() { return lastname; }
	public String getId() { return id; }
}

class SalariedEmployee extends Employee {
	public double salary;
	public SalariedEmployee(String firstname, String lastname, String id, double sal) {
		super(firstname, lastname, id);
		this.salary = sal;
	}

	@Override
	public double earning() {
		return salary*0.95;
	}

	@Override
	public double bonus(int year) {
		return salary* ((year > 5)? 12:6) ;
	}
}
class ComEmployee extends Employee {
	public double grossSale;
	public double ComRate;
	public ComEmployee(String firstname,String lastname,String id, double sales, double percent) {
		super(firstname, lastname, id);
		this.grossSale = sales;
		this.ComRate = percent;
	}

	@Override
	public double earning() {
		double Commision = grossSale*ComRate;
		return Commision;
	}

	@Override
	public double bonus(int year) {
		return grossSale* ((year > 5)? 6:3) ;
	}
}

public class problem04 {
	private static void form(double arg) {
		form(String.format("%.2f", arg));
	}
	private static void form(String s) {
		System.out.printf("%12s", s);
	}
	private static void printEmp(ArrayList<Employee> a) {
		form("First Name");
		form("Last Name");
		form("Earning");
		form("Bonus\n");
		for (Employee worker : a) {
			form(worker.getFirstname());
			form(worker.getLastname());
			form(worker.earning());
			form(worker.bonus(6));
			System.out.println();
		}
	}
	
	public static void main(String[] args) {
		ArrayList<Employee> workers = new ArrayList<>();
		
		SalariedEmployee a1 = new SalariedEmployee("AAA", "AAAA", "1", 20000);
		SalariedEmployee a2 = new SalariedEmployee("BBB", "BBBB", "2", 19999);
		ComEmployee b1 = new ComEmployee("CCC", "CCCC", "3", 5000, 0.4);
		ComEmployee b2 = new ComEmployee("DDD", "DDDD", "4", 7500, 0.6);
		workers.add(a1);
		workers.add(a2);
		workers.add(b1);
		workers.add(b2);
		
		printEmp(workers);
	}
}
	