package week2;

import java.util.Scanner;

final class RectCheck {
	public static int RectangleCheck(Rectangle a, Rectangle b) {
		int xOverlap=0;
		int yOverlap=0;
		if (a.left <= b.right && b.right <= a.right) xOverlap++;
		if (a.left <= b.left && b.left <= a.right) xOverlap++;
		if (a.bottom <= b.top && b.top <= a.top) yOverlap++;
		if (a.bottom <= b.bottom && b.bottom <= b.top) yOverlap++;
		
		if (xOverlap == 2 && yOverlap == 2) return 2;
		else if (xOverlap+yOverlap > 0) return 1;
		else if (xOverlap+yOverlap == 0) return 0;
		return -1;
	}
}

class Rectangle {
	protected double x;
	protected double y;
	protected double width;
	protected double height;
	
	protected double right;
	protected double left;
	protected double top;
	protected double bottom;
	
	public Rectangle(double x, double y, double width, double height) {
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
		
		this.right = x+width/2.0;
		this.left = x-width/2.0;
		this.top = y+height/2.0;
		this.bottom = y-height/2.0;
	}
}

public class problem07 {
	public static void main(String[] args) {
		Scanner input = new Scanner(System.in);
		
		System.out.println("Enter's rectangle 1's center (x,y), width, and height:\t");
		Rectangle a = new Rectangle(input.nextDouble(), input.nextDouble(), input.nextDouble(), input.nextDouble());
		System.out.println("Enter's rectangle 2's center (x,y), width, and height:\t");
		Rectangle b = new Rectangle(input.nextDouble(), input.nextDouble(), input.nextDouble(), input.nextDouble());
		int state = RectCheck.RectangleCheck(a,b);
		switch(state) {
			case 2: System.out.println("b is CONTAINED by a"); break;
			case 1: System.out.println("b is OVERLAPPED by a"); break;
			case 0: System.out.println("b is SEPARATED from a"); break;
		}
	}
}
