package week5;

import java.util.Scanner;
import java.util.ArrayList;

class ALI { // ArrayList of Integers
	public final ArrayList<Integer> list;
	public ALI(Scanner input) {
		list = new ArrayList<>();
		while (input.hasNextInt()) {
			int temp = input.nextInt();
			if (temp == 0) { break; }
			list.add(temp);
		}
	}
}

final class SortMergeList {
	public static ArrayList<Integer> intersect(ArrayList<Integer> a, ArrayList<Integer> b) {
		ArrayList<Integer> c = new ArrayList<>();
		
		int i=0, j=0;
		int aS = a.size(), bS = b.size();
		while ( i < aS && j < bS ) {
			int a1 = a.get(i);
			int b1 = b.get(j);
			if (a1 == b1) {
				c.add(a1);
				i++; j++;
			} else if (a1 < b1) {
				i++;
			} else {
				j++;
			}
		}
		return c;
	}
}

public class problem03 {
	public static void main(String[] args) {
		Scanner input = new Scanner(System.in);
		
		ALI A = new ALI(input);
		ALI B = new ALI(input);
		ArrayList<Integer> C = SortMergeList.intersect(A.list, B.list);
		for (Integer thing : C) {
			System.out.print(thing + " ");
		}
		System.out.println();
	}
}

/*
1 4 8 9 11 15 17 28 41 59 0
4 7 11 17 19 20 23 28 37 59 81 0
*/