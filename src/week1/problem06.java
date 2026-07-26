package week1;

import java.util.Scanner;

public class problem06 {
	public static void printLine(int amount) {
		for (int s=0; s<amount; s=s+1) {
			System.out.printf("-");
		}
		System.out.println();
	}
	
	public static void main(String[] args) {
		int space = 6;
		
		Scanner input = new Scanner(System.in);
		System.out.printf("Please input the numbers of columns and rows:\t");
		int row = input.nextInt();
		int column = input.nextInt();
		int spaceAmount = (space+1)*column;
		
		System.out.printf("%" + space + "s|", "");
		for (int i=2; i<column+1; i=i+1) {
			System.out.printf("%" + space + "d|", i);
		}
		System.out.println();
		printLine(spaceAmount);
		
		for (int i=2; i<row+1; i=i+1) {
			System.out.printf("%" + space + "d|", i);
			for (int j=2; j<column+1; j=j+1) {
				System.out.printf("%" + space + "d|", i*j);
			}
			System.out.println();
			
			printLine(spaceAmount);
		}
		System.out.println();
		
	}
}
