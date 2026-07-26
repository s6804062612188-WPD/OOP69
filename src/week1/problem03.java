package week1;

import java.util.Scanner;

public class problem03 {
	public static void main(String[] arg) {
		Scanner input = new Scanner(System.in);
		double s1,s2,s3,s, area;
		double x1,x2,x3,y1,y2,y3;
		System.out.printf("Enter three point for a triangle: ");
		x1 = input.nextDouble(); y1 = input.nextDouble();
		x2 = input.nextDouble(); y2 = input.nextDouble();
		x3 = input.nextDouble(); y3 = input.nextDouble();
		
		s1 = Math.sqrt( Math.pow(x1-x2, 2) + Math.pow(y1-y2, 2)  );
		s2 = Math.sqrt( Math.pow(x2-x3, 2) + Math.pow(y2-y3, 2)  );
		s3 = Math.sqrt( Math.pow(x1-x3, 2) + Math.pow(y1-y3, 2)  );
		
		s = (s1+s2+s3)/2;
		area = Math.sqrt( s*(s-s1)*(s-s2)*(s-s3) );
		System.out.printf("The area of the triangle is: %.3f\n", area);
	}
}
