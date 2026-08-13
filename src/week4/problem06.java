package week4;

import java.util.Scanner;

class Point {
	public double x;
	public double y;
	public Point (double x,double y) {
		this.x = x;
		this.y = y;
	};
}

final class Distance {
	public static double dist (Point a, Point b) {
		return Math.sqrt (Math.pow(a.x-b.x, 2) + Math.pow(a.y-b.y, 2));
	}
}

public class problem06 {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int N = sc.nextInt();
		Point[] ps = new Point[N];
		for (int i=0; i<N; i=i+1) {
			ps[i]= new Point (sc.nextDouble (), sc.nextDouble());
		}
		double shortest = -1;
		for (Point a : ps) {
			for (Point b: ps) {
				double d = Distance.dist(a, b);
//				System.out.printf("%f, &£ | £,£: %f\n", a.x, a.y, b.x, b.y, d);
				if (a == b) { continue; }
				if (shortest == -1) { shortest = d; }
				if (d < shortest) { shortest = d; }
			}
		}
		System.out.printf("%.2f\n", shortest);
	}
}

/*
8
3 3
-1 3
1 1
4 2
2 0.5
-1 -1
4 -0.5
2 -1
*/