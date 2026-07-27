package week3;

class A2 {
    int x = 10, y = 11;
    void methodA(B2 objB) {
        objB.var1 = x;
        objB.var2 = y;
    }
}
class B2 {
    int var1, var2;
}

class TestPassByValue2 {
    public static void main(String args[]) {
        B2 objB = new B2();
        A2 objA = new A2();
        objB.var1 = 5;
        objB.var2 = 6;
        System.out.println("var1(before)="+objB.var1);
        System.out.println("var2(before)="+objB.var2);
        
        objA.methodA(objB);
        System.out.println("var1(after)="+objB.var1);
        System.out.println("var2(after)="+objB.var2);
    }
}