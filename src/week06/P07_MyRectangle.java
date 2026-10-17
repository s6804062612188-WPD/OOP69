package week06;

public class P07_MyRectangle {
	public static void main(String[] args) {
		MyRectangle2D r1 = new MyRectangle2D();
		MyRectangle2D r2 = new MyRectangle2D(0, 0, 4, 6);
		MyRectangle2D r3 = new MyRectangle2D(0, 0, 2, 2);
		MyRectangle2D r4 = new MyRectangle2D(3, 0, 4, 4);

		// Test default constructor
		System.out.println("=== Default Rectangle ===");
		System.out.println("X: " + r1.getX());
		System.out.println("Y: " + r1.getY());
		System.out.println("Width: " + r1.getWidth());
		System.out.println("Height: " + r1.getHeight());
		System.out.println("Area: " + r1.getArea());
		System.out.println("Perimeter: " + r1.getPerimeter());

		// Test parameterized constructor
		System.out.println("\n=== Rectangle r2 ===");
		System.out.println("Area: " + r2.getArea());
		System.out.println("Perimeter: " + r2.getPerimeter());

		// Test contains(point)
		System.out.println("\n=== Contains Point ===");
		System.out.println("r2 contains (1, 1): "
				+ r2.contains(1, 1));
		System.out.println("r2 contains (3, 0): "
				+ r2.contains(3, 0));

		// Test contains(rectangle)
		System.out.println("\n=== Contains Rectangle ===");
		System.out.println("r2 contains r3: "
				+ r2.contains(r3));

		// Test overlaps(rectangle)
		System.out.println("\n=== Overlapping Rectangles ===");
		System.out.println("r2 overlaps r4: "
				+ r2.overlaps(r4));

		// Test setters
		System.out.println("\n=== Testing Setters ===");
		r1.setX(2);
		r1.setY(3);
		r1.setWidth(5);
		r1.setHeight(4);

		System.out.println("X: " + r1.getX());
		System.out.println("Y: " + r1.getY());
		System.out.println("Width: " + r1.getWidth());
		System.out.println("Height: " + r1.getHeight());
		System.out.println("Area: " + r1.getArea());
		System.out.println("Perimeter: " + r1.getPerimeter());
	}
}

class MyRectangle2D {
	private double x;
	private double y;
	private double width;
	private double height;

	public MyRectangle2D() {
		this(0, 0, 1, 1);
	}
	public MyRectangle2D(double x, double y,
						 double width, double height) {
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
	}

	// Getters and setters
	public double getX() { return x; }
	public double getY() { return y; }
	public void setX(double x) { this.x = x; }
	public void setY(double y) { this.y = y; }

	public double getWidth() { return width; }
	public double getHeight() { return height; }
	public void setWidth(double width) { this.width = width; }
	public void setHeight(double height) { this.height = height; }

	// Calculate area
	public double getArea() { return width * height; }
	// Calculate perimeter
	public double getPerimeter() { return 2 * (width + height); }

	// Check whether a point is inside or on the boundary
	public boolean contains(double x, double y) {
		return Math.abs(x - this.x) <= width / 2 &&
			   Math.abs(y - this.y) <= height / 2;
	}

	// Check whether another rectangle is fully contained
	public boolean contains(MyRectangle2D r) {
		return Math.abs(r.x - this.x) + r.width / 2 <= width / 2 &&
			   Math.abs(r.y - this.y) + r.height / 2 <= height / 2;
	}

	// Check whether another rectangle overlaps this rectangle
	public boolean overlaps(MyRectangle2D r) {
		return Math.abs(r.x - this.x) < (width + r.width) / 2 &&
			   Math.abs(r.y - this.y) < (height + r.height) / 2;
	}
}