package week4;

import java.util.Scanner;

class field {
	int h[][];
	int row;
	int col;
	
	field(int r, int c) {
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
		class spawnPoint {
			public int a; public int b;
			public int sum;
			
			spawnPoint(int a, int b) {
				this.a = a;
				this.b = b;
				this.sum = 0;
			}
			public boolean isValid(int a, int b) {
				return (0 <= a && a < row && 0 <= b && b < col);
			}
			private boolean check(int sum, boolean ver) {
				int ta=0, tb=1;
				if (ver) { ta = 1;	tb = 0; }
				if (!isValid(a,b) || !isValid(a+ta,b+tb)) return false;

				int curSum = h[a][b] + h[a+ta][b+tb];
				if (sum >= curSum) return false;
				if ( Math.abs(h[a][b] - h[a+ta][b+tb]) > 10) return false;
				this.sum = curSum;
//				System.out.printf("%d,%d to %d,%d = %d\n",a, b, a+ta, b+tb, curSum);
				return true;
			}
		}
		
		int spawnI=-1, spawnJ=-1, sum=Integer.MIN_VALUE;
		
		for (int i=0; i<row; i=i+1) {
			for (int j=0; j<col; j=j+1) { 
				spawnPoint ch = new spawnPoint(i,j);
				if (ch.check(sum, true)) {
					sum = ch.sum;
					spawnI = i;	spawnJ = j;
				}
				if (ch.check(sum, false)) {
					sum = ch.sum;
					spawnI = i;	spawnJ = j;
				}
			}
		}
		
		if (spawnI == -1 && spawnJ == -1) {
			System.out.println("No pikachu has spawned.");
			return;
		} else {
			System.out.printf("%d %d\n", ++spawnI, ++spawnJ);
		}
		
	}
}

public class problem08 {
	public static void main(String[] args) {
		Scanner input = new Scanner(System.in);
		
		field h = new field(input.nextInt(), input.nextInt());
		h.input(input);
//		h.print();
		h.findPikachu();
	}
} 