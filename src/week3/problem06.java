package week3;

import java.util.Scanner;

class Palindrome {
    public static boolean isPalindrome(String s) {
        s = s.toLowerCase();
        int len = s.length();
        for(int i=0; i<len/2; i=i+1) {
            if(s.charAt(i)!=s.charAt(len-i-1) ) {
                return false;
            }
        }
        return true;
    }
}

public class problem06 {
    public static void main( String[] args ) {
        Scanner input = new Scanner(System.in);
        
        String text = input.next();
        if (Palindrome.isPalindrome(text)) {
           System.out.println("1, ["+ text + "] is a palindrome.");
        } else {
           System.out.println("0, [" + text + "]  is not a palindrome");
        }
    }
}
