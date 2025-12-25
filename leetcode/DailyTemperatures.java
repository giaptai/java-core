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

    // solution 2 - O(n)
    public static int[] dailyTemperatures2(int[] temperatures) {
        Stack<Integer> stack = new Stack<>();
        return temperatures;
    }

    public static void main(String[] args) {
        int[] case1 = { 73, 74, 75, 71, 69, 72, 76, 73 };
        System.out.println(Arrays.toString(dailyTemperatures(case1)));
    }
}