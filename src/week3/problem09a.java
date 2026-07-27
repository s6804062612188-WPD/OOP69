package week3;

import java.util.Scanner;
import java.util.ArrayList;

class CheckTriangle {
	public static boolean leftOfTheLine(double x0, double y0, double x1, double y1, double x2, double y2) {
		return orientation(x0, y0, x1, y1, x2, y2) == 1;
	}
	public static boolean onTheSameLine(double x0, double y0, double x1, double y1, double x2, double y2) {
		return orientation(x0, y0, x1, y1, x2, y2) == 0;		
	}
	public static boolean onTheLineSegment(double x0, double y0, double x1, double y1, double x2, double y2) {
		if ( onTheSameLine(x0, y0, x1, y1, x2, y2) ) {
			if ( Math.min(x0, x1) <= x2 && x2 <= Math.max(x0, x1) &&
				 Math.min(y0, y1) <= y2 && y2 <= Math.max(y0, y1)) {
				return true;
			}
		}
		return false;
	}
	public static int orientation(double x0, double y0, double x1, double y1, double x2, double y2) {
		double temp = (x1 - x0)*(y2-y0) - (y1-y0)*(x2-x0); // cross-product
		
		if (temp > 0) return 1;
		else if (temp < 0) return 2;
		return 0;
	}
}

public class problem09a {
	public static void main(String[] args) {
		Scanner input = new Scanner(System.in);
		
		double x0 = input.nextDouble();
		double y0 = input.nextDouble();
		double x1 = input.nextDouble();
		double y1 = input.nextDouble();
		double x2 = input.nextDouble();
		double y2 = input.nextDouble();
		
		String left = "LEFT", right = "RIGHT";
		if ( y0 == y1 ) {
			System.out.println("the line's slope is 0, meaning that left and right does not exists");
			System.out.println("instead, it's top or bottom of the line");
			    if (x1 > x0) {
					left = "TOP";
					right = "BOTTOM";
				} else {
					left = "BOTTOM";
					right = "TOP";
				}
			System.out.println("------------------------------");
		}
		if (CheckTriangle.onTheSameLine(x0, y0, x1, y1, x2, y2)) {
			System.out.println("0, p2 is on the secant line from p0 to p1");
			if (CheckTriangle.onTheLineSegment(x0, y0, x1, y1, x2, y2)) {
				System.out.println("additionally, p2 is between p0 and p1");
			}
		} else {
			if (CheckTriangle.leftOfTheLine(x0, y0, x1, y1, x2, y2)) {
				System.out.println("2, p2 is on the " + left + " side of secant line from p0 to p1");
			} else {
				System.out.println("1, p2 is on the " + right + " side of secant line from p0 to p1");
			}
		}
	}
}
