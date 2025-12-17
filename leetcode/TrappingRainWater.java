package leetcode;

import java.lang.Math;

public class TrappingRainWater {
    // solution 1 - O(n^(2))
    public static int trap(int[] height) {
        int sum = 0;
        for (int i = 1; i < height.length - 1; i++) {
            // max left
            int maxLeft = 0;
            for (int l = 0; l <= i - 1; l++) {
                maxLeft = Math.max(maxLeft, height[l]);
            }

            // max right
            int maxRight = 0;
            for (int j = i; j < height.length; j++) {
                maxRight = Math.max(maxRight, height[j]);
            }

            // calculate warter trapped
            int minH = Math.min(maxLeft, maxRight);
            int water = minH - height[i];
            if (water > 0) {
                sum += water;
            }
        }
        return sum;
    }

    // solution 2 - time complexity 0(n), space complexity 0(n)
    public static int trap2(int[] height) {
        int sum = 0;
        int n = height.length;
        int[] maxLeft = new int[n];
        int[] maxRight = new int[n];

        // find max left
        maxLeft[0] = height[0];
        for (int i = 1; i < n; i++) {
            maxLeft[i] = Math.max(maxLeft[i - 1], height[i]);
        }

        // find max right
        maxRight[n - 1] = height[n - 1];
        for (int j = n - 2; j >= 0; j--) {
            maxRight[j] = Math.max(maxRight[j + 1], height[j]);
        }

        // calculate water
        for (int k = 1; k < height.length - 1; k++) {
            int h = Math.min(maxLeft[k], maxRight[k]);
            if (h > height[k]) {
                sum += (h - height[k]);
            }
        }
        return sum;
    }

    // solution 3 - time complexity 0(n), space complexity 0(1)
    public static int trap3(int[] height) {
        int sum = 0;
        int left = 0;
        int right = height.length - 1;
        int maxL = 0;
        int maxR = 0;

        while (left < right) {
            if (height[left] < height[right]) {
                if (height[left] >= maxL) {
                    maxL = height[left];
                } else {
                    sum += maxL - height[left];
                }
                left++;
            } else {
                if (height[right] >= maxR) {
                    maxR = height[right];
                } else {
                    sum += maxR - height[right];
                }
                right--;
            }
        }
        return sum;
    }

    public static void main(String[] args) {
        int[] case1 = { 0, 2, 0, 3, 1, 0, 1, 3, 2, 1 };
        int[] case2 = { 0, 1, 0, 2, 1, 0, 1, 3, 2, 1, 2, 1 };
        int[] case3 = { 4, 2, 0, 3, 2, 5 };
        // System.out.println(trap(case1));
        // System.out.println(trap2(case1));
        // System.out.println(trap2(case2));
        // System.out.println(trap2(case3));
        System.out.println(trap3(case1));
        System.out.println(trap3(case2));
        System.out.println(trap3(case3));
    }
}
