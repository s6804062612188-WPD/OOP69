package week3;

import java.util.Scanner;
import java.util.ArrayList;

class Point {
	protected double x;
	protected double y;
	
	Point(double x, double y) {
		this.x = x;
		this.y = y;
	}
	
	public static double orientation(Point p0, Point p1, Point p2) {
		return ( (p1.x - p0.x)*(p2.y-p0.y) - (p1.y-p0.y)*(p2.x-p0.x));  // cross-product
	}
}

public class problem09b {
	public static void main(String[] args) {
		Scanner input = new Scanner(System.in);
		
		ArrayList<Point> p = new ArrayList<>();
		double temp1, temp2;
		for (int i=0; i<3; i=i+1) {
			temp1 = input.nextDouble();
			temp2 = input.nextDouble();
			p.add( new Point(temp1, temp2) );
		}

		double o = Point.orientation(p.get(0), p.get(1), p.get(2));
		if ( o == 0 ) {
			System.out.println("0, this point is ON the line");
		} else if( o > 0 ) {
			System.out.println("1, this point is in the LEFT side of the line");
		} else if( o < 0 ) {
			System.out.println("2, this point is in the RIGHT side of the line");
		}
	}
}
