package week2;

import java.text.DecimalFormat;

// ComplexTest.java
class Complex {
	private double r, i;
	Complex(double r, double i) {
		this.r = r; this.i = i;
	}
	Complex(Complex c) {
		this(c.r, c.i);
	}
	public void add(Complex c) {
		r += c.r;
		i += c.i;
	}
	public void subtract(Complex c) {
		r -= c.r;
		i -= c.i;
	}
	public void multiply(Complex c) {
		double aaa = r;
		double bbb = i;
		r = aaa * c.r - bbb * c.i;
		i = aaa * c.i + bbb * c.r;
	}
	public void divide(Complex c) {
		double ccc = c.r;
		double ddd = c.i;
		double v = ccc*ccc + ddd*ddd;
		if (v == 0) {
			System.out.println("Invalid complex division");
			return;
		}
		Complex temp = new Complex(ccc, -ddd);
		this.multiply(temp);
		r /= v;
		i /= v;
	}
	
	public void print() {
	 DecimalFormat df = new DecimalFormat("0.######");
	 System.out.printf("%s ", df.format(this.r));
	 if (i>=0) System.out.printf("+ ");
	 System.out.printf("%si\n", df.format(this.i));
	}
}
public class problem01 {
	public static void main(String[] args) {
		Complex a = new Complex(1.0, 2.0);
		Complex b = new Complex(3.0, 4.0);
		Complex c = new Complex(a);
		c.add(b);
		c.print();
		c.subtract(b);
		c.print();
		c.multiply(b);
		c.print();
		c.divide(b);
		c.print();
	}
}
