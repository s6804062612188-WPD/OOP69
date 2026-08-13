package week4;
public class problem03 {
	public static void print (int[] a) {
		for (int thing: a) {
			System.out.print (thing +" ");
		}
		System.out.println();
	}

	public static int[] dosomething (int[] input) {
		int temp;
		for (int i=1; i<input.length; i=i+1) {
			for (int j=i; j>0; j--) {
				if (input [j] <input [j-1]) {
					temp = input [j];
					input[j] = input [j-1];
					input[j-1] = temp;
				}
			}
			System.out.print("iteration "+i+ ": ");
			print(input);
		}
		return input;
	}

	public static void main(String[] args) {
		int[] list = {1, 9, 3, 7,2};
		print(list);
		list = dosomething(list);
		print(list);
	}	
}