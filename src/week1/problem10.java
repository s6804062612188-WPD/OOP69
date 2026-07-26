package week1;

import java.util.Scanner;
import java.text.DecimalFormat;
import java.util.ArrayList;

class Thing {
	public double x;
	public double y;
}

class Tornado extends Thing {
	public double r;
	public double v;
	
	public void updateHour() {
		x = x - v;
		r -= r*0.2;
	}
	
	public void hit(House house) {
		if (house.destroyed) return;
		
		double dx = house.x - this.x;
		double dy = house.y - this.y;
		if (dx*dx + dy*dy <= r*r) {
			house.destroyed = true;
		}
	}
	
	public Tornado(double x, double y, double radius, double vel) {
		this.x = x;
		this.y = y;
		this.r = radius;
		this.v = vel;
	}
}

class House extends Thing {
	public boolean destroyed = false;
	
	public House(double x, double y) {
		this.x = x;
		this.y = y;
	}
}

public class problem10 {
	public static void main(String[] args) {
		final double MAX = 1_000_000_000;
		Scanner input = new Scanner(System.in);
		
		double temp1; double temp2; double temp3; double temp4;
		while (true) {
			System.out.println("/>Input the tornado's x, y, radius, and velocity:");
			temp1 = input.nextDouble();
			temp2 = input.nextDouble();
			temp3 = input.nextDouble();
			temp4 = input.nextDouble();
			if ( !(1 < temp1 && temp1 < MAX && 1 < temp2 && temp2 < MAX) ) {
				System.out.println("\n/>Error, the coordinates must be between 1 and " + MAX);
				continue;
			}
			if ( !(1 < temp3 && temp3 < 100) ) {
				System.out.println("\n/>Error, the radius of the tornado must be between 1 and 100");
				continue;
			}
			if ( !(1 < temp4 && temp4 < 100) ) {
				System.out.println("\n/>Error, the velocity of the tornado must be between 1 and 100");
				continue;
			}
			break;
		}
		Tornado T = new Tornado(temp1, temp2, temp3, temp4);
		
		System.out.println("");
		int houseCount=0;
		while (true) {
			System.out.println("/>Input the amount of houses:");
			houseCount = input.nextInt();
			if ( !(1 < houseCount && houseCount < 10000) ) {
				System.out.println("\n/>Error, the house count must be between 1 and 10000");
				continue;
			}
			break;
		}
		ArrayList<House> village = new ArrayList<>();
		for(int i=0; i<houseCount; i=i+1) {
			while (true) {
				System.out.println("/>Input house #" + (i+1) +"'s coordinates");
				temp1 = input.nextDouble();
				temp2 = input.nextDouble();
				if ( !(1 < temp1 && temp1 < MAX && 1 < temp2 && temp2 < MAX) ) {
					System.out.println("\n/>Error, the coordinates must be between 1 and " + MAX);
					continue;
				}
				break;
			}
			village.add(new House(temp1, temp2));
		}
		
		int hours = 0;
		do {
			for (House h : village) {
				T.hit(h);
			}
			T.updateHour();
			hours++;
		} while (T.r > 0.5);
		
		int destCount = 0;
		for (House h : village) {
			if (h.destroyed) destCount++;
		}
		
		DecimalFormat df = new DecimalFormat("0.############");
		System.out.printf("\n/>Simulation lasted %d %s, before the tornado's radius diminished (r<0.5)\n",
			hours,
			(hours == 1)? "hour":"hours" );
		System.out.printf("/>There %s %d house%s destroyed.",
			(destCount == 1)? "was" : "were",
			destCount,
			(destCount == 1)? "" : "s"
		);
		if (destCount > 0) {
			System.out.printf(" Here are the list:\n");
			for (House h : village) {
				if (h.destroyed) {
					System.out.println(" - @[" + df.format(h.x) + ", " + df.format(h.y) + "]");
				}
			}	
		}
		System.out.println();
	}
}
