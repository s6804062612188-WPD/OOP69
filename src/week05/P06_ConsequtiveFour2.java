package week05;

import java.util.Scanner;
import java.util.ArrayList;

public class P06_ConsequtiveFour2 {
	public static boolean isConsecutiveFour(
		ArrayList<ArrayList<Integer>> values
	) {
		int rows = values.size();
		int cols = values.get(0).size();

		int[][] directions = {
			{0, 1}, {1, 0}, {1, 1}, {1, -1}
		};

		for (int i = 0; i < rows; i++) {
			for (int j = 0; j < cols; j++) {
				for (int[] d : directions) {
					int endRow = i + 3 * d[0];
					int endCol = j + 3 * d[1];

					if (endRow < 0 || endRow >= rows ||
						endCol < 0 || endCol >= cols) {
						continue;
					}

					int value = values.get(i).get(j);
					boolean match = true;

					for (int k = 1; k < 4; k++) {
						if (values.get(i + k * d[0]).get(j + k * d[1]) != value) {
							match = false;
							break;
						}
					}

					if (match) return true;
				}
			}
		}

		return false;
	}

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);

		int rows = sc.nextInt();
		int cols = sc.nextInt();

		ArrayList<ArrayList<Integer>> values = new ArrayList<>();

		for (int i = 0; i < rows; i++) {
			ArrayList<Integer> row = new ArrayList<>();

			for (int j = 0; j < cols; j++) {
				row.add(sc.nextInt());
			}

			values.add(row);
		}

		if (isConsecutiveFour(values)) System.out.println("1");
		else System.out.println("0");
	}
}
