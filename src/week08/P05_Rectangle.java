package week08;

class Rectangle extends GeometricObject implements Comparable<Rectangle> {
	private double width, height;

	public Rectangle() {
		this(1.0, 1.0);
	}
	public Rectangle(double width, double height) {
		this.width = width;
		this.height = height;
	}
	public Rectangle(double width, double height, String color, boolean filled) {
		super(color, filled);
		this.width = width;
		this.height = height;
	}

	public double getWidth() { return width; }
	public void setWidth(double width) { this.width = width; }
	public double getHeight() { return height; }
	public void setHeight(double height) { this.height = height; }
	@Override public double getArea() { return width * height; }
	@Override public double getPerimeter() { return 2 * (width + height); }
	
	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (!(obj instanceof Rectangle)) {
			return false;
		}

		Rectangle other = (Rectangle) obj;
		return Double.compare(getArea(), other.getArea()) == 0;
	}

	@Override
	public int hashCode() {
		return Double.hashCode(getArea());
	}

	@Override
	public int compareTo(Rectangle other) {
		return Double.compare(getArea(), other.getArea());
	}

	@Override
	public String toString() {
		return "Rectangle[width=" + width
				+ ", height=" + height
				+ ", area=" + getArea() + "]";
	}
}
