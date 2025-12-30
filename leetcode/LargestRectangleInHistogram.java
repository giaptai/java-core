package leetcode;

import java.lang.Math;
import java.util.Deque;
import java.util.ArrayDeque;

public class LargestRectangleInHistogram {

    // solution 1 - O(n^2)
    public static int largestRectangleArea(int[] heights) {
        int len = heights.length;
        int maxArea = 0;
        for (int i = 0; i < len; i++) {
            int minH = heights[i];
            for (int j = i; j < len; j++) {
                minH = Math.min(minH, heights[j]);
                int area = minH * (j - i + 1);
                maxArea = Math.max(maxArea, area);
            }
        }
        return maxArea;
    }

    // solution 2 - O(n) - monotonic stack
    public static int largestRectangleArea2(int[] heights) {
        Deque<Integer> d = new ArrayDeque<>(); // stack stores indices in increasing height order
        int maxArea = 0;
        // i <= heights.length: add a virtual bar with height 0 at the end
        // to force all remaining bars in stack to be processe
        for (int i = 0; i <= heights.length; i++) {
            int curr_h = (i == heights.length ? 0 : heights[i]);
            // when current height < stack top:
            // the bar at stack top can't extend right anymore
            // so we pop it and calculate its max area
            while (!d.isEmpty() && curr_h < heights[d.peek()]) {
                int height = heights[d.pop()];
                // width = distance between current index and the new stack top
                // if stack empty: this bar is the smallest from index 0 to i
                int width = d.isEmpty() ? i : i - d.peek() - 1;
                maxArea = Math.max(maxArea, height * width);
            }
            d.push(i);
        }
        return maxArea;
    }

    // solution 3

    public static void main(String[] args) {
        int[] case1 = { 2, 1, 5, 6, 2, 3 };
        int[] case2 = { 1, 2, 3, 4 };
        int[] case3 = { 4, 3, 2, 1 };
        // System.out.println(largestRectangleArea(case1));
        System.out.println(largestRectangleArea2(case1));
        System.out.println(largestRectangleArea2(case2));
        System.out.println(largestRectangleArea2(case3));
    }
}
