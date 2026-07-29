package week2;

import java.text.DecimalFormat;

import java.util.Scanner;
import java.util.ArrayList;

class RegularPolygon {
	private int n;
	private double sideLength;
	private double x;
	private double y;
	
	public RegularPolygon() {
		this(3, 1, 0,0);
	}
	public RegularPolygon(int n, double length) {
		this(n, length, 0,0);
	}
	public RegularPolygon(int n, double length, double x, double y) {
		this.n = n;
		this.sideLength = length;
		this.x = x;
		this.y = y;
	}
	
	public int getN() { return this.n; }
	public double getSideLength() { return this.sideLength; }
	public double getX() { return this.x; }
	public double getY() { return this.y; }
	
	public void setN(int n) { this.n = n; }
	public void setSideLength(double SL) { this.sideLength = SL; }
	public void setX(double x) { this.x = x; }
	public void setY(double y) { this.y = y; }
	
	public double getPerimeter() { return getN()*getSideLength(); }
	
	public double getArea() {
		double area;
		area = getN()*getSideLength()*getSideLength() / (4 * Math.tan(Math.PI / getN()) );
		return area;
	}
}

public class problem08 {
	public static void main(String[] args) {
		Scanner input = new Scanner(System.in);
		ArrayList<RegularPolygon> shapes = new ArrayList<>();
		
		shapes.add( new RegularPolygon() );
		shapes.add( new RegularPolygon(6,4) );
		shapes.add( new RegularPolygon(10, 4, 5.6, 7.8) );
		
		System.out.println("/>for the 4th polygon, enter it yourself (n, sidelength, x,y):\t");
		shapes.add( new RegularPolygon(
				input.nextInt(),
				input.nextDouble(),
				input.nextDouble(), 
				input.nextDouble() ) );
		
		System.out.println("\n------------------------------------------\n");
		DecimalFormat df = new DecimalFormat("0.######");
		for (RegularPolygon p : shapes) {
			System.out.printf("%s-agon, with sideLength = %s, @(%s, %s)\n",
					df.format( p.getN() ),
					df.format( p.getSideLength() ),
					df.format( p.getX() ),
					df.format( p.getY() ) );
			System.out.printf("perimeter = %s, area = %s\n",
					df.format( p.getPerimeter() ),
					df.format( p.getArea() ) );
			System.out.println();
		}
	}
}
