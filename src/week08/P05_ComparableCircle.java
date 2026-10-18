package week08;

class ComparableCircle extends Circle {

	public ComparableCircle() {
		super();
	}

	public ComparableCircle(double radius) {
		super(radius);
	}

	public ComparableCircle(double radius, String color, boolean filled) {
		super(radius, color, filled);
	}

	@Override
	public int compareTo(Circle other) {
		return Double.compare(getArea(), other.getArea());
	}
}
