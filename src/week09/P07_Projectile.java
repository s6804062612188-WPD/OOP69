package week09;

class Projectile {
	private double initialY;
	private double speed;
	private double angle;
	private final double gravity = -10.0;

	public Projectile(double initialY, double speed, double angle) {
		this.initialY = initialY;
		this.speed = speed;
		this.angle = Math.toRadians(angle);
	}

	public double getFlightTime() {
		double uy = speed * Math.sin(angle);

		// Solve:
		// initialY = uy*t + 0.5*gravity*t^2
		double a = 0.5 * gravity;
		double b = uy;
		double c = -initialY;

		double discriminant = b * b - 4 * a * c;

		if (discriminant < 0) {
			return -1;
		}

		double t1 = (-b - Math.sqrt(discriminant)) / (2 * a);
		double t2 = (-b + Math.sqrt(discriminant)) / (2 * a);

		// Select the positive solution.
		if (t1 >= 0 && t2 >= 0) {
			return Math.max(t1, t2);
		}
		if (t1 >= 0) return t1;
		if (t2 >= 0) return t2;

		return -1;
	}

	public double getLandingX() {
		double time = getFlightTime();

		if (time < 0) return Double.NaN;

		double ux = speed * Math.cos(angle);
		return ux * time;
	}
}
