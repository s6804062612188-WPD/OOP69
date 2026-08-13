package week5;

import java.util.Scanner;
import java.util.ArrayList;

public class problem01 {
	public static void main(String[] args) {
		Scanner input = new Scanner(System.in);

		System.out.println("/Press enter twice to continue the program.../");
		ArrayList<Integer> list = new ArrayList<>();
		ArrayList<Integer> filtered = new ArrayList<>();
		while (true) {
			String line = input.nextLine();
			
			if (line.isEmpty()) { break; }
			
			Scanner sc = new Scanner(line);
			while (sc.hasNextInt()) {
				int temp = sc.nextInt();
				list.add(temp);
				if (temp%2==1) { filtered.add(temp); }
			}
			sc.close();
		}
		if ( list.isEmpty() ) {
			System.out.println("You haven't added anything.");
			return;
		}
		
		double average = 0;
		int max = list.get(0); int min = list.get(0);
		for (Integer thing : list) {
			if (thing > max) max = thing;
			if (thing < min) min = thing;
			average += thing;
		}
		average /= list.size();
		System.out.printf("%.2f\n%d\n%d\n", average, max, min);
		int size = filtered.size();
		for (int i=0; i<size; i=i+1) {
			System.out.print(filtered.get(i));
			if (i!=size-1) System.out.print(" ");
		}
		System.out.println();
		
	}
}

/*
5 9 2 9 1 2 8 9 1 6
9 1 3 9 8 4 2 1 5 7
2 7 9 3 8 5 2 7 6 8
1 6 2 1 7 7 1 9 4 1
8 5 2 3 9 8 5 6 3 3
*/