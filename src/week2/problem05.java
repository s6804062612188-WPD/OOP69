package week2;

import java.util.Scanner;

class LinearEquation {
	private double a,b,c,d,e,f;
	
	LinearEquation() {
		this.a=0.0;
		this.b=0.0;
		this.c=0.0;
		this.d=0.0;
		this.e=0.0;
		this.f=0.0;
		
	}

	public LinearEquation(double a, double b, double c, double d, double e, double f) {
		this.a = a;
		this.b = b;
		this.c = c;
		this.d = d;
		this.e = e;
		this.f = f;
	}
	
	public double getA() {
		return this.a;
	}
	public double getB() {
		return this.b;
	}
	public double getC() {
		return this.c;
	}
	public double getD() {
		return this.d;
	}
	public double getE() {
		return this.e;
	}
	public double getF() {
		return this.f;
	}
	
	private double adbc() {
		return a*d-b*c;
	}
	
	public boolean isSolvable() {
		return adbc()!=0;
	}
	
	public double getX() {
		return (e*d-b*f)/adbc();
	}
	public double getY() {
		return (a*f-e*c)/adbc();
	}
	
}

public class problem05 {
	public static void main(String[] args) {
		Scanner input = new Scanner(System.in);
		System.out.printf("Enter a b c d e f:\t");
		double a = input.nextDouble();
		double b = input.nextDouble();
		double c = input.nextDouble();
		double d = input.nextDouble();
		double e = input.nextDouble();
		double f = input.nextDouble();
		LinearEquation thing = new LinearEquation(a,b,c,d,e,f);
		System.out.printf("%fx + %fy = %f\n%fx + %fy = %f\n", a,b,e,c,d,f);
		if (thing.isSolvable()) {
			System.out.printf("x is %f & y is %f", thing.getX(), thing.getY());
		} else {
			System.out.printf("The equation has no real solution");
		}
		System.out.printf("\n");
	}
}
