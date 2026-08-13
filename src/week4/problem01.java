package week4;

public class problem01 {
	public static int findMinIdx(int[] a) {
		int minIdx = 0;
		for (int k=1; k<a.length; k=k+1) {
			if (a[k] < a[minIdx]) {
				minIdx = k;
			}
		}
		return minIdx;
	}
	
	public static int findMaxIdx(int[] a) {
		int maxIdx =0;
		for (int k=1; k<a.length; k=k+1) {
			if (a[k] > a [maxIdx]) {
				maxIdx = k;
			}
		}
		return maxIdx;
	}

	public static void main(String[] args) {
		int a[] = {-128, 65, -235, 99,0,26};
		int minIndex = findMinIdx(a);
		int maxIndex = findMaxIdx(a);
		System.out.println("min value is a["+minIndex+"] = "+a[minIndex]);
		System.out.println("max value is a["+maxIndex+"] = "+a[maxIndex]);
	}
}