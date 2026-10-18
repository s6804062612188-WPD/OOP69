package week08;

class Octagon extends GeometricObject implements Comparable<Octagon>, Cloneable {
	private double side;

	public Octagon() {
		this(1.0);
	}
	public Octagon(double side) {
		this.side = side;
	}

	public double getSide() { return side; }
	public void setSide(double side) { this.side = side; }
	@Override public double getArea() { return (2 + 4 / Math.sqrt(2)) * side * side; }
	@Override public double getPerimeter() { return 8 * side; }

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (!(obj instanceof Octagon)) {
			return false;
		}

		Octagon other = (Octagon) obj;
		return Double.compare(getArea(), other.getArea()) == 0;
	}

	@Override
	public int hashCode() {
		return Double.hashCode(getArea());
	}

	@Override
	public int compareTo(Octagon other) {
		return Double.compare(getArea(), other.getArea());
	}

	@Override
	public Octagon clone() {
		try {
			return (Octagon) super.clone();
		} catch (CloneNotSupportedException e) {
			throw new AssertionError(e);
		}
	}

	@Override
	public String toString() {
		return "Octagon[side=" + side
				+ ", area=" + getArea()
				+ ", perimeter=" + getPerimeter() + "]";
	}
}
