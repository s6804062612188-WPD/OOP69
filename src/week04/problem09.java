package week04;

import java.util.Scanner;

public class problem09 {
	public static boolean isConsecutiveFour(int[][] values) {
		int rows = values.length;
		int cols = values[0].length;

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

					int value = values[i][j];
					boolean match = true;

					for (int k = 1; k < 4; k++) {
						if (values[i + k*d[0]][j + k*d[1]] != value) {
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

		int[][] values = new int[rows][cols];

		for (int i = 0; i < rows; i++) {
			for (int j = 0; j < cols; j++) {
				values[i][j] = sc.nextInt();
			}
		}
		
		if (isConsecutiveFour(values)) System.out.println("1");
		else System.out.println("0");
	}
}

/*
6 7
0 1 0 3 1 6 1
0 1 6 8 6 0 1
5 6 2 1 8 2 9
6 5 6 1 1 9 1
1 3 6 1 4 0 7
3 3 3 3 4 0 7
*/