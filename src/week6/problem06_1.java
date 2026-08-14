package week6;

import static java.lang.System.out;
import java.util.Scanner;

public class problem06_1 {
	public static void main(String[] args) {
		Scanner input = new Scanner(System.in);
		
		out.println("Enter your name and surname, age, and birth day");
		String name = input.next();
		String surname = input.next();
		int age = input.nextInt();
		Date bDate = new Date(input.nextInt(), input.next(), input.nextInt());
		Person p1123 = new Person(name, surname, age, bDate);
		SavingAccount a1123 = new SavingAccount(p1123, 1123, 20000, 4.5, new Date());
		
		Person temp = new Person("John", "Doe", -1, new Date() );
		Account a1100 = new Account(temp , 1100, 0.0, 0.0, new Date());
		
		a1123.withdraw(2500);
		a1123.deposit(3000);
		
		a1123.transferMoney(a1100, 1000);
		
		out.print(a1123.toString());
		out.print(a1100.toString());
		
	}
}

/*
abc def 99999 5 February 2025
*/