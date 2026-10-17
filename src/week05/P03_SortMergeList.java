package week05;

import java.util.Scanner;
import java.util.ArrayList;
import java.util.Collections;

class ALI { // ArrayList of Integers
	public final ArrayList<Integer> list;
	public ALI(Scanner input) {
		list = new ArrayList<>();
		while (input.hasNextInt()) {
			int temp = input.nextInt();
			if (temp == 0) { break; }
			list.add(temp);
		}
		Collections.sort(list);
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

public class P03_SortMergeList {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		ALI A = new ALI(sc);
		ALI B = new ALI(sc);
		
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