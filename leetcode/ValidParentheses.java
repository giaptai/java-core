package leetcode;

import java.util.Stack;
import java.util.Deque;
import java.util.ArrayDeque;

public class ValidParentheses {

    // solution 1
    // Time complexity: O(n)
    // Space complexity: O(n)
    public static boolean isValid(String s) {
        Stack<Character> st = new Stack<>();
        for (int i = 0; i < s.length(); i++) {
            Character c = s.charAt(i);
            if (c == '(' || c == '[' || c == '{') {
                st.push(c);
            } else {
                if (st.size() > 0) {
                    if (st.peek() == '(' && c == ')') {
                        st.pop();
                    } else if (st.peek() == '[' && c == ']') {
                        st.pop();
                    } else if (st.peek() == '{' && c == '}') {
                        st.pop();
                    } else {
                        return false;
                    }
                } else {
                    return false;
                }
            }
        }
        return st.size() == 0;
    }

    // solution 2
    public static boolean isValid2(String s) {
        Deque<Character> qe = new ArrayDeque<>(); // it has head and tail pointers
        for (int i = 0; i < s.length(); i++) {
            Character c = s.charAt(i);
            if (c == '(' || c == '[' || c == '{') {
                qe.add(c); // this will add at the tail pointer
            } else {
                if (qe.size() > 0) {
                    if (qe.peekLast() == '(' && c == ')') {
                        qe.pollLast();
                    } else if (qe.peekLast() == '[' && c == ']') {
                        qe.pollLast();
                    } else if (qe.peekLast() == '{' && c == '}') {
                        qe.pollLast();
                    } else {
                        return false;
                    }
                } else {
                    return false;
                }
            }
        }
        return true;
    }

    public static void main(String[] args) {

    }
}