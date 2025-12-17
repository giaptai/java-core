package leetcode;

import java.lang.Math;

public class ContainerWithMostWater {

    public static int maxArea(int[] height) {
        int maxAreas = 0;
        int left = 0;
        int right = height.length - 1;
        while (left < right) {
            int h = Math.min(height[left], height[right]);
            int areas = h * (right - left);
            maxAreas = Math.max(maxAreas, areas);
            if (height[left] < height[right]) {
                left++;
            } else {
                right--;
            }
        }
        return maxAreas;
    }

    public static int maxArea2(int[] height) {
        int maxAreas = 0;
        int left = 0;
        int right = height.length - 1;
        while (left < right) {
            int areas = 1;
            if (height[left] < height[right]) {
                areas = height[left] * (right - left);
                left++;
            } else {
                areas = height[right] * (right - left);
                right--;
            }
            maxAreas = Math.max(maxAreas, areas);
        }
        return maxAreas;
    }

    public static void main(String[] args) {
        int[] case1 = { 1, 8, 6, 2, 5, 4, 8, 3, 7 };
        int[] case2 = { 1, 1 };
        System.out.println(maxArea(case1));
        System.out.println(maxArea(case2));
    }
}
