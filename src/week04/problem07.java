package week04;

import java.util.Scanner;
import java.util.Arrays;

class MinTwoSet {
	private int[] numbers;
	private int n;

	public MinTwoSet(int n, int[] numbers) {
		this.n = n;
		this.numbers = numbers;
	}

	public long solve() {
		Arrays.sort(numbers);

		long sumA = 0, sumB = 0;

		for (int i = n - 1; i >= 0; i--) {
			if (sumA <= sumB) sumA += numbers[i];
			else sumB += numbers[i];
		}

		return Math.abs(sumA - sumB);
	}
}

public class problem07 {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);

		int n = sc.nextInt();
		int[] numbers = new int[n];

		for (int i = 0; i < n; i++) {
			numbers[i] = sc.nextInt();
		}

		MinTwoSet problem = new MinTwoSet(n, numbers);

		System.out.println(problem.solve());
	}
}
