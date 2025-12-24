package leetcode;

import java.util.Stack;

public class EvaluateReversePolishNotation {
    public static int evalRPN(String[] tokens) {
        if (tokens.length == 0) {
            return 0;
        }
        Stack<Integer> st = new Stack<>();
        for (String i : tokens) {
            if (i.matches("^-?\\d+$")) {
                st.push(Integer.parseInt(i));
            } else if (i.equals("+")) {
                int sum = st.pop();
                sum += st.pop();
                st.push(sum);
            } else if (i.equals("-")) {
                int minus = st.pop();
                st.push(st.pop() - minus);
            } else if (i.equals("*")) {
                int multiply = st.pop();
                multiply *= st.pop();
                st.push(multiply);
            } else if (i.equals("/")) {
                int divide = st.pop();
                int divideR = st.pop() / divide;
                st.push(divideR);
            }
        }
        return st.peek();
    }

    public static void main(String[] args) {
        String[] case1 = { "2", "1", "+", "3", "*" };
        String[] case2 = { "4", "13", "5", "/", "+" };
        String[] case3 = { "10", "6", "9", "3", "+", "-11", "*", "/", "*", "17", "+", "5", "+" };
        String[] case4 = {"4","3","-"};
        System.out.println(evalRPN(case1));
    }
}
