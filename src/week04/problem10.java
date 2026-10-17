package week04;

import java.util.Scanner;

class Car {
	private int lane;

	public Car(int startLane) {
		this.lane = startLane;
	}

	public int getLane() { return lane; }
	public void setLane(int lane) { this.lane = lane; }
}

class Road {
	private int lanes;
	private int time;
	private int[][] obstacles;

	public Road(int lanes, int time) {
		this.lanes = lanes;
		this.time = time;
		this.obstacles = new int[time][lanes];
	}

	public void input(Scanner sc) {
		for (int i = 0; i < time; i++) {
			for (int j = 0; j < lanes; j++) {
				obstacles[i][j] = sc.nextInt();
			}
		}
	}

	public boolean isSafe(int time, int lane) {
		if (lane < 1 || lane > lanes) return false;

		return obstacles[time - 1][lane - 1] == 0;
	}

	public boolean findPath(Car car, int time, int[] moves) {
		if (time == this.time) {
			return true;
		}

		int currentLane = car.getLane();

		// Try left = 1, right = 2, then stay = 3)
		for (int choice = 1; choice <= 3; choice++) {
			int nextLane = currentLane;

			if (choice == 1) {
				nextLane--;
			} else if (choice == 2) {
				nextLane++;
			}

			if (!isSafe(time + 1, nextLane)) {
				continue;
			}

			car.setLane(nextLane);
			moves[time] = choice;

			if (findPath(car, time + 1, moves)) {
				return true;
			}

			// Backtrack
			car.setLane(currentLane);
		}

		return false;
	}
}

public class problem10 {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);

		int m = sc.nextInt();
		int n = sc.nextInt();
		int t = sc.nextInt();

		Road road = new Road(m, t);
		road.input(sc);

		Car car = new Car(n);
		int[] moves = new int[t];

		if (road.findPath(car, 0, moves)) {
			for (int move : moves) {
				System.out.println(move);
			}
		}

		sc.close();
	}
}
