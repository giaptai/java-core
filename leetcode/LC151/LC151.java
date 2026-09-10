package LC151;

/*
Given an input string s, reverse the order of the words.
A word is defined as a sequence of non-space characters. The words in s will be separated by at least one space.
Return a string of the words in reverse order concatenated by a single space.
Note that s may contain leading or trailing spaces or multiple spaces between two words. The returned string should only have a single space separating the words. Do not include any extra spaces.

Example 1:
Input: s = "the sky is blue"
Output: "blue is sky the"

Example 2:
Input: s = "  hello world  "
Output: "world hello"
Explanation: Your reversed string should not contain leading or trailing spaces.

Example 3:
Input: s = "a good   example"
Output: "example good a"
Explanation: You need to reduce multiple spaces between two words to a single space in the reversed string.

Constraints:
    1 <= s.length <= 104
    s contains English letters (upper-case and lower-case), digits, and spaces ' '.
    There is at least one word in s.

Follow-up: If the string data type is mutable in your language, can you solve it in-place with O(1) extra space?
*/

public class LC151 {
    public static String reverseWords(String s) {
        StringBuilder res = new StringBuilder();
        int i = s.length() - 1;
        while (i >= 0) {
            while (i >= 0 && s.charAt(i) == ' ') {
                i--;
            }
            if (i < 0)
                break;
            int end = i + 1;
            while (i >= 0 && s.charAt(i) != ' ') {
                i--;
            }
            if (res.length() > 0) {
                res.append(' ');
            }
            res.append(s, i + 1, end);
        }
        return res.toString();
    }

    public static String reverseWords1(String s) {
        String[] words = s.trim().split("\\s+");
        int l = 0;
        int r = words.length - 1;
        while (l < r) {
            String temp = words[l].trim();
            words[l] = words[r];
            words[r] = temp;
            l++;
            r--;
        }
        return String.join(" ", words);
    }

    public static String reverseWords0(String s) {
        int i = s.length() - 1;
        StringBuilder sb = new StringBuilder();
        while (i >= 0) {
            // skip spaces
            while (i >= 0 && s.charAt(i) == ' ') {
                i--;
            }
            if (i < 0) {
                break;
            }
            int end = i;
            // star of word
            while (i >= 0 && s.charAt(i) != ' ') {
                i--;
            }
            if (sb.length() > 0) {
                sb.append('_');
            }
            sb.append(s, i + 1, end + 1);
        }
        return sb.toString();
    }

    public static void main(String[] args) {
        System.out.println(reverseWords0("  hello world  "));
        // System.out.println(reverseWords0("the sky is blue"));
        // System.out.println(reverseWords0("s"));
    }
}
