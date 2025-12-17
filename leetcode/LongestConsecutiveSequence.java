package leetcode;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import java.lang.Math;

public class LongestConsecutiveSequence {

    // solution 1
    public static int longestConsecutive(int[] nums) {
        Arrays.sort(nums); // --> sort - O(nlogn)
        int maxLen = 1;
        int currLen = 1;
        for (int i = 1; i < nums.length; i++) { // iterate --> O(n)
            if (nums[i - 1] == nums[i] - 1) {
                currLen++;
            } else if (nums[i - 1] == nums[i]) {
                continue;
            } else {
                maxLen = Math.max(maxLen, currLen);
                currLen = 1;
            }
        }
        return Math.max(maxLen, currLen);
    }

    // solution 2 O(n)
    public static int longestConsecutive2(int[] nums) {
        if (nums.length == 0) {
            return 0;
        }
        // put in a set to make element distinct
        Set<Integer> uniNum = new HashSet<>();
        for (Integer i : nums) {
            uniNum.add(i);
        }
        int maxLen = 0;
        for (Integer val : uniNum) {
            // check left
            if (!uniNum.contains(val - 1)) {
                Integer numTemp = val;
                Integer len = 1;
                while (uniNum.contains(numTemp + 1)) {
                    numTemp += 1;
                    len++;
                }
                maxLen = maxLen < len ? len : maxLen;
            }
        }
        return maxLen;
    }

    public static void main(String[] args) {
        int[] case1 = { 100, 4, 200, 1, 3, 2 };
        int[] case2 = {};
        int[] case3 = { 9, 1, 4, 7, 3, -1, 0, 5, 8, -1, 6 };
        // System.out.println(longestConsecutive(case1));
        // System.out.println(longestConsecutive(case2));
        // System.out.println(longestConsecutive(case3));
        System.out.println(longestConsecutive2(case1));
        // System.out.println(longestConsecutive2(case2));
        // System.out.println(longestConsecutive2(case3));
    }
}
