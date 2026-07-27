// raw power
package week3;

import java.util.Scanner;
import java.util.ArrayList;

class UFO {
	int start;
	int end;
	
	UFO(int a, int b) {
		this.start = a;
		this.end = b;
	}
}

public class problem10a {
	public static void main(String[] args) {
		Scanner input = new Scanner(System.in);
		
		int UFOCount, laserCount;
		
		UFOCount = input.nextInt();
		laserCount = input.nextInt();
		
		ArrayList<UFO> invasion = new ArrayList<>();
		for (int i=0; i<UFOCount; i=i+1) {
			invasion.add( new UFO( input.nextInt(), input.nextInt() ) );
		}
		
		ArrayList<Integer> strikes = new ArrayList<>();
		for (int i=0; i<laserCount; i=i+1) {
			strikes.add( input.nextInt() );
		}
		
		int abduct = 0;
		for (int l : strikes) {
			for (UFO u : invasion) {
				if (u.start < l && l < u.end) {
					abduct++;
				}
			}
		}
		System.out.println(abduct);
	}
}
