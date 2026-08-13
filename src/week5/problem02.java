package week5;

import java.util.Scanner;
import java.util.ArrayList;

public class problem02 {
	public static boolean isInteger(String text) {
		try {
			Integer.parseInt(text);
			return true;
		} catch (NumberFormatException e) {
			return false;
		}
	}
	public static ArrayList<String> addStars(ArrayList<String> a) {
		ArrayList<String> ret = new ArrayList<>();
		for (String thing : a) {
			ret.add(thing);
			ret.add("*");
		}
		return ret;
	} 
	public static ArrayList<String> removeStars(ArrayList<String> a) {
		int i=0;
		while (i<a.size()) {
			if (a.get(i) != "*") {
				i++;
				a.remove(i);
			}
		}
		return a;
	} 
	
	public static void main(String[] args) {
		Scanner input = new Scanner(System.in);
		
		String line;
		ArrayList<String> list = new ArrayList<>();
		while (true) {
			line = input.nextLine();
			
			if (isInteger(line)) { break; }
			list.add(line);
		}
		int amount = Integer.parseInt(line);
		int cmd=0;
		for (int i=0; i<amount; i=i+1) {
			cmd = input.nextInt();
			if (cmd == 1) { list = addStars(list); }
			if (cmd == 2) { list = removeStars(list); }
			System.out.println(list);
		}
	}
}

/*
the
quick
brown
fox
2
1
2
*/