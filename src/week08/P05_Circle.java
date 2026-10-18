package week08;

class Circle extends GeometricObject implements Comparable<Circle> {
	private double radius;

	public Circle() {
		this(1.0);
	}

	public Circle(double radius) {
		this.radius = radius;
	}

	public Circle(double radius, String color, boolean filled) {
		super(color, filled);
		this.radius = radius;
	}

	public double getRadius() { return radius; }
	public void setRadius(double radius) { this.radius = radius; }
	@Override public double getArea() { return Math.PI * radius * radius; }
	@Override public double getPerimeter() { return 2 * Math.PI * radius; }

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (!(obj instanceof Circle)) {
			return false;
		}

		Circle other = (Circle) obj;
		return Double.compare(radius, other.radius) == 0;
	}

	@Override
	public int hashCode() {
		return Double.hashCode(radius);
	}

	@Override
	public int compareTo(Circle other) {
		return Double.compare(radius, other.radius);
	}

	@Override
	public String toString() {
		return "Circle[radius=" + radius
				+ ", area=" + getArea() + "]";
	}
}
