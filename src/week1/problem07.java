package week1;

import java.util.Scanner;
import java.text.DecimalFormat;

public class problem07 {
	public static void main(String[] args) {
		Scanner input = new Scanner(System.in);
		
		double x; int n;
		while (true) {
			System.out.printf("Please input x, n:\t");
			x = input.nextDouble();
			n = input.nextInt();
			if (n<0) System.out.printf("Error, n cannot be negative\n");
			else break;
		}
		
		DecimalFormat df = new DecimalFormat("0.############");
		double sum = 0;
		for (int i=0; i<=n; i=i+1) {
			sum += Math.pow(x,i);
		}
		System.out.println("Output is:\t" + df.format(sum) );
	}
}
