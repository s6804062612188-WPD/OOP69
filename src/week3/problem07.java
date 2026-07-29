package week3;

import java.util.Scanner;
import java.lang.Math;

final class MyTriangle {
	public static boolean isValid(double side1, double side2, double side3) {
		if ( !(side1 > 0 && side2 > 0 && side3 > 0) ) return false;
		
		double largest = Math.max(side1, Math.max(side2, side3));
		if (side1 + side2 + side3 <= 2.0*largest) return false;
		return true;
	}
	
	public static double area(double side1, double side2, double side3) {
		double area = 0;
		double s = (side1 + side2 + side3)/2.0;
		
		area = Math.sqrt(s*(s-side1)*(s-side2)*(s-side3));
		return area;
	}
}

public class problem07 {
	public static void main(String[] args) {
		Scanner input = new Scanner(System.in);
		
		double a,b,c;
		a = input.nextDouble();
		b = input.nextDouble();
		c = input.nextDouble();
		
		if ( MyTriangle.isValid(a,b,c) ) {
			System.out.println("1, this can be triangle");
			System.out.printf("and the area is:\t%f\n", MyTriangle.area(a,b,c));
		} else {
			System.out.println("0, this is not a triangle");
		}
	}
}
