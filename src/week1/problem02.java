package week1;

import java.util.Scanner;

public class problem02 {
	public static void main(String[] arg) {
		Scanner input = new Scanner(System.in);
		System.out.printf("Enter the monthly saving amount: ");
		double monthlyDeposit = input.nextDouble();
		double rate = 0.05;
		double interest=0;
		for (int i=0; i<6; i=i+1) {
			interest += monthlyDeposit;
			interest += interest*(rate/12.0);
		}
		
		System.out.printf("After the sixth month, the account value is $%.2f\n", interest);
	}
}   
