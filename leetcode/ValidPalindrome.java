package leetcode;

public class ValidPalindrome {
    // solution 1
    public static boolean isPalindrome(String s) {
        String cleanedStr = s.replaceAll("[^a-zA-Z\\d]","").toUpperCase();
        int left = 0;
        int right = cleanedStr.length() - 1;
        while (left < right) {
            if (cleanedStr.charAt(left) != cleanedStr.charAt(right)) {
                return false;
            }
            left++;
            right--;
        }
        return true;
    }

    // solution 2

    public static void main(String[] args) {
        String case1 = "madam";
        String case2 = "A man, a plan, a canal – Panama";
        System.out.println(isPalindrome(case1));
        System.out.println(isPalindrome(case2));
    }
}
