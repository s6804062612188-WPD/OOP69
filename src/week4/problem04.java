package week4;

import java.text.DecimalFormat;
import java.util.Scanner;

class ASF { // AscendSortFreq
	double[] A;
	public ASF(int n, Scanner input) {
		this.A = new double[n];
		for (int i=0; i<n; i=i+1) {
			this.A[i] = input.nextDouble();
		}
	};
	
	public static double[] AscendSort(double[] A) {
		double[] B = A.clone();
		int size = B.length;
		for(int i=0; i<size-1; i=i+1) {
			for (int j=i; j<size-1; j=j+1) {
				if (B[j] > B[j+1]) {
					double temp = B[j];
					B[j] = B[j+1];
					B[j+1] = temp;
				}
			}
		}
		
		return B;
	}
	
	public static double[] SortCommuFreq(double[] B) {
		int size = B.length;
		
		int unique=1;
		for (int i=0; i<size-1; i=i+1) {
			if (B[i] != B[i+1]) { unique++; }
		}
		
		double[] C = new double[unique];
		double cur = B[0];
		int i=0, j=0;
		int count=0;
		do {
			if (B[i] == cur) { count++; }
			else {
				C[j] = count;
				cur = B[i];
				j++;
			}
			i++;
		} while(i<size);
		C[j] = count;
		
		return C;
	}
}

public class problem04 {
	private static void printArray(double[] array) {
		DecimalFormat df = new DecimalFormat("0.######");
		
		int size = array.length;
		for(int i=0; i<size; i=i+1) {
			System.out.print( df.format(array[i]) );
			if (i != size-1) System.out.print(" ");
		}
		System.out.println();
	} 
	
	public static void main(String[] args) {
		Scanner input = new Scanner(System.in);
		ASF asf = new ASF(input.nextInt(), input);
		
		System.out.println("The array is:");
		printArray(asf.A);
		
		double[] B = ASF.AscendSort(asf.A);
		System.out.println("Sorted by ascending order:");
		printArray(B);
		
		double[] C = ASF.SortCommuFreq(B);
		System.out.println("By cumulative succession in ascending order:");
		printArray(C);
	}
}

/*
5
5 9 5 9 8
*/