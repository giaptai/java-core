package leetcode.LC2390;

import java.util.ArrayDeque;
import java.util.Deque;

public class LC2390 {
    public static String removeStars(String s) {
        Deque<Character> dq = new ArrayDeque<>();
        int i = 0;
        while (i < s.length()) {
            if (dq.isEmpty() && s.charAt(i) == '*') {
                i++;
                continue;
            } else if (s.charAt(i) != '*') {
                dq.push(s.charAt(i));
            } else if (!dq.isEmpty() && s.charAt(i) == '*') {
                dq.pop();
            }
            i++;
        }
        StringBuilder sb = new StringBuilder();
        while (!dq.isEmpty()) {
            sb.insert(0, dq.pop());
        }
        return sb.toString();
    }

    public static void main(String[] args) {
        System.out.println(removeStars("leet**cod*e"));
    }
}