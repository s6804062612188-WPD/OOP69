package week3;

class A1 {
    int x = 10, y = 5;
    void methodA (int a, int b) {
        a = x;
        b = y;
    }
}
class TestPassByValue1 {
    public static void main (String args[]) {
        int var1, var2;
        A1 objA = new A1();
        var1 = 5;
        var2 = 5;
        System.out.println("var1(before) = " +var1);
        System.out.println("var2(before) = " +var2);
        objA.methodA(var1, var2);
        System.out.println("var1(after) = " + var1);
        System.out.println("var2(after) = " + var2);
    }
}