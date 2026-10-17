package week06;

class Rectangle {
	double width, height;
	double x, y;

	public Rectangle() {
		this(1, 1, 0, 0);
	}
	public Rectangle(double width, double height,
					 double x, double y) {
		this.width = width;
		this.height = height;
		this.x = x;
		this.y = y;
	}

	// Return rectangle area
	public double getArea(Rectangle a) {
		return a.width * a.height;
	}
}

class Line {
	double x1, y1;
	double x2, y2;

	public Line() {
		this(0, 0, 0, 0);
	}
	public Line(double x1, double y1,
				double x2, double y2) {
		this.x1 = x1;
		this.y1 = y1;
		this.x2 = x2;
		this.y2 = y2;
	}

	public double getLong(Line a) {
		double dx = a.x2 - a.x1;
		double dy = a.y2 - a.y1;

		return Math.sqrt(dx * dx + dy * dy);
	}
}

public class P08_RectangleLine {
	public static int contains(Line a, Rectangle b) {
		boolean first =
			a.x1 >= b.x &&
			a.x1 <= b.x + b.width &&
			a.y1 >= b.y &&
			a.y1 <= b.y + b.height;

		boolean second =
			a.x2 >= b.x &&
			a.x2 <= b.x + b.width &&
			a.y2 >= b.y &&
			a.y2 <= b.y + b.height;

		return first && second ? 1 : 0;
	}

	public static int cross(Line a, Line b) {
		double d1 = direction(a.x1, a.y1, a.x2, a.y2,
							  b.x1, b.y1);
		double d2 = direction(a.x1, a.y1, a.x2, a.y2,
							  b.x2, b.y2);
		double d3 = direction(b.x1, b.y1, b.x2, b.y2,
							  a.x1, a.y1);
		double d4 = direction(b.x1, b.y1, b.x2, b.y2,
							  a.x2, a.y2);

		if (((d1 > 0 && d2 < 0) || (d1 < 0 && d2 > 0)) &&
			((d3 > 0 && d4 < 0) || (d3 < 0 && d4 > 0))) {
			return 1;
		}

		if (d1 == 0 && onSegment(a.x1, a.y1, a.x2, a.y2,
								 b.x1, b.y1)) return 1;
		if (d2 == 0 && onSegment(a.x1, a.y1, a.x2, a.y2,
								 b.x2, b.y2)) return 1;
		if (d3 == 0 && onSegment(b.x1, b.y1, b.x2, b.y2,
								 a.x1, a.y1)) return 1;
		if (d4 == 0 && onSegment(b.x1, b.y1, b.x2, b.y2,
								 a.x2, a.y2)) return 1;

		return 0;
	}

	private static double direction(
			double ax, double ay, double bx, double by,
			double px, double py) {
		return (bx - ax) * (py - ay) -
			   (by - ay) * (px - ax);
	}

	private static boolean onSegment(
			double ax, double ay, double bx, double by,
			double px, double py) {
		return px >= Math.min(ax, bx) &&
			   px <= Math.max(ax, bx) &&
			   py >= Math.min(ay, by) &&
			   py <= Math.max(ay, by);
	}

	public static int overlaps(Rectangle a, Rectangle b) {
		boolean horizontal =
			a.x < b.x + b.width &&
			a.x + a.width > b.x;

		boolean vertical =
			a.y < b.y + b.height &&
			a.y + a.height > b.y;

		return horizontal && vertical ? 1 : 0;
	}

	public static double distance(Line a, Rectangle b) {
		double lineX = (a.x1 + a.x2) / 2.0;
		double lineY = (a.y1 + a.y2) / 2.0;

		double rectX = b.x + b.width / 2.0;
		double rectY = b.y + b.height / 2.0;

		double dx = lineX - rectX;
		double dy = lineY - rectY;

		return Math.sqrt(dx * dx + dy * dy);
	}

	public static void main(String[] args) {
		Rectangle r1 = new Rectangle(10, 6, 0, 0);
		Rectangle r2 = new Rectangle(5, 5, 8, 2);

		Line l1 = new Line(1, 1, 5, 4);
		Line l2 = new Line(2, 5, 8, 0);
		Line l3 = new Line(20, 20, 25, 25);

		System.out.println("Area: " + r1.getArea(r1));
		System.out.println("Line length: " + l1.getLong(l1));

		System.out.println("Line inside rectangle: " + contains(l1, r1));
		System.out.println("Lines cross: " + cross(l1, l2));
		System.out.println("Rectangles overlap: " + overlaps(r1, r2));
		System.out.println("Center distance: " + distance(l3, r1));
	}
}