package week08;

import java.util.ArrayList;

public class P04_Final {
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