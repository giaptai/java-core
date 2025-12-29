package leetcode;

import java.lang.Math; 

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

    // solution 2 - O(n) - wtf
    public static int largestRectangleArea2(int[] heights) {
        if (heights.length == 1) {
            return 1 * heights[0];
        }

        int len = heights.length;
        int[] leftH = new int[len];
        int[] rightH = new int[len];

        leftH[0] = -1;
        for (int i = 1; i < len; i++) {
            int p = i - 1;
            while (p >= 0 && heights[p] >= heights[i]) {
                p = leftH[p];
            }
            leftH[i] = p;
        }

        rightH[len - 1] = len;
        for (int j = len - 2; j >= 0; j--) {
            int p = j + 1;
            while (p < len && heights[p] >= heights[j]) {
                p = rightH[p];
            }
            rightH[j] = p;
        }
        int maxArea = 0;
        for (int k = 0; k < len; k++) {
            maxArea = Math.max(maxArea, heights[k] * (rightH[k] - leftH[k] - 1));
        }

        return maxArea;
    }

    // solution 3

    public static void main(String[] args) {
        int[] case1 = { 2, 1, 5, 6, 2, 3 };
        System.out.println(largestRectangleArea(case1));
        // System.out.println(largestRectangleArea2(case1));
    }
}
