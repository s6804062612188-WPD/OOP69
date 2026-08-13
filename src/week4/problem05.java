package week4;

import java.util.Scanner;

class Matrix {
	public int[][] m;
	public int column;
	public int row;
	public Matrix (int a, int b) {
		this.m = new int[a] [b];
		this.row = a;
		this.column = b;
	};
	public void inputMatrix(Scanner sc) {
		for (int i=0; i<row; i=i+1) {
			for (int j=0; j<column; j=j+1) {
				this.m[i][j] = sc.nextInt();
			}
		}
	}

	public void print() {
		for (int i=0; i<row; i=i+1) {
			for (int j=0; j<column; j=j+1) {
				System.out.print(m[i][j] + " ");
			}
			System.out.println();
		}
	}
}

final class MatrixMultiplication {
	public static Matrix mult (Matrix a, Matrix b) {
		if (a.column != b.row) {
			System.out.println("Cannot perform multiplication, mismatch dimenions");
			return null;
		}
		
		Matrix c = new Matrix (a.row, b.column);
		for (int i=0; i<a.row; i=i+1) {
			for (int j=0; j<b.column; j=j+1) {
				c.m[i][j] = 0;
				for (int k=0; k<a.column; k=k+1) {
//					System.out.printf("%d,%d: %d: %d %d\n",
//							i, j, k, a.m[i][k], b.m[i][j]);
					c.m[i][j] += (a.m[i] [k] *b.m[k] [j]);
				}
			}
		}
		return c;
	}
}

public class problem05 {
	public static void main(String[] args) {
		Scanner input = new Scanner(System.in);
		int m; int n;
		
		m = input.nextInt(); n = input.nextInt();
		Matrix A = new Matrix (m, n);
		A.inputMatrix(input);
		
		m = input.nextInt(); n = input.nextInt();
		Matrix B = new Matrix (m, n);
		B.inputMatrix(input);
		
//		A.print();
//		B.print();
		Matrix C = MatrixMultiplication.mult(A, B);
		if (C != null) { C.print(); }
		else { System.out.println("C is empty"); }
		
	}
}

/*
3 2
1 2
-1 0
3 2
2 3
1 5 2
-2 0 1
*/