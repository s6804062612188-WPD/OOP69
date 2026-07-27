package week3;

import java.util.Scanner;

final class Recursion {
	public static double m(int i) {
		if (i == 0) return 0;
		
		double ret = 0.0;
		for (int j = 1; j<=i; j=j+1) {
			double temp = 1.0/(j*2.0-1.0);
			if (j%2 == 1) ret += temp;
			else ret -= temp;
		}
		return 4.0*ret;
	}
}

public class problem08 {
	public static void main(String[] args) {
		Scanner input = new Scanner(System.in);
		
		int term;
		System.out.println("enter the term amount to compute pi:");
		term = input.nextInt();
		System.out.printf("the pi estimation until %d-th term:\n%.12f\n", term, Recursion.m(term));
		System.out.printf("the actual pi:\n%.12f\n", Math.PI );
	
	}
}
