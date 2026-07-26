package week1;

import java.util.Scanner;

public class problem01 {
	public static void main(String[] args) {
		Scanner input = new Scanner(System.in);
		double fahrenheit = input.nextDouble();
		double celcius = (fahrenheit-32.0)/9.0*5.0;
		System.out.println(fahrenheit + " fahrenheit = " + celcius +  " celcius");
	}
}
	