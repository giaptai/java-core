package leetcode;

import java.util.Arrays;
import java.util.Stack;

public class DailyTemperatures {

    // solution 1 - O(n^2)
    public static int[] dailyTemperatures(int[] temperatures) {
        int len = temperatures.length;
        for (int i = 0; i < len - 1; i++) {
            boolean flag = true;
            for (int j = i + 1; j < len; j++) {
                if (temperatures[i] < temperatures[j]) {
                    temperatures[i] = j - i;
                    flag = true;
                    break;
                }
                flag = false;
            }
            if (!flag) {
                temperatures[i] = 0;
            }
        }
        temperatures[len - 1] = 0;
        return temperatures;
    }

    // solution 2 - Space & Time O(n)
    public static int[] dailyTemperatures2(int[] temperatures) {
        Stack<Integer> st = new Stack<>();
        int[] result = new int[temperatures.length];
        for (int i = 0; i < temperatures.length; i++) {
            while (!st.isEmpty() && temperatures[i] > temperatures[st.peek()]) {
                int idx = i - st.peek();
                result[st.pop()] = idx;
            }
            st.push(i);
        }
        return result;
    }

    public static void main(String[] args) {
        int[] case1 = { 73, 74, 75, 71, 69, 72, 76, 73 };
        int[] case2 = { 73, 74, 75, 71, 69 };
        // System.out.println(Arrays.toString(dailyTemperatures(case1)));
        System.out.println(Arrays.toString(dailyTemperatures2(case2)));
    }
}