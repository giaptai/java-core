package leetcode;

import java.util.Map;
import java.util.HashMap;

public class TwoSum {
    public static int[] twoSum(int[] nums, int target) {
        Map<Integer, Integer> containNum = new HashMap<>();
        for (int i = 0; i < nums.length - 1; i++) {
            int diff = target - nums[i];
            if (containNum.containsKey(diff)) {
                return new int[] { containNum.get(diff), i };
            } else {
                containNum.put(nums[i], i);
            }
        }
        return new int[2];
    }

    public static void main(String[] args) {
        int[] case1 = { 2, 7, 11, 15 };
        int[] case2 = { 3, 2, 4 };
        int[] case3 = { 3, 3 };
        //
        System.out.println(twoSum(case1, 9));
        System.out.println(twoSum(case2, 6));
        System.out.println(twoSum(case3, 6));
    }
}
