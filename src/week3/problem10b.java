// ChatGPT 5 optimized
package week3;

import java.util.Scanner;
import java.util.ArrayList;
import java.util.Collections;

class UFO {
	int start;
	int end;
	
	UFO(int a, int b) {
		this.start = a;
		this.end = b;
	}
}

public class problem10b {
	public static int bound(ArrayList<Integer> arr, int target, boolean strict) {
		int left = 0;
		int right = arr.size();

		while (left < right) {
			int mid = (left + right) / 2;

			if ( (strict)? arr.get(mid)<=target : arr.get(mid)<target) left = mid + 1;
			else right = mid;
		}
		return left;
	}
	
	public static int lowerBound(ArrayList<Integer> arr, int target) {
		return bound(arr, target, false);
	}
	public static int upperBound(ArrayList<Integer> arr, int target) {
		return bound(arr, target, true);
	}
	
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
		Collections.sort(strikes);
		
		int abduct = 0;
		for (UFO u : invasion) {
			abduct += lowerBound(strikes, u.end) - upperBound(strikes, u.start);
		}
		System.out.println(abduct);
	}
}
