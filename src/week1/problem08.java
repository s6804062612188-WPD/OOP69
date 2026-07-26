package week1;

import java.util.Scanner;

public class problem08 {
	public static int a0;
	public static int a1;
	
	public static long recur(int k) {
		if (k == 0) return (long)a0;
		else if (k == 1) return (long)a1;
		else return (long)(k*k*recur(k-1) - recur(k-2) + Math.pow(3,k));
	}
	
	public static void main(String[] args) {
		Scanner input = new Scanner(System.in);
		
		int n;
		System.out.print("Please input n, a0, and a1:\t");
		n = input.nextInt();
		a0 = input.nextInt();
		a1 = input.nextInt();
		
		System.out.print("Output is:\t");
		for (int i=0; i<=n; i=i+1) {
			System.out.print( recur(i) );
			if (i != n) System.out.print(" ");
		}
		System.out.println();
	}
}
