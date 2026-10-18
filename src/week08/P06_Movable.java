package week08;

interface Movable {
	void moveUp();
	void moveDown();
	void moveLeft();
	void moveRight();
}

class MovablePoint implements Movable {
	int x, y;
	int xSpeed, ySpeed;

	public MovablePoint(int x, int y, int xSpeed, int ySpeed) {
		this.x = x;
		this.y = y;
		this.xSpeed = xSpeed;
		this.ySpeed = ySpeed;
	}

	@Override
	public void moveUp() { y -= ySpeed; }
	@Override
	public void moveDown() { y += ySpeed; }
	@Override
	public void moveLeft() { x -= xSpeed; }
	@Override
	public void moveRight() { x += xSpeed; }

	@Override
	public String toString() {
		return "(" + x + "," + y + "), speed=("
				+ xSpeed + "," + ySpeed + ")";
	}
}

class MovableCircle implements Movable {
	private int radius;
	private MovablePoint center;

	public MovableCircle(int x, int y, int xSpeed, int ySpeed, int radius) {
		this.center = new MovablePoint(x, y, xSpeed, ySpeed);
		this.radius = radius;
	}

	@Override
	public void moveUp() { center.moveUp(); }
	@Override
	public void moveDown() { center.moveDown(); }
	@Override
	public void moveLeft() { center.moveLeft(); }
	@Override
	public void moveRight() { center.moveRight(); }

	@Override
	public String toString() {
		return "MovableCircle[center=" + center
				+ ", radius=" + radius + "]";
	}
}

public class P06_Movable {
	public static void main(String[] args) {
		MovablePoint point = new MovablePoint(1, 2, 3, 4);
		System.out.println("Point: " + point);

		point.moveUp();
		System.out.println("Move up: " + point);

		point.moveRight();
		System.out.println("Move right: " + point);

		System.out.println();

		MovableCircle circle = new MovableCircle(5, 10, 2, 3, 7);
		System.out.println("Circle: " + circle);

		circle.moveUp();
		System.out.println("Move up: " + circle);

		circle.moveLeft();
		System.out.println("Move left: " + circle);
	}
}
