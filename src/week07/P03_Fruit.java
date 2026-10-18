package week07;

class Fruit {}

class Apple extends Fruit {
	public void makeApple() {
		System.out.println("Making an apple.");
	}
}

class Orange extends Fruit {
	public void makeOrangeJuice() {
		System.out.println("Making orange juice.");
	}
}

class GoldenDelicious extends Apple {}
class Macintosh extends Apple {}

public class P03_Fruit {
	private static void print(String s) {
		System.out.println(s);
	}
	public static void main(String[] args) {
		Fruit fruit = new GoldenDelicious();
		Orange orange = new Orange();

		print("1. Is fruit instanceof Orange? "
				+ (fruit instanceof Orange));

		print("2. Is fruit instanceof Apple? "
				+ (fruit instanceof Apple));

		print("3. Is fruit instanceof GoldenDelicious? "
				+ (fruit instanceof GoldenDelicious));

		print("4. Is fruit instanceof Macintosh? "
				+ (fruit instanceof Macintosh));

		print("5. Is orange instanceof Orange? "
				+ (orange instanceof Orange));

		print("6. Is orange instanceof Fruit? "
				+ (orange instanceof Fruit));

		print("7. Is orange instanceof Apple? false");

		print("8. Can fruit and orange invoke makeApple()? ");
		print("fruit: Yes, because the actual object is a "
				+ "GoldenDelicious, which inherits from Apple. "
				+ "orange: No, because Orange does not define or inherit "
				+ "makeApple().");

		print("9. Can orange and fruit invoke makeOrangeJuice()? ");
		print("orange: Yes, because Orange defines the method. "
				+ "fruit: No, because the reference type Fruit does not "
				+ "declare makeOrangeJuice().");
	}
}
