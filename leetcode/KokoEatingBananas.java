package leetcode;

import java.util.Arrays;

public class KokoEatingBananas {
    public static int minEatingSpeed(int[] piles, int h) {
        int left = 1;
        int right = Arrays.stream(piles).max().getAsInt();
        int result = right;
        while (left <= right) {
            int mid = left + (right - left) / 2;
            long hours = 0;
            for (int i = 0; i < piles.length; i++) {
                hours += (piles[i] + mid - 1) / mid;
            }
            if (hours <= h) {
                result = mid;
                right = mid - 1;
            } else {
                left = mid + 1;
            }
        }
        return result;
    }

    public static void main(String[] args) {
        int[] case1 = { 1, 4, 3, 2 };
        int[] case2 ={30,11,23,4,20};
        System.out.println(minEatingSpeed(case1, 9));
        System.out.println(minEatingSpeed(case2, 6));
    }
}