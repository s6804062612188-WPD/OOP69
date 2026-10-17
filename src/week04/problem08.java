package week04;

import java.util.Scanner;

class Field {
	int h[][];
	int row;
	int col;

	Field(int r, int c) {
		this.row = r;
		this.col = c;
		this.h = new int[r][c];
	}
	public void input(Scanner input) {
		for (int i=0; i<row; i=i+1) {
			for (int j=0; j<col; j=j+1) {
				this.h[i][j] = input.nextInt();
			}
		}
	}
	public void print() {
		for (int i=0; i<row; i=i+1) {
			for (int j=0; j<col; j=j+1) {
				System.out.print(this.h[i][j]);
				if (j != col-1) System.out.print(" ");
			}
			System.out.println();
		}
	}

	public void findPikachu() {
		int bestSum = Integer.MIN_VALUE;
		int bestI = -1, bestJ = -1;

		for (int i = 0; i < row; i++) {
			for (int j = 0; j < col; j++) {

				// 1. Scan right
				if (j + 1 < col) {
					int a = h[i][j];
					int b = h[i][j + 1];

					if (Math.abs(a - b) <= 10) {
						int sum = a + b;

						if (sum > bestSum) {
							bestSum = sum;
							bestI = i; bestJ = j;
						}
					}
				}

				// 2. Scan down
				if (i + 1 < row) {
					int a = h[i][j];
					int b = h[i + 1][j];

					if (Math.abs(a - b) <= 10) {
						int sum = a + b;

						if (sum > bestSum) {
							bestSum = sum;
							bestI = i; bestJ = j;
						}
					}
				}
			}
		}

		if (bestI == -1) {
			System.out.println("No pikachu has spawned.");
		} else {
			System.out.printf("%d %d%n", bestI + 1, bestJ + 1);
		}
	}
}

public class problem08 {
	public static void main(String[] args) {
		Scanner input = new Scanner(System.in);

		Field h = new Field(input.nextInt(), input.nextInt());
		h.input(input);
//		h.print();
		h.findPikachu();
	}
}

/*
4 5
5 1 2 10 4
4 30 3 0 100
3 25 10 4 10
3 20 4 8 5

4 4
0 0 0 0
0 0 0 0
0 1 1 1
1 1 0 0
*/