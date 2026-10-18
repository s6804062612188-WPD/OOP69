// raw power
package week03;

import java.util.Scanner;
import java.util.ArrayList;

class UFO1 {
	int start;
	int end;
	
	UFO1(int a, int b) {
		this.start = a;
		this.end = b;
	}
}

public class problem10a {
	public static void main(String[] args) {
		Scanner input = new Scanner(System.in);
		
		int UFO1Count, laserCount;
		
		UFO1Count = input.nextInt();
		laserCount = input.nextInt();
		
		ArrayList<UFO1> invasion = new ArrayList<>();
		for (int i=0; i<UFO1Count; i=i+1) {
			invasion.add( new UFO1( input.nextInt(), input.nextInt() ) );
		}
		
		ArrayList<Integer> strikes = new ArrayList<>();
		for (int i=0; i<laserCount; i=i+1) {
			strikes.add( input.nextInt() );
		}
		
		int abduct = 0;
		for (int l : strikes) {
			for (UFO1 u : invasion) {
				if (u.start < l && l < u.end) {
					abduct++;
				}
			}
		}
		System.out.println(abduct);
	}
}
