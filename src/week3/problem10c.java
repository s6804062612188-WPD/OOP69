// GOD ALGORITHM, left to right sweep
package week3;

import java.util.Scanner;
import java.util.ArrayList;
import java.util.Collections;

class Event {
	int position;
	int type; //1 is the END of an UFO, 2 is the laser, 3 is the START of an UFO
	
	Event(int a, int b) {
		this.position = a;
		this.type = b;
	}
}

public class problem10c {
	public static void main(String[] args) {
		Scanner input = new Scanner(System.in);
		
		int UFOCount, laserCount;
		
		UFOCount = input.nextInt();
		laserCount = input.nextInt();
		
		ArrayList<Event> everything = new ArrayList<>();
		for (int i=0; i<UFOCount; i=i+1) {
			everything.add( new Event( input.nextInt(), 3 ) );
			everything.add( new Event( input.nextInt(), 1 ) );
		}
		
		for (int i=0; i<laserCount; i=i+1) {
			everything.add( new Event( input.nextInt(), 2 ) );
		}
		
		Collections.sort(everything, (Event a, Event b) -> {
			if (a.position != b.position)
				return Integer.compare(a.position, b.position);

			return Integer.compare(a.type, b.type);
		});
		
		int abduct = 0; int active = 0;
		for (Event e : everything) {
			if (e.type == 3) active++;
			if (e.type == 1) active--;
			if (e.type == 2) abduct += active;
		}
		System.out.println(abduct);
	}
}
