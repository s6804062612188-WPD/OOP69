package week09;

import java.util.ArrayList;
import java.util.Scanner;

class Particle {
	private int id;
	private int energy;

	public Particle(int id, int energy) {
		this.id = id;
		this.energy = energy;
	}

	public int getId() { return id; }
	public int getEnergy() { return energy; }
}

class ParticleCollision {
	private final ArrayList<Particle> particles;
	private long totalEnergy;

	public ParticleCollision(ArrayList<Particle> particles) {
		this.particles = particles;
		this.totalEnergy = 0;
	}

	public long calculateTotalEnergy() {
		while (particles.size() >= 2) {
			long maxDifference = -1;
			int pairIndex = -1;

			// Find the adjacent pair with the largest difference.
			// Scanning from left to right preserves the tie-breaking rule.
			for (int i = 0; i < particles.size() - 1; i++) {
				long difference = Math.abs(
					(long) particles.get(i).getEnergy()
					- particles.get(i + 1).getEnergy()
				);

				if (difference > maxDifference) {
					maxDifference = difference;
					pairIndex = i;
				}
			}

			// Add the released energy.
			totalEnergy += maxDifference;

			// Both particles disappear.
			particles.remove(pairIndex + 1);
			particles.remove(pairIndex);
		}

		return totalEnergy;
	}
}

public class P09_Particle {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);

		int n = sc.nextInt();
		ArrayList<Particle> particles = new ArrayList<>();

		for (int i = 1; i <= n; i++) {
			int energy = sc.nextInt();
			particles.add(new Particle(i, energy));
		}

		ParticleCollision experiment = new ParticleCollision(particles);

		System.out.println(experiment.calculateTotalEnergy());
	}
}
