package week2;

import java.util.Scanner;

final class MaxPrimeNumber {
	protected static int maxprime = -1;
	
	private MaxPrimeNumber() {}
	
	private static boolean isPrime(int n) {
		if (n<2) return false;
		if (n==2 || n==3) return true;
		
		int sq = (int) Math.sqrt(n);
		for (int i=2; i<=sq; i=i+1) {
			if (n%i == 0) return false;
		}
		
		return true;
	}
	
	public static void contest(int n) {
		if (MaxPrimeNumber.isPrime(n)) {
			if (n > MaxPrimeNumber.maxprime) maxprime = n;
		}
	}
}

public class problem09 {
	public static void main(String[] args) {
		Scanner input = new Scanner(System.in);
		
		boolean flagZero = false;
		do {
			String line = input.nextLine();
			Scanner lineScanner = new Scanner(line);
			while (lineScanner.hasNextInt()) {
				int x = lineScanner.nextInt();
				if (x == 0) flagZero = true;
				else MaxPrimeNumber.contest(x);
			}
			lineScanner.close();
		} while(!flagZero);
		
		System.out.println(MaxPrimeNumber.maxprime);
	} 
}
