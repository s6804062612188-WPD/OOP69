package week1;

import java.util.Scanner;
import java.text.DecimalFormat;

public class problem09 {
	public static void main(String[] args) {
		Scanner input = new Scanner(System.in);
		double max=-123456789; int maxOccur = -1;
		double temp;
		while (true) {
			temp = input.nextDouble();
			if (temp == 0) break;
			if (maxOccur < 0) {
				max = temp;
				maxOccur = 1;
			} else if (temp > max) {
				max = temp;
				maxOccur = 1;
			} else if (temp == max) {
				maxOccur += 1;
			}
		}
		
		if (maxOccur < 0) System.out.println("no numbers were input, thus no results");
		else {
			DecimalFormat df = new DecimalFormat("0.############");
			System.out.println(df.format(max) + " " + maxOccur);
		}
	}
}
