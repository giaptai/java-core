package leetcode;

import java.util.Map;
import java.util.HashMap;
import java.util.Set;
import java.util.HashSet;

public class ContainsDuplicate {
    // solution 1 - HashMap
    public static boolean containsDuplicate(int[] nums) {
        Map<Integer, Integer> freqNum = new HashMap<>();
        for (Integer i : nums) {
            if (freqNum.containsKey(i)) {
                return true;
            }
            freqNum.put(i, 1);
        }
        return false;
    }

    // solution 2 - Set
    public static boolean containsDuplicate2(int[] nums) {
        Set<Integer> freqNum = new HashSet<>();
        for (Integer i : nums) {
            freqNum.add(i);
        }
        return freqNum.size() != nums.length;
    }

    public static void main(String[] args) {
        int[] case1 = { 1, 2, 3, 1 };
        int[] case2 = { 1, 2, 3, 4 };
        int[] case3 = { 1, 1, 1, 3, 3, 4, 3, 2, 4, 2 };

        System.out.println(containsDuplicate(case1));
        System.out.println(containsDuplicate(case2));
        System.out.println(containsDuplicate(case3));

        System.out.println(containsDuplicate2(case1));
        System.out.println(containsDuplicate2(case2));
        System.out.println(containsDuplicate2(case3));
    }
}
