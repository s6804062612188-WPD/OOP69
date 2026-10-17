package week05;

import java.util.Scanner;

class Grid {
	public int[][] population;
	public int rows, cols;

	public Grid(int rows, int cols, Scanner sc) {
		this.rows = rows;
		this.cols = cols;
		population = new int[rows][cols];

		for (int i = 0; i < rows; i++) {
			for (int j = 0; j < cols; j++) {
				population[i][j] = sc.nextInt();
			}
		}
	}

	public int pAt(int r, int c) {
		return population[r][c];
	}
}

class Shop {
	private Grid grid;
	public int k;

	public Shop(Grid grid, int k) {
		this.grid = grid;
		this.k = k;
	}

	public int findBestSum() {
		int rows = grid.rows;
		int cols = grid.cols;

		// Horizontal running sums
		int[][] horizontal = new int[rows][cols - k + 1];

		for (int i = 0; i < rows; i++) {
			int sum = 0;

			for (int j = 0; j < k; j++) {
				sum += grid.pAt(i, j);
			}

			horizontal[i][0] = sum;

			for (int j = 1; j <= cols - k; j++) {
				sum -= grid.pAt(i, j - 1);
				sum += grid.pAt(i, j + k - 1);
				horizontal[i][j] = sum;
			}
		}

		// Vertical running sums
		int best = 0;

		for (int j = 0; j <= cols - k; j++) {
			int sum = 0;

			for (int i = 0; i < k; i++) {
				sum += horizontal[i][j];
			}

			best = Math.max(best, sum);

			for (int i = 1; i <= rows - k; i++) {
				sum -= horizontal[i - 1][j];
				sum += horizontal[i + k - 1][j];
				best = Math.max(best, sum);
			}
		}

		return best;
	}
}


public class P04_KIB {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		int m = sc.nextInt();
		int n = sc.nextInt();
		int k = sc.nextInt();

		Grid grid = new Grid(m, n, sc);
		Shop shop = new Shop(grid, k);

		System.out.println(shop.findBestSum());
	}
}

/*
5 10
2
5 9 2 9 1 2 8 9 1 6
9 1 3 9 8 4 2 1 5 7
2 7 9 3 8 5 2 7 6 8
1 6 2 1 7 7 1 9 4 1
8 5 2 3 9 8 5 6 3 3
*/