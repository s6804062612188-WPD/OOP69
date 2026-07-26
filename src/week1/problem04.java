package week1;

import java.util.Scanner;

public class problem04 {
	public static void main(String[] arg) {
		Scanner input = new Scanner(System.in);
		System.out.printf("Enter balance and interest rate (e.g. 3 for 3%%):\t");
		double remain = input.nextDouble();
		double annualRate = input.nextDouble();

		System.out.printf("The interest is:\t$%.5f\n", remain*(annualRate/100.0)/12.0);
	}
}   
