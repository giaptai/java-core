package leetcode;

import java.lang.StringBuilder;

public class ReverseString {

    // solution 1
    public static String reverseString(String s) {
        StringBuilder sb = new StringBuilder();

        for (int i = s.length() - 1; i >= 0; i--) {
            sb.append(s.charAt(i));
        }
        return sb.toString();
    }

    // solution 2
    public static String reverseString2(String s) {
        return new StringBuilder(s).reverse().toString();
    }

    // solution 3
    public static String reverseString3(String s) {
        char[] chars = s.toCharArray();
        int left = 0, right = chars.length - 1;
        while (left < right) {
            char temp = chars[left];
            chars[left] = chars[right];
            chars[right] = temp;
            left++;
            right--;
        }
        return new String(chars);
    }

    public static void main(String[] args) {
        String case1 = "hello";
        System.out.println(reverseString(case1));
    }
}
