package leetcode.LC2215;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class LC2215 {
    public static List<List<Integer>> findDifference(int[] nums1, int[] nums2) {
        Map<Integer, Integer> m = new HashMap<>();
        for (int i : nums1) {
            m.put(i, m.getOrDefault(i, 0) + 1);
        }
        List<List<Integer>> result = new ArrayList<>();
        result.add(new ArrayList<>());
        result.add(new ArrayList<>());
        Set<Integer> s2 = new HashSet<>();
        for (int j : nums2) {
            if (!m.containsKey(j) && s2.add(j)) {
                result.get(1).add(j);
            } else {
                m.put(j, 0);
            }
        }
        
        for (Map.Entry<Integer, Integer> entry : m.entrySet()) {
            if (entry.getValue() != 0) {
                result.get(0).add(entry.getKey());
            }

        }
        return result;
    }

    public static void main(String[] args) {
        // System.out.println(findDifference(new int[] { 1, 2, 3, 3 }, new int[] { 1, 1,
        // 2, 2 }));
        System.out.println(findDifference(new int[] { -80, -15, -81, -28, -61, 63, 14, -45, -35, -10 },
                new int[] { -1, -40, -44, 41, 10, -43, 69, 10, 2 }));

    }
}
