package week04;

import java.util.Scanner;

class OilGrid {
	final int m, n;
	final char[][] grid;
	final boolean[][] visited;
	
	final int[] dr = {-1, -1, -1, 0, 0, 1, 1, 1};
	final int[] dc = {-1, 0, 1, -1, 1, -1, 0, 1};

	OilGrid(int m, int n, Scanner sc) {
		this.m = m;
		this.n = n;
		grid = new char[m][n];
		visited = new boolean[m][n];

		int i = 0, j = 0;

		while (i < m) {
			String line = sc.next();

			for (int k = 0; k < line.length(); k++) {
				grid[i][j] = line.charAt(k);

				j++;
				if (j == n) {
					j = 0;
					i++;
					if (i == m) break;
				}
			}
		}
	}

	private void spread(int r, int c) {
		visited[r][c] = true;

		for (int d = 0; d < 8; d++) {
			int nr = r + dr[d];
			int nc = c + dc[d];

			if (0 <= nr && nr < m &&
				0 <= nc && nc < n &&
				grid[nr][nc] == '@' &&
				!visited[nr][nc]) {
				spread(nr, nc);
			}
		}
	}

	int countDeposits() {
		int count = 0;

		for (int i = 0; i < m; i++) {
			for (int j = 0; j < n; j++) {
				if (grid[i][j] == '@' && !visited[i][j]) {
					count++;
					spread(i, j);
				}
			}
		}

		return count;
	}
}


public class problem11 {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		while (true) {
			System.out.println("oil reserve, enter 0 to exit.");
			int m = sc.nextInt();
			if (m == 0) return;
			int n = sc.nextInt();

			OilGrid oil = new OilGrid(m, n, sc);
			System.out.println(oil.countDeposits());
		}
	}
}
