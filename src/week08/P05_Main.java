package week08;

abstract class GeometricObject {
	private String color = "white";
	private boolean filled;

	protected GeometricObject() {}
	protected GeometricObject(String color, boolean filled) {
		this.color = color;
		this.filled = filled;
	}

	public String getColor() { return color; }
	public void setColor(String color) { this.color = color; }
	public boolean isFilled() { return filled; }
	public void setFilled(boolean filled) { this.filled = filled; }

	public abstract double getArea();
	public abstract double getPerimeter();
}

public class P05_Main {
	public static void main(String[] args) {
		// 5.1 Circle
		System.out.println("=== 5.1 Circle ===");

		Circle c1 = new Circle(5);
		Circle c2 = new Circle(5);
		Circle c3 = new Circle(8);

		System.out.println(c1);
		System.out.println("c1 equals c2: " + c1.equals(c2));
		System.out.println("c1 equals c3: " + c1.equals(c3));
		System.out.println("Compare c1 and c3: "
				+ c1.compareTo(c3));

		// 5.2 Rectangle
		System.out.println("\n=== 5.2 Rectangle ===");

		Rectangle r1 = new Rectangle(4, 6);
		Rectangle r2 = new Rectangle(3, 8);
		Rectangle r3 = new Rectangle(5, 5);

		System.out.println(r1);
		System.out.println("r1 equals r2: " + r1.equals(r2));
		System.out.println("r1 equals r3: " + r1.equals(r3));
		System.out.println("Compare r1 and r3: "
				+ r1.compareTo(r3));

		// 5.3 Octagon
		System.out.println("\n=== 5.3 Octagon ===");

		Octagon original = new Octagon(5);

		System.out.println("Area: " + original.getArea());
		System.out.println("Perimeter: "
				+ original.getPerimeter());

		Octagon copy = original.clone();

		System.out.println("Original: " + original);
		System.out.println("Copy: " + copy);
		System.out.println("Equal: " + original.equals(copy));
		System.out.println("Same object: " + (original == copy));

		// 5.4 ComparableCircle
		System.out.println("\n=== 5.4 ComparableCircle ===");

		ComparableCircle cc1 = new ComparableCircle(5);
		ComparableCircle cc2 = new ComparableCircle(8);

		System.out.println("Circle 1 area: " + cc1.getArea());
		System.out.println("Circle 2 area: " + cc2.getArea());

		if (cc1.compareTo(cc2) > 0) {
			System.out.println("Circle 1 is larger.");
		} else if (cc1.compareTo(cc2) < 0) {
			System.out.println("Circle 2 is larger.");
		} else {
			System.out.println("Both circles have equal areas.");
		}
	}
}
