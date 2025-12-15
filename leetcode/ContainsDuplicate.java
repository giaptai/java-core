package leetcode;

import java.util.Map;
import java.util.HashMap;

public class ContainsDuplicate {
    public static boolean containsDuplicate(int[] nums) {
        Map<Integer, Integer> dupNum = new HashMap<>();
        for (int item : nums) {
            if (dupNum.containsKey(item)) {
                return true;
            }
            dupNum.put(item, 1);
        }
        return false;
    }

    public static void main(String[] args) {
        int[] case1 = { 1, 2, 3, 1 };
        int[] case2 = { 1, 2, 3, 4 };
        int[] case3 = { 1, 1, 1, 3, 3, 4, 3, 2, 4, 2 };

        System.out.println(containsDuplicate(case1));
        System.out.println(containsDuplicate(case2));
        System.out.println(containsDuplicate(case3));
    }
}
